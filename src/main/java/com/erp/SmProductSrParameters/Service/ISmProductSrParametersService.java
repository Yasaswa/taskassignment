package com.erp.SmProductSrParameters.Service;

import java.util.Map;

import com.erp.SmProductSrParameters.Model.CSmProductSrParametersModel;

public interface ISmProductSrParametersService {

	Map<String, Object> FnAddUpdateRecord(CSmProductSrParametersModel smProductSrParametersModel);

	Map<String, Object> FnDeleteRecord(int product_parameter_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id);

	
}
