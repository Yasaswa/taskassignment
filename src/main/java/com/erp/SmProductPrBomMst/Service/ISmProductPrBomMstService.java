package com.erp.SmProductPrBomMst.Service;

import org.json.JSONObject;

import java.util.Map;

public interface ISmProductPrBomMstService {

	Map<String, Object> FnAddUpdateRecord(JSONObject detailObject);

	Map<String, Object> FnDeleteRecord(String product_pr_bom_no, String deleted_by);


}
