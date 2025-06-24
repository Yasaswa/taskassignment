package com.erp.XmSpinningProdCount.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmSpinningProdCount.Model.CXmSpinningProdCountLevelsModel;
import com.erp.XmSpinningProdCount.Model.CXmSpinningProdCountLevelsViewModel;
import com.erp.XmSpinningProdCount.Model.CxmSpinningProdCountModel;
import com.erp.XmSpinningProdCount.Repository.IXmSpinningProdCountLevelsRepository;
import com.erp.XmSpinningProdCount.Repository.IXmSpinningProdCountLevelsViewRepository;
import com.erp.XmSpinningProdCount.Repository.IXmSpinningProdCountRepository;
import com.erp.XmSpinningProdCount.Repository.IXmSpinningProdCountViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CXmSpinningProdCountServiceImpt implements IXmSpinningProdCountService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmSpinningProdCountRepository iXmSpinningProdCountRepository;

	@Autowired
	IXmSpinningProdCountViewRepository iXmSpinningProdCountViewRepository;

	@Autowired
	IXmSpinningProdCountLevelsRepository iXmSpinningProdCountLevelsRepository;
	
	@Autowired
	IXmSpinningProdCountLevelsViewRepository iXmSpinningProdCountLevelsViewRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		// get CommonId's
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");

		// get master data
		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray spinProdCountlevelArray = (JSONArray) jsonObject.get("SpinningProdCountLevelData");

		try {
			
			// Get Spinning Spinning ProdCount data
			CxmSpinningProdCountModel spinningProdCountModel = objectMapper.readValue(masterjson.toString(),
					CxmSpinningProdCountModel.class);

		    int production_count_id	= spinningProdCountModel.getProduction_count_id();
		    
			//save spinningProdCountModel
			CxmSpinningProdCountModel model = iXmSpinningProdCountRepository
					.checkIfNameExist(spinningProdCountModel.getProduction_count_name(), company_id);

			if (model != null) {
				responce.put("success", 0);
				responce.put("error", "Production Count Name is already exist!...");
		         return responce;
			}

			// Save spinningProdCountModel
			CxmSpinningProdCountModel respCxmSpinningProdCountModel = iXmSpinningProdCountRepository
					.save(spinningProdCountModel);


			//check here spinningProdCountArray and save/update it.
			if (!spinProdCountlevelArray.isEmpty()) {

				// update spinning Product count level details
				iXmSpinningProdCountLevelsRepository.updateSpinningProdCountLevelsDetails(
						respCxmSpinningProdCountModel.getProduction_count_id(), company_id);

				// JSON array into a list of CXmSpinningProdCountLevelsModel objects using an ObjectMapper
				List<CXmSpinningProdCountLevelsModel> cxmSpinningProdCountLevelsModel = objectMapper.readValue(
						spinProdCountlevelArray.toString(), new TypeReference<List<CXmSpinningProdCountLevelsModel>>() {
						});

				cxmSpinningProdCountLevelsModel.forEach(prodCountLevel -> {
					prodCountLevel.setProduction_count_id(respCxmSpinningProdCountModel.getProduction_count_id());

				});

				// Saved cxmSpinningProdCountLevelsModel
				iXmSpinningProdCountLevelsRepository.saveAll(cxmSpinningProdCountLevelsModel);

			}

			responce.put("data", respCxmSpinningProdCountModel);
			responce.put("success", 1);
			responce.put("error", "");
			responce.put("message",production_count_id == 0 ? "Record added successfully!..." : "Record updated successfully!...");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XmSpinningProdCount/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XmSpinningProdCount/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int production_count_id, String deleted_by, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			// Delete Spinning ProdCount
			iXmSpinningProdCountRepository.FnDeleteSpinningProdCountRecord(production_count_id, deleted_by, company_id);

			// Delete Spinning ProdCount Levels
			iXmSpinningProdCountLevelsRepository.FnDeleteSpinningProdCountLevelsRecord(production_count_id, deleted_by,
					company_id);
			responce.put("success", "1");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int production_count_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Fetch Spinning ProdCount Records
			CxmSpinningProdCountModel spinningProdCountData = 
					iXmSpinningProdCountRepository.FnShowSpinningProdCountRecordForUpdate(production_count_id, company_id);
		
			//Fetch Spinning ProdCount Levels Records
		    List<CXmSpinningProdCountLevelsViewModel> spinningProdCountLevelsData =
					iXmSpinningProdCountLevelsViewRepository.FnShowSpinningProdCountLevelsForUpdate(production_count_id, company_id);
		    
		    
			responce.put("success", 1);
			responce.put("spinningProdCountData", spinningProdCountData);
			responce.put("spinningProdCountLevelsData", spinningProdCountLevelsData);

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

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
