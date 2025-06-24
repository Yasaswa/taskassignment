package com.erp.XtWeavingProductionOrderMaster.Service;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;



public interface IXtWeavingProductionOrderMasterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) throws JsonProcessingException;

	Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_order_id, int company_id);

	Map<String, Object> FnDeleteRecord(int weaving_production_order_id, String deleted_by);

	Map<String, Object> GetLastSetNoWeavingProductionOrder();

	Map<String, Object> FnDeletePerticularMaterialRecord(int production_order_material_id, String deleted_by);

	Map<String, Object> FnAddMaterialRecord(JSONObject jsonObject);


}
