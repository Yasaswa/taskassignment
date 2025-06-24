package com.erp.HtShiftRosterNew.Repository;

import com.erp.HtShiftRosterNew.Model.CHtShiftRosterNewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface IHtShiftRosterNewRepository extends JpaRepository<CHtShiftRosterNewModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update ht_shift_roster_new set is_delete = 1, deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where employee_code = ?1 and attendance_month = ?2 and attendance_year = ?3  and is_delete = 0", nativeQuery = true)
    void updateShiftRosterRecord(String employeeCode, Integer attendanceMonth, Integer attendanceYear, String createdBy);

    @Query(value = "FROM CHtShiftRosterNewModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
    List<CHtShiftRosterNewModel> FnGetShiftRosterDetails(List<String> employeeCodes, List<Integer> month, List<Integer> year);

    @Query(value = "SELECT r.employee_id, r.employee_code, e.employee_name, e.department_name, e.department_id, e.sub_department_id, e.old_employee_code " +
            "FROM ht_shift_roster_new r " +
            "LEFT JOIN cmv_employee e ON e.employee_id = r.employee_id " +
            "WHERE e.employee_type_group = :employeeType " +
            "   AND r.is_delete = 0 " +
            "   AND r.attendance_month = :attendanceMonth " +
            "   AND r.attendance_year = :attendanceYear " +
            "   AND r.company_id = :company_id " +
            "   AND (r.shift_id31 = :fromShift OR r.shift_id30 = :fromShift OR r.shift_id29 = :fromShift OR r.shift_id28 = :fromShift)",
            nativeQuery = true)
    List<Object[]> findShiftRosterEmployeeDetails(
            @Param("employeeType") String employeeType,
            @Param("attendanceMonth") int attendanceMonth,
            @Param("attendanceYear") int attendanceYear,
            @Param("fromShift") int fromShift,
            @Param("company_id") int company_id);


    @Query(" SELECT e.employee_id, e.employee_code, e.employee_name ,e.department_name,e.department_id,e.sub_department_id, e.old_employee_code " +
            " FROM CEmployeesViewModel e " +
            "WHERE e.employee_type_group = :employeeType " +
            "AND e.current_shift_id = :currentShiftId " +
            "AND e.is_delete = 0 " +
            "AND e.company_id = :company_id " +
            "AND (:departmentId IS NULL OR e.department_id = :departmentId) " +
            "AND (:subDepartmentId IS NULL OR e.sub_department_id = :subDepartmentId)")
    List<Object[]> findShiftRosterEmployeeData(
            @Param("employeeType") String employeeType,
            @Param("departmentId") Integer departmentId,  // Changed to Integer for nullability
            @Param("subDepartmentId") Integer subDepartmentId,  // Changed to Integer for nullability
            @Param("currentShiftId") int currentShiftId,
            @Param("company_id") int company_id);


    @Query(value = "FROM CHtShiftRosterNewModel model where model.employee_code = ?1 and attendance_month = ?2 and attendance_year = ?3 and is_delete = 0")
    List<CHtShiftRosterNewModel> findByEmployee_code(String employeeCode, Integer attendanceMonth, Integer attendanceYear);


    @Query(value = "FROM CHtShiftRosterNewModel model where model.employee_code IN ?1 and model.attendance_month = ?2 and model.attendance_year = ?3 and model.is_delete = false")
    List<CHtShiftRosterNewModel> FnGetShiftRosterNewDetails(List<String> employeeCodes, int month, int year);
}
