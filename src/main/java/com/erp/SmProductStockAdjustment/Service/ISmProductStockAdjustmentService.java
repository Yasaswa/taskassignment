package com.erp.SmProductStockAdjustment.Service;

import org.json.JSONObject;

import java.util.Map;

public interface ISmProductStockAdjustmentService {

	Map<String, Object> FnAddUpdateRecord(JSONObject stockAdjustmentJson);

	Map<String, Object> FnDeleteRecord(int stock_adjustment_transaction_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int stock_adjustment_transaction_id, int company_id);

}
