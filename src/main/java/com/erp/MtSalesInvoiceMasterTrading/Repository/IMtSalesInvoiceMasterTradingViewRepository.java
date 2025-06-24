package com.erp.MtSalesInvoiceMasterTrading.Repository;

import com.erp.MtSalesInvoiceMasterTrading.Model.CMtSalesInvoiceMasterTradingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IMtSalesInvoiceMasterTradingViewRepository extends JpaRepository<CMtSalesInvoiceMasterTradingViewModel, Integer> {

	@Query(value = "FROM CMtSalesInvoiceMasterTradingViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.sales_invoice_master_transaction_id Desc")
	Page<CMtSalesInvoiceMasterTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CMtSalesInvoiceMasterTradingViewModel model where model.is_delete =0 and model.sales_invoice_master_transaction_id = ?1 and model.company_id = ?2 order by model.sales_invoice_master_transaction_id Desc")
	Page<CMtSalesInvoiceMasterTradingViewModel> FnShowParticularRecord(int sales_invoice_master_transaction_id, Pageable pageable, int company_id);

	@Query(value = "SELECT * FROM mtv_sales_invoice_master_trading_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();

	@Query(value = "FROM CMtSalesInvoiceMasterTradingViewModel model where model.sales_invoice_no = ?1 and model.sales_invoice_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	CMtSalesInvoiceMasterTradingViewModel FnShowSalesInvoiceMasterTradingRecords(String sales_invoice_no, int sales_invoice_version,
	                                                                             String financial_year, int company_id);

	@Query(value = "FROM CMtSalesInvoiceMasterTradingViewModel model where model.is_delete =0 and  model.sales_invoice_status = ?1 AND model.company_id= ?2 ORDER BY model.sales_invoice_master_transaction_id DESC")
	List<CMtSalesInvoiceMasterTradingViewModel> getSalesInvoiceMasterRecordsByStatus(String sales_invoice_status,
	                                                                                 int company_id);

	@Query(value = "FROM CMtSalesInvoiceMasterTradingViewModel model where model.is_delete =0 and model.company_id=?1 ORDER BY model.sales_invoice_master_transaction_id DESC")
	List<CMtSalesInvoiceMasterTradingViewModel> getAllSalesInvoiceRecords(int company_id);


}
