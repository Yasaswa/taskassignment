package com.erp.Product_Material_Shape.Repository;

import com.erp.Product_Material_Shape.Model.CProductMaterialShapeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IProductMaterialShapeRepository extends JpaRepository<CProductMaterialShapeModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update sm_product_material_shape set is_delete = 1 , deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_material_shape_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int product_material_shape_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_material_shape WHERE product_material_shape_name = ?1")
	CProductMaterialShapeModel getCheck(String product_material_shape_name);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_material_shape WHERE (product_material_shape_name = ?1 or (?2 IS NOT NULL AND product_material_shape_short_name = ?2)) and company_id = ?3 order by product_material_shape_id Desc limit 1")
	CProductMaterialShapeModel getCheck(String product_material_shape_name, String product_material_shape_short_name,
	                                    Integer company_id);


}
