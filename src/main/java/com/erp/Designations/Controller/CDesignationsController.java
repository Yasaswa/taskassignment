package com.erp.Designations.Controller;

import com.erp.Designations.Model.CDesignationsModel;
import com.erp.Designations.Service.IDesignationsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/designation")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CDesignationsController {

	@Autowired
	IDesignationsService iDesignationsService;

	@PostMapping("/FnAddUpdateRecord")
	public HashMap<String, Object> FnAddUpdateRecord(@RequestBody CDesignationsModel cDesignationsModel)
			throws SQLException {
		HashMap<String, Object> resp = new HashMap<>();
		try {
			resp = iDesignationsService.FnAddUpdateRecord(cDesignationsModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	@PostMapping("/EarningAndDeductionFnAddUpdateRecord")
	public Map<String, Object> EarningAndDeductionFnAddUpdateRecord(
			@RequestParam("DesignationEarningAndDeductionMappingData") JSONObject jsonObject) {
		return iDesignationsService.EarningAndDeductionFnAddUpdateRecord(jsonObject);
	}

	@GetMapping("/FnShowEarningAndDeductionRecords/{company_id}")
	public Map<String, Object> FnShowEarningAndDeductionRecords(@PathVariable int company_id) {
		return iDesignationsService.FnShowEarningAndDeductionRecords(company_id);
	}

	@GetMapping("/EarningAndDeductionFnShowAllRecords/{designation_id}")
	public Map<String, Object> EarningAndDeductionFnShowAllRecords(@PathVariable int designation_id) {
		return iDesignationsService.EarningAndDeductionFnShowAllRecords(designation_id);
	}

	@GetMapping("/FnEmployeeEarningTypeAndDeductionTypeShowAllRecords/{employee_type_name}/{company_id}")
	public Map<String, Object> FnEmployeeEarningTypeAndDeductionTypeShowAllRecords(
			@PathVariable String employee_type_name, @PathVariable int company_id) {
		return iDesignationsService.FnEmployeeEarningTypeAndDeductionTypeShowAllRecords(employee_type_name, company_id);
	}

	@DeleteMapping("/FnDeleteRecord/{designation_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int designation_id, @PathVariable String deleted_by) {
		Map<String, Object> responce = iDesignationsService.FnDeleteRecord(designation_id, deleted_by);
		return responce;
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cDesignationsModel = null;
		try {
			cDesignationsModel = iDesignationsService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDesignationsModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cDesignationsModel = null;
		try {
			cDesignationsModel = iDesignationsService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDesignationsModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{designation_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int designation_id) throws SQLException {
		Map<String, Object> resp = new HashMap();
		try {
			resp = iDesignationsService.FnShowParticularRecordForUpdate(designation_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{designation_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int designation_id)
			throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iDesignationsService.FnShowParticularRecord(company_id, designation_id);
			System.out.println("Responce: " + resp.toString());

			return resp.toString();

		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return resp;

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cCDesignationsRptModel = null;
		try {
			cCDesignationsRptModel = iDesignationsService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCDesignationsRptModel;

	}

	@GetMapping("/FnShowDesignationSalaryHeadsMappingRecord/{designation_salary_heads_mapping_id}/{company_id}")
	public Map<String, Object> FnShowDesignationSalaryHeadsMappingRecord(
			@PathVariable int designation_salary_heads_mapping_id, @PathVariable int company_id) throws SQLException {
		Map<String, Object> responce = iDesignationsService
				.FnShowDesignationSalaryHeadsMappingRecord(designation_salary_heads_mapping_id, company_id);
		return responce;
	}

}
