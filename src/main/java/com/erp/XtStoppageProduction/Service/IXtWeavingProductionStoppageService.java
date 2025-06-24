package com.erp.XtStoppageProduction.Service;


import org.json.JSONObject;

import java.util.Map;

public interface IXtWeavingProductionStoppageService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);
	
	Map<String, Object> FnDeleteRecord(int weaving_production_stoppage_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(Integer weaving_production_stoppage_id,int sub_section_id, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdateWVWV(int weavingProductionStoppageId,int sub_section_id, int companyId);
}
