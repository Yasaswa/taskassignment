package com.erp.MtSalesQuotationMasterServices.Repository;

import com.erp.MtSalesQuotationMasterServices.Model.CMtSalesQuotationMasterServicesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IMtSalesQuotationMasterServicesRepository extends JpaRepository<CMtSalesQuotationMasterServicesModel, Integer> {

	@Query(value = "FROM CMtSalesQuotationMasterServicesModel model where model.is_delete =0 and model.quotation_master_transaction_id = ?1 and model.company_id = ?2")
	CMtSalesQuotationMasterServicesModel FnShowParticularRecordForUpdate(int quotation_master_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_services set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteSalesQtMasterServicesRecords(String quotation_no, int quotation_version, int company_id, String deleted_by);


	@Query(value = "SELECT * FROM mt_sales_quotation_master_services WHERE quotation_master_transaction_id = ?1 and company_id= ?2", nativeQuery = true)
	CMtSalesQuotationMasterServicesModel getExistingRecord(int quotation_master_transaction_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_services set quotation_mail_sent_status = ?1 where company_id = ?2 and quotation_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String quotation_mail_sent_status, int company_id, int quotation_master_transaction_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_services set is_active = 0, quotation_status = 'X' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationMasterService(String quotation_no, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_services set quotation_status = 'O' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationDetailsStatusBySO(String quotationNo, int company_id);
}
