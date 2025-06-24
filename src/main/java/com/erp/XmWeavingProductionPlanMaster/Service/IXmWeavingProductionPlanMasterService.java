package com.erp.XmWeavingProductionPlanMaster.Service;

import java.util.Map;


import org.json.JSONObject;

public interface IXmWeavingProductionPlanMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject cXmWeavingProductionPlanMasterModel);

	Map<String, Object> FnDeleteRecord(int weaving_production_plan_master_id, int company_id, String deleted_by);
	
	Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_plan_master_id, int company_id);


}
