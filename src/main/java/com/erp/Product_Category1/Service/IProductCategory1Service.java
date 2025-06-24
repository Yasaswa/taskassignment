package com.erp.Product_Category1.Service;

import com.erp.Product_Category1.Model.CProductCategory1Model;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductCategory1Service {

//	JSONObject FnAddUpdateRecord(CProductCategory1Model cProductCategory1Model);

//	Object FnDeleteRecord(int product_category1_id, String deleted_by);
//
//	Object FnShowAllRecords(Pageable pageable);
//
//	Object FnShowAllActiveRecords(Pageable pageable);
//
//	JSONObject FnShowParticularRecordForUpdate(int product_category1_id);
//
//	JSONObject FnShowParticularRecord(int company_id, int product_category1_id);
//
//	Object FnShowAllReportRecords(Pageable pageable);

	Map<String, Object> FnShowProductTypeGroupParameterAndParameterValueRecords(int product_type_id, int company_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int product_category1_id, String deleted_by);

	Map<String, Object> FnShowProductCategory1AndGroupParameterRecordForUpdate(int product_category1_id,
			int company_id);


}
