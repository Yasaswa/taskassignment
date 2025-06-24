package com.erp.Product_Category5.Service;

import com.erp.Product_Category5.Model.CProductCategory5Model;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductCategory5Service {

	JSONObject FnAddUpdateRecord(CProductCategory5Model cProductCategory5Model);

	Object FnDeleteRecord(int product_category5_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int product_category5_id);

	JSONObject FnShowParticularRecord(int company_id, int product_category5_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
