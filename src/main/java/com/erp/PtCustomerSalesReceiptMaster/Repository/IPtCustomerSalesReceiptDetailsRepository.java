package com.erp.PtCustomerSalesReceiptMaster.Repository;

import com.erp.PtCustomerSalesReceiptMaster.Model.CPtCustomerSalesReceiptDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IPtCustomerSalesReceiptDetailsRepository extends JpaRepository<CPtCustomerSalesReceiptDetailsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update ft_customer_sales_receipt_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where customer_sales_receipt_no = ?1 and customer_sales_receipt_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnUpdateCustomerSalesReceiptDetailsRecord(String customer_sales_receipt_no, int customer_sales_receipt_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_customer_sales_receipt_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where customer_sales_receipt_no = ?1 and customer_sales_receipt_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteCustomerSalesReceiptDetailsRecords(String customer_sales_receipt_no, int customer_sales_receipt_version,
	                                              int company_id);


	@Modifying
	@Transactional
	@Query(value = "update  ft_customer_sales_receipt_details set  customer_sales_item_receipt_status = ?1 WHERE customer_sales_receipt_no =?2 AND company_id = ?3 AND is_delete = 0 ", nativeQuery = true)
	void FnUpdateCustomerSalesReceiptDetailsStatus(String customer_sales_item_receipt_status, String customer_sales_receipt_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_customer_sales_receipt_details set sales_invoice_item_status = ?2 WHERE sales_invoice_details_transaction_id = ?1 AND is_delete = 0 ", nativeQuery = true)
	void FnUpdateStatusByCustomerReceiptDetails(int sales_invoice_details_transaction_id, String invoiceStatus);
}
