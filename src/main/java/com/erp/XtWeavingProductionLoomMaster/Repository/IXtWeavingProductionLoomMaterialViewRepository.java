package com.erp.XtWeavingProductionLoomMaster.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionLoomMaster.Model.CXtWeavingProductionLoomMaterialViewModel;


public interface IXtWeavingProductionLoomMaterialViewRepository extends JpaRepository<CXtWeavingProductionLoomMaterialViewModel, Integer> {

	
	@Query(value = "FROM CXtWeavingProductionLoomMaterialViewModel model WHERE model.is_delete = 0 and model.weaving_production_loom_master_id = ?1 and model.company_id = ?2")
	List<CXtWeavingProductionLoomMaterialViewModel> FnShowWVProductionLoomMaterialRecorForUpdate(
			int weaving_production_loom_master_id, int company_id);


	

}
