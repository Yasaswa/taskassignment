package com.erp.weeklyoff.Controller;

import com.erp.security.auth.JwtUtils;
import com.erp.weeklyoff.Model.CWeeklyoffModel;
import com.erp.weeklyoff.Service.IWeeklyoffService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/weeklyoff")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CWeeklyoffController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IWeeklyoffService iWeeklyoffService;

	@Autowired
	DataSource dataSource;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CWeeklyoffModel cWeeklyoffModel) throws SQLException {

		JSONObject resp = new JSONObject();

		try {

			resp = iWeeklyoffService.FnAddUpdateRecord(cWeeklyoffModel);

		} catch (Exception e) {

		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{weeklyoff_id}")
	public Object FnDeleteRecord(@PathVariable int weeklyoff_id) {
		return iWeeklyoffService.FnDeleteRecord(weeklyoff_id);
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cDepartmentViewModel = null;
		try {
			cDepartmentViewModel = iWeeklyoffService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDepartmentViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cDepartmentViewModel = null;
		try {
			cDepartmentViewModel = iWeeklyoffService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDepartmentViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{weeklyoff_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int weeklyoff_id) throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iWeeklyoffService.FnShowParticularRecordForUpdate(weeklyoff_id);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{weeklyoff_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int weeklyoff_id) throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;

		try {

			resp = iWeeklyoffService.FnShowParticularRecord(company_id, company_branch_id, weeklyoff_id);
			System.out.println("Responce: " + resp.toString());

		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cWeeklyoffRptModel = null;
		try {
			cWeeklyoffRptModel = iWeeklyoffService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cWeeklyoffRptModel;

	}
}
