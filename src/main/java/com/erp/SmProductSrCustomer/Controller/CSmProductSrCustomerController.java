package com.erp.SmProductSrCustomer.Controller;

import com.erp.SmProductSrCustomer.Model.CSmProductSrCustomerViewModel;
import com.erp.SmProductSrCustomer.Service.ISmProductSrCustomerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/SmProductSrCustomer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductSrCustomerController {

	@Autowired
	ISmProductSrCustomerService iSmProductSrCustomerService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("SrCustomerData") JSONObject jsonObject) {
		Map<String, Object> responce = iSmProductSrCustomerService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CSmProductSrCustomerViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
			throws SQLException {
		Page<CSmProductSrCustomerViewModel> cSmProductSrCustomerViewModel = iSmProductSrCustomerService
				.FnShowAllActiveRecords(pageable, company_id);
		return cSmProductSrCustomerViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{product_sr_id}/{company_id}")
	public Page<CSmProductSrCustomerViewModel> FnShowParticularRecord(@PathVariable int product_sr_id,
	                                                                  Pageable pageable, @PathVariable int company_id) {
		Page<CSmProductSrCustomerViewModel> cSmProductSrCustomerViewModel = iSmProductSrCustomerService
				.FnShowParticularRecord(product_sr_id, pageable, company_id);
		return cSmProductSrCustomerViewModel;

	}

}
