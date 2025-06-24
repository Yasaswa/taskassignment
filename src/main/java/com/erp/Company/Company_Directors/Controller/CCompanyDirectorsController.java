package com.erp.Company.Company_Directors.Controller;

import com.erp.Company.Company_Directors.Model.CCompanyDirectorsModel;
import com.erp.Company.Company_Directors.Model.CCompanyDirectorsViewModel;
import com.erp.Company.Company_Directors.Service.ICompanyDirectorsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/companydirectors")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCompanyDirectorsController {

	@Autowired
	ICompanyDirectorsService iCompanyDirectorsService;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestBody CCompanyDirectorsModel companydirectorsModel) throws SQLException {

		JSONObject resp = new JSONObject();

		try {
			resp = iCompanyDirectorsService.FnAddUpdateRecord(companydirectorsModel);
			return resp.toString();

		} catch (Exception e) {
			e.getMessage();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllRecords")
	Page<CCompanyDirectorsViewModel> FnShowAllRecords(Pageable pageable) throws SQLException {
		Page<CCompanyDirectorsViewModel> CCompanyDirectorsModel = null;
		try {
			CCompanyDirectorsModel = iCompanyDirectorsService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CCompanyDirectorsModel;
	}

	@PostMapping("/FnDeleteRecord/{company_director_id}")
	public CCompanyDirectorsModel FnDeleteRecord(@PathVariable int company_director_id) {

		return iCompanyDirectorsService.FnDeleteRecord(company_director_id);

	}

	@GetMapping("/FnShowAllActiveRecords")
	Page<CCompanyDirectorsViewModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Page<CCompanyDirectorsViewModel> cCompanyDirectorsViewModel = null;

		try {
			cCompanyDirectorsViewModel = iCompanyDirectorsService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCompanyDirectorsViewModel;

	}

	@GetMapping("/FnShowParticularRecords/{company_id}")
	public Page<CCompanyDirectorsViewModel> FnShowParticularRecord(@PathVariable int company_id, Pageable pageable)
			throws SQLException {

		Page<CCompanyDirectorsViewModel> cCompanyDirectorsViewModel = null;
		try {
			cCompanyDirectorsViewModel = iCompanyDirectorsService.FnShowParticularRecord(company_id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCompanyDirectorsViewModel;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_director_id}")
	public CCompanyDirectorsViewModel FnShowParticularRecordForUpdate(@PathVariable int company_director_id)
			throws SQLException {

		CCompanyDirectorsViewModel cCompanyDirectorsViewModel = null;
		try {
			cCompanyDirectorsViewModel = iCompanyDirectorsService.FnShowParticularRecordForUpdate(company_director_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCompanyDirectorsViewModel;

	}

}
