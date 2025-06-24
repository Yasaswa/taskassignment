package com.erp.Product_Category4.Controller;

import com.erp.Product_Category4.Model.CProductCategory4Model;
import com.erp.Product_Category4.Service.IProductCategory4Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/productcategory4", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductCategory4Controller {

	@Autowired
	IProductCategory4Service iProductCategory4Service;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductCategory4Model cProductCategory4Model) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iProductCategory4Service.FnAddUpdateRecord(cProductCategory4Model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{product_category4_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_category4_id, @PathVariable String deleted_by) {
		return iProductCategory4Service.FnDeleteRecord(product_category4_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductCategory4ViewModel = null;
		try {
			cProductCategory4ViewModel = iProductCategory4Service.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory4ViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductCategory4ViewModel = null;
		try {
			cProductCategory4ViewModel = iProductCategory4Service.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory4ViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_category4_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_category4_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductCategory4Service.FnShowParticularRecordForUpdate(product_category4_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_category4_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_category4_id)
			throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductCategory4Service.FnShowParticularRecord(company_id, product_category4_id);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductCategory4RptModel = null;
		try {
			cProductCategory4RptModel = iProductCategory4Service.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory4RptModel;

	}
}
