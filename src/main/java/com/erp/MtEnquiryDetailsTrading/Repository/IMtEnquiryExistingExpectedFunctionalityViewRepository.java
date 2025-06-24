package com.erp.MtEnquiryDetailsTrading.Repository;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryExistingExpectedFunctionalityViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtEnquiryExistingExpectedFunctionalityViewRepository extends JpaRepository<CMtEnquiryExistingExpectedFunctionalityViewModel, Long> {


	@Query(value = "from CMtEnquiryExistingExpectedFunctionalityViewModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<CMtEnquiryExistingExpectedFunctionalityViewModel> FnShowEnquiryExistingExpectedFunctionality(String enquiry_no,
	                                                                                                  int enquiry_version, int company_id);


	@Query(value = "from CMtEnquiryExistingExpectedFunctionalityViewModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<CMtEnquiryExistingExpectedFunctionalityViewModel> FnShowEnquiryExistingTrading(String enquiry_no,
	                                                                                    Integer enquiry_version, int company_id);

}
