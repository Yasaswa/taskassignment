package com.erp.XmWeavingProductionPlanMaster.Controller;


import java.util.Map;



import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.erp.XmWeavingProductionPlanMaster.Service.IXmWeavingProductionPlanMasterService;


@RestController
@RequestMapping("/api/XmWeavingProductionPlanMaster")
public class CXmWeavingProductionPlanMasterController {

	@Autowired
	IXmWeavingProductionPlanMasterService iXmWeavingProductionPlanMasterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("WeavingProdSpecSheet") JSONObject jsonObject) {
		return iXmWeavingProductionPlanMasterService.FnAddUpdateRecord(jsonObject);

	}

	@DeleteMapping("/FnDeleteRecord/{weaving_production_plan_master_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_production_plan_master_id, @PathVariable int company_id,@PathVariable String deleted_by) {
		return iXmWeavingProductionPlanMasterService.FnDeleteRecord(weaving_production_plan_master_id, company_id,deleted_by);

	}
	

	@GetMapping("/FnShowParticularRecordForUpdate/{weaving_production_plan_master_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int weaving_production_plan_master_id, @PathVariable int company_id)  {
		Map<String, Object> responce =  iXmWeavingProductionPlanMasterService.FnShowParticularRecordForUpdate(weaving_production_plan_master_id, company_id);
		return responce;
	}

}
