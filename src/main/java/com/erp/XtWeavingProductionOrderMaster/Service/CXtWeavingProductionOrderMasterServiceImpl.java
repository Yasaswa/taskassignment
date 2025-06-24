package com.erp.XtWeavingProductionOrderMaster.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XtWarpingProductionOrder.Model.CXtWarpingProductionOrderModel;
import com.erp.XtWeavingProductionOrderMaster.Model.*;
import com.erp.XtWeavingProductionOrderMaster.Repository.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CXtWeavingProductionOrderMasterServiceImpl implements IXtWeavingProductionOrderMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXtWeavingProductionOrderMasterRepository iXtWeavingProductionOrderMasterRepository;

	@Autowired
	IXtWeavingProductionOrderSummaryViewRepository iXtWeavingProductionOrderSummaryViewRepository;

	@Autowired
	IXtWeavingProductionMaterialRepository iXtWeavingProductionMaterialRepository;

	@Autowired
	IXtWeavingProductionMaterialViewRepository iXtWeavingProductionMaterialViewRepository;

	@Autowired
	IXtWeavingProductionDetailsRepository iXtWeavingProductionDetailsRepository;

	@Autowired
	IXtWeavingProductionOrderStyleDetailsModelRepository iXtWeavingProductionOrderStyleDetailsModelRepository;

	@Autowired
	IXtvWeavingProductionOrderStyleDetailsRepository iXtvWeavingProductionOrderStyleDetailsRepository;


	@Transactional(rollbackFor = {Exception.class, SQLException.class, DataAccessException.class})
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) throws JsonProcessingException {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int weaving_production_order_id = commonIdsObj.getInt("weaving_production_order_id");

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray wvProdMaterialArray = (JSONArray) jsonObject.get("WeaingProductMaterialData");
		JSONObject detailsJson = jsonObject.getJSONObject("wvProductiondetailsData");
		JSONArray wvProdSalesOrderStyleDetails = jsonObject.getJSONArray("SalesOrderData");

		try {

			List<CXtWeavingProductionMaterialModel> cXtWeavingProductionMaterialModel = null;

			// Get Weaving Production Order data
			CXtWeavingProductionOrderMasterModel cXtWeavingProductionOrderMasterModel = objectMapper
					.readValue(masterjson.toString(), CXtWeavingProductionOrderMasterModel.class);

			// Saved cXtWeavingProductionOrderMasterModel
			CXtWeavingProductionOrderMasterModel responceWeavingProductionOrderMasterModel = iXtWeavingProductionOrderMasterRepository
					.save(cXtWeavingProductionOrderMasterModel);

			// JSON array into a list of CXtWeavingProductionMaterialModel objects using an ObjectMapper
			cXtWeavingProductionMaterialModel = objectMapper.readValue(wvProdMaterialArray.toString(), new TypeReference<List<CXtWeavingProductionMaterialModel>>() {
			});

			cXtWeavingProductionMaterialModel.forEach(items -> {
				items.setWeaving_production_order_id(responceWeavingProductionOrderMasterModel.getWeaving_production_order_id());
				items.setSet_no(responceWeavingProductionOrderMasterModel.getSet_no());

			});

			// here if update then only remove materials
			if (weaving_production_order_id != 0) {

				// distinct production order material ID's
				List<Integer> distinctProductOrderMaterialIds = cXtWeavingProductionMaterialModel.parallelStream()
						.map(CXtWeavingProductionMaterialModel::getProduction_order_material_id).distinct()
						.collect(Collectors.toList());

				// update material records if it's removed
				iXtWeavingProductionMaterialRepository.updateProductMaterialsRecords(distinctProductOrderMaterialIds, responceWeavingProductionOrderMasterModel.getSet_no());

			} else {
				//Weaving Production Details
				CXtWeavingProductionDetailsModel cXtWeavingProductionDetailsModel =
						objectMapper.readValue(detailsJson.toString(), CXtWeavingProductionDetailsModel.class);

				//save cXtWeavingProductionDetailsModel
				iXtWeavingProductionDetailsRepository.save(cXtWeavingProductionDetailsModel);

			}
			// Saved Weaving Production Material
			iXtWeavingProductionMaterialRepository.saveAll(cXtWeavingProductionMaterialModel);

//            //Weaving Production Details
//            CXtWeavingProductionDetailsModel cXtWeavingProductionDetailsModel =
//                    objectMapper.readValue(detailsJson.toString(), CXtWeavingProductionDetailsModel.class);
//
//            //save cXtWeavingProductionDetailsModel
//            iXtWeavingProductionDetailsRepository.save(cXtWeavingProductionDetailsModel);


//CHANGES BY VISHAL

			List<CXtWeavingProductionOrderStyleDetailsModel> cXtWeavingProductionOrderStyleDetailsModelList = new ArrayList<>();

			for (int i = 0; i < wvProdSalesOrderStyleDetails.length(); i++) {
				JSONObject order = wvProdSalesOrderStyleDetails.getJSONObject(i);
				JSONObject quantities = order.getJSONObject("quantities");

				// Set weaving_production_order_id for each item in cXtWeavingProductionMaterialModel
//                cXtWeavingProductionMaterialModel.forEach(items -> {
//                    items.setWeaving_production_order_id(responceWeavingProductionOrderMasterModel.getWeaving_production_order_id());
//                    items.setProduct_material_id(responceWeavingProductionOrderMasterModel.getProduct_material_id());
//                });


				for (String key : quantities.keySet()) {
					JSONObject newOrder = new JSONObject(order.toString()); // Create a copy of the original order
					// Set style_variant1 and style_variant1_qty fields
					newOrder.put("style_variant1", key);
					newOrder.put("style_variant1_qty", quantities.getDouble(key));
					newOrder.remove("quantities");

					newOrder.put("weaving_production_order_id", responceWeavingProductionOrderMasterModel.getWeaving_production_order_id());
					newOrder.put("product_material_id", responceWeavingProductionOrderMasterModel.getProduct_material_id());

					// Convert newOrder JSON to CXtWeavingProductionOrderStyleDetailsModel object
					CXtWeavingProductionOrderStyleDetailsModel model = objectMapper.readValue(newOrder.toString(), CXtWeavingProductionOrderStyleDetailsModel.class);
					cXtWeavingProductionOrderStyleDetailsModelList.add(model);
				}
			}
			iXtWeavingProductionOrderStyleDetailsModelRepository.saveAll(cXtWeavingProductionOrderStyleDetailsModelList);


			responce.put("data", responceWeavingProductionOrderMasterModel);
			responce.put("success", 1);
			responce.put("message",
					responceWeavingProductionOrderMasterModel.getWeaving_order_status().equals("A") ? "Record Approved successfully!..."
							: (responceWeavingProductionOrderMasterModel.getWeaving_order_status().equals("R") ? "Record Rejected successfully!.."
							: (weaving_production_order_id == 0 ? "Record added successfully!..."
							: "Record updated successfully!...")));

		} catch (DataAccessException e) {
			handleDataAccessException(e, company_id, responce);
			throw e; // rethrow the exception to ensure rollback
		} catch (Exception e) {
			handleGenericException(e, company_id, responce);
			throw e; // rethrow the exception to ensure rollback
		}

		return responce;
	}

	private void handleDataAccessException(DataAccessException e, int company_id, Map<String, Object> response) {
		e.printStackTrace();
		Throwable rootCause = e.getRootCause();
		if (rootCause instanceof SQLException) {
			SQLException sqlEx = (SQLException) rootCause;
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XtWeavingProductionOrderMaster/FnAddUpdateRecord", sqlEx.getErrorCode(),
					sqlEx.getMessage());
		} else {
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XtWeavingProductionOrderMaster/FnAddUpdateRecord", 0, e.getMessage());
		}
		response.put("success", 0);
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

	@Override
	public Map<String, Object> FnDeleteRecord(int weaving_production_order_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			// Delete Weaving Production Order Master
			iXtWeavingProductionOrderMasterRepository.FnDeleteProductionOrderRecord(weaving_production_order_id,
					deleted_by);

			// Delete Weaving Production Material Master
			iXtWeavingProductionMaterialRepository.FnDeleteWVProductionMaterialRecord(weaving_production_order_id,
					deleted_by);

			responce.put("success", 1);

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int weaving_production_order_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			// Fetch Weaving Production Order Records
			Map<String, Object> WeavingProductionOrderRecord = iXtWeavingProductionOrderSummaryViewRepository
					.FnShowParticularRecordForUpdate(weaving_production_order_id, company_id);

			// Fetch Weaving Production Material Records
			List<CXtWeavingProductionMaterialViewModel> WeavingProductionMaterialRecord = iXtWeavingProductionMaterialViewRepository
					.FnWeavingProductionMaterialRecordForUpdate(weaving_production_order_id, company_id);

			// Store WeavingProductionMaterialRecord in new hashMap
			Map<Integer, List<CXtWeavingProductionMaterialViewModel>> data = new HashMap<>();
			WeavingProductionMaterialRecord.stream()
					.collect(Collectors.groupingBy(CXtWeavingProductionMaterialViewModel::getProduction_sub_section_id))
					.forEach((productionSubSectionId, groupData) -> {
						data.put(productionSubSectionId, groupData);

					});

			// Modify data to include production_sub_section_short_name
			Map<String, List<CXtWeavingProductionMaterialViewModel>> WeavingProductionMaterialRecordWithShortNames = new HashMap<>();
			WeavingProductionMaterialRecord.stream()
					.filter(record -> data.containsKey(record.getProduction_sub_section_id())) // Filter records to
					// those included in the
					// data map
					.forEach(record -> {
						String shortName = record.getProduction_sub_section_short_name();
						if (!WeavingProductionMaterialRecordWithShortNames.containsKey(shortName)) {
							WeavingProductionMaterialRecordWithShortNames.put(shortName, new ArrayList<>());
						}
						WeavingProductionMaterialRecordWithShortNames.get(shortName).add(record);
					});

//			Get sales order details style
//			List<CXtWeavingProductionOrderStyleDetailsModel> getWeavingProductionSalesOrderDetails =
//					iXtWeavingProductionOrderStyleDetailsModelRepository.getSalesOrderStyleDetails(weaving_production_order_id, company_id);


//			Changes by vishal
			List<CXtvWeavingProductionOrderStyleDetailsModel> inputList =
					iXtvWeavingProductionOrderStyleDetailsRepository.getSalesOrderStyleViewDetails(weaving_production_order_id, company_id);

//			Get distict sales order no
			List<String> getDistinctSalesOrderNo = inputList.stream().map(CXtvWeavingProductionOrderStyleDetailsModel::getSales_order_no)
					.distinct().collect(Collectors.toList());

			Map<String, Map<String, Object>> resultMap = new HashMap<>();

			getDistinctSalesOrderNo.forEach(salesOrderNo -> {
				Map<String, Object> output = resultMap.getOrDefault(salesOrderNo, new HashMap<>());

				List<CXtvWeavingProductionOrderStyleDetailsModel> detailsModels = inputList.stream().filter(item -> item.getSales_order_no().equals(salesOrderNo))
						.collect(Collectors.toList());

				detailsModels.forEach(soStyleDetails -> {
					output.put("company_branch_id", String.valueOf(soStyleDetails.getCompany_branch_id()));
					output.put("sort_no", String.valueOf(soStyleDetails.getSort_no()));
					output.put("company_id", String.valueOf(soStyleDetails.getCompany_id()));
					output.put("set_no", soStyleDetails.getSet_no());
					output.put("schedule_date", soStyleDetails.getSchedule_date());
					output.put("sales_order_no", soStyleDetails.getSales_order_no());
					output.put("customer_name", soStyleDetails.getCustomer_name());
					// Directly add quantities to the output map
					if (soStyleDetails.getStyle_variant1() != null) {
						output.put(soStyleDetails.getStyle_variant1(), soStyleDetails.getStyle_variant1_qty());
					}
				});

				// Calculate the total quantity using reduce method
				double totalQuantity = detailsModels.stream()
						.map(CXtvWeavingProductionOrderStyleDetailsModel::getStyle_variant1_qty)
						.reduce(0.0, Double::sum);

				output.put("total", totalQuantity); // Update the total quantity in the output


				resultMap.put(salesOrderNo, output);
			});


			List<Map<String, Object>> WeavingProductionSalesOrderDetails = new ArrayList<>(resultMap.values());

			// Print or return the resultList as required
			WeavingProductionSalesOrderDetails.forEach(System.out::println);


			responce.put("WeavingProductionOrderRecord", WeavingProductionOrderRecord);
			responce.put("WeavingProductionMaterialRecordWithShortNames",
					WeavingProductionMaterialRecordWithShortNames);
			responce.put("WeavingProductionSalesOrderDetails", WeavingProductionSalesOrderDetails);
//			responce.put("getWeavingProductionSalesOrderDetails",getWeavingProductionSalesOrderDetails);
			responce.put("success", 1);

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;

	}

	@Override
	public Map<String, Object> GetLastSetNoWeavingProductionOrder() {
		Map<String, Object> responce = new HashMap<>();
		try {

			// Fetch Last SetNo Weaving Production Order
			Map<String, Object> responceLastSetNoWeavingProductionOrder = iXtWeavingProductionOrderMasterRepository
					.GetLastSetNoWeavingProductionOrder();

			responce.put("data", responceLastSetNoWeavingProductionOrder);
			responce.put("success", 1);

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}

		return responce;
	}

	@Override
	public Map<String, Object> FnDeletePerticularMaterialRecord(int production_order_material_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			// Delete Perticular Material Record
			iXtWeavingProductionMaterialRepository.FnDeletePerticularMaterialRecord(production_order_material_id,
					deleted_by);
			responce.put("success", 1);

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;

		}
		return responce;
	}

	@Override
	public Map<String, Object> FnAddMaterialRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");

		JSONArray wvProdMaterialArray = (JSONArray) jsonObject.get("WVProductMaterialData");

		try {
			// Get Weaving Production Order Material data
			List<CXtWeavingProductionMaterialModel> cXtWeavingProductionMaterialModel = objectMapper.readValue(
					wvProdMaterialArray.toString(), new TypeReference<List<CXtWeavingProductionMaterialModel>>() {
					});
			// Saved Weaving Production Material
			List<CXtWeavingProductionMaterialModel> respWeavingProductionMaterialModel = iXtWeavingProductionMaterialRepository
					.saveAll(cXtWeavingProductionMaterialModel);

			responce.put("data", respWeavingProductionMaterialModel);
			responce.put("success", 1);
			responce.put("message", "Record added successfully!...");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XtWeavingProductionOrderMaster/FnAddMaterialRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XtWeavingProductionOrderMaster/FnAddMaterialRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}


}
