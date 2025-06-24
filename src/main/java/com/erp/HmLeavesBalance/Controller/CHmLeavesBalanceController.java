package com.erp.HmLeavesBalance.Controller;

import com.erp.HmLeavesBalance.Service.IHmLeavesBalanceService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/HmLeavesBalance")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmLeavesBalanceController {

	@Autowired
	IHmLeavesBalanceService iHmLeavesBalanceService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("HmLeavesBalanceData") JSONObject jsonObject) {
		Map<String, Object> resp = new HashMap<>();
		resp = iHmLeavesBalanceService.FnAddUpdateRecord(jsonObject);
		return resp;
	}

	@DeleteMapping("/FnDeleteRecord/{leaves_balance_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int leaves_balance_id, @PathVariable String deleted_by) {
		Map<String, Object> resp = iHmLeavesBalanceService.FnDeleteRecord(leaves_balance_id, deleted_by);
		return resp;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{leaves_balance_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id,
	                                                           @PathVariable int leaves_balance_id) {
		return iHmLeavesBalanceService.FnShowParticularRecordForUpdate(company_id, leaves_balance_id);
	}
}
