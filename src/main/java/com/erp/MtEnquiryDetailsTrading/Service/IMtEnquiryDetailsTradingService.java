package com.erp.MtEnquiryDetailsTrading.Service;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryDetailsTradingViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtEnquiryDetailsTradingService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Page<CMtEnquiryDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	// Page<CMtEnquiryDetailsTradingRptModel> FnShowAllReportRecords(Pageable
	// pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int enquiry_details_transaction_id, int company_id);

	Page<CMtEnquiryDetailsTradingViewModel> FnShowParticularRecord(int enquiry_details_transaction_id,
	                                                               Pageable pageable, int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String enquiry_no, int enquiry_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnDeleteRecord(String enquiry_no, int enquiry_version, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnSendEmail(int company_id, int enquiry_master_transaction_id, JSONObject emailData);

}
