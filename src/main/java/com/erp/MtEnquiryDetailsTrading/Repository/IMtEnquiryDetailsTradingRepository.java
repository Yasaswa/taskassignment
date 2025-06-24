package com.erp.MtEnquiryDetailsTrading.Repository;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryDetailsTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


public interface IMtEnquiryDetailsTradingRepository extends JpaRepository<CMtEnquiryDetailsTradingModel, Integer> {

	@Query(value = "FROM CMtEnquiryDetailsTradingModel model where model.is_delete =0 and model.enquiry_details_transaction_id = ?1 and model.company_id = ?2")
	CMtEnquiryDetailsTradingModel FnShowParticularRecordForUpdate(int enquiry_details_transaction_id, int company_id);

	@Query(value = "SELECT * FROM mt_enquiry_details_trading  WHERE enquiry_no = ?1 and enquiry_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	void FnShowEnquiryDetailsMasterRecord(String enquiry_no, Integer enquiry_version, String financial_year,
	                                      int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_details_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where enquiry_no = ?2 and enquiry_version = ?3 and company_id = ?4", nativeQuery = true)
	void FnUpdateDetailsMasterRecord(String deleted_by, String enquiry_no, int enquiry_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_details_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteEnquiryDetails(String enquiry_no, int enquiry_version, int company_id);


	@Query(value = "SELECT * FROM mtv_enquiry_details_trading  WHERE enquiry_no = ?1 and enquiry_version = ?2 and financial_year  =?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowEnquiryDetailsRecords(String enquiry_no, int enquiry_version, String financial_year,
	                                                      int company_id);

	// Query for update the details status after quotation approved.
	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_details_trading set enquiry_item_status = 'Q' where enquiry_no IN (?1) and is_delete = 0 and company_id = ?2", nativeQuery = true)
	void FnUpdateEQItemsStatusByQuotation(List<String> enquiry_nos, int company_id);

	@Query(value = "FROM CMtEnquiryDetailsTradingModel model where model.is_delete=0 and model.company_id = ?1 AND model.enquiry_no IN ?2")
	List<CMtEnquiryDetailsTradingModel> FnShowEnquiryDetailsByEnqNos(int company_id, List<String> enquiryNosList);

}
