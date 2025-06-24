package com.erp.Sl_Gl_Mapping.Controller;

import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingModel;
import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingViewModel;
import com.erp.Sl_Gl_Mapping.Service.ISl_Gl_MappingService;
import com.erp.security.auth.JwtUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/slglmapping")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSl_Gl_MappingController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	ISl_Gl_MappingService iSl_Gl_MappingService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CSl_Gl_MappingModel cSl_Gl_MappingModel) throws SQLException {

		JSONObject resp = new JSONObject();
		HttpStatus status;

		try {
			resp = iSl_Gl_MappingService.FnAddUpdateRecord(cSl_Gl_MappingModel);
			return resp;

		} catch (Exception e) {

		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{sl_gl_mapping_id}")
	public Object FnDeleteRecord(@PathVariable int sl_gl_mapping_id) {
		return iSl_Gl_MappingService.FnDeleteRecord(sl_gl_mapping_id);
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cSl_Gl_MappingViewModel = null;
		try {
			cSl_Gl_MappingViewModel = iSl_Gl_MappingService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSl_Gl_MappingViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cSl_Gl_MappingViewModel = null;

		try {
			cSl_Gl_MappingViewModel = iSl_Gl_MappingService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSl_Gl_MappingViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{sl_gl_mapping_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int sl_gl_mapping_id) throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iSl_Gl_MappingService.FnShowParticularRecordForUpdate(sl_gl_mapping_id);
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{schedule_ledger_id}/{company_id}")
	public Page<CSl_Gl_MappingViewModel> FnShowParticularRecord(@PathVariable int company_id,
	                                                            @PathVariable int schedule_ledger_id, Pageable pageable) throws SQLException {
		return iSl_Gl_MappingService.FnShowParticularRecord(company_id, schedule_ledger_id, pageable);

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cSl_Gl_MappingViewModel = null;
		try {
			cSl_Gl_MappingViewModel = iSl_Gl_MappingService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cSl_Gl_MappingViewModel;

	}

}
