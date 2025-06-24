package com.erp.SmProductSrCustomer.Repository;

import com.erp.SmProductSrCustomer.Model.CSmProductSrCustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductSrCustomerRepository extends JpaRepository<CSmProductSrCustomerModel, Integer> {

	@Query(value = "FROM CSmProductSrCustomerModel model where model.is_delete =0 and model.product_sr_customer_id = ?1 and model.company_id = ?2")
	CSmProductSrCustomerModel FnShowParticularRecordForUpdate(int product_sr_customer_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr_customer set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_sr_id=?1 and company_branch_id = ?2 and company_id = ?3", nativeQuery = true)
	int updateStatus(String product_sr_id, int company_branch_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_sr_customer set is_delete = 1,  deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where product_sr_id=?1  and company_id = ?2", nativeQuery = true)
	void deleteproductSrCustomerRecords(String product_sr_id, int company_id, String deleted_by);


	@Query(value = "FROM CSmProductSrCustomerModel model where model.is_delete = 0 and model.product_sr_id = ?1 and model.company_id = ?2")
	List<CSmProductSrCustomerModel> FnShowProductServiceCustomerRecords(String product_sr_id, int company_id);


}
