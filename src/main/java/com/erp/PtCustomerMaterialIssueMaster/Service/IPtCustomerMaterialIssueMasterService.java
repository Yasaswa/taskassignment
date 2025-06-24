package com.erp.PtCustomerMaterialIssueMaster.Service;


import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;



public interface IPtCustomerMaterialIssueMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) throws JsonProcessingException;

	Map<String, Object> FnDeleteRecord(String customer_material_issue_no, int customer_material_issue_version,
			int company_id, String deleted_by);

//	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String customer_material_issue_no,
//			int customer_material_issue_version, String financial_year, int company_id);

	Map<String, Object> FnShowAllMasterAndDetailsmodelRecords(String customer_material_issue_no,
			int customer_material_issue_version, String financial_year, int company_id);

//	Map<String, Object> FnShowIndentDetailsRecords(JSONObject indentNo);


}
