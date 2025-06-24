package com.erp.InternalMaterialTransferDetails.Repository;

import com.erp.InternalMaterialTransferDetails.Model.CInternalMaterialTransferMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface IInternalMaterialTransferMasterRepository extends JpaRepository<CInternalMaterialTransferMasterModel,Integer> {

    @Query(value = "FROM CInternalMaterialTransferMasterModel model where model.is_delete =0 and model.inter_material_transfer_master_id = ?1 and model.company_id = ?2")
    CInternalMaterialTransferMasterModel FnShowParticularRecordForUpdate(int material_loan_master_id, int company_id);


    @Query(value = "SELECT * FROM pt_inter_material_transfer_master WHERE inter_material_transfer_no = ?1 and loan_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
    CInternalMaterialTransferMasterModel getExistingRecord(String loanNo, Integer integer, String financialYear, int companyId);

    @Modifying
    @Transactional
    @Query(value = "update pt_inter_material_transfer_master set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?2 where inter_material_transfer_master_id = ?1 and is_delete = 0", nativeQuery = true)
    void deleteInternalMaterial(int inter_material_transfer_master_id, String userName);

    @Query(value = "SELECT * FROM ptv_inter_material_transfer_master  WHERE inter_material_transfer_no = ?1 and financial_year = ?2 and company_id = ?3", nativeQuery = true)
    Map<String, Object> FnShowInternalTransferMasterRecord(String interMaterialTransferNo, String financialYear, int companyId);
}
