package com.erp.SmProductPr.Repository;

import com.erp.SmProductPr.Model.CSmProductPrStockSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductPrStockSummaryRepository extends JpaRepository<CSmProductPrStockSummaryModel, Integer> {

	@Query(value = "FROM CSmProductPrStockSummaryModel model where model.is_delete=false and model.product_pr_id IN ?1 AND model.company_id = ?2")
	List<CSmProductPrStockSummaryModel> FnGetAllProductPrStockSummary(List<String> distinctPrMaterialIds, int company_id);

	@Query(value = "FROM CSmProductPrStockSummaryModel model where model.is_delete=false")
	List<CSmProductPrStockSummaryModel> getAllStockMaterials();

}
