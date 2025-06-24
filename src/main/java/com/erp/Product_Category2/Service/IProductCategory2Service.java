package com.erp.Product_Category2.Service;

import com.erp.Product_Category2.Model.CProductCategory2Model;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductCategory2Service {

	JSONObject FnAddUpdateRecord(CProductCategory2Model cProductCategory2Model);

	Object FnDeleteRecord(int product_category2_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int product_category2_id);

	JSONObject FnShowParticularRecord(int company_id, int product_category2_id);

	Object FnShowAllReportRecords(Pageable pageable);

}
