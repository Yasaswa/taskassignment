package com.erp.PtSupplierPurchasePaymentMaster.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface IPtSupplierPurchasePaymentMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String isApproveOrCancel);

	Map<String, Object> FnDeleteRecord(String supplier_purchase_payment_no, int supplier_purchase_payment_version,
	                                   int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String supplier_purchase_payment_no,
	                                                          int supplier_purchase_payment_version, String financial_year, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnSendEmail(int companyId, int supplierPurchasePaymentMasterTransactionId, JSONObject emailData);
}
