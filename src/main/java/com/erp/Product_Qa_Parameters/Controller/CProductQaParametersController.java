package com.erp.Product_Qa_Parameters.Controller;

import com.erp.Product_Qa_Parameters.Model.CProductQaParametersModel;
import com.erp.Product_Qa_Parameters.Service.IProductQaParametersService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/productqaparameters", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductQaParametersController {


	@Autowired
	IProductQaParametersService iProductQaParametersService;


	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductQaParametersModel cProductQaParametersModel) throws SQLException {

		JSONObject resp = new JSONObject();

		try {
			resp = iProductQaParametersService.FnAddUpdateRecord(cProductQaParametersModel);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{product_qa_parameters_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_qa_parameters_id, @PathVariable String deleted_by) {
		return iProductQaParametersService.FnDeleteRecord(product_qa_parameters_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductQaParametersViewModel = null;
		try {
			cProductQaParametersViewModel = iProductQaParametersService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductQaParametersViewModel.toString();
	}


	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductQaParametersViewModel = null;
		try {
			cProductQaParametersViewModel = iProductQaParametersService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductQaParametersViewModel.toString();
	}


	@GetMapping("/FnShowParticularRecordForUpdate/{product_qa_parameters_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_qa_parameters_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductQaParametersService.FnShowParticularRecordForUpdate(product_qa_parameters_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{product_qa_parameters_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_qa_parameters_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductQaParametersService.FnShowParticularRecord(company_id, product_qa_parameters_id);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductQaParametersRptModel = null;
		try {
			cProductQaParametersRptModel = iProductQaParametersService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductQaParametersRptModel;

	}

}
