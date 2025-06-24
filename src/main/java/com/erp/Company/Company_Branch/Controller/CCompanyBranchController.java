package com.erp.Company.Company_Branch.Controller;

import com.erp.Company.Company_Branch.Model.CCompanyBranchModel;
import com.erp.Company.Company_Branch.Model.CCompanyBranchViewModel;
import com.erp.Company.Company_Branch.Service.ICompanyBranchService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/companybranch")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class CCompanyBranchController {
	@Autowired
	ICompanyBranchService iCompanyBranchService;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestBody CCompanyBranchModel CompanyBranchModel) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iCompanyBranchService.FnAddUpdateRecord(CompanyBranchModel);
			System.out.println("Responce: " + resp.toString());
			return resp.toString();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@PostMapping("/FnDeleteRecord/{company_branch_id}/{deleted_by}")
	public CCompanyBranchModel FnDeleteRecord(@PathVariable int company_branch_id, @PathVariable String deleted_by) {
		return iCompanyBranchService.FnDeleteRecord(company_branch_id, deleted_by);
	}

	@GetMapping("/FnShowAllRecords")
	Page<CCompanyBranchViewModel> FnShowAllRecords(Pageable pageable) throws SQLException {
		Page<CCompanyBranchViewModel> CompanyBranchViewModel = null;
		try {
			CompanyBranchViewModel = iCompanyBranchService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CompanyBranchViewModel;

	}

	@GetMapping("/FnShowAllActiveRecords")
	Page<CCompanyBranchViewModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Page<CCompanyBranchViewModel> CompanyBranchViewModel = null;
		try {
			CompanyBranchViewModel = iCompanyBranchService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CompanyBranchViewModel;

	}

	@GetMapping("/FnShowParticularRecords/{company_id}")
	public Page<CCompanyBranchViewModel> FnShowParticularRecord(@PathVariable int company_id, Pageable pageable)
			throws SQLException {
		Page<CCompanyBranchViewModel> cCompanyBranchViewModel = null;
		try {
			cCompanyBranchViewModel = iCompanyBranchService.FnShowParticularRecord(company_id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCompanyBranchViewModel;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_branch_id}")
	public CCompanyBranchModel FnShowParticularRecordForUpdate(@PathVariable int company_branch_id)
			throws SQLException {
		CCompanyBranchModel cCompanyBranchModel = null;
		try {
			cCompanyBranchModel = iCompanyBranchService.FnShowParticularRecordForUpdate(company_branch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCompanyBranchModel;

	}


	@GetMapping("/FnShowAllReportRecords")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iCompanyBranchService.FnShowAllReportRecords(pageable);

	}


	@PostMapping("/FnShowFilterRecords")
	public List<CCompanyBranchViewModel> FnShowFilterRecords(@RequestParam("jsonQuery") JSONObject jsonQuery) {
		return iCompanyBranchService.FnShowFilterRecords(jsonQuery);
	}

	@PostMapping("/FnMShowFilterRecords/{page}/{size}")
	public String FnMShowFilterRecords(@RequestParam("jsonQuery") JSONObject jsonQuery, @PathVariable int page,
	                                   @PathVariable int size) {
		JSONObject jsonObject = new JSONObject();
		jsonObject = iCompanyBranchService.FnMShowFilterRecords(jsonQuery, page, size);
		return jsonObject.toString();

	}

}
