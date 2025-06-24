package com.erp.MTaskAssignMaster.Respository;

import com.erp.MTaskAssignMaster.Model.CMTaskMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IMTaskMasterRepository extends JpaRepository<CMTaskMasterModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "update mt_task_assign_table set is_delete = 1, deleted_by= ?2, deleted_on = CURRENT_TIMESTAMP() where task_transaction_id = ?1", nativeQuery = true)
    void FnDeleteRecord(int task_transaction_id, String deleted_by, int company_id);


    @Query("FROM CMTaskMasterModel t WHERE t.task_transaction_id = :task_transaction_id AND t.company_id = :company_id")
    CMTaskMasterModel FnGetTaskModel(@Param("task_transaction_id") Integer task_transaction_id, @Param("company_id") Integer company_id);
}
