package com.erp.Taxtype.Controller;

import com.erp.Taxtype.Model.CTaxtypeModel;
import com.erp.Taxtype.Service.ITaxtypeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/taxtype", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CTaxtypeController {
	@Autowired
	ITaxtypeService iTaxtypeService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CTaxtypeModel cTaxtypeModel) throws SQLException {
		Map<String, Object> resp = new HashMap<>();
		resp = iTaxtypeService.FnAddUpdateRecord(cTaxtypeModel);
		return resp;
	}

	@DeleteMapping("/FnDeleteRecord/{taxtype_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int taxtype_id, @PathVariable String deleted_by) {
		return iTaxtypeService.FnDeleteRecord(taxtype_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	public Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object ctaxTypeViewModel = iTaxtypeService.FnShowAllRecords(pageable);
		return ctaxTypeViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object ctaxTypeViewModel = null;
		ctaxTypeViewModel = iTaxtypeService.FnShowAllActiveRecords(pageable);
		return ctaxTypeViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{taxtype_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int taxtype_id) throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iTaxtypeService.FnShowParticularRecordForUpdate(taxtype_id);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{taxtype_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int taxtype_id)
			throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iTaxtypeService.FnShowParticularRecord(company_id, taxtype_id);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cTaxtypeRptModel = null;
		cTaxtypeRptModel = iTaxtypeService.FnShowAllReportRecords(pageable);
		return cTaxtypeRptModel;

	}

}
