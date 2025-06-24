package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationFollowupProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesQuotationFollowupProjectRepository extends JpaRepository<CMtSalesQuotationFollowupProjectModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_followup_project set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where quotation_no= ?2 and company_id = ?3", nativeQuery = true)
	void updateFollowupProjectRecords(String created_By, String quotation_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_followup_project set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteQuotationFollowupProjectDetails(String quotation_no, int quotation_version, int company_id,
	                                           String deleted_by);

}
