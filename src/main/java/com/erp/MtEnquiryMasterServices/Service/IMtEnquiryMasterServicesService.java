package com.erp.MtEnquiryMasterServices.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtEnquiryMasterServicesService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Map<String, Object> FnDeleteRecord(String enquiry_no, int enquiry_version, int company_id, String deleted_by);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String enquiry_no, int enquiry_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnSendEmail(int company_id, int enquiry_master_transaction_id, JSONObject emailData);

}
