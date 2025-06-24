package com.erp.MtEnquiryMasterServices.Repository;

import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryExistingExpectedFunctionalityServiceViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtEnquiryExistingExpectedFunctionalityServiceViewRepository extends JpaRepository<CMtEnquiryExistingExpectedFunctionalityServiceViewModel, Integer> {


	@Query(value = "from CMtEnquiryExistingExpectedFunctionalityServiceViewModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<CMtEnquiryExistingExpectedFunctionalityServiceViewModel> FnShowEnquiryExistingExpectedFunctionality(String enquiry_no,
	                                                                                                         int enquiry_version, int company_id);

	@Query(value = "from CMtEnquiryExistingExpectedFunctionalityServiceViewModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<CMtEnquiryExistingExpectedFunctionalityServiceViewModel> FnShowEnquiryExistingTrading(String enquiry_no,
	                                                                                           Integer enquiry_version, int company_id);

}
