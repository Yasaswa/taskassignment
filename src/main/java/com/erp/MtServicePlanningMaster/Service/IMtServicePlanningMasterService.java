package com.erp.MtServicePlanningMaster.Service;

import java.util.Map;


import org.json.JSONObject;


public interface IMtServicePlanningMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int service_planning_master_transaction_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int service_planning_master_transaction_id, int company_id);


}
