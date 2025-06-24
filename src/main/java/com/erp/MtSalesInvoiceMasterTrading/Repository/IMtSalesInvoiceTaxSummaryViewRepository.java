package com.erp.MtSalesInvoiceMasterTrading.Repository;

import com.erp.MtSalesInvoiceMasterTrading.Model.CMtSalesInvoiceTaxSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtSalesInvoiceTaxSummaryViewRepository extends JpaRepository<CMtSalesInvoiceTaxSummaryViewModel, Integer> {

	@Query(value = "FROM CMtSalesInvoiceTaxSummaryViewModel model WHERE model.sales_invoice_no = ?1 and model.sales_invoice_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	List<CMtSalesInvoiceTaxSummaryViewModel> FnShowSalesInvoiceTaxSummaryRecords(String sales_invoice_no, int sales_invoice_version,
	                                                                             String financial_year, int company_id);


}
