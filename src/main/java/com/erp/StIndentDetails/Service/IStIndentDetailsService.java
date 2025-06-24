package com.erp.StIndentDetails.Service;

import com.erp.StIndentDetails.Model.CStIndentDetailsViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IStIndentDetailsService {


	Page<CStIndentDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<CStIndentDetailsViewModel> FnShowParticularRecord(int indent_details_id, Pageable pageable, int company_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String indent_no, int indent_version, String financial_year,
	                                                          int company_id);

	Map<String, Object> FnDeleteRecord(String indent_no, int indent_version, int company_id, String user_name);

	Map<String, Object> FnCancleRecord(String indent_no, int indent_version, int company_id, String user_name);

	Map<String, Object> FnGetPartialIndentMaterials(String indent_no, int indent_version, String financial_year, int company_id);

	Map<String, Object> FnPreCloseRecord(String indent_no, int company_id, int UserId, JSONObject stIndentData);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);


}
