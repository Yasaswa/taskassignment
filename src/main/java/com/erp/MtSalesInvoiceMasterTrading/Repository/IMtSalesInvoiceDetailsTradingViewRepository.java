package com.erp.MtSalesInvoiceMasterTrading.Repository;

import com.erp.MtSalesInvoiceMasterTrading.Model.CMtSalesInvoiceDetailsTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtSalesInvoiceDetailsTradingViewRepository extends JpaRepository<CMtSalesInvoiceDetailsTradingViewModel, Integer> {

	@Query(value = "SELECT * FROM ftv_sales_invoice_details_trading WHERE sales_invoice_no = ?1 and sales_invoice_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowSalesInvoiceDetailsTradingRecords(String sales_invoice_no,
	                                                                  int sales_invoice_version, String financial_year, int company_id);

	@Query(value = "FROM CMtSalesInvoiceDetailsTradingViewModel model where model.is_delete = 0 AND model.customer_id = ?1 AND model.company_id = ?2 AND model.sales_invoice_item_status IN('E','W','V','I') ORDER BY model.payment_schedule_date DESC ")
	List<CMtSalesInvoiceDetailsTradingViewModel> FnShowSalesInvoiceDetailsRecords(int customer_id, int company_id);

	@Query(value = "FROM CMtSalesInvoiceDetailsTradingViewModel model where model.is_delete =0 and model.sales_invoice_no = ?1 and model.company_id=?2")
	List<CMtSalesInvoiceDetailsTradingViewModel> FnGetSalesInvoiceDetails(String sales_invoice_no, int company_id);

//	@Query(value="SELECT * FROM " ,nativeQuery = true)
//	Map<String, Object> FnShowAlldetailsReportRecords();

}
