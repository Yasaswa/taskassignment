package com.erp.Product_Make.Controller;

import com.erp.Product_Make.Model.CProductMakeModel;
import com.erp.Product_Make.Service.IProductMakeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/productmake")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductMakeController {

	@Autowired
	IProductMakeService iProductMakeService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductMakeModel cProductMakeModel) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iProductMakeService.FnAddUpdateRecord(cProductMakeModel);
			System.out.println("Responce: " + resp.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{product_make_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_make_id, @PathVariable String deleted_by) {
		return iProductMakeService.FnDeleteRecord(product_make_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductMakeViewModel = null;
		try {
			cProductMakeViewModel = iProductMakeService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMakeViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductMakeViewModel = null;
		try {
			cProductMakeViewModel = iProductMakeService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMakeViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_make_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_make_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductMakeService.FnShowParticularRecordForUpdate(product_make_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_make_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_make_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {

			resp = iProductMakeService.FnShowParticularRecord(company_id, product_make_id);
			System.out.println("Responce: " + resp.toString());


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductMakeRptModel = null;
		try {
			cProductMakeRptModel = iProductMakeService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductMakeRptModel;

	}
}
