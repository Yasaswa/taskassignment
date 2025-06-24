package com.erp.Department.Controller;

import com.erp.Department.Model.CDepartmentModel;
import com.erp.Department.Service.IDepartmentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CDepartmentController {

	@Autowired
	IDepartmentService iDepartmentService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CDepartmentModel cDepartmentModel) throws SQLException {
		return iDepartmentService.FnAddUpdateRecord(cDepartmentModel);
	}

	@DeleteMapping("/FnDeleteRecord/{department_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int department_id, @PathVariable String deleted_by) {
		return iDepartmentService.FnDeleteRecord(department_id, deleted_by);
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cDepartmentViewModel = null;
		try {
			cDepartmentViewModel = iDepartmentService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDepartmentViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cDepartmentViewModel = null;
		try {
			cDepartmentViewModel = iDepartmentService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDepartmentViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{department_id}/{company_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int department_id, @PathVariable int company_id)
			throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iDepartmentService.FnShowParticularRecordForUpdate(department_id, company_id);

		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{department_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int department_id) throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;

		try {

			resp = iDepartmentService.FnShowParticularRecord(company_id, company_branch_id, department_id);
			System.out.println("Responce: " + resp.toString());

		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cDepartmentRptModel = null;
		try {
			cDepartmentRptModel = iDepartmentService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDepartmentRptModel;

	}


}
