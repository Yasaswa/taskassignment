package com.erp.RawMaterial.Product_Rm_Process.Controller;

import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessModel;
import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessViewModel;
import com.erp.RawMaterial.Product_Rm_Process.Service.IProductRmProcessService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/productrmprocess")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CProductRmProcessController {

	@Autowired
	private IProductRmProcessService iProductRmProcessService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestParam("productProcessJson") JSONObject jsonObject) throws SQLException {
		JSONObject responce = new JSONObject();
		responce = iProductRmProcessService.FnAddUpdateRecord(jsonObject);

		return responce.toString();

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public List<CProductRmProcessViewModel> FnShowAllActiveRecords(@PathVariable int company_id) throws SQLException {
		List<CProductRmProcessViewModel> cProductRmProcessViewModel = iProductRmProcessService
				.FnShowAllActiveRecords(company_id);

		return cProductRmProcessViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{product_rm_id}/{company_id}")
	public List<CProductRmProcessModel> FnShowParticularRecord(@PathVariable int product_rm_id,
	                                                           @PathVariable int company_id) throws SQLException {
		List<CProductRmProcessModel> cProductRmProcessModel = iProductRmProcessService
				.FnShowParticularRecord(product_rm_id, company_id);
		return cProductRmProcessModel;
	}

}
