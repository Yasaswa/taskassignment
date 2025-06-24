package com.erp.AmCompanySettings.Controller;

import com.erp.AmCompanySettings.Model.CAmCompanySettingsModel;
import com.erp.AmCompanySettings.Model.CAmCompanySettingsViewModel;
import com.erp.AmCompanySettings.Service.IAmCompanySettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/AmCompanySettings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmCompanySettingsController {

	@Autowired
	IAmCompanySettingsService iAmCompanySettingsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CAmCompanySettingsModel AmCompanySettingsModel) {
		return iAmCompanySettingsService.FnAddUpdateRecord(AmCompanySettingsModel);

	}

	@DeleteMapping("/FnDeleteRecord/{am_company_settings_id}/{company_id}")
	public Object FnDeleteRecord(@PathVariable int am_company_settings_id, @PathVariable int company_id) {
		return iAmCompanySettingsService.FnDeleteRecord(am_company_settings_id, company_id);

	}

	// Added by Mohit :- this api use when we need deleted by functionality

//	@DeleteMapping("/FnDeleteRecord/{am_company_settings_id}/{company_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int am_company_settings_id, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iAmCompanySettingsService.FnDeleteRecord(am_company_settings_id, company_id, deleted_by);
//
//	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CAmCompanySettingsViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CAmCompanySettingsViewModel> cAmCompanySettingsViewModel = iAmCompanySettingsService.FnShowAllActiveRecords(pageable, company_id);
		return cAmCompanySettingsViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{am_company_settings_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int am_company_settings_id, @PathVariable int company_id) {
		return iAmCompanySettingsService.FnShowParticularRecordForUpdate(am_company_settings_id, company_id);
	}

//	@GetMapping("/FnShowAllReportRecords/{company_id}")
//	public Page<CAmCompanySettingsRptModel> FnShowAllReportRecords(Pageable pageable, , @PathVariable int company_id) {
//		return iAmCompanySettingsService.FnShowAllReportRecords(pageable, company_id);
//
//	}

	@GetMapping("/FnShowParticularRecords/{am_company_settings_id}/{company_id}")
	public Page<CAmCompanySettingsViewModel> FnShowParticularRecord(@PathVariable int am_company_settings_id, Pageable pageable, @PathVariable int company_id) {
		return iAmCompanySettingsService.FnShowParticularRecord(am_company_settings_id, pageable, company_id);

	}


}
