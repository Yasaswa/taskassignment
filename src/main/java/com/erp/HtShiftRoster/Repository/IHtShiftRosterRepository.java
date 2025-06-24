package com.erp.HtShiftRoster.Repository;

import com.erp.HtShiftRoster.Model.CHtShiftRosterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IHtShiftRosterRepository extends JpaRepository<CHtShiftRosterModel, Integer> {


    @Modifying
    @Transactional
    @Query(value = "update ht_shift_roster set is_delete = 1, deleted_by = ?4, deleted_on = CURRENT_TIMESTAMP() where employee_code = ?1 and attendance_month = ?2 and attendance_year = ?3  and is_delete = 0", nativeQuery = true)
    void updateShiftRosterRecord(String employeeCode, Integer attendanceMonth, Integer attendanceYear, String createdBy);

//    @Query(value = "FROM CHtShiftRosterModel model where model.employee_code = ?1 and attendance_month = ?2 and attendance_year = ?3 and is_delete = 0")
//    Optional<CHtShiftRosterModel> findByEmployee_code(String employeeCode, Integer attendanceMonth, Integer attendanceYear);

    @Query(value = "FROM CHtShiftRosterModel model where model.employee_code IN ?1 and model.attendance_month IN ?2 and model.attendance_year IN ?3 and model.is_delete = false")
    List<CHtShiftRosterModel> FnGetShiftRosterDetails(List<String> employeeCodes, List<Integer> month, List<Integer> year);

    @Query(value = "SELECT r.employee_id, r.employee_code, e.employee_name, e.department_name, e.department_id, e.sub_department_id, e.old_employee_code " +
            "FROM ht_shift_roster r " +
            "LEFT JOIN cmv_employee e ON e.employee_id = r.employee_id " +
            "WHERE e.employee_type_group = :employeeType " +
            "   AND e.company_id = :company_id " +
            "   AND r.is_delete = 0 " +
            "   AND r.attendance_month = :attendanceMonth " +
            "   AND r.attendance_year = :attendanceYear " +
            "   AND (r.shift_id31 = :fromShift OR r.shift_id30 = :fromShift OR r.shift_id29 = :fromShift OR r.shift_id28 = :fromShift)",
            nativeQuery = true)
    List<Object[]> findShiftRosterEmployeeDetails(
            @Param("employeeType") String employeeType,
            @Param("attendanceMonth") int attendanceMonth,
            @Param("attendanceYear") int attendanceYear,
            @Param("fromShift") int fromShift,
            @Param("company_id") int company_id);


    @Query(" SELECT e.employee_id, e.employee_code, e.employee_name ,e.department_name,e.department_id,e.sub_department_id,e.old_employee_code" +
            " FROM CEmployeesViewModel e " +
            "WHERE e.employee_type_group = :employeeType " +
            "AND e.company_id = :company_id " +
            "AND e.shift_id = :shiftId " +
            "AND e.is_delete = 0 " +
            "AND (:departmentId IS NULL OR e.department_id = :departmentId) " +
            "AND (:subDepartmentId IS NULL OR e.sub_department_id = :subDepartmentId)")
    List<Object[]> findShiftRosterEmployeeData(
            @Param("employeeType") String employeeType,
            @Param("departmentId") Integer departmentId,  // Changed to Integer for nullability
            @Param("subDepartmentId") Integer subDepartmentId,  // Changed to Integer for nullability
            @Param("shiftId") int shiftId,
            @Param("company_id") int company_id);

    @Query(value = "FROM CHtShiftRosterModel model where model.employee_code = ?1 and attendance_month = ?2 and attendance_year = ?3 and is_delete = 0")
    List<CHtShiftRosterModel> findByEmployee_code(String employeeCode, Integer attendanceMonth, Integer attendanceYear);
//    @Query(value = "SELECT el.two_day_shift,el.shift_name FROM CEmployeesViewModel el WHERE el.employee_code = ?1)")
//       List<CEmployeesViewModel> getEmployeeShiftDetails(String employeeCode);
}
