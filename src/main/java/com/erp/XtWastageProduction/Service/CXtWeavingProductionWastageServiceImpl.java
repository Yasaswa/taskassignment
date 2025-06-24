package com.erp.XtWastageProduction.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XtStoppageProduction.Model.CXtWeavingProductionStoppageModel;
import com.erp.XtWastageProduction.Model.CXtWeavingProductionWastageModel;
import com.erp.XtWastageProduction.Repository.IXtWeavingProductionWastageRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CXtWeavingProductionWastageServiceImpl implements IXtWeavingProductionWastageService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IXtWeavingProductionWastageRepository iXtWeavingWastageProductionSizingWastageRepository;


    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Get CommonId's
            JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
            int company_id = commonIdsObj.getInt("company_id");
            String production_set_no = commonIdsObj.getString("production_set_no");

            // Get TransSZStoppageData
            JSONArray stoppageArray = jsonObject.getJSONArray("TransWVProdWastageData");
            Integer subSectionId = null;
            if (commonIdsObj.has("cmb_sub_section_id")) {
                Object subSectionValue = commonIdsObj.get("cmb_sub_section_id");
                if (subSectionValue instanceof Integer) {
                    subSectionId = (Integer) subSectionValue;
                } else if (subSectionValue instanceof String) {
                    subSectionId = Integer.valueOf((String) subSectionValue);
                }
            }
            if (!stoppageArray.isEmpty()) {

                // Convert JSONArray to list of CXtWeavingWastageProductionSizingWastageModel using ObjectMapper
                List<CXtWeavingProductionWastageModel> wastageModels =
                        objectMapper.readValue(stoppageArray.toString(), new TypeReference<List<CXtWeavingProductionWastageModel>>() {
                        });
                // Save stoppageModels using the repository
                List<Integer> distinctWastageIds = wastageModels.stream().map(CXtWeavingProductionWastageModel::getWeaving_production_wastage_id).collect(Collectors.toList());
                if (!distinctWastageIds.isEmpty()) {
                    if (production_set_no != null && !production_set_no.isEmpty()) {
                        iXtWeavingWastageProductionSizingWastageRepository.FnUpdateWastageRecords(distinctWastageIds, production_set_no, company_id, subSectionId);
                    } else {
                        iXtWeavingWastageProductionSizingWastageRepository.updateWeavingProductionSizingWastageDetailsWVWV(distinctWastageIds, company_id, subSectionId);

                    }
                }


                iXtWeavingWastageProductionSizingWastageRepository.saveAll(wastageModels);
                // Prepare the success response
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
    public Map<String, Object> FnDeleteRecord(int weaving_production_wastage_id, String deleted_by) {
        // Delete Production Sizing Stoppage Record
        iXtWeavingWastageProductionSizingWastageRepository
                .FnDeleteProductionSizingWastageRecord(weaving_production_wastage_id, deleted_by);
        Map<String, Object> responce = new HashMap<>();
        try {
            int weaving_production_stoppage_id = 0;
            iXtWeavingWastageProductionSizingWastageRepository.FnDeleteProductionSizingWastageRecord(weaving_production_stoppage_id, deleted_by);
            responce.put("success", 1);
        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
//        return Map.of();

    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(String production_set_no, int sub_section_id, int company_id) {
        // Fetch Production Sizing Stoppage Record for update
        Map<String, Object> response = new HashMap<>();
        try {
            List<CXtWeavingProductionWastageModel> wastageModels = iXtWeavingWastageProductionSizingWastageRepository.FnShowParticularRecordForUpdate(production_set_no, sub_section_id, company_id);
            response.put("WeavingProductionWastageRecords", wastageModels);
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
    public Map<String, Object> FnShowParticularRecordForUpdateWVWV(int weaving_production_wastage_id, int sub_section_id, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Fetch Production Sizing Stoppage Record for update
            List<CXtWeavingProductionWastageModel> wastageModels = iXtWeavingWastageProductionSizingWastageRepository
                    .FnShowParticularRecordForUpdateWVWV(weaving_production_wastage_id, sub_section_id, company_id);
            response.put("WeavingProductionWastageRecords", wastageModels);
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
