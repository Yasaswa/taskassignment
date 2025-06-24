package com.erp.MtEnquiryMasterProject.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface IMtEnquiryMasterProjectService {

	Map<String, Object> FnAddUpdateRecord(JSONObject transEnquiryProjectJson, boolean isApprove);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String enquiry_no, int enquiry_version, String financial_year, int company_id);

	Map<String, Object> FnDeleteRecord(String enquiry_no, int enquiry_version, String deleted_by, int company_id);

	Map<String, Object> FnSendEmail(int company_id, int enquiry_master_transaction_id, JSONObject emailData);

}
