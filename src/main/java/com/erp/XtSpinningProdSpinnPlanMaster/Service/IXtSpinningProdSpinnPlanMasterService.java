package com.erp.XtSpinningProdSpinnPlanMaster.Service;

import org.json.JSONObject;

import java.util.Map;


public interface IXtSpinningProdSpinnPlanMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String is_approve);

	Map<String, Object> FnDeleteRecord(int spinn_plan_id, String deleted_by);

	Map<String, Object> FnShowMasterAndDetailsModelRecordForUpdate(JSONObject jsonObject);

}
