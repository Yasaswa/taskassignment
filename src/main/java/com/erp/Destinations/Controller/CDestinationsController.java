package com.erp.Destinations.Controller;

import com.erp.Destinations.Model.CDestinationsModel;
import com.erp.Destinations.Service.IDestinationsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/destination")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CDestinationsController {

	@Autowired
	IDestinationsService iDestinationsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CDestinationsModel cDestinationsModel) {
		Map<String, Object> resp = iDestinationsService.FnAddUpdateRecord(cDestinationsModel);
		return resp;
	}

	@DeleteMapping("/FnDeleteRecord/{destination_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int destination_id, @PathVariable String deleted_by) {
		return iDestinationsService.FnDeleteRecord(destination_id, deleted_by);
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cDestinationsViewModel = null;
		try {
			cDestinationsViewModel = iDestinationsService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDestinationsViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cDestinationsViewModel = null;

		try {
			cDestinationsViewModel = iDestinationsService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDestinationsViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{destination_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int destination_id) throws SQLException {
		Map<String, Object> resp = new HashMap();
		try {
			resp = iDestinationsService.FnShowParticularRecordForUpdate(destination_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{destination_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int destination_id)
			throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iDestinationsService.FnShowParticularRecord(company_id, destination_id);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cShiftRptModel = null;
		try {
			cShiftRptModel = iDestinationsService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cShiftRptModel;

	}

}
