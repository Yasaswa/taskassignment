package com.erp.XtWeavingProductionWarpingMaster.Repository;

import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingMasterModel;
import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IXtWeavingProductionWarpingMasterViewRepository extends JpaRepository<CXtWeavingProductionWarpingMasterViewModel, Integer> {

    @Query(value = "FROM CXtWeavingProductionWarpingMasterViewModel model WHERE model.is_delete = 0 and model.weaving_production_warping_master_id = ?1 and model.company_id = ?2")
    CXtWeavingProductionWarpingMasterViewModel FnShowWeavingProductionWarpingMasterRecordForUpdate(
            int weaving_production_warping_master_id, int company_id);
}
