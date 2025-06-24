//package com.erp.SmProductTypeGroupParameters.Service;
//
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Service;
//
//import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
//import com.erp.SmProductTypeGroupParameters.Model.CSmProductTypeGroupParametersModel;
//import com.erp.SmProductTypeGroupParameters.Model.CSmProductTypeGroupParametersValueViewModel;
//import com.erp.SmProductTypeGroupParameters.Model.CSmProductTypeGroupParametersValuesModel;
//import com.erp.SmProductTypeGroupParameters.Model.CSmProductTypeGroupParametersViewModel;
//import com.erp.SmProductTypeGroupParameters.Repository.ISmProductTypeGroupParametersRepository;
//import com.erp.SmProductTypeGroupParameters.Repository.ISmProductTypeGroupParametersValueRepository;
//import com.erp.SmProductTypeGroupParameters.Repository.ISmProductTypeGroupParametersValueViewRepository;
//import com.erp.SmProductTypeGroupParameters.Repository.ISmProductTypeGroupParametersViewRepository;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Service
//public class CSmProductTypeGroupParametersServiceImpl implements ISmProductTypeGroupParametersService {
//
//	@Autowired
//	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
//
//	@Autowired
//	ISmProductTypeGroupParametersRepository iSmProductTypeGroupParametersRepository;
//
//	@Autowired
//	ISmProductTypeGroupParametersValueRepository iSmProductTypeGroupParametersValueRepository;
//	
//	@Autowired
//	ISmProductTypeGroupParametersViewRepository iSmProductTypeGroupParametersViewRepository;
//	
//	
//	@Autowired
//	ISmProductTypeGroupParametersValueViewRepository iSmProductTypeGroupParametersValueViewRepository;
//	
//
//	@Override
//	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
//		Map<String, Object> responce = new HashMap<>();
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
//		int company_id = commonIdsObj.getInt("company_id");
//		int product_type_id = commonIdsObj.getInt("product_type_id");
//
//		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
//		JSONArray ProductTypeGroupParameterArray = (JSONArray) jsonObject.get("ProductTypeGroupParameterData");
//		JSONArray ProductTypeGroupParameterValueArray = (JSONArray) jsonObject.get("ProductTypeGroupParameterValueData");
//
//		try {
//
//			CSmProductTypeGroupParametersModel jsonModel = objectMapper.readValue(masterjson.toString(),
//					CSmProductTypeGroupParametersModel.class);
//			
//			CSmProductTypeGroupParametersModel responseProductTypeGroupParametersModel = iSmProductTypeGroupParametersRepository
//					.save(jsonModel);
//
//			if (!ProductTypeGroupParameterArray.isEmpty()) {
//
//				List<CSmProductTypeGroupParametersModel> cSmProductTypeGroupParametersModel = objectMapper
//						.readValue(ProductTypeGroupParameterArray.toString(),
//								new TypeReference<List<CSmProductTypeGroupParametersModel>>() {
//								});
//
//				cSmProductTypeGroupParametersModel.forEach(items -> {
//					items.setProduct_type_group_parameters_id(responseProductTypeGroupParametersModel.getProduct_type_group_parameters_id());
//				});
//				
//				iSmProductTypeGroupParametersRepository.updateProductTypeGroupParametersRecord(company_id,product_type_id);
//				iSmProductTypeGroupParametersRepository.saveAll(cSmProductTypeGroupParametersModel);
//				
//			}
//			
//			
//			if (!ProductTypeGroupParameterValueArray.isEmpty()) {
//
//			List<CSmProductTypeGroupParametersValuesModel> cSmProductTypeGroupParametersValuesModel = objectMapper
//					.readValue(ProductTypeGroupParameterValueArray.toString(),
//							new TypeReference<List<CSmProductTypeGroupParametersValuesModel>>() {
//							});
//
//			cSmProductTypeGroupParametersValuesModel.forEach(items -> {
//				items.setProduct_type_group_parameters_id(responseProductTypeGroupParametersModel.getProduct_type_group_parameters_id());
//			});
//			
//			iSmProductTypeGroupParametersValueRepository.updateProductTypeGroupParametersValueRecord(company_id,product_type_id);
//			iSmProductTypeGroupParametersValueRepository.saveAll(cSmProductTypeGroupParametersValuesModel);
//			
//			}
//
//
//			responce.put("success", "1");
//			responce.put("data", responseProductTypeGroupParametersModel);
//			responce.put("error", "");
//			responce.put("message", "Record added successfully!");
//
//		} catch (DataAccessException e) {
//			e.printStackTrace();
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
//						"/api/SmProductTypeGroupParameters/FnAddUpdateRecord", sqlEx.getErrorCode(),
//						sqlEx.getMessage());
//				System.out.println(sqlEx.getMessage());
//				responce.put("success", "0");
//				responce.put("data", "");
//				responce.put("error", e.getMessage());
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
//					"/api/SmProductTypeGroupParameters/FnAddUpdateRecord", 0, e.getMessage());
//			responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", e.getMessage());
//		}
//		return responce;
//	}
//
//	@Override
//	public Map<String, Object> FnShowParticularRecordForUpdate(int product_type_group_parameters_id, int company_id) {
//		Map<String, Object> responce = new HashMap<>();
//		try {
//							
//			
//			List<CSmProductTypeGroupParametersViewModel> cSmProductTypeGroupParametersModel = iSmProductTypeGroupParametersViewRepository.FnShowProductTypeGroupParametersValueRecordForUpdate(product_type_group_parameters_id,company_id);
//
//			List<CSmProductTypeGroupParametersValueViewModel> csmProductTypeGroupParametersValuesModel = iSmProductTypeGroupParametersValueViewRepository.FnShowProductTypeGroupParametersValueRecordForUpdate(product_type_group_parameters_id,company_id);
//
//			responce.put("ProductTypeGroupParametersModel", cSmProductTypeGroupParametersModel);
//			responce.put("ProductTypeGroupParametersValuesModel", csmProductTypeGroupParametersValuesModel);
//			responce.put("success", "1");
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				responce.put("success", "0");
//				responce.put("data", "");
//				responce.put("error", e.getMessage());
//				return responce;
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", e.getMessage());
//			return responce;
//		}
//		return responce;
//	}
//
//	@Override
//	public Map<String, Object> FnDeleteRecord(int product_type_group_parameters_id, String deleted_by) {
//		Map<String, Object> responce = new HashMap<>();
//		try {
//
//			iSmProductTypeGroupParametersRepository
//					.FnDeleteProductTypeGroupParametersRecord(product_type_group_parameters_id, deleted_by);
//			iSmProductTypeGroupParametersValueRepository
//					.FnDeleteProductTypeGroupParametersValueRecord(product_type_group_parameters_id, deleted_by);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			responce.put("success", "0");
//			responce.put("data", "");
//			responce.put("error", e.getMessage());
//		}
//		return responce;
//	}
//
//
//}
