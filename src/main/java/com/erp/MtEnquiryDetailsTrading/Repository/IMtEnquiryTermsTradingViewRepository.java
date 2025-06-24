package com.erp.MtEnquiryDetailsTrading.Repository;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryTermsTradingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtEnquiryTermsTradingViewRepository extends JpaRepository<CMtEnquiryTermsTradingViewModel, Long> {


	@Query(value = "from CMtEnquiryTermsTradingViewModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<CMtEnquiryTermsTradingViewModel> FnShowEnquiryTermsTrading(String enquiry_no, int enquiry_version,
	                                                                int company_id);


}
