package com.erp.Transporter.Controller;

import com.erp.Transporter.Model.CTransporterViewModel;
import com.erp.Transporter.Service.ITransporterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/transporter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CTransporterController {
	@Autowired
	ITransporterService iTransporterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("TransporterData") JSONObject jsonObject) {
		Map<String, Object> responce = iTransporterService.FnAddUpdateRecord(jsonObject);
		return responce;

	}


	@GetMapping("/FnShowAllRecords/{transporter_id}/{company_id}")
	public Map<String, Object> FnShowAllRecords(@PathVariable int transporter_id, @PathVariable int company_id) throws SQLException {
		return iTransporterService.FnShowAllRecords(transporter_id, company_id);
	}


	@DeleteMapping("/FnDeleteRecord/{transporter_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int transporter_id, @PathVariable String deleted_by) {
		return iTransporterService.FnDeleteRecord(transporter_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cTransporterViewModel = null;
		try {
			cTransporterViewModel = iTransporterService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterViewModel;
	}


	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cTransporterViewModel = null;

		try {
			cTransporterViewModel = iTransporterService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{transporter_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int transporter_id) throws SQLException {
		Object cTransporterViewModel = null;
		try {
			cTransporterViewModel = iTransporterService.FnShowParticularRecordForUpdate(transporter_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterViewModel;

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{transporter_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id, @PathVariable int transporter_id) throws SQLException {
		JSONObject cTransporterViewModel = null;
		try {
			cTransporterViewModel = iTransporterService.FnShowParticularRecord(company_id, company_branch_id, transporter_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterViewModel.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cTransporterRptModel = null;
		try {
			cTransporterRptModel = iTransporterService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterRptModel;

	}


}
