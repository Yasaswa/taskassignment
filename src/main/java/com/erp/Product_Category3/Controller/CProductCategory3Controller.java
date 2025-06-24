package com.erp.Product_Category3.Controller;

import com.erp.Product_Category3.Model.CProductCategory3Model;
import com.erp.Product_Category3.Service.IProductCategory3Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/productcategory3")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductCategory3Controller {

	@Autowired
	IProductCategory3Service iProductCategory3Service;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductCategory3Model cProductCategory3Model) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iProductCategory3Service.FnAddUpdateRecord(cProductCategory3Model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{product_category3_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_category3_id, @PathVariable String deleted_by) {
		return iProductCategory3Service.FnDeleteRecord(product_category3_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductCategory3ViewModel = null;
		try {
			cProductCategory3ViewModel = iProductCategory3Service.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory3ViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductCategory3ViewModel = null;
		try {
			cProductCategory3ViewModel = iProductCategory3Service.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory3ViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_category3_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_category3_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductCategory3Service.FnShowParticularRecordForUpdate(product_category3_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_category3_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_category3_id)
			throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductCategory3Service.FnShowParticularRecord(company_id, product_category3_id);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductCategory3RptModel = null;
		try {
			cProductCategory3RptModel = iProductCategory3Service.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory3RptModel;

	}
}
