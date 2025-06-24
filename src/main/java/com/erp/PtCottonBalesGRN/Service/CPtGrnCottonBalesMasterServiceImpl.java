package com.erp.PtCottonBalesGRN.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.CottonBalesMixingChart.Model.CCottonBalesMixingChartModel;
import com.erp.CottonBalesMixingChart.Respository.ICottonBalesMixingChartRepository;
import com.erp.PtCottonBalesGRN.Model.*;
import com.erp.PtCottonBalesGRN.Repository.*;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsModel;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderMasterModel;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderTaxSummaryModel;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderDetailsRepository;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderDetailsViewRepository;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderMasterRepository;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderTaxSummaryRepository;
import com.erp.SmMaterialStockManagement.Model.CSmMaterialStockLogModel;
import com.erp.SmMaterialStockManagement.Model.CSmProductMaterialStockNewModel;
import com.erp.SmMaterialStockManagement.Repository.ISmMaterialStockLogRepository;
import com.erp.SmMaterialStockManagement.Repository.ISmMaterialStockManagementRepository;
import com.erp.SmMaterialStockManagement.Service.ISmMaterialStockManagementService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.var;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CPtGrnCottonBalesMasterServiceImpl implements IPtGrnCottonBalesMasterService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IPtGrnCottonBalesMasterRepository iPtGrnCottonBalesMasterRepository;

    @Autowired
    IPtGrnCottonBalesDetailsRepository iPtGrnCottonBalesDetailsRepository;

    @Autowired
    IPtGrnCottonBalesOrderTaxSummaryRepository iPtGrnCottonBalesOrderTaxSummaryRepository;

    @Autowired
    IPtGrnCottonBalesPaymentTermsRepository iPtGrnCottonBalesPaymentTermsRepository;

    @Autowired
    ISmMaterialStockManagementService iSmMaterialStockManagementService;

    @Autowired
    IPtGrnCottonBalesDetailsViewRepository iPtGrnCottonBalesDetailsViewRepository;
    @Autowired
    IPtGrnCottonBalesMasterViewRepository iPtGrnCottonBalesMasterViewRepository;

    @Autowired
    IPtPurchaseOrderDetailsViewRepository iPtPurchaseOrderDetailsViewRepository;


    @Autowired
    IPtPurchaseOrderDetailsRepository iPtPurchaseOrderDetailsRepository;

    @Autowired
    IDocumentsRepository iDocumentsRepository;

    @Autowired
    IPtPurchaseOrderMasterRepository iPtPurchaseOrderMasterRepository;

    @Autowired
    IPtPurchaseOrderTaxSummaryRepository iPtPurchaseOrderTaxSummaryRepository;

    @Autowired
    ISmMaterialStockManagementRepository iSmMaterialStockManagementRepository;
    @Autowired
    ISmMaterialStockLogRepository iSmMaterialStockLogRepository;

    @Autowired
    ICottonBalesMixingChartRepository iCottonBalesMixingChartRepository;

    @Autowired
    private JdbcTemplate executeQuery;
//	@Override
//	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
//
//
//
//		return Map.of();
//	}

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        boolean update = false;

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int issue_flag = commonIdsObj.getInt("issue_flag");
        String goods_receipt_no = commonIdsObj.getString("goods_receipt_no");
        int goods_receipt_version = commonIdsObj.getInt("goods_receipt_version");
        String financial_year = commonIdsObj.getString("financial_year");
        String KeyForViewUpdate = commonIdsObj.getString("KeyForViewUpdate");
        String ApprovedName = commonIdsObj.getString("ApprovedByName");

        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray notesTaxSummaryArray = (JSONArray) jsonObject.get("TransTaxSummaryData");
        JSONArray paymentTermsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
//        Integer masterTransactionId = masterjson.getInt("grn_cotton_bales_master_transaction_id");

        try {
            String lastGeneratedBatchNo = "";
            CPtGrnCottonBalesMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
                    CPtGrnCottonBalesMasterModel.class);

            // Update Purchase Order's Supplier ID if its gets Changed in GRN
            String purchase_order_no = jsonModel.getPurchase_order_no();
            CPtPurchaseOrderMasterModel cPtPurchaseOrderMasterModel = iPtPurchaseOrderMasterRepository.FnFetchPO(purchase_order_no, company_id);
            if(cPtPurchaseOrderMasterModel.getSupplier_id() != jsonModel.getSupplier_id()){
                CPtPurchaseOrderTaxSummaryModel cPtPurchaseOrderTaxSummaryModel = iPtPurchaseOrderTaxSummaryRepository.FnFetchPO(purchase_order_no, company_id);

                cPtPurchaseOrderMasterModel.setSupplier_id(jsonModel.getSupplier_id());
                cPtPurchaseOrderMasterModel.setSupplier_city_id(jsonModel.getSupplier_city_id());
                cPtPurchaseOrderMasterModel.setSupplier_state_id(jsonModel.getSupplier_state_id());
                cPtPurchaseOrderMasterModel.setSupplier_contacts_ids(jsonModel.getSupplier_contacts_ids());
                iPtPurchaseOrderMasterRepository.save(cPtPurchaseOrderMasterModel);

                cPtPurchaseOrderTaxSummaryModel.setSupplier_id(jsonModel.getSupplier_id());
                cPtPurchaseOrderTaxSummaryModel.setSupplier_city_id(jsonModel.getSupplier_city_id());
                cPtPurchaseOrderTaxSummaryModel.setSupplier_state_id(jsonModel.getSupplier_state_id());
                cPtPurchaseOrderTaxSummaryModel.setSupplier_contacts_ids(jsonModel.getSupplier_contacts_ids());
                iPtPurchaseOrderTaxSummaryRepository.save(cPtPurchaseOrderTaxSummaryModel);
            }

            CPtGrnCottonBalesMasterModel responceGrnCottonBalesMasterTrading = iPtGrnCottonBalesMasterRepository
                    .save(jsonModel);

            String GRNNo = commonIdsObj.getString("goods_receipt_no").replaceAll("/", "_");
            List<String> groupIds = Collections.singletonList(GRNNo);
            iDocumentsRepository.updateDocActive(groupIds);



            if (KeyForViewUpdate.equals("")) {
                lastGeneratedBatchNo = iPtGrnCottonBalesDetailsRepository.FnGetLastLotNo(company_id);
                if (lastGeneratedBatchNo == null || lastGeneratedBatchNo.isEmpty()) {
                    lastGeneratedBatchNo = "0";
                }
            }

            List<CPtGrnCottonBalesDetailsModel> cPtGrnCottonBalesDetailsModels = objectMapper
                    .readValue(detailsArray.toString(), new TypeReference<List<CPtGrnCottonBalesDetailsModel>>() {
                    });


            var finalLastGeneratedBatchNo = 0;
            if (KeyForViewUpdate.equals("")) {
                finalLastGeneratedBatchNo = Integer.parseInt(lastGeneratedBatchNo) + 1;
            }


            for (int i = 0; i < cPtGrnCottonBalesDetailsModels.size(); i++) {
                CPtGrnCottonBalesDetailsModel item = cPtGrnCottonBalesDetailsModels.get(i);
                if (KeyForViewUpdate.equals("")) {
                    item.setBatch_no(String.valueOf(finalLastGeneratedBatchNo + i));
                }
                item.setGoods_receipt_version(jsonModel.getGoods_receipt_version());
                item.setGrn_cotton_bales_master_transaction_id(
                        responceGrnCottonBalesMasterTrading.getGrn_cotton_bales_master_transaction_id()
                );
            }

//            List<CPtGrnCottonBalesDetailsModel> approvedCottonBalesGRNDetails =
//                    iPtGrnCottonBalesDetailsRepository.FnShowPerticularGRNCottonBalesDetailsRecords(
//                            goods_receipt_no
////                            company_id
//                    );
//            List<CPtGrnCottonBalesDetailsModel> cPtGrnCottonBalesDetailsModelsCopy = approvedCottonBalesGRNDetails
//                    .stream()
//                    .map(original -> {
//                        CPtGrnCottonBalesDetailsModel copy = new CPtGrnCottonBalesDetailsModel();
//                        BeanUtils.copyProperties(original, copy);
//                        return copy;
//                    })
//                    .collect(Collectors.toList());
            List<Object[]> approvedCottonBalesGRNDetailsArray =
                    iPtGrnCottonBalesDetailsRepository.FnShowPerticularGRNCottonBalesDetailsRecords(goods_receipt_no);


            cPtGrnCottonBalesDetailsModels = iPtGrnCottonBalesDetailsRepository.saveAll(cPtGrnCottonBalesDetailsModels);


//			 Goods Receipts Notes Tax Summary
            iPtGrnCottonBalesOrderTaxSummaryRepository.updateStatus(goods_receipt_no,
//                    masterjson.getInt("goods_receipt_version"),
                    financial_year, company_id);

            List<CPtGrnCottonBalesOrderTaxSummaryModel> cPtGrnCottonBalesOrderTaxSummaryModels = objectMapper
                    .readValue(notesTaxSummaryArray.toString(),
                            new TypeReference<List<CPtGrnCottonBalesOrderTaxSummaryModel>>() {
                            });

            cPtGrnCottonBalesOrderTaxSummaryModels.forEach(items -> {
                items.setGoods_receipt_version(jsonModel.getGoods_receipt_version());
                items.setGrn_cotton_bales_master_transaction_id(
                        responceGrnCottonBalesMasterTrading.getGrn_cotton_bales_master_transaction_id());
            });
            iPtGrnCottonBalesOrderTaxSummaryRepository.saveAll(cPtGrnCottonBalesOrderTaxSummaryModels);

//   			 Goods Receipts Payment Terms
            iPtGrnCottonBalesPaymentTermsRepository.updateStatus(goods_receipt_no,
//                    masterjson.getInt("goods_receipt_version"),
                    financial_year, company_id);

            List<CPtGrnCottonBalesPaymentTermsModel> cPtGrnCottonBalesPaymentTermsModels = objectMapper.readValue(
                    paymentTermsArray.toString(), new TypeReference<List<CPtGrnCottonBalesPaymentTermsModel>>() {
                    });

            cPtGrnCottonBalesPaymentTermsModels.forEach(items -> {
                items.setGoods_receipt_version(jsonModel.getGoods_receipt_version());
                items.setGrn_cotton_bales_master_transaction_id(
                        responceGrnCottonBalesMasterTrading.getGrn_cotton_bales_master_transaction_id());
            });

            iPtGrnCottonBalesPaymentTermsRepository.saveAll(cPtGrnCottonBalesPaymentTermsModels);

//				update purchase order master & details status
            FnUpdatePurchaseOrderStatus(responceGrnCottonBalesMasterTrading, company_id);

            //Only material Rate Updation in Mixing Chart , Quality Testing, Stock & Stock Log Tables

            if (issue_flag == 1) {
                List<String> distinctBatchNo = cPtGrnCottonBalesDetailsModels.stream()
                        .map(CPtGrnCottonBalesDetailsModel::getBatch_no)
                        .distinct().collect(Collectors.toList());

                List<CSmMaterialStockLogModel> stockLogModelsForGRN = iCottonBalesMixingChartRepository.FnGetStockLogDetailsAgainstLotNos(distinctBatchNo, company_id);
                List<CSmProductMaterialStockNewModel> cSmProductMaterialStockNewModelsForGRN = iCottonBalesMixingChartRepository.FnGetStockDetailsAgainstLotNos(distinctBatchNo, company_id);

                // Step 1: Create a lookup map from GRN batch_no to batch_rate
                Map<String, Double> lotRateMap = cPtGrnCottonBalesDetailsModels.stream()
                        .collect(Collectors.toMap(CPtGrnCottonBalesDetailsModel::getBatch_no, CPtGrnCottonBalesDetailsModel::getMaterial_rate));

                // Step 2: Directly update matching log entries
                for (CSmMaterialStockLogModel logModel : stockLogModelsForGRN) {
                    Double rate = lotRateMap.get(logModel.getBatch_no());
                    if (rate != null) {
                        logModel.setBatch_rate(rate);
                    }
                }

                for (CSmProductMaterialStockNewModel stockNewModel : cSmProductMaterialStockNewModelsForGRN){
                    Double rate = lotRateMap.get(stockNewModel.getBatch_no());

                    if (rate != null){
                        stockNewModel.setBatch_rate(rate);
                    }
                }

                iSmMaterialStockManagementRepository.saveAll(cSmProductMaterialStockNewModelsForGRN);
                iSmMaterialStockLogRepository.saveAll(stockLogModelsForGRN);

            } else {
                // Updating Stock Table From GRN
                if (KeyForViewUpdate.equals("approve") || (KeyForViewUpdate.equals("update") && jsonModel.getGoods_receipt_status().equals("G"))) {

                    //  ************************************ Stock Update functionality after Approve Done___________

                    if (KeyForViewUpdate.equals("update") && jsonModel.getGoods_receipt_status().equals("G")) {

//                    Map<String, CPtGrnCottonBalesDetailsModel> previousDataMap = new HashMap<>();
//                    for (CPtGrnCottonBalesDetailsModel prevData : approvedCottonBalesGRNDetailsArray) {
//                        String key = prevData.getProduct_material_id() + "_" + prevData.getBatch_no();
//                        previousDataMap.put(key, prevData);
//                    }
                        Map<String, Object[]> previousDataMap = new HashMap<>();
                        for (Object[] row : approvedCottonBalesGRNDetailsArray) {
                            // Replace 0 and 1 with actual indexes of `product_material_id` and `batch_no`
                            String productMaterialId = String.valueOf(row[0]); // assuming index 0
                            String batchNo = String.valueOf(row[1]);           // assuming index 1

                            String key = productMaterialId + "_" + batchNo;
                            previousDataMap.put(key, row);
                        }
                        List<String> distinctBatchNo = cPtGrnCottonBalesDetailsModels.stream()
                                .map(CPtGrnCottonBalesDetailsModel::getBatch_no)
                                .distinct().collect(Collectors.toList());

                        List<String> distinctMaterialIds = cPtGrnCottonBalesDetailsModels.stream()
                                .map(CPtGrnCottonBalesDetailsModel::getProduct_material_id)
                                .distinct().collect(Collectors.toList());

                        List<CSmProductMaterialStockNewModel> stockModels = iPtGrnCottonBalesDetailsRepository
                                .FnGetAllCottonBalesStockDetails(distinctMaterialIds, company_id, distinctBatchNo);

                        List<CSmMaterialStockLogModel> stockLogModels = iPtGrnCottonBalesDetailsRepository
                                .FnGetAllCottonBalesStockLogDetails(distinctMaterialIds, company_id, distinctBatchNo);

                        Timestamp currentTimestamp = Timestamp.valueOf(LocalDateTime.now());

                        List<CSmProductMaterialStockNewModel> updatedStockModels = new ArrayList<>();

                        for (CPtGrnCottonBalesDetailsModel approvedCottonBalesGRNItem : cPtGrnCottonBalesDetailsModels) {
                            String key = approvedCottonBalesGRNItem.getProduct_material_id() + "_" + approvedCottonBalesGRNItem.getBatch_no();
                            double previousQTY = 0.0;
                            double previousWeight = 0.0;

//                        if (previousDataMap.containsKey(key)) {
//                            CPtGrnCottonBalesDetailsModel prevData = previousDataMap.get(key);
//                            previousQTY = prevData.getProduct_material_grn_accepted_quantity();
//                            previousWeight = prevData.getProduct_material_grn_accepted_weight();
//                        }
                            if (previousDataMap.containsKey(key)) {
                                Object[] prevData = previousDataMap.get(key);

                                // Replace 2 and 3 with the correct indexes based on your SELECT query
                                if (prevData[2] != null) {
                                    previousQTY = Double.parseDouble(prevData[2].toString());
                                }
                                if (prevData[3] != null) {
                                    previousWeight = Double.parseDouble(prevData[3].toString());
                                }
                            }

                            double updatedQTY = approvedCottonBalesGRNItem.getProduct_material_grn_accepted_quantity();
                            double updatedWeight = approvedCottonBalesGRNItem.getProduct_material_grn_accepted_weight();
                            double quantityDiff = updatedQTY - previousQTY;
                            double weightDiff = updatedWeight - previousWeight;


                            for (CSmProductMaterialStockNewModel stockModel : stockModels) {
                                if (stockModel.getProduct_material_id().equals(approvedCottonBalesGRNItem.getProduct_material_id()) &&
                                        stockModel.getBatch_no().equals(approvedCottonBalesGRNItem.getBatch_no())) {

                                    CSmProductMaterialStockNewModel matchedModel =
                                            iSmMaterialStockManagementRepository.fnGetdataBasedonPrmIdBatchNo(
                                                    stockModel.getProduct_material_id(),
                                                    stockModel.getBatch_no()
                                            );

                                    if (matchedModel != null) {
                                        stockModel.setStock_transaction_id(matchedModel.getStock_transaction_id());
                                        stockModel.setPurchase_quantity(stockModel.getPurchase_quantity() + quantityDiff);
                                        stockModel.setPurchase_weight(stockModel.getPurchase_weight() + weightDiff);
                                        stockModel.setClosing_balance_weight(stockModel.getClosing_balance_weight() + weightDiff);
                                        stockModel.setClosing_balance_quantity(stockModel.getClosing_balance_quantity() + quantityDiff);
                                        stockModel.setModified_by(matchedModel.getModified_by());
                                        stockModel.setBatch_rate(approvedCottonBalesGRNItem.getMaterial_rate());
                                        stockModel.setModified_on(currentTimestamp);
                                        updatedStockModels.add(stockModel);
                                    }
                                }
                            }
                        }
                        for (CSmMaterialStockLogModel logModel : stockLogModels) {
                            for (CPtGrnCottonBalesDetailsModel approvedItem : cPtGrnCottonBalesDetailsModels) {
                                if (logModel.getProduct_material_id().equals(approvedItem.getProduct_material_id()) &&
                                        logModel.getBatch_no().equals(approvedItem.getBatch_no())) {

                                    double previousQTY = 0.0;
                                    double previousWeight = 0.0;
                                    String key = approvedItem.getProduct_material_id() + "_" + approvedItem.getBatch_no();

//                                if (previousDataMap.containsKey(key)) {
//                                    CPtGrnCottonBalesDetailsModel prevData = previousDataMap.get(key);
//                                    previousQTY = prevData.getProduct_material_grn_accepted_quantity();
//                                    previousWeight = prevData.getProduct_material_grn_accepted_weight();
//                                }
                                    if (previousDataMap.containsKey(key)) {
                                        Object[] prevData = previousDataMap.get(key);

                                        // Replace 2 and 3 with the correct indexes based on your SELECT query
                                        if (prevData[2] != null) {
                                            previousQTY = Double.parseDouble(prevData[2].toString());
                                        }
                                        if (prevData[3] != null) {
                                            previousWeight = Double.parseDouble(prevData[3].toString());
                                        }
                                    }

                                    double updatedQTY = approvedItem.getProduct_material_grn_accepted_quantity();
                                    double updatedWeight = approvedItem.getProduct_material_grn_accepted_weight();
                                    double quantityDiff = updatedQTY - previousQTY;
                                    double weightDiff = updatedWeight - previousWeight;

                                    logModel.setQuantity(logModel.getQuantity() + quantityDiff);
                                    logModel.setWeight(logModel.getWeight() + weightDiff);
                                    logModel.setBatch_rate(approvedItem.getMaterial_rate());
                                    logModel.setModified_on(currentTimestamp);
                                }
                            }
                        }


                        if (!updatedStockModels.isEmpty()) {
                            iSmMaterialStockManagementRepository.saveAll(updatedStockModels);
                            iSmMaterialStockLogRepository.saveAll(stockLogModels);

                        }
                    } else {

                        //  ************************************ Stock Functionality After Approve___________

                        List<CSmProductMaterialStockNewModel> stockModels = new ArrayList<>();
                        List<CSmMaterialStockLogModel> stockLogModels = new ArrayList<>();

                        cPtGrnCottonBalesDetailsModels.stream().forEach(grndata -> {

                            //For Product Material Stock
                            Optional<CSmProductMaterialStockNewModel> firstMatchingModel = stockModels.stream()
                                    .filter(stockModel -> stockModel.getProduct_material_id().trim().equals(grndata.getProduct_material_id().trim()) &&
                                            stockModel.getBatch_no().trim().equals(grndata.getBatch_no().trim()))
                                    .findFirst();

                            firstMatchingModel.ifPresentOrElse(stockModel -> {
                                stockModel.setPurchase_weight(stockModel.getPurchase_weight() + grndata.getProduct_material_grn_accepted_weight());
                                stockModel.setPurchase_quantity(stockModel.getPurchase_quantity() + grndata.getProduct_material_grn_accepted_quantity());
                                stockModel.setClosing_balance_weight(stockModel.getClosing_balance_weight() + grndata.getProduct_material_grn_accepted_weight());
                                stockModel.setClosing_balance_quantity(stockModel.getClosing_balance_quantity() + grndata.getProduct_material_grn_accepted_quantity());
                            }, () -> {
                                CSmProductMaterialStockNewModel cSmProductMaterialStockNewModel = new CSmProductMaterialStockNewModel();
                                cSmProductMaterialStockNewModel.setCompany_id(grndata.getCompany_id());
                                cSmProductMaterialStockNewModel.setCompany_branch_id(grndata.getCompany_branch_id());
                                cSmProductMaterialStockNewModel.setFinancial_year(grndata.getFinancial_year());
                                cSmProductMaterialStockNewModel.setProduct_type_group(grndata.getGoods_receipt_type());
                                cSmProductMaterialStockNewModel.setProduct_type_id(grndata.getGoods_receipt_type_id());
                                cSmProductMaterialStockNewModel.setProduct_material_unit_id(grndata.getProduct_material_unit_id());
                                cSmProductMaterialStockNewModel.setProduct_material_id(grndata.getProduct_material_id());
                                cSmProductMaterialStockNewModel.setProduct_material_packing_id(grndata.getProduct_material_packing_id());
                                cSmProductMaterialStockNewModel.setBatch_no(grndata.getBatch_no());
                                cSmProductMaterialStockNewModel.setSupplier_batch_no(grndata.getSupplier_batch_no());
                                cSmProductMaterialStockNewModel.setGodown_id(grndata.getGodown_id());
                                cSmProductMaterialStockNewModel.setGoods_receipt_no(grndata.getGoods_receipt_no());
                                cSmProductMaterialStockNewModel.setBatch_rate(grndata.getMaterial_rate());
                                cSmProductMaterialStockNewModel.setPurchase_weight(grndata.getProduct_material_grn_accepted_weight());
                                cSmProductMaterialStockNewModel.setPurchase_quantity(grndata.getProduct_material_grn_accepted_quantity());
                                cSmProductMaterialStockNewModel.setClosing_balance_weight(grndata.getProduct_material_grn_accepted_weight());
                                cSmProductMaterialStockNewModel.setClosing_balance_quantity(grndata.getProduct_material_grn_accepted_quantity());

                                stockModels.add(cSmProductMaterialStockNewModel);
                            });

                            // For Product Material Stock Log Every Add new Entry from GRN Details Data
                            CSmMaterialStockLogModel cSmMaterialStockLogModel = new CSmMaterialStockLogModel();
                            cSmMaterialStockLogModel.setCompany_id(grndata.getCompany_id());
                            cSmMaterialStockLogModel.setCompany_branch_id(grndata.getCompany_branch_id());
                            cSmMaterialStockLogModel.setFinancial_year(grndata.getFinancial_year());
                            cSmMaterialStockLogModel.setTransaction_date(grndata.getGoods_receipt_date());
                            cSmMaterialStockLogModel.setTransaction_type("Purchase");
                            cSmMaterialStockLogModel.setTransaction_no(grndata.getGoods_receipt_no());
                            cSmMaterialStockLogModel.setProduct_type_id(grndata.getGoods_receipt_type_id());
                            cSmMaterialStockLogModel.setProduct_category1_id(grndata.getProduct_category1_id());
                            cSmMaterialStockLogModel.setProduct_category2_id(grndata.getProduct_category2_id());
                            cSmMaterialStockLogModel.setProduct_material_unit_id(grndata.getProduct_material_unit_id());
                            cSmMaterialStockLogModel.setProduct_material_id(grndata.getProduct_material_id());
                            cSmMaterialStockLogModel.setBatch_no(grndata.getBatch_no());
                            cSmMaterialStockLogModel.setBatch_rate(grndata.getMaterial_rate());
                            cSmMaterialStockLogModel.setWeight(grndata.getProduct_material_grn_accepted_weight());
                            cSmMaterialStockLogModel.setQuantity(grndata.getProduct_material_grn_accepted_quantity());
                            cSmMaterialStockLogModel.setNo_of_boxes(0);
                            cSmMaterialStockLogModel.setGodown_id(grndata.getGodown_id());
                            cSmMaterialStockLogModel.setGodown_section_id(0);
                            cSmMaterialStockLogModel.setGodown_section_beans_id(0);
                            cSmMaterialStockLogModel.setCreated_on(responceGrnCottonBalesMasterTrading.getApproved_date());
                            cSmMaterialStockLogModel.setCreated_by(ApprovedName);

                            stockLogModels.add(cSmMaterialStockLogModel);
                        });
                        Map<String, Object> grnStockdata = new HashMap<>();
                        grnStockdata.put("GRNStockModels", stockModels);
                        grnStockdata.put("GRNActivityLogModels", stockLogModels);
                        grnStockdata.put("ApprovedName", ApprovedName);
                        iSmMaterialStockManagementService.FnGRNAddUpdateRecord(grnStockdata);

                    }
                }

            }

            responce.put("message",
                    KeyForViewUpdate.equals("update") ? "Record Updated successfully!" :
                            (KeyForViewUpdate.equals("approve") ? "Record Approved successfully!" : "Record Added successfully!")
            );
            responce.put("data", responceGrnCottonBalesMasterTrading);
            responce.put("success", 1);
            responce.put("error", "");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtGrnCottonBalesMaster/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                responce.put("success", 0);
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtGrnCottonBalesMaster/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", 0);
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnDeleteRecord(int grn_cotton_bales_master_transaction_id, String UserName) {
        return null;
    }


//	private void FnUpdatePurchaseOrderStatus(CPtGrnCottonBalesMasterModel responseGRNMasterTrading, int company_id) {
//		List<String> inCompleteMaterialPOs = new ArrayList<>();
//
//		int masterId = responseGRNMasterTrading.getGrn_cotton_bales_master_transaction_id();
//		// Retrieve the list of goods receipt details based on the master transaction id
//		List<CPtGrnCottonBalesDetailsViewModel> responseGRNCottonBalesDetails = iPtGrnCottonBalesDetailsViewRepository
//				.getAllDetails(masterId);
//
//		// Get distinct purchase order numbers
//		List<String> distinctPurchaseOrderNumbers = responseGRNCottonBalesDetails.stream()
//				.map(CPtGrnCottonBalesDetailsViewModel::getPurchase_order_no).distinct().collect(Collectors.toList());
//
//		// Retrieve the list of goods receipt details based on the master transaction id
//		List<CPtPurchaseOrderDetailsViewModel> responsePurchaseDetails = iPtPurchaseOrderDetailsViewRepository
//				.FngetExistingPurchaseOrderDetails(distinctPurchaseOrderNumbers, company_id);
//
//		// Retrieve the previous list of goods receipt details based on PO nos
//		List<CPtGrnCottonBalesDetailsViewModel> existingGoodReceiptDetails = iPtGrnCottonBalesDetailsViewRepository
//				.getAllExistingGRNPODetails(distinctPurchaseOrderNumbers, company_id);
//
//		// Create a set of distinct composite keys from the goods receipt details where the condition is met
//		Set<String> distinctKeys = existingGoodReceiptDetails.stream()
//				.map(goodReceipt -> goodReceipt.getProduct_material_id() + ":" + goodReceipt.getPurchase_order_no())
//				.collect(Collectors.toSet());
//
//		// Create a map with the distinct composite keys
//		Map<String, Boolean> goodsReceiptDetailsMap = distinctKeys.stream()
//				.collect(Collectors.toMap(
//						key -> key,
//						key -> true
//				));
//
//		// Filter purchase order details to only include those not in the goods receipt details map
//		List<CPtPurchaseOrderDetailsViewModel> filteredPurchaseDetails = responsePurchaseDetails.stream()
//				.filter(purchaseOrder -> !goodsReceiptDetailsMap.containsKey(purchaseOrder.getProduct_material_id() + ":" + purchaseOrder.getPurchase_order_no()))
//				.collect(Collectors.toList());
//
//
//		if (responseGRNMasterTrading.isIs_preeclosed()) {
//			System.out.println("Material Item Status: (Z-PreeClosed)");
//
//			responseGRNCottonBalesDetails.forEach(material -> {
//				iPtGrnCottonBalesDetailsRepository.FnUpdatePoMaterialStatusAndQty("Z",
//						material.getPree_closed_grn_quantity(), material.getPree_closed_grn_weight(),
//						material.getPurchase_order_details_transaction_id());
//			});
//
//			// Update each distinct purchase order number status
//			distinctPurchaseOrderNumbers.forEach(poNumber -> {
//				System.out.println("Material Item Status: (Z-PreeClosed) for PO: " + poNumber);
//				iPtGrnCottonBalesDetailsRepository.FnUpdateAllPoMaterialStatus("Z", poNumber);
//				iPtGrnCottonBalesDetailsRepository.FnUpdatePoStatus("Z", poNumber);
//			});
//		} else {
//			// Update material status for each item
//			responseGRNCottonBalesDetails.forEach(item -> {
//
//				// Calculate the remaining quantity for the current item
//				double materialRemainingQty = item.getProduct_material_po_approved_quantity() - item.getPrev_grn_bales_quantity();
//				double materialRemainingWt = item.getProduct_material_po_approved_weight() - item.getPrev_grn_bales_weight();
//				if (materialRemainingQty == 0 || materialRemainingQty < 0) {
//					System.out.println("Material Item Status: (C-Completed): " + materialRemainingQty);
//					iPtGrnCottonBalesDetailsRepository.FnUpdatePoMaterialStatus("C", materialRemainingQty,
//							materialRemainingWt, item.getPurchase_order_details_transaction_id());
//				} else if (materialRemainingQty > 0) {
//					inCompleteMaterialPOs.add(item.getPurchase_order_no());
//					System.out.println("Material Item Status: (I-Partial GRN): " + materialRemainingQty);
//					iPtGrnCottonBalesDetailsRepository.FnUpdatePoMaterialStatus("I", materialRemainingQty,
//							materialRemainingWt, item.getPurchase_order_details_transaction_id());
//				}
//			});
//
//			// Update PO Master status
//			distinctPurchaseOrderNumbers.forEach(poNumber -> {
//				if (inCompleteMaterialPOs.contains(poNumber) || !filteredPurchaseDetails.isEmpty()) {
//					// Update status to "I" for incomplete material POs
//					iPtGrnCottonBalesDetailsRepository.FnUpdatePoStatus("I", poNumber);
//				} else {
//					// Update status to "C" for complete material POs
//					iPtGrnCottonBalesDetailsRepository.FnUpdatePoStatus("C", poNumber);
//				}
//			});
//
//		}
//	}

    private void FnUpdatePurchaseOrderStatus(CPtGrnCottonBalesMasterModel responseGRNMasterTrading, int company_id) {
        // Fetch PO details
        ArrayList<CPtPurchaseOrderDetailsModel> poDetailsModels =
                iPtPurchaseOrderDetailsRepository.fnGetPoModelsAgainstPONo(
                        responseGRNMasterTrading.getPurchase_order_no(), company_id);

        // Store PO quantities mapped by Material ID
        Map<Integer, Double> poDetailsQtyAgainstMaterial = new HashMap<>();
        poDetailsModels.forEach(po ->
                poDetailsQtyAgainstMaterial.merge(
                        po.getPurchase_order_details_transaction_id(),
                        po.getProduct_material_po_approved_quantity(),
                        Double::sum
                )
        );

        // Fetch GRN details
        ArrayList<CPtGrnCottonBalesDetailsModel> grnDetailsModels =
                iPtGrnCottonBalesDetailsRepository.fnGetGRNModelsAgainstPONo(
                        responseGRNMasterTrading.getPurchase_order_no(), company_id);

        // Store the sum of GRN-approved quantities by Material ID
        Map<Integer, Double> grnQtyAgainstMaterial = new HashMap<>();
        grnDetailsModels.forEach(grn ->
                grnQtyAgainstMaterial.merge(
                        grn.getPurchase_order_details_transaction_id(),
                        grn.getProduct_material_grn_accepted_quantity(),
                        Double::sum
                )
        );

        // List to store PO details and master IDs
        List<Integer> poDetailsIds = new ArrayList<>();


        // First, collect poDetailsIds based on condition
        poDetailsModels.forEach(po -> {
            Integer poDetailsId = po.getPurchase_order_details_transaction_id();
            double totalGRNQty = grnQtyAgainstMaterial.getOrDefault(poDetailsId, 0.0);
            double totalPOQty = poDetailsQtyAgainstMaterial.getOrDefault(poDetailsId, 0.0);

            if (totalGRNQty == totalPOQty || totalGRNQty > totalPOQty) {
                poDetailsIds.add(po.getPurchase_order_details_transaction_id());
            }
        });

        // Check if all materials satisfy the condition
        boolean allSatisfied = poDetailsModels.stream().allMatch(po -> {
            Integer poDetailsId = po.getPurchase_order_details_transaction_id();
            double totalGRNQty = grnQtyAgainstMaterial.getOrDefault(poDetailsId, 0.0);
            double totalPOQty = poDetailsQtyAgainstMaterial.getOrDefault(poDetailsId, 0.0);

            return (totalGRNQty > totalPOQty) || (totalGRNQty == totalPOQty); // Explicit OR condition
        });


        // If all conditions are satisfied, add master transaction ID
        if (!poDetailsModels.isEmpty()) {
            Integer poMasterId = poDetailsModels.get(0).getPurchase_order_master_transaction_id();
            iPtGrnCottonBalesDetailsRepository.FnUpdatePoMasterStatus(poMasterId, allSatisfied ? "C" : "I");
        }

        if (!poDetailsIds.isEmpty()) {
            iPtGrnCottonBalesDetailsRepository.FnUpdatePoMaterialStatus(poDetailsIds, company_id);
        }
    }


//	@Override
//	public Map<String, Object> FnDeleteRecord(int grn_cotton_bales_master_transaction_id, String UserName) {
//		Map<String, Object> responce = new HashMap<>();
//		try {
//			iPtGrnCottonBalesMasterRepository.deleteGRNCottonBalesMaster(grn_cotton_bales_master_transaction_id, UserName);
//			iPtGrnCottonBalesDetailsRepository.deleteGRNCottonBalesDetails(grn_cotton_bales_master_transaction_id, UserName);
//			iPtGrnCottonBalesOrderTaxSummaryRepository
//					.deleteGRNCottonBalesNotesTaxSummary(grn_cotton_bales_master_transaction_id, UserName);
//			iPtGrnCottonBalesPaymentTermsRepository.deleteGRNCottonBalesPaymentTerms(grn_cotton_bales_master_transaction_id, UserName);
////			iPtGoodsReceiptIndentDetailsRepository.deleteIndentGRNDetails(grn_cotton_bales_master_transaction_id, UserName);
//			responce.put("success", "1");
//			responce.put("data", "");
//			responce.put("error", "");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", "");
//		}
//		return responce;
//	}

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String goods_receipt_no, int goods_receipt_version,
                                                                     String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            Map<String, Object> grnCottonBalesMasterRecords = iPtGrnCottonBalesMasterRepository
                    .FnShowGRNCottonBalesMasterRecord(goods_receipt_no, goods_receipt_version, financial_year,
                            company_id);

            List<CPtGrnCottonBalesDetailsViewModel> grnCottonBalesDetailsRecords = iPtGrnCottonBalesDetailsRepository
                    .FnShowGRNCottonBalesDetailsRecords(goods_receipt_no, goods_receipt_version, financial_year,
                            company_id);

            List<CPtGrnCottonBalesOrderTaxSummaryModel> grnCottonBalesTaxSummaryRecords = iPtGrnCottonBalesOrderTaxSummaryRepository
                    .FnShowGRNCottonBalesTaxSummaryRecords(goods_receipt_no, goods_receipt_version, financial_year,
                            company_id);

            List<CPtGrnCottonBalesPaymentTermsModel> grnCottonBalesPaymentTermsRecords = iPtGrnCottonBalesPaymentTermsRepository
                    .FnShowGRNCottonBalesPaymentTermsRecords(goods_receipt_no, goods_receipt_version, financial_year,
                            company_id);
//			List<CptGoodsReceiptIndentDetailsViewModel> goodsReceiptsNotesIndentDetailsRecords = iPtGoodsReceiptIndentDetailsViewRepository.FnShowGoodsReceiptNoteIndentDetailsRecords(goods_receipt_no, goods_receipt_version, financial_year,
//					company_id);

            responce.put("GRNCottonBalesMasterRecord", grnCottonBalesMasterRecords);
            responce.put("GRNCottonBalesDetailsRecords", grnCottonBalesDetailsRecords);
            responce.put("GRNCottonBalesTaxSummaryRecords", grnCottonBalesTaxSummaryRecords);
            responce.put("GRNCottonBalesPaymentTermsRecords", grnCottonBalesPaymentTermsRecords);
//			responce.put("GoodsReceiptsNotesIndentDetailsRecords", goodsReceiptsNotesIndentDetailsRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowPurchaseOrderDetailsRecords(JSONObject purchaseOrderNo) {
        Map<String, Object> response = new HashMap<>();
        try {
            JSONObject jsonObject = purchaseOrderNo.getJSONObject("Ids");
            int company_id = jsonObject.getInt("company_id");
            int expected_branch_id = jsonObject.getInt("expected_branch_id");

            JSONArray purchaseOrderNos = purchaseOrderNo.getJSONArray("purchase_order_nos");
            List<Map<String, Object>> fetchdata = new ArrayList<>();
            List<Map<String, Object>> paymentData = new ArrayList<>();
            if (!purchaseOrderNos.isEmpty()) {
                List<Object> queryParams = new ArrayList<>();
                String view = "ptv_purchase_order_details";

                StringBuilder query = new StringBuilder("SELECT * FROM ")
                        .append(view)
                        .append(" WHERE is_delete = 0 and purchase_order_item_status = 'A' ")
                        .append(" and grn_item_status IN ('P' , 'I') ")
                        .append("and company_id = ? and expected_branch_id = ? ").append(" and purchase_order_no IN ")
                        .append("(");
                queryParams.add(company_id);
                queryParams.add(expected_branch_id);

                // Append placeholders for the purchase_order_no values
                for (int count = 0; count < purchaseOrderNos.length(); count++) {
                    query.append("?");
                    queryParams.add(purchaseOrderNos.getString(count));
                    if (count < purchaseOrderNos.length() - 1) {
                        query.append(", ");
                    }
                }

                query.append(")");

                System.out.println("FnShowPurchaseOrderDetailsRecords: " + query);
                fetchdata = executeQuery.queryForList(query.toString(), queryParams.toArray());

//          Clear the StringBuilder and List<Object>
                query.setLength(0);
                queryParams.clear();

                String paymentView = "ptv_purchase_order_payment_terms";
                query = new StringBuilder("SELECT * FROM ")
                        .append(paymentView)
                        .append(" WHERE is_delete = 0 ")
                        .append("and company_id = ? and company_branch_id = ? ").append(" and purchase_order_no IN ")
                        .append("(");
                queryParams.add(company_id);
                queryParams.add(expected_branch_id);

                // Append placeholders for the purchase_order_no values
                for (int count = 0; count < purchaseOrderNos.length(); count++) {
                    query.append("?");
                    queryParams.add(purchaseOrderNos.getString(count));
                    if (count < purchaseOrderNos.length() - 1) {
                        query.append(", ");
                    }
                }
                query.append(")");
                paymentData = executeQuery.queryForList(query.toString(), queryParams.toArray());
                response.put("data", fetchdata);
                response.put("purchasePaymentTerms", paymentData);
            } else {
                response.put("data", fetchdata);
                response.put("purchasePaymentTerms", paymentData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
