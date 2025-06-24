package com.erp.HmEmployeeTypeEarningAndTypeDeduction.Controller;

import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Service.IHmEmployeeTypeEarningAndDeductionMappingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/HmEmployeeTypeEarningAndTypeDeduction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmEmployeeTypeEarningMappingController {

	@Autowired
	IHmEmployeeTypeEarningAndDeductionMappingService iHmEmployeeTypeEarningMappingService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("EmployeeEarningAndDeductionMappingData") JSONObject jsonObject) {
		return iHmEmployeeTypeEarningMappingService.FnAddUpdateRecord(jsonObject);

	}

	@DeleteMapping("/FnDeleteRecord/{employee_type_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int employee_type_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iHmEmployeeTypeEarningMappingService.FnDeleteRecord(employee_type_id, company_id, deleted_by);
	}


	@GetMapping("/FnShowAllRecords")
	public Map<String, Object> FnShowAllRecords(@RequestParam String employee_type_name, @RequestParam String employee_type_group_name, @RequestParam int company_id) {
		return iHmEmployeeTypeEarningMappingService.FnShowAllRecords(employee_type_name, employee_type_group_name, company_id);
	}


}
