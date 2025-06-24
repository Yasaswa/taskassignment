package com.erp.MtSalesInvoiceMasterTrading.Repository;

import com.erp.MtSalesInvoiceMasterTrading.Model.CMtSalesInvoiceMasterTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IMtSalesInvoiceMasterTradingRepository extends JpaRepository<CMtSalesInvoiceMasterTradingModel, Integer> {

	@Query(value = "SELECT * FROM ft_sales_invoice_master_trading WHERE sales_invoice_no = ?1 and sales_invoice_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	CMtSalesInvoiceMasterTradingModel getExistingRecord(String sales_invoice_no, Integer sales_invoice_version,
	                                                    String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ft_sales_invoice_master_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where  sales_invoice_no= ?1 and sales_invoice_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesInvoiceMasterRecords(String sales_invoice_no, int sales_invoice_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "UPDATE CMtSalesInvoiceMasterTradingModel model SET model.sales_invoice_status = 'X' WHERE model.sales_invoice_no = ?1 and model.company_id = ?2 and model.is_delete = 0")
	void updateSalesInvoiceStatus(String sales_invoice_no, int company_id);
}
