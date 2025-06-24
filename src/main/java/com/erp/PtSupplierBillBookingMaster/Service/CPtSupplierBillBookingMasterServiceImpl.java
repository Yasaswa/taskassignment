package com.erp.PtSupplierBillBookingMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtSalesInvoiceMasterTrading.Model.CMtSalesInvoiceDetailsTradingViewModel;
import com.erp.MtSalesInvoiceMasterTrading.Repository.IMtSalesInvoiceDetailsTradingViewRepository;
import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsViewModel;
import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptMasterViewModel;
import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptsNotesTaxSummaryViewModel;
import com.erp.PtGoodsReceiptDetails.Repository.*;
import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderPaymentTermsViewModel;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderDetailsRepository;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderMasterRepository;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderPaymentTermsViewRepository;
import com.erp.PtPurchaseDetails.Repository.IPtPurchaseOrderSchedulesRepository;
import com.erp.PtSupplierBillBookingMaster.Model.*;
import com.erp.PtSupplierBillBookingMaster.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CPtSupplierBillBookingMasterServiceImpl implements IPtSupplierBillBookingMasterService {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

    @Autowired
    IPtSupplierBillBookingMasterRepository iPtSupplierBillBookingMasterRepository;

    @Autowired
    IPtSupplierBillBookingDetailsRepository iPtSupplierBillBookingDetailsRepository;

    @Autowired
    IPtGoodsReceiptMasterViewRepository iPtGoodsReceiptMasterViewRepository;

    @Autowired
    IPtGoodsReceiptDetailsViewRepository iPtGoodsReceiptDetailsViewRepository;
    @Autowired
    IPtGoodsReceiptsNotesTaxSummaryViewRepository iPtGoodsReceiptsNotesTaxSummaryViewRepository;

    @Autowired
    IPtPurchaseOrderPaymentTermsViewRepository iPtPurchaseOrderPaymentTermsViewRepository;

    @Autowired
    IPtSupplierBillBookingSummaryViewRepository iPtSupplierBillBookingSummaryViewRepository;

    @Autowired
    IPtSupplierBillBookingDetailsViewRepository iPtSupplierBillBookingDetailsViewRepository;

    @Autowired
    IPtPurchaseOrderMasterRepository iPtPurchaseOrderMasterRepository;

    @Autowired
    IPtPurchaseOrderDetailsRepository iPtPurchaseOrderDetailsRepository;

    @Autowired
    IPtPurchaseOrderSchedulesRepository iPtPurchaseOrderSchedulesRepository;

    @Autowired
    IPtGoodsReceiptMasterRepository iPtGoodsReceiptMasterRepository;

    @Autowired
    IPtGoodsReceiptDetailsRepository iPtGoodsReceiptDetailsRepository;

    @Autowired
    IMtSalesInvoiceDetailsTradingViewRepository iMtSalesInvoiceDetailsTradingViewRepository;

    @Autowired
    IFtPurchaseBillTaxSummaryRepository iFtPurchaseBillTaxSummaryRepository;

    @Autowired
    IFtPurchaseBillTaxSummaryViewRepository iFtPurchaseBillTaxSummaryViewRepository;

    @Override
    @Transactional
    public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String isApproveOrCancel) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        boolean update = false;

        JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
        int company_id = commonIdsObj.getInt("company_id");
        String supplier_bill_booking_no = commonIdsObj.getString("supplier_bill_booking_no");
        int supplier_bill_booking_version = commonIdsObj.getInt("supplier_bill_booking_version");
        String financial_year = commonIdsObj.getString("financial_year");

        JSONObject masterjson = jsonObject.getJSONObject("TransHeaderFooterData");
        JSONArray detailsData = (JSONArray) jsonObject.get("TransDetailData");
        JSONArray purchaseBillTaxArray = (JSONArray) jsonObject.get("TransPurchaseBillTaxData");


        try {
            CPtSupplierBillBookingMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
                    CPtSupplierBillBookingMasterModel.class);

            if (isApproveOrCancel.equalsIgnoreCase("approve")) {

                responce = FnBillBookingDetailsUpdateRecord(commonIdsObj, jsonModel, detailsData);

            } else if (isApproveOrCancel.equalsIgnoreCase("cancel")
                    || isApproveOrCancel.equalsIgnoreCase("financeApprove")) {

                responce = FnUpdateBillBookStatusRecords(isApproveOrCancel, jsonModel, detailsData, commonIdsObj);

            } else {
                // Bill Booking Master
                CPtSupplierBillBookingMasterModel getExistingRecord = iPtSupplierBillBookingMasterRepository
                        .getExistingRecord(supplier_bill_booking_no, jsonModel.getSupplier_bill_booking_version(),
                                financial_year, company_id);

                if (getExistingRecord != null) {
                    update = true;
                    getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
                    getExistingRecord.setDeleted_on(new Date());
                    getExistingRecord.setIs_delete(true);
                    iPtSupplierBillBookingMasterRepository.save(getExistingRecord);

                    jsonModel.setSupplier_bill_booking_version(getExistingRecord.getSupplier_bill_booking_version() + 1);

                }
                CPtSupplierBillBookingMasterModel responseSupplierBillBookingMaster = iPtSupplierBillBookingMasterRepository
                        .save(jsonModel);

                // Bill Booking Details
                iPtSupplierBillBookingDetailsRepository.FnUpdateBillBookingDetailsRecord(supplier_bill_booking_no,
                        masterjson.getInt("supplier_bill_booking_version"), company_id);

                List<CPtSupplierBillBookingDetailsModel> cptSupplierBillBookingDetailsModel = objectMapper.readValue(
                        detailsData.toString(), new TypeReference<List<CPtSupplierBillBookingDetailsModel>>() {
                        });

                cptSupplierBillBookingDetailsModel.forEach(items -> {
                    items.setSupplier_bill_booking_version(jsonModel.getSupplier_bill_booking_version());
                    items.setSupplier_bill_booking_master_transaction_id(
                            responseSupplierBillBookingMaster.getSupplier_bill_booking_master_transaction_id());
                });

                List<CPtSupplierBillBookingDetailsModel> supplierBillBookingDetailsRecords = iPtSupplierBillBookingDetailsRepository
                        .saveAll(cptSupplierBillBookingDetailsModel);


                if (!purchaseBillTaxArray.isEmpty()) {
                //Purchase tax Summary
                iFtPurchaseBillTaxSummaryRepository.FnUpdatePurchaseBillTaxSummaryRecord(supplier_bill_booking_no,
                        masterjson.getInt("supplier_bill_booking_version"), company_id);
                List<CPtPurchaseBillTaxSummaryModel> cptPurchaseBillTaxSummaryModel = objectMapper.readValue(
                            purchaseBillTaxArray.toString(), new TypeReference<List<CPtPurchaseBillTaxSummaryModel>>() {
                            });

                    cptPurchaseBillTaxSummaryModel.forEach(pobilltaxitems -> {
                        pobilltaxitems.setSupplier_bill_booking_version(jsonModel.getSupplier_bill_booking_version());
                        pobilltaxitems.setSupplier_bill_booking_master_transaction_id(responseSupplierBillBookingMaster.getSupplier_bill_booking_master_transaction_id());
                    });

                    iFtPurchaseBillTaxSummaryRepository.saveAll(cptPurchaseBillTaxSummaryModel);
                }
                responce.put("data", responseSupplierBillBookingMaster);
                responce.put("success", "1");
                responce.put("error", "");
                responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

                List<String> purchaseOrderNos = supplierBillBookingDetailsRecords.parallelStream()
                        .map(CPtSupplierBillBookingDetailsModel::getPurchase_order_no).distinct()
                        .collect(Collectors.toList());

                List<String> goodReceiptNos = supplierBillBookingDetailsRecords.parallelStream()
                        .map(CPtSupplierBillBookingDetailsModel::getGoods_receipt_no).distinct()
                        .collect(Collectors.toList());

                // After save or update change Purchase Order Master/Details/Schedules status
                // and Good receipt Master/Details status
//				if (!update) {
                iPtPurchaseOrderMasterRepository.FnUpdatePurchaseOrderMasterRecord(purchaseOrderNos, company_id);
                iPtPurchaseOrderDetailsRepository.FnUpdatePurchaseOrderDetailsRecord(purchaseOrderNos, company_id);
                iPtPurchaseOrderSchedulesRepository.FnUpdatePurchaseOrderSchedulesRecord(purchaseOrderNos,
                        company_id);
                iPtGoodsReceiptMasterRepository.FnUpdateGoodsReceiptMasterRecord(goodReceiptNos, company_id);
                iPtGoodsReceiptDetailsRepository.FnUpdateGoodsReceiptDetailsRecord(goodReceiptNos, company_id);
//				}

            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtSupplierBillBookingMaster/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtSupplierBillBookingMaster/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    private Map<String, Object> FnUpdateBillBookStatusRecords(String isApproveOrCancel,
                                                              CPtSupplierBillBookingMasterModel jsonModel, JSONArray detailsData, JSONObject commonIdsObj) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        int company_id = commonIdsObj.getInt("company_id");
        String supplier_bill_booking_no = commonIdsObj.getString("supplier_bill_booking_no");
        String status = "";

        try {
            // Bill Book Master data
            CPtSupplierBillBookingMasterModel responseSupplierBillBookMasterData = iPtSupplierBillBookingMasterRepository
                    .save(jsonModel);

            if (isApproveOrCancel.equalsIgnoreCase("cancel")) {
                status = "X";
                List<CPtSupplierBillBookingDetailsModel> cptSupplierBillBookingDetailsModel = objectMapper.readValue(
                        detailsData.toString(), new TypeReference<List<CPtSupplierBillBookingDetailsModel>>() {
                        });

                List<String> purchaseOrderNos = cptSupplierBillBookingDetailsModel.parallelStream()
                        .map(CPtSupplierBillBookingDetailsModel::getPurchase_order_no).distinct()
                        .collect(Collectors.toList());

                List<String> goodReceiptNos = cptSupplierBillBookingDetailsModel.parallelStream()
                        .map(CPtSupplierBillBookingDetailsModel::getGoods_receipt_no).distinct()
                        .collect(Collectors.toList());

                if (!goodReceiptNos.isEmpty() && !purchaseOrderNos.isEmpty()) {

                    iPtPurchaseOrderMasterRepository.FnUpdatePurchaseOrderStatus(purchaseOrderNos, company_id);
                    iPtPurchaseOrderDetailsRepository.FnUpdatePurchaseOrderDetailsStatus(purchaseOrderNos, company_id);
                    iPtPurchaseOrderSchedulesRepository.FnUpdatePurchaseOrderSchedulesStatus(purchaseOrderNos,
                            company_id);
                    iPtGoodsReceiptMasterRepository.FnUpdateGoodsReceiptMasterStatus(goodReceiptNos, company_id);
                    iPtGoodsReceiptDetailsRepository.FnUpdateGoodsReceiptDetailsStatus(goodReceiptNos, company_id);
                }

                iPtSupplierBillBookingMasterRepository.FnUpdateBillBookingMasterStatus(status, supplier_bill_booking_no,
                        company_id);
                iPtSupplierBillBookingDetailsRepository.FnUpdateBillBookingDetailsStatus(status,
                        supplier_bill_booking_no, company_id);

                responce.put("success", "1");
                responce.put("data", responseSupplierBillBookMasterData);
                responce.put("error", "");
                responce.put("message", "Bill Booking is Canceled");
            } else {

                status = "F";
                iPtSupplierBillBookingMasterRepository.FnUpdateBillBookingMasterStatus(status, supplier_bill_booking_no,
                        company_id);
                iPtSupplierBillBookingDetailsRepository.FnUpdateBillBookingDetailsStatus(status,
                        supplier_bill_booking_no, company_id);

                responce.put("success", "1");
                responce.put("data", responseSupplierBillBookMasterData);
                responce.put("error", "");
                responce.put("message", "Bill Booking is Finance Approve...!");
            }

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtSupplierBillBookingMaster/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtSupplierBillBookingMaster/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    private Map<String, Object> FnBillBookingDetailsUpdateRecord(JSONObject commonIdsObj,
                                                                 CPtSupplierBillBookingMasterModel jsonModel, JSONArray detailsData) {
        Map<String, Object> responce = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        int company_id = commonIdsObj.getInt("company_id");
        try {

            // Bill Book Master data
            CPtSupplierBillBookingMasterModel responseBillBookMasterData = iPtSupplierBillBookingMasterRepository
                    .save(jsonModel);

            // Bill Booking Details
            List<CPtSupplierBillBookingDetailsModel> cptSupplierBillBookingDetailsModel = objectMapper
                    .readValue(detailsData.toString(), new TypeReference<List<CPtSupplierBillBookingDetailsModel>>() {
                    });

            iPtSupplierBillBookingDetailsRepository.saveAll(cptSupplierBillBookingDetailsModel);

            responce.put("success", "1");
            responce.put("data", responseBillBookMasterData);
            responce.put("error", "");
            responce.put("message", "Bill Booking approved successfully...! ");

        } catch (DataAccessException e) {
            e.printStackTrace();
            if (e.getRootCause() instanceof SQLException) {
                SQLException sqlEx = (SQLException) e.getRootCause();
                amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                        "/api/PtSupplierBillBookingMaster/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
                System.out.println(sqlEx.getMessage());
                responce.put("success", "0");
                responce.put("data", "");
                responce.put("error", e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
                    "/api/PtSupplierBillBookingMaster/FnAddUpdateRecord", 0, e.getMessage());
            responce.put("success", "0");
            responce.put("data", "");
            responce.put("error", e.getMessage());
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnDeleteRecord(String supplier_bill_booking_no, int supplier_bill_booking_version,
                                              int company_id, String user_name) {
        Map<String, Object> responce = new HashMap<>();
        try {

            iPtSupplierBillBookingMasterRepository.deleteSupplierBillBookingMasterRecords(supplier_bill_booking_no,
                    supplier_bill_booking_version, company_id, user_name);
            iPtSupplierBillBookingDetailsRepository.deleteSupplierBillBookingDetailsRecords(supplier_bill_booking_no,
                    supplier_bill_booking_version, company_id, user_name);
            iFtPurchaseBillTaxSummaryRepository.deletePurchaseBillTaxSummaryRecords(supplier_bill_booking_no,
                    supplier_bill_booking_version, company_id, user_name);

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
    public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
        Map<String, Object> response = new HashMap<>();
        if ("summary".equals(reportType)) {
            Map<String, Object> results = iPtSupplierBillBookingSummaryViewRepository.FnShowAllSummaryReportRecords();
            response.put("content", results);
        } else {
            Map<String, Object> results = iPtSupplierBillBookingDetailsViewRepository.FnShowAlldetailsReportRecords();
            response.put("content", results);
        }

        return response;
    }

    @Override
    public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String supplier_bill_booking_no,
                                                                     int supplier_bill_booking_version, String financial_year, int company_id) {
        Map<String, Object> responce = new HashMap<>();
        try {

            Map<String, Object> supplierBillBookingMasterRecord = iPtSupplierBillBookingSummaryViewRepository
                    .FnShowSupplierBillBookingMasterRecords(supplier_bill_booking_no, supplier_bill_booking_version,
                            financial_year, company_id);
            List<Map<String, Object>> supplierBillBookingDetailsRecord = iPtSupplierBillBookingDetailsViewRepository
                    .FnShowSupplierBillBookingDetailsRecords(supplier_bill_booking_no, supplier_bill_booking_version,
                            financial_year, company_id);
            List<CFtPurchaseBillTaxSummaryViewModel> purchaseBillTaxSummaryRecords = iFtPurchaseBillTaxSummaryViewRepository
                    .FnShowPurchaseBillTaxSummaryRecords(supplier_bill_booking_no, supplier_bill_booking_version,
                            financial_year, company_id);

            responce.put("SupplierBillBookingMasterRecord", supplierBillBookingMasterRecord);
            responce.put("SupplierBillBookingDetailsRecord", supplierBillBookingDetailsRecord);
            responce.put("PurchaseBillTaxSummaryRecords", purchaseBillTaxSummaryRecords);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responce;
    }

    @Override
    public Map<String, Object> FnShowGoodReceiptSummaryRecords(JSONObject goodReceiptNo, Pageable pageable) {
        Map<String, Object> response = new HashMap<>();

        try {

            List<CPtPurchaseOrderPaymentTermsViewModel> purchaseOrderPaymentTermsData = new ArrayList<>();

            JSONArray goodReceiptNos = goodReceiptNo.getJSONArray("good_receipt_nos");
            JSONObject commonIdsObj = goodReceiptNo.getJSONObject("Ids");

            int company_id = commonIdsObj.getInt("company_id");
            int expected_branch_id = commonIdsObj.getInt("expected_branch_id");
            int supplier_id = commonIdsObj.getInt("supplier_id");
//			int goods_receipt_type_id = commonIdsObj.getInt("goods_receipt_type_id");

            List<String> goodReceiptNosList = goodReceiptNos.toList().stream().map(Object::toString)
                    .collect(Collectors.toList());

            List<CPtGoodsReceiptMasterViewModel> goodReceiptSummaryDetailsRecords = iPtGoodsReceiptMasterViewRepository
                    .FnShowGoodReceiptSummaryDetails(company_id, expected_branch_id, supplier_id, goodReceiptNosList);

            List<CPtGoodsReceiptDetailsViewModel> goodReceiptDetailsRecords = iPtGoodsReceiptDetailsViewRepository
                    .FnShowGoodReceiptDetails(company_id, expected_branch_id, supplier_id, goodReceiptNosList);

            List<CPtGoodsReceiptsNotesTaxSummaryViewModel> goodReceiptTaxSummaryRecords = iPtGoodsReceiptsNotesTaxSummaryViewRepository
                    .FnShowGoodReceiptTaxSummaryViewData(company_id, expected_branch_id, supplier_id, goodReceiptNosList);

            List<String> distinctPoNos = goodReceiptDetailsRecords.parallelStream()
                    .map(CPtGoodsReceiptDetailsViewModel::getPurchase_order_no).distinct().collect(Collectors.toList());

            purchaseOrderPaymentTermsData.addAll(iPtPurchaseOrderPaymentTermsViewRepository
                    .FnShowpurchaseOrderPaymentTermsData(company_id, distinctPoNos));

            response.put("GoodReceiptSummaryDetailsRecords", goodReceiptSummaryDetailsRecords);
            response.put("GoodReceiptDetailsRecords", goodReceiptDetailsRecords);
            response.put("GoodReceiptTaxSummaryRecords", goodReceiptTaxSummaryRecords);
            response.put("PurchaseOrderPaymentTermsData", purchaseOrderPaymentTermsData);
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
    public Map<String, Object> FnSendEmail(int company_id, int supplier_bill_booking_master_transaction_id,
                                           JSONObject emailData) {
        Map<String, Object> emailingResponse = new HashMap<>();

        EmailController emailController = new EmailController();
        Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);

        Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
        // Update the mail send status in master table.
        if (emailSentStatus) {
            iPtSupplierBillBookingMasterRepository.updateMailStatus("S", company_id,
                    supplier_bill_booking_master_transaction_id);
        } else {
            iPtSupplierBillBookingMasterRepository.updateMailStatus("F", company_id,
                    supplier_bill_booking_master_transaction_id);
        }

        emailingResponse.put("Status", emailSentStatus);
        emailingResponse.put("success", emailControllerResponse.get("success"));
        emailingResponse.put("error", emailControllerResponse.get("error"));
        emailingResponse.put("message", emailControllerResponse.get("message"));
        return emailingResponse;
    }

    @Override
    public Map<String, Object> FnShowBillBookingDetailsRecords(int supplier_id, int company_id, int bill_book_type_id) {
        Map<String, Object> response = new HashMap<>();
        try {

            List<CPtSupplierBillBookingDetailsViewModel> supplierBillBookingDetailsRecords = iPtSupplierBillBookingDetailsViewRepository
                    .FnShowSupplierBillBookingDetailsRecords(supplier_id, company_id , bill_book_type_id);

            response.put("SupplierBillBookingDetailsRecords", supplierBillBookingDetailsRecords);
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
    public Map<String, Object> FnShowSalesInvoiceDetailsRecords(int customer_id, int company_id) {
        Map<String, Object> response = new HashMap<>();
        try {

            List<CMtSalesInvoiceDetailsTradingViewModel> salesInvoiceDetailsTradingViewModelRecords =
                    iMtSalesInvoiceDetailsTradingViewRepository.FnShowSalesInvoiceDetailsRecords(customer_id, company_id);

            response.put("SalesInvoiceDetailsRecords", salesInvoiceDetailsTradingViewModelRecords);
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

}
