package com.erp.HtAttendaceNew.Repository;

import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyModel;
import com.erp.HtAttendaceNew.Model.CHtAttendanceMonthlyNewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IHtAttendanceMonthlyNewRepository extends JpaRepository<CHtAttendanceMonthlyNewModel, Integer> {

	@Query(value = "FROM CHtAttendanceMonthlyNewModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
	List<CHtAttendanceMonthlyNewModel> getEmployeesMonthlyRecords(List<String> getEmployeeCodes, int month, int year);

	@Query(value = "FROM CHtAttendanceMonthlyNewModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.company_id = ?4 and is_delete = false")
	List<CHtAttendanceMonthlyNewModel> FnGetMonthlyAttendanceByEmplCodes(List<String> employeeCodes, int salaryForMonthNumber, int salaryForYear, int company_id);
}
