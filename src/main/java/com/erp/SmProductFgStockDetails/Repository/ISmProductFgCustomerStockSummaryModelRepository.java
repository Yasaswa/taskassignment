package com.erp.SmProductFgStockDetails.Repository;

import com.erp.SmProductFgStockDetails.Model.CSmProductFgCustomerStockSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductFgCustomerStockSummaryModelRepository extends JpaRepository<CSmProductFgCustomerStockSummaryModel, Integer> {
	@Query(value = "FROM CSmProductFgCustomerStockSummaryModel model  where model.is_delete= false and model.product_fg_id IN ?1 AND model.company_id = ?2")
	List<CSmProductFgCustomerStockSummaryModel> FnGetAllProductFgCustomerStockSummary(List<String> distinctMaterialIds, int company_id);

	@Query(value = "FROM CSmProductFgCustomerStockSummaryModel model where model.is_delete= 0")
	List<CSmProductFgCustomerStockSummaryModel> getAllFgCustomerStockMaterials();
}