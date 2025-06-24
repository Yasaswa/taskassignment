package com.erp.HtShortLeave.Repository;

import com.erp.HmLeavesBalance.Model.CHmLeaveBalanceModel;
import com.erp.HtShortLeave.Model.CHtShortLeaveModel;
import com.erp.HtShortLeave.Model.CHtShortLeaveViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IHtShortLeaveRepository extends JpaRepository<CHtShortLeaveModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update ht_short_leave set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where short_leave_transaction_id=?1", nativeQuery = true)
    void FnDeleteRecord(int short_leave_transaction_id, String deleted_by);

    @Query(value = "FROM CHtShortLeaveViewModel model where model.is_delete =0 and model.short_leave_transaction_id = ?1 and model.company_id = ?2")
    CHtShortLeaveViewModel FnShowParticularRecordForUpdate(int shortLeaveTransactionId, int companyId);

//    @Modifying
//    @Transactional
//    @Query(value = "update ht_short_leave set approval_status = ?3 ,company_id=?4 , deleted_on = CURRENT_TIMESTAMP() where short_leave_date=?2 and employee_code IN ?1", nativeQuery = true)
//    void FnupdateApproveStatus(List<String> employeeCodes, String shortLeaveDate, String approvalStatus, int companyId);

    @Query(value = "FROM CHtShortLeaveModel model where model.is_delete =0 and model.company_id = ?1 and model.punch_code = ?2 and model.short_leave_date = ?3 and model.leave_type = ?4 and model.leave_type = 'Approved'")
    List<CHtShortLeaveModel> findByPunchCodeAndEmployeeId(int companyId, String punchingCode, String shortLeaveDate, String leave_type);


    @Modifying
    @Transactional
    @Query(value = "update ht_short_leave set approval_status = ?3 ,company_id=?4 ,in_time=?5,leave_reason=?8,total_hours=?7,approved_by=?6, deleted_on = CURRENT_TIMESTAMP() where short_leave_date=?2 and leave_type=?9 and employee_code IN ?1", nativeQuery = true)
    void FnupdateApproveStatus(List<String> employeeCodes, String shortLeaveDate, String status, int companyId, String inTime, Integer approvedBy, String totalHours, String leaveReason,String leaveType);
    @Query("FROM CHmLeaveBalanceModel model WHERE model.is_delete = false AND model.leave_type_id = ?1 AND model.employee_code IN (?2) AND model.company_id = ?3")
    List<CHmLeaveBalanceModel> FnGetLeavesApplicationsBalanceDetails(int leaveTypeId, List<String> employeeCodes, int companyId);
}
