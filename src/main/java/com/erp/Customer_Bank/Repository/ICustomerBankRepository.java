package com.erp.Customer_Bank.Repository;

import com.erp.Customer_Bank.Model.CCustomerBankModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ICustomerBankRepository extends JpaRepository<CCustomerBankModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_customer_banks set is_delete = 1  , deleted_on = CURRENT_TIMESTAMP() where cust_bank_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int cust_bank_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_customer_banks WHERE cust_bank_account_type = ?1")
	CCustomerBankModel getCheck(String cust_bank_account_type);

	@Modifying
	@Transactional
	@Query(value = "update cm_customer_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where customer_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
	void updateCustomerBankActiveStatus(int customer_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update cm_customer_banks set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where cust_branch_id=?1 and is_delete = 0", nativeQuery = true)
	void FnDeleteCustomerBranchBankRecords(int cust_branch_id, String deleted_by);

	@Query(value = "FROM CCustomerBankModel model where model.is_delete = 0 and model.customer_id = ?1 and model.company_id = ?2")
	List<CCustomerBankModel> FnShowCustomerBankRecords(int customer_id, int company_id);


}
