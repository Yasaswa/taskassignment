package com.erp.HmManualAttendance.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.HmLeavesApplications.Repository.IHmLeavesApplicationsRepository;
import com.erp.HmShiftManagement.Model.CHtAttendanceDailyModel;
import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyModel;
import com.erp.HmShiftManagement.Repository.IHtAttendanceDailyRepository;
import com.erp.HmShiftManagement.Repository.IHtAttendanceMonthlyRepository;
import com.erp.HtShiftRoster.Model.CHtShiftRosterModel;
import com.erp.HtShiftRoster.Repository.IHtShiftRosterRepository;
import com.erp.JobType.Model.CJobTypeModel;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import com.erp.XmProductionHoliday.Repository.IXmProductionHolidayRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmManualAttendance.Model.CHmManualAttendanceModel;
import com.erp.HmManualAttendance.Repository.IHmManualAttendanceRepository;
import com.erp.JobType.Repository.IJobTypeRepository;
import com.erp.Shift.Repository.IShiftRepository;

@Service
public class CHmManualAttendanceServiceImpl implements IHmManualAttendanceService {


    //	private static final int PR = 1;
//	private static final int AB = 2;
//	private static final int HF = 3;
//	private static final int OD = 4;
//	private static final int WO = 5;
//	private static final int LV = 6;
//	private static final int HD = 7;
//	private static final int CF = 8;
//	private static final int HP = 9;
    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IHmManualAttendanceRepository iHmManualAttendanceRepository;

    @Autowired
    IHtAttendanceDailyRepository iHtAttendanceDailyRepository;
    @Autowired
    IShiftRepository iShiftRepository;
    @Autowired
    IHmLeavesApplicationsRepository iHmLeavesApplicationsRepository;
    @Autowired
    IHtShiftRosterRepository iHtShiftRosterRepository;
    @Autowired
    IXmProductionHolidayRepository iXmProductionHolidayRepository;
    @Autowired
    IJobTypeRepository iJobTypeRepository;
    @Autowired
    IHtAttendanceMonthlyRepository iHtAttendanceMonthlyRepository;

    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();
        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        String employee_type = "";
        String employeeTypeGroup = "";
        int company_id = commonIdsObj.getInt("company_id");
        int attendance_type_id = commonIdsObj.getInt("attendance_type_id");
        String modified_by = commonIdsObj.getString("modified_by");

        JSONArray attendanceData = jsonObject.getJSONArray("MannualAttendanceData");
        JSONArray attendanceDateArray = jsonObject.getJSONArray("ManualAttendaceDates");
        try {

            //Create List for add multiple addAttendanceDetails Records
            List<CHmManualAttendanceModel> addAttendanceDetails = new ArrayList<>();
            List<CHtAttendanceDailyModel> addDailyAttendanceDetails = new ArrayList<>();
            List<CHtAttendanceMonthlyModel> monthlyAttendanceDetail = new ArrayList<>();
//
            // Fetch all attendance_dates
            List<String> attendance_dates = new ArrayList<>();

            // Fetch all employee codes beforehand, assuming they are available in a list
            List<String> employeeCodesList = new ArrayList<>();


//			Iterate on employees attendance
            for (Object employeeAttendanceDetail : attendanceData) {
                JSONObject attendanceDetails = (JSONObject) employeeAttendanceDetail;
                String employeeCode = attendanceDetails.getString("employee_code");
                employeeCodesList.add(employeeCode); // add employee codes in list to update previous records
                String employeeName = attendanceDetails.getString("field_name");
                int departmentId = attendanceDetails.getInt("department_id");
                int subDepartmentId = attendanceDetails.getInt("sub_department_id");
                int employeeId = attendanceDetails.getInt("field_id");
                int halfday_hours = attendanceDetails.getInt("halfday_hours");
                int fullday_hours = attendanceDetails.getInt("fullday_hours");
                int grace_early_time = attendanceDetails.getInt("grace_early_time");
                int grace_late_time = attendanceDetails.getInt("grace_late_time");

                employee_type = attendanceDetails.getString("employee_type");
                employeeTypeGroup = attendanceDetails.getString("employee_type_group");
                String startTimes = commonIdsObj.getString("startTime");
                String endTimes = commonIdsObj.getString("endTime");
                Map<String, Object> responseEmployeeShiftDetails = iShiftRepository.getEmployeeShiftDetails(employeeCode);
//				Map<String, Object> responseEmployeeShiftDetails = iShiftRepository.getEmployeeShiftDetails(employeeCodesList);
                String attendance_date = "";
                int attendanceTypeId = 0;
                String attendanceType = "";
                // Create a new HashMap to store key-value pairs
                HashMap<String, Object> employeeShiftMap = new HashMap<>();

                // Define the keys for specific values based on their positions
                String[] keys = {"start_time", "end_time", "two_day_shift"};
//				// Iterate over the values in responseEmployeeShiftDetails and assign keys
                int index = 0;
                for (Object value : responseEmployeeShiftDetails.values()) {
                    // Assign keys based on index
                    String key = keys[index];
                    // Put the value in the map with the assigned key
                    employeeShiftMap.put(key, value);
                    index++;
                }

                // Now attendanceList contains each attendanceMap for each employee
//				Iterate on attendance dates
                for (Object dateObj : attendanceDateArray) {
                    JSONObject dateDetails = (JSONObject) dateObj;
                    String header = dateDetails.getString("header");
                    attendanceType = attendanceDetails.getString(header); // Absent or Present (AB or PR)

//				     Assuming accessor is the date string in the format '1/2/2024'
                    attendance_date = dateDetails.getString("accessor");

                    // Define the formatter for the original date format
                    DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate originalDate = LocalDate.parse(attendance_date, originalFormatter);
                    DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    // Format the LocalDate object into the desired format
                    attendance_date = originalDate.format(desiredFormatter); // yyyy-MM-dd
                    attendance_dates.add(attendance_date); // add dates in list to update is_delete =1 for previous
                    // records

                    LocalTime currentTime = LocalTime.now(); // Get the current time
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // Define the formatter
                    // Format the current time into the desired format
                    String formattedTime = currentTime.format(timeFormatter);

                    // Concatenate the formatted date and time
                    String dateTimeString = attendance_date + " " + formattedTime;

                    //fetch attendance_type_id using job_type_short_name as attendanceType
                    attendanceTypeId = iJobTypeRepository.getAttendanceType(attendanceType);

                    String start_time = startTimes;
                    String end_time = endTimes;
                    System.out.println("start_time:" + start_time);
                    System.out.println("end_time:" + end_time);


                    String startTimePart = start_time; // Extracting time part
                    String endTimePart = end_time; // Extracting time part

                    // Parsing the time parts into LocalTime objects
                    LocalTime startTime = LocalTime.parse(startTimePart);
                    LocalTime endTime = LocalTime.parse(endTimePart);

                    // Printing the extracted times
                    System.out.println("Start Time: " + startTime);
                    System.out.println("End Time: " + endTime);

                    LocalTime start_Time = startTime; // Get the current time
                    LocalTime end_Time = endTime; // Get the current time

//					// Format the current time into the desired format
                    String formattedstartTime = start_Time.format(timeFormatter);
                    String formattedendTime = end_Time.format(timeFormatter);

                    // Concatenate the formatted date and time for start time and end time
                    String dateStartTimeString = attendance_date + " " + formattedstartTime;
                    String dateEndTimeString = attendance_date + " " + formattedendTime;

                    System.out.println("dateStartTimeString: " + dateStartTimeString);
                    System.out.println("dateEndTimeString: " + dateEndTimeString);

                    // Create a new attendance record for start time
                    CHmManualAttendanceModel attendanceModelStart = new CHmManualAttendanceModel();
                    attendanceModelStart.setCompany_id(company_id);
                    attendanceModelStart.setEmployee_name(employeeName);
                    attendanceModelStart.setEmployee_code(employeeCode);
                    attendanceModelStart.setDepartment_id(departmentId);
                    attendanceModelStart.setSub_department_id(subDepartmentId);
                    attendanceModelStart.setAttendance_date(attendance_date);
                    attendanceModelStart.setAttendance_date_time(dateStartTimeString);
                    attendanceModelStart.setAttendance_type(attendanceType);
                    attendanceModelStart.setAttendance_type_id(attendanceTypeId);
                    attendanceModelStart.setEmployee_type(employee_type);
                    attendanceModelStart.setCreated_by(modified_by);
                    attendanceModelStart.setModified_by(modified_by);

                    // Add start time attendance model to the list
                    addAttendanceDetails.add(attendanceModelStart);

                    // Create a new attendance record for end time
                    CHmManualAttendanceModel attendanceModelEnd = new CHmManualAttendanceModel();
                    attendanceModelEnd.setCompany_id(company_id);
                    attendanceModelEnd.setEmployee_name(employeeName);
                    attendanceModelEnd.setEmployee_code(employeeCode);
                    attendanceModelEnd.setDepartment_id(departmentId);
                    attendanceModelEnd.setSub_department_id(subDepartmentId);
                    attendanceModelEnd.setAttendance_date(attendance_date);
                    attendanceModelEnd.setAttendance_date_time(dateEndTimeString);
                    attendanceModelEnd.setAttendance_type(attendanceType);
                    attendanceModelEnd.setAttendance_type_id(attendanceTypeId);
                    attendanceModelEnd.setEmployee_type(employee_type);
                    attendanceModelEnd.setCreated_by(modified_by);
                    attendanceModelEnd.setModified_by(modified_by);

                    // Add end time attendance model to the list
                    addAttendanceDetails.add(attendanceModelEnd);
//                }

                    LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), startTime);
                    LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), endTime);

                    if (endDateTime.isBefore(startDateTime)) {
                        endDateTime = endDateTime.plusDays(1);
                    }
                    Duration workedDurations = Duration.between(startDateTime, endDateTime);
                    long hours = workedDurations.toHours();
                    long minutes = workedDurations.toMinutes() % 60;
                    long seconds = workedDurations.getSeconds() % 60;


                    // Format the difference as "HH:mm:ss"
                    String workingHours = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                    CHtAttendanceDailyModel dailyAttendanceDetails = new CHtAttendanceDailyModel();
                    String[] dateParts = attendance_date.split("-");
                    int year = Integer.parseInt(dateParts[0]);
                    int month = Integer.parseInt(dateParts[1]);
                    int day = Integer.parseInt(dateParts[2]);
                    YearMonth yearMonth = YearMonth.of(year, month);

                    // Get the number of days in the month
                    int monthDays = yearMonth.lengthOfMonth();
                    dailyAttendanceDetails.setCompany_id(company_id);
                    dailyAttendanceDetails.setCompany_branch_id(commonIdsObj.getInt("company_branch_id"));
                    dailyAttendanceDetails.setEmployee_code(employeeCode);
                    dailyAttendanceDetails.setDepartment_id(departmentId);
                    dailyAttendanceDetails.setEmployee_id(employeeId);
                    dailyAttendanceDetails.setAttendance_date(day);
                    dailyAttendanceDetails.setAttendance_year(year);
                    dailyAttendanceDetails.setAttendance_month(month);
                    dailyAttendanceDetails.setEmployee_type(employeeTypeGroup);
                    dailyAttendanceDetails.setCreated_by(modified_by);
                    dailyAttendanceDetails.setModified_by(modified_by);
                    dailyAttendanceDetails.setShift_present(commonIdsObj.getString("shiftName"));
                    dailyAttendanceDetails.setShift_scheduled(commonIdsObj.getString("shiftName"));

                    if (!attendanceType.equals("WO") && !attendanceType.equals("LV")) {
                        dailyAttendanceDetails.setIn_time(LocalTime.parse(startTimes, DateTimeFormatter.ofPattern("HH:mm:ss")).format(DateTimeFormatter.ofPattern("HH:mm")));
                        dailyAttendanceDetails.setOut_time(LocalTime.parse(endTimes, DateTimeFormatter.ofPattern("HH:mm:ss")).format(DateTimeFormatter.ofPattern("HH:mm")));
                        dailyAttendanceDetails.setWorking_minutes(workingHours);
                    } else {
                        dailyAttendanceDetails.setIn_time("00:00");
                        dailyAttendanceDetails.setOut_time("00:00");
                        dailyAttendanceDetails.setWorking_minutes("00:00:00");
                    }
                    dailyAttendanceDetails.setJob_type(attendanceType);
                    dailyAttendanceDetails.setJob_type_id(attendanceTypeId);
//                    dailyAttendanceDetails.setWorking_minutes(workingHours);
                    dailyAttendanceDetails.setAtt_date_time(attendance_date);
                    dailyAttendanceDetails.setShift_id(commonIdsObj.getInt("shiftId"));
                    dailyAttendanceDetails.setAttendance_flag("M");
                    dailyAttendanceDetails.setPresent_flag("Y");
                    dailyAttendanceDetails.setNight_shift_present_flag("N");
                    dailyAttendanceDetails.setWeek_off_present_flag("N");
                    dailyAttendanceDetails.setLate_mark_flag("N");
                    dailyAttendanceDetails.setGate_pass_flag("N");
                    dailyAttendanceDetails.setEarly_go_flag("N");

                    addDailyAttendanceDetails.add(dailyAttendanceDetails);

                }
                iHmManualAttendanceRepository.updateDailyAttendanceRecord(attendance_dates, employeeTypeGroup,
                        employeeCodesList, company_id);
                iHtAttendanceDailyRepository.saveAll(addDailyAttendanceDetails);

                //			************monthly attendance implementation*******************************************************

                String[] dateParts = attendance_date.split("-");
                int year = Integer.parseInt(dateParts[0]);
                int month = Integer.parseInt(dateParts[1]);
                int day = Integer.parseInt(dateParts[2]);
                YearMonth yearMonth = YearMonth.of(year, month);
                String start_time = startTimes;
                String end_time = endTimes;
                System.out.println("start_time:" + start_time);
                System.out.println("end_time:" + end_time);


                String startTimePart = start_time; // Extracting time part
                String endTimePart = end_time; // Extracting time part

                // Parsing the time parts into LocalTime objects
                LocalTime startTime = LocalTime.parse(startTimePart);
                LocalTime endTime = LocalTime.parse(endTimePart);

                // Get the number of days in the month
                int monthDays = yearMonth.lengthOfMonth();

                CHtAttendanceMonthlyModel monthlyAttendanceDetails = new CHtAttendanceMonthlyModel();
                List<CHtAttendanceMonthlyModel> existingRecords = iHtAttendanceMonthlyRepository.findByEmployee_code(
                        employeeCode,
                        month,
                        year
                );
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
                    mergedRecord.setCompany_branch_id(commonIdsObj.getInt("company_branch_id"));
                    mergedRecord.setEmployee_code(employeeCode);
                    mergedRecord.setDepartment_id(departmentId);
                    mergedRecord.setSub_department_id(subDepartmentId);
                    mergedRecord.setEmployee_id(employeeId);
                    mergedRecord.setAttendance_year(year);
                    mergedRecord.setAttendance_month(month);
                    mergedRecord.setEmployee_type(employeeTypeGroup);
                    mergedRecord.setCreated_by(modified_by);
                    mergedRecord.setModified_by(modified_by);
                    mergedRecord.setProcess_date(new Date());
                    mergedRecord.setAttendance_flag("M");
                    mergedRecord.setMonth_days(monthDays);
                }

                // Update employee-specific fields based on the new data
                String[] datePart = attendanceDetails.keys().next().split("/");
                if (datePart.length > 1) {
                    month = Integer.parseInt(datePart[1]);
                }

                // Initialize counters
                int present_days = 0;
                int absent_days = 0;
                int week_off_days = 0;
                double monthly_hours = 0;
                double half_days = 0.0;
                double night_days = 0;
                double present_hours = 0;
                double half_day_hours = 0;
                int holiday_days = 0;
                int leaves_days = 0;
                int od_days = 0;
                double total_salary_days = 0;
                double coff_hours = 0;
                int coff_days = 0;

                LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), startTime);
                LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), endTime);

                if (endDateTime.isBefore(startDateTime)) {
                    endDateTime = endDateTime.plusDays(1);
                }

                Duration workedDuration = Duration.between(startDateTime, endDateTime);
                long totalMinutesWorked = workedDuration.toMinutes();
                double fullDayMinutes = fullday_hours * 60;
                double halfDayMinutes = halfday_hours * 60;

                String jobType = "";
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                // Merge daily data into mergedRecord
                for (int days = 1; days <= monthDays; days++) {
                    String key = days + "/" + month;
                    String presentColumnName = "presenty" + days;
                    String inOutTimeColumnName = "in_out_time" + days;

                    try {
                        // Get the fields using reflection
                        Field presentField = CHtAttendanceMonthlyModel.class.getDeclaredField(presentColumnName);
                        Field inOutTimeField = CHtAttendanceMonthlyModel.class.getDeclaredField(inOutTimeColumnName);

                        // Ensure fields are accessible
                        presentField.setAccessible(true);
                        inOutTimeField.setAccessible(true);

                        if (attendanceDetails.has(key)) {
                            String value = attendanceDetails.getString(key);
                            presentField.set(mergedRecord, value); // Set present value

                            if (!value.equals("WO") && !value.equals("LV")) {
                                String formattedStartTimes = LocalTime.parse(startTimes, DateTimeFormatter.ofPattern("HH:mm:ss")).format(DateTimeFormatter.ofPattern("HH:mm"));
                                String formattedEndTimes = LocalTime.parse(endTimes, DateTimeFormatter.ofPattern("HH:mm:ss")).format(DateTimeFormatter.ofPattern("HH:mm"));

                                inOutTimeField.set(mergedRecord, formattedStartTimes + "/" + formattedEndTimes);
                            } else {
                                inOutTimeField.set(mergedRecord, "00:00" + "/" + "00:00");
                            }

                        }

                    } catch (NoSuchFieldException | IllegalAccessException | JSONException e) {
                        e.printStackTrace(); // Handle exceptions appropriately
                    }
                }


                // Calculate counters and update allmergedRecord
                for (int days = 1; days <= monthDays; days++) {
                    String key = days + "/" + month;
                    String presentColumnName = "presenty" + days;
                    String inOutTimeColumnName = "in_out_time" + days;

                    try {
                        // Get the fields using reflection
                        Field presentField = CHtAttendanceMonthlyModel.class.getDeclaredField(presentColumnName);
                        Field inOutTimeField = CHtAttendanceMonthlyModel.class.getDeclaredField(inOutTimeColumnName);

                        // Ensure fields are accessible
                        presentField.setAccessible(true);
                        inOutTimeField.setAccessible(true);

                        // Get values from mergedRecord
                        String presentValue = (String) presentField.get(mergedRecord);
                        String inOutTimeValue = (String) inOutTimeField.get(mergedRecord);

                        // Check if presentValue is not null or handle accordingly
                        if (presentValue != null) {
                            // Example processing
                            System.out.println("Present Value: " + presentValue);

                            // Set values in allmergedRecord if needed
                            presentField.set(allmergedRecord, presentValue);
                            inOutTimeField.set(allmergedRecord, inOutTimeValue);

//                            jobType = iJobTypeRepository.getAttendanceType(presentValue);

                            if ("WO".equals(presentValue)) {
                                // Count the number of 'WO' values
                                week_off_days++;
                            } else if ("HD".equals(presentValue)) {
                                holiday_days++;
                            } else if ("LV".equals(presentValue)) {
                                leaves_days++;
                            } else if ("OD".equals(presentValue)) {
                                od_days++;
                            } else if ("CF".equals(presentValue)) {
                                coff_days++;
                                coff_hours += totalMinutesWorked / 60;
                            } else if (totalMinutesWorked + grace_early_time >= fullDayMinutes) {
                                present_days++;
                                present_hours += totalMinutesWorked / 60;
                            } else if ((totalMinutesWorked >= halfDayMinutes && totalMinutesWorked < fullDayMinutes)) {
                                half_days++;
                                half_day_hours += totalMinutesWorked / 60; // Convert to hours
                            } else if (totalMinutesWorked < halfDayMinutes || "AB".equals(presentValue)) {
                                absent_days++;
                            }
                            String ShiftName = iHtAttendanceMonthlyRepository.getAttendanceShiftName(employeeCode, days, month, year);
//                           boolean two_Day_Shift;
//                            if(ShiftName !=null) {
//                                 two_Day_Shift = iHtAttendanceMonthlyRepository.gettwo_Day_Shift(ShiftName);
//                            }
//                                if (two_Day_Shift) {
//                                    night_days++;
//                                }
                            boolean twoDayShift = false; // Initialize with a default value
                            if (ShiftName != null) {
                                twoDayShift = iHtAttendanceMonthlyRepository.gettwo_Day_Shift(ShiftName);
                            }
                            if (twoDayShift) {
                                night_days++;
                            }
                            monthly_hours = present_hours + half_day_hours + coff_hours;
                            total_salary_days = present_days + leaves_days + od_days + (half_days / 2.0) + holiday_days;

                        }
                    } catch (NoSuchFieldException | IllegalAccessException | JSONException e) {
                        e.printStackTrace(); // Handle exceptions appropriately
                    }
                }

                try {
                    Field presentDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("present_days");
                    presentDaysField.setAccessible(true);
                    presentDaysField.set(allmergedRecord, present_days);

                    Field halfDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("half_days");
                    halfDaysField.setAccessible(true);
                    halfDaysField.set(allmergedRecord, half_days);

                    Field holiDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("holiday_days");
                    holiDaysField.setAccessible(true);
                    holiDaysField.set(allmergedRecord, holiday_days);

                    Field coffDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("coff_days");
                    coffDaysField.setAccessible(true);
                    coffDaysField.set(allmergedRecord, coff_days);

                    Field absentDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("absent_days");
                    absentDaysField.setAccessible(true);
                    absentDaysField.set(allmergedRecord, absent_days);

                    Field nightDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("night_days");
                    nightDaysField.setAccessible(true);
                    nightDaysField.set(allmergedRecord, night_days);

                    Field totalSalaryDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("total_salary_days");
                    totalSalaryDaysField.setAccessible(true);
                    totalSalaryDaysField.set(allmergedRecord, total_salary_days);

                    Field leaveDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("leaves_days");
                    leaveDaysField.setAccessible(true);
                    leaveDaysField.set(allmergedRecord, leaves_days);

                    Field od_DaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("od_days");
                    od_DaysField.setAccessible(true);
                    od_DaysField.set(allmergedRecord, od_days);

                    Field weeklyOffDaysField = CHtAttendanceMonthlyModel.class.getDeclaredField("week_off_days");
                    weeklyOffDaysField.setAccessible(true);
                    weeklyOffDaysField.set(allmergedRecord, week_off_days);

                    Field workedHoursField = CHtAttendanceMonthlyModel.class.getDeclaredField("monthly_hours");
                    workedHoursField.setAccessible(true);
                    workedHoursField.set(allmergedRecord, monthly_hours);


                    allmergedRecord.setCompany_id(company_id);
                    allmergedRecord.setCompany_branch_id(commonIdsObj.getInt("company_branch_id"));
                    allmergedRecord.setEmployee_code(employeeCode);
                    allmergedRecord.setDepartment_id(departmentId);
                    allmergedRecord.setSub_department_id(subDepartmentId);
                    allmergedRecord.setEmployee_id(employeeId);
                    allmergedRecord.setAttendance_year(year);
                    allmergedRecord.setAttendance_month(month);
                    allmergedRecord.setEmployee_type(employeeTypeGroup);
                    allmergedRecord.setCreated_by(modified_by);
                    allmergedRecord.setModified_by(modified_by);
                    allmergedRecord.setProcess_date(new Date());
                    allmergedRecord.setAttendance_flag("M");
                    allmergedRecord.setMonth_days(monthDays);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace(); // Handle exceptions appropriately
                }

                System.out.println("All Merged Record: " + allmergedRecord);

// Save the merged record
                iHtAttendanceMonthlyRepository.save(allmergedRecord);
                //				********************************************************************
            }

            //update Manual Attendance Details
            iHmManualAttendanceRepository.updateMannualAttendanceRecord(attendance_dates, employee_type,
                    employeeCodesList, company_id);
            iHmManualAttendanceRepository.saveAll(addAttendanceDetails);

//            iHmManualAttendanceRepository.updateDailyAttendanceRecord(attendance_dates, employeeTypeGroup,
//                    employeeCodesList, company_id);
//            iHtAttendanceDailyRepository.saveAll(addDailyAttendanceDetails);


            responce.put("success", 1);
            responce.put("error", "");
            responce.put("message", "Record added successfully!...");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/HmManualAttendance/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/HmManualAttendance/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;
    }

    @Override
    public Map<String, Object> FnDeleteRecord(int attendance_transaction_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iHmManualAttendanceRepository.FnDeleteManualAttendanceRecord(attendance_transaction_id, deleted_by);
            responce.put("success", 1);

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("error", e.getMessage());

        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowMannualAttendanceRecordForUpdate(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();

        JSONObject attendanceJson = jsonObject.getJSONObject("MannualAttendanceData");
        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        try {
            int company_id = commonIdsObj.getInt("company_id");

            String from_date = attendanceJson.getString("from_date");
            String to_date = attendanceJson.getString("to_date");
            AtomicInteger department_id = new AtomicInteger(0);
            AtomicInteger sub_department_id = new AtomicInteger(0);
//            Integer department_id = attendanceJson.;
//            Integer subDepartment_id = attendanceJson.getSub_department_id();

            if (attendanceJson.has("department_id") && !attendanceJson.isNull("department_id"))
            {
                department_id.set(attendanceJson.getInt("department_id"));
            }
            if (attendanceJson.has("sub_department_id") && !attendanceJson.isNull("sub_department_id"))
            {
                sub_department_id.set(attendanceJson.getInt("sub_department_id"));
            }
//			String employee_type;
//			employee_type = attendanceJson.optString("employee_type", null);

            String employee_type = attendanceJson.getString("employee_type");
            if (employee_type.isEmpty() && employee_type.equals("")) {
                employee_type = null;
            }

            String employee_name = attendanceJson.getString("employee_name");
            if (employee_name.isEmpty() && employee_name.equals("")) {
                employee_name = null;
            }

            List<Map<String, Object>> addEmployeesAttendanceDetails = new ArrayList<>();
            List<Map<String, Object>> datesList = new ArrayList<>();

//			Get data from ht_attendance by using employee_type, department_id, from_date, to_date
            List<CHmManualAttendanceModel> attendanceDetails = iHmManualAttendanceRepository
                    .findByEmployeeTypeAndDepartmentIdAndAttendanceDateBetween(employee_type, employee_name,
                            department_id.get(), from_date, to_date, company_id,sub_department_id.get());

            if (attendanceDetails.size() != 0) {
//			Create group using employee code
                Map<String, List<CHmManualAttendanceModel>> groupedByEmployeeCode = attendanceDetails.stream()
                        .collect(Collectors.groupingBy(CHmManualAttendanceModel::getEmployee_code));

//			Iterate on employee code group
                for (Map.Entry<String, List<CHmManualAttendanceModel>> entry : groupedByEmployeeCode.entrySet()) {
                    String employeeCode = entry.getKey();
                    List<CHmManualAttendanceModel> employeeAttendanceList = entry.getValue();

//				Now you can process each employeeCode and its corresponding list of details
                    System.out.println("Employee Code ID: " + employeeCode);
                    Map<String, Object> attendanceMap = new HashMap<>();
//              Iterate on particular employee object
                    for (CHmManualAttendanceModel employeeOneDayAttendance : employeeAttendanceList) {

//					Check is there details is available in addEmployeesAttendanceDetails by compairing employee_code
                        Optional<Map<String, Object>> foundDetail = addEmployeesAttendanceDetails.stream()
                                .filter(item -> {
                                    Object paramName = item.get("employee_code");
                                    return paramName != null
                                            && paramName.equals(employeeOneDayAttendance.getEmployee_code());
                                }).findFirst();

//				    	get day from 2024-02-01 00:00:00, this date time
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                        LocalDate attendanceDate = LocalDate.parse(employeeOneDayAttendance.getAttendance_date(),
                                formatter);
//                      Get the day from the LocalDate
                        int day = attendanceDate.getDayOfMonth();
                        int month = attendanceDate.getMonthValue();

//						System.out.println("Original attendance_date: " + attendanceDate);
//						System.out.println("Extracted day: " + day);

                        if (foundDetail.isPresent()) {
                            attendanceMap = foundDetail.get();
                            attendanceMap.put(String.valueOf(day), employeeOneDayAttendance.getAttendance_type());
                        } else {
//                            int field_id = iHmManualAttendanceRepository.getemployeeId(employeeOneDayAttendance.getEmployee_code());
//                            attendanceMap.put("field_id", field_id);
                            List<Object[]> results = iHmManualAttendanceRepository.findEmployeeDetails(employeeOneDayAttendance.getEmployee_code());
                            if (!results.isEmpty()) {
                                // Extract the first result from the results list
                                Object[] result = results.get(0);

                                // Populate the attendanceMap directly from the result array
//                                Map<String, Object> attendanceMap = new HashMap<>();
                                attendanceMap.put("field_id", ((BigInteger) result[0]).intValue());
                                attendanceMap.put("halfday_hours", ((BigDecimal) result[1]).doubleValue());
                                attendanceMap.put("fullday_hours",  ((BigDecimal) result[2]).doubleValue());
                                attendanceMap.put("grace_early_time", ((BigInteger) result[3]).doubleValue());
                                attendanceMap.put("employee_type_group", (String) result[4]);
                                attendanceMap.put("employee_type", (String) result[5]);
                                attendanceMap.put("grace_late_time", ((BigInteger) result[6]).doubleValue());
                                // Now attendanceMap contains the required values
                            }
                                attendanceMap.put("field_name", employeeOneDayAttendance.getEmployee_name());
                                attendanceMap.put("employee_code", employeeOneDayAttendance.getEmployee_code());
                                attendanceMap.put("employee_type", employeeOneDayAttendance.getEmployee_type());
                                attendanceMap.put("department_id", employeeOneDayAttendance.getDepartment_id());
                                attendanceMap.put("sub_department_id", employeeOneDayAttendance.getSub_department_id());
                                attendanceMap.put(String.valueOf(day) + "/" + String.valueOf(month),
                                        employeeOneDayAttendance.getAttendance_type());
                            }

                    }
                    addEmployeesAttendanceDetails.add(attendanceMap);
                }

                // Parse the date strings into LocalDate objects
                LocalDate startDate = LocalDate.parse(from_date);
                LocalDate endDate = LocalDate.parse(to_date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                List<String> formattedDates = new ArrayList<>();

                // Loop through the date range and add formatted dates to the list
                LocalDate currentDate = startDate;
                while (!currentDate.isAfter(endDate)) {
                    Map<String, Object> object = new HashMap<>();

                    formattedDates.add(currentDate.format(formatter));
                    LocalDate date = LocalDate.parse(currentDate.format(formatter), formatter);
                    int dayNumber = date.getDayOfMonth();
                    int monthNumber = date.getMonthValue();

//				    System.out.println("monthNumber : " + monthNumber);

                    object.put("header", String.valueOf(dayNumber) + "/" + String.valueOf(monthNumber));
//					object.put("header", String.valueOf(dayNumber));
                    object.put("accessor", currentDate);

                    currentDate = currentDate.plusDays(1);

                    datesList.add(object);
                }
            }
            responce.put("MannualAttendanceDetails", addEmployeesAttendanceDetails);
            responce.put("AttendanceDates", datesList);
            responce.put("success", 1);

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("error", e.getMessage());
        }
        return responce;
    }

}


