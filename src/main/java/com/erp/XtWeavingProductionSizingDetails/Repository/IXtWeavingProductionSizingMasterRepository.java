package com.erp.XtWeavingProductionSizingDetails.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionSizingDetails.Model.CXtWeavingProductionSizingMasterModel;

import java.util.ArrayList;
import java.util.Map;

public interface IXtWeavingProductionSizingMasterRepository extends JpaRepository<CXtWeavingProductionSizingMasterModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionSizingMasterModel model SET model.is_delete = 1 , model.deleted_by = ?2, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_sizing_master_id = ?1")
	void FnDeleteProductionSizingMasterRecord(int weaving_production_sizing_master_id, String deleted_by);

	
	@Query(value = "FROM CXtWeavingProductionSizingMasterModel model WHERE model.is_delete = 0 and model.weaving_production_sizing_master_id = ?1 and model.company_id = ?2")
	CXtWeavingProductionSizingMasterModel FnShowParticularWVProductionSizingRecordForUpdate(
			int weaving_production_sizing_master_id, int company_id);


	//For sizing master on selecting set no
 @Query(value = "select xwpwd.actual_count ,xwpwd.yarn_count, xwpwd.product_rm_id, xwpwd.weight_per_pkg, xwpwd.creel_ends as warping_creel_ends , xwpwd.length as warping_length, xwpo.job_type, xwpo.set_length , xwpo.product_material_name , xwpo.customer_id , xwpo.t_ends as total_ends, ap.property_name as beam_name from xt_weaving_production_warping_details xwpwd left join xtv_warping_production_order xwpo on xwpo.set_no = xwpwd.weaving_production_set_no left join am_properties ap on ap.property_id  = xwpwd.beam_no and ap.is_delete = 0 where xwpwd.weaving_production_set_no = ?1 and xwpwd.company_id = ?2", nativeQuery = true)
	ArrayList<Map<String, Object>>	FnGetSizingMasterData(String set_no, int company_id);

}
