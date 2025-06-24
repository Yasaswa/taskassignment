package com.erp.XtWarpingProductionOrder.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderStockDetailsModel;

import javax.transaction.Transactional;


public interface IXtWarpingProductionOrderStockDetailsRepository extends JpaRepository<CXtWarpingProductionOrderStockDetailsModel, Integer> {

	@Query(value = "FROM CXtWarpingProductionOrderStockDetailsModel model where model.is_delete =0 and model.warping_order_stock_details_id = ?1 and model.company_id = ?2")
	CXtWarpingProductionOrderStockDetailsModel FnShowParticularRecordForUpdate(int warping_order_stock_details_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update xt_warping_production_order_stock_details set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where warping_production_order_id=?1", nativeQuery = true)
	void FnDeleteStockDetailsRecord(int warping_production_order_id, String deleted_by, int company_id);
}
