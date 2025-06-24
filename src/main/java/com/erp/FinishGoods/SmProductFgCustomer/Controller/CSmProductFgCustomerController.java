package com.erp.FinishGoods.SmProductFgCustomer.Controller;

import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerModel;
import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerViewModel;
import com.erp.FinishGoods.SmProductFgCustomer.Service.ISmProductFgCustomerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/SmProductFgCustomer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgCustomerController {

	@Autowired
	ISmProductFgCustomerService iSmProductFgCustomerService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestParam("productFgCustomerJson") JSONObject jsonObject) throws SQLException {
		JSONObject responce = new JSONObject();
		try {
			responce = iSmProductFgCustomerService.FnAddUpdateRecord(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce.toString();

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	Page<CSmProductFgCustomerViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CSmProductFgCustomerViewModel> cSmProductFgCustomerViewModel = iSmProductFgCustomerService.FnShowAllActiveRecords(pageable, company_id);
		return cSmProductFgCustomerViewModel;
	}


	@GetMapping("/FnShowParticularRecords/{product_fg_id}")
	public Page<CSmProductFgCustomerModel> FnShowParticularRecord(@PathVariable int product_fg_id, Pageable pageable)
			throws SQLException {
		Page<CSmProductFgCustomerModel> cSmProductFgCustomerModel = null;
		try {
			cSmProductFgCustomerModel = iSmProductFgCustomerService.FnShowParticularRecord(product_fg_id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSmProductFgCustomerModel;

	}

	@GetMapping("/FnShowAllActiveRecordsCustomersFG/{customer_id}/{company_id}")
	Page<CSmProductFgCustomerViewModel> FnShowAllActiveRecordsCustomersFG(Pageable pageable, @PathVariable int company_id, @PathVariable int customer_id) throws SQLException {
		Page<CSmProductFgCustomerViewModel> cSmProductFgCustomerViewModel = iSmProductFgCustomerService.FnShowAllActiveRecordsCustomersFG(pageable, company_id, customer_id);
		return cSmProductFgCustomerViewModel;
	}


}
