package com.erp.XtWeavingProductionLoomMaster.Service;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;


public interface IXtWeavingProductionLoomMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int weaving_production_loom_master_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_loom_master_id, int company_id);

	List<Map<String, Object>> GetStdNormsForLoomUtilizationReportData(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage);





}
