package com.erp.SmProductFgStockDetails.Repository;

import com.erp.SmProductFgStockDetails.Model.CSmProductFgStockSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISmProductFgStockSummaryRepository extends JpaRepository<CSmProductFgStockSummaryModel, Integer> {
	@Query(value = "FROM CSmProductFgStockSummaryModel model where model.is_delete= False")
	List<CSmProductFgStockSummaryModel> getAllStockMaterials();

//changes by dipti (because of day_closed not present in table sm_product_fg_stock_summary)
//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE CSmProductFgStockSummaryModel model SET day_closed = TRUE where model.is_delete= false")
//	void FnUpdateStockDayClosed();


//	@Query(value = "FROM CSmProductFgStockSummaryModel model where model.is_delete=0 and model.company_id = ?1  AND model.day_closed = false AND model.product_fg_id IN ?2")
//	List<CSmProductFgStockSummaryModel> FnGetProductFgStockSummary(int companyId, List<String> distinctMaterialIds);


	@Query(value = "FROM CSmProductFgStockSummaryModel model where model.is_delete=0 and model.company_id = ?1 AND model.product_fg_id IN ?2")
	List<CSmProductFgStockSummaryModel> FnGetProductFgStockSummary(int companyId, List<String> distinctMaterialIds);

	@Query(value = "FROM CSmProductFgStockSummaryModel model where model.is_delete=false and model.product_fg_id IN ?1 AND model.company_id = ?2")
	List<CSmProductFgStockSummaryModel> FnGetAllProductFgStockSummary(List<String> distinctMaterialIds, int company_id);


}