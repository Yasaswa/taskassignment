package com.erp.HmProfessionalTax.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmProfessionalTax.Model.CHmProfessionalTaxModel;
import com.erp.HmProfessionalTax.Repository.IHmProfessionalTaxRepository;
import com.erp.HmProfessionalTax.Repository.IHmProfessionalTaxViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CHmProfessionalTaxServiceImpl implements IHmProfessionalTaxService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHmProfessionalTaxRepository iHmProfessionalTaxRepository;

	@Autowired
	IHmProfessionalTaxViewRepository iHmProfessionalTaxViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		int professional_tax_id = 0;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String gender = commonIdsObj.getString("gender");

		JSONArray detailsArray = (JSONArray) jsonObject.get("TaxDetailsData");

		try {

			iHmProfessionalTaxRepository.updateProfessionalTaxRecord(company_id, gender);

			if (detailsArray != null) {

				List<CHmProfessionalTaxModel> cHmProfessionalTaxModel = objectMapper.readValue(detailsArray.toString(),
						new TypeReference<List<CHmProfessionalTaxModel>>() {
						});

				List<CHmProfessionalTaxModel> responseContent = iHmProfessionalTaxRepository
						.saveAll(cHmProfessionalTaxModel);

//				for (Object obj : detailsArray) {
//					JSONObject detailObject = (JSONObject) obj;
//					professional_tax_id = detailObject.getInt("professional_tax_id");
//
//				}
				responce.put("success", 1);
				responce.put("data", responseContent);
				responce.put("error", "");
				responce.put("message",
						professional_tax_id == 0 ? "Record added successfully!..." : "Record update successfully!");

			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/HmProfessionalTax/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HmProfessionalTax/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int company_id, String deleted_by) {
		Map<String, Object> resp = new HashMap<>();
		try {
			iHmProfessionalTaxRepository.FnDeleteRecord(company_id, deleted_by);
			resp.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("success", 0);
			resp.put("error", e.getMessage());
		}
		return null;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(String gender) {
		Map<String, Object> responce = new HashMap<>();
		try {
			List<CHmProfessionalTaxModel> responceProfessionalTaxDetails = iHmProfessionalTaxRepository.FnShowParticularRecordForUpdate(gender);
			responce.put("data", responceProfessionalTaxDetails);
			responce.put("success", 1);

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

}
