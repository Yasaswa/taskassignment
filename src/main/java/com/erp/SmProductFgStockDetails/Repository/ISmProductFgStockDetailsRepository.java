package com.erp.SmProductFgStockDetails.Repository;

import com.erp.SmProductFgStockDetails.Model.CSmProductFgStockDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ISmProductFgStockDetailsRepository extends JpaRepository<CSmProductFgStockDetailsModel, Integer> {

	@Query(value = "FROM CSmProductFgStockDetailsModel model where model.is_delete=false AND model.day_closed = false")
	List<CSmProductFgStockDetailsModel> getAllStockDetail();

	@Modifying
	@Transactional
	@Query(value = "UPDATE CSmProductFgStockDetailsModel model SET model.day_closed = TRUE where model.is_delete= false")
	void FnUpdateStockDetailsDayClosed();

	@Query(value = "FROM CSmProductFgStockDetailsModel model where model.is_delete=0 and model.company_id = ?1 AND model.product_fg_id IN ?2")
	List<CSmProductFgStockDetailsModel> FnGetProductFgDetailsSummary(int companyId, List<String> distinctMaterialIds);

	@Query(value = "FROM CSmProductFgStockDetailsModel model where model.is_delete=false and model.day_closed=false and model.product_fg_id IN ?1 and model.company_id = ?2")
	List<CSmProductFgStockDetailsModel> FnGetAllProductFgStockDetails(List<String> distinctMaterialIds, int company_id);


	@Query(value = "Select * FROM sm_product_fg_stock_details_customer where is_delete = 0 and day_closed = 0", nativeQuery = true)
	List<CSmProductFgStockDetailsModel> getAllFgStockDetail();

}