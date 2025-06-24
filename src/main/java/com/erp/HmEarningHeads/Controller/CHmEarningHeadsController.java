package com.erp.HmEarningHeads.Controller;

import com.erp.HmEarningHeads.Model.CHmEarningHeadsModel;
import com.erp.HmEarningHeads.Model.CHmEarningHeadsViewModel;
import com.erp.HmEarningHeads.Service.IHmEarningHeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/HmEarningHeads")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmEarningHeadsController {

	@Autowired
	IHmEarningHeadsService iHmEarningHeadsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CHmEarningHeadsModel HmEarningHeadsModel) {
		return iHmEarningHeadsService.FnAddUpdateRecord(HmEarningHeadsModel);

	}

	@DeleteMapping("/FnDeleteRecord/{earning_heads_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int earning_heads_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iHmEarningHeadsService.FnDeleteRecord(earning_heads_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CHmEarningHeadsViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CHmEarningHeadsViewModel> cHmEarningHeadsViewModel = iHmEarningHeadsService.FnShowAllActiveRecords(pageable, company_id);
		return cHmEarningHeadsViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{earning_heads_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int earning_heads_id, @PathVariable int company_id) {
		return iHmEarningHeadsService.FnShowParticularRecordForUpdate(earning_heads_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iHmEarningHeadsService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{earning_heads_id}/{company_id}")
	public Page<CHmEarningHeadsViewModel> FnShowParticularRecord(@PathVariable int earning_heads_id, Pageable pageable, @PathVariable int company_id) {
		return iHmEarningHeadsService.FnShowParticularRecord(earning_heads_id, pageable, company_id);

	}


}
