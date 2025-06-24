package com.erp.SmProductCodification.Controller;


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

import com.erp.SmProductCodification.Model.CSmProductCodificationModel;

import com.erp.SmProductCodification.Service.ISmProductCodificationService;


@RestController
@RequestMapping("/api/SmProductCodification")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductCodificationController {

	@Autowired
	ISmProductCodificationService iSmProductCodificationService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CSmProductCodificationModel SmProductCodificationModel) {
		Map<String, Object> responce =  iSmProductCodificationService.FnAddUpdateRecord(SmProductCodificationModel);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{product_codification_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int product_codification_id, @PathVariable String deleted_by) {
		Map<String, Object> responce = iSmProductCodificationService.FnDeleteRecord(product_codification_id, deleted_by);
		return responce;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{product_codification_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int product_codification_id, @PathVariable int company_id)  {
		Map<String, Object> responce =  iSmProductCodificationService.FnShowParticularRecordForUpdate(product_codification_id, company_id);
		return responce;
	}

	

 	
 
}
