package com.erp.MtEnquiryMasterProject.Repository;

import com.erp.MtEnquiryMasterProject.Model.CMtEnquiryExistingExpectedFunctionalityProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtEnquiryExistingExpectedFunctionalityProjectRepository extends JpaRepository<CMtEnquiryExistingExpectedFunctionalityProjectModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_existing_expected_functionality_project set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where enquiry_no = ?2 and enquiry_version = ?3  and company_id = ?4", nativeQuery = true)
	void updateExistingFunctionlityStatus(String created_by, String enquiry_no, int enquiry_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_existing_expected_functionality_project set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where enquiry_master_transaction_id = ?1 and company_id = ?3", nativeQuery = true)
	void deleteEnquiryExistingExpectedFunctionality(int enquiry_master_transaction_id, String deleted_by, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_existing_expected_functionality_project set is_delete = 1, deleted_by= ?3, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?4", nativeQuery = true)
	void deleteEnquiryExistingExpectedFunctionality(String enquiry_no, int enquiry_version, String deleted_by,
	                                                int company_id);


}
