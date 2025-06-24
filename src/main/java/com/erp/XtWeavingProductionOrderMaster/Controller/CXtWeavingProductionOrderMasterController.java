package com.erp.XtWeavingProductionOrderMaster.Controller;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.XtWeavingProductionOrderMaster.Service.IXtWeavingProductionOrderMasterService;


@RestController
@RequestMapping("/api/XtWeavingProductionOrder")
public class CXtWeavingProductionOrderMasterController {

	@Autowired
	IXtWeavingProductionOrderMasterService iXtWeavingProductionOrderMasterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("WeavingProductionOrderData") JSONObject jsonObject) throws JsonProcessingException {
		Map<String, Object> responce = iXtWeavingProductionOrderMasterService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{weaving_production_order_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_production_order_id, @PathVariable String deleted_by) {
		return iXtWeavingProductionOrderMasterService.FnDeleteRecord(weaving_production_order_id, deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{weaving_production_order_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int weaving_production_order_id, @PathVariable int company_id)  {
		Map<String, Object> responce =  iXtWeavingProductionOrderMasterService.FnShowParticularRecordForUpdate(weaving_production_order_id, company_id);
		return responce; 
	}

	@GetMapping("/GetLastRecordSetNoWeavingProductionOrder")
	public Map<String, Object> GetLastSetNoWeavingProductionOrder()  {
		Map<String, Object> responce =  iXtWeavingProductionOrderMasterService.GetLastSetNoWeavingProductionOrder();
		return responce; 
	}

	@DeleteMapping("/FnDeletePerticularMaterialRecord/{production_order_material_id}/{deleted_by}")
	public Map<String, Object> FnDeletePerticularMaterialRecord(@PathVariable int production_order_material_id, @PathVariable String deleted_by) {
		return iXtWeavingProductionOrderMasterService.FnDeletePerticularMaterialRecord(production_order_material_id, deleted_by);

	}
 
	@PostMapping("/FnAddMaterialRecord")
	public Map<String, Object> FnAddMaterialRecord(@RequestParam("WeavingProductionMaterialData") JSONObject jsonObject) {
		Map<String, Object> responce = iXtWeavingProductionOrderMasterService.FnAddMaterialRecord(jsonObject);
		return responce;

	}
	
}
