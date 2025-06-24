package com.erp.Product_Grade.Controller;

import com.erp.Product_Grade.Model.CProductGradeModel;
import com.erp.Product_Grade.Service.IProductGradeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/productgrade", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductGradeController {

	@Autowired
	IProductGradeService iProductGradeService;


	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestBody CProductGradeModel cProductGradeModel) throws SQLException {

		JSONObject resp = new JSONObject();

		try {
			resp = iProductGradeService.FnAddUpdateRecord(cProductGradeModel);
			return resp.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	/* Added By Dipti (ERP DB Testing 1.1) */

//	@PostMapping("/FnAddUpdateRecord")
//	public String FnAddUpdateRecord(@RequestBody CProductMaterialGradeModel cProductMaterialGradeModel) throws SQLException {
//
//		JSONObject resp = new JSONObject();
//
//		try {
//			resp = iProductGradeService.FnAddUpdateRecord(cProductMaterialGradeModel);
//			return resp.toString();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return resp.toString();
//
//	}
	/* Added By Dipti (ERP DB Testing 1.1) */


	@DeleteMapping("/FnDeleteRecord/{product_grade_id}")
	public Object FnDeleteRecord(@PathVariable int product_grade_id) {
		return iProductGradeService.FnDeleteRecord(product_grade_id);

	}

	/* Added By Dipti (ERP DB Testing 1.1) */

//	@DeleteMapping("/FnDeleteRecord/{product_material_grade_id}")
//	public Object FnDeleteRecord(@PathVariable int product_material_grade_id) {
//		return iProductGradeService.FnDeleteRecord(product_material_grade_id);
//
//	}
//
	/* Added By Dipti (ERP DB Testing 1.1) */

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductGradeViewModel = null;
		try {
			cProductGradeViewModel = iProductGradeService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductGradeViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductGradeViewModel = null;
		try {
			cProductGradeViewModel = iProductGradeService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductGradeViewModel;
	}


	@GetMapping("/FnShowParticularRecord/{company_id}/{product_grade_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_grade_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductGradeService.FnShowParticularRecord(company_id, product_grade_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();


	}


	@GetMapping("/FnShowParticularRecordForUpdate/{product_rm_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_rm_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductGradeService.FnShowParticularRecordForUpdate(product_rm_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}


	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductCategory1RptModel = null;
		try {
			cProductCategory1RptModel = iProductGradeService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductCategory1RptModel;

	}


}
