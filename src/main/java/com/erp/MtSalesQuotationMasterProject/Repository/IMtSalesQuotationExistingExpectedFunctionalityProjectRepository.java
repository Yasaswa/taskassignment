package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationExistingExpectedFunctionalityProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesQuotationExistingExpectedFunctionalityProjectRepository extends JpaRepository<CMtSalesQuotationExistingExpectedFunctionalityProjectModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_existing_expected_functionality_project  set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateExistingExpectedProjectFunctionalityStatus(String quotation_no, int quotation_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_existing_expected_functionality_project set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteQuotationExistingExpectedFunctionalityProjectDetails(String quotation_no, int quotation_version,
	                                                                int company_id, String deleted_by);

}
