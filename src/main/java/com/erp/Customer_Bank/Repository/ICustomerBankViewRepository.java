package com.erp.Customer_Bank.Repository;

import com.erp.Customer_Bank.Model.CCustomerBankViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerBankViewRepository extends JpaRepository<CCustomerBankViewModel, Long> {

	@Query(value = "Select * FROM cmv_customer_banks order by cust_bank_id Desc", nativeQuery = true)
	Page<CCustomerBankViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_customer_banks where is_delete =0  order by cust_bank_id Desc", nativeQuery = true)
	Page<CCustomerBankViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query("FROM CCustomerBankViewModel where is_delete =0 and cust_branch_id = ?1 and company_id = ?2")
	List<CCustomerBankViewModel> FnShowParticularRecordForUpdate(int cust_branch_id, int company_id);

	@Query(nativeQuery = true, value = "Select * FROM cmv_customer_banks where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and cust_bank_id = ?3")
	CCustomerBankViewModel FnShowParticularRecord(int company_id, int company_branch_id, int cust_bank_id);

	@Query(nativeQuery = true, value = "Select * FROM cmv_customer_banks where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and customer_id = ?3 and cust_branch_id=?4")
	List<CCustomerBankViewModel> checkRecordIfExist(int company_id, int company_branch_id, int customer_id,
	                                                int cust_branch_id);

}
