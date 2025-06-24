package com.erp.MtEnquiryDetailsTrading.Repository;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryTermsTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IMtEnquiryTermsTradingRepository extends JpaRepository<CMtEnquiryTermsTradingModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_terms_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?1 where enquiry_no = ?2 and enquiry_version = ?3 and company_id = ?4", nativeQuery = true)
	void updateEnquiryTermsPOTermsStatus(String deleted_by, String enquiry_no, Integer enquiry_version, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_terms_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteEnquiryTerms(String enquiry_no, int enquiry_version, int company_id);


	@Query(value = "from CMtEnquiryTermsTradingModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<CMtEnquiryTermsTradingModel> FnShowEnquiryPoTerms(String enquiry_no, Integer enquiry_version, int company_id);


}
