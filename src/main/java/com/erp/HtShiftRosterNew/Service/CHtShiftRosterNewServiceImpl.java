package com.erp.HtShiftRosterNew.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HtShiftRosterNew.Model.CHtShiftRosterNewModel;
import com.erp.HtShiftRosterNew.Repository.IHtShiftRosterNewRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class CHtShiftRosterNewServiceImpl implements IHtShiftRosterNewService {


    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IHtShiftRosterNewRepository iHtShiftRosterNewRepository;

    @Override
    public List<Map<String, Object>> FnShowShiftRosterEmployeeData(JSONObject jsonObject) {

        JSONObject attendanceJson = jsonObject.getJSONObject("EmployeeShiftRosterData");
        String employeeType = attendanceJson.getString("employee_type");
        int company_id = attendanceJson.getInt("company_id");

        // Initialize departmentId and subDepartmentId as null
        Integer departmentId = null;
        Integer subDepartmentId = null;

        // Check if department_id is present and not empty
        if (attendanceJson.has("department_id") && !attendanceJson.isNull("department_id")) {
            departmentId = attendanceJson.getInt("department_id");
        }

        // Check if sub_department_id is present and not empty
        if (attendanceJson.has("sub_department_id") && !attendanceJson.isNull("sub_department_id")) {
            subDepartmentId = attendanceJson.getInt("sub_department_id");
        }

        int attendanceMonth = attendanceJson.getInt("attendance_month");
        int attendanceYear = attendanceJson.getInt("attendance_year");
        int shiftId = attendanceJson.getInt("shift_id");
        List<Map<String, Object>> response = new ArrayList<>();

        try {
            List<Object[]> employeeDetails = iHtShiftRosterNewRepository.findShiftRosterEmployeeDetails(
                    employeeType, attendanceMonth, attendanceYear, shiftId, company_id);

            if (employeeDetails == null || employeeDetails.isEmpty()) {  // Check if employeeDetails is null or empty
                // Fetch employee data if employeeDetails is empty
                employeeDetails = iHtShiftRosterNewRepository.findShiftRosterEmployeeData(
                        employeeType, departmentId, subDepartmentId, shiftId, company_id);
            }

            for (Object[] detail : employeeDetails) {
                Map<String, Object> employee = new HashMap<>();
                employee.put("field_id", detail[0]);
                employee.put("employee_code", detail[1]);
                employee.put("field_name", detail[2]);
                employee.put("department_name", detail[3]);
                employee.put("department_id", detail[4]);
                employee.put("sub_department_id", detail[5]);
                employee.put("old_employee_code", detail[6]);
                response.add(employee);
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
    @Override
    public Map<String, Object> FnAddUpdateShiftRosterDetails(JSONObject jsonObject) throws JsonProcessingException {
        Map<String, Object> response = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject shiftRosterMasterData = jsonObject.getJSONObject("ShiftRosterMasterData");
        int company_id = shiftRosterMasterData.getInt("company_id");

        try {
            JSONArray shiftDetailsData = jsonObject.getJSONArray("shiftDetailsData");
            String employee_type = shiftRosterMasterData.getString("employee_type");
            // Convert JSON to model for shiftRosterMasterData
            CHtShiftRosterNewModel attendanceRequests = objectMapper.readValue(shiftRosterMasterData.toString(), CHtShiftRosterNewModel.class);
            String roster_id = shiftRosterMasterData.getString("roster_id");

            for (int i = 0; i < shiftDetailsData.length(); i++) {
                JSONObject shiftDetail = shiftDetailsData.getJSONObject(i);

                // Extract necessary fields
                int fieldId = shiftDetail.getInt("field_id");
                String employee_code = shiftDetail.getString("employee_code");
                int departmentId = shiftDetail.getInt("department_id");
                int subDepartmentId = shiftDetail.getInt("sub_department_id");

                // Retrieve existing records for the specified month and employee
                List<CHtShiftRosterNewModel> existingRecords = iHtShiftRosterNewRepository.findByEmployee_code(
                        employee_code,
                        attendanceRequests.getAttendance_month(),
                        attendanceRequests.getAttendance_year()
                );
                Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                CHtShiftRosterNewModel mergedRecord = new CHtShiftRosterNewModel();
                CHtShiftRosterNewModel addMergeRecord = new CHtShiftRosterNewModel();
                // Merge existing records with the new data
                if (!existingRecords.isEmpty()) {
                    // Assuming there's only one record per employee per month
                    mergedRecord = existingRecords.get(0);
                    BeanUtils.copyProperties(mergedRecord, addMergeRecord);

                    // Mark all existing records as deleted
                    for (CHtShiftRosterNewModel record : existingRecords) {
                        record.setIs_delete(true);
                        iHtShiftRosterNewRepository.save(record);
                        // iHtShiftRosterRepository.updateShiftRosterRecord(employee_code, attendanceRequests.getAttendance_month(), attendanceRequests.getAttendance_year(), attendanceRequests.getCreated_by());
                    }
                } else {
                    addMergeRecord.setCompany_id(attendanceRequests.getCompany_id());
                    addMergeRecord.setCompany_branch_id(attendanceRequests.getCompany_branch_id());
                    addMergeRecord.setEmployee_type(attendanceRequests.getEmployee_type());
                    addMergeRecord.setAttendance_month(attendanceRequests.getAttendance_month());
                    addMergeRecord.setAttendance_year(attendanceRequests.getAttendance_year());
                    // addMergeRecord.setProcess_date(attendanceRequests.getProcess_date());
                    addMergeRecord.setProcess_date(currentDate);
                    addMergeRecord.setFinancial_year(attendanceRequests.getFinancial_year());
                    addMergeRecord.setCreated_by(attendanceRequests.getCreated_by());
                }

                // Update employee-specific fields
                addMergeRecord.setRoster_process_id(0);
                addMergeRecord.setEmployee_id(fieldId);
                addMergeRecord.setRoster_id(roster_id);
                addMergeRecord.setEmployee_type(employee_type);
                addMergeRecord.setProcess_date(currentDate);
                addMergeRecord.setEmployee_code(employee_code);
                addMergeRecord.setDepartment_id(departmentId);
                addMergeRecord.setSub_department_id(subDepartmentId);

                // Process shift details
                Iterator<String> keys = shiftDetail.keys();
                while (keys.hasNext()) {
                    String key = keys.next();

                    // Check if the key matches the pattern \d+/\\d+
                    if (key.matches("\\d+/\\d+")) {
                        String[] parts = key.split("/");
                        int day = Integer.parseInt(parts[0]);
                        String shiftIdField = "shift_id" + day;
                        String shiftNameField = "shift_name" + day;

                        try {
                            // Get the fields using reflection
                            Field shiftIdFieldObj = mergedRecord.getClass().getDeclaredField(shiftIdField);
                            Field shiftNameFieldObj = mergedRecord.getClass().getDeclaredField(shiftNameField);

                            // Ensure accessibility of private fields
                            shiftIdFieldObj.setAccessible(true);
                            shiftNameFieldObj.setAccessible(true);

                            // Extract id and Name from the nested JSON object
                            JSONObject nestedObject = shiftDetail.getJSONObject(key);
                            int shiftId = nestedObject.getInt("id");
                            String shiftName = nestedObject.getString("Name");

                            // Set the values in the model object
                            shiftIdFieldObj.set(addMergeRecord, shiftId);
                            shiftNameFieldObj.set(addMergeRecord, shiftName);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

                // Save the merged record as a new entry
                iHtShiftRosterNewRepository.save(addMergeRecord);
            }

            // Prepare your response
            response.put("success", 1);
            response.put("error", "");
            response.put("message", "Records added successfully");

        } catch (DataAccessException e) {
            handleDataAccessException(e, company_id, response);
            throw e; // rethrow the exception to ensure rollback
        } catch (Exception e) {
            handleGenericException(e, company_id, response);
            throw e; // rethrow the exception to ensure rollback
        }

        return response;
    }

    private void handleDataAccessException(DataAccessException e, int company_id, Map<String, Object> response) {
        e.printStackTrace();
        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof SQLException) {
            SQLException sqlEx = (SQLException) rootCause;
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtCustomerMaterialIssueMaster/FnAddUpdateRecord", sqlEx.getErrorCode(),
                    sqlEx.getMessage());
        } else {
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtCustomerMaterialIssueMaster/FnAddUpdateRecord", 0, e.getMessage());
        }
        response.put("success", "0");
        response.put("data", "");
        response.put("error", e.getMessage());
    }

    private void handleGenericException(Exception e, int company_id, Map<String, Object> response) {
        e.printStackTrace();
        amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                "/api/PtCustomerMaterialIssueMaster/FnAddUpdateRecord", 0, e.getMessage());
        response.put("success", "0");
        response.put("data", "");
        response.put("error", e.getMessage());
    }

}
