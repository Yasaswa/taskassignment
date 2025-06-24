package com.erp.XtWeavingProductionInspectionMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Properties.Model.CPropertiesViewModel;
import com.erp.Common.Properties.Repository.IPropertiesViewRepository;
import com.erp.FinishGoods.SmProductFg.Model.CSmProductFgModel;
import com.erp.FinishGoods.SmProductFg.Repository.ISmProductFgRepository;
import com.erp.FinishGoods.SmProductFg.Repository.ISmProductFgViewRepository;
import com.erp.RawMaterial.Product_Rm.Model.CSmProductDynamicParametersModel;
import com.erp.RawMaterial.Product_Rm.Repository.IProductDynamicParameterRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryRepository;
import com.erp.XtWeavingProductionInspectionMaster.Model.*;
import com.erp.XtWeavingProductionInspectionMaster.Repository.*;
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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CXtWeavingProductionInspectionMasterServiceImpl implements IXtWeavingProductionInspectionMasterService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IXtWeavingProductionInspectionMasterRepository iXtWeavingProductionInspectionMasterRepository;

    @Autowired
    IXtWeavingProductionInspectionMasterViewRepository iXtWeavingProductionInspectionMasterViewRepository;

    @Autowired
    IXtWeavingProductionInspectionDetailsRepository iXtWeavingProductionInspectionDetailsRepository;

    @Autowired
    IXtWeavingProductionInspectionDetailsViewRepository iXtWeavingProductionInspectionDetailsViewRepository;

    @Autowired
    IXtWeavingProductionInspectionWastageRepository iXtWeavingProductionInspectionWastageRepository;

    @Autowired
    IXtWeavingProductionInspectionWastageViewRepository iXtWeavingProductionInspectionWastageViewRepository;

    @Autowired
    IXtWeavingProductionInspectionMaterialViewRepository iXtWeavingProductionInspectionMaterialViewRepository;

    @Autowired
    IXtWeavingProductionInspectionMaterialRepository iXtWeavingProductionInspectionMaterialRepository;

    @Autowired
    IPropertiesViewRepository iPropertiesViewRepository;

    @Autowired
    IXtWeavingProductionDetailsRepository iXtWeavingProductionDetailsRepository;


    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;


    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    IProductDynamicParameterRepository iProductDynamicParameterRepository;

    @Autowired
    ISmProductFgViewRepository iSmProductFgViewRepository;

    @Autowired
    ISmProductFgRepository iSmProductFgRepository;


    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // get CommonId's
        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        String keyForViewUpdate = commonIdsObj.getString("keyForViewUpdate");
        int weaving_production_inspection_master_id = commonIdsObj.getInt("weaving_production_inspection_master_id");

        // get master data
        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray wastageArray = (JSONArray) jsonObject.get("TransProdWastageData");
        JSONArray inspectionMaterialArray = (JSONArray) jsonObject.get("TransMaterialData");
        JSONArray inspectionApprovedData = (JSONArray) jsonObject.get("InspectionApprovedData");
        Map<String, Object> stockResponse = new HashMap<>();

        try {
            CXtWeavingProductionInspectionMasterModel weavingProductionInspectionMasterModel =
                    objectMapper.readValue(masterjson.toString(), CXtWeavingProductionInspectionMasterModel.class);

            //save weavingProductionInspectionMasterModel
            CXtWeavingProductionInspectionMasterModel respWVProductionInspectionMasterModel =
                    iXtWeavingProductionInspectionMasterRepository.save(weavingProductionInspectionMasterModel);

            // Process Inspection Details
            if (!detailsArray.isEmpty()) {
                // Update Inspection details
                iXtWeavingProductionInspectionDetailsRepository.updateWVProductionInspectionDetailsRecord(respWVProductionInspectionMasterModel.getWeaving_production_inspection_master_id(), company_id);

                // Convert JSON array into a list of CXtWeavingProductionInspectionDetailsModel objects using an ObjectMapper
                List<CXtWeavingProductionInspectionDetailsModel> cXtWeavingProductionInspectionDetailsModel = objectMapper
                        .readValue(detailsArray.toString(),
                                new TypeReference<List<CXtWeavingProductionInspectionDetailsModel>>() {});

                // Set master id and production code for each item
                cXtWeavingProductionInspectionDetailsModel.forEach(item -> {
                    item.setWeaving_production_inspection_master_id(respWVProductionInspectionMasterModel.getWeaving_production_inspection_master_id());
                    item.setInspection_production_code(respWVProductionInspectionMasterModel.getInspection_production_code());
                });
                // Save cXtWeavingProductionInspectionDetailsModel
                List<CXtWeavingProductionInspectionDetailsModel> respWVProductionInspectionDetailsModel = iXtWeavingProductionInspectionDetailsRepository.saveAll(cXtWeavingProductionInspectionDetailsModel);


                if (respWVProductionInspectionMasterModel.getInspection_production_master_status().equals("A")) {
                     stockResponse = FnAddMaterialStock(respWVProductionInspectionMasterModel, inspectionApprovedData, keyForViewUpdate);
                    System.out.println("FnAddMaterialStock" + stockResponse);
                }
            }
//			*********************************************************************Process start to save/update wastage Inspection ****************************************************************************************************8
            // Process Inspection Wastage Details
            if (!wastageArray.isEmpty()) {
                // JSON array into a list of CXtWeavingProductionInspectionWastageModel objects using an ObjectMapper
                List<CXtWeavingProductionInspectionWastageModel> cxtWeavingProductionInspectionWastageModel =
                        objectMapper.readValue(wastageArray.toString(), new TypeReference<List<CXtWeavingProductionInspectionWastageModel>>() {
                        });

                cxtWeavingProductionInspectionWastageModel.forEach(wastagedetails -> {

                    wastagedetails.setWeaving_production_inspection_master_id(respWVProductionInspectionMasterModel.getWeaving_production_inspection_master_id());
                    wastagedetails.setInspection_production_code(respWVProductionInspectionMasterModel.getInspection_production_code());

                });

                //update Weaving Production Inspection Wastage Details
                if (weaving_production_inspection_master_id != 0) {

                    List<Integer> distinctwvProductionInspectionWastageIds = cxtWeavingProductionInspectionWastageModel.parallelStream()
                            .map(CXtWeavingProductionInspectionWastageModel::getWeaving_production_inspection_wastage_id)
                            .distinct().collect(Collectors.toList());

                    iXtWeavingProductionInspectionWastageRepository.updateWeavingProductionInspectionWastageDetails(distinctwvProductionInspectionWastageIds, respWVProductionInspectionMasterModel.getInspection_production_code());

                }
                //Saved cxtWeavingProductionInspectionWastageModel
                iXtWeavingProductionInspectionWastageRepository.saveAll(cxtWeavingProductionInspectionWastageModel);

            } else {
                iXtWeavingProductionInspectionWastageRepository.updateWeavingProductionAllInspectionWastageDetails(respWVProductionInspectionMasterModel.getInspection_production_code());

            }
//			*********************************************************************Process end to save/update wastage Inspection ****************************************************************************************************8


            System.out.println("status : " + respWVProductionInspectionMasterModel.getInspection_production_master_status());
            responce.put("stockResponse" , stockResponse);
            responce.put("success", 1);
            responce.put("data", "");
            responce.put("error", "");
            responce.put("message",
                    respWVProductionInspectionMasterModel.getInspection_production_master_status().equals("A") ? "Record Approved successfully!..."
                            : (respWVProductionInspectionMasterModel.getInspection_production_master_status().equals("R") ? "Record Rejected successfully!.."
                            : (weaving_production_inspection_master_id == 0 ? "Record added successfully!..."
                            : "Record updated successfully!...")));
        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionInspectionMaster/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/XtWeavingProductionInspectionMaster/FnAddUpdateRecord", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;
    }

    private void FnAddUpdateStock(List<CXtWeavingProductionInspectionMaterialModel> cXtWeavingProductionInspectionMaterialModel,
                                  CXtWeavingProductionInspectionMasterModel respWVProductionInspectionMasterModel, int company_id) {

        List<CSmProductRmStockSummaryModel> updatedSummaryStock = new ArrayList<>();
        List<CSmProductRmStockDetailsModel> updatedDetailsStock = new ArrayList<>();
        List<CSmProductStockTracking> updatedStockTracking = new ArrayList<>();
        Map<String, Object> stockDetails = new HashMap<>();

        List<String> distinctMaterialIds = cXtWeavingProductionInspectionMaterialModel.stream()
                .map(CXtWeavingProductionInspectionMaterialModel::getProduct_material_id)
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
        Map<String, List<CXtWeavingProductionInspectionMaterialModel>> wpDetailsMaterialWiseGroup = cXtWeavingProductionInspectionMaterialModel.stream()
                .collect(Collectors.groupingBy(CXtWeavingProductionInspectionMaterialModel::getProduct_material_id));

        AtomicReference<Double> reducedQtyRef = new AtomicReference<>(null);

//		Iterate on warping details material groups
        for (Map.Entry<String, List<CXtWeavingProductionInspectionMaterialModel>> group : wpDetailsMaterialWiseGroup.entrySet()) {
            String product_material_id = group.getKey();
            List<CXtWeavingProductionInspectionMaterialModel> wpMaterialMovementDetailsList = group.getValue();

            // Set common attributes for all warping materials
            wpMaterialMovementDetailsList.forEach(ipMaterials -> {

                ipMaterials.setWeaving_production_inspection_master_id(respWVProductionInspectionMasterModel.getWeaving_production_inspection_master_id());
                ipMaterials.setInspection_production_code(respWVProductionInspectionMasterModel.getInspection_production_code());

                List<Object> consumptionQtyInfoList = ipMaterials.getConsumptionQtyInfo();

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
                                cSmProductRmStockSummaryModel.setCompany_id(ipMaterials.getCompany_id());
                                cSmProductRmStockSummaryModel.setCompany_branch_id(ipMaterials.getCompany_branch_id());
                                cSmProductRmStockSummaryModel.setFinancial_year(ipMaterials.getFinancial_year());
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
                            FnModifyStockDetails(productRmStockDetailsList, cSmProductRmStockSummaryModel, product_material_id, godownWiseConsumptionQty, ipMaterials, respWVProductionInspectionMasterModel, updatedDetailsStock, updatedStockTracking);

                        }

                    }
                } else {
                    AtomicDouble total_consumption_quantity = new AtomicDouble(ipMaterials.getConsumption_quantity());

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
                                        summaryModel.setCompany_id(ipMaterials.getCompany_id());
                                        summaryModel.setCompany_branch_id(ipMaterials.getCompany_branch_id());
                                        summaryModel.setFinancial_year(ipMaterials.getFinancial_year());
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
                                    FnModifyStockDetails(productRmStockDetailsList, summObj, product_material_id, reducedQtyRef, ipMaterials, respWVProductionInspectionMasterModel, updatedDetailsStock, updatedStockTracking);

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

    private void FnModifyStockDetails(AtomicReference<List<CSmProductRmStockDetailsModel>> productRmStockDetailsList, CSmProductRmStockSummaryModel summObj, String product_material_id, AtomicReference<Double> reducedQtyRef, CXtWeavingProductionInspectionMaterialModel ipMaterials,
                                      CXtWeavingProductionInspectionMasterModel respWVProductionInspectionMasterModel, List<CSmProductRmStockDetailsModel> updatedDetailsStock, List<CSmProductStockTracking> updatedStockTracking) {
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
                    FnAddIntoStockTracking(updatedStockTracking, ipMaterials, respWVProductionInspectionMasterModel, stockDetail, reducedQtyRef);

                } else {
                }
            });

        }


    }

    private void FnAddIntoStockTracking(List<CSmProductStockTracking> updatedStockTracking, CXtWeavingProductionInspectionMaterialModel ipMaterials, CXtWeavingProductionInspectionMasterModel respWVProductionInspectionMasterModel,
                                        CSmProductRmStockDetailsModel stockDetail, AtomicReference<Double> reducedQtyRef) {
        CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();

        cSmProductStockTracking.setCompany_id(ipMaterials.getCompany_id());
        cSmProductStockTracking.setCompany_branch_id(ipMaterials.getCompany_branch_id());
        cSmProductStockTracking.setFinancial_year(ipMaterials.getFinancial_year());
        cSmProductStockTracking.setProduct_material_id(ipMaterials.getProduct_material_id());
        cSmProductStockTracking.setGoods_receipt_no(stockDetail.getGoods_receipt_no());
        cSmProductStockTracking.setConsumption_no(ipMaterials.getInspection_production_code());
        cSmProductStockTracking.setConsumption_date(new Date());
        cSmProductStockTracking.setConsumption_location("Warping");
        cSmProductStockTracking.setConsumption_detail_no(ipMaterials.getInspection_production_set_no() + "/" + ipMaterials.getShift());
        cSmProductStockTracking.setConsumption_quantity(reducedQtyRef.get());
        cSmProductStockTracking.setCreated_by(respWVProductionInspectionMasterModel.getCreated_by());

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

    //	Stock insertion after inspection approval
    private Map<String, Object> FnAddMaterialStock(CXtWeavingProductionInspectionMasterModel weavingProductionInspectionMasterModel, JSONArray inspectionApprovedData, String keyForViewUpdate) {

        Map<String, Object> bothBatchresponse = new HashMap<>();
        Map<String, Object> batchresponse = new HashMap<>();
        Set<String> increasedMaterials = new HashSet<>();
        Set<String> decreasedMaterials = new HashSet<>();

//		 Retrieve the list of goods receipt details based on the master transaction id
        List<CXtWeavingProductionInspectionDetailsViewModel> approvedInspectionDetails = iXtWeavingProductionInspectionDetailsViewRepository.FnShowWvProductionInspectionApprovedRecords(weavingProductionInspectionMasterModel.getWeaving_production_inspection_master_id());

        // Convert previous inspectionApprovedData (JSONArray) to a Map for quick lookup
        Map<String, JSONObject> previousDataMap = new HashMap<>();
        for (int i = 0; i < inspectionApprovedData.length(); i++) {
            JSONObject prevData = inspectionApprovedData.getJSONObject(i);
            String key = prevData.getString("product_material_id") + "_" + prevData.getInt("godown_id") + "_" + prevData.getInt("roll_no");
            previousDataMap.put(key, prevData);
        }

//		Create list to add object to save stock details & Summary
        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

        for (CXtWeavingProductionInspectionDetailsViewModel approvedInspectionItem : approvedInspectionDetails) {
            String key = approvedInspectionItem.getProduct_material_id() + "_" + approvedInspectionItem.getGodown_id() + "_" + approvedInspectionItem.getRoll_no();
            double previousMtr = 0.0;
            double previousWeight = 0.0;
            String material_id = approvedInspectionItem.getProduct_material_id();

            // Check if material exists in previous data
            if (previousDataMap.containsKey(key)) {
                JSONObject prevData = previousDataMap.get(key);
                previousMtr = prevData.optDouble("inspection_mtr", 0.0);
                previousWeight = prevData.optDouble("weight", 0.0);
            }
            // Get updated values
            double updatedMtr = approvedInspectionItem.getInspection_mtr();
            double updatedWeight = approvedInspectionItem.getWeight();
            // Declare variables outside the block
            double mtrDifference;
            double weightDifference;
            Integer noOfRolls = 0;

            // Calculate difference
            if (keyForViewUpdate.equals("approve")) {
                noOfRolls = 1;
                mtrDifference = updatedMtr;
                weightDifference = updatedWeight;
            } else {
                mtrDifference = updatedMtr - previousMtr;
                weightDifference = updatedWeight - previousWeight;
            }
            // Check for increase or decrease
            if (mtrDifference > 0 || weightDifference > 0) {
                increasedMaterials.add(material_id);
            }
            if (mtrDifference < 0 || weightDifference < 0) {
                decreasedMaterials.add(material_id);
            }
            CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

            Optional<CSmProductRmStockSummaryModel> checkTheMaterialAlreadyExist = addProductRmStockSummaryList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(approvedInspectionItem.getProduct_material_id()) &&
                            item.getGodown_id().equals(approvedInspectionItem.getGodown_id())).findFirst();

            if (checkTheMaterialAlreadyExist.isPresent()) {
                productRmStockSummaryModel = checkTheMaterialAlreadyExist.get();

                productRmStockSummaryModel.setClosing_balance_quantity(productRmStockSummaryModel.getClosing_balance_quantity() + mtrDifference);
                productRmStockSummaryModel.setClosing_balance_weight(productRmStockSummaryModel.getClosing_balance_weight() + weightDifference);
                productRmStockSummaryModel.setClosing_no_of_boxes(productRmStockSummaryModel.getClosing_no_of_boxes() + noOfRolls);
                productRmStockSummaryModel.setProduction_no_of_boxes(productRmStockSummaryModel.getProduction_no_of_boxes() + noOfRolls);
                productRmStockSummaryModel.setProduction_quantity(productRmStockSummaryModel.getProduction_quantity() + mtrDifference);
                productRmStockSummaryModel.setProduction_weight(productRmStockSummaryModel.getProduction_weight() + weightDifference);
            } else {
                productRmStockSummaryModel.setCompany_id(approvedInspectionItem.getCompany_id());
                productRmStockSummaryModel.setCompany_branch_id(approvedInspectionItem.getCompany_branch_id());
                productRmStockSummaryModel.setFinancial_year(approvedInspectionItem.getFinancial_year());
                productRmStockSummaryModel.setProduct_type_group(approvedInspectionItem.getProduct_type_group());
                productRmStockSummaryModel.setProduct_type_id(approvedInspectionItem.getProduct_type_id());
                productRmStockSummaryModel.setProduct_rm_id(material_id);
                productRmStockSummaryModel.setProduct_material_unit_id(approvedInspectionItem.getProduct_material_stock_unit_id());
                productRmStockSummaryModel.setProduct_material_packing_id(approvedInspectionItem.getProduct_material_packing_id());

                productRmStockSummaryModel.setClosing_no_of_boxes(noOfRolls);
                productRmStockSummaryModel.setClosing_balance_quantity(mtrDifference);
                productRmStockSummaryModel.setClosing_balance_weight(weightDifference);
                productRmStockSummaryModel.setProduction_no_of_boxes(noOfRolls);
                productRmStockSummaryModel.setProduction_quantity(mtrDifference);
                productRmStockSummaryModel.setProduction_weight(weightDifference);

                productRmStockSummaryModel.setGodown_id(approvedInspectionItem.getGodown_id());
                productRmStockSummaryModel.setGodown_section_id(approvedInspectionItem.getGodown_section_id());
                productRmStockSummaryModel.setGodown_section_beans_id(approvedInspectionItem.getGodown_section_beans_id());
                productRmStockSummaryModel.setCreated_by(approvedInspectionItem.getCreated_by());
                productRmStockSummaryModel.setModified_by(approvedInspectionItem.getCreated_by());
                productRmStockSummaryModel.setModified_on(approvedInspectionItem.getModified_on());

                addProductRmStockSummaryList.add(productRmStockSummaryModel);
            }

//			smv_product_rm_stock_details
            CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

            String batchNo = (approvedInspectionItem.getStyle() == null || approvedInspectionItem.getStyle().isEmpty())
                    ? approvedInspectionItem.getSort_no()
                    : approvedInspectionItem.getSort_no() + "-" + approvedInspectionItem.getStyle();

            Optional<CSmProductRmStockDetailsModel> checkTheMaterialDertailAlreadyExist = addProductRmStockDetailsList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(approvedInspectionItem.getProduct_material_id()) &&
                            item.getGodown_id().equals(approvedInspectionItem.getGodown_id()) &&
                            item.getBatch_no().equals(batchNo) &&
                            item.getGoods_receipt_no().equals(approvedInspectionItem.getInspection_production_set_no())).findFirst();

            if (checkTheMaterialDertailAlreadyExist.isPresent()) {
                productRmStockDetailsModel = checkTheMaterialDertailAlreadyExist.get();
                productRmStockDetailsModel.setClosing_no_of_boxes(productRmStockDetailsModel.getClosing_no_of_boxes() + noOfRolls);
                productRmStockDetailsModel.setClosing_balance_quantity(productRmStockDetailsModel.getClosing_balance_quantity() + mtrDifference);
                productRmStockDetailsModel.setClosing_balance_weight(productRmStockDetailsModel.getClosing_balance_weight() + weightDifference);
                productRmStockDetailsModel.setProduction_no_of_boxes(productRmStockDetailsModel.getProduction_no_of_boxes() + noOfRolls);
                productRmStockDetailsModel.setProduction_quantity(productRmStockDetailsModel.getProduction_quantity() + mtrDifference);
                productRmStockDetailsModel.setProduction_weight(productRmStockDetailsModel.getProduction_weight() + weightDifference);
            } else {
                productRmStockDetailsModel.setCompany_id(approvedInspectionItem.getCompany_id());
                productRmStockDetailsModel.setCompany_branch_id(approvedInspectionItem.getCompany_branch_id());
                productRmStockDetailsModel.setFinancial_year(approvedInspectionItem.getFinancial_year());
                productRmStockDetailsModel.setProduct_type_group(approvedInspectionItem.getProduct_type_group());
                productRmStockDetailsModel.setProduct_type_id(approvedInspectionItem.getProduct_type_id());
                productRmStockDetailsModel.setProduct_rm_id(approvedInspectionItem.getProduct_material_id());
                productRmStockDetailsModel.setProduct_material_unit_id(approvedInspectionItem.getProduct_material_stock_unit_id());
                productRmStockDetailsModel.setProduct_material_packing_id(approvedInspectionItem.getProduct_material_packing_id());
                productRmStockDetailsModel.setStock_date(approvedInspectionItem.getInspection_production_date());

                productRmStockDetailsModel.setGoods_receipt_no(approvedInspectionItem.getInspection_production_set_no());
                productRmStockDetailsModel.setBatch_no(batchNo);
                productRmStockDetailsModel.setProduction_no_of_boxes(noOfRolls);
                productRmStockDetailsModel.setProduction_quantity(mtrDifference);
                productRmStockDetailsModel.setProduction_weight(weightDifference);
                productRmStockDetailsModel.setClosing_no_of_boxes(noOfRolls);
                productRmStockDetailsModel.setClosing_balance_quantity(mtrDifference);
                productRmStockDetailsModel.setClosing_balance_weight(weightDifference);
                productRmStockDetailsModel.setGodown_id(approvedInspectionItem.getGodown_id());
                productRmStockDetailsModel.setCreated_by(approvedInspectionItem.getCreated_by());
                productRmStockDetailsModel.setModified_by(approvedInspectionItem.getCreated_by());

                addProductRmStockDetailsList.add(productRmStockDetailsModel);
            }
        }
        // Lists to track increased and decreased stock entries
        List<CSmProductRmStockSummaryModel> increasedStockSummaryList = new ArrayList<>();
        List<CSmProductRmStockDetailsModel> increasedStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> decreasedStockSummaryList = new ArrayList<>();
        List<CSmProductRmStockDetailsModel> decreasedStockDetailsList = new ArrayList<>();

// Iterate through addProductRmStockSummaryList
        for (CSmProductRmStockSummaryModel stockEntry : addProductRmStockSummaryList) {
            String materialId = stockEntry.getProduct_rm_id();
            if (increasedMaterials.contains(materialId)) {
                increasedStockSummaryList.add(stockEntry);
            }
            if (decreasedMaterials.contains(materialId)) {
                decreasedStockSummaryList.add(stockEntry);
            }
        }
        // Iterate through addProductRmStockDetailsList
        for (CSmProductRmStockDetailsModel stockEntry : addProductRmStockDetailsList) {
            String materialId = stockEntry.getProduct_rm_id();
            if (increasedMaterials.contains(materialId)) {
                increasedStockDetailsList.add(stockEntry);
            }
            if (decreasedMaterials.contains(materialId)) {
                decreasedStockDetailsList.add(stockEntry);
            }
        }
        // Prepare Map objects for increase and decrease
        Map<String, Object> increaseStockDetails = new HashMap<>();
        Map<String, Object> decreaseStockDetails = new HashMap<>();

        if (!increasedStockSummaryList.isEmpty() || !increasedStockDetailsList.isEmpty()) {
            increaseStockDetails.put("RmStockSummary", increasedStockSummaryList);
            increaseStockDetails.put("RmStockDetails", increasedStockDetailsList);
            batchresponse = cSmProductRmStockDetailsController.FnAddUpdateFGStock(increaseStockDetails, "Increase", weavingProductionInspectionMasterModel.getCompany_id());
            bothBatchresponse.put("FGStockAddDetails", batchresponse);
        }

        if (!decreasedStockSummaryList.isEmpty() || !decreasedStockDetailsList.isEmpty()) {
            decreaseStockDetails.put("RmStockSummary", decreasedStockSummaryList);
            decreaseStockDetails.put("RmStockDetails", decreasedStockDetailsList);
            batchresponse = cSmProductRmStockDetailsController.FnAddUpdateFGStock(decreaseStockDetails, "Decrease", weavingProductionInspectionMasterModel.getCompany_id());
            bothBatchresponse.put("FGStockReducedDetails", batchresponse);
        }

        System.out.println("bothBatchresponse" + bothBatchresponse);
        return bothBatchresponse;
    }

    @Override
    public Map<String, Object> FnDeleteRecord(int weaving_production_inspection_master_id, int company_id,
                                              String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            //Delete Weaving Production Inspection Master Record
            iXtWeavingProductionInspectionMasterRepository.FnDeleteRecordWVProductionInspectionMasterRecord(weaving_production_inspection_master_id, company_id, deleted_by);

            //Delete Weaving Production Inspection Details Record
            iXtWeavingProductionInspectionDetailsRepository.FnDeleteRecordWVProductionInspectionDetailsRecord(weaving_production_inspection_master_id, company_id, deleted_by);

            //Delete Weaving Production Inspection Wastage Record
            iXtWeavingProductionInspectionWastageRepository.FnDeleteRecordWVProductionInspectionWastageRecord(weaving_production_inspection_master_id, company_id, deleted_by);

            //Delete Weaving Production Inspection Material Record
            iXtWeavingProductionInspectionMaterialRepository.FnDeleteRecordWVProductionInspectionMaterialRecord(weaving_production_inspection_master_id, company_id, deleted_by);

            responce.put("success", 1);

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
    public Map<String, Object> FnDeleteWeavingProductionData(int weaving_production_inspection_master_id, int company_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            //Delete Weaving Production Inspection Master Record
            iXtWeavingProductionInspectionMasterRepository.FnDeleteRecordWVProductionInspectionMasterRecord(weaving_production_inspection_master_id, company_id, deleted_by);

            //Delete Weaving Production Inspection Details Record
            iXtWeavingProductionInspectionDetailsRepository.FnDeleteRecordWVProductionInspectionDetailsRecord(weaving_production_inspection_master_id, company_id, deleted_by);

            responce.put("success", 1);

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
    public Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_inspection_master_id,
                                                               int company_id) {
        Map<String, Object> responce = new HashMap<>();

        try {

            //Fetch Weaving Production Inspection Master Record
            CXtWeavingProductionInspectionMasterModel WeavingProductionInspectionMasterModelRecord = iXtWeavingProductionInspectionMasterRepository.FnShowWVProductionInspectionMasterRecordForUpdate(weaving_production_inspection_master_id, company_id);

            //Fetch Weaving Production Inspection Details Record
            List<CXtWeavingProductionInspectionDetailsViewModel> WeavingProductionInspectionDetailsRecords = iXtWeavingProductionInspectionDetailsViewRepository.FnShowWVProductionInspectionDetailsRecordForUpdate(weaving_production_inspection_master_id, company_id);

            //Fetch Weaving Production Inspection Wastage Record
            List<CXtWeavingProductionInspectionWastageViewModel> WeavingProductionInspectionWastageRecords = iXtWeavingProductionInspectionWastageViewRepository.FnShowWVProductionInspectionWastageRecordForUpdate(weaving_production_inspection_master_id, company_id);

            //Fetch Weaving Production Inspection Material Record
            List<CXtWeavingProductionInspectionMaterialViewModel> weavingProductionInspectionMaterialRecords = iXtWeavingProductionInspectionMaterialViewRepository.FnShowWVProductionInspectionMaterialRecordForUpdate(weaving_production_inspection_master_id, company_id);

            responce.put("success", 1);
            responce.put("WeavingProductionInspectionMasterModelRecord", WeavingProductionInspectionMasterModelRecord);
            responce.put("WeavingProductionInspectionDetailsRecords", WeavingProductionInspectionDetailsRecords);
            responce.put("WeavingProductionInspectionWastageRecords", WeavingProductionInspectionWastageRecords);
            responce.put("WeavingProductionInspectionMaterialRecords", weavingProductionInspectionMaterialRecords);

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
    public Map<String, Object> FnShowParticularInspectionShiftSummary(String inspection_production_date, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Map<String, Object>> shiftWiseSummary = new LinkedHashMap<>();

            // Get shifts and sort them
            //Get Production shifts from property master
            List<CPropertiesViewModel> getShifts = iPropertiesViewRepository.FnShowParticularRecord("ProductionShifts", company_id)
                    .stream()
                    .sorted(Comparator.comparingLong(CPropertiesViewModel::getProperty_id))
                    .collect(Collectors.toList());

            // Get today's production summary and previous date's production summary
            List<CXtWeavingProductionInspectionDetailsViewModel> productionSummarytoday = iXtWeavingProductionInspectionDetailsViewRepository.FnShowParticularInspectionShiftSummary(inspection_production_date, company_id);
            List<CXtWeavingProductionInspectionDetailsViewModel> productionSummaryPreviousdateDetails = iXtWeavingProductionInspectionDetailsViewRepository.FnShowPreviousInspectionShiftSummary(inspection_production_date, company_id);


            // Iterate on production shifts
            getShifts.forEach(shifts -> {
                Map<String, Object> shiftObject = new LinkedHashMap<>();
                shiftObject.put("shift", shifts.getProperty_name());

                // Get today's production data for the current shift
                CXtWeavingProductionInspectionDetailsViewModel shiftwisetodaydateData = productionSummarytoday.stream()
                        .filter(item -> item.getShift().equals(shifts.getProperty_name()) && item.getInspection_production_date().equals(inspection_production_date))
                        .findFirst()
                        .orElse(null);

                if (shiftwisetodaydateData != null) {
//					shiftObject.put("shift_total_product_in_meter", shiftwisetodaydateData.getTotal_product_in_meter());
//					shiftObject.put("shift_total_inspection_mtr", shiftwisetodaydateData.getTotal_inspection_mtr());
//					shiftObject.put("shift_total_weight", shiftwisetodaydateData.getTotal_weight());

                } else {
                    shiftObject.put("shift_total_product_in_meter", 0);
                    shiftObject.put("shift_total_inspection_mtr", 0);
                    shiftObject.put("shift_total_weight", 0);
                }

                // Get previous date's production data for the current shift
                List<CXtWeavingProductionInspectionDetailsViewModel> uptoDateInspectionShiftSummaryData = productionSummaryPreviousdateDetails.stream()
                        .filter(item -> item.getShift().equals(shifts.getProperty_name()))
                        .collect(Collectors.toList());

                if (uptoDateInspectionShiftSummaryData != null) {

                    double total_product_in_meter = uptoDateInspectionShiftSummaryData.stream().mapToDouble(CXtWeavingProductionInspectionDetailsViewModel::getProduct_in_meter).sum();
                    double total_inspection_mtr = uptoDateInspectionShiftSummaryData.stream().mapToDouble(CXtWeavingProductionInspectionDetailsViewModel::getInspection_mtr).sum();
                    double total_weight = uptoDateInspectionShiftSummaryData.stream().mapToDouble(CXtWeavingProductionInspectionDetailsViewModel::getWeight).sum();

                    // Round the totals to two decimal places
                    total_product_in_meter = Math.round(total_product_in_meter * 100.0) / 100.0;
                    total_inspection_mtr = Math.round(total_inspection_mtr * 100.0) / 100.0;
                    total_weight = Math.round(total_weight * 100.0) / 100.0;


                    // Put the rounded totals into shiftObject
                    //here upto date total product_in_meter,inspection_mtr_total, total weight.
                    shiftObject.put("shift_product_in_meter_upto_date_total", total_product_in_meter);
                    shiftObject.put("shift_inspection_mtr_upto_date_total", total_inspection_mtr);
                    shiftObject.put("shift_total_upto_date_weight", total_weight);

                } else {
                    shiftObject.put("shift_product_in_meter_upto_date_total", 0);
                    shiftObject.put("shift_inspection_mtr_upto_date_total", 0);
                    shiftObject.put("shift_total_upto_date_weight", 0);

                }

                shiftWiseSummary.put(shifts.getProperty_name(), shiftObject);
            });

            response.put("success", 1);
            response.put("data", shiftWiseSummary);
            response.put("error", "");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/XtWeavingProductionInspectionDetails/FnShowParticularInspectionShiftSummary", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWeavingProductionInspectionDetails/FnShowParticularInspectionShiftSummary", 0, e.getMessage());
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());

        }
        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> FnSaveDailyProductionData(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
            int company_id = commonIdsObj.getInt("company_id");
            int weaving_production_loom_master_id = commonIdsObj.getInt("weaving_production_loom_master_id");
            JSONObject masterjson = jsonObject.getJSONObject("DailyProductionReportMasteData");
            JSONArray detailsArray = (JSONArray) jsonObject.get("DailyProductionReport");

            String message = "Record inserted successfully!";

            //save weavingProductionInspectionMasterModel
            CXtWeavingProductionInspectionMasterModel weavingProductionInspectionMasterModel =
                    objectMapper.readValue(masterjson.toString(), CXtWeavingProductionInspectionMasterModel.class);

            //Saving Details Data by updating master id
            List<CXtWeavingProductionInspectionDetailsModel> dailyProductionReport = objectMapper.readValue(detailsArray.toString(), new TypeReference<List<CXtWeavingProductionInspectionDetailsModel>>() {
            });


            CXtWeavingProductionInspectionMasterModel respWVProductionInspectionMasterModel =
                    iXtWeavingProductionInspectionMasterRepository.save(weavingProductionInspectionMasterModel);


            List<String> distinctSortNos = dailyProductionReport.stream()
                    .map(CXtWeavingProductionInspectionDetailsModel::getSort_no)
                    .distinct()
                    .collect(Collectors.toList());

            List<CSmProductDynamicParametersModel> productDynamicParameters = iProductDynamicParameterRepository.findProductIdsBySortNos(distinctSortNos);

//            GET PRODUCT_FG_IDS FROM DYNAMIC PROPERTIES
            List<String> productIdListSeparated = productDynamicParameters.parallelStream().map(CSmProductDynamicParametersModel::getProduct_id).collect(Collectors.toList());

//            Get Product details from smv_product_fg
            List<CSmProductFgModel> getProductDetails = iSmProductFgRepository.getProductFgDetails(productIdListSeparated);

//		    Get the current date
            Date currentDate = new Date();

//		    Formatting the date to display only the date portion
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = dateFormat.format(currentDate);

            Map<String, Object> stockDetails = new HashMap<>();


//		    Create list to add object to save stock details & Summary
            List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
            List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

//          ITERATE ON DAILY PRODUCTION DETAILS REPORTS
            dailyProductionReport.forEach(item -> {
//              GET sm_product_dynamic_parameters for some sort no
                Optional<CSmProductDynamicParametersModel> dynamicParametersModel = productDynamicParameters.stream()
                        .filter(pdParameters -> pdParameters.getProduct_parameter_value().equals(item.getSort_no())).findFirst();

                item.setWeaving_production_inspection_master_id(respWVProductionInspectionMasterModel.getWeaving_production_inspection_master_id());
                item.setInspection_production_code(respWVProductionInspectionMasterModel.getInspection_production_code());


                if (dynamicParametersModel.isPresent()) {
                    CSmProductDynamicParametersModel dynamicParametersObject = dynamicParametersModel.get();

                    item.setProduct_material_id(dynamicParametersObject.getProduct_id());

//                  GET PRODUCT DETAILS FROM getProductDetails
                    CSmProductFgModel smProductFgModel = getProductDetails.stream()
                            .filter(finishGood -> finishGood.getProduct_fg_id().equals(dynamicParametersObject.getProduct_id()))
                            .findFirst().get();

//			        sm_product_rm_stock_summary
//					Check if an entry with the same identifier (e.g., product_rm_id or any unique field) exists
                    Optional<CSmProductRmStockSummaryModel> getExistingSummaryForProduct = addProductRmStockSummaryList
                            .parallelStream()
                            .filter(summary -> summary.getProduct_rm_id().equals(smProductFgModel.getProduct_fg_id()))
                            .findFirst();

                    if (getExistingSummaryForProduct.isPresent()) {
                        // Update existing entry
                        CSmProductRmStockSummaryModel existingSummary = getExistingSummaryForProduct.get();

                        // Update the quantity fields by adding new values to the existing ones
                        existingSummary.setClosing_balance_quantity(existingSummary.getClosing_balance_quantity() + item.getInspection_mtr());
                        existingSummary.setClosing_balance_weight(existingSummary.getClosing_balance_weight() + item.getWeight());
                        existingSummary.setProduction_quantity(existingSummary.getProduction_quantity() + item.getInspection_mtr());
                        existingSummary.setProduction_weight(existingSummary.getProduction_weight() + item.getWeight());

                    } else {
                        // Create a new entry if it doesn't exist
                        CSmProductRmStockSummaryModel productRmStockSummaryModel = CSmProductRmStockSummaryModel.builder()
                                .company_id(company_id)
                                .company_branch_id(item.getCompany_branch_id())
                                .financial_year(item.getFinancial_year())
                                .product_type_id(smProductFgModel.getProduct_type_id())
                                .product_rm_id(smProductFgModel.getProduct_fg_id())
                                .product_material_unit_id(smProductFgModel.getProduct_fg_stock_unit_id())
                                .product_material_packing_id(smProductFgModel.getProduct_fg_packing_id())
                                .closing_balance_quantity(item.getInspection_mtr())
                                .closing_balance_weight(item.getWeight())
                                .production_weight(item.getWeight())
                                .production_quantity(item.getInspection_mtr())
                                .created_by(item.getCreated_by())
                                .modified_by(item.getCreated_by())
                                .godown_id(smProductFgModel.getGodown_id())
                                .godown_section_id(smProductFgModel.getGodown_section_id())
                                .godown_section_beans_id(smProductFgModel.getGodown_section_beans_id())
                                .build();

                        addProductRmStockSummaryList.add(productRmStockSummaryModel);
                    }


//			        sm_product_rm_stock_details
                    Optional<CSmProductRmStockDetailsModel> getExistingObjectForSortNo = addProductRmStockDetailsList
                            .parallelStream().filter(fg -> fg.getGoods_receipt_no().equals(item.getSort_no())).findFirst();

                    if (getExistingObjectForSortNo.isPresent()) {
//                      Update existing object with new quantity
                        CSmProductRmStockDetailsModel existingModel = getExistingObjectForSortNo.get();

                        // Update the quantity fields as required
                        existingModel.setClosing_balance_quantity(existingModel.getClosing_balance_quantity() + item.getInspection_mtr());
                        existingModel.setClosing_balance_weight(existingModel.getClosing_balance_weight() + item.getWeight());
                        existingModel.setProduction_quantity(existingModel.getProduction_quantity() + item.getInspection_mtr());
                        existingModel.setProduction_weight(existingModel.getProduction_weight() + item.getWeight());

                    } else {
                        CSmProductRmStockDetailsModel productRmStockDetailsModel = CSmProductRmStockDetailsModel.builder()
                                .company_id(company_id)
                                .company_branch_id(item.getCompany_branch_id())
                                .financial_year(item.getFinancial_year())
                                .product_type_id(smProductFgModel.getProduct_type_id())
//							    .product_type_group()
                                .product_rm_id(smProductFgModel.getProduct_fg_id())
                                .product_material_unit_id(smProductFgModel.getProduct_fg_stock_unit_id())
                                .product_material_packing_id(smProductFgModel.getProduct_fg_packing_id())
                                .stock_date(todayDate)
                                .goods_receipt_no(item.getSort_no())
                                .closing_balance_quantity(item.getInspection_mtr())
                                .closing_balance_weight(item.getWeight())
                                .production_weight(item.getWeight())
                                .production_quantity(item.getInspection_mtr())
                                .created_by(item.getCreated_by())
                                .modified_by(item.getCreated_by())
                                .godown_id(smProductFgModel.getGodown_id())
                                .godown_section_id(smProductFgModel.getGodown_section_id())
                                .godown_section_beans_id(smProductFgModel.getGodown_section_beans_id())
                                .build();

                        addProductRmStockDetailsList.add(productRmStockDetailsModel);
                    }


                }
            });

            stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
            stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
//			stockDetails.put("StockTracking", addProductStockTrackingList);

//			Store in xt_weaving_inspection_details
            iXtWeavingProductionInspectionDetailsRepository.saveAll(dailyProductionReport);

////			Insert in stock table
//            cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", company_id);


            response.put("success", 1);
            response.put("data", dailyProductionReport);
            response.put("error", "");
            response.put("message", message);
//
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            response.put("message", "Error occurred.");
        }


        return response;
    }

    @Override
    public Map<String, Object> GetLastRollNoWeavingInspection(int book_type_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            Map<String, Object> responceLastRollNoWeavingInspection = iXtWeavingProductionInspectionDetailsRepository
                    .GetLastRollNoWeavingInspection(book_type_id);

            responce.put("data", responceLastRollNoWeavingInspection);
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
