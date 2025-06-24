package com.erp.PtCustomerMaterialIssueMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.PtCustomerMaterialIssueMaster.Model.CPtCustomerMaterialIssueDetailsModel;
import com.erp.PtCustomerMaterialIssueMaster.Model.CPtCustomerMaterialIssueDetailsViewModel;
import com.erp.PtCustomerMaterialIssueMaster.Model.CPtCustomerMaterialIssueMasterModel;
import com.erp.PtCustomerMaterialIssueMaster.Repository.IPtCustomerMaterialIssueDetailsRepository;
import com.erp.PtCustomerMaterialIssueMaster.Repository.IPtCustomerMaterialIssueDetailsViewRepository;
import com.erp.PtCustomerMaterialIssueMaster.Repository.IPtCustomerMaterialIssueMasterRepository;
import com.erp.PtCustomerMaterialIssueMaster.Repository.IPtCustomerMaterialIssueSummaryViewRepository;
import com.erp.PtGoodsReceiptMasterCustomer.Repository.IPtGoodsReceiptDetailsCustomerRepository;
import com.erp.PtGoodsReceiptMasterCustomer.Repository.IPtGoodsReceiptMasterCustomerRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductStockTracking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CPtCustomerMaterialIssueMasterServiceImpl implements IPtCustomerMaterialIssueMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IPtCustomerMaterialIssueMasterRepository iPtCustomerMaterialIssueMasterRepository;

	@Autowired
	IPtCustomerMaterialIssueDetailsRepository iPtCustomerMaterialIssueDetailsRepository;

	@Autowired
	IPtCustomerMaterialIssueDetailsViewRepository iPtCustomerMaterialIssueDetailsViewRepository;

	@Autowired
	IPtCustomerMaterialIssueSummaryViewRepository iPtCustomerMaterialIssueSummaryViewRepository;

	@Autowired
	IPtGoodsReceiptDetailsCustomerRepository iPtGoodsReceiptDetailsCustomerRepository;

	@Autowired
	IPtGoodsReceiptMasterCustomerRepository iPtGoodsReceiptMasterCustomerRepository;

	@Autowired
	CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

	@Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String customer_material_issue_no = commonIdsObj.getString("customer_material_issue_no");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsarray = (JSONArray) jsonObject.get("TransDetailData");
		String status = "";
		try {

			// Get Customer Material Issue Master Data
			CPtCustomerMaterialIssueMasterModel jsonModel = objectMapper.readValue(masterjson.toString(),
					CPtCustomerMaterialIssueMasterModel.class);

			CPtCustomerMaterialIssueMasterModel getExistingRecord = iPtCustomerMaterialIssueMasterRepository
					.getExistingRecord(customer_material_issue_no, jsonModel.getCustomer_material_issue_version(),
							financial_year, company_id);

			// Update Customer Material Issue Master Data if Record Exist
			if (getExistingRecord != null) {
				update = true;
				getExistingRecord.setCreated_by(jsonModel.getCreated_by());
				getExistingRecord.setDeleted_on(new Date());
				getExistingRecord.setIs_delete(true);
				iPtCustomerMaterialIssueMasterRepository.save(getExistingRecord);

				jsonModel.setCustomer_material_issue_version(getExistingRecord.getCustomer_material_issue_version() + 1);

			}

			// Save Customer Material Issue Master Data
			CPtCustomerMaterialIssueMasterModel responseCustomerMaterialIssueMasterModel = iPtCustomerMaterialIssueMasterRepository
					.save(jsonModel);

			// Get Customer Material Issue Detail Array
			List<CPtCustomerMaterialIssueDetailsModel> customerMaterialIssueDetailsModel = objectMapper.readValue(
					detailsarray.toString(), new TypeReference<List<CPtCustomerMaterialIssueDetailsModel>>() {
					});

			if (!customerMaterialIssueDetailsModel.isEmpty()) {
				int customer_material_issue_version = masterjson.getInt("customer_material_issue_version");
				if (update) {
					iPtCustomerMaterialIssueDetailsRepository.updateStatus(customer_material_issue_no, financial_year,
							company_id);
				}


				//Update Customer Material Receipt status
				List<String> inCustomerGoodsReceiptNOs = new ArrayList<>();

				// Get distinct Cutomer Order No order numbers
				List<String> distinctCustomerGoodReceiptNumbers = customerMaterialIssueDetailsModel.stream()
						.map(CPtCustomerMaterialIssueDetailsModel::getCustomer_goods_receipt_no).distinct().collect(Collectors.toList());

				// Update material status for each item
				customerMaterialIssueDetailsModel.forEach(item -> {

					// set version and master transaction id
					item.setCustomer_material_issue_version(jsonModel.getCustomer_material_issue_version());
					item.setCustomer_material_issue_master_transaction_id(responseCustomerMaterialIssueMasterModel
							.getCustomer_material_issue_master_transaction_id());

					// Calculate the remaining quantity for the current item
					double materialRemainingQty = item.getCustomer_material_accepted_quantity() - item.getProduct_material_issue_quantity();

					if (materialRemainingQty == 0) {
						System.out.println("Material Item Status: (C-Completed): " + materialRemainingQty);
						iPtGoodsReceiptDetailsCustomerRepository.updateCustomerMaterialReceiptDetailStatus("C", item.getCustomer_material_id());

					} else if (materialRemainingQty > 0) {
						inCustomerGoodsReceiptNOs.add(item.getCustomer_goods_receipt_no());
						System.out.println("Material Item Status: (I-Partial Issue): " + materialRemainingQty);
						iPtGoodsReceiptDetailsCustomerRepository.updateCustomerMaterialReceiptDetailStatus("I", item.getCustomer_material_id());
					}

				});

				distinctCustomerGoodReceiptNumbers.forEach(customerGoodReceiptNumber -> {

					if (inCustomerGoodsReceiptNOs.contains(customerGoodReceiptNumber)) {
						// Update status to "I" for incomplete material Customer Good Receipt Number
						iPtGoodsReceiptMasterCustomerRepository.updateCustomerMaterialReceiptMasterStatus("I", customerGoodReceiptNumber);
					} else {
						// Update status to "C" for complete material Customer Good Receipt Number
						iPtGoodsReceiptMasterCustomerRepository.updateCustomerMaterialReceiptMasterStatus("MC", customerGoodReceiptNumber);
					}
				});

				List<CPtCustomerMaterialIssueDetailsModel> responseCustomerMaterialIssueDetails = iPtCustomerMaterialIssueDetailsRepository
						.saveAll(customerMaterialIssueDetailsModel);

				// Update Stock here
				FnUpdateProductStockDetails(responseCustomerMaterialIssueMasterModel, responseCustomerMaterialIssueDetails, company_id);

			}

			response.put("success", "1");
			response.put("data", responseCustomerMaterialIssueMasterModel);
			response.put("error", "");
			response.put("message", update ? "Record updated successfully!" : "Record added successfully!");

		} catch (DataAccessException e) {
			handleDataAccessException(e, company_id, response);
			throw e; // rethrow the exception to ensure rollback
		} catch (Exception e) {
			handleGenericException(e, company_id, response);
			throw e; // rethrow the exception to ensure rollback
		}

		return response;
	}

	private void FnUpdateProductStockDetails(CPtCustomerMaterialIssueMasterModel responseCustomerMaterialIssueMasterModel, List<CPtCustomerMaterialIssueDetailsModel> customerMaterialIssueDetailsModel,
	                                         int company_id) {

//		GET CURRENTDATE
		Date currentDate = new Date();

//	    FORMATTING THE DATE TO DISPLAY ONLY THE DATE PORTION
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = dateFormat.format(currentDate);

		Map<String, Object> stockDetails = new HashMap<>();

//		CREATE LIST TO ADD OBJECT TO SAVE STOCK DETAILS & SUMMARY
		List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
		List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
		List<CSmProductStockTracking> addStockTracking = new ArrayList<>();

		customerMaterialIssueDetailsModel.forEach(issueItem -> {
			String material_id = issueItem.getCustomer_material_id();
			System.out.println("RM Summary Object for material_id: " + material_id);
			CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
			CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

			productRmStockSummaryModel.setProduction_issue_quantity(issueItem.getProduction_issue_quantity());
			productRmStockSummaryModel.setProduction_issue_weight(issueItem.getProduction_issue_weight());

			productRmStockSummaryModel.setClosing_balance_quantity(-issueItem.getProduction_issue_quantity());
			productRmStockSummaryModel.setClosing_balance_weight(-issueItem.getProduction_issue_weight());


			productRmStockSummaryModel.setCompany_id(issueItem.getCompany_id());
			productRmStockSummaryModel.setCompany_branch_id(issueItem.getCompany_branch_id());
			productRmStockSummaryModel.setFinancial_year(issueItem.getFinancial_year());
			productRmStockSummaryModel.setCustomer_id(responseCustomerMaterialIssueMasterModel.getCustomer_id());

			productRmStockSummaryModel.setProduct_type_group(issueItem.getCustomer_material_issue_type());
			productRmStockSummaryModel.setProduct_type_id(issueItem.getCustomer_material_issue_type_id());
			productRmStockSummaryModel.setProduct_rm_id(issueItem.getCustomer_material_id());
			productRmStockSummaryModel.setProduct_material_unit_id(issueItem.getCustomer_material_unit_id());
			productRmStockSummaryModel.setProduct_material_packing_id(issueItem.getCustomer_material_packing_id());
			productRmStockSummaryModel.setModified_by(responseCustomerMaterialIssueMasterModel.getCreated_by());
			productRmStockSummaryModel.setGodown_id(issueItem.getGodown_id());
			productRmStockSummaryModel.setGodown_section_id(issueItem.getGodown_section_id());
			productRmStockSummaryModel.setGodown_section_beans_id(issueItem.getGodown_section_beans_id());


			addProductRmStockSummaryList.add(productRmStockSummaryModel);

//			 CUTOMER GOOD RECEIPT DETAILS JSON
			System.out.println(" RM Details Object for material_id: " + material_id);
			productRmStockDetailsModel.setProduction_issue_quantity(issueItem.getProduction_issue_quantity());
			productRmStockDetailsModel.setProduction_issue_weight(issueItem.getProduction_issue_weight());

			productRmStockDetailsModel.setClosing_balance_quantity(-issueItem.getProduction_issue_quantity());
			productRmStockDetailsModel.setClosing_balance_weight(-issueItem.getProduction_issue_weight());

			productRmStockDetailsModel.setGoods_receipt_no(issueItem.getCustomer_goods_receipt_no());
			productRmStockDetailsModel.setCompany_id(issueItem.getCompany_id());
			productRmStockDetailsModel.setCompany_branch_id(issueItem.getCompany_branch_id());
			productRmStockDetailsModel.setFinancial_year(issueItem.getFinancial_year());
			productRmStockDetailsModel.setCustomer_id(responseCustomerMaterialIssueMasterModel.getCustomer_id());
//			productRmStockDetailsModel.setCustomer_order_no(issueItem.getCustomer_order_no());
			productRmStockDetailsModel.setProduct_rm_id(issueItem.getCustomer_material_id());
			productRmStockDetailsModel.setCustomer_goods_receipt_no(issueItem.getCustomer_goods_receipt_no());
			productRmStockDetailsModel.setGodown_id(issueItem.getGodown_id());
			productRmStockDetailsModel.setGodown_section_id(issueItem.getGodown_section_id());
			productRmStockDetailsModel.setGodown_section_beans_id(issueItem.getGodown_section_beans_id());

			addProductRmStockDetailsList.add(productRmStockDetailsModel);

			CSmProductStockTracking cSmProductStockTracking = new CSmProductStockTracking();

			cSmProductStockTracking.setCompany_id(issueItem.getCompany_id());
			cSmProductStockTracking.setCompany_branch_id(issueItem.getCompany_branch_id());
			cSmProductStockTracking.setFinancial_year(issueItem.getFinancial_year());
			cSmProductStockTracking.setProduct_material_id(issueItem.getCustomer_material_id());
			cSmProductStockTracking.setGoods_receipt_no(issueItem.getCustomer_goods_receipt_no());
			cSmProductStockTracking.setConsumption_no(issueItem.getCustomer_material_issue_no());
			cSmProductStockTracking.setConsumption_detail_no(String.valueOf(issueItem.getCustomer_material_issue_details_transaction_id()));
			cSmProductStockTracking.setConsumption_date(new Date());
			cSmProductStockTracking.setConsumption_location("Indent Issue");
			cSmProductStockTracking.setConsumption_quantity(issueItem.getProduction_issue_quantity());
			cSmProductStockTracking.setCreated_by(issueItem.getCreated_by());

			addStockTracking.add(cSmProductStockTracking);  // Add into stock tracking list

		});

		stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
		stockDetails.put("RmStockDetails", addProductRmStockDetailsList);

		cSmProductRmStockDetailsController.FnAddUpdateFGStock(stockDetails, "Decrease", company_id);
	}


	@Override
	public Map<String, Object> FnDeleteRecord(String customer_material_issue_no, int customer_material_issue_version,
	                                          int company_id, String deleted_by) {

		Map<String, Object> response = new HashMap<>();
		try {

			iPtCustomerMaterialIssueMasterRepository.FnDeleteCustomerMaterialIssueMasterRecord(
					customer_material_issue_no, customer_material_issue_version, company_id, deleted_by);
			iPtCustomerMaterialIssueDetailsRepository.FnDeleteCustomerMaterialIssueDetailsRecord(
					customer_material_issue_no, customer_material_issue_version, company_id, deleted_by);

			response.put("success", 1);
			response.put("data", "");
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
	public Map<String, Object> FnShowAllMasterAndDetailsmodelRecords(String customer_material_issue_no,
	                                                                 int customer_material_issue_version, String financial_year, int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {

			// Retrieve Customer Material Issue Master Record
			Map<String, Object> customerMaterialIssueMasterRecord = iPtCustomerMaterialIssueMasterRepository
					.FnShowCustomerMaterialIssueMasterRecords(customer_material_issue_no,
							customer_material_issue_version, financial_year, company_id);

			// Retrieve Customer Material Issue Details Records
			List<CPtCustomerMaterialIssueDetailsViewModel> customerMaterialIssueDetailsRecords = iPtCustomerMaterialIssueDetailsViewRepository
					.FnShowCustomerMaterialIssueDetailsRecords(customer_material_issue_no,
							customer_material_issue_version, financial_year, company_id);

			response.put("CustomerMaterialIssueMasterRecord", customerMaterialIssueMasterRecord);
			response.put("CustomerMaterialIssueDetailsRecords", customerMaterialIssueDetailsRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private void handleDataAccessException(DataAccessException e, int company_id, Map<String, Object> response) {
		e.printStackTrace();
		Throwable rootCause = e.getRootCause();
		if (rootCause instanceof SQLException) {
			SQLException sqlEx = (SQLException) rootCause;
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtCustomerMaterialIssueMaster/FnAddUpdateRecord", sqlEx.getErrorCode(),
					sqlEx.getMessage());
		} else {
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtCustomerMaterialIssueMaster/FnAddUpdateRecord", 0, e.getMessage());
		}
		response.put("success", "0");
		response.put("data", "");
		response.put("error", e.getMessage());
	}

	private void handleGenericException(Exception e, int company_id, Map<String, Object> response) {
		e.printStackTrace();
		amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
				"/api/PtCustomerMaterialIssueMaster/FnAddUpdateRecord", 0, e.getMessage());
		response.put("success", "0");
		response.put("data", "");
		response.put("error", e.getMessage());
	}

}
