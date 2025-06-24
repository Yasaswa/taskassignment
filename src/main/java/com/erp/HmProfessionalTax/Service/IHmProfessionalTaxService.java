package com.erp.HmProfessionalTax.Service;

import org.json.JSONObject;

import java.util.Map;

public interface IHmProfessionalTaxService {

	Map<String, Object> FnDeleteRecord(int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(String gender);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);


}
