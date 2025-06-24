package com.erp.XtWeavingProductionOrderMaster.Repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionOrderSummaryViewModel;

public interface IXtWeavingProductionOrderSummaryViewRepository extends JpaRepository<CXtWeavingProductionOrderSummaryViewModel, Integer>{

//	@Query(value = "FROM CXtWeavingProductionOrderSummaryViewModel model where model.is_delete = 0 and model.weaving_production_order_id = ?1 and model.company_id = ?2")
	
	
	@Query(value = "SELECT * FROM xtv_weaving_production_order  where is_delete = 0 and weaving_production_order_id = ?1 and company_id = ?2", nativeQuery = true)
	Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_order_id, int company_id);

	@Query(value = "FROM CXtWeavingProductionOrderSummaryViewModel model WHERE model.company_id = ?1 ORDER BY set_no DESC", nativeQuery = true)
	Map<String, Object> GetLastSetNoWeavingProductionOrder(int company_id);


	
}
