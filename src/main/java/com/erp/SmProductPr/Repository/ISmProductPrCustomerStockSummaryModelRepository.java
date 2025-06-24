package com.erp.SmProductPr.Repository;

import com.erp.SmProductPr.Model.CSmProductPrCustomerStockSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductPrCustomerStockSummaryModelRepository extends JpaRepository<CSmProductPrCustomerStockSummaryModel, Integer> {
	@Query(value = "Select * FROM sm_product_pr_stock_summary_customer where is_delete=0 and product_pr_id IN ?1 AND company_id = ?2", nativeQuery = true)
	List<CSmProductPrCustomerStockSummaryModel> FnGetAllProductPrCustomerStockSummary(List<String> product_pr_id, int companyId);

	@Query(value = "FROM CSmProductPrCustomerStockSummaryModel model where model.is_delete=0")
	List<CSmProductPrCustomerStockSummaryModel> getAllPrCustomerStockMaterials();
}