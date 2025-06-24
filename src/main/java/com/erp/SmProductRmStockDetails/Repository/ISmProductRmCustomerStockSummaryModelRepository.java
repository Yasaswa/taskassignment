package com.erp.SmProductRmStockDetails.Repository;

import com.erp.SmProductRmStockDetails.Model.CSmProductRmCustomerStockSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductRmCustomerStockSummaryModelRepository extends JpaRepository<CSmProductRmCustomerStockSummaryModel, Integer> {

	@Query(value = "Select * FROM sm_product_rm_stock_summary_customer WHERE product_rm_id IN ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
	List<CSmProductRmCustomerStockSummaryModel> FnGetAllProductRmCustomerStockSummary(List<String> distinctMaterialIds, int company_id);

	@Query(value = "FROM CSmProductRmCustomerStockSummaryModel model WHERE model.is_delete = 0")
	List<CSmProductRmCustomerStockSummaryModel> getAllCustomerStockMaterials();
}