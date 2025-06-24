package com.erp.XmProductionHoliday.controller;

import com.erp.XmProductionHoliday.Model.CXmProductionHolidayModel;
import com.erp.XmProductionHoliday.Model.CXmProductionHolidayViewModel;
import com.erp.XmProductionHoliday.Service.IXmProductionHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/XmProductionHoliday")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXmProductionHolidayController {


	@Autowired
	IXmProductionHolidayService iHmProductionHolidayService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CXmProductionHolidayModel xmProductionHolidayModel) {
		return iHmProductionHolidayService.FnAddUpdateRecord(xmProductionHolidayModel);

	}

	@DeleteMapping("/FnDeleteRecord/{production_holiday_id}/{deleted_by}/{company_id}")
	public Object FnDeleteRecord(@PathVariable int production_holiday_id, @PathVariable String deleted_by, @PathVariable int company_id) {
		return iHmProductionHolidayService.FnDeleteRecord(production_holiday_id, deleted_by, company_id);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CXmProductionHolidayModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CXmProductionHolidayModel> cxmProductionHolidayModel = iHmProductionHolidayService.FnShowAllActiveRecords(pageable, company_id);
		return cxmProductionHolidayModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{production_holiday_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int production_holiday_id, @PathVariable int company_id) {
		return iHmProductionHolidayService.FnShowParticularRecordForUpdate(production_holiday_id, company_id);
	}

//	@GetMapping("/FnShowAllReportRecords/{company_id}")
//	public Page<CXmProductionHolidayRptModel> FnShowAllReportRecords(Pageable pageable,@PathVariable int company_id) {
//		return iHmProductionHolidayService.FnShowAllReportRecords(pageable, company_id);
//
//	}
//	

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iHmProductionHolidayService.FnShowAllReportRecords(pageable);

	}

	@GetMapping("/FnShowParticularRecords/{production_holiday_id}/{company_id}")
	public Page<CXmProductionHolidayViewModel> FnShowParticularRecord(@PathVariable int production_holiday_id, Pageable pageable, @PathVariable int company_id) {
		return iHmProductionHolidayService.FnShowParticularRecord(production_holiday_id, pageable, company_id);

	}

}
