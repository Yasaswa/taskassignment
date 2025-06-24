package com.erp.XmProductionSection.Controller;

import com.erp.XmProductionSection.Model.CXmProductionSectionModel;
import com.erp.XmProductionSection.Model.CXmProductionSubSectionModel;
import com.erp.XmProductionSection.Service.IXmProductionSectionService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/XmProductionSection")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXmProductionSectionController {

	@Autowired
	IXmProductionSectionService iXmProductionSectionService;

	@PostMapping("/FnAddUpdateProdSectionRecord")
	public Map<String, Object> FnAddUpdateProdSectionRecord(@RequestBody CXmProductionSectionModel XmProductionSectionModel) {
		Map<String, Object> responce = iXmProductionSectionService.FnAddUpdateProdSectionRecord(XmProductionSectionModel);
		return responce;
	}

	
	@DeleteMapping("/FnDeleteProdSectionRecord/{production_section_id}/{deleted_by}")
	public Map<String, Object> FnDeleteProdSectionRecord(@PathVariable int production_section_id, @PathVariable String deleted_by) {
		return iXmProductionSectionService.FnDeleteProdSectionRecord(production_section_id, deleted_by);

	}
	
	@GetMapping("/FnShowParticularProdSectionRecordForUpdate/{production_section_id}/{company_id}")
	public Map<String, Object> FnShowParticularProdSectionRecordForUpdate(@PathVariable int production_section_id, @PathVariable int company_id) {
		return iXmProductionSectionService.FnShowParticularProdSectionRecordForUpdate(production_section_id, company_id);
	}

	/* *********************************************************Production Sub Section Api Start******************************************************************************* */
	

	@PostMapping("/FnAddUpdateProdSubSectionAndGodownMappingRecord")
	public Map<String, Object> FnAddUpdateProdSubSectionAndGodownMappingRecord(@RequestParam("ProductionSubSectionData") JSONObject jsonObject) {
		Map<String, Object> responce = iXmProductionSectionService.FnAddUpdateProdSubSectionAndGodownMappingRecord(jsonObject);
		return responce;
	}

	@DeleteMapping("/FnDeleteProdSubSectionRecord/{production_sub_section_id}/{deleted_by}")
	public Map<String, Object> FnDeleteProdSubSectionRecord(@PathVariable int production_sub_section_id, @PathVariable String deleted_by) {
		return iXmProductionSectionService.FnDeleteProdSubSectionRecord(production_sub_section_id, deleted_by);

	}

	@GetMapping("/FnShowParticularProdSubSectionRecordForUpdate/{production_sub_section_id}/{company_id}")
	public Map<String, Object> FnShowParticularProdSubSectionRecordForUpdate(@PathVariable int production_sub_section_id, @PathVariable int company_id) {
		return iXmProductionSectionService.FnShowParticularProdSubSectionRecordForUpdate(production_sub_section_id, company_id);
	}


}
