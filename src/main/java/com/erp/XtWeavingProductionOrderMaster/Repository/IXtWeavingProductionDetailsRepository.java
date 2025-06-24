package com.erp.XtWeavingProductionOrderMaster.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionDetailsModel;

public interface IXtWeavingProductionDetailsRepository extends JpaRepository<CXtWeavingProductionDetailsModel, Integer> {


	@Query(value = "FROM CXtWeavingProductionDetailsModel model WHERE model.is_delete = 0 and set_no IN ?1")
	List<CXtWeavingProductionDetailsModel> getAllProductionDetails(List<String> weavingProductionSetNumbers);


	 @Modifying
	 @Transactional
	 @Query(value = "update CXtWeavingProductionDetailsModel model SET model.sizing_length = ?1,model.sizing_net_weight = ?2, model.creel_waste = ?3, model.size_waste = ?4 ,model.unsize_waste = ?5 WHERE model.set_no = ?6")
	 void updateWeavingProductionSizingOrderDetails(double totalLengthForSet, double totalWeightForSet,
			double totalCreelWasteForSet, double totalSizeWasteForSet, double totalUnsizeWasteForSet,
			String set_no);

	 @Modifying
	 @Transactional
	 @Query(value = "update CXtWeavingProductionDetailsModel model SET model.inspection_product_in_meter = ?1,model.inspection_mtr = ?2, model.product_pick = ?3, model.inspection_weight = ?4 WHERE model.set_no = ?5")
	 void updateWeavingProductionInspectionDetail(double total_product_in_meter, double total_inspection_mtr,
			double total_product_pick, double total_inspection_weight, String set_no);


	 @Modifying
	 @Transactional
	 @Query(value = "update CXtWeavingProductionDetailsModel model SET model.prodcut_1000pick = ?1, model.loom_product_in_meter = ?2 WHERE model.set_no = ?3")
	 void updateWeavingProductionLoomDetails(double prodcut_1000pick, double loom_product_in_meter,
			String set_no);

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionDetailsModel model SET model.warping_length = ?1 , model.warping_weight = ?2 WHERE model.set_no = ?3")
	void updateWeavingProductionWarpingDetails(double total_warping_length, double total_warping_weight, String set_no);
	 

}
