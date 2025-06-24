package com.erp.XtWarpingProductionOrder.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderCreelsModel;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;


public interface IXtWarpingProductionOrderCreelsRepository extends JpaRepository<CXtWarpingProductionOrderCreelsModel, Integer> {

	@Query(value = "FROM CXtWarpingProductionOrderCreelsModel model where model.is_delete =0 and model.warping_production_order_id = ?1 and model.company_id = ?2")
	List<CXtWarpingProductionOrderCreelsModel> FnShowParticularRecordForUpdate(int warping_production_order_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update xt_warping_production_order_creels set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where warping_production_order_id=?1", nativeQuery = true)
	void FnDeleteBeamDetailsRecord(int warping_production_order_id, String deleted_by, int company_id);

	@Query(value = "SELECT * FROM xt_warping_production_order_creels WHERE is_delete = 0 AND set_no = ?1", nativeQuery = true)
	List<CXtWarpingProductionOrderCreelsModel> FnGetOrderCreelsData(String setNo);

	@Modifying
	@Query(value = "UPDATE xt_warping_production_order_creels " +
			"SET is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP " +
			"WHERE warping_production_order_id = ?3 " +
			"AND warping_production_order_creels_id NOT IN ?1",
			nativeQuery = true)
	int FnDeleteWarpingCreelData(List<Integer> warpingCreelIds,
								 String username,
								 Integer warpingProductionOrderId);


	@Modifying
	@Query(value = "UPDATE xt_warping_production_order_creels " +
			"SET is_delete = 1, deleted_by = ?1, deleted_on = CURRENT_TIMESTAMP " +
			"WHERE warping_production_order_id = ?2",
			nativeQuery = true)
	int updateAllWarpingCreelData(String username, Integer warpingProductionOrderId);



}
