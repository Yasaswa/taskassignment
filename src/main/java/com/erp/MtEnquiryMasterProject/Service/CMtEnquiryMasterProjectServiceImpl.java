package com.erp.MtEnquiryMasterProject.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtEnquiryMasterProject.Model.*;
import com.erp.MtEnquiryMasterProject.Repository.*;
import com.erp.SmProductPr.Model.CSmProductPrModel;
import com.erp.SmProductPr.Service.ISmProductPrService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CMtEnquiryMasterProjectServiceImpl implements IMtEnquiryMasterProjectService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtEnquiryMasterProjectRepository iMtEnquiryMasterProjectRepository;

	@Autowired
	IMtEnquiryDetailsProjectRepository iMtEnquiryDetailsProjectRepository;

	@Autowired
	IMtEnquiryTermsProjectRepository iMtEnquiryTermsProjectRepository;

	@Autowired
	IMtEnquiryExistingExpectedFunctionalityProjectRepository iMtEnquiryExistingExpectedFunctionalityProjectRepository;

	@Autowired
	IMtEnquiryMasterProjectViewRepository IMtEnquiryMasterProjectViewRepository;

	@Autowired
	IMtEnquiryDetailsProjectViewRepository iMtEnquiryDetailsProjectViewRepository;

	@Autowired
	IMtEnquiryExistingExpectedFunctionalityProjectViewRepository iMtEnquiryExistingExpectedFunctionalityProjectViewRepository;

	@Autowired
	IMtEnquiryTermsProjectViewRepository iMtEnquiryTermsProjectViewRepository;


	@Autowired
	private JdbcTemplate executeQuery;

//	@Autowired
//	IMtEnquiryMasterProjectRptRepository iMtEnquiryMasterProjectRptRepository;


	@Autowired
	ISmProductPrService iSmProductPrService;


	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject transEnquiryProjectJson, boolean isApprove) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) transEnquiryProjectJson.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int enquiry_version = commonIdsObj.getInt("enquiry_version");
		AtomicInteger atomic_enquiry_version = new AtomicInteger(enquiry_version);

		String enquiry_no = commonIdsObj.getString("enquiry_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterData = transEnquiryProjectJson.getJSONObject("EnquiryHeaderData");
		JSONArray DetailData = (JSONArray) transEnquiryProjectJson.get("EnquiryDetailData");
		JSONArray projectMaterialsData = (JSONArray) transEnquiryProjectJson.get("projectMaterialsData");
		JSONArray existingExpectedFunctionalityArray = (JSONArray) transEnquiryProjectJson.get("EnquiryExistingExpectedFunctionalityData");
		JSONArray enquiryTermsArray = (JSONArray) transEnquiryProjectJson.get("EnquiryTermsData");


		try {
			CMtEnquiryMasterProjectModel enquiryMasterData = objectMapper.readValue(masterData.toString(), CMtEnquiryMasterProjectModel.class);

			if (!isApprove) {
//				// Enquiry Details Data.
				List<CMtEnquiryDetailsProjectModel> cMtEnquiryDetailsProjectModels = objectMapper
						.readValue(DetailData.toString(), new TypeReference<List<CMtEnquiryDetailsProjectModel>>() {
						});

//				Before saving any data in enquiry transaction store the data into the project_master table.
				List<CSmProductPrModel> cSmProductPrModels = objectMapper
						.readValue(projectMaterialsData.toString(), new TypeReference<List<CSmProductPrModel>>() {
						});
				Map<String, Object> projectResponse = new HashMap<>();
				for (CSmProductPrModel cSmProductPrModel : cSmProductPrModels) {
					projectResponse.clear();
					projectResponse = iSmProductPrService.FnAddUpdateMaterial(cSmProductPrModel, company_id);
					if (projectResponse.get("success").equals(1)) {
						CSmProductPrModel updatedObject = (CSmProductPrModel) projectResponse.get("data");

						// Update the enquiry details in the cMtEnquiryDetailsProjectModels where material name matches.
						cMtEnquiryDetailsProjectModels.stream()
								.map(enquiryItem -> {
									if (updatedObject.getProduct_pr_name().equals(enquiryItem.getProduct_material_name())) {
										enquiryItem.setProduct_material_id(updatedObject.getProduct_pr_id());
									}
									return enquiryItem;
								})
								.collect(Collectors.toList());
					}
				}

//				// ** For Enquiry Master
				CMtEnquiryMasterProjectModel results = iMtEnquiryMasterProjectRepository.FnCheckEnquiryIsExist(enquiry_no, financial_year, company_id);
				if (results != null) {
					update = true;
					results.setDeleted_by(enquiryMasterData.getCreated_by());
					results.setDeleted_on(new Date());
					results.setIs_delete(true);
					iMtEnquiryMasterProjectRepository.save(results);
					enquiryMasterData.setEnquiry_version(results.getEnquiry_version() + 1);
				}
				CMtEnquiryMasterProjectModel responceEnquiryMaster = iMtEnquiryMasterProjectRepository.save(enquiryMasterData);

//				// ** For Enquiry Details
				iMtEnquiryDetailsProjectRepository.FnUpdateDetailsMasterRecord(enquiryMasterData.getCreated_by(), enquiry_no, enquiry_version, company_id);
				cMtEnquiryDetailsProjectModels.forEach(detail -> {
					detail.setEnquiry_version(responceEnquiryMaster.getEnquiry_version());
					detail.setEnquiry_master_transaction_id(responceEnquiryMaster.getEnquiry_master_transaction_id());
				});
				iMtEnquiryDetailsProjectRepository.saveAll(cMtEnquiryDetailsProjectModels);

				// ** Existing Expected Functionality Master
				iMtEnquiryExistingExpectedFunctionalityProjectRepository.updateExistingFunctionlityStatus(enquiryMasterData.getCreated_by(), enquiry_no, masterData.getInt("enquiry_version"), company_id);
				List<CMtEnquiryExistingExpectedFunctionalityProjectModel> MtEnquiryExistingExpectedFunctionalityModel = objectMapper
						.readValue(existingExpectedFunctionalityArray.toString(),
								new TypeReference<List<CMtEnquiryExistingExpectedFunctionalityProjectModel>>() {
								});

				MtEnquiryExistingExpectedFunctionalityModel.forEach(expectedFunctionality -> {
					expectedFunctionality.setEnquiry_functionality_transaction_id(0);
					expectedFunctionality.setEnquiry_version(responceEnquiryMaster.getEnquiry_version());
					expectedFunctionality.setEnquiry_master_transaction_id(responceEnquiryMaster.getEnquiry_master_transaction_id());
				});
				iMtEnquiryExistingExpectedFunctionalityProjectRepository.saveAll(MtEnquiryExistingExpectedFunctionalityModel);

				// ** For Enquiry Terms
				if (!enquiryTermsArray.isEmpty()) {
					iMtEnquiryTermsProjectRepository.updateEnquiryTermsStatus(enquiryMasterData.getCreated_by(), enquiry_no, enquiry_version, company_id);
					List<CMtEnquiryTermsProjectModel> enquiryTermsProjectModel = objectMapper.readValue(
							enquiryTermsArray.toString(), new TypeReference<List<CMtEnquiryTermsProjectModel>>() {
							});
					enquiryTermsProjectModel.forEach(enquiryTerm -> {
						enquiryTerm.setEnquiry_terms_transaction_id(0);
						enquiryTerm.setEnquiry_version(responceEnquiryMaster.getEnquiry_version());
						enquiryTerm.setEnquiry_master_transaction_id(responceEnquiryMaster.getEnquiry_master_transaction_id());
					});
					iMtEnquiryTermsProjectRepository.saveAll(enquiryTermsProjectModel);
				}

				responce.put("success", "1");
				responce.put("data", responceEnquiryMaster);
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

			} else {
				responce = FnSalesOrderDetailsUpdateRecord(DetailData, commonIdsObj, enquiryMasterData);
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtEnquiryMasterProject/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtEnquiryMasterProject/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	private Map<String, Object> FnSalesOrderDetailsUpdateRecord(JSONArray detailData, JSONObject commonIdsObj,
	                                                            CMtEnquiryMasterProjectModel enquiryMasterData) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		int company_id = commonIdsObj.getInt("company_id");
		try {

			// Approve the enquiry master.
			enquiryMasterData = iMtEnquiryMasterProjectRepository.save(enquiryMasterData);
			// Approve the enquiry items.
			List<CMtEnquiryDetailsProjectModel> enquiryDetailsTradingModel = objectMapper.readValue(detailData.toString(),
					new TypeReference<List<CMtEnquiryDetailsProjectModel>>() {
					});
			iMtEnquiryDetailsProjectRepository.saveAll(enquiryDetailsTradingModel);

			responce.put("success", "1");
			responce.put("data", enquiryMasterData);
			responce.put("error", "");
			responce.put("message", "Record approved successfully!...");
			responce.put("message", enquiryMasterData.getEnquiry_status().equals("R") ? "Enquiry has been rejected...! " : "Enquiry has been approved successfully...! ");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtEnquiryMasterProject/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtEnquiryMasterProject/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		try {
			String query;

			if ("summary".equals(reportType)) {
				query = "SELECT * FROM mtv_enquiry_master_project_summary_rpt";
				List<Map<String, Object>> results = executeQuery.queryForList(query);
				response.put("content", results);
			} else {
				query = "SELECT * FROM mtv_enquiry_details_project_rpt";
				List<Map<String, Object>> results = executeQuery.queryForList(query);
				response.put("content", results);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String enquiry_no, int enquiry_version, String financial_year, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			Map<String, Object> enquiryMasterProjectRecord = IMtEnquiryMasterProjectViewRepository
					.FnShowEnquiryMasterRecord(enquiry_no, enquiry_version, financial_year, company_id);
			List<Map<String, Object>> enquiryDetailsProjectRecords = iMtEnquiryDetailsProjectViewRepository
					.FnShowEnquiryDetailsProjectRecords(enquiry_no, enquiry_version, financial_year, company_id);
			List<CMtEnquiryExistingExpectedFunctionalityProjectModel> enquiryExistingExpectedFunctionalityProjectRecords = iMtEnquiryExistingExpectedFunctionalityProjectViewRepository
					.FnShowEnquiryExistingExpectedProjectsFunctionality(enquiry_no, enquiry_version, company_id);
			List<MtEnquiryTermsProjectViewModel> enquiryTermsProjectRecords = iMtEnquiryTermsProjectViewRepository
					.FnShowEnquiryTermsProjects(enquiry_no, enquiry_version, company_id);

			responce.put("EnquiryMasterProjectRecord", enquiryMasterProjectRecord);
			responce.put("EnquiryDetailsProjectRecords", enquiryDetailsProjectRecords);
			responce.put("EquiryExistingExpectedFunctionalityProjectRecords", enquiryExistingExpectedFunctionalityProjectRecords);
			responce.put("EnquiryTermsProjectRecords", enquiryTermsProjectRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(String enquiry_no, int enquiry_version, String deleted_by,
	                                          int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iMtEnquiryMasterProjectRepository.deleteEnquiry(enquiry_no, enquiry_version, deleted_by, company_id);
			iMtEnquiryDetailsProjectRepository.deleteEnquiryDetails(enquiry_no, enquiry_version, deleted_by, company_id);
			iMtEnquiryTermsProjectRepository.deleteEnquiryTerms(enquiry_no, enquiry_version, deleted_by, company_id);
			iMtEnquiryExistingExpectedFunctionalityProjectRepository.deleteEnquiryExistingExpectedFunctionality(enquiry_no, enquiry_version, deleted_by, company_id);

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
	public Map<String, Object> FnSendEmail(int company_id, int enquiry_master_transaction_id, JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);
		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
		// Update the mail send status in master table.
		if (emailSentStatus) {
			iMtEnquiryMasterProjectRepository.updateMailStatus("S", company_id, enquiry_master_transaction_id);
		} else {
			iMtEnquiryMasterProjectRepository.updateMailStatus("F", company_id, enquiry_master_transaction_id);
		}

		emailingResponse.put("Status", emailSentStatus);
		emailingResponse.put("success", emailControllerResponse.get("success"));
		emailingResponse.put("error", emailControllerResponse.get("error"));
		emailingResponse.put("message", emailControllerResponse.get("message"));
		return emailingResponse;
	}
}
