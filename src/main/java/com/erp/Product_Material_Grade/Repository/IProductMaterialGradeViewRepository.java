package com.erp.Product_Material_Grade.Repository;

import com.erp.Product_Material_Grade.Model.CProductMaterialGradeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface IProductMaterialGradeViewRepository extends JpaRepository<CProductMaterialGradeViewModel, Long> {


	@Query(value = "Select * FROM  smv_product_material_grade order by product_material_grade_id Desc", nativeQuery = true)
	Page<CProductMaterialGradeViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_material_grade where is_delete =0  order by product_material_grade_id Desc", nativeQuery = true)
	Page<CProductMaterialGradeViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_material_grade where is_delete =0 and product_material_grade_id = ?1", nativeQuery = true)
	CProductMaterialGradeViewModel FnShowParticularRecordForUpdate(int product_material_grade_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_material_grade where is_delete =0 and product_material_grade_id = ?1")
	CProductMaterialGradeViewModel FnShowParticularRecord(int product_material_grade_id);

	@Query(value = "SELECT * FROM smv_product_material_grade_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
