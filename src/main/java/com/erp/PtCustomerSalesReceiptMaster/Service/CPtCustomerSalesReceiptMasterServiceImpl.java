package com.erp.PtCustomerSalesReceiptMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.PtCustomerSalesReceiptMaster.Model.CPtCustomerSalesReceiptDetailsModel;
import com.erp.PtCustomerSalesReceiptMaster.Model.CPtCustomerSalesReceiptMasterModel;
import com.erp.PtCustomerSalesReceiptMaster.Repository.IPtCustomerSalesReceiptDetailsRepository;
import com.erp.PtCustomerSalesReceiptMaster.Repository.IPtCustomerSalesReceiptDetailsViewRepository;
import com.erp.PtCustomerSalesReceiptMaster.Repository.IPtCustomerSalesReceiptMasterRepository;
import com.erp.PtCustomerSalesReceiptMaster.Repository.IPtCustomerSalesReceiptMasterSummaryViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CPtCustomerSalesReceiptMasterServiceImpl implements IPtCustomerSalesReceiptMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IPtCustomerSalesReceiptMasterRepository iPtCustomerSalesReceiptMasterRepository;

	@Autowired
	IPtCustomerSalesReceiptDetailsRepository iPtCustomerSalesReceiptDetailsRepository;

	@Autowired
	IPtCustomerSalesReceiptMasterSummaryViewRepository iPtCustomerSalesReceiptMasterSummaryViewRepository;

	@Autowired
	IPtCustomerSalesReceiptDetailsViewRepository iPtCustomerSalesReceiptDetailsViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String isApproveOrCancel) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String customer_sales_receipt_no = commonIdsObj.getString("customer_sales_receipt_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterjson = jsonObject.getJSONObject("customerSalesReceiptMasterData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("customerSalesReceiptDetailsData");

		try {

//			CPtCustomerSalesReceiptMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
//					CPtCustomerSalesReceiptMasterModel.class);
//
//			if (isApproveOrCancel.equalsIgnoreCase("approve")) {
//
//				responce = FnCustomerSalesReceiptDetailsUpdateRecord(commonIdsObj, jsonModel, detailsArray);
//
//			} else if (isApproveOrCancel.equalsIgnoreCase("cancel")) {
//				
//				responce = FnUpdateCustomerSalesReceiptStatusRecords(jsonModel, commonIdsObj);
//				
//			}
			CPtCustomerSalesReceiptMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CPtCustomerSalesReceiptMasterModel.class);

			if (isApproveOrCancel.equalsIgnoreCase("cancel")
					|| isApproveOrCancel.equalsIgnoreCase("financeApprove")) {

				responce = FnCustomerSalesReceiptDetailsUpdateRecord(isApproveOrCancel, commonIdsObj, jsonModel, detailsArray);

			} else {
				// Customer Sales Receipt Master
				CPtCustomerSalesReceiptMasterModel getExistingRecord = iPtCustomerSalesReceiptMasterRepository
						.getExistingRecord(customer_sales_receipt_no, jsonModel.getCustomer_sales_receipt_version(),
								company_id, financial_year);

				if (getExistingRecord != null) {
					update = true;
					getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
					getExistingRecord.setIs_delete(true);
					getExistingRecord.setDeleted_on(new Date());
					iPtCustomerSalesReceiptMasterRepository.save(getExistingRecord);

					jsonModel.setCustomer_sales_receipt_version(
							getExistingRecord.getCustomer_sales_receipt_version() + 1);

				}
				CPtCustomerSalesReceiptMasterModel responseCustomerSalesReceiptMasterModel = iPtCustomerSalesReceiptMasterRepository
						.save(jsonModel);

				// Customer Sales Receipt Details
				iPtCustomerSalesReceiptDetailsRepository.FnUpdateCustomerSalesReceiptDetailsRecord(
						customer_sales_receipt_no, masterjson.getInt("customer_sales_receipt_version"), company_id);

				List<CPtCustomerSalesReceiptDetailsModel> cptSupplierPurchasePaymentDetailsModel = objectMapper
						.readValue(detailsArray.toString(),
								new TypeReference<List<CPtCustomerSalesReceiptDetailsModel>>() {
								});

				cptSupplierPurchasePaymentDetailsModel.forEach(items -> {
					items.setCustomer_sales_receipt_version(jsonModel.getCustomer_sales_receipt_version());
					items.setCustomer_sales_receipt_master_transaction_id(
							responseCustomerSalesReceiptMasterModel.getCustomer_sales_receipt_master_transaction_id());
				});

				iPtCustomerSalesReceiptDetailsRepository.saveAll(cptSupplierPurchasePaymentDetailsModel);

				responce.put("data", responseCustomerSalesReceiptMasterModel);
				responce.put("success", "1");
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/PtCustomerSalesReceiptMaster/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtCustomerSalesReceiptMaster/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	private Map<String, Object> FnUpdateCustomerSalesReceiptStatusRecords(CPtCustomerSalesReceiptMasterModel jsonModel,
	                                                                      JSONObject commonIdsObj) {
		Map<String, Object> responce = new HashMap<>();
		int company_id = commonIdsObj.getInt("company_id");
		String customer_sales_receipt_no = commonIdsObj.getString("customer_sales_receipt_no");
		String status = "";
		try {
			//Saved Customer Sales Receipt Master
			CPtCustomerSalesReceiptMasterModel responseCustomerSalesReceiptMasterModel = iPtCustomerSalesReceiptMasterRepository
					.save(jsonModel);

			status = "X";
			iPtCustomerSalesReceiptMasterRepository.FnUpdateCustomerSalesReceiptMasterStatus(status,
					customer_sales_receipt_no, company_id);
			iPtCustomerSalesReceiptDetailsRepository.FnUpdateCustomerSalesReceiptDetailsStatus(status,
					customer_sales_receipt_no, company_id);

			responce.put("success", "1");
			responce.put("data", responseCustomerSalesReceiptMasterModel);
			responce.put("error", "");
			responce.put("message", "Customer Sales Receipt is Canceled");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/PtCustomerSalesReceiptMaster/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtCustomerSalesReceiptMaster/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	private Map<String, Object> FnCustomerSalesReceiptDetailsUpdateRecord(String isApproveOrCancel, JSONObject commonIdsObj,
	                                                                      CPtCustomerSalesReceiptMasterModel jsonModel, JSONArray detailsArray) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = commonIdsObj.getInt("company_id");
		String customer_sales_receipt_no = commonIdsObj.getString("customer_sales_receipt_no");
		String status = "";
		try {

			// Customer Sales Receipt Master
			CPtCustomerSalesReceiptMasterModel responseCustomerSalesReceiptMasterModel = iPtCustomerSalesReceiptMasterRepository
					.save(jsonModel);

			List<CPtCustomerSalesReceiptDetailsModel> cptCustomerSalesReceiptDetailsModel = objectMapper.readValue(
					detailsArray.toString(), new TypeReference<List<CPtCustomerSalesReceiptDetailsModel>>() {
					});

			if (isApproveOrCancel.equalsIgnoreCase("cancel")) {
				status = "X";


				iPtCustomerSalesReceiptMasterRepository.FnUpdateCustomerSalesReceiptMasterStatus(status, customer_sales_receipt_no,
						company_id);
				iPtCustomerSalesReceiptDetailsRepository.FnUpdateCustomerSalesReceiptDetailsStatus(status,
						customer_sales_receipt_no, company_id);

				responce.put("success", "1");
				responce.put("data", responseCustomerSalesReceiptMasterModel);
				responce.put("error", "");
				responce.put("message", "Purchase Payment is Canceled");
			} else {
				status = "F";
				for (CPtCustomerSalesReceiptDetailsModel model : cptCustomerSalesReceiptDetailsModel) {
					// Assuming these fields are of String and Long types respectively
					String invoiceStatus = model.getSales_invoice_item_status();
					int invoiceTransactionId = model.getSales_invoice_details_transaction_id();

					iPtCustomerSalesReceiptDetailsRepository.FnUpdateStatusByCustomerReceiptDetails(invoiceTransactionId, invoiceStatus);
				}
				iPtCustomerSalesReceiptMasterRepository.FnUpdateCustomerSalesReceiptMasterStatus(status, customer_sales_receipt_no,
						company_id);
				iPtCustomerSalesReceiptDetailsRepository.FnUpdateCustomerSalesReceiptDetailsStatus(status,
						customer_sales_receipt_no, company_id);

				responce.put("success", "1");
				responce.put("data", responseCustomerSalesReceiptMasterModel);
				responce.put("error", "");
				responce.put("message", "Purchase Payment is Finance Approve...!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtCustomerSalesReceiptMaster/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String customer_sales_receipt_no, int customer_sales_receipt_version,
	                                          int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iPtCustomerSalesReceiptMasterRepository.deleteCustomerSalesReceiptMasterRecords(customer_sales_receipt_no,
					customer_sales_receipt_version, company_id);
			iPtCustomerSalesReceiptDetailsRepository.deleteCustomerSalesReceiptDetailsRecords(customer_sales_receipt_no,
					customer_sales_receipt_version, company_id);

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
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String customer_sales_receipt_no,
	                                                                 int customer_sales_receipt_version, String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			Map<String, Object> customerSalesReceiptMasterRecord = iPtCustomerSalesReceiptMasterSummaryViewRepository
					.FnShowCustomerSalesReceiptMasterRecords(customer_sales_receipt_no, customer_sales_receipt_version,
							financial_year, company_id);
			List<Map<String, Object>> customerSalesReceiptDetailsRecord = iPtCustomerSalesReceiptDetailsViewRepository
					.FnShowCustomerSalesReceiptDetailsRecords(customer_sales_receipt_no, customer_sales_receipt_version,
							financial_year, company_id);

			responce.put("CustomerSalesReceiptMasterRecord", customerSalesReceiptMasterRecord);
			responce.put("CustomerSalesReceiptDetailsRecord", customerSalesReceiptDetailsRecord);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		if ("summary".equals(reportType)) {
			Map<String, Object> results = iPtCustomerSalesReceiptMasterSummaryViewRepository
					.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
			Map<String, Object> results = iPtCustomerSalesReceiptMasterSummaryViewRepository
					.FnShowAlldetailsReportRecords();
			response.put("content", results);
		}
		return response;
	}

	@Override
	public Map<String, Object> FnSendEmail(int company_id, int customer_sales_receipt_master_transaction_id,
	                                       JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);

		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
		// Update the mail send status in master table.
		if (emailSentStatus) {
			iPtCustomerSalesReceiptMasterRepository.updateMailStatus("S", company_id,
					customer_sales_receipt_master_transaction_id);
		} else {
			iPtCustomerSalesReceiptMasterRepository.updateMailStatus("F", company_id,
					customer_sales_receipt_master_transaction_id);
		}

		emailingResponse.put("Status", emailSentStatus);
		emailingResponse.put("success", emailControllerResponse.get("success"));
		emailingResponse.put("error", emailControllerResponse.get("error"));
		emailingResponse.put("message", emailControllerResponse.get("message"));
		return emailingResponse;
	}

}
