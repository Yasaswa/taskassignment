package com.erp.SmProductPr.Repository;

import com.erp.SmProductPr.Model.CSmProductPrCustomerStockDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductPrCustomerStockDetailsModelRepository extends JpaRepository<CSmProductPrCustomerStockDetailsModel, Integer> {
	@Query(value = "Select * FROM sm_product_pr_stock_details_customer where is_delete=0 and day_closed = 0 and product_pr_id IN ?1 AND company_id = ?2", nativeQuery = true)
	List<CSmProductPrCustomerStockDetailsModel> FnGetAllProductPrCustomerStockDetails(List<String> distinctPrMaterialIds, int company_id);

	@Query(value = "FROM CSmProductPrCustomerStockDetailsModel model where model.is_delete=0 and model.day_closed = false")
	List<CSmProductPrCustomerStockDetailsModel> getAllPrCustomerStockDetail();
}