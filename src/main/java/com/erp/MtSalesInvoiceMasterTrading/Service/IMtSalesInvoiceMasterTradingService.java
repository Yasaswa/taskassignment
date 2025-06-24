package com.erp.MtSalesInvoiceMasterTrading.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtSalesInvoiceMasterTradingService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(String sales_invoice_no, int sales_invoice_version, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(JSONObject jsonObject);

	Map<String, Object> FnShowDispatchChallanDetails(JSONObject dispatchChallanData);

	Map<String, Object> FnGetSaleInvoiceDataByStatus(String sales_invoice_status, int company_id);

	Map<String, Object> FnGenerateTokenForEInvoice(int company_id);

	Map<String, Object> FnCancelSalesInvoice(JSONObject jsonObject);

	Map<String, Object> FnGetSalesInvoiceDetails(String sales_invoice_no, int company_id);

	Map<String, Object> FnGenerateEInvoice(JSONObject jsonObject);
}
