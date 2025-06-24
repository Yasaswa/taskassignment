package com.erp.Product_Rejection_Parameters.Service;


import com.erp.Product_Rejection_Parameters.Model.CProductRejectionParametersModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductRejectionParametersService {

	JSONObject FnAddUpdateRecord(CProductRejectionParametersModel cProductRejectionParametersModel);

	Object FnDeleteRecord(int product_rejection_parameters_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int product_rejection_parameters_id);

	JSONObject FnShowParticularRecord(int company_id, int product_rejection_parameters_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
