package com.erp.Product_Type.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Product_Category1.Model.CProductCategory1Model;
import com.erp.Product_Category1.Repository.IProductCategory1Repository;
import com.erp.Product_Category2.Model.CProductCategory2Model;
import com.erp.Product_Category2.Repository.IProductCategory2Repository;
import com.erp.Product_Category3.Model.CProductCategory3Model;
import com.erp.Product_Category3.Repository.IProductCategory3Repository;
import com.erp.Product_Category4.Model.CProductCategory4Model;
import com.erp.Product_Category4.Repository.IProductCategory4Repository;
import com.erp.Product_Category5.Model.CProductCategory5Model;
import com.erp.Product_Category5.Repository.IProductCategory5Repository;
import com.erp.Product_Type.Model.CProductTypeModel;
import com.erp.Product_Type.Model.CProductTypeViewModel;
import com.erp.Product_Type.Model.CSmProductTypeGroupParametersModel;
import com.erp.Product_Type.Model.CSmProductTypeGroupParametersValueViewModel;
import com.erp.Product_Type.Model.CSmProductTypeGroupParametersValuesModel;
import com.erp.Product_Type.Model.CSmProductTypeGroupParametersViewModel;
import com.erp.Product_Type.Repository.IProductTypeRepository;
import com.erp.Product_Type.Repository.IProductTypeViewRepository;
import com.erp.Product_Type.Repository.ISmProductTypeGroupParametersRepository;
import com.erp.Product_Type.Repository.ISmProductTypeGroupParametersValueRepository;
import com.erp.Product_Type.Repository.ISmProductTypeGroupParametersValueViewRepository;
import com.erp.Product_Type.Repository.ISmProductTypeGroupParametersViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CProductTypeServiceImpl implements IProductTypeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IProductTypeRepository iProductTypeRepository;

	@Autowired
	IProductTypeViewRepository iProductTypeViewRepository;

	@Autowired
	ISmProductTypeGroupParametersRepository iSmProductTypeGroupParametersRepository;

	@Autowired
	ISmProductTypeGroupParametersValueRepository iSmProductTypeGroupParametersValueRepository;

	@Autowired
	ISmProductTypeGroupParametersViewRepository iSmProductTypeGroupParametersViewRepository;

	@Autowired
	ISmProductTypeGroupParametersValueViewRepository iSmProductTypeGroupParametersValueViewRepository;
	
	@Autowired
	IProductCategory1Repository iProductCategory1Repository;
	
	@Autowired
	IProductCategory2Repository iProductCategory2Repository;
	
	@Autowired
	IProductCategory3Repository iProductCategory3Repository;
	
	@Autowired
	IProductCategory4Repository iProductCategory4Repository;
	
	@Autowired
	IProductCategory5Repository iProductCategory5Repository;
	
	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {

		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		Integer product_type_id = commonIdsObj.has("product_type_id") ? commonIdsObj.getInt("product_type_id") : null;

		boolean updateProductTypeGroupParams = product_type_id != null;

		AtomicInteger atomicproductId = new AtomicInteger(product_type_id != null ? product_type_id : 0);

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray ProductTypeGroupParameterArray = (JSONArray) jsonObject.get("ProductTypeGroupParameterData");
		JSONArray ProductTypeGroupParameterValueArray = (JSONArray) jsonObject.get("ProductTypeGroupParameterValueData");

		try {

			//Get masterjson Objcet here 
			CProductTypeModel jsonModel = objectMapper.readValue(masterjson.toString(), CProductTypeModel.class);
			
			if (jsonModel.getProduct_type_id() == 0) {
				// Check similar Product Type Name and short name is exist or not
				CProductTypeModel resultsProductTypeName = null;
				if (jsonModel.getProduct_type_short_name() == null || jsonModel.getProduct_type_short_name().isEmpty()) {
					resultsProductTypeName = iProductTypeRepository.getCheck(jsonModel.getProduct_type_name(), null,
							jsonModel.getCompany_id());
				} else {
					resultsProductTypeName = iProductTypeRepository.getCheck(jsonModel.getProduct_type_name(),
							jsonModel.getProduct_type_short_name(), jsonModel.getCompany_id());
				}

				if (resultsProductTypeName != null) {
					responce.put("success", 0);
					responce.put("error", "Product Type Name and Short Name is already exist!");
					return responce;
				}
			}
			
			//save Product Type  Data 
			CProductTypeModel responseProductTypeModel = iProductTypeRepository.save(jsonModel);
			
			//Save General entry for Product Category 1
			CProductCategory1Model cProductCategory1Model = new CProductCategory1Model();
			cProductCategory1Model.setCompany_id(company_id);
			cProductCategory1Model.setProduct_type_id(responseProductTypeModel.getProduct_type_id());	
			cProductCategory1Model.setProduct_category1_name("General");	
			CProductCategory1Model respProdCategory1 = iProductCategory1Repository.save(cProductCategory1Model); 
			
	 
//			Save General entry for Product Category 2
			CProductCategory2Model cProductCategory2Model = new CProductCategory2Model();
			cProductCategory2Model.setCompany_id(company_id);
			cProductCategory2Model.setProduct_type_id(respProdCategory1.getProduct_type_id());	
			cProductCategory2Model.setProduct_category1_id(respProdCategory1.getProduct_category1_id());	
			cProductCategory2Model.setProduct_category2_name("General");	
			CProductCategory2Model respProductCategory2 =	iProductCategory2Repository.save(cProductCategory2Model);
		
			
//			Save General entry for Product Category 3
			CProductCategory3Model cProductCategory3Model = new CProductCategory3Model();
			cProductCategory3Model.setCompany_id(company_id);	
			cProductCategory3Model.setProduct_type_id(respProductCategory2.getProduct_type_id());	
			cProductCategory3Model.setProduct_category1_id(respProductCategory2.getProduct_category1_id());	
			cProductCategory3Model.setProduct_category2_id(respProductCategory2.getProduct_category2_id());	
			cProductCategory3Model.setProduct_category3_name("General");	
			CProductCategory3Model respProductCategory3 = iProductCategory3Repository.save(cProductCategory3Model);		
			
//			Save General entry for Product Category 4
			CProductCategory4Model cProductCategory4Model = new CProductCategory4Model();
			cProductCategory4Model.setCompany_id(company_id);
			cProductCategory4Model.setProduct_type_id(respProductCategory3.getProduct_type_id());	
			cProductCategory4Model.setProduct_category1_id(respProductCategory3.getProduct_category1_id());	
			cProductCategory4Model.setProduct_category2_id(respProductCategory3.getProduct_category2_id());
			cProductCategory4Model.setProduct_category3_id(respProductCategory3.getProduct_category3_id());		
			cProductCategory4Model.setProduct_category4_name("General");	
			CProductCategory4Model respProductCategory4 = iProductCategory4Repository.save(cProductCategory4Model);
			
			
//			Save General entry for Product Category 5
			CProductCategory5Model cProductCategory5Model = new CProductCategory5Model();
			cProductCategory5Model.setCompany_id(company_id);	
			cProductCategory5Model.setProduct_type_id(respProductCategory4.getProduct_type_id());	
			cProductCategory5Model.setProduct_category1_id(respProductCategory4.getProduct_category1_id());	
			cProductCategory5Model.setProduct_category2_id(product_type_id);		
			cProductCategory5Model.setProduct_category2_id(respProductCategory4.getProduct_category2_id());
			cProductCategory5Model.setProduct_category3_id(respProductCategory4.getProduct_category3_id());	
			cProductCategory5Model.setProduct_category4_id(respProductCategory4.getProduct_category4_id());	
			cProductCategory5Model.setProduct_category5_name("General");	
			iProductCategory5Repository.save(cProductCategory5Model);	
			
			
			List<CSmProductTypeGroupParametersModel> cSmProductTypeGroupParametersModel = objectMapper.readValue(
					ProductTypeGroupParameterArray.toString(),
					new TypeReference<List<CSmProductTypeGroupParametersModel>>() {
					});
			
			//Get responce of  Product Type Group Parameters 
			AtomicReference<List<CSmProductTypeGroupParametersModel>> responseProductTypeGroupParametersModel = new AtomicReference<>();

			// Get Product Type Group Parameter Data and save it
			if (!ProductTypeGroupParameterArray.isEmpty()) {
				
				//update  Product Type Group Parameter Data 
//				iSmProductTypeGroupParametersRepository.updateProductTypeGroupParametersRecord(company_id,product_type_id);

			    // Update Product Type Group Parameter Data if needed
			    if (updateProductTypeGroupParams) {
			        iSmProductTypeGroupParametersRepository.updateProductTypeGroupParametersRecord(company_id, product_type_id);
			    }
				
				cSmProductTypeGroupParametersModel.forEach(items -> {
					items.setProduct_type_id(responseProductTypeModel.getProduct_type_id());
					items.setProduct_type_group(responseProductTypeModel.getProduct_type_group());
					items.setModified_by(responseProductTypeModel.getCreated_by());
					items.setCreated_by(responseProductTypeModel.getCreated_by());

				});

				//save Product Type Group Parameters 
				responseProductTypeGroupParametersModel.set(iSmProductTypeGroupParametersRepository.saveAll(cSmProductTypeGroupParametersModel));
			}
			
			
			// Update Product Type Group Parameters Value Record if needed
			if (updateProductTypeGroupParams) {
			    iSmProductTypeGroupParametersValueRepository.updateProductTypeGroupParametersValueRecord(company_id, product_type_id);
			}

			// Process Product Type Group Parameters Values Data
			List<CSmProductTypeGroupParametersValuesModel> addProductTypeGroupParametersValues = new ArrayList<>();
			
			if (!ProductTypeGroupParameterValueArray.isEmpty()) {
			    List<CSmProductTypeGroupParametersValuesModel> ptGroupParametersValues = objectMapper.readValue(
			        ProductTypeGroupParameterValueArray.toString(),
			        new TypeReference<List<CSmProductTypeGroupParametersValuesModel>>() {});

			    ptGroupParametersValues.forEach(groupItem -> {
			        // Find the corresponding group in the previously fetched parameters
			        Optional<CSmProductTypeGroupParametersModel> findGroupNameObj = responseProductTypeGroupParametersModel.get()
			            .stream()
			            .filter(item -> item.getProduct_type_group_parameters_name().equals(groupItem.getGroup_name()))
			            .findFirst();

			        // If group found, create and save new entry for each value
			        if (findGroupNameObj.isPresent()) {
			            CSmProductTypeGroupParametersModel foundObject = findGroupNameObj.get();
			            CSmProductTypeGroupParametersValuesModel csmProductTypeGroupParametersValuesModel = new CSmProductTypeGroupParametersValuesModel();
			            csmProductTypeGroupParametersValuesModel.setProduct_type_group_parameters_values_id(0);
			            csmProductTypeGroupParametersValuesModel.setProduct_type_group_parameters_id(foundObject.getProduct_type_group_parameters_id());
			            csmProductTypeGroupParametersValuesModel.setProduct_type_id(responseProductTypeModel.getProduct_type_id());
			            csmProductTypeGroupParametersValuesModel.setProduct_type_group(responseProductTypeModel.getProduct_type_group());
			            csmProductTypeGroupParametersValuesModel.setProduct_type_group_parameters_values_code(groupItem.getProduct_type_group_parameters_values_code());
			            csmProductTypeGroupParametersValuesModel.setProduct_type_group_parameters_values_name(groupItem.getProduct_type_group_parameters_values_name());
			            csmProductTypeGroupParametersValuesModel.setCompany_id(company_id);
			            csmProductTypeGroupParametersValuesModel.setCreated_by(groupItem.getCreated_by());
			            csmProductTypeGroupParametersValuesModel.setModified_by(groupItem.getCreated_by());
			            csmProductTypeGroupParametersValuesModel.setRemark(groupItem.getRemark());
			            addProductTypeGroupParametersValues.add(csmProductTypeGroupParametersValuesModel);
			        }
			    });

			    // Save Product Type Group Parameters Values Details
			    iSmProductTypeGroupParametersValueRepository.saveAll(addProductTypeGroupParametersValues);
			}

			responce.put("success", "1");
			responce.put("data", responseProductTypeModel);
			responce.put("error", "");
			responce.put("message",
					atomicproductId.get() == 0 ? "Record added successfully!" : "Record update successfully!");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

			return responce;
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int product_type_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

	        // Delete Product Type details record
			iProductTypeRepository.FnDeleteRecord(product_type_id, deleted_by);
			
	        // Delete Product Type Group Parameters records
			iSmProductTypeGroupParametersRepository.FnDeleteProductTypeGroupParametersRecord(product_type_id,
					deleted_by);
			
	        //Delete Product Type Group Parameters Values records
			iSmProductTypeGroupParametersValueRepository.FnDeleteProductTypeGroupParametersValueRecord(product_type_id,
					deleted_by);
			
			responce.put("success", 1);


		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}
	
	
	@Override
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(int product_type_id, int company_id) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        // Fetch Product Type details record
	        CProductTypeViewModel productTypeDetailsRecord = iProductTypeViewRepository.FnShowParticularRecord(product_type_id, company_id);

	        // Fetch Product Type Group Parameters records
	        List<CSmProductTypeGroupParametersViewModel> productTypeGroupParametersRecord = iSmProductTypeGroupParametersViewRepository.FnShowProductTypeGroupParametersRecordForUpdate(product_type_id, company_id);

	        // Fetch Product Type Group Parameters Values records
	        List<CSmProductTypeGroupParametersValueViewModel> productTypeGroupParametersValuesRecord = iSmProductTypeGroupParametersValueViewRepository.FnShowProductTypeGroupParametersValueRecordForUpdate(product_type_id, company_id);

	        // Populate response map
	        response.put("ProductTypeDetailsRecord", productTypeDetailsRecord);
	        response.put("ProductTypeGroupParametersRecord", productTypeGroupParametersRecord);
	        response.put("ProductTypeGroupParametersValuesRecord", productTypeGroupParametersValuesRecord);
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


//	@Override
//	public Object FnShowAllReportRecords(Pageable pageable) {
//		JSONObject resp = new JSONObject();
//		try {
//
//			Page<List<Map<String, Object>>> data = iProductTypeViewRepository.FnShowAllReportRecords(pageable);
//			System.out.println(data);
//			resp.put("success", 1);
//			resp.put("error", "");
//
//			return new Object[] { data, resp };
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", 0);
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			resp.put("success", 0);
//			resp.put("error", "");
//			e.printStackTrace();
//		}
//
//		return new Object[] { "", resp };
//	}

//	@Override
//	public JSONObject FnAddUpdateRecord(CProductTypeModel cProductTypeModel) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		CProductTypeModel MyModel = null;
//		int company_id = cProductTypeModel.getCompany_id();
//		try {
//			Optional<CProductTypeModel> option = iProductTypeRepository
//					.findById(cProductTypeModel.getProduct_type_id());
//
//			if (option.isPresent()) {
//				CProductTypeModel mymodel = iProductTypeRepository.save(cProductTypeModel);
//				String json = objectMapper.writeValueAsString(mymodel);
//				resp.put("success", "1");
//				resp.put("data", json);
//				resp.put("error", "");
//				resp.put("message", "Record updated succesfully!...");
//				System.out.println("Product Type updated successfully!..");
//				return resp;
//			} else {
//
//				//Check similar Product Type Name and short name is exist or not
//				CProductTypeModel resultsProductTypeName = null;
//				if (cProductTypeModel.getProduct_type_short_name() == null || cProductTypeModel.getProduct_type_short_name().isEmpty()) {
//					resultsProductTypeName = iProductTypeRepository.getCheck(cProductTypeModel.getProduct_type_name(),
//							null, cProductTypeModel.getCompany_id());
//				} else {
//					resultsProductTypeName = iProductTypeRepository.getCheck(cProductTypeModel.getProduct_type_name(),
//							cProductTypeModel.getProduct_type_short_name(),cProductTypeModel.getCompany_id());
//				}
//
//				if (resultsProductTypeName != null) {
//					resp.put("success", 0);
//					resp.put("error", "Product Type Name and Short Name is already exist!");
//					return resp;
//				}else {
//
//					CProductTypeModel ProductTypeModel = iProductTypeRepository.save(cProductTypeModel);
//					String json = objectMapper.writeValueAsString(ProductTypeModel);
//					resp.put("success", "1");
//					resp.put("data", json);
//					resp.put("error", "");
//					resp.put("message", "Record added succesfully!...");
//					System.out.println(" Product Type saved succesfully!..");
//					return resp;
//				}
//			}
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord",
//						sqlEx.getErrorCode(), sqlEx.getMessage());
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord", 0,
//					e.getMessage());
//			resp.put("success", "0");
//			resp.put("data", "");
//			resp.put("error", e.getMessage());
//
//			return resp;
//		}
//		return resp;
//
//	}

//	@Override
//	public Object FnShowAllRecords(Pageable pageable) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			Page<CProductTypeViewModel> data = iProductTypeViewRepository.FnShowAllRecords(pageable);
//			String json = objectMapper.writeValueAsString(data);
//			resp.put("data", json);
//			resp.put("success", "1");
//			resp.put("error", "");
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			resp.put("success", "0");
//			resp.put("error", "");
//			e.printStackTrace();
//		}
//
//		return resp;
//
//	}

//	@Override
//	public Object FnShowAllActiveRecords(Pageable pageable) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//			Page<CProductTypeViewModel> data = iProductTypeViewRepository.FnShowAllActiveRecords(pageable);
//			String json = objectMapper.writeValueAsString(data);
//			resp.put("data", json);
//			resp.put("success", "1");
//			resp.put("error", "");
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			resp.put("success", "0");
//			resp.put("error", "");
//			e.printStackTrace();
//		}
//
//		return resp;
//
//	}



//	@Override
//	public JSONObject FnShowParticularRecordForUpdate(int product_type_id) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//
//			CProductTypeViewModel json = iProductTypeViewRepository.FnShowParticularRecordForUpdate(product_type_id);
//			String json1 = objectMapper.writeValueAsString(json);
//			resp.put("success", "1");
//			resp.put("data", json1);
//			resp.put("error", "");
//
//			return resp;
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return resp;
//	}

//	@Override
//	public JSONObject FnShowParticularRecord(int company_id, int product_type_id) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		try {
//
//			CProductTypeViewModel json = iProductTypeViewRepository.FnShowParticularRecord(company_id, product_type_id);
//			String json1 = objectMapper.writeValueAsString(json);
//
//			resp.put("success", "1");
//			resp.put("data", json1);
//			resp.put("error", "");
//
//			return resp;
//
//		} catch (DataAccessException e) {
//			if (e.getRootCause() instanceof SQLException) {
//				SQLException sqlEx = (SQLException) e.getRootCause();
//				System.out.println(sqlEx.getMessage());
//				resp.put("success", "0");
//				resp.put("data", "");
//				resp.put("error", sqlEx.getMessage());
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return resp;
//	}

//	@Override
//	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
//		JSONObject resp = new JSONObject();
//		ObjectMapper objectMapper = new ObjectMapper();
//		CProductTypeModel MyModel = null;
//		int company_id = cProductTypeModel.getCompany_id();
//		try {
//			Optional<CProductTypeModel> option = iProductTypeRepository
//					.findById(cProductTypeModel.getProduct_type_id());
//
//			if (option.isPresent()) {
//				CProductTypeModel mymodel = iProductTypeRepository.save(cProductTypeModel);
//				String json = objectMapper.writeValueAsString(mymodel);
//				resp.put("success", "1");
//				resp.put("data", json);
//				resp.put("error", "");
//				resp.put("message", "Record updated succesfully!...");
//				System.out.println("Product Type updated successfully!..");
//				return resp;
//			} else {
//
//				//Check similar Product Type Name and short name is exist or not
//				CProductTypeModel resultsProductTypeName = null;
//				if (cProductTypeModel.getProduct_type_short_name() == null || cProductTypeModel.getProduct_type_short_name().isEmpty()) {
//					resultsProductTypeName = iProductTypeRepository.getCheck(cProductTypeModel.getProduct_type_name(),
//							null, cProductTypeModel.getCompany_id());
//				} else {
//					resultsProductTypeName = iProductTypeRepository.getCheck(cProductTypeModel.getProduct_type_name(),
//							cProductTypeModel.getProduct_type_short_name(),cProductTypeModel.getCompany_id());
//				}
//
//				if (resultsProductTypeName != null) {
//					resp.put("success", 0);
//					resp.put("error", "Product Type Name and Short Name is already exist!");
//					return resp;
//				}else {
//
//					CProductTypeModel ProductTypeModel = iProductTypeRepository.save(cProductTypeModel);
//					String json = objectMapper.writeValueAsString(ProductTypeModel);
//					resp.put("success", "1");
//					resp.put("data", json);
//					resp.put("error", "");
//					resp.put("message", "Record added succesfully!...");
//					System.out.println(" Product Type saved succesfully!..");
//					return resp;
//				}
//	return resp;

//			}

}
