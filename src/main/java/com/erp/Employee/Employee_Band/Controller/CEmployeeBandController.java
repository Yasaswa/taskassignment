package com.erp.Employee.Employee_Band.Controller;

import com.erp.Employee.Employee_Band.Model.CEmployeeBandModel;
import com.erp.Employee.Employee_Band.Service.IEmployeeBandService;
import com.erp.security.auth.JwtUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
@RequestMapping("/api/employeesband")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CEmployeeBandController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IEmployeeBandService iEmployeeBandService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CEmployeeBandModel cEmployeeBandModel) throws SQLException {
		JSONObject resp = new JSONObject();
		try {

			resp = iEmployeeBandService.FnAddUpdateRecord(cEmployeeBandModel);
			return resp.toString();

		} catch (Exception e) {

		}
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{employee_band_id}")
	public Object FnDeleteRecord(@PathVariable int employee_band_id) {
		return iEmployeeBandService.FnDeleteRecord(employee_band_id);
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cEmployeeBandViewModel = null;
		try {
			cEmployeeBandViewModel = iEmployeeBandService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cEmployeeBandViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cEmployeeBandViewModel = null;
		try {
			cEmployeeBandViewModel = iEmployeeBandService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cEmployeeBandViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{employee_band_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int employee_band_id) throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iEmployeeBandService.FnShowParticularRecordForUpdate(employee_band_id);
		} catch (Exception e) {
		}
		return resp.toString();

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{employee_band_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int employee_band_id) throws SQLException {
		JSONObject resp = new JSONObject();
		HttpStatus status;
		try {
			resp = iEmployeeBandService.FnShowParticularRecord(company_id, employee_band_id);
		} catch (Exception e) {

		}
		return resp.toString();

	}


}
