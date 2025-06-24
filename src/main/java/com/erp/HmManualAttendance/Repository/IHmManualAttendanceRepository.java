package com.erp.HmManualAttendance.Repository;

import java.util.List;

import javax.transaction.Transactional;

import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.HmManualAttendance.Model.CHmManualAttendanceModel;

public interface IHmManualAttendanceRepository extends JpaRepository<CHmManualAttendanceModel, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update hm_manual_attendance set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where attendance_transaction_id=?1", nativeQuery = true)
    void FnDeleteManualAttendanceRecord(int attendance_transaction_id, String deleted_by);

    @Query(value = "FROM CHmManualAttendanceModel model where model.is_delete = 0 and model.employee_type = ?1 and model.company_id = ?2")
    List<CHmManualAttendanceModel> FnShowManualAttendanceRecord(String employee_type, int company_id);

    @Transactional
    @Modifying
    @Query("update CHmManualAttendanceModel ma set ma.is_delete = true, ma.deleted_on = current_timestamp() where ma.attendance_date IN ?1 and ma.employee_type = ?2 and ma.employee_code IN ?3 and ma.company_id = ?4 and ma.is_delete = false")
    void updateMannualAttendanceRecord(List<String> attendanceDateList, String employee_type,
                                       List<String> employeeCodesList, int company_id);

    @Query(value = "FROM CHmManualAttendanceModel model where model.is_delete = 0 and model.employee_type = ?1 and model.attendance_date IN ?2")
    CHmManualAttendanceModel findByEmployeeTypeAndAttendanceDate(String employee_type, String formattedDateTime);

    @Query(value = "FROM CHmManualAttendanceModel model WHERE (?1 IS NULL OR model.employee_type = ?1) AND (?2 IS NULL OR model.employee_name = ?2) AND (?3 = 0 OR model.department_id = ?3)"
            + " AND model.attendance_date >= ?4 AND model.attendance_date <= ?5 AND model.sub_department_id = ?7 AND model.company_id = ?6")
    List<CHmManualAttendanceModel> findByEmployeeTypeAndDepartmentIdAndAttendanceDateBetween(String employee_type,
                                                                                             String employee_name, int department_id, String from_date, String to_date, int company_id, int sub_department_id);

    @Transactional
    @Modifying
    @Query("update CHtAttendanceDailyModel model set model.is_delete = true, model.deleted_on = current_timestamp() where model.att_date_time IN ?1 and model.employee_type = ?2  and model.employee_code IN ?3 and model.company_id = ?4")
    void updateDailyAttendanceRecord(List<String> attendanceDates, String employee_type, List<String> employeeCodesList, int companyId);

    //	@Query(value = "SELECT employee_id,halfday_hours,fullday_hours,grace_early_time,employee_type_group,employee_type FROM cmv_employee  where employee_code = ?1  and is_delete = 0", nativeQuery = true)
//    int getemployeeId(String employeeCode);
    @Query(value = "SELECT e.employee_id, e.halfday_hours, e.fullday_hours, e.grace_early_time, e.employee_type_group, e.employee_type,e.grace_late_time FROM cmv_employee e WHERE e.employee_code = ?1 AND e.is_delete = 0", nativeQuery = true)
    List<Object[]> findEmployeeDetails(String employeeCode);
}
