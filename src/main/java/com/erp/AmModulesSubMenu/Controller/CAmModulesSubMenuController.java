package com.erp.AmModulesSubMenu.Controller;

import com.erp.AmModulesSubMenu.Model.CAmModulesSubMenuModel;
import com.erp.AmModulesSubMenu.Model.CAmModulesSubMenuViewModel;
import com.erp.AmModulesSubMenu.Service.IAmModulesSubMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/AmModulesSubMenu")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmModulesSubMenuController {

	@Autowired
	IAmModulesSubMenuService iAmModulesSubMenuService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CAmModulesSubMenuModel AmModulesSubMenuModel) {
		return iAmModulesSubMenuService.FnAddUpdateRecord(AmModulesSubMenuModel);

	}

	@DeleteMapping("/FnDeleteRecord/{modules_sub_menu_id}/{company_id}")
	public Object FnDeleteRecord(@PathVariable int modules_sub_menu_id, @PathVariable int company_id) {
		return iAmModulesSubMenuService.FnDeleteRecord(modules_sub_menu_id, company_id);

	}
	// Added by Mohit :- this api use when we need deleted by functionality

//	@DeleteMapping("/FnDeleteRecord/{modules_sub_menu_id}/{company_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int modules_sub_menu_id, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iAmModulesSubMenuService.FnDeleteRecord(modules_sub_menu_id, company_id,deleted_by);
//
//	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CAmModulesSubMenuViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CAmModulesSubMenuViewModel> cAmModulesSubMenuViewModel = iAmModulesSubMenuService.FnShowAllActiveRecords(pageable, company_id);
		return cAmModulesSubMenuViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{modules_sub_menu_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int modules_sub_menu_id, @PathVariable int company_id) {
		Map<String, Object> responce = iAmModulesSubMenuService.FnShowParticularRecordForUpdate(modules_sub_menu_id, company_id);
		return responce;
	}

//	@GetMapping("/FnShowAllReportRecords/{company_id}")
//	public Page<CAmModulesSubMenuRptModel> FnShowAllReportRecords(Pageable pageable,@PathVariable int company_id) {
//		return iAmModulesSubMenuService.FnShowAllReportRecords(pageable, company_id);
//
//	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iAmModulesSubMenuService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{modules_sub_menu_id}/{company_id}")
	public Page<CAmModulesSubMenuViewModel> FnShowParticularRecord(@PathVariable int modules_sub_menu_id, Pageable pageable, @PathVariable int company_id) {
		return iAmModulesSubMenuService.FnShowParticularRecord(modules_sub_menu_id, pageable, company_id);

	}


}
