package com.erp.District.Controller;

import com.erp.District.Model.CDistrictModel;
import com.erp.District.Service.IDistrictService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;


@RestController
@RequestMapping(value = "/api/district", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CDistrictController {


	@Autowired
	IDistrictService iDistrictService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CDistrictModel cDistrictModel) throws SQLException {
		JSONObject resp = new JSONObject();

		try {

			resp = iDistrictService.FnAddUpdateRecord(cDistrictModel);
			return resp;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{district_id}")
	public Object FnDeleteRecord(@PathVariable int district_id) {
		return iDistrictService.FnDeleteRecord(district_id);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cDistrictViewModel = null;
		try {
			cDistrictViewModel = iDistrictService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDistrictViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {

		Object cDistrictViewModel = null;

		try {
			cDistrictViewModel = iDistrictService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDistrictViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{district_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int district_id) throws SQLException {
		Object cDistrictViewModel = null;
		try {
			cDistrictViewModel = iDistrictService.FnShowParticularRecordForUpdate(district_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDistrictViewModel;

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{district_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int district_id) throws SQLException {
		Object cDistrictViewModel = null;
		try {
			cDistrictViewModel = iDistrictService.FnShowParticularRecord(company_id, district_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDistrictViewModel;

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cDistrictRptModel = null;
		try {
			cDistrictRptModel = iDistrictService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDistrictRptModel;

	}


}
