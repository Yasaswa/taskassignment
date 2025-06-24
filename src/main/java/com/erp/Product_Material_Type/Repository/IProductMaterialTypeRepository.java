package com.erp.Product_Material_Type.Repository;

import com.erp.Product_Material_Type.Model.CProductMaterialTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductMaterialTypeRepository extends JpaRepository<CProductMaterialTypeModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update sm_product_material_type set is_delete = 1 ,deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_material_type_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_material_type_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_material_type WHERE (product_material_type_name = ?1 or (?2 IS NOT NULL AND product_material_type_short_name = ?2)) and company_id = ?3 order by product_material_type_id Desc limit 1")
	CProductMaterialTypeModel getCheck(String product_material_type_name, String product_material_type_short_name,
	                                   Integer company_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_material_type WHERE ( product_material_type_name = ?1 or product_material_type_short_name =?2)and company_id = ?3 and is_delete = 0")
//	CProductMaterialTypeModel getCheck(String product_material_type_name, String product_material_type_short_name, Integer company_id);


}
