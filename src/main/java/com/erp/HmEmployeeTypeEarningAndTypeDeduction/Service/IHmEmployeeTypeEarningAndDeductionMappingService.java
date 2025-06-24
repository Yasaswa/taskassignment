package com.erp.HmEmployeeTypeEarningAndTypeDeduction.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IHmEmployeeTypeEarningAndDeductionMappingService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int employee_type_id, int company_id, String deleted_by);

	Map<String, Object> FnShowAllRecords(String employee_type_name, String employee_type_group_name, int company_id);


}
