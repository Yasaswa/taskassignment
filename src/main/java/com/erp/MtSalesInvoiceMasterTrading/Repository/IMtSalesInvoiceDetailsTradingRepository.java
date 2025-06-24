package com.erp.MtSalesInvoiceMasterTrading.Repository;

import com.erp.MtSalesInvoiceMasterTrading.Model.CMtSalesInvoiceDetailsTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesInvoiceDetailsTradingRepository extends JpaRepository<CMtSalesInvoiceDetailsTradingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update ft_sales_invoice_details_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where sales_invoice_no = ?1 and sales_invoice_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnUpdateSalesInvoiceDetailsRecord(String sales_invoice_no, int sales_invoice_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update ft_sales_invoice_details_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where  sales_invoice_no= ?1 and sales_invoice_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesInvoiceDetailsRecords(String sales_invoice_no, int sales_invoice_version, int company_id);

}
