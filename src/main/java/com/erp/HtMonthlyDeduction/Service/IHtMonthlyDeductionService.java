package com.erp.HtMonthlyDeduction.Service;

import java.util.Map;

import org.json.JSONObject;

public interface IHtMonthlyDeductionService {

//	Map<String, Object> FnAddUpdateRecord(CHtMonthlyDeductionDeatilsModel cHtMonthlyDeductionModel);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);
	Object FnDeleteRecord(int monthly_deduction_master_transaction_id, int company_id, String deleted_by);
	Map<String, Object> FnShowParticularRecordForUpdate(int monthly_deduction_master_transaction_id, int company_id);

}
