package com.erp.XtWarpingProductionOrder.Service;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;

public interface IXtWarpingProductionOrderService {

	Map<String, Object> FnAddUpdateRecord(JSONObject warpingProductionOrderRequest) throws JsonProcessingException;

    Map<String, Object> FnDeleteRecord(int warpingProductionOrderId, String deleted_by, int companyId);

	Map<String, Object> FnShowParticularRecordForUpdate(int warping_production_order_id, int company_id);

	Map<String, Object> FnGetProductBasedProperties(String product_id);
}
