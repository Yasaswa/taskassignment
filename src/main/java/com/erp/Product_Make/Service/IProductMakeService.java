package com.erp.Product_Make.Service;

import com.erp.Product_Make.Model.CProductMakeModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductMakeService {

	JSONObject FnAddUpdateRecord(CProductMakeModel cProductMakeModel);

	Object FnDeleteRecord(int product_make_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int product_make_id);

	JSONObject FnShowParticularRecord(int company_id, int product_make_id);

	Object FnShowAllReportRecords(Pageable pageable);


}
