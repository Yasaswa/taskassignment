package com.erp.PtCustomerSalesReceiptMaster.Repository;

import com.erp.PtCustomerSalesReceiptMaster.Model.CPtCustomerSalesReceiptMasterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IPtCustomerSalesReceiptMasterRepository extends JpaRepository<CPtCustomerSalesReceiptMasterModel, Integer> {

	@Query(value = "SELECT * FROM ft_customer_sales_receipt_master WHERE customer_sales_receipt_no = ?1 and customer_sales_receipt_version = ?2 and company_id= ?3 and financial_year = ?4 and is_delete = 0", nativeQuery = true)
	CPtCustomerSalesReceiptMasterModel getExistingRecord(String customer_sales_receipt_no,
	                                                     int customer_sales_receipt_version, int company_id, String financial_year);

	@Modifying
	@Transactional
	@Query(value = "update ft_customer_sales_receipt_master set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where customer_sales_receipt_no = ?1 and customer_sales_receipt_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteCustomerSalesReceiptMasterRecords(String customer_sales_receipt_no, int customer_sales_receipt_version,
	                                             int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_customer_sales_receipt_master  set customer_sales_receipt_status = ?1 WHERE customer_sales_receipt_no =?2  AND company_id = ?3 AND is_delete = 0 ", nativeQuery = true)
	void FnUpdateCustomerSalesReceiptMasterStatus(String customer_sales_receipt_status, String customer_sales_receipt_no, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update ft_customer_sales_receipt_master set customer_receipt_mail_sent_status = ?1 where company_id = ?2 and customer_sales_receipt_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String string, int company_id, int customer_sales_receipt_master_transaction_id);


}
