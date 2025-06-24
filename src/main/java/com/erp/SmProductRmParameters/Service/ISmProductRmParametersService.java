package com.erp.SmProductRmParameters.Service;
import java.util.Map;


import com.erp.SmProductRmParameters.Model.CSmProductRmParametersModel;

public interface ISmProductRmParametersService {

	Map<String, Object> FnAddUpdateRecord(CSmProductRmParametersModel cSmProductRmParametersModel);

	Map<String, Object> FnDeleteRecord(int product_parameter_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id);



}
