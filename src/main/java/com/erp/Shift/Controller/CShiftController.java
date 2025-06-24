package com.erp.Shift.Controller;

import com.erp.Shift.Model.CShiftModel;
import com.erp.Shift.Service.IShiftService;
import com.erp.security.auth.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/shift")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CShiftController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IShiftService iShiftService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CShiftModel cShiftModel) throws SQLException {
		Map<String, Object> resp = iShiftService.FnAddUpdateRecord(cShiftModel);
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{shift_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int shift_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iShiftService.FnDeleteRecord(shift_id, company_id, deleted_by);
	}

	@GetMapping("/FnShowAllRecords/{company_id}")
	public Map<String, Object> FnShowAllRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cShiftViewModel = iShiftService.FnShowAllRecords(pageable, company_id);
		return cShiftViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Map<String, Object> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cShiftViewModel = iShiftService.FnShowAllActiveRecords(pageable, company_id);
		return cShiftViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{shift_id}" )
//			"/{company_id}" +
//			"")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int shift_id) throws SQLException {
		Map<String, Object> resp = iShiftService.FnShowParticularRecordForUpdate(shift_id);
		return resp;

	}

	@GetMapping("/FnShowParticularRecord/{shift_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecord(@PathVariable int company_id,
	                                                  @PathVariable int shift_id) throws SQLException {
		Map<String, Object> resp = iShiftService.FnShowParticularRecord(company_id, shift_id);
		return resp;

	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cShiftRptModel = iShiftService.FnShowAllReportRecords(pageable, company_id);
		return cShiftRptModel;

	}

}
