package com.erp.SmProductPrParameters.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.SmProductPrParameters.Model.CSmProductPrParametersModel;
import com.erp.SmProductPrParameters.Service.ISmProductPrParametersService;


@RestController
@RequestMapping("/api/SmProductPrParameters")
public class CSmProductPrParametersController {

	@Autowired
	ISmProductPrParametersService iSmProductPrParametersService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CSmProductPrParametersModel SmProductPrParametersModel) {
		Map<String, Object> responce = iSmProductPrParametersService.FnAddUpdateRecord(SmProductPrParametersModel);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{product_parameter_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int product_parameter_id, @PathVariable String deleted_by) {
		return iSmProductPrParametersService.FnDeleteRecord(product_parameter_id, deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_type_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_type_id, @PathVariable int company_id)  {
		return  iSmProductPrParametersService.FnShowParticularRecordForUpdate(product_type_id, company_id);
	}
 	
 
}
