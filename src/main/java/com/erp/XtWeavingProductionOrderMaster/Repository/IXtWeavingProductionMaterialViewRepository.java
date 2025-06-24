package com.erp.XtWeavingProductionOrderMaster.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionMaterialViewModel;

public interface IXtWeavingProductionMaterialViewRepository extends JpaRepository<CXtWeavingProductionMaterialViewModel, Integer>{

	@Query(value = "FROM CXtWeavingProductionMaterialViewModel model WHERE model.is_delete = 0 and model.weaving_production_order_id = ?1 and model.company_id = ?2")
	List<CXtWeavingProductionMaterialViewModel> FnWeavingProductionMaterialRecordForUpdate(
			int weaving_production_order_id, int company_id);
	
}
