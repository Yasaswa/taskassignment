package com.erp.RawMaterial.SmProductRmQaMapping.Controller;

import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingModel;
import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingViewModel;
import com.erp.RawMaterial.SmProductRmQaMapping.Service.ISmProductRmQaMappingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/SmProductRmQaMapping")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductRmQaMappingController {

	@Autowired
	ISmProductRmQaMappingService iSmProductRmQaMappingService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestParam("productQaMappingJson") JSONObject jsonObject) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iSmProductRmQaMappingService.FnAddUpdateRecord(jsonObject);

		return resp.toString();

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	Page<CSmProductRmQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
			throws SQLException {
		Page<CSmProductRmQaMappingViewModel> cSmProductRmQaMappingViewModel = iSmProductRmQaMappingService
				.FnShowAllActiveRecords(pageable, company_id);
		return cSmProductRmQaMappingViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{product_rm_id}/{company_id}")
	public Page<CSmProductRmQaMappingModel> FnShowParticularRecord(@PathVariable int product_rm_id, Pageable pageable,
	                                                               @PathVariable int company_id) throws SQLException {
		Page<CSmProductRmQaMappingModel> cSmProductRmQaMappingViewModel = iSmProductRmQaMappingService
				.FnShowParticularRecord(product_rm_id, pageable, company_id);
		return cSmProductRmQaMappingViewModel;

	}

}
