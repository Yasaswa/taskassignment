package com.erp.SmProductRmStockDetails.Repository;

import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductStockTrackingRepository extends JpaRepository<CSmProductStockTracking, Integer> {
	@Query(value = "FROM CSmProductStockTracking model where model.is_delete= false and model.product_material_id IN ?1 AND model.company_id = ?2")
	List<CSmProductStockTracking> FnGetAllProductTrackingDetails(List<String> distinctMaterialIds, int company_id);

	@Query(value = "FROM CSmProductStockTracking model where model.is_delete= false and model.product_material_id IN ?1 ")
	List<CSmProductStockTracking> FnGetAllProductTrackingDetailsRawMaterials(List<String> distinctMaterialIds);
}