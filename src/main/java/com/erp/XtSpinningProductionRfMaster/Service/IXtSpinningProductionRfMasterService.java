package com.erp.XtSpinningProductionRfMaster.Service;

import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public interface IXtSpinningProductionRfMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int spinning_production_rf_master_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordUpdate(
			int spinning_production_rf_master_id, int company_id);

	Map<String, Object> FnShowParticularShiftSummary(String shift, String spinning_production_date, int company_id);

	Map<String, Object> FnShowProductionReport(Map<String, Object> reportRequest, HttpServletResponse httpServletResponse);
}
