package com.erp.Company.Company_Bank.Controller;

import com.erp.Company.Company_Bank.Model.CCompanyBankViewModel;
import com.erp.Company.Company_Bank.Service.CCompanyBankServiceImpl;
import com.erp.Company.Company_Bank.Service.ICompanyBankService;
import com.erp.security.auth.JwtUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/companybank")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCompanyBankController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	CCompanyBankServiceImpl objCCompanyBankServiceImpl;

	@Autowired
	ICompanyBankService iCompanyBankService;

	@Autowired
	DataSource dataSource;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestParam("companyBankJson") JSONObject jsonObject) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iCompanyBankService.FnAddUpdateRecord(jsonObject);
			System.out.println("Responce: " + resp.toString());
			return resp.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{company_bank_id}")
	public Object FnDeleteRecord(@PathVariable int company_bank_id) {
		return iCompanyBankService.FnDeleteRecord(company_bank_id);

	}

	@GetMapping("/FnShowAllActiveRecords")
	Page<CCompanyBankViewModel> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Page<CCompanyBankViewModel> CCompanyBankViewModel = null;
		try {
			CCompanyBankViewModel = iCompanyBankService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CCompanyBankViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{company_bank_id}")
	public CCompanyBankViewModel FnShowParticularRecord(@PathVariable int company_bank_id) throws SQLException {
		CCompanyBankViewModel cCompanyBankViewModel = null;
		try {
			cCompanyBankViewModel = iCompanyBankService.FnShowParticularRecord(company_bank_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCompanyBankViewModel;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_branch_id}/{company_id}")
	public List<CCompanyBankViewModel> FnShowParticularRecordForUpdate(@PathVariable int company_branch_id,
	                                                                   @PathVariable int company_id, Pageable pageable) throws SQLException {
		List<CCompanyBankViewModel> cCompanyBankViewModel = null;
		try {
			cCompanyBankViewModel = iCompanyBankService.FnShowParticularRecordForUpdate(company_branch_id, company_id,
					pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCompanyBankViewModel;

	}

	@GetMapping("/FnShowAllRecords")
	Page<CCompanyBankViewModel> FnShowAllRecords(Pageable pageable) throws SQLException {
		Page<CCompanyBankViewModel> ccompanyBankviewModel = null;
		try {
			ccompanyBankviewModel = iCompanyBankService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ccompanyBankviewModel;
	}

}
