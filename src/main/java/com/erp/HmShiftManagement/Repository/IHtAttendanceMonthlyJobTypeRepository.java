package com.erp.HmShiftManagement.Repository;


import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyJobTypeModel;
import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IHtAttendanceMonthlyJobTypeRepository extends JpaRepository<CHtAttendanceMonthlyJobTypeModel, Integer> {

    @Query(value = "FROM CHtAttendanceMonthlyJobTypeModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
    List<CHtAttendanceMonthlyJobTypeModel> getEmployeesRecordsByMonth(List<String> getEmployeeCodes, int month, int year);

    @Query(value = "FROM CHtAttendanceMonthlyJobTypeModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
    List<CHtAttendanceMonthlyJobTypeModel> getJobTypeWiseMonthlyRecordsByMonth(List<String> getEmployeeCodes, int month, int year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE CHtAttendanceMonthlyJobTypeModel model SET model.is_delete = true, deleted_on = CURRENT_TIMESTAMP() where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
	void inActiveExistingJobTypeSalary(List<String> getEmployeeCodes, int month, int year);
}
