package com.erp.XtWarpingProductionOrder.Repository;

import com.erp.XtWarpingProductionOrder.Model.CxtWarpingProductionOrderViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IXtWarpingProductionOrderViewModelRepository extends JpaRepository<CxtWarpingProductionOrderViewModel, Integer> {

	@Query(value = "FROM CxtWarpingProductionOrderViewModel model where model.is_delete =0 and model.warping_production_order_id = ?1 and model.company_id = ?2")
	CxtWarpingProductionOrderViewModel FnShowParticularRecordForUpdate(int warping_production_order_id, int company_id);
}