package com.erp.Product_Category1.Controller;

import com.erp.Product_Category1.Model.CProductCategory1Model;
import com.erp.Product_Category1.Service.IProductCategory1Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/productcategory1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductCategory1Controller {

	@Autowired
	IProductCategory1Service iProductCategory1Service;

//	@PostMapping("/FnAddUpdateRecord")
//	public Object FnAddUpdateRecord(@RequestBody CProductCategory1Model cProductCategory1Model) throws SQLException {
//
//		JSONObject resp = new JSONObject();
//
//		try {
//			resp = iProductCategory1Service.FnAddUpdateRecord(cProductCategory1Model);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp.toString();
//	}
//
//	@DeleteMapping("/FnDeleteRecord/{product_category1_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int product_category1_id, @PathVariable String deleted_by) {
//		return iProductCategory1Service.FnDeleteRecord(product_category1_id, deleted_by);
//	}
	
	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("ProductCategory1AllData") JSONObject jsonObject) {
		Map<String, Object> responce = iProductCategory1Service.FnAddUpdateRecord(jsonObject);
		return responce;
	}

	
	@DeleteMapping("/FnDeleteRecord/{product_category1_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int product_category1_id,@PathVariable String deleted_by) {
		return iProductCategory1Service.FnDeleteRecord(product_category1_id,deleted_by);

	}
	
	@GetMapping("/FnShowProductTypeGroupParameterAndParameterValueRecords/{product_type_id}/{company_id}")
	public Map<String, Object> FnShowProductTypeGroupParameterAndParameterValueRecords(@PathVariable int product_type_id,@PathVariable int company_id) throws SQLException {
		Map<String, Object> responce = iProductCategory1Service.FnShowProductTypeGroupParameterAndParameterValueRecords(product_type_id,company_id);
		return responce;

	}
	
	@GetMapping("/FnShowProductCategory1AndGroupParameterRecordForUpdate/{product_category1_id}/{company_id}")
	public Map<String, Object> FnShowProductCategory1AndGroupParameterRecordForUpdate(@PathVariable int product_category1_id,@PathVariable int company_id) throws SQLException {
		Map<String, Object> responce = iProductCategory1Service.FnShowProductCategory1AndGroupParameterRecordForUpdate(product_category1_id,company_id);
		return responce;

	}
	

	
//	@GetMapping("/FnShowAllRecords")
//	Object FnShowAllRecords(Pageable pageable) throws SQLException {
//		Object cProductCategory1ViewModel = null;
//		try {
//			cProductCategory1ViewModel = iProductCategory1Service.FnShowAllRecords(pageable);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cProductCategory1ViewModel.toString();
//	}
//
//	@GetMapping("/FnShowAllActiveRecords")
//	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
//		Object cProductCategory1ViewModel = null;
//		try {
//			cProductCategory1ViewModel = iProductCategory1Service.FnShowAllActiveRecords(pageable);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cProductCategory1ViewModel.toString();
//	}
//
//	@GetMapping("/FnShowParticularRecordForUpdate/{product_category1_id}")
//	public Object FnShowParticularRecordForUpdate(@PathVariable int product_category1_id) throws SQLException {
//		JSONObject resp = new JSONObject();
//		try {
//			resp = iProductCategory1Service.FnShowParticularRecordForUpdate(product_category1_id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp.toString();
//
//	}
//
//	@GetMapping("/FnShowParticularRecord/{company_id}/{product_category1_id}")
//	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_category1_id)
//			throws SQLException {
//		JSONObject resp = new JSONObject();
//
//		try {
//			resp = iProductCategory1Service.FnShowParticularRecord(company_id, product_category1_id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp.toString();
//	}
//
//	@GetMapping("/FnShowAllReportRecords")
//	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
//		Object cProductCategory1RptModel = null;
//		try {
//			cProductCategory1RptModel = iProductCategory1Service.FnShowAllReportRecords(pageable);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cProductCategory1RptModel;
//	}
}
