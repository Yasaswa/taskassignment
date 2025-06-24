package com.erp.Product_Category4.Service;

import com.erp.Product_Category4.Model.CProductCategory4Model;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductCategory4Service {

	JSONObject FnAddUpdateRecord(CProductCategory4Model cProductCategory4Model);

	Object FnDeleteRecord(int product_category4_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int product_category4_id);

	JSONObject FnShowParticularRecord(int company_id, int product_category4_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
