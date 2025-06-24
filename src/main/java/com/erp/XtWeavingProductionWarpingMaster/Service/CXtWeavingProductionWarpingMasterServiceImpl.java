package com.erp.XtWeavingProductionWarpingMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Properties.Model.CPropertiesViewModel;
import com.erp.Common.Properties.Repository.IPropertiesViewRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTrackingViewModel;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockSummaryRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductStockTrackingViewModelRepository;
import com.erp.StIndentIssueMaster.Repository.IStIndentMaterialIssueDetailRepository;
import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderCreelsModel;
import com.erp.XtWarpingProductionOrder.Repository.IXtWarpingProductionOrderCreelsRepository;
import com.erp.XtWeavingProductionOrderMaster.Repository.IXtWeavingProductionDetailsRepository;
import com.erp.XtWeavingProductionWarpingMaster.Model.*;
import com.erp.XtWeavingProductionWarpingMaster.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.AtomicDouble;
import lombok.var;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CXtWeavingProductionWarpingMasterServiceImpl implements IXtWeavingProductionWarpingMasterService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IXtWeavingProductionWarpingDetailsRepository iXtWeavingProductionWarpingDetailsRepository;

    @Autowired
    IXtWeavingProductionWarpingDetailsViewRepository iXtWeavingProductionWarpingDetailsViewRepository;

    @Autowired
    IXtWeavingProductionWarpingMasterRepository iXtWeavingProductionWarpingMasterRepository;

    @Autowired
    IXtWeavingProductionWarpingMaterialRepository iXtWeavingProductionWarpingMaterialRepository;

    @Autowired
    IXtWeavingProductionWarpingMaterialViewRepository iXtWeavingProductionWarpingMaterialViewRepository;

    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Autowired
    ISmProductRmStockSummaryRepository iSmProductRmStockSummaryRepository;


    @Autowired
    IPropertiesViewRepository iPropertiesViewRepository;

    @Autowired
    CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

    @Autowired
    ISmProductStockTrackingViewModelRepository iSmProductStockTrackingViewModelRepository;

    @Autowired
    IXtWeavingProductionDetailsRepository iXtWeavingProductionDetailsRepository;

    @Autowired
    IXtWeavingProductionWarpingMasterViewRepository iXtWeavingProductionWarpingMasterViewRepository;

    @Autowired
    IXtWeavingProductionWarpingBottomDetailsRepository iXtWeavingProductionWarpingBottomDetailsRepository;

    @Autowired
    IXtWarpingProductionOrderCreelsRepository iXtWarpingProductionOrderCreelsRepository;

    @Autowired
    IStIndentMaterialIssueDetailRepository iStIndentMaterialIssueDetailRepository;

    @Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        // get CommonId's
        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int weaving_production_warping_master_id = commonIdsObj.getInt("weaving_production_warping_master_id");
        String set_srno_dt = commonIdsObj.getString("set_srno_dt");

        // get master data
        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray warpingBottomDetails = (JSONArray) jsonObject.get("TransWVBottomData");

        JSONArray warpingMaterialArray = (JSONArray) jsonObject.get("TransWarpingMaterialData");


        try {
            List<CXtWeavingProductionWarpingDetailsModel> respWeavingProductionWarpingDetailsModel = null;

            CXtWeavingProductionWarpingMasterModel respWVProductionWarpingMasterModel = objectMapper
                    .readValue(masterjson.toString(), CXtWeavingProductionWarpingMasterModel.class);


//			Saving xt_weaving_production_warping_master
            CXtWeavingProductionWarpingMasterModel respWVProductionwarpingMasterModel = iXtWeavingProductionWarpingMasterRepository
                    .save(respWVProductionWarpingMasterModel);

//          Saving xt_weaving_production_warping_details
            if (!detailsArray.isEmpty()) {
                // Update existing warping details
                iXtWeavingProductionWarpingDetailsRepository.updateWVProductionWarpingMasterRecord(respWVProductionwarpingMasterModel.getWeaving_production_warping_master_id(), company_id);

                // Weaving Production Warping Details Array
                List<CXtWeavingProductionWarpingDetailsModel> cXtWeavingProductionWarpingDetailsModel = objectMapper.readValue(detailsArray.toString(),
                        new TypeReference<List<CXtWeavingProductionWarpingDetailsModel>>() {
                        });

                cXtWeavingProductionWarpingDetailsModel.forEach(item -> {
                    item.setWeaving_production_warping_master_id(respWVProductionwarpingMasterModel.getWeaving_production_warping_master_id());
                    item.setWarping_production_code(respWVProductionwarpingMasterModel.getWarping_production_code());
                });
                respWeavingProductionWarpingDetailsModel = iXtWeavingProductionWarpingDetailsRepository.saveAll(cXtWeavingProductionWarpingDetailsModel);

            }

//			*********************************************************************Process start to save/update Warping materials****************************************************************************************************8

            // Process warping materials
//			if (!warpingMaterialArray.isEmpty()) {
//
//				List<CXtWeavingProductionWarpingMaterialModel> cXtWeavingProductionWarpingMaterialModel = objectMapper
//						.readValue(warpingMaterialArray.toString(), new TypeReference<List<CXtWeavingProductionWarpingMaterialModel>>() {
//						});
//
////				Stock comsumption
//				if (!respWVProductionwarpingMasterModel.getWarping_production_master_status().equals("A"))
////					FnAddUpdateStock(cXtWeavingProductionWarpingMaterialModel, respWVProductionwarpingMasterModel, company_id);
//					FnAddUpdateYarnStock(cXtWeavingProductionWarpingMaterialModel, respWVProductionwarpingMasterModel, company_id);
//
//				// Update Material Movement
//				if (weaving_production_warping_master_id != 0) {
//
//					List<Integer> distinctwvProductionWarpingMaterialIds = cXtWeavingProductionWarpingMaterialModel.parallelStream().
//							map(CXtWeavingProductionWarpingMaterialModel::getWeaving_production_warping_material_id).distinct().collect(Collectors.toList());
//
//					iXtWeavingProductionWarpingMaterialRepository.updateWeavingProductionWarpingMaterial(distinctwvProductionWarpingMaterialIds, respWVProductionwarpingMasterModel.getWarping_production_code());
//
//				} else {
//					iXtWeavingProductionWarpingMaterialRepository.updateAllWarpingMaterial(respWVProductionwarpingMasterModel.getWarping_production_code());
//				}
//				// Saved cXtWeavingProductionWarpingMaterialModel
//				iXtWeavingProductionWarpingMaterialRepository.saveAll(cXtWeavingProductionWarpingMaterialModel);
//			}

            ///////////********************Saving Bottom Details**************************/////////////
            List<CXtWeavingProductionWarpingBottomDetailsModel> cXtWeavingProductionWarpingBottomDetailsModels = objectMapper
                    .readValue(warpingBottomDetails.toString(), new TypeReference<List<CXtWeavingProductionWarpingBottomDetailsModel>>() {
                    });
            if (!warpingBottomDetails.isEmpty() ) {

                String set_no = respWVProductionWarpingMasterModel.getSet_no();
                List<String> set_sr_nos = iXtWeavingProductionWarpingBottomDetailsRepository.FnGetSetSrNo(set_srno_dt, company_id);
                int sr_no;
                if (set_sr_nos == null || set_sr_nos.isEmpty()) {
                    sr_no = 1;
                } else {
                    String set_sr_no = set_sr_nos.get(set_sr_nos.size() - 1);
                    sr_no = Integer.parseInt(set_sr_no.split("-")[1]) + 1;
                }

                for (var bottomDetailsModel : cXtWeavingProductionWarpingBottomDetailsModels) {
                    if(bottomDetailsModel.getWeaving_production_warping_bottom_details_id() == 0){
                        bottomDetailsModel.setSr_no(set_no + "-" + sr_no);
                        bottomDetailsModel.setWeaving_production_warping_master_id(
                                respWVProductionWarpingMasterModel.getWeaving_production_warping_master_id()
                        );
                        sr_no++; // Increment serial number
                    }
                }

                cXtWeavingProductionWarpingBottomDetailsModels =
                        iXtWeavingProductionWarpingBottomDetailsRepository.saveAll(cXtWeavingProductionWarpingBottomDetailsModels);
            }


            //Updating Stock Data
//            if (stock_adjust && !detailsArray.isEmpty()) {
////                double cone_per_weight = commonIdsObj.getDouble("cone_per_weight");
////                String product_rm_id = commonIdsObj.getString("product_rm_id");
//                String set_no = respWVProductionwarpingMasterModel.getSet_no();
//                List<String> goodsrecieptnumbers = iStIndentMaterialIssueDetailRepository.FnGetGRNumbers(set_no, company_id);
//
//                List<String> updatedGRNumbers = goodsrecieptnumbers.stream()
//                        .map(goodsReceiptNo -> goodsReceiptNo + "-" + set_no)
//                        .collect(Collectors.toList());
//
//
//                double setClosing_balance_quantity = 0;
//                double setClosing_balance_weight = 0;
//                Integer setClosing_no_of_boxes = 0;
//
//
////                updateStock(setClosing_balance_quantity, setClosing_balance_weight, setClosing_no_of_boxes, cone_per_weight, company_id, product_rm_id, updatedGRNumbers);
//
//            }

            responce.put("success", 1);
            responce.put("data", respWVProductionwarpingMasterModel);
            responce.put("error", "");
            responce.put("message",
                    respWVProductionwarpingMasterModel.getWarping_production_master_status().equals("A") ? "Record Approved successfully!..."
                            : (respWVProductionwarpingMasterModel.getWarping_production_master_status().equals("R") ? "Record Rejected successfully!.."
                            : (weaving_production_warping_master_id == 0 ? "Record added successfully!..."
                            : "Record updated successfully!...")));

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/XtWeavingProductionWarping/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWeavingProductionWarping/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }

        return responce;

    }

    public void updateStock(double closing_balance_qty, double closing_balance_weight, Integer setClosing_no_of_boxes, double cone_per_weight, int company_id, String product_rm_id, List<String> updatedGRNumbers) {
        iSmProductRmStockDetailsRepository.updateStockDetailsForWarping(closing_balance_qty, closing_balance_weight, setClosing_no_of_boxes, cone_per_weight, company_id, product_rm_id, updatedGRNumbers);
    }


    private void handleDataAccessException(DataAccessException e, int company_id, Map<String, Object> response) {
        e.printStackTrace();
        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof SQLException) {
            SQLException sqlEx = (SQLException) rootCause;
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWeavingProductionWarping/FnAddUpdateRecord", sqlEx.getErrorCode(),
                    sqlEx.getMessage());
        } else {
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWeavingProductionWarping/FnAddUpdateRecord", 0, e.getMessage());
        }
        response.put("success", 0);
        response.put("data", "");
        response.put("error", e.getMessage());
    }

    private void handleGenericException(Exception e, int company_id, Map<String, Object> response) {
        e.printStackTrace();
        amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                "/api/XtWeavingProductionWarping/FnAddUpdateRecord", 0, e.getMessage());
        response.put("success", 0);
        response.put("data", "");
        response.put("error", e.getMessage());
    }

    private void FnAddUpdateYarnStock(List<CXtWeavingProductionWarpingMaterialModel> cXtWeavingProductionWarpingMaterialModel, CXtWeavingProductionWarpingMasterModel respWVProductionwarpingMasterModel, int company_id) {
        Map<String, Object> stockDetails = new HashMap<>();

        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addStockTracking = new ArrayList<>();

        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);

//		iterate on indent materials for stock issue
        cXtWeavingProductionWarpingMaterialModel.forEach(materialMovementRecord -> {

            CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
            CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

//          REDUCE THE STOCK FROM SIZING STORE GODOWN FROM Weaving DEPARTMENT
            productRmStockDetailsModel.setGoods_receipt_no(materialMovementRecord.getGoods_receipt_no());
            productRmStockDetailsModel.setProduct_rm_id(materialMovementRecord.getProduct_material_id());
            productRmStockDetailsModel.setProduct_type_id(12);
//			productRmStockDetailsModel.setStock_date(responseIndentIssueMaster.getIssue_date());
            productRmStockDetailsModel.setGodown_id(6);
            productRmStockDetailsModel.setGodown_section_id(6);
            productRmStockDetailsModel.setGodown_section_beans_id(6);
            productRmStockDetailsModel.setClosing_balance_quantity(-materialMovementRecord.getConsumption_quantity());
            productRmStockDetailsModel.setClosing_balance_weight(-materialMovementRecord.getConsumption_weight());
//			productRmStockDetailsModel.setClosing_no_of_boxes(-indentRecord.getProduct_material_issue_boxes());
            addProductRmStockDetailsList.add(productRmStockDetailsModel);


//			if object is present in summary then just increase the qty of existing object
            Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(materialMovementRecord.getProduct_material_id()) &&
                            Objects.equals(6, item.getGodown_id()) &&
                            Objects.equals(6, item.getGodown_section_id()) &&
                            Objects.equals(6, item.getGodown_section_beans_id())
                    ).findFirst();

            if (getSummaryObject.isPresent()) {
                CSmProductRmStockSummaryModel getProductRmStockSummaryModel = getSummaryObject.get();
                productRmStockSummaryModel.setClosing_balance_quantity(getProductRmStockSummaryModel.getClosing_balance_quantity() + (-materialMovementRecord.getConsumption_quantity()));
                productRmStockSummaryModel.setClosing_balance_weight(getProductRmStockSummaryModel.getClosing_balance_weight() + (-materialMovementRecord.getConsumption_weight()));
            } else {
                productRmStockSummaryModel.setCompany_id(materialMovementRecord.getCompany_id());
                productRmStockSummaryModel.setCompany_branch_id(materialMovementRecord.getCompany_branch_id());
                productRmStockSummaryModel.setProduct_type_id(12);
                productRmStockSummaryModel.setClosing_balance_quantity(-materialMovementRecord.getConsumption_quantity());
                productRmStockSummaryModel.setClosing_balance_weight(-materialMovementRecord.getConsumption_weight());
                productRmStockSummaryModel.setProduct_rm_id(materialMovementRecord.getProduct_material_id());
                productRmStockSummaryModel.setProduct_material_unit_id(materialMovementRecord.getProduct_material_unit_id());
                productRmStockSummaryModel.setModified_by(materialMovementRecord.getCreated_by());
                productRmStockSummaryModel.setGodown_id(6);
                productRmStockSummaryModel.setGodown_section_id(6);
                productRmStockSummaryModel.setGodown_section_beans_id(6);

                addProductRmStockSummaryList.add(productRmStockSummaryModel);

            }
        });

        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        stockDetails.put("StockTracking", addStockTracking);

        cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Decrease", company_id);

    }


    @Override
    public Map<String, Object> FnDeleteRecord(int weaving_production_warping_master_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            // Delete Production Warping Master Record
            iXtWeavingProductionWarpingMasterRepository.FnDeleteProductionWarpingMasterRecord(weaving_production_warping_master_id, deleted_by);

            // Delete Production Warping Details Record
            iXtWeavingProductionWarpingDetailsRepository.FnDeleteProductionWarpingDetailsRecord(weaving_production_warping_master_id, deleted_by);

            // Delete Production Warping Stoppage Record
//			iXtWeavingProductionWarpingStoppageRepository.FnDeleteProductionWarpingStoppageRecord(weaving_production_warping_master_id, deleted_by);

            // Delete Production Warping Material Record
            iXtWeavingProductionWarpingMaterialRepository.FnDeleteProductionWarpingMaterialRecord(weaving_production_warping_master_id, deleted_by);

            // Delete Production Warping Wastage Record
//			iXtWeavingProductionWarpingWastageRepository.FnDeleteProductionWarpingWastageRecord(weaving_production_warping_master_id, deleted_by);

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
    public Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_warping_master_id,
                                                               int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            // Fetch Weaving Production Warping Master Records
            CXtWeavingProductionWarpingMasterViewModel wvProductionWarpingMasterModelRecords = iXtWeavingProductionWarpingMasterViewRepository
                    .FnShowWeavingProductionWarpingMasterRecordForUpdate(weaving_production_warping_master_id,
                            company_id);

            // Fetch Weaving Production Warping Details Records
            List<CXtWeavingProductionWarpingDetailsViewModel> warpingProductionDetails = iXtWeavingProductionWarpingDetailsViewRepository
                    .FnShowWeavingProductionWarpingDetailsRecordForUpdate(weaving_production_warping_master_id,
                            company_id);

            //Fetch Warping Production Bottom Details
            List<CXtWeavingProductionWarpingBottomDetailsModel> warpingBottomDetailsModels = iXtWeavingProductionWarpingBottomDetailsRepository
                    .FnShowWarpingBottomDetails(weaving_production_warping_master_id,
                            company_id);

            List<CXtWarpingProductionOrderCreelsModel> warpingProductionOrderCreelsModels = iXtWarpingProductionOrderCreelsRepository.FnGetOrderCreelsData(wvProductionWarpingMasterModelRecords.getSet_no());

//			// Fetch Weaving Production Warping Stoppage Details Records
//			List<CXtWeavingProductionWarpingStoppageViewModel> WeavingProductionWarpingStoppageModelRecords = iXtWeavingProductionWarpingStoppageViewRepository
//					.FnShowWeavingProductionWarpingStoppageRecordForUpdate(weaving_production_warping_master_id,
//							company_id);

            // Fetch Weaving Production Warping Material Records
            List<CXtWeavingProductionWarpingMaterialViewModel> WeavingProductionWarpingMaterialRecords = iXtWeavingProductionWarpingMaterialViewRepository
                    .FnShowWeavingProductionWarpingMaterialRecordForUpdate(weaving_production_warping_master_id, company_id);

//			Get Records from  sm_product_stock_tracking_view for stock
            List<CSmProductStockTrackingViewModel> getStockRecords = iSmProductStockTrackingViewModelRepository.FnShowStockRecords(wvProductionWarpingMasterModelRecords.getWarping_production_code(), company_id);

            AtomicDouble totalConsumptionQty = new AtomicDouble();

//			Iterate on warping material details to push their stock consumption details into particular object
            int[] index = {0}; // Create an array to hold the index

            WeavingProductionWarpingMaterialRecords.forEach((item) -> {
                List<CSmProductStockTrackingViewModel> matchingObjectStock = getStockRecords.stream()
                        .filter(stock -> stock.getConsumption_no().equals(item.getWarping_production_code() + item.getShift()))
                        .collect(Collectors.toList());

                List<Map<String, Object>> consumptionQtyInfoList = new ArrayList<>();
                List<Object> consumptionQtyInfoListObj = new ArrayList<>();

//              Add the consumption qty info list object for material wise godown consumption identification
                if (matchingObjectStock != null && matchingObjectStock.size() != 0) {
                    matchingObjectStock.forEach(stockTrackingViewData -> {
                        Map<String, Object> consumptionQtyInfo = new HashMap<>();

                        String indexString = Integer.toString(index[0]);
                        consumptionQtyInfo.put("index", indexString);
                        consumptionQtyInfo.put("shift", item.getShift());
                        consumptionQtyInfo.put("set_no", item.getWeaving_production_set_no());
                        consumptionQtyInfo.put("product_material_id", item.getProduct_material_id());
                        consumptionQtyInfo.put("consumption_quantity", stockTrackingViewData.getConsumption_quantity());
                        consumptionQtyInfo.put("godown_id", stockTrackingViewData.getGodown_id());
                        consumptionQtyInfo.put("godown_section_id", stockTrackingViewData.getGodown_section_id());
                        consumptionQtyInfo.put("godown_section_beans_id", stockTrackingViewData.getGodown_section_beans_id());
                        consumptionQtyInfoList.add(consumptionQtyInfo);

                        index[0]++;
                    });

                    for (Map<String, Object> map : consumptionQtyInfoList) {
                        consumptionQtyInfoListObj.add(map); // Add the entire map object to the list of objects
                    }

                    totalConsumptionQty.getAndAdd(item.getConsumption_quantity());
                    item.setConsumptionQtyInfo(consumptionQtyInfoListObj);
                    WeavingProductionWarpingMaterialRecords.set(WeavingProductionWarpingMaterialRecords.indexOf(item), item);
                }

            });

            WeavingProductionWarpingMaterialRecords.forEach((item) -> {
                item.setStock_quantity(item.getStock_quantity() + totalConsumptionQty.get());
            });

//			// Fetch Weaving Production Warping Wastage Records
//			List<CXtWeavingProductionWarpingWastageViewModel> WeavingProductionWarpingWastageRecords = iXtWeavingProductionWarpingWastageViewRepository
//					.FnShowWeavingProductionWarpingStoppageRecordForUpdate(weaving_production_warping_master_id, company_id);

            responce.put("WVProductionWarpingMasterModelRecords", wvProductionWarpingMasterModelRecords);
            responce.put("WVProductionWarpingDetailModelRecords", warpingProductionDetails);
            responce.put("WarpingProductionOrderCreelsRecords", warpingProductionOrderCreelsModels);
            responce.put("WeavingProductionWarpingMaterialRecords", WeavingProductionWarpingMaterialRecords);
            responce.put("WeavingProductionWarpingBottomDetails", warpingBottomDetailsModels);
//			responce.put("WeavingProductionWarpingWastageRecords", WeavingProductionWarpingWastageRecords);

            responce.put("success", "1");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/XtWeavingProductionWarping/FnShowParticularRecordForUpdate", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
                return responce;
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWeavingProductionWarping/FnShowParticularRecordForUpdate", 0, e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
            return responce;
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowParticularWarpingShiftSummary(String warping_production_date,
                                                                   int company_id) {
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
            List<CXtWeavingProductionWarpingDetailsViewModel> productionSummarytoday = iXtWeavingProductionWarpingDetailsViewRepository.FnShowParticularWarpingShiftSummary(warping_production_date, company_id);
            List<CXtWeavingProductionWarpingDetailsViewModel> productionSummaryPreviousdateDetails = iXtWeavingProductionWarpingDetailsViewRepository.FnShowParticularWarpingPreviousShiftSummary(warping_production_date, company_id);

            // Get stoppage time
//			double stoppageTime = iXtWeavingProductionWarpingDetailsViewRepository.FnShowStoppageTimeForShiftSummary(warping_production_date);

            // Iterate on production shifts
            getShifts.forEach(shifts -> {
                Map<String, Object> shiftObject = new LinkedHashMap<>();
                shiftObject.put("shift", shifts.getProperty_name());

                // Get today's production data for the current shift
                CXtWeavingProductionWarpingDetailsViewModel shiftwisetodaydateData = productionSummarytoday.stream()
                        .filter(item -> item.getShift().equals(shifts.getProperty_name()) && item.getWarping_production_date().equals(warping_production_date))
                        .findFirst()
                        .orElse(null);

                if (shiftwisetodaydateData != null) {

//					shiftObject.put("warping_total_legnth", shiftwisetodaydateData.getWarping_total_legnth());
//					shiftObject.put("warping_total_act_bottom", shiftwisetodaydateData.getWarping_total_act_bottom());


                } else {
                    shiftObject.put("warping_total_legnth", 0);
                    shiftObject.put("warping_total_act_bottom", 0);
                    shiftObject.put("stoppage_time", 0);
                }

                // Get previous date's production data for the current shift
                List<CXtWeavingProductionWarpingDetailsViewModel> uptoDateWarpingShiftSummaryData = productionSummaryPreviousdateDetails.stream()
                        .filter(item -> item.getShift().equals(shifts.getProperty_name()))
                        .collect(Collectors.toList());

                if (uptoDateWarpingShiftSummaryData != null) {

                    // Calculate sum of warping total length, total actual bottom, and stoppage time
                    double total_warping_length = uptoDateWarpingShiftSummaryData.stream().mapToDouble(CXtWeavingProductionWarpingDetailsViewModel::getLength).sum();
//					double total_act_bottom = uptoDateWarpingShiftSummaryData.stream().mapToDouble(CXtWeavingProductionWarpingDetailsViewModel::getAct_bottom).sum();

                    // Round the totals to two decimal places
                    total_warping_length = Math.round(total_warping_length * 100.0) / 100.0;
//					total_act_bottom = Math.round(total_act_bottom * 100.0) / 100.0;


                    //here sum of warping total length,total actual bottom and stoppage time
                    shiftObject.put("warping_total_upto_date_legnth", total_warping_length);
//					shiftObject.put("warping_total_upto_date_act_bottom", total_act_bottom);

                } else {

                    shiftObject.put("warping_total_upto_date_legnth", 0);
                    shiftObject.put("warping_total_upto_date_act_bottom", 0);
                    shiftObject.put("upto_date_stoppage_time", 0);
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
                        "/api/XtWeavingProductionWarping/FnShowParticularShiftSummary", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                response.put("success", 0);
                response.put("data", "");
                response.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/XtWeavingProductionWarping/FnShowParticularShiftSummary", 0, e.getMessage());
            response.put("success", 0);
            response.put("data", "");
            response.put("error", e.getMessage());

        }
        return response;
    }

    @Override
    public Map<String, Object> FnChangePrintStatus(int weaving_production_warping_master_id) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iXtWeavingProductionWarpingMasterRepository.FnUpdatePrintStatusWarpingMasterRecord(weaving_production_warping_master_id);
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
