package com.erp.State.Controller;

import com.erp.State.Model.CStateModel;
import com.erp.State.Service.IStateService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
@RequestMapping(value = "/api/state", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CStateController {

	@Autowired
	IStateService iStateService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CStateModel cStateModel) throws SQLException {
		JSONObject resp = new JSONObject();

		try {

			resp = iStateService.FnAddUpdateRecord(cStateModel);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{state_id}")
	public Object FnDeleteRecord(@PathVariable int state_id) {
		return iStateService.FnDeleteRecord(state_id);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cStateViewModel = null;
		try {
			cStateViewModel = iStateService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cStateViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cStateViewModel = null;

		try {
			cStateViewModel = iStateService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cStateViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{state_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int state_id) throws SQLException {
		Object cStateViewModel = null;
		try {
			cStateViewModel = iStateService.FnShowParticularRecordForUpdate(state_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cStateViewModel.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{state_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int state_id) throws SQLException {
		Object cStateViewModel = null;
		try {
			cStateViewModel = iStateService.FnShowParticularRecord(company_id, state_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cStateViewModel.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object CStateRptModel = null;
		try {
			CStateRptModel = iStateService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CStateRptModel;

	}


}
