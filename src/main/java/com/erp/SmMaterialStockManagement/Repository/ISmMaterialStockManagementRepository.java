package com.erp.SmMaterialStockManagement.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmMaterialStockManagement.Model.CSmProductMaterialStockNewModel;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ISmMaterialStockManagementRepository extends JpaRepository<CSmProductMaterialStockNewModel, Integer> {
	@Query(value = "SELECT * FROM sm_product_material_stock_new WHERE product_material_id = :prmId AND batch_no = :batchNo AND is_delete = 0 LIMIT 1", nativeQuery = true)
	CSmProductMaterialStockNewModel fnGetdataBasedonPrmIdBatchNo(@Param("prmId") String prmId, @Param("batchNo") String batchNo);


}
