package com.erp.MtQuotationDetails.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtQuotationDetailsService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Map<String, Object> FnDeleteRecord(String quotation_no, int quotation_version, int company_id, String deleted_by);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String quotation_no, int quotation_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnGetQuotationDetailsByItemStatus(JSONObject jsonObject, int company_id);

	Map<String, Object> FnShowEnquiryDetailsRecords(JSONObject enquiryNo, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnSendEmail(int company_id, int quotation_master_transaction_id, JSONObject emailData);


	Map<String, Object> FnAddUpdateQuotationFollowupRecord(JSONObject jsonObject);

}
