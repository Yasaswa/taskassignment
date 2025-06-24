package com.erp.HmShiftManagement.Repository;

import com.erp.HmShiftManagement.Model.CHtAttendanceDailyModel;
import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IHtAttendanceMonthlyRepository extends JpaRepository<CHtAttendanceMonthlyModel, Integer> {

	@Query(value = "FROM CHtAttendanceMonthlyModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.company_id = ?4 and is_delete = false")
	List<CHtAttendanceMonthlyModel> FnGetMonthlyAttendanceByEmplCodes(List<String> employeeCodes, int attendance_month, int attendance_year, int company_id);


	@Query(value = "FROM CHtAttendanceMonthlyModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
	List<CHtAttendanceMonthlyModel> getEmployeesMonthlyRecords(List<String> getEmployeeCodes, int month, int year);

	@Query(value = "FROM CHtAttendanceMonthlyModel model where model.employee_code = ?1 and attendance_month = ?2 and attendance_year = ?3 and is_delete = 0")
	List<CHtAttendanceMonthlyModel> findByEmployee_code(String employeeCode, int month, int year);

	@Query(value = "SELECT shift_present FROM ht_attendance_daily  where employee_code = ?1 and attendance_date = ?2 and attendance_month = ?3 and attendance_year = ?4 and is_delete = 0", nativeQuery = true)
	String getAttendanceShiftName(String employeeCode, int days, int month, int year);

	@Query(value = "SELECT two_day_shift FROM cm_shift WHERE shift_name = ?1 and is_delete = 0", nativeQuery = true)
	boolean gettwo_Day_Shift(String shiftName);


	@Query(value = "FROM CHtAttendanceDailyModel model where model.employee_code = ?1 and attendance_date = ?2 and attendance_month = ?3 and attendance_year = ?4 and is_delete = 0 and company_id = ?5")
	List<CHtAttendanceDailyModel> existingDataForLeaveApplication(String employeeCode, int day, int month, int year, int company_id);

	@Query(value = "FROM CHtAttendanceMonthlyModel model where model.employee_code IN (?1) and attendance_year = ?2 and is_delete = 0 and coff_days != 0")
	List<CHtAttendanceMonthlyModel> findByEmployeeCodeAndAttendanceYear(List<String> employeeSameCode, int year);


	@Transactional
	@Modifying
	@Query("update CHtAttendanceMonthlyModel model set model.coff_days = ?4, model.modified_on = current_timestamp() where model.employee_code = ?1 and model.attendance_process_id = ?2  and model.attendance_month = ?3 and model.company_id = ?5")
	void updateCoffs(String employeeCode, Integer attendanceProcessId, Integer attendanceMonth, double totalRemainingCoffs, int companyId);


	@Query(value = "SELECT employee_type FROM cm_employee  where employee_code = ?1 and employee_id = ?2 and employee_type_group = ?3 and company_id = ?4 and is_delete = 0", nativeQuery = true)
	String FnGetEmployeesByEmplType(String employeeCode, Integer employeeId, String employeeType, int companyId);

}




