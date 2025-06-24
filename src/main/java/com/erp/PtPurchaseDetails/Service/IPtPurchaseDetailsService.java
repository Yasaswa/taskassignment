package com.erp.PtPurchaseDetails.Service;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IPtPurchaseDetailsService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String isApproveOrPreClosed);

//	Map<String, Object> FnPurchaseOrderDetailsUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(String purchase_order_no, int purchase_order_version, int company_id, String user_name);

	Page<CPtPurchaseOrderDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<CPtPurchaseOrderDetailsViewModel> FnShowParticularRecord(int purchase_order_details_transaction_id,
	                                                              Pageable pageable, int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String purchase_order_no, int purchase_order_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowIndentDetailsRecords(JSONObject indentNo,
	                                               int company_id);

	Map<String, Object> FnSendEmail(int company_id, int purchase_order_master_transaction_id, JSONObject emailData);

	Map<String, Object> FnAcceptPurchaseOrder(JSONObject pOAcceptanceJson, int company_id);


	Map<String, Object> FnGetPartialgrnMaterials(String purchaseOrderNo, int purchaseOrderVersion, String financialYear, int companyId);

	Map<String, Object> FnUpdatePreClosedRecord(JSONObject jsonObject, Integer company_id, Integer po_master_transaction_id);
}
