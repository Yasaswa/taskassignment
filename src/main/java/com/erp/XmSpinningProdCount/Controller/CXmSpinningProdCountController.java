package com.erp.XmSpinningProdCount.Controller;


import com.erp.XmSpinningProdCount.service.IXmSpinningProdCountService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/XmSpinningProdCount")
public class CXmSpinningProdCountController {

	@Autowired
	IXmSpinningProdCountService iXmSpinningProdCountService;


	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("SpinningProdCountData") JSONObject jsonObject) {
		Map<String, Object> responce =  iXmSpinningProdCountService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{production_count_id}/{deleted_by}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int production_count_id, @PathVariable String deleted_by, @PathVariable int company_id) {
		return iXmSpinningProdCountService.FnDeleteRecord(production_count_id, deleted_by, company_id);
	}
	

	@GetMapping("/FnShowParticularRecordForUpdate/{production_count_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int production_count_id, @PathVariable int company_id) {
		Map<String, Object> responce =   iXmSpinningProdCountService.FnShowParticularRecordForUpdate(production_count_id, company_id);
		return responce;
	}
}
