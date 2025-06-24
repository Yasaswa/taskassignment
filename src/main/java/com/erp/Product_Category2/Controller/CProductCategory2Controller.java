package com.erp.Product_Category2.Controller;

import com.erp.Product_Category2.Model.CProductCategory2Model;
import com.erp.Product_Category2.Service.IProductCategory2Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/productcategory2", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductCategory2Controller {

	@Autowired
	IProductCategory2Service iProductCategory2Service;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductCategory2Model cProductCategory2Model) throws SQLException {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject resp = new JSONObject();

		try {
			resp = iProductCategory2Service.FnAddUpdateRecord(cProductCategory2Model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}


	@DeleteMapping("/FnDeleteRecord/{product_category2_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_category2_id, @PathVariable String deleted_by) {
		return iProductCategory2Service.FnDeleteRecord(product_category2_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductCategory2ViewModel = null;
		try {
			cProductCategory2ViewModel = iProductCategory2Service.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory2ViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductCategory2ViewModel = null;
		try {
			cProductCategory2ViewModel = iProductCategory2Service.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory2ViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_category2_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_category2_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductCategory2Service.FnShowParticularRecordForUpdate(product_category2_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_category2_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_category2_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductCategory2Service.FnShowParticularRecord(company_id, product_category2_id);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductCategory2RptModel = null;
		try {
			cProductCategory2RptModel = iProductCategory2Service.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory2RptModel;

	}
}
