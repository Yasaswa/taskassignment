package com.erp.SmProductSrQaMapping.Controller;

import com.erp.SmProductSrQaMapping.Model.CSmProductSrQaMappingViewModel;
import com.erp.SmProductSrQaMapping.Service.ISmProductSrQaMappingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/SmProductSrQaMapping")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductSrQaMappingController {

	@Autowired
	ISmProductSrQaMappingService iSmProductSrQaMappingService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("TransDetailData") JSONObject jsonObject) {
		Map<String, Object> responce = iSmProductSrQaMappingService.FnAddUpdateRecord(jsonObject);
		return responce;

	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CSmProductSrQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CSmProductSrQaMappingViewModel> cSmProductSrQaMappingViewModel = iSmProductSrQaMappingService.FnShowAllActiveRecords(pageable, company_id);
		return cSmProductSrQaMappingViewModel;
	}


	@GetMapping("/FnShowParticularRecords/{product_sr_id}/{company_id}")
	public Page<CSmProductSrQaMappingViewModel> FnShowParticularRecord(@PathVariable int product_sr_id, Pageable pageable, @PathVariable int company_id) {
		Page<CSmProductSrQaMappingViewModel> cSmProductSrQaMappingViewModel = iSmProductSrQaMappingService.FnShowParticularRecord(product_sr_id, pageable, company_id);
		return cSmProductSrQaMappingViewModel;

	}


}
