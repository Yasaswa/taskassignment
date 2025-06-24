package com.erp.FinancialYear.Controller;

import com.erp.FinancialYear.Model.CFinancialYearModel;
import com.erp.FinancialYear.Service.IFinancialYearService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/finaincialyear")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CFinancialYearController {


	@Autowired
	IFinancialYearService iFinancialYearService;


	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CFinancialYearModel financialYearModel) throws SQLException {
		JSONObject resp = new JSONObject();

		try {

			resp = iFinancialYearService.FnAddUpdateRecord(financialYearModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}


	@DeleteMapping("/FnDeleteRecord/{financial_year_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int financial_year_id, @PathVariable String deleted_by) {
		return iFinancialYearService.FnDeleteRecord(financial_year_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cFinancialYearViewModel = null;
		try {
			cFinancialYearViewModel = iFinancialYearService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cFinancialYearViewModel.toString();
	}


	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cFinancialYearViewModel = null;

		try {
			cFinancialYearViewModel = iFinancialYearService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cFinancialYearViewModel.toString();
	}


	@GetMapping("/FnShowParticularRecord/{company_id}/{financial_year_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int financial_year_id) throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iFinancialYearService.FnShowParticularRecord(company_id, financial_year_id);
		} catch (Exception e) {

		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{financial_year_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int financial_year_id) throws SQLException {
		return iFinancialYearService.FnShowParticularRecordForUpdate(financial_year_id);

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object CAgentRptModel = null;
		CAgentRptModel = iFinancialYearService.FnShowAllReportRecords(pageable);

		return CAgentRptModel;

	}
}

