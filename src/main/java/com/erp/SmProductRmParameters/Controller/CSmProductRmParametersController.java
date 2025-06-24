package com.erp.SmProductRmParameters.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.SmProductRmParameters.Model.CSmProductRmParametersModel;
import com.erp.SmProductRmParameters.Service.ISmProductRmParametersService;


@RestController
@RequestMapping("/api/SmProductRmParameters")
public class CSmProductRmParametersController {

	@Autowired
	ISmProductRmParametersService iSmProductRmParametersService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CSmProductRmParametersModel SmProductRmParametersModel) {
		return iSmProductRmParametersService.FnAddUpdateRecord(SmProductRmParametersModel);

	}

	@DeleteMapping("/FnDeleteRecord/{product_parameter_id}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int product_parameter_id, @PathVariable String deleted_by) {
		return iSmProductRmParametersService.FnDeleteRecord(product_parameter_id, deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_type_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_type_id, @PathVariable int company_id)  {
		return  iSmProductRmParametersService.FnShowParticularRecordForUpdate(product_type_id, company_id);
	}

}
