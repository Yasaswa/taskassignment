package com.erp.Product_Material_Measure_Parameters.Repository;

import com.erp.Product_Material_Measure_Parameters.Model.CProductMaterialMeasureParametersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IProductMaterialMeasureParametersRepository extends JpaRepository<CProductMaterialMeasureParametersModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_material_measure_parameter set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where product_material_measure_parameter_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int product_material_measure_parameter_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_material_measure_parameter WHERE (product_material_measure_parameter_name = ?1 or (?2 IS NOT NULL AND product_material_measure_parameter_short_name = ?2)) and company_id = ?3 order by product_material_measure_parameter_id Desc limit 1")
	CProductMaterialMeasureParametersModel getCheck(String product_material_measure_parameter_name,
	                                                String product_material_measure_parameter_short_name, Integer company_id);


}
