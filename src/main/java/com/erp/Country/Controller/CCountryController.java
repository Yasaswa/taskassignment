package com.erp.Country.Controller;

import com.erp.Country.Model.CCountryModel;
import com.erp.Country.Service.ICountryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/country")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCountryController {

	@Autowired
	ICountryService iCountryService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestBody CCountryModel cCountryModel) throws SQLException {
		JSONObject resp = new JSONObject();

		try {

			resp = iCountryService.FnAddUpdateRecord(cCountryModel);


		} catch (Exception e) {
			e.printStackTrace();

		}
		return resp.toString();
	}

	@GetMapping("/FnFetchCountryCodes")
	public List<String> FnFetchCountryCodes() {
		List<String> countryViewModels = null;
		try {
			countryViewModels = iCountryService.FnFetchCountryCodes();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return countryViewModels;
	}

}
