package com.erp.XtWeavingProductionSizingDetails.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingStoppageViewModel;

public interface IXtWeavingProductionSizingStoppageViewRepository extends JpaRepository<CXtWeavingProductionSizingStoppageViewModel, Integer>{
	
	@Query(value = "FROM CXtWeavingProductionSizingStoppageViewModel model WHERE model.is_delete = 0 and model.weaving_production_sizing_master_id = ?1 and model.company_id = ?2")
	List<CXtWeavingProductionSizingStoppageViewModel> FnShowWVProductionSizingStoppageRecordForUpdate(
			int weaving_production_sizing_master_id, int company_id);

}
