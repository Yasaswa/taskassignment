package com.erp.Taxation.Controller;

import com.erp.Taxation.Model.CTaxationModel;
import com.erp.Taxation.Service.ITaxationService;
import com.erp.security.auth.JwtUtils;
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
@RequestMapping(value = "/api/taxation", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CTaxationController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	ITaxationService iTaxationService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CTaxationModel cTaxationModel) throws SQLException {
		Map<String, Object> resp = new HashMap<>();
		resp = iTaxationService.FnAddUpdateRecord(cTaxationModel);
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{taxation_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int taxation_id, @PathVariable String deleted_by) {
		return iTaxationService.FnDeleteRecord(taxation_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cTaxationViewModel = iTaxationService.FnShowAllRecords(pageable);
		return cTaxationViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cTaxationViewModel = null;
		cTaxationViewModel = iTaxationService.FnShowAllActiveRecords(pageable);
		return cTaxationViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{taxation_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int taxation_id) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iTaxationService.FnShowParticularRecordForUpdate(taxation_id);
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{taxation_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int taxation_id)
			throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iTaxationService.FnShowParticularRecord(company_id, taxation_id);

		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iTaxationService.FnShowAllReportRecords(pageable);

	}
}
