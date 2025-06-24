package com.erp.MtQuotationDetails.Repository;

import com.erp.MtQuotationDetails.Model.CMtSalesQuotationFollowupTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesQuotationFollowupTradingRepository extends JpaRepository<CMtSalesQuotationFollowupTradingModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_followup_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where quotation_no= ?2 and company_id = ?3", nativeQuery = true)
	void updateFollowupTradingRecords(String deleted_by, String quotation_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_followup_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteQuotationFollowupTradingDetails(String quotation_no, int quotation_version, int company_id, String deleted_by);


}
