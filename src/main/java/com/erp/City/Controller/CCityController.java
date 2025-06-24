package com.erp.City.Controller;

import com.erp.City.Model.CCityModel;
import com.erp.City.Model.CCityViewModel;
import com.erp.City.Service.ICityService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/city")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCityController {

	@Autowired
	ICityService iCityService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CCityModel CityModel) throws SQLException {
		JSONObject resp = new JSONObject();
		try {
			resp = iCityService.FnAddUpdateRecord(CityModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp.toString();
	}

	@DeleteMapping("/FnDeleteRecord/{city_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int city_id, @PathVariable String deleted_by) {
		return iCityService.FnDeleteRecord(city_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object CityViewModel = null;

		try {
			CityViewModel = iCityService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CityViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object CityViewModel = null;

		try {
			CityViewModel = iCityService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CityViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{city_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int city_id) throws SQLException {
		Map<String, Object> resp = new HashMap();
		try {
			resp = iCityService.FnShowParticularRecordForUpdate(city_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{city_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int city_id) throws SQLException {
		Object CityViewModel = null;
		try {
			CityViewModel = iCityService.FnShowParticularRecord(company_id, city_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CityViewModel;

	}

	@GetMapping("/FnShowAllReportRecords")
	Object FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Object cityRptModel = null;
		try {
			cityRptModel = iCityService.FnShowAllReportRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityRptModel;

	}

	@PostMapping("/FnShowFilterRecords")
	public List<CCityViewModel> FnShowFilterRecords(@RequestParam("jsonQuery") org.json.JSONObject jsonQuery) {

		return iCityService.FnShowFilterRecords(jsonQuery);

	}

	@PostMapping("/FnMShowFilterRecords/{page}/{size}")
	public String FnMShowFilterRecords(@RequestParam("jsonQuery") JSONObject jsonQuery, @PathVariable int page,
	                                   @PathVariable int size) {
		JSONObject jsonObject = new JSONObject();
		jsonObject = iCityService.FnMShowFilterRecords(jsonQuery, page, size);
		return jsonObject.toString();

	}

}
