package com.erp.HmCompOffDetails.Repository;

import com.erp.HmCompOffDetails.Model.CHmCompOffDetailsModel;
import com.erp.HmCompOffDetails.Model.CHmCompOffDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface IHmCompOffDetailsRepository extends JpaRepository<CHmCompOffDetailsModel, Integer> {
    @Query(value = "FROM CHmCompOffDetailsViewModel model where model.is_delete =0 and model.company_id = ?1 and model.comp_off_intimation_details_id = ?2 ")
    CHmCompOffDetailsViewModel FnShowParticularRecordForUpdate(int companyId, int comp_off_intimation_details_id);

    @Query(value = "FROM CHmCompOffDetailsModel model where model.is_delete =0 and model.company_id = ?1 and model.punch_code = ?2 and model.att_date_time = ?3")
    List<CHmCompOffDetailsModel> findByPunchCodeAndEmployeeId(int company_id, String punchingCode, String attendanceDate);


    @Modifying
    @Transactional
    @Query(value = "update ht_comp_off_intimation_details set is_delete = 1, deleted_by = ?4,deleted_on = CURRENT_TIMESTAMP() where company_id = ?2 and punch_code=?1 and att_date_time=?3 and status='pending'", nativeQuery = true)
    void FnDeleteRecord(String punch_code, int company_id, String att_date_time, String deletedBy);

    @Modifying
    @Transactional
    @Query(value = "update ht_comp_off_intimation_details set approval_remark = ?4, status = ?5 where company_id = ?3 and employee_code IN ?1 and att_date_time=?2", nativeQuery = true)
    void updateApprovalStatus(List<String> employeeCodes, String attendanceDate, int company_id, String approval_remark, String status);

//    @Query("SELECT COUNT(d) FROM CHmCompOffDetailsModel d " +
//            "WHERE d.employee_code = :employeeCode " +
//            "AND d.status = 'Approved' " +
//            "AND d.att_date_time BETWEEN :startDate AND :endDate")
//    long countEntriesInRange(@Param("employeeCode") String employeeCode,
//                             @Param("startDate") String startDate,
//                             @Param("endDate") String endDate);


    @Query(value = "SELECT * FROM ht_comp_off_intimation_details " +
            "WHERE employee_code = :employeeCode " +
            "AND status = 'Approved' " +
            "AND comp_holiday_type = 'C' " +
            "AND DATE(DATE_ADD(att_date_time, INTERVAL 30 DAY)) >= CURDATE()",
            nativeQuery = true)
    List<CHmCompOffDetailsModel> findValidCompOffs(@Param("employeeCode") String employeeCode);

}
