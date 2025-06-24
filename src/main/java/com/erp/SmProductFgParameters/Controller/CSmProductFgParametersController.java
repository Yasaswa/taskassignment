package com.erp.SmProductFgParameters.Controller;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.SmProductFgParameters.Model.CSmProductFgParametersModel;
import com.erp.SmProductFgParameters.Service.ISmProductFgParametersService;

@RestController
@RequestMapping("/api/SmProductFgParameters")
public class CSmProductFgParametersController {

	@Autowired
	ISmProductFgParametersService iSmProductFgParametersService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CSmProductFgParametersModel SmProductFgParametersModel) {
		Map<String, Object> responce = iSmProductFgParametersService.FnAddUpdateRecord(SmProductFgParametersModel);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{product_parameter_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int product_parameter_id, @PathVariable String deleted_by) {
		return iSmProductFgParametersService.FnDeleteRecord(product_parameter_id, deleted_by);

	}


	@GetMapping("/FnShowParticularRecordForUpdate/{product_type_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_type_id, @PathVariable int company_id)  {
		 Map<String, Object> responce = iSmProductFgParametersService.FnShowParticularRecordForUpdate(product_type_id, company_id);
		 return responce;
	}


 	
 
}
