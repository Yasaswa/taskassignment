package com.erp.XtWeavingProductionInspectionMaster.Service;

import java.util.Map;

import org.json.JSONObject;


public interface IXtWeavingProductionInspectionMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int weaving_production_inspection_master_id, int company_id, String deleted_by);

	Map<String, Object> FnDeleteWeavingProductionData(int weaving_production_inspection_master_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_inspection_master_id, int company_id);

	Map<String, Object> FnShowParticularInspectionShiftSummary(String inspection_production_date, int company_id);


	Map<String, Object> FnSaveDailyProductionData(JSONObject jsonObject);

	Map<String, Object> GetLastRollNoWeavingInspection(int bookTypeId);
}
