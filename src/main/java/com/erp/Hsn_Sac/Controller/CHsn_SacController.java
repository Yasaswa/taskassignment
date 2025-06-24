package com.erp.Hsn_Sac.Controller;

import com.erp.Hsn_Sac.Model.CHsn_SacModel;
import com.erp.Hsn_Sac.Service.IHsn_SacService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/hsnsac", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHsn_SacController {

	@Autowired
	IHsn_SacService iHsn_SacService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CHsn_SacModel cHsn_SacModel) throws SQLException {
		Map<String, Object> resp = new HashMap<>();
		resp = iHsn_SacService.FnAddUpdateRecord(cHsn_SacModel);
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{hsn_sac_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int hsn_sac_id, @PathVariable String deleted_by) {
		return iHsn_SacService.FnDeleteRecord(hsn_sac_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cHsn_SacViewModel = null;
		cHsn_SacViewModel = iHsn_SacService.FnShowAllRecords(pageable);

		return cHsn_SacViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cHsn_SacViewModel = null;
		cHsn_SacViewModel = iHsn_SacService.FnShowAllActiveRecords(pageable);

		return cHsn_SacViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{hsn_sac_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int hsn_sac_id) throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iHsn_SacService.FnShowParticularRecordForUpdate(hsn_sac_id);

		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{hsn_sac_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int hsn_sac_id)
			throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iHsn_SacService.FnShowParticularRecord(company_id, hsn_sac_id);
		System.out.println("Responce: " + resp.toString());

		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object CHsn_SacRptModel = null;

		CHsn_SacRptModel = iHsn_SacService.FnShowAllReportRecords(pageable);

		return CHsn_SacRptModel;

	}

}
