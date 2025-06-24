package com.erp.FmGeneralLedger.Controller;

import com.erp.FmGeneralLedger.Model.CFmGeneralLedgerViewModel;
import com.erp.FmGeneralLedger.Service.IFmGeneralLedgerService;
import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingModel;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/FmGeneralLedger")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CFmGeneralLedgerController {

	@Autowired
	IFmGeneralLedgerService iFmGeneralLedgerService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("GeneralLedgerData") JSONObject jsonObject) {
		Map<String, Object> responce = iFmGeneralLedgerService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{general_ledger_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int general_ledger_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iFmGeneralLedgerService.FnDeleteRecord(general_ledger_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CFmGeneralLedgerViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CFmGeneralLedgerViewModel> cFmGeneralLedgerViewModel = iFmGeneralLedgerService.FnShowAllActiveRecords(pageable, company_id);
		return cFmGeneralLedgerViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{general_ledger_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int general_ledger_id, @PathVariable int company_id) {
		Map<String, Object> responce = iFmGeneralLedgerService.FnShowParticularRecordForUpdate(general_ledger_id, company_id);
		return responce;
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iFmGeneralLedgerService.FnShowAllReportRecords(pageable, company_id);
	}

	@GetMapping("/FnShowParticularRecords/{general_ledger_id}/{company_id}")
	public Page<CSl_Gl_MappingModel> FnShowParticularRecord(@PathVariable int general_ledger_id, Pageable pageable, @PathVariable int company_id) {
		Page<CSl_Gl_MappingModel> cFmGeneralLedgerViewModel = iFmGeneralLedgerService.FnShowParticularRecord(general_ledger_id, pageable, company_id);
		return cFmGeneralLedgerViewModel;

	}


}
