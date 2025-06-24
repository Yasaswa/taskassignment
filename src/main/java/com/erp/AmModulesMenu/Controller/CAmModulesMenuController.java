package com.erp.AmModulesMenu.Controller;

import com.erp.AmModulesMenu.Model.CAmModulesMenuModel;
import com.erp.AmModulesMenu.Model.CAmModulesMenuViewModel;
import com.erp.AmModulesMenu.Service.IAmModulesMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/AmModulesMenu")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmModulesMenuController {

	@Autowired
	IAmModulesMenuService iAmModulesMenuService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CAmModulesMenuModel AmModulesMenuModel) {
		return iAmModulesMenuService.FnAddUpdateRecord(AmModulesMenuModel);

	}


	@DeleteMapping("/FnDeleteRecord/{modules_menu_id}/{company_id}")
	public Object FnDeleteRecord(@PathVariable int modules_menu_id, @PathVariable int company_id) {
		return iAmModulesMenuService.FnDeleteRecord(modules_menu_id, company_id);

	}

	// Added by Mohit :- this api use when we need deleted by functionality

//	@DeleteMapping("/FnDeleteRecord/{modules_menu_id}/{company_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int modules_menu_id, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iAmModulesMenuService.FnDeleteRecord(modules_menu_id, company_id, deleted_by);
//
//	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CAmModulesMenuViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CAmModulesMenuViewModel> cAmModulesMenuViewModel = iAmModulesMenuService.FnShowAllActiveRecords(pageable, company_id);
		return cAmModulesMenuViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{modules_menu_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int modules_menu_id, @PathVariable int company_id) {
		Map<String, Object> responce = iAmModulesMenuService.FnShowParticularRecordForUpdate(modules_menu_id, company_id);
		return responce;
	}

//	@GetMapping("/FnShowAllReportRecords/{company_id}")
//	public Page<CAmModulesMenuRptModel> FnShowAllReportRecords(Pageable pageable,  @PathVariable int company_id) {
//		return iAmModulesMenuService.FnShowAllReportRecords(pageable, company_id);
//
//	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iAmModulesMenuService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{modules_menu_id}/{company_id}")
	public Page<CAmModulesMenuViewModel> FnShowParticularRecord(@PathVariable int modules_menu_id, Pageable pageable, @PathVariable int company_id) {
		return iAmModulesMenuService.FnShowParticularRecord(modules_menu_id, pageable, company_id);

	}


}
