package com.erp.MtSalesOrderMasterTrading.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.nio.file.*;

import com.erp.Common.Documents.Model.CDocumentsModel;
import com.erp.Common.Documents.Repository.IDocumentsRepository;
import com.erp.MtSalesOrderMasterTrading.Model.*;
import com.erp.MtSalesOrderMasterTrading.Repository.*;
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

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtQuotationDetails.Repository.IMtSalesQuotationDetailsTradingRepository;
import com.erp.MtQuotationDetails.Repository.IMtSalesQuotationMasterTradingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CMtSalesOrderMasterTradingServiceImpl implements IMtSalesOrderMasterTradingService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IMtSalesOrderMasterTradingRepository iMtSalesOrderMasterTradingRepository;

    @Autowired
    IMtSalesOrderTermsTradingRepository iMtSalesOrderTermsTradingRepository;
    @Autowired
    IDocumentsRepository iDocumentsRepository;

    @Autowired
    IMtSalesOrderMasterTradingSummaryViewRepository iMtSalesOrderMasterTradingSummaryViewRepository;

    @Autowired
    IMtSalesOrderSchedulesTradingRepository iMtSalesOrderSchedulesTradingRepository;

    @Autowired
    IMtSalesOrderPaymentTermsTradingRepository iMtSalesOrderPaymentTermsTradingRepository;

    @Autowired
    IMtSalesOrderDetailsTradingViewRepository iMtSalesOrderDetailsTradingViewRepository;

    @Autowired
    IMtSalesOrderSchedulesTradingViewRepository iMtSalesOrderSchedulesTradingViewRepository;

    @Autowired
    IMtSalesOrderPaymentTermsTradingViewRepository iMtSalesOrderPaymentTermsTradingViewRepository;

    @Autowired
    IMtSalesOrderTaxSummaryRepository iMtSalesOrderTaxSummaryRepository;

    @Autowired
    IMtSalesOrderTaxSummaryViewRepository iMtSalesOrderTaxSummaryViewRepository;

    @Autowired
    IMtSalesOrderDetailsTradingRepository iMtSalesOrderDetailsTradingRepository;

    @Autowired
    IMtSalesOrderSaudaSheetRepository iMtSalesOrderSaudaSheetRepository;

    @Autowired
    IMtSalesOrderDetailsTrackingTradingViewRepository iMtSalesOrderDetailsTrackingTradingViewRepository;

    @Autowired
    IMtSalesQuotationDetailsTradingRepository iMtSalesQuotationDetailsTradingRepository;

    @Autowired
    IMtSalesQuotationMasterTradingRepository iMtSalesQuotationMasterTradingRepository;

    @Autowired
    IMtSalesOrderProductDynamicParametersRepository iMtSalesOrderProductDynamicParametersRepository;

    @Autowired
    IMtSalesOrderProductDynamicParametersViewRepository iMtSalesOrderProductDynamicParametersViewRepository;


    @Autowired
    IMtSalesOrderRawMaterialsRepository iMtSalesOrderRawMaterialsRepository;

    @Autowired
    IMtSalesOrderRawMaterialsViewRepository iMtSalesOrderRawMaterialsViewRepository;


    @Autowired
    private JdbcTemplate executeQuery;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {

        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        boolean update = false;

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String sales_order_no = commonIdsObj.getString("sales_order_no");
        String financial_year = commonIdsObj.getString("financial_year");
        int salesOrderMasterTransId = commonIdsObj.getInt("sales_order_master_transaction_id");
        JSONObject json = jsonObject.getJSONObject("TransMasterData");
        JSONArray array = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray payment_termsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
        JSONArray TaxSummaryArray = (JSONArray) jsonObject.get("TaxSummaryData");
        JSONArray scheduleArray = (JSONArray) jsonObject.get("TransDetailScheduleData");
        JSONArray orderTermsArray = (JSONArray) jsonObject.get("TransSalesOrderTermsData");
        JSONArray productParametersData = (JSONArray) jsonObject.get("TransProductParametersData");
        JSONArray rawMaterialsData = (JSONArray) jsonObject.get("TransRawMaterialData");
        JSONArray documentData = (JSONArray) jsonObject.get("DocumentUpload");
        JSONArray saudaShit = (JSONArray) jsonObject.get("SaudaShitDetails");
        String sanitizedSalesOrderNo = "";
        try {
            CMtSalesOrderMasterTradingModel jsonModel = objectMapper.readValue(json.toString(),
                    CMtSalesOrderMasterTradingModel.class);
            String salesOrderNo = jsonModel.getSales_order_no();
//			String sales_order_nos=salesOrderNo.replaceAll('/', '_')
            sanitizedSalesOrderNo = salesOrderNo.replaceAll("/", "_");
            if (!isApprove) {

                String sales_order_date = jsonModel.getSales_order_date();

                CMtSalesOrderMasterTradingModel cMtSalesOrderMasterTradingModel = new CMtSalesOrderMasterTradingModel();

                String query = "Select * FROM mt_sales_order_master_trading WHERE is_delete = 0 and sales_order_no = '"
                        + sales_order_no.toString() + "' and sales_order_version = " + jsonModel.getSales_order_version()
                        + " and financial_year = '" + financial_year.toString() + "' and company_id = " + company_id + "";

                List<CMtSalesOrderMasterTradingModel> results = executeQuery.query(query,
                        new BeanPropertyRowMapper<>(CMtSalesOrderMasterTradingModel.class));

                if (!results.isEmpty()) {
                    update = true;
                    cMtSalesOrderMasterTradingModel = results.get(0);
                    cMtSalesOrderMasterTradingModel.setDeleted_on(new Date());
                    cMtSalesOrderMasterTradingModel.setIs_delete(true);
                    iMtSalesOrderMasterTradingRepository.save(cMtSalesOrderMasterTradingModel);
                    String salesOrder = commonIdsObj.getString("sales_order_no").replaceAll("/", "_");
                    List<String> groupIds = Collections.singletonList(salesOrder);
                    iDocumentsRepository.updateDocActive(groupIds);
                    
                    jsonModel.setSales_order_version(cMtSalesOrderMasterTradingModel.getSales_order_version() + 1);

                }
                String salesOrder = commonIdsObj.getString("sales_order_no").replaceAll("/", "_");
                List<String> groupIds = Collections.singletonList(salesOrder);
                iDocumentsRepository.updateDocActive(groupIds);
                CMtSalesOrderMasterTradingModel responseSalesOrderMaster = iMtSalesOrderMasterTradingRepository
                        .save(jsonModel);

                iMtSalesOrderDetailsTradingRepository.updateStatus(sales_order_no, json.getInt("sales_order_version"),
                        company_id);

                List<CMtSalesOrderDetailsTradingModel> orderDetailsTradingModels = objectMapper.readValue(array.toString(),
                        new TypeReference<List<CMtSalesOrderDetailsTradingModel>>() {
                        });

                orderDetailsTradingModels.forEach(orderDetail -> {
                    orderDetail.setSales_order_version(jsonModel.getSales_order_version());
                    orderDetail.setSales_order_master_transaction_id(responseSalesOrderMaster.getSales_order_master_transaction_id());
                });
                orderDetailsTradingModels.forEach(items -> {
                    iMtSalesOrderDetailsTradingRepository.updateHsnCodeForProductIds(items.getProduct_material_id(), items.getProduct_material_hsn_code_id());
//                    }
                });
//				moveFiles(sanitizedSalesOrderNo);

                iMtSalesOrderDetailsTradingRepository.saveAll(orderDetailsTradingModels);

                if (saudaShit != null && !saudaShit.isEmpty()) {

                    iMtSalesOrderSaudaSheetRepository.updateSaudaShitDetails(sales_order_no,company_id);
                    //changes related sauda shit
                    List<CMtSalesOrderSaudaSheetModel> cMtSalesOrderSaudaSheetModel = objectMapper.readValue(saudaShit.toString(),
                            new TypeReference<List<CMtSalesOrderSaudaSheetModel>>() {
                            });
                    cMtSalesOrderSaudaSheetModel.forEach(orderDetail -> {

                        orderDetail.setSales_order_master_transaction_id(responseSalesOrderMaster.getSales_order_master_transaction_id());
                    });
                    iMtSalesOrderSaudaSheetRepository.saveAll(cMtSalesOrderSaudaSheetModel);
                }
//                int sales_order_master_transaction_id = jsonModel.getSales_order_master_transaction_id();
                if (salesOrderMasterTransId == 0 && !documentData.isEmpty()) {
                    moveDirectory(sanitizedSalesOrderNo);
//                    CDocumentsModel object = objectMapper.readValue(documentData.toString(), CDocumentsModel.class);
//                    iDocumentsRepository.save(object);
                    List<CDocumentsModel> documentAllData = objectMapper.readValue(
                            documentData.toString(),
                            new TypeReference<List<CDocumentsModel>>() {
                            });
                    documentAllData.forEach(document -> {
                        // Replace only the first occurrence of "TempStores" with "DocStores"
                        String updatedPath = document.getDocument_path().replaceFirst("TempStores", "DocStores");
                        document.setDocument_path(updatedPath);
                    });

                    iDocumentsRepository.saveAll(documentAllData);
                }

                // Payment Terms
                if (!payment_termsArray.isEmpty()) {
                    iMtSalesOrderPaymentTermsTradingRepository.updatePaymentTermsStatus(sales_order_no,
                            json.getInt("sales_order_version"), company_id);

                    List<CMtSalesOrderPaymentTermsTradingModel> tradingOrderPaymentTerms = objectMapper.readValue(
                            payment_termsArray.toString(),
                            new TypeReference<List<CMtSalesOrderPaymentTermsTradingModel>>() {
                            });
                    tradingOrderPaymentTerms.forEach(paymentTerm -> {
                        paymentTerm.setSales_order_version(jsonModel.getSales_order_version());
                        paymentTerm.setSales_order_master_transaction_id(responseSalesOrderMaster.getSales_order_master_transaction_id());
                    });
                    iMtSalesOrderPaymentTermsTradingRepository.saveAll(tradingOrderPaymentTerms);

                }

                if (!TaxSummaryArray.isEmpty()) {// Tax Summary

                    iMtSalesOrderTaxSummaryRepository.updateTaxSummary(sales_order_no, json.getInt("sales_order_version"),
                            company_id);

                    List<CMtSalesOrderMasterTaxSummaryModel> orderTaxSummary = objectMapper.readValue(
                            TaxSummaryArray.toString(), new TypeReference<List<CMtSalesOrderMasterTaxSummaryModel>>() {
                            });
                    orderTaxSummary.forEach(taxSummaryRec -> {
                        taxSummaryRec.setSales_order_version(jsonModel.getSales_order_version());
                        taxSummaryRec
                                .setSales_order_master_transaction_id(responseSalesOrderMaster.getSales_order_master_transaction_id());
                    });

                    iMtSalesOrderTaxSummaryRepository.saveAll(orderTaxSummary);

                }
                if (!scheduleArray.isEmpty()) {// Payment Schedules

                    iMtSalesOrderSchedulesTradingRepository.updatePaymentSchedules(sales_order_no,
                            json.getInt("sales_order_version"), company_id);

                    List<CMtSalesOrderSchedulesTradingModel> orderMaterialSchedules = objectMapper.readValue(
                            scheduleArray.toString(), new TypeReference<List<CMtSalesOrderSchedulesTradingModel>>() {
                            });


                    orderMaterialSchedules.forEach(materialSchedule -> {
                        materialSchedule.setSales_order_schedules_transaction_id(0);
                        materialSchedule.setSales_order_version(jsonModel.getSales_order_version());
                        materialSchedule.setSales_order_master_transaction_id(responseSalesOrderMaster.getSales_order_master_transaction_id());
                    });

                    iMtSalesOrderSchedulesTradingRepository.saveAll(orderMaterialSchedules);

                }
                if (!orderTermsArray.isEmpty()) { // Order Terms

                    iMtSalesOrderTermsTradingRepository.updateOrderTermdetails(sales_order_no,
                            json.getInt("sales_order_version"), company_id);

                    List<CMtSalesOrderTermsTradingModel> orderTerms = objectMapper.readValue(orderTermsArray.toString(),
                            new TypeReference<List<CMtSalesOrderTermsTradingModel>>() {
                            });

                    orderTerms.forEach(orderTerm -> {
                        orderTerm.setSales_order_version(jsonModel.getSales_order_version());
                        orderTerm.setSales_order_master_transaction_id(responseSalesOrderMaster.getSales_order_master_transaction_id());
                    });

                    iMtSalesOrderTermsTradingRepository.saveAll(orderTerms);
                }

                // Product Parameters Data
                if (!productParametersData.isEmpty()) {

                    iMtSalesOrderProductDynamicParametersRepository.updateSalesOrderParametersData(sales_order_no, json.getInt("sales_order_version"), company_id);

                    List<CMtSalesOrderProductDynamicParametersModel> cMtSalesOrderProductDynamicParametersModel = objectMapper.readValue(
                            productParametersData.toString(), new TypeReference<List<CMtSalesOrderProductDynamicParametersModel>>() {
                            });

                    cMtSalesOrderProductDynamicParametersModel.forEach(items -> {
                        items.setSales_order_version(responseSalesOrderMaster.getSales_order_version());
                        items.setSales_order_master_transaction_id(responseSalesOrderMaster.getSales_order_master_transaction_id());
                    });
                    iMtSalesOrderProductDynamicParametersRepository.saveAll(cMtSalesOrderProductDynamicParametersModel);
                }

                // Product Parameters Data
                if (!rawMaterialsData.isEmpty()) {

                    iMtSalesOrderRawMaterialsRepository.updateSalesOrderRawMaterialData(sales_order_no, json.getInt("sales_order_version"), company_id);

                    List<CMtSalesOrderRawMaterialsDetailsModel> cMtSalesOrderRawMaterialsDetailsModels = objectMapper.readValue(
                            rawMaterialsData.toString(), new TypeReference<List<CMtSalesOrderRawMaterialsDetailsModel>>() {
                            });

                    cMtSalesOrderRawMaterialsDetailsModels.forEach(items -> {
                        items.setSales_order_version(responseSalesOrderMaster.getSales_order_version());
                        items.setSales_order_master_transaction_id(responseSalesOrderMaster.getSales_order_master_transaction_id());
                    });
                    iMtSalesOrderRawMaterialsRepository.saveAll(cMtSalesOrderRawMaterialsDetailsModels);
                }

                responce.put("success", "1");
                responce.put("data", responseSalesOrderMaster);
                responce.put("error", "");
                //responce.put("message", update ? "Record updated successfull...! \r\n  sales order no :" + sales_order_no.toString() + " and  version : "+jsonModel.getSales_order_version() : "Record added successfully...! \r\n  sales order no : " + sales_order_no.toString() +" and version : "+ jsonModel.getSales_order_version());
                responce.put("message", update ? "Record updated successfully...! " : "Record added successfully...! ");

            } else {
                responce = FnSalesOrderDetailsUpdateRecord(array, commonIdsObj, jsonModel, scheduleArray);
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/MtSalesOrderMasterTrading/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/MtSalesOrderMasterTrading/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;

    }

    @Override
    public Map<String, Object> FnDeleteRecord(String sales_order_no, int sales_order_version, int company_id, String deleted_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iMtSalesOrderMasterTradingRepository.deleteSalesDetails(sales_order_no, sales_order_version, company_id, deleted_by);

            iMtSalesOrderSchedulesTradingRepository.deleteSalesSchedules(sales_order_no, sales_order_version,
                    company_id, deleted_by);

            iMtSalesOrderTaxSummaryRepository.deleteSalesTaxSummary(sales_order_no, sales_order_version, company_id, deleted_by);

            iMtSalesOrderPaymentTermsTradingRepository.deleteSalesOrderPaymentTerms(sales_order_no, sales_order_version,
                    company_id, deleted_by);

            iMtSalesOrderTermsTradingRepository.deleteSalesOrderTermsTrading(sales_order_no, sales_order_version,
                    company_id, deleted_by);

            //Delete Sales Order Dynamic Parameter Record
            iMtSalesOrderProductDynamicParametersRepository.deleteSalesOrderDynamicParameterRecords(sales_order_no, sales_order_version,
                    company_id, deleted_by);

            //Delete Sales Order Raw Materials Record
            iMtSalesOrderRawMaterialsRepository.deleteSalesOrderRawMaterialsRecords(sales_order_no, sales_order_version,
                    company_id, deleted_by);

            //Delete Sales Order Raw Materials Record
            iMtSalesOrderSaudaSheetRepository.deleteSalesOrderSaudaSheetRecords(sales_order_no,
                    company_id, deleted_by);

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


    private Map<String, Object> FnSalesOrderDetailsUpdateRecord(JSONArray array, JSONObject commonIdsObj, CMtSalesOrderMasterTradingModel jsonModel, JSONArray scheduleArray) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        int company_id = commonIdsObj.getInt("company_id");
        int company_branch_id = commonIdsObj.getInt("company_branch_id");
        String sales_order_no = commonIdsObj.getString("sales_order_no");
        String financial_year = commonIdsObj.getString("financial_year");


        try {

            //master data
            CMtSalesOrderMasterTradingModel updatedMasterData = iMtSalesOrderMasterTradingRepository.save(jsonModel);

            //Schedule data

            List<CMtSalesOrderSchedulesTradingModel> orderMaterialSchedules = objectMapper.readValue(
                    scheduleArray.toString(), new TypeReference<List<CMtSalesOrderSchedulesTradingModel>>() {
                    });

            iMtSalesOrderSchedulesTradingRepository.saveAll(orderMaterialSchedules);

            //detail data
            List<CMtSalesOrderDetailsTradingModel> salesOrderdetailsTradingModels = objectMapper.readValue(array.toString(),
                    new TypeReference<List<CMtSalesOrderDetailsTradingModel>>() {
                    });
            List<CMtSalesOrderDetailsTradingModel> updatedSODetails = iMtSalesOrderDetailsTradingRepository.saveAll(salesOrderdetailsTradingModels);


            // Update the quotation status.
            if ("Q".equals(updatedMasterData.getSales_order_creation_type())) {
                String quotationNo = updatedMasterData.getSales_quotation_no();

                iMtSalesQuotationDetailsTradingRepository.updateSalesQuotationDetailsStatus(quotationNo, company_id);
                iMtSalesQuotationMasterTradingRepository.updateSalesQuotationMasterStatus(quotationNo, company_id);
                // update the quotation_details status as 'S'.
                // Also update the quotation master status as 'O'.
            }

            responce.put("success", "1");
            responce.put("data", updatedMasterData);
            responce.put("error", "");
            responce.put("message", jsonModel.getSales_order_status().equals("A") ? "Sales Order approved successfully...! " : "Sales Order rejected...! ");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/MtSalesOrderMasterTrading/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtSalesOrderMasterTrading/FnAddUpdateRecord",
                    0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }


    @Override
    public Page<CMtSalesOrderMasterTradingSummaryViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
        return iMtSalesOrderMasterTradingSummaryViewRepository.FnShowAllActiveRecords(pageable, company_id);
    }

    @Override
    public Map<String, Object> FnShowParticularRecordForUpdate(int sales_order_master_transaction_id, int company_id) {
        Map<String, Object> responce = new HashMap();
        CMtSalesOrderMasterTradingModel cMtSalesOrderMasterTradingModel = null;
        try {
            cMtSalesOrderMasterTradingModel = iMtSalesOrderMasterTradingRepository
                    .FnShowParticularRecordForUpdate(sales_order_master_transaction_id, company_id);
            responce.put("success", "1");
            responce.put("data", cMtSalesOrderMasterTradingModel);
            responce.put("error", "");
        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());

        }
        return responce;
    }

    @Override
    public Page<CMtSalesOrderMasterTradingSummaryViewModel> FnShowParticularRecord(
            int sales_order_master_transaction_id, Pageable pageable, int company_id) {
        return iMtSalesOrderMasterTradingSummaryViewRepository.FnShowParticularRecord(sales_order_master_transaction_id,
                pageable, company_id);
    }

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String sales_order_no, int sales_order_version,
                                                                     String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            Map<String, Object> salesOrderMasterRecord = iMtSalesOrderMasterTradingRepository
                    .FnShowSalesOrderMasterRecord(sales_order_no, sales_order_version, financial_year, company_id);

            List<CMtSalesOrderDetailsTradingViewModel> salesOrderDetailsRecords = iMtSalesOrderDetailsTradingViewRepository
                    .FnSalesOrderDetailsRecords(sales_order_no, sales_order_version, company_id);

            List<CMtSalesOrderSchedulesTradingViewModel> salesOrderSchedulesModel = iMtSalesOrderSchedulesTradingViewRepository
                    .FnshowsalesOrderSchedule(sales_order_no, sales_order_version, company_id);

            List<CMtSalesOrderPaymentTermsTradingViewModel> salesOrderPaymentTerms = iMtSalesOrderPaymentTermsTradingViewRepository
                    .FnShowSalesOrderPaymentTerms(sales_order_no, sales_order_version, company_id);

            List<CMtSalesOrderMasterTaxSummaryViewModel> salesOrderTaxSummary = iMtSalesOrderTaxSummaryViewRepository
                    .FnShowSalesOrderTaxSummary(sales_order_no, sales_order_version, company_id);

            List<CMtSalesOrderTermsTradingViewModel> salesOrderTermTrading = iMtSalesOrderTermsTradingRepository
                    .FnShowSalesOrderTermsTrading(sales_order_no, sales_order_version, company_id);

            List<CMtSalesOrderDetailsTrackingTradingViewModel> salesOrderDetailsTrackingTrading = iMtSalesOrderDetailsTrackingTradingViewRepository
                    .FnShowSalesOrderTrackingTrading(sales_order_no, sales_order_version, company_id);

            //Fetch Sales Order Product Dynamic Parameters Record for update
//            List<CMtSalesOrderProductDynamicParametersViewModel> salesOrderProductDynamicParametersRecord =
//                    iMtSalesOrderProductDynamicParametersViewRepository.FnShowSalesOrderProductDynamicParameters(sales_order_no, sales_order_version, company_id);

            //Fetch Sales Order Product Dynamic Parameters Record for update
            List<CMtSalesOrderRawMaterialsDetailsViewModel> cMtSalesOrderRawMaterialsDetailsViewModels =
                    iMtSalesOrderRawMaterialsViewRepository.FnShowSalesOrderRawMaterialsData(sales_order_no, sales_order_version, company_id);

            //Fetch Sales Order sauda shit details
            List<CMtSalesOrderSaudaSheetModel> cMtSalesOrderSaudaSheetModel =
                    iMtSalesOrderSaudaSheetRepository.FnShowParticularRecordForUpdate(sales_order_no, company_id);


            responce.put("salesOrderMasterRecord", salesOrderMasterRecord);
            responce.put("salesOrderDetailsRecords", salesOrderDetailsRecords);
            responce.put("saleOrderSchedule", salesOrderSchedulesModel);
            responce.put("saleOrderPaymentTerms", salesOrderPaymentTerms);
            responce.put("salesOrderTaxSummary", salesOrderTaxSummary);
            responce.put("salesOrderTermTrading", salesOrderTermTrading);
            responce.put("salesOrderDetailsTrackingTrading", salesOrderDetailsTrackingTrading);
//            responce.put("salesOrderProductDynamicParametersRecord", salesOrderProductDynamicParametersRecord);
            responce.put("salesOrderRawMaterialsRecords", cMtSalesOrderRawMaterialsDetailsViewModels);
            responce.put("saudaShitDetails", cMtSalesOrderSaudaSheetModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowSalesOrderDetailsTradingCustomerRecord(
            JSONObject customerOrderNo, Pageable pageable, int company_id) {
        Map<String, Object> resp = new HashMap<>();
        try {
            JSONArray customerOrderNos = customerOrderNo.getJSONArray("cust_order_nos");
            List<Object> queryParams = new ArrayList<>();
            String view = "mtv_sales_order_details_trading";

            StringBuilder query = new StringBuilder("SELECT * FROM ").append(view).append(" WHERE customer_order_no IN").append("(");

            // Append placeholders for the customer_order_no values
            for (int count = 0; count < customerOrderNos.length(); count++) {
                query.append("?");
                queryParams.add(customerOrderNos.getString(count)); // Assuming customerOrderNo is an array of strings
                if (count < customerOrderNos.length() - 1) {
                    query.append(", ");
                }
            }

            query.append(")");


            List<CMtSalesOrderDetailsTradingViewModel> fetchdata = jdbcTemplate.query(query.toString(), queryParams.toArray(),
                    new BeanPropertyRowMapper<>(CMtSalesOrderDetailsTradingViewModel.class));

            resp.put("success", "1");
            resp.put("data", fetchdata);
            resp.put("error", "");
            resp.put("message", "Record added succesfully!...");

        } catch (DataAccessException e) {
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                System.out.println(sqlEx.getMessage());
                resp.put("success", "0");
                resp.put("data", "");
                resp.put("error", sqlEx.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("success", "0");
            resp.put("data", "");
            resp.put("error", e.getMessage());
        }

        return resp;

    }

    @Override
    public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {

        Map<String, Object> response = new HashMap<>();
        String query;

        if ("summary".equals(reportType)) {
            query = "SELECT * FROM mtv_sales_order_master_trading_summary_rpt";
            List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
            response.put("content", results);
        } else {
            query = "SELECT * FROM mtv_sales_order_details_trading_rpt";
            List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
            response.put("content", results);
        }

        return response;
    }

    @Override
    public Page<CMtSalesOrderDetailsTradingViewModel> FnShowParticularRecord(String customer_order_no,
                                                                             Pageable pageable, int company_id) {
        return iMtSalesOrderDetailsTradingViewRepository.FnShowParticularRecord(customer_order_no,
                pageable, company_id);
    }


    @Override
    public Map<String, Object> FnSendEmail(int company_id, int sales_order_master_transaction_id, JSONObject emailData) {
        Map<String, Object> emailingResponse = new HashMap<>();

        EmailController emailController = new EmailController();
        Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);
        Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
        // Update the mail send status in master table.
        if (emailSentStatus) {
            iMtSalesOrderMasterTradingRepository.updateMailStatus("S", company_id, sales_order_master_transaction_id);
        } else {
            iMtSalesOrderMasterTradingRepository.updateMailStatus("F", company_id, sales_order_master_transaction_id);
        }

        emailingResponse.put("Status", emailSentStatus);
        emailingResponse.put("success", emailControllerResponse.get("success"));
        emailingResponse.put("error", emailControllerResponse.get("error"));
        emailingResponse.put("message", emailControllerResponse.get("message"));
        return emailingResponse;
    }

    @Override
    public Map<String, Object> FnAcceptCustomerOrder(JSONObject customerSOAcceptanceJson, int company_id) {
        Map<String, Object> fnAcceptCustomerOrderResponse = new HashMap<>();
        try {
            int sales_order_master_transaction_id = customerSOAcceptanceJson.getInt("sales_order_master_transaction_id");
            String sales_order_acceptance_status = customerSOAcceptanceJson.getString("sales_order_acceptance_status");
            iMtSalesOrderMasterTradingRepository.FnAcceptCustomerOrder(sales_order_acceptance_status, sales_order_master_transaction_id, company_id);

            fnAcceptCustomerOrderResponse.put("success", "1");
            fnAcceptCustomerOrderResponse.put("error", "");
            fnAcceptCustomerOrderResponse.put("message", "Sales Order accepted successfully...! ");

        } catch (Exception e) {
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtSalesOrderMasterTrading/FnAcceptCustomerOrder", 0, e.getMessage());
            fnAcceptCustomerOrderResponse.put("success", "0");
            fnAcceptCustomerOrderResponse.put("data", "");
            fnAcceptCustomerOrderResponse.put("error", e.getMessage());
            e.printStackTrace();
        }
        return fnAcceptCustomerOrderResponse;
    }


    public void moveDirectory(String sanitizedSalesOrderNo) {
        String sourcePath = "C:\\opt\\ERP_DEV\\TempStores\\Trading Sales Order\\" + sanitizedSalesOrderNo + "\\";
        String destinationPath = "C:\\opt\\ERP_DEV\\DocStores\\Trading Sales Order\\" + sanitizedSalesOrderNo + "\\";

        try {
            // Create directories if they do not exist
            Files.createDirectories(Paths.get(destinationPath));

            // Move the directory and its contents
            moveDirectoryRecursively(Paths.get(sourcePath), Paths.get(destinationPath));

            System.out.println("Directory moved successfully.");
        } catch (IOException e) {
            System.err.println("Error moving directory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void moveDirectoryRecursively(Path source, Path destination) throws IOException {
        if (Files.isDirectory(source)) {
            Files.createDirectories(destination);
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(source)) {
                for (Path entry : stream) {
                    moveDirectoryRecursively(entry, destination.resolve(entry.getFileName()));
                }
            }
            Files.delete(source); // Delete the source directory after moving its contents
        } else {
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @Override
    public Map<String, Object> FnCancelSalesOrder(String sales_order_no, int sales_order_version, int company_id, String modified_by) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iMtSalesOrderMasterTradingRepository.cancelSalesMasterDetails(sales_order_no, sales_order_version, company_id, modified_by);

            iMtSalesOrderSchedulesTradingRepository.cancelSalesSchedules(sales_order_no, sales_order_version,company_id, modified_by);

            iMtSalesOrderDetailsTradingRepository.cancelSalesDetails(sales_order_no, sales_order_version,company_id,modified_by);

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
}


