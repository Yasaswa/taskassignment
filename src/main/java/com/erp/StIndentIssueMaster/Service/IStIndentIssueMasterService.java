package com.erp.StIndentIssueMaster.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IStIndentIssueMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String acceptFLag);

	Map<String, Object> FnDeleteRecord(String issue_no, int issue_version, int company_id, String deleted_by);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String issue_no, int issue_version, String financial_year,
	                                                          int company_id);

	Map<String, Object> FnShowIndentDetailsRecords(JSONObject indentNo);


}
