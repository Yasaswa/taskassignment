package com.erp.HmLeavesBalance.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IHmLeavesBalanceService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int leaves_balance_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int leaves_balance_id);


}
