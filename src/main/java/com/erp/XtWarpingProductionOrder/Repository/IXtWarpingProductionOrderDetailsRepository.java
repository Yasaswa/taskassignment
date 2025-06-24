package com.erp.XtWarpingProductionOrder.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderDetailsModel;


public interface IXtWarpingProductionOrderDetailsRepository extends JpaRepository<CXtWarpingProductionOrderDetailsModel, Integer> {

//	@Query(value = "FROM CXtWarpingProductionOrderDetailsModel model where model.is_delete =0 and model.warping_order_details_id = ?1 and model.company_id = ?2")
//	CXtWarpingProductionOrderDetailsModel FnShowParticularRecordForUpdate(int warping_order_details_id, int company_id);


}
