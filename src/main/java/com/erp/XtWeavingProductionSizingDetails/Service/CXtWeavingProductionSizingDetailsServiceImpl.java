package com.erp.XtWeavingProductionSizingDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Properties.Model.CPropertiesViewModel;
import com.erp.Common.Properties.Repository.IPropertiesViewRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryRepository;
import com.erp.XtBeamInwards.Repository.IXtBeamInwardsRepository;
import com.erp.XtWeavingProductionOrderMaster.Model.CXtWeavingProductionDetailsModel;
import com.erp.XtWeavingProductionOrderMaster.Repository.IXtWeavingProductionDetailsRepository;
import com.erp.XtWeavingProductionSizingDetails.Model.*;
import com.erp.XtWeavingProductionSizingDetails.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.AtomicDouble;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CXtWeavingProductionSizingDetailsServiceImpl implements IXtWeavingProductionSizingDetailsService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IXtWeavingProductionSizingDetailsRepository iXtWeavingProductionSizingDetailsRepository;

    @Autowired
    IXtWeavingProductionSizingDetailsViewRepository iXtWeavingProductionSizingDetailsViewRepository;

    @Autowired
    IXtWeavingProductionSizingMasterRepository iXtWeavingProductionSizingMasterRepository;

    @Autowired
    IXtWeavingProductionSizingStoppageRepository iXtWeavingProductionSizingStoppageRepository;

    @Autowired
    IXtWeavingProductionSizingStoppageViewRepository iXtWeavingProductionSizingStoppageViewRepository;

    @Autowired
    IXtWeavingProductionSizingMaterialRepository iXtWeavingProductionSizingMaterialRepository;

    @Autowired
    IXtWeavingProductionSizingMaterialViewRepository iXtWeavingProductionSizingMaterialViewRepository;

    @Autowired
    IXtWeavingProductionSizingWastageRepository iXtWeavingProductionSizingWastageRepository;

    @Autowired
    IXtWeavingProductionSizingWastageViewRepository iXtWeavingProductionSizingWastageViewRepository;

    @Autowired
    IPropertiesViewRepository iPropertiesViewRepository;

    @Autowired
    IXtWeavingProductionDetailsRepository iXtWeavingProductionDetailsRepository;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;


    @Autowired
    IXtSizingProductionStockDetailsRepository iXtSizingProductionStockDetailsRepository;

    @Autowired
    IXtBeamInwardsRepository iXtBeamInwardsRepository;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // get CommonId's
        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int weaving_production_sizing_master_id = commonIdsObj.getInt("weaving_production_sizing_master_id");

        // get master data
        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray stoppageArray = (JSONArray) jsonObject.get("TransSZStoppageData");
        JSONArray sizingMaterialArray = (JSONArray) jsonObject.get("TransSZMaterialData");
        JSONArray wastageArray = (JSONArray) jsonObject.get("TransSZWastageData");

        JSONArray stockDetailsArray = (JSONArray) jsonObject.get("TransStockDetailData");
        String modified_by = masterjson.getString("created_by");

        try {
            List<CXtWeavingProductionSizingDetailsModel> respWeavingProductionSizingDetailsModel = null;
            List<CxtSizingProductionStockDetailModel> respsizingProductionStockDetails = null;


            CXtWeavingProductionSizingMasterModel cXtWeavingProductionSizingMasterModel = objectMapper
                    .readValue(masterjson.toString(), CXtWeavingProductionSizingMasterModel.class);

            // Save cXtWeavingProductionSizingMasterModel
            CXtWeavingProductionSizingMasterModel respcWVProductionSizingMasterModel =
                    iXtWeavingProductionSizingMasterRepository.save(cXtWeavingProductionSizingMasterModel);

            String customer_order_no = respcWVProductionSizingMasterModel.getCustomer_order_no();

            if (!detailsArray.isEmpty()) {

                //update sizing details
//                iXtWeavingProductionSizingDetailsRepository.updateWeavingProductionSizingDetails(respcWVProductionSizingMasterModel.getWeaving_production_sizing_master_id(), company_id);

                // JSON array into a list of CXtWeavingProductionSizingDetailsModel objects using an ObjectMapper
                List<CXtWeavingProductionSizingDetailsModel> cXtWeavingProductionSizingDetailsModel =
                        objectMapper.readValue(detailsArray.toString(), new TypeReference<List<CXtWeavingProductionSizingDetailsModel>>() {
                        });

                List<String> beamNos = cXtWeavingProductionSizingDetailsModel.stream()
                        .map(CXtWeavingProductionSizingDetailsModel::getSizing_beam_no)
                        .collect(Collectors.toList());


                cXtWeavingProductionSizingDetailsModel.forEach(wvSZdetails -> {

                    wvSZdetails.setWeaving_production_sizing_master_id(respcWVProductionSizingMasterModel.getWeaving_production_sizing_master_id());
                    wvSZdetails.setSizing_production_code(respcWVProductionSizingMasterModel.getSizing_production_code());

                });

                iXtBeamInwardsRepository.updateCustOrderNo(customer_order_no, beamNos, company_id);
                //Saved cXtWeavingProductionSizingDetailsModel
                respWeavingProductionSizingDetailsModel = iXtWeavingProductionSizingDetailsRepository.saveAll(cXtWeavingProductionSizingDetailsModel);

                // Extract all sizing_beam_no values for Update beam status in inward table
                List<Integer> sizingBeamIds = IntStream.range(0, detailsArray.length())
                        .mapToObj(detailsArray::getJSONObject)
                        .filter(detail -> detail.has("sizing_beam_no"))
                        .map(detail -> Integer.parseInt(detail.getString("sizing_beam_no"))) // Convert to Integer
                        .collect(Collectors.toList());

                iXtWeavingProductionSizingDetailsRepository.FnUpdateBeamInwordStatusRecord(sizingBeamIds, modified_by);

                // add entry in sized beam stock
                if (!stockDetailsArray.isEmpty()) {

                    //update sizing details
//                    iXtSizingProductionStockDetailsRepository.updateSizingProductionStockDetails(respcWVProductionSizingMasterModel.getWeaving_production_sizing_master_id(), company_id);
//
//					// JSON array into a list of CXtWeavingProductionSizingDetailsModel objects using an ObjectMapper
                    List<CxtSizingProductionStockDetailModel> cxtSizingProductionStockDetailModels =
                            objectMapper.readValue(stockDetailsArray.toString(), new TypeReference<List<CxtSizingProductionStockDetailModel>>() {
                            });

                    cxtSizingProductionStockDetailModels.forEach(stock -> {

                        stock.setWeaving_production_sizing_master_id(respcWVProductionSizingMasterModel.getWeaving_production_sizing_master_id());
                        stock.setSizing_production_code(respcWVProductionSizingMasterModel.getSizing_production_code());
                    });
                    Map<String, Integer> idMapping = respWeavingProductionSizingDetailsModel.stream()
                            .collect(Collectors.toMap(
                                    CXtWeavingProductionSizingDetailsModel::getSizing_beam_no, // Replace with actual property
                                    CXtWeavingProductionSizingDetailsModel::getWeaving_production_sizing_details_id // Replace with actual ID getter
                            ));

//                      Assign IDs based on matching keys
                    cxtSizingProductionStockDetailModels.forEach(stockDetail -> {
                        Integer id = idMapping.get(stockDetail.getBeam_no()); // Replace with actual key getter
                        if (id != null) {
                            stockDetail.setWeaving_production_sizing_details_id(id);
                        }
                    });
                    respsizingProductionStockDetails = iXtSizingProductionStockDetailsRepository.saveAll(cxtSizingProductionStockDetailModels);


                }

                // Extract distinct weaving production set numbers from respWeavingProductionSizingDetailsModel
                List<String> weavingProductionSetNumbers = respWeavingProductionSizingDetailsModel.stream()
                        .map(CXtWeavingProductionSizingDetailsModel::getWeaving_production_set_no)
                        .distinct() // Make sure to distinct the set numbers
                        .collect(Collectors.toList());

                // Retrieve all weaving production sizing details and production details for the extracted set numbers
                List<CXtWeavingProductionSizingDetailsModel> detailsList = iXtWeavingProductionSizingDetailsRepository.getAllWeavingProductionSizingDetails(weavingProductionSetNumbers);
                List<CXtWeavingProductionDetailsModel> productionDetailsList = iXtWeavingProductionDetailsRepository.getAllProductionDetails(weavingProductionSetNumbers);

                // Filter set numbers that exist in both weaving production sizing details and production details
                List<String> matchingSetNumbers = weavingProductionSetNumbers.stream()
                        .filter(set_no ->
                                productionDetailsList.stream().anyMatch(prodItem -> prodItem.getSet_no().equals(set_no))
                        )
                        .collect(Collectors.toList());

                System.out.println("matchingSetNumbers: " + matchingSetNumbers.toString());

                if (respcWVProductionSizingMasterModel.getSizing_production_master_status().equals("A")) {
                    // Iterate over the matching set numbers
                    matchingSetNumbers.forEach(set_no -> {

                        // Filter weaving production sizing details by set number
                        List<CXtWeavingProductionSizingDetailsModel> filterDetailBySetNo = detailsList.parallelStream()
                                .filter(item -> item.getWeaving_production_set_no().equals(set_no))
                                .filter(item -> item.getSizing_production_status().equals("A"))
                                .collect(Collectors.toList());


                        // Filter production details by set number
                        List<CXtWeavingProductionDetailsModel> filterProductionDetailBySetNo = productionDetailsList.parallelStream()
                                .filter(item -> item.getSet_no().equals(set_no)).collect(Collectors.toList());

                        System.out.println("set_no: " + set_no);

                        // Calculate total sizing length
                        double total_sizing_length = filterDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionSizingDetailsModel::getSizing_length)
                                .sum();

                        // Calculate total existing sizing length
                        double existing_sizing_length = filterProductionDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionDetailsModel::getSizing_length).sum();

                        // Calculate total net weight
                        double totalNet_weight = filterDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionSizingDetailsModel::getNet_weight)
                                .sum();

                        // Calculate total existing net weight
                        double existing_net_weight = filterProductionDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionDetailsModel::getSizing_net_weight).sum();

                        // Calculate total creel waste
                        double totalCreel_waste = filterDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionSizingDetailsModel::getCreel_waste)
                                .sum();

                        // Calculate total existing creel waste
                        double existing_creel_waste = filterProductionDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionDetailsModel::getCreel_waste).sum();

                        // Calculate total size waste
                        double totalSize_waste = filterDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionSizingDetailsModel::getSize_waste)
                                .sum();

                        // Calculate total existing size waste
                        double existing_size_waste = filterProductionDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionDetailsModel::getSize_waste).sum();

                        // Calculate total unsize waste
                        double totalUnsize_waste = filterDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionSizingDetailsModel::getUnsize_waste)
                                .sum();

                        // Calculate total existing unsize waste
                        double existing_unsize_waste = filterProductionDetailBySetNo.stream()
                                .mapToDouble(CXtWeavingProductionDetailsModel::getUnsize_waste).sum();

                        // Calculate total length, weight, creel waste, size waste, and unsize waste for the set
                        double totalLengthForSet = total_sizing_length + existing_sizing_length;
                        double totalWeightForSet = totalNet_weight + existing_net_weight;
                        double totalCreelWasteForSet = totalCreel_waste + existing_creel_waste;
                        double totalSizeWasteForSet = totalSize_waste + existing_size_waste;
                        double totalUnsizeWasteForSet = totalUnsize_waste + existing_unsize_waste;

                        System.out.println("totalLengthForSet: " + totalLengthForSet);

                        // Update weaving production sizing order details with the calculated totals
                        iXtWeavingProductionDetailsRepository.updateWeavingProductionSizingOrderDetails(totalLengthForSet, totalWeightForSet, totalCreelWasteForSet, totalSizeWasteForSet, totalUnsizeWasteForSet, set_no);
                    });

                }

            }

            if (!stoppageArray.isEmpty()) {

                // JSON array into a list of CXtWeavingProductionSizingStoppageModel objects using an ObjectMapper
                List<CXtWeavingProductionSizingStoppageModel> cXtWeavingProductionSizingStoppageModel =
                        objectMapper.readValue(stoppageArray.toString(), new TypeReference<List<CXtWeavingProductionSizingStoppageModel>>() {
                        });

                cXtWeavingProductionSizingStoppageModel.forEach(stoppagedetails -> {
                    stoppagedetails.setWeaving_production_sizing_master_id(respcWVProductionSizingMasterModel.getWeaving_production_sizing_master_id());
                    stoppagedetails.setSizing_production_code(respcWVProductionSizingMasterModel.getSizing_production_code());

                });

                // update Weaving Production Sizing Stoppage Details
                if (weaving_production_sizing_master_id != 0) {

                    //Extract distinct stoppage IDs
                    List<Integer> distinctwvProductionSizingStoppageIds = cXtWeavingProductionSizingStoppageModel.parallelStream()
                            .map(CXtWeavingProductionSizingStoppageModel::getWeaving_production_sizing_stoppage_id).distinct()
                            .collect(Collectors.toList());

                    System.out.println("distinctwvProductionSizingStoppageIds: " + distinctwvProductionSizingStoppageIds);
                    System.out.println("Sizing_production_code : " + respcWVProductionSizingMasterModel.getSizing_production_code());

                    iXtWeavingProductionSizingStoppageRepository.updateWeavingProductionSizingStoppageDetails(distinctwvProductionSizingStoppageIds, respcWVProductionSizingMasterModel.getSizing_production_code(), company_id);

                }
                // Saved cXtWeavingProductionSizingStoppageModel
                iXtWeavingProductionSizingStoppageRepository.saveAll(cXtWeavingProductionSizingStoppageModel);

            } else {
                iXtWeavingProductionSizingStoppageRepository.updateWeavingProductionAllSizingStoppageDetails(respcWVProductionSizingMasterModel.getSizing_production_code(),
                        company_id);
            }


            // Process sizing materials
            if (!sizingMaterialArray.isEmpty()) {

                // JSON array into a list of CXtWeavingProductionSizingMaterialModel objects using an ObjectMapper
                List<CXtWeavingProductionSizingMaterialModel> cXtWeavingProductionSizingMaterialModel = objectMapper.readValue(
                        sizingMaterialArray.toString(), new TypeReference<List<CXtWeavingProductionSizingMaterialModel>>() {
                        });

//				Stock comsumption
                FnAddUpdateStock(cXtWeavingProductionSizingMaterialModel, respcWVProductionSizingMasterModel, company_id);

                // Set common attributes for all sizing materials
                cXtWeavingProductionSizingMaterialModel.forEach(sizingMaterials -> {
                    sizingMaterials.setWeaving_production_sizing_master_id(
                            respcWVProductionSizingMasterModel.getWeaving_production_sizing_master_id());
                    sizingMaterials.setSizing_production_code(respcWVProductionSizingMasterModel.getSizing_production_code());
                });

                // Update Material Movement
                if (weaving_production_sizing_master_id != 0) {
                    List<Integer> distinctwvProductionSizingMaterialIds = cXtWeavingProductionSizingMaterialModel
                            .parallelStream()
                            .map(CXtWeavingProductionSizingMaterialModel::getWeaving_production_sizing_material_id).distinct()
                            .collect(Collectors.toList());

                    iXtWeavingProductionSizingMaterialRepository.updateWeavingProductionSizingMaterial(distinctwvProductionSizingMaterialIds, respcWVProductionSizingMasterModel.getSizing_production_code());

                }

                // Save sizing materials
                iXtWeavingProductionSizingMaterialRepository.saveAll(cXtWeavingProductionSizingMaterialModel);
            }

            if (!wastageArray.isEmpty()) {

                // JSON array into a list of CXtWeavingProductionSizingWastageModel objects using an ObjectMapper
                List<CXtWeavingProductionSizingWastageModel> cXtWeavingProductionSizingWastageModel = objectMapper
                        .readValue(wastageArray.toString(), new TypeReference<List<CXtWeavingProductionSizingWastageModel>>() {
                        });

                // Set common attributes for all stoppage details
                cXtWeavingProductionSizingWastageModel.forEach(wastageDetails -> {
                    wastageDetails.setWeaving_production_sizing_master_id(
                            respcWVProductionSizingMasterModel.getWeaving_production_sizing_master_id());
                    wastageDetails.setSizing_production_code(respcWVProductionSizingMasterModel.getSizing_production_code());
                });

                // Update Stoppage Details
                if (weaving_production_sizing_master_id != 0) {

                    //Extract distinct wastage IDs
                    List<Integer> distinctwvProductionSZWastageIds = cXtWeavingProductionSizingWastageModel.parallelStream()
                            .map(CXtWeavingProductionSizingWastageModel::getWeaving_production_sizing_wastage_id).distinct()
                            .collect(Collectors.toList());

                    iXtWeavingProductionSizingWastageRepository.updateWeavingProductionSZWastageDetails(distinctwvProductionSZWastageIds, respcWVProductionSizingMasterModel.getSizing_production_code());

                }
                //saved cXtWeavingProductionSizingWastageModel
                iXtWeavingProductionSizingWastageRepository.saveAll(cXtWeavingProductionSizingWastageModel);

            } else {
                iXtWeavingProductionSizingWastageRepository.updateWeavingProductionAllSZWastageDetails(respcWVProductionSizingMasterModel.getSizing_production_code());
            }

            responce.put("data", respcWVProductionSizingMasterModel);
            responce.put("success", 1);
            responce.put("error", "");
            responce.put("message", respcWVProductionSizingMasterModel.getSizing_production_master_status().equals("A") ? "Record Approved successfully!..." :
                    (respcWVProductionSizingMasterModel.getSizing_production_master_status().equals("R") ? "Record Rejected successfully!.." :
                            (weaving_production_sizing_master_id == 0 ? "Record added successfully!..." : "Record updated successfully!...")));

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/XtWeavingProductionSizingDetails/FnAddUpdateRecord", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (
                Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWeavingProductionSizingDetails/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;
    }

    private void FnAddUpdateStock(List<CXtWeavingProductionSizingMaterialModel> cXtWeavingProductionSizingMaterialModel,
                                  CXtWeavingProductionSizingMasterModel respcWVProductionSizingMasterModel, int company_id) {
        List<CSmProductRmStockSummaryModel> updatedSummaryStock = new ArrayList<>();
        List<CSmProductRmStockDetailsModel> updatedDetailsStock = new ArrayList<>();
        List<CSmProductStockTracking> updatedStockTracking = new ArrayList<>();
        Map<String, Object> stockDetails = new HashMap<>();

        List<String> distinctMaterialIds = cXtWeavingProductionSizingMaterialModel.stream()
                .map(CXtWeavingProductionSizingMaterialModel::getProduct_material_id)
                .distinct().collect(Collectors.toList());

        List<CSmProductRmStockDetailsModel> getDetailsRecords = distinctMaterialIds != null ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockDetails(distinctMaterialIds, company_id) : null;
        List<CSmProductRmStockSummaryModel> getSummaryRecords = distinctMaterialIds != null ? iSmProductRmStockSummaryRepository.FnGetAllProductRmStockSummary(distinctMaterialIds, company_id) : null;

        AtomicReference<List<CSmProductRmStockDetailsModel>> productRmStockDetailsList = new AtomicReference<>();
        AtomicReference<List<CSmProductRmStockSummaryModel>> productRmStockSummaryList = new AtomicReference<>();

        if (getDetailsRecords != null) {
            productRmStockDetailsList.set(copyDetailsList(getDetailsRecords));
        }

        if (getSummaryRecords != null) {
            productRmStockSummaryList.set(copySummaryList(getSummaryRecords));
        }


//		Create Group of Sizing details material wise
        Map<String, List<CXtWeavingProductionSizingMaterialModel>> wpDetailsMaterialWiseGroup = cXtWeavingProductionSizingMaterialModel.stream()
                .collect(Collectors.groupingBy(CXtWeavingProductionSizingMaterialModel::getProduct_material_id));

        AtomicReference<Double> reducedQtyRef = new AtomicReference<>(null);

//		Iterate on Sizing details material groups
        for (Map.Entry<String, List<CXtWeavingProductionSizingMaterialModel>> group : wpDetailsMaterialWiseGroup.entrySet()) {
            String product_material_id = group.getKey();
            List<CXtWeavingProductionSizingMaterialModel> wpMaterialMovementDetailsList = group.getValue();

            // Set common attributes for all Sizing materials
            wpMaterialMovementDetailsList.forEach(szMaterials -> {
                // Set common attributes for all Sizing materials
                szMaterials.setWeaving_production_sizing_master_id(respcWVProductionSizingMasterModel.getWeaving_production_sizing_master_id());
                szMaterials.setSizing_production_code(respcWVProductionSizingMasterModel.getSizing_production_code());

                List<Object> consumptionQtyInfoList = szMaterials.getConsumptionQtyInfo();

                if (consumptionQtyInfoList != null) {
                    for (Object item : consumptionQtyInfoList) {
                        Map<String, Object> consumptionInfo = (Map<String, Object>) item;

                        Integer godown_id = (Integer) consumptionInfo.get("godown_id");
                        Integer godown_section_id = (Integer) consumptionInfo.get("godown_section_id");
                        Integer godown_section_beans_id = (Integer) consumptionInfo.get("godown_section_beans_id");

                        Object consumptionQuantityObj = consumptionInfo.get("consumption_quantity");
                        AtomicReference<Double> godownWiseConsumptionQty = new AtomicReference<>();

                        if (consumptionQuantityObj instanceof String) {
                            godownWiseConsumptionQty.set(Double.parseDouble((String) consumptionQuantityObj));
                        } else if (consumptionQuantityObj instanceof Integer) {
                            godownWiseConsumptionQty.set((double) consumptionQuantityObj);
                        }

                        if (godownWiseConsumptionQty.get() > 0) {
                            CSmProductRmStockSummaryModel cSmProductRmStockSummaryModel = new CSmProductRmStockSummaryModel();

//							If in updatedSummaryStock stock is available we need to update existing object
                            Optional<CSmProductRmStockSummaryModel> matchingObjectStockSummary = updatedSummaryStock.stream()
                                    .filter(summaryModel -> summaryModel.getProduct_rm_id().equals(product_material_id) &&
                                            Objects.equals(summaryModel.getGodown_id(), godown_id) &&
                                            Objects.equals(summaryModel.getGodown_section_id(), godown_section_id) &&
                                            Objects.equals(summaryModel.getGodown_section_beans_id(), godown_section_beans_id)
                                    ).findFirst();


                            if (matchingObjectStockSummary.isPresent()) {
                                cSmProductRmStockSummaryModel = matchingObjectStockSummary.get();
                                cSmProductRmStockSummaryModel.setClosing_balance_quantity(cSmProductRmStockSummaryModel.getClosing_balance_quantity() + (-godownWiseConsumptionQty.get()));
                                updatedSummaryStock.set(updatedSummaryStock.indexOf(cSmProductRmStockSummaryModel), cSmProductRmStockSummaryModel);
                            } else {
                                cSmProductRmStockSummaryModel.setCompany_id(szMaterials.getCompany_id());
                                cSmProductRmStockSummaryModel.setCompany_branch_id(szMaterials.getCompany_branch_id());
                                cSmProductRmStockSummaryModel.setFinancial_year(szMaterials.getFinancial_year());
                                cSmProductRmStockSummaryModel.setProduct_rm_id(product_material_id);
                                cSmProductRmStockSummaryModel.setGodown_id(godown_id);
                                cSmProductRmStockSummaryModel.setGodown_section_id(godown_section_id);
                                cSmProductRmStockSummaryModel.setGodown_section_beans_id(godown_section_beans_id);
                                cSmProductRmStockSummaryModel.setClosing_balance_quantity(-godownWiseConsumptionQty.get());

                                updatedSummaryStock.add(cSmProductRmStockSummaryModel); // Add into summary stock list
                            }


//									Update summary stock closing balance qty
                            Optional<CSmProductRmStockSummaryModel> findSummaryObj = productRmStockSummaryList.get().parallelStream().filter(summStockObj -> summStockObj.getProduct_rm_id().equals(product_material_id) &&
                                    Objects.equals(summStockObj.getGodown_id(), godown_id)
                                    && Objects.equals(summStockObj.getGodown_section_id(), godown_section_id)
                                    && Objects.equals(summStockObj.getGodown_section_beans_id(), godown_section_beans_id)).findFirst();

                            findSummaryObj.ifPresent(object -> {
                                // Modify the object here, for example:
                                object.setClosing_balance_quantity(object.getClosing_balance_quantity() - godownWiseConsumptionQty.get());
                            });


//							Get material stock from specific godown
//							Modify STock details & tracking
                            FnModifyStockDetails(productRmStockDetailsList, cSmProductRmStockSummaryModel, product_material_id, godownWiseConsumptionQty, szMaterials, respcWVProductionSizingMasterModel, updatedDetailsStock, updatedStockTracking);

                        }

                    }
                } else {
                    AtomicDouble total_consumption_quantity = new AtomicDouble(szMaterials.getConsumption_quantity());

                    productRmStockSummaryList.get().stream().filter(filterRmSummaryByMaterial -> filterRmSummaryByMaterial.getProduct_rm_id().equals(product_material_id))
                            .forEach(summObj -> {
                                double availableQty = summObj.getClosing_balance_quantity();
                                Integer godown_id = summObj.getGodown_id();
                                Integer godown_section_id = summObj.getGodown_section_id();
                                Integer godown_section_beans_id = summObj.getGodown_section_beans_id();

                                if (availableQty > 0) {
                                    reducedQtyRef.set(Math.min(total_consumption_quantity.get(), availableQty));    // get min qty from this & set it into reducedQtyRef
                                    total_consumption_quantity.addAndGet(-reducedQtyRef.get());

                                    Optional<CSmProductRmStockSummaryModel> findGdConsumedQtyObj = updatedSummaryStock.stream().filter(specificGdItem -> specificGdItem.getProduct_rm_id().equals(product_material_id) &&
                                            Objects.equals(specificGdItem.getGodown_id(), godown_id) &&
                                            Objects.equals(specificGdItem.getGodown_section_id(), godown_section_id) &&
                                            Objects.equals(specificGdItem.getGodown_section_beans_id(), godown_section_beans_id)).findFirst();

                                    CSmProductRmStockSummaryModel summaryModel = new CSmProductRmStockSummaryModel();

                                    if (findGdConsumedQtyObj.isPresent()) {
                                        summaryModel = findGdConsumedQtyObj.get();
                                        summaryModel.setClosing_balance_quantity(summaryModel.getClosing_balance_quantity() + (-reducedQtyRef.get()));
                                        updatedSummaryStock.set(updatedSummaryStock.indexOf(summaryModel), summaryModel);
                                    } else {
                                        summaryModel.setCompany_id(szMaterials.getCompany_id());
                                        summaryModel.setCompany_branch_id(szMaterials.getCompany_branch_id());
                                        summaryModel.setFinancial_year(szMaterials.getFinancial_year());
                                        summaryModel.setProduct_rm_id(product_material_id);
                                        summaryModel.setGodown_id(godown_id);
                                        summaryModel.setGodown_section_id(godown_section_id);
                                        summaryModel.setGodown_section_beans_id(godown_section_beans_id);
                                        summaryModel.setClosing_balance_quantity(-reducedQtyRef.get());

                                        updatedSummaryStock.add(summaryModel); // Add into summary stock list
                                    }


//									 Update summary stock closing balance qty
                                    Optional<CSmProductRmStockSummaryModel> findSummaryObj = productRmStockSummaryList.get().parallelStream()
                                            .filter(summStockObj -> summStockObj.getProduct_rm_id().equals(product_material_id) &&
                                                    Objects.equals(summStockObj.getGodown_id(), godown_id) &&
                                                    Objects.equals(summStockObj.getGodown_section_id(), godown_section_id) &&
                                                    Objects.equals(summStockObj.getGodown_section_beans_id(), godown_section_beans_id)).findFirst();

                                    findSummaryObj.ifPresent(object -> {
                                        object.setClosing_balance_quantity(object.getClosing_balance_quantity() - reducedQtyRef.get());
                                    });

//									Modify STock details & tracking
                                    FnModifyStockDetails(productRmStockDetailsList, summObj, product_material_id, reducedQtyRef, szMaterials, respcWVProductionSizingMasterModel, updatedDetailsStock, updatedStockTracking);


                                }
                            });
                }

            });

        }

        stockDetails.put("RmStockSummary", updatedSummaryStock);
        stockDetails.put("RmStockDetails", updatedDetailsStock);
        stockDetails.put("StockTracking", updatedStockTracking);

        cSmProductRmStockDetailsController.FnAddUpdateFGStock(stockDetails, "Increase", company_id);

    }

    private void FnModifyStockDetails(AtomicReference<List<CSmProductRmStockDetailsModel>> productRmStockDetailsList, CSmProductRmStockSummaryModel sumObj, String product_material_id, AtomicReference<Double> reducedQtyRef, CXtWeavingProductionSizingMaterialModel szMaterials,
                                      CXtWeavingProductionSizingMasterModel respcWVProductionSizingMasterModel, List<CSmProductRmStockDetailsModel> updatedDetailsStock, List<CSmProductStockTracking> updatedStockTracking) {
        Integer godown_id = sumObj.getGodown_id();
        Integer godown_section_id = sumObj.getGodown_section_id();
        Integer godown_section_beans_id = sumObj.getGodown_section_beans_id();

        AtomicDouble total_consumption_gosdown_wise = new AtomicDouble(reducedQtyRef.get());

//		Get material stock from specific godown
        List<CSmProductRmStockDetailsModel> getMaterialStockDetailsGodownWise = productRmStockDetailsList.get().stream()
                .filter(stockMaterial -> stockMaterial.getProduct_rm_id().equals(product_material_id) &&
                        Objects.equals(stockMaterial.getGodown_id(), godown_id) &&
                        Objects.equals(stockMaterial.getGodown_section_id(), godown_section_id) &&
                        Objects.equals(stockMaterial.getGodown_section_beans_id(), godown_section_beans_id))
                .collect(Collectors.toList());

        if (getMaterialStockDetailsGodownWise.size() != 0) {
            getMaterialStockDetailsGodownWise.forEach(stockDetail -> {
                CSmProductRmStockDetailsModel cSmProductRmStockDetailsModel = stockDetail.copy();

                double availableQtyStockDetail = stockDetail.getClosing_balance_quantity();
                System.out.println("availableQtyStockDetail : " + availableQtyStockDetail);

                if (total_consumption_gosdown_wise.get() > 0) {

                    reducedQtyRef.set(Math.min(reducedQtyRef.get(), availableQtyStockDetail));

                    total_consumption_gosdown_wise.getAndAdd(-reducedQtyRef.get());

//					If in updatedSummaryStock stock is available we need to update existing object
                    Optional<CSmProductRmStockDetailsModel> matchingObjectStockDetails = updatedDetailsStock.stream()
                            .filter(detailsModel -> detailsModel.getProduct_rm_id().equals(product_material_id) &&
                                    Objects.equals(detailsModel.getGodown_id(), godown_id) &&
                                    Objects.equals(detailsModel.getGodown_section_id(), godown_section_id) &&
                                    Objects.equals(detailsModel.getGodown_section_beans_id(), godown_section_beans_id)
                            ).findFirst();
                    if (matchingObjectStockDetails.isPresent()) {
                        cSmProductRmStockDetailsModel = matchingObjectStockDetails.get();
                        cSmProductRmStockDetailsModel.setClosing_balance_quantity(cSmProductRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQtyRef.get()));
                        updatedDetailsStock.set(updatedDetailsStock.indexOf(cSmProductRmStockDetailsModel), cSmProductRmStockDetailsModel);
                    } else {
                        cSmProductRmStockDetailsModel.setClosing_balance_quantity(-reducedQtyRef.get());
                        updatedDetailsStock.add(cSmProductRmStockDetailsModel);  // Add into details stock list
                    }

                    Optional<CSmProductRmStockDetailsModel> findDetailsObj = productRmStockDetailsList.get().stream()
                            .filter(existingStockDetils -> existingStockDetils.getStock_transaction_id() == stockDetail.getStock_transaction_id()).findFirst();

                    findDetailsObj.ifPresent(object -> {
                        object.setClosing_balance_quantity(object.getClosing_balance_quantity() - reducedQtyRef.get());

                    });

//					Stock tracking table consumption
                    FnAddIntoStockTracking(updatedStockTracking, szMaterials, respcWVProductionSizingMasterModel, stockDetail, reducedQtyRef);

                } else {
                    return;
                }
            });

        }
    }

    private void FnAddIntoStockTracking(List<CSmProductStockTracking> updatedStockTracking, CXtWeavingProductionSizingMaterialModel szMaterials, CXtWeavingProductionSizingMasterModel respcWVProductionSizingMasterModel, CSmProductRmStockDetailsModel stockDetail, AtomicReference<Double> reducedQtyRef) {

        CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();

        cSmProductStockTracking.setCompany_id(szMaterials.getCompany_id());
        cSmProductStockTracking.setCompany_branch_id(szMaterials.getCompany_branch_id());
        cSmProductStockTracking.setFinancial_year(szMaterials.getFinancial_year());
        cSmProductStockTracking.setProduct_material_id(szMaterials.getProduct_material_id());
        cSmProductStockTracking.setGoods_receipt_no(stockDetail.getGoods_receipt_no());
        cSmProductStockTracking.setConsumption_no(szMaterials.getSizing_production_code());
        cSmProductStockTracking.setConsumption_date(new Date());
        cSmProductStockTracking.setConsumption_location("Sizing");
        cSmProductStockTracking.setConsumption_detail_no(szMaterials.getWeaving_production_set_no() + "/" + szMaterials.getShift());
        cSmProductStockTracking.setConsumption_quantity(reducedQtyRef.get());
        cSmProductStockTracking.setCreated_by(respcWVProductionSizingMasterModel.getCreated_by());

        updatedStockTracking.add(cSmProductStockTracking);  // Add into stock tracking list
    }

    private List<CSmProductRmStockDetailsModel> copyDetailsList(List<CSmProductRmStockDetailsModel> getDetailsRecords) {
        return getDetailsRecords.stream().map(details -> {
            CSmProductRmStockDetailsModel copy = new CSmProductRmStockDetailsModel();
            try {
                BeanUtils.copyProperties(details, copy);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
            return copy;
        }).collect(Collectors.toList());
    }

    private List<CSmProductRmStockSummaryModel> copySummaryList(List<CSmProductRmStockSummaryModel> getSummaryRecords) {
        return getSummaryRecords.stream().map(summary -> {
            CSmProductRmStockSummaryModel copy = new CSmProductRmStockSummaryModel();
            try {
                BeanUtils.copyProperties(summary, copy);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception as needed
            }
            return copy;
        }).collect(Collectors.toList());
    }


    @Override
    public Map<String, Object> FnDeleteRecord(int weaving_production_sizing_master_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            // Delete Production Sizing Details Record
            iXtWeavingProductionSizingMasterRepository
                    .FnDeleteProductionSizingMasterRecord(weaving_production_sizing_master_id, deleted_by);

            // Delete Production Sizing Details Record
            iXtWeavingProductionSizingDetailsRepository
                    .FnDeleteProductionSizingDetailsRecord(weaving_production_sizing_master_id, deleted_by);

            // Delete Production Sizing Stoppage Record
            iXtWeavingProductionSizingStoppageRepository
                    .FnDeleteProductionSizingStoppageRecord(weaving_production_sizing_master_id, deleted_by);

            // Delete Production Sizing Material Record
            iXtWeavingProductionSizingMaterialRepository
                    .FnDeleteProductionSizingMaterialRecord(weaving_production_sizing_master_id, deleted_by);

            // Delete Production Sizing Wastage Record
            iXtWeavingProductionSizingWastageRepository
                    .FnDeleteProductionSizingWastageRecord(weaving_production_sizing_master_id, deleted_by);

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

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_sizing_master_id,
                                                               int company_id) {
        Map<String, Object> responce = new HashMap<>();

        try {

            // Fetch Production Sizing Master Record for update
            CXtWeavingProductionSizingMasterModel weavingProductionSizingMasterModelRecord = iXtWeavingProductionSizingMasterRepository
                    .FnShowParticularWVProductionSizingRecordForUpdate(weaving_production_sizing_master_id, company_id);

            // Fetch Production Sizing Details Record for update
            List<CXtWeavingProductionSizingDetailsViewModel> weavingProductionSizingDetailsModelRecord = iXtWeavingProductionSizingDetailsViewRepository
                    .FnShowWVProductionSizingDetailsRecordForUpdate(weaving_production_sizing_master_id, company_id);

            // Fetch Production Sizing Stoppage Record for update
            List<CXtWeavingProductionSizingStoppageViewModel> weavingProductionSizingStoppageModelRecord = iXtWeavingProductionSizingStoppageViewRepository
                    .FnShowWVProductionSizingStoppageRecordForUpdate(weaving_production_sizing_master_id, company_id);

            // Fetch Production Sizing Material Record for update
            List<CXtWeavingProductionSizingMaterialViewModel> weavingProductionSizingMaterialRecord = iXtWeavingProductionSizingMaterialViewRepository
                    .FnShowWVProductionSizingMaterialRecordForUpdate(weaving_production_sizing_master_id, company_id);

            // Fetch Production Sizing Stoppage Record for update
            List<CXtWeavingProductionSizingWastageViewModel> weavingProductionSizingWastageRecord = iXtWeavingProductionSizingWastageViewRepository
                    .FnShowWVProductionSizingStoppageRecordForUpdate(weaving_production_sizing_master_id, company_id);

            responce.put("WeavingProductionSizingMasterModelRecord", weavingProductionSizingMasterModelRecord);
            responce.put("WeavingProductionSizingDetailsRecord", weavingProductionSizingDetailsModelRecord);
            responce.put("WeavingProductionSizingStoppageRecord", weavingProductionSizingStoppageModelRecord);
            responce.put("WeavingProductionSizingMaterialRecord", weavingProductionSizingMaterialRecord);
            responce.put("WeavingProductionSizingWastageRecord", weavingProductionSizingWastageRecord);

            responce.put("success", 1);

        } catch (DataAccessException e) {
            e.printStackTrace();
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
    public Map<String, Object> FnShowParticularSizingShiftSummary(String sizing_production_date, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
//            Map<String, Map<String, Object>> shiftWiseSummary = new LinkedHashMap<>();
//
//            // Get shifts and sort them
//            // Get Production shifts from property master
//            List<CPropertiesViewModel> getShifts = iPropertiesViewRepository
//                    .FnShowParticularRecord("ProductionShifts", company_id).stream()
//                    .sorted(Comparator.comparingLong(CPropertiesViewModel::getProperty_id))
//                    .collect(Collectors.toList());
//
//            // Get today's production summary and previous date's production summary
//            List<CXtWeavingProductionSizingDetailsViewModel> productionSummarytoday = iXtWeavingProductionSizingDetailsViewRepository
//                    .FnShowParticularSizingShiftSummary(sizing_production_date, company_id);
//            List<CXtWeavingProductionSizingDetailsViewModel> productionSummaryPreviousdateDetails = iXtWeavingProductionSizingDetailsViewRepository
//                    .FnShowParticularSizingPreviousShiftSummary(sizing_production_date, company_id);
//
//            // Iterate on production shifts
//            getShifts.forEach(shifts -> {
//                Map<String, Object> shiftObject = new LinkedHashMap<>();
//                shiftObject.put("shift", shifts.getProperty_name());
//
//                // Get today's production data for the current shift
//                CXtWeavingProductionSizingDetailsViewModel shiftwisetodaydateData = productionSummarytoday.stream()
//                        .filter(item -> item.getShift().equals(shifts.getProperty_name())
//                                && item.getSizing_production_date().equals(sizing_production_date))
//                        .findFirst().orElse(null);
//
//                if (shiftwisetodaydateData != null) {
//                    shiftObject.put("shift_sizing_total_length", shiftwisetodaydateData.getSizing_total_length());
//                    shiftObject.put("shift_net_total_weight", shiftwisetodaydateData.getNet_total_weight());
//                    shiftObject.put("shift_size_total_waste", shiftwisetodaydateData.getSize_total_waste());
//                    shiftObject.put("shift_unsize_total_waste", shiftwisetodaydateData.getUnsize_total_waste());
//                    shiftObject.put("shift_creel_total_waste", shiftwisetodaydateData.getCreel_total_waste());
//                } else {
//                    shiftObject.put("shift_sizing_total_length", 0);
//                    shiftObject.put("shift_net_total_weight", 0);
//                    shiftObject.put("shift_size_total_waste", 0);
//                    shiftObject.put("shift_unsize_total_waste", 0);
//                    shiftObject.put("shift_creel_total_waste", 0);
//                }
//
//                // Get previous date's production data for the current shift
//                List<CXtWeavingProductionSizingDetailsViewModel> uptoDateSizingShiftSummaryData = productionSummaryPreviousdateDetails
//                        .stream().filter(item -> item.getShift().equals(shifts.getProperty_name()))
//                        .collect(Collectors.toList());
//
//                if (uptoDateSizingShiftSummaryData != null) {
//
//                    // Calculate sum of various values
//                    double total_length = uptoDateSizingShiftSummaryData.stream()
//                            .mapToDouble(CXtWeavingProductionSizingDetailsViewModel::getSizing_length).sum();
//                    double total_weight = uptoDateSizingShiftSummaryData.stream()
//                            .mapToDouble(CXtWeavingProductionSizingDetailsViewModel::getNet_weight).sum();
//                    double total_size_waste = uptoDateSizingShiftSummaryData.stream()
//                            .mapToDouble(CXtWeavingProductionSizingDetailsViewModel::getSize_waste).sum();
//                    double total_unsize_waste = uptoDateSizingShiftSummaryData.stream()
//                            .mapToDouble(CXtWeavingProductionSizingDetailsViewModel::getUnsize_waste).sum();
//                    double total_creel_waste = uptoDateSizingShiftSummaryData.stream()
//                            .mapToDouble(CXtWeavingProductionSizingDetailsViewModel::getCreel_waste).sum();
//
//                    // Round the totals to two decimal places
//                    total_length = Math.round(total_length * 100.0) / 100.0;
//                    total_weight = Math.round(total_weight * 100.0) / 100.0;
//                    total_size_waste = Math.round(total_size_waste * 100.0) / 100.0;
//                    total_unsize_waste = Math.round(total_unsize_waste * 100.0) / 100.0;
//                    total_creel_waste = Math.round(total_creel_waste * 100.0) / 100.0;
//
//                    // Put the rounded totals into shiftObject
//                    // here sum of sizing total length,net total weight, size total waste ,unsized
//                    // total weight and creel total waste
//                    shiftObject.put("shift_sizing_total_upto_date_length", total_length);
//                    shiftObject.put("shift_net_total_upto_date_weight", total_weight);
//                    shiftObject.put("shift_size_total_upto_date_waste", total_size_waste);
//                    shiftObject.put("shift_unsize_total_upto_date_waste", total_unsize_waste);
//                    shiftObject.put("shift_creel_total_upto_date_waste", total_creel_waste);
//
//                } else {
//                    shiftObject.put("shift_sizing_total_upto_date_length", 0);
//                    shiftObject.put("shift_net_total_upto_date_weight", 0);
//                    shiftObject.put("shift_size_total_upto_date_waste", 0);
//                    shiftObject.put("shift_unsize_total_upto_date_waste", 0);
//                    shiftObject.put("shift_creel_total_upto_date_waste", 0);
//                }
//
//                shiftWiseSummary.put(shifts.getProperty_name(), shiftObject);
//            });
//
//            response.put("success", 1);
//            response.put("data", shiftWiseSummary);
//            response.put("error", "");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/XtWeavingProductionSizingDetails/FnShowParticularShiftSummary", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWeavingProductionSizingDetails/FnShowParticularShiftSummary", 0, e.getMessage());
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());

        }
        return response;
    }

}
