package com.erp.MtEnquiryMasterServices.Repository;

import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryDetailsServicesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IMtEnquiryDetailsServicesRepository extends JpaRepository<CMtEnquiryDetailsServicesModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_details_services set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	void FnUpdateDetailsServiceRecord(String enquiry_no, int enquiry_version, int company_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_details_services set is_delete = 1, deleted_on = CURRENT_TIMESTAMP(), deleted_by = ?4 where enquiry_no = ?1 and enquiry_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteEnquiryDetailsServicesRecords(String enquiry_no, int enquiry_version, int company_id, String deleted_by);

	// Query for update the details status after quotation approved.
	@Modifying
	@Transactional
	@Query(value = "update mt_enquiry_details_services set enquiry_item_status = 'Q' where enquiry_no IN (?1) and is_delete = 0 and company_id = ?2", nativeQuery = true)
	void FnUpdateEQItemsStatusByQuotation(List<String> enquiry_nos, int company_id);

	@Query(value = "FROM CMtEnquiryDetailsServicesModel model where model.is_delete=0 and model.company_id = ?1 AND model.enquiry_no IN ?2")
	List<CMtEnquiryDetailsServicesModel> FnShowEnquiryDetailsByEnqNos(int company_id, List<String> enquiryNosList);

}
