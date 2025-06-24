package com.erp.SaudaSheetMaster.Repository;

import com.erp.SaudaSheetMaster.Model.CMtSaudaSheetMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSaudaSheetMasterRepository extends JpaRepository<CMtSaudaSheetMasterModel,Integer> {

    @Modifying
    @Transactional
    @Query(value = "Update CMtSaudaSheetMasterModel model SET model.is_delete = 1, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.sauda_sheet_master_id = ?1 and model.company_id = ?2")
    void FnDeleteSaudaSheetMasterRecord(int saudasheetMasterId, int companyId, String deletedBy);
}
