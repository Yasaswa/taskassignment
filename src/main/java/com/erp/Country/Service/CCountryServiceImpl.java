package com.erp.Country.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Country.Model.CCountryModel;
import com.erp.Country.Repository.ICountryRepository;
import com.erp.Country.Repository.ICountryViewRepository;
import com.erp.State.Model.CStateModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CCountryServiceImpl implements ICountryService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICountryRepository iCountryRepository;

	@Autowired
	ICountryViewRepository iCountryViewRepository;


	@Override
	public List<String> FnFetchCountryCodes() {
		List<String> json = null;
		try {
			json = iCountryViewRepository.FnFetchCountryCodes();
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}


	@Override
	public JSONObject FnAddUpdateRecord(CCountryModel cCountryModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		CCountryModel MyModel = null;
		int company_id = cCountryModel.getCompany_id();
		try {
			Optional<CCountryModel> option = iCountryRepository.findById(cCountryModel.getCountry_id());

			if (option.isPresent()) {
				CCountryModel mymodel = iCountryRepository.save(cCountryModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Country  updated successfully!..");
				return resp;
			} else {
				
//				CCountryModel model = iCountryRepository.getCheck(cCountryModel.getCountry_name());
//				if (model != null) {
//					resp.put("success", "0");
//					resp.put("data", "");
//					resp.put("error", " Country Name is already exist!");
//
//					return resp;
//
//				} 
				//Check similar Country Name and short name is exist or not
				CCountryModel resultsCountryeName = null;
				if (cCountryModel.getCountry_short_name() == null || cCountryModel.getCountry_short_name().isEmpty()) {
					resultsCountryeName = iCountryRepository.getCheck(cCountryModel.getCountry_name(),
							null, cCountryModel.getCompany_id());
				} else {
					resultsCountryeName = iCountryRepository.getCheck(cCountryModel.getCountry_name(),
							cCountryModel.getCountry_short_name(),cCountryModel.getCompany_id());
				}

				if (resultsCountryeName != null) {
					resp.put("success", 0);
					resp.put("message", " State Name and Short Name is already exist!");
					return resp;
				}else {

					CCountryModel CountryModel = iCountryRepository.save(cCountryModel);
					String json = objectMapper.writeValueAsString(CountryModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");

					System.out.println(" Country saved succesfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/country/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/country/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}

		return resp;

	}

}
