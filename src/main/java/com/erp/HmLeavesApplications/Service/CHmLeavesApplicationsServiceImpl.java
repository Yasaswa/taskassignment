package com.erp.HmLeavesApplications.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.GenerateAutoNo.Model.GAutoNoModel;
import com.erp.Common.GenerateAutoNo.Service.AutoGenerateNoService;
import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.HmLeavesApplications.Model.CHmLeavesApplicationsModel;
import com.erp.HmLeavesApplications.Repository.IHmLeavesApplicationsRepository;
import com.erp.HmLeavesBalance.Model.CHmLeaveBalanceModel;
import com.erp.HmLeavesBalance.Repository.IHmLeavesBalanceRepository;
import com.erp.HmManualAttendance.Model.CHmManualAttendanceModel;
import com.erp.HmManualAttendance.Repository.IHmManualAttendanceRepository;
import com.erp.HmShiftManagement.Model.CHtAttendanceDailyModel;
import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyModel;
import com.erp.HmShiftManagement.Repository.IHtAttendanceDailyRepository;
import com.erp.HmShiftManagement.Repository.IHtAttendanceMonthlyRepository;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.SQLException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CHmLeavesApplicationsServiceImpl implements IHmLeavesApplicationsService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    AutoGenerateNoService autoGenerateNoService;
    @Autowired
    IHmLeavesApplicationsRepository iHmLeavesApplicationsRepository;
    @Autowired
    IHmManualAttendanceRepository iHmManualAttendanceRepository;
    @Autowired
    IHmLeavesBalanceRepository iHmLeavesBalanceRepository;
    @Autowired
    IHtAttendanceDailyRepository iHtAttendanceDailyRepository;
    @Autowired
    IHtAttendanceMonthlyRepository iHtAttendanceMonthlyRepository;

    //    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(CHmLeavesApplicationsModel cHmLeavesApplicationsModel,
                                                 boolean isApprove) {
        Map<String, Object> responce = new HashMap<>();
        int company_id = cHmLeavesApplicationsModel.getCompany_id();
        int leaves_transaction_id = cHmLeavesApplicationsModel.getLeaves_transaction_id();
        String startRequestDateStr = cHmLeavesApplicationsModel.getLeaves_requested_from_date();
        String endRequestDateStr = cHmLeavesApplicationsModel.getLeaves_requested_to_date();
        String startDateStr = cHmLeavesApplicationsModel.getLeaves_approved_from_date();
        String endDateStr = cHmLeavesApplicationsModel.getLeaves_approved_to_date();
        String propertyValue = iHmLeavesApplicationsRepository.getEmployeeType(cHmLeavesApplicationsModel.getEmployee_type());
        String employeeTypeGroup = iHmLeavesApplicationsRepository.getEmployeeTypeGroup(propertyValue);
        if ("SS".equals(propertyValue)) {
            employeeTypeGroup = "Staffs";
        }
        List<CHtAttendanceDailyModel> addDailyAttendanceDetails = new ArrayList<>();
        List<String> presentDates = new ArrayList<>();
        String punchingCode = cHmLeavesApplicationsModel.getPunch_code();
        String SHORT_COMPANY = cHmLeavesApplicationsModel.getShort_company();
        String SHORT_FINANCIAL_YEAR = cHmLeavesApplicationsModel.getShort_financial_year();
        String financial_year = cHmLeavesApplicationsModel.getFinancial_year();

        JSONObject json = new JSONObject();
        json.put("fieldName", "leaves_applications_id");
        json.put("company_id", company_id);
        json.put("Length", "6");
        json.put("financialShortYear", financial_year);
        json.put("column_name", "");
        json.put("tblName", "hm_leaves_applications");
        json.put("column_value", "");


        try {
            DateTimeFormatter formats = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate requestedFromDate = LocalDate.parse(startRequestDateStr, formats);
            LocalDate requestedToDate = LocalDate.parse(endRequestDateStr, formats);
//            boolean noRecordsFound = true;
            for (LocalDate date = requestedFromDate; !date.isAfter(requestedToDate); date = date.plusDays(1)) {
                // Check if the date is today or earlier
                String attendance_date = "";
                attendance_date = date.toString(); // format the date as string

                String[] dateParts = attendance_date.split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);

                List<CHtAttendanceDailyModel> existingDataDailyData = iHtAttendanceMonthlyRepository.existingDataForLeaveApplication(cHmLeavesApplicationsModel.getEmployee_code(), day, month, year, company_id);
//                List<CHtAttendanceMonthlyModel> existingRecords = iHtAttendanceMonthlyRepository.findByEmployee_code(cHmLeavesApplicationsModel.getEmployee_code(), month, year);

                if (!existingDataDailyData.isEmpty()) {
//                    noRecordsFound = false;

                    CHtAttendanceDailyModel firstRecord = existingDataDailyData.get(0); // Get the first record (if exists)
//                          && !existingRecords.isEmpty()
//                        CHtAttendanceMonthlyModel monthlyRecords = existingRecords.get(0);
//                        String monthlyRecord = "presenty" + dayOfMonth;
//                        Field presentField = monthlyRecords.getClass().getDeclaredField(monthlyRecord);
//
//                        presentField.setAccessible(true);
//                        String existingPresentValue = (String) presentField.get(monthlyRecords);

//                    if (!"AB".equals(firstRecord.getJob_type())) {
////                        presentDates.add(attendance_date);
//                        String jobTypeMessage = "";
//
//                        // Check job type and add specific messages
//                        if ("WO".equals(firstRecord.getJob_type())) {
//                            jobTypeMessage = "Weekly off";
//                            presentDates.add(attendance_date + " (" + jobTypeMessage + ")");
//                        } else if ("HD".equals(firstRecord.getJob_type())) {
//                            jobTypeMessage = "Holiday";
//                            presentDates.add(attendance_date + " (" + jobTypeMessage + ")");
//                        } else {
//                            jobTypeMessage = "Present";
//                            presentDates.add(attendance_date + " (" + jobTypeMessage + ")");
//                        }
//                    }
                    if ("PR".equals(firstRecord.getJob_type()) || "HF".equals(firstRecord.getJob_type())) {
                        String jobTypeMessage = "";

                        // Check job type and add specific messages
                        if ("HF".equals(firstRecord.getJob_type())) {
                            jobTypeMessage = "Half Day";
                            presentDates.add(attendance_date + " (" + jobTypeMessage + ")");
                        } else if ("PR".equals(firstRecord.getJob_type())) {
                            jobTypeMessage = "Present";
                            presentDates.add(attendance_date + " (" + jobTypeMessage + ")");
                        }
                    }
                }

            }
//            if (noRecordsFound &&isApprove) {
//                String message = "Leave application not applicable as you are not marked present in the daily attendance records.";
//                responce.put("message", message);
//                responce.put("success", 1);
//                responce.put("error", "");
//                return responce;
//            }else {
            if (!presentDates.isEmpty()) {
                // Return a message or handle accordingly
//                    String allPresentDates = String.join(", ", presentDates);
//                    String message = "You are already  present on the dates: " + allPresentDates + ". Leave application not Applicable.";
                String allPresentDates = String.join(", ", presentDates);
                String message = "You are already marked as " + allPresentDates + ". Leave application not applicable.";
                responce.put("message", message);
                responce.put("success", 1);
                responce.put("error", "");
            } else {

//                CHmLeavesApplicationsModel respContent = iHmLeavesApplicationsRepository.save(cHmLeavesApplicationsModel);
//               Retrieve the list of employees with the same punching code
                List<CEmployeesViewModel> employeeDetailsForSamePunchCode = iHmLeavesApplicationsRepository.employeeDetailsForSamePunchCode(punchingCode);
                List<CHmLeavesApplicationsModel> applicationsToSave = new ArrayList<>();

                if (employeeDetailsForSamePunchCode.size() > 1) {
                    List<CHmLeavesApplicationsModel> leaveApplicationsForSamePunchCode = iHmLeavesApplicationsRepository.leaveApplicationForSamePunchCode(punchingCode, startRequestDateStr, endRequestDateStr);

                    // Iterate over each employee with the same punching code
                    List<Integer> leaveApplicationsIds = leaveApplicationsForSamePunchCode.stream()
                            .map(CHmLeavesApplicationsModel::getLeaves_transaction_id)  // Extract leaves_applications_id
                            .collect(Collectors.toList());

                    // Collect them into a List

                    if (!leaveApplicationsIds.isEmpty()) {
                        // Update all existing records for this punch_code
                        for (Integer leavesApplicationsId : leaveApplicationsIds) {
                            // Fetch the existing application model using the leaves_transaction_id
                            CHmLeavesApplicationsModel existingApplication = iHmLeavesApplicationsRepository.findById(leavesApplicationsId)
                                    .orElse(null);

                            if (existingApplication != null) {
                                // Update only the fields you want to change
                                existingApplication.setLeave_reason(cHmLeavesApplicationsModel.getLeave_reason());
                                existingApplication.setLeaves_requested_from_date(cHmLeavesApplicationsModel.getLeaves_requested_from_date());
                                existingApplication.setLeaves_requested_to_date(cHmLeavesApplicationsModel.getLeaves_requested_to_date());
                                existingApplication.setLeaves_approved_from_date(cHmLeavesApplicationsModel.getLeaves_approved_from_date());
                                existingApplication.setLeaves_approved_to_date(cHmLeavesApplicationsModel.getLeaves_approved_to_date());
                                existingApplication.setLeaves_applied_days(cHmLeavesApplicationsModel.getLeaves_applied_days());
                                existingApplication.setLeaves_sanction_days(cHmLeavesApplicationsModel.getLeaves_sanction_days());
                                existingApplication.setLeaves_rejection_days(cHmLeavesApplicationsModel.getLeaves_rejection_days());
                                existingApplication.setWork_handover_id(cHmLeavesApplicationsModel.getWork_handover_id());
                                existingApplication.setApproved_date(cHmLeavesApplicationsModel.getApproved_date());
                                existingApplication.setApplied_by_id(cHmLeavesApplicationsModel.getApplied_by_id());
                                existingApplication.setLeave_status(cHmLeavesApplicationsModel.getLeave_status());
                                existingApplication.setLeave_approve_remark(cHmLeavesApplicationsModel.getLeave_approve_remark());
                                existingApplication.setApproved_by_id(cHmLeavesApplicationsModel.getApproved_by_id());
                                existingApplication.setReporting_to(cHmLeavesApplicationsModel.getReporting_to());
                                existingApplication.setSub_department_id(existingApplication.getSub_department_id());

                                // Preserve the fields you do not want to change
                                existingApplication.setEmployee_code(existingApplication.getEmployee_code());
                                existingApplication.setPunch_code(existingApplication.getPunch_code());
                                existingApplication.setEmployee_id(existingApplication.getEmployee_id());
                                existingApplication.setDepartment_id(existingApplication.getDepartment_id());

                                // Save the updated application
                                CHmLeavesApplicationsModel updatedApplication = iHmLeavesApplicationsRepository.save(existingApplication);
                                applicationsToSave.add(updatedApplication);
                            }
                        }
                    } else {
                        for (CEmployeesViewModel employeeDetails : employeeDetailsForSamePunchCode) {
                            // Create new application for the employee
                            CHmLeavesApplicationsModel newApplication = new CHmLeavesApplicationsModel();
                            BeanUtils.copyProperties(cHmLeavesApplicationsModel, newApplication);
                            String result = FnGenerateAutoNo(json);
                            newApplication.setLeaves_applications_id(SHORT_COMPANY + "/" + SHORT_FINANCIAL_YEAR + "/" + "L" + result);
                            newApplication.setEmployee_id(employeeDetails.getEmployee_id());
                            newApplication.setEmployee_code(employeeDetails.getEmployee_code());
                            newApplication.setPunch_code(employeeDetails.getOld_employee_code());
                            newApplication.setDepartment_id(employeeDetails.getDepartment_id());
                            newApplication.setSub_department_id(employeeDetails.getSub_department_id());
                            // Save the new application
                            CHmLeavesApplicationsModel savedNewApplication = iHmLeavesApplicationsRepository.save(newApplication);
                            applicationsToSave.add(savedNewApplication);
                        }

                    }
                } else {
                    CHmLeavesApplicationsModel respContent = iHmLeavesApplicationsRepository.save(cHmLeavesApplicationsModel);
                    applicationsToSave.add(respContent);
                }
//                Save all applications at once
                List<String> employeeSameCode = new ArrayList<>();
                String leave_status = null;
                int financialYear = 0;
                int leave_type_id = 0;
                String employee_type = null;
                double LeaveSanctionDays = 0.0;
                for (CHmLeavesApplicationsModel leaveApplication : applicationsToSave) {
                    employeeSameCode.add(leaveApplication.getEmployee_code());
                    if (leave_status == null) { // Check if it's the first entry
                        leave_type_id = leaveApplication.getLeave_type_id();
                        employee_type = leaveApplication.getEmployee_type();
                        leave_status = leaveApplication.getLeave_status();
                        financialYear = Integer.parseInt(leaveApplication.getFinancial_year());
                        LeaveSanctionDays = leaveApplication.getLeaves_sanction_days();
                    }

                }

//             **************************************************************
                if (isApprove) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate approveFromDate = LocalDate.parse(startDateStr, format);
                    LocalDate approveToDate = LocalDate.parse(endDateStr, format);
                    LocalDate today = LocalDate.now();
                    // Check if the startDate is within the range from startDate to endDate, and endDate is not after today

                    if ((approveFromDate.isBefore(today.plusDays(1)) || approveFromDate.isEqual(today)) &&
                            (approveToDate.isAfter(approveFromDate.minusDays(1)) || approveToDate.isEqual(approveFromDate))) {
                        // Parse the start and end dates
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        List<String> attendance_dates = new ArrayList<>();
                        String attendance_date = "";
                        List<String> employeeCodesList = new ArrayList<>();
                        String employeeCode = cHmLeavesApplicationsModel.getEmployee_code();


                        for (LocalDate date = approveFromDate; !date.isAfter(approveToDate); date = date.plusDays(1)) {
                            // Check if the date is today or earlier
                            if (!date.isAfter(today)) {

                                attendance_date = date.toString(); // format the date as string
                                String employeeCodes = cHmLeavesApplicationsModel.getEmployee_code();
                                employeeCodesList.add(employeeCodes);

                                CHtAttendanceDailyModel dailyAttendanceDetails = new CHtAttendanceDailyModel();
                                String[] dateParts = attendance_date.split("-");
                                int year = Integer.parseInt(dateParts[0]);
                                int month = Integer.parseInt(dateParts[1]);
                                int day = Integer.parseInt(dateParts[2]);

                                List<CHtAttendanceDailyModel> existingDataForLeaveApplication = iHtAttendanceMonthlyRepository.existingDataForLeaveApplication(employeeCodes, day, month, year, company_id);

                                if (!existingDataForLeaveApplication.isEmpty()) {
                                    // Access the first element from the list
                                    CHtAttendanceDailyModel firstRecord = existingDataForLeaveApplication.get(0);

                                    // Check the job_type of the first record
                                    if ("AB".equals(firstRecord.getJob_type())) {

                                        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                        LocalDate originalDate = LocalDate.parse(attendance_date, originalFormatter);

                                        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                                        attendance_date = originalDate.format(desiredFormatter);
                                        attendance_dates.add(attendance_date);

                                        // Set the job_type_code_id based on the retrieved record
                                        dailyAttendanceDetails.setJob_type_code_id(firstRecord.getJob_type_code_id());
                                        dailyAttendanceDetails.setJob_type_code_short_name(firstRecord.getJob_type_code_short_name());
                                        dailyAttendanceDetails.setShift_scheduled(firstRecord.getShift_scheduled());
                                        dailyAttendanceDetails.setShift_present(firstRecord.getShift_present());
                                        dailyAttendanceDetails.setShift_id(firstRecord.getShift_id());
                                        dailyAttendanceDetails.setCurrent_day_salary(firstRecord.getCurrent_day_salary());
                                        dailyAttendanceDetails.setRemark(firstRecord.getRemark());

                                        // Set other details
                                        dailyAttendanceDetails.setCompany_id(company_id);
                                        dailyAttendanceDetails.setCompany_branch_id(cHmLeavesApplicationsModel.getCompany_branch_id());
                                        dailyAttendanceDetails.setEmployee_code(cHmLeavesApplicationsModel.getEmployee_code());
                                        dailyAttendanceDetails.setEmployee_id(cHmLeavesApplicationsModel.getEmployee_id());
                                        dailyAttendanceDetails.setAttendance_date(day);
                                        dailyAttendanceDetails.setAttendance_year(year);
                                        dailyAttendanceDetails.setAttendance_month(month);
                                        dailyAttendanceDetails.setEmployee_type(employeeTypeGroup);
                                        dailyAttendanceDetails.setCreated_by(cHmLeavesApplicationsModel.getCreated_by());
                                        dailyAttendanceDetails.setModified_by(cHmLeavesApplicationsModel.getModified_by());
                                        dailyAttendanceDetails.setIn_time("00:00");
                                        dailyAttendanceDetails.setOut_time("00:00");
                                        dailyAttendanceDetails.setJob_type("LV");
                                        dailyAttendanceDetails.setJob_type_id(6);
                                        dailyAttendanceDetails.setWorking_minutes("00:00");
                                        dailyAttendanceDetails.setAtt_date_time(attendance_date);
                                        dailyAttendanceDetails.setAttendance_flag("M");
                                        dailyAttendanceDetails.setPresent_flag("Y");
                                        dailyAttendanceDetails.setNight_shift_present_flag("N");
                                        dailyAttendanceDetails.setWeek_off_present_flag("N");
                                        dailyAttendanceDetails.setLate_mark_flag("N");
                                        dailyAttendanceDetails.setGate_pass_flag("N");
                                        dailyAttendanceDetails.setEarly_go_flag("N");

                                        // Add to the list of daily attendance details
                                        addDailyAttendanceDetails.add(dailyAttendanceDetails);
//                                    } else if (!"AB".equals(firstRecord.getJob_type())) {
                                    } else if ("PR".equals(firstRecord.getJob_type()) || "HF".equals(firstRecord.getJob_type())) {
                                        // Accumulate the dates where the user is already present
                                        presentDates.add(attendance_date);
                                    }
                                }

                            }
                        }
                        System.out.println("All Merged Record: " + addDailyAttendanceDetails);
//                        iHmManualAttendanceRepository.updateDailyAttendanceRecord(attendance_dates, employeeTypeGroup,
//                                employeeCodesList, company_id);
//                        iHtAttendanceDailyRepository.saveAll(addDailyAttendanceDetails);
                        //*******************************************************************
                        if (employeeDetailsForSamePunchCode.size() > 1) {
                            // Iterate through the list and get employee_id and employee_code
                            Set<CHtAttendanceDailyModel> uniqueDailyAttendanceDetails = new HashSet<>();

                            for (CEmployeesViewModel employee : employeeDetailsForSamePunchCode) {
                                int employeeId = employee.getEmployee_id(); // Adjust according to your model
                                String sameEmployeeCode = employee.getEmployee_code(); // Adjust according to your model
                                employeeCodesList.add(sameEmployeeCode);

                                // Create a new entry for each employee
                                for (CHtAttendanceDailyModel originalDetail : addDailyAttendanceDetails) { // This contains the original details you want to copy
                                    // Create a new instance of CHtAttendanceDailyModel for each entry
                                    CHtAttendanceDailyModel newDetail = new CHtAttendanceDailyModel();

                                    // Copy the data from originalDetail to newDetail
                                    newDetail.setJob_type_code_id(originalDetail.getJob_type_code_id());
                                    newDetail.setJob_type_code_short_name(originalDetail.getJob_type_code_short_name());
                                    newDetail.setShift_scheduled(originalDetail.getShift_scheduled());
                                    newDetail.setShift_present(originalDetail.getShift_present());
                                    newDetail.setShift_id(originalDetail.getShift_id());
                                    newDetail.setCurrent_day_salary(originalDetail.getCurrent_day_salary());
                                    newDetail.setRemark(originalDetail.getRemark());

                                    // Set additional fields based on the current employee
                                    newDetail.setCompany_id(company_id);
                                    newDetail.setCompany_branch_id(originalDetail.getCompany_branch_id());
                                    newDetail.setEmployee_code(sameEmployeeCode); // Set employee code for the current employee
                                    newDetail.setEmployee_id(employeeId); // Set employee ID for the current employee
                                    newDetail.setAttendance_date(originalDetail.getAttendance_date());
                                    newDetail.setAttendance_year(originalDetail.getAttendance_year());
                                    newDetail.setAttendance_month(originalDetail.getAttendance_month());
                                    newDetail.setEmployee_type(employeeTypeGroup);
                                    newDetail.setCreated_by(originalDetail.getCreated_by());
                                    newDetail.setModified_by(originalDetail.getModified_by());
                                    newDetail.setIn_time("00:00");
                                    newDetail.setOut_time("00:00");
                                    newDetail.setJob_type("LV");
                                    newDetail.setJob_type_id(6);
                                    newDetail.setWorking_minutes("00:00");
                                    newDetail.setAtt_date_time(originalDetail.getAtt_date_time());
                                    newDetail.setAttendance_flag("M");
                                    newDetail.setPresent_flag("Y");
                                    newDetail.setNight_shift_present_flag("N");
                                    newDetail.setWeek_off_present_flag("N");
                                    newDetail.setLate_mark_flag("N");
                                    newDetail.setGate_pass_flag("N");
                                    newDetail.setEarly_go_flag("N");

                                    // Add the new entry to the list
                                    uniqueDailyAttendanceDetails.add(newDetail);
                                }
//                                    }
//                                }
                            }
                            List<CHtAttendanceDailyModel> finalDailyAttendanceDetails = new ArrayList<>(uniqueDailyAttendanceDetails);
                            // Now call the repository methods after all entries are updated
                            iHmManualAttendanceRepository.updateDailyAttendanceRecord(attendance_dates, employeeTypeGroup, employeeCodesList, company_id);
                            iHtAttendanceDailyRepository.saveAll(finalDailyAttendanceDetails);
                        } else {
                            iHmManualAttendanceRepository.updateDailyAttendanceRecord(attendance_dates, employeeTypeGroup,
                                    employeeCodesList, company_id);
                            iHtAttendanceDailyRepository.saveAll(addDailyAttendanceDetails);
                        }

                        //************monthly attendance implementation*******************************************************

                        String[] dateParts = attendance_date.split("-");
                        int year = Integer.parseInt(dateParts[0]);
                        int month = Integer.parseInt(dateParts[1]);
                        int day = Integer.parseInt(dateParts[2]);
                        YearMonth yearMonth = YearMonth.of(year, month);

                        // Get the number of days in the month
                        int monthDays = yearMonth.lengthOfMonth();
                        CEmployeesViewModel employeeDetails = iHmLeavesApplicationsRepository.getEmployeeDetails(employeeCode);

                        List<CHtAttendanceMonthlyModel> existingRecords = iHtAttendanceMonthlyRepository.findByEmployee_code(employeeCode, month, year);
                        CHtAttendanceMonthlyModel mergedRecord;
                        CHtAttendanceMonthlyModel allmergedRecord = new CHtAttendanceMonthlyModel();

                        // Check if existing records are found
                        if (!existingRecords.isEmpty()) {
                            // Assuming there's only one record per employee per month
                            mergedRecord = existingRecords.get(0);
                            allmergedRecord = existingRecords.get(0);
                        } else {
                            // Initialize new record with required fields
                            mergedRecord = new CHtAttendanceMonthlyModel();

                            mergedRecord.setCompany_id(company_id);
                            mergedRecord.setCompany_branch_id(cHmLeavesApplicationsModel.getCompany_branch_id());
                            mergedRecord.setEmployee_code(cHmLeavesApplicationsModel.getEmployee_code());
                            mergedRecord.setDepartment_id(employeeDetails.getDepartment_id());
                            mergedRecord.setSub_department_id(employeeDetails.getSub_department_id());
                            mergedRecord.setEmployee_id(cHmLeavesApplicationsModel.getEmployee_id());
                            mergedRecord.setAttendance_year(year);
                            mergedRecord.setAttendance_month(month);
                            mergedRecord.setEmployee_type(employeeTypeGroup);
                            mergedRecord.setCreated_by(cHmLeavesApplicationsModel.getCreated_by());
                            mergedRecord.setModified_by(cHmLeavesApplicationsModel.getModified_by());
                            mergedRecord.setProcess_date(new Date());
                            mergedRecord.setAttendance_flag("M");
                            mergedRecord.setMonth_days(monthDays);
                        }

                        // Initialize counters
                        double absent_days = 0;
                        double leaves_days = 0;
                        double total_salary_days = 0;

                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                        for (LocalDate date = approveFromDate; !date.isAfter(approveToDate); date = date.plusDays(1)) {
                            // Check if the date is today or earlier
                            if (!date.isAfter(today)) {
                                int dayOfMonth = date.getDayOfMonth();

                                String presentColumnName = "presenty" + dayOfMonth;
                                String inOutTimeColumnName = "in_out_time" + dayOfMonth;

                                try {
                                    // Get the fields using reflection
                                    Field presentField = mergedRecord.getClass().getDeclaredField(presentColumnName);
                                    Field inOutTimeField = mergedRecord.getClass().getDeclaredField(inOutTimeColumnName);

                                    // Ensure fields are accessible
                                    presentField.setAccessible(true);
                                    inOutTimeField.setAccessible(true);
                                    // Retrieve existing values
                                    String existingPresentValue = (String) presentField.get(mergedRecord);
                                    // Apply conditions based on existing data
                                    if ("AB".equals(existingPresentValue)) {
                                        // Increment or decrement counters
                                        leaves_days++;
                                        absent_days--;
                                        total_salary_days++;

                                        // Set values for present and inOutTime fields
                                        presentField.set(mergedRecord, "LV"); // Set present value
                                        inOutTimeField.set(mergedRecord, "00:00/00:00"); // Set inOutTime value

                                        try {
                                            Field leavesDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("leaves_days");
                                            leavesDaysField.setAccessible(true);
                                            Field absentDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("absent_days");
                                            absentDaysField.setAccessible(true);
                                            Field totalSalaryDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("total_salary_days");
                                            totalSalaryDaysField.setAccessible(true);

                                            // Retrieve existing values from allmergedRecord
                                            Double existingLeavesDays = (Double) leavesDaysField.get(allmergedRecord);
                                            Double existingAbsentDays = (Double) absentDaysField.get(allmergedRecord);
                                            Double existingTotalSalaryDays = (Double) totalSalaryDaysField.get(allmergedRecord);

                                            // Apply your dynamic adjustment logic
                                            double adjustment = 1.0;  // Example adjustment value, modify as needed

                                            // Update values dynamically
                                            leaves_days = existingLeavesDays + adjustment;
                                            absent_days = existingAbsentDays - adjustment;
                                            total_salary_days = existingTotalSalaryDays + adjustment;

                                            // Set updated values back to fields
                                            leavesDaysField.set(allmergedRecord, leaves_days);
                                            absentDaysField.set(allmergedRecord, absent_days);
                                            totalSalaryDaysField.set(allmergedRecord, total_salary_days);
                                        } catch (NoSuchFieldException | IllegalAccessException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        // Set other fields
                        allmergedRecord.setCompany_id(company_id);
                        allmergedRecord.setCompany_branch_id(cHmLeavesApplicationsModel.getCompany_branch_id());
                        allmergedRecord.setEmployee_code(cHmLeavesApplicationsModel.getEmployee_code());
                        allmergedRecord.setDepartment_id(employeeDetails.getDepartment_id());
                        allmergedRecord.setSub_department_id(employeeDetails.getSub_department_id());
                        allmergedRecord.setEmployee_id(cHmLeavesApplicationsModel.getEmployee_id());
                        allmergedRecord.setAttendance_year(year);
                        allmergedRecord.setAttendance_month(month);
                        allmergedRecord.setEmployee_type(employeeTypeGroup);
                        allmergedRecord.setCreated_by(cHmLeavesApplicationsModel.getCreated_by());
                        allmergedRecord.setModified_by(cHmLeavesApplicationsModel.getModified_by());
                        allmergedRecord.setProcess_date(new Date());
                        allmergedRecord.setAttendance_flag("M");
                        allmergedRecord.setMonth_days(monthDays);

                        System.out.println("All Merged Record: " + allmergedRecord);
//                        iHtAttendanceMonthlyRepository.save(allmergedRecord);
                        if (employeeDetailsForSamePunchCode.size() > 1) {
                            // Retrieve existing records for each employee
                            for (CEmployeesViewModel employee : employeeDetailsForSamePunchCode) {
                                String sameEmployeeCode = employee.getEmployee_code();
                                List<CHtAttendanceMonthlyModel> monthlySamePunchRecords = iHtAttendanceMonthlyRepository.findByEmployee_code(sameEmployeeCode, month, year);

                                if (!monthlySamePunchRecords.isEmpty()) {
                                    // Iterate over each existing record
                                    for (CHtAttendanceMonthlyModel existingRecord : monthlySamePunchRecords) {
                                        // Update the existing record with fields from allmergedRecord
                                        existingRecord.setCompany_id(allmergedRecord.getCompany_id());
                                        existingRecord.setCompany_branch_id(allmergedRecord.getCompany_branch_id());
                                        existingRecord.setEmployee_code(employee.getEmployee_code());
                                        existingRecord.setDepartment_id(employee.getDepartment_id());
                                        existingRecord.setSub_department_id(employee.getSub_department_id());
                                        existingRecord.setEmployee_id(employee.getEmployee_id());
                                        existingRecord.setAttendance_year(allmergedRecord.getAttendance_year());
                                        existingRecord.setAttendance_month(allmergedRecord.getAttendance_month());
                                        existingRecord.setEmployee_type(allmergedRecord.getEmployee_type());
                                        existingRecord.setCreated_by(allmergedRecord.getCreated_by());
                                        existingRecord.setModified_by(allmergedRecord.getModified_by());
                                        existingRecord.setProcess_date(allmergedRecord.getProcess_date());
                                        existingRecord.setAttendance_flag(allmergedRecord.getAttendance_flag());
                                        existingRecord.setMonth_days(allmergedRecord.getMonth_days());
                                        existingRecord.setPresent_days(allmergedRecord.getPresent_days());
                                        existingRecord.setOt_days(allmergedRecord.getOt_days());
                                        existingRecord.setLeaves_days(allmergedRecord.getLeaves_days());
                                        existingRecord.setOd_days(allmergedRecord.getOd_days());
                                        existingRecord.setHalf_days(allmergedRecord.getHalf_days());
                                        existingRecord.setHoliday_days(allmergedRecord.getHoliday_days());
                                        existingRecord.setWeek_off_days(allmergedRecord.getWeek_off_days());
                                        existingRecord.setCoff_days(allmergedRecord.getCoff_days());
                                        existingRecord.setAbsent_days(allmergedRecord.getAbsent_days());
                                        existingRecord.setTotal_salary_days(allmergedRecord.getTotal_salary_days());
                                        existingRecord.setMonthly_hours(allmergedRecord.getMonthly_hours());
                                        existingRecord.setNight_days(allmergedRecord.getNight_days());
                                        existingRecord.setTotal_latemarks(allmergedRecord.getTotal_latemarks());
                                        existingRecord.setTotal_latemarks_days(allmergedRecord.getTotal_latemarks_days());

                                        // Set the remaining time and present fields
                                        existingRecord.setIn_out_time1(allmergedRecord.getIn_out_time1());
                                        existingRecord.setPresenty1(allmergedRecord.getPresenty1());
                                        existingRecord.setIn_out_time2(allmergedRecord.getIn_out_time2());
                                        existingRecord.setPresenty2(allmergedRecord.getPresenty2());
                                        existingRecord.setIn_out_time3(allmergedRecord.getIn_out_time3());
                                        existingRecord.setPresenty3(allmergedRecord.getPresenty3());
                                        existingRecord.setIn_out_time4(allmergedRecord.getIn_out_time4());
                                        existingRecord.setPresenty4(allmergedRecord.getPresenty4());
                                        existingRecord.setIn_out_time5(allmergedRecord.getIn_out_time5());
                                        existingRecord.setPresenty5(allmergedRecord.getPresenty5());
                                        existingRecord.setIn_out_time6(allmergedRecord.getIn_out_time6());
                                        existingRecord.setPresenty6(allmergedRecord.getPresenty6());
                                        existingRecord.setIn_out_time7(allmergedRecord.getIn_out_time7());
                                        existingRecord.setPresenty7(allmergedRecord.getPresenty7());
                                        existingRecord.setIn_out_time8(allmergedRecord.getIn_out_time8());
                                        existingRecord.setPresenty8(allmergedRecord.getPresenty8());
                                        existingRecord.setIn_out_time9(allmergedRecord.getIn_out_time9());
                                        existingRecord.setPresenty9(allmergedRecord.getPresenty9());
                                        existingRecord.setIn_out_time10(allmergedRecord.getIn_out_time10());
                                        existingRecord.setPresenty10(allmergedRecord.getPresenty10());
                                        existingRecord.setIn_out_time11(allmergedRecord.getIn_out_time11());
                                        existingRecord.setPresenty11(allmergedRecord.getPresenty11());
                                        existingRecord.setIn_out_time12(allmergedRecord.getIn_out_time12());
                                        existingRecord.setPresenty12(allmergedRecord.getPresenty12());
                                        existingRecord.setIn_out_time13(allmergedRecord.getIn_out_time13());
                                        existingRecord.setPresenty13(allmergedRecord.getPresenty13());
                                        existingRecord.setIn_out_time14(allmergedRecord.getIn_out_time14());
                                        existingRecord.setPresenty14(allmergedRecord.getPresenty14());
                                        existingRecord.setIn_out_time15(allmergedRecord.getIn_out_time15());
                                        existingRecord.setPresenty15(allmergedRecord.getPresenty15());
                                        existingRecord.setIn_out_time16(allmergedRecord.getIn_out_time16());
                                        existingRecord.setPresenty16(allmergedRecord.getPresenty16());
                                        existingRecord.setIn_out_time17(allmergedRecord.getIn_out_time17());
                                        existingRecord.setPresenty17(allmergedRecord.getPresenty17());
                                        existingRecord.setIn_out_time18(allmergedRecord.getIn_out_time18());
                                        existingRecord.setPresenty18(allmergedRecord.getPresenty18());
                                        existingRecord.setIn_out_time19(allmergedRecord.getIn_out_time19());
                                        existingRecord.setPresenty19(allmergedRecord.getPresenty19());
                                        existingRecord.setIn_out_time20(allmergedRecord.getIn_out_time20());
                                        existingRecord.setPresenty20(allmergedRecord.getPresenty20());
                                        existingRecord.setIn_out_time21(allmergedRecord.getIn_out_time21());
                                        existingRecord.setPresenty21(allmergedRecord.getPresenty21());
                                        existingRecord.setIn_out_time22(allmergedRecord.getIn_out_time22());
                                        existingRecord.setPresenty22(allmergedRecord.getPresenty22());
                                        existingRecord.setIn_out_time23(allmergedRecord.getIn_out_time23());
                                        existingRecord.setPresenty23(allmergedRecord.getPresenty23());
                                        existingRecord.setIn_out_time24(allmergedRecord.getIn_out_time24());
                                        existingRecord.setPresenty24(allmergedRecord.getPresenty24());
                                        existingRecord.setIn_out_time25(allmergedRecord.getIn_out_time25());
                                        existingRecord.setPresenty25(allmergedRecord.getPresenty25());
                                        existingRecord.setIn_out_time26(allmergedRecord.getIn_out_time26());
                                        existingRecord.setPresenty26(allmergedRecord.getPresenty26());
                                        existingRecord.setIn_out_time27(allmergedRecord.getIn_out_time27());
                                        existingRecord.setPresenty27(allmergedRecord.getPresenty27());
                                        existingRecord.setIn_out_time28(allmergedRecord.getIn_out_time28());
                                        existingRecord.setPresenty28(allmergedRecord.getPresenty28());
                                        existingRecord.setIn_out_time29(allmergedRecord.getIn_out_time29());
                                        existingRecord.setPresenty29(allmergedRecord.getPresenty29());
                                        existingRecord.setIn_out_time30(allmergedRecord.getIn_out_time30());
                                        existingRecord.setPresenty30(allmergedRecord.getPresenty30());
                                        existingRecord.setIn_out_time31(allmergedRecord.getIn_out_time31());
                                        existingRecord.setPresenty31(allmergedRecord.getPresenty31());

                                        existingRecord.setIs_active(allmergedRecord.isIs_active());
                                        existingRecord.setIs_delete(allmergedRecord.isIs_delete());
                                        existingRecord.setRemark(allmergedRecord.getRemark());

                                        // Set the modified date
                                        existingRecord.setModified_on(new Date()); // Assuming you want to update the modified date
                                    }

                                    // Save all updated records in one batch
                                    iHtAttendanceMonthlyRepository.saveAll(monthlySamePunchRecords);
                                }
                            }
                        } else {
                            // Save the single record if no employee details to update
                            iHtAttendanceMonthlyRepository.save(allmergedRecord);
                        }
                    }
                    //********************************************************************

//                        if (leave_type_id == 4) {
////                        double sanctionDays = respContent.getLeaves_sanction_days();
//                            // Retrieve all records for the specified year
//                            List<CHtAttendanceMonthlyModel> allRecords = iHtAttendanceMonthlyRepository.findByEmployeeCodeAndAttendanceYear(employeeSameCode, financialYear);
//
////                     Group records by employee code
//                            Map<String, List<CHtAttendanceMonthlyModel>> recordsByEmployee = allRecords.stream()
//                                    .collect(Collectors.groupingBy(CHtAttendanceMonthlyModel::getEmployee_code));
//
////                          Retrieve all records for the specified employee and year
//                            for (Map.Entry<String, List<CHtAttendanceMonthlyModel>> entry : recordsByEmployee.entrySet()) {
//                                String employeeCode = entry.getKey();
//                                List<CHtAttendanceMonthlyModel> employeeRecords = entry.getValue();
//
//                                // Sort records by month
//                                employeeRecords.sort(Comparator.comparing(CHtAttendanceMonthlyModel::getAttendance_month));
//
//                                // Initialize sanctionDays for the current employee (assuming it might be different per employee)
//                                double sanctionDays = LeaveSanctionDays;
//
//                                // Reduce sanctionDays from coff_days starting from the least month
//                                for (CHtAttendanceMonthlyModel attendance : employeeRecords) {
//                                    if (sanctionDays <= 0) {
//                                        break;
//                                    }
//
//                                    double currentCoffDays = attendance.getCoff_days();
//
//                                    if (currentCoffDays > 0) {
//                                        if (sanctionDays >= currentCoffDays) {
//                                            sanctionDays -= currentCoffDays;
//                                            attendance.setCoff_days(0);
//                                        } else {
//                                            attendance.setCoff_days(currentCoffDays - sanctionDays);
//                                            sanctionDays = 0;
//                                        }
//                                    }
//                                }
//                            }
//
////                     After processing, save all updated records
//                            iHtAttendanceMonthlyRepository.saveAll(allRecords);
//                        }

                    List<CHmLeaveBalanceModel> getLeaveBalanceDetails = iHmLeavesBalanceRepository
                            .FnGetLeavesApplicationsBalanceDetails(employee_type, leave_type_id, employeeSameCode, company_id);
////           ********************  SANDWICH FUNCTIONALITY GET HOLIDAY AND WEEKLY OF ***********************************//////////
//
//                    DateTimeFormatter dateformats = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//                    LocalDate approveFromDt = LocalDate.parse(startDateStr, dateformats);
//                    LocalDate approveToDt = LocalDate.parse(endDateStr, dateformats);
//                    int weeklyOffCount = 0;
//                    int overlapCount = 0;
 //                   int adjustedHolidayCount = 0;
//                    // Get the employee's weekly off day, if available
////           ********************  SANDWICH FUNCTIONALITY GET HOLIDAY AND WEEKLY OF ***********************************//////////


// Subtract the weekly off count from the holiday count


                    if (getLeaveBalanceDetails != null && !getLeaveBalanceDetails.isEmpty()) {
                        // Iterate over each leave balance model
                        for (CHmLeaveBalanceModel leaveBalance : getLeaveBalanceDetails) {
                            // Only perform updates if leave_type_id is not 10
                            if (leave_type_id != 10 && leave_type_id != 11) {
//                                // Update the leaves_taken and closing_balance fields
//                                // MANAGE LEAVE BALANCE FOR SANDWICH
//                                if ("Yes".equals(cHmLeavesApplicationsModel.getLeave_sandwich())) {
//                                    adjustedHolidayCount = 0;
//                                } else {
//                                    Optional<String> weeklyOffName = employeeDetailsForSamePunchCode.stream()
//                                            .findFirst()
//                                            .map(CEmployeesViewModel::getWeeklyoff_name);
//
////                  If a weekly off day is present, convert it to DayOfWeek
//                                    DayOfWeek weeklyOffDay = weeklyOffName.map(off -> DayOfWeek.valueOf(off.toUpperCase())).orElse(null);
//
////                          Get all holidays
//                                    List<CXmProductionHolidayModel> holidays = iHmLeavesApplicationsRepository.findAllHolidays();
//                                    Set<LocalDate> holidaysDate = holidays.stream()
//                                            .map(holiday -> LocalDate.parse(holiday.getProduction_holiday_date(), dateformats))
//                                            .collect(Collectors.toSet());
//
////                            Count holidays in the range between approveFromDt and approveToDt
//                                    int holidayCount = (int) holidaysDate.stream()
//                                            .filter(holidayDate -> (holidayDate.isEqual(approveFromDt) ||
//                                                    holidayDate.isEqual(approveToDt) ||
//                                                    (holidayDate.isAfter(approveFromDt) && holidayDate.isBefore(approveToDt))))
//                                            .count();
//
//// Initialize variables for counting weekly offs and overlapping holiday/weekly off days
//
//                                    if (weeklyOffDay != null) {
//                                        LocalDate currentDate = approveFromDt;
//                                        while (!currentDate.isAfter(approveToDt)) {
//                                            boolean isWeeklyOff = currentDate.getDayOfWeek() == weeklyOffDay;
//                                            boolean isHoliday = holidaysDate.contains(currentDate);
//
//                                            // Check if the current day is both a weekly off and a holiday
//                                            if (isWeeklyOff && isHoliday) {
//                                                overlapCount++; // Track overlap between weekly off and holiday
//                                            } else if (isWeeklyOff) {
//                                                weeklyOffCount++; // Only count as weekly off if it's not also a holiday
//                                            }
//
//                                            currentDate = currentDate.plusDays(1);
//                                        }
//                                    }
//                                    adjustedHolidayCount = holidayCount - weeklyOffCount;
//                                }
//
//                                leaveBalance.setLeaves_taken(
//                                        leaveBalance.getLeaves_taken() + (LeaveSanctionDays - adjustedHolidayCount));
//                                leaveBalance.setClosing_balance(
//                                        leaveBalance.getClosing_balance() - (LeaveSanctionDays - adjustedHolidayCount));
////           ********************  SANDWICH FUNCTIONALITY GET HOLIDAY AND WEEKLY OF ***********************************//////////


                                leaveBalance.setLeaves_taken(
                                        leaveBalance.getLeaves_taken() + LeaveSanctionDays);
                                leaveBalance.setClosing_balance(
                                        leaveBalance.getClosing_balance() - LeaveSanctionDays);
                                leaveBalance.setOpening_balance(
                                        leaveBalance.getOpening_balance() - LeaveSanctionDays);
                                // Save the updated leave balance model
                                iHmLeavesBalanceRepository.save(leaveBalance);
                            }
                        }
                    }
                }
                // Construct the primary message based on leave_status and other conditions
                String primaryMessage = leave_status.equals("Cancelled") ? "Leave has been cancelled successfully!" :
                        leave_status.equals("Approved") ? "Leave has been approved successfully!" :
                                leave_status.equals("Rejected") ? "Leave has been rejected!" :
                                        isApprove ? "Record Approved successfully!..." :
                                                leaves_transaction_id == 0 ? "Record added successfully!..." :
                                                        "Record updated successfully!...";

                if (!presentDates.isEmpty()) {
                    String allPresentDates = String.join(", ", presentDates);
                    // Append the presentDates message to the primary message
                    primaryMessage += " Employee is already present on the: " + allPresentDates + ".";
                }
                responce.put("message", primaryMessage);
                responce.put("success", 1);
//                responce.put("data", respContent);
                responce.put("error", "");
            }
//            }
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
    public Map<String, Object> FnDeleteRecord(String punch_code, String leaves_requested_from_date, String leaves_requested_to_date, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iHmLeavesApplicationsRepository.FnDeleteRecord(punch_code, leaves_requested_from_date, leaves_requested_to_date, deleted_by);
            responce.put("success", 1);
        } catch (Exception e) {
            responce.put("success", 0);
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int leaves_transaction_id) {
        Map<String, Object> responce = new HashMap<>();
        try {
            CHmLeavesApplicationsModel cHmLeavesApplicationsModel = iHmLeavesApplicationsRepository
                    .FnShowParticularRecordForUpdate(company_id, leaves_transaction_id);
            CHmLeaveBalanceModel getLeaveBalanceDetails = iHmLeavesBalanceRepository
                    .FnGetLeavesApplicationsDetails(cHmLeavesApplicationsModel.getEmployee_type(), cHmLeavesApplicationsModel.getLeave_type_id(), cHmLeavesApplicationsModel.getEmployee_code(), company_id);
            responce.put("success", 1);
            responce.put("data", cHmLeavesApplicationsModel);
            responce.put("leaveBalanceData", getLeaveBalanceDetails);
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
    public Map<String, Object> FnShowLeaveBalanceDetails(int company_id, String employee_code) {
        Map<String, Object> responce = new HashMap<>();
        try {

            List<CHmLeaveBalanceModel> leaveBalanceDetails = iHmLeavesBalanceRepository
                    .FnShowLeaveBalanceDetails(company_id, employee_code);

            responce.put("success", 1);
            responce.put("LeaveBalanceDetails", leaveBalanceDetails);

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowExisitingCount(String employee_code, String leaves_requested_from_date,
                                                    String leaves_requested_to_date) {
        Map<String, Object> responce = new HashMap<>();
        try {

            Map<String, Object> ShowExisitingCount = iHmLeavesApplicationsRepository.FnShowExisitingCount(employee_code,
                    leaves_requested_from_date, leaves_requested_to_date);

            responce.put("ShowExisitingCount", ShowExisitingCount);
            responce.put("success", 1);

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }


    public String FnGenerateAutoNo(JSONObject json) throws IOException {
        String jsonData = json.toString();
        Gson gson = new Gson();
        GAutoNoModel autoNoQuery = gson.fromJson(jsonData, GAutoNoModel.class);
        return autoGenerateNoService.FnGenerateAutoNo(json, autoNoQuery);
    }

//    public boolean FnShowCheckSandwich(String weeklyOff, String leavesRequestedFromDate, String leavesRequestedToDate) {
//        // Convert weeklyOff to DayOfWeek
//        DayOfWeek weeklyOffDay = DayOfWeek.valueOf(weeklyOff.toUpperCase());
//        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust pattern as necessary
//
//        // Retrieve all holidays from the database
//        List<CXmProductionHolidayModel> holidays = iHmLeavesApplicationsRepository.findAllHolidays();
//        Set<LocalDate> holidaysDate = holidays.stream()
//                .map(holiday -> LocalDate.parse(holiday.getProduction_holiday_date(), dateFormatter))
//                .collect(Collectors.toSet());
//
//        // Parse the date strings to LocalDate
//        LocalDate fromDate = LocalDate.parse(leavesRequestedFromDate);
//        LocalDate toDate = LocalDate.parse(leavesRequestedToDate);
//
//        // Check if the start date or end date is a holiday or weekly off
//        boolean isStartDateHolidayOrOff = holidaysDate.contains(fromDate) || fromDate.getDayOfWeek() == weeklyOffDay;
//        boolean isEndDateHolidayOrOff = holidaysDate.contains(toDate) || toDate.getDayOfWeek() == weeklyOffDay;
//
//        // If the start or end date is a holiday or weekly off, it's not a sandwich leave
//        if (isStartDateHolidayOrOff || isEndDateHolidayOrOff) {
//            return false;
//        }
//
//        // Loop through the dates in the leave period to check for weekly offs or holidays between start and end
//        LocalDate currentDate = fromDate.plusDays(1); // Start checking from the day after the start date
//        boolean foundWeeklyOff = false;
//        boolean foundHoliday = false;
//
//        while (!currentDate.isAfter(toDate.minusDays(1))) { // Check all days except start and end dates
//            if (currentDate.getDayOfWeek() == weeklyOffDay) {
//                foundWeeklyOff = true;
//            }
//
//            if (holidaysDate.contains(currentDate)) {
//                foundHoliday = true;
//            }
//
//            currentDate = currentDate.plusDays(1);
//        }
//
//        // If a weekly off or holiday was found in the middle of the leave period, and the leave is more than 2 days
//        long totalDaysRequested = ChronoUnit.DAYS.between(fromDate, toDate) + 1; // Including both from and to dates
//        if ((foundWeeklyOff || foundHoliday) && totalDaysRequested > 2) {
//            return true; // There is a sandwich condition
//        }
//
//        return false; // No sandwich condition found
//    }


}
