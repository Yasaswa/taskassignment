package com.erp.XtWeavingProductionLoomMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryRepository;
import com.erp.XtWeavingProductionLoomMaster.Model.*;
import com.erp.XtWeavingProductionLoomMaster.Repository.*;
import com.erp.XtWeavingProductionOrderMaster.Repository.IXtWeavingProductionDetailsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.AtomicDouble;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CXtWeavingProductionLoomMasterServiceImpl implements IXtWeavingProductionLoomMasterService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IXtWeavingProductionLoomMasterRepository iXtWeavingProductionLoomMasterRepository;

    @Autowired
    IXtWeavingProductionLoomMasterViewRepository iXtWeavingProductionLoomMasterViewRepository;

    @Autowired
    IXtWeavingProductionLoomDetailsRepository iXtWeavingProductionLoomDetailsRepository;

    @Autowired
    IXtWeavingProductionLoomDetailsViewRepository iXtWeavingProductionLoomDetailsViewRepository;

    @Autowired
    IXtWeavingProductionLoomStoppageRepository iXtWeavingProductionLoomStoppageRepository;

    @Autowired
    IXtWeavingProductionLoomStoppageViewRepository iXtWeavingProductionLoomStoppageViewRepository;

    @Autowired
    IXtWeavingProductionLoomWastageRepository iXtWeavingProductionLoomWastageRepository;

    @Autowired
    IXtWeavingProductionLoomWastageViewRepository iXtWeavingProductionLoomWastageViewRepository;

    @Autowired
    IXtWeavingProductionDetailsRepository iXtWeavingProductionDetailsRepository;

    @Autowired
    IXtWeavingProductionLoomMaterialRepository iXtWeavingProductionLoomMaterialRepository;

    @Autowired
    IXtWeavingProductionLoomMaterialViewRepository iXtWeavingProductionLoomMaterialViewRepository;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;


    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int weaving_production_loom_master_id = commonIdsObj.getInt("weaving_production_loom_master_id");

        // get master data
        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray loomstoppageArray = (JSONArray) jsonObject.get("TransLoomStoppageData");
        JSONArray loomwastageArray = (JSONArray) jsonObject.get("TransLoomWastageData");
        JSONArray loomMaterialArray = (JSONArray) jsonObject.get("TransLoomMaterialData");

        try {

            List<CXtWeavingProductionLoomDetailsModel> respWVWeavingProductionLoomDetails = null;

            CXtWeavingProductionLoomMasterModel cXtWeavingProductionLoomMasterModel = objectMapper
                    .readValue(masterjson.toString(), CXtWeavingProductionLoomMasterModel.class);

            // Save cXtWeavingProductionLoomMasterModel
            CXtWeavingProductionLoomMasterModel respWeavingProductionLoomMasterModel =
                    iXtWeavingProductionLoomMasterRepository.save(cXtWeavingProductionLoomMasterModel);

            //Process Loom Details
            if (!detailsArray.isEmpty()) {

                // JSON array into a list of CXtWeavingProductionLoomDetailsModel objects using an ObjectMapper
                List<CXtWeavingProductionLoomDetailsModel> cXtWeavingProductionLoomDetailsModel =
                        objectMapper.readValue(detailsArray.toString(), new TypeReference<List<CXtWeavingProductionLoomDetailsModel>>() {
                        });

                cXtWeavingProductionLoomDetailsModel.forEach(loomdetails -> {
                    loomdetails.setWeaving_production_loom_master_id(respWeavingProductionLoomMasterModel.getWeaving_production_loom_master_id());

                });

                //Saved cXtWeavingProductionLoomDetailsModel
                respWVWeavingProductionLoomDetails = iXtWeavingProductionLoomDetailsRepository.saveAll(cXtWeavingProductionLoomDetailsModel);

//                By yogesh

//                List<String> loomIds = respWVWeavingProductionLoomDetails.parallelStream()
//                        .map(data -> String.valueOf(data.getMachine_id())) // Convert Integer to String
//                        .collect(Collectors.toList());
//
//// Update the loom number status using the extracted IDs
//                int rowsUpdated = iXtWeavingProductionLoomDetailsRepository.updateLoomNoStatus(loomIds);
//                System.out.println("Rows updated: " + rowsUpdated);
//
//					// Make sure to distinct the set numbers Loom details
//					List<String> weavingLoomProductionSetNumbers = respWVWeavingProductionLoomDetails.stream()
//			                .map(CXtWeavingProductionLoomDetailsModel::getSet_no)
//			                .distinct()
//			                .collect(Collectors.toList());
//
//				     List<CXtWeavingProductionLoomDetailsModel> detailsList = iXtWeavingProductionLoomDetailsRepository.getAllWeavingProductionLoomDetails(weavingLoomProductionSetNumbers);
//				     List<CXtWeavingProductionDetailsModel> productionDetailsList = iXtWeavingProductionDetailsRepository.getAllProductionDetails(weavingLoomProductionSetNumbers);
//
//					 // Filter set numbers that exist in both details and production details
//					 List<String> matchingSetNumbers = weavingLoomProductionSetNumbers.stream() .filter(set_no ->productionDetailsList.stream()
//							 .anyMatch(prodItem -> prodItem.getSet_no().equals(set_no))).collect(Collectors.toList());
//
//					System.out.println("matchingSetNumbers: "+ matchingSetNumbers.toString());
//
//				   if (respWeavingProductionLoomMasterModel.getLoom_production_master_status().equals("A")) {
//
//					        matchingSetNumbers.forEach(set_no-> {
//
//					        	List<CXtWeavingProductionLoomDetailsModel> filterDetailBySetNo = detailsList.parallelStream()
//					        			.filter(item -> item.getSet_no().equals(set_no)).collect(Collectors.toList());
//
//
//					        	List<CXtWeavingProductionDetailsModel> filterProductionDetailBySetNo = productionDetailsList.parallelStream()
//					        			.filter(item -> item.getSet_no().equals(set_no)).collect(Collectors.toList());
//
//					        	System.out.println("set_no: "+ set_no);
//
//					        	// Assuming you want to sum up the length
//						        double totalProdcut_1000pick = filterDetailBySetNo.stream()
//						                .mapToDouble(CXtWeavingProductionLoomDetailsModel::getProdcut_1000pick)
//						                .sum();
//
//						        double totalLoom_product_in_meter = filterDetailBySetNo.stream()
//						                .mapToDouble(CXtWeavingProductionLoomDetailsModel::getProduct_in_meter)
//						                .sum();
//
//						        double existing_prodcut_1000pick = filterProductionDetailBySetNo.stream()
//						        		.mapToDouble(CXtWeavingProductionDetailsModel::getProdcut_1000pick).sum();
//
//
//						        double existing_loom_product_in_meter = filterProductionDetailBySetNo.stream()
//						        		.mapToDouble(CXtWeavingProductionDetailsModel::getLoom_product_in_meter).sum();
//
//
//						        // Add totallength and totallength1 together
//						        double total_prodcut_1000pick = totalProdcut_1000pick + existing_prodcut_1000pick;
//						        double total_loom_product_in_meter = totalLoom_product_in_meter + existing_loom_product_in_meter;
//
//
//
//						        System.out.println("totalLengthForSet: " + total_prodcut_1000pick +"total_loom_product_in_meter: "+ total_loom_product_in_meter +"set_no :" +set_no);
//
//						        iXtWeavingProductionDetailsRepository.updateWeavingProductionLoomDetails(total_prodcut_1000pick,total_loom_product_in_meter,set_no);
//
//					        });
//
//				   }
            }

            // Process Loom materials
            if (!loomMaterialArray.isEmpty()) {
                // JSON array into a list of CXtWeavingProductionLoomMaterialModel objects using an ObjectMapper
                List<CXtWeavingProductionLoomMaterialModel> cXtWeavingProductionLoomMaterialModel =
                        objectMapper.readValue(loomMaterialArray.toString(), new TypeReference<List<CXtWeavingProductionLoomMaterialModel>>() {
                        });


//							Stock consumption
//					FnAddUpdateStock(cXtWeavingProductionLoomMaterialModel, respWeavingProductionLoomMasterModel, company_id);

                // Set common attributes for all sizing materials
                cXtWeavingProductionLoomMaterialModel.forEach(wastagedetails -> {
                    wastagedetails.setWeaving_production_loom_master_id(respWeavingProductionLoomMasterModel.getWeaving_production_loom_master_id());
                    wastagedetails.setLoom_production_code(respWeavingProductionLoomMasterModel.getLoom_production_code());

                });

                //update Weaving Production Loom Material Details
                if (weaving_production_loom_master_id != 0) {

                    List<Integer> distinctloomProductionMaterialIds = cXtWeavingProductionLoomMaterialModel.parallelStream()
                            .map(CXtWeavingProductionLoomMaterialModel::getWeaving_production_loom_material_id)
                            .distinct().collect(Collectors.toList());

                    iXtWeavingProductionLoomMaterialRepository.updateLoomProductionMaterialDetails(distinctloomProductionMaterialIds, respWeavingProductionLoomMasterModel.getLoom_production_code());

                }

                //Saved cXtWeavingProductionLoomMaterialModel
                iXtWeavingProductionLoomMaterialRepository.saveAll(cXtWeavingProductionLoomMaterialModel);
            } else {
                iXtWeavingProductionLoomMaterialRepository.updateLoomProductionAllMaterialDetails(respWeavingProductionLoomMasterModel.getLoom_production_code());
            }

            //Process Stoppage Details
            if (!loomstoppageArray.isEmpty()) {

                // JSON array into a list of CXtWeavingProductionLoomStoppageModel objects using an ObjectMapper
                List<CXtWeavingProductionLoomStoppageModel> cXtWeavingProductionLoomStoppageModel =
                        objectMapper.readValue(loomstoppageArray.toString(), new TypeReference<List<CXtWeavingProductionLoomStoppageModel>>() {
                        });

                cXtWeavingProductionLoomStoppageModel.forEach(stoppagedetails -> {
                    stoppagedetails.setWeaving_production_loom_master_id(respWeavingProductionLoomMasterModel.getWeaving_production_loom_master_id());

                });

                //update Weaving Production loom Stoppage Details
                if (weaving_production_loom_master_id != 0) {

                    //Extract distinct stoppage Id's
                    List<Integer> distinctloomProductionStoppageIds = cXtWeavingProductionLoomStoppageModel.parallelStream()
                            .map(CXtWeavingProductionLoomStoppageModel::getWeaving_production_loom_stoppage_id)
                            .distinct().collect(Collectors.toList());

                    // Execute the update query
                    iXtWeavingProductionLoomStoppageRepository.updateLoomProductionStoppageDetails(distinctloomProductionStoppageIds, respWeavingProductionLoomMasterModel.getLoom_production_code());

                }

                //Saved cXtWeavingProductionLoomStoppageModel
                iXtWeavingProductionLoomStoppageRepository.saveAll(cXtWeavingProductionLoomStoppageModel);

            } else {

                iXtWeavingProductionLoomStoppageRepository.updateALLLoomProductionStoppageDetails(respWeavingProductionLoomMasterModel.getLoom_production_code());
            }

            //Process Loom Wastage
            if (!loomwastageArray.isEmpty()) {

                // JSON array into a list of CXtWeavingProductionLoomWastageModel objects using an ObjectMapper
                List<CXtWeavingProductionLoomWastageModel> cXtWeavingProductionLoomWastageModel =
                        objectMapper.readValue(loomwastageArray.toString(), new TypeReference<List<CXtWeavingProductionLoomWastageModel>>() {
                        });

                cXtWeavingProductionLoomWastageModel.forEach(wastagedetails -> {
                    wastagedetails.setWeaving_production_loom_master_id(respWeavingProductionLoomMasterModel.getWeaving_production_loom_master_id());

                });

                //update Weaving Production Loom wastage Details
                if (weaving_production_loom_master_id != 0) {

                    //Extract distinct wastage Id's
                    List<Integer> distinctloomProductionWastageIds = cXtWeavingProductionLoomWastageModel.parallelStream()
                            .map(CXtWeavingProductionLoomWastageModel::getWeaving_production_loom_wastage_id)
                            .distinct().collect(Collectors.toList());

                    iXtWeavingProductionLoomWastageRepository.updateLoomProductionWastageDetails(distinctloomProductionWastageIds, respWeavingProductionLoomMasterModel.getLoom_production_code());


                }
                //Saved cXtWeavingProductionLoomWastageModel
                iXtWeavingProductionLoomWastageRepository.saveAll(cXtWeavingProductionLoomWastageModel);

            } else {
                iXtWeavingProductionLoomWastageRepository.updateLoomProductionAllWastageDetails(respWeavingProductionLoomMasterModel.getLoom_production_code());
            }

            responce.put("data", respWeavingProductionLoomMasterModel);
            responce.put("success", 1);
            responce.put("error", "");
            responce.put("message", respWeavingProductionLoomMasterModel.getLoom_production_master_status().equals("A") ? "Record Approved successfully!..."
                    : (respWeavingProductionLoomMasterModel.getLoom_production_master_status().equals("R") ? "Record Rejected successfully!.."
                    : (weaving_production_loom_master_id == 0 ? "Record added successfully!..." : "Record updated successfully!...")));

        } catch (DataAccessException e) {
            e.printStackTrace();

            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionLoomMaster/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionLoomMaster/FnAddUpdateRecord", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;
    }

    private void FnAddUpdateStock(List<CXtWeavingProductionLoomMaterialModel> cXtWeavingProductionLoomMaterialModel, CXtWeavingProductionLoomMasterModel respWeavingProductionLoomMasterModel, int company_id) {
        List<CSmProductRmStockSummaryModel> updatedSummaryStock = new ArrayList<>();
        List<CSmProductRmStockDetailsModel> updatedDetailsStock = new ArrayList<>();
        List<CSmProductStockTracking> updatedStockTracking = new ArrayList<>();
        Map<String, Object> stockDetails = new HashMap<>();

        List<String> distinctMaterialIds = cXtWeavingProductionLoomMaterialModel.stream()
                .map(CXtWeavingProductionLoomMaterialModel::getProduct_material_id)
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


//		Create Group of warping details material wise
        Map<String, List<CXtWeavingProductionLoomMaterialModel>> loomDetailsMaterialWiseGroup = cXtWeavingProductionLoomMaterialModel.stream()
                .collect(Collectors.groupingBy(CXtWeavingProductionLoomMaterialModel::getProduct_material_id));

        AtomicReference<Double> reducedQtyRef = new AtomicReference<>(null);

//		Iterate on warping details material groups
        for (Map.Entry<String, List<CXtWeavingProductionLoomMaterialModel>> group : loomDetailsMaterialWiseGroup.entrySet()) {
            String product_material_id = group.getKey();
            List<CXtWeavingProductionLoomMaterialModel> loomMaterialMovementDetailsList = group.getValue();

            // Set common attributes for all warping materials
            loomMaterialMovementDetailsList.forEach(loomMaterials -> {
                // Set common attributes for all warping materials
                loomMaterials.setWeaving_production_loom_master_id(respWeavingProductionLoomMasterModel.getWeaving_production_loom_master_id());
                loomMaterials.setLoom_production_code(respWeavingProductionLoomMasterModel.getLoom_production_code());

                List<Object> consumptionQtyInfoList = loomMaterials.getConsumptionQtyInfo();

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
                                cSmProductRmStockSummaryModel.setCompany_id(loomMaterials.getCompany_id());
                                cSmProductRmStockSummaryModel.setCompany_branch_id(loomMaterials.getCompany_branch_id());
                                cSmProductRmStockSummaryModel.setFinancial_year(loomMaterials.getFinancial_year());
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
                            FnModifyStockDetails(productRmStockDetailsList, cSmProductRmStockSummaryModel, product_material_id, godownWiseConsumptionQty, loomMaterials, respWeavingProductionLoomMasterModel, updatedDetailsStock, updatedStockTracking);

                        }

                    }
                } else {
                    AtomicDouble total_consumption_quantity = new AtomicDouble(loomMaterials.getConsumption_quantity());

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
                                        summaryModel.setCompany_id(loomMaterials.getCompany_id());
                                        summaryModel.setCompany_branch_id(loomMaterials.getCompany_branch_id());
                                        summaryModel.setFinancial_year(loomMaterials.getFinancial_year());
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
                                    FnModifyStockDetails(productRmStockDetailsList, summObj, product_material_id, reducedQtyRef, loomMaterials, respWeavingProductionLoomMasterModel, updatedDetailsStock, updatedStockTracking);


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


    private void FnModifyStockDetails(AtomicReference<List<CSmProductRmStockDetailsModel>> productRmStockDetailsList, CSmProductRmStockSummaryModel summObj, String product_material_id, AtomicReference<Double> reducedQtyRef, CXtWeavingProductionLoomMaterialModel loomMaterials, CXtWeavingProductionLoomMasterModel respWeavingProductionLoomMasterModel, List<CSmProductRmStockDetailsModel> updatedDetailsStock, List<CSmProductStockTracking> updatedStockTracking) {
        Integer godown_id = summObj.getGodown_id();
        Integer godown_section_id = summObj.getGodown_section_id();
        Integer godown_section_beans_id = summObj.getGodown_section_beans_id();

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
                    FnAddIntoStockTracking(updatedStockTracking, loomMaterials, respWeavingProductionLoomMasterModel, stockDetail, reducedQtyRef);

                } else {
                    return;
                }
            });

        }

    }


    private void FnAddIntoStockTracking(List<CSmProductStockTracking> updatedStockTracking, CXtWeavingProductionLoomMaterialModel loomMaterials, CXtWeavingProductionLoomMasterModel respWeavingProductionLoomMasterModel, CSmProductRmStockDetailsModel stockDetail, AtomicReference<Double> reducedQtyRef) {
        CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();

        cSmProductStockTracking.setCompany_id(loomMaterials.getCompany_id());
        cSmProductStockTracking.setCompany_branch_id(loomMaterials.getCompany_branch_id());
        cSmProductStockTracking.setFinancial_year(loomMaterials.getFinancial_year());
        cSmProductStockTracking.setProduct_material_id(loomMaterials.getProduct_material_id());
        cSmProductStockTracking.setGoods_receipt_no(stockDetail.getGoods_receipt_no());
        cSmProductStockTracking.setConsumption_no(loomMaterials.getLoom_production_code());
        cSmProductStockTracking.setConsumption_date(new Date());
        cSmProductStockTracking.setConsumption_location("Warping");
        cSmProductStockTracking.setConsumption_detail_no(loomMaterials.getLoom_production_set_no() + "/" + loomMaterials.getShift());
        cSmProductStockTracking.setConsumption_quantity(reducedQtyRef.get());
        cSmProductStockTracking.setCreated_by(respWeavingProductionLoomMasterModel.getCreated_by());

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
    public Map<String, Object> FnDeleteRecord(int weaving_production_loom_master_id, int company_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();

        try {

            //Delete Weaving Production Loom Master Record
            iXtWeavingProductionLoomMasterRepository.FnDeleteWeavingProductionLoomMasterRecord(weaving_production_loom_master_id, company_id, deleted_by);

            //Delete Weaving Production Loom Details Record
            iXtWeavingProductionLoomDetailsRepository.FnDeleteWeavingProductionLoomDetailsRecord(weaving_production_loom_master_id, company_id, deleted_by);

            //Delete Weaving Production Loom Stoppage Record
            iXtWeavingProductionLoomStoppageRepository.FnDeleteWeavingProductionLoomStoppageRecord(weaving_production_loom_master_id, company_id, deleted_by);

            //Delete Weaving Production Loom Wastage Record
            iXtWeavingProductionLoomWastageRepository.FnDeleteWeavingProductionLoomWastageRecord(weaving_production_loom_master_id, company_id, deleted_by);

            //Delete Weaving Loom Production Material Record
            iXtWeavingProductionLoomMaterialRepository.FnDeleteWeavingProductionLoomMaterialRecord(weaving_production_loom_master_id, company_id, deleted_by);

            responce.put("success", 1);
            responce.put("data", "");

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionLoomMaster/FnAddUpdateRecord", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_loom_master_id, int company_id) {
        Map<String, Object> responce = new HashMap<>();

        try {

            //Fetch Weaving Production Loom Master Record
            CXtWeavingProductionLoomMasterViewModel weavingProductionLoomMasterRecord = iXtWeavingProductionLoomMasterViewRepository.FnShowWVProductionLoomMasterRecorForUpdate(weaving_production_loom_master_id, company_id);

            //Fetch Weaving Production Loom Details Record
            List<CXtWeavingProductionLoomDetailsViewModel> weavingProductionLoomDetailsRecord = iXtWeavingProductionLoomDetailsViewRepository.FnShowWVProductionLoomDetailsRecorForUpdate(weaving_production_loom_master_id, company_id);

            //Fetch Weaving Production Loom Stoppage Record
            List<CXtWeavingProductionLoomStoppageViewModel> weavingProductionLoomStoppageRecord = iXtWeavingProductionLoomStoppageViewRepository.FnShowWVProductionLoomStoppageRecorForUpdate(weaving_production_loom_master_id, company_id);

            //Fetch Weaving Production Loom Wastage Record
            List<CXtWeavingProductionLoomWastageViewModel> weavingProductionLoomWastageRecord = iXtWeavingProductionLoomWastageViewRepository.FnShowWVProductionLoomWastageRecorForUpdate(weaving_production_loom_master_id, company_id);

            //Fetch Weaving Production Loom Material Record
            List<CXtWeavingProductionLoomMaterialViewModel> weavingProductionLoomMaterialRecord = iXtWeavingProductionLoomMaterialViewRepository.FnShowWVProductionLoomMaterialRecorForUpdate(weaving_production_loom_master_id, company_id);

            responce.put("WeavingProductionLoomMasterRecord", weavingProductionLoomMasterRecord);
            responce.put("WeavingProductionLoomDetailsRecord", weavingProductionLoomDetailsRecord);
            responce.put("WeavingProductionLoomStoppageRecord", weavingProductionLoomStoppageRecord);
            responce.put("WeavingProductionLoomWastageRecord", weavingProductionLoomWastageRecord);
            responce.put("weavingProductionLoomMaterialRecord", weavingProductionLoomMaterialRecord);
            responce.put("success", 1);

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionLoomMaster/FnShowParticularRecordForUpdate",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionLoomMaster/FnShowParticularRecordForUpdate", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;
    }

    @Override
    public List<Map<String, Object>> GetStdNormsForLoomUtilizationReportData(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage) {
        int companyId = commonIdsObj.getInt("company_id");
        String production_stoppage_reasons_name = commonIdsObj.getString("production_stoppage_reasons_name");
        String production_stoppage_reasons_type = commonIdsObj.getString("production_stoppage_reasons_type");
        int sub_section_id = commonIdsObj.getInt("sub_section_id");

        MapSqlParameterSource params = new MapSqlParameterSource();
        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(*) over() as total_count, ");
        query.append("sub_section_id, ");
        query.append("production_stoppage_reasons_name, ");
        query.append("production_stoppage_reasons_type, ");
        query.append("std_stoppage_loss_per_hour, ");
        query.append("std_stoppage_minutes ");
        query.append("FROM xm_production_stoppage_reasons ");
        query.append("WHERE company_id = :companyId ");
        params.addValue("companyId", companyId);
        query.append("AND is_delete = 0 AND section_id = 18 ");
        query.append("AND std_stoppage_loss_per_hour != '0' ");
        query.append("AND std_stoppage_minutes != '0' ");

        if (!Objects.equals(production_stoppage_reasons_name, "All")) {
            query.append("AND production_stoppage_reasons_name = :production_stoppage_reasons_name ");
            params.addValue("production_stoppage_reasons_name", production_stoppage_reasons_name);
        }
        if (sub_section_id != 0) {
            query.append("AND sub_section_id = :sub_section_id ");
            params.addValue("sub_section_id", sub_section_id);
        }
        if (!Objects.equals(production_stoppage_reasons_type, "All")) {
            query.append("AND production_stoppage_reasons_type = :production_stoppage_reasons_type ");
            params.addValue("production_stoppage_reasons_type", production_stoppage_reasons_type);
        }
        if (entriesPerPage != 0) {
            query.append("LIMIT ").append(pageCurrent).append(", ").append(entriesPerPage);
        }
        System.out.println("Standard Norms For Loom Utilization Query : " + query);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> sizedStockData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
        return sizedStockData;
    }



}
