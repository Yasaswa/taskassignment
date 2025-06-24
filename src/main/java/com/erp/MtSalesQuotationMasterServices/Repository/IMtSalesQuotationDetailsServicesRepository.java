package com.erp.MtSalesQuotationMasterServices.Repository;

import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationDetailsServicesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtSalesQuotationDetailsServicesRepository extends JpaRepository<CMtSalesQuotationDetailsServicesModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update CMtSalesQuotationDetailsServicesModel model set model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() where model.quotation_no = ?1 and model.quotation_version = ?2 and model.company_id = ?3")
	void FnUpdateSalesQtDetailsRecord(String quotation_no, int quotation_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_services set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesQtDetailsServicesRecords(String quotation_no, int quotation_version, int company_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_services set quotation_item_status = 'X' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationDetails(String quotation_no, int company_id);
	
	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_details_services set quotation_item_status = 'S' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationDetailsStatusBySO(String quotationNo, int company_id);

}
