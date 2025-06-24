package com.erp.Transporter_Banks.Controller;

import com.erp.Transporter.Model.CTransporterBanksViewModel;
import com.erp.Transporter_Banks.Service.ITransporterBanksService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/transporterbanks", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CTransporterBanksController {

	@Autowired
	ITransporterBanksService iTransporterBanksService;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestParam("transporterBankJson") JSONObject jsonObject) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iTransporterBanksService.FnAddUpdateRecord(jsonObject);

		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{transporter_bank_id}")
	public Object FnDeleteRecord(@PathVariable int transporter_bank_id) {
		return iTransporterBanksService.FnDeleteRecord(transporter_bank_id);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cTransporterBanksViewModel = null;
		try {
			cTransporterBanksViewModel = iTransporterBanksService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterBanksViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cTransporterBanksViewModel = null;

		try {
			cTransporterBanksViewModel = iTransporterBanksService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterBanksViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{company_branch_id}/{transporter_id}")
	public List<CTransporterBanksViewModel> FnShowParticularRecordForUpdate(@PathVariable int company_id,
	                                                                        @PathVariable int company_branch_id, @PathVariable int transporter_id) throws SQLException {
		List<CTransporterBanksViewModel> cTransporterBankViewModel = null;
		try {
			cTransporterBankViewModel = iTransporterBanksService.FnShowParticularRecordForUpdate(company_id,
					company_branch_id, transporter_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterBankViewModel;

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{transporter_bank_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int transporter_bank_id) throws SQLException {
		Object cTransporterBanksViewModel = null;
		try {
			cTransporterBanksViewModel = iTransporterBanksService.FnShowParticularRecord(company_id, company_branch_id,
					transporter_bank_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterBanksViewModel;

	}

}
