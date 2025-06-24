package com.erp.MtSalesOrderMasterWorks.Service;

import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksSummaryRptModel;
import com.erp.MtSalesOrderMasterWorks.Model.CMtSalesOrderMasterWorksSummaryViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtSalesOrderMasterWorksService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove);

	Map<String, Object> FnDeleteRecord(String sales_order_no, int sales_order_version, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int sales_order_master_transaction_id, int company_id);

	Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowParticularRecord(int sales_order_master_transaction_id,
	                                                                      Pageable pageable, int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String sales_order_no, int sales_order_version,
	                                                          String financial_year, int company_id);

	Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(
			String customer_order_no, Pageable pageable, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Page<CMtSalesOrderMasterWorksSummaryViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<CMtSalesOrderMasterWorksSummaryRptModel> FnShowAllReportRecords(Pageable pageable);

}
