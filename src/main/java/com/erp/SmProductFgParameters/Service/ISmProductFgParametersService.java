package com.erp.SmProductFgParameters.Service;

import java.util.Map;
import com.erp.SmProductFgParameters.Model.CSmProductFgParametersModel;

public interface ISmProductFgParametersService {

	Map<String, Object> FnAddUpdateRecord(CSmProductFgParametersModel cSmProductFgParametersModel);

	Map<String, Object> FnShowParticularRecordForUpdate(int product_type_id, int company_id);

	Map<String, Object> FnDeleteRecord(int product_parameter_id, String deleted_by);
	

}
