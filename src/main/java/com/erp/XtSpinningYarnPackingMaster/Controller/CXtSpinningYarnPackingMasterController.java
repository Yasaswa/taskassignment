package com.erp.XtSpinningYarnPackingMaster.Controller;

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

import com.erp.XtSpinningYarnPackingMaster.Service.IXtSpinningYarnPackingMasterService;


@RestController
@RequestMapping("/api/XtSpinningYarnPackingMaster")
public class CXtSpinningYarnPackingMasterController {

	@Autowired
	IXtSpinningYarnPackingMasterService iXtSpinningYarnPackingMasterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("SpinningYarnPackingData") JSONObject jsonObject) {
		Map<String, Object> responce = iXtSpinningYarnPackingMasterService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{yarn_packing_master_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int yarn_packing_master_id, @PathVariable int company_id,@PathVariable String deleted_by) {
		Map<String, Object> responce = iXtSpinningYarnPackingMasterService.FnDeleteRecord(yarn_packing_master_id,company_id, deleted_by);
		return responce;

	}


	@GetMapping("/FnShowParticularRecordForUpdate/{yarn_packing_master_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int yarn_packing_master_id, @PathVariable int company_id)  {
		Map<String, Object> responce = iXtSpinningYarnPackingMasterService.FnShowParticularRecordForUpdate(yarn_packing_master_id, company_id);
		return responce;
	}

 
}
