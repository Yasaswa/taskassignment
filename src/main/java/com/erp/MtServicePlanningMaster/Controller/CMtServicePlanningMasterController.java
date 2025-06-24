package com.erp.MtServicePlanningMaster.Controller;


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

import com.erp.MtServicePlanningMaster.Service.IMtServicePlanningMasterService;


@RestController
@RequestMapping("/api/MtServicePlanningMaster")
public class CMtServicePlanningMasterController {

	@Autowired
	IMtServicePlanningMasterService iMtServicePlanningMasterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("ServicePlanningMasterData") JSONObject jsonObject) {
		Map<String, Object> responce = iMtServicePlanningMasterService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{service_planning_master_transaction_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int service_planning_master_transaction_id, @PathVariable String deleted_by) {
		return iMtServicePlanningMasterService.FnDeleteRecord(service_planning_master_transaction_id, deleted_by);

	}
	
	@GetMapping("/FnShowParticularRecordForUpdate/{service_planning_master_transaction_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int service_planning_master_transaction_id, @PathVariable int company_id) {
		Map<String, Object> responce =  iMtServicePlanningMasterService.FnShowParticularRecordForUpdate(service_planning_master_transaction_id,company_id);
		return responce;
	}

}
