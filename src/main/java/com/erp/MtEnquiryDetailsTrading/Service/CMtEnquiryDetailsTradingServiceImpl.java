package com.erp.MtEnquiryDetailsTrading.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtEnquiryDetailsTrading.Model.*;
import com.erp.MtEnquiryDetailsTrading.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CMtEnquiryDetailsTradingServiceImpl implements IMtEnquiryDetailsTradingService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtEnquiryDetailsTradingRepository iMtEnquiryDetailsTradingRepository;

	@Autowired
	IMtEnquiryMasterTradingSummaryRepository iMtEnquiryMasterTradingSummaryRepository;

	@Autowired
	IMtEnquiryExistingExpectedFunctionalityRepository iMtEnquiryExistingExpectedFunctionalityRepository;

	@Autowired
	IMtEnquiryTermsTradingRepository iMtEnquiryTermsTradingRepository;

	@Autowired
	IMtEnquiryTermsTradingViewRepository iMtEnquiryTermsTradingViewRepository;

	@Autowired
	IMtEnquiryExistingExpectedFunctionalityViewRepository iMtEnquiryExistingExpectedFunctionalityViewRepository;

	@Autowired
	IMtEnquiryDetailsTradingViewRepository iMtEnquiryDetailsTradingViewRepository;

	@Autowired
	IMtEnquiryTermsTradingRptRepository iMtEnquiryTermsTradingRptRepository;


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
		//int company_branch_id = commonIdsObj.getInt("company_branch_id");
		int enquiry_version = commonIdsObj.getInt("enquiry_version");
		String enquiry_no = commonIdsObj.getString("enquiry_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterData = jsonObject.getJSONObject("EnquiryHeaderData");
		JSONArray DetailData = (JSONArray) jsonObject.get("EnquiryDetailData");
		JSONArray existingExpectedFunctionalityArray = (JSONArray) jsonObject.get("EnquiryExistingExpectedFunctionalityData");
		JSONArray enquiryTermsArray = (JSONArray) jsonObject.get("EnquiryTermsData");

		try {
			CMtEnquiryMasterTradingModel jsonModel = objectMapper.readValue(masterData.toString(),
					CMtEnquiryMasterTradingModel.class);
			if (!isApprove) {
				Date Enquiry_date = jsonModel.getEnquiry_date();

				//			Enquiry Master Master

				CMtEnquiryMasterTradingModel MtEnquiryMasterTradingModel = new CMtEnquiryMasterTradingModel();

				String query = "Select * FROM mt_enquiry_master_trading WHERE is_delete = 0 and enquiry_no = '" + enquiry_no.toString()
						+ "' and enquiry_version = " + jsonModel.getEnquiry_version() + " and financial_year = '"
						+ financial_year.toString() + "' and company_id = " + company_id + "";

				List<CMtEnquiryMasterTradingModel> results = executeQuery.query(query,
						new BeanPropertyRowMapper<>(CMtEnquiryMasterTradingModel.class));

				if (!results.isEmpty()) {
					update = true;
					MtEnquiryMasterTradingModel = results.get(0);
					MtEnquiryMasterTradingModel.setDeleted_by(jsonModel.getCreated_by());
					MtEnquiryMasterTradingModel.setDeleted_on(new Date());
					MtEnquiryMasterTradingModel.setIs_delete(true);
					iMtEnquiryMasterTradingSummaryRepository.save(MtEnquiryMasterTradingModel);

					jsonModel.setEnquiry_version(MtEnquiryMasterTradingModel.getEnquiry_version() + 1);
				}

				CMtEnquiryMasterTradingModel responceEnquiryMaster = iMtEnquiryMasterTradingSummaryRepository.save(jsonModel);

				//		Enquiry	Details Master

				iMtEnquiryDetailsTradingRepository.FnUpdateDetailsMasterRecord(jsonModel.getCreated_by(), enquiry_no, masterData.getInt("enquiry_version"),
						company_id);

				List<CMtEnquiryDetailsTradingModel> cMtEnquiryDetailsTradingModel = objectMapper
						.readValue(DetailData.toString(), new TypeReference<List<CMtEnquiryDetailsTradingModel>>() {
						});

				cMtEnquiryDetailsTradingModel.forEach(items -> {
					items.setEnquiry_version(jsonModel.getEnquiry_version());
					items.setEnquiry_master_transaction_id(responceEnquiryMaster.getEnquiry_master_transaction_id());
				});

				iMtEnquiryDetailsTradingRepository.saveAll(cMtEnquiryDetailsTradingModel);

				//			Existing Expected Functionality Master

				iMtEnquiryExistingExpectedFunctionalityRepository.updateExistingFunctionlityStatus(jsonModel.getCreated_by(), enquiry_no, masterData.getInt("enquiry_version"), company_id);

				List<CMtEnquiryExistingExpectedFunctionalityModel> MtEnquiryExistingExpectedFunctionalityModel = objectMapper
						.readValue(existingExpectedFunctionalityArray.toString(),
								new TypeReference<List<CMtEnquiryExistingExpectedFunctionalityModel>>() {
								});

				MtEnquiryExistingExpectedFunctionalityModel.forEach(items -> {
					items.setEnquiry_version(jsonModel.getEnquiry_version());
					items.setEnquiry_master_transaction_id(responceEnquiryMaster.getEnquiry_master_transaction_id());
					items.setEnquiry_details_transaction_id(0);
				});

				iMtEnquiryExistingExpectedFunctionalityRepository.saveAll(MtEnquiryExistingExpectedFunctionalityModel);

				//		Enquiry Terms Trading (PO) Terms

				if (!enquiryTermsArray.isEmpty()) {

					iMtEnquiryTermsTradingRepository.updateEnquiryTermsPOTermsStatus(jsonModel.getCreated_by(), enquiry_no, masterData.getInt("enquiry_version"), company_id);

					List<CMtEnquiryTermsTradingModel> MtEnquiryTermsTradingModel = objectMapper.readValue(
							enquiryTermsArray.toString(), new TypeReference<List<CMtEnquiryTermsTradingModel>>() {
							});

					MtEnquiryTermsTradingModel.forEach(tradingModel -> {
						tradingModel.setEnquiry_version(jsonModel.getEnquiry_version());
						tradingModel.setEnquiry_master_transaction_id(responceEnquiryMaster.getEnquiry_master_transaction_id());
						tradingModel.setEnquiry_details_transaction_id(0);
					});

					iMtEnquiryTermsTradingRepository.saveAll(MtEnquiryTermsTradingModel);
				}

				responce.put("success", "1");
				responce.put("data", responceEnquiryMaster);
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

			} else {

				responce = FnSalesOrderDetailsUpdateRecord(DetailData, commonIdsObj, jsonModel);
			}

		} catch (DataAccessException e) {
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
					"/api/MtEnquiryDetailsTrading/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	private Map<String, Object> FnSalesOrderDetailsUpdateRecord(JSONArray detailData, JSONObject commonIdsObj,
	                                                            CMtEnquiryMasterTradingModel jsonModel) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		int company_id = commonIdsObj.getInt("company_id");
//		int enquiry_version = commonIdsObj.getInt("enquiry_version");
//		String enquiry_no = commonIdsObj.getString("enquiry_no");
//		String financial_year = commonIdsObj.getString("financial_year");
		try {


			iMtEnquiryMasterTradingSummaryRepository.save(jsonModel);

			List<CMtEnquiryDetailsTradingModel> enquiryDetailsTradingModel = objectMapper.readValue(detailData.toString(),
					new TypeReference<List<CMtEnquiryDetailsTradingModel>>() {
					});

			iMtEnquiryDetailsTradingRepository.saveAll(enquiryDetailsTradingModel);

			responce.put("success", "1");
			responce.put("data", jsonModel);
			responce.put("error", "");
			responce.put("message", "Record approved successfully!...");
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
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtEnquiryDetailsTrading/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Page<CMtEnquiryDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iMtEnquiryDetailsTradingViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int enquiry_details_transaction_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CMtEnquiryDetailsTradingModel cMtEnquiryDetailsTradingModel = null;
		try {
			cMtEnquiryDetailsTradingModel = iMtEnquiryDetailsTradingRepository
					.FnShowParticularRecordForUpdate(enquiry_details_transaction_id, company_id);
			responce.put("success", "1");
			responce.put("data", cMtEnquiryDetailsTradingModel);
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
	public Page<CMtEnquiryDetailsTradingViewModel> FnShowParticularRecord(int enquiry_details_transaction_id,
	                                                                      Pageable pageable, int company_id) {
		return iMtEnquiryDetailsTradingViewRepository.FnShowParticularRecord(enquiry_details_transaction_id, pageable,
				company_id);
	}

	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String enquiry_no, int enquiry_version,
	                                                                 String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> enquiryMasterRecord = iMtEnquiryMasterTradingSummaryRepository
					.FnShowEnquiryMasterRecord(enquiry_no, enquiry_version, financial_year, company_id);
			List<Map<String, Object>> enquiryDetailsRecords = iMtEnquiryDetailsTradingRepository
					.FnShowEnquiryDetailsRecords(enquiry_no, enquiry_version, financial_year, company_id);
			List<CMtEnquiryExistingExpectedFunctionalityViewModel> MtEnquiryExistingExpectedFunctionalityViewModel = iMtEnquiryExistingExpectedFunctionalityViewRepository
					.FnShowEnquiryExistingExpectedFunctionality(enquiry_no, enquiry_version, company_id);
			List<CMtEnquiryTermsTradingViewModel> MtEnquiryTermsTradingViewModel = iMtEnquiryTermsTradingViewRepository
					.FnShowEnquiryTermsTrading(enquiry_no, enquiry_version, company_id);

			responce.put("EnquiryMasterRecord", enquiryMasterRecord);
			responce.put("EnquiryDetailsRecords", enquiryDetailsRecords);
			responce.put("EnquiryExistingExpectedFunctionality", MtEnquiryExistingExpectedFunctionalityViewModel);
			responce.put("EnquiryTermsTradingPOTerms", MtEnquiryTermsTradingViewModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String enquiry_no, int enquiry_version, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			iMtEnquiryMasterTradingSummaryRepository
					.deleteEnquiry(enquiry_no, enquiry_version, company_id);
			iMtEnquiryDetailsTradingRepository
					.deleteEnquiryDetails(enquiry_no, enquiry_version, company_id);
			iMtEnquiryTermsTradingRepository
					.deleteEnquiryTerms(enquiry_no, enquiry_version, company_id);
			iMtEnquiryExistingExpectedFunctionalityRepository
					.deleteEnquiryExistingExpectedFunctionality(enquiry_no, enquiry_version, company_id);

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
			Map<String, Object> results = iMtEnquiryMasterTradingSummaryRepository.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
			Map<String, Object> results =
					iMtEnquiryDetailsTradingViewRepository.FnShowAlldetailsReportRecords();
			response.put("content", results);
		}

		return response;

	}

	@Override
	public Map<String, Object> FnSendEmail(int company_id, int enquiry_master_transaction_id, JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);
		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
		// Update the mail send status in master table.
		if (emailSentStatus) {
			iMtEnquiryMasterTradingSummaryRepository.updateMailStatus("S", company_id, enquiry_master_transaction_id);
		} else {
			iMtEnquiryMasterTradingSummaryRepository.updateMailStatus("F", company_id, enquiry_master_transaction_id);
		}

		emailingResponse.put("Status", emailSentStatus);
		emailingResponse.put("success", emailControllerResponse.get("success"));
		emailingResponse.put("error", emailControllerResponse.get("error"));
		emailingResponse.put("message", emailControllerResponse.get("message"));
		return emailingResponse;
	}

}
