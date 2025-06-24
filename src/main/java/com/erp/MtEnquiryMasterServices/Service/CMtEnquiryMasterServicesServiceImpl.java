package com.erp.MtEnquiryMasterServices.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtEnquiryMasterServices.Model.*;
import com.erp.MtEnquiryMasterServices.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CMtEnquiryMasterServicesServiceImpl implements IMtEnquiryMasterServicesService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtEnquiryMasterServicesRepository iMtEnquiryMasterServicesRepository;

	@Autowired
	IMtEnquiryMasterServicesSummaryViewRepository iMtEnquiryMasterServicesSummaryViewRepository;

	@Autowired
	IMtEnquiryDetailsServicesRepository iMtEnquiryDetailsServicesRepository;

	@Autowired
	IMtEnquiryDetailsServicesViewRepository iMtEnquiryDetailsServicesViewRepository;

	@Autowired
	IMtEnquiryExistingExpectedFunctionalityServiceRepository iMtEnquiryExistingExpectedFunctionalityServiceRepository;

	@Autowired
	IMtEnquiryTermsServiceRepository iMtEnquiryTermsServiceRepository;

	@Autowired
	IMtEnquiryTermsServiceViewRepository iMtEnquiryTermsServiceViewRepository;

	@Autowired
	IMtEnquiryExistingExpectedFunctionalityServiceViewRepository iMtEnquiryExistingExpectedFunctionalityServiceViewRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String enquiry_no = commonIdsObj.getString("enquiry_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterData = jsonObject.getJSONObject("EnquiryHeaderData");
		JSONArray detailsSrArray = (JSONArray) jsonObject.get("EnquiryDetailData");
		JSONArray existingExpectedFunctionalityArray = (JSONArray) jsonObject
				.get("EnquiryExistingExpectedFunctionalityData");
		JSONArray enquiryTermsArray = (JSONArray) jsonObject.get("EnquiryTermsData");

		try {

			CMtEnquiryMasterServicesModel jsonModel = objectMapper.readValue(masterData.toString(),
					CMtEnquiryMasterServicesModel.class);

			if (!isApprove) {

				// Enquiry Service Master
				CMtEnquiryMasterServicesModel getExistingRecord = iMtEnquiryMasterServicesRepository
						.getExistingRecord(enquiry_no, jsonModel.getEnquiry_version(), financial_year, company_id);

				if (getExistingRecord != null) {
					update = true;
					getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
					getExistingRecord.setDeleted_on(new Date());
					getExistingRecord.setIs_delete(true);
					iMtEnquiryMasterServicesRepository.save(getExistingRecord);

					jsonModel.setEnquiry_version(getExistingRecord.getEnquiry_version() + 1);
				}

				CMtEnquiryMasterServicesModel responseEnquiryMasterServices = iMtEnquiryMasterServicesRepository
						.save(jsonModel);

				// Enquiry Service Details
				iMtEnquiryDetailsServicesRepository.FnUpdateDetailsServiceRecord(enquiry_no,
						masterData.getInt("enquiry_version"), company_id, jsonModel.getCreated_by());

				List<CMtEnquiryDetailsServicesModel> cmtEnquiryDetailsServicesModel = objectMapper.readValue(
						detailsSrArray.toString(), new TypeReference<List<CMtEnquiryDetailsServicesModel>>() {
						});

				cmtEnquiryDetailsServicesModel.forEach(items -> {
					items.setEnquiry_version(jsonModel.getEnquiry_version());
					items.setEnquiry_master_transaction_id(
							responseEnquiryMasterServices.getEnquiry_master_transaction_id());
				});

				iMtEnquiryDetailsServicesRepository.saveAll(cmtEnquiryDetailsServicesModel);

				// Existing Expected Functionality Master
				iMtEnquiryExistingExpectedFunctionalityServiceRepository.updateExistingFunctionlityStatus(jsonModel.getCreated_by(), enquiry_no, masterData.getInt("enquiry_version"), company_id);

				List<CMtEnquiryExistingExpectedFunctionalityServiceModel> MtEnquiryExistingExpectedFunctionalityModel = objectMapper
						.readValue(existingExpectedFunctionalityArray.toString(),
								new TypeReference<List<CMtEnquiryExistingExpectedFunctionalityServiceModel>>() {
								});

				MtEnquiryExistingExpectedFunctionalityModel.forEach(items -> {
					items.setEnquiry_version(jsonModel.getEnquiry_version());
					items.setEnquiry_master_transaction_id(
							responseEnquiryMasterServices.getEnquiry_master_transaction_id());
				});

				iMtEnquiryExistingExpectedFunctionalityServiceRepository.saveAll(MtEnquiryExistingExpectedFunctionalityModel);

				// Enquiry Terms Trading Terms
				if (!enquiryTermsArray.isEmpty()) {

					iMtEnquiryTermsServiceRepository.updateEnquiryTermsPOTermsStatus(jsonModel.getCreated_by(), enquiry_no, masterData.getInt("enquiry_version"), company_id);

					List<CMtEnquiryTermsServiceModel> MtEnquiryTermsServiceModel = objectMapper.readValue(
							enquiryTermsArray.toString(), new TypeReference<List<CMtEnquiryTermsServiceModel>>() {
							});

					MtEnquiryTermsServiceModel.forEach(tradingModel -> {
						tradingModel.setEnquiry_version(jsonModel.getEnquiry_version());
						tradingModel.setEnquiry_master_transaction_id(
								responseEnquiryMasterServices.getEnquiry_master_transaction_id());
					});

					iMtEnquiryTermsServiceRepository.saveAll(MtEnquiryTermsServiceModel);
				}

				responce.put("data", responseEnquiryMasterServices);
				responce.put("success", "1");
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

			} else {

				responce = FnEnquiryServiceDetailsUpdateRecord(detailsSrArray, commonIdsObj, jsonModel);
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtEnquiryMasterServices/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtEnquiryMasterServices/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	private Map<String, Object> FnEnquiryServiceDetailsUpdateRecord(JSONArray detailsSrArray, JSONObject commonIdsObj,
	                                                                CMtEnquiryMasterServicesModel jsonModel) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		int company_id = commonIdsObj.getInt("company_id");
		try {

			// Enquiry Service Master
			iMtEnquiryMasterServicesRepository.save(jsonModel);

			// Enquiry Service Details
			List<CMtEnquiryDetailsServicesModel> responseEnquiryDetailsServicesDetailsRecords = objectMapper
					.readValue(detailsSrArray.toString(), new TypeReference<List<CMtEnquiryDetailsServicesModel>>() {
					});

			iMtEnquiryDetailsServicesRepository.saveAll(responseEnquiryDetailsServicesDetailsRecords);

			responce.put("success", "1");
			responce.put("data", jsonModel);
			responce.put("error", "");
			responce.put("message", jsonModel.getEnquiry_status().equals("R") ? "Enquiry has been rejected...! " : "Enquiry has been approved successfully...! ");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtEnquiryDetailsTrading/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtEnquiryMasterServices/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String enquiry_no, int enquiry_version, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iMtEnquiryMasterServicesRepository.deleteEnquiryMasterServicesRecords(enquiry_no, enquiry_version,
					company_id, deleted_by);
			iMtEnquiryDetailsServicesRepository.deleteEnquiryDetailsServicesRecords(enquiry_no, enquiry_version,
					company_id, deleted_by);
//			iMtEnquiryTermsServiceRepository.deleteEnquiryTerms(enquiry_no, enquiry_version, company_id);
			iMtEnquiryTermsServiceRepository.updateEnquiryTermsPOTermsStatus(deleted_by, enquiry_no, enquiry_version, company_id);
			iMtEnquiryExistingExpectedFunctionalityServiceRepository.deleteEnquiryExistingExpectedFunctionality(enquiry_no,
					enquiry_version, company_id, deleted_by);

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
			Map<String, Object> results = iMtEnquiryMasterServicesSummaryViewRepository.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
			Map<String, Object> results = iMtEnquiryMasterServicesSummaryViewRepository
					.FnShowAlldetailsReportRecords();
			response.put("content", results);
		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String enquiry_no, int enquiry_version,
	                                                                 String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> enquiryMasterServicesRecords = iMtEnquiryMasterServicesSummaryViewRepository
					.FnShowEnquiryMasterServicesDetailsRecords(enquiry_no, enquiry_version, financial_year, company_id);

			List<Map<String, Object>> enquiryDetailsServicesRecords = iMtEnquiryDetailsServicesViewRepository
					.FnShowEnquiryDetailsServicesRecords(enquiry_no, enquiry_version, financial_year, company_id);

			List<CMtEnquiryExistingExpectedFunctionalityServiceViewModel> enquiryExistingExpectedFunctionalityRecords = iMtEnquiryExistingExpectedFunctionalityServiceViewRepository
					.FnShowEnquiryExistingExpectedFunctionality(enquiry_no, enquiry_version, company_id);

			List<CMtEnquiryTermsServiceViewModel> enquiryTermsTradingRecords = iMtEnquiryTermsServiceViewRepository
					.FnShowEnquiryTermsTrading(enquiry_no, enquiry_version, company_id);

			responce.put("EnquiryMasterServicesRecords", enquiryMasterServicesRecords);
			responce.put("EnquiryDetailsServicesRecords", enquiryDetailsServicesRecords);
			responce.put("EnquiryExistingExpectedFunctionalityRecords", enquiryExistingExpectedFunctionalityRecords);
			responce.put("EnquiryTermsTradingRecords", enquiryTermsTradingRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnSendEmail(int company_id, int enquiry_master_transaction_id, JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);

		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");

		// Update the mail send status in master table.
		if (emailSentStatus) {
			iMtEnquiryMasterServicesRepository.updateMailStatus("S", company_id, enquiry_master_transaction_id);
		} else {
			iMtEnquiryMasterServicesRepository.updateMailStatus("F", company_id, enquiry_master_transaction_id);
		}

		emailingResponse.put("Status", emailSentStatus);
		emailingResponse.put("success", emailControllerResponse.get("success"));
		emailingResponse.put("error", emailControllerResponse.get("error"));
		emailingResponse.put("message", emailControllerResponse.get("message"));
		return emailingResponse;

	}

}
