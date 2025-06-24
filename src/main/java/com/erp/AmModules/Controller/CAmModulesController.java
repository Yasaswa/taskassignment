package com.erp.AmModules.Controller;

import com.erp.AmModules.Model.CAmModulesModel;
import com.erp.AmModules.Model.CAmModulesViewModel;
import com.erp.AmModules.Service.IAmModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/AmModules")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmModulesController {

	@Autowired
	IAmModulesService iAmModulesService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CAmModulesModel AmModulesModel) {
		return iAmModulesService.FnAddUpdateRecord(AmModulesModel);

	}

	@DeleteMapping("/FnDeleteRecord/{modules_id}")
	public Object FnDeleteRecord(@PathVariable int modules_id) {
		return iAmModulesService.FnDeleteRecord(modules_id);

	}

	// Added by Mohit :- this api use when we need deleted by functionality

//	@DeleteMapping("/FnDeleteRecord/{modules_id}/{company_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int modules_id, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iAmModulesService.FnDeleteRecord(modules_id,company_id,deleted_by);
//
//	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CAmModulesViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id)
			throws SQLException {
		Page<CAmModulesViewModel> cAmModulesViewModel = iAmModulesService.FnShowAllActiveRecords(pageable, company_id);
		return cAmModulesViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{modules_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int modules_id,
	                                                           @PathVariable int company_id) {
		return iAmModulesService.FnShowParticularRecordForUpdate(modules_id, company_id);
	}

	@GetMapping("/FnShowParticularRecords/{modules_id}/{company_id}")
	public Page<CAmModulesViewModel> FnShowParticularRecord(@PathVariable int modules_id, Pageable pageable,
	                                                        @PathVariable int company_id) {
		return iAmModulesService.FnShowParticularRecord(modules_id, pageable, company_id);

	}

//	old
//	@GetMapping("/FnShowAllReportRecords/{company_id}")
//	public Page<CAmModulesRptModel> FnShowAllReportRecords(Pageable pageable,  @PathVariable int company_id) {
//		return iAmModulesService.FnShowAllReportRecords(pageable, company_id);
//
//	}

	//	new
	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iAmModulesService.FnShowAllReportRecords(pageable, company_id);

	}

}
