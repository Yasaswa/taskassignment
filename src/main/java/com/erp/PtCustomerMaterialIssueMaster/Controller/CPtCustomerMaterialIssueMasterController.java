package com.erp.PtCustomerMaterialIssueMaster.Controller;

import java.sql.SQLException;

import java.util.Map;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.PtCustomerMaterialIssueMaster.Service.IPtCustomerMaterialIssueMasterService;

@RestController
@RequestMapping("/api/PtCustomerMaterialIssueMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtCustomerMaterialIssueMasterController {

	@Autowired
	IPtCustomerMaterialIssueMasterService iPtCustomerMaterialIssueMasterService;



	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("PtCustomerMaterialIssueData") JSONObject jsonObject) throws JsonProcessingException {
		Map<String, Object> responce = iPtCustomerMaterialIssueMasterService.FnAddUpdateRecord(jsonObject);
		return responce;
	}


	@DeleteMapping("/FnDeleteRecord/{customer_material_issue_version}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("customer_material_issue_no") String customer_material_issue_no, @PathVariable int customer_material_issue_version, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iPtCustomerMaterialIssueMasterService.FnDeleteRecord(customer_material_issue_no, customer_material_issue_version, company_id, deleted_by);

	}

	
	@GetMapping("/FnShowAllMasterAndDetailsmodelRecords/{customer_material_issue_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllMasterAndDetailsmodelRecords(@RequestParam("customer_material_issue_no") String customer_material_issue_no, @PathVariable int customer_material_issue_version, @PathVariable String financial_year, @PathVariable int company_id)throws SQLException {
		Map<String, Object> responce = iPtCustomerMaterialIssueMasterService.FnShowAllMasterAndDetailsmodelRecords(customer_material_issue_no, customer_material_issue_version, financial_year,
				company_id);
		return responce;
	}


//	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{customer_material_issue_version}/{financial_year}/{company_id}")
//	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("customer_material_issue_no") String customer_material_issue_no, @PathVariable int customer_material_issue_version, @PathVariable String financial_year, @PathVariable int company_id)throws SQLException {
//		Map<String, Object> responce = iPtCustomerMaterialIssueMasterService.FnShowAllDetailsandMastermodelRecords(customer_material_issue_no, customer_material_issue_version, financial_year,
//				company_id);
//		return responce;
//	}
//	@PostMapping("/FnShowIndentDetailsRecords")
//	public Map<String, Object> FnShowIndentDetailsRecords(@RequestParam("indentNos") JSONObject indentNo) {
//		return iPtCustomerMaterialIssueMasterService.FnShowIndentDetailsRecords(indentNo);
//	}

 	
 
}
