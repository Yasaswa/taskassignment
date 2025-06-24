package com.erp.Product_Qa_Parameters.Service;

import com.erp.Product_Qa_Parameters.Model.CProductQaParametersModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductQaParametersService {

	JSONObject FnAddUpdateRecord(CProductQaParametersModel cProductQaParametersModel);

	Object FnDeleteRecord(int product_qa_parameters_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int product_qa_parameters_id);

	JSONObject FnShowParticularRecord(int company_id, int product_qa_parameters_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
