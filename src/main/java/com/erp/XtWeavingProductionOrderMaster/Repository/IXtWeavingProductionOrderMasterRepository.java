package com.erp.XtWeavingProductionOrderMaster.Repository;

import javax.transaction.Transactional;


import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionOrderMasterModel;

import java.util.Map;


public interface IXtWeavingProductionOrderMasterRepository extends JpaRepository<CXtWeavingProductionOrderMasterModel, Integer> {
	
	@Query(value = "SELECT * FROM xt_warping_production_order ORDER BY set_no DESC LIMIT 1", nativeQuery = true)
	Map<String, Object> GetLastSetNoWeavingProductionOrder();


	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionOrderMasterModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.weaving_production_order_id = ?1")
	void FnDeleteProductionOrderRecord(int weaving_production_order_id, String deleted_by);


}
