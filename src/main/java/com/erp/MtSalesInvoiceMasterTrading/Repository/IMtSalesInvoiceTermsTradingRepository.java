package com.erp.MtSalesInvoiceMasterTrading.Repository;

import com.erp.MtSalesInvoiceMasterTrading.Model.CMtSalesInvoiceTermsTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesInvoiceTermsTradingRepository extends JpaRepository<CMtSalesInvoiceTermsTradingModel, Integer> {
	@Modifying
	@Transactional
	@Query(value = "update CMtSalesInvoiceTermsTradingModel SI set SI.is_delete = false, SI.deleted_on = CURRENT_TIMESTAMP() where SI.sales_invoice_no= ?1 and SI.company_id = ?2")
	void updateSalesInvoiceTerms(String sales_invoice_no, int company_id);
}