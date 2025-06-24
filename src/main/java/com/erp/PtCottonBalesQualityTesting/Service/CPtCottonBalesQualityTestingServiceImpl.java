package com.erp.PtCottonBalesQualityTesting.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.CottonBalesMixingChart.Model.CCottonBalesMixingChartModel;
import com.erp.CottonBalesMixingChart.Respository.ICottonBalesMixingChartRepository;
import com.erp.PtCottonBalesQualityTesting.Model.CPtCottonBalesQualityTestingModel;
import com.erp.PtCottonBalesQualityTesting.Repository.IPtCottonBalesQualityTestingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CPtCottonBalesQualityTestingServiceImpl implements IPtCottonBalesQualityTestingService{

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IPtCottonBalesQualityTestingRepository iPtCottonBalesQualityTestingRepository;

    @Autowired
    ICottonBalesMixingChartRepository iCottonBalesMixingChartRepository;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {

        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        String keyForViewUpdate = commonIdsObj.getString("keyForViewUpdate");

        JSONArray qualityTestingDetails = (JSONArray) jsonObject.get("PtCottonBalesQualityTestingData");
        try {

            List<CPtCottonBalesQualityTestingModel> cPtCottonBalesQualityTestingModels = objectMapper.readValue(
                    qualityTestingDetails.toString(), new TypeReference<List<CPtCottonBalesQualityTestingModel>>() {
                    });

//            List<CPtCottonBalesQualityTestingModel> cPtCottonBalesQualityTestingModels = objectMapper.convertValue(
//                    qualityTestingDetails, new TypeReference<List<CPtCottonBalesQualityTestingModel>>() {});
//            // Extract batch numbers and their statuses from JSON data
            Map<String, String> batchNoStatusMap = cPtCottonBalesQualityTestingModels.stream()
                    .collect(Collectors.toMap(
                            CPtCottonBalesQualityTestingModel::getBatch_no,
                            CPtCottonBalesQualityTestingModel::getQuality_testing_status,
                            (existingStatus, newStatus) -> newStatus // If duplicate batch_no exists, keep the latest status
                    ));

            List<String> batchNos = new ArrayList<>();
            // Update batch status one by one
            for (Map.Entry<String, String> batchEntry : batchNoStatusMap.entrySet()) {
                batchNos.add(batchEntry.getKey());
                iPtCottonBalesQualityTestingRepository.updateSingleBatchStatus(
                        batchEntry.getKey(),  // batch_no
                        batchEntry.getValue(), // status
                        company_id
                );
            }

            iPtCottonBalesQualityTestingRepository.saveAll(cPtCottonBalesQualityTestingModels);

            if(keyForViewUpdate.equals("update")){
                List<CCottonBalesMixingChartModel> balesMixingChartModels = iCottonBalesMixingChartRepository.FnGetMixingChartNosForBalesGRN(batchNos, company_id);
                balesMixingChartModels.forEach(CCottonBalesMixingChartModel -> {
                    Optional<CPtCottonBalesQualityTestingModel> reqTestingModel =
                            cPtCottonBalesQualityTestingModels.stream()
                                    .filter(data -> data.getBatch_no().equals(CCottonBalesMixingChartModel.getBatch_no()))
                                    .findFirst();

                    reqTestingModel.ifPresent(model -> {
                        CCottonBalesMixingChartModel.setUhml(model.getUhml());
                        CCottonBalesMixingChartModel.setMi(model.getMi());
                        CCottonBalesMixingChartModel.setUl(model.getUl());
                        CCottonBalesMixingChartModel.setMic(model.getMic());
                        CCottonBalesMixingChartModel.setStr(model.getStr());
                        CCottonBalesMixingChartModel.setRd(model.getRd());
                        CCottonBalesMixingChartModel.setB_plus(model.getB_plus());
                        CCottonBalesMixingChartModel.setTotal_neps(model.getTotal_neps());
                        CCottonBalesMixingChartModel.setSc_n(model.getSc_n());
                        CCottonBalesMixingChartModel.setSfc_n(model.getSfc_n());
                        CCottonBalesMixingChartModel.setTrash(model.getTrash());
                        CCottonBalesMixingChartModel.setMoisture(model.getMoisture());
                        CCottonBalesMixingChartModel.setCg(model.getCg());
                        CCottonBalesMixingChartModel.setQuality_testing_status(model.getQuality_testing_status());
                        CCottonBalesMixingChartModel.setModified_by(model.getModified_by());
                        CCottonBalesMixingChartModel.setModified_on(model.getModified_on());
                    });
                });

                iCottonBalesMixingChartRepository.saveAll(balesMixingChartModels);
            }



            responce.put("success", "1");
            responce.put("error", "");
            responce.put("message", "Record added successfully!...");


        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtCottonBalesQualityTesting/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/PtCottonBalesQualityTesting/FnAddUpdateRecord",
                    0, e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;
    }
    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int company_id, String quality_testing_no) {
        Map<String, Object> responce = new HashMap<>();

        try {
            List<CPtCottonBalesQualityTestingModel> cPtCottonBalesQualityTestingModel = iPtCottonBalesQualityTestingRepository.FnShowParticularRecordForUpdate(company_id, quality_testing_no);

            responce.put("success", "1");
            responce.put("data", cPtCottonBalesQualityTestingModel);
            responce.put("error", "");
        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
                return responce;
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }
}
