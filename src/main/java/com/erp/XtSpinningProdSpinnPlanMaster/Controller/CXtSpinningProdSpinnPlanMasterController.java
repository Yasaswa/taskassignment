package com.erp.XtSpinningProdSpinnPlanMaster.Controller;

import com.erp.XtSpinningProdSpinnPlanMaster.Service.IXtSpinningProdSpinnPlanMasterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/XtSpinningProdSpinnPlanMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXtSpinningProdSpinnPlanMasterController {

	@Autowired
	IXtSpinningProdSpinnPlanMasterService iXtSpinningProdSpinnPlanMasterService;

	@PostMapping("/FnAddUpdateRecord/{ACFlag}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("SppiningProdAndPlantProdData") JSONObject jsonObject, @PathVariable String ACFlag) {
		return iXtSpinningProdSpinnPlanMasterService.FnAddUpdateRecord(jsonObject, ACFlag);

	}

	@DeleteMapping("/FnDeleteRecord/{spinn_plan_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int spinn_plan_id, @PathVariable String deleted_by) {
		return iXtSpinningProdSpinnPlanMasterService.FnDeleteRecord(spinn_plan_id, deleted_by);

	}

	@PostMapping("/FnShowMasterAndDetailsModelRecordForUpdate")
	public Map<String, Object> FnShowMasterAndDetailsModelRecordForUpdate(@RequestParam("SppiningProdAndPlantProdDetails") JSONObject jsonObject) {
		Map<String, Object> responce = iXtSpinningProdSpinnPlanMasterService.FnShowMasterAndDetailsModelRecordForUpdate(jsonObject);
		return responce;
	}


}
