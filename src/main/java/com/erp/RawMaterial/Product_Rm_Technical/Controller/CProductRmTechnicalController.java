package com.erp.RawMaterial.Product_Rm_Technical.Controller;

import com.erp.RawMaterial.Product_Rm_Technical.Model.CProductRmTechnicalModel;
import com.erp.RawMaterial.Product_Rm_Technical.Service.IProductRmTechnicalService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/productrmtechnical")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductRmTechnicalController {

	@Autowired
	IProductRmTechnicalService iProductRmTechnicalService;

	@Autowired
	DataSource dataSource;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductRmTechnicalModel cProductRmTechnicalModel)
			throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iProductRmTechnicalService.FnAddUpdateRecord(cProductRmTechnicalModel);

		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{product_rm_id}")
	public Object FnDeleteRecord(@PathVariable int product_rm_id) {
		return iProductRmTechnicalService.FnDeleteRecord(product_rm_id);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cProductRmTechnicalViewModel = null;
		try {
			cProductRmTechnicalViewModel = iProductRmTechnicalService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRmTechnicalViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		JSONObject resp = new JSONObject();
		Object cProductRmTechnicalViewModel = null;
		try {
			cProductRmTechnicalViewModel = iProductRmTechnicalService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cProductRmTechnicalViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{product_rm_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int product_rm_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iProductRmTechnicalService.FnShowParticularRecord(company_id, company_branch_id, product_rm_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{company_branch_id}/{product_rm_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                              @PathVariable int product_rm_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iProductRmTechnicalService.FnShowParticularRecordForUpdate(company_id, company_branch_id,
					product_rm_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

}
