package com.erp.HtAttendaceNew.Repository;


import com.erp.HmShiftManagement.Model.CHtAttendanceDailyModel;
import com.erp.HtAttendaceNew.Model.CHtAttendanceDailyNewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


public interface IHtAttendanceDailyNewRepository extends JpaRepository<CHtAttendanceDailyNewModel, Integer> {
	@Query("FROM CHtAttendanceDailyNewModel model " +
			"WHERE model.employee_code IN ?2 and model.is_delete = false " +
			"AND model.employee_type = ?1 " +
			"AND (model.att_date_time BETWEEN ?3 AND ?4) " +
			"AND model.company_id = ?5")
	List<CHtAttendanceDailyNewModel> getDailyAttForCurrentNdPreviousDay(String employee_type, List<String> getEmployeeCodes, String from_date, String to_date, int companyId);

	@Modifying
	@Transactional
	@Query(value = "update ht_attendance_daily_new set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where employee_code IN ?1 and att_date_time IN ?2 and employee_type = ?3 and company_id = ?4", nativeQuery = true)
	void updateExistingDetailsOfSameDate(List<String> getEmployeeCodes, List<LocalDate> attendance_date, String employee_type_group, int company_id);


	@Query("FROM CHtAttendanceDailyNewModel model WHERE model.is_delete = 0 AND model.att_date_time = ?1 AND (model.in_time IS NULL OR model.out_time IS NULL) AND model.employee_code IN ?2")
	List<CHtAttendanceDailyNewModel> getMissPunchDetailsOnSingleDate(String attendanceDate, List<String> getEmployeeCodes);


	@Query("FROM CHtAttendanceDailyNewModel model WHERE model.is_delete = 0 AND model.att_date_time BETWEEN ?1 AND ?2 AND (model.in_time IS NULL OR model.out_time IS NULL) AND model.employee_code IN ?3")
	List<CHtAttendanceDailyNewModel> getMissPunchDetailsWithinDateRange(String attendanceFromDate, String attendanceToDate, List<String> getEmployeeCodes);


}
