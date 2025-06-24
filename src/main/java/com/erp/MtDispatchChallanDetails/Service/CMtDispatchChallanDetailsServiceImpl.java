package com.erp.MtDispatchChallanDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.DispatchSizedYarn.Repository.IDtSizedYarnModelRepository;
import com.erp.FinishGoods.SmProductFg.Model.CSmProductFgModel;
import com.erp.FinishGoods.SmProductFg.Repository.ISmProductFgRepository;
import com.erp.MtDispatchChallanDetails.Model.*;
import com.erp.MtDispatchChallanDetails.Repository.*;
import com.erp.MtDispatchScheduleDetails.Model.*;
import com.erp.MtDispatchScheduleDetails.Repository.*;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderDetailsTradingViewModel;
import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderMasterTradingModel;
import com.erp.MtSalesOrderMasterTrading.Repository.IMtSalesOrderDetailsTradingRepository;
import com.erp.MtSalesOrderMasterTrading.Repository.IMtSalesOrderDetailsTradingViewRepository;
import com.erp.MtSalesOrderMasterTrading.Repository.IMtSalesOrderMasterTradingRepository;
import com.erp.RawMaterial.Product_Rm.Model.CSmProductDynamicParametersModel;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Service.CSmProductRmStockDetailsServiceImpl;
import com.erp.XtSpinningYarnPackingMaster.Model.CXtSpinningYarnPackingDetailsModel;
import com.erp.XtSpinningYarnPackingMaster.Repository.IXtSpinningYarnPackingDetailsRepository;
import com.erp.XtWeavingProductionInspectionMaster.Model.CXtWeavingProductionInspectionDetailsModel;
import com.erp.XtWeavingProductionInspectionMaster.Repository.IXtWeavingProductionInspectionDetailsRepository;
import com.erp.XtWeavingProductionInspectionMaster.Repository.IXtWeavingProductionInspectionDetailsViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CMtDispatchChallanDetailsServiceImpl implements IMtDispatchChallanDetailsService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IMtDispatchChallanMasterTradingRepository iMtDispatchChallanMasterTradingRepository;

    @Autowired
    IMtDispatchChallanDetailsTradingRepository iMtDispatchChallanDetailsTradingRepository;

    @Autowired
    IMtDispatchChallanDetailsTradingViewRepository iMtDispatchChallanDetailsTradingViewRepository;

    @Autowired
    IMtDispatchChallanBatchwiseTradingRepository iMtDispatchChallanBatchwiseTradingRepository;

    @Autowired
    IMtDispatchChallanBatchwiseTradingViewRepository iMtDispatchChallanBatchwiseTradingViewRepository;

    @Autowired
    IMtDispatchChallanTaxSummaryRepository iMtDispatchChallanTaxSummaryRepository;

    @Autowired
    IMtDispatchChallanTaxSummaryViewRepository iMtDispatchChallanTaxSummaryViewRepository;

    @Autowired
    IMtDispatchScheduleMasterTradingRepository iMtDispatchScheduleMasterTradingRepository;

    @Autowired
    IMtDispatchScheduleDetailsTradingRepository iMtDispatchScheduleDetailsTradingRepository;

    @Autowired
    IMtSalesOrderDetailsTradingViewRepository iMtSalesOrderDetailsTradingViewRepository;
    @Autowired
    IDtSizedYarnModelRepository iDtSizedYarnModelRepository;
    @Autowired
    IMtDispatchScheduleDetailsTradingViewRepository iMtDispatchScheduleDetailsTradingViewRepository;

    @Autowired
    CSmProductRmStockDetailsServiceImpl cSmProductRmStockDetailsServiceImpl;

    @Autowired
    IMtSalesOrderDetailsTradingRepository iMtSalesOrderDetailsTradingRepository;

    @Autowired
    IMtSalesOrderMasterTradingRepository iMtSalesOrderMasterTradingRepository;

    @Autowired
    IMtDispatchChallanMasterTradingViewRepository iMtDispatchChallanMasterTradingViewRepository;

    @Autowired
    IMtDispatchInspectionDetailsTradingRepository iMtDispatchInspectionDetailsTradingRepository;

    @Autowired
    IMtDispatchInspectionDetailsTradingViewRepository iMtDispatchInspectionDetailsTradingViewRepositor;
    @Autowired
    IXtWeavingProductionInspectionDetailsRepository iXtWeavingProductionInspectionDetailsRepository;
    @Autowired
    IXtWeavingProductionInspectionDetailsViewRepository iXtWeavingProductionInspectionDetailsViewRepository;
    @Autowired
    IMtDispatchChallanProductDynamicParametersRepository iMtDispatchChallanProductDynamicParametersRepository;
    @Autowired
    IMtDispatchChallanProductDynamicParametersViewRepository iMtDispatchChallanProductDynamicParametersViewRepository;
    @Autowired
    IMtDispatchChallanPackingDetailsRepository iMtDispatchChallanPackingDetailsRepository;
    @Autowired
    IMtDispatchChallanPackingDetailsViewRepository iMtDispatchChallanPackingDetailsViewRepository;
    @Autowired
    IMtDispatchSchedulePackingDetailsViewRepository iMtDispatchSchedulePackingDetailsViewRepository;
    @Autowired
    IMtDispatchScheduleProductDynamicParametersViewRepository iMtDispatchScheduleProductDynamicParametersViewRepository;
    @Autowired
    IXtSpinningYarnPackingDetailsRepository iXtSpinningYarnPackingDetailsRepository;
    @Autowired
    ISmProductFgRepository iSmProductFgRepository;
    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;
    @Autowired
    IDocumentsRepository iDocumentsRepository;
    @Autowired
    private JdbcTemplate executeQuery;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {

        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        boolean update = false;
        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String dispatch_challan_no = commonIdsObj.getString("dispatch_challan_no");
        String financial_year = commonIdsObj.getString("financial_year");

        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray batchwiseArray = (JSONArray) jsonObject.get("TransBatchwiseData");
        JSONArray taxSummaryArray = (JSONArray) jsonObject.get("TaxSummaryData");
        JSONArray inspectionDetailArray = (JSONArray) jsonObject.get("TransInspectionDetailData");
        JSONArray productParametersData = (JSONArray) jsonObject.get("TransProductParametersData");
        JSONArray dispChallanPackingDetails = (JSONArray) jsonObject.get("TransPackingDetailData");


        try {

            CMtDispatchChallanMasterTradingModel jsonModel = objectMapper.readValue(masterjson.toString(),
                    CMtDispatchChallanMasterTradingModel.class);

            String dispatch_challan_date = jsonModel.getDispatch_challan_date();

//			Dispatch Challan Master Trading
            CMtDispatchChallanMasterTradingModel cmtDispatchChallanMasterTradingModel = new CMtDispatchChallanMasterTradingModel();

            String query = "Select * FROM mt_dispatch_challan_master_trading WHERE is_delete = 0 and dispatch_challan_no = '"
                    + dispatch_challan_no + "' and dispatch_challan_version = "
                    + jsonModel.getDispatch_challan_version() + " and financial_year = '" + financial_year
                    + "' and company_id = " + company_id;

            System.out.println("Query For the Dispatch-Challan Versioning: " + query);
            List<CMtDispatchChallanMasterTradingModel> results = executeQuery.query(query,
                    new BeanPropertyRowMapper<>(CMtDispatchChallanMasterTradingModel.class));

            if (!results.isEmpty()) {
                update = true;
                cmtDispatchChallanMasterTradingModel = results.get(0);
                cmtDispatchChallanMasterTradingModel.setDeleted_on(new Date());
                cmtDispatchChallanMasterTradingModel.setIs_delete(true);
                iMtDispatchChallanMasterTradingRepository.save(cmtDispatchChallanMasterTradingModel);

                jsonModel.setDispatch_challan_version(cmtDispatchChallanMasterTradingModel.getDispatch_challan_version() + 1);
            }

            CMtDispatchChallanMasterTradingModel responceDispatchChallanMasterTrading = iMtDispatchChallanMasterTradingRepository
                    .save(jsonModel);

//			Dispatch Challan Details Trading	

            iMtDispatchChallanDetailsTradingRepository.updateStatus(dispatch_challan_no,
                    jsonModel.getDispatch_challan_version(), financial_year, company_id);

            List<CMtDispatchChallanDetailsTradingModel> cmtDispatchChallanDetailsTradingModel = objectMapper.readValue(
                    detailsArray.toString(), new TypeReference<List<CMtDispatchChallanDetailsTradingModel>>() {
                    });

            cmtDispatchChallanDetailsTradingModel.forEach(items -> {
                items.setDispatch_challan_version(jsonModel.getDispatch_challan_version());
                items.setDispatch_challan_master_transaction_id(responceDispatchChallanMasterTrading.getDispatch_challan_master_transaction_id());
            });


            Map<Integer, Map<String, Object>> setNoWiseDetails = IntStream.range(0, inspectionDetailArray.length())
                    .mapToObj(inspectionDetailArray::getJSONObject)
                    .collect(Collectors.groupingBy(
                            detail -> detail.getInt("inspection_production_set_no"), // Group by 'set_no'
                            Collectors.collectingAndThen(
                                    Collectors.toList(),
                                    details -> {
                                        Map<String, Object> setDetails = new HashMap<>();
//                                        setDetails.put("roll_count", details.stream().map(detail -> detail.getInt("roll_no")).collect(Collectors.toSet()).size()); // Count unique 'roll_no'
//                                        setDetails.put("dispatch_quantity", details.stream().mapToDouble(detail -> detail.getDouble("dispatch_quantity")).sum()); // Sum 'quantity' as double
//                                        setDetails.put("dispatch_weight", details.stream().mapToDouble(detail -> detail.getDouble("dispatch_weight")).sum()); // Sum 'weight' as double
                                        // Now group by sort_no-style inside this set_no
                                        Map<String, Map<String, Double>> sortNoDetails = details.stream()
                                                .collect(Collectors.groupingBy(
                                                        d -> {
                                                            String sortNo = d.optString("sort_no", "").trim();
                                                            String style = d.optString("style", "").trim();
                                                            return !style.isEmpty() ? sortNo + "-" + style : sortNo;
                                                        },
                                                        Collectors.collectingAndThen(
                                                                Collectors.toList(),
                                                                list -> {
                                                                    double totalQuantity = list.stream().mapToDouble(j -> j.optDouble("dispatch_quantity", 0)).sum();
                                                                    double totalWeight = list.stream().mapToDouble(j -> j.optDouble("dispatch_weight", 0)).sum();
                                                                    double count = list.size();
                                                                    Map<String, Double> map = new HashMap<>();
                                                                    map.put("dispatch_quantity", totalQuantity);
                                                                    map.put("dispatch_weight", totalWeight);

                                                                    map.put("roll_count", count);  // <-- added count
                                                                    return map;
                                                                }
                                                        )
                                                ));

                                        setDetails.put("sort_no_details", sortNoDetails);

                                        return setDetails;
                                    }
                            )
                    ));

            List<Map<String, Object>> result = setNoWiseDetails.entrySet().stream()
                    .map(entry -> {
                        Map<String, Object> resultMap = new HashMap<>();
                        resultMap.put("set_no", entry.getKey());
                        resultMap.putAll(entry.getValue());
                        return resultMap;
                    })
                    .collect(Collectors.toList());

            String dispatchChallanNo = commonIdsObj.getString("dispatch_challan_no").replaceAll("/", "_");
            List<String> groupIds = Collections.singletonList(dispatchChallanNo);
            iDocumentsRepository.updateDocActive(groupIds);
//			// Batch-Wise Stock Updatation logic. // Dispatch Challan Batchwise Trading.
            List<CMtDispatchChallanDetailsTradingModel> updatedMaterialDetails = iMtDispatchChallanDetailsTradingRepository
                    .saveAll(cmtDispatchChallanDetailsTradingModel);
            HashMap<Object, Object> materialDetailsObj = new HashMap<Object, Object>();
            materialDetailsObj.put("company_id", company_id);
            materialDetailsObj.put("materialDetails", cmtDispatchChallanDetailsTradingModel);
            materialDetailsObj.put("dispatch_roll", result);
            Map<String, Object> rmStockUpdatationResult = cSmProductRmStockDetailsServiceImpl.FnAddUpdateRecord(materialDetailsObj);

            List<CMtDispatchChallanBatchwiseTradingModel> batchWiseData = (List<CMtDispatchChallanBatchwiseTradingModel>) rmStockUpdatationResult
                    .get("BatchWiseStockData");
            batchWiseData.forEach(batchData -> {
                batchData.setDispatch_challan_version(jsonModel.getDispatch_challan_version());
                batchData.setDispatch_challan_master_transaction_id(responceDispatchChallanMasterTrading.getDispatch_challan_master_transaction_id());
            });
            iMtDispatchChallanBatchwiseTradingRepository.saveAll(batchWiseData);

            // Also update the details in sales-order.
            if (responceDispatchChallanMasterTrading.getDispatch_challan_creation_type().equals("O")) {
                // Then get the sales order details for update the previous dispatch qty.
                FnUpdateSODetails(cmtDispatchChallanDetailsTradingModel, company_id);    // update the sales order transaction details.

            } else if (responceDispatchChallanMasterTrading.getDispatch_challan_creation_type().equals("D")) {
                // Then get the dispatch schedule details for update the previous dispatch qty.
                FnUpdateDispatchScheduleDetails(cmtDispatchChallanDetailsTradingModel, company_id);    // First update the dispatch-schedule transaction details.
                FnUpdateSODetails(cmtDispatchChallanDetailsTradingModel, company_id);                    // Then update the sales order transaction details.
            }

            // Tax Summary
            if (!taxSummaryArray.isEmpty()) {
                iMtDispatchChallanTaxSummaryRepository.updateTaxSummary(dispatch_challan_no, masterjson.getInt("dispatch_challan_version"), company_id);

                List<CMtDispatchChallanTaxSummaryModel> dispatchChallanTaxSummary = objectMapper.readValue(
                        taxSummaryArray.toString(), new TypeReference<List<CMtDispatchChallanTaxSummaryModel>>() {
                        });
                dispatchChallanTaxSummary.forEach(taxSummary -> {
                    taxSummary.setDispatch_challan_version(jsonModel.getDispatch_challan_version());
                });
                iMtDispatchChallanTaxSummaryRepository.saveAll(dispatchChallanTaxSummary);
            }

            // Check if there are inspection details to process
            if (!inspectionDetailArray.isEmpty()) {
                // Update inspection details in the database
                iMtDispatchInspectionDetailsTradingRepository.updateInspectionDetails(dispatch_challan_no, masterjson.getInt("dispatch_challan_version"), company_id);

                // Deserialize inspection details from JSON
                List<CMtDispatchInspectionDetailsTradingModel> inspectionDetailsList = objectMapper.readValue(
                        inspectionDetailArray.toString(), new TypeReference<List<CMtDispatchInspectionDetailsTradingModel>>() {
                        });
                // Set additional details for each inspection detail
                inspectionDetailsList.forEach(inspDetails -> {
                    inspDetails.setDispatch_challan_version(jsonModel.getDispatch_challan_version());
                    inspDetails.setDispatch_challan_master_transaction_id(responceDispatchChallanMasterTrading.getDispatch_challan_master_transaction_id());
                });
                // Save all inspection details to the database
                iMtDispatchInspectionDetailsTradingRepository.saveAll(inspectionDetailsList);

                // Update inspectionlist stocks
                List<Integer> distinctInspDetailsIds = inspectionDetailsList.parallelStream()
                        .map(CMtDispatchInspectionDetailsTradingModel::getWeaving_production_inspection_details_id)
                        .distinct()
                        .collect(Collectors.toList());

                // Fetch product dispatch stock information
                List<CXtWeavingProductionInspectionDetailsModel> productDispatchStockList = distinctInspDetailsIds != null ?
                        iXtWeavingProductionInspectionDetailsRepository.getAllDispatchQtySummary(distinctInspDetailsIds, company_id) :
                        null;

                // Update dispatch details and statuses
                inspectionDetailsList.forEach(inspDetails -> {
                    String dispatchStatus = "";
                    String stock_status_description = "";
                    // Get previous dispatch quantity
                    Optional<CXtWeavingProductionInspectionDetailsModel> materialSummary = productDispatchStockList.stream()
                            .filter(matSummary -> inspDetails.getWeaving_production_inspection_details_id()
                                    .equals(matSummary.getWeaving_production_inspection_details_id()))
                            .findFirst();

                    if (materialSummary.isPresent()) {
                        CXtWeavingProductionInspectionDetailsModel foundedMatSummary = materialSummary.get();
                        double totalDispatchQty = foundedMatSummary.getDispatch_quantity() + inspDetails.getDispatch_quantity();
                        double totalDispatchWt = foundedMatSummary.getDispatch_weight() + inspDetails.getDispatch_weight();
                        String dispatchDate = inspDetails.getDispatch_challan_date();
                        if (foundedMatSummary.getInspection_mtr() > totalDispatchQty) {
                            dispatchStatus = "PD";    // for partial dispatch
                            stock_status_description = "Partial Dispatch";
                        } else if (foundedMatSummary.getInspection_mtr() == totalDispatchQty) {
                            dispatchStatus = "D";    // for Fully dispatch
                            stock_status_description = "Dispatched";
                        }
                        // Update dispatch details in the inspection details table
                        iXtWeavingProductionInspectionDetailsRepository.updateDispatchInspectionDetails(totalDispatchQty, dispatchStatus,
                                inspDetails.getWeaving_production_inspection_details_id(), inspDetails.getCompany_id(), dispatchDate, totalDispatchWt, dispatch_challan_no, stock_status_description);
                    } else {
                        // Handle case where material summary is not found
                        System.out.println("Material summary not found for inspection detail ID: " + inspDetails.getWeaving_production_inspection_details_id());
                    }
                });
            }

            // Check if there are product dynamic Parameters Data to process
            if (!productParametersData.isEmpty()) {

                iMtDispatchChallanProductDynamicParametersRepository.updateProductDynamicParameterDetails(dispatch_challan_no, masterjson.getInt("dispatch_challan_version"), company_id);

                // Deserialize inspection details from JSON
                List<CMtDispatchChallanProductDynamicParametersModel> dispatchChallanProductDynamicParametersModel = objectMapper.readValue(
                        productParametersData.toString(), new TypeReference<List<CMtDispatchChallanProductDynamicParametersModel>>() {
                        });

                dispatchChallanProductDynamicParametersModel.forEach(productParameters -> {
                    productParameters.setDispatch_challan_version(responceDispatchChallanMasterTrading.getDispatch_challan_version());
                    productParameters.setDispatch_challan_master_transaction_id(responceDispatchChallanMasterTrading.getDispatch_challan_master_transaction_id());

                });

                iMtDispatchChallanProductDynamicParametersRepository.saveAll(dispatchChallanProductDynamicParametersModel);
            }

            // Check if there are Dispatch Challan Packing Details Data to process
            if (!dispChallanPackingDetails.isEmpty()) {

                iMtDispatchChallanPackingDetailsRepository.updateDispatchChallanPackingDetails(dispatch_challan_no, masterjson.getInt("dispatch_challan_version"), company_id);

                // Deserialize Packing details from JSON
                List<CMtDispatchChallanPackingDetailsModel> dispatchChallanpackingDetailsList = objectMapper.readValue(
                        dispChallanPackingDetails.toString(), new TypeReference<List<CMtDispatchChallanPackingDetailsModel>>() {
                        });

                dispatchChallanpackingDetailsList.forEach(packingdetails -> {
                    packingdetails.setDispatch_challan_version(responceDispatchChallanMasterTrading.getDispatch_challan_version());
                    packingdetails.setDispatch_challan_master_transaction_id(responceDispatchChallanMasterTrading.getDispatch_challan_master_transaction_id());

                });

                // save dispatchChallanPackingDetailsModel
                List<CMtDispatchChallanPackingDetailsModel> packingdetailsList = iMtDispatchChallanPackingDetailsRepository.saveAll(dispatchChallanpackingDetailsList);

                // Update distinctPackingDetailsIds stocks
                List<Integer> distinctPackingDetailsIds = packingdetailsList.parallelStream()
                        .map(CMtDispatchChallanPackingDetailsModel::getDispatch_challan_packing_id)
                        .distinct()
                        .collect(Collectors.toList());

                // Fetch product dispatch stock information
                List<CMtDispatchChallanPackingDetailsModel> productDispatchPackingList = distinctPackingDetailsIds != null ?
                        iMtDispatchChallanPackingDetailsRepository.getAllDispatchQtySummary(distinctPackingDetailsIds, company_id) :
                        null;

                // Update dispatch details and statuses
                productDispatchPackingList.forEach(packingdetails -> {
                    String dispatchStatus = "";

                    // Get previous dispatch quantity
                    Optional<CMtDispatchChallanPackingDetailsModel> materialSummary = productDispatchPackingList.stream()
                            .filter(matSummary -> packingdetails.getDispatch_challan_packing_id()
                                    .equals(matSummary.getDispatch_challan_packing_id()))
                            .findFirst();

                    if (materialSummary.isPresent()) {
                        CMtDispatchChallanPackingDetailsModel foundedMatSummary = materialSummary.get();
                        double totalDispatchQty = foundedMatSummary.getItem_dispatch_quantity() + packingdetails.getItem_dispatch_quantity();
                        if (foundedMatSummary.getPacking_quantity() > totalDispatchQty) {
                            dispatchStatus = "PD";    // for partial dispatch
                        } else if (foundedMatSummary.getPacking_quantity() == totalDispatchQty) {
                            dispatchStatus = "D";    // for Fully dispatch
                        }

                        // Update dispatch details in the yarn packing details table
                        iXtSpinningYarnPackingDetailsRepository.updateDispatchPackingDetails(totalDispatchQty, dispatchStatus,
                                packingdetails.getYarn_packing_details_id(), packingdetails.getCompany_id());
                    } else {
                        // Handle case where material summary is not found
                        System.out.println("Material summary not found for packing detail Id: " + packingdetails.getDispatch_challan_packing_id());
                    }
                });

            }


            responce.put("success", 1);
            responce.put("data", responceDispatchChallanMasterTrading);
            responce.put("error", "");
            responce.put("stockResponse", rmStockUpdatationResult);
            responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/MtDispatchChallanDetails/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/MtDispatchChallanDetails/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;

    }

    @Override
    public Map<String, Object> FnDispatchChallanDetailsUpdateRecord(JSONObject jsonObject) {

        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String dispatch_challan_no = commonIdsObj.getString("dispatch_challan_no");
        String financial_year = commonIdsObj.getString("financial_year");

        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");

        try {

            List<CMtDispatchChallanDetailsTradingModel> cmtDispatchChallanDetailsTradingModel = objectMapper.readValue(
                    detailsArray.toString(), new TypeReference<List<CMtDispatchChallanDetailsTradingModel>>() {
                    });

            iMtDispatchChallanDetailsTradingRepository.saveAll(cmtDispatchChallanDetailsTradingModel);

            responce.put("success", "1");
            responce.put("data", cmtDispatchChallanDetailsTradingModel);
            responce.put("error", "");
            responce.put("message", "Record updates successfully!...");
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/MtDispatchChallanDetails/FnDispatchChallanDetailsUpdateRecord", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/MtDispatchChallanDetails/FnDispatchChallanDetailsUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnDeleteRecord(String dispatch_challan_no, int dispatch_challan_version, int company_id, String deletedBy) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iMtDispatchChallanMasterTradingRepository.deleteChallanMaster(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);
            iMtDispatchChallanDetailsTradingRepository.deleteChallanDetails(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);
            iMtDispatchChallanBatchwiseTradingRepository.deleteChallanBatchWise(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);
            iMtDispatchChallanTaxSummaryRepository.deleteChallanTaxSummary(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);
            iMtDispatchInspectionDetailsTradingRepository.deleteInspectionDetails(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);
            iMtDispatchChallanProductDynamicParametersRepository.deleteProductDynamicParameters(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);
            iMtDispatchChallanPackingDetailsRepository.deletePackingDetails(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);

            responce.put("success", 1);
            responce.put("data", "");
            responce.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", "");
        }
        return responce;
    }


    @Override
    public Page<CMtDispatchChallanDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
        return iMtDispatchChallanDetailsTradingViewRepository.FnShowAllActiveRecords(pageable, company_id);

    }

    @Override
    public Page<CMtDispatchChallanDetailsTradingViewModel> FnShowParticularRecord(
            int dispatch_challan_details_transaction_id, Pageable pageable, int company_id) {
        return iMtDispatchChallanDetailsTradingViewRepository
                .FnShowParticularRecord(dispatch_challan_details_transaction_id, pageable, company_id);

    }

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String dispatch_challan_no,
                                                                     int dispatch_challan_version, String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            CMtDispatchChallanMasterTradingViewModel dispatchChallanMasterRecords = iMtDispatchChallanMasterTradingViewRepository
                    .FnShowDispatchChallanMasterRecord(dispatch_challan_no, dispatch_challan_version, financial_year, company_id);

            List<Map<String, Object>> dispatchChallanDetailsRecords = iMtDispatchChallanDetailsTradingRepository
                    .FnShowDispatchChallanDetailsRecords(dispatch_challan_no, dispatch_challan_version, financial_year, company_id);

            List<CMtDispatchChallanBatchwiseTradingViewModel> dispatchChallanBatchwiseRecords = iMtDispatchChallanBatchwiseTradingViewRepository
                    .FnShowDispatchChallanBatchwiseRecords(dispatch_challan_no, dispatch_challan_version, company_id);

//            List<CMtDispatchChallanTaxSummaryViewModel> dispatchChallanTaxSummaryRecords = iMtDispatchChallanTaxSummaryViewRepository
//                    .FnShowDispatchChallanTaxSummaryRecords(dispatch_challan_no, dispatch_challan_version, company_id);

            List<CMtDispatchInspectionDetailsTradingViewModel> dispatchInspectionDetailsRecords = iMtDispatchInspectionDetailsTradingViewRepositor
                    .FnShowInspectionDetailsTradingRecords(dispatch_challan_no, dispatch_challan_version, company_id);

//            List<CMtDispatchChallanProductDynamicParametersViewModel> dispatchChallanProductDynamicParametersRecords = iMtDispatchChallanProductDynamicParametersViewRepository
//                    .FnShowProductParametersRecords(dispatch_challan_no, dispatch_challan_version, company_id);


//            List<CMtDispatchChallanPackingDetailsViewModel> dispatchChallanPackingDetailsRecord = iMtDispatchChallanPackingDetailsViewRepository
//                    .FnShowDispatchChallanPackingDetailsRecords(dispatch_challan_no, dispatch_challan_version, company_id);

            responce.put("DispatchChallanMasterRecord", dispatchChallanMasterRecords);
            responce.put("DispatchChallanDetailsRecords", dispatchChallanDetailsRecords);
            responce.put("DispatchChallanBatchwiseRecords", dispatchChallanBatchwiseRecords);
//            responce.put("DispatchChallanTaxSummaryRecords", dispatchChallanTaxSummaryRecords);
            responce.put("DispatchInspectionDetailsRecords", dispatchInspectionDetailsRecords);
//            responce.put("DispatchChallanProductDynamicParametersRecords", dispatchChallanProductDynamicParametersRecords);
//            responce.put("DispatchChallanPackingDetailsRecord", dispatchChallanPackingDetailsRecord);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
        Map<String, Object> response = new HashMap<>();
        if ("summary".equals(reportType)) {
            Map<String, Object> results = iMtDispatchChallanMasterTradingViewRepository.FnShowAllSummaryReportRecords();
            response.put("content", results);
        } else {
            Map<String, Object> results =
                    iMtDispatchChallanDetailsTradingViewRepository.FnShowAlldetailsReportRecords();
            response.put("content", results);
        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowDispatchScheduleMasterNos(JSONObject customerOrderNo, Pageable pageable,
                                                               int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {

            JSONArray customerOrderNos = customerOrderNo.getJSONArray("cust_order_nos");
            // Convert JSONArray to List<String>
            List<String> customerOrderNosList = customerOrderNos.toList().stream().map(Object::toString)
                    .collect(Collectors.toList());

            List<Object[]> dispatchScheduleDetailsTrading = iMtDispatchScheduleDetailsTradingRepository
                    .FnShowDispatchScheduleMasterNos(company_id, customerOrderNosList);
            response.put("data", dispatchScheduleDetailsTrading);
            response.put("success", "1");
            response.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "0");
            response.put("data", "");
            response.put("error", "");
        }
        return response;

    }

    @Override
    public Map<String, Object> FnShowSalesOrderDetails(JSONObject customerOrderNo, Pageable pageable, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {
            JSONArray customerOrderNos = customerOrderNo.getJSONArray("cust_order_nos");
            List<String> customerOrderNosList = customerOrderNos.toList().stream().map(Object::toString)
                    .collect(Collectors.toList());

//            List<CMtSalesOrderDetailsTradingViewModel> salesOrderDetailsTradingRecords = iMtSalesOrderDetailsTradingViewRepository
//                    .FnShowSalesOrderDetails(company_id, customerOrderNosList);

            List<Map<String, Object>> salesOrderDetailsTradingRecords = iMtSalesOrderDetailsTradingViewRepository
                    .FnShowSalesOrderDetails(customerOrderNosList);
            List<String> customerOrderNoList = IntStream.range(0, customerOrderNos.length())
                    .mapToObj(i -> customerOrderNos.getString(i))
                    .collect(Collectors.toList());

            List<String> paymentTerms = iDtSizedYarnModelRepository.findPaymentTermsByCustomerOrderNos(customerOrderNoList);

            response.put("data", salesOrderDetailsTradingRecords);
            response.put("paymentTerms", paymentTerms);

            response.put("success", "1");
            response.put("error", "");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", "0");
            response.put("data", "");
            response.put("error", "");
        }
        return response;
    }

    @Override
    public Map<String, Object> FnShowDispatchScheduleDetails(JSONObject dispatchScheduleNo, Pageable pageable,
                                                             int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {

            // Extracting the JSONArray named "dispatch_schedule_nos" from dispatchScheduleNo
            JSONArray dispatchScheduleNos = dispatchScheduleNo.getJSONArray("dispatch_schedule_nos");

            // Converting JSONArray to a List of Strings
            List<String> dispatchScheduleNosList = dispatchScheduleNos.toList().stream().map(Object::toString)
                    .collect(Collectors.toList());

            List<CMtDispatchScheduleDetailsTradingViewModel> dispatchScheduleDetailsTradingRecords = iMtDispatchScheduleDetailsTradingViewRepository
                    .FnShowDispatchScheduleDetails(company_id, dispatchScheduleNosList);

            // Fetching dispatch schedule packing details based on the list of dispatch schedule numbers
            List<CMtDispatchSchedulePackingDetailsViewModel> dispatchSchedulePackingDetailsRecord =
                    iMtDispatchSchedulePackingDetailsViewRepository.FnShowdispatchSchedulePackingDetails(dispatchScheduleNosList, company_id);

            //Fetch Dispatch Schedule Product Dynamic Parameters Record for update
            List<CMtDispatchScheduleProductDynamicParametersViewModel> productDynamicParametersRecord =
                    iMtDispatchScheduleProductDynamicParametersViewRepository.FnShowDispatchSchedulePropertiesParametersRecords(dispatchScheduleNosList, company_id);

            response.put("data", dispatchScheduleDetailsTradingRecords);
            response.put("DispatchSchedulePackingDetailsRecord", dispatchSchedulePackingDetailsRecord);
            response.put("DispatchSchedulePropertiesDetailsRecord", productDynamicParametersRecord);
            response.put("success", 1);
            response.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", "");
        }
        return response;
    }

    private void FnUpdateSODetails(List<CMtDispatchChallanDetailsTradingModel> dispatchChallanDetails, int company_id) {
        String detailModifiedBy = dispatchChallanDetails.get(0).getCreated_by();

        // First find the distinct sales-order-nos.
        List<String> distinctSalesOrderNos = dispatchChallanDetails.parallelStream()
                .map(CMtDispatchChallanDetailsTradingModel::getSales_order_no).distinct()
                .collect(Collectors.toList());
        System.out.println("-- Updating the Sales Orders Details after dispatch-challan: ");
        distinctSalesOrderNos.forEach(System.out::println);

        // get all sales-orders details as per the dispatch-challan details material.s
        List<CMtSalesOrderDetailsTradingModel> salesOrdersDetailsFromDB = iMtSalesOrderDetailsTradingRepository.FnShowSalesOrderDetailsBySONos(company_id, distinctSalesOrderNos);
        List<CMtSalesOrderDetailsTradingModel> updatedSalesOrderDetails = new ArrayList<CMtSalesOrderDetailsTradingModel>();

        List<CMtDispatchChallanDetailsTradingModel> updatedChallanDetails = new ArrayList<CMtDispatchChallanDetailsTradingModel>();

        // Iterate the loop on the distinct_sales order nos.
        distinctSalesOrderNos.forEach(SalesOrderNo -> {
            // From salesOrdersDetailsFromDB(means received data from sales_order table) collect only the the current SalesOrderNo related details.
            List<CMtSalesOrderDetailsTradingModel> currentSONoDetailsFromDB = salesOrdersDetailsFromDB.stream()
                    .filter(soMaterial -> SalesOrderNo.equals(soMaterial.getSales_order_no()))
                    .collect(Collectors.toList());

            // From dispatch-challan materials collect all the challan-details having sales_order_no. current.
            List<CMtDispatchChallanDetailsTradingModel> dispatchChallanDetailsSoBased = dispatchChallanDetails.stream()
                    .filter(dispatchMat -> SalesOrderNo.equals(dispatchMat.getSales_order_no()))
                    .collect(Collectors.toList());

            // Now iterate the loop on the dispatch-material and find the so_sr_no and product_rm_id matching object.
            dispatchChallanDetailsSoBased.forEach(challanMaterial -> {
                String product_material_id = challanMaterial.getProduct_material_id();
                String product_material_so_sr_no = challanMaterial.getSo_sr_no();
                Optional<CMtSalesOrderDetailsTradingModel> matchingSODetailObjs = currentSONoDetailsFromDB.stream()
                        .filter(soDetail -> product_material_id.equals(soDetail.getProduct_material_id()) && product_material_so_sr_no.equals(soDetail.getSo_sr_no()))
                        .findFirst();

                if (matchingSODetailObjs.isPresent()) {
                    CMtSalesOrderDetailsTradingModel matchedSoDetail = matchingSODetailObjs.get();
                    Double updatedSOQty = matchedSoDetail.getSales_quantity() + challanMaterial.getDispatch_quantity();
                    Double updatedSOWt = matchedSoDetail.getSales_weight() + challanMaterial.getDispatch_weight();
                    // Update the sales-order sales details.
                    matchedSoDetail.setSales_quantity(updatedSOQty);
                    matchedSoDetail.setSales_weight(updatedSOWt);
                    matchedSoDetail.setPending_quantity(matchedSoDetail.getMaterial_quantity() - updatedSOQty);
                    matchedSoDetail.setPending_weight(matchedSoDetail.getMaterial_weight() - updatedSOWt);
                    matchedSoDetail.setModified_by(detailModifiedBy);

                    // Update the So_details item_status.
                    if (challanMaterial.isPree_close_flag()) {
                        matchedSoDetail.setPree_closed_quantity(matchedSoDetail.getMaterial_quantity() - matchedSoDetail.getSales_quantity());
                        matchedSoDetail.setPree_closed_weight(matchedSoDetail.getMaterial_weight() - matchedSoDetail.getSales_weight());
                        matchedSoDetail.setSales_order_item_status("Z");
                        System.out.println("Updated SoDetails For SODetailId: " + matchedSoDetail.getSales_order_details_transaction_id() + " Pree-Close Qty: " + matchedSoDetail.getPree_closed_quantity());

                        challanMaterial.setPree_close_quantity(matchedSoDetail.getPree_closed_quantity());
                        updatedChallanDetails.add(challanMaterial);
                    } else if (matchedSoDetail.getSales_quantity() < matchedSoDetail.getMaterial_quantity()) {
                        matchedSoDetail.setSales_order_item_status("I");
                        System.out.println("Updated SoDetails For SODetailId: " + matchedSoDetail.getSales_order_details_transaction_id() + " Sales Qty: " + matchedSoDetail.getSales_quantity() + " Sales Wt: " + matchedSoDetail.getSales_weight());

                    } else if (matchedSoDetail.getSales_quantity() == matchedSoDetail.getMaterial_quantity()) {
                        matchedSoDetail.setSales_order_item_status("C");
                        System.out.println("Updated SoDetails For SODetailId: " + matchedSoDetail.getSales_order_details_transaction_id() + " Sales Qty: " + matchedSoDetail.getSales_quantity() + " Sales Wt: " + matchedSoDetail.getSales_weight());

                    }

                    // add the so_details in the list for update the so-details.
                    updatedSalesOrderDetails.add(matchedSoDetail);
                }
            });
        });

        if (!updatedChallanDetails.isEmpty()) {
            iMtDispatchChallanDetailsTradingRepository.saveAll(updatedChallanDetails);
        }

        // Update the sales_order_details.
        iMtSalesOrderDetailsTradingRepository.saveAll(updatedSalesOrderDetails);
        System.out.println("Updated the SalesOrder Details. Now updating the so-master.");

//		----------------- For Update the Sales-order master status.
        // get all sales-order details after details updatation.
        List<CMtSalesOrderDetailsTradingModel> salesOrdersDetailsFromDBAfterUpdate = iMtSalesOrderDetailsTradingRepository.FnShowSalesOrderDetailsBySONos(company_id, distinctSalesOrderNos);

        // get all sales-order masters records for distinctSoNos.
        List<CMtSalesOrderMasterTradingModel> salesOrdersMasters = iMtSalesOrderMasterTradingRepository.FnShowSalesOrdersBySONos(company_id, distinctSalesOrderNos);

        // To Store the all sales-order-master records for update.
        List<CMtSalesOrderMasterTradingModel> salesOrdersMastersForUpdate = new ArrayList<CMtSalesOrderMasterTradingModel>();

        // Iterate the loop on sales-order master records.
        salesOrdersMasters.forEach(salesOrderMst -> {
            String salesOrderNo = salesOrderMst.getSales_order_no();
            // First from salesOrdersDetailsFromDBAfterUpdate get all details and compare all status.
            List<CMtSalesOrderDetailsTradingModel> currentSalesOrderDetails = salesOrdersDetailsFromDBAfterUpdate.stream()
                    .filter(soDetail -> salesOrderNo.equals(soDetail.getSales_order_no()))
                    .collect(Collectors.toList());

            // Collect all itemStatus values into a List
            List<String> SOItemStatusList = currentSalesOrderDetails.stream()
                    .map(CMtSalesOrderDetailsTradingModel::getSales_order_item_status)
                    .collect(Collectors.toList());

            boolean allMaterialStatusIsCompleted = SOItemStatusList.stream().allMatch(status -> status.equals("C"));
            boolean allMaterialStatusIsPreeClosed = SOItemStatusList.stream().allMatch(status -> status.equals("Z"));

            if (allMaterialStatusIsPreeClosed) {
                salesOrderMst.setSales_order_status("Z");
            } else if (allMaterialStatusIsCompleted) {
                salesOrderMst.setSales_order_status("C");
            } else {
                salesOrderMst.setSales_order_status("I");
            }
            salesOrderMst.setModified_by(detailModifiedBy);
            salesOrdersMastersForUpdate.add(salesOrderMst);            // Add that updated object in the list.
        });
        // Update the sales_order_masters.
        iMtSalesOrderMasterTradingRepository.saveAll(salesOrdersMastersForUpdate);
        System.out.println("Updated the sales-order master status.");
    }

    // Reverse the quantity and weight and status after canceling the dispatch-challan.
    private void FnReverseUpdateSODetails(List<CMtDispatchChallanDetailsTradingModel> dispatchChallanDetails, int company_id, String detailModifiedBy) {
        // First find the distinct sales-order-nos.
        List<String> distinctSalesOrderNos = dispatchChallanDetails.parallelStream()
                .map(CMtDispatchChallanDetailsTradingModel::getSales_order_no).distinct()
                .collect(Collectors.toList());

        System.out.println("\n-- Reverse Updating the Sales Orders Details after dispatch-challan: ");
        distinctSalesOrderNos.forEach(System.out::println);

        // get all sales-order masters records for distinctSoNos.
        List<CMtSalesOrderMasterTradingModel> salesOrdersMasters = iMtSalesOrderMasterTradingRepository.FnShowSalesOrdersBySONos(company_id, distinctSalesOrderNos);
        List<CMtSalesOrderMasterTradingModel> salesOrdersMastersForUpdate = new ArrayList<CMtSalesOrderMasterTradingModel>();

        // get all sales-orders details as per the dispatch-challan details material.s
        List<CMtSalesOrderDetailsTradingModel> salesOrdersDetailsFromDB = iMtSalesOrderDetailsTradingRepository.FnShowSalesOrderDetailsBySONos(company_id, distinctSalesOrderNos);
        List<CMtSalesOrderDetailsTradingModel> updatedSalesOrderDetails = new ArrayList<CMtSalesOrderDetailsTradingModel>();

        // Iterate the loop on the distinct_sales order nos.
        distinctSalesOrderNos.forEach(SalesOrderNo -> {
            // From salesOrdersDetailsFromDB(means received data from sales_order table) collect only the the current SalesOrderNo related details.
            List<CMtSalesOrderDetailsTradingModel> currentSONoDetailsFromDB = salesOrdersDetailsFromDB.stream()
                    .filter(soMaterial -> SalesOrderNo.equals(soMaterial.getSales_order_no()))
                    .collect(Collectors.toList());

            // From dispatch-challan materials collect all the challan-details having sales_order_no. current.
            List<CMtDispatchChallanDetailsTradingModel> dispatchChallanDetailsSoBased = dispatchChallanDetails.stream()
                    .filter(dispatchMat -> SalesOrderNo.equals(dispatchMat.getSales_order_no()))
                    .collect(Collectors.toList());

            // Now iterate the loop on the dispatch-material and find the so_sr_no and product_rm_id matching object. UPDATING THE SO-DETAILS
            dispatchChallanDetailsSoBased.forEach(challanMaterial -> {
                String product_material_id = challanMaterial.getProduct_material_id();
                String product_material_so_sr_no = challanMaterial.getSo_sr_no();
                Optional<CMtSalesOrderDetailsTradingModel> matchingSODetailObjs = currentSONoDetailsFromDB.stream()
                        .filter(soDetail -> product_material_id.equals(soDetail.getProduct_material_id()) && product_material_so_sr_no.equals(soDetail.getSo_sr_no()))
                        .findFirst();

                if (matchingSODetailObjs.isPresent()) {
                    CMtSalesOrderDetailsTradingModel matchedSoDetail = matchingSODetailObjs.get();
                    Double updatedSOQty = matchedSoDetail.getSales_quantity() - challanMaterial.getDispatch_quantity();
                    Double updatedSOWt = matchedSoDetail.getSales_weight() - challanMaterial.getDispatch_weight();
                    // Update the sales-order sales details.
                    matchedSoDetail.setSales_quantity(updatedSOQty);
                    matchedSoDetail.setSales_weight(updatedSOWt);
                    matchedSoDetail.setPending_quantity(matchedSoDetail.getPending_quantity() - challanMaterial.getDispatch_quantity());
                    matchedSoDetail.setPending_weight(matchedSoDetail.getPending_weight() - challanMaterial.getDispatch_weight());
                    matchedSoDetail.setModified_by(detailModifiedBy);
                    // if material_status is pree-closed then don't update anything.
                    if (!"Z".equals(matchedSoDetail.getSales_order_item_status())) {
                        matchedSoDetail.setSales_order_item_status("I");
                    } else if (matchedSoDetail.getSales_quantity() == 0) {
                        matchedSoDetail.setSales_order_item_status("A");
                    }

                    // add the so_details in the list for update the so-details.
                    System.out.println("Updated SoDetails For SODetailId: " + matchedSoDetail.getSales_order_details_transaction_id() + " Sales Qty: " + matchedSoDetail.getSales_quantity() + " Sales Wt: " + matchedSoDetail.getSales_weight());
                    updatedSalesOrderDetails.add(matchedSoDetail);
                }
            });

            // First collect all details status releated to currentSoNo.  UPDATING THE SO-MASTER.
            List<String> soDetailsStatusList = currentSONoDetailsFromDB.stream()
                    .map(CMtSalesOrderDetailsTradingModel::getSales_order_item_status)
                    .collect(Collectors.toList());

            Optional<CMtSalesOrderMasterTradingModel> matchingSOMstObjs = salesOrdersMastersForUpdate.stream()
                    .filter(soMst -> SalesOrderNo.equals(soMst.getSales_order_no()))
                    .findFirst();
            if (matchingSOMstObjs.isPresent()) {
                CMtSalesOrderMasterTradingModel updatedSoMst = matchingSOMstObjs.get();
                // Check all materials are pree-closed.
                boolean allMaterialStatusIsPreeClosed = soDetailsStatusList.stream().allMatch(status -> status.equals("Z"));
                if (allMaterialStatusIsPreeClosed) {
                    updatedSoMst.setSales_order_status("Z");
                } else {
                    updatedSoMst.setSales_order_status("I");
                }
                updatedSoMst.setModified_by(detailModifiedBy);
                salesOrdersMastersForUpdate.add(updatedSoMst);
            }
        });

        // Update the sales_order_details.
        iMtSalesOrderDetailsTradingRepository.saveAll(updatedSalesOrderDetails);
        System.out.println("Updated the SalesOrder Details. Now updating the so-master.");

//		----------------- For Update the Sales-order master status.	
//		// Iterate the loop on sales-order master records.
//		salesOrdersMasters.forEach(salesOrderMst -> {
//	        salesOrderMst.setSales_order_status("I");
//        	salesOrderMst.setModified_by(detailModifiedBy);
//	        salesOrdersMastersForUpdate.add(salesOrderMst);			// Add that updated object in the list.
//	    });
        // Update the sales_order_masters.
        iMtSalesOrderMasterTradingRepository.saveAll(salesOrdersMastersForUpdate);
        System.out.println("Reverse Updated the sales-order master status.");
    }

    private void FnUpdateDispatchScheduleDetails(List<CMtDispatchChallanDetailsTradingModel> dispatchChallanDetails, int company_id) {
        String detailModifiedBy = dispatchChallanDetails.get(0).getCreated_by();

        // First find the distinct dispatch-schedule-nos.
        List<String> distinctDispScheduleNos = dispatchChallanDetails.parallelStream()
                .map(CMtDispatchChallanDetailsTradingModel::getDispatch_schedule_no).distinct()
                .collect(Collectors.toList());

        // List to store the updated dispatch-schedule-Details.
        List<CMtDispatchScheduleDetailsTradingModel> updatedDispScheduleDetails = new ArrayList<CMtDispatchScheduleDetailsTradingModel>();

        // get all dipsatch_schedules details as per the dispatch-challan details material.s
        List<CMtDispatchScheduleDetailsTradingModel> dispSchedulesDetailsFromDB = iMtDispatchScheduleDetailsTradingRepository.FnShowDispatchScheduleDetailsByDispScheduleNos(company_id, distinctDispScheduleNos);

        // Iterate the loop on the distinct_dispatch schedule nos.
        distinctDispScheduleNos.forEach(dispatchScheduleNo -> {
            // From dispSchedulesDetailsFromDB(means received data from dispatch_schedule_details table) collect only the the current DispatchScheduleNo related details.
            List<CMtDispatchScheduleDetailsTradingModel> currentDispSchNoDetailsFromDB = dispSchedulesDetailsFromDB.stream()
                    .filter(dispSchMat -> dispatchScheduleNo.equals(dispSchMat.getDispatch_schedule_no()))
                    .collect(Collectors.toList());

            // From dispatch-challan materials collect all the challan-details having DispatchScheduleNo. current.
            List<CMtDispatchChallanDetailsTradingModel> dispatchChallanDetailsDispSchBased = dispatchChallanDetails.stream()
                    .filter(dispatchMat -> dispatchScheduleNo.equals(dispatchMat.getDispatch_schedule_no()))
                    .collect(Collectors.toList());

            // Now iterate the loop on the dispatch-material and find the so_sr_no and product_rm_id matching object.
            dispatchChallanDetailsDispSchBased.forEach(challanMaterial -> {
                String product_material_id = challanMaterial.getProduct_material_id();
                Optional<CMtDispatchScheduleDetailsTradingModel> matchingDispSchDetailObjs = currentDispSchNoDetailsFromDB.stream()
                        .filter(dispSchDetail -> product_material_id.equals(dispSchDetail.getProduct_material_id()))
                        .findFirst();

                if (matchingDispSchDetailObjs.isPresent()) {
                    CMtDispatchScheduleDetailsTradingModel matchedDispScheDetail = matchingDispSchDetailObjs.get();
                    Double updatedDispQty = matchedDispScheDetail.getDispatched_quantity() + challanMaterial.getDispatch_quantity();
                    Double updatedDispWt = matchedDispScheDetail.getDispatched_weight() + challanMaterial.getDispatch_weight();
                    // Update the dispatch-schedule dispatched details.
                    matchedDispScheDetail.setDispatched_quantity(updatedDispQty);
                    matchedDispScheDetail.setDispatched_weight(updatedDispWt);
                    matchedDispScheDetail.setModified_by(detailModifiedBy);
                    Double actualQty = matchedDispScheDetail.getActual_dispatch_quantity();
//                    matchedDispScheDetail.setDispatch_schedule_item_status("C");
                    // Update the DispatchScheduleDetails item_status.
                    if (updatedDispQty != null && actualQty != null) {
                        double lowerLimit = actualQty * 0.9;
                        double upperLimit = actualQty * 1.1;

//                        if (updatedDispQty >= lowerLimit && updatedDispQty <= upperLimit) {
                        if (updatedDispQty >= lowerLimit) {
                            matchedDispScheDetail.setDispatch_schedule_item_status("C");
                        } else {
                            matchedDispScheDetail.setDispatch_schedule_item_status("I");
                        }
                    }
//                    if (updatedDispQty != null && actualQty != null && updatedDispQty.equals(actualQty)) {
////                    if (matchedDispScheDetail.getDispatched_quantity() == matchedDispScheDetail.getActual_dispatch_quantity()) {
//                        matchedDispScheDetail.setDispatch_schedule_item_status("C");
//                    } else {
//                        matchedDispScheDetail.setDispatch_schedule_item_status("I");
//                    }
//					else if(matchedDispScheDetail.getDispatched_quantity() < matchedDispScheDetail.getActual_dispatch_quantity()){
//						matchedDispScheDetail.setDispatch_schedule_item_status("I");
//					}
                    // add the updatedDispScheduleDetails in the list for update the DispatchScheduleDetails.
                    updatedDispScheduleDetails.add(matchedDispScheDetail);
                }
            });
        });

        // Update the dispatch_schedule_details.
        iMtDispatchScheduleDetailsTradingRepository.saveAll(updatedDispScheduleDetails);

//				----------------- For Update the Dispatch-Schedule master status.--------------------
        // get all dispatch-schedule details after details updatation.
        List<CMtDispatchScheduleDetailsTradingModel> dispSchedulesDetailsFromDBAfterUpdate = iMtDispatchScheduleDetailsTradingRepository.FnShowDispatchScheduleDetailsByDispScheduleNos(company_id, distinctDispScheduleNos);

        // get all dispatch-schedule masters records for distinctDispScheduleNos.
        List<CMtDispatchScheduleMasterTradingModel> dispSchedulesMasters = iMtDispatchScheduleMasterTradingRepository.FnShowDispatchSchedulesByDispSchNos(company_id, distinctDispScheduleNos);

        // To Store the all sales-order-master records for update.
        List<CMtDispatchScheduleMasterTradingModel> dispSchedulesMastersForUpdate = new ArrayList<CMtDispatchScheduleMasterTradingModel>();

        // Iterate the loop on sales-order master records.
        dispSchedulesMasters.forEach(dispSchMst -> {
            String dispScheduleNo = dispSchMst.getDispatch_schedule_no();
            // First from dispSchedulesDetailsFromDBAfterUpdate get all details and compare all status.
            List<CMtDispatchScheduleDetailsTradingModel> currentDispScheduleDetails = dispSchedulesDetailsFromDBAfterUpdate.stream()
                    .filter(soDetail -> dispScheduleNo.equals(dispSchMst.getDispatch_schedule_no()))
                    .collect(Collectors.toList());

            // Collect all itemStatus values into a List
            List<String> dispScheItemStatusList = currentDispScheduleDetails.stream()
                    .map(CMtDispatchScheduleDetailsTradingModel::getDispatch_schedule_item_status)
                    .collect(Collectors.toList());

            boolean allMaterialStatusIsCompleted = dispScheItemStatusList.stream().allMatch(status -> status.equals("C"));
            if (allMaterialStatusIsCompleted) {
                dispSchMst.setDispatch_note_status("C");
            } else {
                dispSchMst.setDispatch_note_status("I");
            }
//            dispSchMst.setDispatch_note_status("C");
            dispSchMst.setModified_by(detailModifiedBy);
            dispSchedulesMastersForUpdate.add(dispSchMst);            // Add that updated object in the list.
        });
        // Update the dispatch_schedule_masters.
        iMtDispatchScheduleMasterTradingRepository.saveAll(dispSchedulesMastersForUpdate);
    }

    // This will be called when dispatch-challan cancelled.
    private void FnReverseUpdateDispatchScheduleDetails(List<CMtDispatchChallanDetailsTradingModel> dispatchChallanDetails, int company_id, String detailModifiedBy) {
        // First find the distinct dispatch-schedule-nos.
        List<String> distinctDispScheduleNos = dispatchChallanDetails.parallelStream()
                .map(CMtDispatchChallanDetailsTradingModel::getDispatch_schedule_no).distinct()
                .collect(Collectors.toList());

        // get all dipsatch_schedules details as per the dispatch-challan details material.s
        List<CMtDispatchScheduleDetailsTradingModel> dispSchedulesDetailsFromDB = iMtDispatchScheduleDetailsTradingRepository.FnShowDispatchScheduleDetailsByDispScheduleNos(company_id, distinctDispScheduleNos);
        List<CMtDispatchScheduleDetailsTradingModel> updatedDispScheduleDetails = new ArrayList<CMtDispatchScheduleDetailsTradingModel>();

        // Iterate the loop on the distinct_dispatch schedule nos.
        distinctDispScheduleNos.forEach(dispatchScheduleNo -> {
            // From dispSchedulesDetailsFromDB(means received data from dispatch_schedule_details table) collect only the the current DispatchScheduleNo related details.
            List<CMtDispatchScheduleDetailsTradingModel> currentDispSchNoDetailsFromDB = dispSchedulesDetailsFromDB.stream()
                    .filter(dispSchMat -> dispatchScheduleNo.equals(dispSchMat.getDispatch_schedule_no()))
                    .collect(Collectors.toList());

            // From dispatch-challan materials collect all the challan-details having DispatchScheduleNo. current.
            List<CMtDispatchChallanDetailsTradingModel> dispatchChallanDetailsDispSchBased = dispatchChallanDetails.stream()
                    .filter(dispatchMat -> dispatchScheduleNo.equals(dispatchMat.getDispatch_schedule_no()))
                    .collect(Collectors.toList());

            // Now iterate the loop on the dispatch-material and find the so_sr_no and product_rm_id matching object.
            dispatchChallanDetailsDispSchBased.forEach(challanMaterial -> {
                String product_material_id = challanMaterial.getProduct_material_id();
                Optional<CMtDispatchScheduleDetailsTradingModel> matchingDispSchDetailObjs = currentDispSchNoDetailsFromDB.stream()
                        .filter(dispSchDetail -> product_material_id.equals(dispSchDetail.getProduct_material_id()))
                        .findFirst();

                if (matchingDispSchDetailObjs.isPresent()) {
                    CMtDispatchScheduleDetailsTradingModel matchedDispScheDetail = matchingDispSchDetailObjs.get();
                    Double updatedDispQty = matchedDispScheDetail.getDispatched_quantity() - challanMaterial.getDispatch_quantity();
                    Double updatedDispWt = matchedDispScheDetail.getDispatched_weight() - challanMaterial.getDispatch_weight();
                    // Update the dispatch-schedule dispatched details.
                    matchedDispScheDetail.setDispatched_quantity(updatedDispQty);
                    matchedDispScheDetail.setDispatched_weight(updatedDispWt);
                    matchedDispScheDetail.setModified_by(detailModifiedBy);
                    if (matchedDispScheDetail.getDispatched_quantity() <= 0.0) {
                        matchedDispScheDetail.setDispatch_schedule_item_status("A");
                    } else {
                        matchedDispScheDetail.setDispatch_schedule_item_status("I");
                    }

                    // add the updatedDispScheduleDetails in the list for update the DispatchScheduleDetails.
                    updatedDispScheduleDetails.add(matchedDispScheDetail);
                }
            });
        });

        // Update the dispatch_schedule_details.
        iMtDispatchScheduleDetailsTradingRepository.saveAll(updatedDispScheduleDetails);

//				----------------- For Update the Dispatch-Schedule master status.--------------------	
        // get all dispatch-schedule masters records for distinctDispScheduleNos.
        List<CMtDispatchScheduleMasterTradingModel> dispSchedulesMasters = iMtDispatchScheduleMasterTradingRepository.FnShowDispatchSchedulesByDispSchNos(company_id, distinctDispScheduleNos);
        List<CMtDispatchScheduleMasterTradingModel> dispSchedulesMastersForUpdate = new ArrayList<CMtDispatchScheduleMasterTradingModel>();

        // Iterate the loop on dispatch-schedule master records.
        dispSchedulesMasters.forEach(dispSchMst -> {
            dispSchMst.setDispatch_note_status("A");
            dispSchMst.setModified_by(detailModifiedBy);
            dispSchedulesMastersForUpdate.add(dispSchMst);            // Add that updated object in the list.
        });
        // Update the dispatch_schedule_masters.
        iMtDispatchScheduleMasterTradingRepository.saveAll(dispSchedulesMastersForUpdate);
    }

    @Override
    public Map<String, Object> FnCancelDispatchChallan(String dispatch_challan_no, int dispatch_challan_version,
                                                       String financial_year, int company_id, String detailsModifiedBy) {
        Map<String, Object> responce = new HashMap<>();
        try {

            // Get all the transaction details.
            CMtDispatchChallanMasterTradingModel dispatchChallanMst = iMtDispatchChallanMasterTradingRepository.FnShowParticularRecordByDispatchChallanNo(dispatch_challan_no, dispatch_challan_version, financial_year, company_id);
            List<CMtDispatchChallanDetailsTradingModel> dispatchChallanMaterialDetails = iMtDispatchChallanDetailsTradingRepository.FnShowRecordsByDispatchChallan(dispatchChallanMst.getDispatch_challan_master_transaction_id(), company_id);
            List<CMtDispatchChallanBatchwiseTradingModel> dispatchChallanBatchWiseMaterialsData = iMtDispatchChallanBatchwiseTradingRepository.FnShowRecordsByDispatchChallan(dispatchChallanMst.getDispatch_challan_master_transaction_id(), company_id);
            List<CMtDispatchInspectionDetailsTradingModel> dispatchInspectionDetailsData = iMtDispatchInspectionDetailsTradingRepository.FnshowRecordsInspectionDetails(dispatchChallanMst.getDispatch_challan_master_transaction_id(), company_id);

            List<CMtDispatchChallanPackingDetailsModel> dispatchPackingDetailsData = iMtDispatchChallanPackingDetailsRepository.FnshowRecordsPackingDetails(dispatchChallanMst.getDispatch_challan_master_transaction_id(), company_id);

            // 1. update the stocks in stock_tables.
            List<CMtDispatchChallanBatchwiseTradingViewModel> dispatchChallanBatchwiseRecords = iMtDispatchChallanBatchwiseTradingViewRepository.FnShowDispatchChallanBatchwiseRecords(dispatch_challan_no, dispatch_challan_version, company_id);
            cSmProductRmStockDetailsServiceImpl.FnReverseStockByDispatchChallan(dispatchChallanMaterialDetails, dispatchChallanBatchwiseRecords, company_id, detailsModifiedBy);

            // 2. update the dependent transactions details.
            if (dispatchChallanMst.getDispatch_challan_creation_type().equals("O")) {    // O Means order created on the basis of sales_order
                FnReverseUpdateSODetails(dispatchChallanMaterialDetails, company_id, detailsModifiedBy);

            } else if (dispatchChallanMst.getDispatch_challan_creation_type().equals("D")) {    // D Means order created on the basis of dispatch_schedule.
                FnReverseUpdateDispatchScheduleDetails(dispatchChallanMaterialDetails, company_id, detailsModifiedBy);        // First update the dispatch-schedule transaction details.
                FnReverseUpdateSODetails(dispatchChallanMaterialDetails, company_id, detailsModifiedBy);                    // Then update the sales order transaction details.
            }

            // 3. update the dispatch-challan transactions details.
            dispatchChallanMst.setDispatch_challan_status("X");
            dispatchChallanMst.setModified_by(detailsModifiedBy);
            dispatchChallanMaterialDetails.forEach(dispatchMaterial -> {
                dispatchMaterial.setDispatch_challan_item_status("X");
                dispatchMaterial.setModified_by(detailsModifiedBy);
            });
            dispatchChallanBatchWiseMaterialsData.forEach(batchWiseMaterial -> {
                batchWiseMaterial.setDispatch_challan_batch_status("X");
                batchWiseMaterial.setModified_by(detailsModifiedBy);
            });

            // 4. update inspection details if present.
            if (!dispatchInspectionDetailsData.isEmpty()) {
                // Update inspectionlist stocks
                List<Integer> distinctInspDetailsIds = dispatchInspectionDetailsData.parallelStream()
                        .map(CMtDispatchInspectionDetailsTradingModel::getWeaving_production_inspection_details_id)
                        .distinct()
                        .collect(Collectors.toList());

                // Fetch product dispatch stock information
                List<CXtWeavingProductionInspectionDetailsModel> productDispatchStockList = distinctInspDetailsIds != null ?
                        iXtWeavingProductionInspectionDetailsRepository.getAllDispatchQtySummary(distinctInspDetailsIds, company_id) :
                        null;

                dispatchInspectionDetailsData.forEach(inspectObj -> {
                    String dispatchStatus = "";
                    String stock_status_description = "In-Stock";
                    // Get previous dispatch quantity
                    Optional<CXtWeavingProductionInspectionDetailsModel> materialSummary = productDispatchStockList.stream()
                            .filter(matSummary -> inspectObj.getWeaving_production_inspection_details_id()
                                    .equals(matSummary.getWeaving_production_inspection_details_id()))
                            .findFirst();
                    if (materialSummary.isPresent()) {
                        CXtWeavingProductionInspectionDetailsModel foundedMatSummary = materialSummary.get();
                        double totalDispatchQty = foundedMatSummary.getDispatch_quantity() - inspectObj.getDispatch_quantity();
                        double totalDispatchWt = foundedMatSummary.getDispatch_weight() - inspectObj.getDispatch_weight();
                        String dispatchDate = inspectObj.getDispatch_challan_date();
                        dispatchStatus = "A";
//                        if (foundedMatSummary.getInspection_mtr() > totalDispatchQty) {
//                            dispatchStatus = "PD";    // for partial dispatch
//                        } else if (foundedMatSummary.getInspection_mtr() == totalDispatchQty) {
//                            dispatchStatus = "D";    // for Fully dispatch
//                        }
                        // Update dispatch details in the inspection details table
                        iXtWeavingProductionInspectionDetailsRepository.updateDispatchInspectionDetails(totalDispatchQty, dispatchStatus,
                                inspectObj.getWeaving_production_inspection_details_id(), inspectObj.getCompany_id(), dispatchDate, totalDispatchWt, dispatch_challan_no, stock_status_description);
                    } else {
                        // Handle case where material summary is not found
                        System.out.println("Material summary not found for inspection detail ID: " + inspectObj.getWeaving_production_inspection_details_id());
                    }

                });

            }


            // Update packing details if present.
            if (!dispatchPackingDetailsData.isEmpty()) {
                // Update Packing list stocks
                List<Integer> distinctPackingDetailsIds = dispatchPackingDetailsData.parallelStream()
                        .map(CMtDispatchChallanPackingDetailsModel::getDispatch_challan_packing_id)
                        .distinct()
                        .collect(Collectors.toList());

                // Fetch product dispatch stock information
                List<CMtDispatchChallanPackingDetailsModel> productDispatchStockList = distinctPackingDetailsIds != null ?
                        iMtDispatchChallanPackingDetailsRepository.getAllDispatchQtySummary(distinctPackingDetailsIds, company_id) :
                        null;

                dispatchPackingDetailsData.forEach(packingdetailObj -> {
                    // Get previous dispatch quantity
                    Optional<CMtDispatchChallanPackingDetailsModel> materialSummary = productDispatchStockList.stream()
                            .filter(matSummary -> packingdetailObj.getDispatch_challan_packing_id()
                                    .equals(matSummary.getDispatch_challan_packing_id()))
                            .findFirst();

                    if (materialSummary.isPresent()) {
                        CMtDispatchChallanPackingDetailsModel foundedMatSummary = materialSummary.get();
                        double totalDispatchQty = foundedMatSummary.getItem_dispatch_quantity() - packingdetailObj.getItem_dispatch_quantity();

                        List<CXtSpinningYarnPackingDetailsModel> spinningYarnPackingDetails = iXtSpinningYarnPackingDetailsRepository.FnshowRecordsYarnPackingdetailsDetails(packingdetailObj.getYarn_packing_details_id(), packingdetailObj.getCompany_id());

                        // Iterate over spinning yarn packing details
                        spinningYarnPackingDetails.forEach(yarnPackingDetails -> {
                            String packingdetailStatus = "";
                            if (yarnPackingDetails.getShipping_mark().equals("Continue")) {
                                packingdetailStatus = "WIP";
                            } else if (yarnPackingDetails.getShipping_mark().equals("Complete")) {
                                packingdetailStatus = "Completed";
                            }
                            // Update dispatch details in the packing details table
                            iXtSpinningYarnPackingDetailsRepository.updateDispatchPackingDetails(totalDispatchQty, packingdetailStatus,
                                    yarnPackingDetails.getYarn_packing_details_id(), packingdetailObj.getCompany_id());
                        });

                    } else {
                        // Handle case where material summary is not found
                        System.out.println("Material summary not found for inspection detail ID: " + packingdetailObj.getDispatch_challan_packing_id());
                    }
                });
            }


            iMtDispatchChallanMasterTradingRepository.save(dispatchChallanMst);
            iMtDispatchChallanDetailsTradingRepository.saveAll(dispatchChallanMaterialDetails);
            iMtDispatchChallanBatchwiseTradingRepository.saveAll(dispatchChallanBatchWiseMaterialsData);

            responce.put("success", "1");
            responce.put("error", "");
            responce.put("message", "Dispatch-Challan Canceled Successfully...!");
            System.out.println("Dispatch-challan cancelled: " + dispatch_challan_no);
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/MtDispatchChallanDetails/FnCancelDispatchChallan", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/MtDispatchChallanDetails/FnCancelDispatchChallan", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnSendEmail(int company_id, int dispatch_challan_details_transaction_id,
                                           JSONObject emailData) {
        Map<String, Object> emailingResponse = new HashMap<>();

        EmailController emailController = new EmailController();
        Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);
        Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
        // Update the mail send status in master table.
//		if(emailSentStatus) {
//			iMtSalesOrderMasterTradingRepository.updateMailStatus("S", company_id, dispatch_challan_details_transaction_id);
//		}else {
//			iMtSalesOrderMasterTradingRepository.updateMailStatus("F", company_id, dispatch_challan_details_transaction_id);
//		}

        emailingResponse.put("Status", emailSentStatus);
        emailingResponse.put("success", emailControllerResponse.get("success"));
        emailingResponse.put("error", emailControllerResponse.get("error"));
        emailingResponse.put("message", emailControllerResponse.get("message"));
        return emailingResponse;
    }

    @Override
    public Map<String, Object> FnSaveDailyDispatchChallanData(JSONObject jsonObject) {
        Map<String, Object> response = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
            int company_id = commonIdsObj.getInt("company_id");
            Map<String, Object> stockDetails = new HashMap<>();

            // get master data
            JSONObject masterJSON = (JSONObject) jsonObject.get("DailyDispatchChallanMasterData");
            JSONArray detailsArray = (JSONArray) jsonObject.get("DailyDispatchChallanReport");
            JSONArray dispatchInspectionArray = (JSONArray) jsonObject.get("DispatchInspectionDetails");
            String message = "Record inserted successfully!";

//		    Create list to add object to save stock details & Summary
            List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
            List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

            CMtDispatchChallanMasterTradingModel jsonModel = objectMapper.readValue(masterJSON.toString(),
                    CMtDispatchChallanMasterTradingModel.class);
            CMtDispatchChallanMasterTradingModel responceDispatchChallanMasterTrading = iMtDispatchChallanMasterTradingRepository
                    .save(jsonModel);

            List<CMtDispatchChallanDetailsTradingModel> dailyDispatchChallanReport = objectMapper.readValue(detailsArray.toString(), new TypeReference<List<CMtDispatchChallanDetailsTradingModel>>() {
            });

            dailyDispatchChallanReport.forEach((item) -> {
                item.setDispatch_challan_master_transaction_id(responceDispatchChallanMasterTrading.getDispatch_challan_master_transaction_id());
            });

            List<CMtDispatchInspectionDetailsTradingModel> dispatchInspectionDetails = objectMapper.readValue(dispatchInspectionArray.toString(), new TypeReference<List<CMtDispatchInspectionDetailsTradingModel>>() {
            });

            dispatchInspectionDetails.forEach((item) -> {
                item.setDispatch_challan_master_transaction_id(responceDispatchChallanMasterTrading.getDispatch_challan_master_transaction_id());
            });

            dailyDispatchChallanReport = iMtDispatchChallanDetailsTradingRepository.saveAll(dailyDispatchChallanReport);
            dispatchInspectionDetails = iMtDispatchInspectionDetailsTradingRepository.saveAll(dispatchInspectionDetails);


            List<Integer> distinctRollNo = dispatchInspectionDetails.parallelStream().map(CMtDispatchInspectionDetailsTradingModel::getRoll_no)
                    .distinct().collect(Collectors.toList());

            List<String> distinctSortNo = dispatchInspectionDetails.parallelStream().map(CMtDispatchInspectionDetailsTradingModel::getSort_no)
                    .distinct().collect(Collectors.toList());

            List<CXtWeavingProductionInspectionDetailsModel> getWeavingInspectionDetails = iXtWeavingProductionInspectionDetailsRepository.getAllDetailsOfInspection(distinctRollNo, distinctSortNo, company_id);

//            GET PRODUCT_FG_IDS FROM DYNAMIC PROPERTIES
            List<String> productIdListSeparated = getWeavingInspectionDetails.parallelStream().map(CXtWeavingProductionInspectionDetailsModel::getProduct_material_id).collect(Collectors.toList());

//          Get Product details from smv_product_fg
            List<CSmProductFgModel> getProductDetails = iSmProductFgRepository.getProductFgDetails(productIdListSeparated);

            List<CXtWeavingProductionInspectionDetailsModel> addUpdatedInspectionDetailsList = new ArrayList<>();

//		    Get the current date
            Date currentDate = new Date();

//		    Formatting the date to display only the date portion
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayDate = dateFormat.format(currentDate);

            //updateInspectionProductionStatus
            dispatchInspectionDetails.forEach(item -> {
                Optional<CXtWeavingProductionInspectionDetailsModel> detailsModel = getWeavingInspectionDetails.stream()
                        .filter(inspectionItem -> inspectionItem.getRoll_no().equals(item.getRoll_no()) && inspectionItem.getSort_no().equals(item.getSort_no())).findFirst();

                CSmProductFgModel smProductFgModel = getProductDetails.stream()
                        .filter(finishGood -> finishGood.getProduct_fg_id().equals(item.getProduct_material_id()))
                        .findFirst().get();

                if (detailsModel.isPresent()) {
                    CXtWeavingProductionInspectionDetailsModel getInspectionObject = detailsModel.get();
                    getInspectionObject.setDispatch_quantity(item.getDispatch_quantity());
                    getInspectionObject.setDispatch_weight(item.getDispatch_weight());
                    getInspectionObject.setInspection_production_status("D");

                    addUpdatedInspectionDetailsList.add(getInspectionObject);

//              Reduce the stock from stock table
//				Check if an entry with the same identifier (e.g., product_rm_id or any unique field) exists
                    Optional<CSmProductRmStockSummaryModel> getExistingSummaryForProduct = addProductRmStockSummaryList
                            .parallelStream()
                            .filter(summary -> summary.getProduct_rm_id().equals(getInspectionObject.getProduct_material_id()))
                            .findFirst();

                    if (getExistingSummaryForProduct.isPresent()) {
                        // Update existing entry
                        CSmProductRmStockSummaryModel existingSummary = getExistingSummaryForProduct.get();

                        // Update the quantity fields by adding new values to the existing ones
                        existingSummary.setClosing_balance_quantity(existingSummary.getClosing_balance_quantity() - item.getDispatch_quantity());
                        existingSummary.setClosing_balance_weight(existingSummary.getClosing_balance_weight() - item.getDispatch_weight());
                        existingSummary.setSales_quantity(existingSummary.getSales_quantity() + item.getDispatch_quantity());
                        existingSummary.setSales_weight(existingSummary.getSales_weight() + item.getDispatch_weight());

                    } else {
                        // Create a new entry if it doesn't exist
                        CSmProductRmStockSummaryModel productRmStockSummaryModel = CSmProductRmStockSummaryModel.builder()
                                .company_id(company_id)
                                .company_branch_id(item.getCompany_branch_id())
                                .financial_year(item.getFinancial_year())
                                .product_rm_id(item.getProduct_material_id())
                                .closing_balance_quantity(-item.getDispatch_quantity())
                                .closing_balance_weight(-item.getDispatch_weight())
                                .sales_quantity(item.getDispatch_quantity())
                                .sales_weight(item.getDispatch_weight())
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
                        existingModel.setClosing_balance_quantity(existingModel.getClosing_balance_quantity() - item.getDispatch_quantity());
                        existingModel.setClosing_balance_weight(existingModel.getClosing_balance_weight() - item.getDispatch_weight());
                        existingModel.setSales_quantity(existingModel.getSales_quantity() + item.getDispatch_quantity());
                        existingModel.setSales_weight(existingModel.getSales_weight() + item.getDispatch_weight());
                    } else {
                        CSmProductRmStockDetailsModel productRmStockDetailsModel = CSmProductRmStockDetailsModel.builder()
                                .company_id(company_id)
                                .company_branch_id(item.getCompany_branch_id())
                                .financial_year(item.getFinancial_year())
                                .product_rm_id(item.getProduct_material_id())
                                .stock_date(todayDate)
                                .goods_receipt_no(item.getSort_no())
                                .closing_balance_quantity(-item.getDispatch_quantity())
                                .closing_balance_weight(-item.getDispatch_weight())
                                .sales_quantity(item.getDispatch_quantity())
                                .sales_weight(item.getDispatch_weight())
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


//			Insert in stock table
            cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", company_id);

//          Insert in inspection details
            iXtWeavingProductionInspectionDetailsRepository.saveAll(addUpdatedInspectionDetailsList);


            response.put("success", 1);
            response.put("data", dailyDispatchChallanReport);
            response.put("error", "");
            response.put("message", message);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());
            response.put("message", "Error occurred.");
        }
        return response;
    }

    private void FnAddUpdateStock(List<CMtDispatchChallanDetailsTradingModel> dailyDispatchChallanReport,
                                  List<CMtDispatchInspectionDetailsTradingModel> dispatchInspectionDetails) {


    }

    @Override
    public Map<String, Object> FnDeleteDailyDispatchReport(String dispatch_challan_no, int dispatch_challan_version, int company_id, String deletedBy) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iMtDispatchChallanMasterTradingRepository.deleteChallanMaster(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);
            iMtDispatchChallanDetailsTradingRepository.deleteChallanDetails(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);
            iMtDispatchInspectionDetailsTradingRepository.deleteInspectionDetails(dispatch_challan_no, dispatch_challan_version, company_id, deletedBy);

            responce.put("success", 1);
            responce.put("data", "");
            responce.put("error", "");

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", "");
        }
        return responce;
    }


}
