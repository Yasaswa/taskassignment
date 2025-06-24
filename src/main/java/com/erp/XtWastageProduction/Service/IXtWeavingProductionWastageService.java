package com.erp.XtWastageProduction.Service;


import java.util.Map;

import org.json.JSONObject;

public interface IXtWeavingProductionWastageService {

    Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

    Map<String, Object> FnDeleteRecord(int weaving_production_sizing_master_id, String deleted_by);

    Map<String, Object> FnShowParticularRecordForUpdate(String production_set_no,int sub_section_id, int company_id);

    Map<String, Object> FnShowParticularRecordForUpdateWVWV(int weavingProductionStoppageId, int subSectionId, int companyId);
}
