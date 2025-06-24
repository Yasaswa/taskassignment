package com.erp.HmShiftManagement.Repository;

import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyJobTypeNewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IHtAttendanceMonthlyJobTypeNewRepository extends JpaRepository<CHtAttendanceMonthlyJobTypeNewModel, Integer> {
	@Modifying
	@Transactional
	@Query(value = "UPDATE CHtAttendanceMonthlyJobTypeNewModel model SET model.is_delete = true, deleted_on = CURRENT_TIMESTAMP() where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
	void inActiveExistingJobTypeSalary(List<String> getEmployeeCodes, int month, int year);

	@Query(value = "FROM CHtAttendanceMonthlyJobTypeNewModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
	List<CHtAttendanceMonthlyJobTypeNewModel> getJobTypeWiseMonthlyRecordsByMonth(List<String> employeeCodes, int salaryForMonthNumber, int salaryForYear, int company_id);
}