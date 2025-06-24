package com.erp.MtSalesQuotationMasterServices.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryDetailsServicesModel;
import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryDetailsServicesViewModel;
import com.erp.MtEnquiryMasterServices.Model.CMtEnquiryMasterServicesModel;
import com.erp.MtEnquiryMasterServices.Repository.IMtEnquiryDetailsServicesRepository;
import com.erp.MtEnquiryMasterServices.Repository.IMtEnquiryDetailsServicesViewRepository;
import com.erp.MtEnquiryMasterServices.Repository.IMtEnquiryMasterServicesRepository;
import com.erp.MtQuotationDetails.Repository.*;
import com.erp.MtSalesQuotationMasterServices.Model.*;
import com.erp.MtSalesQuotationMasterServices.Repository.*;
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

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CMtSalesQuotationMasterServicesServiceImpl implements IMtSalesQuotationMasterServicesService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtSalesQuotationMasterServicesRepository iMtSalesQuotationMasterServicesRepository;

	@Autowired
	IMtSalesQuotationDetailsServicesRepository iMtSalesQuotationDetailsServicesRepository;

	@Autowired
	IMtSalesQuotationTermsTradingRepository iMtSalesQuotationTermsTradingRepository;

	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityRepository iMtSalesQuotationExistingExpectedFunctionalityRepository;

	@Autowired
	IMtSalesQuotationPaymentTermsTradingRepository iMtSalesQuotationPaymentTermsTradingRepository;

	@Autowired
	IMtEnquiryDetailsServicesViewRepository iMtEnquiryDetailsServicesViewRepository;

	@Autowired
	IMtEnquiryMasterServicesRepository iMtEnquiryMasterServicesRepository;

	@Autowired
	IMtSalesQuotationMasterServicesViewRepository iMtSalesQuotationMasterServicesViewRepository;

	@Autowired
	IMtSalesQuotationDetailsServicesViewRepository iMtSalesQuotationDetailsServicesViewRepository;

	@Autowired
	IMtSalesQuotationTermsTradingViewRepository iMtSalesQuotationTermsTradingViewRepository;

	@Autowired
	IMtQuotationPaymentTermsTradingViewRepository iMtQuotationPaymentTermsTradingViewRepository;

	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityViewRepository iMtSalesQuotationExistingExpectedFunctionalityViewRepository;

	@Autowired
	IMtSalesQuotationFollowupTradingViewRepository iMtSalesQuotationFollowupTradingViewRepository;

// new Repository

	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityServiceRepository iMtSalesQuotationExistingExpectedFunctionalityServiceRepository;

	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityServiceViewRepository iMtSalesQuotationExistingExpectedFunctionalityServiceViewRepository;

	@Autowired
	IMtSalesQuotationFollowUpServicesRepository iMtSalesQuotationFollowUpServicesRepository;

	@Autowired
	IMtSalesQuotationFollowUpServicesViewRepository iMtSalesQuotationFollowUpServicesViewRepository;

	@Autowired
	IMtSalesQuotationPaymentTermsServicesRepository iMtSalesQuotationPaymentTermsServicesRepository;

	@Autowired
	IMtSalesQuotationPaymentTermsServicesViewRepository iMtSalesQuotationPaymentTermsServicesViewRepository;

	@Autowired
	IMtSalesQuotationTermsServicesRepository iMtSalesQuotationTermsServicesRepository;

	@Autowired
	IMtSalesQuotationTermsServicesViewRepository iMtSalesQuotationTermsServicesViewRepository;

	///////////
	@Autowired
	private JdbcTemplate executeQuery;

	// Enquiry Repos.
	@Autowired
	IMtEnquiryDetailsServicesRepository iMtEnquiryDetailsServicesRepository;


	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String quotation_no = commonIdsObj.getString("quotation_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterData = jsonObject.getJSONObject("salesQtHeaderData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("salesQtDetailData");
		JSONArray termsArray = (JSONArray) jsonObject.get("TransTermsData");
		JSONArray existingFucntionalityArray = (JSONArray) jsonObject.get("TransExistingFucntionalityData");
		JSONArray payment_termsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");

		try {

			CMtSalesQuotationMasterServicesModel jsonModel = objectMapper.readValue(masterData.toString(),
					CMtSalesQuotationMasterServicesModel.class);

			if (!isApprove) {

				// Quotation Service Master
				CMtSalesQuotationMasterServicesModel cmtSalesQuotationMasterServiceModel = new CMtSalesQuotationMasterServicesModel();
//				CMtSalesQuotationMasterServicesModel getExistingRecord = iMtSalesQuotationMasterServicesRepository
//						.getExistingRecord(jsonModel.getQuotation_master_transaction_id(), company_id);

				String query = "Select * FROM mt_sales_quotation_master_services WHERE is_delete = 0 and quotation_no = '"
						+ quotation_no.toString() + "' and quotation_version = " + jsonModel.getQuotation_version()
						+ " and financial_year = " + financial_year + " and company_id = " + company_id + "";

				List<CMtSalesQuotationMasterServicesModel> results = executeQuery.query(query,
						new BeanPropertyRowMapper<>(CMtSalesQuotationMasterServicesModel.class));

				if (!results.isEmpty()) {
					update = true;
					cmtSalesQuotationMasterServiceModel = results.get(0);
					cmtSalesQuotationMasterServiceModel.setDeleted_by(jsonModel.getCreated_by());
					cmtSalesQuotationMasterServiceModel.setDeleted_on(new Date());
					cmtSalesQuotationMasterServiceModel.setIs_delete(true);
					iMtSalesQuotationMasterServicesRepository.save(cmtSalesQuotationMasterServiceModel);

					jsonModel.setQuotation_version(cmtSalesQuotationMasterServiceModel.getQuotation_version() + 1);
				}

				CMtSalesQuotationMasterServicesModel responseSaleQtMasterServices = iMtSalesQuotationMasterServicesRepository
						.save(jsonModel);

				// Quotation Service Details
				iMtSalesQuotationDetailsServicesRepository.FnUpdateSalesQtDetailsRecord(quotation_no,
						masterData.getInt("quotation_version"), company_id);

				List<CMtSalesQuotationDetailsServicesModel> cMtSalesQuotationDetailsServicesModel = objectMapper
						.readValue(detailsArray.toString(),
								new TypeReference<List<CMtSalesQuotationDetailsServicesModel>>() {
								});

				cMtSalesQuotationDetailsServicesModel.forEach(items -> {
					items.setQuotation_version(jsonModel.getQuotation_version());
					items.setQuotation_master_transaction_id(
							responseSaleQtMasterServices.getQuotation_master_transaction_id());
				});

				iMtSalesQuotationDetailsServicesRepository.saveAll(cMtSalesQuotationDetailsServicesModel);

				// Quotation Terms 

				if (!termsArray.isEmpty()) {

					iMtSalesQuotationTermsServicesRepository.updateSalesQuotationTermsStatus(quotation_no,
							masterData.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationTermsServiceModel> cmtSalesQuotationTermsServiceModel = objectMapper
							.readValue(termsArray.toString(),
									new TypeReference<List<CMtSalesQuotationTermsServiceModel>>() {
									});

					cmtSalesQuotationTermsServiceModel.forEach(items -> {
						items.setQuotation_terms_transaction_id(0);
						items.setQuotation_version(jsonModel.getQuotation_version());
						items.setQuotation_master_transaction_id(responseSaleQtMasterServices.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationTermsServicesRepository.saveAll(cmtSalesQuotationTermsServiceModel);
				}

				// Quotation Existing Expected Functionality

				if (!existingFucntionalityArray.isEmpty()) {

					iMtSalesQuotationExistingExpectedFunctionalityServiceRepository.updateExistingExpectedFunctionalityStatus(
							quotation_no, masterData.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationExistingExpectedFunctionalityServiceModel> cmtSalesQuotationExistingExpectedFunctionalityModel = objectMapper
							.readValue(existingFucntionalityArray.toString(),
									new TypeReference<List<CMtSalesQuotationExistingExpectedFunctionalityServiceModel>>() {
									});

					cmtSalesQuotationExistingExpectedFunctionalityModel.forEach(items -> {
						items.setQuotation_functionality_transaction_id(0);
						items.setQuotation_version(jsonModel.getQuotation_version());
						items.setQuotation_master_transaction_id(responseSaleQtMasterServices.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationExistingExpectedFunctionalityServiceRepository
							.saveAll(cmtSalesQuotationExistingExpectedFunctionalityModel);
				}

				// Quotation Payment Terms Functionality

				if (!payment_termsArray.isEmpty()) {

					iMtSalesQuotationPaymentTermsServicesRepository.updatePaymentTermsStatus(quotation_no,
							masterData.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationPaymentTermsServicesModel> tradingOrderPaymentTerms = objectMapper.readValue(
							payment_termsArray.toString(),
							new TypeReference<List<CMtSalesQuotationPaymentTermsServicesModel>>() {
							});
					tradingOrderPaymentTerms.forEach(paymentTerm -> {
						paymentTerm.setQuotation_payment_terms_transaction_id(0);
						paymentTerm.setQuotation_version(jsonModel.getQuotation_version());
						paymentTerm.setQuotation_master_transaction_id(responseSaleQtMasterServices.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationPaymentTermsServicesRepository.saveAll(tradingOrderPaymentTerms);

				}

				responce.put("data", responseSaleQtMasterServices);
				responce.put("success", "1");
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

			} else {

				responce = FnSalesQuotationServiceDetailsUpdateRecord(detailsArray, commonIdsObj, jsonModel);
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesQuotationMasterServices/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesQuotationMasterServices/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	private Map<String, Object> FnSalesQuotationServiceDetailsUpdateRecord(JSONArray detailsArray,
	                                                                       JSONObject commonIdsObj, CMtSalesQuotationMasterServicesModel jsonModel) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		int company_id = commonIdsObj.getInt("company_id");

		try {
			// Quotation master
			CMtSalesQuotationMasterServicesModel salesQtMasterServiceData = iMtSalesQuotationMasterServicesRepository.save(jsonModel);

			// Quotation details
			List<CMtSalesQuotationDetailsServicesModel> quotationDetails = objectMapper.readValue(
					detailsArray.toString(), new TypeReference<List<CMtSalesQuotationDetailsServicesModel>>() {
					});
			iMtSalesQuotationDetailsServicesRepository.saveAll(quotationDetails);

			// ***** Update the Enquiry status.
			if ("E".equals(salesQtMasterServiceData.getQuotation_transaction_type())) {
				String detailModifiedBy = quotationDetails.get(0).getCreated_by();

				List<String> distinctEnquiryNos = quotationDetails.parallelStream()
						.map(CMtSalesQuotationDetailsServicesModel::getEnquiry_no).distinct()
						.collect(Collectors.toList());

				// 1. Updating the enquiry-details status.
				List<CMtEnquiryDetailsServicesModel> enquiryDetailsFromDB = iMtEnquiryDetailsServicesRepository.FnShowEnquiryDetailsByEnqNos(company_id, distinctEnquiryNos);
				List<CMtEnquiryDetailsServicesModel> updatedEnquiryDetails = new ArrayList<CMtEnquiryDetailsServicesModel>();

				// Iterate the loop on the distinctEnquiryNos.
				distinctEnquiryNos.forEach(enquiryNo -> {
					// From enquiryDetailsFromDB(means received data from enquiry_details table) collect only the the current enquiryNo related details.
					List<CMtEnquiryDetailsServicesModel> currentEnqNoDetailsFromDB = enquiryDetailsFromDB.stream()
							.filter(enqMaterial -> enquiryNo.equals(enqMaterial.getEnquiry_no()))
							.collect(Collectors.toList());

					// From quotation materials collect all the quotationMaterial having enquiry_no. current.
					List<CMtSalesQuotationDetailsServicesModel> quotationDetailsEnqBased = quotationDetails.stream()
							.filter(quotationMat -> enquiryNo.equals(quotationMat.getEnquiry_no()))
							.collect(Collectors.toList());

					// Now iterate the loop on the quotation-material and find product_id matching object.
					quotationDetailsEnqBased.forEach(quotationMaterial -> {
						String product_material_id = quotationMaterial.getProduct_material_id();
						Optional<CMtEnquiryDetailsServicesModel> matchingEnqDetailObjs = currentEnqNoDetailsFromDB.stream()
								.filter(enqDetail -> product_material_id.equals(enqDetail.getProduct_material_id()))
								.findFirst();

						if (matchingEnqDetailObjs.isPresent()) {
							CMtEnquiryDetailsServicesModel matchedEnqDetail = matchingEnqDetailObjs.get();
							// Update the enquiry detail status.	
							String currentQuotationMatStatus = quotationMaterial.getQuotation_item_status();
							if ("A".equals(currentQuotationMatStatus)) {    // If it is approved the enquiry item status is quotation.
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
					iMtEnquiryDetailsServicesRepository.saveAll(updatedEnquiryDetails);
				}

//				----------------- For Update the Enquiry master status.
				// get all enquiry details after details updatation.
				List<CMtEnquiryDetailsServicesModel> enquiryDetailsFromDBAfterUpdate = iMtEnquiryDetailsServicesRepository.FnShowEnquiryDetailsByEnqNos(company_id, distinctEnquiryNos);

				// get all sales-order masters records for distinctSoNos.
				List<CMtEnquiryMasterServicesModel> enquiryMasters = iMtEnquiryMasterServicesRepository.FnShowEnquiriesByEnqNos(company_id, distinctEnquiryNos);

				// To Store the all enquiry-master records for update.
				List<CMtEnquiryMasterServicesModel> enquiryMastersForUpdate = new ArrayList<CMtEnquiryMasterServicesModel>();

				// Iterate the loop on enquiry master records.
				enquiryMasters.forEach(enquiryMst -> {
					String enquiryNo = enquiryMst.getEnquiry_no();
					// First from enquiryDetailsFromDBAfterUpdate get all details and compare all status.
					List<CMtEnquiryDetailsServicesModel> currentEnquiryDetails = enquiryDetailsFromDBAfterUpdate.stream()
							.filter(enqDetail -> enquiryNo.equals(enqDetail.getEnquiry_no()))
							.collect(Collectors.toList());

					// Collect all itemStatus values into a List
					List<String> EnqItemStatusList = currentEnquiryDetails.stream()
							.map(CMtEnquiryDetailsServicesModel::getEnquiry_item_status)
							.collect(Collectors.toList());

					boolean allMaterialStatusIsRejected = EnqItemStatusList.stream().allMatch(status -> status.equals("R"));
					boolean allMaterialStatusIsQTSent = EnqItemStatusList.stream().allMatch(status -> status.equals("Q"));

					if (allMaterialStatusIsQTSent) {
						enquiryMst.setEnquiry_status("Q");
					} else if (allMaterialStatusIsRejected) {
						enquiryMst.setEnquiry_status("R");
					}
					enquiryMst.setModified_by(detailModifiedBy);
					enquiryMastersForUpdate.add(enquiryMst);            // Add that updated object in the list.
				});

				// save the Updated the sales_order_masters.
				iMtEnquiryMasterServicesRepository.saveAll(enquiryMastersForUpdate);

//				iMtEnquiryDetailsServicesRepository.FnUpdateEQItemsStatusByQuotation(distinctEnquiryNos, company_id);
//				iMtEnquiryMasterServicesRepository.FnUpdateEQStatusByQuotation(distinctEnquiryNos, company_id);
			}

			responce.put("success", "1");
			responce.put("data", salesQtMasterServiceData);
			responce.put("error", "");
			String quotation_status = jsonModel.getQuotation_status();
			responce.put("message", quotation_status.equals("R") ? "Quotation has been rejected...! " : "Quotation has been approved successfully...! ");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesQuotationMasterServices/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesQuotationMasterServices/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String quotation_no, int quotation_version, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iMtSalesQuotationMasterServicesRepository.deleteSalesQtMasterServicesRecords(quotation_no,
					quotation_version, company_id, deleted_by);
			iMtSalesQuotationDetailsServicesRepository.deleteSalesQtDetailsServicesRecords(quotation_no,
					quotation_version, company_id, deleted_by);
			iMtSalesQuotationTermsServicesRepository.deleteQuotationTerms(quotation_no,
					quotation_version, company_id, deleted_by);
			iMtSalesQuotationExistingExpectedFunctionalityServiceRepository.deleteQuotationExistingExpectedFunctionality(quotation_no,
					quotation_version, company_id, deleted_by);
			iMtSalesQuotationPaymentTermsServicesRepository.deleteQuotationPaymentTerms(quotation_no,
					quotation_version, company_id, deleted_by);
			iMtSalesQuotationFollowUpServicesRepository.deleteQuotationFollowUp(quotation_no,
					quotation_version, company_id, deleted_by); // For FollowUps

			responce.put("success", 1);
			responce.put("data", "");
			responce.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
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

			CMtSalesQuotationMasterServicesViewModel salesQtMasterServicesRecords = iMtSalesQuotationMasterServicesViewRepository
					.FnShowsQuotationMasterServicesDetailsRecords(quotation_no, quotation_version, financial_year,
							company_id);

			List<Map<String, Object>> salesQtDetailsServicesRecords = iMtSalesQuotationDetailsServicesViewRepository
					.FnShowQuotationDetailsServicesRecords(quotation_no, quotation_version, financial_year, company_id);

			List<CMtSalesQuotationTermsServiceViewModel> quotationTermsServiceRecords = iMtSalesQuotationTermsServicesViewRepository
					.FnShowQuotationTermsServiceRecord(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationExistingExpectedFunctionalityServiceViewModel> quotationExistingExpectedFucntionalityRecords = iMtSalesQuotationExistingExpectedFunctionalityServiceViewRepository
					.FnShowQuotationExistingExpectedFucntionalityServiceRecord(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationPaymentTermsServiceViewModel> quotationPaymentTermsRecord = iMtSalesQuotationPaymentTermsServicesViewRepository
					.FnShowQuotationPaymentTerms(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationFollowUpServiceViewModel> quotationFollowupTradingService = iMtSalesQuotationFollowUpServicesViewRepository
					.FnShowQuotationFollowupServiceRecord(quotation_no, company_id);

			responce.put("SalesQtMasterServicesRecords", salesQtMasterServicesRecords);
			responce.put("SalesQtDetailsServicesRecords", salesQtDetailsServicesRecords);
			responce.put("QuotationTermsTradingRecords", quotationTermsServiceRecords);
			responce.put("QuotationExistingExpectedFucntionalityRecords", quotationExistingExpectedFucntionalityRecords);
			responce.put("QuotationPaymentTermsRecord", quotationPaymentTermsRecord);
			responce.put("QuotationFollowupTradingDetails", quotationFollowupTradingService);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		if ("summary".equals(reportType)) {
			Map<String, Object> results = iMtSalesQuotationMasterServicesViewRepository.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
			Map<String, Object> results = iMtSalesQuotationMasterServicesViewRepository.FnShowAlldetailsReportRecords();
			response.put("content", results);
		}
		return response;
	}

	@Override
	public Map<String, Object> FnGetEnquiryNoList(int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {

			List<String> enqiryNoList = iMtEnquiryMasterServicesRepository.FnGetEnquiryNoDetails(company_id);
			response.put("data", enqiryNoList);
			response.put("success", 1);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowEnquiryDetailsRecords(JSONObject enquiryNo, int company_id) {
		Map<String, Object> response = new HashMap<>();

		try {

			JSONArray enquiryNos = enquiryNo.getJSONArray("enquiry_nos");
//			int customer_id = enquiryNo.getInt("customer_id");


			List<String> enquiryNoList = enquiryNos.toList().stream().map(Object::toString)
					.collect(Collectors.toList());

			List<CMtEnquiryDetailsServicesViewModel> enquiryDetailsServicesRecords = iMtEnquiryDetailsServicesViewRepository
					.FnShowEnquiryDetailsRecord(company_id, enquiryNoList);

			response.put("EnquiryDetailsServicesRecords", enquiryDetailsServicesRecords);
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

	@Override
	public Map<String, Object> FnSendEmail(int company_id, int quotation_master_transaction_id, JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);
		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
		// Update the mail send status in master table.
		if (emailSentStatus) {
			iMtSalesQuotationMasterServicesRepository.updateMailStatus("S", company_id, quotation_master_transaction_id);
		} else {
			iMtSalesQuotationMasterServicesRepository.updateMailStatus("F", company_id, quotation_master_transaction_id);
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
			List<CMtSalesQuotationFollowUpServiceModel> followupTradingDetails = objectMapper.readValue(
					followup_Array.toString(),
					new TypeReference<List<CMtSalesQuotationFollowUpServiceModel>>() {
					});
			String created_By = followupTradingDetails.get(0).getCreated_by();
			boolean isCancelationFlag = followupTradingDetails.parallelStream().anyMatch(item -> item.isCancelation_flag() == true);

			if (!followup_Array.isEmpty()) {
				iMtSalesQuotationFollowUpServicesRepository.updateFollowupTradingRecords(created_By, quotation_no, company_id);
				iMtSalesQuotationFollowUpServicesRepository.saveAll(followupTradingDetails);
			}

			// Update the quotation master status.
			if (isCancelationFlag) {

				// If Transaction type is Service then update the quotation service table.
				String queryForUpdateQTSMasterStatus = "update mt_sales_quotation_master_services set is_active = 0, quotation_status = 'X' "
						+ "where is_delete = 0 and quotation_no = '" + quotation_no + "' and company_id = " + company_id + " ";
				executeQuery.update(queryForUpdateQTSMasterStatus);

				String queryForUpdateQTSDetailsStatus = "update mt_sales_quotation_details_services set is_active = 0, quotation_item_status = 'X' "
						+ "where is_delete = 0 and quotation_no = '" + quotation_no + "' and company_id = " + company_id + " ";
				executeQuery.update(queryForUpdateQTSDetailsStatus);

				System.out.println("queryForUpdateQTSMasterStatus: " + queryForUpdateQTSMasterStatus);
				System.out.println("queryForUpdateQTSDetailsStatus: " + queryForUpdateQTSDetailsStatus);
			}

			responce.put("success", "1");
			responce.put("error", "");
			responce.put("message", "Record added successfully!");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesQuotationMasterServices/FnAddUpdateQuotationFollowupRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesQuotationMasterServices/FnAddUpdateQuotationFollowupRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

}
