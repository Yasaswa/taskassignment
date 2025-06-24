package com.erp.MtSalesQuotationMasterProject.Repository;

import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationMasterProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface IMtSalesQuotationMasterProjectRepository
		extends JpaRepository<CMtSalesQuotationMasterProjectModel, Integer> {

	@Query(value = "FROM CMtSalesQuotationMasterProjectModel model where model.is_delete =0 and model.quotation_master_transaction_id = ?1 and model.company_id = ?2")
	CMtSalesQuotationMasterProjectModel FnShowParticularRecordForUpdate(int quotation_master_transaction_id, int company_id);


	@Query(value = "FROM CMtSalesQuotationMasterProjectModel model WHERE  model.quotation_no = ?1 and  model.quotation_version = ?2 and model.financial_year = ?3 and model.company_id= ?4")
	CMtSalesQuotationMasterProjectModel getExistingRecord(String quotation_no, Integer quotation_version,
	                                                      String financial_year, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_project  set is_active = 0, quotation_status = 'X' where is_delete = 0 and quotation_no = ?1 and company_id = ?2", nativeQuery = true)
	void updateSalesQuotationProjectMaster(String quotation_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_project set  quotation_mail_sent_status = ?1 where company_id = ?2 and quotation_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String string, int company_id, int quotation_master_transaction_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_sales_quotation_master_project set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where quotation_no = ?1 and quotation_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteQuotationProjectMaster(String quotation_no, int quotation_version, int company_id, String deleted_by);

	@Query(value = "SELECT * FROM mt_sales_quotation_master_project  WHERE quotation_no = ?1 and quotation_version = ?2 and financial_year = ?3 and company_id= ?4 and is_delete = 0", nativeQuery = true)
	Map<String, Object> FnShowQuotationMasterProjectRecord(String quotation_no, int quotation_version,
	                                                       String financial_year, int company_id);

}
