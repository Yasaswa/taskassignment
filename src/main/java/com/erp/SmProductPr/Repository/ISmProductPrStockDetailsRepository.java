package com.erp.SmProductPr.Repository;

import com.erp.SmProductPr.Model.CSmProductPrStockDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductPrStockDetailsRepository extends JpaRepository<CSmProductPrStockDetailsModel, Integer> {

	@Query(value = "FROM CSmProductPrStockDetailsModel model where model.is_delete=false and model.day_closed = false and model.product_pr_id IN ?1 AND model.company_id = ?2")
	List<CSmProductPrStockDetailsModel> FnGetAllProductPrStockDetails(List<String> distinctPrMaterialIds, int company_id);

	@Query(value = "FROM CSmProductPrStockDetailsModel model where model.is_delete=false and model.day_closed = false")
	List<CSmProductPrStockDetailsModel> getAllPrStockDetail();

}
