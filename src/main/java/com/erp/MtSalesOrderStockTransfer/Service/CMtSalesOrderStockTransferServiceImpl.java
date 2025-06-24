package com.erp.MtSalesOrderStockTransfer.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.MtSalesOrderStockTransfer.Model.CMtSalesOrderStockTransferDetailsModel;
import com.erp.MtSalesOrderStockTransfer.Model.CMtSalesOrderStockTransferDetailsViewModel;
import com.erp.MtSalesOrderStockTransfer.Model.CMtSalesOrderStockTransferMasterModel;
import com.erp.MtSalesOrderStockTransfer.Model.CMtSalesOrderStockTransferMasterViewModel;
import com.erp.MtSalesOrderStockTransfer.Repository.IMtSalesOrderStockTransferDetailsModelRepository;
import com.erp.MtSalesOrderStockTransfer.Repository.IMtSalesOrderStockTransferDetailsViewModelRepository;
import com.erp.MtSalesOrderStockTransfer.Repository.IMtSalesOrderStockTransferMasterModelRepository;
import com.erp.MtSalesOrderStockTransfer.Repository.IMtSalesOrderStockTransferMasterViewModelRepository;
import com.erp.SmProductRmStockDetails.Controller.CSmProductRmStockDetailsController;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockDetailsModel;
import com.erp.SmProductRmStockDetails.Model.CSmProductRmStockSummaryModel;
import com.erp.SmProductRmStockDetails.Repository.ISmProductRmStockDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CMtSalesOrderStockTransferServiceImpl implements IMtSalesOrderStockTransferService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtSalesOrderStockTransferMasterModelRepository iMtSalesOrderStockTransferMasterModelRepository;

	@Autowired
	IMtSalesOrderStockTransferDetailsModelRepository iMtSalesOrderStockTransferDetailsModelRepository;

	@Autowired
	ISmProductRmStockDetailsRepository iSmProductRmStockDetailsRepository;

	@Autowired
	CSmProductRmStockDetailsController cSmProductRmStockDetailsController;

	@Autowired
	IMtSalesOrderStockTransferDetailsViewModelRepository iMtSalesOrderStockTransferDetailsViewModelRepository;

	@Autowired
	IMtSalesOrderStockTransferMasterViewModelRepository iMtSalesOrderStockTransferMasterViewModelRepository;

	@Override
	@Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
	public Map<String, Object> FnAddUpdateRecord(JSONObject requestTransferStock) throws JsonProcessingException {
		Map<String, Object> response = new HashMap<>();

		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) requestTransferStock.get("commonIds");

		int company_id = commonIdsObj.getInt("company_id");
		try {

			JSONObject customerStockMasterJson = requestTransferStock.getJSONObject("TransHeaderData");
			JSONArray customerStockDetailsJson = (JSONArray) requestTransferStock.get("TransDetailData");

			//	SAVE THE SALES ORDER CUSTOMER ORDER DETAILS IN mt_sales_order_stock_transfer_master
			CMtSalesOrderStockTransferMasterModel convertJsonToMasterModel = objectMapper.readValue(customerStockMasterJson.toString(), CMtSalesOrderStockTransferMasterModel.class);

			convertJsonToMasterModel = iMtSalesOrderStockTransferMasterModelRepository.save(convertJsonToMasterModel);

			//	SAVE THE SALES ORDER CUSTOMER ORDER DETAILS IN mt_sales_order_stock_transfer_master
			List<CMtSalesOrderStockTransferDetailsModel> convertJsonToDetailsModel = objectMapper.readValue(customerStockDetailsJson.toString(), new TypeReference<List<CMtSalesOrderStockTransferDetailsModel>>() {
			});

			CMtSalesOrderStockTransferMasterModel finalConvertJsonToMasterModel = convertJsonToMasterModel;
			convertJsonToDetailsModel.forEach(item -> {
				item.setSales_order_transfer_id(finalConvertJsonToMasterModel.getSales_order_transfer_id());
			});

			iMtSalesOrderStockTransferDetailsModelRepository.saveAll(convertJsonToDetailsModel);

//			Transfer Stock
			FnUpdateProductRmStockDetails(convertJsonToMasterModel, convertJsonToDetailsModel);

			response.put("success", 1);
			response.put("error", "");
			response.put("message", "Record added successfully!...");

		} catch (DataAccessException e) {
			handleDataAccessException(e, company_id, response);
			throw e; // rethrow the exception to ensure rollback
		} catch (Exception e) {
			handleGenericException(e, company_id, response);
			throw e; // rethrow the exception to ensure rollback
		}

		return response;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int sales_order_transfer_id) {
		Map<String, Object> response = new LinkedHashMap<>();
		try {
//          Get Sales Order Stock Master Details
			CMtSalesOrderStockTransferMasterViewModel getSoStockTransferMaster = iMtSalesOrderStockTransferMasterViewModelRepository.FnShowParticularRecordForUpdate(sales_order_transfer_id);

//          Get Sales Order Stock Details
			List<CMtSalesOrderStockTransferDetailsViewModel> getSoStockTransferDetails = iMtSalesOrderStockTransferDetailsViewModelRepository.FnShowParticularRecordForUpdate(sales_order_transfer_id);

			response.put("soStockTransferHeaderData", getSoStockTransferMaster);
			response.put("soStockTransferDetailsData", getSoStockTransferDetails);
			response.put("success", 1);
		}catch (Exception e){
			e.printStackTrace();
			response.put("error", e.getMessage());
			response.put("success", 0);
		}
		return response;
	}

	private void FnUpdateProductRmStockDetails(CMtSalesOrderStockTransferMasterModel customerStockMasterModel,
	                                           List<CMtSalesOrderStockTransferDetailsModel> customerStockDetailsModel) {

		String product_material_id = customerStockMasterModel.getProduct_material_id();

//		 Get the current date
		Date currentDate = new Date();

//		 Formatting the date to display only the date portion
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String todayDate = dateFormat.format(currentDate);

//		Get Distinct GRN No.'s
		List<String> distinctGrnNos = customerStockDetailsModel.parallelStream().map(CMtSalesOrderStockTransferDetailsModel::getGoods_receipt_no)
				.distinct().collect(Collectors.toList());

//		 Retrieve the list of goods receipt details based on the master transaction id
		List<CSmProductRmStockDetailsModel> responseGoodReceiptDetails = iSmProductRmStockDetailsRepository
				.getGrnDetailsForTransferStock(distinctGrnNos, Math.toIntExact(customerStockMasterModel.getCompany_id()));

		Map<String, Object> stockDetails = new HashMap<>();

//		Create list to add object to save stock details & Summary
		List<CSmProductRmStockDetailsModel> addProductRmStockDetailsList = new ArrayList<>();
		List<CSmProductRmStockSummaryModel> addProductRmStockSummaryList = new ArrayList<>();

		customerStockDetailsModel.forEach(object -> {
//			String material_id = object.getPro();

			String goods_receipt_no = object.getGoods_receipt_no();

			Optional<CSmProductRmStockDetailsModel> ptGoodsReceiptDetailsModel = responseGoodReceiptDetails.stream().filter(item -> item.getGoods_receipt_no().equals(goods_receipt_no)
					&& item.getProduct_rm_id().equals(product_material_id)).findFirst();

			CSmProductRmStockDetailsModel rmStockDetailsModel = new CSmProductRmStockDetailsModel();

			if (ptGoodsReceiptDetailsModel.isPresent()) {
				rmStockDetailsModel = ptGoodsReceiptDetailsModel.get();

				rmStockDetailsModel.setTransfer_issue_quantity(object.getTransfer_quantity());
				rmStockDetailsModel.setTransfer_issue_weight(object.getTransfer_weight());
				rmStockDetailsModel.setClosing_balance_quantity(-object.getTransfer_quantity());
				rmStockDetailsModel.setClosing_balance_weight(-object.getTransfer_weight());

				addProductRmStockDetailsList.add(rmStockDetailsModel);

				CSmProductRmStockDetailsModel rmStockDetailsModelLatest = new CSmProductRmStockDetailsModel();
				BeanUtils.copyProperties(rmStockDetailsModel, rmStockDetailsModelLatest);

				rmStockDetailsModelLatest.setStock_transaction_id(0);
				rmStockDetailsModelLatest.setGoods_receipt_no(rmStockDetailsModelLatest.getGoods_receipt_no() + "/1");
				rmStockDetailsModelLatest.setClosing_balance_quantity(object.getTransfer_quantity());
				rmStockDetailsModelLatest.setClosing_balance_weight(object.getTransfer_weight());
				rmStockDetailsModelLatest.setTransfer_issue_quantity(0);
				rmStockDetailsModelLatest.setTransfer_issue_weight(0);
				rmStockDetailsModelLatest.setReserve_quantity(0);
				rmStockDetailsModelLatest.setReserve_weight(0);
				rmStockDetailsModelLatest.setTransfer_receipt_quantity(object.getTransfer_quantity());
				rmStockDetailsModelLatest.setTransfer_receipt_weight(object.getTransfer_weight());
				rmStockDetailsModelLatest.setTotal_box_weight(object.getTransfer_weight());
				rmStockDetailsModelLatest.setTotal_quantity_in_box(object.getTransfer_quantity());
				rmStockDetailsModelLatest.setGodown_id(object.getGodown_id());
				rmStockDetailsModelLatest.setGodown_section_id(object.getGodown_section_id());
				rmStockDetailsModelLatest.setGodown_section_beans_id(object.getGodown_section_beans_id());

				addProductRmStockDetailsList.add(rmStockDetailsModelLatest);

			}
		});

		stockDetails.put("RmStockDetails", addProductRmStockDetailsList);

		cSmProductRmStockDetailsController.FnAddUpdateRmStockRawMaterials(stockDetails, "Increase", Math.toIntExact(customerStockMasterModel.getCompany_id()));

	}


	private void handleDataAccessException(DataAccessException e, int company_id, Map<String, Object> response) {
		e.printStackTrace();
		Throwable rootCause = e.getRootCause();
		if (rootCause instanceof SQLException) {
			SQLException sqlEx = (SQLException) rootCause;
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesOrderStockTransfer/FnAddUpdateRecord", sqlEx.getErrorCode(),
					sqlEx.getMessage());
		} else {
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/MtSalesOrderStockTransfer/FnAddUpdateRecord", 0, e.getMessage());
		}
		response.put("success", 0);
		response.put("data", "");
		response.put("error", e.getMessage());
	}

	private void handleGenericException(Exception e, int company_id, Map<String, Object> response) {
		e.printStackTrace();
		amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
				"/api/XtWarpingProductionOrder/FnAddUpdateRecord", 0, e.getMessage());
		response.put("success", 0);
		response.put("data", "");
		response.put("error", e.getMessage());
	}
}
