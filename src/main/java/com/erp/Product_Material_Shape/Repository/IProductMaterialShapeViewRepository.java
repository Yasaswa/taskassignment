package com.erp.Product_Material_Shape.Repository;

import com.erp.Product_Material_Shape.Model.CProductMaterialShapeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductMaterialShapeViewRepository extends JpaRepository<CProductMaterialShapeViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_material_shape order by product_material_shape_id Desc", nativeQuery = true)
	Page<CProductMaterialShapeViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_material_shape where is_delete =0  order by product_material_shape_id Desc", nativeQuery = true)
	Page<CProductMaterialShapeViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_material_shape where is_delete =0 and product_material_shape_id = ?1", nativeQuery = true)
	CProductMaterialShapeViewModel FnShowParticularRecordForUpdate(int product_material_shape_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_material_shape where is_delete =0 and company_id = ?1 and product_material_shape_id = ?2")
	CProductMaterialShapeViewModel FnShowParticularRecord(int company_id, int product_material_shape_id);

	@Query(value = "SELECT * FROM smv_product_material_shape_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
