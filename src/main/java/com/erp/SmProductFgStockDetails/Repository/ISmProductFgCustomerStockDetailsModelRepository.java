package com.erp.SmProductFgStockDetails.Repository;

import com.erp.SmProductFgStockDetails.Model.CSmProductFgCustomerStockDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ISmProductFgCustomerStockDetailsModelRepository extends JpaRepository<CSmProductFgCustomerStockDetailsModel, Integer> {
	
	
//	@Query(value = "Select * FROM sm_product_fg_stock_details_customer where is_delete = 0 and product_fg_id IN ?1 and company_id = ?2 and day_closed = 0", nativeQuery = true)
	@Query(value = "FROM CSmProductFgCustomerStockDetailsModel model where model.is_delete = 0 and model.product_fg_id IN ?1 and model.company_id = ?2 and model.day_closed = 0")
	List<CSmProductFgCustomerStockDetailsModel> FnGetAllProductFgCustomerStockDetails(List<String> product_fg_id, int company_id);
  
	@Query(value = "FROM CSmProductFgCustomerStockDetailsModel model where model.is_delete = false and model.day_closed = false")
	List<CSmProductFgCustomerStockDetailsModel> getAllFgCustomerStockDetail();

	@Modifying
	@Transactional
	@Query(value = "UPDATE CSmProductFgCustomerStockDetailsModel model SET model.day_closed = TRUE where model.is_delete= false")
	void FnUpdateCustomerStockDetailsDayClosed();
}