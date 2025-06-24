package com.erp.PtCustomerSalesReceiptMaster.Repository;

import com.erp.PtCustomerSalesReceiptMaster.Model.CPtCustomerSalesReceiptMasterSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IPtCustomerSalesReceiptMasterSummaryViewRepository extends JpaRepository<CPtCustomerSalesReceiptMasterSummaryViewModel, Integer> {


	@Query(value = "SELECT * FROM ftv_customer_sales_receipt_master_summary WHERE customer_sales_receipt_no = ?1 and customer_sales_receipt_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowCustomerSalesReceiptMasterRecords(String customer_sales_receipt_no,
	                                                            int customer_sales_receipt_version, String financial_year, int company_id);


	@Query(value = "SELECT * FROM ptv_customer_sales_receipt_master_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();

	@Query(value = "SELECT * FROM ptv_customer_sales_receipt_details_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();


}
