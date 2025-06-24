package com.erp.SmProductTypeDynamicControls.Repository;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductTypeDynamicControls.Model.CSmProductTypeDynamicControlsModel;


public interface ISmProductTypeDynamicControlsRepository extends JpaRepository<CSmProductTypeDynamicControlsModel, Integer> {


	@Query(value = "FROM CSmProductTypeDynamicControlsModel model where model.is_delete = 0 and model.control_name = ?1 and model.company_id = ?2 and model.product_type_id = ?3")
	CSmProductTypeDynamicControlsModel checkIfNameExist(String control_name,int company_id , int product_type_id );

	@Modifying
	@Transactional
	@Query(value = "update CSmProductTypeDynamicControlsModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_type_dynamic_controls_id = ?1")
	void FnDeleteProductTypeDynamicControlsRecord(int product_type_dynamic_controls_id, String deleted_by);

	@Query(value = "SELECT * FROM smv_product_dynamic_parameters model where model.is_delete = 0 and model.product_id = ?1", nativeQuery = true)
	List<Map<String, Object>> FnGetProductBasedProperties(String product_id);

	@Query(value = "SELECT * FROM smv_product_dynamic_parameters model WHERE model.is_delete = 0 AND model.product_id IN ?1", nativeQuery = true)
	List<Map<String, Object>> FnGetProductBasedPropertiesForDispatch(List<String> product_ids);

}
