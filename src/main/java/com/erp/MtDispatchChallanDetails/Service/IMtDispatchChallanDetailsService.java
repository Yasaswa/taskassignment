package com.erp.MtDispatchChallanDetails.Service;

import com.erp.MtDispatchChallanDetails.Model.CMtDispatchChallanDetailsTradingViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtDispatchChallanDetailsService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDispatchChallanDetailsUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(String dispatch_challan_no, int dispatch_challan_version, int company_id, String deletedBy);

	Page<CMtDispatchChallanDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<CMtDispatchChallanDetailsTradingViewModel> FnShowParticularRecord(
			int dispatch_challan__batchwise_transaction_id, Pageable pageable, int company_id);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String dispatch_challan_no, int dispatch_challan_version,
	                                                          String financial_year, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowDispatchScheduleMasterNos(JSONObject customerOrderNo,
	                                                    Pageable pageable, int company_id);

	Map<String, Object> FnShowSalesOrderDetails(JSONObject customerOrderNo, Pageable pageable,
	                                            int company_id);

	Map<String, Object> FnShowDispatchScheduleDetails(JSONObject dispatchScheduleNo, Pageable pageable,
	                                                  int company_id);

	Map<String, Object> FnCancelDispatchChallan(String dispatch_challan_no, int dispatch_challan_version,
	                                            String financial_year, int company_id, String detailsModifiedBy);

	Map<String, Object> FnSendEmail(int company_id, int dispatch_challan_details_transaction_id, JSONObject emailData);

    Map<String, Object> FnSaveDailyDispatchChallanData(JSONObject jsonObject);

	Map<String, Object> FnDeleteDailyDispatchReport(String dispatch_challan_no, int dispatch_challan_version, int company_id, String deletedBy);

}
