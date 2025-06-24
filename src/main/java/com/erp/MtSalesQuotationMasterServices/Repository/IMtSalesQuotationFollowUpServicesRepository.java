package com.erp.MtSalesQuotationMasterServices.Repository;

import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationFollowUpServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesQuotationFollowUpServicesRepository extends JpaRepository<CMtSalesQuotationFollowUpServiceModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_followup_services  set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where quotation_no= ?2 and company_id = ?3", nativeQuery = true)
	void updateFollowupTradingRecords(String deleted_by, String quotation_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_followup_services  set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteQuotationFollowUp(String quotation_no, int quotation_version, int company_id, String deleted_by);


}
