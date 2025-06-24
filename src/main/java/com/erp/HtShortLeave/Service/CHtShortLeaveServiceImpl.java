package com.erp.HtShortLeave.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.HmCompOffDetails.Model.CHmCompOffDetailsModel;
import com.erp.HmLeavesApplications.Repository.IHmLeavesApplicationsRepository;
import com.erp.HmLeavesBalance.Model.CHmLeaveBalanceModel;
import com.erp.HmLeavesBalance.Repository.IHmLeavesBalanceRepository;
import com.erp.HtShortLeave.Model.CHtShortLeaveModel;
import com.erp.HtShortLeave.Model.CHtShortLeaveViewModel;
import com.erp.HtShortLeave.Repository.IHtShortLeaveRepository;
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
public class CHtShortLeaveServiceImpl implements IHtShortLeaveService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
    @Autowired
    IHtShortLeaveRepository iHtShortLeaveRepository;
    @Autowired
    IHmLeavesBalanceRepository iHmLeavesBalanceRepository;
    @Autowired
    IHmLeavesApplicationsRepository iHmLeavesApplicationsRepository;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(CHtShortLeaveModel cHtShortLeaveModel, boolean isApprove) {
        Map<String, Object> responce = new HashMap<>();
        int company_id = cHtShortLeaveModel.getCompany_id();
        int short_leave_transaction_id = cHtShortLeaveModel.getShort_leave_transaction_id();
        String shortLeaveDate = cHtShortLeaveModel.getShort_leave_date();
        String punchingCode = cHtShortLeaveModel.getPunch_code();
        String status = cHtShortLeaveModel.getApproval_status();
        String leave_type = cHtShortLeaveModel.getLeave_type();
        try {
            List<CHtShortLeaveModel> addShortLeaveDetails = new ArrayList<>();

            List<CEmployeesViewModel> employeeDetailsForSamePunchCode = iHmLeavesApplicationsRepository.employeeDetailsForSamePunchCode(punchingCode);
            List<String> employeeCodes = employeeDetailsForSamePunchCode.parallelStream().map(CEmployeesViewModel::getEmployee_code).collect(Collectors.toList());
            List<CHtShortLeaveModel> existingShortLeaveDetailsList = iHtShortLeaveRepository.findByPunchCodeAndEmployeeId(company_id, punchingCode, shortLeaveDate, leave_type);

            // If no existing entries found, create a new one, else update the existing one
            if (existingShortLeaveDetailsList.isEmpty() && "Pending".equals(status)) {
//          GET EMPLOYEE LIST EXCEPT CURRENT EMPLOYEEE
                List<CEmployeesViewModel> getOtherEmployeesWithSamePunchCode = employeeDetailsForSamePunchCode.stream()
                        .filter(employee -> !employee.getEmployee_code().equals(cHtShortLeaveModel.getEmployee_code())).collect(Collectors.toList());

                if (getOtherEmployeesWithSamePunchCode.size() != 0) {

                    getOtherEmployeesWithSamePunchCode.forEach(employeeCompOff -> {
                        CHtShortLeaveModel model = new CHtShortLeaveModel();

                        BeanUtils.copyProperties(cHtShortLeaveModel, model);
                        model.setEmployee_code(employeeCompOff.getEmployee_code());
                        model.setEmployee_id(employeeCompOff.getEmployee_id());

                        addShortLeaveDetails.add(model);
                    });
                }
                addShortLeaveDetails.add(cHtShortLeaveModel);    // ADD REQUESTED COM-OFF
                List<CHtShortLeaveModel> respContent = iHtShortLeaveRepository.saveAll(addShortLeaveDetails);
                responce.put("data", respContent);

            } else if ("Approved".equals(cHtShortLeaveModel.getApproval_status()) || "Rejected".equals(cHtShortLeaveModel.getApproval_status()) || "Cancelled".equals(cHtShortLeaveModel.getApproval_status())) {
                iHtShortLeaveRepository.FnupdateApproveStatus(employeeCodes, shortLeaveDate, status, company_id, cHtShortLeaveModel.getIn_time(), cHtShortLeaveModel.getApproved_by(), cHtShortLeaveModel.getTotal_hours(), cHtShortLeaveModel.getLeave_reason(), cHtShortLeaveModel.getLeave_type());

                if ("Half Day Leave".equals(cHtShortLeaveModel.getLeave_type())) {
                    List<CHmLeaveBalanceModel> getLeaveBalanceDetails = iHtShortLeaveRepository
                            .FnGetLeavesApplicationsBalanceDetails(cHtShortLeaveModel.getLeave_type_id(), employeeCodes, company_id);
                    if (getLeaveBalanceDetails != null && !getLeaveBalanceDetails.isEmpty()) {
                        // Iterate over each leave balance model
                        for (CHmLeaveBalanceModel leaveBalance : getLeaveBalanceDetails) {
                            if ("Approved".equals(status)) {
                                leaveBalance.setLeaves_taken(
                                        leaveBalance.getLeaves_taken() + 0.5);
                                leaveBalance.setClosing_balance(
                                        leaveBalance.getClosing_balance() - 0.5);
                                leaveBalance.setOpening_balance(
                                        leaveBalance.getOpening_balance() - 0.5);
                                // Save the updated leave balance model
                                iHmLeavesBalanceRepository.save(leaveBalance);
                            } else {
                                leaveBalance.setLeaves_taken(
                                        leaveBalance.getLeaves_taken() - 0.5);
                                leaveBalance.setClosing_balance(
                                        leaveBalance.getClosing_balance() + 0.5);
                                leaveBalance.setOpening_balance(
                                        leaveBalance.getOpening_balance() + 0.5);
                                // Save the updated leave balance model
                                iHmLeavesBalanceRepository.save(leaveBalance);
                            }


                        }
                    }
                }
            }


//            CHtShortLeaveModel respContent = iHtShortLeaveRepository.save(cHtShortLeaveModel);
//
//            String employee_code = respContent.getEmployee_code();
//             String approval_status = respContent.getApproval_status();
////
//////			This is for approve of leave application
//           if (isApprove) {
//               iHtShortLeaveRepository.FnupdateApproveStatus(short_leave_transaction_id,approval_status, company_id);
//              }


            responce.put("success", 1);
//            responce.put("data", respContent);
            responce.put("error", "");
            responce.put("message", status.equals("Cancelled") ? " Short Leave has been cancelled successfully!" : (status.equals("Approved") ? "Short Leave has been approved successfully!" :
                    (status.equals("Rejected") ? "Leave has been rejected!" : (isApprove ? "Record Approved successfully!..." :
                            (short_leave_transaction_id == 0 ? "Record added successfully!..." : "Record updated successfully!...")))));


        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/HmLeavesApplications/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/HmLeavesApplications/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;


    }

    @Override
    public Map<String, Object> FnDeleteRecord(int short_leave_transaction_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iHtShortLeaveRepository.FnDeleteRecord(short_leave_transaction_id, deleted_by);
            responce.put("success", 1);
        } catch (Exception e) {
            responce.put("success", 0);
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int short_leave_transaction_id, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {
            CHtShortLeaveViewModel cHtShortLeaveViewModel = iHtShortLeaveRepository.FnShowParticularRecordForUpdate(short_leave_transaction_id, company_id);
            responce.put("success", 1);
            responce.put("data", cHtShortLeaveViewModel);
            responce.put("error", "");
        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
                return responce;
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }
}
