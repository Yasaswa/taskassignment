package com.erp.Country.Service;

import com.erp.Country.Model.CCountryModel;
import org.json.JSONObject;

import java.util.List;

public interface ICountryService {

	JSONObject FnAddUpdateRecord(CCountryModel cCountryModel);

	List<String> FnFetchCountryCodes();

}
