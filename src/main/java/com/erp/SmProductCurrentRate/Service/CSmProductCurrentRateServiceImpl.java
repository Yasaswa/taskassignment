package com.erp.SmProductCurrentRate.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductCurrentRate.Model.CSmProductCurrentRateModel;
import com.erp.SmProductCurrentRate.Repository.ISmProductCurrentRateRepository;
import com.erp.SmProductCurrentRate.Repository.ISmProductCurrentRateViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CSmProductCurrentRateServiceImpl implements ISmProductCurrentRateService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ISmProductCurrentRateRepository iSmProductCurrentRateRepository;

	@Autowired
	ISmProductCurrentRateViewRepository iSmProductCurrentRateViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
	
		JSONArray prodCurrentRateArray = (JSONArray) jsonObject.get("ProductCurrentRateData");
		
		try {
			
			// JSON array into a list of CSmProductCurrentRateModel objects using an ObjectMapper
			List<CSmProductCurrentRateModel> cSmProductCurrentRateModel = objectMapper.readValue(
					prodCurrentRateArray.toString(), new TypeReference<List<CSmProductCurrentRateModel>>() {
					});

			//update cSmProductCurrentRateModel 
			cSmProductCurrentRateModel.forEach(currentRateRec ->{
				
				String product_material_id = currentRateRec.getProduct_material_id();
				iSmProductCurrentRateRepository.updateProductCurrentRateDetails(product_material_id,company_id);
				
			});
			
			//save cSmProductCurrentRateModel
			List<CSmProductCurrentRateModel> respProductCurrentRateModel = iSmProductCurrentRateRepository.saveAll(cSmProductCurrentRateModel);
			
			responce.put("success", 1);
			responce.put("data", respProductCurrentRateModel);
			responce.put("error", "");
			responce.put("message","Record added successfully..!");
			
		}catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductCurrentRate/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/SmProductCurrentRate/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}
	
	}
