package com.erp.XtWarpingProductionOrder.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtWarpingProductionOrder.Model.CXtvWarpingProductionOrderStockDetailsModel;

import java.util.List;


public interface IXtvWarpingProductionOrderStockDetailsRepository extends JpaRepository<CXtvWarpingProductionOrderStockDetailsModel, Integer> {

	@Query(value = "FROM CXtvWarpingProductionOrderStockDetailsModel model where model.is_delete =0 and model.warping_production_order_id = ?1 and model.company_id = ?2")
	List<CXtvWarpingProductionOrderStockDetailsModel> FnShowParticularRecordForUpdate(int warping_production_order_id, int company_id);


}
