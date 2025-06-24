package com.erp.HmLeavesBalance.Repository;

import com.erp.HmLeavesBalance.Model.CHmLeaveBalanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface IHmLeavesBalanceRepository extends JpaRepository<CHmLeaveBalanceModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update hm_leaves_balance set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where leaves_balance_id=?1", nativeQuery = true)
    void FnDeleteRecord(int leaves_balance_id, String deleted_by);

    @Query(value = "FROM CHmLeaveBalanceModel model where model.is_delete =0 and model.company_id=?1 and model.leaves_balance_id = ?2")
    CHmLeaveBalanceModel FnShowParticularRecordForUpdate(int company_id, int leaves_balance_id);

    @Modifying
    @Transactional
    @Query(value = "update CHmLeaveBalanceModel model set model.is_delete = true, model.deleted_on = CURRENT_TIMESTAMP() where model.financial_year=?1 and model.employee_type = ?2 and model.company_id = ?3")
    void updateStatus(String financial_year, String employee_type, int company_id);


    @Query(value = "Select * From hm_leaves_balance where is_delete = 0 and  company_id = ?1 and employee_code = ?2", nativeQuery = true)
    List<CHmLeaveBalanceModel> FnShowLeaveBalanceDetails(int company_id, String employee_code);

    @Query(value = "SELECT * FROM hm_leaves_balance  where is_delete =0 and employee_type = ?1 and leave_type_id = ?2 AND employee_code = ?3 ", nativeQuery = true)
    CHmLeaveBalanceModel FnGetLeavesBalanceDetails(String employee_type, int leave_type_id, String employee_code);

    @Query(value = "FROM CHmLeaveBalanceModel model where model.is_delete = false and model.employee_type = ?1 and model.leave_type_id = ?2 AND model.employee_code = ?3 and model.company_id=?4")
    CHmLeaveBalanceModel FnGetLeavesApplicationsDetails(String employee_type, int leave_type_id, String employee_code, int company_id);

    @Query("FROM CHmLeaveBalanceModel model WHERE model.is_delete = false AND model.employee_type = ?1 AND model.leave_type_id = ?2 AND model.employee_code IN (?3) AND model.company_id = ?4")
    List<CHmLeaveBalanceModel> FnGetLeavesApplicationsBalanceDetails(String employeeType, int leaveTypeId, List<String> employeeSameCode, int companyId);

    @Query("SELECT l FROM CHmLeaveBalanceModel l WHERE l.leave_type_id = :leaveTypeId and employee_type = 'Staff' and is_delete = 0")
    List<CHmLeaveBalanceModel> findByLeaveTypeId(@Param("leaveTypeId") Integer leaveTypeId);
    @Query("SELECT l FROM CHmLeaveBalanceModel l WHERE l.leave_type_id = :leaveTypeId and employee_type = 'Staff' and is_delete = 0")
    List<CHmLeaveBalanceModel> findByLeaveTypeIdPLandCL(int leaveTypeId);


//    @Query("SELECT COUNT(l) FROM CHmLeaveBalanceModel l WHERE l.leave_type_id = :leaveTypeId AND l.effective_date = :effectiveDate AND l.employee_type = 'Staff' AND l.is_delete = 0 AND l.financial_year = :currentYear")
//    long countByLeaveTypeIdAndEffectiveDate(
//            @Param("leaveTypeId") int leaveTypeId,
//            @Param("effectiveDate") String effectiveDate,
//            @Param("currentYear") String currentYear
//    );

//    @Query("SELECT COUNT(l) FROM CHmLeaveBalanceModel l WHERE l.leave_type_id = :leaveTypeId AND l.effective_date = :effectiveDate AND l.employee_type = 'Staff'AND l.employee_code= :employeeCode AND l.is_delete = 0 AND l.financial_year = :currentYear")
//    boolean existsByLeaveTypeIdAndEffectiveDateAndEmployeeCode(int leaveTypeId, String effectiveDate, String employeeCode, String currentYear);
@Query("SELECT COUNT(l) FROM CHmLeaveBalanceModel l WHERE l.leave_type_id = :leaveTypeId AND l.effective_date = :effectiveDate AND l.employee_type = 'Staff' AND l.employee_code= :employeeCode AND l.is_delete = 0 AND l.financial_year = :currentYear")
Long countByLeaveTypeIdAndEffectiveDateAndEmployeeCode(int leaveTypeId, String effectiveDate, String employeeCode, String currentYear);

}
