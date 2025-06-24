package com.erp.MtServiceReportingMaster.Controller;


import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.MtServiceReportingMaster.Service.IMtServiceReportingMasterService;


@RestController
@RequestMapping("/api/MtServiceReportingMaster")
public class CMtServiceReportingMasterController {

	@Autowired
	IMtServiceReportingMasterService iMtServiceReportingMasterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("ServiceReportData") JSONObject jsonObject) {
		Map<String, Object> responce = iMtServiceReportingMasterService.FnAddUpdateRecord(jsonObject); 
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{service_reporting_master_transaction_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int service_reporting_master_transaction_id, @PathVariable int company_id ,@PathVariable String deleted_by) {
		return iMtServiceReportingMasterService.FnDeleteRecord(service_reporting_master_transaction_id, company_id,deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{service_reporting_master_transaction_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int service_reporting_master_transaction_id, @PathVariable int company_id)  {
		Map<String, Object> responce = iMtServiceReportingMasterService.FnShowParticularRecordForUpdate(service_reporting_master_transaction_id, company_id);
		return responce;
	}

 
}
