package com.erp.XtProductionStandardLossMaster.Repository;

import com.erp.StRawMaterialReturnMaster.Model.CStRawMaterialReturnMasterViewModel;
import com.erp.XtProductionStandardLossMaster.Model.CXtProductionStandardLossMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IXtProductionLossMasterRepository extends JpaRepository<CXtProductionStandardLossMasterModel, Integer> {

    @Query(value = "FROM CXtProductionStandardLossMasterModel model WHERE model.production_standard_losses_id = ?1 AND model.is_delete = 0 AND model.company_id = ?2")
    CXtProductionStandardLossMasterModel FnShowStandardLossDetailsRecord(Integer productionStandardLossesId, Integer companyId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE xt_production_standard_losses SET is_delete = 1, deleted_by = ?3, deleted_on = CURRENT_TIMESTAMP() WHERE production_standard_losses_id = ?1 AND company_id = ?2", nativeQuery = true)
    void FnDeleteRawMaterialReturnMasterRecord(Integer productionStandardLossesId, Integer companyId, String deletedBy);
}
