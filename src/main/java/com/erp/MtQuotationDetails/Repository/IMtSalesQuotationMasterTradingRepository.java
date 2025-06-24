package com.erp.MtQuotationDetails.Repository;

import com.erp.MtQuotationDetails.Model.CMtSalesQuotationMasterTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface IMtSalesQuotationMasterTradingRepository extends JpaRepository<CMtSalesQuotationMasterTradingModel, Integer> {


	@Query(value = "Select * from mt_sales_quotation_master_trading where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	CMtSalesQuotationMasterTradingModel getLastQuotationVersion(String quotation_no, String company_id);

	@Query(value = "SELECT * FROM mtv_sales_quotation_trading_summary  WHERE quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	Map<String, Object> FnShowQuotationMasterTradingRecord(String quotation_no, int quotation_version,
	                                                       String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteQotationMaster(String quotation_no, int quotation_version, int company_id, String deleted_by);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_trading set  quotation_mail_sent_status = ?1 where company_id = ?2 and quotation_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String quotation_mail_sent_status, int company_id, int quotation_master_transaction_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_trading set is_active = 0, quotation_status = 'X' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationMasterTrading(String quotation_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_trading set quotation_status = 'O' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationMasterStatus(String quotationNo, int company_id);

}
