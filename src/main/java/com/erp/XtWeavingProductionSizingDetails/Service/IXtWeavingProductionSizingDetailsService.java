package com.erp.XtWeavingProductionSizingDetails.Service;


import java.util.Map;

import org.json.JSONObject;

public interface IXtWeavingProductionSizingDetailsService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);
	
	Map<String, Object> FnDeleteRecord(int weaving_production_sizing_master_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_sizing_master_id, int company_id);

	Map<String, Object> FnShowParticularSizingShiftSummary(String sizing_production_date, int company_id);
	
}
