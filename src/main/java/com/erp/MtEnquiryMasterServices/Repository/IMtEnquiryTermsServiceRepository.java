package com.erp.MtEnquiryMasterServices.Repository;

import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryTermsServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IMtEnquiryTermsServiceRepository extends JpaRepository<CMtEnquiryTermsServiceModel, Integer> {

	@Modifying
	@Transactional
//	@Query(value = "update mt_enquiry_terms_service set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where enquiry_no = ?2 and enquiry_version = ?3 and company_id = ?4", nativeQuery = true)
	@Query(value = "Update CMtEnquiryTermsServiceModel modal set modal.is_delete = 1, modal.deleted_on = CURRENT_TIMESTAMP(), modal.deleted_by = ?1 where modal.enquiry_no = ?2 and modal.enquiry_version = ?3 and modal.company_id = ?4")
	void updateEnquiryTermsPOTermsStatus(String deleted_by, String enquiry_no, Integer enquiry_version, int company_id);

	@Modifying
	@Transactional
//	@Query(value = "update mt_enquiry_terms_service set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	@Query(value = "Update CMtEnquiryTermsServiceModel modal set modal.is_delete = 1, modal.deleted_on = CURRENT_TIMESTAMP() where modal.enquiry_no = ?1 and modal.enquiry_version = ?2 and modal.company_id = ?3")
	void deleteEnquiryTerms(String enquiry_no, int enquiry_version, int company_id);

	@Query(value = "from CMtEnquiryTermsServiceModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<CMtEnquiryTermsServiceModel> FnShowEnquiryPoTerms(String enquiry_no, Integer enquiry_version, int company_id);

}
