package com.erp.XtWeavingProductionSizingDetails.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingWastageViewModel;

public interface IXtWeavingProductionSizingWastageViewRepository extends JpaRepository<CXtWeavingProductionSizingWastageViewModel, Integer>{

	
	
	@Query(value = "FROM CXtWeavingProductionSizingWastageViewModel model WHERE model.is_delete = 0 and model.weaving_production_sizing_master_id = ?1 and model.company_id = ?2")
	List<CXtWeavingProductionSizingWastageViewModel> FnShowWVProductionSizingStoppageRecordForUpdate(
			int weaving_production_sizing_master_id, int company_id);

}
