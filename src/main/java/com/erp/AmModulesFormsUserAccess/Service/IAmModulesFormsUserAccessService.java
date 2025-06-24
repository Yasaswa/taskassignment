package com.erp.AmModulesFormsUserAccess.Service;

import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface IAmModulesFormsUserAccessService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	List<CAmModulesFormsUserAccessViewModel> FnShowAllActiveRecords(String user_id, int company_id);

}
