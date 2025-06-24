package com.erp.Product_Material_Type.Repository;

import com.erp.Product_Material_Type.Model.CProductMaterialTypeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductMaterialTypeViewRepository extends JpaRepository<CProductMaterialTypeViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_material_type order by product_material_type_id Desc", nativeQuery = true)
	Page<CProductMaterialTypeViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_material_type where is_delete =0  order by product_material_type_id Desc", nativeQuery = true)
	Page<CProductMaterialTypeViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_material_type where is_delete =0 and product_material_type_id = ?1", nativeQuery = true)
	CProductMaterialTypeViewModel FnShowParticularRecordForUpdate(int product_material_type_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_material_type where is_delete =0 and product_material_type_id = ?1")
	CProductMaterialTypeViewModel FnShowParticularRecord(int product_material_type_id);

	@Query(value = "SELECT * FROM smv_product_material_type_rpt", nativeQuery = true)
	Map<String, String> FnShowAllReportRecords();

}
