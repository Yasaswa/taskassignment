package com.erp.FmCostCenterHeads.Controller;

import com.erp.FmCostCenterHeads.Model.CFmCostCenterHeadsModel;
import com.erp.FmCostCenterHeads.Service.IFmCostCenterHeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/FmCostCenterHeads")
public class CFmCostCenterHeadsController {

	@Autowired
	IFmCostCenterHeadsService iFmCostCenterHeadsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CFmCostCenterHeadsModel costCenterHeadRequest) {
		return iFmCostCenterHeadsService.FnAddUpdateRecord(costCenterHeadRequest);

	}

	@DeleteMapping("/FnDeleteRecord/{cost_center_heads_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int cost_center_heads_id,@PathVariable int company_id,@PathVariable String deleted_by) {
		return iFmCostCenterHeadsService.FnDeleteRecord(cost_center_heads_id,company_id,deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{cost_center_heads_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int cost_center_heads_id) {
		return iFmCostCenterHeadsService.FnShowParticularRecordForUpdate(cost_center_heads_id);
	}

}
