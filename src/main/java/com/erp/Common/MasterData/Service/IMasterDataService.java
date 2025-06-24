package com.erp.Common.MasterData.Service;

import com.erp.Common.MasterData.Model.CGlobalQueryObjectModel;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface IMasterDataService {

	Map<String, Object> FnShowParticularRecord(JSONObject json, int company_id);

	Map<String, Object> FnShowParticularRecordByOperator(JSONObject json, int company_id);

	List<Map<String, Object>> FnShowRecords(JSONObject json, CGlobalQueryObjectModel globalQuery);

	List<Map<String, Object>> FnEvictRecords(JSONObject json, CGlobalQueryObjectModel globalQuery);


	Map<String, Object> FnShowMaterialItems(JSONObject materialSearchObject);

	Map<String, Object> FnShowSearchMaterialItems(JSONObject materialSearchObject);
}
