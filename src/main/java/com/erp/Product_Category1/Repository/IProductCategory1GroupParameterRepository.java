package com.erp.Product_Category1.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.Product_Category1.Model.CProductCategory1GroupParameterModel;

public interface IProductCategory1GroupParameterRepository extends JpaRepository<CProductCategory1GroupParameterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update CProductCategory1GroupParameterModel model set model.is_delete = 1 , model.deleted_on = CURRENT_TIMESTAMP() where model.company_id = ?1 and model.product_category1_id = ?2")
	void updateProductCategory1GroupParameterRecord(int company_id, Integer product_category1_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_category1_group_parameter set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_category1_id = ?1", nativeQuery = true)
	void FnDeleteProductCategory1GroupParameterRecord(int product_category1_id, String deleted_by);

	@Query(value = "FROM CProductCategory1GroupParameterModel model where model.is_delete = 0 and model.product_category1_id = ?1 and model.company_id = ?2")
	List<CProductCategory1GroupParameterModel> FnShowProductCategory1GroupParameterRecordForUpdate(
			int product_category1_id, int company_id);

}
