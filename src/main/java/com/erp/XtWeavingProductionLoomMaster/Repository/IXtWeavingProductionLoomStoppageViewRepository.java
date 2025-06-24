package com.erp.XtWeavingProductionLoomMaster.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionLoomMaster.Model.CXtWeavingProductionLoomStoppageViewModel;


public interface IXtWeavingProductionLoomStoppageViewRepository extends JpaRepository<CXtWeavingProductionLoomStoppageViewModel, Integer>{

		@Query(value = "FROM CXtWeavingProductionLoomStoppageViewModel model WHERE model.is_delete = 0 and model.weaving_production_loom_master_id = ?1 and model.company_id = ?2")
		List<CXtWeavingProductionLoomStoppageViewModel> FnShowWVProductionLoomStoppageRecorForUpdate(
			int weaving_production_loom_master_id, int company_id);
	
}
