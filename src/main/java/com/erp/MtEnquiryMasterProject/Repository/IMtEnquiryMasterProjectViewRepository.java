package com.erp.MtEnquiryMasterProject.Repository;

import com.erp.MtEnquiryMasterProject.Model.MtEnquiryMasterProjectSummaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IMtEnquiryMasterProjectViewRepository extends JpaRepository<MtEnquiryMasterProjectSummaryModel, Integer> {

	@Query(value = "SELECT * FROM mtv_enquiry_master_project_summary  WHERE enquiry_no = ?1 and enquiry_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowEnquiryMasterRecord(String enquiry_no, int enquiry_version, String financial_year,
	                                              int company_id);

}
