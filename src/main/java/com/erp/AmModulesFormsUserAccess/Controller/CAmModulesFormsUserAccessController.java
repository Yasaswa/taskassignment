package com.erp.AmModulesFormsUserAccess.Controller;

import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;
import com.erp.AmModulesFormsUserAccess.Service.IAmModulesFormsUserAccessService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/AmModulesFormsUserAccess")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAmModulesFormsUserAccessController {

	@Autowired
	IAmModulesFormsUserAccessService iAmModulesFormsUserAccessService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("UserAccessData") JSONObject jsonObject) {
		Map<String, Object> responce = iAmModulesFormsUserAccessService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@GetMapping("/FnShowAllActiveRecords/{user_code}/{company_id}")
	public List<CAmModulesFormsUserAccessViewModel> FnShowAllActiveRecords(@PathVariable String user_code,
	                                                                       @PathVariable int company_id) throws SQLException {
		return iAmModulesFormsUserAccessService.FnShowAllActiveRecords(user_code, company_id);
	}

}
