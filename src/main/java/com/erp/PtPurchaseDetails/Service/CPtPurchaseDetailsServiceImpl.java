package com.erp.PtPurchaseDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.PtCottonBalesGRN.Repository.IPtGrnCottonBalesDetailsRepository;
import com.erp.PtGoodsReceiptDetails.Repository.IPtGoodsReceiptDetailsRepository;
import com.erp.PtPurchaseDetails.Model.*;
import com.erp.PtPurchaseDetails.Repository.*;
import com.erp.RawMaterial.Product_Rm.Repository.IProductRmRepository;
import com.erp.StIndentDetails.Model.CStIndentDetailsViewModel;
import com.erp.StIndentDetails.Repository.IStIndentDetailsRepository;
import com.erp.StIndentDetails.Repository.IStIndentDetailsViewRepository;
import com.erp.StIndentDetails.Repository.IStIndentMasterRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CPtPurchaseDetailsServiceImpl implements IPtPurchaseDetailsService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IPtPurchaseOrderMasterRepository iPtPurchaseOrderMasterRepository;

    @Autowired
    IPtPurchaseOrderDetailsRepository iPtPurchaseOrderDetailsRepository;
    @Autowired
    IProductRmRepository iProductRmRepository;

    @Autowired
    IPtPurchaseOrderDetailsViewRepository iPtPurchaseOrderDetailsViewRepository;

    @Autowired
    IPtPurchaseOrderSchedulesRepository iPtPurchaseOrderSchedulesRepository;

    @Autowired
    IPtPurchaseOrderSchedulesViewRepository iPtPurchaseOrderSchedulesViewRepository;

    @Autowired
    IPtPurchaseOrderTaxSummaryRepository iPtPurchaseOrderTaxSummaryRepository;

    @Autowired
    IPtPurchaseOrderTaxSummaryViewRepository iPtPurchaseOrderTaxSummaryViewRepository;

    @Autowired
    IPtPurchaseOrderPaymentTermsRepository iPtPurchaseOrderPaymentTermsRepository;

    @Autowired
    IPtPurchaseOrderTermsTradingRepository iPtPurchaseOrderTermsTradingRepository;

    @Autowired
    IPtPurchaseOrderProductDynamicParametersRepository iPtPurchaseOrderProductDynamicParametersRepository;

    @Autowired
    IPtPurchaseOrderProductDynamicParametersViewRepository iPtPurchaseOrderProductDynamicParametersViewRepository;

    @Autowired
    IPtGoodsReceiptDetailsRepository iptGoodsReceiptDetailsRepository;
    @Autowired
    private JdbcTemplate executeQuery;

    @Autowired
    IStIndentDetailsViewRepository iStIndentDetailsViewRepository;

    @Autowired
    IStIndentMasterRepository iStIndentMasterRepository;
    @Autowired
    IStIndentDetailsRepository iStIndentDetailsRepository;

    @Autowired
    IDocumentsRepository iDocumentsRepository;

    @Autowired
    IPtGrnCottonBalesDetailsRepository iPtGrnCottonBalesDetailsRepository;

    @Override
    public Map<String, Object> FnDeleteRecord(String purchase_order_no, int purchase_order_version, int company_id,
                                              String user_name) {
        Map<String, Object> responce = new HashMap<>();
        try {
            iPtPurchaseOrderMasterRepository.deletePurchase(purchase_order_no, purchase_order_version, company_id,
                    user_name);
            iPtPurchaseOrderDetailsRepository.deletePurchaseDetails(purchase_order_no, purchase_order_version,
                    company_id, user_name);
            iPtPurchaseOrderSchedulesRepository.deletePurchaseSchedules(purchase_order_no, purchase_order_version,
                    company_id, user_name);
            iPtPurchaseOrderTaxSummaryRepository.deletePurchaseTaxSummary(purchase_order_no, purchase_order_version,
                    company_id, user_name);
            iPtPurchaseOrderPaymentTermsRepository.deletePurchaseOrderPaymentTerms(purchase_order_no,
                    purchase_order_version, company_id, user_name);
            iPtPurchaseOrderTermsTradingRepository.deletePurchaseOrderTermsTrading(purchase_order_no,
                    purchase_order_version, company_id, user_name);
            iPtPurchaseOrderProductDynamicParametersRepository.deleteProductDynamicParameters(purchase_order_no, purchase_order_version, company_id, user_name);

            responce.put("success", "1");
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
    public Page<CPtPurchaseOrderDetailsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
        return iPtPurchaseOrderDetailsViewRepository.FnShowAllActiveRecords(pageable, company_id);

    }

    @Override
    public Page<CPtPurchaseOrderDetailsViewModel> FnShowParticularRecord(int purchase_order_details_transaction_id,
                                                                         Pageable pageable, int company_id) {
        return iPtPurchaseOrderDetailsViewRepository.FnShowParticularRecord(purchase_order_details_transaction_id,
                pageable, company_id);

    }

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String purchase_order_no,
                                                                     int purchase_order_version, String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        List<Map<String, Object>> updatePurchaseOrderDetailsRecords = new ArrayList<>();
        String gooodsReceiptNoteNo = ""; // goods receipt no for raw cotton product type
        try {

            Map<String, Object> purchaseOrderMasterRecords = iPtPurchaseOrderMasterRepository
                    .FnShowPurchaseOrderMasterRecord(purchase_order_no, purchase_order_version, financial_year,
                            company_id);
            List<Map<String, Object>> purchaseOrderDetailsRecords = iPtPurchaseOrderDetailsViewRepository
                    .FnShowPurchaseOrderDetailsRecords(purchase_order_no, purchase_order_version, financial_year,
                            company_id);

            if (purchaseOrderDetailsRecords != null) {
                List<String> distinctMaterialIds = new ArrayList<>();
                distinctMaterialIds = purchaseOrderDetailsRecords.stream()
                        .filter(record -> record.containsKey("product_material_id") && record.get("product_material_id") != null)
                        .map(record -> record.get("product_material_id").toString())
                        .distinct()
                        .collect(Collectors.toList());

                List<Map<String, Object>> productRmStockSummaryList = !distinctMaterialIds.isEmpty() ? iPtPurchaseOrderDetailsRepository.FnGetQtyProductRmStockSummary(distinctMaterialIds, company_id) : null;
                System.out.println("productRmStockSummaryList: " + productRmStockSummaryList);
                // Process each record and create a new updated record
                for (Map<String, Object> item : purchaseOrderDetailsRecords) {
                    if (item.containsKey("product_material_id") && item.get("product_material_id") != null) {
                        Map<String, Object> updatedItem = new HashMap<>(item);
                        String materialId = item.get("product_material_id").toString();

                        Optional<Map<String, Object>> materialSummary = productRmStockSummaryList != null
                                ? productRmStockSummaryList.stream()
                                .filter(matSummary -> materialId.equals(matSummary.get("product_rm_id").toString()))
                                .findFirst()
                                : Optional.empty();
                        if (materialSummary.isPresent()) {
                            Map<String, Object> matSummary = (Map<String, Object>) materialSummary.get();
                            System.out.println("matSummary is Present: " + matSummary);
                            // Create a new map or clone the original item map and add/update stock summary
                            updatedItem.put("available_stock_quantity", matSummary.get("closing_balance_quantity")); // Assuming "stock_summary" is a key to store summary
                            updatedItem.put("available_stock_weight", matSummary.get("closing_balance_weight")); // Assuming "stock_summary" is a key to store summary
                        } else {
                            updatedItem.put("available_stock_quantity", 0); // Assuming "stock_summary" is a key to store summary
                            updatedItem.put("available_stock_weight", 0); // Assuming "stock_summary" is a key to store summary
                        }
                        updatePurchaseOrderDetailsRecords.add(updatedItem);

                    }
                    System.out.println("updatePurchaseOrderDetailsRecords: " + updatePurchaseOrderDetailsRecords);
                }
            }


            List<CPtPurchaseOrderSchedulesViewModel> purchaseOrderSchedulesRecords = iPtPurchaseOrderSchedulesViewRepository
                    .FnShowPurchaseOrderSchedules(purchase_order_no, purchase_order_version, company_id);
            List<CPtPurchaseOrderTaxSummaryViewModel> purchaseOrderTaxSummaryRecords = iPtPurchaseOrderTaxSummaryViewRepository
                    .FnShowPurchaseOrderTaxSummary(purchase_order_no, purchase_order_version, company_id);
            List<CPtPurchaseOrderPaymentTermsModel> purchaseOrderPaymentTermsRecords = iPtPurchaseOrderPaymentTermsRepository
                    .FnShowPurchaseOrderPaymentTerms(purchase_order_no, purchase_order_version, company_id);
            List<CPtPurchaseOrderTermsModel> purchaseOrderTermsTradingRecords = iPtPurchaseOrderTermsTradingRepository
                    .FnShowPurchaseOrderTermsTrading(purchase_order_no, purchase_order_version, company_id);
            List<CPtPurchaseOrderProductDynamicParametersViewModel> purchaseOrderProductParametersRecords = iPtPurchaseOrderProductDynamicParametersViewRepository
                    .FnShowPurchaseOrderDynamicParameters(purchase_order_no, purchase_order_version, company_id);
            if (purchaseOrderMasterRecords.get("product_type_short_name").equals("RC")) {
                gooodsReceiptNoteNo = iptGoodsReceiptDetailsRepository
                        .FnGetGoodsReceiptNoteNo(purchase_order_no, purchase_order_version, company_id);
            }
            responce.put("PurchaseOrderMasterRecord", purchaseOrderMasterRecords);
            responce.put("PurchaseOrderDetailsRecords", updatePurchaseOrderDetailsRecords);
            responce.put("PurchaseOrderSchedules", purchaseOrderSchedulesRecords);
            responce.put("PurchaseOrderTaxSummary", purchaseOrderTaxSummaryRecords);
            responce.put("PurchaseOrderPaymentTerms", purchaseOrderPaymentTermsRecords);
            responce.put("PurchaseOrderTermsTrading", purchaseOrderTermsTradingRecords);
            responce.put("PurchaseOrderProductParametersRecords", purchaseOrderProductParametersRecords);
            responce.put("goods_receipt_no", gooodsReceiptNoteNo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
        Map<String, Object> response = new HashMap<>();
        String query;

        if ("summary".equals(reportType)) {
            query = "SELECT * FROM ptv_purchase_order_master_summary_rpt";
            List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
            response.put("content", results);
        } else {
            query = "SELECT * FROM ptv_purchase_order_details_rpt";
            List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
            response.put("content", results);
        }

        return response;

    }

    @Override
    public Map<String, Object> FnShowIndentDetailsRecords(JSONObject indentNo, int company_id) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> scheduleFetchData = new ArrayList<>();
//        List<Map<String, Object>> poTearmsFetchData = new ArrayList<>();

        try {
            JSONArray indentNos = indentNo.getJSONArray("indent_nos");
            List<Object> queryParams = new ArrayList<>();
            String view = "stv_indent_details";

            StringBuilder query = new StringBuilder("SELECT * FROM ").append(view)
                    .append(" WHERE indent_item_status = 'A' ")
                    .append(" and po_item_status IN ('P' , 'IPO') ")
                    .append(" and indent_no IN")
                    .append("(");

            // Append placeholders for the purchase_order_no values
            for (int count = 0; count < indentNos.length(); count++) {
                query.append("?");
                queryParams.add(indentNos.getString(count));
                if (count < indentNos.length() - 1) {
                    query.append(", ");
                }
            }

            query.append(")");

            System.out.println("Indent Details Records: " + query);
            List<Map<String, Object>> fetchdata = executeQuery.queryForList(query.toString(), queryParams.toArray());

            // indent Scedule
            if (!fetchdata.isEmpty()) {
                List<String> materialIds = fetchdata.stream()
                        .map(row -> (String) row.get("product_material_id"))
                        .distinct()
                        .collect(Collectors.toList());

                List<Object> scheduleQueryParams = new ArrayList<>();
                String scheduleView = "stv_indent_schedules";

                StringBuilder scheduleQuery = new StringBuilder("SELECT * FROM ")
                        .append(scheduleView)
                        .append(" WHERE indent_no IN").append("(");

                // Append placeholders for the purchase_order_no values
                for (int count = 0; count < indentNos.length(); count++) {
                    scheduleQuery.append("?");
                    scheduleQueryParams.add(indentNos.getString(count));
                    if (count < indentNos.length() - 1) {
                        scheduleQuery.append(", ");
                    }
                }
                scheduleQuery.append(")");
                scheduleQuery.append(" AND product_material_id IN").append("(");

                // Append placeholders for the purchase_order_no values
                for (int count = 0; count < materialIds.size(); count++) {
                    scheduleQuery.append("?");
                    scheduleQueryParams.add(materialIds.get(count));
                    if (count < materialIds.size() - 1) {
                        scheduleQuery.append(", ");
                    }
                }
                scheduleQuery.append(")");
                System.out.println("Indent Schedule Records: " + scheduleQuery);
                scheduleFetchData = executeQuery.queryForList(scheduleQuery.toString(),
                        scheduleQueryParams.toArray());

                // indent po tearms
//                List<Object> poTearmsQueryParams = new ArrayList<>();
//                String poTearmsView = "st_indent_schedules";
//
//                StringBuilder poTearmsQuery = new StringBuilder("SELECT * FROM ").append(poTearmsView)
//                        .append(" WHERE indent_no IN").append("(");
//
//                // Append placeholders for the purchase_order_no values
//                for (int count = 0; count < indentNos.length(); count++) {
//                    poTearmsQuery.append("?");
//                    poTearmsQueryParams.add(indentNos.getString(count));
//                    if (count < indentNos.length() - 1) {
//                        poTearmsQuery.append(", ");
//                    }
//                }
//                poTearmsQuery.append(")");
//                System.out.println("FnShowPurchaseOrderScheduleRecords: " + poTearmsQuery);
//                poTearmsFetchData = executeQuery.queryForList(poTearmsQuery.toString(),
//                        poTearmsQueryParams.toArray());
            }

            response.put("indentDetailsData", fetchdata);
            response.put("indentScheduleData", scheduleFetchData);
//            response.put("indentPoTearmsData", poTearmsFetchData);

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/PtPurchaseDetails/FnShowIndentDetailsRecords",
                    0, e.getMessage());
            response.put("success", "0");
            response.put("data", "");
            response.put("error", e.getMessage());
        }
        return response;
    }

    @Override
    public Map<String, Object> FnSendEmail(int company_id, int purchase_order_master_transaction_id,
                                           JSONObject emailData) {
        Map<String, Object> emailingResponse = new HashMap<>();

        EmailController emailController = new EmailController();
        Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);

        Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
        // Update the mail send status in master table.
        if (emailSentStatus) {
            iPtPurchaseOrderMasterRepository.updateMailStatus("S", company_id, purchase_order_master_transaction_id);
        } else {
            iPtPurchaseOrderMasterRepository.updateMailStatus("F", company_id, purchase_order_master_transaction_id);
        }

        emailingResponse.put("Status", emailSentStatus);
        emailingResponse.put("success", emailControllerResponse.get("success"));
        emailingResponse.put("error", emailControllerResponse.get("error"));
        emailingResponse.put("message", emailControllerResponse.get("message"));
        return emailingResponse;
    }

    @Override
    public Map<String, Object> FnAcceptPurchaseOrder(JSONObject pOAcceptanceJson, int company_id) {
        Map<String, Object> fnAcceptPurchaseOrderResponse = new HashMap<>();
        try {
            int purchase_order_master_transaction_id = pOAcceptanceJson.getInt("purchase_order_master_transaction_id");
            String purchase_order_acceptance_status = pOAcceptanceJson.getString("purchase_order_acceptance_status");
            iPtPurchaseOrderMasterRepository.FnAcceptPurchaseOrder(purchase_order_acceptance_status,
                    purchase_order_master_transaction_id, company_id);

            fnAcceptPurchaseOrderResponse.put("success", "1");
            fnAcceptPurchaseOrderResponse.put("error", "");
            fnAcceptPurchaseOrderResponse.put("message", "Purchase Order accepted successfully...! ");

        } catch (Exception e) {
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/MtSalesOrderMasterTrading/FnAcceptCustomerOrder", 0, e.getMessage());
            fnAcceptPurchaseOrderResponse.put("success", "0");
            fnAcceptPurchaseOrderResponse.put("data", "");
            fnAcceptPurchaseOrderResponse.put("error", e.getMessage());
            e.printStackTrace();
        }
        return fnAcceptPurchaseOrderResponse;
    }


    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String isApproveOrPreClosed) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        boolean update = false;

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String purchase_order_no = commonIdsObj.getString("purchase_order_no");
        String financial_year = commonIdsObj.getString("financial_year");

        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderFooterData");
        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray scheduleArray = (JSONArray) jsonObject.get("TransScheduleData");
        JSONArray paymentTermsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
        JSONArray orderTermsArray = (JSONArray) jsonObject.get("TransPOTermsData");
        JSONArray taxSummaryTermsArray = (JSONArray) jsonObject.get("TransTaxSummaryTermsData");
        JSONArray productParametersData = (JSONArray) jsonObject.get("TransProductParametersData");

        try {
            CPtPurchaseOrderMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
                    CPtPurchaseOrderMasterModel.class);

            if (isApproveOrPreClosed.equalsIgnoreCase("approve") || isApproveOrPreClosed.equalsIgnoreCase("ApproveUpdate")) {
                responce = FnPurchaseOrderDetailsUpdateRecord(detailsArray, jsonModel, commonIdsObj, scheduleArray, orderTermsArray ,isApproveOrPreClosed);
            }
//            else if (isApproveOrPreClosed.equalsIgnoreCase("preClosed")) {
//                responce = FnUpdatePreClosedRecord(detailsArray, jsonModel, commonIdsObj);
//            }
            else if (isApproveOrPreClosed.equalsIgnoreCase("cancel")) {
                responce = FnUpdateCanceledRecord(detailsArray, jsonModel, commonIdsObj, scheduleArray, isApproveOrPreClosed);
            } else {

                //Purchase Order Master
                CPtPurchaseOrderMasterModel ptPurchaseOrderMasterModel = new CPtPurchaseOrderMasterModel();
                String query = "Select * FROM pt_purchase_order_master WHERE is_delete = 0 and purchase_order_no = '"
                        + purchase_order_no.toString() + "' and purchase_order_version = "
                        + jsonModel.getPurchase_order_version() + " and financial_year = '" + financial_year.toString()
                        + "' and company_id = " + company_id + "";

                List<CPtPurchaseOrderMasterModel> results = executeQuery.query(query,
                        new BeanPropertyRowMapper<>(CPtPurchaseOrderMasterModel.class));

                if (!results.isEmpty()) {
                    update = true;
                    ptPurchaseOrderMasterModel = results.get(0);
                    ptPurchaseOrderMasterModel.setDeleted_on(new Date());
                    ptPurchaseOrderMasterModel.setIs_delete(true);
                    iPtPurchaseOrderMasterRepository.save(ptPurchaseOrderMasterModel);
                    jsonModel.setPurchase_order_version(ptPurchaseOrderMasterModel.getPurchase_order_version() + 1);

                    // below repo is for updating is_active status of uploaded document while saving PO.
                    String purchaseOrderNo = commonIdsObj.getString("purchase_order_no").replaceAll("/", "_");
                    List<String> groupIds = Collections.singletonList(purchaseOrderNo);
                    iDocumentsRepository.updateDocActive(groupIds);

                }

                CPtPurchaseOrderMasterModel responcePurchaseOrderMaster = iPtPurchaseOrderMasterRepository
                        .save(jsonModel);

//			Purchase Order Details
                iPtPurchaseOrderDetailsRepository.updateStatus(purchase_order_no,
                        masterjson.getInt("purchase_order_version"), financial_year, company_id);

                // below repo is for updating is_active status of uploaded document while saving & Updating PO.
                String purchaseOrderNo = commonIdsObj.getString("purchase_order_no").replaceAll("/", "_");
                List<String> groupIds = Collections.singletonList(purchaseOrderNo);
                iDocumentsRepository.updateDocActive(groupIds);

                List<CPtPurchaseOrderDetailsModel> cPtPurchaseOrderDetailsModels = objectMapper
                        .readValue(detailsArray.toString(), new TypeReference<List<CPtPurchaseOrderDetailsModel>>() {
                        });

                cPtPurchaseOrderDetailsModels.forEach(items -> {
                    items.setPurchase_order_version(jsonModel.getPurchase_order_version());
                    items.setPurchase_order_master_transaction_id(
                            responcePurchaseOrderMaster.getPurchase_order_master_transaction_id());
                });
                List<CPtPurchaseOrderDetailsModel> responcePurchaseOrderDetails = iPtPurchaseOrderDetailsRepository.saveAll(cPtPurchaseOrderDetailsModels);
                String poCreation = masterjson.getString("purchase_order_creation_type");
                if ("A".equals(poCreation)) {
                    FnUpdateIndentStatus(cPtPurchaseOrderDetailsModels, company_id, "O");
                }

                cPtPurchaseOrderDetailsModels.forEach(items -> {
                    iProductRmRepository.updateHsnCodeForProductMaterialIds(items.getProduct_material_id(), items.getProduct_material_hsn_code_id());
                });

//			Purchase Order Schedules
                if (!scheduleArray.isEmpty()) {
                    iPtPurchaseOrderSchedulesRepository.updatePurchaseOrderSchedulesStatus(purchase_order_no,
                            masterjson.getInt("purchase_order_version"), company_id);

                    List<CPtPurchaseOrderSchedulesModel> cptPurchaseOrderSchedulesModel = objectMapper.readValue(
                            scheduleArray.toString(), new TypeReference<List<CPtPurchaseOrderSchedulesModel>>() {
                            });
                    cptPurchaseOrderSchedulesModel.forEach(items -> {
                        items.setPurchase_order_schedules_transaction_id(0);
                        items.setPurchase_order_version(jsonModel.getPurchase_order_version());
                        items.setPurchase_order_master_transaction_id(
                                responcePurchaseOrderMaster.getPurchase_order_master_transaction_id());
                    });

                    iPtPurchaseOrderSchedulesRepository.saveAll(cptPurchaseOrderSchedulesModel);
                }
//			Purchase Order Tax Summary
                if (!taxSummaryTermsArray.isEmpty()) {
                    iPtPurchaseOrderTaxSummaryRepository.updatePurchaseTaxSummaryStatus(purchase_order_no,
                            masterjson.getInt("purchase_order_version"), financial_year, company_id);

                    List<CPtPurchaseOrderTaxSummaryModel> cptPurchaseOrderTaxSummaryModel = objectMapper.readValue(
                            taxSummaryTermsArray.toString(),
                            new TypeReference<List<CPtPurchaseOrderTaxSummaryModel>>() {
                            });
                    cptPurchaseOrderTaxSummaryModel.forEach(items -> {
                        items.setPurchase_order_version(jsonModel.getPurchase_order_version());
                        items.setPurchase_order_master_transaction_id(
                                responcePurchaseOrderMaster.getPurchase_order_master_transaction_id());
                    });
                    iPtPurchaseOrderTaxSummaryRepository.saveAll(cptPurchaseOrderTaxSummaryModel);
                }
//			Order Payment Terms
                if (!paymentTermsArray.isEmpty()) {
                    iPtPurchaseOrderPaymentTermsRepository.updatePurchaseOrderPaymentStatus(purchase_order_no,
                            masterjson.getInt("purchase_order_version"), company_id);
                    List<CPtPurchaseOrderPaymentTermsModel> cptPurchaseOrderPaymentTermsModel = objectMapper.readValue(
                            paymentTermsArray.toString(), new TypeReference<List<CPtPurchaseOrderPaymentTermsModel>>() {
                            });
                    cptPurchaseOrderPaymentTermsModel.forEach(items -> {
                        items.setPurchase_order_version(jsonModel.getPurchase_order_version());
                        items.setPurchase_order_master_transaction_id(
                                responcePurchaseOrderMaster.getPurchase_order_master_transaction_id());
                    });
                    iPtPurchaseOrderPaymentTermsRepository.saveAll(cptPurchaseOrderPaymentTermsModel);
                }

//			Order Terms
                if (!orderTermsArray.isEmpty()) {
                    iPtPurchaseOrderTermsTradingRepository.updatePurchaseOrderTermsStatus(purchase_order_no,
                            masterjson.getInt("purchase_order_version"), company_id);

                    List<CPtPurchaseOrderTermsModel> cptPurchaseOrderTermsModel = objectMapper.readValue(
                            orderTermsArray.toString(), new TypeReference<List<CPtPurchaseOrderTermsModel>>() {
                            });
                    cptPurchaseOrderTermsModel.forEach(items -> {
                        items.setPurchase_order_version(jsonModel.getPurchase_order_version());
                        items.setPurchase_order_master_transaction_id(
                                responcePurchaseOrderMaster.getPurchase_order_master_transaction_id());
                    });
                    iPtPurchaseOrderTermsTradingRepository.saveAll(cptPurchaseOrderTermsModel);
                }
                // Product Parameters Data
                if (!productParametersData.isEmpty()) {
                    iPtPurchaseOrderProductDynamicParametersRepository.updatePurchaseOrderParametersData(purchase_order_no,
                            masterjson.getInt("purchase_order_version"), company_id);
                    List<CPtPurchaseOrderProductDynamicParametersModel> cPtPurchaseOrderProductDynamicParametersModel = objectMapper.readValue(
                            productParametersData.toString(), new TypeReference<List<CPtPurchaseOrderProductDynamicParametersModel>>() {
                            });
                    cPtPurchaseOrderProductDynamicParametersModel.forEach(items -> {
                        items.setPurchase_order_version(jsonModel.getPurchase_order_version());
                        items.setPurchase_order_master_transaction_id(
                                responcePurchaseOrderMaster.getPurchase_order_master_transaction_id());
                    });
                    iPtPurchaseOrderProductDynamicParametersRepository.saveAll(cPtPurchaseOrderProductDynamicParametersModel);
                }

                responce.put("success", "1");
                responce.put("data", responcePurchaseOrderMaster);
                responce.put("purchaseOrderDetails", responcePurchaseOrderDetails);
                responce.put("error", "");
                responce.put("message", update ? "Record updated successfully!" : "Record added successfully!");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtPurchaseDetails/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/PtPurchaseDetails/FnAddUpdateRecord",
                    0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;

    }

    private Map<String, Object> FnUpdateCanceledRecord(JSONArray detailsArray, CPtPurchaseOrderMasterModel jsonModel, JSONObject commonIdsObj, JSONArray scheduleArray, String isApproveOrPreClosed) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String purchase_order_no = commonIdsObj.getString("purchase_order_no");
        String financial_year = commonIdsObj.getString("financial_year");

        try {
            // Purchase Order master
            iPtPurchaseOrderMasterRepository.save(jsonModel);

            // Purchase Order details
            List<CPtPurchaseOrderDetailsModel> cptPurchaseOrderDetailsModel = objectMapper
                    .readValue(detailsArray.toString(), new TypeReference<List<CPtPurchaseOrderDetailsModel>>() {
                    });

            iPtPurchaseOrderDetailsRepository.saveAll(cptPurchaseOrderDetailsModel);

            List<CPtPurchaseOrderSchedulesModel> cptPurchaseOrderSchedulesModel = objectMapper.readValue(
                    scheduleArray.toString(), new TypeReference<List<CPtPurchaseOrderSchedulesModel>>() {
                    });
            iPtPurchaseOrderSchedulesRepository.saveAll(cptPurchaseOrderSchedulesModel);


            // Get a single distinct indent number
            Optional<String> singleIndentNumber = cptPurchaseOrderDetailsModel.stream()
                    .map(CPtPurchaseOrderDetailsModel::getIndent_no)
                    .distinct()
                    .findFirst();

            // Check if a value is present and retrieve it
            String indentNumber = singleIndentNumber.orElse("");
            if (!indentNumber.isEmpty()) {

                for (CPtPurchaseOrderDetailsModel detailsItem : cptPurchaseOrderDetailsModel) {
                    iStIndentDetailsRepository.revertIndentDetailsPOStatus(detailsItem.getIndent_no(), detailsItem.getProduct_material_id(), company_id);
                }
                long poCount = iPtPurchaseOrderDetailsRepository.checkPOAgainstIndent(purchase_order_no, indentNumber, company_id);
                if (poCount <= 0) {
                    iStIndentMasterRepository.revertIndentMasterPOStatus("P", indentNumber, company_id);
                } else {
                    iStIndentMasterRepository.revertIndentMasterPOStatus("IPO", indentNumber, company_id);
                }
            }
            responce.put("success", "1");
            responce.put("data", cptPurchaseOrderDetailsModel);
            responce.put("error", "");
            responce.put("message", "Record Canceled successfully...!");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtPurchaseDetails/FnPurchaseOrderDetailsUpdateRecord", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtPurchaseDetails/FnPurchaseOrderDetailsUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }


    @Override
    public Map<String, Object> FnUpdatePreClosedRecord(JSONObject jsonObject, Integer company_id, Integer po_master_transaction_id) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
        try {

            // Purchase Order details
            List<CPtPurchaseOrderDetailsModel> cptPurchaseOrderDetailsModel = objectMapper
                    .readValue(detailsArray.toString(), new TypeReference<List<CPtPurchaseOrderDetailsModel>>() {
                    });

            for (CPtPurchaseOrderDetailsModel details : cptPurchaseOrderDetailsModel) {
                iPtPurchaseOrderDetailsRepository.updatePreClosedDetailsRecord(details.getPree_closed_quantity(),details.getPre_closed_remark(), details.getPurchase_order_details_transaction_id());
            }

            List<Map<String, Object>> getPurchaseOrderDetails = iPtPurchaseOrderDetailsRepository.getPODetails(po_master_transaction_id);
            boolean allContainZ = true; // Assume all contain 'Z'

            for (Map<String, Object> details : getPurchaseOrderDetails) {
                String purchaseOrderItemStatus = (String) details.get("purchase_order_item_status"); // Extract status from map
                if (!"Z".equals(purchaseOrderItemStatus)) {
                    allContainZ = false; // If any item does not contain 'Z', set to false
                    break; // Exit loop as soon as we find a non-'Z' status
                }
            }

            if (allContainZ) {
                iPtPurchaseOrderMasterRepository.updatePreClosedMasterStatus(po_master_transaction_id);
                System.out.println("All purchase order item statuses contain 'Z'.");
            }


            responce.put("success", "1");
            responce.put("data", cptPurchaseOrderDetailsModel);
            responce.put("error", "");
            responce.put("message", "Records Pre-Closed successfully...!");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtPurchaseDetails/FnPurchaseOrderDetailsUpdateRecord", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtPurchaseDetails/FnPurchaseOrderDetailsUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    public Map<String, Object> FnPurchaseOrderDetailsUpdateRecord(JSONArray detailsArray,
                                                                  CPtPurchaseOrderMasterModel jsonModel, JSONObject commonIdsObj, JSONArray scheduleArray, JSONArray orderTermsArray, String isApproveOrPreClosed) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        int company_id = commonIdsObj.getInt("company_id");
        String purchase_order_no = commonIdsObj.getString("purchase_order_no");

        try {
            // Purchase Order master
            iPtPurchaseOrderMasterRepository.save(jsonModel);

            // Purchase Order schedule
            iPtPurchaseOrderSchedulesRepository.updatePurchaseOrderSchedulesStatus(purchase_order_no,
                    jsonModel.getPurchase_order_version(), company_id);
            List<CPtPurchaseOrderSchedulesModel> cptPurchaseOrderSchedulesModel = objectMapper.readValue(scheduleArray.toString(), new TypeReference<List<CPtPurchaseOrderSchedulesModel>>() {
            });
            iPtPurchaseOrderSchedulesRepository.saveAll(cptPurchaseOrderSchedulesModel);

            // Purchase Order details
            List<CPtPurchaseOrderDetailsModel> cptPurchaseOrderDetailsModel = objectMapper
                    .readValue(detailsArray.toString(), new TypeReference<List<CPtPurchaseOrderDetailsModel>>() {
                    });

            List<CPtPurchaseOrderDetailsModel> savedPurchaseOrderDetailsModel = iPtPurchaseOrderDetailsRepository.saveAll(cptPurchaseOrderDetailsModel);

            //Order Terms
            List<CPtPurchaseOrderTermsModel> cptPurchaseOrderTermsModel = objectMapper.readValue(
                    orderTermsArray.toString(), new TypeReference<List<CPtPurchaseOrderTermsModel>>() {
                    });
            iPtPurchaseOrderTermsTradingRepository.saveAll(cptPurchaseOrderTermsModel);

            String poCreation = jsonModel.getPurchase_order_creation_type();
            if ("A".equals(poCreation)) {
                FnUpdateIndentStatus(cptPurchaseOrderDetailsModel, company_id, "POA");
            }
            if(isApproveOrPreClosed.equalsIgnoreCase("ApproveUpdate")){
                updatePoRates(savedPurchaseOrderDetailsModel);
            }

            responce.put("success", "1");
            responce.put("data", cptPurchaseOrderDetailsModel);
            responce.put("error", "");
            String purchase_order_status = jsonModel.getPurchase_order_status();
            responce.put("message", isApproveOrPreClosed.equalsIgnoreCase("ApproveUpdate") ? "Record Updated successfully...!" :
                    purchase_order_status.equals("A") ? "Record approved successfully...!" : "Record rejected  successfully...! ");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtPurchaseDetails/FnPurchaseOrderDetailsUpdateRecord", sqlEx.getErrorCode(),
                        sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtPurchaseDetails/FnPurchaseOrderDetailsUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }
    public void updatePoRates(List<CPtPurchaseOrderDetailsModel> savedPurchaseOrderDetailsModel) {
        String sql = "UPDATE pt_grn_cotton_bales_details SET po_material_rate = ? WHERE product_material_id = ? AND purchase_order_details_transaction_id = ? AND purchase_order_no = ? AND is_delete = 0";

        executeQuery.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CPtPurchaseOrderDetailsModel detail = savedPurchaseOrderDetailsModel.get(i);
                ps.setDouble(1, detail.getMaterial_rate());
                ps.setString(2, detail.getProduct_material_id());
                ps.setInt(3, detail.getPurchase_order_details_transaction_id());
                ps.setString(4, detail.getPurchase_order_no());
            }

            @Override
            public int getBatchSize() {
                return savedPurchaseOrderDetailsModel.size();
            }
        });
    }
    private void FnUpdateIndentStatus(List<CPtPurchaseOrderDetailsModel> detailsArray, int companyId, String indentStatus) {
        List<String> inCompleteMaterialIndents = new ArrayList<>();
        // Get distinct indent order numbers
        List<String> distinctIndentNumbers = detailsArray.stream()
                .map(CPtPurchaseOrderDetailsModel::getIndent_no)
                .flatMap(indentNumbers -> Arrays.stream(indentNumbers.split(":")))
                .distinct()
                .collect(Collectors.toList());

        // Retrieve the list of goods receipt details based on the master transaction id
        List<CStIndentDetailsViewModel> responseIndentDetails = iStIndentDetailsViewRepository
                .FngetExistingIndentMaterials(distinctIndentNumbers, companyId);

        // Retrieve the previous list of goods receipt details based on PO nos
        List<CPtPurchaseOrderDetailsViewModel> existingPODetails = iPtPurchaseOrderDetailsViewRepository
                .getAllExistingPODetails(distinctIndentNumbers, companyId);

        // Create a set of distinct composite keys from the goods receipt details where the condition is met
        Set<String> distinctKeys = existingPODetails.stream()
                .map(podetails -> podetails.getProduct_material_id() + ":" + podetails.getIndent_no())
                .collect(Collectors.toSet());

        // Create a map with the distinct composite keys
        Map<String, Boolean> indentDetailsMap = distinctKeys.stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> true
                ));

        // Filter indent details to only include those not in the po details map
        List<CStIndentDetailsViewModel> filteredPurchaseDetails = responseIndentDetails.stream()
                .filter(indent -> !indentDetailsMap.containsKey(indent.getProduct_material_id() + ":" + indent.getIndent_no()))
                .collect(Collectors.toList());

        for (CPtPurchaseOrderDetailsModel detail : detailsArray) {
            String materialId = detail.getProduct_material_id();
            Optional<CStIndentDetailsViewModel> materialSummary = responseIndentDetails != null
                    ? responseIndentDetails.stream()
                    .filter(matSummary -> materialId.equals(matSummary.getProduct_material_id()))
                    .findFirst()
                    : Optional.empty();

            if (materialSummary.isPresent()) {
                CStIndentDetailsViewModel matSummary = materialSummary.get();
                // Calculate the remaining quantity for the current item
                double materialRemainingQty = matSummary.getProduct_material_approved_quantity() - matSummary.getPrev_po_quantity();
                if (materialRemainingQty == 0 || materialRemainingQty < 0) {
                    System.out.println("Indent Material Item Status: ( PO Ordered): " + matSummary.getProduct_rm_name() + " : " + materialRemainingQty);
                    iPtPurchaseOrderDetailsRepository.FnUpdateIndentDetailsStatus(indentStatus, distinctIndentNumbers, matSummary.getProduct_material_id(), companyId);
                } else if (materialRemainingQty > 0) {//if material quantity is not used all then partial po ordered
                    inCompleteMaterialIndents.add(matSummary.getIndent_no());
                    System.out.println("Indent Material Item Status: (Partial): " + matSummary.getProduct_rm_name() + " : " + materialRemainingQty);
                    iPtPurchaseOrderDetailsRepository.FnUpdateIndentDetailsStatus("IPO", distinctIndentNumbers, matSummary.getProduct_material_id(), companyId);
                }
            }
        }

        if (indentStatus.equalsIgnoreCase("O")) {
            if (!filteredPurchaseDetails.isEmpty() || !inCompleteMaterialIndents.isEmpty()) {//if material quantity is not used all or any material left for po then Indent Status will be IPO
                indentStatus = "IPO";
                System.out.println("filteredPurchaseDetails is not empty, Indent is IPO : " + filteredPurchaseDetails);
            }
            iPtPurchaseOrderDetailsRepository.FnUpdateIndentMasterStatus(indentStatus, distinctIndentNumbers, companyId);
        } else if (indentStatus.equalsIgnoreCase("POA") && filteredPurchaseDetails.isEmpty() && inCompleteMaterialIndents.isEmpty()) {
            indentStatus = "POA";
            System.out.println("filteredPurchaseDetails is empty, Indent is POA : " + filteredPurchaseDetails);
            iPtPurchaseOrderDetailsRepository.FnUpdateIndentMasterStatus(indentStatus, distinctIndentNumbers, companyId);
        }
    }


    @Override
    public Map<String, Object> FnGetPartialgrnMaterials(String purchase_order_no, int purchase_order_version, String financial_year, int company_id) {

        Map<String, Object> responce = new HashMap<>();
        List<Map<String, Object>> updatePurchaseOrderDetailsRecords = new ArrayList<>();
        String gooodsReceiptNoteNo = ""; // goods receipt no for raw cotton product type
        try {

            Map<String, Object> purchaseOrderMasterRecords = iPtPurchaseOrderMasterRepository
                    .FnShowPurchaseOrderMasterRecord(purchase_order_no, purchase_order_version, financial_year,
                            company_id);
            List<Map<String, Object>> purchaseOrderDetailsRecords = iPtPurchaseOrderDetailsViewRepository
                    .FnShowPurchaseOrderDetailsForPreClosed(purchase_order_no, purchase_order_version, financial_year,
                            company_id);

            if (purchaseOrderDetailsRecords != null) {
                // Process each record and create a new updated record
                for (Map<String, Object> item : purchaseOrderDetailsRecords) {
                    if (item.containsKey("pending_quantity") && item.get("pending_quantity") != null) {
                        // Ensure that the pending_quantity is treated as a numeric value
                        Double pendingQuantity = Double.parseDouble(item.get("pending_quantity").toString());

                        if (pendingQuantity > 0) {
                            Map<String, Object> updatedItem = new HashMap<>(item);
                            updatedItem.put("pree_closed_quantity", item.get("pending_quantity"));
                            updatedItem.put("pree_closed_weight", item.get("pending_weight"));

                            updatePurchaseOrderDetailsRecords.add(updatedItem);
                        }
                    }
                    System.out.println("still material in outstanding: " + updatePurchaseOrderDetailsRecords);
                }
            }

            List<CPtPurchaseOrderSchedulesViewModel> purchaseOrderSchedulesRecords = iPtPurchaseOrderSchedulesViewRepository
                    .FnShowPurchaseOrderSchedules(purchase_order_no, purchase_order_version, company_id);

            if (purchaseOrderMasterRecords.get("product_type_short_name").equals("RC")) {
                gooodsReceiptNoteNo = iptGoodsReceiptDetailsRepository
                        .FnGetGoodsReceiptNoteNo(purchase_order_no, purchase_order_version, company_id);
            }
            responce.put("PurchaseOrderMasterRecord", purchaseOrderMasterRecords);
            responce.put("PurchaseOrderDetailsRecords", updatePurchaseOrderDetailsRecords);
            responce.put("PurchaseOrderSchedules", purchaseOrderSchedulesRecords);
            responce.put("goods_receipt_no", gooodsReceiptNoteNo);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }


}

