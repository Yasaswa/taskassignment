package com.erp.Product_Material_Measure_Parameters.Repository;

import com.erp.Product_Material_Measure_Parameters.Model.CProductMaterialMeasureParametersViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductMaterialMeasureParametersViewRepository extends JpaRepository<CProductMaterialMeasureParametersViewModel, Long> {

//	
//	@Query(value = "Select * FROM  smv_product__material_measure_parameter order by product_material_measure_parameter_id Desc", nativeQuery = true)
//	Page<CProductMaterialMeasureParametersViewModel> FnShowAllRecords(Pageable pageable);
//	
//	@Query(value = "Select * FROM  smv_product__material_measure_parameter where is_delete =0  order by product_material_measure_parameter_id Desc", nativeQuery = true)
//	Page<CProductMaterialMeasureParametersViewModel> FnShowAllActiveRecords(Pageable pageable);
//	
//	@Query(value = "Select * FROM  smv_product__material_measure_parameter where is_delete =0 and product_material_measure_parameter_id = ?1", nativeQuery = true)
//	CProductMaterialMeasureParametersViewModel FnShowParticularRecordForUpdate(int product_material_measure_parameter_id);
//	
//
//	@Query(nativeQuery = true, value ="Select * FROM  smv_product__material_measure_parameter where is_delete =0  and product_material_measure_parameter_id = ?1")
//	CProductMaterialMeasureParametersViewModel FnShowParticularRecord(  int product_material_measure_parameter_id);
//
//	@Query(value ="SELECT * FROM smv_product__material_measure_parameter_rpt", nativeQuery = true)
//	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

//Added By Dipti  (Testing DB 1.1)	
	//changed smv_product__material_measure_parameter(1.0)  to smv_product_material_measure_parameter

	@Query(value = "Select * FROM  smv_product_material_measure_parameter order by product_material_measure_parameter_id Desc", nativeQuery = true)
	Page<CProductMaterialMeasureParametersViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_material_measure_parameter where is_delete =0  order by product_material_measure_parameter_id Desc", nativeQuery = true)
	Page<CProductMaterialMeasureParametersViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_material_measure_parameter where is_delete =0 and product_material_measure_parameter_id = ?1", nativeQuery = true)
	CProductMaterialMeasureParametersViewModel FnShowParticularRecordForUpdate(int product_material_measure_parameter_id);


	@Query(nativeQuery = true, value = "Select * FROM  smv_product_material_measure_parameter where is_delete =0  and product_material_measure_parameter_id = ?1")
	CProductMaterialMeasureParametersViewModel FnShowParticularRecord(int product_material_measure_parameter_id);

	@Query(value = "SELECT * FROM smv_product_material_measure_parameter_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

//Added By Dipti  (Testing DB 1.1)	


}
