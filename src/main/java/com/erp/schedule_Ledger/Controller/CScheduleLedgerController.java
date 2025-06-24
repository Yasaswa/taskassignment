package com.erp.schedule_Ledger.Controller;

import com.erp.schedule_Ledger.Model.CScheduleLedgerModel;
import com.erp.schedule_Ledger.Service.IScheduleLedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/scheduleledger", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CScheduleLedgerController {

	@Autowired
	IScheduleLedgerService iScheduleLedgerService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CScheduleLedgerModel cScheduleLedgerModel) {
		Map<String, Object> resp = iScheduleLedgerService.FnAddUpdateRecord(cScheduleLedgerModel);
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{schedule_ledger_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int schedule_ledger_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iScheduleLedgerService.FnDeleteRecord(schedule_ledger_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords/{company_id}")
	public Map<String, Object> FnShowAllRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cSheduleLedgerViewModel = iScheduleLedgerService.FnShowAllRecords(pageable, company_id);
		return cSheduleLedgerViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Map<String, Object> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cSheduleLedgerViewModel = iScheduleLedgerService.FnShowAllActiveRecords(pageable, company_id);
		return cSheduleLedgerViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{schedule_ledger_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int schedule_ledger_id, @PathVariable int company_id) throws SQLException {
		Map<String, Object> resp = iScheduleLedgerService.FnShowParticularRecordForUpdate(schedule_ledger_id, company_id);
		return resp;

	}

	@GetMapping("/FnShowParticularRecord/{schedule_ledger_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecord(@PathVariable int schedule_ledger_id, @PathVariable int company_id) throws SQLException {
		Map<String, Object> resp = iScheduleLedgerService.FnShowParticularRecord(schedule_ledger_id, company_id);
		return resp;

	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cSecheduleLedgerRptModel = iScheduleLedgerService.FnShowAllReportRecords(pageable, company_id);
		return cSecheduleLedgerRptModel;

	}

	@GetMapping("/FnShowScheduleLedgerSrNo/{report_side}/{report_type}/{company_id}")
	public Map<String, Object> FnShowScheduleLedgerSrNo(@PathVariable String report_side, @PathVariable String report_type, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cSecheduleLedgerRptModel = iScheduleLedgerService.FnShowScheduleLedgerSrNo(report_side, report_type, company_id);
		return cSecheduleLedgerRptModel;

	}
}
