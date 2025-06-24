package com.erp.XtStoppageProduction.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XtStoppageProduction.Model.CXtWeavingProductionStoppageModel;
import com.erp.XtStoppageProduction.Repository.IXtWeavingProductionStoppageRepository;
import com.erp.XtWeavingProductionSizingDetails.Model.*;
import com.erp.XtWeavingProductionSizingDetails.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CXtWeavingProductionStoppageServiceImpl implements IXtWeavingProductionStoppageService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXtWeavingProductionStoppageRepository iXtWeavingProductionStoppageRepository;


	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> response = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Get CommonId's
			JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
			int company_id = commonIdsObj.getInt("company_id");
			String production_set_no = commonIdsObj.getString("production_set_no");
			Integer subSectionId = null;
			if (commonIdsObj.has("cmb_sub_section_id")) {
				Object subSectionValue = commonIdsObj.get("cmb_sub_section_id");
				if (subSectionValue instanceof Integer) {
					subSectionId = (Integer) subSectionValue;
				} else if (subSectionValue instanceof String) {
					subSectionId = Integer.valueOf((String) subSectionValue);
				}
			}			JSONArray stoppageArray = jsonObject.getJSONArray("TransWVProdStoppageData");
			if (!stoppageArray.isEmpty()) {

				List<CXtWeavingProductionStoppageModel> stoppageModels = objectMapper.readValue(stoppageArray.toString(), new TypeReference<List<CXtWeavingProductionStoppageModel>>() {
				});

				List<Integer> distinctStoppageModelIds = stoppageModels.stream().map(CXtWeavingProductionStoppageModel::getWeaving_production_stoppage_id).collect(Collectors.toList());
				if (!distinctStoppageModelIds.isEmpty()) {
					if (production_set_no != null && !production_set_no.isEmpty()) {
						iXtWeavingProductionStoppageRepository.updateWeavingProductionSizingStoppageDetails(distinctStoppageModelIds, production_set_no, company_id,subSectionId);
					}else{
						iXtWeavingProductionStoppageRepository.updateWeavingProductionSizingStoppageDetailsWVWV(distinctStoppageModelIds, company_id,subSectionId);

					}
				}
				// Save stoppageModels using the repository
				iXtWeavingProductionStoppageRepository.saveAll(stoppageModels);
				response.put("success", 1);
				response.put("message", "Stoppage data saved successfully");
			}
		} catch (Exception e) {
			// Handle any other exceptions
			response.put("success", 0);
			response.put("message", "An unexpected error occurred: " + e.getMessage());
		}

		return response;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int weaving_production_stoppage_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			iXtWeavingProductionStoppageRepository.FnDeleteProductionSizingStoppageRecord(weaving_production_stoppage_id, deleted_by);
			responce.put("success", 1);
		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(Integer weaving_production_stoppage_id,int sub_section_id , int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Fetch Production Sizing Stoppage Record for update
			List<CXtWeavingProductionStoppageModel> weavingProductionStoppageRecords = iXtWeavingProductionStoppageRepository
					.FnShowParticularRecordForUpdate(weaving_production_stoppage_id,sub_section_id, company_id);
			response.put("WeavingProductionStoppageRecords", weavingProductionStoppageRecords);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
			return response;
		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdateWVWV(int weaving_production_stoppage_id,int sub_section_id ,int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {
			// Fetch Production Sizing Stoppage Record for update
			List<CXtWeavingProductionStoppageModel> weavingProductionStoppageRecords = iXtWeavingProductionStoppageRepository
					.FnShowParticularRecordForUpdateWVWV(weaving_production_stoppage_id,sub_section_id, company_id);
			response.put("WeavingProductionStoppageRecords", weavingProductionStoppageRecords);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
			return response;
		}
		return response;
	}
}