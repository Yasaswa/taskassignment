package com.erp.HmShiftManagement.Repository;


import com.erp.HmShiftManagement.Model.CHtAttendanceDailyTempModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.HmShiftManagement.Model.CHtAttendanceDailyModel;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


public interface IHtAttendanceDailyRepository extends JpaRepository<CHtAttendanceDailyModel, Integer> {
	@Modifying
	@Transactional
	@Query(value = "update ht_attendance_daily set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where employee_code IN ?1 and att_date_time IN ?2 and employee_type = ?3 and company_id = ?4", nativeQuery = true)
	void updateExistingDetailsOfSameDate(List<String> getEmployeeCodes, List<LocalDate> attendance_date, String employee_type_group, int company_id);

	@Query("FROM CHtAttendanceDailyModel model WHERE model.is_delete = 0 AND model.att_date_time BETWEEN ?1 AND ?2 AND (model.in_time IS NULL OR model.out_time IS NULL) AND model.employee_code IN ?3")
	List<CHtAttendanceDailyModel> getMissPunchDetailsWithinDateRange(String attendanceFromDate, String attendanceToDate, List<String> getEmployeeCodes);

	@Query("FROM CHtAttendanceDailyModel model WHERE model.is_delete = 0 AND model.att_date_time = ?1 AND (model.in_time IS NULL OR model.out_time IS NULL) AND model.employee_code IN ?2")
	List<CHtAttendanceDailyModel> getMissPunchDetailsOnSingleDate(String attendanceDate, List<String> getEmployeeCodes);

	@Query("FROM CHtAttendanceDailyModel model WHERE model.is_delete = 0 AND model.employee_id IN ?1 AND model.attendance_month = ?2  AND model.attendance_year = ?3")
	List<CHtAttendanceDailyModel> getDailyAttDtlsByEmplCodes(List<Integer> getEmployeeIds, int attendance_month, int attendance_year);


	@Query("FROM CHtAttendanceDailyModel model WHERE model.is_delete = 0 AND model.employee_type = ?1 AND model.attendance_date = ?2 AND model.attendance_month = ?3 AND model.attendance_year = ?4 AND model.company_id = ?5")
	List<CHtAttendanceDailyModel> getDailyAttForCurrentNdPreviousDay(String employee_type, int attendance_date, int attendance_month, int attendance_year, int company_id);

//	@Query("FROM CHtAttendanceDailyModel model WHERE model.is_delete = 0 AND model.employee_type = ?1 AND (model.att_date_time = ?2 OR model.att_date_time = DATE_SUB(?2, INTERVAL 1 DAY)) AND model.company_id = ?3")
//	List<CHtAttendanceDailyModel> getDailyAttForCurrentNdPreviousDay(String employeeTypeGroup, String attendanceDate, int companyId);


//	@Query("FROM CHtAttendanceDailyModel model " +
//			"WHERE model.is_delete = 0 " +
//			"AND model.employee_type = ?1 " +
//			"AND (model.att_date_time = ?2 OR model.att_date_time = ?3) " +
//			"AND model.company_id = ?4")
//	List<CHtAttendanceDailyModel> getDailyAttForCurrentNdPreviousDay( String employee_type, String currentDate, String previousDate, int companyId);

	@Query("FROM CHtAttendanceDailyModel model " +
			"WHERE model.employee_type = ?1 " +
			"AND model.employee_code IN ?2 " +
			"AND (model.att_date_time BETWEEN ?3 AND ?4) " +
			"AND model.company_id = ?5 " +
			"AND model.is_delete = false ")
	List<CHtAttendanceDailyModel> getDailyAttForCurrentNdPreviousDay(String employee_type, List<String> getEmployeeCodes, String from_date, String to_date, int companyId);


//	@Query("FROM CHtAttendanceDailyModel model " +
//			"WHERE model.employee_code IN ?2 and model.is_delete = false " +
//			"AND model.employee_type = ?1 " +
//			"AND model.attendance_month = ?3 " +
//			"AND model.company_id = ?4")
//	List<CHtAttendanceDailyModel> getDailyAttForCurrentMonth(String employee_type_group, List<String> getEmployeeCodes, int month, int company_id);

}
