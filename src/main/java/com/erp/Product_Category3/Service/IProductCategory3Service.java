package com.erp.Product_Category3.Service;

import com.erp.Product_Category3.Model.CProductCategory3Model;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductCategory3Service {

	JSONObject FnAddUpdateRecord(CProductCategory3Model cProductCategory3Model);

	Object FnDeleteRecord(int product_category3_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int product_category3_id);

	JSONObject FnShowParticularRecord(int company_id, int product_category3_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
