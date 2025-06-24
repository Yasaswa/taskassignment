package com.erp.MtSalesQuotationMasterProject.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Common.EmailUtilities.Controller.EmailController;
import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryDetailsTradingModel;
import com.erp.MtEnquiryDetailsTrading.Repository.IMtEnquiryDetailsTradingRepository;
import com.erp.MtEnquiryDetailsTrading.Repository.IMtEnquiryMasterTradingSummaryRepository;
import com.erp.MtEnquiryMasterProject.Model.CMtEnquiryDetailsProjectModel;
import com.erp.MtEnquiryMasterProject.Model.CMtEnquiryMasterProjectModel;
import com.erp.MtEnquiryMasterProject.Repository.IMtEnquiryDetailsProjectRepository;
import com.erp.MtEnquiryMasterProject.Repository.IMtEnquiryMasterProjectRepository;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationDetailsProjectModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationExistingExpectedFunctionalityProjectModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationExistingExpectedFunctionalityProjectViewModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationFollowupProjectModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationFollowupProjectViewModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationMasterProjectModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationPaymentTermsProjectModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationPaymentTermsProjectViewModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationProjectDetailsViewModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationTermsProjectModel;
import com.erp.MtSalesQuotationMasterProject.Model.CMtSalesQuotationTermsProjectViewModel;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationDetailsProjectRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationDetailsProjectViewRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationExistingExpectedFunctionalityProjectRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationExistingExpectedFunctionalityProjectViewRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationFollowupProjectRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationFollowupProjectViewRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationMasterProjectRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationPaymentTermsProjectRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationPaymentTermsProjectViewRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationSummaryProjectViewRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationTermsProjectRepository;
import com.erp.MtSalesQuotationMasterProject.Repository.IMtSalesQuotationTermsProjectViewRepository;
import com.erp.SmProductPr.Model.CSmProductPrModel;
import com.erp.SmProductPr.Service.ISmProductPrService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CMtSalesQuotationMasterProjectServiceImpl implements IMtSalesQuotationMasterProjectService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtSalesQuotationMasterProjectRepository iMtSalesQuotationMasterProjectRepository;

	@Autowired
	ISmProductPrService iSmProductPrService;
	
	@Autowired
	IMtSalesQuotationDetailsProjectRepository iMtSalesQuotationDetailsProjectRepository;
	
	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityProjectRepository iMtSalesQuotationExistingExpectedFunctionalityProjectRepository;
	
	@Autowired
	IMtSalesQuotationPaymentTermsProjectRepository iMtSalesQuotationPaymentTermsProjectRepository;
	
	@Autowired
	IMtSalesQuotationTermsProjectRepository iMtSalesQuotationTermsProjectRepository;

	@Autowired
	IMtEnquiryDetailsTradingRepository iMtEnquiryDetailsTradingRepository;
	
	@Autowired
	IMtEnquiryMasterTradingSummaryRepository iMtEnquiryMasterTradingSummaryRepository;
	
	@Autowired
	IMtSalesQuotationFollowupProjectRepository iMtSalesQuotationFollowupProjectRepository;
	
	@Autowired
	IMtSalesQuotationDetailsProjectViewRepository iMtSalesQuotationDetailsProjectViewRepository;
	
	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityProjectViewRepository iMtSalesQuotationExistingExpectedFunctionalityProjectViewRepository;
	
	@Autowired
	IMtSalesQuotationFollowupProjectViewRepository iMtSalesQuotationFollowupProjectViewRepository;
	
	@Autowired
	IMtSalesQuotationPaymentTermsProjectViewRepository iMtSalesQuotationPaymentTermsProjectViewRepository;
	
	@Autowired
	IMtSalesQuotationSummaryProjectViewRepository iMtSalesQuotationSummaryProjectViewRepository;
	
	@Autowired
	IMtSalesQuotationTermsProjectViewRepository iMtSalesQuotationTermsProjectViewRepository;
	

	@Autowired
	IMtEnquiryDetailsProjectRepository iMtEnquiryDetailsProjectRepository;
	
	@Autowired
	IMtEnquiryMasterProjectRepository iMtEnquiryMasterProjectRepository;
	
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, boolean isApprove) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String quotation_no = commonIdsObj.getString("quotation_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
		JSONArray termsArray = (JSONArray) jsonObject.get("TransTermsData");
		JSONArray existingFucntionalityArray =  (JSONArray) jsonObject.get("TransExistingExpectedFucntionalityData");
		JSONArray payment_termsArray = (JSONArray) jsonObject.get("TransPaymentTermsData");
		JSONArray projectMaterialsData = (JSONArray) jsonObject.get("projectMaterialsData");

		try {
			CMtSalesQuotationMasterProjectModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CMtSalesQuotationMasterProjectModel.class);
			if (!isApprove) {
				
				boolean allProjectMaterialsAreSaved = true;

				//Before saving any data in enquiry transaction store the data into the project_master table.
				List<CSmProductPrModel> cSmProductPrModels = objectMapper.readValue(projectMaterialsData.toString(),
						new TypeReference<List<CSmProductPrModel>>() {
						});

				// Quotation Project Details 
				List<CMtSalesQuotationDetailsProjectModel> cmtSalesQuotationDetailsProjectModel = objectMapper
						.readValue(detailsArray.toString(),
								new TypeReference<List<CMtSalesQuotationDetailsProjectModel>>() {
								});

				Map<String, Object> projectResponse = new HashMap<>();
				for (CSmProductPrModel cSmProductPrModel : cSmProductPrModels) {
					projectResponse.clear();
					projectResponse = iSmProductPrService.FnAddUpdateMaterial(cSmProductPrModel, company_id);
				
					if (projectResponse.get("success").equals(1)) {
						CSmProductPrModel updatedObject = (CSmProductPrModel) projectResponse.get("data");
						// Update the enquiry details in the cMtEnquiryDetailsProjectModels where
						// material name matches.
						cmtSalesQuotationDetailsProjectModel.stream().map(quotationItem -> {
							if (updatedObject.getProduct_pr_name().equals(quotationItem.getProduct_material_name())) {
								quotationItem.setProduct_material_id(updatedObject.getProduct_pr_id());
							}
							return quotationItem;
						}).collect(Collectors.toList());
					}else {
						allProjectMaterialsAreSaved = false;
						responce.put("error", projectResponse.get("error"));
						break;
					}
				}
				
				if(!allProjectMaterialsAreSaved) {
					responce.put("success", "0");
					responce.put("data", "");
					if(responce.get("error").equals("")) {
						responce.put("error", "Error in saving materials...!");
					}
					return responce;
				}

				// Quotation Project Master Data
				CMtSalesQuotationMasterProjectModel getExistingRecord = iMtSalesQuotationMasterProjectRepository
						.getExistingRecord(quotation_no, jsonModel.getQuotation_version(), financial_year, company_id);

				if (getExistingRecord != null) {
					update = true;
					getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
					getExistingRecord.setDeleted_on(new Date());
					getExistingRecord.setis_delete(true);
					iMtSalesQuotationMasterProjectRepository.save(getExistingRecord);

					jsonModel.setQuotation_version(getExistingRecord.getQuotation_version() + 1);
				}

				CMtSalesQuotationMasterProjectModel responceSalesQuotationMasterProject = iMtSalesQuotationMasterProjectRepository
						.save(jsonModel);

				// Quotation Details Trading
				iMtSalesQuotationDetailsProjectRepository.updateStatus(quotation_no,
						masterjson.getInt("quotation_version"), company_id);
				
				cmtSalesQuotationDetailsProjectModel.forEach(items -> {
					items.setQuotation_version(jsonModel.getQuotation_version());
					items.setQuotation_master_transaction_id(
							responceSalesQuotationMasterProject.getQuotation_master_transaction_id());
				});

				iMtSalesQuotationDetailsProjectRepository.saveAll(cmtSalesQuotationDetailsProjectModel);

				//Quotation Project Terms
				if (!termsArray.isEmpty()) {
					iMtSalesQuotationTermsProjectRepository.updateSalesQuotationProjectTermsStatus(quotation_no,
							masterjson.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationTermsProjectModel> cmtSalesQuotationTermsProjectModel = objectMapper
							.readValue(termsArray.toString(),
									new TypeReference<List<CMtSalesQuotationTermsProjectModel>>() {
									});

					cmtSalesQuotationTermsProjectModel.forEach(items -> {
						items.setQuotation_version(jsonModel.getQuotation_version());
						items.setQuotation_master_transaction_id(
								responceSalesQuotationMasterProject.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationTermsProjectRepository.saveAll(cmtSalesQuotationTermsProjectModel);
				}

				//Quotation Existing Expected Project Functionality
				if (!existingFucntionalityArray.isEmpty()) {
					iMtSalesQuotationExistingExpectedFunctionalityProjectRepository.updateExistingExpectedProjectFunctionalityStatus(
							quotation_no, masterjson.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationExistingExpectedFunctionalityProjectModel> cmtSalesQuotationExistingExpectedFunctionalityProjectModel = objectMapper
							.readValue(existingFucntionalityArray.toString(),
									new TypeReference<List<CMtSalesQuotationExistingExpectedFunctionalityProjectModel>>() {
									});

					cmtSalesQuotationExistingExpectedFunctionalityProjectModel.forEach(items -> {
						items.setQuotation_version(jsonModel.getQuotation_version());
						items.setQuotation_master_transaction_id(
								responceSalesQuotationMasterProject.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationExistingExpectedFunctionalityProjectRepository
							.saveAll(cmtSalesQuotationExistingExpectedFunctionalityProjectModel);
				}
//				Quotation Payment Terms Functionality
				if (!payment_termsArray.isEmpty()) {
					iMtSalesQuotationPaymentTermsProjectRepository.updatePaymentTermsProjectStatus(quotation_no,
							masterjson.getInt("quotation_version"), company_id);

					List<CMtSalesQuotationPaymentTermsProjectModel> salesQuotationPaymentTermsProject = objectMapper.readValue(
							payment_termsArray.toString(),
							new TypeReference<List<CMtSalesQuotationPaymentTermsProjectModel>>() {
							});
					salesQuotationPaymentTermsProject.forEach(paymentTerm -> {
						paymentTerm.setQuotation_version(jsonModel.getQuotation_version());
						paymentTerm.setQuotation_master_transaction_id(
								responceSalesQuotationMasterProject.getQuotation_master_transaction_id());
					});
					iMtSalesQuotationPaymentTermsProjectRepository.saveAll(salesQuotationPaymentTermsProject);

				}

				responce.put("success", "1");
				responce.put("data", responceSalesQuotationMasterProject);
				responce.put("error", "");
				responce.put("message", update ? "Record updated successfully!" : "Record added successfully!");
			} else {
				responce = FnSalesQuotationProjectDetailsUpdateRecord(detailsArray, jsonModel, commonIdsObj);
			}

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesQuotationMasterProject/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesQuotationMasterProject/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	private Map<String, Object> FnSalesQuotationProjectDetailsUpdateRecord(JSONArray detailsArray,
			CMtSalesQuotationMasterProjectModel jsonModel, JSONObject commonIdsObj) {

		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		int company_id = commonIdsObj.getInt("company_id");

		try {
			// Quotation master
			CMtSalesQuotationMasterProjectModel updatedQTProjectMasterData = iMtSalesQuotationMasterProjectRepository.save(jsonModel);

			// Quotation details
			List<CMtSalesQuotationDetailsProjectModel> quotationDetails = objectMapper.readValue(
					detailsArray.toString(), new TypeReference<List<CMtSalesQuotationDetailsProjectModel>>() {
					});
			List<CMtSalesQuotationDetailsProjectModel> quotationFollowupTradingRecords = iMtSalesQuotationDetailsProjectRepository.saveAll(quotationDetails);

			// ***** Update the Enquiry status.
			if ("E".equals(updatedQTProjectMasterData.getQuotation_transaction_type())) {
				String detailModifiedBy = quotationDetails.get(0).getCreated_by();

				List<String> distinctEnquiryNos = quotationDetails.parallelStream()
						.map(CMtSalesQuotationDetailsProjectModel::getEnquiry_no).distinct()
						.collect(Collectors.toList());

				// 1. Updating the enquiry-details status.
				List<CMtEnquiryDetailsProjectModel> enquiryDetailsFromDB = iMtEnquiryDetailsProjectRepository.FnShowEnquiryProjectDetailsByEnqNos(company_id, distinctEnquiryNos);
				List<CMtEnquiryDetailsProjectModel> updatedEnquiryDetails = new ArrayList<CMtEnquiryDetailsProjectModel>();

				// Iterate the loop on the distinctEnquiryNos.
				distinctEnquiryNos.forEach(enquiryNo -> {
					// From enquiryDetailsFromDB(means received data from enquiry_details table) collect only the the current enquiryNo related details.
					List<CMtEnquiryDetailsProjectModel> currentEnqNoDetailsFromDB = enquiryDetailsFromDB.stream()
							.filter(enqMaterial -> enquiryNo.equals(enqMaterial.getEnquiry_no()))
							.collect(Collectors.toList());

					// From quotation materials collect all the quotationMaterial having enquiry_no. current.
					List<CMtSalesQuotationDetailsProjectModel> quotationDetailsEnqBased = quotationDetails.stream()
							.filter(quotationMat -> enquiryNo.equals(quotationMat.getEnquiry_no()))
							.collect(Collectors.toList());

					// Now iterate the loop on the quotation-material and find product_id matchingobject.
					quotationDetailsEnqBased.forEach(quotationMaterial -> {
						String product_material_id = quotationMaterial.getProduct_material_id();
						Optional<CMtEnquiryDetailsProjectModel> matchingEnqDetailObjs = currentEnqNoDetailsFromDB.stream()
								.filter(enqDetail -> product_material_id.equals(enqDetail.getProduct_material_id()))
								.findFirst();

						if (matchingEnqDetailObjs.isPresent()) {
							CMtEnquiryDetailsProjectModel matchedEnqDetail = matchingEnqDetailObjs.get();
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
					iMtEnquiryDetailsProjectRepository.saveAll(updatedEnquiryDetails);
				}

//							----------------- For Update the Enquiry master status.
				// get all enquiry details after details updatation.
				List<CMtEnquiryDetailsProjectModel> enquiryDetailsFromDBAfterUpdate = iMtEnquiryDetailsProjectRepository.FnShowEnquiryProjectDetailsByEnqNos(company_id, distinctEnquiryNos);

				// get all sales-order masters records for distinctSoNos.
				List<CMtEnquiryMasterProjectModel> enquiryMasters = iMtEnquiryMasterProjectRepository.FnShowEnquiriesByEnqNos(company_id, distinctEnquiryNos);

				// To Store the all enquiry-master records for update.
				List<CMtEnquiryMasterProjectModel> enquiryMastersForUpdate = new ArrayList<CMtEnquiryMasterProjectModel>();

				// Iterate the loop on enquiry master records.
				enquiryMasters.forEach(enquiryMst -> {
					String enquiryNo = enquiryMst.getEnquiry_no();
					// First from enquiryDetailsFromDBAfterUpdate get all details and compare all status.
					List<CMtEnquiryDetailsProjectModel> currentEnquiryDetails = enquiryDetailsFromDBAfterUpdate
							.stream().filter(enqDetail -> enquiryNo.equals(enqDetail.getEnquiry_no()))
							.collect(Collectors.toList());

					// Collect all itemStatus values into a List
					List<String> EnqItemStatusList = currentEnquiryDetails.stream()
							.map(CMtEnquiryDetailsProjectModel::getEnquiry_item_status).collect(Collectors.toList());

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
				iMtEnquiryMasterProjectRepository.saveAll(enquiryMastersForUpdate);
			}

			responce.put("success", "1");
			responce.put("data", updatedQTProjectMasterData);
			responce.put("error", "");
			String quotation_status = jsonModel.getQuotation_status();
			responce.put("message", quotation_status.equals("R") ? "Quotation has been rejected...! "
					: "Quotation has been approved successfully...! ");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesQuotationMasterProject/FnPurchaseOrderDetailsUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesQuotationMasterProject/FnPurchaseOrderDetailsUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	
	
	@Override
	public Map<String, Object> FnAddUpdateQuotationFollowupRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String quotation_no = commonIdsObj.getString("quotation_no");

		JSONArray followup_Array = (JSONArray) jsonObject.get("TransfollowupData");

		try {
			List<CMtSalesQuotationFollowupProjectModel> salesQuotationFollowupProject = objectMapper.readValue(
					followup_Array.toString(), new TypeReference<List<CMtSalesQuotationFollowupProjectModel>>() {
					});
			String created_By = salesQuotationFollowupProject.get(0).getCreated_by();
			boolean isCancelationFlag = salesQuotationFollowupProject.parallelStream()
					.anyMatch(item -> item.isCancelation_flag() == true);

			if (!followup_Array.isEmpty()) {
				iMtSalesQuotationFollowupProjectRepository.updateFollowupProjectRecords(created_By, quotation_no,
						company_id);
				iMtSalesQuotationFollowupProjectRepository.saveAll(salesQuotationFollowupProject);
			}

			// Update the quotation master status.
			if (isCancelationFlag) {
				iMtSalesQuotationMasterProjectRepository.updateSalesQuotationProjectMaster(quotation_no,
							company_id);
				iMtSalesQuotationDetailsProjectRepository.updateSalesQuotationProjectDetails(quotation_no, company_id);

			}

			responce.put("success", 1);
			responce.put("error", "");
			responce.put("message", "Record added successfully!");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesQuotationMasterProject/FnAddUpdateQuotationFollowupRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesQuotationMasterProject/FnAddUpdateQuotationFollowupRecord", 0, e.getMessage());
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
			iMtSalesQuotationMasterProjectRepository.deleteQuotationProjectMaster(quotation_no, quotation_version, company_id,
					deleted_by); // For master.
			iMtSalesQuotationDetailsProjectRepository.deleteQuotationProjectDetails(quotation_no, quotation_version,
					company_id, deleted_by); // For details.
			iMtSalesQuotationTermsProjectRepository.deleteQuotationTermsProjectDetails(quotation_no, quotation_version, company_id,
					deleted_by); // For common terms.
			iMtSalesQuotationExistingExpectedFunctionalityProjectRepository.deleteQuotationExistingExpectedFunctionalityProjectDetails(quotation_no,
					quotation_version, company_id, deleted_by); // For Existing Functionality.
			iMtSalesQuotationPaymentTermsProjectRepository.deleteQuotationPaymentTermsProjectDetails(quotation_no, quotation_version,
					company_id, deleted_by); // For Payment Terms.
			iMtSalesQuotationFollowupProjectRepository.deleteQuotationFollowupProjectDetails(quotation_no,
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

			Map<String, Object> quotationMasterProjectRecords = iMtSalesQuotationMasterProjectRepository
					.FnShowQuotationMasterProjectRecord(quotation_no, quotation_version, financial_year, company_id);

			List<Map<String, Object>> quotationDetailsProjectRecords = iMtSalesQuotationDetailsProjectViewRepository
					.FnShowQuotationDetailsProjectRecord(quotation_no, quotation_version, financial_year, company_id);

			List<CMtSalesQuotationTermsProjectViewModel> quotationTermsProjectRecords = iMtSalesQuotationTermsProjectViewRepository
					.FnShowQuotationTermsTradingRecord(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationExistingExpectedFunctionalityProjectViewModel> quotationExistingExpectedFucntionalityProjectRecords = iMtSalesQuotationExistingExpectedFunctionalityProjectViewRepository
					.FnShowQuotationExistingExpectedFucntionalityRecord(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationPaymentTermsProjectViewModel> quotationPaymentTermsProjectRecord = iMtSalesQuotationPaymentTermsProjectViewRepository
					.FnShowQuotationPaymentTermsProject(quotation_no, quotation_version, company_id);

			List<CMtSalesQuotationFollowupProjectViewModel> quotationFollowupProjectDetails = iMtSalesQuotationFollowupProjectViewRepository
					.FnShowQuotationFollowupTradingRecord(quotation_no, company_id);

			responce.put("QuotationMasterProjectRecords", quotationMasterProjectRecords);
			responce.put("QuotationDetailsProjectRecords", quotationDetailsProjectRecords);
			responce.put("QuotationTermsProjectRecords", quotationTermsProjectRecords);
			responce.put("QuotationExistingExpectedFucntionalityProjectRecords",quotationExistingExpectedFucntionalityProjectRecords);
			responce.put("QuotationPaymentTermsProjectRecord", quotationPaymentTermsProjectRecord);
			responce.put("QuotationFollowupProjectDetails", quotationFollowupProjectDetails);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}
	
	@Override
	public Map<String, Object> FnSendEmail(int company_id, int quotation_master_transaction_id, JSONObject emailData) {
		Map<String, Object> emailingResponse = new HashMap<>();

		EmailController emailController = new EmailController();
		Map<String, Object> emailControllerResponse = emailController.emailSender(company_id, emailData);

		Boolean emailSentStatus = (Boolean) emailControllerResponse.get("Status");
		// Update the mail send status in master table.
		if (emailSentStatus) {
			iMtSalesQuotationMasterProjectRepository.updateMailStatus("S", company_id, quotation_master_transaction_id);
		} else {
			iMtSalesQuotationMasterProjectRepository.updateMailStatus("F", company_id, quotation_master_transaction_id);
		}

		emailingResponse.put("Status", emailSentStatus);
		emailingResponse.put("success", emailControllerResponse.get("success"));
		emailingResponse.put("error", emailControllerResponse.get("error"));
		emailingResponse.put("message", emailControllerResponse.get("message"));
		return emailingResponse;
	}

	@Override
	public Map<String, Object> FnGetQuotationProjectDetailsByItemStatus(JSONObject jsonObject, int company_id) {
		Map<String, Object> responce = new HashMap<>();

		try {
			int quotation_version = jsonObject.getInt("quotation_version");
			String quotation_no = jsonObject.getString("quotation_no");
			String financial_year = jsonObject.getString("financial_year");
			String quotation_item_status = jsonObject.getString("quotation_item_status");

			List<CMtSalesQuotationProjectDetailsViewModel> responseProjectDetailsData = iMtSalesQuotationDetailsProjectViewRepository
					.FnGetQuotationProjectDetailsByItemStatus(quotation_no, quotation_version, financial_year,
							quotation_item_status, company_id);

			List<CMtSalesQuotationPaymentTermsProjectViewModel> responsePaymentTermsProjectData = iMtSalesQuotationPaymentTermsProjectViewRepository
					.FnGetQuotationPaymentTermsProjectData(quotation_no, quotation_version, company_id);

			responce.put("QuotationProjectDetailsData", responseProjectDetailsData);
			responce.put("QuotationPaymentTermsProjectData", responsePaymentTermsProjectData);
			responce.put("success", "1");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/MtSalesQuotationMasterProject/FnGetQuotationDetailsByItemStatus", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesQuotationMasterProject/FnGetQuotationDetailsByItemStatus", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}

		return responce;
	}

}
