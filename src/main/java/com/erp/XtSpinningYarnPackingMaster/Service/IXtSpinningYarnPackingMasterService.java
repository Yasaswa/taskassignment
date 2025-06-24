package com.erp.XtSpinningYarnPackingMaster.Service;


import java.util.Map;

import org.json.JSONObject;

public interface IXtSpinningYarnPackingMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int yarn_packing_master_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int yarn_packing_master_id, int company_id2);
	
	
}
