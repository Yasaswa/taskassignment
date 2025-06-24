package com.erp.MtEnquiryMasterProject.Repository;

import com.erp.MtEnquiryMasterProject.Model.MtEnquiryTermsProjectViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IMtEnquiryTermsProjectViewRepository extends JpaRepository<MtEnquiryTermsProjectViewModel, Integer> {


	@Query(value = "from MtEnquiryTermsProjectViewModel model where model.is_delete = 0 and model.enquiry_no = ?1 and model.enquiry_version = ?2 and model.company_id = ?3")
	List<MtEnquiryTermsProjectViewModel> FnShowEnquiryTermsProjects(String enquiry_no, int enquiry_version,
	                                                                int company_id);

}
