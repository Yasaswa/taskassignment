package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationDetailsProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesQuotationDetailsProjectRepository extends JpaRepository<CMtSalesQuotationDetailsProjectModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_project set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateStatus(String quotation_no, int quotation_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_project set quotation_item_status = 'X' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationProjectDetails(String quotation_no, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_project set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteQuotationProjectDetails(String quotation_no, int quotation_version, int company_id, String deleted_by);

//	@Query(value = "SELECT * FROM mt_sales_quotation_details_project  WHERE quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
//	List<Map<String, Object>> FnShowQuotationDetailsProjectRecord(String quotation_no, int quotation_version,
//			String financial_year, int company_id);

}
