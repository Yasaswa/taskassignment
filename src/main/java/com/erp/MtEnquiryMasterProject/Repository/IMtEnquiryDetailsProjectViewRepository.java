package com.erp.MtEnquiryMasterProject.Repository;

import com.erp.MtEnquiryMasterProject.Model.CMtEnquiryDetailsProjectViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtEnquiryDetailsProjectViewRepository extends JpaRepository<CMtEnquiryDetailsProjectViewModel, Integer> {


	@Query(value = "SELECT * FROM mtv_enquiry_details_project  WHERE enquiry_no = ?1 and enquiry_version = ?2 and financial_year  =?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowEnquiryDetailsProjectRecords(String enquiry_no, int enquiry_version,
	                                                             String financial_year, int company_id);

}
