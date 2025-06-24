package com.erp.AmModulesForms.Controller;

import com.erp.AmModulesForms.Model.CAmModulesFormsModel;
import com.erp.AmModulesForms.Model.CAmModulesFormsViewModel;
import com.erp.AmModulesForms.Service.IAmModulesFormsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/AmModulesForms")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmModulesFormsController {

	@Autowired
	IAmModulesFormsService iAmModulesFormsService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CAmModulesFormsModel cAmModulesFormsModel)
			throws SQLException {
		JSONObject responce = new JSONObject();
		try {
			responce = iAmModulesFormsService.FnAddUpdateRecord(cAmModulesFormsModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{modules_forms_id}/{company_id}")
	public Object FnDeleteRecord(@PathVariable int modules_forms_id, @PathVariable int company_id) {
		return iAmModulesFormsService.FnDeleteRecord(modules_forms_id, company_id);

	}
	// Added by Mohit :- this api use when we need deleted by functionality


//	@DeleteMapping("/FnDeleteRecord/{modules_forms_id}/{company_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int modules_forms_id, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iAmModulesFormsService.FnDeleteRecord(modules_forms_id, company_id,deleted_by );
//
//	}


	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public List<CAmModulesFormsViewModel> FnShowAllActiveRecords(@PathVariable int company_id)
			throws SQLException {
		return iAmModulesFormsService.FnShowAllActiveRecords(company_id);
	}

	@GetMapping("/FnShowParticularRecords/{modules_forms_id}/{company_id}")
	public CAmModulesFormsViewModel FnShowParticularRecord(@PathVariable int modules_forms_id, @PathVariable int company_id) {
		return iAmModulesFormsService.FnShowParticularRecord(modules_forms_id, company_id);

	}

//	old 
//	@GetMapping("/FnShowAllReportRecords")
//	public Page<CAmModulesFormsRptModel> FnShowAllReportRecords(Pageable pageable) {
//		return iAmModulesFormsService.FnShowAllReportRecords(pageable);
//
//	}

	//	new
	@GetMapping("/FnShowAllReportRecords")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable) {
		return iAmModulesFormsService.FnShowAllReportRecords(pageable);

	}


}
