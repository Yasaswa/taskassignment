package com.erp.Product_Category5.Controller;

import com.erp.Product_Category5.Model.CProductCategory5Model;
import com.erp.Product_Category5.Service.IProductCategory5Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/productcategory5", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductCategory5Controller {

	@Autowired
	IProductCategory5Service iProductCategory5Service;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductCategory5Model cProductCategory5Model) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iProductCategory5Service.FnAddUpdateRecord(cProductCategory5Model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{product_category5_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_category5_id, @PathVariable String deleted_by) {
		return iProductCategory5Service.FnDeleteRecord(product_category5_id, deleted_by);
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductCategory5ViewModel = null;
		try {
			cProductCategory5ViewModel = iProductCategory5Service.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory5ViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductCategory5ViewModel = null;
		try {
			cProductCategory5ViewModel = iProductCategory5Service.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory5ViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_category5_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_category5_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductCategory5Service.FnShowParticularRecordForUpdate(product_category5_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_category5_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_category5_id)
			throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductCategory5Service.FnShowParticularRecord(company_id, product_category5_id);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductCategory5RptModel = null;
		try {
			cProductCategory5RptModel = iProductCategory5Service.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory5RptModel;

	}
}
