package com.erp.Employee.Employees.Controller;

import com.erp.Employee.Employees.Service.IEmployeesService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CEmployeesController {

	@Autowired
	IEmployeesService iEmployeesService;

//	@PostMapping("/FnAddUpdateRecord")
//	public Map<String, Object> FnAddUpdateRecord(@RequestBody CEmployeesModel cEmployeesModel) throws SQLException {
//		Map<String, Object> resp = iEmployeesService.FnAddUpdateRecord(cEmployeesModel);
//		return resp;
//	}

	@PostMapping("/FnAddUpdateRecord/{isApprove}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("EmployeeServiceData") JSONObject jsonObject, @RequestParam(name = "empImageFile", required = false) MultipartFile empImageFile,@PathVariable boolean isApprove) {
		return iEmployeesService.FnAddUpdateRecord(jsonObject, empImageFile, isApprove);
	}

	@GetMapping("/GetDataFromDesignationOrEmplyeeType/{company_id}/{common_id}")
	public Map<String, Object> GetDataFromDesignationOrEmplyeeType(@PathVariable int company_id, @PathVariable int common_id, @RequestParam String earning_deduction_mapping_baseKey) {
		return iEmployeesService.GetDataFromDesignationOrEmplyeeType(company_id, common_id, earning_deduction_mapping_baseKey);
	}


	@GetMapping("/FnShowSalaryAndWorkProfileRecords/{employee_id}")
	public Map<String, Object> FnShowSalaryAndWorkProfileRecords(@PathVariable int employee_id) {
		return iEmployeesService.FnShowSalaryAndWorkProfileRecords(employee_id);
	}


	@PostMapping("/EarningAndDeductionFnAddUpdateRecord")
	public Map<String, Object> EarningAndDeductionFnAddUpdateRecord(@RequestParam("DesignationEarningAndDeductionMappingData") JSONObject jsonObject) {
		return iEmployeesService.EarningAndDeductionFnAddUpdateRecord(jsonObject);
	}

	@GetMapping("/EarningAndDeductionFnShowAllRecords/{employee_id}")
	public Map<String, Object> EarningAndDeductionFnShowAllRecords(@PathVariable int employee_id) {
		return iEmployeesService.EarningAndDeductionFnShowAllRecords(employee_id);
	}

	@DeleteMapping("/FnDeleteRecord/{employee_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int employee_id, @PathVariable String deleted_by) {
		return iEmployeesService.FnDeleteRecord(employee_id, deleted_by);
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cEmployeesViewModel = null;
		try {
			cEmployeesViewModel = iEmployeesService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cEmployeesViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cEmployeesViewModel = null;

		try {
			cEmployeesViewModel = iEmployeesService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cEmployeesViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{employee_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int employee_id) throws SQLException {
		Map<String, Object> responce = new HashMap<>();
		responce = iEmployeesService.FnShowParticularRecordForUpdate(employee_id);
		return responce;
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{employee_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int employee_id) throws SQLException {
		Object cEmployeesViewModel = null;
		try {
			cEmployeesViewModel = iEmployeesService.FnShowParticularRecord(company_id, company_branch_id, employee_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cEmployeesViewModel.toString();

	}

	@GetMapping("/FnShowAllSummaryReportRecords")
	Object FnShowAllSummaryReportRecords(Pageable pageable) throws SQLException {
		Object cCEmployeeSummaryRptModel = null;
		try {
			cCEmployeeSummaryRptModel = iEmployeesService.FnShowAllSummaryReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCEmployeeSummaryRptModel;

	}

	@GetMapping("/FnShowAllSummaryRecords")
	Object FnShowAllSummaryRecords(Pageable pageable) throws SQLException {
		Object cCEmployeeSummaryModel = null;
		try {
			cCEmployeeSummaryModel = iEmployeesService.FnShowAllSummaryRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCEmployeeSummaryModel.toString();

	}


	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cCEmployeeRptModel = null;
		try {
			cCEmployeeRptModel = iEmployeesService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCEmployeeRptModel;

	}

	@GetMapping("/FnShowEmployeeSalaryHeadsMappingRecord/{employee_salary_heads_mapping_id}/{company_id}")
	public Map<String, Object> FnShowEmployeeSalaryHeadsMappingRecord(@PathVariable int employee_salary_heads_mapping_id, @PathVariable int company_id) throws SQLException {
		Map<String, Object> responce = iEmployeesService.FnShowEmployeeSalaryHeadsMappingRecord(employee_salary_heads_mapping_id, company_id);
		return responce;
	}


}
