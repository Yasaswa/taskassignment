package com.erp.PtCustomerSalesReceiptMaster.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IPtCustomerSalesReceiptMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String isApproveOrCancel);

	Map<String, Object> FnDeleteRecord(String customer_sales_receipt_no, int customer_sales_receipt_version,
	                                   int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String customer_sales_receipt_no,
	                                                          int customer_sales_receipt_version, String financial_year, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnSendEmail(int company_id, int customer_sales_receipt_master_transaction_id,
	                                JSONObject emailData);

}
