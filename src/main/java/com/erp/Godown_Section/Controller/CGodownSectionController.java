package com.erp.Godown_Section.Controller;

import com.erp.Godown_Section.Model.CGodownSectionModel;
import com.erp.Godown_Section.Service.IGodownSectionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/godownsection", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CGodownSectionController {

	@Autowired
	IGodownSectionService iGodownSectionService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CGodownSectionModel cGodownSectionModel) throws SQLException {

		JSONObject resp = new JSONObject();
		try {
			resp = iGodownSectionService.FnAddUpdateRecord(cGodownSectionModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{godown_section_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int godown_section_id, @PathVariable String deleted_by) {
		return iGodownSectionService.FnDeleteRecord(godown_section_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cGodownSectionViewModel = null;
		try {
			cGodownSectionViewModel = iGodownSectionService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cGodownSectionViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cGodownSectionViewModel = null;
		try {
			cGodownSectionViewModel = iGodownSectionService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cGodownSectionViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{godown_section_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int godown_section_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iGodownSectionService.FnShowParticularRecordForUpdate(godown_section_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{godown_section_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int godown_section_id)
			throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iGodownSectionService.FnShowParticularRecord(company_id, godown_section_id);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iGodownSectionService.FnShowAllReportRecords(pageable);
	}

}
