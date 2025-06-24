package com.erp.MtEnquiryMasterProject.Repository;


import com.erp.MtEnquiryMasterProject.Model.CMtEnquiryDetailsProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import javax.transaction.Transactional;


public interface IMtEnquiryDetailsProjectRepository extends JpaRepository<CMtEnquiryDetailsProjectModel, Integer> {

	@Query(value = "FROM CMtEnquiryDetailsProjectModel model where model.is_delete =0 and model.enquiry_details_transaction_id = ?1 and model.company_id = ?2")
	CMtEnquiryDetailsProjectModel FnShowParticularRecordForUpdate(int enquiry_details_transaction_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "UPDATE CMtEnquiryDetailsProjectModel model SET model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP(), model.deleted_by = ?1 where model.is_delete = 0 and model.enquiry_no = ?2 and model.enquiry_version = ?3 and model.company_id = ?4")
	void FnUpdateDetailsMasterRecord(String deleted_by, String enquiry_no, int enquiry_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_details_project set is_delete = 1, deleted_by= ?3, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?4", nativeQuery = true)
	void deleteEnquiryDetails(String enquiry_no, int enquiry_version, String deleted_by, int company_id);

	@Query(value = "FROM CMtEnquiryDetailsProjectModel model where model.is_delete=0 and model.company_id = ?1 AND model.enquiry_no IN ?2")
	List<CMtEnquiryDetailsProjectModel> FnShowEnquiryProjectDetailsByEnqNos(int company_id,
			List<String> distinctEnquiryNos);
}
