package com.erp.Godown_Section_Beans.Controller;

import com.erp.Employee.Employee_Band.Service.IEmployeeBandService;
import com.erp.Godown_Section_Beans.Model.CGodownSectionBeansModel;
import com.erp.Godown_Section_Beans.Service.IGodownSectionBeansService;
import com.erp.security.auth.JwtUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/godownsectionbeans")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CGodownSectionBeansController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IGodownSectionBeansService iGodownSectionBeansService;

	@Autowired
	IEmployeeBandService iEmployeeBandService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CGodownSectionBeansModel cGodownSectionBeansModel)
			throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iGodownSectionBeansService.FnAddUpdateRecord(cGodownSectionBeansModel);
		} catch (Exception e) {
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{godown_section_beans_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int godown_section_beans_id, @PathVariable String deleted_by) {
		return iGodownSectionBeansService.FnDeleteRecord(godown_section_beans_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cGodownSectionBeansViewModel = null;
		try {
			cGodownSectionBeansViewModel = iGodownSectionBeansService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cGodownSectionBeansViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cGodownSectionBeansViewModel = null;
		try {
			cGodownSectionBeansViewModel = iGodownSectionBeansService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cGodownSectionBeansViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{godown_section_beans_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int godown_section_beans_id) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iGodownSectionBeansService.FnShowParticularRecordForUpdate(godown_section_beans_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{godown_section_beans_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int godown_section_beans_id)
			throws SQLException {
		JSONObject resp = new JSONObject();

		try {
			resp = iGodownSectionBeansService.FnShowParticularRecord(company_id, godown_section_beans_id);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();

	}

	@GetMapping("/FnShowAllReportRecords")
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		return iGodownSectionBeansService.FnShowAllReportRecords(pageable);

	}


}
