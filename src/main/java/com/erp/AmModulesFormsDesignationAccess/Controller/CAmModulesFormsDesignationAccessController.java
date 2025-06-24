package com.erp.AmModulesFormsDesignationAccess.Controller;

import com.erp.AmModulesFormsDesignationAccess.Model.CAmModulesFormsDesignationAccessViewModel;
import com.erp.AmModulesFormsDesignationAccess.Service.IAmModulesFormsDesignationAccessService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/AmModulesFormsDesignationAccess")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmModulesFormsDesignationAccessController {

	@Autowired
	IAmModulesFormsDesignationAccessService iAmModulesFormsDesignationAccessService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("DesignationAccessData") JSONObject jsonObject) {
		Map<String, Object> responce = iAmModulesFormsDesignationAccessService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{modules_forms_designation_access_id}/{company_id}")
	public Object FnDeleteRecord(@PathVariable int modules_forms_designation_access_id, @PathVariable int company_id) {
		return iAmModulesFormsDesignationAccessService.FnDeleteRecord(modules_forms_designation_access_id, company_id);
	}

	// Added by Mohit :- this api use when we need deleted by functionality.. 04-01-24

//	@DeleteMapping("/FnDeleteRecord/{modules_forms_designation_access_id}/{company_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int modules_forms_designation_access_id, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iAmModulesFormsDesignationAccessService.FnDeleteRecord(modules_forms_designation_access_id, company_id, deleted_by);
//	}


	@GetMapping("/FnShowAllActiveRecords/{designation_id}/{company_id}")
	public List<CAmModulesFormsDesignationAccessViewModel> FnShowAllActiveRecords(@PathVariable int designation_id, @PathVariable int company_id) throws SQLException {
		List<CAmModulesFormsDesignationAccessViewModel> cAmModulesFormsDesignationAccessViewModel = iAmModulesFormsDesignationAccessService.FnShowAllActiveRecords(designation_id, company_id);
		return cAmModulesFormsDesignationAccessViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{modules_forms_designation_access_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int modules_forms_designation_access_id, @PathVariable int company_id) {
		Map<String, Object> responce = new HashMap<>();
		responce = iAmModulesFormsDesignationAccessService.FnShowParticularRecordForUpdate(modules_forms_designation_access_id, company_id);
		return responce;
	}


	@GetMapping("/FnShowParticularRecords/{modules_forms_designation_access_id}/{company_id}")
	public Page<CAmModulesFormsDesignationAccessViewModel> FnShowParticularRecord(@PathVariable int modules_forms_designation_access_id, Pageable pageable, @PathVariable int company_id) {
		Page<CAmModulesFormsDesignationAccessViewModel> cAmModulesFormsDesignationAccessViewModel = iAmModulesFormsDesignationAccessService.FnShowParticularRecord(modules_forms_designation_access_id, pageable, company_id);
		return cAmModulesFormsDesignationAccessViewModel;

	}


}
