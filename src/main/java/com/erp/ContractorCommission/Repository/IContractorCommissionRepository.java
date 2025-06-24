package com.erp.ContractorCommission.Repository;

import com.erp.ContractorCommission.Model.CContractorCommissionModel;
import com.erp.JobType.Model.CJobTypeViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IContractorCommissionRepository extends JpaRepository<CContractorCommissionModel, Integer> {


    @Query(nativeQuery = true, value = "Select * FROM  hmv_commission_rate where is_delete =0  and commission_rate_id = ?1")
    CContractorCommissionModel FnShowParticularRecord(int commissionRateId);
    @Modifying
    @Transactional
    @Query(value = "update hm_commission_rate set is_delete = 1, deleted_by = ?2,deleted_on = CURRENT_TIMESTAMP() where commission_rate_id=?1", nativeQuery = true)
    void FnDeleteRecord(int commissionRateId, String deletedBy);
}
