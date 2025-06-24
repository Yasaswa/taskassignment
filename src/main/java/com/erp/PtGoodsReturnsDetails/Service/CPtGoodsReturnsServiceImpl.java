package com.erp.PtGoodsReturnsDetails.Service;

import com.erp.MtSalesOrderMasterTrading.Model.CMtSalesOrderPaymentTermsTradingModel;
import com.erp.PtGoodsReturnsDetails.Model.*;
import com.erp.PtGoodsReturnsDetails.Repository.IPtGoodsReturnBottomDetailsRepository;
import com.erp.PtGoodsReturnsDetails.Repository.IPtGoodsReturnsPaymentTermsRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;

import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.erp.StIndentIssueMaster.Model.CStIndentIssueDetailsModel;
import com.erp.XtWarpingBottomReturn.Repository.IXtWarpingBottomStockRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.PtGoodsReturnsDetails.Repository.IPtGoodsReturnsDetailsRepository;
import com.erp.PtGoodsReturnsDetails.Repository.IPtGoodsReturnsRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Service
public class CPtGoodsReturnsServiceImpl implements IPtGoodsReturnsService {
    private final CSmProductRmStockDetailsController cSmProductRmStockDetailsController;
    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
    @Autowired
    IPtGoodsReturnsRepository iPtGoodsReturnsRepository;
    @Autowired
    IPtGoodsReturnsDetailsRepository iPtGoodsReturnsDetailsRepository;
    @Autowired
    IPtGoodsReturnsPaymentTermsRepository iPtGoodsReturnsPaymentTermsRepository;
    @Autowired
    IPtGoodsReturnBottomDetailsRepository iPtGoodsReturnBottomDetailsRepository;
    @Autowired
    IXtWarpingBottomStockRepository iXtWarpingBottomStockRepository;
    @Autowired
    ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
        Map<String, Object> responce = new HashMap<>();

        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject GoodsReturnMasterData = jsonObject.getJSONObject("GoodsReturnMasterData");
        JSONArray GoodsReturnDetails = (JSONArray) jsonObject.get("GoodsReturnDetailData");
        int company_id = GoodsReturnMasterData.getInt("company_id");
        int goods_return_master_id = GoodsReturnMasterData.getInt("goods_return_master_id");
        String goods_return_no = GoodsReturnMasterData.getString("goods_return_no");
        String goods_return_date = GoodsReturnMasterData.getString("goods_return_date");
        String goods_return_master_status = GoodsReturnMasterData.getString("goods_return_status");
        JSONArray paymentTermsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
        JSONArray warpingbottomDetailsArray = (JSONArray) jsonObject.get("warpingbottomDetailsArray");
        AtomicInteger goods_return_master_id_atomic = new AtomicInteger(goods_return_master_id);
        try {

            CPtGoodsReturnsMasterModel cPtGoodsReturnsMasterModel = objectMapper
                    .readValue(GoodsReturnMasterData.toString(), CPtGoodsReturnsMasterModel.class);

            CPtGoodsReturnsMasterModel respGoodsReturnMasterData = iPtGoodsReturnsRepository.save(cPtGoodsReturnsMasterModel);

            List<CPtGoodsReturnsDetailsModel> cPtGoodsReturnsDetailsModels = objectMapper.readValue(GoodsReturnDetails.toString(),
                    new TypeReference<List<CPtGoodsReturnsDetailsModel>>() {
                    });

            cPtGoodsReturnsDetailsModels.forEach(item -> {
                item.setGoods_return_master_id(respGoodsReturnMasterData.getGoods_return_master_id());
            });

//            if (goods_return_master_id != 0 && !"A".equals(goods_return_master_status)) {
            if (goods_return_master_id != 0) {

                List<Integer> distinctGoodsReturnMasterIds = cPtGoodsReturnsDetailsModels.parallelStream()
                        .map(CPtGoodsReturnsDetailsModel::getGoods_return_master_id).distinct()
                        .collect(Collectors.toList());

                iPtGoodsReturnsDetailsRepository.updateGoodsReturnRecords(distinctGoodsReturnMasterIds);
            }


            iPtGoodsReturnsDetailsRepository.saveAll(cPtGoodsReturnsDetailsModels);
            if (!paymentTermsArray.isEmpty()) {
                iPtGoodsReturnsPaymentTermsRepository.updatePaymentTermsStatus(goods_return_master_id,
                        GoodsReturnMasterData.getInt("goods_version"), company_id);

                List<CPtGoodsReturnsPaymentTermsModel> tradingOrderPaymentTerms = objectMapper.readValue(
                        paymentTermsArray.toString(),
                        new TypeReference<List<CPtGoodsReturnsPaymentTermsModel>>() {
                        });
                tradingOrderPaymentTerms.forEach(paymentTerm -> {
                    paymentTerm.setGoods_version(cPtGoodsReturnsMasterModel.getGoods_version());
                    paymentTerm.setGoods_return_master_id(respGoodsReturnMasterData.getGoods_return_master_id());
                });
                iPtGoodsReturnsPaymentTermsRepository.saveAll(tradingOrderPaymentTerms);

            }

            if (!warpingbottomDetailsArray.isEmpty()) {
                iPtGoodsReturnBottomDetailsRepository.updateWarpingBottomStatus(goods_return_master_id, company_id);

                List<CPtGoodsReturnBottomDetailsModel> warpingBottomDetails = objectMapper.readValue(
                        warpingbottomDetailsArray.toString(),
                        new TypeReference<List<CPtGoodsReturnBottomDetailsModel>>() {
                        });
                warpingBottomDetails.forEach(bottomDetails -> {
                    bottomDetails.setGoods_return_master_id(respGoodsReturnMasterData.getGoods_return_master_id());
                });
                iPtGoodsReturnBottomDetailsRepository.saveAll(warpingBottomDetails);

            }

            if ("A".equals(goods_return_master_status)) {
//                List<CPtGoodsReturnsDetailsModel> goodsReturnsDetailsRecords = iPtGoodsReturnsDetailsRepository.FnShowIndentMaterialIssueDetailRecords(cPtGoodsReturnsMasterModel.getGoods_return_no(), cPtGoodsReturnsMasterModel.getCompany_id());
//                List<CPtGoodsReturnsDetailsModel> addIndentIssueDetails = new ArrayList<>();
//                cPtGoodsReturnsDetailsModels.forEach(item -> {
//                    Optional<CPtGoodsReturnsDetailsModel> cPtGoodsReturnsDetailsModel = goodsReturnsDetailsRecords.stream()
//                            .filter(issueRecord -> issueRecord.getProduct_rm_id().equals(item.getProduct_rm_id()) && issueRecord.getIssue_batch_no().equals(item.getIssue_batch_no())).findFirst();

//                    if (cPtGoodsReturnsDetailsModel.isPresent()) {
//                        CPtGoodsReturnsDetailsModel cPtGoodsReturnsDetailsModel1 = cPtGoodsReturnsDetailsModel.get();
//
//                        cPtGoodsReturnsDetailsModel1.setProduct_material_issue_return_quantity(item.getProduct_material_receipt_quantity());
//                        cPtGoodsReturnsDetailsModel1.setProduct_material_issue_return_weight(item.getProduct_material_receipt_weight());
//                        addIndentIssueDetails.add(cPtGoodsReturnsDetailsModel1);
//                    }
//                });
                if (warpingbottomDetailsArray != null && !warpingbottomDetailsArray.isEmpty()) {
                    List<Integer> warpingBottomStockDetailsIds = IntStream.range(0, warpingbottomDetailsArray.length())
                            .mapToObj(i -> warpingbottomDetailsArray.getJSONObject(i).getInt("warping_bottom_stock_id"))
                            .collect(Collectors.toList());

                    if (!warpingBottomStockDetailsIds.isEmpty()) {
                        iXtWarpingBottomStockRepository.updateWarpingBottomStockDetailsStatus(warpingBottomStockDetailsIds,goods_return_no,goods_return_date ,company_id);
                    }
                }
//				UPDATE STOCK DETAILS
                Map<String, Object> reduceStockResponse =  FnUpdateRawMaterialStockDetails(respGoodsReturnMasterData, cPtGoodsReturnsDetailsModels, cPtGoodsReturnsMasterModel.getCompany_id()); // STOCK ISSUE

                if (reduceStockResponse.containsKey("issuedBatchList")) {
                    responce.put("issuedBatchList", reduceStockResponse.get("issuedBatchList"));
                }


            }

            responce.put("success", 1);
            responce.put("data", respGoodsReturnMasterData);
            responce.put("error", "");
//            responce.put("message", goods_return_master_id_atomic.get() == 0 ? "Record added successfully!..." : "Record updated successfully!...");
            if ("A".equals(goods_return_master_status)) {
                responce.put("message", "Record Approved successfully!...");
            } else if (goods_return_master_id_atomic.get() == 0) {
                responce.put("message", "Record added successfully!...");
            } else {
                responce.put("message", "Record updated successfully!...");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/GoodsReturnsDetails/FnAddUpdateRecord",
                        sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/GoodsReturnsDetails/FnAddUpdateRecord", 0,
                    e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }


    private Map<String, Object> FnUpdateRawMaterialStockDetails(CPtGoodsReturnsMasterModel responseIndentIssueMaster, List<CPtGoodsReturnsDetailsModel> indentIssueDetails, int company_id) {

//		Get CurrentDate
        Date currentDate = new Date();
//	    Formatting the date to display only the date portion
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Object> stockDetails = new HashMap<>();

        //		Get distinct material ids
        List<String> distinctMaterialIds = indentIssueDetails.stream().map(CPtGoodsReturnsDetailsModel::getProduct_rm_id).distinct()
                .collect(Collectors.toList());
//        Map<String, Double> distinctBatchWithWeights = indentIssueDetails.stream()
//                .collect(Collectors.toMap(
//                        CPtGoodsReturnsDetailsModel::getIssue_batch_no,  // Key: issue_batch_no
//                        CPtGoodsReturnsDetailsModel::getCone_per_wt, // Value: cone_per_weight
//                        (existing, replacement) -> existing // Merge function to handle duplicates
//                ));
        Map<String, List<Double>> distinctBatchWithWeights = indentIssueDetails.stream()
                .collect(Collectors.groupingBy(
                        CPtGoodsReturnsDetailsModel::getIssue_batch_no,  // Key: issue_batch_no
                        Collectors.mapping(CPtGoodsReturnsDetailsModel::getCone_per_wt, Collectors.toList()) // Store weights as List
                ));

        // Extract batch numbers and weights
        List<String> batchNumbers = new ArrayList<>(distinctBatchWithWeights.keySet());
//        List<Double> weights = new ArrayList<>(distinctBatchWithWeights.values());
        List<Double> weights = distinctBatchWithWeights.values().stream()
                .flatMap(List::stream) // Flatten multiple weights into a single list
                .distinct()
                .collect(Collectors.toList());
        int GodownId = indentIssueDetails.isEmpty() ? 0 : Integer.parseInt(indentIssueDetails.get(0).getGodown_id());
//		Get the data from sm_product_rm_stock_details table for stock issue
        List<CSmProductRmStockDetailsModel> productStockDetailsList = !distinctMaterialIds.isEmpty() ? iSmProductRmStockDetailsRepository.FnGetAllProductRmStockRawMaterialsLotGodownwise(distinctMaterialIds, company_id, batchNumbers, weights,GodownId) : null;


        List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
        List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
        List<CSmProductStockTracking> addStockTracking = new ArrayList<>();

//		iterate on indent materials for stock issue
        indentIssueDetails.forEach(indentRecord -> {
//            AtomicReference<Double> goods_return_quantity = new AtomicReference<>(indentRecord.getGoods_return_quantity());
//            AtomicReference<Double> goods_return_weight = new AtomicReference<>(indentRecord.getGoods_return_weight());


//            Get only specific material stock
            List<CSmProductRmStockDetailsModel> getDistinctMaterialWiseStock = productStockDetailsList.stream()
                    .filter(item -> item.getProduct_rm_id().equals(indentRecord.getProduct_rm_id())
                                    && Objects.equals(item.getCompany_id(), company_id)
                                    && item.getClosing_balance_quantity() > 0
//
                                    && Objects.equals(item.getGodown_id(), Integer.parseInt(indentRecord.getGodown_id()))
                                    && Objects.equals(item.getGodown_section_id(), Integer.parseInt(indentRecord.getGodown_section_id())) // Fixed
                                    && Objects.equals(item.getGodown_section_beans_id(), Integer.parseInt(indentRecord.getGodown_section_beans_id()))
                                    && Objects.equals(item.getGoods_receipt_no(),indentRecord.getGoods_receipt_no())// Fixed
//                                    && Objects.equals(item.getBatch_no(), indentRecord.getIssue_batch_no())
                    )
                    .collect(Collectors.toList());
            AtomicReference<Double> goods_return_quantity = new AtomicReference<>(indentRecord.getGoods_return_quantity());
            AtomicReference<Double> goods_return_weight = new AtomicReference<>(indentRecord.getGoods_return_weight());
            AtomicReference<Integer> goods_return_boxes = new AtomicReference<>(indentRecord.getGoods_return_boxes());

            AtomicReference<Double> reducedQty = new AtomicReference<>();
            AtomicReference<Double> reducedWt = new AtomicReference<>();
            AtomicReference<Integer> reducedBox = new AtomicReference<>();

            getDistinctMaterialWiseStock.forEach(stockRecord -> {
                if (goods_return_quantity.get() > 0) {
                    double available_stock_quantity = stockRecord.getClosing_balance_quantity();
                    double available_stock_weight = stockRecord.getClosing_balance_weight();
                    Integer available_stock_Boxes = stockRecord.getClosing_no_of_boxes();

                    reducedQty.set(Math.min(goods_return_quantity.get(), available_stock_quantity));
                    reducedWt.set(Math.min(goods_return_weight.get(), available_stock_weight));
                    reducedBox.set(Math.min(goods_return_boxes.get(), available_stock_Boxes));

                    goods_return_quantity.set(goods_return_quantity.get() - reducedQty.get());
                    goods_return_weight.set(goods_return_weight.get() - reducedWt.get());
                    goods_return_boxes.set(goods_return_boxes.get() - reducedBox.get());

                    CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
                    CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();

//					if object is present in details then just increase the qty of existing object
                    Optional<CSmProductRmStockDetailsModel> getDetailsObject = addProductRmStockDetailsList.stream()
                            .filter(item -> item.getProduct_rm_id().equals(stockRecord.getProduct_rm_id()) &&
                                            Objects.equals(stockRecord.getGodown_id(), item.getGodown_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_id(), item.getGodown_section_id()) &&
                                            Objects.equals(stockRecord.getGodown_section_beans_id(), item.getGodown_section_beans_id())
                                    && item.getBatch_no().equals(stockRecord.getBatch_no())
//                                            && item.getGoods_receipt_no().equals(stockRecord.getGoods_receipt_no())
                            ).findFirst();

//                    CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
//                    CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();
//                    if (getDetailsObject.isPresent()) {
//                        productRmStockDetailsModel = getDetailsObject.get();
//                        productRmStockDetailsModel.setClosing_balance_quantity(productRmStockDetailsModel.getClosing_balance_quantity() + (-reducedQty.get()));
//                        productRmStockDetailsModel.setClosing_balance_weight(productRmStockDetailsModel.getClosing_balance_weight() + (-reducedWt.get()));
//                        productRmStockDetailsModel.setClosing_no_of_boxes(productRmStockDetailsModel.getClosing_no_of_boxes() + (-reducedBox.get()));
//                        productRmStockDetailsModel.setSupplier_return_quantity(productRmStockDetailsModel.getSupplier_return_quantity() + reducedQty.get());
//                        productRmStockDetailsModel.setSupplier_return_weight(productRmStockDetailsModel.getSupplier_return_weight() + reducedWt.get());
//                        productRmStockDetailsModel.setSupplier_return_boxes(productRmStockDetailsModel.getSupplier_return_boxes() + reducedBox.get());
//
//                    } else {
                        productRmStockDetailsModel.setGoods_receipt_no(stockRecord.getGoods_receipt_no());
                        productRmStockDetailsModel.setProduct_rm_id(indentRecord.getProduct_rm_id());
                        productRmStockDetailsModel.setProduct_type_id(responseIndentIssueMaster.getProduct_type_id());
//            productRmStockDetailsModel.setSupplier_id(responseIndentIssueMaster.getSupplier_id());
                        productRmStockDetailsModel.setGodown_id(Integer.parseInt(indentRecord.getGodown_id()));
                        productRmStockDetailsModel.setGodown_section_id(Integer.parseInt(indentRecord.getGodown_section_id()));
                        productRmStockDetailsModel.setGodown_section_beans_id(Integer.parseInt(indentRecord.getGodown_section_beans_id()));
                        productRmStockDetailsModel.setClosing_balance_quantity(-reducedQty.get());
                        productRmStockDetailsModel.setClosing_balance_weight(-reducedWt.get());
                        productRmStockDetailsModel.setClosing_no_of_boxes(-reducedBox.get());
                        productRmStockDetailsModel.setSupplier_return_quantity(reducedQty.get());
                        productRmStockDetailsModel.setSupplier_return_weight(reducedWt.get());
                        productRmStockDetailsModel.setSupplier_return_boxes(reducedBox.get());
                        productRmStockDetailsModel.setBatch_no(indentRecord.getIssue_batch_no());
                        productRmStockDetailsModel.setStock_date(responseIndentIssueMaster.getGoods_return_date());
                        productRmStockDetailsModel.setCompany_id(indentRecord.getCompany_id());
                        productRmStockDetailsModel.setCompany_branch_id(indentRecord.getCompany_branch_id());
                        productRmStockDetailsModel.setFinancial_year(indentRecord.getFinancial_year());
                        addProductRmStockDetailsList.add(productRmStockDetailsModel);

//                    }
//          STOCK TRACKING
//                    CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();
//
//                    cSmProductStockTracking.setCompany_id(indentRecord.getCompany_id());
//                    cSmProductStockTracking.setCompany_branch_id(indentRecord.getCompany_branch_id());
//                    cSmProductStockTracking.setFinancial_year(indentRecord.getFinancial_year());
//                    cSmProductStockTracking.setProduct_material_id(indentRecord.getProduct_rm_id());
//                    cSmProductStockTracking.setGoods_receipt_no(indentRecord.getGoods_receipt_no());
//                    cSmProductStockTracking.setConsumption_no(responseIndentIssueMaster.getGoods_return_no());
//                    cSmProductStockTracking.setConsumption_detail_no(responseIndentIssueMaster.getGoods_return_no());
//                    cSmProductStockTracking.setConsumption_date(new Date());
//                    cSmProductStockTracking.setConsumption_location("Indent Issue");
//                    cSmProductStockTracking.setConsumption_detail_no(String.valueOf(indentRecord.getGoods_return_details_id()));
//                    cSmProductStockTracking.setConsumption_quantity(goods_return_quantity.get());
//                    cSmProductStockTracking.setCreated_by(indentRecord.getCreated_by());
//
//                    addStockTracking.add(cSmProductStockTracking);  // Add into stock tracking list

//					if object is present in summary then just increase the qty of existing object
                    Optional<CSmProductRmStockSummaryModel> getSummaryObject = addProductRmStockSummaryList.stream()
                            .filter(item -> item.getProduct_rm_id().equals(indentRecord.getProduct_rm_id()) &&
                                    Objects.equals(Integer.parseInt(indentRecord.getGodown_id()), item.getGodown_id()) &&
                                    Objects.equals(Integer.parseInt(indentRecord.getGodown_section_id()), item.getGodown_section_id()) &&
                                    Objects.equals(Integer.parseInt(indentRecord.getGodown_section_beans_id()), item.getGodown_section_beans_id())
                            ).findFirst();

                    if (getSummaryObject.isPresent()) {
                        productRmStockSummaryModel = getSummaryObject.get();
                        productRmStockSummaryModel.setClosing_balance_quantity(productRmStockSummaryModel.getClosing_balance_quantity() + (-reducedQty.get()));
                        productRmStockSummaryModel.setClosing_balance_weight(productRmStockSummaryModel.getClosing_balance_weight() + (-reducedWt.get()));
                        productRmStockSummaryModel.setClosing_no_of_boxes(productRmStockSummaryModel.getClosing_no_of_boxes() + (-reducedBox.get()));
                        productRmStockSummaryModel.setSupplier_return_quantity(productRmStockSummaryModel.getSupplier_return_quantity() + reducedQty.get());
                        productRmStockSummaryModel.setSupplier_return_weight(productRmStockSummaryModel.getSupplier_return_weight() + reducedWt.get());
                        productRmStockSummaryModel.setSupplier_return_boxes(productRmStockSummaryModel.getSupplier_return_boxes() + reducedBox.get());

                    } else {

                        productRmStockSummaryModel.setCompany_id(indentRecord.getCompany_id());
                        productRmStockSummaryModel.setCompany_branch_id(indentRecord.getCompany_branch_id());
                        productRmStockSummaryModel.setProduct_type_id(responseIndentIssueMaster.getProduct_type_id());
//                productRmStockSummaryModel.setSupplier_id(responseIndentIssueMaster.getSupplier_id());
                        productRmStockSummaryModel.setFinancial_year(indentRecord.getFinancial_year());
                        productRmStockSummaryModel.setClosing_balance_quantity(-reducedQty.get());
                        productRmStockSummaryModel.setClosing_balance_weight(-reducedWt.get());
                        productRmStockSummaryModel.setClosing_no_of_boxes(reducedBox.get());
                        productRmStockSummaryModel.setSupplier_return_quantity(reducedQty.get());
                        productRmStockSummaryModel.setSupplier_return_weight(reducedWt.get());
                        productRmStockSummaryModel.setSupplier_return_boxes(reducedBox.get());
                        productRmStockSummaryModel.setProduct_rm_id(indentRecord.getProduct_rm_id());
                        productRmStockSummaryModel.setModified_by(indentRecord.getCreated_by());
                        productRmStockSummaryModel.setProduct_rm_id(indentRecord.getProduct_rm_id());
                        productRmStockSummaryModel.setGodown_id(Integer.parseInt(indentRecord.getGodown_id()));
                        productRmStockSummaryModel.setGodown_section_id(Integer.parseInt(indentRecord.getGodown_section_id()));
                        productRmStockSummaryModel.setGodown_section_beans_id(Integer.parseInt(indentRecord.getGodown_section_beans_id()));
                        addProductRmStockSummaryList.add(productRmStockSummaryModel);

                    }
                }
            });
        });
        stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
        stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
        stockDetails.put("StockTracking", addStockTracking);

        Map<String, Object> batchresponse = cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Decrease", company_id);

        return batchresponse;
    }


    @Override
    public Map<String, Object> FnDeleteRecord(int goods_return_master_id, int company_id,
                                              String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iPtGoodsReturnsRepository.FnDeleteGoodsReturnsMasterRecord(goods_return_master_id, company_id,
                    deleted_by);

            iPtGoodsReturnsDetailsRepository.FnDeleteGoodsReturnsDetailsRecord(goods_return_master_id, company_id,
                    deleted_by);
            iPtGoodsReturnsPaymentTermsRepository.FnDeleteGoodsReturnsDetailsRecord(goods_return_master_id, company_id,
                    deleted_by);

            responce.put("success", 1);
            responce.put("data", "");

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
    public Map<String, Object> FnShowParticularRecordForUpdate(int goods_return_master_id, int company_id) {
        Map<String, Object> responce = new HashMap<>();

        try {

            CPtGoodsReturnsMasterViewModel GoodReturnsMasterData =
                    iPtGoodsReturnsRepository.FnShowParticularRecordForUpdate(goods_return_master_id, company_id);

            List<CPtGoodsReturnsDetailsViewModel> GoodReturnsDetailsRecord =
                    iPtGoodsReturnsDetailsRepository.FnShowGoodsReturnsDetailRecord(goods_return_master_id, company_id);

            List<CPtGoodsReturnsPaymentTermsModel> goodsReturnPaymentTerms =
                    iPtGoodsReturnsPaymentTermsRepository.FnShowGoodsReturnPaymentTerms(goods_return_master_id, company_id);

            List<CPtGoodsReturnBottomDetailsModel> goodsReturnBottomDetails =
                    iPtGoodsReturnBottomDetailsRepository.FnShowGoodsReturnBottomDetails(goods_return_master_id, company_id);


            responce.put("success", 1);
            responce.put("GoodReturnsMasterData", GoodReturnsMasterData);
            responce.put("GoodReturnsDetailsRecord", GoodReturnsDetailsRecord);
            responce.put("goodsReturnsPaymentTerms", goodsReturnPaymentTerms);
            responce.put("goodsReturnBottomDetails", goodsReturnBottomDetails);


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
