package com.erp.CmPaymentSchedule.Controller;

import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleModel;
import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleViewModel;
import com.erp.CmPaymentSchedule.Service.ICmPaymentScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/CmPaymentSchedule")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCmPaymentScheduleController {

	@Autowired
	ICmPaymentScheduleService iCmPaymentScheduleService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CCmPaymentScheduleModel CmPaymentScheduleModel) {
		Map<String, Object> responce = new HashMap<>();
		responce = iCmPaymentScheduleService.FnAddUpdateRecord(CmPaymentScheduleModel);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{payment_schedule_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int payment_schedule_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iCmPaymentScheduleService.FnDeleteRecord(payment_schedule_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CCmPaymentScheduleViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CCmPaymentScheduleViewModel> cCmPaymentScheduleViewModel = iCmPaymentScheduleService.FnShowAllActiveRecords(pageable, company_id);
		return cCmPaymentScheduleViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{payment_schedule_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int payment_schedule_id, @PathVariable int company_id) {
		Map<String, Object> responce = new HashMap<>();
		responce = iCmPaymentScheduleService.FnShowParticularRecordForUpdate(payment_schedule_id, company_id);
		return responce;
	}


	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iCmPaymentScheduleService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{payment_schedule_id}/{company_id}")
	public Page<CCmPaymentScheduleViewModel> FnShowParticularRecord(@PathVariable int payment_schedule_id, Pageable pageable, @PathVariable int company_id) {
		Page<CCmPaymentScheduleViewModel> cCmPaymentScheduleViewModel = iCmPaymentScheduleService.FnShowParticularRecord(payment_schedule_id, pageable, company_id);
		return cCmPaymentScheduleViewModel;

	}


}
