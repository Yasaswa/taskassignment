package com.erp.SmProductRmStockDetails.Repository;


import com.erp.SmProductRmStockDetails.Model.CSmProductRmCustomerStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductRmStockSummaryRepository extends JpaRepository<CSmProductRmStockSummaryModel, Integer> {

	@Query(value = "FROM CSmProductRmStockSummaryModel model where model.is_delete=0 and model.company_id = ?1 AND model.product_rm_id IN ?2")
	List<CSmProductRmStockSummaryModel> FnGetProductRmStockSummary(int company_id, List<String> distinctMaterialIds);

	@Query(value = "FROM CSmProductRmStockSummaryModel model where model.is_delete = false  ")
	List<CSmProductRmStockSummaryModel> getAllStockMaterials();

	@Query(value = "FROM CSmProductRmStockSummaryModel model WHERE model.product_rm_id IN ?1 and company_id = ?2 and model.is_delete = false")
	List<CSmProductRmStockSummaryModel> FnGetAllProductRmStockSummary(List<String> distinctMaterialIds, int company_id);

	@Query(value = "Select * FROM sm_product_rm_stock_summary_customer WHERE product_rm_id IN ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
	List<CSmProductRmCustomerStockSummaryModel> FnGetAllProductRmCustomerStockSummary(List<String> materialIds, int company_id);

//	@Query(value = "FROM CSmProductRmStockSummaryModel model WHERE model.product_rm_id IN ?1  and model.is_delete = false ")
//	List<CSmProductRmStockSummaryModel> FnGetAllProductRmStockSummaryRawMaterials(List<String> distinctMaterialIds);

	@Transactional
	@Modifying
	@Query("UPDATE CSmProductRmStockSummaryModel model SET model.is_delete = 1, model.deleted_by = ?2 WHERE model.product_rm_id = ?1")
	void deleteRmStockSummary(String product_rm_id, String deleted_by);

//	@Modifying
//	@Transactional
//	@Query(value = "UPDATE CSmProductRmStockSummaryModel model SET day_closed = TRUE where model.is_delete=0")
//	void FnUpdateStockDetailsDayClosed();


	@Modifying
	@Transactional
	@Query(value = "UPDATE CSmProductRmStockSummaryModel model SET material_rate = ?1 where  model.is_delete=0 and model.product_rm_id = ?2 and model.company_id = ?3")
	void FnUpdatedMaterialAverageRate(double materialRate, String productMaterialId, Integer companyId);


}
