package com.erp.Customer.Repository;

import com.erp.Customer.Model.CCustomerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface ICustomerRepository extends JpaRepository<CCustomerModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_customer set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where customer_id=?1 ", nativeQuery = true)
	Object FnDeleteRecord(int customer_id, String deleted_by, Integer company_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_customer_branch set is_delete = 1,  deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where customer_id=?1 ", nativeQuery = true)
	void FnDeleteCustBranchRecord(int customer_id, String deleted_by, int company_id);

	@Modifying
	@Transactional
//	@Query(value = "update cm_customer_contacts set is_delete = 1,  deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where customer_id=?1 and company_id = ?3", nativeQuery = true)
	@Query(value = "update cm_customer_contacts set is_delete = 1,  deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where customer_id=?1", nativeQuery = true)
	Object FnDeleteCustConstactsRecord(int customer_id, String deleted_by, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_customer_banks set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where customer_id=?1 ", nativeQuery = true)
	void FnDeleteCustBankRecords(int customer_id, String deleted_by, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_customer_banks set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where cust_branch_id=?1 and company_id = ?3", nativeQuery = true)
	void FnDeleteCustBranchBankRecords(int customer_branch_id, String deleted_by, int company_id);

	//@Query(nativeQuery = true, value = "SELECT * FROM cm_customer WHERE (customer_name = ?1 or (?2 IS NOT NULL AND customer_short_name = ?2)) and company_id = ?3 order by customer_id Desc limit 1")
//	CCustomerModel getCheck(String customer_name, String customer_short_name, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_customer WHERE (customer_name = ?1) and company_id = ?2 order by customer_id Desc limit 1")
	CCustomerModel getCheck(String customer_name, int company_id);

	@Query(value = "SELECT * FROM cmv_customer_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

	@Query(nativeQuery = true, value = "Select * from cm_customer where is_delete = 0 and customer_id = ?1 and company_id = ?2")
    CCustomerModel FnShowCustomerRecords(int customer_id, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM amv_modules_forms_user_access WHERE user_name = ?1 and company_id = ?2 and is_delete = 0 ")
	CCustomerModel getUserName(String username, Integer company_id);
}
