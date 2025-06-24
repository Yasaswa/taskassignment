package com.erp.XtWeavingProductionWarpingMaster.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionWarpingMaster.Model.CXtWeavingProductionWarpingMaterialViewModel;


public interface IXtWeavingProductionWarpingMaterialViewRepository extends JpaRepository<CXtWeavingProductionWarpingMaterialViewModel, Integer>{

	@Query(value = "FROM CXtWeavingProductionWarpingMaterialViewModel model WHERE model.is_delete = 0 and model.weaving_production_warping_master_id = ?1 and model.company_id = ?2")
//	@Query(value = "SELECT * FROM xtv_weaving_production_warping_material model WHERE model.is_delete = 0 and model.weaving_production_warping_master_id = ?1 and model.company_id = ?2",nativeQuery = true)
	List<CXtWeavingProductionWarpingMaterialViewModel> FnShowWeavingProductionWarpingMaterialRecordForUpdate(
			int weaving_production_warping_master_id, int company_id);


}
