package com.erp.XmYarnPackingTypes.Controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.XmYarnPackingTypes.Model.CXmYarnPackingTypesModel;
import com.erp.XmYarnPackingTypes.Service.IXmYarnPackingTypesService;


@RestController
@RequestMapping("/api/XmYarnPackingTypes")
public class CXmYarnPackingTypesController {

	@Autowired
	IXmYarnPackingTypesService iXmYarnPackingTypesService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CXmYarnPackingTypesModel XmYarnPackingTypesModel) {
		 Map<String, Object> responce = iXmYarnPackingTypesService.FnAddUpdateRecord(XmYarnPackingTypesModel);
		 return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{yarn_packing_types_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int yarn_packing_types_id, @PathVariable int company_id,@PathVariable String deleted_by) {
		return iXmYarnPackingTypesService.FnDeleteRecord(yarn_packing_types_id, company_id,deleted_by);

	}
	
	@GetMapping("/FnShowParticularRecordForUpdate/{yarn_packing_types_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int yarn_packing_types_id, @PathVariable int company_id)  {
		 Map<String, Object> responce =  iXmYarnPackingTypesService.FnShowParticularRecordForUpdate(yarn_packing_types_id, company_id);
		 return responce;
	}

}
