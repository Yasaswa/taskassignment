package com.erp.MtSalesOrderMasterProjects.Service;

import com.erp.MtSalesOrderMasterProjects.Model.CMtSalesOrderMasterProjectSummaryViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtSalesOrderMasterProjectsService {

	Map<String, Object> FnDeleteRecord(String sales_order_no, int sales_order_version, int company_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Map<String, Object> FnShowParticularRecordForUpdate(int sales_order_master_transaction_id, int company_id);

	Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowParticularRecord(int sales_order_master_transaction_id,
	                                                                        Pageable pageable, int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String sales_order_no, int sales_order_version,
	                                                          String financial_year, int company_id);

	Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(
			String customer_order_no, Pageable pageable, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Page<CMtSalesOrderMasterProjectSummaryViewModel> FnShowAllActiveRecords(Pageable pageable);

}
