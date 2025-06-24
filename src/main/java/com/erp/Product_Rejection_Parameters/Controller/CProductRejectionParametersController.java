package com.erp.Product_Rejection_Parameters.Controller;

import com.erp.Product_Rejection_Parameters.Model.CProductRejectionParametersModel;
import com.erp.Product_Rejection_Parameters.Service.IProductRejectionParametersService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/api/Productrejectionparameter", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductRejectionParametersController {

	@Autowired
	IProductRejectionParametersService iProductRejectionParametersService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductRejectionParametersModel cProductRejectionParametersModel) throws SQLException {

		JSONObject resp = new JSONObject();

		try {
			resp = iProductRejectionParametersService.FnAddUpdateRecord(cProductRejectionParametersModel);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{product_rejection_parameters_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int product_rejection_parameters_id, @PathVariable String deleted_by) {
		return iProductRejectionParametersService.FnDeleteRecord(product_rejection_parameters_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductRejectionParametersViewModel = null;
		try {
			cProductRejectionParametersViewModel = iProductRejectionParametersService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRejectionParametersViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cProductRejectionParametersViewModel = null;
		try {
			cProductRejectionParametersViewModel = iProductRejectionParametersService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRejectionParametersViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_rejection_parameters_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int product_rejection_parameters_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductRejectionParametersService.FnShowParticularRecordForUpdate(product_rejection_parameters_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}


	@GetMapping("/FnShowParticularRecord/{company_id}/{product_rejection_parameters_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int product_rejection_parameters_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductRejectionParametersService.FnShowParticularRecord(company_id, product_rejection_parameters_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();


	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cProductRejectionParametersRptModel = null;
		try {
			cProductRejectionParametersRptModel = iProductRejectionParametersService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRejectionParametersRptModel;

	}

}
