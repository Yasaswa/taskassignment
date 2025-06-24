package com.erp.MtEnquiryMasterProject.Repository;


import com.erp.MtEnquiryMasterProject.Model.CMtEnquiryMasterProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import javax.transaction.Transactional;


public interface IMtEnquiryMasterProjectRepository extends JpaRepository<CMtEnquiryMasterProjectModel, Integer> {

	@Query(value = "FROM CMtEnquiryMasterProjectModel model where model.is_delete =0 and model.enquiry_master_transaction_id = ?1 and model.company_id = ?2")
	CMtEnquiryMasterProjectModel FnShowParticularRecordForUpdate(int enquiry_master_transaction_id, int company_id);

	@Query(value = "FROM CMtEnquiryMasterProjectModel model where model.is_delete =0 and model.enquiry_no = ?1 and model.financial_year = ?2 and model.company_id = ?3")
	CMtEnquiryMasterProjectModel FnCheckEnquiryIsExist(String enquiry_no, String financial_year, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_project set is_delete = 1, deleted_by= ?3, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?4", nativeQuery = true)
	void deleteEnquiry(String enquiry_no, int enquiry_version, String deleted_by, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_master_project set enquiry_mail_sent_status = ?1 where company_id = ?2 and enquiry_master_transaction_id =?3", nativeQuery = true)
	void updateMailStatus(String enquiry_mail_sent_status, int company_id, int enquiry_master_transaction_id);

	
	@Query(value = "FROM CMtEnquiryMasterProjectModel model where model.is_delete=0 and model.company_id = ?1 AND model.enquiry_no IN ?2")
	List<CMtEnquiryMasterProjectModel> FnShowEnquiriesByEnqNos(int company_id, List<String> distinctEnquiryNos);
}
