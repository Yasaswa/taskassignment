package com.erp.MtSalesOrderMasterServices.Service;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderMasterServicesSummaryViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface IMtSalesOrderMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Map<String, Object> FnDeleteRecord(String sales_order_no, int sales_order_version, int company_id ,String deleted_by);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String sales_order_no, int sales_order_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Page<CMtSalesOrderMasterServicesSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(String customer_order_no,
	                                                                                               Pageable pageable, int company_id);
	Map<String, Object> FnGetQuotationNoList(int company_id);

	Map<String, Object> FnShowQuotationDetailsRecords(JSONObject quotationNo, int company_id);

	Map<String, Object> FnGetQuotationDetailsByItemStatus(JSONObject jsonObject, int company_id);

	Map<String, Object> FnSendEmail(int company_id, int sales_order_master_transaction_id, JSONObject emailData);

	Map<String, Object> FnAcceptCustomerOrder(JSONObject customerSOAcceptanceJson, int company_id);

}
