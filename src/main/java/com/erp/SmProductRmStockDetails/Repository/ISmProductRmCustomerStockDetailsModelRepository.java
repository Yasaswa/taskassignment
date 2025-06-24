package com.erp.SmProductRmStockDetails.Repository;

import com.erp.SmProductRmStockDetails.Model.CSmProductRmCustomerStockDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ISmProductRmCustomerStockDetailsModelRepository extends JpaRepository<CSmProductRmCustomerStockDetailsModel, Integer> {

	@Query(value = "Select * FROM sm_product_rm_stock_details_customer where is_delete= 0 and product_rm_id IN ?1 AND company_id = ?2", nativeQuery = true)
	List<CSmProductRmCustomerStockDetailsModel> FnGetAllProductRmCustomerStockDetails(List<String> distinctMaterialIds, int company_id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE CSmProductRmCustomerStockDetailsModel model SET day_closed = TRUE where model.is_delete=0")
	void FnUpdateCustomerStockDetailsDayClosed();

	@Query(value = "FROM CSmProductRmCustomerStockDetailsModel model where model.is_delete=false and model.day_closed = false")
	List<CSmProductRmCustomerStockDetailsModel> getAllCustomerStockDetail();
}