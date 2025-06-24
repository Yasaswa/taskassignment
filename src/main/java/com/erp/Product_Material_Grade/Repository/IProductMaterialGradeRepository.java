package com.erp.Product_Material_Grade.Repository;

import com.erp.Product_Material_Grade.Model.CProductMaterialGradeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductMaterialGradeRepository extends JpaRepository<CProductMaterialGradeModel, Integer> {


//	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_material_grade WHERE (product_material_grade_name = ?1 or product_material_grade_short_name =?2) and company_id = ?3 and is_delete = 0")
//	CProductMaterialGradeModel getCheck(String product_material_grade_name, String product_material_grade_short_name, Integer company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_material_grade set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where product_material_grade_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_material_grade_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_material_grade WHERE (product_material_grade_name = ?1 or (?2 IS NOT NULL AND product_material_grade_short_name = ?2)) and company_id = ?3 order by product_material_grade_id Desc limit 1")
	CProductMaterialGradeModel getCheck(String product_material_grade_name, String product_material_grade_short_name,
	                                    Integer company_id);

}
