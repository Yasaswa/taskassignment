package com.erp.Product_Type.Service;

import com.erp.Product_Type.Model.CProductTypeModel;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

public interface IProductTypeService {


	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int product_type_id, String deleted_by);
	
	Map<String, Object> FnShowAllDetailsandMastermodelRecords(int product_type_id, int company_id);

	
//	Object FnShowAllRecords(Pageable pageable);
//
//	Object FnShowAllActiveRecords(Pageable pageable);

//	JSONObject FnShowParticularRecordForUpdate(int product_type_id);

//	JSONObject FnShowParticularRecord(int company_id, int product_type_id);

//	Object FnShowAllReportRecords(Pageable pageable);




}
