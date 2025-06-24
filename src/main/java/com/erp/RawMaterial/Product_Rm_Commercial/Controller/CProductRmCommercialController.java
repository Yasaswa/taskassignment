package com.erp.RawMaterial.Product_Rm_Commercial.Controller;

import com.erp.RawMaterial.Product_Rm_Commercial.Model.CProductRmCommercialModel;
import com.erp.RawMaterial.Product_Rm_Commercial.Service.IProductRmCommercialService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/productrmcommercial")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductRmCommercialController {

	@Autowired
	IProductRmCommercialService iProductRmCommercialService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CProductRmCommercialModel cProductRmCommercialModel)
			throws SQLException {
		JSONObject responce = new JSONObject();
		responce = iProductRmCommercialService.FnAddUpdateRecord(cProductRmCommercialModel);

		return responce.toString();

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{company_branch_id}/{product_rm_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                              @PathVariable String product_rm_id) throws SQLException {
		JSONObject responce = new JSONObject();

		try {
			responce = iProductRmCommercialService.FnShowParticularRecordForUpdate(company_id, company_branch_id,
					product_rm_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce.toString();

	}

}
