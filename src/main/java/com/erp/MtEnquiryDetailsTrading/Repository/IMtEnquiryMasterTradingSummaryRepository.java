package com.erp.MtEnquiryDetailsTrading.Repository;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryMasterTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IMtEnquiryMasterTradingSummaryRepository extends JpaRepository<CMtEnquiryMasterTradingModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteEnquiry(String enquiry_no, int enquiry_version, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	void updateEnquiryMasterStatus(String enquiry_no, Integer enquiry_version, int company_id);


	@Query(value = "SELECT * FROM mtv_enquiry_master_trading_summary  WHERE enquiry_no = ?1 and enquiry_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowEnquiryMasterRecord(String enquiry_no, int enquiry_version, String financial_year,
	                                              int company_id);

	@Query(value = "Select * from mt_enquiry_master_trading where is_delete = 0 and enquiry_no = ?1 and company_id = ?2", nativeQuery = true)
	CMtEnquiryMasterTradingModel getLastPOEnquiryVersion(String enquiry_no, String company_id);


	// Query for update the details status after quotation approved.
	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_trading set enquiry_status = 'Q' where enquiry_no IN (?1) and is_delete = 0 and company_id = ?2", nativeQuery = true)
	void FnUpdateEQStatusByQuotation(List<String> enquiry_nos, int company_id);

	@Query(value = "select * FROM mtv_enquiry_master_trading_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_trading set enquiry_mail_sent_status = ?1 where company_id = ?2 and enquiry_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String enquiry_mail_sent_status, int company_id, int enquiry_master_transaction_id);

	@Query(value = "FROM CMtEnquiryMasterTradingModel model where model.is_delete=0 and model.company_id = ?1 AND model.enquiry_no IN ?2")
	List<CMtEnquiryMasterTradingModel> FnShowEnquiriesByEnqNos(int company_id, List<String> enquiryNosList);


}
