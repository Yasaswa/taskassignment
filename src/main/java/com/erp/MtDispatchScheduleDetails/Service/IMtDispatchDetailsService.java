package com.erp.MtDispatchScheduleDetails.Service;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleDetailsTradingViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IMtDispatchDetailsService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDispatchScheduleDetailsUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(String dispatch_schedule_no, int dispatch_schedule_version, int company_id, String deleted_by);

	Map<String, Object> FnShowAllDetailsandMastermodelRecords(String dispatch_schedule_no,
	                                                          int dispatch_schedule_version, String financial_year, int company_id);

	Page<CMtDispatchScheduleDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<CMtDispatchScheduleDetailsTradingViewModel> FnShowParticularRecord(
			int dispatch_schedule_details_transaction_id, Pageable pageable, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowDispatchScheduleDetailsTradingRecords(JSONObject customerOrderNo, String dispatch_schedule_no, int dispatch_schedule_version, int company_id);

}
