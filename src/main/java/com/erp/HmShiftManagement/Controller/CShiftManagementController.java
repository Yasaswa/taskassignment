package com.erp.HmShiftManagement.Controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.Employee.Employees.Repository.IEmployeesViewRepository;
import com.erp.HmAttendanceStatus.Model.CHmAttendanceStatusModel;
import com.erp.HmAttendanceStatus.Repository.IHmAttendanceStatusRepository;
import com.erp.HmCompOffDetails.Repository.IHmCompOffDetailsRepository;
import com.erp.HmHrpayrollSettings.Model.CHmHrpayrollSettingsModel;
import com.erp.HmHrpayrollSettings.Repository.IHmHrpayrollSettingsRepository;
import com.erp.HmCompOffDetails.Model.CHmCompOffDetailsModel;
import com.erp.HmLeavesApplications.Repository.IHmLeavesApplicationsRepository;
import com.erp.HmLeavesBalance.Model.CHmLeaveBalanceModel;
import com.erp.HmLeavesBalance.Repository.IHmLeavesBalanceRepository;
import com.erp.HmShiftManagement.Model.*;
import com.erp.HmShiftManagement.Repository.*;
import com.erp.HmShiftManagement.Service.IHtShiftManagementService;
import com.erp.HtSalaryRules.Model.CHtvSalaryRulesViewModel;
import com.erp.HtSalaryRules.Repository.IHtvSalaryRulesViewModelRepository;
import com.erp.HtShiftRoster.Model.CHtShiftRosterModel;
import com.erp.HtShiftRoster.Repository.IHtShiftRosterRepository;
import com.erp.HtShortLeave.Model.CHtShortLeaveModel;
import com.erp.JobType.Model.CJobTypeModel;
import com.erp.JobType.Repository.IJobTypeRepository;
import com.erp.Shift.Model.CShiftModel;
import com.erp.Shift.Model.CShiftViewModel;
import com.erp.Shift.Repository.IShiftRepository;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import com.erp.XmProductionHoliday.Repository.IXmProductionHolidayRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/api/shiftManagement")
public class CShiftManagementController {

    private static final int PRESENT = 1;
    private static final int ABSENT = 2;
    private static final int HALF_DAY = 3;
    private static final int OUT_DOR_DUTY = 4;
    private static final int WEEKLY_OFF = 5;
    private static final int LEAVES = 6;
    private static final int HOLIDAY = 7;
    private static final int C_OFF = 8;
    private static final int HOLIDAY_PRESENT = 9;


    @Autowired
    IEmployeesViewRepository iEmployeesViewRepository;

    @Autowired
    IHtAttendanceDailyRepository iHtAttendanceDailyRepository;

    @Autowired
    IHtMissPunchCorrectionRepository iHtMissPunchCorrectionRepository;
    @Autowired
    IHtAttendanceMonthlyRepository iHtAttendanceMonthlyRepository;
    @Autowired
    IJobTypeRepository iJobTypeRepository;
    @Autowired
    IHmAttendanceStatusRepository iHmAttendanceStatusRepository;
    @Autowired
    IXmProductionHolidayRepository iXmProductionHolidayRepository;
    @Autowired
    IHmLeavesApplicationsRepository iHmLeavesApplicationsRepository;
    @Autowired
    IHtShiftRosterRepository iHtShiftRosterRepository;
    @Autowired
    IShiftRepository iShiftRepository;
    @Autowired
    IHmAttLogRepository iHmAttLogRepository;
    @Autowired
    IHmAttLog2ModelRepository iHmAttLog2ModelRepository;
    @Autowired
    IHtvSalaryRulesViewModelRepository iHtvSalaryRulesViewModelRepository;
    @Autowired
    IHtAttendanceMonthlyJobTypeRepository iHtAttendanceMonthlyJobTypeRepository;
    @Autowired
    IHtShiftManagementService iHtShiftManagementService;
    @Autowired
    IHmvAttLogCombinedModelRepository iHmvAttLogCombinedModelRepository;
    @Autowired
    private IHmHrpayrollSettingsRepository iHmHrpayrollSettingsRepository;

    @Autowired
    private IHmLeavesBalanceRepository iHmLeavesBalanceRepository;
    @Autowired
    private IHmCompOffDetailsRepository iHmCompOffDetailsRepository;

    @Autowired
    private JdbcTemplate executeQuery;
    @Value("${attendanceProcess.url}")
    private String attendanceProcessUrl;

    public static Double FnConvertStringToDouble(String doubleValue) {
        return Optional.ofNullable(doubleValue)
                .filter(str -> !str.isEmpty())
                .map(s -> {
                    try {
                        return Double.parseDouble(s);
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }
                }).orElse(0.0);


    }


    public static boolean hasEmployeeTakenLeave(List<Map<String, Object>> leaveApplicationDetails, String employeeCode, String attendanceDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate attendanceLocalDate = LocalDate.parse(attendanceDate, dateFormatter);

        return leaveApplicationDetails.stream()
                .filter(leaveDetails -> employeeCode.equals(leaveDetails.get("employee_code")))
                .anyMatch(leaveDetails -> {
                    java.sql.Date fromDateSql = (java.sql.Date) leaveDetails.get("leaves_approved_from_date");
                    java.sql.Date toDateSql = (java.sql.Date) leaveDetails.get("leaves_approved_to_date");

                    if (fromDateSql != null && toDateSql != null) {
                        // Convert java.sql.Date to LocalDate
                        LocalDate fromDate = fromDateSql.toLocalDate();
                        LocalDate toDate = toDateSql.toLocalDate();

                        // Check if attendanceDate is within the leave period
                        return !attendanceLocalDate.isBefore(fromDate) && !attendanceLocalDate.isAfter(toDate);
                    }
                    return false;
                });
    }


    public static Map<String, Object> FnGetEmployeeLeaveDtlsByEmplCode(List<Map<String, Object>> leaveApplicationDetails, String employeeCode) {
        return leaveApplicationDetails
                .parallelStream()
                .filter(leaveDetails -> leaveDetails.containsKey("employee_code") && leaveDetails.get("employee_code").equals(employeeCode))
                .findFirst()
                .orElse(null);
    }

    public static String getLeaveTypeCode(List<Map<String, Object>> leaveApplicationDetails, String employeeCode, String attendanceDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate attendanceLocalDate = LocalDate.parse(attendanceDate, dateFormatter);

        return leaveApplicationDetails.stream()
                .filter(leaveDetails -> employeeCode.equals(leaveDetails.get("employee_code")))
                .filter(leaveDetails -> {
                    java.sql.Date fromDateSql = (java.sql.Date) leaveDetails.get("leaves_approved_from_date");
                    java.sql.Date toDateSql = (java.sql.Date) leaveDetails.get("leaves_approved_to_date");

                    if (fromDateSql != null && toDateSql != null) {
                        LocalDate fromDate = fromDateSql.toLocalDate();
                        LocalDate toDate = toDateSql.toLocalDate();
                        return !attendanceLocalDate.isBefore(fromDate) && !attendanceLocalDate.isAfter(toDate);
                    }
                    return false;
                })
                .map(leaveDetails -> leaveDetails.get("leave_type_code").toString())
                .findFirst()
                .orElse("");
    }

    public static boolean hasEmployeeTakenShortLeave(List<CHtShortLeaveModel> shortLeaveApplicationDetails, String employeeCode, String attendanceDate) {
        return shortLeaveApplicationDetails.stream()
                .anyMatch(leaveDetails -> leaveDetails.getEmployee_code().equals(employeeCode) && leaveDetails.getShort_leave_date().equals(attendanceDate));
    }

    public static boolean hasEmployeeTakenHalfDayLeave(List<CHtShortLeaveModel> shortLeaveApplicationDetails, String employeeCode, String attendanceDate) {
        return shortLeaveApplicationDetails.stream()
                .anyMatch(leaveDetails -> leaveDetails.getEmployee_code().equals(employeeCode) && "Half Day Leave".equals(leaveDetails.getLeave_type()) && leaveDetails.getShort_leave_date().equals(attendanceDate));
    }

    public static boolean hasEmployeeTakenGatePass(List<CHtShortLeaveModel> shortLeaveApplicationDetails, String employeeCode, String attendanceDate) {
        return shortLeaveApplicationDetails.stream()
                .anyMatch(leaveDetails -> leaveDetails.getEmployee_code().equals(employeeCode) && "Gate Pass".equals(leaveDetails.getLeave_type()) && leaveDetails.getShort_leave_date().equals(attendanceDate));
    }

    public static CHmAttendanceStatusModel getAttendanceDetailById(List<CHmAttendanceStatusModel> attendanceStatusDetails, int attendanceStatustId) {
        return attendanceStatusDetails.stream()
                .filter(jobTypeDetail -> jobTypeDetail.getAttendance_status_id() == attendanceStatustId).findFirst().get();
    }

    public static CJobTypeModel getJobTypeDetailById(List<CJobTypeModel> jobTypeDetails, int job_type_id) {
        return jobTypeDetails.stream()
                .filter(jobTypeDetail -> jobTypeDetail.getJob_type_id() == job_type_id)
                .findFirst()
                .orElse(null);
    }


    public static String getHoursAndMinutes(LocalDateTime dateTime) {
        int hours = dateTime.getHour();
        int minutes = dateTime.getMinute();
        return String.format("%02d:%02d", hours, minutes);
    }

    public static boolean isHalfDayOnDate(String leavesApprovedFromDate, String leavesApprovedToDate, double leavesAppliedDays, String checkDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse(leavesApprovedFromDate, formatter);
        LocalDate toDate = LocalDate.parse(leavesApprovedToDate, formatter);
        LocalDate queryDate = LocalDate.parse(checkDate, formatter);

        // Check if the query date is within the leave period (inclusive of fromDate and toDate)
        if ((queryDate.isEqual(fromDate) || queryDate.isAfter(fromDate)) &&
                (queryDate.isEqual(toDate) || queryDate.isBefore(toDate))) {

            // Calculate total days including both start and end dates
            long totalDays = ChronoUnit.DAYS.between(fromDate, toDate) + 1;

            // Check if applied leave days are more than total days, implying there's a half-day leave
            return true;
        }

        return false;
    }

    public static void setPresentyDateWiseForMonthlyCalculations(CHtAttendanceDailyModel empAtt, CHtAttendanceMonthlyModel htAttendanceMonthlyModel, int day, List<Map<String, Object>> leaveApplicationDetails, List<CHtShortLeaveModel> shortLeaveApplicationDetails) {
        try {
            // Construct field names dynamically based on the day
            String inOutTimeFieldName = "in_out_time" + day;
            String presentyFieldName = "presenty" + day;

            // Initialize fields
            Field inOutTimeField = htAttendanceMonthlyModel.getClass().getDeclaredField(inOutTimeFieldName);
            Field presentyField = htAttendanceMonthlyModel.getClass().getDeclaredField(presentyFieldName);

            // Make fields accessible
            inOutTimeField.setAccessible(true);
            presentyField.setAccessible(true);

            Object inOutTimeValue = inOutTimeField.get(htAttendanceMonthlyModel);
            String prevInOutTime = (String) inOutTimeField.get(htAttendanceMonthlyModel);


            double prevHours = 0.0;
            String formattedTime = "00:00"; // Default value
            if (prevInOutTime != null && !prevInOutTime.isEmpty() && prevInOutTime.contains("/")) {
                String[] prevTimes = prevInOutTime.trim().split("/");
                System.out.println("prevInOutTime" + prevInOutTime + empAtt.getEmployee_code());
                // Ensure we have both start and end times
                if (prevTimes.length >= 2 && !prevTimes[0].trim().isEmpty() && !prevTimes[1].trim().isEmpty()) {
                    try {
                        LocalTime startTime = LocalTime.parse(prevTimes[0].trim());
                        LocalTime endTime = LocalTime.parse(prevTimes[1].trim());
                        // Check if either time is "00:00"
                        if (startTime.equals(LocalTime.MIDNIGHT) || endTime.equals(LocalTime.MIDNIGHT)) {
                            prevHours = 0.0;
                        } else {
                            // Use LocalDateTime for proper date-time handling
                            LocalDateTime startDateTime = LocalDateTime.of(LocalDate.now(), startTime);
                            LocalDateTime endDateTime = LocalDateTime.of(LocalDate.now(), endTime);

                            // If end time is before start time, assume it belongs to the next day
                            if (endDateTime.isBefore(startDateTime)) {
                                endDateTime = endDateTime.plusDays(1);
                            }

                            Duration duration = Duration.between(startDateTime, endDateTime);
                            long hours = duration.toHours();
                            long minutes = duration.toMinutesPart(); // Java 9+

                            formattedTime = String.format("%02d:%02d", hours, minutes);
                            prevHours = Double.parseDouble(String.format("%d.%02d", Math.abs(hours), Math.abs(minutes)));

                            System.out.println(prevHours);
                        }
                    } catch (DateTimeParseException e) {
                        System.err.println("Invalid time format in prevInOutTime: " + prevInOutTime);
                    }
                } else {
                    System.err.println("Invalid prevInOutTime format: " + prevInOutTime);
                }
            }

            Object presentyValue = presentyField.get(htAttendanceMonthlyModel);

            // Get in_time and out_time, replace with "00:00" if null
            String inTime = (empAtt.getIn_time() != null) ? empAtt.getIn_time() : "00:00";
            String outTime = (empAtt.getOut_time() != null) ? empAtt.getOut_time() : "00:00";
            Double currentShiftHrs = FnCalculateHoursInDecimal(empAtt.getShift_start_end_time());


//            if ("Semi-Staff".equals(empAtt.getEmployee_type_group()) && "HD".equals(empAtt.getJob_type())) {
            if ("Semi-Staff".equals(empAtt.getEmployee_type_group()) && ("HD".equals(empAtt.getJob_type()) || "HP".equals(empAtt.getJob_type()))) {

                // Get the shift time from empAtt and split into inTime and outTime
                String shiftTime = empAtt.getShift_start_end_time(); // Example: "08:00:00-20:00:00"
                if (shiftTime != null && shiftTime.contains("-")) {
                    String[] shiftTimes = shiftTime.split("-");
                    inTime = shiftTimes[0].substring(0, 5);  // Set inTime to the shift's start time (first 5 chars)
                    outTime = shiftTimes[1].substring(0, 5);

                    // Convert working minutes to hours in decimal format
                    double workingMinutes = 0.0;

                    String workingMinutesStr = empAtt.getWorking_minutes();
                    if (workingMinutesStr != null && !workingMinutesStr.trim().isEmpty()) {
                        try {
                            String[] timeParts = workingMinutesStr.split(":");
                            if (timeParts.length == 2) {
                                int hours = Integer.parseInt(timeParts[0]);  // Extract hours
                                int minutes = Integer.parseInt(timeParts[1]); // Extract minutes

                                // Convert to total minutes
                                workingMinutes = (hours * 60) + minutes;
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid working minutes format: " + workingMinutesStr);
                        }
                    }
                    // Convert working minutes to hours
                    double workingHours = workingMinutes / 60.0;

// Add working hours to shift hours
                    double totalHours = currentShiftHrs + workingHours;

// Convert total hours to HH:mm format
                    int hours = (int) totalHours;
                    int minutes = (int) ((totalHours - hours) * 60);

                    String formattedTotalHours = String.format("%02d:%02d", hours, minutes);

// Update working minutes with the new calculated time
                    empAtt.setWorking_minutes(formattedTotalHours);
//                    empAtt.setWorking_minutes("12:00");
                }
            }

            // Concatenate in_time and out_time using StringBuilder
            StringBuilder inOutTime = new StringBuilder(inTime).append("/").append(outTime);

            boolean isPRJobType = empAtt.getJob_type() != null && presentyValue != null && presentyValue.toString().startsWith(empAtt.getJob_type());

            if (htAttendanceMonthlyModel.getAttendance_process_id() != null && inOutTimeValue != null
                    && inOutTimeValue.toString().trim().equals(inOutTime.toString().trim()) && isPRJobType)
                return;
            boolean halfDayLeaveStatus = hasEmployeeTakenHalfDayLeave(shortLeaveApplicationDetails, empAtt.getEmployee_code(), empAtt.getAtt_date_time());
            boolean hasEmployeeTakenShortLeave = hasEmployeeTakenShortLeave(shortLeaveApplicationDetails, empAtt.getEmployee_code(), empAtt.getAtt_date_time());
            boolean hasGatePassStatus = hasEmployeeTakenGatePass(shortLeaveApplicationDetails, empAtt.getEmployee_code(), empAtt.getAtt_date_time());

            // Build the extra marks
            StringBuilder extraMark = new StringBuilder();
            if ("Y".equals(empAtt.getLate_mark_flag())) {
                extraMark.append("/L");
            }
            if ("Y".equals(empAtt.getEarly_go_flag())) {
                extraMark.append("/E");

            }
            if ("Y".equals(empAtt.getGate_pass_flag())) {
                if (halfDayLeaveStatus) {
                    extraMark.append("/HDL"); // Half Day Leave
                } else if (hasEmployeeTakenShortLeave && !halfDayLeaveStatus) {
                    extraMark.append("/SL"); // Short Leave
                } else if (hasGatePassStatus && !halfDayLeaveStatus) {
                    extraMark.append("/G"); // General Gate Pass
                }
            }


            String jobTypeShortName = "";
//          // If Employee On Leave then set the it's LeaveType as a attendace_Status other wise send as it is job_type_shortName;
//			if (empAtt.getJob_type_id() == LEAVES) {
//				jobTypeShortName = getLeaveTypeCode(leaveApplicationDetails, empAtt.getEmployee_code());
//			} else {
//				jobTypeShortName = empAtt.getJob_type();
//			}
            jobTypeShortName = empAtt.getJob_type();
            // Set the values for the fields
            inOutTimeField.set(htAttendanceMonthlyModel, inOutTime.toString());  // Store in_out time by concatenation

            if ("Workers".equals(empAtt.getEmployee_type()) && empAtt.getJob_type_id() != LEAVES) {
                jobTypeShortName = empAtt.getJob_type();
                presentyField.set(htAttendanceMonthlyModel, jobTypeShortName + extraMark);  // Present flag with extra marks
            } else {
                presentyField.set(htAttendanceMonthlyModel, jobTypeShortName + extraMark);  // Present flag with extra marks
            }
//            double formattedHours = parseTimeToHours(formattedTime);
            double workingMinutes = 0.0;
            if (empAtt.getWorking_minutes() != null && empAtt.getWorking_minutes().contains(":")) {
                String[] parts = empAtt.getWorking_minutes().split(":");
                int hours = Integer.parseInt(parts[0]);
                int minutes = Integer.parseInt(parts[1]);
//                workingMinutes = hours + (minutes / 60.0);
//                workingMinutes = Double.parseDouble(String.format("%02d.%02d", hours, minutes));
                workingMinutes = Double.parseDouble(String.format("%02d.%02d", Math.abs(hours), Math.abs(minutes)));
            }
//			CALCULATE TOTAL HOURS

            Double totalShiftHrs = FnCalculateHoursInDecimal(empAtt.getShift_start_end_time());
            Double workingHours = parseTimeToHours(empAtt.getWorking_minutes());

// Ensure workingHours does not exceed totalShiftHrs
//            if (workingHours > totalShiftHrs && !"Semi-Staff".equals(empAtt.getEmployee_type_group()) && !"HD".equals(empAtt.getJob_type())) {
//            if (workingHours > totalShiftHrs && "Semi-Staff".equals(empAtt.getEmployee_type_group()) && !("HD".equals(empAtt.getJob_type()) || "HP".equals(empAtt.getJob_type()))) {
//                workingHours = workingHours;
//            } else {
//                workingHours = totalShiftHrs;
//            }
            if (workingHours > totalShiftHrs) {
                workingHours = totalShiftHrs;
            }
            if (workingHours >= totalShiftHrs && "Semi-Staff".equals(empAtt.getEmployee_type_group()) && ("HD".equals(empAtt.getJob_type()) || "HP".equals(empAtt.getJob_type()))) {
                Double workingHour = parseTimeToHours(empAtt.getWorking_minutes());
                workingHours = workingHour;

            }
            htAttendanceMonthlyModel.setMonthly_hours(
                    empAtt.getWorking_minutes() != null
//                            ? htAttendanceMonthlyModel.getMonthly_hours() - prevHours + workingMinutes
                            ? htAttendanceMonthlyModel.getMonthly_hours() - prevHours + workingHours
                            : htAttendanceMonthlyModel.getMonthly_hours()
            );

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static double parseTimeToHours(String time) {
        if (time == null || time.isEmpty()) {
            return 0.0;
        }

        try {
            String[] parts = time.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            return hours + (minutes / 60.0);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid time format: " + time);
            return 0.0;
        }
    }

    public static void setWeeklyOffDays(CHtAttendanceMonthlyModel htAttendanceMonthlyModel, int year, int month, String weeklyoffName, List<String> thisMonthHolidaysDates, boolean attendance_exclude_flag) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int weeklyOffCount = 0;
        // Convert holiday dates to a set for fast lookup
        Set<String> holidayDatesSet = thisMonthHolidaysDates.stream().collect(Collectors.toSet());

        // Get today's date
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        // Check if the given year and month match the current year and month
        if (year == currentYear && month == currentMonth) {
            int attendanceDayOfMonth = today.getDayOfMonth();
            for (int i = 1; i <= attendanceDayOfMonth; i++) {
                LocalDate currentDay = LocalDate.of(year, month, i);
                String currentDayString = currentDay.format(formatter);
                // Check if the current day is a weekly off and not a holiday
//                if (currentDay.getDayOfWeek().name().equalsIgnoreCase(weeklyoffName) && (attendance_exclude_flag || !holidayDatesSet.contains(currentDayString))) {
//                    weeklyOffCount++;
//                }
//               // Removed the condition for auto_attendace_flag;(Because for the also set the holiday)
                if (currentDay.getDayOfWeek().name().equalsIgnoreCase(weeklyoffName)
//                        && (!holidayDatesSet.contains(currentDayString))
                ) {
                    weeklyOffCount++;
                }
            }
        } else {
            // If the given year and month do not match the current year and month, count for the entire month
            LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
            LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth());
            for (LocalDate date = firstDayOfMonth; !date.isAfter(lastDayOfMonth); date = date.plusDays(1)) {
                String currentDayString = date.format(formatter);
                // Check if the current day is a weekly off and not a holiday
//                if (date.getDayOfWeek().name().equalsIgnoreCase(weeklyoffName) && (attendance_exclude_flag || !holidayDatesSet.contains(currentDayString))) {
//                    weeklyOffCount++;
//                }
                // Removed the condition for auto_attendace_flag;(Because for the also set the holiday)
                if (date.getDayOfWeek().name().equalsIgnoreCase(weeklyoffName)
//                        && (!holidayDatesSet.contains(currentDayString))
                ) {
                    weeklyOffCount++;
                }
            }
        }
        htAttendanceMonthlyModel.setWeek_off_days(weeklyOffCount);
    }

    //	// Supportable format HH:MM && HH:MM:SS
    private static Duration FnCalculateHoursDiff(String fromTime, String toTime) {
        String punchInTime = fromTime, punchOutTime = toTime;
        LocalDateTime punchIn = LocalDateTime.of(LocalDate.now(), LocalTime.parse(punchInTime, determineFormatter(punchInTime)));
        LocalDateTime punchOut = LocalDateTime.of(LocalDate.now(), LocalTime.parse(punchOutTime, determineFormatter(punchOutTime)));
        // Check if punch-out time is before punch-in time, indicating it's the next day
        if (punchOut.isBefore(punchIn)) {
            punchOut = punchOut.plusDays(1); // Add a day to punch-out time
        }
        Duration duration = Duration.between(punchIn, punchOut);
        return duration;
    }

    //	// Supportable format 08:00:00-20:00:00 && 08:00:00/20:00:00 && 08:00-20:00 && 08:00/20:00
    private static Double FnCalculateHoursInDecimal(String startEndTime) {
        String[] times;
        if (startEndTime.contains("-")) {
            times = startEndTime.split("-");
        } else {
            times = startEndTime.split("/");
        }
        String start = times[0], end = times[1];

        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(start, determineFormatter(start)));
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(end, determineFormatter(end)));
        // Check if end time is before start time, indicating it's the next day
        if (endTime.isBefore(startTime)) {
            endTime = endTime.plusDays(1); // Add a day to end time
        }
        Duration duration = Duration.between(startTime, endTime);
        Double calculatedHrs = duration.toMinutes() / 60.0;
        return calculatedHrs;
    }

    private static DateTimeFormatter determineFormatter(String time) {
        if (time.contains(":") && time.split(":").length == 3) {
            return DateTimeFormatter.ofPattern("HH:mm:ss");
        } else {
            return DateTimeFormatter.ofPattern("HH:mm");
        }
    }

    //	GET SHIFT ROSTER DETAILS
    public List<CHtShiftRosterModel> FnGetShiftRosterDetails(List<String> employeeCodes, List<Integer> months, List<Integer> years) {
        List<CHtShiftRosterModel> employeeShiftDetails = iHtShiftRosterRepository.FnGetShiftRosterDetails(employeeCodes, months, years);
        return employeeShiftDetails;
    }

    //   DEFINE A HELPER METHOD TO EXTRACT THE CORRECT SHIFT NAME BASED ON THE DAY OF THE MONTH
    private Map<String, String> getShiftDetailsForDay(CHtShiftRosterModel shiftRoster, int day) {
        Map<String, String> shiftDetails = new HashMap<>();
        try {
            Field shiftIdField = CHtShiftRosterModel.class.getDeclaredField("shift_id" + day);
            Field shiftNameField = CHtShiftRosterModel.class.getDeclaredField("shift_name" + day);
            shiftIdField.setAccessible(true);
            shiftNameField.setAccessible(true);
            String shiftId = (String) shiftIdField.get(shiftRoster);
            String shiftName = (String) shiftNameField.get(shiftRoster);
            shiftDetails.put("shift_id", shiftId);
            shiftDetails.put("shift_name", shiftName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            shiftDetails.put("shift_id", null);
            shiftDetails.put("shift_name", null);
        }
        return shiftDetails;
    }

    //    @GetMapping("/FnShowSchedulerData")
//    public ResponseEntity<Map<String, Object>> FnShowSchedulerData() {
//        Map<String, Object> response = new LinkedHashMap<>();
//        long pythonAPIStartTime = System.currentTimeMillis();
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> apiResponse = restTemplate.getForEntity(attendanceProcessUrl, String.class);
//        long pythonAPIEndTime = System.currentTimeMillis();
//        System.out.println("Python API For Log Finish In " + (pythonAPIEndTime - pythonAPIStartTime) + " milliseconds");
//
//        // Check if API call was successful
//        if (apiResponse.getStatusCode() != HttpStatus.OK) {
//            response.put("success", 0);
//            response.put("error", "Failed to fetch attendance data from external API");
    ////            return response;
//        }
//
//        return ResponseEntity.ok(response);
//    }
    @GetMapping("/FnShowSchedulerData")
    public ResponseEntity<Map<String, Object>> FnShowSchedulerData() {
        Map<String, Object> response = new LinkedHashMap<>();
        long pythonAPIStartTime = System.currentTimeMillis();
        int maxRetries = 3;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            try {
                RestTemplate restTemplate = new RestTemplate();
                ResponseEntity<String> apiResponse = restTemplate.getForEntity(attendanceProcessUrl, String.class);
                long pythonAPIEndTime = System.currentTimeMillis();
                System.out.println("Python API For Log Finish In " + (pythonAPIEndTime - pythonAPIStartTime) + " milliseconds");

                // Check if API call was successful
                if (apiResponse.getStatusCode() != HttpStatus.OK) {
                    response.put("success", 0);
                    response.put("error", "Failed to fetch attendance data from external API");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }

                // Process the successful API response
                response.put("success", 1);
                response.put("data", apiResponse.getBody());  // Add the actual API data here

            } catch (Exception e) {

                retryCount++;
                System.out.println("Retrying... Attempt: " + retryCount + " Error: " + e.getMessage());
                if (retryCount >= maxRetries) {
                    response.put("success", 0);
                    response.put("error", "Error occurred while calling external API: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
                }
            }
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/FnShowEmployeesDetails")
    public Map<String, Object> FnShowEmployeeDetails(@RequestBody CEmployeeWiseAttendanceRequest employeeWiseAttendanceRequest) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            long javaAPIStartTime = System.currentTimeMillis();

            int company_id = employeeWiseAttendanceRequest.getCompany_id();
            String employeeTypeGroup = employeeWiseAttendanceRequest.getEmployee_type_group();
            String shiftName = employeeWiseAttendanceRequest.getShift_name();
            AtomicBoolean two_day_shift = new AtomicBoolean(employeeWiseAttendanceRequest.isTwo_day_shift());
            Integer department_id = employeeWiseAttendanceRequest.getDepartment_id();
            Integer subDepartment_id = employeeWiseAttendanceRequest.getSub_department_id();
            String employeeId = employeeWiseAttendanceRequest.getEmployee_id();
            // Extract date range from the request
            LocalDate fromDate = LocalDate.parse(employeeWiseAttendanceRequest.getAttendance_date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String toDateStr = employeeWiseAttendanceRequest.getDt_att_To_date();
            LocalDate toDate = toDateStr != null && !toDateStr.isEmpty() ? LocalDate.parse(toDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")) : fromDate;

//			GET MONTHS AND YEARS LIST FOR SHIFT ROSTER DETAILS INFO
            List<Integer> monthList = new ArrayList<>();
            List<Integer> yearList = new ArrayList<>();

            // Loop through months between fromDate and toDate
            LocalDate currentMonthStart = fromDate;
            while (!currentMonthStart.isAfter(toDate)) {
                monthList.add(currentMonthStart.getMonthValue());
                yearList.add(currentMonthStart.getYear());
                currentMonthStart = currentMonthStart.plusMonths(1);
            }

            List<Map<String, Object>> employeeDateAndShiftWiseDetails = new ArrayList<>();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT ")
                    .append("employee_id, ")
                    .append("employee_code, ")
                    .append("employee_type, ")
                    .append("employee_type_group, ")
                    .append("employee_name, ")
                    .append("department_name, ")
                    .append("sub_department_name, ")
                    .append("designation_name, ")
                    .append("shift_name, ")
                    .append("weeklyoff_name, ")
                    .append("department_group, ")
                    .append("old_employee_code, ")
                    .append("machine_employee_code, ")
                    .append("shift_start_end_time, ")
                    .append("shift_grace_hours_min, ")
                    .append("shift_grace_hours_max, ")
                    .append("halfday_hours, ")
                    .append("fullday_hours, ")
                    .append("grace_early_time, ")
                    .append("grace_late_time, ")
                    .append("two_day_shift, ")
                    .append("attendance_exclude_flag, ")
                    .append("department_id, ")
                    .append("shift_id, ")
                    .append("job_type_id, job_type_short_name, ")
                    .append("sub_department_id ")
                    .append("FROM cmv_employee ")
                    .append("WHERE company_id = ? and is_active = 1 ")
                    .append("AND employee_type_group = ? ");

            List<Object> params = new ArrayList<>();
            params.add(company_id);
            params.add(employeeTypeGroup);

            if (shiftName != null && !shiftName.isEmpty() && !shiftName.equals("All")) {
                queryBuilder.append("AND shift_name = ? ");
                params.add(shiftName);
            }

            if (department_id != null && department_id != 0) {
                queryBuilder.append("AND department_id = ? ");
                params.add(department_id);
            }
            if (subDepartment_id != null && subDepartment_id != 0) {
                queryBuilder.append("AND sub_department_id = ? ");
                params.add(subDepartment_id);
            }
            if (employeeId != null && !employeeId.isEmpty() && !employeeId.equals("All")) {
                queryBuilder.append("AND employee_id = ? ");
                params.add(employeeId);
            }
            queryBuilder.append("ORDER BY sub_department_id, department_id,  shift_name ASC");

            List<CEmployeesViewModel> employeeRecords = executeQuery.query(queryBuilder.toString(), params.toArray(), new BeanPropertyRowMapper<>(CEmployeesViewModel.class));

//			GET SHIFT DETAILS FROM ht_shift_roster
            List<String> getEmployeeCodes = employeeRecords.stream().map(CEmployeesViewModel::getEmployee_code).collect(Collectors.toList());
            List<CHtShiftRosterModel> getShiftRosterDetails = FnGetShiftRosterDetails(getEmployeeCodes, monthList, yearList);

//			GET SHIFTS FROM SHIFT MASTER
            List<CShiftModel> getShiftDetails = iShiftRepository.getAllShiftDetails();

//			GET ALL ATTENDANCE DETAILS
            List<CHmAttendanceStatusModel> attendanceStatusDetails = iHmAttendanceStatusRepository.getAttendanceStatusDetails();

//			GET ALL JOB TYPES
            List<CJobTypeModel> jobTypeDetails = iJobTypeRepository.getJobTypeDetails(company_id);

//			GET ALL HOLIDAYS BETN THAT DATE
            List<CXmProductionHolidayModel> currentMonthHolidays = iXmProductionHolidayRepository.FnGetHolidaysBetweenTwoDates(fromDate.toString(), toDateStr);

//			GET THE LEAVE APPLICATIONS OF EMPLOYEES
            List<Map<String, Object>> leaveApplicationDetails = iHmLeavesApplicationsRepository.getLeavesApplicationDetails(fromDate, toDate, getEmployeeCodes);

//          GET ALL DATES BETWEEN FROMDATE AND TODATE
            List<LocalDate> dateList = Stream.iterate(fromDate, date -> date.plusDays(1))
                    .limit(fromDate.until(toDate).getDays() + 1)
                    .collect(Collectors.toList());

//          Convert dateList to a comma-separated string for SQL IN clause
            String formattedDates = dateList.stream()
                    .map(date -> "'" + date + "'")  // Add single quotes around each date
                    .collect(Collectors.joining(", "));

//          PRINT THE RESULT
//			dateList.forEach(System.out::println);

//			QUERY TO FETCH SHORT LEAVES
            String shortLeavesQuery = String.format(
                    "SELECT * FROM ht_short_leave " +
                            "WHERE DATE(short_leave_date) IN (%s) " +
                            "AND approval_status = 'Approved' " +
                            "AND is_delete = 0", formattedDates);

            List<CHtShortLeaveModel> shortLeaveApplicationDetails = executeQuery.query(shortLeavesQuery, new BeanPropertyRowMapper<>(CHtShortLeaveModel.class));

//          QUERY TO FETCH ATTENDANCE LOG DETAILS
            // List of punching codes
            List<String> getPunchingCodes = employeeRecords.stream()
                    .map(CEmployeesViewModel::getOld_employee_code)
                    .collect(Collectors.toList());

//          QUERY TO FETCH ATTENDANCE LOG DETAILS
            StringBuilder attendanceQuery = new StringBuilder();
            attendanceQuery.append("SELECT * FROM hmv_att_log ");
            attendanceQuery.append("WHERE DATE(STR_TO_DATE(att_date_time, '%Y-%m-%d %H:%i:%s')) BETWEEN ? AND ? ");

//          Add IN clause for employee_code if list is not empty
            if (!getPunchingCodes.isEmpty()) {
                // Append placeholders for IN clause
                String placeholders = getPunchingCodes.stream()
                        .map(code -> "?")
                        .collect(Collectors.joining(", "));
                attendanceQuery.append("AND att_device_emp_code IN (").append(placeholders).append(") ");
            }

            List<Object> attendanceParams = new ArrayList<>();
            attendanceParams.add(fromDate.toString());
            attendanceParams.add(toDate.plusDays(1).toString());

//          Add all employee codes as parameters
            attendanceParams.addAll(getPunchingCodes);

//          Execute query with parameters
            List<Map<String, Object>> attendanceLogDetails = executeQuery.queryForList(attendanceQuery.toString(), attendanceParams.toArray());

            // GET MISSPUNCH CORRECTION DATA Start **************************************************

            //          QUERY TO FETCH ATTENDANCE LOG DETAILS
            StringBuilder attendanceQueryCorrection = new StringBuilder();
            attendanceQueryCorrection.append("SELECT * FROM ht_misspunch_correction ");
//			attendanceQueryCorrection.append("WHERE DATE(STR_TO_DATE(att_date_time, '%Y-%m-%d %H:%i:%s')) BETWEEN ? AND ? ");
            attendanceQueryCorrection.append("WHERE DATE(att_date_time) BETWEEN ? AND ? ");
//          Add IN clause for employee_code if list is not empty
            if (!getPunchingCodes.isEmpty()) {
                // Append placeholders for IN clause
                String placeholders = getPunchingCodes.stream()
                        .map(code -> "?")
                        .collect(Collectors.joining(", "));
                attendanceQueryCorrection.append("AND punch_code IN (").append(placeholders).append(") ");
            }

            List<Object> attendanceParameter = new ArrayList<>();
            attendanceParameter.add(fromDate.toString());
            attendanceParameter.add(toDate.plusDays(1).toString());

//          Add all employee codes as parameters
            attendanceParameter.addAll(getPunchingCodes);

//          Execute query with parameters
            List<Map<String, Object>> attendanceMisspunchCorrection = executeQuery.queryForList(attendanceQueryCorrection.toString(), attendanceParameter.toArray());

            // GET MISSPUNCH CORRECTION DATA End  **************************************************


//			GET TODAY & PREVIOUS DATE DATA
            List<CHtAttendanceDailyModel> getDailyAttendanceDetails = iHtAttendanceDailyRepository.getDailyAttForCurrentNdPreviousDay(employeeTypeGroup, getEmployeeCodes, String.valueOf(fromDate.minusDays(1)), toDateStr, company_id);

//		    // ** Get the HsRPayroll Setting;
            CHmHrpayrollSettingsModel hrPayrollSettings = iHmHrpayrollSettingsRepository.FnGetPayrollSettingByCompanyId(company_id);


//          Iterate over the date range
            for (LocalDate currentDate = fromDate; !currentDate.isAfter(toDate); currentDate = currentDate.plusDays(1)) {
//              CONVERT LOCALDATE TO STRING FOR THE QUERY
                String attendanceDate = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//			    USING ATTENDANCE DATE GET DAY NAME
//              String attendanceDate = employeeWiseAttendanceRequest.getAttendance_date();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(attendanceDate, formatter);

//              EXTRACT DateNo, MONTH AND YEAR
                int monthDay = date.getDayOfMonth();
                int month = date.getMonthValue(); // RETURNS THE MONTH (1-12)
                int year = date.getYear(); // RETURNS THE YEAR

//          Get It's previouse date; Determine the previous date as a LocalDate
                LocalDate previousDate;
                if (date.getDayOfMonth() == 1) { // Get the last day of the previous month
                    previousDate = date.minusMonths(1).withDayOfMonth(date.minusMonths(1).lengthOfMonth());
                } else {    // Get the previous day
                    previousDate = date.minusDays(1);
                }

                DayOfWeek dayOfWeek = date.getDayOfWeek();
                String dayName = dayOfWeek.name();

//			   CHECK ITS HOLIDAY OR NOT
                boolean isHolidayFound = currentMonthHolidays.parallelStream()
                        .anyMatch(holiday -> holiday.getProduction_holiday_date().equals(date.format(formatter)));

//              Set the isHoliday flag based on whether a matching holiday was found
                int isHoliday = isHolidayFound ? 1 : 0;

//              QUERY TO FETCH ATTENDANCE LOG DETAILS
//				StringBuilder attendanceQuery = new StringBuilder();
//				attendanceQuery.append("SELECT * FROM hmv_att_log ");
//
//				List<Object> attendanceParams = new ArrayList<>();
//				LocalDate attendanceLocalDate = LocalDate.parse(attendanceDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//				if (attendanceDate != null && !attendanceDate.isEmpty()) {
//					if (two_day_shift.get() || shiftName.equals("All")) {
//						LocalDate nextDate = attendanceLocalDate.plusDays(1);
//						attendanceQuery.append("WHERE DATE(STR_TO_DATE(att_date_time, '%Y-%m-%d %H:%i:%s')) IN (?, ?) ");
//						attendanceParams.add(attendanceDate);
//						attendanceParams.add(nextDate.toString());
//					} else {
//						attendanceQuery.append("WHERE DATE(STR_TO_DATE(att_date_time, '%Y-%m-%d %H:%i:%s')) = ? ");
//						attendanceParams.add(attendanceDate);
//					}
//				}
//
//				List<Map<String, Object>> attendanceLogDetails = executeQuery.queryForList(attendanceQuery.toString(), attendanceParams.toArray());

//			    QUERY TO FETCH LEAVE APPLICATION DETAILS
//				List<Map<String, Object>> leaveApplicationDetails = iHmLeavesApplicationsRepository.getLeavesApplicationDetails(attendanceDate);  // get leave application details

//			    QUERY TO FETCH SHORT LEAVES
//				String shortLeavesQuery = "SELECT * FROM ht_short_leave " +
//						"WHERE DATE(out_time) = " +
//						"'" + attendanceDate + "'" +
//						"AND approval_status = 'Approved' " +
//						"AND is_delete = 0";
//
//				List<Map<String, Object>> shortLeaveApplicationDetails = executeQuery.queryForList(shortLeavesQuery);   // get short leave application details

                List<Map<String, Object>> employeeShiftWiseDetails = new ArrayList<>();

//				Get PreviousDay DailyShiftManagement;
//				List<CHtAttendanceDailyModel> currentNdPreviousDayDailyAttAllEmpl = iHtAttendanceDailyRepository.getDailyAttForCurrentNdPreviousDay(employeeTypeGroup, attendanceDate, String.valueOf(previousDate), company_id);
                List<CHtAttendanceDailyModel> currentNdPreviousDayDailyAttAllEmpl = getDailyAttendanceDetails.stream()
                        .filter(log -> log.getAtt_date_time().equals(attendanceDate) || log.getAtt_date_time().equals(date.minusDays(1).toString()))
                        .collect(Collectors.toList());

//			ITERATE ON EMPLOYEE DETAILS FOR IN OUT TIMINGS
                employeeRecords.stream()
                        .forEach(employeeItem -> {
//						    CONVERT EMPLOYEEITEM TO A MAP<STRING, OBJECT>
                            Map<String, Object> employeeMap = convertEmployeeItemToMap(employeeItem);

                            Integer shift_id = employeeItem.getShift_id();
                            Double halfday_hours = employeeItem.getHalfday_hours();
                            Double fullday_hours = employeeItem.getFullday_hours();
                            Double grace_early_time = employeeItem.getGrace_early_time();
                            Double grace_late_time = employeeItem.getGrace_late_time();
                            String shift_start_end_time = employeeItem.getShift_start_end_time();
                            Integer shift_grace_hours_min = employeeItem.getShift_grace_hours_min();
                            Integer shift_grace_hours_max = employeeItem.getShift_grace_hours_max();
                            two_day_shift.set(employeeItem.isTwo_day_shift());

                            employeeMap.put("attendance_date", monthDay);
                            employeeMap.put("attendance_month", month);
                            employeeMap.put("attendance_year", year);

//						CHECK IF THE EMPLOYEE IS IN THE SHIFT ROSTER DETAILS
                            Optional<CHtShiftRosterModel> optionalShiftRoster = getShiftRosterDetails.stream()
                                    .filter(roster -> roster.getEmployee_code().equals(employeeItem.getEmployee_code())
                                            && roster.getAttendance_year().equals(year)
                                            && roster.getAttendance_month().equals(month))
                                    .findFirst();

                            if (optionalShiftRoster.isPresent()) {
                                CHtShiftRosterModel shiftRoster = optionalShiftRoster.get();
                                int dayOfMonth = date.getDayOfMonth();
                                Map<String, String> shiftDetailsForDay = getShiftDetailsForDay(shiftRoster, dayOfMonth);

//							IF ROSTER SHIFT NAME IS DIFFERENT FROM INCOMING SHIFT THEN DONT SEND EMPLOYEE TO FRONTEND because of that its return
                                if (shiftDetailsForDay.get("shift_name") != null && !"all".equalsIgnoreCase(shiftName) && !shiftDetailsForDay.get("shift_name").equals(shiftName)) {
                                    return;
                                }

//							FIND SHIFT DEATILS NOW FROM SHIFT MASTER ONLY IF EMPLOYEE SHIFT DIFFERENT FROM EMPLOYEE MASTER
                                if (shiftDetailsForDay.get("shift_id") != null && !String.valueOf(shift_id).equals(shiftDetailsForDay.get("shift_id"))) {
                                    CShiftModel shiftModel = getShiftDetails.parallelStream()
                                            .filter(shift -> String.valueOf(shift.getShift_id()).equals(shiftDetailsForDay.get("shift_id"))).findFirst().get();

                                    halfday_hours = shiftModel.getHalfday_hours();
                                    fullday_hours = shiftModel.getFullday_hours();
                                    grace_early_time = Double.valueOf(shiftModel.getGrace_early_time());
                                    grace_late_time = Double.valueOf(shiftModel.getGrace_late_time());
                                    shift_grace_hours_min = shiftModel.getShift_grace_hours_min();
                                    shift_grace_hours_max = shiftModel.getShift_grace_hours_max();
                                    shift_start_end_time = shiftModel.getStart_time() + "-" + shiftModel.getEnd_time();
                                    two_day_shift.set(shiftModel.isTwo_day_shift());

                                    employeeMap.put("shift_name", shiftModel.getShift_name());
                                    employeeMap.put("shift_start_end_time", shift_start_end_time);
                                    employeeMap.put("halfday_hours", halfday_hours);
                                    employeeMap.put("fullday_hours", fullday_hours);
                                    employeeMap.put("grace_early_time", grace_early_time);
                                    employeeMap.put("grace_late_time", grace_late_time);
                                    employeeMap.put("two_day_shift", two_day_shift.get());
                                    employeeMap.put("shift_present", shiftModel.getShift_name());
                                }

                            }


//				        SET DEPARTMENT & SUB-DEPARTMENT ID
                            employeeMap.put("shift_grace_hours_min", shift_grace_hours_min);
                            employeeMap.put("shift_grace_hours_max", shift_grace_hours_max);
                            employeeMap.put("department_id", employeeItem.getDepartment_id());
                            employeeMap.put("sub_department_id", employeeItem.getSub_department_id());
                            employeeMap.put("att_date_time", attendanceDate);
                            String weeklyOffName = employeeItem.getWeeklyoff_name();

                            List<Map<String, Object>> findEmployeeAttendanceDetls;
                            if (two_day_shift.get()) {
                                LocalDate nextDate = date.plusDays(1);
//                                findEmployeeAttendanceDetls = attendanceLogDetails.stream()
//                                        .filter(item -> (item.get("att_device_emp_code").equals(employeeItem.getOld_employee_code())
//                                                || item.get("att_device_emp_code").equals(employeeItem.getMachine_employee_code()))
//                                                && (LocalDate.parse(item.get("att_date_time").toString().substring(0, 10), formatter).equals(date)
//                                                || LocalDate.parse(item.get("att_date_time").toString().substring(0, 10), formatter).equals(nextDate)))
//                                        .collect(Collectors.toList());
                                findEmployeeAttendanceDetls = attendanceLogDetails.stream()
                                        .filter(item -> (
                                                        item.get("att_device_emp_code").toString().trim().equals(employeeItem.getOld_employee_code().trim())
                                                                || item.get("att_device_emp_code").toString().trim().equals(employeeItem.getMachine_employee_code().trim()))
                                                        && (
                                                        LocalDate.parse(item.get("att_date_time").toString().substring(0, 10), formatter).equals(date)
                                                                || LocalDate.parse(item.get("att_date_time").toString().substring(0, 10), formatter).equals(nextDate)
                                                )
                                        )
                                        .collect(Collectors.toList());
                            } else {
//                                findEmployeeAttendanceDetls = attendanceLogDetails.stream()
//                                        .filter(item -> (item.get("att_device_emp_code").equals(employeeItem.getOld_employee_code())
//                                                || item.get("att_device_emp_code").equals(employeeItem.getMachine_employee_code()))
//                                                && LocalDate.parse(item.get("att_date_time").toString().substring(0, 10), formatter).equals(date))
//                                        .collect(Collectors.toList());
                                findEmployeeAttendanceDetls = attendanceLogDetails.stream()
                                        .filter(item -> (item.get("att_device_emp_code").toString().trim().equals(employeeItem.getOld_employee_code().trim())
                                                || item.get("att_device_emp_code").toString().trim().equals(employeeItem.getMachine_employee_code().trim()))
                                                && LocalDate.parse(item.get("att_date_time").toString().substring(0, 10), formatter).equals(date))
                                        .collect(Collectors.toList());
                            }

//				        GET SHIFT START AND END TIMES
                            String shiftStartEndTime = shift_start_end_time;
                            if (shiftStartEndTime != null && !shiftStartEndTime.isEmpty()) {
                                String[] startEndTimes = shiftStartEndTime.split("-");
                                if (startEndTimes.length == 2) {
                                    LocalTime shiftStartTime = LocalTime.parse(startEndTimes[0].trim(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                                    LocalTime shiftEndTime = LocalTime.parse(startEndTimes[1].trim(), DateTimeFormatter.ofPattern("HH:mm:ss"));

//						        PARSE SHIFT_GRACE_HOURS_MIN AND SHIFT_GRACE_HOURS_MAX
//								int graceHoursMin = employeeItem.getShift_grace_hours_min();
//								int graceHoursMax = employeeItem.getShift_grace_hours_max();
                                    LocalTime adjustedShiftStartTime = shiftStartTime.minusHours(shift_grace_hours_min);
                                    LocalTime adjustedShiftEndTime = shiftEndTime.plusHours(shift_grace_hours_max);

                                    Integer finalShift_grace_hours_max = shift_grace_hours_max;
                                    Optional<LocalDateTime> minDateTime = findEmployeeAttendanceDetls.stream()
                                            .map(item -> LocalDateTime.parse((CharSequence) item.get("att_date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                            .filter(dateTime -> {
                                                LocalDate logDate = dateTime.toLocalDate();
                                                LocalTime time = dateTime.toLocalTime();
                                                // FILTER FOR LOGDATE EQUAL TO DATE AND TIME BETWEEN ADJUSTEDSHIFTSTARTTIME AND SHIFTSTARTTIME.PLUSHOURS(SHIFT_GRACE_HOURS_MAX)
                                                return logDate.equals(date) && !time.isBefore(adjustedShiftStartTime) && !time.isAfter(shiftStartTime.plusHours(finalShift_grace_hours_max));
                                            })
                                            .min(LocalDateTime::compareTo);

//									Optional<LocalDateTime> maxDateTime = findEmployeeAttendanceDetls.stream()
//											.map(item -> LocalDateTime.parse((CharSequence) item.get("att_date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
//											.filter(dateTime -> {
//												LocalTime time = dateTime.toLocalTime();
//												return !time.isAfter(adjustedShiftEndTime);
//											})
//											.max(LocalDateTime::compareTo);

                                    Optional<LocalDateTime> maxDateTime = findEmployeeAttendanceDetls.stream()
                                            .map(item -> LocalDateTime.parse((CharSequence) item.get("att_date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                            .filter(dateTime -> {
                                                LocalDate logDate = dateTime.toLocalDate();
                                                LocalTime time = dateTime.toLocalTime();

                                                // Check if it's a two-day shift
                                                if (two_day_shift.get()) {
                                                    LocalDate nextDate = date.plusDays(1);

                                                    // Condition for two-day shift: check log time on current date or next date, up to the adjustedShiftEndTime
                                                    return (logDate.equals(date) && !time.isAfter(LocalTime.MIDNIGHT)) ||
                                                            (logDate.equals(nextDate) && !time.isAfter(adjustedShiftEndTime));
                                                } else {
                                                    // Single day shift: check only up to adjustedShiftEndTime on the same day
                                                    return logDate.equals(date) && !time.isAfter(adjustedShiftEndTime);
                                                }
                                            })
                                            .max(LocalDateTime::compareTo);

//						            CHECK IF MAXDATETIME IS GREATER THAN MINDATETIME
                                    if (minDateTime.isPresent() && maxDateTime.isPresent()) {
                                        long minimumDifferenceInMinutes = 1; // Specify the minimum difference in minutes
                                        if (!maxDateTime.get().isAfter(minDateTime.get().plusMinutes(minimumDifferenceInMinutes))) {
                                            maxDateTime = Optional.empty();
                                        }
                                    }


//                              // For AUTO Attendance add IN_PUNCH & OUT_PUNCH
                                    if (employeeItem.isAttendance_exclude_flag()) {
                                        boolean isOnLeave = hasEmployeeTakenLeave(leaveApplicationDetails, employeeItem.getEmployee_code(), attendanceDate);
                                        boolean isWeeklyOff = weeklyOffName.equalsIgnoreCase(dayName);
                                        if (!isOnLeave && !isWeeklyOff) {
//                                      IF MINDATETIME IS NOT PRESENT AND ATTENDANCE_EXCLUDE_FLAG IS TRUE, SET MINDATETIME TO SHIFT START TIME
                                            if (!minDateTime.isPresent()) {
                                                minDateTime = Optional.of(shiftStartTime.atDate(date));
                                            }
//                                      IF MAXDATETIME IS NOT PRESENT AND ATTENDANCE_EXCLUDE_FLAG IS TRUE, SET MAXDATETIME TO SHIFT END TIME
                                            if (!maxDateTime.isPresent()) {
                                                maxDateTime = Optional.of(shiftEndTime.atDate(date));
                                            }
                                        }
                                    }

                                    // CHECK IF MINDATETIME AND MAXDATETIME ARE EQUAL OR MAXDATETIME IS LESS THAN MINDATETIME
                                    if (minDateTime.isPresent() && maxDateTime.isPresent()) {
                                        if (minDateTime.get().equals(maxDateTime.get()) || maxDateTime.get().isBefore(minDateTime.get())) {
                                            if (employeeItem.isAttendance_exclude_flag()) {
                                                minDateTime = Optional.of(shiftStartTime.atDate(date));
                                                maxDateTime = Optional.of(shiftEndTime.atDate(date));
                                            }
                                        }
                                    }

//                              PROCESS THE MIN AND MAX DATES AS NEEDED
                                    employeeMap.put("min_att_time", minDateTime.isPresent() ? minDateTime.get().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);
                                    employeeMap.put("max_att_time", maxDateTime.isPresent() ? maxDateTime.get().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")) : null);

//                              Get the attendance date from employeeMap, if it exists
                                    LocalDate employeeAttendanceDate = Optional.ofNullable(employeeMap.get("att_date_time"))
                                            .map(dateStr -> LocalDate.parse(dateStr.toString()))
                                            .orElse(null);
                                    // Use a mutable container (array) for temporary LocalDateTime values
                                    Optional<LocalDateTime>[] tempMinAttTime = new Optional[]{Optional.empty()};
                                    Optional<LocalDateTime>[] tempMaxAttTime = new Optional[]{Optional.empty()};
//                               Check for matching employee_code and attendance_date
                                    attendanceMisspunchCorrection.stream().filter(record -> {
                                        LocalDate recordAttendanceDate = Optional.ofNullable(record.get("att_date_time"))
                                                .map(dateStr -> LocalDate.parse(dateStr.toString()))
                                                .orElse(null);
                                        return record.get("employee_code").equals(employeeMap.get("employee_code")) && recordAttendanceDate.isEqual(employeeAttendanceDate);
                                    }).findFirst().ifPresent(record -> {
                                        LocalDate attCorrectionDt = Optional.ofNullable(record.get("att_date_time"))
                                                .map(dateStr -> LocalDate.parse(dateStr.toString()))
                                                .orElse(null); // Get the attendance date

                                        // UPDATE MIN_ATT_TIME (IN_TIME) IF IT'S NULL OR EMPTY
                                        if (employeeMap.get("min_att_time") == null || employeeMap.get("min_att_time").toString().isEmpty()) {
                                            String inTimeStr = (String) record.get("in_time");
                                            employeeMap.put("punch_type", record.get("punch_type"));
                                            employeeMap.put("min_att_time", inTimeStr);
                                            tempMinAttTime[0] = Optional.ofNullable(inTimeStr)
                                                    .map(timeStr -> {
                                                        LocalTime time = LocalTime.parse(timeStr);
                                                        return LocalDateTime.of(attCorrectionDt, time);
                                                    });

                                        }
                                        // UPDATE MAX_ATT_TIME (OUT_TIME) IF IT'S NULL OR EMPTY
                                        if (employeeMap.get("max_att_time") == null || employeeMap.get("max_att_time").toString().isEmpty()) {
                                            String outTimeStr = (String) record.get("out_time");
                                            employeeMap.put("punch_type", record.get("punch_type"));
                                            employeeMap.put("max_att_time", outTimeStr);
                                            tempMaxAttTime[0] = Optional.ofNullable(outTimeStr)
                                                    .map(timeStr -> {
                                                        LocalTime time = LocalTime.parse(timeStr);
                                                        return LocalDateTime.of(attCorrectionDt, time);
                                                    });

                                        }

                                    });
                                    // Final assignment after stream processing
                                    if (tempMinAttTime[0].isPresent()) {
                                        minDateTime = tempMinAttTime[0];
                                        employeeMap.put("attendance_flag", 'M');
                                    }
                                    if (tempMaxAttTime[0].isPresent()) {
                                        maxDateTime = tempMaxAttTime[0];
                                        employeeMap.put("attendance_flag", 'M');
                                    }
                                    boolean halfDayLeaveStatus = hasEmployeeTakenHalfDayLeave(shortLeaveApplicationDetails, employeeItem.getEmployee_code(), attendanceDate);
                                    if (halfDayLeaveStatus) {
                                        Optional<LocalDateTime> minDateTimes = findEmployeeAttendanceDetls.stream()
                                                .map(item -> LocalDateTime.parse((CharSequence) item.get("att_date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                                .filter(dateTime -> dateTime.toLocalDate().equals(date)) // only match by date
                                                .min(LocalDateTime::compareTo); // get the earliest one
                                        minDateTime = minDateTimes;
                                        // Format to "HH:mm" only if present
                                        String formattedMinTime = minDateTimes
                                                .map(time -> time.format(DateTimeFormatter.ofPattern("HH:mm")))
                                                .orElse(null);

                                        employeeMap.put("min_att_time", formattedMinTime);

                                        Optional<LocalDateTime> maxDateTimes = findEmployeeAttendanceDetls.stream()
                                                .map(item -> LocalDateTime.parse((CharSequence) item.get("att_date_time"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                                .filter(dateTime -> dateTime.toLocalDate().equals(date)) // filter by date only
                                                .max(LocalDateTime::compareTo); // get the latest punch
                                        maxDateTime = maxDateTimes;
                                        String formattedMaxTime = maxDateTimes
                                                .map(time -> time.format(DateTimeFormatter.ofPattern("HH:mm")))
                                                .orElse(null);

                                        employeeMap.put("max_att_time", formattedMaxTime);
                                    }

                                    if ("Staffs".equals(employeeItem.getEmployee_type_group())) {
                                        boolean isOnLeave = leaveApplicationDetails.stream()
                                                .anyMatch(leave -> leave.get("employee_code").equals(employeeItem.getEmployee_code()));

                                        if (isOnLeave) {
                                            String leaveNearbyFlag = iHtMissPunchCorrectionRepository.checkNearbyLeave(employeeItem.getEmployee_code(), attendanceDate);
                                            Optional<CHtAttendanceDailyModel> attendanceMatch = getDailyAttendanceDetails.stream()
                                                    .filter(att -> att.getEmployee_code().equals(employeeItem.getEmployee_code()) &&
                                                            att.getAtt_date_time().equals(attendanceDate))
                                                    .findFirst();

                                            String remark = attendanceMatch.map(CHtAttendanceDailyModel::getRemark).orElse(null);

                                            if ("Yes".equals(leaveNearbyFlag) || "MP(Out)HF".equals(remark) || "MP(In)HF".equals(remark)) {


                                                if ("MP(Out)HF".equals(remark)) {
                                                    employeeMap.put("present_extra_remark", "MP(Out)HF");
                                                    employeeMap.put("remark", "MP(Out)HF");
                                                }
                                                if ("MP(In)HF".equals(remark)) {
                                                    employeeMap.put("present_extra_remark", "MP(In)HF");
                                                    employeeMap.put("remark", "MP(In)HF");
                                                }
                                                // Format times
                                                String formattedMinTime = minDateTime.map(time -> time.format(DateTimeFormatter.ofPattern("HH:mm"))).orElse(null);
                                                String formattedMaxTime = maxDateTime.map(time -> time.format(DateTimeFormatter.ofPattern("HH:mm"))).orElse(null);
                                                double halfdayminute = halfday_hours * 60;

                                                if (minDateTime.isPresent() && !maxDateTime.isPresent()) {
                                                    // Only morning punch
                                                    LocalDateTime simulatedOut = minDateTime.get().toLocalDate().atTime(shiftStartTime.plusMinutes((long) halfdayminute));
                                                    formattedMaxTime = simulatedOut.format(DateTimeFormatter.ofPattern("HH:mm"));
                                                    maxDateTime = Optional.of(simulatedOut);
                                                    employeeMap.put("present_extra_remark", "MP(Out)HF");
                                                    employeeMap.put("remark", "MP(Out)HF");
                                                } else if (!minDateTime.isPresent() && maxDateTime.isPresent()) {
                                                    // Only night punch
                                                    LocalDateTime simulatedIn = maxDateTime.get().toLocalDate().atTime(shiftEndTime.minusMinutes((long) halfdayminute));
                                                    formattedMinTime = simulatedIn.format(DateTimeFormatter.ofPattern("HH:mm"));
                                                    minDateTime = Optional.of(simulatedIn);
                                                    employeeMap.put("present_extra_remark", "MP(In)HF");
                                                    employeeMap.put("remark", "MP(In)HF");
                                                }

                                                employeeMap.put("min_att_time", formattedMinTime);
                                                employeeMap.put("max_att_time", formattedMaxTime);
//                                        employeeMap.put("present_extra_remark", "MPHF");
                                            }
                                        }
                                    }
                                    if (minDateTime.isPresent() && maxDateTime.isPresent()) {
                                        LocalDateTime minDateTimeValue = minDateTime.get();
                                        LocalDateTime maxDateTimeValue = maxDateTime.get();

                                        // Adjust maxDateTimeValue to the next day if it is earlier than minDateTimeValue
                                        if (maxDateTimeValue.isBefore(minDateTimeValue)) {
                                            maxDateTimeValue = maxDateTimeValue.plusDays(1);
                                        }

                                        Duration workedDuration = Duration.between(minDateTimeValue, maxDateTimeValue);
                                        long hoursWorked = workedDuration.toHours();
                                        long minutesWorked = workedDuration.toMinutes() % 60;

//									// Calculate the maximum allowed worked hours based on the shift times
//									Duration shiftFixHrsMin = FnCalculateHoursDiff(startEndTimes[0].trim(), startEndTimes[1].trim());
//									// Convert both durations to total minutes for comparison
//									long totalWorkedMinutes = workedDuration.toMinutes();
//									long totalMaxMinutes = shiftFixHrsMin.toMinutes();
//									// Adjust worked hours and minutes to the maximum allowed
//									if (totalWorkedMinutes > totalMaxMinutes) {
//										hoursWorked = shiftFixHrsMin.toHours();
//										minutesWorked = shiftFixHrsMin.toMinutes() % 60;
//									}

                                        employeeMap.put("worked_hours", String.format("%02d:%02d", hoursWorked, minutesWorked));

                                        Optional<CHtAttendanceDailyModel> currentAttendanceFlag = getDailyAttendanceDetails.stream()
                                                .filter(log -> log.getAtt_date_time().equals(attendanceDate) &&
                                                        log.getEmployee_code().equals(employeeMap.get("employee_code")))
                                                .findFirst(); // Get the first match

//                                Store the result in the map, checking if it's present
                                        currentAttendanceFlag.ifPresent(flag -> employeeMap.put("attendance_flag", flag.getAttendance_flag()));

////							        CALCULATE WORKED HOURS
//									Duration workedDuration = Duration.between(minDateTimeValue, maxDateTimeValue);
//									long workedMinutes = workedDuration.toMinutes();
//
//									// Calculate full day hours in minutes
//									long fullDayMinutes = (long) (fullday_hours * 60);
//
//									// Compare worked duration with full day hours
//									if (workedMinutes > fullDayMinutes) {
//										workedMinutes = fullDayMinutes;
//									}
//
//									// Calculate hours and minutes from workedMinutes
//									long hoursWorked = workedMinutes / 60;
//									long minutesWorked = workedMinutes % 60;
//
//									// Format the worked hours
//									String workedHours = String.format("%02d:%02d", hoursWorked, minutesWorked);
//
//									// Store the result in the map
//									employeeMap.put("worked_hours", workedHours);

////									IF ATTENDACE EXCLUDE FOR ONE OF THE EMPLOYEE
//									if (employeeItem.isAttendance_exclude_flag()) {
//										FnCheckEmployeeAttendanceExcludeStatus(weeklyOffName, dayName, attendanceStatusDetails, employeeMap);
//									} else {
////							        	DETERMINE EMPLOYEE PRESENT STATUS
//										determineEmployeePresenceStatus(grace_early_time, grace_late_time, fullday_hours, halfday_hours,
//												employeeItem.getEmployee_code(), workedDuration, weeklyOffName, dayName, attendanceDate, leaveApplicationDetails, attendanceStatusDetails, isHoliday, two_day_shift, employeeMap);
////							        	EXTRA REMARK REGARDING EMPLOYEE PRESENTY
//										determineEmployeeExtraRemark(grace_early_time, grace_late_time, employeeItem.getEmployee_code(), minDateTimeValue,
//												maxDateTimeValue, shiftStartTime, shiftEndTime, shortLeaveApplicationDetails, employeeMap);
//									}

//							            DETERMINE EMPLOYEE PRESENT STATUS
                                        determineEmployeePresenceStatus(grace_early_time, grace_late_time, fullday_hours, halfday_hours,
                                                employeeItem.getEmployee_code(), workedDuration, weeklyOffName, dayName, attendanceDate, leaveApplicationDetails,
                                                attendanceStatusDetails, isHoliday, two_day_shift, employeeMap, employeeItem.isAttendance_exclude_flag(), shortLeaveApplicationDetails, hrPayrollSettings);

//							            EXTRA REMARK REGARDING EMPLOYEE PRESENTY
                                        determineEmployeeExtraRemark(grace_early_time, grace_late_time, employeeItem.getEmployee_code(), minDateTimeValue,
                                                maxDateTimeValue, shiftStartTime, shiftEndTime, shortLeaveApplicationDetails, employeeMap, attendanceDate);

                                    }

//						        IF EMPLOYEE IN & OUT TIME NOT FOUND THEN CHECK HE IS ON WEEKLY OFF OR ABSENT OR ON LEAVE
                                    else {
                                        String attendanceStatus = "";
                                        CHmAttendanceStatusModel attendanceStatusModel = null;

//                                  IF ATTENDACE EXCLUDE IS TRUE THEN HE IS ALWAYS PRESENT or HE HAS WEEKLY OFF
                                        if (employeeItem.isAttendance_exclude_flag()) {
                                            if (weeklyOffName != null && weeklyOffName.equalsIgnoreCase(dayName)
//                                                    && isHoliday == 0
                                            ) {
                                                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, WEEKLY_OFF);
                                            } else if (isHoliday == 1) {
                                                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, HOLIDAY);
                                            } else if (hasEmployeeTakenLeave(leaveApplicationDetails, employeeItem.getEmployee_code(), attendanceDate)) {
                                                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, LEAVES);
                                            } else {
                                                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, PRESENT);
                                            }
                                        } else {
                                            if (weeklyOffName != null && weeklyOffName.equalsIgnoreCase(dayName)
//                                                    && isHoliday == 0
                                            ) {
                                                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, WEEKLY_OFF);
                                            } else if (isHoliday == 1) {
                                                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, HOLIDAY);
                                            } else if (hasEmployeeTakenLeave(leaveApplicationDetails, employeeItem.getEmployee_code(), attendanceDate)) {
                                                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, LEAVES);

                                                LocalDate attendanceDates = LocalDate.parse(attendanceDate, formatter); // Use the actual string for attendanceDate
                                                leaveApplicationDetails.stream()
                                                        .filter(leaveDetails -> {
                                                            String employeeCode = (String) leaveDetails.get("employee_code");
                                                            java.sql.Date leaveFromDateStr = (java.sql.Date) leaveDetails.get("leaves_approved_from_date");
                                                            java.sql.Date leaveToDateStr = (java.sql.Date) leaveDetails.get("leaves_approved_to_date");
                                                            // Converting java.sql.Date to LocalDate
                                                            LocalDate leaveFromDate = leaveFromDateStr.toLocalDate();
                                                            LocalDate leaveToDate = leaveToDateStr.toLocalDate();
                                                            return employeeItem.getEmployee_code().equals(employeeCode)
                                                                    && !attendanceDates.isBefore(leaveFromDate)
                                                                    && !attendanceDates.isAfter(leaveToDate);
                                                        })
                                                        .forEachOrdered(leaveDetails -> {
                                                            BigInteger leaveTypeIdBigInt = (BigInteger) leaveDetails.get("leave_type_id");
                                                            int leaveTypeId = leaveTypeIdBigInt.intValue();

                                                            if (leaveTypeId == 11) {
//                                                                String currentRemark = (String) leaveDetails.get("present_extra_remark");
//                                                                if (currentRemark != null && !currentRemark.isEmpty()) {
//                                                                    employeeMap.put("present_extra_remark", currentRemark + ",Out Door Duty");
//                                                                } else {
                                                                String shiftStartEndTimes = (String) employeeMap.get("shift_start_end_time");
                                                                if (shiftStartEndTimes != null && !shiftStartEndTimes.isEmpty()) {
                                                                    String[] times = shiftStartEndTimes.split("-");
                                                                    if (times.length == 2) {
//
                                                                        String minAttTime = times[0].substring(0, 5); // Extract "20:00"
                                                                        String maxAttTime = times[1].substring(0, 5); // Extract "08:00"

                                                                        employeeMap.put("min_att_time", minAttTime);
                                                                        employeeMap.put("max_att_time", maxAttTime);

                                                                        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(minAttTime));
                                                                        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.parse(maxAttTime));

                                                                        if (endTime.isBefore(startTime)) {
                                                                            endTime = endTime.plusDays(1);
                                                                        }

                                                                        Duration duration = Duration.between(startTime, endTime);
                                                                        long hours = duration.toHours();
                                                                        long minutes = duration.toMinutesPart();

                                                                        String workedHours = String.format("%02d:%02d", hours, minutes);
                                                                        employeeMap.put("worked_hours", workedHours);

                                                                    }
                                                                }

                                                                employeeMap.put("attendance_status", "PR");
                                                                employeeMap.put("job_type_id", 1);
                                                                employeeMap.put("job_type", "PR");
                                                                employeeMap.put("present_extra_remark", "Out Door Duty");
//                                                                }
                                                            }
                                                        });

                                            } else {
                                                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, ABSENT);
                                            }
                                        }

//                                  // If Employee On Leave then set the it's LeaveType as a attendace_Status other wise send as it is job_type_shortName;
//									if (attendanceStatusModel.getAttendance_status_id() == LEAVES) {
//										attendanceStatus = getLeaveTypeCode(leaveApplicationDetails, employeeItem.getEmployee_code());
//									} else {
//										attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
//									}
                                        if (!"Out Door Duty".equals(employeeMap.get("present_extra_remark"))) {
                                            attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();

                                            employeeMap.put("attendance_status", attendanceStatus);
                                            employeeMap.put("job_type_id", attendanceStatusModel.getAttendance_status_id());
                                            employeeMap.put("job_type", attendanceStatusModel.getAttendance_status_short_name());
                                            employeeMap.put("present_extra_remark", null);
                                            employeeMap.put("worked_hours", null);
                                        }

                                        if (employeeItem.isAttendance_exclude_flag() && (!minDateTime.isPresent() || !maxDateTime.isPresent())) {
                                            employeeMap.put("attendance_flag", 'M');
                                        }
//									employeeMap.put("min_att_time", null);
//									employeeMap.put("max_att_time", null);
                                    }

                                }
                            }

                            // Collect all records for the target employee ID
                            CHtAttendanceDailyModel previousDayDailyAtt = null;
                            CHtAttendanceDailyModel currentDayDailyAtt = null;

                            List<CHtAttendanceDailyModel> currentNdPreviousDayDailyAttRec = currentNdPreviousDayDailyAttAllEmpl.parallelStream()
                                    .filter(att -> att.getEmployee_id().equals(employeeItem.getEmployee_id()))
                                    .collect(Collectors.toList());

                            if (!currentNdPreviousDayDailyAttRec.isEmpty()) {
                                previousDayDailyAtt = currentNdPreviousDayDailyAttRec.parallelStream()
                                        .filter(att -> att.getAtt_date_time().equals(String.valueOf(previousDate)))
                                        .findFirst()
                                        .orElse(null);

                                currentDayDailyAtt = currentNdPreviousDayDailyAttRec.parallelStream()
                                        .filter(att -> att.getAtt_date_time().equals(String.valueOf(date)))
                                        .findFirst()
                                        .orElse(null);
                            }
                            employeeMap.put("job_type_code_id", FnSetEmployeeJobCode(employeeItem, jobTypeDetails, currentDayDailyAtt, previousDayDailyAtt, employeeMap));

                            employeeShiftWiseDetails.add(employeeMap);
                            employeeDateAndShiftWiseDetails.add(employeeMap);
                        });

            }

            response.put("content", employeeDateAndShiftWiseDetails);
            response.put("columns", prepareHeadersAndAccessors());
            response.put("success", 1);

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();

            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            return response;
        }
        return response;

    }

    private Integer FnSetEmployeeJobCode(CEmployeesViewModel employee, List<CJobTypeModel> jobTypeDetails, CHtAttendanceDailyModel currentDayDailyAtt, CHtAttendanceDailyModel previousDayDailyAtt, Map<String, Object> employeeMap) {
        String JobTypeIsEditable = "N";
        Integer jobTypeId = (Integer) employeeMap.getOrDefault("job_type_id", 0);
        String jobTypeCodeShortName = (String) employeeMap.getOrDefault("job_type", "");

//      Also set if attendace is already_added for that day; then also store it's details; means job_type_code and hours;
        if (currentDayDailyAtt != null) {
            employeeMap.put("already_added_present_shift", currentDayDailyAtt.getShift_present());
            employeeMap.put("already_added_job_type_code_short_name", currentDayDailyAtt.getJob_type_code_short_name());
            employeeMap.put("already_added_job_type_code_id", currentDayDailyAtt.getJob_type_code_id());
//            employeeMap.put("already_added_job_type_code_short_name", employee.getJob_type_id());
//            employeeMap.put("already_added_job_type_code_id", employee.getJob_type_short_name());
            employeeMap.put("already_added_worked_hours", currentDayDailyAtt.getWorking_minutes());
            employeeMap.put("already_added_attendance", true);
        } else {
            employeeMap.put("already_added_job_type_code_short_name", null);
            employeeMap.put("already_added_job_type_code_id", null);
            employeeMap.put("already_added_worked_hours", 0);
            employeeMap.put("already_added_attendance", false);
        }

        if ("Workers".equals(employee.getEmployee_type_group())) {
            if (Arrays.asList(PRESENT, HALF_DAY, HOLIDAY_PRESENT).contains(jobTypeId) || (employeeMap.containsKey("week_off_present_flag") && "Y".equals(employeeMap.getOrDefault("week_off_present_flag", "")))) {
                employeeMap.put("job_code_iseditable", "Y");
            } else {
                employeeMap.put("job_code_iseditable", 'N');
            }

            // IF ATTENDANCE DATE DATA IS ALREADY AVAILABLE IN ht_attendance_daily THEN SET JOB TYPE to same
            if (currentDayDailyAtt != null) {
                jobTypeId = currentDayDailyAtt.getJob_type_code_id();
                jobTypeCodeShortName = currentDayDailyAtt.getJob_type_code_short_name();
            }// First Found JobTypeId & JobTypeShortName LastDay Work;
            else if (previousDayDailyAtt != null) {
                CJobTypeModel previousDayJobType = getJobTypeDetailById(jobTypeDetails, previousDayDailyAtt.getJob_type_code_id());
                jobTypeId = previousDayJobType.getJob_type_id();
                jobTypeCodeShortName = previousDayJobType.getJob_type_short_name();
            } // GET JOBTYPE FROM EMPLOYEE MASTER
            else {
                jobTypeId = employee.getJob_type_id();
                jobTypeCodeShortName = employee.getJob_type_short_name();
            }

            employeeMap.put("job_type_code_short_name", jobTypeCodeShortName);
            return jobTypeId;

        } else if ("Staffs".equals(employee.getEmployee_type_group())) {
            employeeMap.put("job_code_iseditable", 'N');
            employeeMap.put("job_type_code_short_name", jobTypeCodeShortName);
            return jobTypeId;
        }

        employeeMap.put("job_code_iseditable", JobTypeIsEditable);
        return jobTypeId;


//      System.out.println("After CODE: " + employee.getEmployee_code() + "\tJOBTypeId: " + employeeMap.getOrDefault("job_type_id", "") + "\tJOBTypeName: " + employeeMap.getOrDefault("job_type", "") );

//		// If it is on leave, absent, weekly_off then set as it is jobTypeCode;
//		if (Arrays.asList(ABSENT, LEAVES).contains(jobTypeId)) {
//			employeeMap.put("job_code_iseditable", "N");
//			employeeMap.put("job_type_code_short_name", empJobTypeShortName);
//			return empJobTypeId;
//		}
//
//		if ("Workers".equals(employee.getEmployee_type_group())) {
//			if (Arrays.asList(PRESENT, HALF_DAY, HOLIDAY_PRESENT).contains(jobTypeId)) {
//				employeeMap.put("job_code_iseditable", "Y");
//				// First Found JobTypeId & JobTypeShortName LastDay Work;
//				int previousDayJobTypeCodeId = 0;
//				if (previousDayDailyAtt != null) {
//					CJobTypeModel previousDayJobType = getJobTypeDetailById(jobTypeDetails, previousDayDailyAtt.getJob_type_code_id());
//					jobTypeId = previousDayJobType.getJob_type_id();
//					jobTypeCodeShortName = previousDayJobType.getJob_type_short_name();
//
//					previousDayJobTypeCodeId = previousDayJobType.getJob_type_id();
//				}
//
//				// If not found then set it from employee_master; means previous day it was not present
//				if (Arrays.asList(ABSENT, LEAVES, WEEKLY_OFF, HOLIDAY, 0).contains(previousDayJobTypeCodeId)) {
//					jobTypeId = employee.getJob_type_id();
//					jobTypeCodeShortName = employee.getJob_type_short_name();
//				}
//				employeeMap.put("job_type_code_short_name", jobTypeCodeShortName);
//				return jobTypeId;
//
//			} else if (employeeMap.containsKey("week_off_present_flag") && "Y".equals(employeeMap.getOrDefault("week_off_present_flag", ""))) {
//				employeeMap.put("job_code_iseditable", "Y");
//				// First Found JobTypeId & JobTypeShortName LastDay Work;
//				int previousDayJobTypeCodeId = 0;
//				if (previousDayDailyAtt != null) {
//					CJobTypeModel previousDayJobType = getJobTypeDetailById(jobTypeDetails, previousDayDailyAtt.getJob_type_code_id());
//					jobTypeId = previousDayJobType.getJob_type_id();
//					jobTypeCodeShortName = previousDayJobType.getJob_type_short_name();
//
//					previousDayJobTypeCodeId = previousDayJobType.getJob_type_id();
//				}
//
//				// If not found then set it from employee_master; means previous day it was not present
//				if (Arrays.asList(ABSENT, LEAVES, WEEKLY_OFF, HOLIDAY, 0).contains(previousDayJobTypeCodeId)) {
//					jobTypeId = employee.getJob_type_id();
//					jobTypeCodeShortName = employee.getJob_type_short_name();
//				}
//				employeeMap.put("job_type_code_short_name", jobTypeCodeShortName);
//				return jobTypeId;
//
//			} else {
//				employeeMap.put("job_code_iseditable", 'N');
//				employeeMap.put("job_type_code_short_name", empJobTypeShortName);
//				return empJobTypeId;
//
//			}
//		} else if ("Staffs".equals(employee.getEmployee_type_group())) {
//			employeeMap.put("job_code_iseditable", 'N');
//			employeeMap.put("job_type_code_short_name", jobTypeCodeShortName);
//			return jobTypeId;
//
//		}
//		employeeMap.put("job_code_iseditable", JobTypeIsEditable);
//		return empJobTypeId;
    }

    //	CHECK EMPLOYEE HAS ATTENDANCE EXCLUDE THE HE HAS TOTALLY PRESENT ALWAYS
    private void FnCheckEmployeeAttendanceExcludeStatus(String weeklyOffName, String dayName,
                                                        List<CHmAttendanceStatusModel> attendanceStatusDetails, Map<String, Object> employeeMap) {
        CHmAttendanceStatusModel attendanceStatusModel = null;
        String attendanceStatus = null;

        if (weeklyOffName != null && weeklyOffName.equalsIgnoreCase(dayName)) {
            attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, WEEKLY_OFF);
            employeeMap.put("week_off_present_flag", "Y");
        } else {
            attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, PRESENT);
        }
        attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
        employeeMap.put("attendance_status", attendanceStatus);
        employeeMap.put("job_type_id", attendanceStatusModel.getAttendance_status_id());
        employeeMap.put("job_type", attendanceStatusModel.getAttendance_status_short_name());
        employeeMap.put("present_flag", "Y");
    }

    private void determineEmployeeExtraRemark(Double grace_early_time, Double grace_late_time, String employee_code, LocalDateTime minDateTimeValue,
                                              LocalDateTime maxDateTimeValue, LocalTime shiftStartTime, LocalTime shiftEndTime,
                                              List<CHtShortLeaveModel> shortLeaveApplicationDetails, Map<String, Object> employeeMap, String attendanceDate) {

        boolean shortLeaveStatus = hasEmployeeTakenShortLeave(shortLeaveApplicationDetails, employee_code, attendanceDate);
        boolean halfDayLeaveStatus = hasEmployeeTakenHalfDayLeave(shortLeaveApplicationDetails, employee_code, attendanceDate);
        boolean halfGatePassStatus = hasEmployeeTakenGatePass(shortLeaveApplicationDetails, employee_code, attendanceDate);
        // PARSE GRACE TIMES AS LONG TO WORK WITH LOCALTIME
        long graceLateMinutes = grace_late_time != null ? grace_late_time.longValue() : 0;
        long graceEarlyMinutes = grace_early_time != null ? grace_early_time.longValue() : 0;

        // ADJUST SHIFT TIMES BY GRACE PERIODS
        LocalTime adjustedShiftStartTime = shiftStartTime.plusMinutes(graceLateMinutes);
        LocalTime adjustedShiftEndTime = shiftEndTime.minusMinutes(graceEarlyMinutes);

        CJobTypeModel jobTypeModel;

        // CHECK FOR "LATE MARK"
        if (minDateTimeValue.toLocalTime().isAfter(adjustedShiftStartTime)) {
//            if (!halfDayLeaveStatus) {
//                employeeMap.put("present_extra_remark", "L");
//                employeeMap.put("late_mark_flag", "Y");
//            }
            String currentRemarks = (String) employeeMap.get("present_extra_remark");
            if (!halfDayLeaveStatus) {
                if (currentRemarks != null && !currentRemarks.isEmpty()) {
                    employeeMap.put("present_extra_remark", currentRemarks + ", L");
                    employeeMap.put("late_mark_flag", "Y");
                } else {
                    employeeMap.put("present_extra_remark", "L");
                }
                employeeMap.put("late_mark_flag", "Y");
            }
        }

        // CHECK FOR "EARLY GO"
        if (maxDateTimeValue.toLocalTime().isBefore(adjustedShiftEndTime)) {
            String currentRemark = (String) employeeMap.get("present_extra_remark");
            if (!halfDayLeaveStatus) {
                if (currentRemark != null && !currentRemark.isEmpty()) {
                    employeeMap.put("present_extra_remark", currentRemark + ", E");
                } else {
                    employeeMap.put("present_extra_remark", "E");
                }
                employeeMap.put("early_go_flag", "Y");
            }
        }

        // IF THE EMPLOYEE HAS TAKEN SHORT LEAVE, ADD THIS INFORMATION AS WELL
//            if (shortLeaveStatus) {
//            if (halfDayLeaveStatus) {
//                String currentRemark = (String) employeeMap.get("present_extra_remark");
//                if (currentRemark != null && !currentRemark.isEmpty()) {
//                    employeeMap.put("present_extra_remark", currentRemark + ", Half Day Leave");
//                } else {
//                    employeeMap.put("present_extra_remark", "HDL");
//                }
//                employeeMap.put("gate_pass_flag", "Y");
//                employeeMap.put("remark", "HDL");
//            } else {
//                String currentRemark = (String) employeeMap.get("present_extra_remark");
//                if (currentRemark != null && !currentRemark.isEmpty()) {
//                    employeeMap.put("present_extra_remark", currentRemark + ", Short Leave");
//                } else {
//                    employeeMap.put("present_extra_remark", "SL");
//                }
//                employeeMap.put("gate_pass_flag", "Y");
//                employeeMap.put("remark", "SL");
//            }
//        }
        if (halfDayLeaveStatus) {
            String currentRemark = (String) employeeMap.get("present_extra_remark");
            if (currentRemark != null && !currentRemark.isEmpty()) {
                employeeMap.put("present_extra_remark", currentRemark + ", HDL");
            } else {
                employeeMap.put("present_extra_remark", "HDL");
            }
            employeeMap.put("gate_pass_flag", "Y");
            employeeMap.put("remark", "HDL");

        } else if (shortLeaveStatus && !halfDayLeaveStatus && !halfGatePassStatus) {
            String currentRemark = (String) employeeMap.get("present_extra_remark");
            if (currentRemark != null && !currentRemark.isEmpty()) {
                employeeMap.put("present_extra_remark", currentRemark + ", SL");
            } else {
                employeeMap.put("present_extra_remark", "SL");
            }
            employeeMap.put("gate_pass_flag", "Y");
            employeeMap.put("remark", "SL");

        } else if (halfGatePassStatus && !halfDayLeaveStatus) {
            String currentRemark = (String) employeeMap.get("present_extra_remark");
            if (currentRemark != null && !currentRemark.isEmpty()) {
                employeeMap.put("present_extra_remark", currentRemark + ", G");
            } else {
                employeeMap.put("present_extra_remark", "G");
            }
            employeeMap.put("gate_pass_flag", "Y");
            employeeMap.put("remark", "G");
        }


    }

    private void determineEmployeePresenceStatus(Double grace_early_time, Double grace_late_time, Double fullday_hours, Double halfday_hours,
                                                 String employee_code,
                                                 Duration workedDuration, String weeklyOffName,
                                                 String dayName, String attendanceDate, List<Map<String, Object>> leaveApplicationDetails, List<CHmAttendanceStatusModel> attendanceStatusDetails,
                                                 int isHoliday, AtomicBoolean two_day_shift, Map<String, Object> employeeMap, boolean attendance_exclude_flag, List<CHtShortLeaveModel> shortLeaveApplicationDetails, CHmHrpayrollSettingsModel hrPayrollSettings) {
        long totalMinutesWorked = workedDuration.toMinutes();
        double fullDayMinutes = fullday_hours * 60;
        double halfDayMinutes = halfday_hours * 60;
        CHmAttendanceStatusModel attendanceStatusModel = null;

        String attendanceStatus = null;

        double totalWorkedMinutes = totalMinutesWorked + grace_early_time;
        boolean halfDayLeaveStatus = hasEmployeeTakenHalfDayLeave(shortLeaveApplicationDetails, employee_code, attendanceDate);
        boolean halfGatePassStatus = hasEmployeeTakenGatePass(shortLeaveApplicationDetails, employee_code, attendanceDate);

        boolean hasEmployeeTakenShortLeave = hasEmployeeTakenShortLeave(shortLeaveApplicationDetails, employee_code, attendanceDate);

        if (hasEmployeeTakenShortLeave && !halfDayLeaveStatus) {
            double shortLeaveMinutes = hrPayrollSettings.getShort_leave_hours() * 60;
            totalWorkedMinutes += shortLeaveMinutes;
        }
        if (halfDayLeaveStatus) {
            double halfDayMinute = halfDayMinutes;
            totalWorkedMinutes += halfDayMinute;
        }

//		CHECK FULL DAY PRESENCE
        if (totalWorkedMinutes >= fullDayMinutes) {
            if (weeklyOffName != null && weeklyOffName.equalsIgnoreCase(dayName)
//                    && isHoliday == 0
            ) {
//				// Commented because Prashant sir told if weekly of then show everywhere weekly off; & and increment only the count in c_off & weekly_off;
//				jobTypeModel = getAttendanceDetailById(attendanceStatusDetails, C_OFF);

                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, WEEKLY_OFF);
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
                employeeMap.put("week_off_present_flag", "Y");
            } else if (isHoliday == 1) {
                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, HOLIDAY_PRESENT);
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
            } else {
                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, PRESENT);
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();

                if (halfDayLeaveStatus) {
                    employeeMap.put("remark", "HDL");
                } else if (hasEmployeeTakenShortLeave && !halfDayLeaveStatus && !halfGatePassStatus) {
                    employeeMap.put("remark", "SL");
                } else if (halfGatePassStatus && !halfDayLeaveStatus) {
                    employeeMap.put("remark", "G");
                }
            }
            employeeMap.put("present_flag", "Y");
        }

//		CHECK HALF DAY PRESENCE
        else if (totalMinutesWorked >= halfDayMinutes && !attendance_exclude_flag) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate attendanceLocalDate = LocalDate.parse(attendanceDate, dateFormatter);

            Optional<Map<String, Object>> halfDayLeaveOpt = leaveApplicationDetails.stream()
                    .filter(leaveDetails -> leaveDetails.containsKey("employee_code")
                            && leaveDetails.get("employee_code").equals(employee_code))
                    .filter(leaveDetails -> {
                        java.sql.Date fromDateSql = (java.sql.Date) leaveDetails.get("leaves_approved_from_date");
                        java.sql.Date toDateSql = (java.sql.Date) leaveDetails.get("leaves_approved_to_date");

                        if (fromDateSql != null && toDateSql != null) {
                            // Convert java.sql.Date to LocalDate
                            LocalDate fromDate = fromDateSql.toLocalDate();
                            LocalDate toDate = toDateSql.toLocalDate();

                            // Check if attendanceDate falls between the approved leave dates (inclusive)
                            return !attendanceLocalDate.isBefore(fromDate) && !attendanceLocalDate.isAfter(toDate);
                        }
                        return false;
                    })
                    .findFirst();


//			Optional<Map<String, Object>> halfDayLeaveOpt = leaveApplicationDetails.stream()
//					.filter(leaveDetails -> leaveDetails.containsKey("employee_code") && leaveDetails.get("employee_code").equals(employee_code))
//					.findFirst();

            if (halfDayLeaveOpt.isPresent()) {
                Map<String, Object> leaveDetails = halfDayLeaveOpt.get();
                boolean isHalfDay = isHalfDayOnDate(
                        leaveDetails.get("leaves_approved_from_date").toString(),
                        leaveDetails.get("leaves_approved_to_date").toString(),
                        ((Number) leaveDetails.get("leaves_sanction_days")).doubleValue(),
                        attendanceDate);

                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, HALF_DAY);
                String halfDayStatus = attendanceStatusModel.getAttendance_status_short_name();
                if (isHalfDay) {
                    String leaveStatus = getAttendanceDetailById(attendanceStatusDetails, LEAVES).getAttendance_status_short_name();
                    // If Employee On Leave then set the it's LeaveType as a attendace_Status other wise send as it is job_type_shortName;
                    if (attendanceStatusModel.getAttendance_status_id() == LEAVES) {
                        attendanceStatus = halfDayStatus + "/" + getLeaveTypeCode(leaveApplicationDetails, employee_code, attendanceDate);
                    } else {
                        attendanceStatus = halfDayStatus + "/" + leaveStatus;
                    }
                } else {
                    attendanceStatus = halfDayStatus;
                }
            } else {
                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, HALF_DAY);
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
            }

            if (weeklyOffName != null && weeklyOffName.equalsIgnoreCase(dayName) && isHoliday == 0) {
//				// Commented because Prashant sir told if weekly of then show everywhere weekly off; & and increment only the count in c_off & weekly_off;
                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, WEEKLY_OFF);
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
                employeeMap.put("week_off_present_flag", "Y");
            } else if (isHoliday == 1) {
                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, HOLIDAY_PRESENT);
//                attendanceStatus = getAttendanceDetailById(attendanceStatusDetails, C_OFF).getJob_type_short_name();
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
            }

        }

//      CHECK ABSENT STATUS
        else {
//			CHECK WEKKLY OFF
            if (weeklyOffName != null && weeklyOffName.equalsIgnoreCase(dayName)) {
                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, WEEKLY_OFF);
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
            }
//			CHECK HOLIDAY
            else if (isHoliday == 1) {
                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, HOLIDAY);
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
            }
//          Don't Apply the LateMark & EarlyGo For autoAttendace;
            else if (attendance_exclude_flag) {
                attendanceStatusModel = getAttendanceDetailById(attendanceStatusDetails, PRESENT);
                attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
            }
//			OTHERWISE HE IS ABSENT
            else {
                attendanceStatusModel = hasEmployeeTakenLeave(leaveApplicationDetails, employee_code, attendanceDate) ?
                        getAttendanceDetailById(attendanceStatusDetails, LEAVES) : getAttendanceDetailById(attendanceStatusDetails, ABSENT);
                // If Employee On Leave then set the it's LeaveType as a attendace_Status other wise send as it is job_type_shortName;
                if (attendanceStatusModel.getAttendance_status_id() == LEAVES) {
                    attendanceStatus = getLeaveTypeCode(leaveApplicationDetails, employee_code, attendanceDate);
                } else {
                    attendanceStatus = attendanceStatusModel.getAttendance_status_short_name();
                }
            }
        }

        if ((attendanceStatusModel.getAttendance_status_id() == PRESENT || attendanceStatusModel.getAttendance_status_id() == HALF_DAY
                || attendanceStatusModel.getAttendance_status_id() == C_OFF || attendanceStatusModel.getAttendance_status_id() == HOLIDAY_PRESENT) && two_day_shift.get()) {

            employeeMap.put("night_shift_present_flag", "Y");
        }

        employeeMap.put("attendance_status", attendanceStatus);
        employeeMap.put("job_type_id", attendanceStatusModel.getAttendance_status_id());
        employeeMap.put("job_type", attendanceStatusModel.getAttendance_status_short_name());
    }

    private Map<String, Object> convertEmployeeItemToMap(CEmployeesViewModel employeeItem) {
        // Create a Map representing employeeItem
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("employee_id", employeeItem.getEmployee_id());
        map.put("employee_code", employeeItem.getEmployee_code());
        map.put("old_employee_code", employeeItem.getOld_employee_code());
        map.put("employee_type", employeeItem.getEmployee_type_group());
        map.put("employee_name", employeeItem.getEmployee_name());
        map.put("department_name", employeeItem.getDepartment_name());
        map.put("sub_department_name", employeeItem.getSub_department_name());
        map.put("designation_name", employeeItem.getDesignation_name());
        map.put("shift_name", employeeItem.getShift_name());
        map.put("weeklyoff_name", employeeItem.getWeeklyoff_name());
        map.put("department_group", employeeItem.getDepartment_group());
        map.put("machine_employee_code", employeeItem.getMachine_employee_code());
        map.put("shift_start_end_time", employeeItem.getShift_start_end_time());
        map.put("halfday_hours", employeeItem.getHalfday_hours());
        map.put("fullday_hours", employeeItem.getFullday_hours());
        map.put("grace_early_time", employeeItem.getGrace_early_time());
        map.put("grace_late_time", employeeItem.getGrace_late_time());
        map.put("two_day_shift", employeeItem.isTwo_day_shift());
        map.put("shift_scheduled", employeeItem.getShift_name());
        map.put("shift_present", employeeItem.getShift_name());
        map.put("attendance_exclude_flag", employeeItem.isAttendance_exclude_flag());
        // Add more fields as needed

        return map;
    }

    private List<Map<String, Object>> prepareHeadersAndAccessors() {
        List<Map<String, Object>> headersAndAccessors = new ArrayList<>();

        String[] headers = {
                "Employee Code", "Punching Code",
                "Employee Name", "Department Name", "Shift Name", "Attendance Date",
                "In Time", "Out Time", "Working Hrs.", "Attendance Status",
                "Extra Remark",
                "Machine Employee Code"
                // Add more headers as needed
        };

        String[] accessors = {
                "employee_code", "old_employee_code",
                "employee_name", "department_name", "shift_name", "att_date_time",
                "min_att_time",
                "max_att_time", "worked_hours", "attendance_status", "present_extra_remark", "machine_employee_code",
                // Make sure this corresponds to the headers array
        };

        for (int i = 0; i < headers.length; i++) {
            Map<String, Object> headerAccessor = new LinkedHashMap<>();
            headerAccessor.put("Headers", headers[i]);
            headerAccessor.put("accessor", accessors[i]);
            headersAndAccessors.add(headerAccessor);
        }

        return headersAndAccessors;
    }


    @PostMapping("/FnAddUpdateAttendanceDetails")
    @Transactional
    public Map<String, Object> FnAddUpdateAttendanceDetails(@RequestParam("getEmployeeAttendanceDetails") JSONObject getEmployeeAttendanceDetails) {
        Map<String, Object> response = new LinkedHashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject punchingMasterData = getEmployeeAttendanceDetails.getJSONObject("punchingMasterData");

        JSONArray punchingDetailsData = (JSONArray) getEmployeeAttendanceDetails.get("punchingDetailsData");

        try {
            List<CHtAttendanceMonthlyModel> htAttendanceMonthlyModels = new ArrayList<>();
            List<CHtAttendanceMonthlyJobTypeModel> htAttendanceMonthlyModelsJobTypeWise = new ArrayList<>();

            List<CHmAttLog2Model> attLogDetails = new ArrayList<>();

//			CONVERT PUNCHING MASTER FROM JSON TO MODEL
            CEmployeeWiseAttendanceRequest attendanceRequests = objectMapper.readValue(punchingMasterData.toString(), CEmployeeWiseAttendanceRequest.class);

//          GET LATE MARK RULES
            List<CHtvSalaryRulesViewModel> lateComingSalaryRules = iHtvSalaryRulesViewModelRepository.getLateMarkRules("LATE COMING", attendanceRequests.getCompany_id());

//			DATE FORMAT
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//          Define the desired date format
            DateTimeFormatter formatterInHhMmSs = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//			PARSE THE DATE STRING TO A LOCALDATE OBJECT
//			LocalDate date = LocalDate.parse(attendanceRequests.getAttendance_date(), formatter);

//          EXTRACT DATE RANGE FROM THE REQUEST
            LocalDate fromDate = LocalDate.parse(attendanceRequests.getAttendance_date(), formatter);

            String toDateStr = attendanceRequests.getDt_att_To_date();
            LocalDate toDate = toDateStr != null && !toDateStr.isEmpty() ? LocalDate.parse(toDateStr, formatter) : fromDate;

//          GET ALL DATES BETWEEN FROMDATE AND TODATE
            List<LocalDate> dateList = Stream.iterate(fromDate, date -> date.plusDays(1))
                    .limit(fromDate.until(toDate).getDays() + 1)
                    .collect(Collectors.toList());

//          Convert dateList to a comma-separated string for SQL IN clause
            String formattedDates = dateList.stream()
                    .map(date -> "'" + date + "'")  // Add single quotes around each date
                    .collect(Collectors.joining(", "));

//          PRINT THE RESULT
//			dateList.forEach(System.out::println);

//          Loop through the date range from 'fromDate' to 'toDate'
            LocalDate currentDate = fromDate;

// 			GET THE START AND END DATE OF THE MONTH
            YearMonth yearMonth = YearMonth.from(fromDate);
            LocalDate monthStartDate = yearMonth.atDay(1);
            LocalDate monthEndDate = yearMonth.atEndOfMonth();

            int month = fromDate.getMonthValue();
            int year = fromDate.getYear();

//			CHECK ITS HOLIDAY OR NOT
            List<CXmProductionHolidayModel> currentMonthHolidays = iXmProductionHolidayRepository.FnGetHolidaysBetweenTwoDates(monthStartDate.format(formatter), monthEndDate.format(formatter));
            List<String> currentMonthHolidayDates = currentMonthHolidays.stream().map(CXmProductionHolidayModel::getProduction_holiday_date).collect(Collectors.toList());

//			GET ALL JOB TYPES
            List<CJobTypeModel> jobTypeDetails = iJobTypeRepository.getJobTypeDetails(attendanceRequests.getCompany_id());

            List<CShiftModel> getShiftDetails = iShiftRepository.getAllShiftDetails();

//			GET ALL ATEENDANCE STATUS DETAILS
            List<CHmAttendanceStatusModel> attendanceStatusDetails = iHmAttendanceStatusRepository.getAttendanceStatusDetails();

//			QUERY TO FETCH SHORT LEAVES
            String shortLeavesQuery = String.format(
                    "SELECT * FROM ht_short_leave " +
                            "WHERE DATE(short_leave_date) IN (%s) " +
                            "AND approval_status = 'Approved' " +
                            "AND is_delete = 0", formattedDates);

            List<CHtShortLeaveModel> shortLeaveApplicationDetails = executeQuery.query(shortLeavesQuery, new BeanPropertyRowMapper<>(CHtShortLeaveModel.class));

//			CONVERT PUNCHING DETAILS DATA FROM JSON TO MODEL
            List<CHtAttendanceDailyModel> htAttendanceDailyModels = objectMapper.readValue(punchingDetailsData.toString(), new TypeReference<List<CHtAttendanceDailyModel>>() {
            });

            List<String> getEmployeeCodes = htAttendanceDailyModels.stream()
                    .map(CHtAttendanceDailyModel::getEmployee_code).distinct().collect(Collectors.toList());

//			GET THE LEAVE APPLICATIONS OF EMPLOYEES
            List<Map<String, Object>> leaveApplicationDetails = iHmLeavesApplicationsRepository.getLeavesApplicationDetails(fromDate, toDate, getEmployeeCodes);

//			UPDATE EXISTING
            iHtAttendanceDailyRepository.updateExistingDetailsOfSameDate(getEmployeeCodes, dateList, attendanceRequests.getEmployee_type_group(), attendanceRequests.getCompany_id());


//			GET ATTENDANCE MONTHLY DETAILS
            List<CHtAttendanceMonthlyModel> getAttendanceMonthlyDetails = iHtAttendanceMonthlyRepository
                    .getEmployeesMonthlyRecords(getEmployeeCodes, month, year);

//		    // ** Get the HsRPayroll Setting;
            CHmHrpayrollSettingsModel hrPayrollSettings = iHmHrpayrollSettingsRepository.FnGetPayrollSettingByCompanyId(attendanceRequests.getCompany_id());


//          ITERATE ON FROM DATE TO TO DATE
            while (!currentDate.isAfter(toDate)) {
//				Update the date format
                String attendanceDateStr = currentDate.format(formatter);

//				Parse the current date
                LocalDate date = LocalDate.parse(attendanceDateStr, formatter);

//				EXTRACT DAY, MONTH, AND YEAR FOR THE CURRENT DATE
                int day = date.getDayOfMonth();

//				GET THE DAY NAME
                String dayName = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

//			    GET THE NUMBER OF DAYS IN THE MONTH
                int monthDays = date.lengthOfMonth();

//              CHECK IF THE CURRENT DATE IS A HOLIDAY USING PARALLELSTREAM
                boolean isHolidayFound = currentMonthHolidays.parallelStream()
                        .anyMatch(holiday -> holiday.getProduction_holiday_date().equals(date.format(formatter)));

//              Set the isHoliday flag based on whether a matching holiday was found
                int isHoliday = isHolidayFound ? 1 : 0;

                htAttendanceDailyModels.stream().filter(item -> item.getAttendance_date().equals(day) && item.getAttendance_month().equals(month)
                                && item.getAttendance_year().equals(year))
                        .forEach(empAtt -> {
                            empAtt.setAtt_date_time(attendanceDateStr);

                            if ("M".equals(empAtt.getAttendance_flag())
                                    && empAtt.getIn_time() != null && !empAtt.getIn_time().isEmpty()
                                    && empAtt.getOut_time() != null && !empAtt.getOut_time().isEmpty()) {
                                // PARSE THE DATE AND TIME STRINGS INTO LOCALDATE AND LOCALTIME
//					            LocalDate attDate = LocalDate.parse(attendanceRequests.getAttendance_date());
                                LocalTime minTime = LocalTime.parse(empAtt.getIn_time());
                                LocalTime maxTime = LocalTime.parse(empAtt.getOut_time());

                                // CREATE LOCALDATETIME INSTANCES
                                LocalDateTime minDateTimeValue = LocalDateTime.of(date, minTime);
                                LocalDateTime maxDateTimeValue = LocalDateTime.of(date, maxTime);

                                // Check if the shift spans across two days
                                if (maxTime.isBefore(minTime) && empAtt.isTwo_day_shift()) {
                                    maxDateTimeValue = maxDateTimeValue.plusDays(1);
                                }

                                // CALCULATE THE DURATION WORKED
                                Duration workedDuration = Duration.between(minDateTimeValue, maxDateTimeValue);

                                Map<String, Object> employeeMap = new LinkedHashMap<>();

                                AtomicBoolean two_day_shift = new AtomicBoolean(empAtt.isTwo_day_shift());

                                determineEmployeePresenceStatus(empAtt.getGrace_early_time(), empAtt.getGrace_late_time(), empAtt.getFullday_hours(), empAtt.getHalfday_hours(),
                                        empAtt.getEmployee_code(), workedDuration, empAtt.getWeeklyoff_name(), dayName, attendanceDateStr, leaveApplicationDetails, attendanceStatusDetails,
                                        isHoliday, two_day_shift, employeeMap, empAtt.isAttendance_exclude_flag(), shortLeaveApplicationDetails, hrPayrollSettings);

//					            SET THE VALUES IN EMPATT FROM THE EMPLOYEEMAP
                                empAtt.setJob_type((String) employeeMap.get("job_type"));
                                empAtt.setJob_type_id(Integer.parseInt(employeeMap.get("job_type_id").toString()));
                                empAtt.setWeek_off_present_flag((String) employeeMap.getOrDefault("week_off_present_flag", "N"));
                                empAtt.setNight_shift_present_flag((String) employeeMap.getOrDefault("night_shift_present_flag", "N"));


                                String[] startEndTimes = empAtt.getShift_start_end_time().split("-");
                                LocalTime shiftStartTime = LocalTime.parse(startEndTimes[0].trim(), DateTimeFormatter.ofPattern("HH:mm:ss"));
                                LocalTime shiftEndTime = LocalTime.parse(startEndTimes[1].trim(), DateTimeFormatter.ofPattern("HH:mm:ss"));

                                determineEmployeeExtraRemark(empAtt.getGrace_early_time(), empAtt.getGrace_late_time(), empAtt.getEmployee_code(), minDateTimeValue, maxDateTimeValue
                                        , shiftStartTime, shiftEndTime, shortLeaveApplicationDetails, employeeMap, attendanceDateStr);

//					            USE GETORDEFAULT TO HANDLE MISSING KEYS
                                empAtt.setGate_pass_flag((String) employeeMap.getOrDefault("gate_pass_flag", "N"));
                                empAtt.setEarly_go_flag((String) employeeMap.getOrDefault("early_go_flag", "N"));
                                empAtt.setLate_mark_flag((String) employeeMap.getOrDefault("late_mark_flag", "N"));
//                                empAtt.setRemark((String) employeeMap.getOrDefault("remark", ""));

//				    	        CREATE OBJECT FOR CHmAttLogModel
                                CHmAttLog2Model hmAttLog2Model = new CHmAttLog2Model();

                                if (minDateTimeValue != null) {
                                    hmAttLog2Model.setAtt_date_added(new Date());
                                    hmAttLog2Model.setAtt_device_emp_code(empAtt.getOld_employee_code());
                                    hmAttLog2Model.setAtt_device_ip("1.1.1.1");
                                    hmAttLog2Model.setAtt_device_id(1);
//					                hmAttLog2Model.setAtt_log_transaction_id();
                                    hmAttLog2Model.setAtt_date_time(minDateTimeValue.format(formatterInHhMmSs));

                                    attLogDetails.add(hmAttLog2Model);
                                }

                                if (maxDateTimeValue != null) {
                                    hmAttLog2Model = new CHmAttLog2Model();

                                    hmAttLog2Model.setAtt_date_added(new Date());
                                    hmAttLog2Model.setAtt_device_emp_code(empAtt.getOld_employee_code());
                                    hmAttLog2Model.setAtt_device_ip("1.1.1.1");
                                    hmAttLog2Model.setAtt_device_id(1);
//					                hmAttLog2Model.setAtt_log_transaction_id();
                                    hmAttLog2Model.setAtt_date_time(maxDateTimeValue.format(formatterInHhMmSs));

                                    attLogDetails.add(hmAttLog2Model);
                                }

                            }

//				            PREPARE CHTATTENDANCEMONTHLYMODEL TO STORE MONTHLY DATA OF EMPLOYEE ATTENDANCE
                            Optional<CHtAttendanceMonthlyModel> findMatchingObjectOfMonth = getAttendanceMonthlyDetails.stream()
                                    .filter(item -> item.getEmployee_code().equals(empAtt.getEmployee_code())).findFirst();

                            if (!findMatchingObjectOfMonth.isPresent()) {
                                // Check if it is present in htAttendanceMonthlyModels
                                findMatchingObjectOfMonth = htAttendanceMonthlyModels.stream()
                                        .filter(item -> item.getEmployee_code().equals(empAtt.getEmployee_code()))
                                        .findFirst();
                            }

                            CHtAttendanceMonthlyModel htAttendanceMonthlyModel = new CHtAttendanceMonthlyModel();

                            if (findMatchingObjectOfMonth.isPresent()) {
                                htAttendanceMonthlyModel = findMatchingObjectOfMonth.get();
                                String employeeType = iHtAttendanceMonthlyRepository.FnGetEmployeesByEmplType(empAtt.getEmployee_code(), empAtt.getEmployee_id(), empAtt.getEmployee_type(), empAtt.getCompany_id());
                                empAtt.setEmployee_type_group(employeeType);
//					            ADD ATTENDANCE STATUS IN OBJECT
                                updateAttendanceDays(empAtt, htAttendanceMonthlyModel, shortLeaveApplicationDetails, leaveApplicationDetails, lateComingSalaryRules);

                                if (htAttendanceMonthlyModel.getTotal_latemarks() != 0 && !"Workers".equals(htAttendanceMonthlyModel.getEmployee_type())) {
                                    Optional<CHtvSalaryRulesViewModel> applicableRule = getApplicableRule(lateComingSalaryRules, htAttendanceMonthlyModel.getTotal_latemarks(), empAtt);

                                    CHtAttendanceMonthlyModel finalHtAttendanceMonthlyModel = htAttendanceMonthlyModel;
                                    if (!"Semi-Staff".equals(empAtt.getEmployee_type_group())) {
                                        applicableRule.ifPresent(rule -> {
                                            double total_late_mark_days = 0;
                                            if (rule.getJob_type_id() == 3) {
                                                total_late_mark_days = 0.5;
                                            } else if (rule.getJob_type_id() == 2) {
//                                            total_late_mark_days = finalHtAttendanceMonthlyModel.getTotal_latemarks() - Double.valueOf(rule.getRule_days());
                                                double lateMarks = finalHtAttendanceMonthlyModel.getTotal_latemarks();
                                                double ruleDays = Double.valueOf(rule.getRule_days());

                                                if (lateMarks == 4) {
                                                    total_late_mark_days = 0.5; // 4 late marks = 0.5 days
                                                } else if (lateMarks == 5) {
                                                    total_late_mark_days = 1; // 5 late marks = 1 day
                                                } else if (lateMarks > 5) {
                                                    total_late_mark_days = 1 + (lateMarks - ruleDays); // Start from 1 day at 5 late marks, add 1 for each extra late mark
                                                }
                                            }
                                            finalHtAttendanceMonthlyModel.setTotal_latemarks_days(total_late_mark_days);
                                        });
                                    }

                                    htAttendanceMonthlyModel.setTotal_latemarks_days(finalHtAttendanceMonthlyModel.getTotal_latemarks_days());
                                }


//					            GET MONTHLY ATTENDANCE FIELDS
                                setPresentyDateWiseForMonthlyCalculations(empAtt, htAttendanceMonthlyModel, day, leaveApplicationDetails, shortLeaveApplicationDetails); // SET DATEWISE MONTHLY CALCULATION

//					            CALCULATE TOTAL SALARY DAYS
                                double totalSalaryDays = htAttendanceMonthlyModel.calculateTotalSalaryDays(empAtt.isAttendance_exclude_flag());
                                htAttendanceMonthlyModel.setTotal_salary_days(totalSalaryDays);

//					            GET WEEKLY OFF DAYS
                                setWeeklyOffDays(htAttendanceMonthlyModel, year, month, empAtt.getWeeklyoff_name(), currentMonthHolidayDates, empAtt.isAttendance_exclude_flag());

//					            htAttendanceMonthlyModel.setMonthly_hours(htAttendanceMonthlyModel.getMonthly_hours() + Double.parseDouble(empAtt.getWorking_minutes()));

                            } else {
                                htAttendanceMonthlyModel.setCompany_id(empAtt.getCompany_id());
                                htAttendanceMonthlyModel.setCompany_branch_id(empAtt.getCompany_branch_id());
                                htAttendanceMonthlyModel.setProcess_date(new Date());
                                htAttendanceMonthlyModel.setEmployee_type(attendanceRequests.getEmployee_type_group());
                                htAttendanceMonthlyModel.setEmployee_id(empAtt.getEmployee_id());
                                htAttendanceMonthlyModel.setEmployee_code(empAtt.getEmployee_code());
                                htAttendanceMonthlyModel.setAttendance_month(month);
                                htAttendanceMonthlyModel.setAttendance_year(year);
                                htAttendanceMonthlyModel.setDepartment_id(empAtt.getDepartment_id());
                                htAttendanceMonthlyModel.setSub_department_id(empAtt.getSub_department_id());
                                htAttendanceMonthlyModel.setMonth_days(monthDays);

//					            ADD ATTENDANCE STATUS IN OBJECT
                                updateAttendanceDays(empAtt, htAttendanceMonthlyModel, shortLeaveApplicationDetails, leaveApplicationDetails, lateComingSalaryRules);
//
//					            GET WEEKLY OFF DAYS
                                setWeeklyOffDays(htAttendanceMonthlyModel, year, month, empAtt.getWeeklyoff_name(), currentMonthHolidayDates, empAtt.isAttendance_exclude_flag());

//				                GET MONTHLY ATTENDANCE FIELDS
                                setPresentyDateWiseForMonthlyCalculations(empAtt, htAttendanceMonthlyModel, day, leaveApplicationDetails, shortLeaveApplicationDetails);  // SET DATEWISE MONTHLY CALCULATION

//					            CALCULATE TOTAL SALARY DAYS
                                double totalSalaryDays = htAttendanceMonthlyModel.calculateTotalSalaryDays(empAtt.isAttendance_exclude_flag());
                                htAttendanceMonthlyModel.setTotal_salary_days(totalSalaryDays);

//					            CALCULATE TOTAL HOURS
                                Double totalShiftHr = FnCalculateHoursInDecimal(empAtt.getShift_start_end_time());
                                Double workingHours = parseTimeToHours(empAtt.getWorking_minutes());

// Ensure workingHours does not exceed totalShiftHr
//                                    if (workingHours > totalShiftHr && !"Semi-Staff".equals(empAtt.getEmployee_type_group()) && !"HD".equals(empAtt.getJob_type())) {
//                                if (workingHours > totalShiftHr && "Semi-Staff".equals(empAtt.getEmployee_type_group()) && ("HD".equals(empAtt.getJob_type()) || "HP".equals(empAtt.getJob_type()))) {
//                                    workingHours = workingHours;
//                                } else {
//                                    workingHours = totalShiftHr;
//                                }
                                if (workingHours > totalShiftHr) {
                                    workingHours = totalShiftHr;
                                }
                                if (workingHours >= totalShiftHr && "Semi-Staff".equals(empAtt.getEmployee_type_group()) && ("HD".equals(empAtt.getJob_type()) || "HP".equals(empAtt.getJob_type()))) {
                                    Double workingHour = parseTimeToHours(empAtt.getWorking_minutes());
                                    workingHours = workingHour;

                                }
                                htAttendanceMonthlyModel.setMonthly_hours(
                                        empAtt.getWorking_minutes() != null ? workingHours : 0.0
                                );
//                                htAttendanceMonthlyModel.setMonthly_hours(
//                                        empAtt.getWorking_minutes() != null ? parseTimeToHours(empAtt.getWorking_minutes()) : 0.0
//                                );
//

                            }

                            Optional<CHtAttendanceMonthlyModel> getMonthlyObjectFromExistingDetails = htAttendanceMonthlyModels.stream()
                                    .filter(item -> item.getEmployee_code().equals(empAtt.getEmployee_code())).findFirst();

                            if (getMonthlyObjectFromExistingDetails.isPresent()) {
                                htAttendanceMonthlyModels.set(htAttendanceMonthlyModels.indexOf(getMonthlyObjectFromExistingDetails.get()), htAttendanceMonthlyModel);
                            } else {
                                htAttendanceMonthlyModels.add(htAttendanceMonthlyModel);
                            }

                        });

//				Move to the next date
                currentDate = currentDate.plusDays(1);
            }


            iHtAttendanceDailyRepository.saveAll(htAttendanceDailyModels);    //INSERT INTO HT_ATTENDANCE_DAILY EMPLOYEES DAILY ATTENDANCE

            List<CHtAttendanceMonthlyModel> getUpdatedMonthlyAttendance = iHtAttendanceMonthlyRepository.saveAll(htAttendanceMonthlyModels);  //INSERT INTO ht_attendance_monthly EMPLOYEES MONTHLY ATTENDANCE

            iHmAttLog2ModelRepository.saveAll(attLogDetails);  // INSERT INTO hm_att_log FOR MISSING PUNCH SAVE

            if ("Workers".equals(attendanceRequests.getEmployee_type_group())) {
//			    IN ACTIVE EXISTING SALARY JOB TYPE FOR THAT EMPLOYEES
                iHtAttendanceMonthlyJobTypeRepository.inActiveExistingJobTypeSalary(getEmployeeCodes, month, year);

//			    GET EMPLOYEE LATEST MONTH DATA FROM DAILY ATTENDANCE
                String employeeCodesString = getEmployeeCodes.stream()
                        .map(code -> "'" + code + "'")  // Wrap each employee code in single quotes
                        .collect(Collectors.joining(","));

                String query = String.format(
//                        "SELECT model.*, emp.shift_start_end_time, emp.department_id, emp.sub_department_id ,emp.job_type_id AS emp_job_type_id, emp.job_type_short_name AS emp_job_type_short_name  " +
                        "SELECT model.*, emp.shift_start_end_time, emp.department_id, emp.sub_department_id " +
                                "FROM ht_attendance_daily model " +
                                "JOIN cmv_employee emp ON model.employee_code = emp.employee_code " +
                                "WHERE model.employee_code IN (%s) " +
                                "AND model.is_delete = false " +
                                "AND model.employee_type = '%s' " +
                                "AND model.attendance_month = %d " +
                                "AND model.company_id = %d " +
                                "AND emp.is_active = 1",  // Added condition to filter active employees
                        employeeCodesString, attendanceRequests.getEmployee_type_group(), month, attendanceRequests.getCompany_id()
                );

                List<CHtAttendanceDailyTempModel> getDailyAttendanceDetails = executeQuery.query(
                        query,
                        new RowMapper<CHtAttendanceDailyTempModel>() {
                            @Override
                            public CHtAttendanceDailyTempModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                                CHtAttendanceDailyTempModel model = new CHtAttendanceDailyTempModel();

                                // Mapping fields from ResultSet to CHtAttendanceDailyTempModel
                                model.setDaily_attendance_id(rs.getInt("daily_attendance_id"));
                                model.setCompany_id(rs.getInt("company_id"));
                                model.setCompany_branch_id(rs.getInt("company_branch_id"));
                                model.setEmployee_id(rs.getInt("employee_id"));
                                model.setEmployee_code(rs.getString("employee_code"));
                                model.setEmployee_type(rs.getString("employee_type"));
                                model.setAttendance_date(rs.getInt("attendance_date"));
                                model.setAttendance_month(rs.getInt("attendance_month"));
                                model.setAttendance_year(rs.getInt("attendance_year"));
                                model.setShift_id(rs.getInt("shift_id"));
                                model.setShift_scheduled(rs.getString("shift_scheduled"));
                                model.setShift_present(rs.getString("shift_present"));
                                model.setJob_type_id(rs.getInt("job_type_id"));
                                model.setJob_type(rs.getString("job_type"));
                                model.setIn_time(rs.getString("in_time"));
                                model.setOut_time(rs.getString("out_time"));
                                model.setWorking_minutes(rs.getString("working_minutes"));
                                model.setLate_mark_flag(rs.getString("late_mark_flag"));
                                model.setEarly_go_flag(rs.getString("early_go_flag"));
                                model.setGate_pass_flag(rs.getString("gate_pass_flag"));
                                model.setWeek_off_present_flag(rs.getString("week_off_present_flag"));
                                model.setNight_shift_present_flag(rs.getString("night_shift_present_flag"));
                                model.setCreated_by(rs.getString("created_by"));
                                model.setCreated_on(rs.getTimestamp("created_on"));
                                model.setModified_by(rs.getString("modified_by"));
                                model.setModified_on(rs.getTimestamp("modified_on"));
                                model.setDeleted_by(rs.getString("deleted_by"));
                                model.setDeleted_on(rs.getTimestamp("deleted_on"));
                                model.setRemark(rs.getString("remark"));
                                model.setAtt_date_time(rs.getString("att_date_time"));
                                model.setPresent_flag(rs.getString("present_flag"));
                                model.setAttendance_flag(rs.getString("attendance_flag"));

                                model.setJob_type_code_id(rs.getInt("job_type_code_id"));
                                model.setJob_type_code_short_name(rs.getString("job_type_code_short_name"));

                                // Additional fields from cmv_employee
                                model.setShift_start_end_time(rs.getString("shift_start_end_time"));
                                model.setDepartment_id(rs.getInt("department_id"));
                                model.setSub_department_id(rs.getInt("sub_department_id"));
//                                model.setJob_type_code_id(rs.getInt("emp_job_type_id"));
//                                model.setJob_type_code_short_name(rs.getString("emp_job_type_short_name"));

                                return model;
                            }
                        }
                );

//          ITERATE ON EMPLOYEE CODE & PROCESS JOB TYPE WISE SALARY
                getEmployeeCodes.forEach(employeeCode -> {

                    Optional<CHtAttendanceMonthlyModel> findMatchingObjectOfMonth = getUpdatedMonthlyAttendance.stream()
                            .filter(item -> item.getEmployee_code().equals(employeeCode)).findFirst();

                    List<CHtAttendanceDailyTempModel> getDailyAttendance = getDailyAttendanceDetails.stream()
                            .filter(item -> item.getEmployee_code().equals(employeeCode)).collect(Collectors.toList());

                    // Collecting the new entries separately to avoid modification while iterating
                    List<CHtAttendanceMonthlyJobTypeModel> newEntries = new ArrayList<>();

                    getDailyAttendance.forEach(empAtt -> {
                        // GET JOB TYPE CODE MODEL
                        CJobTypeModel currentJobTypeCodeModel = getJobTypeDetailById(jobTypeDetails, empAtt.getJob_type_code_id());

                        // GET MONTHLY MODEL OF JOB TYPE
                        List<CHtAttendanceMonthlyJobTypeModel> currentEmpJobTypeWiseMonthlyDtl = newEntries.stream()
                                .filter(item -> item.getEmployee_code().equals(empAtt.getEmployee_code()))
                                .collect(Collectors.toList());

                        // Create the new models for this employee's attendance
                        List<CHtAttendanceMonthlyJobTypeModel> cHtAttendanceMonthlyJobTypeModels = FnSetMonthlyAttendaceJobTypeWise(
                                empAtt, currentEmpJobTypeWiseMonthlyDtl, currentJobTypeCodeModel, findMatchingObjectOfMonth.get(),getShiftDetails);

                        // Collect new entries in the list
                        newEntries.addAll(cHtAttendanceMonthlyJobTypeModels);
                    });

                    // Safely add the new entries after parallel processing
                    synchronized (htAttendanceMonthlyModelsJobTypeWise) {
                        htAttendanceMonthlyModelsJobTypeWise.addAll(newEntries);
                    }

                });


                iHtAttendanceMonthlyJobTypeRepository.saveAll(htAttendanceMonthlyModelsJobTypeWise); //INSERT INTO ht_attendance_monthly EMPLOYEES JOBTypeCode MONTHLY ATTENDANCE
            }

            response.put("success", 1);
            response.put("message", "Records added successfully!...");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("error", e.getMessage());
        }

        return response;
    }

    //	THE METHOD IS TO CHECK FOR RULE FOR SPECIFIC DEPARTMENT OR FOR ALL DEPARTMENT
    public Optional<CHtvSalaryRulesViewModel> getApplicableRule(List<CHtvSalaryRulesViewModel> rules, Double lateMarks, CHtAttendanceDailyModel empAtt) {
        // First, try to find a rule specific to the employee's department
        Optional<CHtvSalaryRulesViewModel> departmentSpecificRule = rules.stream()
                .filter(rule -> (rule.getDepartment_id() == empAtt.getDepartment_id() && rule.getSub_department_id() == empAtt.getSub_department_id())
                        || (rule.getDepartment_id() == empAtt.getDepartment_id() && rule.getSub_department_id() == 0))
                .filter(rule -> lateMarks >= Integer.parseInt(rule.getRule_days()))
                .max(Comparator.comparingInt(rule -> Integer.parseInt(rule.getRule_days())))
                .or(() -> rules.stream()
                        .filter(rule -> (rule.getDepartment_id() == empAtt.getDepartment_id() && rule.getSub_department_id() == empAtt.getSub_department_id())
                                || (rule.getDepartment_id() == empAtt.getDepartment_id() && rule.getSub_department_id() == 0))
                        .filter(rule -> lateMarks == Integer.parseInt(rule.getRule_days()))
                        .findFirst());

        if (departmentSpecificRule.isPresent()) {
            return departmentSpecificRule;
        }

        // If no department-specific rule is found, use general rules
        return rules.stream()
                .filter(rule -> lateMarks >= Integer.parseInt(rule.getRule_days()))
                .max(Comparator.comparingInt(rule -> Integer.parseInt(rule.getRule_days())))
                .or(() -> rules.stream()
                        .filter(rule -> lateMarks == Integer.parseInt(rule.getRule_days()))
                        .findFirst());
    }

    private List<CHtAttendanceMonthlyJobTypeModel> FnSetMonthlyAttendaceJobTypeWise(CHtAttendanceDailyTempModel empAtt, List<CHtAttendanceMonthlyJobTypeModel> currentEmpJobTypeWiseMonthlyDtl, CJobTypeModel currentJobTypeModel, CHtAttendanceMonthlyModel htAttendanceMonthlyModel, List<CShiftModel> shiftDetails) {
        List<CHtAttendanceMonthlyJobTypeModel> htAttendanceMonthlyModelsJobTypeWise = new ArrayList<>();

        Double totalShiftHrs = 0.0;

        String currentShift = empAtt.getShift_present();
        if (currentShift != null && !currentShift.isEmpty()) {
            Optional<CShiftModel> currentShiftTime = shiftDetails.stream()
                    .filter(shift -> String.valueOf(shift.getShift_name()).equals(currentShift))
                    .findFirst();

            if (currentShiftTime.isPresent()) {
                String result = currentShiftTime.get().getStart_time() + "-" + currentShiftTime.get().getEnd_time();
                System.out.println(result);
                totalShiftHrs = FnCalculateHoursInDecimal(result);
            } else {
                totalShiftHrs = FnCalculateHoursInDecimal(empAtt.getShift_start_end_time());
            }
        } else {
            totalShiftHrs = FnCalculateHoursInDecimal(empAtt.getShift_start_end_time());
        }


//        if (totalShiftHrs == null) {
//            totalShiftHrs = FnCalculateHoursInDecimal(empAtt.getShift_start_end_time());
//        }
//        System.out.println(totalShiftHrs);

        // Current Selected JobTypeModel;
        Double perHrJobTypeCodeRate = currentJobTypeModel.getJob_type_rate() / totalShiftHrs;

        // Calculate working hours in decimal
        Double workingHoursInDecimal = parseTimeToHours(empAtt.getWorking_minutes());


        // Get working minutes as HH:mm:ss format (e.g., "11:16:00")
//        String workingMinutesStr = empAtt.getWorking_minutes();
//
//        double workingHoursInDecimal = 0.0;
//        if (workingMinutesStr != null && !workingMinutesStr.isEmpty()) {
//            String[] parts = workingMinutesStr.split(":");
//            if (parts.length >= 2) { // Ensure it contains at least HH and mm
//                int hours = Integer.parseInt(parts[0]);  // Extract hours
//                int minutes = Integer.parseInt(parts[1]); // Extract minutes
////                workingHoursInDecimal = hours + (minutes / 60.0); // Convert to decimal hours
//                workingHoursInDecimal = Double.parseDouble(String.format("%02d.%02d", Math.abs(hours), Math.abs(minutes)));
//
//            }
//        }

        // Ensure working hours do not exceed totalShiftHrs
        if (workingHoursInDecimal > totalShiftHrs) {
            workingHoursInDecimal = totalShiftHrs;
        }

        // Update that Day Salary into the ht_attendance_daily modal
        empAtt.setCurrent_day_salary(workingHoursInDecimal * perHrJobTypeCodeRate);

//		Also Update that Day Salary into the ht_attendace_daily modal;
//		empAtt.setCurrent_day_salary(parseTimeToHours(empAtt.getWorking_minutes()) * perHrJobTypeCodeRate);

        CHtAttendanceMonthlyJobTypeModel existingJobTypeWiseMonthlyAtt = currentEmpJobTypeWiseMonthlyDtl.parallelStream()
//                .filter(item -> item.getJob_type_id() == empAtt.getJob_type_code_id())
                .filter(item -> item.getJob_type_id().equals(empAtt.getJob_type_code_id()))
                .findFirst()
                .orElse(null);

        // Update the existing MonthlyattendanceStatusDetails and return that object;
        if (existingJobTypeWiseMonthlyAtt != null) {
            existingJobTypeWiseMonthlyAtt.setAttendance_process_id(htAttendanceMonthlyModel.getAttendance_process_id());
            existingJobTypeWiseMonthlyAtt.setJob_type_total_days(existingJobTypeWiseMonthlyAtt.getJob_type_total_days() + 1);
            if ("Y".equalsIgnoreCase(empAtt.getNight_shift_present_flag())) {
                existingJobTypeWiseMonthlyAtt.setJob_type_night_total_days(existingJobTypeWiseMonthlyAtt.getJob_type_night_total_days() + 1);
            }
            existingJobTypeWiseMonthlyAtt.setJob_type_attendance_total_days(existingJobTypeWiseMonthlyAtt.getJob_type_attendance_total_days() + 1);
            existingJobTypeWiseMonthlyAtt.setJob_type_attendance_total_hours(
                    existingJobTypeWiseMonthlyAtt.getJob_type_attendance_total_hours() + workingHoursInDecimal
            );

            existingJobTypeWiseMonthlyAtt.setJob_type_total_salary(
                    existingJobTypeWiseMonthlyAtt.getJob_type_total_salary() + empAtt.getCurrent_day_salary()
            );
            // Soft delete entry if job_type_total_days is zero;
            existingJobTypeWiseMonthlyAtt.setIs_delete(existingJobTypeWiseMonthlyAtt.getJob_type_total_days() == 0);

            htAttendanceMonthlyModelsJobTypeWise.add(existingJobTypeWiseMonthlyAtt);
            return htAttendanceMonthlyModelsJobTypeWise;
        }

        // create new MonthlyattendanceStatusDetails and return that object;
        CHtAttendanceMonthlyJobTypeModel currentJobTypeMonthlyAtt = new CHtAttendanceMonthlyJobTypeModel();
        currentJobTypeMonthlyAtt.setCompany_id(empAtt.getCompany_id());
        currentJobTypeMonthlyAtt.setCompany_branch_id(empAtt.getCompany_branch_id());
        currentJobTypeMonthlyAtt.setAttendance_job_type_wise_process_id(0);
        currentJobTypeMonthlyAtt.setAttendance_process_id(htAttendanceMonthlyModel.getAttendance_process_id());
        currentJobTypeMonthlyAtt.setProcess_date(new Date());
        currentJobTypeMonthlyAtt.setEmployee_type(empAtt.getEmployee_type());
        currentJobTypeMonthlyAtt.setEmployee_id(empAtt.getEmployee_id());
        currentJobTypeMonthlyAtt.setEmployee_code(empAtt.getEmployee_code());
        currentJobTypeMonthlyAtt.setAttendance_month(empAtt.getAttendance_month());
        currentJobTypeMonthlyAtt.setAttendance_year(empAtt.getAttendance_year());
        currentJobTypeMonthlyAtt.setDepartment_id(empAtt.getDepartment_id());
        currentJobTypeMonthlyAtt.setSub_department_id(empAtt.getSub_department_id());
        currentJobTypeMonthlyAtt.setJob_type_id(empAtt.getJob_type_code_id());
        currentJobTypeMonthlyAtt.setJob_type_short_name(empAtt.getJob_type_code_short_name());
        currentJobTypeMonthlyAtt.setJob_type_total_days(1);
        // Update Total Night Day's
        if ("Y".equalsIgnoreCase(empAtt.getNight_shift_present_flag())) {
            currentJobTypeMonthlyAtt.setJob_type_night_total_days(1);
        }
        currentJobTypeMonthlyAtt.setJob_type_attendance_total_days(1);
        currentJobTypeMonthlyAtt.setJob_type_attendance_total_hours(workingHoursInDecimal);
        currentJobTypeMonthlyAtt.setJob_type_total_salary(
                currentJobTypeMonthlyAtt.getJob_type_total_salary() + (workingHoursInDecimal * perHrJobTypeCodeRate)
        );
        currentJobTypeMonthlyAtt.setJob_type_rate(currentJobTypeModel.getJob_type_rate());
        currentJobTypeMonthlyAtt.setJob_type_night_allowance(currentJobTypeModel.getJob_type_night_allowance());
        currentJobTypeMonthlyAtt.setJob_type_attendance_allowance(currentJobTypeModel.getJob_type_attendance_allowance());
        currentJobTypeMonthlyAtt.setIs_active(true);
        currentJobTypeMonthlyAtt.setIs_delete(false);
        currentJobTypeMonthlyAtt.setCreated_by(empAtt.getCreated_by());
        currentJobTypeMonthlyAtt.setModified_by(empAtt.getModified_by());

        htAttendanceMonthlyModelsJobTypeWise.add(currentJobTypeMonthlyAtt);
        return htAttendanceMonthlyModelsJobTypeWise;
    }

    private void updateAttendanceDays(CHtAttendanceDailyModel empAtt, CHtAttendanceMonthlyModel htAttendanceMonthlyModel,
                                      List<CHtShortLeaveModel> shortLeaveApplicationDetails,
                                      List<Map<String, Object>> leaveApplicationDetails, List<CHtvSalaryRulesViewModel> lateComingSalaryRules) {
        if (htAttendanceMonthlyModel.getAttendance_process_id() != null) {
            try {
                String jobTypeShortName = empAtt.getJob_type();

//			    CONSTRUCT FIELD NAMES DYNAMICALLY BASED ON THE DAY
                String presentyFieldName = "presenty" + empAtt.getAttendance_date();

//			    INITIALIZE FIELDS
                Field presentyField = htAttendanceMonthlyModel.getClass().getDeclaredField(presentyFieldName);

//              MAKE FIELDS ACCESSIBLE
                presentyField.setAccessible(true);

//			    GET THE VALUE
                Object presentyValue = presentyField.get(htAttendanceMonthlyModel);

                boolean isPRJobType = jobTypeShortName != null && presentyValue != null && presentyValue.toString().startsWith(jobTypeShortName);


                if (isPRJobType) {
                    return;
                } else {
//                  Convert presentyValue to a String
                    if (presentyValue != null) {
                        String presentyString = presentyValue.toString();

//                      Check if the string contains '/'
                        if (presentyString.contains("/")) {
                            // Split the string by "/" and get the first part
                            presentyString = presentyString.split("/")[0];
                        }
                        switch (presentyString) {
                            case "PR":
                                htAttendanceMonthlyModel.setPresent_days(htAttendanceMonthlyModel.getPresent_days() - 1);
                                break;
                            case "LV":
                                Map<String, Object> takenLeaveDtl = FnGetEmployeeLeaveDtlsByEmplCode(leaveApplicationDetails, empAtt.getEmployee_code());
                                if (takenLeaveDtl != null && takenLeaveDtl.containsKey("leave_type_paid_flag") && "Paid".equals(takenLeaveDtl.getOrDefault("leave_type_paid_flag", ""))) {
                                    htAttendanceMonthlyModel.setLeaves_days(htAttendanceMonthlyModel.getLeaves_days() - 1);
                                } else {
                                    htAttendanceMonthlyModel.setAbsent_days(htAttendanceMonthlyModel.getAbsent_days() - 1);
                                }
                                break;
                            case "HF":
                                boolean shortLeaveStatus = hasEmployeeTakenShortLeave(shortLeaveApplicationDetails, empAtt.getEmployee_code(), empAtt.getAtt_date_time());
                                if (shortLeaveStatus) {
                                    htAttendanceMonthlyModel.setHalf_days(htAttendanceMonthlyModel.getHalf_days() - 1);
                                    htAttendanceMonthlyModel.setAbsent_days(htAttendanceMonthlyModel.getAbsent_days() - 0.5);

                                } else {
                                    htAttendanceMonthlyModel.setHalf_days(htAttendanceMonthlyModel.getHalf_days() - 1);
                                    htAttendanceMonthlyModel.setAbsent_days(htAttendanceMonthlyModel.getAbsent_days() - 0.5);
                                }
                                break;
                            case "HD":
                                if (!"Workers".equals(empAtt.getEmployee_type())) {
                                    htAttendanceMonthlyModel.setHoliday_days(htAttendanceMonthlyModel.getHoliday_days() - 1);
                                }
                                break;
                            case "HP":
                                htAttendanceMonthlyModel.setHoliday_days(htAttendanceMonthlyModel.getHoliday_days() + 1);
                                htAttendanceMonthlyModel.setCoff_days(htAttendanceMonthlyModel.getCoff_days() - 1);
                                break;
                            case "CF":
                                htAttendanceMonthlyModel.setCoff_days(htAttendanceMonthlyModel.getCoff_days() - 1);
                                break;
                            case "WO":    // Increment the C_OffDays if the
                                if ("Y".equals(empAtt.getWeek_off_present_flag())) {
                                    htAttendanceMonthlyModel.setCoff_days(htAttendanceMonthlyModel.getCoff_days() - 1);
                                }
                                break;
                            case "AB":
                                htAttendanceMonthlyModel.setAbsent_days(htAttendanceMonthlyModel.getAbsent_days() - 1);
                                break;
                            default:
                                // Handle unknown job type
                                break;
                        }

                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        int jobTypeId = empAtt.getJob_type_id();
        switch (jobTypeId) {
            case PRESENT:
                htAttendanceMonthlyModel.setPresent_days(htAttendanceMonthlyModel.getPresent_days() + 1);
                break;
            case LEAVES:
                Map<String, Object> takenLeaveDtl = FnGetEmployeeLeaveDtlsByEmplCode(leaveApplicationDetails, empAtt.getEmployee_code());
                if (takenLeaveDtl != null && takenLeaveDtl.containsKey("leave_type_paid_flag") && "Paid".equals(takenLeaveDtl.getOrDefault("leave_type_paid_flag", ""))) {
                    htAttendanceMonthlyModel.setLeaves_days(htAttendanceMonthlyModel.getLeaves_days() + 1);
                } else {
                    htAttendanceMonthlyModel.setAbsent_days(htAttendanceMonthlyModel.getAbsent_days() + 1);
                }
                break;
            case HALF_DAY:
                boolean shortLeaveStatus = hasEmployeeTakenShortLeave(shortLeaveApplicationDetails, empAtt.getEmployee_code(), empAtt.getAtt_date_time());
                if (shortLeaveStatus) {
                    htAttendanceMonthlyModel.setHalf_days(htAttendanceMonthlyModel.getHalf_days() + 1);
                } else {
                    htAttendanceMonthlyModel.setHalf_days(htAttendanceMonthlyModel.getHalf_days() + 1);
                    htAttendanceMonthlyModel.setAbsent_days(htAttendanceMonthlyModel.getAbsent_days() + 0.5);
                }
                break;
            case HOLIDAY:
                if (!"Workers".equals(empAtt.getEmployee_type())) {
                    htAttendanceMonthlyModel.setHoliday_days(htAttendanceMonthlyModel.getHoliday_days() + 1);
                }
                break;
            case HOLIDAY_PRESENT:
                htAttendanceMonthlyModel.setHoliday_days(htAttendanceMonthlyModel.getHoliday_days() + 1);
                htAttendanceMonthlyModel.setCoff_days(htAttendanceMonthlyModel.getCoff_days() + 1);
                break;
            case C_OFF:
                htAttendanceMonthlyModel.setCoff_days(htAttendanceMonthlyModel.getCoff_days() + 1);
                break;
            case WEEKLY_OFF:    // Increment the C_OffDays if the
                if ("Y".equals(empAtt.getWeek_off_present_flag())) {
                    htAttendanceMonthlyModel.setCoff_days(htAttendanceMonthlyModel.getCoff_days() + 1);
                }
                break;
            case ABSENT:
                htAttendanceMonthlyModel.setAbsent_days(htAttendanceMonthlyModel.getAbsent_days() + 1);
                break;
            default:
                // Handle unknown job type
                break;
        }

        if ("Y".equals(empAtt.getLate_mark_flag())
//                || "Y".equals(empAtt.getEarly_go_flag())
        ) {
//            if("Y".equals(empAtt.getLate_mark_flag()) && "Y".equals(empAtt.getEarly_go_flag())){
//                htAttendanceMonthlyModel.setTotal_latemarks(htAttendanceMonthlyModel.getTotal_latemarks() + 2);
//            } else {
//                htAttendanceMonthlyModel.setTotal_latemarks(htAttendanceMonthlyModel.getTotal_latemarks() + 1);
//            }
//
//            if(htAttendanceMonthlyModel.getTotal_latemarks() >= 3){
//                htAttendanceMonthlyModel.setTotal_latemarks_days(htAttendanceMonthlyModel.getTotal_latemarks() / 3);
//            }
            htAttendanceMonthlyModel.setTotal_latemarks(htAttendanceMonthlyModel.getTotal_latemarks() + 1);


        }

        if ("Y".equalsIgnoreCase(empAtt.getNight_shift_present_flag())) {
            htAttendanceMonthlyModel.setNight_days(htAttendanceMonthlyModel.getNight_days() + 1);
        }
    }

    //    MISSED PUNCH REPORT
    @PostMapping("/FnShowEmployeesMissPunchDetails")
    public Map<String, Object> FnShowEmployeesMissPunchDetails(@RequestBody CEmployeeMissPunchDetailsRequest employeeWiseAttendanceRequest) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            List<Map<String, Object>> employeeMissedPunchDetails = new ArrayList<>();

            int company_id = employeeWiseAttendanceRequest.getCompany_id();
            String employeeTypeGroup = employeeWiseAttendanceRequest.getEmployee_type_group();
            String shiftName = employeeWiseAttendanceRequest.getShift_name();
            Integer department_id = employeeWiseAttendanceRequest.getDepartment_id();
            Integer subDepartment_id = employeeWiseAttendanceRequest.getSub_department_id();
            Integer employeeId = employeeWiseAttendanceRequest.getEmployee_id();

//			USING ATTENDANCE DATE GET DAY NAME
            String attendanceFromDate = employeeWiseAttendanceRequest.getAttendance_from_date();
            String attendanceToDate = employeeWiseAttendanceRequest.getAttendance_to_date();

            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT ")
                    .append("employee_id, ")
                    .append("employee_code, ")
                    .append("employee_type, ")
                    .append("employee_type_group, ")
                    .append("employee_name, ")
                    .append("department_name, ")
                    .append("sub_department_name, ")
                    .append("designation_name, ")
                    .append("shift_name, ")
                    .append("weeklyoff_name, ")
                    .append("department_group, ")
                    .append("old_employee_code, ")
                    .append("machine_employee_code, ")
                    .append("shift_start_end_time, ")
                    .append("shift_grace_hours_min, ")
                    .append("shift_grace_hours_max, ")
                    .append("halfday_hours, ")
                    .append("fullday_hours, ")
                    .append("grace_early_time, ")
                    .append("grace_late_time, ")
                    .append("two_day_shift, ")
                    .append("attendance_exclude_flag, ")
                    .append("department_id, ")
                    .append("shift_id, ")
                    .append("reporting_to, ")
                    .append("sub_department_id ")
                    .append("FROM cmv_employee ")
                    .append("WHERE company_id = ? ")
                    .append("AND employee_type_group = ? ")
                    .append("AND is_active = 1 ");

            List<Object> params = new ArrayList<>();
            params.add(company_id);
            params.add(employeeTypeGroup);

            if (shiftName != null && !shiftName.isEmpty() && !shiftName.equals("All")) {
                queryBuilder.append("AND shift_name = ? ");
                params.add(shiftName);
            }

            if (department_id != null && department_id != 0) {
                queryBuilder.append("AND department_id = ? ");
                params.add(department_id);
            }
            if (subDepartment_id != null && subDepartment_id != 0) {
                queryBuilder.append("AND sub_department_id = ? ");
                params.add(subDepartment_id);
            }
            // Add employee_id or reporting_to condition with LIKE
            if (employeeId != null && employeeId != 0 && employeeId != 1 && employeeId != 3) {
                queryBuilder.append("AND (employee_id LIKE ? OR reporting_to LIKE ?) ");
                String employeeIdPattern = "%" + employeeId + "%";
                params.add(employeeIdPattern);
                params.add(employeeIdPattern);
            }


            queryBuilder.append("ORDER BY sub_department_id, department_id,  shift_name ASC");

            List<CEmployeesViewModel> employeeRecords = executeQuery.query(queryBuilder.toString(), params.toArray(), new BeanPropertyRowMapper<>(CEmployeesViewModel.class));

//			SEPERATE EMPLOYEE CODES FROM ALL EMPLOYEE RECORDS
            List<String> getEmployeeCodes = employeeRecords.stream().map(CEmployeesViewModel::getEmployee_code).collect(Collectors.toList());
            List<CShiftModel> getShiftDetails = iShiftRepository.getAllShiftDetails();

//          GET MISSED PUNCH DETAILS FROM THIS ht_attendance_daily
            List<CHtAttendanceDailyModel> getMissPunchDetails;
            if (attendanceToDate == null || attendanceToDate.isEmpty()) {
                getMissPunchDetails = iHtAttendanceDailyRepository.getMissPunchDetailsOnSingleDate(attendanceFromDate, getEmployeeCodes);
            } else {
                getMissPunchDetails = iHtAttendanceDailyRepository.getMissPunchDetailsWithinDateRange(attendanceFromDate, attendanceToDate, getEmployeeCodes);
            }

            List<CHtMissPunchCorrectionModel> missPunchCorrectionDetails = iHtMissPunchCorrectionRepository.getMissPunchCorrectionWithinDateRange(attendanceFromDate, attendanceToDate, getEmployeeCodes);

//			ITERATE ON EMPLOYEE DETAILS FOR IN OUT TIMINGS
            employeeRecords.stream()
                    .forEach(employeeItem -> {
//						List<CHtAttendanceDailyModel> getMissPunchDetailsEmployeeWise = getMissPunchDetails.stream()
//								.filter(item -> item.getEmployee_code().equals(employeeItem.getEmployee_code())).collect(Collectors.toList());
//						List<CHtAttendanceDailyModel> getMissPunchDetailsEmployeeWise = getMissPunchDetails.stream()
//								.filter(item -> item.getEmployee_code().equals(employeeItem.getEmployee_code()))
//								.filter(item -> (item.getIn_time() == null || item.getIn_time().isEmpty() || item.getIn_time().equals("00:00"))
//										|| (item.getOut_time() == null || item.getOut_time().isEmpty() || item.getOut_time().equals("00:00")))
//								.filter(item -> (item.getIn_time() != null && !item.getIn_time().isEmpty() && !item.getIn_time().equals("00:00"))
//										|| (item.getOut_time() != null && !item.getOut_time().isEmpty() && !item.getOut_time().equals("00:00")))
//								.collect(Collectors.toList());

                        List<CHtAttendanceDailyModel> getMissPunchDetailsEmployeeWise = getMissPunchDetails.stream()
                                .filter(item -> item.getEmployee_code().equals(employeeItem.getEmployee_code()))
                                .filter(item ->
                                        // Check if in_time is invalid and out_time is valid
                                        ((item.getIn_time() == null || item.getIn_time().isEmpty() || item.getIn_time().equals("00:00"))
                                                && (item.getOut_time() != null && !item.getOut_time().isEmpty() && !item.getOut_time().equals("00:00")))

                                                // OR check if out_time is invalid and in_time is valid
                                                || ((item.getOut_time() == null || item.getOut_time().isEmpty() || item.getOut_time().equals("00:00"))
                                                && (item.getIn_time() != null && !item.getIn_time().isEmpty() && !item.getIn_time().equals("00:00")))
                                )
                                .collect(Collectors.toList());
                        getMissPunchDetailsEmployeeWise.forEach(missingPunchItem -> {

                            Map<String, Object> employeeMap = new LinkedHashMap<>();

                            employeeMap.put("employee_id", employeeItem.getEmployee_id());
                            employeeMap.put("employee_code", employeeItem.getEmployee_code());
                            employeeMap.put("old_employee_code", employeeItem.getOld_employee_code());
                            employeeMap.put("machine_employee_code", employeeItem.getMachine_employee_code());
                            employeeMap.put("employee_type", employeeItem.getEmployee_type_group());
                            employeeMap.put("employee_name", employeeItem.getEmployee_name());
                            employeeMap.put("department_name", employeeItem.getDepartment_name());
                            employeeMap.put("sub_department_name", employeeItem.getSub_department_name());
                            employeeMap.put("shift_name", missingPunchItem.getShift_present());
                            employeeMap.put("shift_start_end_time", getShiftDetails.stream()
                                    .filter(shift -> shift.getShift_name().equals(missingPunchItem.getShift_present()))
                                    .map(shift -> String.format("%s-%s", shift.getStart_time(), shift.getEnd_time()))
                                    .findFirst().orElse("Time not found"));
                            employeeMap.put("two_day_shift", getShiftDetails.stream()
                                    .filter(shift -> shift.getShift_name().equals(missingPunchItem.getShift_present()))
                                    .map(CShiftModel::isTwo_day_shift)
                                    .findFirst().orElse(false));
//                            employeeMap.put("shift_start_end_time", employeeItem.getShift_start_end_time());
//                            employeeMap.put("two_day_shift", employeeItem.isTwo_day_shift());
                            employeeMap.put("attendance_date", missingPunchItem.getAtt_date_time());
                            employeeMap.put("min_att_time", missingPunchItem.getIn_time());
                            employeeMap.put("max_att_time", missingPunchItem.getOut_time());
                            employeeMap.put("reporting_to", employeeItem.getReporting_to());
                            if (missingPunchItem.getOut_time() != null && !missingPunchItem.getOut_time().isEmpty()) {
                                employeeMap.put("editableIn", "Yes");
                            }
                            if (missingPunchItem.getIn_time() != null && !missingPunchItem.getIn_time().isEmpty()) {
                                employeeMap.put("editableOut", "Yes");
                            }

                            if ("Staffs".equals(employeeItem.getEmployee_type_group())) {
                                LocalDate FromDate = LocalDate.parse(attendanceFromDate);
                                LocalDate ToDate = LocalDate.parse(attendanceToDate);
                                List<Map<String, Object>> leaveApplicationDetails = iHmLeavesApplicationsRepository.getLeavesApplicationDetails(FromDate, ToDate, getEmployeeCodes);

                                boolean isOnLeave = leaveApplicationDetails.stream()
                                        .anyMatch(leave -> leave.get("employee_code").equals(employeeItem.getEmployee_code()));

                                if (isOnLeave) {
                                    String leaveNearbyFlag = iHtMissPunchCorrectionRepository.checkNearbyLeave(employeeItem.getEmployee_code(), missingPunchItem.getAtt_date_time());
                                    employeeMap.put("leave_nearby_flag", leaveNearbyFlag); // "yes" or "no"
                                }
                            } else {
                                employeeMap.put("leave_nearby_flag", "no"); // "yes" or "no"
                            }
                            // Now, check for corrections and update in/out times
                            missPunchCorrectionDetails.stream()
                                    .filter(correction ->
                                            correction.getEmployee_code().equals(employeeItem.getEmployee_code()) &&
                                                    LocalDate.parse(correction.getAtt_date_time()).isEqual(LocalDate.parse(missingPunchItem.getAtt_date_time()))
                                    ).findFirst().ifPresent(correction -> {
                                        employeeMap.put("misspunch_correction_id", correction.getMisspunch_correction_id());
                                        // Update min_att_time and max_att_time from correction details
                                        if (correction.getIn_time() != null && !correction.getIn_time().isEmpty()) {
                                            employeeMap.put("min_att_time", correction.getIn_time());
                                            employeeMap.put("punching_type", correction.getPunch_type());
                                        }
                                        if (correction.getOut_time() != null && !correction.getOut_time().isEmpty()) {
                                            employeeMap.put("max_att_time", correction.getOut_time());
                                            employeeMap.put("punching_type", correction.getPunch_type());
                                        }
                                    });
                            employeeMissedPunchDetails.add(employeeMap);
                        });
                    });
            // Step 2: Add any records from missPunchCorrectionDetails not already in getMissPunchDetailsEmployeeWise
            missPunchCorrectionDetails.forEach(correction -> {
                boolean existsInMissPunchDetails = employeeMissedPunchDetails.stream().anyMatch(detail ->
                        detail.get("employee_code").equals(correction.getEmployee_code()) &&
                                detail.get("attendance_date").equals(correction.getAtt_date_time())
                );

                if (!existsInMissPunchDetails) {
                    Map<String, Object> employeeMap = new LinkedHashMap<>();
                    CEmployeesViewModel employee = employeeRecords.stream()
                            .filter(emp -> emp.getEmployee_code().equals(correction.getEmployee_code()))
                            .findFirst().orElse(null);

                    if (employee != null) {
                        employeeMap.put("employee_id", employee.getEmployee_id());
                        employeeMap.put("employee_code", employee.getEmployee_code());
                        employeeMap.put("old_employee_code", employee.getOld_employee_code());
                        employeeMap.put("machine_employee_code", employee.getMachine_employee_code());
                        employeeMap.put("employee_type", employee.getEmployee_type_group());
                        employeeMap.put("employee_name", employee.getEmployee_name());
                        employeeMap.put("department_name", employee.getDepartment_name());
                        employeeMap.put("sub_department_name", employee.getSub_department_name());
                        employeeMap.put("shift_name", employee.getShift_name());
                        employeeMap.put("shift_start_end_time", employee.getShift_start_end_time());
                        employeeMap.put("two_day_shift", employee.isTwo_day_shift());

                    }
                    String leaveNearbyFlag = iHtMissPunchCorrectionRepository.checkNearbyLeave(
                            correction.getEmployee_code(),
                            correction.getAtt_date_time()
                    );
                    employeeMap.put("leave_nearby_flag", leaveNearbyFlag);
                    employeeMap.put("attendance_date", correction.getAtt_date_time());
                    employeeMap.put("min_att_time", correction.getIn_time());
                    employeeMap.put("max_att_time", correction.getOut_time());
                    employeeMap.put("misspunch_correction_id", correction.getMisspunch_correction_id());
                    if ("In".equals(correction.getPunch_type())) {
                        employeeMap.put("punching_type", "IN_approve");
                    } else {
                        employeeMap.put("punching_type", "OUT_approve");
                    }


                    employeeMissedPunchDetails.add(employeeMap);
                }
            });

            // SORT employeeMissedPunchDetails BY attendance_date
            List<Map<String, Object>> respEmployeeMissedPunchDetails = (List<Map<String, Object>>) employeeMissedPunchDetails.stream()
                    .sorted(Comparator.comparing(map -> (Comparable) map.get("attendance_date")))
                    .collect(Collectors.toList());


            response.put("content", respEmployeeMissedPunchDetails);
            response.put("columns", prepareHeadersAndAccessors());
            response.put("success", 1);

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();

            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            return response;
        }
        return response;

    }

    @PostMapping("/FnShowEmployeesAllPunchDetails")
    public Map<String, Object> FnShowEmployeesAllPunchDetails(@RequestBody EmployeePunchingRequest employeePunchingRequest) {
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Parse the input dates
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format as needed
            LocalDate fromDate = LocalDate.parse(employeePunchingRequest.getFrom_Date(), formatter);
            LocalDate toDate = LocalDate.parse(employeePunchingRequest.getTo_Date(), formatter);

            List<CEmployeesViewModel> cEmployeesViewModel = iEmployeesViewRepository.FnGetEmployeesForReport(
                    employeePunchingRequest.getEmployee_type(),
                    employeePunchingRequest.getDepartment(),
                    employeePunchingRequest.getSub_department(),
                    employeePunchingRequest.getCompany_id(),
                    employeePunchingRequest.getPunching_code()

            );

            List<String> distinctPunchingCodes = cEmployeesViewModel.stream().map(CEmployeesViewModel::getOld_employee_code)
                    .collect(Collectors.toList());

            List<CHmvAttLogCombinedModel> hmvAttLogCombinedModels = iHmvAttLogCombinedModelRepository.getPunchingDetailsByPunchCodes(distinctPunchingCodes,
                    sdf.parse(employeePunchingRequest.getFrom_Date()),
                    sdf.parse(employeePunchingRequest.getTo_Date()));


            // Generate a list of dates from fromDate to toDate
            List<LocalDate> dateList = Stream.iterate(fromDate, date -> date.plusDays(1))
                    .limit(toDate.toEpochDay() - fromDate.toEpochDay() + 1) // +1 to include toDate
                    .collect(Collectors.toList());

            List<Map<String, Object>> employeeAttendanceList = new ArrayList<>();

            // Iterate over each employee
            for (CEmployeesViewModel employee : cEmployeesViewModel) {
                // Filter attendance records for the employee
                List<CHmvAttLogCombinedModel> attendanceDateTimes = hmvAttLogCombinedModels.stream()
                        .filter(log -> log.getAtt_device_emp_code().equals(employee.getOld_employee_code()))
                        .collect(Collectors.toList());

                // For each date, create the attendance record
                for (LocalDate date : dateList) {
                    // Find matching attendance records for the current date
                    List<CHmvAttLogCombinedModel> matchingRecords = attendanceDateTimes.stream()
                            .filter(log -> log.getAtt_date_time().startsWith(date.toString())) // Check if the date matches
                            .collect(Collectors.toList());

                    for (CHmvAttLogCombinedModel record : matchingRecords) {
                        Map<String, Object> employeeAttendanceData = new HashMap<>();
                        employeeAttendanceData.put("employee_code", employee.getEmployee_code());
                        employeeAttendanceData.put("employee_name", employee.getEmployee_name());
                        employeeAttendanceData.put("department", employee.getDepartment_name());
                        employeeAttendanceData.put("sub_department", employee.getSub_department_name());
                        employeeAttendanceData.put("att_date_time", record.getAtt_date_time());
                        employeeAttendanceData.put("old_employee_code", employee.getOld_employee_code());
                        // Add this attendance record to the list
                        employeeAttendanceList.add(employeeAttendanceData);
                    }
                }
            }


            // Populate the response with the combined data
            response.put("data", employeeAttendanceList);
            response.put("status", "success");
            // If you want to include the original attendance data as well
            response.put("EMP_PUNCHING_DATA", hmvAttLogCombinedModels);
            response.put("status", "success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/FnAddUpdateMissPunchRecord")
    public Map<String, Object> FnAddUpdateMissPunchRecord(@RequestParam("MisspunchCorrection") JSONObject jsonObject) {
        Map<String, Object> responce = iHtShiftManagementService.FnAddUpdateMissPunchRecord(jsonObject);
        return responce;

    }

    @PostMapping("/FnUpdateLockStatus")
    public Map<String, Object> FnUpdateLockStatus(@RequestBody Map<String, Object> jsonObject) {
        // Delegate the call to the service layer
        Map<String, Object> response = iHtShiftManagementService.FnUpdateLockStatus(jsonObject);
        return response;
    }


    //@Scheduled(cron = "0 59 23 L * ?") // Last day of month, 11:59 PM
//@Transactional
//    public void resetLeaveBalance() {
//        LocalDate today = LocalDate.now();
//        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
//        LocalDate startRange = endOfMonth.minusDays(6); // Last 7 days = 6 days before + end day
//
//
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    String formattedStartRange = startRange.atStartOfDay().format(formatter);
//    String formattedEndRange = endOfMonth.atTime(23, 59, 59).format(formatter);
//
//        List<CHmLeaveBalanceModel> compOffBalances = iHmLeavesBalanceRepository.findByLeaveTypeId(4);
//
//        for (CHmLeaveBalanceModel balance : compOffBalances) {
//            String employeeCode = balance.getEmployee_code();
//
//            long count = iHmCompOffDetailsRepository.countEntriesInRange(
//                    employeeCode,
//                    formattedStartRange,
//                    formattedEndRange
//            );
//
//            balance.setClosing_balance((int) count); // set how many entries found (or 0)
//        }
//
//        iHmLeavesBalanceRepository.saveAll(compOffBalances);
//        System.out.println("Comp-off balances updated for leave_type_id = 4.");
//    }

    //********** Scheduler for Compoff leave balance only one month period for the compoff balance

    @Scheduled(cron = "0 5 0 * * ?") // Every day at 12:05 AM
    @Transactional
    public void resetLeaveBalance() {
        List<CHmLeaveBalanceModel> compOffBalances = iHmLeavesBalanceRepository.findByLeaveTypeId(4);
        LocalDate today = LocalDate.now();
        String currentYear = String.valueOf(today.getYear());

        for (CHmLeaveBalanceModel balance : compOffBalances) {
            String employeeCode = balance.getEmployee_code();

            List<CHmCompOffDetailsModel> validCompOffs =
                    iHmCompOffDetailsRepository.findValidCompOffs(employeeCode);

            balance.setClosing_balance(validCompOffs.size());
            balance.setFinancial_year(currentYear);
        }

        iHmLeavesBalanceRepository.saveAll(compOffBalances);
        System.out.println("Daily comp-off balance updated.");
    }


    //********** Scheduler for creadit leave balance to every after three month

    // Manual Credit Trigger
    @PostMapping("/manual-credit")
    public ResponseEntity<String> creditQuarterlyLeavesManually(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        creditQuarterlyLeaves(startDate);
        return ResponseEntity.ok("Leave Balance Creadited for: " + startDate);
    }


    // Scheduled Credit Trigger (Already Defined with @Scheduled)
    @Scheduled(cron = "0 15 0 1 1,4,7,10 *") //12:15 AM
    public void creditQuarterlyLeavesScheduled() {
        creditQuarterlyLeaves(LocalDate.now());  // Automatically pick today's date
    }

    public void creditQuarterlyLeaves(LocalDate passedDate) {
        LocalDate today = (passedDate != null) ? passedDate : LocalDate.now(); // Use passed date if provided
        String currentYear = String.valueOf(today.getYear());
        int leaveTypeId;
        String leaveTypeName;
        final double LEAVE_CREDIT = 3.0;

        Map<Integer, String> leaveMap = Map.of(1, "PL", 4, "CL", 7, "PL", 10, "CL");
        leaveTypeName = leaveMap.get(today.getMonthValue());

        if (leaveTypeName == null) {
            System.out.println("No leave credited. Invalid month: " + today.getMonthValue());
            return;
        }

        leaveTypeId = "PL".equals(leaveTypeName) ? 1 : 2;
        String effectiveDate = passedDate.format(DateTimeFormatter.ISO_DATE);

        // ✅ Define the effective date (today's date)
//        boolean alreadyCredited = iHmLeavesBalanceRepository.countByLeaveTypeIdAndEffectiveDate(leaveTypeId, effectiveDate,currentYear) > 0;
//        if (alreadyCredited) {
//            System.out.println("Skip: Leave already credited for " + leaveTypeName + " on " + today);
//            return;
//        }

        List<CHmLeaveBalanceModel> balances = iHmLeavesBalanceRepository.findByLeaveTypeIdPLandCL(leaveTypeId);
        List<CHmLeaveBalanceModel> updatedBalances = new ArrayList<>();

        for (CHmLeaveBalanceModel balance : balances) {
            Double currentClosingBalance = balance.getClosing_balance();
            Double currentEarningBalance = balance.getLeaves_earned();

            if ("2025".equals(currentYear) && currentEarningBalance >= 6.0) {
                continue; // Skip if already earned 6 or more
            }

            String employeeCode = balance.getEmployee_code(); // or use the correct identifier
            Long count = iHmLeavesBalanceRepository.countByLeaveTypeIdAndEffectiveDateAndEmployeeCode(leaveTypeId, effectiveDate, employeeCode, currentYear);
            if (count > 0) {
                continue; // Skip this employee if leave has already been credited
            }
            // Credit the leave
            balance.setClosing_balance(currentClosingBalance + LEAVE_CREDIT);
            balance.setLeaves_earned(currentEarningBalance + LEAVE_CREDIT);
            balance.setOpening_balance(currentClosingBalance + LEAVE_CREDIT);
            balance.setFinancial_year(currentYear);
            balance.setEffective_date(effectiveDate);

            updatedBalances.add(balance);
        }

        iHmLeavesBalanceRepository.saveAll(updatedBalances);
        System.out.println("Credited " + LEAVE_CREDIT + " " + leaveTypeName + " to " + updatedBalances.size() + " employees on " + today);
    }



    @Scheduled(cron = "0 10 0 * * ?") // every day at 12:10 AM
    public void autoDailyAttendance() {
        addUpdateDailyAttendance();
    }

    public void addUpdateDailyAttendance() {

        Map<Integer, Integer> companyBranchMap = new HashMap<>();
        companyBranchMap.put(1, 1);
        companyBranchMap.put(2, 3);
        companyBranchMap.put(3, 4);

        List<String> employeeTypeGroups;

        for (Map.Entry<Integer, Integer> entry : companyBranchMap.entrySet()) {
            Integer companyId = entry.getKey();
            Integer branchId = entry.getValue();
            if (branchId == 4) {
                employeeTypeGroups = Arrays.asList("Staffs");
            } else {
                employeeTypeGroups = Arrays.asList("Staffs", "Workers");
            }
            for (String empType : employeeTypeGroups) {
                JSONObject requestData = new JSONObject();
                requestData.put("company_id", companyId);
                requestData.put("employee_type_group", empType);
                requestData.put("shift_name", "All");
                requestData.put("two_day_shift", "false");
                requestData.put("attendance_date", LocalDate.now().minusDays(1).toString());
                requestData.put("dt_att_To_date", LocalDate.now().minusDays(1).toString());

                // Set fields to null
                requestData.put("department_id", JSONObject.NULL);
                requestData.put("sub_department_id", JSONObject.NULL);
                requestData.put("employee_id", JSONObject.NULL);


                JSONObject responseData = callFnShowEmployeesDetailsAPI(requestData);

                if (responseData != null && responseData.has("content")) {
                    JSONArray contentArray = responseData.getJSONArray("content");

                    JSONObject saveData = new JSONObject();
                    saveData.put("punchingMasterData", requestData);

                    JSONArray cleanedArray = new JSONArray();
                    List<String> allowedKeys = Arrays.asList(
                            "company_id", "company_branch_id", "daily_attendance_id",
                            "employee_id", "employee_code", "employee_type", "machine_employee_code",
                            "old_employee_code", "shift_scheduled", "shift_present", "job_type_id", "job_type",
                            "attendance_date", "attendance_month", "attendance_year", "in_time", "out_time",
                            "working_minutes", "late_mark_flag", "early_go_flag", "gate_pass_flag",
                            "week_off_present_flag", "night_shift_present_flag", "created_by", "modified_by",
                            "att_date_time", "present_flag", "department_id", "sub_department_id", "weeklyoff_name",
                            "attendance_flag", "halfday_hours", "fullday_hours", "grace_early_time", "grace_late_time",
                            "shift_start_end_time", "two_day_shift", "attendance_exclude_flag",
                            "job_type_code_id", "job_type_code_short_name", "already_added_attendance",
                            "already_added_job_type_code_id", "already_added_job_type_code_short_name",
                            "already_added_worked_hours", "remark", "is_delete"
                    );

                    Map<String, String> keyMapping = new HashMap<>();
                    keyMapping.put("max_att_time", "out_time");
                    keyMapping.put("min_att_time", "in_time");
                    keyMapping.put("worked_hours", "working_minutes");

                    for (int i = 0; i < contentArray.length(); i++) {
                        JSONObject original = contentArray.getJSONObject(i);
                        JSONObject filtered = new JSONObject();

                        for (String key : allowedKeys) {
                            // Check if there's a mapping
                            String sourceKey = key;

                            // Reverse-lookup: check if any incoming key maps to this expected key
                            for (Map.Entry<String, String> entrys : keyMapping.entrySet()) {
                                if (entrys.getValue().equals(key) && original.has(entrys.getKey())) {
                                    sourceKey = entrys.getKey();
                                    break;
                                }
                            }

                            if (original.has(sourceKey)) {
                                filtered.put(key, original.get(sourceKey));
                            }
                        }

                        // Set default "N" for missing flags
                        filtered.put("late_mark_flag", original.optString("late_mark_flag", "N"));
                        filtered.put("early_go_flag", original.optString("early_go_flag", "N"));
                        filtered.put("gate_pass_flag", original.optString("gate_pass_flag", "N"));
                        filtered.put("week_off_present_flag", original.optString("week_off_present_flag", "N"));
                        filtered.put("night_shift_present_flag", original.optString("night_shift_present_flag", "N"));
                        filtered.put("attendance_flag", original.optString("attendance_flag", "A"));


                        // Add branch ID
                        filtered.put("company_branch_id", branchId);
                        filtered.put("company_id", companyId);

                        cleanedArray.put(filtered);
                    }

                    saveData.put("punchingDetailsData", cleanedArray);
                    callFnAddUpdateAttendanceDetails(saveData);
                }
            }
        }
    }


    private JSONObject callFnShowEmployeesDetailsAPI(JSONObject requestData) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            CEmployeeWiseAttendanceRequest attendanceRequest = mapper.readValue(requestData.toString(), CEmployeeWiseAttendanceRequest.class);

            Map<String, Object> responseMap = FnShowEmployeeDetails(attendanceRequest);
            System.out.println(responseMap);
            return new JSONObject(responseMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();  // Handle exception properly
            return null;
        }
    }

    private void callFnAddUpdateAttendanceDetails(JSONObject saveData) {
        // Direct call to your method that expects JSONObject
        Map<String, Object> response = FnAddUpdateAttendanceDetails(saveData);

        // Handle the response (optional)
        System.out.println(response);
    }

}