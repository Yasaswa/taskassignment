package com.erp.XmSpinningProdCount.service;


import org.json.JSONObject;

import java.util.Map;

public interface IXmSpinningProdCountService {


	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int production_count_id, String deleted_by, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int production_count_id, int company_id);
	
}
