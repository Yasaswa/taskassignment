package com.erp.Employee.CmEmployeesSalary.Controller;

import com.erp.Employee.CmEmployeesSalary.Model.CCmEmployeesSalaryModel;
import com.erp.Employee.CmEmployeesSalary.Service.ICmEmployeesSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/CmEmployeesSalary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCmEmployeesSalaryController {

	@Autowired
	ICmEmployeesSalaryService iCmEmployeesSalaryService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CCmEmployeesSalaryModel CmEmployeesSalaryModel) {
		Map<String, Object> responce = new HashMap<>();
		responce = iCmEmployeesSalaryService.FnAddUpdateRecord(CmEmployeesSalaryModel);
		return responce;

	}


	@GetMapping("/FnShowParticularRecordForUpdate/{employee_salary_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int employee_salary_id, @PathVariable int company_id) {
		Map<String, Object> responce = new HashMap<>();
		responce = iCmEmployeesSalaryService.FnShowParticularRecordForUpdate(employee_salary_id, company_id);
		return responce;
	}


}
