package com.erp.XtWeavingProductionLoomMaster.Repository;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.XtWeavingProductionLoomMaster.Model.CXtWeavingProductionLoomMasterModel;
import org.springframework.data.repository.query.Param;


public interface IXtWeavingProductionLoomMasterRepository extends JpaRepository<CXtWeavingProductionLoomMasterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update CXtWeavingProductionLoomMasterModel model SET model.is_delete = 1 ,model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.weaving_production_loom_master_id = ?1 and model.company_id = ?2")
	void FnDeleteWeavingProductionLoomMasterRecord(int weaving_production_loom_master_id, int company_id,
			String deleted_by);

//	@Query(value = "SELECT data_import_file = ?1 FROM xt_weaving_production_loom_master WHERE  is_delete = 0",nativeQuery = true)
//	List<String> getExistingImportFileName(String filename);
//	
	@Query(value = "SELECT data_import_file FROM xt_weaving_production_loom_master WHERE data_import_file = ?1 AND is_delete = 0", nativeQuery = true)
	List<String> getExistingImportFileName(String filename);


	//Query for excel sheet import for loom production
	@Query(value = "SELECT spf.product_fg_code, spdp.product_parameter_name, spdp.product_parameter_value, ap.property_name AS weave_design FROM sm_product_fg spf LEFT JOIN sm_product_dynamic_parameters spdp ON spf.product_fg_id = spdp.product_id AND (spdp.product_parameter_name LIKE '%WEAVE%' OR spdp.product_parameter_name LIKE '%EPI 1%' OR spdp.product_parameter_name LIKE '%PPI 1%' OR spdp.product_parameter_name LIKE '%WIDTH%') AND spdp.is_delete = 0 LEFT JOIN am_properties ap ON ap.property_id = spdp.product_parameter_value AND ap.properties_master_name LIKE '%WEAVE%' AND ap.is_delete = 0 WHERE spf.product_fg_code IN :sortNos AND spf.is_delete = 0", nativeQuery = true)
	ArrayList<Map<String, Object>> FnGetProductBasedProperties(@Param("sortNos") List<String> sortNos);


	@Query(value = "SELECT wpo.set_no, wpo.product_material_style as material_style FROM xt_warping_production_order wpo WHERE wpo.set_no IN :setNos and wpo.is_delete = 0", nativeQuery = true)
	ArrayList<Map<String, Object>> FnGetMaterialStyles(@Param("setNos") List<String> setNos);


}
