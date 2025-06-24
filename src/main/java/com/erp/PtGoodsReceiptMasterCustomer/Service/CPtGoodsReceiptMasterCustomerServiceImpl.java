package com.erp.PtGoodsReceiptMasterCustomer.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.PtGoodsReceiptMasterCustomer.Model.CPtGoodsReceiptDetailsCustomerModel;
import com.erp.PtGoodsReceiptMasterCustomer.Model.CPtGoodsReceiptDetailsCustomerViewModel;
import com.erp.PtGoodsReceiptMasterCustomer.Model.CPtGoodsReceiptMasterCustomerModel;
import com.erp.PtGoodsReceiptMasterCustomer.Repository.IPtGoodsReceiptDetailsCustomerRepository;
import com.erp.PtGoodsReceiptMasterCustomer.Repository.IPtGoodsReceiptDetailsCustomerViewRepository;
import com.erp.PtGoodsReceiptMasterCustomer.Repository.IPtGoodsReceiptMasterCustomerRepository;
import com.erp.PtGoodsReceiptMasterCustomer.Repository.IPtGoodsReceiptMasterCustomerViewRepository;
import com.erp.SmProductFgStockDetails.Repository.ISmProductFgCustomerStockDetailsModelRepository;
import com.erp.SmProductFgStockDetails.Repository.ISmProductFgCustomerStockSummaryModelRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.*;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmCustomerStockDetailsModelRepository;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmCustomerStockSummaryModelRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CPtGoodsReceiptMasterCustomerServiceImpl implements IPtGoodsReceiptMasterCustomerService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IPtGoodsReceiptMasterCustomerRepository iPtGoodsReceiptMasterCustomerRepository;

	@Autowired
	IPtGoodsReceiptMasterCustomerViewRepository iPtGoodsReceiptMasterCustomerViewRepository;

	@Autowired
	IPtGoodsReceiptDetailsCustomerRepository iPtGoodsReceiptDetailsCustomerRepository;

	@Autowired
	IPtGoodsReceiptDetailsCustomerViewRepository iPtGoodsReceiptDetailsCustomerViewRepository;

	@Autowired
	ISmProductRmCustomerStockSummaryModelRepository iSmProductRmCustomerStockSummaryModelRepository;

	@Autowired
	ISmProductRmCustomerStockDetailsModelRepository iSmProductRmCustomerStockDetailsModelRepository;

	@Autowired
	ISmProductFgCustomerStockSummaryModelRepository iSmProductFgCustomerStockSummaryModelRepository;

	@Autowired
	ISmProductFgCustomerStockDetailsModelRepository iSmProductFgCustomerStockDetailsModelRepository;

	@Autowired
	CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

	@Autowired
	private JdbcTemplate executeQuery;

	@Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String customer_goods_receipt_no = commonIdsObj.getString("customer_goods_receipt_no");
		int customer_goods_receipt_version = commonIdsObj.getInt("customer_goods_receipt_version");
		String financial_year = commonIdsObj.getString("financial_year");

		JSONObject customermasterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsData = (JSONArray) jsonObject.get("TransDetailData");

		try {
			CPtGoodsReceiptMasterCustomerModel jsonModel = objectMapper.readValue(customermasterjson.toString(),
					CPtGoodsReceiptMasterCustomerModel.class);

			// Customer Goods Receipt Master
			CPtGoodsReceiptMasterCustomerModel getExistingRecord = iPtGoodsReceiptMasterCustomerRepository
					.getExistingRecord(customer_goods_receipt_no, jsonModel.getCustomer_goods_receipt_version(),
							financial_year, company_id);

			if (getExistingRecord != null) {
				update = true;
				getExistingRecord.setDeleted_by(jsonModel.getCreated_by());
				getExistingRecord.setDeleted_on(new Date());
				getExistingRecord.setIs_delete(true);
				iPtGoodsReceiptMasterCustomerRepository.save(getExistingRecord);

				jsonModel.setCustomer_goods_receipt_version(getExistingRecord.getCustomer_goods_receipt_version() + 1);
			}
			CPtGoodsReceiptMasterCustomerModel responseGoodsReceiptMasterCustomer = iPtGoodsReceiptMasterCustomerRepository
					.save(jsonModel);

			// Customer Goods Receipt Details
			iPtGoodsReceiptDetailsCustomerRepository.FnUpdateGoodReceiptDetailsCustomerRecord(customer_goods_receipt_no,
					company_id);

			List<CPtGoodsReceiptDetailsCustomerModel> cptGoodsReceiptDetailsCustomerModel = objectMapper
					.readValue(detailsData.toString(), new TypeReference<List<CPtGoodsReceiptDetailsCustomerModel>>() {
					});

			cptGoodsReceiptDetailsCustomerModel.forEach(items -> {
				items.setCustomer_goods_receipt_version(jsonModel.getCustomer_goods_receipt_version());
				items.setCustomer_goods_receipt_master_transaction_id(
						responseGoodsReceiptMasterCustomer.getCustomer_goods_receipt_master_transaction_id());
			});

			cptGoodsReceiptDetailsCustomerModel = iPtGoodsReceiptDetailsCustomerRepository.saveAll(cptGoodsReceiptDetailsCustomerModel);

			FnUpdateProductStockDetails(responseGoodsReceiptMasterCustomer, cptGoodsReceiptDetailsCustomerModel,
					responseGoodsReceiptMasterCustomer, company_id);

			response.put("data", responseGoodsReceiptMasterCustomer);
			response.put("success", "1");
			response.put("error", "");
			response.put("message", update ? "Record updated successfully..!" : "Record added successfully!...");

		} catch (DataAccessException e) {
			handleDataAccessException(e, company_id, response);
			throw e; // rethrow the exception to ensure rollback
		} catch (Exception e) {
			handleGenericException(e, company_id, response);
			throw e; // rethrow the exception to ensure rollback
		}
		return response;
	}

	private void handleDataAccessException(DataAccessException e, int company_id, Map<String, Object> response) {
		e.printStackTrace();
		Throwable rootCause = e.getRootCause();
		if (rootCause instanceof SQLException) {
			SQLException sqlEx = (SQLException) rootCause;
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtGoodsReceiptMasterCustomer/FnAddUpdateRecord", sqlEx.getErrorCode(),
					sqlEx.getMessage());
		} else {
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/PtGoodsReceiptMasterCustomer/FnAddUpdateRecord", 0, e.getMessage());
		}
		response.put("success", "0");
		response.put("data", "");
		response.put("error", e.getMessage());
	}

	private void handleGenericException(Exception e, int company_id, Map<String, Object> response) {
		e.printStackTrace();
		amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
				"/api/PtGoodsReceiptMasterCustomer/FnAddUpdateRecord", 0, e.getMessage());
		response.put("success", "0");
		response.put("data", "");
		response.put("error", e.getMessage());
	}

	private void FnUpdateProductStockDetails(CPtGoodsReceiptMasterCustomerModel responseGoodsReceiptMasterCustomer,
	                                         List<CPtGoodsReceiptDetailsCustomerModel> responseGoodReceiptDetails,
	                                         CPtGoodsReceiptMasterCustomerModel goodsReceiptMasterCustomer, int company_id) {
//		Get CurrentDate
		Date currentDate = new Date();

//	    Formatting the date to display only the date portion
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = dateFormat.format(currentDate);

//		STOCK DETAILS STORE
		Map<String, Object> stockDetails = new HashMap<>();

//		CREATE LIST TO ADD OBJECT TO SAVE STOCK DETAILS & SUMMARY
		List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
		List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
		List<CSmProductStockTracking> addProductStockTrackingList = new ArrayList<>();


//		ITERATE THE CUSTOMER GOOD RECEIVED DETAILS
		if (responseGoodReceiptDetails.size() != 0) {
			responseGoodReceiptDetails.forEach(object -> {
				String material_id = object.getCustomer_material_id();

				CSmProductRmStockSummaryModel productRmStockSummaryModel = new CSmProductRmStockSummaryModel();
				CSmProductRmStockDetailsModel productRmStockDetailsModel = new CSmProductRmStockDetailsModel();

				productRmStockSummaryModel.setCustomer_receipt_quantity(object.getCustomer_material_accepted_quantity());
				productRmStockSummaryModel.setCustomer_receipt_weight(object.getCustomer_material_accepted_weight());
				productRmStockSummaryModel.setClosing_balance_quantity(object.getCustomer_material_accepted_quantity());
				productRmStockSummaryModel.setClosing_balance_weight(object.getCustomer_material_accepted_weight());
				productRmStockSummaryModel.setOrder_quantity(object.getCustomer_material_order_quantity());
//				productRmStockSummaryModel.setOrder_weight(object.getCus);
				productRmStockSummaryModel.setCompany_id(object.getCompany_id());
				productRmStockSummaryModel.setCompany_branch_id(object.getCompany_branch_id());
				productRmStockSummaryModel.setFinancial_year(object.getFinancial_year());
				productRmStockSummaryModel.setCustomer_id(responseGoodsReceiptMasterCustomer.getCustomer_id());
				productRmStockSummaryModel.setProduct_type_group(object.getCustomer_goods_receipt_type());
				productRmStockSummaryModel.setProduct_type_id(object.getCustomer_goods_receipt_type_id());
				productRmStockSummaryModel.setProduct_rm_id(object.getCustomer_material_id());
				productRmStockSummaryModel.setProduct_material_unit_id(object.getCustomer_material_unit_id());
				productRmStockSummaryModel.setProduct_material_packing_id(object.getCustomer_material_packing_id());
				productRmStockSummaryModel.setGodown_id(object.getGodown_id());
				productRmStockSummaryModel.setGodown_section_id(object.getGodown_section_id());
				productRmStockSummaryModel.setGodown_section_beans_id(object.getGodown_section_beans_id());

				productRmStockSummaryModel.setTotal_box_weight(object.getTotal_box_weight());
				productRmStockSummaryModel.setTotal_quantity_in_box(object.getTotal_quantity_in_box());
				productRmStockSummaryModel.setWeight_per_box_item(object.getWeight_per_box_item());

				addProductRmStockSummaryList.add(productRmStockSummaryModel);

//              CUTOMER GOOD RECEIPT DETAILS JSON

				productRmStockDetailsModel.setCompany_id(object.getCompany_id());
				productRmStockDetailsModel.setCompany_branch_id(object.getCompany_branch_id());
				productRmStockDetailsModel.setFinancial_year(object.getFinancial_year());
				productRmStockDetailsModel.setStock_date(todayDate);
				productRmStockDetailsModel.setGoods_receipt_no(goodsReceiptMasterCustomer.getCustomer_goods_receipt_no());
				productRmStockDetailsModel.setCustomer_id(goodsReceiptMasterCustomer.getCustomer_id());
				productRmStockDetailsModel.setCustomer_order_no(goodsReceiptMasterCustomer.getCustomer_order_no());
				productRmStockDetailsModel.setProduct_type_group(object.getCustomer_goods_receipt_type());
				productRmStockDetailsModel.setProduct_type_id(object.getCustomer_goods_receipt_type_id());
				productRmStockDetailsModel.setProduct_rm_id(object.getCustomer_material_id());
				productRmStockDetailsModel.setProduct_material_unit_id(object.getCustomer_material_unit_id());
				productRmStockDetailsModel.setProduct_material_packing_id(object.getCustomer_material_packing_id());

				productRmStockDetailsModel.setBatch_no(object.getCustomer_batch_no());
				productRmStockDetailsModel.setBatch_expiry_date(object.getExpiry_date());
				productRmStockDetailsModel.setCustomer_goods_receipt_no(object.getCustomer_goods_receipt_no());
				productRmStockDetailsModel.setGodown_id(object.getGodown_id());
				productRmStockDetailsModel.setGodown_section_id(object.getGodown_section_id());
				productRmStockDetailsModel.setGodown_section_beans_id(object.getGodown_section_beans_id());

				productRmStockDetailsModel.setCustomer_receipt_quantity(object.getCustomer_material_accepted_quantity());
				productRmStockDetailsModel.setCustomer_receipt_weight(object.getCustomer_material_accepted_weight());
				productRmStockDetailsModel.setClosing_balance_quantity(object.getCustomer_material_accepted_quantity());
				productRmStockDetailsModel.setClosing_balance_weight(object.getCustomer_material_accepted_weight());
				productRmStockDetailsModel.setOrder_quantity(object.getCustomer_material_order_quantity());
//				productRmStockDetailsModel.setOrder_weight(object.getCus);
				productRmStockDetailsModel.setTotal_box_weight(object.getTotal_box_weight());
				productRmStockDetailsModel.setTotal_quantity_in_box(object.getTotal_quantity_in_box());
				productRmStockDetailsModel.setWeight_per_box_item(object.getWeight_per_box_item());

				addProductRmStockDetailsList.add(productRmStockDetailsModel);

//			    Stock tracking details
				CSmProductStockTracking smProductStockTracking = new CSmProductStockTracking();

				smProductStockTracking.setCompany_id(object.getCompany_id());
				smProductStockTracking.setCompany_branch_id(object.getCompany_branch_id());
				smProductStockTracking.setFinancial_year(object.getFinancial_year());
				smProductStockTracking.setGoods_receipt_no(object.getCustomer_goods_receipt_no());
				smProductStockTracking.setProduct_material_id(material_id);
				smProductStockTracking.setStock_date(todayDate);
				smProductStockTracking.setStock_quantity(object.getCustomer_material_accepted_weight());
				smProductStockTracking.setProduct_material_unit_id(object.getCustomer_material_unit_id());
				smProductStockTracking.setStock_type("Customer");
				smProductStockTracking.setCreated_by(object.getCreated_by());

				addProductStockTrackingList.add(smProductStockTracking);


			});
			stockDetails.put("RmStockSummary", addProductRmStockSummaryList);
			stockDetails.put("RmStockDetails", addProductRmStockDetailsList);
			stockDetails.put("StockTracking", addProductStockTrackingList);

		}

		cSmProductRmStockDetailsController.FnAddUpdateFGStock(stockDetails, "Increase", company_id);
	}

//	private void FnUpdateProductStockDetails(CPtGoodsReceiptMasterCustomerModel responseGoodsReceiptMasterCustomer,
//			List<CPtGoodsReceiptDetailsCustomerModel> existingDetails,
//			CPtGoodsReceiptMasterCustomerModel goodsReceiptMasterCustomer, int company_id) {
//
////		Get CurrentDate
//		Date currentDate = new Date();
//
////	    Formatting the date to display only the date portion
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String todayDate = dateFormat.format(currentDate);
//		Map<String, Object> stockDetails = new HashMap<>();
//
////		 Retrieve the list of goods receipt details based on the master transaction id
//		List<CPtGoodsReceiptDetailsCustomerViewModel> responseGoodReceiptDetails = iPtGoodsReceiptDetailsCustomerViewRepository
//				.getAllDetails(responseGoodsReceiptMasterCustomer.getCustomer_goods_receipt_master_transaction_id());
//
//
//		//		Create list to add object to save stock details & Summary
//		List<CSmProductRmCustomerStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
//		List<CSmProductRmCustomerStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();
//
//
////			Iterate the customer good received details
//		if(responseGoodReceiptDetails.size() != 0){
//		responseGoodReceiptDetails.forEach(object -> {
//				String material_id = object.getCustomer_material_id();
//				System.out.println(" RM Summary Object for material_id: " + material_id);
//				CSmProductRmCustomerStockSummaryModel productRmStockSummaryModel = new CSmProductRmCustomerStockSummaryModel();
//				CSmProductRmCustomerStockDetailsModel productRmStockDetailsModel = new CSmProductRmCustomerStockDetailsModel();
//
//				Optional<CSmProductRmCustomerStockSummaryModel> updateRMSummaryRecords = addProductRmStockSummaryList
//						.stream().filter(item -> material_id.equals(item.getProduct_rm_id())).findFirst();
//
////				This is for getting previous quantity from customer good receipt details list before save
//				Optional<CPtGoodsReceiptDetailsCustomerModel> matchExisting = existingDetails.stream()
//						.filter(item -> material_id.equals(item.getCustomer_material_id())).findFirst();
//
//				CPtGoodsReceiptDetailsCustomerModel existingDetailObj = matchExisting.get();
//
//				double acceptedQuantity = object.getCustomer_material_accepted_quantity();
//				double acceptedWeight = object.getCustomer_material_accepted_weight();
//				double finalQuantity = acceptedQuantity - existingDetailObj.getPrev_accepted_quantity();
//				double finalWeight = acceptedWeight - existingDetailObj.getPrev_accepted_weight();
//
//				double orderQuantiy = object.getCustomer_material_order_quantity();
//				double finalOrderQuantity = orderQuantiy - existingDetailObj.getPrev_order_quantity();
//				double orderWeight = object.getCustomer_material_order_weight();
//				double finalOrderWeight = orderWeight - existingDetailObj.getPrev_order_weight();
//
//				if (updateRMSummaryRecords.isPresent()) {
//					CSmProductRmCustomerStockSummaryModel model = updateRMSummaryRecords.get();
//					model.setClosing_balance_quantity(model.getClosing_balance_quantity() + finalQuantity);
//					model.setClosing_balance_weight(model.getClosing_balance_weight() + finalWeight);
//					model.setCustomer_receipt_quantity(model.getCustomer_receipt_quantity() + finalQuantity);
//					model.setCustomer_receipt_weight(model.getCustomer_receipt_weight() + finalWeight);
//
//					// If you want to update the list with the modified object
//					int index = addProductRmStockSummaryList.indexOf(model);
//					addProductRmStockSummaryList.set(index, model);
//				} else {
//					if (existingDetailObj.getCustomer_material_prev_accepted_quantity() != object
//							.getCustomer_material_accepted_quantity()) {
//						productRmStockSummaryModel.setCustomer_receipt_quantity(finalQuantity);
//						productRmStockSummaryModel.setCustomer_receipt_weight(finalWeight);
//						productRmStockSummaryModel.setClosing_balance_quantity(finalQuantity);
//						productRmStockSummaryModel.setClosing_balance_weight(finalWeight);
//						productRmStockSummaryModel.setCompany_id(object.getCompany_id());
//						productRmStockSummaryModel.setCompany_branch_id(object.getCompany_branch_id());
//						productRmStockSummaryModel.setFinancial_year(object.getFinancial_year());
//						productRmStockSummaryModel.setCustomer_id(object.getCustomer_id());
//						productRmStockSummaryModel.setProduct_type_group(object.getCustomer_goods_receipt_type());
//						productRmStockSummaryModel.setProduct_type_id(object.getCustomer_goods_receipt_type_id());
//						productRmStockSummaryModel.setProduct_rm_id(object.getCustomer_material_id());
//						productRmStockSummaryModel.setProduct_material_unit_id(object.getCustomer_material_unit_id());
//						productRmStockSummaryModel.setProduct_material_packing_id(object.getCustomer_material_packing_id());
//						productRmStockSummaryModel.setGodown_id(object.getGodown_id());
//						productRmStockSummaryModel.setGodown_section_id(object.getGodown_section_id());
//						productRmStockSummaryModel.setGodown_section_beans_id(object.getGodown_section_beans_id());
//
//					} else {
//						return;
//					}
//				}
//
//				addProductRmStockSummaryList.add(productRmStockSummaryModel);
//
//				// Cutomer Good Receipt Details json
////				System.out.println(" RM Details Object for material_id: " + material_id);
//
//				if (existingDetailObj.getCustomer_material_prev_accepted_quantity() != object
//						.getCustomer_material_accepted_quantity()) {
//					productRmStockDetailsModel.setCustomer_receipt_quantity(
//							object.getCustomer_material_order_quantity() - existingDetailObj.getPrev_order_quantity());
//					productRmStockDetailsModel.setCustomer_receipt_weight(
//							object.getCustomer_material_accepted_weight() - existingDetailObj.getPrev_order_weight());
//					productRmStockDetailsModel.setClosing_balance_quantity(finalQuantity);
//					productRmStockDetailsModel.setClosing_balance_weight(finalWeight);
//
//				}
//
//				productRmStockDetailsModel.setCompany_id(object.getCompany_id());
//				productRmStockDetailsModel.setCompany_branch_id(object.getCompany_branch_id());
//				productRmStockDetailsModel.setFinancial_year(object.getFinancial_year());
//				productRmStockDetailsModel.setCustomer_id(object.getCustomer_id());
//				productRmStockDetailsModel.setCustomer_order_no(goodsReceiptMasterCustomer.getCustomer_order_no());
//				productRmStockDetailsModel.setProduct_type_group(object.getCustomer_goods_receipt_type());
//				productRmStockDetailsModel.setProduct_type_id(object.getCustomer_goods_receipt_type_id());
//				productRmStockDetailsModel.setProduct_rm_id(object.getCustomer_material_id());
//				productRmStockDetailsModel.setProduct_material_unit_id(object.getCustomer_material_unit_id());
//				productRmStockDetailsModel.setProduct_material_packing_id(object.getCustomer_material_packing_id());
//
//				productRmStockDetailsModel.setBatch_no(object.getCustomer_batch_no());
//				productRmStockDetailsModel.setBatch_expiry_date(object.getExpiry_date());
//				productRmStockDetailsModel.setCustomer_goods_receipt_no(object.getCustomer_goods_receipt_no());
//				productRmStockDetailsModel.setGodown_id(object.getGodown_id());
//				productRmStockDetailsModel.setGodown_section_id(object.getGodown_section_id());
//				productRmStockDetailsModel.setGodown_section_beans_id(object.getGodown_section_beans_id());
//
//				addProductRmStockDetailsList.add(productRmStockDetailsModel);
//
//			});
//			stockDetails.put("RmCustomerStockSummary", addProductRmStockSummaryList);
//			stockDetails.put("RmCustomerStockDetails", addProductRmStockDetailsList);
//
//		}
//
//		cSmProductRmStockDetailsController.FnAddUpdateCustomerStock(stockDetails, "Increase", company_id);
//
//	}

	@Override
	@Transactional
	public Map<String, Object> FnDeleteRecord(String customer_goods_receipt_no, int customer_id, int company_id,
	                                          String UserName) {
		Map<String, Object> response = new HashMap<>();
		try {
			iPtGoodsReceiptMasterCustomerRepository.deleteGoodsReceiptMasterCustomer(customer_goods_receipt_no,
					company_id, UserName);
			iPtGoodsReceiptDetailsCustomerRepository.deleteGoodsReceiptDetailsCustomer(customer_goods_receipt_no,
					company_id, UserName);

			response.put("success", "1");
			response.put("data", "");
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
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();

		if ("summary".equals(reportType)) {
			Map<String, Object> results = iPtGoodsReceiptMasterCustomerViewRepository.FnShowAllSummaryReportRecords();
			response.put("content", results);
		} else {
			Map<String, Object> results = iPtGoodsReceiptDetailsCustomerViewRepository.FnShowAlldetailsReportRecords();
			response.put("content", results);
		}

		return response;
	}

	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(String customer_goods_receipt_no,
	                                                                 int customer_goods_receipt_version, String financial_year, int company_id) {
		Map<String, Object> response = new HashMap<>();
		try {

			Map<String, Object> goodReceiptCustomerMasterRecord = iPtGoodsReceiptMasterCustomerRepository
					.FnShowGoodReceiptCustomerMasterRecords(customer_goods_receipt_no, customer_goods_receipt_version,
							financial_year, company_id);
			List<Map<String, Object>> goodReceiptCustomerDetailsRecord = iPtGoodsReceiptDetailsCustomerRepository
					.FnShowGoodReceiptCustomerDetailsRecords(customer_goods_receipt_no, customer_goods_receipt_version,
							financial_year, company_id);

			response.put("GoodReceiptCustomerMasterRecord", goodReceiptCustomerMasterRecord);
			response.put("GoodReceiptCustomerDetailsRecord", goodReceiptCustomerDetailsRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// here Update Customer Material Return Stock
	@Override
	public Map<String, Object> FnUpdateCustomerStockDetailsRecord(JSONObject jsonObject) {
		Map<String, Object> response = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = jsonObject.getJSONObject("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		JSONArray detailsArray = jsonObject.getJSONArray("TransDetailData");

		try {

			// Get List of detailsArray
			List<CPtGoodsReceiptDetailsCustomerModel> cPtGoodsReceiptDetailsCustomerModel = objectMapper
					.readValue(detailsArray.toString(), new TypeReference<List<CPtGoodsReceiptDetailsCustomerModel>>() {
					});

			// Iterate over the list of CPtGoodsReceiptDetailsCustomerModel objects
			for (CPtGoodsReceiptDetailsCustomerModel object : cPtGoodsReceiptDetailsCustomerModel) {
				// get required fields
				double customer_return_quantity = object.getCustomer_return_quantity();
				double customer_return_weight = object.getCustomer_return_weight();
				String customer_goods_receipt_no = object.getCustomer_goods_receipt_no();
				String customer_material_id = object.getCustomer_material_id();

				// update the customer material receipt details
				iPtGoodsReceiptDetailsCustomerRepository.updateCustomerMaterialReceiptDetails(customer_return_quantity,
						customer_return_weight, customer_goods_receipt_no, customer_material_id);
			}

			// Update Customer Material Return Stock
//			FnUpdateCustomerMaterialStockDetails(cPtGoodsReceiptDetailsCustomerModel, company_id);

			response.put("success", 1);
			response.put("message", "Material return successfully");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				response.put("success", 0);
				response.put("data", "");
				response.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("error", e.getMessage());
		}
		return response;

	}

	//  Customer Material Return Stock
	private void FnUpdateCustomerMaterialStockDetails(
			List<CPtGoodsReceiptDetailsCustomerModel> responseGoodsReceiptDetailsCustomerModel, int company_id) {

		Map<String, Object> stockDetails = new HashMap<>();
//		 Retrieve the list of goods receipt details based on the master transaction id

		// Retrieve customer_goods_receipt_master_transaction_id from each
		List<Integer> responseCustomerGoodsReceiptMasterId = responseGoodsReceiptDetailsCustomerModel.stream()
				.map(CPtGoodsReceiptDetailsCustomerModel::getCustomer_goods_receipt_master_transaction_id)
				.collect(Collectors.toList());

//		get All customer details
		List<CPtGoodsReceiptDetailsCustomerViewModel> responseGoodReceiptDetails = iPtGoodsReceiptDetailsCustomerViewRepository
				.getAllDetails(responseCustomerGoodsReceiptMasterId);

//		Create list to add object to save stock details & Summary
		List<CSmProductRmCustomerStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
		List<CSmProductRmCustomerStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

		if (responseGoodReceiptDetails.size() != 0) {

			responseGoodReceiptDetails.forEach(object -> {
				String material_id = object.getCustomer_material_id();
				System.out.println("RM Summary Object for : " + material_id);

				// Create new summary and detail objects
				CSmProductRmCustomerStockSummaryModel productRmStockSummaryModel = new CSmProductRmCustomerStockSummaryModel();
				CSmProductRmCustomerStockDetailsModel productRmStockDetailsModel = new CSmProductRmCustomerStockDetailsModel();

				double finalReturnQuantity = object.getCustomer_return_quantity();
				double finalReturnWeight = object.getCustomer_return_weight();

				//Check if material is same 
				Optional<CSmProductRmCustomerStockSummaryModel> updateSummaryRMRecords = addProductRmStockSummaryList
						.stream().filter(item -> material_id.equals(item.getProduct_rm_id())).findFirst();

				Optional<CPtGoodsReceiptDetailsCustomerModel> matchExisting = responseGoodsReceiptDetailsCustomerModel
						.stream().filter(item -> material_id.equals(item.getCustomer_material_id())).findFirst();

				if (updateSummaryRMRecords.isPresent()) {
					// Update existing summary record
					CSmProductRmCustomerStockSummaryModel model = updateSummaryRMRecords.get();
					// Update quantities
					double finalQuantity = finalReturnQuantity + model.getCustomer_return_quantity();
					double finalWeight = finalReturnWeight + model.getCustomer_return_weight();

					model.setCustomer_return_quantity(finalQuantity);
					model.setCustomer_return_weight(finalWeight);
					model.setClosing_balance_quantity(-finalQuantity);
					model.setClosing_balance_weight(-finalWeight);

					// Update the list with the modified object
					int index = addProductRmStockSummaryList.indexOf(model);
					addProductRmStockSummaryList.set(index, model);
				} else {
					if (matchExisting.isPresent()) {
						productRmStockSummaryModel.setCompany_id(object.getCompany_id());
						productRmStockSummaryModel.setCompany_branch_id(object.getCompany_branch_id());
						productRmStockSummaryModel.setFinancial_year(object.getFinancial_year());
						productRmStockSummaryModel.setCustomer_id(object.getCustomer_id());
						productRmStockSummaryModel.setProduct_rm_id(object.getCustomer_material_id());
						productRmStockSummaryModel.setCreated_by(object.getCreated_by());

						productRmStockSummaryModel.setCustomer_return_quantity(finalReturnQuantity);
						productRmStockSummaryModel.setCustomer_return_weight(finalReturnWeight);
						productRmStockSummaryModel.setClosing_balance_quantity(-finalReturnQuantity);
						productRmStockSummaryModel.setClosing_balance_weight(-finalReturnWeight);

						productRmStockSummaryModel.setGodown_id(object.getGodown_id());
						productRmStockSummaryModel.setGodown_section_id(object.getGodown_section_id());
						productRmStockSummaryModel.setGodown_section_beans_id(object.getGodown_section_beans_id());
					} else {
						return;
					}

					// Add summary to the list
					addProductRmStockSummaryList.add(productRmStockSummaryModel);
				}

				// Then continue with the details logic in a similar manner as for FG
				if (matchExisting.isPresent()) {

					productRmStockDetailsModel.setCustomer_return_quantity(finalReturnQuantity);
					productRmStockDetailsModel.setCustomer_return_weight(finalReturnWeight);
					productRmStockDetailsModel.setClosing_balance_quantity(-finalReturnQuantity);
					productRmStockDetailsModel.setClosing_balance_weight(-finalReturnWeight);
				}
				// Populate detail model
				productRmStockDetailsModel.setCompany_id(object.getCompany_id());
				productRmStockDetailsModel.setCompany_id(object.getCompany_id());
				productRmStockDetailsModel.setCompany_branch_id(object.getCompany_branch_id());
				productRmStockDetailsModel.setFinancial_year(object.getFinancial_year());
				productRmStockDetailsModel.setCustomer_id(object.getCustomer_id());
				productRmStockDetailsModel.setCustomer_order_no(object.getCustomer_order_no());
				productRmStockDetailsModel.setProduct_rm_id(object.getCustomer_material_id());

				productRmStockDetailsModel.setBatch_no(object.getCustomer_batch_no());
				productRmStockDetailsModel.setBatch_expiry_date(object.getExpiry_date());
				productRmStockDetailsModel.setCustomer_goods_receipt_no(object.getCustomer_goods_receipt_no());

				productRmStockDetailsModel.setGodown_id(object.getGodown_id());
				productRmStockDetailsModel.setGodown_section_id(object.getGodown_section_id());
				productRmStockDetailsModel.setGodown_section_beans_id(object.getGodown_section_beans_id());
				// Add detail to the list
				addProductRmStockDetailsList.add(productRmStockDetailsModel);

			});

			// Add the lists to the stockDetails map
			stockDetails.put("RmCustomerStockSummary", addProductRmStockSummaryList);
			stockDetails.put("RmCustomerStockDetails", addProductRmStockDetailsList);
		}
//		cSmProductRmStockDetailsController.FnAddUpdateCustomerStock(stockDetails, "Decrease", company_id);
	}

}
