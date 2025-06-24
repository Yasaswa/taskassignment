package com.erp.SmProductSrProcess.Controller;

import com.erp.SmProductSrProcess.Model.CSmProductSrProcessViewModel;
import com.erp.SmProductSrProcess.Service.ISmProductSrProcessService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/SmProductSrProcess")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductSrProcessController {

	@Autowired
	ISmProductSrProcessService iSmProductSrProcessService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("SrServiceData") JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		responce = iSmProductSrProcessService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CSmProductSrProcessViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
			throws SQLException {
		Page<CSmProductSrProcessViewModel> cSmProductSrProcessViewModel = iSmProductSrProcessService
				.FnShowAllActiveRecords(pageable, company_id);
		return cSmProductSrProcessViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{product_sr_id}/{company_id}")
	public Page<CSmProductSrProcessViewModel> FnShowParticularRecord(@PathVariable int product_sr_id, Pageable pageable,
	                                                                 @PathVariable int company_id) {
		Page<CSmProductSrProcessViewModel> cSmProductSrProcessViewModel = iSmProductSrProcessService
				.FnShowParticularRecord(product_sr_id, pageable, company_id);
		return cSmProductSrProcessViewModel;

	}

}
