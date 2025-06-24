package com.erp.FinishGoods.SmProductFgCustomer.Repository;

import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductFgCustomerRepository extends JpaRepository<CSmProductFgCustomerModel, Integer> {

	@Query(value = "FROM CSmProductFgCustomerModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_id = ?1 order by cdvm.product_fg_id Desc")
	Page<CSmProductFgCustomerModel> FnShowParticularRecord(int product_fg_id, Pageable pageable);

	@Query(value = "FROM CSmProductFgCustomerModel smfg where smfg.is_delete = 0 and smfg.product_fg_id = ?1")
	List<CSmProductFgCustomerModel> checkRecordIfExist(String product_fg_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_customer set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP()   where product_fg_customer_id=?1", nativeQuery = true)
	Object updateProductFgSupplierActiveStatus(int product_fg_customer_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_customer set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_fg_id=?1 and company_id=?2", nativeQuery = true)
	void updateProductFgCustomerActiveStatus(String product_fg_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_customer set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteCustomerRecords(String product_fg_id, String deleted_by);

//	@Query(value = "FROM CSmProductFgCustomerModel model WHERE model.product_fg_id = ?1 and model.company_id = ?2 and model.is_delete = 0")
//	List<CSmProductFgCustomerModel> FnShowParticularProductFgCustomerRecords(String product_fg_id, int company_id);
	
	@Query(value = "FROM CSmProductFgCustomerModel model WHERE model.product_fg_id = ?1 and model.is_delete = 0")
	List<CSmProductFgCustomerModel> FnShowParticularProductFgCustomerRecords(String product_fg_id);


}
