package com.erp.Employee.Employees.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsUserAccess.Model.CUserViewModel;
import com.erp.AmModulesFormsUserAccess.Repository.IUserViewModelRepository;
import com.erp.Contractors.Model.CContractorBankModel;
import com.erp.Designations.Model.CDesignationDeductionMappingModel;
import com.erp.Designations.Model.CDesignationEarningMappingModel;
import com.erp.Designations.Model.CDesignationsModel;
import com.erp.Designations.Repository.IDesignationDeductionMappingModelRepository;
import com.erp.Designations.Repository.IDesignationEarningMappingModelRepository;
import com.erp.Designations.Repository.IDesignationsRepository;
import com.erp.Employee.CmEmployeesSalary.Model.CCmEmployeesSalaryModel;
import com.erp.Employee.CmEmployeesSalary.Repository.ICmEmployeesSalaryRepository;
import com.erp.Employee.Employees.Model.*;
import com.erp.Employee.Employees.Repository.*;
import com.erp.Employee.Employees_Workprofile.Model.CEmployeesWorkprofileModel;
import com.erp.Employee.Employees_Workprofile.Repository.IEmployeesWorkprofileRepository;
import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Model.CHmEmployeeTypeDeductionMappingModel;
import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Model.CHmEmployeeTypeEarningMappingModel;
import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Repository.IHmEmployeeTypeDeductionMappingRepository;
import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Repository.IHmEmployeeTypeEarningMappingRepository;
import com.erp.HmLeavesBalance.Model.CHmLeaveBalanceModel;
import com.erp.HmLeavesBalance.Repository.IHmLeavesBalanceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CEmployeesServiceImpl implements IEmployeesService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IEmployeesRepository iEmployeesRepository;

    @Autowired
    ICmEmployeesSalaryRepository iCmEmployeesSalaryRepository;

    @Autowired
    IEmployeesWorkprofileRepository iEmployeesWorkprofileRepository;

    @Autowired
    IEmployeesViewRepository iEmployeesViewRepository;

    @Autowired
    IEmployeesSummaryRepository iEmployeesSummaryRepository;

    @Autowired
    IEmployeeEarningMappingModelRepository iEmployeeEarningMappingModelRepository;

    @Autowired
    IEmployeeDeductionMappingModelRepository iEmployeeDeductionMappingModelRepository;

    @Autowired
    IEmployeeDeductionMappingViewModelRepository iEmployeeDeductionMappingViewModelRepository;

    @Autowired
    IEmployeeEarningMappingViewModelRepository iEmployeeEarningMappingViewModelRepository;

    @Autowired
    IEmployeeSalaryHeadsMappingViewModelRepository iEmployeeSalaryHeadsMappingViewModelRepository;

    @Autowired
    IDesignationDeductionMappingModelRepository iDesignationDeductionMappingModelRepository;

    @Autowired
    IDesignationEarningMappingModelRepository iDesignationEarningMappingModelRepository;

    @Autowired
    IHmEmployeeTypeEarningMappingRepository iHmEmployeeTypeEarningMappingRepository;

    @Autowired
    IHmEmployeeTypeDeductionMappingRepository iHmEmployeeTypeDeductionMappingRepository;

    @Autowired
    IUserViewModelRepository iUserViewModelRepository;

    @Autowired
    IDesignationsRepository iDesignationsRepository;
    @Autowired
    IEmployeesQualificationRepository iEmployeesQualificationRepository;
    @Autowired
    IEmployeeWorkExperienceRepisitory iEmployeeWorkExperienceRepisitory;


    @Autowired
    IHmLeavesBalanceRepository iHmLeavesBalanceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${document.file.path}")
    private String filePath;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile empImageFile, boolean isApprove) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int employee_id = commonIdsObj.getInt("employee_id");
        int designation_id = commonIdsObj.getInt("designation_id");

        AtomicInteger atomicEmployeeId = new AtomicInteger(employee_id);
        AtomicInteger atomicDesignationId = new AtomicInteger(designation_id);

        JSONObject Json = jsonObject.getJSONObject("EmployeeMasterData");
        JSONObject salaryJson = jsonObject.getJSONObject("EmployeeSalaryData");
        JSONObject workprofileJson = jsonObject.getJSONObject("EmployeeWorkProfileData");
        JSONArray employeeEarnings = (JSONArray) jsonObject.get("EmployeeEarningsData");
        JSONArray employeeDeductions = (JSONArray) jsonObject.get("EmployeeDeductionData");
        JSONArray employeeQualificationData = (JSONArray) jsonObject.get("EmployeeQualificationData");
        JSONArray employeeExperienceData = (JSONArray) jsonObject.get("EmployeeExperienceData");


        try {
            CEmployeesModel responseEmployeeMaster = null;

            // Employee
            CEmployeesModel jsonModel = objectMapper.readValue(Json.toString(), CEmployeesModel.class);

            // Check User Name Exist or Not
//			CUserViewModel checkUserName = iUserViewModelRepository.getUserName(jsonModel.getUsername(), company_id,
//					jsonModel.getEmployee_code());

            CUserViewModel checkUserName = null;
            if (jsonModel.getUsername() == null || jsonModel.getUsername().isEmpty()) {
//				// Commented Because UserName is unique for all companies.(as per prashant sir told on 08-07-2024)
//				checkUserName = iUserViewModelRepository.getUserName(null, jsonModel.getCompany_id(), jsonModel.getEmployee_code());
                checkUserName = iUserViewModelRepository.getUserName(null, jsonModel.getEmployee_code());
            } else {
//				// Commented Because UserName is unique for all companies.(as per prashant sir told on 08-07-2024)
//				checkUserName = iUserViewModelRepository.getUserName(jsonModel.getUsername(), jsonModel.getCompany_id(), jsonModel.getEmployee_code());
                checkUserName = iUserViewModelRepository.getUserName(jsonModel.getUsername(), jsonModel.getEmployee_code());
            }

            if (checkUserName != null) {
                response.put("success", 0);
                response.put("error", "User Name is already exist!");
                return response;
            }

            if (jsonModel.getEmployee_id() == 0) {
                String machineEmployeeCode = jsonModel.getMachine_employee_code().isEmpty() ? null : jsonModel.getMachine_employee_code();
                String oldEmployeeCode = jsonModel.getOld_employee_code().isEmpty() ? null : jsonModel.getOld_employee_code();
                CEmployeesModel model = iEmployeesRepository.getCheck(machineEmployeeCode, oldEmployeeCode, company_id);

//				CEmployeesModel model = iEmployeesRepository.getCheck(jsonModel.getMachine_employee_code(),jsonModel.getOld_employee_code(), company_id);

                if (!"Worker".equals(jsonModel.getEmployee_type())) {
                    List<Integer> leaveTypeIds = Arrays.asList(1, 2, 3, 4, 11, 12);

                    List<CHmLeaveBalanceModel> leaveBalanceList = leaveTypeIds.stream().map(id -> {
                        CHmLeaveBalanceModel bal = new CHmLeaveBalanceModel();
                        bal.setCompany_id(jsonModel.getCompany_id());
                        bal.setFinancial_year(String.valueOf(Year.now().getValue())); // current year
                        bal.setEffective_date(String.valueOf(LocalDate.now())); // sets today's date
                        bal.setEmployee_type(jsonModel.getEmployee_type());
                        bal.setEmployee_code(jsonModel.getEmployee_code());
                        bal.setLeave_type_id(id);
                        bal.setOpening_balance(0.0);
                        bal.setLeaves_earned(0.0);
                        bal.setLeaves_taken(0.0);
                        bal.setLeaves_adjusted(0.0);
                        bal.setClosing_balance(0.0);
                        bal.setIs_active(true);
                        bal.setCreated_by(jsonModel.getCreated_by()); // or fetch from session/user
                        bal.setCreated_on(new Date());
                        return bal;
                    }).collect(Collectors.toList());

                    iHmLeavesBalanceRepository.saveAll(leaveBalanceList);
                }

                if (model != null) {
                    response.put("success", 0);
                    response.put("data", "");
                    response.put("error", "Employee Punch Code is already exist!");
                    return response;
                }
            }

            if (empImageFile != null) {
                Map<String, Object> empPhotoStoreResponse = FnStoreEmployeePhoto(jsonModel.getEmployee_code(),
                        jsonModel.getEmployee_name(), empImageFile);

                // Accessing values from qrCodeStoreResponse
                Object successValue = empPhotoStoreResponse.get("success");
                Object dataValue = empPhotoStoreResponse.get("data");
                int success = (int) successValue;
                if (success == 1) {
                    jsonModel.setImage_path((String) dataValue);
                }
            }

//			If New Employee then generate the random password and encrypt it and save it in db.
            if (jsonModel.getEmployee_id() == 0 && jsonModel.getCell_no1() != null && !jsonModel.getCell_no1().isEmpty()) {
                jsonModel.setPassword(passwordEncoder.encode(jsonModel.getCell_no1()));
            }

            responseEmployeeMaster = iEmployeesRepository.save(jsonModel);
            // Check if the employee is inactive
            if (responseEmployeeMaster != null && !responseEmployeeMaster.isIs_active()) {
                // Call FnDeleteRecord if the employee is inactive
                iEmployeesRepository.FnDeleteEmployeeRecord(responseEmployeeMaster.getEmployee_id(), responseEmployeeMaster.getModified_by());
            }
            atomicEmployeeId.set(responseEmployeeMaster.getEmployee_id());

            // Employee Salary

            CCmEmployeesSalaryModel salaryjsonModel = objectMapper.readValue(salaryJson.toString(),
                    CCmEmployeesSalaryModel.class);

            salaryjsonModel.setEmployee_id(atomicEmployeeId.get());

            CCmEmployeesSalaryModel responseEmployeeSalaryDetails = iCmEmployeesSalaryRepository.save(salaryjsonModel);

            // Employee WorkProfile

            CEmployeesWorkprofileModel workProfilejsonModel = objectMapper.readValue(workprofileJson.toString(),
                    CEmployeesWorkprofileModel.class);

            workProfilejsonModel.setEmployee_id(atomicEmployeeId.get());
            if (workProfilejsonModel.getDesignation_id() == 0) {
                CDesignationsModel designationsModel = new CDesignationsModel();
                designationsModel.setCompany_id(company_id);
                designationsModel.setDesignation_name(workProfilejsonModel.getDesignation_name());
                designationsModel.setShort_name(workProfilejsonModel.getDesignation_name());
                designationsModel.setEmployee_type(jsonModel.getEmployee_type());

                designationsModel = iDesignationsRepository.save(designationsModel);
                workProfilejsonModel.setDesignation_id(designationsModel.getDesignation_id());
                atomicDesignationId.set(designationsModel.getDesignation_id());
            }

            iEmployeesWorkprofileRepository.save(workProfilejsonModel); // save designation work profile

//			Employee Earnings
            if (!employeeEarnings.isEmpty()) {
                if (employee_id != 0)
                    iEmployeeEarningMappingModelRepository.updateEarningStatus(employee_id, company_id, jsonModel.getCreated_by());

                List<CEmployeeEarningMappingModel> employeeEarningMappings = objectMapper.readValue(
                        employeeEarnings.toString(), new TypeReference<List<CEmployeeEarningMappingModel>>() {
                        });
                employeeEarningMappings.forEach(item -> {
                    item.setEmployee_id(atomicEmployeeId.get());
                    item.setDesignation_id(atomicDesignationId.get());
                });

                iEmployeeEarningMappingModelRepository.saveAll(employeeEarningMappings);
            }

//			Employee Deductions
            if (!employeeDeductions.isEmpty()) {
                if (employee_id != 0)
                    iEmployeeDeductionMappingModelRepository.updateDeductionStatus(employee_id, company_id, jsonModel.getCreated_by());

                List<CEmployeeDeductionMappingModel> employeeDeductionMappings = objectMapper.readValue(
                        employeeDeductions.toString(), new TypeReference<List<CEmployeeDeductionMappingModel>>() {
                        });

                employeeDeductionMappings.forEach(item -> {
                    item.setEmployee_id(atomicEmployeeId.get());
                    item.setDesignation_id(atomicDesignationId.get());
                });

                iEmployeeDeductionMappingModelRepository.saveAll(employeeDeductionMappings);
            }

//			Employee Qualification 
            if (!employeeQualificationData.isEmpty()) {
//				if (employee_id != 0) {

                if (!employeeQualificationData.isEmpty()) {
                    List<CEmployeeQualificationModel> cEmployeeQualificationModel = objectMapper.readValue(
                            employeeQualificationData.toString(),
                            new TypeReference<List<CEmployeeQualificationModel>>() {
                            });

                    List<Integer> emplEduTrnIds = new ArrayList<>();

                    cEmployeeQualificationModel.forEach(emplEdu -> {
                        emplEdu.setEmployee_id(atomicEmployeeId.get());
                        emplEduTrnIds.add(emplEdu.getEmployee_academic_id());
                    });
                    String created_by = cEmployeeQualificationModel.get(0).getCreated_by();
                    int emplId = cEmployeeQualificationModel.get(0).getEmployee_id();
                    iEmployeesQualificationRepository.updateEmplOldEduDtls(emplId, emplEduTrnIds, created_by);
                    iEmployeesQualificationRepository.saveAll(cEmployeeQualificationModel);
                }
//				}
            }

            if (!employeeExperienceData.isEmpty()) {
                List<CEmployeeExperienceModel> cEmployeeExperienceModel = objectMapper.readValue(
                        employeeExperienceData.toString(),
                        new TypeReference<List<CEmployeeExperienceModel>>() {
                        });
                cEmployeeExperienceModel.forEach(items -> {
                    items.setEmployee_id(atomicEmployeeId.get());
                });
                iEmployeeWorkExperienceRepisitory.saveAll(cEmployeeExperienceModel);
            }

            response.put("success", 1);
            response.put("data", responseEmployeeMaster);
            response.put("error", "");
            response.put("message", isApprove ?
                    "Record Approved Successfully!" : employee_id == 0 ? "Record added successfully!" : "Record updated successfully!");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/employees/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/employees/FnAddUpdateRecord", 0,
                    e.getMessage());
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }

    private Map<String, Object> FnStoreEmployeePhoto(String employee_code, String employee_name, MultipartFile file)
            throws IOException {
        Map<String, Object> response = new HashMap<>();
        try {
            String docStorePath1 = filePath + "DocStores" + File.separator;
            if (!new File(docStorePath1).exists()) {
                new File(docStorePath1).mkdir();
            }
            String docStorePath = docStorePath1 + "Employees" + File.separator;
            if (!new File(docStorePath).exists()) {
                new File(docStorePath).mkdir();
            }
            String doctStorePathWithId = docStorePath + employee_code + File.separator;
            if (!new File(doctStorePathWithId).exists()) {
                new File(doctStorePathWithId).mkdir();
            }

            File directoryPath = new File(doctStorePathWithId);
            String[] contents = directoryPath.list();
            if (contents != null && contents.length != 0) {
                for (String content : contents) {
                    System.out.println("File is available");
                    File deleteExistingDoc = new File(doctStorePathWithId + content);
                    deleteExistingDoc.delete();
                }
            }
            String DocOrignalName = file.getOriginalFilename();
            String extesion = DocOrignalName.substring(DocOrignalName.lastIndexOf(".") + 0);
            String docName = employee_name + extesion;
            String storeDoc = doctStorePathWithId + docName;

            // Convert to Path object
            Path path = Paths.get(storeDoc);

            // Use normalize() to standardize separators and remove redundant elements
            Path standardizedPath = path.normalize();

            // Convert back to a string
            String resultPath = standardizedPath.toString();

            String[] storedocDb = resultPath.split(Pattern.quote("ERP_DEV" + File.separator));

            String storedocDbVal = storedocDb[1];

            File dest = new File(storeDoc);
            file.transferTo(dest);

            response.put("success", 1);
            response.put("data", storedocDbVal);
            response.put("error", "");

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());

        }

        return response;
    }

    @Override
    public Object FnDeleteRecord(int employee_id, String deleted_by) {
        iEmployeesQualificationRepository.fndeleteEmployeeQualificationDetail(employee_id, deleted_by);
        iEmployeeWorkExperienceRepisitory.fndeleteEmployeeRecord(employee_id, deleted_by);
        return iEmployeesRepository.FnDeleteRecord(employee_id, deleted_by);
    }

    @Override
    public Object FnShowAllRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Page<CEmployeesViewModel> data = iEmployeesViewRepository.FnShowAllRecords(pageable);
            String json = objectMapper.writeValueAsString(data);
            resp.put("data", json);
            resp.put("success", "1");
            resp.put("error", "");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return resp;

    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int employee_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            CEmployeesViewModel cEmployeesViewModel = iEmployeesViewRepository
                    .FnShowParticularRecordForUpdate(employee_id);

//			String decryptedPassword = PasswordManager.decrypt(cEmployeesViewModel.getPassword());
//			cEmployeesViewModel.setPassword(decryptedPassword);

            response.put("success", "1");
            response.put("data", cEmployeesViewModel);
            response.put("error", "");
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                e.printStackTrace();
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                response.put("success", "0");
                response.put("data", "");
                response.put("error", e.getMessage());
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());

            return response;
        }
        return response;
    }

    @Override
    public Object FnShowAllActiveRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Page<CEmployeesViewModel> data = iEmployeesViewRepository.FnShowAllActiveRecords(pageable);
            String json = objectMapper.writeValueAsString(data);
            resp.put("data", json);
            resp.put("success", "1");
            resp.put("error", "");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return resp;

    }

    @Override
    public Object FnShowParticularRecord(int company_id, int company_branch_id, int employee_id) {
        JSONObject resp = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            CEmployeesViewModel json = iEmployeesViewRepository.FnShowParticularRecord(company_id, company_branch_id,
                    employee_id);
            String json1 = objectMapper.writeValueAsString(json);

            resp.put("success", "1");
            resp.put("data", json1);
            resp.put("error", "");

            return resp;

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("data", "");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    @Override
    public Object FnShowAllReportRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        try {

            Page<Map<String, Object>> data = iEmployeesViewRepository.FnShowAllReportRecords(pageable);
            System.out.println(data);
            resp.put("success", "1");
            resp.put("error", "");

            return new Object[]{data, resp};

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return new Object[]{"", resp};
    }

    @Override
    public Object FnShowAllSummaryReportRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        try {
            Page<Map<String, Object>> data = iEmployeesViewRepository.FnShowAllSummaryReportRecords(pageable);
            System.out.println(data);
            resp.put("success", "1");
            resp.put("error", "");

            return new Object[]{data, resp};

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return new Object[]{"", resp};
    }

    @Override
    public Object FnShowAllSummaryRecords(Pageable pageable) {
        JSONObject resp = new JSONObject();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Page<CEmployeesSummaryModel> data = iEmployeesSummaryRepository.FnShowAllSummaryRecords(pageable);
            String json = objectMapper.writeValueAsString(data);
            resp.put("data", json);
            resp.put("success", "1");
            resp.put("error", "");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            resp.put("success", "0");
            resp.put("error", "");
            e.printStackTrace();
        }

        return resp;

    }

    @Override
    public Map<String, Object> EarningAndDeductionFnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        int employee_id = commonIdsObj.getInt("employee_id");

        JSONArray masterjson = (JSONArray) jsonObject.get("TransEarningHeadData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDeductionHeadData");

//		Employee Earning Head 
        try {
            List<CEmployeeEarningMappingModel> cEmployeeEarningMapping = objectMapper.readValue(masterjson.toString(),
                    new TypeReference<List<CEmployeeEarningMappingModel>>() {
                    });

            iEmployeeEarningMappingModelRepository.updateEarningRecord(company_id, employee_id);

            iEmployeeEarningMappingModelRepository.saveAll(cEmployeeEarningMapping);

//		Employee Deduction Head

            iEmployeeDeductionMappingModelRepository.updateDeductionRecord(company_id, employee_id);

            List<CEmployeeDeductionMappingModel> cEmployeeDeductionMappingModel = objectMapper
                    .readValue(detailsArray.toString(), new TypeReference<List<CEmployeeDeductionMappingModel>>() {
                    });

            iEmployeeDeductionMappingModelRepository.saveAll(cEmployeeDeductionMappingModel);

            response.put("success", 1);
            response.put("data", "");
            response.put("error", "");
            response.put("message", "Record added successfully!...");
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/employees/EarningAndDeductionFnAddUpdateRecord", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", "0");
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/employees/EarningAndDeductionFnAddUpdateRecord", 0, e.getMessage());
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }

    @Override
    public Map<String, Object> EarningAndDeductionFnShowAllRecords(int employee_id) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<CEmployeeEarningMappingModel> earningMappingRecords = iEmployeeEarningMappingModelRepository
                    .FnShowErningMapingRecords(employee_id);

            List<CEmployeeDeductionMappingModel> deductionMappingRecords = iEmployeeDeductionMappingModelRepository
                    .FnShowDeductionMapingRecords(employee_id);

            response.put("EarningMappingRecords", earningMappingRecords);
            response.put("DeductionMappingRecords", deductionMappingRecords);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowEmployeeSalaryHeadsMappingRecord(int employee_salary_heads_mapping_id,
                                                                      int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {

            List<CEmployeeSalaryHeadsMappingViewModel> data = iEmployeeSalaryHeadsMappingViewModelRepository
                    .FnShowEmployeeSalaryHeadMappingRecord(employee_salary_heads_mapping_id, company_id);

            response.put("data", data);
            response.put("success", 1);
            response.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());

            return response;
        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowSalaryAndWorkProfileRecords(int employee_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            CEmployeesViewModel employeeMasterRecords = iEmployeesViewRepository
                    .FnShowEmployeeMasterRecords(employee_id);

            String fileDestination = filePath + employeeMasterRecords.getImage_path();

            // Read the image file and encode it as Base64
            if (employeeMasterRecords.getImage_path() != null && !employeeMasterRecords.getImage_path().isEmpty() && new File(fileDestination).exists()) {
                Path path = Paths.get(fileDestination);
                byte[] fileContent = Files.readAllBytes(path);
                String encodedImage = Base64.getEncoder().encodeToString(fileContent);
                employeeMasterRecords.setEncodedImage(encodedImage); // Assuming you have a field for this in the model
            }

            CCmEmployeesSalaryModel employeeSalaryRecords = iCmEmployeesSalaryRepository
                    .FnShowEmployeeSalaryRecords(employee_id);

            CEmployeesWorkprofileModel employeeWorkprofileRecords = iEmployeesWorkprofileRepository
                    .FnShowEmployeeWorkProfileRecords(employee_id);

            // Define the stored procedure cal
            Map<String, Object> shiftInfo = callGetTodayShiftInfo(employee_id);
            // Convert and set the shift_id
            if (shiftInfo.get("shift_id") != null) {
                employeeWorkprofileRecords.setShift_id(((Number) shiftInfo.get("shift_id")).intValue());
            }

            List<CEmployeeEarningMappingModel> earningMappingRecords = iEmployeeEarningMappingModelRepository
                    .FnShowErningMapingRecords(employee_id);

            List<CEmployeeDeductionMappingModel> deductionMappingRecords = iEmployeeDeductionMappingModelRepository
                    .FnShowDeductionMapingRecords(employee_id);

            List<CEmployeeQualificationModel> employeesQualificationDetails = iEmployeesQualificationRepository
                    .FnShowParticularRecord(employee_id);

            List<CEmployeeExperienceModel> employeeExperiencedetails = iEmployeeWorkExperienceRepisitory
                    .FnShowEmployeeExperienceRecord(employee_id);


            // List<CEmployeeDeductionMappingModel> deductionMappingRecords =
            // iEmployeeDeductionMappingModelRepository.FnShowDeductionMapingRecords(employee_id);

            response.put("EmployeeMasterRecords", employeeMasterRecords);
            response.put("EmployeeSalaryRecords", employeeSalaryRecords);
            response.put("EmployeeWorkprofileRecords", employeeWorkprofileRecords);
            response.put("EarningMappingRecords", earningMappingRecords);
            response.put("DeductionMappingRecords", deductionMappingRecords);
            response.put("EmployeesQualificationDetails", employeesQualificationDetails);
            response.put("EmployeeExperiencedetails", employeeExperiencedetails);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public Map<String, Object> callGetTodayShiftInfo(int employeeId) {
        String storedProcedure = "{call GetTodayShiftInfo(?)}";

        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall(storedProcedure);
                    cs.setInt(1, employeeId);
                    return cs;
                },
                (CallableStatementCallback<Map<String, Object>>) cs -> {
                    cs.execute();
                    Map<String, Object> resultMap = new HashMap<>();

                    try (ResultSet rs = cs.getResultSet()) {
                        if (rs != null && rs.next()) {
                            // Assuming the result set has columns 'shift_id' and 'shift_name'
                            resultMap.put("shift_id", rs.getObject("shift_id"));
                            resultMap.put("shift_name", rs.getObject("shift_name"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return resultMap;
                }
        );
    }

    @Override
    public Map<String, Object> GetDataFromDesignationOrEmplyeeType(int company_id, int common_id,
                                                                   String earning_deduction_mapping_baseKey) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (earning_deduction_mapping_baseKey.equals("DW")) {
                List<CDesignationDeductionMappingModel> designationDeductionAllRecords = iDesignationDeductionMappingModelRepository
                        .FnShowAllDesignationDeductionRecords(company_id, common_id);
                List<CDesignationEarningMappingModel> designationEarningAllRecords = iDesignationEarningMappingModelRepository
                        .FnShowAllDesignationEarningRecords(company_id, common_id);

                response.put("ExistingDeductionRecords", designationDeductionAllRecords);
                response.put("ExistingEarningRecords", designationEarningAllRecords);
            } else {
                List<CHmEmployeeTypeDeductionMappingModel> EmployeeTypeDeductionAllRecords = iHmEmployeeTypeDeductionMappingRepository
                        .FnShowAllEmployeeTypeDeductionRecords(company_id, common_id);
                List<CHmEmployeeTypeEarningMappingModel> EmployeeTypeEarningAllRecords = iHmEmployeeTypeEarningMappingRepository
                        .FnShowAllEmployeeTypeEarningRecords(company_id, common_id);

                response.put("ExistingDeductionRecords", EmployeeTypeDeductionAllRecords);
                response.put("ExistingEarningRecords", EmployeeTypeEarningAllRecords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
