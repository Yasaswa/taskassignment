package com.erp.FmProfitCenter.Service;

import com.erp.FmProfitCenter.Model.CFmProfitCenterModel;
import com.erp.FmProfitCenter.Model.CFmProfitCenterViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IFmProfitCenterService {

	JSONObject FnAddUpdateRecord(CFmProfitCenterModel cFmProfitCenterModel);

	Object FnDeleteRecord(int profit_center_id, int company_id, String deleted_by);

	Page<CFmProfitCenterViewModel> FnShowAllActiveRecords(Pageable pageable);

	CFmProfitCenterModel FnShowParticularRecordForUpdate(int profit_center_id, int company_id);

	Page<CFmProfitCenterViewModel> FnShowParticularRecord(int profit_center_id, Pageable pageable);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

//	Page<CFmProfitCenterRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

}
