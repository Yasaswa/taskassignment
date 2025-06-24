package com.erp.Bank.Controller;

import com.erp.Bank.Service.IBankService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CBankController {

	@Autowired
	IBankService iBankService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestParam("BankData") JSONObject jsonObject) throws SQLException {
		Map<String, Object> response = iBankService.FnAddUpdateRecord(jsonObject);
		return response;

	}

	@DeleteMapping("/FnDeleteRecord/{bank_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int bank_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iBankService.FnDeleteRecord(bank_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords() throws SQLException {
		Object cbankViewModel = null;

		try {
			cbankViewModel = iBankService.FnShowAllRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cbankViewModel;
	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	Object FnShowAllActiveRecords(@PathVariable int company_id, Pageable pageable) throws SQLException {
		Object cbankViewModel = null;
		cbankViewModel = iBankService.FnShowAllActiveRecords(company_id, pageable);

		return cbankViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{bank_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int bank_id, @PathVariable int company_id) throws SQLException {
		Map<String, Object> responce = iBankService.FnShowParticularRecordForUpdate(bank_id, company_id);
		return responce;
	}


	@GetMapping("/FnShowParticularRecord/{company_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, Pageable pageable) throws SQLException {
		Object bankViewModel = null;
		try {
			bankViewModel = iBankService.FnShowParticularRecord(company_id, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankViewModel;

	}


	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object bankRptModel = null;
		try {
			bankRptModel = iBankService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bankRptModel;

	}

}
