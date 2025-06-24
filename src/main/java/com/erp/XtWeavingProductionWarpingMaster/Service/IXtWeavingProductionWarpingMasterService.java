package com.erp.XtWeavingProductionWarpingMaster.Service;

import java.util.Map;

import org.json.JSONObject;


public interface IXtWeavingProductionWarpingMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int weaving_production_warping_master_id, String deleted_by);

	Map<String, Object> FnChangePrintStatus(int weaving_production_warping_master_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_warping_master_id, int company_id);

	Map<String, Object> FnShowParticularWarpingShiftSummary(String warping_production_date, int company_id);

	

}
