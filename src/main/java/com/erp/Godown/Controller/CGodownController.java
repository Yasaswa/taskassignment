package com.erp.Godown.Controller;

import com.erp.Godown.Model.CGodownModel;
import com.erp.Godown.Service.IGodownService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/api/godown", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CGodownController {

	@Autowired
	IGodownService iGodownService;


	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CGodownModel cGodownModel) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iGodownService.FnAddUpdateRecord(cGodownModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{godown_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int godown_id, @PathVariable String deleted_by) {
		return iGodownService.FnDeleteRecord(godown_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cGodownViewModel = null;
		try {
			cGodownViewModel = iGodownService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cGodownViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cGodownViewModel = null;
		try {
			cGodownViewModel = iGodownService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cGodownViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{godown_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int godown_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iGodownService.FnShowParticularRecordForUpdate(godown_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{godown_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int godown_id) throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iGodownService.FnShowParticularRecord(company_id, godown_id);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iGodownService.FnShowAllReportRecords(pageable);

	}


}
