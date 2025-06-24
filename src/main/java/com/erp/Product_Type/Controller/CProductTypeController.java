package com.erp.Product_Type.Controller;


import com.erp.Product_Type.Service.IProductTypeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/producttype", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductTypeController {

	@Autowired
	IProductTypeService iProductTypeService;

	
	
	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("ProductTypeParameterGroupData") JSONObject jsonObject) {
		Map<String, Object> responce = iProductTypeService.FnAddUpdateRecord(jsonObject);
		return responce;
	}

	
	@DeleteMapping("/FnDeleteRecord/{product_type_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int product_type_id,@PathVariable String deleted_by) {
		return iProductTypeService.FnDeleteRecord(product_type_id,deleted_by);

	}
	

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{product_type_id}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@PathVariable int product_type_id, @PathVariable int company_id) throws SQLException {
		 Map<String, Object> responce =  iProductTypeService.FnShowAllDetailsandMastermodelRecords(product_type_id, company_id);
		 return responce;
	}
	
	
	
	
	
//	@GetMapping("/FnShowAllRecords")
//	Object FnShowAllRecords(Pageable pageable) throws SQLException {
//		Object cProductTypeViewModel = null;
//		try {
//			cProductTypeViewModel = iProductTypeService.FnShowAllRecords(pageable);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cProductTypeViewModel.toString();
//	}
//
//	@GetMapping("/FnShowAllActiveRecords")
//	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
//		Object cProductTypeViewModel = null;
//		try {
//			cProductTypeViewModel = iProductTypeService.FnShowAllActiveRecords(pageable);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cProductTypeViewModel.toString();
//	}

//	@GetMapping("/FnShowParticularRecordForUpdate/{product_type_id}")
//	public Object FnShowParticularRecordForUpdate(@PathVariable int product_type_id) throws SQLException {
//		JSONObject resp = new JSONObject();
//		try {
//			resp = iProductTypeService.FnShowParticularRecordForUpdate(product_type_id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp.toString();
//
//	}

//	@GetMapping("/FnShowParticularRecord/{company_id}/{product_type_id}")
//	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_type_id) throws SQLException {
//		JSONObject resp = new JSONObject();
//
//		try {
//
//			resp = iProductTypeService.FnShowParticularRecord(company_id, product_type_id);
//			System.out.println("Responce: " + resp.toString());
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		return resp.toString();
//
//	}

//	@GetMapping("/FnShowAllReportRecords")
//	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
//		Object cProductTypeRptModel = null;
//		try {
//			cProductTypeRptModel = iProductTypeService.FnShowAllReportRecords(pageable);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return cProductTypeRptModel;
//
//	}
//	
//	@PostMapping("/FnAddUpdateRecord")
//	public Object FnAddUpdateRecord(@RequestBody CProductTypeModel cProductTypeModel) throws SQLException {
//
//		JSONObject resp = new JSONObject();
//
//		try {
//			resp = iProductTypeService.FnAddUpdateRecord(cProductTypeModel);
//			System.out.println("Responce: " + resp.toString());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp.toString();
//
//	}

}
