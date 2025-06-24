package com.erp.MtEnquiryMasterServices.Repository;

import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryDetailsServicesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtEnquiryDetailsServicesViewRepository extends JpaRepository<CMtEnquiryDetailsServicesViewModel, Integer> {


	@Query(value = "SELECT * FROM mtv_enquiry_details_services  WHERE enquiry_no = ?1 and enquiry_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowEnquiryDetailsServicesRecords(String enquiry_no, int enquiry_version,
	                                                              String financial_year, int company_id);


	@Query(value = "FROM CMtEnquiryDetailsServicesViewModel model WHERE model.is_delete=0 AND model.enquiry_item_status = 'A' AND model.company_id = ?1 AND model.enquiry_no IN ?2")
	List<CMtEnquiryDetailsServicesViewModel> FnShowEnquiryDetailsRecord(int company_id, List<String> enquiryNoList);

}
