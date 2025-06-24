package com.erp.MtEnquiryMasterServices.Repository;

import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryExistingExpectedFunctionalityServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IMtEnquiryExistingExpectedFunctionalityServiceRepository extends JpaRepository<CMtEnquiryExistingExpectedFunctionalityServiceModel, Integer> {


	@Modifying
	@Transactional
//	@Query(value = "update mt_enquiry_existing_expected_functionality_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where enquiry_no = ?2 and enquiry_version = ?3  and company_id = ?4", nativeQuery = true)
	@Query(value = "Update CMtEnquiryExistingExpectedFunctionalityServiceModel modal set modal.is_delete = 1, modal.deleted_on = CURRENT_TIMESTAMP(), modal.deleted_by = ?1 where modal.enquiry_no = ?2 and modal.enquiry_version = ?3 and modal.company_id = ?4")
	void updateExistingFunctionlityStatus(String deleted_by, String enquiry_no, Integer enquiry_version, int company_id);


	@Modifying
	@Transactional
//	@Query(value = "update mt_enquiry_existing_expected_functionality_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	@Query(value = "Update CMtEnquiryExistingExpectedFunctionalityServiceModel modal set modal.is_delete = 1, modal.deleted_on = CURRENT_TIMESTAMP(), modal.deleted_by = ?4 where modal.enquiry_no = ?1 and modal.enquiry_version = ?2 and modal.company_id = ?3")
	void deleteEnquiryExistingExpectedFunctionality(String enquiry_no, int enquiry_version, int company_id, String deleted_by);


}
