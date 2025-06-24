package com.erp.SaudaSheetMaster.Repository;

import com.erp.PtGoodsReturnsDetails.Model.CPtGoodsReturnsMasterViewModel;
import com.erp.SaudaSheetMaster.Model.CMtSaudaSheetMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMtSaudaSheetMasterViewRepository extends JpaRepository<CMtSaudaSheetMasterViewModel,Integer> {

    @Query(value = "FROM CMtSaudaSheetMasterViewModel model where model.is_delete = 0 and model.sauda_sheet_master_id = ?1 and model.company_id = ?2")
    CMtSaudaSheetMasterViewModel FnShowParticularRecordForUpdate(int saudaSheetMasterId, int companyId);
}
