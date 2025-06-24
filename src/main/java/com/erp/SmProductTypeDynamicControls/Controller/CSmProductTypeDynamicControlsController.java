package com.erp.SmProductTypeDynamicControls.Controller;


import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.SmProductTypeDynamicControls.Model.CSmProductTypeDynamicControlsModel;
import com.erp.SmProductTypeDynamicControls.Service.ISmProductTypeDynamicControlsService;


@RestController
@RequestMapping("/api/SmProductTypeDynamicControls")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductTypeDynamicControlsController {

	@Autowired
	ISmProductTypeDynamicControlsService iSmProductTypeDynamicControlsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CSmProductTypeDynamicControlsModel SmProductTypeDynamicControlsModel) {
		return iSmProductTypeDynamicControlsService.FnAddUpdateRecord(SmProductTypeDynamicControlsModel);

	}

	@DeleteMapping("/FnDeleteRecord/{product_type_dynamic_controls_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int product_type_dynamic_controls_id, @PathVariable String deleted_by) {
		return iSmProductTypeDynamicControlsService.FnDeleteRecord(product_type_dynamic_controls_id, deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_type_dynamic_controls_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_type_dynamic_controls_id, @PathVariable int company_id)  {
		return  iSmProductTypeDynamicControlsService.FnShowParticularRecordForUpdate(product_type_dynamic_controls_id, company_id);
	}



 	
 
}
