package com.erp.HmDeductionHeads.Controller;

import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsModel;
import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsViewModel;
import com.erp.HmDeductionHeads.Service.IHmDeductionHeadsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/HmDeductionHeads")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmDeductionHeadsController {

	@Autowired
	IHmDeductionHeadsService iHmDeductionHeadsService;


	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CHmDeductionHeadsModel chmDeductionHeadsModel) {
		return iHmDeductionHeadsService.FnAddUpdateRecord(chmDeductionHeadsModel);

	}

	@DeleteMapping("/FnDeleteRecord/{deduction_heads_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int deduction_heads_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iHmDeductionHeadsService.FnDeleteRecord(deduction_heads_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CHmDeductionHeadsViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CHmDeductionHeadsViewModel> chmDeductionHeadsViewModel = iHmDeductionHeadsService.FnShowAllActiveRecords(pageable, company_id);
		return chmDeductionHeadsViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{deduction_heads_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int deduction_heads_id, @PathVariable int company_id) {
		return iHmDeductionHeadsService.FnShowParticularRecordForUpdate(deduction_heads_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iHmDeductionHeadsService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{deduction_heads_id}/{company_id}")
	public Page<CHmDeductionHeadsViewModel> FnShowParticularRecord(@PathVariable int deduction_heads_id, Pageable pageable, @PathVariable int company_id) {
		return iHmDeductionHeadsService.FnShowParticularRecord(deduction_heads_id, pageable, company_id);

	}

}
