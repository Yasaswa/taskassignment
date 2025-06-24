package com.erp.SmProductSrSupplier.Controller;

import com.erp.SmProductSrSupplier.Model.CSmProductSrSupplierViewModel;
import com.erp.SmProductSrSupplier.Service.ISmProductSrSupplierService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/SmProductSrSupplier")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductSrSupplierController {

	@Autowired
	ISmProductSrSupplierService iSmProductSrSupplierService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("") JSONObject jsonObject) {
		Map<String, Object> responce = iSmProductSrSupplierService.FnAddUpdateRecord(jsonObject);
		return responce;

	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CSmProductSrSupplierViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CSmProductSrSupplierViewModel> cSmProductSrSupplierViewModel = iSmProductSrSupplierService.FnShowAllActiveRecords(pageable, company_id);
		return cSmProductSrSupplierViewModel;
	}


	@GetMapping("/FnShowParticularRecords/{product_sr_id}/{company_id}")
	public Page<CSmProductSrSupplierViewModel> FnShowParticularRecord(@PathVariable int product_sr_id, Pageable pageable, @PathVariable int company_id) {
		Page<CSmProductSrSupplierViewModel> cSmProductSrSupplierViewModel = iSmProductSrSupplierService.FnShowParticularRecord(product_sr_id, pageable, company_id);
		return cSmProductSrSupplierViewModel;

	}


}
