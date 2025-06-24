package com.erp.SmProductPrParameters.Service;

import java.util.Map;

import com.erp.SmProductPrParameters.Model.CSmProductPrParametersModel;

public interface ISmProductPrParametersService {

	Map<String, Object> FnAddUpdateRecord(CSmProductPrParametersModel smProductPrParametersModel);

	Map<String, Object> FnDeleteRecord(int product_parameter_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id);


}
