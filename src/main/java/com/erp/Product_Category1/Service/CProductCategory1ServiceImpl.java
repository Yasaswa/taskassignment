package com.erp.Product_Category1.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.transaction.Transactional;

import com.erp.Product_Type.Model.CSmProductTypeGroupParametersViewModel;
import com.erp.Product_Type.Repository.ISmProductTypeGroupParametersViewRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Category1.Model.CProductCategory1GroupParameterModel;
import com.erp.Product_Category1.Model.CProductCategory1Model;
import com.erp.Product_Category1.Model.CProductCategory1ViewModel;
import com.erp.Product_Category1.Repository.IProductCategory1GroupParameterRepository;
import com.erp.Product_Category1.Repository.IProductCategory1Repository;
import com.erp.Product_Category1.Repository.IProductCategory1ViewRepository;
import com.erp.Product_Type.Model.CSmProductTypeGroupParametersModel;
import com.erp.Product_Type.Model.CSmProductTypeGroupValuesParametersViewModel;
import com.erp.Product_Type.Repository.IProductTypeGroupValuesParameterViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CProductCategory1ServiceImpl implements IProductCategory1Service {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductCategory1Repository iProductCategory1Repository;

	@Autowired
	IProductCategory1ViewRepository iProductCategory1ViewRepository;

	@Autowired
	ISmProductTypeGroupParametersViewRepository iSmProductTypeGroupParametersViewRepository;
	
	@Autowired
	IProductTypeGroupValuesParameterViewRepository iProductTypeGroupValuesParameterViewRepository;
	
	@Autowired
	IProductCategory1GroupParameterRepository iProductCategory1GroupParameterRepository;
	
	@Autowired
	IProductCategory1ViewRepository productCategoryRepository;
	
	@Autowired
	IProductCategory1GroupParameterRepository productCategoryGroupParameterRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		Integer product_category1_id = commonIdsObj.has("product_category1_id") ? commonIdsObj.getInt("product_category1_id") : null;
		
		//Update ProductCategory1GroupParameterArray 
		boolean updateProductCategory1GroupParams = product_category1_id != null;

		AtomicInteger productCategory1Id = new AtomicInteger(product_category1_id != null ? product_category1_id : 0);

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray ProductCategory1GroupParameterArray = (JSONArray) jsonObject.get("ProductCategory1GroupParameterData");

		try {

			//Get masterjson Objcet here 
			CProductCategory1Model jsonModel = objectMapper.readValue(masterjson.toString(), CProductCategory1Model.class);
			
			if (jsonModel.getProduct_type_id() == 0) {
				
				//Check similar Product Category 1 Name and short name is exist or not
				CProductCategory1Model resultsProductCategory1Name = null;
				if (jsonModel.getProduct_category1_short_name() == null || jsonModel.getProduct_category1_short_name().isEmpty()) {
					resultsProductCategory1Name = iProductCategory1Repository.getCheck(jsonModel.getProduct_category1_name(),
							null, jsonModel.getCompany_id());
				} else {
					resultsProductCategory1Name = iProductCategory1Repository.getCheck(jsonModel.getProduct_category1_name(),
							jsonModel.getProduct_category1_short_name(),jsonModel.getCompany_id());
				}

				if (resultsProductCategory1Name != null) {
					responce.put("success", 0);
					responce.put("error", "Product Category 1 Name or Short name is already exist!");
					return responce;
				}
				
			}
			//save Product Type 1 Data 
			CProductCategory1Model responceProductCategory1Model = iProductCategory1Repository.save(jsonModel);


			// Get Product Category 1 Group Parameter Data and save it
			if (!ProductCategory1GroupParameterArray.isEmpty()) {
				
			    // Update Product Category Group Parameter Data if needed
			    if (updateProductCategory1GroupParams) {
			    	iProductCategory1GroupParameterRepository.updateProductCategory1GroupParameterRecord(company_id, product_category1_id);
			    }
				
				List<CProductCategory1GroupParameterModel> cSmProductCategory1GroupParameterRepositoryModel = objectMapper.readValue(
						ProductCategory1GroupParameterArray.toString(),
						new TypeReference<List<CProductCategory1GroupParameterModel>>() {
						});
				
			    cSmProductCategory1GroupParameterRepositoryModel.forEach(items -> {
					items.setProduct_type_id(responceProductCategory1Model.getProduct_type_id());
					items.setProduct_category1_id(responceProductCategory1Model.getProduct_category1_id());

				});

				//save Product Category 1 Group Parameters Records
			    iProductCategory1GroupParameterRepository.saveAll(cSmProductCategory1GroupParameterRepositoryModel);
			
			
		}
			
			responce.put("success", "1");
			responce.put("data", responceProductCategory1Model);
			responce.put("error", "");
			responce.put("message",productCategory1Id.get() == 0 ? "Record added successfully!" : "Record update successfully!");
			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory1/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/productcategory1/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

			return responce;
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int product_category1_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

	        // Delete Product Type details record
			iProductCategory1Repository.FnDeleteRecord(product_category1_id, deleted_by);
			
	        // Delete Product Category1 Group Parameters records
			iProductCategory1GroupParameterRepository.FnDeleteProductCategory1GroupParameterRecord(product_category1_id,
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
	public Map<String, Object> FnShowProductTypeGroupParameterAndParameterValueRecords(int product_type_id,
			int company_id) {
		  Map<String, Object> response = new HashMap<>();
		    try {
		    
		        // Fetch Product Type Group Parameters Name and Values records
		        List<CSmProductTypeGroupParametersViewModel> productTypeGroupParametersAndValuesRecord =
				        iSmProductTypeGroupParametersViewRepository.FnShowProductTypeGroupParametersRecordForUpdate(product_type_id, company_id);

		        // Populate response map
		        response.put("ProductTypeGroupParametersAndValuesRecord", productTypeGroupParametersAndValuesRecord);
		        response.put("success", "1");
		    } catch (Exception e) {
		        // Error handling
		        e.printStackTrace();
		        response.put("success", "0");
		        response.put("data", "");
		        response.put("error", e.getMessage());
		    }
		    return response;
	}
	
	@Override
	public Map<String, Object> FnShowProductCategory1AndGroupParameterRecordForUpdate(int product_category1_id,
			int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			 // Fetch Product Category 1 Records			
		    Map<String, Object>	productCategory1Record = iProductCategory1ViewRepository.FnShowProductCategory1RecordForUpdate(product_category1_id,company_id);
		    
			 // Fetch Product Category 1 Group Parameter Records
			List<CProductCategory1GroupParameterModel>  productCategory1GroupParameterRecords = iProductCategory1GroupParameterRepository.FnShowProductCategory1GroupParameterRecordForUpdate(product_category1_id,company_id);
			
			 
			responce.put("productCategory1Record", productCategory1Record);
			responce.put("ProductCategory1GroupParameterRecords", productCategory1GroupParameterRecords);
			responce.put("success", 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}


}
