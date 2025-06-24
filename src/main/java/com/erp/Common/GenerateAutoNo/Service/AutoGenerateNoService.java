package com.erp.Common.GenerateAutoNo.Service;

import com.erp.Common.GenerateAutoNo.Model.CProdTransactionNo;
import com.erp.Common.GenerateAutoNo.Model.GAutoNoModel;
import org.json.JSONObject;

import java.util.Map;

public interface AutoGenerateNoService {

	String FnGenerateAutoNo(JSONObject json, GAutoNoModel autoNoQuery);

	String FnGenerateMaterialId(JSONObject json, GAutoNoModel autoNoQuery);

	Map<String, Object> FnGenerateTransactionNo(CProdTransactionNo autoNoQuery);

}
