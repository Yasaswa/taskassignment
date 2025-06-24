package com.erp.HtSalarySummaryNew.Controller;

import com.erp.HtSalarySummaryNew.Service.IHtSalarySummaryNewService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/HtSalarySummaryNew")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHtSalarySummaryNewController {

	@Autowired
	IHtSalarySummaryNewService iHtSalarySummaryNewService;

	@PostMapping("/FnAddUpdateRecord/{company_id}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("MtSalaryProcessingData") JSONObject MtSalaryProcessingData, @PathVariable int company_id) {
		return iHtSalarySummaryNewService.FnAddUpdateRecord(MtSalaryProcessingData, company_id);
	}

	@DeleteMapping("/FnDeleteRecord/{salary_transaction_id}/{company_id}")
	public Object FnDeleteRecord(@PathVariable int salary_transaction_id, @PathVariable int company_id) {
		return iHtSalarySummaryNewService.FnDeleteRecord(salary_transaction_id, company_id);
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{salary_transaction_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int salary_transaction_id, @PathVariable int company_id) {
		return iHtSalarySummaryNewService.FnShowParticularRecordForUpdate(salary_transaction_id, company_id);
	}

	@PostMapping("/FnDisplaySalariesCalculations/{company_id}")
	public Map<String, Object> FnDisplaySalariesCalculations(@RequestParam("MtSalaryProcessingFilters") JSONObject MtSalaryProcessingFilters, @PathVariable int company_id) {
		return iHtSalarySummaryNewService.FnDisplaySalariesCalculations(MtSalaryProcessingFilters, company_id);
	}

}
