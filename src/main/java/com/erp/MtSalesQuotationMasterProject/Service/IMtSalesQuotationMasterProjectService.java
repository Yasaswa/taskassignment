package com.erp.MtSalesQuotationMasterProject.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IMtSalesQuotationMasterProjectService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Map<String, Object> FnDeleteRecord(String quotation_no, int quotation_version, int company_id, String deleted_by);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String quotation_no, int quotation_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnAddUpdateQuotationFollowupRecord(JSONObject jsonObject);

	Map<String, Object> FnSendEmail(int company_id, int quotation_master_transaction_id, JSONObject emailData);

	Map<String, Object> FnGetQuotationProjectDetailsByItemStatus(JSONObject jsonObject, int company_id);

}
