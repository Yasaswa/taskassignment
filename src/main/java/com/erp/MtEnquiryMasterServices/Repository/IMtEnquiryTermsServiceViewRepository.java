package com.erp.MtEnquiryMasterServices.Repository;

import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryTermsServiceViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtEnquiryTermsServiceViewRepository extends JpaRepository<CMtEnquiryTermsServiceViewModel, Integer> {

	@Query(value = "from CMtEnquiryTermsServiceViewModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<CMtEnquiryTermsServiceViewModel> FnShowEnquiryTermsTrading(String enquiry_no, int enquiry_version,
	                                                                int company_id);

}
