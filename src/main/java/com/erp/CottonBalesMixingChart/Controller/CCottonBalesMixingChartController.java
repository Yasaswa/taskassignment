package com.erp.CottonBalesMixingChart.Controller;


import com.erp.CottonBalesMixingChart.Model.CCottonBalesMixingChartModel;
import com.erp.CottonBalesMixingChart.Model.CMixingChartStandardValuesModel;
import com.erp.CottonBalesMixingChart.Respository.ICottonBalesMixingChartRepository;
import com.erp.CottonBalesMixingChart.Respository.ICottonBalesStandardValuesRepository;
import com.erp.SmMaterialStockManagement.Model.CSmMaterialStockLogModel;
import com.erp.SmMaterialStockManagement.Model.CSmProductMaterialStockNewModel;
import com.erp.SmMaterialStockManagement.Service.CSmMaterialStockManagementServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/CottonBalesMixingChart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCottonBalesMixingChartController {

    @Autowired
    ICottonBalesMixingChartRepository iCottonBalesMixingChartRepository;

    @Autowired
    ICottonBalesStandardValuesRepository iCottonBalesStandardValuesRepository;

    @Autowired
    CSmMaterialStockManagementServiceImpl cSmMaterialStockManagementService;

    @GetMapping("/FnGetStockDataAgainstLotNo/{lot_no}/{company_id}")
    public ResponseEntity<Map<String, Object>> FnGetStockDataAgainstLotNo(
            @PathVariable String lot_no, @PathVariable int company_id) {

        Map<String, Object> response = new HashMap<>();

        try {
            List<Map<String, Object>> qualityTestingDetails = iCottonBalesMixingChartRepository.FnGetStockDataAgainstLot(lot_no, company_id);

            response.put("QualityTestingData", qualityTestingDetails);
            response.put("success", 1);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/FnGetPrevMixingChartDetails/{company_id}/{plant_id}/{mixing_chart_date}")
    public Map<String, Object> FnGetPrevMixingChartDetails(@PathVariable Integer company_id, @PathVariable Integer plant_id, @PathVariable String mixing_chart_date, @RequestParam String mixing_chart_no) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> qualityTestingDetails = new ArrayList<>();
        List<String> lastsavedLotNos = new ArrayList<>();

        if (mixing_chart_no.equals("")) {
            mixing_chart_no = iCottonBalesMixingChartRepository.FnGetLatestMixingChartNo(company_id, plant_id, mixing_chart_date);
            lastsavedLotNos = iCottonBalesMixingChartRepository.FnGetLatestsavedLotNos(company_id, mixing_chart_no);
            qualityTestingDetails = iCottonBalesMixingChartRepository.FnGetStockDataAgainstLotNosForAdd(lastsavedLotNos, company_id);
        } else {
            lastsavedLotNos = iCottonBalesMixingChartRepository.FnGetLotNosByMixingChart(company_id, mixing_chart_no);
            qualityTestingDetails = iCottonBalesMixingChartRepository.FnGetStockDataAgainstLotNosForUpdate(lastsavedLotNos, company_id, mixing_chart_no);
        }

        CMixingChartStandardValuesModel cMixingChartStandardValuesModel =
                iCottonBalesStandardValuesRepository.FnGetMixingChartStandardValues(company_id, mixing_chart_no);

        response.put("QualityTestingData", qualityTestingDetails);
        response.put("MixingChartStandardValues", cMixingChartStandardValuesModel);

        return response;
    }

    @GetMapping("/FnGetLastMixingCartNo/{plant_id}/{company_id}/{month}/{year}")
    public Integer FnGetLastMixingCartNo(@PathVariable int plant_id, @PathVariable int company_id, @PathVariable int month, @PathVariable int year) {
        Integer lastMixingNo = iCottonBalesMixingChartRepository.FnGetNextMixingCartNo(plant_id, company_id, month, year);
        return lastMixingNo;
    }


    @PostMapping("/FnAddUpdateRecord")
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("MixingChartData") JSONObject jsonObject) {

        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JSONArray mixingChartDetails = jsonObject.getJSONArray("MixingChartDetails");
            JSONObject mixingChartStandardDetails = jsonObject.getJSONObject("MixingChartStandardValues");
            List<CCottonBalesMixingChartModel> cPtGrnCottonBalesDetailsModels = objectMapper.readValue(mixingChartDetails.toString(), new TypeReference<List<CCottonBalesMixingChartModel>>() {
            });

            List<Integer> balesMixingChartIds = cPtGrnCottonBalesDetailsModels.stream().map(bales -> bales.getMixing_chart_cotton_bales_transaction_id()).collect(Collectors.toList());

            if(balesMixingChartIds.size() > 0){
                iCottonBalesMixingChartRepository.FnGetUpdateDeletedRows(cPtGrnCottonBalesDetailsModels.get(0).getMixing_chart_no() ,balesMixingChartIds, cPtGrnCottonBalesDetailsModels.get(0).getModified_by());
            }

            cPtGrnCottonBalesDetailsModels = iCottonBalesMixingChartRepository.saveAll(cPtGrnCottonBalesDetailsModels);

            CMixingChartStandardValuesModel cMixingChartStandardValuesModel = objectMapper.readValue(mixingChartStandardDetails.toString(), new TypeReference<CMixingChartStandardValuesModel>() {
            });
            iCottonBalesStandardValuesRepository.save(cMixingChartStandardValuesModel);
            response.put("success", 1);
            response.put("data", mixingChartDetails);
            response.put("error", "");
            response.put("message", "Records Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            response.put("message", "Error occurred.");
        }
        return response;
    }

    @GetMapping("/FnShowParticularRecordForUpdate/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id,
                                                               @RequestParam String mixing_chart_no) {
        Map<String, Object> responce = new HashMap<>();

        try {
            List<CCottonBalesMixingChartModel> cCottonBalesMixingChartModels = iCottonBalesMixingChartRepository.FnFetchData(mixing_chart_no, company_id);
            CMixingChartStandardValuesModel cMixingChartStandardValuesModel =
                    iCottonBalesStandardValuesRepository.FnGetMixingChartStandardValues(company_id, mixing_chart_no);

            responce.put("success", "1");
            responce.put("data", cCottonBalesMixingChartModels);
            responce.put("MixingChartStandardValues", cMixingChartStandardValuesModel);
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

    @DeleteMapping("/FnDeleteRecord/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable String deleted_by , @RequestParam String mixing_chart_no) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iCottonBalesMixingChartRepository.FnDeleteRecord(mixing_chart_no, deleted_by);
            responce.put("success", 1);
        } catch (Exception e) {
            responce.put("success", 0);
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }


    @PostMapping("/FnIssueAllMixingChart/{company_id}/{UserName}")
    @Transactional
    public Map<String, Object> FnIssueAllMixingChart(@PathVariable int company_id, @PathVariable String UserName) {
        Map<String, Object> responce = new HashMap<>();

        try {
            List<String> lot_nos = iCottonBalesMixingChartRepository.FnGetLotNos(company_id);
            // Use Timestamp instead of Date for better DB compatibility
            Timestamp currentDate = Timestamp.from(Instant.now());

            // Check if lot_nos is empty
            if (lot_nos.isEmpty()) {
                responce.put("success", 0);
                responce.put("error", "No lots available for issue.");
                return responce;
            }

            // Stock New Models
            List<CSmProductMaterialStockNewModel> stockNewModels = iCottonBalesMixingChartRepository.FnGetStockDetailsAgainstLotNos(lot_nos, company_id);

            // Get Total issued qty, total issued weight & Batch no for Stock New tableFor Bales To update closing balance & weight
            List<Map<String, Object>> stockDataAgainstLotNo = iCottonBalesMixingChartRepository.FnGetDataFromMixingChart(lot_nos, company_id);

            // Updating stock quantities
            stockNewModels = stockNewModels.stream()
                    .map(stockData -> {
                        Optional<Map<String, Object>> reqModel = stockDataAgainstLotNo.stream()
                                .filter(lotNo -> Objects.equals(lotNo.get("batch_no"), stockData.getBatch_no()))
                                .findFirst();

                        reqModel.ifPresent(model -> {
                            double issueQuantity = model.get("issue_quantity") != null ? ((Number) model.get("issue_quantity")).doubleValue() : 0.0;
                            double issueWeight = model.get("issue_weight") != null ? ((Number) model.get("issue_weight")).doubleValue() : 0.0;

                            stockData.setProduction_issue_quantity(stockData.getProduction_issue_quantity() + issueQuantity);
                            stockData.setProduction_issue_weight(stockData.getProduction_issue_weight() + issueWeight);
                            stockData.setClosing_balance_quantity(stockData.getClosing_balance_quantity() - issueQuantity);
                            stockData.setClosing_balance_weight(stockData.getClosing_balance_weight() - issueWeight);
                            stockData.setModified_by(UserName);
                            stockData.setModified_on(currentDate);
                        });

                        return stockData;
                    })
                    .collect(Collectors.toList());

            //Get Individual Records for Mixing Chart Against the lot no which are not issued yet.
            List<CCottonBalesMixingChartModel> balesMixingChartModels = iCottonBalesMixingChartRepository.FnGetMixingChartNosForStockLog(lot_nos, company_id);

            // Get Previously saved Stock Log Models Against the lot no's and returing log models with distinct batch no's (No duplication)
            List<CSmMaterialStockLogModel> stockLogModels = iCottonBalesMixingChartRepository
                    .FnGetStockLogDetailsAgainstLotNos(lot_nos, company_id)
                    .stream()
                    .collect(Collectors.toMap(
                            CSmMaterialStockLogModel::getBatch_no, // Use batch_no as key
                            Function.identity(), // Use the model itself as the value
                            (existing, replacement) -> existing // If duplicate batch_no found, keep the first one
                    ))
                    .values().stream().collect(Collectors.toList());


            // Updating stock log data
            List<CSmMaterialStockLogModel> updatedStockLogModels = balesMixingChartModels.stream().map(baleschart -> {
                CSmMaterialStockLogModel reqModel = stockLogModels.stream()
                        .filter(lotNo -> Objects.equals(lotNo.getBatch_no(), baleschart.getBatch_no()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Matching batch_no not found for " + baleschart.getBatch_no()));

                CSmMaterialStockLogModel clonedData = new CSmMaterialStockLogModel(reqModel);

                clonedData.setTransaction_no(baleschart.getMixing_chart_no());
                try {
                    clonedData.setTransaction_date(new SimpleDateFormat("yyyy-MM-dd").parse(baleschart.getMixing_chart_date()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                clonedData.setTransaction_type("Issue");
                clonedData.setStock_log_transaction_id(0);
                clonedData.setQuantity(baleschart.getIssue_quantity());
                clonedData.setWeight(baleschart.getIssue_weight());
                clonedData.setCreated_by(UserName);
                clonedData.setCreated_on(currentDate);

                return clonedData;
            }).collect(Collectors.toList());

            // Save updated stock and log models
            cSmMaterialStockManagementService.FnIssueAllMixingBales(stockNewModels, updatedStockLogModels);
            iCottonBalesMixingChartRepository.FnUpdateIssueFlag(lot_nos ,company_id);

            responce.put("success", 1);
        } catch (Exception e) {
            responce.put("success", 0);
            responce.put("error", e.getMessage());
        }

        return responce;
    }


}
