package com.erp.MtSalesOrderMasterTrading.Service;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingViewModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTradingSummaryViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtSalesOrderMasterTradingService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	//Object FnDeleteRecord(int sales_order_master_transaction_id, int company_id);

	Page<CMtSalesOrderMasterTradingSummaryViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int sales_order_master_transaction_id, int company_id);

	Page<CMtSalesOrderMasterTradingSummaryViewModel> FnShowParticularRecord(int sales_order_master_transaction_id, Pageable pageable, int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String sales_order_no, int sales_order_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnShowSalesOrderDetailsTradingCustomerRecord(JSONObject customerOrderNo,
	                                                                 Pageable pageable, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnDeleteRecord(String sales_order_no, int sales_order_version, int company_id, String deleted_by);

	Page<CMtSalesOrderDetailsTradingViewModel> FnShowParticularRecord(String customer_order_no, Pageable pageable,
	                                                                  int company_id);

	Map<String, Object> FnSendEmail(int company_id, int sales_order_master_transaction_id, JSONObject emailData);

	Map<String, Object> FnAcceptCustomerOrder(JSONObject customerAcceptanceJson, int company_id);


	Map<String, Object> FnCancelSalesOrder(String sales_order_no, int sales_order_version, int company_id, String modified_by);
}
