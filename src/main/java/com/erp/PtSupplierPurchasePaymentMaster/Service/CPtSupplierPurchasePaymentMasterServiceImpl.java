package com.erp.PtSupplierPurchasePaymentMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsViewModel;
import com.erp.PtSupplierBillBookingMaster.Repository.IPtSupplierBillBookingDetailsRepository;
import com.erp.PtSupplierBillBookingMaster.Repository.IPtSupplierBillBookingMasterRepository;
import com.erp.PtSupplierPurchasePaymentMaster.Model.CPtSupplierPurchasePaymentDetailsModel;
import com.erp.PtSupplierPurchasePaymentMaster.Model.CPtSupplierPurchasePaymentMasterModel;
import com.erp.PtSupplierPurchasePaymentMaster.Repository.IPtSupplierPurchasePaymentDetailsRepository;
import com.erp.PtSupplierPurchasePaymentMaster.Repository.IPtSupplierPurchasePaymentDetailsViewRepository;
import com.erp.PtSupplierPurchasePaymentMaster.Repository.IPtSupplierPurchasePaymentMasterRepository;
import com.erp.PtSupplierPurchasePaymentMaster.Repository.IPtSupplierPurchasePaymentMasterSumamryViewRepository;
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
public class CPtSupplierPurchasePaymentMasterServiceImpl implements IPtSupplierPurchasePaymentMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IPtSupplierPurchasePaymentMasterRepository iPtSupplierPurchasePaymentMasterRepository;

	@Autowired
	IPtSupplierPurchasePaymentDetailsRepository iPtSupplierPurchasePaymentDetailsRepository;

	@Autowired
	IPtSupplierPurchasePaymentMasterSumamryViewRepository iPtSupplierPurchasePaymentMasterSumamryViewRepository;

	@Autowired
	IPtSupplierPurchasePaymentDetailsViewRepository iPtSupplierPurchasePaymentDetailsViewRepository;

	@Autowired
	IPtSupplierBillBookingMasterRepository iPtSupplierBillBookingMasterRepository;

	@Autowired
	IPtSupplierBillBookingDetailsRepository iPtSupplierBillBookingDetailsRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, String isApproveOrCancel) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String supplier_purchase_payment_no = commonIdsObj.getString("supplier_purchase_payment_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderFooterData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");

		try {

			CPtSupplierPurchasePaymentMasterModel masterjsonModel = objectMapper.readValue(masterjson.toString(),
					CPtSupplierPurchasePaymentMasterModel.class);

			if (isApproveOrCancel.equalsIgnoreCase("cancel")
					|| isApproveOrCancel.equalsIgnoreCase("financeApprove")) {

				responce = FnPurchasePaymentUpdateRecord(isApproveOrCancel, commonIdsObj, masterjsonModel, detailsArray);

			} else {

				// Supplier Purchase Payment Master
				CPtSupplierPurchasePaymentMasterModel getExistingRecord = iPtSupplierPurchasePaymentMasterRepository
						.getExistingRecord(supplier_purchase_payment_no,
								masterjsonModel.getSupplier_purchase_payment_version(), company_id, financial_year);

				if (getExistingRecord != null) {
					update = true;
					getExistingRecord.setDeleted_by(masterjsonModel.getCreated_by());
					getExistingRecord.setIs_delete(true);
					getExistingRecord.setDeleted_on(new Date());
					iPtSupplierPurchasePaymentMasterRepository.save(getExistingRecord);

					masterjsonModel.setSupplier_purchase_payment_version(getExistingRecord.getSupplier_purchase_payment_version() + 1);
				}
				CPtSupplierPurchasePaymentMasterModel responseSupplierPurchasePaymentMasterModel = iPtSupplierPurchasePaymentMasterRepository
						.save(masterjsonModel);

				// Supplier Purchase Payment Details
				iPtSupplierPurchasePaymentDetailsRepository.FnUpdateSupplierPurchasePaymentDetailsRecord(
						supplier_purchase_payment_no, masterjson.getInt("supplier_purchase_payment_version"), company_id);

				List<CPtSupplierPurchasePaymentDetailsModel> cptSupplierPurchasePaymentDetailsModel = objectMapper
						.readValue(detailsArray.toString(),
								new TypeReference<List<CPtSupplierPurchasePaymentDetailsModel>>() {
								});

				cptSupplierPurchasePaymentDetailsModel.forEach(items -> {
					items.setSupplier_purchase_payment_version(masterjsonModel.getSupplier_purchase_payment_version());
					items.setSupplier_purchase_payment_master_transaction_id(responseSupplierPurchasePaymentMasterModel
							.getSupplier_purchase_payment_master_transaction_id());
				});

				iPtSupplierPurchasePaymentDetailsRepository.saveAll(cptSupplierPurchasePaymentDetailsModel);

				responce.put("data", responseSupplierPurchasePaymentMasterModel);
				responce.put("success", "1");
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/PtSupplierPurchasePaymentMaster/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtSupplierPurchasePaymentMaster/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}


	private Map<String, Object> FnPurchasePaymentUpdateRecord(String isApproveOrCancel, JSONObject commonIdsObj, CPtSupplierPurchasePaymentMasterModel masterjsonModel, JSONArray detailsArray) {
		List<String> inCompleteMaterialBillNos = new ArrayList<>();
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = commonIdsObj.getInt("company_id");
		String supplier_purchase_payment_no = commonIdsObj.getString("supplier_purchase_payment_no");
		String status = "";

		try {
			//Purchase Payment Master data
			CPtSupplierPurchasePaymentMasterModel responseBillBookMasterData = iPtSupplierPurchasePaymentMasterRepository
					.save(masterjsonModel);

			List<CPtSupplierPurchasePaymentDetailsModel> cptSupplierPurchasePaymentDetailsModel = objectMapper.readValue(
					detailsArray.toString(), new TypeReference<List<CPtSupplierPurchasePaymentDetailsModel>>() {
					});
			// get distinct bill book numbers
			List<String> distinctBillBookNumbers = cptSupplierPurchasePaymentDetailsModel.stream()
					.map(CPtSupplierPurchasePaymentDetailsModel::getSupplier_bill_booking_no).distinct().collect(Collectors.toList());

			if (isApproveOrCancel.equalsIgnoreCase("cancel")) {
				status = "X";


				iPtSupplierPurchasePaymentMasterRepository.FnUpdatePurchaePaymentMasterStatus(status, supplier_purchase_payment_no,
						company_id);
				iPtSupplierPurchasePaymentDetailsRepository.FnUpdatePurchasePaymentDetailsStatus(status,
						supplier_purchase_payment_no, company_id);

				for (CPtSupplierPurchasePaymentDetailsModel model : cptSupplierPurchasePaymentDetailsModel) {
					// set bill status as last status finance approve
					String billBookStatus = "F";
					int billBookTransactionId = model.getSupplier_bill_booking_details_transaction_id();
					inCompleteMaterialBillNos.add(model.getSupplier_bill_booking_no());
					iPtSupplierBillBookingDetailsRepository.FnUpdateStatusByPurchasePaymentDetails(billBookTransactionId, billBookStatus);
				}

				// Update BillBOok Master status
				distinctBillBookNumbers.forEach(billNumber -> {
					if (inCompleteMaterialBillNos.contains(billNumber)) {
						// Update status to "F" for incomplete material bills
						iPtSupplierBillBookingDetailsRepository.FnUpdateBillMasterStatusByPayment("F", billNumber);
					}
				});



				responce.put("success", "1");
				responce.put("data", responseBillBookMasterData);
				responce.put("error", "");
				responce.put("message", "Purchase Payment is Canceled");
			} else {
				status = "F";

				iPtSupplierPurchasePaymentMasterRepository.FnUpdatePurchaePaymentMasterStatus(status, supplier_purchase_payment_no,
						company_id);
				iPtSupplierPurchasePaymentDetailsRepository.FnUpdatePurchasePaymentDetailsStatus(status,
						supplier_purchase_payment_no, company_id);

				for (CPtSupplierPurchasePaymentDetailsModel model : cptSupplierPurchasePaymentDetailsModel) {
					// Assuming these fields are of String and Long types respectively
					String billBookStatus = model.getSupplier_bill_item_booking_status();
					int billBookDetailsTransactionId = model.getSupplier_bill_booking_details_transaction_id();
					iPtSupplierBillBookingDetailsRepository.FnUpdateStatusByPurchasePaymentDetails(billBookDetailsTransactionId, billBookStatus);

					if(Objects.equals(billBookStatus, "R")) {
						inCompleteMaterialBillNos.add(model.getSupplier_bill_booking_no());
					}

				}
				// Update BillBOok Master status
				distinctBillBookNumbers.forEach(billNumber -> {
					if (inCompleteMaterialBillNos.contains(billNumber)) {
						// Update status to "I" for incomplete material bills
						iPtSupplierBillBookingDetailsRepository.FnUpdateBillMasterStatusByPayment("R", billNumber);
					} else {
						// Update status to "C" for complete material bills
						iPtSupplierBillBookingDetailsRepository.FnUpdateBillMasterStatusByPayment("C", billNumber);
					}
				});
				responce.put("success", "1");
				responce.put("data", responseBillBookMasterData);
				responce.put("error", "");
				responce.put("message", "Purchase Payment is Finance Approve...!");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/PtSupplierPurchasePaymentMaster/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtSupplierPurchasePaymentMaster/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String supplier_purchase_payment_no,
	                                          int supplier_purchase_payment_version, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iPtSupplierPurchasePaymentMasterRepository.deleteSupplierPurchasePaymentMasterRecords(
					supplier_purchase_payment_no, supplier_purchase_payment_version, company_id);
			iPtSupplierPurchasePaymentDetailsRepository.deleteSupplierPurchasePaymentDetailsRecords(
					supplier_purchase_payment_no, supplier_purchase_payment_version, company_id);

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
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String supplier_purchase_payment_no,
	                                                                 int supplier_purchase_payment_version, String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> supplierPurchasePaymentMasterRecord = iPtSupplierPurchasePaymentMasterSumamryViewRepository
					.FnShowSupplierPurchasePaymentMasterRecords(supplier_purchase_payment_no,
							supplier_purchase_payment_version, financial_year, company_id);

			List<Map<String, Object>> supplierPurchasePaymentDetailsRecord = iPtSupplierPurchasePaymentDetailsViewRepository
					.FnShowSupplierPurchasePaymentDetailsRecords(supplier_purchase_payment_no,
							supplier_purchase_payment_version, financial_year, company_id);

			responce.put("SupplierPurchasePaymentMasterRecord", supplierPurchasePaymentMasterRecord);
			responce.put("SupplierPurchasePaymentDetailsRecord", supplierPurchasePaymentDetailsRecord);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		if ("summary".equals(reportType)) {
			Map<String, Object> results = iPtSupplierPurchasePaymentMasterSumamryViewRepository.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
			Map<String, Object> results = iPtSupplierPurchasePaymentMasterSumamryViewRepository.FnShowAlldetailsReportRecords();
			response.put("content", results);
		}

		return response;
	}

	@Override
	public Map<String, Object> FnSendEmail(int company_id, int supplierPurchasePaymentMasterTransactionId, JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);

		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
		// Update the mail send status in master table.
		if (emailSentStatus) {
			iPtSupplierPurchasePaymentMasterRepository.updateMailStatus("S", company_id, supplierPurchasePaymentMasterTransactionId);
		} else {
			iPtSupplierPurchasePaymentMasterRepository.updateMailStatus("F", company_id, supplierPurchasePaymentMasterTransactionId);
		}
		emailingResponse.put("Status", emailSentStatus);
		emailingResponse.put("success", emailControllerResponse.get("success"));
		emailingResponse.put("error", emailControllerResponse.get("error"));
		emailingResponse.put("message", emailControllerResponse.get("message"));
		return emailingResponse;
	}
}
