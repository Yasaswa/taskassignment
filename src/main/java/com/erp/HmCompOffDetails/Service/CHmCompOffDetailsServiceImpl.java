package com.erp.HmCompOffDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.HmCompOffDetails.Model.CHmCompOffDetailsModel;
import com.erp.HmCompOffDetails.Model.CHmCompOffDetailsViewModel;
import com.erp.HmCompOffDetails.Repository.IHmCompOffDetailsRepository;
import com.erp.HmLeavesApplications.Repository.IHmLeavesApplicationsRepository;
import com.erp.HmLeavesBalance.Model.CHmLeaveBalanceModel;
import com.erp.HmLeavesBalance.Repository.IHmLeavesBalanceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CHmCompOffDetailsServiceImpl implements IHmCompOffDetailsService {

    @Autowired
    IHmCompOffDetailsRepository iHmCompOffDetailsRepository;

    @Autowired
    IHmLeavesBalanceRepository iHmLeavesBalanceRepository;

    @Autowired
    IHmLeavesApplicationsRepository iHmLeavesApplicationsRepository;

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Override
    @Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
    public Map<String, Object> FnAddUpdateRecord(CHmCompOffDetailsModel cHmCompOffDetailsModel) {
        Map<String, Object> response = new HashMap<>();

        int company_id = cHmCompOffDetailsModel.getCompany_id();
        try {
            List<CHmCompOffDetailsModel> addCompOffIntimationDetails = new ArrayList<>();

            String attendanceDate = cHmCompOffDetailsModel.getAtt_date_time();
            String employee_type = cHmCompOffDetailsModel.getEmployee_type();
            String punchingCode = cHmCompOffDetailsModel.getPunch_code();
            String status = cHmCompOffDetailsModel.getStatus();
            String comp_holiday_type = cHmCompOffDetailsModel.getComp_holiday_type();

            List<CEmployeesViewModel> employeeDetailsForSamePunchCode = iHmLeavesApplicationsRepository.employeeDetailsForSamePunchCode(punchingCode);

//        GET EMPLOYEE CODES
            List<String> employeeCodes = employeeDetailsForSamePunchCode.parallelStream().map(CEmployeesViewModel::getEmployee_code).collect(Collectors.toList());

            List<CHmCompOffDetailsModel> existingCompOffDetailsList = iHmCompOffDetailsRepository.findByPunchCodeAndEmployeeId(company_id, punchingCode, attendanceDate);

            // If no existing entries found, create a new one, else update the existing one
            if (existingCompOffDetailsList.isEmpty() && !"Approved".equals(status)) {
//          GET EMPLOYEE LIST EXCEPT CURRENT EMPLOYEEE
                List<CEmployeesViewModel> getOtherEmployeesWithSamePunchCode = employeeDetailsForSamePunchCode.stream()
                        .filter(employee -> !employee.getEmployee_code().equals(cHmCompOffDetailsModel.getEmployee_code())).collect(Collectors.toList());

                if (getOtherEmployeesWithSamePunchCode.size() != 0) {

                    getOtherEmployeesWithSamePunchCode.forEach(employeeCompOff -> {
                        CHmCompOffDetailsModel model = new CHmCompOffDetailsModel();

                        BeanUtils.copyProperties(cHmCompOffDetailsModel, model);
                        model.setEmployee_code(employeeCompOff.getEmployee_code());
                        model.setEmployee_id(employeeCompOff.getEmployee_id());

                        addCompOffIntimationDetails.add(model);
                    });
                }

                addCompOffIntimationDetails.add(cHmCompOffDetailsModel);    // ADD REQUESTED COM-OFF

                iHmCompOffDetailsRepository.saveAll(addCompOffIntimationDetails);
            } else if ("Approved".equals(cHmCompOffDetailsModel.getStatus()) || "Rejected".equals(cHmCompOffDetailsModel.getStatus())) {
                iHmCompOffDetailsRepository.updateApprovalStatus(employeeCodes, attendanceDate, company_id,
                        cHmCompOffDetailsModel.getApproval_remark(), cHmCompOffDetailsModel.getStatus());
            } else if (!"Approved".equals(status)) {
                response.put("message", String.format("For the date %s, the COMPOFF / HOLIDAY intimation is already available.", cHmCompOffDetailsModel.getAtt_date_time()));
                return response;
            }

            if ("Approved".equals(cHmCompOffDetailsModel.getStatus())) {
                int leaveTypeId = 0;
                if ("H".equals(comp_holiday_type)) {
                    leaveTypeId = 12; // Set leaveTypeId to 12 if holiday type is "H"
                } else {
                    leaveTypeId = 4; // Set leaveTypeId to 4 for other cases
                }
                List<CHmLeaveBalanceModel> getLeaveBalanceDetails = iHmLeavesBalanceRepository.FnGetLeavesApplicationsBalanceDetails(employee_type, leaveTypeId, employeeCodes, company_id);

                List<CHmLeaveBalanceModel> addLeaveBalanceDetails = new ArrayList<>();

                if (getLeaveBalanceDetails.size() != 0) {
                    getLeaveBalanceDetails.forEach(leaveBalance -> {
                        leaveBalance.setClosing_balance(leaveBalance.getClosing_balance() + 1);
                        addLeaveBalanceDetails.add(leaveBalance);
                    });
                }

                // Save the updated leave balance model
                iHmLeavesBalanceRepository.saveAll(addLeaveBalanceDetails);
            }


//      After all entries are saved, return a response
            long compOffId = cHmCompOffDetailsModel.getComp_off_intimation_details_id();

            if ("Approved".equals(status)) {
                response.put("message", "Record Approved successfully!...");
            } else if ("Rejected".equals(status)) {
                response.put("message", "Record Rejected successfully!...");
            } else if ("Pending".equals(status)) {
//                if (compOffId == 0) {  // New record case
                    response.put("message", "Record added successfully!...");
//                } else {  // Existing record case
//                    response.put("message", "Record Updated successfully!...");
//                }
            } else {
                response.put("message", "Record added successfully!...");
            }
//			else if ("Pending".equals(status) && compOffId != 0) {
//				response.put("message", "Record Updated successfully!...");
//			} else {
//				response.put("message", "Record added successfully!...");
//			}

            response.put("error", "");
        } catch (DataAccessException e) {
            handleDataAccessException(e, company_id, response);
            throw e; // rethrow the exception to ensure rollback
        } catch (Exception e) {
            handleGenericException(e, company_id, response);
            throw e; // rethrow the exception to ensure rollback
        }

        return response;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int comp_off_intimation_details_id) {
        Map<String, Object> responce = new HashMap<>();
        try {
            CHmCompOffDetailsViewModel cHmCompOffDetailsModel = iHmCompOffDetailsRepository
                    .FnShowParticularRecordForUpdate(company_id, comp_off_intimation_details_id);
            responce.put("success", 0);
            responce.put("data", cHmCompOffDetailsModel);
            responce.put("error", "");
        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }


    @Override
    public Map<String, Object> FnDeleteRecord(String punch_code, int companyId, String att_date_time, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iHmCompOffDetailsRepository.FnDeleteRecord(punch_code, companyId, att_date_time, deleted_by);
            responce.put("success", "1");
            responce.put("data", "");
            responce.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", "");
        }
        return responce;


    }

    private void handleDataAccessException(DataAccessException e, int company_id, Map<String, Object> response) {
        e.printStackTrace();
        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof SQLException) {
            SQLException sqlEx = (SQLException) rootCause;
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWarpingProductionOrder/FnAddUpdateRecord", sqlEx.getErrorCode(),
                    sqlEx.getMessage());
        } else {
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWarpingProductionOrder/FnAddUpdateRecord", 0, e.getMessage());
        }
        response.put("success", 0);
        response.put("data", "");
        response.put("error", e.getMessage());
    }

    private void handleGenericException(Exception e, int company_id, Map<String, Object> response) {
        e.printStackTrace();
        amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                "/api/XtWarpingProductionOrder/FnAddUpdateRecord", 0, e.getMessage());
        response.put("success", 0);
        response.put("data", "");
        response.put("error", e.getMessage());
    }
}