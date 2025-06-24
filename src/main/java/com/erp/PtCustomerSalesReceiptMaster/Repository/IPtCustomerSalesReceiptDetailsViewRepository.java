package com.erp.PtCustomerSalesReceiptMaster.Repository;

import com.erp.PtCustomerSalesReceiptMaster.Model.CPtCustomerSalesReceiptDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IPtCustomerSalesReceiptDetailsViewRepository extends JpaRepository<CPtCustomerSalesReceiptDetailsViewModel, Integer> {

	@Query(value = "SELECT * FROM ftv_customer_sales_receipt_details WHERE customer_sales_receipt_no = ?1 and customer_sales_receipt_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowCustomerSalesReceiptDetailsRecords(String customer_sales_receipt_no,
	                                                                   int customer_sales_receipt_version, String financial_year, int company_id);

}
