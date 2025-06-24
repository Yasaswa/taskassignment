package com.erp.MtQuotationDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryDetailsTradingModel;
import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryMasterTradingModel;
import com.erp.MtEnquiryDetailsTrading.Repository.IMtEnquiryDetailsTradingRepository;
import com.erp.MtEnquiryDetailsTrading.Repository.IMtEnquiryMasterTradingSummaryRepository;
import com.erp.MtQuotationDetails.Model.*;
import com.erp.MtQuotationDetails.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CMtQuotationDetailsServiceImpl implements IMtQuotationDetailsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtSalesQuotationMasterTradingRepository iMtSalesQuotationMasterTradingRepository;

	@Autowired
	IMtSalesQuotationDetailsTradingRepository iMtSalesQuotationDetailsTradingRepository;

	@Autowired
	IMtSalesQuotationDetailsTradingViewRepository iMtSalesQuotationDetailsTradingViewRepository;

	@Autowired
	IMtSalesQuotationTermsTradingRepository iMtSalesQuotationTermsTradingRepository;

	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityRepository iMtSalesQuotationExistingExpectedFunctionalityRepository;

	@Autowired
	IMtSalesQuotationTermsTradingViewRepository iMtSalesQuotationTermsTradingViewRepository;

	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityViewRepository iMtSalesQuotationExistingExpectedFunctionalityViewRepository;

	@Autowired
	IMtSalesQuotationPaymentTermsTradingRepository iMtSalesQuotationPaymentTermsTradingRepository;

	@Autowired
	IMtQuotationPaymentTermsTradingViewRepository iMtQuotationPaymentTermsTradingViewRepository;

	@Autowired
	IMtSalesQuotationFollowupTradingRepository iMtSalesQuotationFollowupTradingRepository;

	@Autowired
	IMtSalesQuotationFollowupTradingViewRepository iMtSalesQuotationFollowupTradingViewRepository;

	// Enquiry Repos.
	@Autowired
	IMtEnquiryDetailsTradingRepository iMtEnquiryDetailsTradingRepository;
	@Autowired
	IMtEnquiryMasterTradingSummaryRepository iMtEnquiryMasterTradingSummaryRepository;

	@Autowired
	private JdbcTemplate executeQuery;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		String quotation_no = commonIdsObj.getString("quotation_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderFooterData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
		JSONArray termsArray = (JSONArray) jsonObject.get("TransTermsData");
		JSONArray existingFucntionalityArray = (JSONArray) jsonObject.get("TransExistingFucntionalityData");
		JSONArray payment_termsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");

		try {
			CMtSalesQuotationMasterTradingModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CMtSalesQuotationMasterTradingModel.class);

			if (!isApprove) {
				String quotation_date = jsonModel.getQuotation_date();

//			Quotation Master Trading

				CMtSalesQuotationMasterTradingModel cmtSalesQuotationMasterTradingModel = new CMtSalesQuotationMasterTradingModel();

				String query = "Select * FROM mt_sales_quotation_master_trading WHERE is_delete = 0 and quotation_no = '"
						+ quotation_no.toString() + "' and quotation_version = " + jsonModel.getQuotation_version()
						+ " and financial_year = " + financial_year + " and company_id = " + company_id + "";

				List<CMtSalesQuotationMasterTradingModel> results = executeQuery.query(query,
						new BeanPropertyRowMapper<>(CMtSalesQuotationMasterTradingModel.class));

				if (!results.isEmpty()) {
					update = true;
					cmtSalesQuotationMasterTradingModel = results.get(0);
					cmtSalesQuotationMasterTradingModel.setDeleted_on(new Date());
					cmtSalesQuotationMasterTradingModel.setIs_delete(true);
					iMtSalesQuotationMasterTradingRepository.save(cmtSalesQuotationMasterTradingModel);

					jsonModel.setQuotation_version(cmtSalesQuotationMasterTradingModel.getQuotation_version() + 1);
				}

				CMtSalesQuotationMasterTradingModel responceQuotationMasterTrading = iMtSalesQuotationMasterTradingRepository
						.save(jsonModel);

//			Quotation Details Trading

				iMtSalesQuotationDetailsTradingRepository.updateStatus(quotation_no,
						masterjson.getInt("quotation_version"), company_id);

				List<CMtSalesQuotationDetailsTradingModel> cmtSalesQuotationDetailsTradingModel = objectMapper
						.readValue(detailsArray.toString(),
								new TypeReference<List<CMtSalesQuotationDetailsTradingModel>>() {
								});

				cmtSalesQuotationDetailsTradingModel.forEach(items -> {
					items.setQuotation_version(jsonModel.getQuotation_version());
					items.setQuotation_master_transaction_id(
							responceQuotationMasterTrading.getQuotation_master_transaction_id());
				});

				iMtSalesQuotationDetailsTradingRepository.saveAll(cmtSalesQuotationDetailsTradingModel);

//			Quotation Terms Trading

				if (!termsArray.isEmpty()) {
					iMtSalesQuotationTermsTradingRepository.updateSalesQuotationTermsStatus(quotation_no,
							masterjson.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationTermsTradingModel> cmtSalesQuotationTermsTradingModel = objectMapper
							.readValue(termsArray.toString(),
									new TypeReference<List<CMtSalesQuotationTermsTradingModel>>() {
									});

					cmtSalesQuotationTermsTradingModel.forEach(items -> {
						items.setQuotation_version(jsonModel.getQuotation_version());
						items.setQuotation_master_transaction_id(
								responceQuotationMasterTrading.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationTermsTradingRepository.saveAll(cmtSalesQuotationTermsTradingModel);
				}

//			Quotation Existing Expected Functionality

				if (!existingFucntionalityArray.isEmpty()) {
					iMtSalesQuotationExistingExpectedFunctionalityRepository.updateExistingExpectedFunctionalityStatus(
							quotation_no, masterjson.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationExistingExpectedFunctionalityModel> cmtSalesQuotationExistingExpectedFunctionalityModel = objectMapper
							.readValue(existingFucntionalityArray.toString(),
									new TypeReference<List<CMtSalesQuotationExistingExpectedFunctionalityModel>>() {
									});

					cmtSalesQuotationExistingExpectedFunctionalityModel.forEach(items -> {
						items.setQuotation_version(jsonModel.getQuotation_version());
						items.setQuotation_master_transaction_id(
								responceQuotationMasterTrading.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationExistingExpectedFunctionalityRepository
							.saveAll(cmtSalesQuotationExistingExpectedFunctionalityModel);
				}
//				Quotation Payment Terms Functionality

				if (!payment_termsArray.isEmpty()) {
					iMtSalesQuotationPaymentTermsTradingRepository.updatePaymentTermsStatus(quotation_no,
							masterjson.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationPaymentTermsTradingModel> tradingOrderPaymentTerms = objectMapper.readValue(
							payment_termsArray.toString(),
							new TypeReference<List<CMtSalesQuotationPaymentTermsTradingModel>>() {
							});
					tradingOrderPaymentTerms.forEach(paymentTerm -> {
						paymentTerm.setQuotation_version(jsonModel.getQuotation_version());
						paymentTerm.setQuotation_master_transaction_id(
								responceQuotationMasterTrading.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationPaymentTermsTradingRepository.saveAll(tradingOrderPaymentTerms);

				}

				responce.put("success", "1");
				responce.put("data", responceQuotationMasterTrading);
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully!" : "Record added successfully!");
			} else {
				responce = FnQuotationDetailsUpdateRecord(detailsArray, jsonModel, commonIdsObj);
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtQuotationDetails/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtQuotationDetails/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	private Map<String, Object> FnQuotationDetailsUpdateRecord(JSONArray detailsArray,
	                                                           CMtSalesQuotationMasterTradingModel jsonModel, JSONObject commonIdsObj) {

		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		int company_id = commonIdsObj.getInt("company_id");

		try {
			// Quotation master
			CMtSalesQuotationMasterTradingModel updatedQTMasterData = iMtSalesQuotationMasterTradingRepository.save(jsonModel);

			// Quotation details
			List<CMtSalesQuotationDetailsTradingModel> quotationDetails = objectMapper.readValue(
					detailsArray.toString(), new TypeReference<List<CMtSalesQuotationDetailsTradingModel>>() {
					});
			List<CMtSalesQuotationDetailsTradingModel> quotationFollowupTradingRecords = iMtSalesQuotationDetailsTradingRepository.saveAll(quotationDetails);

			// ***** Update the Enquiry status.
			if ("E".equals(updatedQTMasterData.getQuotation_transaction_type())) {
				String detailModifiedBy = quotationDetails.get(0).getCreated_by();

				List<String> distinctEnquiryNos = quotationDetails.parallelStream()
						.map(CMtSalesQuotationDetailsTradingModel::getenquiry_no).distinct()
						.collect(Collectors.toList());

				// 1. Updating the enquiry-details status.
				List<CMtEnquiryDetailsTradingModel> enquiryDetailsFromDB = iMtEnquiryDetailsTradingRepository.FnShowEnquiryDetailsByEnqNos(company_id, distinctEnquiryNos);
				List<CMtEnquiryDetailsTradingModel> updatedEnquiryDetails = new ArrayList<CMtEnquiryDetailsTradingModel>();

				// Iterate the loop on the distinctEnquiryNos.
				distinctEnquiryNos.forEach(enquiryNo -> {
					// From enquiryDetailsFromDB(means received data from enquiry_details table) collect only the the current enquiryNo related details.
					List<CMtEnquiryDetailsTradingModel> currentEnqNoDetailsFromDB = enquiryDetailsFromDB.stream()
							.filter(enqMaterial -> enquiryNo.equals(enqMaterial.getEnquiry_no()))
							.collect(Collectors.toList());

					// From quotation materials collect all the quotationMaterial having enquiry_no. current.
					List<CMtSalesQuotationDetailsTradingModel> quotationDetailsEnqBased = quotationDetails.stream()
							.filter(quotationMat -> enquiryNo.equals(quotationMat.getenquiry_no()))
							.collect(Collectors.toList());

					// Now iterate the loop on the quotation-material and find product_id matchingobject.
					quotationDetailsEnqBased.forEach(quotationMaterial -> {
						String product_material_id = quotationMaterial.getProduct_material_id();
						Optional<CMtEnquiryDetailsTradingModel> matchingEnqDetailObjs = currentEnqNoDetailsFromDB.stream()
								.filter(enqDetail -> product_material_id.equals(enqDetail.getProduct_material_id()))
								.findFirst();

						if (matchingEnqDetailObjs.isPresent()) {
							CMtEnquiryDetailsTradingModel matchedEnqDetail = matchingEnqDetailObjs.get();
							// Update the enquiry detail status.
							String currentQuotationMatStatus = quotationMaterial.getQuotation_item_status();
							if ("A".equals(currentQuotationMatStatus)) { // If it is approved the enquiry item status is  quotation.
								matchedEnqDetail.setEnquiry_item_status("Q");
							} else if ("R".equals(currentQuotationMatStatus)) { // If it is rejected the enquiry item status is rejected.
								matchedEnqDetail.setEnquiry_item_status("R");
							}

							matchedEnqDetail.setModified_by(detailModifiedBy);

							// add the so_details in the list for update the so-details.
							updatedEnquiryDetails.add(matchedEnqDetail);
						}
					});
				});

				if (!updatedEnquiryDetails.isEmpty()) {
					iMtEnquiryDetailsTradingRepository.saveAll(updatedEnquiryDetails);
				}

//							----------------- For Update the Enquiry master status.
				// get all enquiry details after details updatation.
				List<CMtEnquiryDetailsTradingModel> enquiryDetailsFromDBAfterUpdate = iMtEnquiryDetailsTradingRepository.FnShowEnquiryDetailsByEnqNos(company_id, distinctEnquiryNos);

				// get all sales-order masters records for distinctSoNos.
				List<CMtEnquiryMasterTradingModel> enquiryMasters = iMtEnquiryMasterTradingSummaryRepository.FnShowEnquiriesByEnqNos(company_id, distinctEnquiryNos);

				// To Store the all enquiry-master records for update.
				List<CMtEnquiryMasterTradingModel> enquiryMastersForUpdate = new ArrayList<CMtEnquiryMasterTradingModel>();

				// Iterate the loop on enquiry master records.
				enquiryMasters.forEach(enquiryMst -> {
					String enquiryNo = enquiryMst.getEnquiry_no();
					// First from enquiryDetailsFromDBAfterUpdate get all details and compare all status.
					List<CMtEnquiryDetailsTradingModel> currentEnquiryDetails = enquiryDetailsFromDBAfterUpdate
							.stream().filter(enqDetail -> enquiryNo.equals(enqDetail.getEnquiry_no()))
							.collect(Collectors.toList());

					// Collect all itemStatus values into a List
					List<String> EnqItemStatusList = currentEnquiryDetails.stream()
							.map(CMtEnquiryDetailsTradingModel::getEnquiry_item_status).collect(Collectors.toList());

					boolean allMaterialStatusIsRejected = EnqItemStatusList.stream().allMatch(status -> status.equals("R"));
					boolean allMaterialStatusIsQTSent = EnqItemStatusList.stream().allMatch(status -> status.equals("Q"));

					if (allMaterialStatusIsQTSent) {
						enquiryMst.setEnquiry_status("Q");
					} else if (allMaterialStatusIsRejected) {
						enquiryMst.setEnquiry_status("R");
					}
					enquiryMst.setModified_by(detailModifiedBy);
					enquiryMastersForUpdate.add(enquiryMst); // Add that updated object in the list.
				});

				// save the Updated the sales_order_masters.
				iMtEnquiryMasterTradingSummaryRepository.saveAll(enquiryMastersForUpdate);

//				iMtEnquiryDetailsTradingRepository.FnUpdateEQItemsStatusByQuotation(distinctEnquiryNos, company_id);
//				iMtEnquiryMasterTradingSummaryRepository.FnUpdateEQStatusByQuotation(distinctEnquiryNos, company_id);
			}

			responce.put("success", "1");
			responce.put("data", updatedQTMasterData);
			responce.put("error", "");
			String quotation_status = jsonModel.getQuotation_status();
			responce.put("message", quotation_status.equals("R") ? "Quotation has been rejected...! "
					: "Quotation has been approved successfully...! ");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtQuotationDetails/FnPurchaseOrderDetailsUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtQuotationDetails/FnPurchaseOrderDetailsUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnGetQuotationDetailsByItemStatus(JSONObject jsonObject, int company_id) {
		Map<String, Object> responce = new HashMap<>();

		try {
			int quotation_version = jsonObject.getInt("quotation_version");
			String quotation_no = jsonObject.getString("quotation_no");
			String financial_year = jsonObject.getString("financial_year");
			String quotation_item_status = jsonObject.getString("quotation_item_status");

			List<CMtSalesQuotationDetailsTradingViewModel> responseDetailsData = iMtSalesQuotationDetailsTradingViewRepository
					.FnGetQuotationDetailsByItemStatus(quotation_no, quotation_version, financial_year,
							quotation_item_status, company_id);

			List<CMtSalesQuotationPaymentTermsTradingViewModel> responsePaymentTermsData = iMtQuotationPaymentTermsTradingViewRepository
					.FnGetQuotationPaymentTermsData(quotation_no, quotation_version, company_id);

			responce.put("quotationDetailsData", responseDetailsData);
			responce.put("quotationPaymentTermsData", responsePaymentTermsData);
			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtQuotationDetails/FnGetQuotationDetailsByItemStatus", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtQuotationDetails/FnGetQuotationDetailsByItemStatus", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}

		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String quotation_no, int quotation_version, int company_id,
	                                          String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			iMtSalesQuotationMasterTradingRepository.deleteQotationMaster(quotation_no, quotation_version, company_id,
					deleted_by); // For master.
			iMtSalesQuotationDetailsTradingRepository.deleteQuotationDetails(quotation_no, quotation_version,
					company_id, deleted_by); // For details.
			iMtSalesQuotationTermsTradingRepository.deleteQuotationDetails(quotation_no, quotation_version, company_id,
					deleted_by); // For common terms.
			iMtSalesQuotationExistingExpectedFunctionalityRepository.deleteQuotationDetails(quotation_no,
					quotation_version, company_id, deleted_by); // For Existing Functionality.
			iMtSalesQuotationPaymentTermsTradingRepository.deleteQuotationDetails(quotation_no, quotation_version,
					company_id, deleted_by); // For Payment Terms.
			iMtSalesQuotationFollowupTradingRepository.deleteQuotationFollowupTradingDetails(quotation_no,
					quotation_version, company_id, deleted_by); // For FollowUps
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
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String quotation_no, int quotation_version,
	                                                                 String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> quotationMasterTradingRecords = iMtSalesQuotationMasterTradingRepository
					.FnShowQuotationMasterTradingRecord(quotation_no, quotation_version, financial_year, company_id);

			List<Map<String, Object>> quotationDetailsTradingRecords = iMtSalesQuotationDetailsTradingRepository
					.FnShowQuotationDetailsTradingRecord(quotation_no, quotation_version, financial_year, company_id);

			List<CMtSalesQuotationTermsTradingViewModel> quotationTermsTradingRecords = iMtSalesQuotationTermsTradingViewRepository
					.FnShowQuotationTermsTradingRecord(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationExistingExpectedFunctionalityViewModel> quotationExistingExpectedFucntionalityRecords = iMtSalesQuotationExistingExpectedFunctionalityViewRepository
					.FnShowQuotationExistingExpectedFucntionalityRecord(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationPaymentTermsTradingViewModel> quotationPaymentTermsRecord = iMtQuotationPaymentTermsTradingViewRepository
					.FnShowQuotationPaymentTerms(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationFollowupTradingViewModel> quotationFollowupTradingDetails = iMtSalesQuotationFollowupTradingViewRepository
					.FnShowQuotationFollowupTradingRecord(quotation_no, company_id);

			responce.put("QuotationMasterTradingRecords", quotationMasterTradingRecords);
			responce.put("QuotationDetailsTradingRecords", quotationDetailsTradingRecords);
			responce.put("QuotationTermsTradingRecords", quotationTermsTradingRecords);
			responce.put("QuotationExistingExpectedFucntionalityRecords",
					quotationExistingExpectedFucntionalityRecords);
			responce.put("QuotationPaymentTermsRecord", quotationPaymentTermsRecord);
			responce.put("QuotationFollowupTradingDetails", quotationFollowupTradingDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowEnquiryDetailsRecords(JSONObject enquiryNo, int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {
			JSONArray enquiryNos = enquiryNo.getJSONArray("enquiryNos");
			List<Object> queryParams = new ArrayList<>();
			String view = "mtv_enquiry_details_trading";

			StringBuilder query = new StringBuilder("SELECT * FROM ").append(view).append(" WHERE enquiry_no IN")
					.append("(");

			// Append placeholders for the purchase_order_no values
			for (int count = 0; count < enquiryNos.length(); count++) {
				query.append("?");
				queryParams.add(enquiryNos.getString(count));
				if (count < enquiryNos.length() - 1) {
					query.append(", ");
				}
			}

			query.append(") and is_delete = 0 and enquiry_item_status = 'A'");

			System.out.println("FnShowEnquiryDetailsRecords: " + query);
			List<Map<String, Object>> fetchEnquiryDetailsData = executeQuery.queryForList(query.toString(),
					queryParams.toArray());

			response.put("EnquiryDetailsData", fetchEnquiryDetailsData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		String query;
		if ("summary".equals(reportType)) {
			query = "SELECT * FROM mtv_sales_quotation_trading_summary_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		} else {
			query = "SELECT * FROM mtv_sales_quotation_trading_details_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		}

		return response;
	}

	@Override
	public Map<String, Object> FnSendEmail(int company_id, int quotation_master_transaction_id, JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);

		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
		// Update the mail send status in master table.
		if (emailSentStatus) {
			iMtSalesQuotationMasterTradingRepository.updateMailStatus("S", company_id, quotation_master_transaction_id);
		} else {
			iMtSalesQuotationMasterTradingRepository.updateMailStatus("F", company_id, quotation_master_transaction_id);
		}

		emailingResponse.put("Status", emailSentStatus);
		emailingResponse.put("success", emailControllerResponse.get("success"));
		emailingResponse.put("error", emailControllerResponse.get("error"));
		emailingResponse.put("message", emailControllerResponse.get("message"));
		return emailingResponse;
	}

	@Override
	public Map<String, Object> FnAddUpdateQuotationFollowupRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String quotation_no = commonIdsObj.getString("quotation_no");
		int quotation_version = commonIdsObj.getInt("quotation_version");
		String business_type = commonIdsObj.getString("business_type");

		JSONArray followup_Array = (JSONArray) jsonObject.get("TransfollowupData");

		try {
			List<CMtSalesQuotationFollowupTradingModel> followupTradingDetails = objectMapper.readValue(
					followup_Array.toString(), new TypeReference<List<CMtSalesQuotationFollowupTradingModel>>() {
					});
			String created_By = followupTradingDetails.get(0).getCreated_by();
			boolean isCancelationFlag = followupTradingDetails.parallelStream()
					.anyMatch(item -> item.isCancelation_flag() == true);

			if (!followup_Array.isEmpty()) {
				iMtSalesQuotationFollowupTradingRepository.updateFollowupTradingRecords(created_By, quotation_no,
						company_id);
				iMtSalesQuotationFollowupTradingRepository.saveAll(followupTradingDetails);
			}

			// Update the quotation master status.
			if (isCancelationFlag) {
				// If Transaction type is Trading then update the quotation trading table.
				if (business_type.trim().equals("Trading")) {
					iMtSalesQuotationMasterTradingRepository.updateSalesQuotationMasterTrading(quotation_no,
							company_id);
					iMtSalesQuotationDetailsTradingRepository.updateSalesQuotationDetails(quotation_no, company_id);

				} else if (business_type.trim().equals("Service")) {
					// If Transaction type is Service then update the quotation service table.
					String queryForUpdateQTSMasterStatus = "update mt_sales_quotation_master_services set is_active = 0, quotation_status = 'X' "
							+ "where is_delete = 0 and quotation_no = '" + quotation_no + "' and company_id = "
							+ company_id + " ";
					executeQuery.update(queryForUpdateQTSMasterStatus);

					String queryForUpdateQTSDetailsStatus = "update mt_sales_quotation_details_services set is_active = 0, quotation_item_status = 'X' "
							+ "where is_delete = 0 and quotation_no = '" + quotation_no + "' and company_id = "
							+ company_id + " ";
					executeQuery.update(queryForUpdateQTSDetailsStatus);

					System.out.println("queryForUpdateQTSMasterStatus: " + queryForUpdateQTSMasterStatus);
					System.out.println("queryForUpdateQTSDetailsStatus: " + queryForUpdateQTSDetailsStatus);
				}

			}

			responce.put("success", "1");
			responce.put("error", "");
			responce.put("message", "Record added successfully!");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtQuotationDetails/FnAddUpdateQuotationFollowupRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtQuotationDetails/FnAddUpdateQuotationFollowupRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

}
