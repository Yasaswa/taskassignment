package com.erp.Employee.Employee_Grade.Controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessModel;
import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessViewModel;
import com.erp.Employee.Employee_Grade.Model.CEmployee_GradeModel;
import com.erp.Employee.Employee_Grade.Service.IEmployee_GradeService;

@RestController
@RequestMapping("/api/employeegrade")
public class CEmployee_GradeController {


	@Autowired
	IEmployee_GradeService iEmployee_GradeService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CEmployee_GradeModel cEmployee_GradeModel) throws SQLException {
		
		Map<String, Object> resp = new HashMap<>();
		resp = iEmployee_GradeService.FnAddUpdateRecord(cEmployee_GradeModel);
		return resp;

	
	}
	
	@DeleteMapping("/FnDeleteRecord/{employee_grade_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int employee_grade_id,@PathVariable int company_id,@PathVariable String deleted_by) {
		return iEmployee_GradeService.FnDeleteRecord(employee_grade_id,company_id,deleted_by);
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{employee_grade_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int employee_grade_id,@PathVariable int company_id) throws SQLException {
		
		Map<String, Object> resp = new HashMap<>();
		resp = iEmployee_GradeService.FnShowParticularRecordForUpdate(employee_grade_id,company_id);
		return resp;

	}

	//*****************************************************Api for Modules Form Grade Access*****************************************************************

	@PostMapping("/FnAddUpdateGradeAccessRecord")
	public Map<String, Object> FnAddUpdateGradeAccessRecord(@RequestParam("EmployeeGradeAccessData") JSONObject jsonObject) {
		Map<String, Object> responce = iEmployee_GradeService.FnAddUpdateGradeAccessRecord(jsonObject);
		return responce;

	}


	@DeleteMapping("/FnDeleteGradeAccessRecord/{modules_forms_grade_access_id}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteGradeAccessRecord(@PathVariable int modules_forms_grade_access_id, @PathVariable int company_id,@PathVariable String deleted_by) {
		return iEmployee_GradeService.FnDeleteGradeAccessRecord(modules_forms_grade_access_id, company_id, deleted_by);
	}


	@GetMapping("/FnShowParticularGradeAccessRecordForUpdate/{modules_forms_grade_access_id}/{company_id}")
	public Map<String, Object> FnShowParticularGradeAccessRecordForUpdate(@PathVariable int modules_forms_grade_access_id, @PathVariable int company_id) {
		Map<String, Object> responce = new HashMap<>();
		responce = iEmployee_GradeService.FnShowParticularGradeAccessRecordForUpdate(modules_forms_grade_access_id, company_id);
		return responce;
	}
	

	@GetMapping("/FnShowAllActiveRecords/{grade_id}/{company_id}")
	public List<CAmModulesFormsGradeAccessViewModel> FnShowAllActiveRecords(@PathVariable int grade_id, @PathVariable int company_id) throws SQLException {
		
		List<CAmModulesFormsGradeAccessViewModel> cAmModulesFormsGradeAccessModel =
				iEmployee_GradeService.FnShowAllAciveRecords(grade_id, company_id);
		
		return cAmModulesFormsGradeAccessModel;
	}


}
