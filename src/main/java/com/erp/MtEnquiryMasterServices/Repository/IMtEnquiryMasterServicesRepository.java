package com.erp.MtEnquiryMasterServices.Repository;

import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryMasterServicesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IMtEnquiryMasterServicesRepository extends JpaRepository<CMtEnquiryMasterServicesModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_services set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteEnquiryMasterServicesRecords(String enquiry_no, int enquiry_version, int company_id, String deleted_by);

	@Query(value = "SELECT * FROM mt_enquiry_master_services WHERE  enquiry_no = ?1 and  enquiry_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	CMtEnquiryMasterServicesModel getExistingRecord(String enquiry_no, int enquiry_version, String financial_year,
	                                                int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_services set enquiry_mail_sent_status = ?1 where company_id = ?2 and enquiry_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String enquiry_mail_sent_status, int company_id, int enquiry_master_transaction_id);

	@Query(value = "SELECT model.enquiry_no FROM CMtEnquiryMasterServicesModel model WHERE model.is_delete = 0 AND model.company_id = ?1 AND model.enquiry_status = 'A'")
	List<String> FnGetEnquiryNoDetails(int company_id);

	// Query for update the details status after quotation approved.
	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_services set enquiry_status = 'Q' where enquiry_no IN (?1) and is_delete = 0 and company_id = ?2", nativeQuery = true)
	void FnUpdateEQStatusByQuotation(List<String> enquiry_nos, int company_id);

	@Query(value = "FROM CMtEnquiryMasterServicesModel model where model.is_delete=0 and model.company_id = ?1 AND model.enquiry_no IN ?2")
	List<CMtEnquiryMasterServicesModel> FnShowEnquiriesByEnqNos(int company_id, List<String> enquiryNosList);

}
