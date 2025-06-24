package com.erp.MtEnquiryMasterServices.Repository;

import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryMasterServicesSummaryViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IMtEnquiryMasterServicesSummaryViewRepository extends JpaRepository<CMtEnquiryMasterServicesSummaryViewModel, Integer> {


	@Query(value = "SELECT * FROM mtv_enquiry_master_services_summary  WHERE enquiry_no = ?1 and enquiry_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowEnquiryMasterServicesDetailsRecords(String enquiry_no, int enquiry_version,
	                                                              String financial_year, int company_id);


	@Query(value = "SELECT * FROM mtv_enquiry_master_services_summary_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();

	@Query(value = "SELECT * FROM mtv_enquiry_details_services_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();


}
