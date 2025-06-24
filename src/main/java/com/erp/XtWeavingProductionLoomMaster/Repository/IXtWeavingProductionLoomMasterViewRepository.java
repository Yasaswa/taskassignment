package com.erp.XtWeavingProductionLoomMaster.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionLoomMaster.Model.CXtWeavingProductionLoomMasterViewModel;


public interface IXtWeavingProductionLoomMasterViewRepository extends JpaRepository<CXtWeavingProductionLoomMasterViewModel, Integer> {

	
	@Query(value = "FROM CXtWeavingProductionLoomMasterViewModel model WHERE model.is_delete = 0 and model.weaving_production_loom_master_id = ?1 and model.company_id = ?2")
	CXtWeavingProductionLoomMasterViewModel FnShowWVProductionLoomMasterRecorForUpdate(int weaving_production_loom_master_id, int company_id);

	

}
