package com.erp.SmProductTypeAccordianAccess.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.SmProductTypeAccordianAccess.Service.ISmProductTypeAccordianAccessService;

@RestController
@RequestMapping("/api/SmProductTypeAccordianAccess")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductTypeAccordianAccessController {

	@Autowired
	ISmProductTypeAccordianAccessService iSmProductTypeAccordianAccessService;

	@GetMapping("/FnShowAllActiveRecords/{product_type_short_name}/{modules_forms_id}")
	public Map<String, Object> FnShowAllActiveRecords(@PathVariable String product_type_short_name,@PathVariable int  modules_forms_id)  {
		Map<String, Object> responce = iSmProductTypeAccordianAccessService.FnShowAllActiveRecords(product_type_short_name,modules_forms_id);
		return responce;
	}

	
 	
 
}
