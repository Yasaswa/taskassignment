package com.erp.XmProductionSection.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmProductionSection.Model.CXmProductionSectionModel;
import com.erp.XmProductionSection.Model.CXmProductionSubSectionGodownMappingModel;
import com.erp.XmProductionSection.Model.CXmProductionSubSectionGodownMappingViewModel;
import com.erp.XmProductionSection.Model.CXmProductionSubSectionModel;
import com.erp.XmProductionSection.Repository.IXmProductionSectionRepository;
import com.erp.XmProductionSection.Repository.IXmProductionSectionViewRepository;
import com.erp.XmProductionSection.Repository.IXmProductionSubSectionGodownMappingRepository;
import com.erp.XmProductionSection.Repository.IXmProductionSubSectionGodownMappingViewRepository;
import com.erp.XmProductionSection.Repository.IXmProductionSubSectionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CXmProductionSectionServiceImpl implements IXmProductionSectionService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmProductionSectionRepository iXmProductionSectionRepository;

	@Autowired
	IXmProductionSectionViewRepository iXmProductionSectionViewRepository;

	@Autowired
	IXmProductionSubSectionRepository iXmProductionSubSectionRepository;
	
	@Autowired
	IXmProductionSubSectionGodownMappingRepository iXmProductionSubSectionGodownMappingRepository;
	
	@Autowired
	IXmProductionSubSectionGodownMappingViewRepository iXmProductionSubSectionGodownMappingViewRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateProdSectionRecord(CXmProductionSectionModel xmProductionSectionModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CXmProductionSectionModel> option = iXmProductionSectionRepository
				.findById(xmProductionSectionModel.getProduction_section_id());
		int company_id = xmProductionSectionModel.getCompany_id();
		try {
			if (option.isPresent()) {
				xmProductionSectionModel.setModified_on(new Date());
				CXmProductionSectionModel cxmProductionSectionModel = iXmProductionSectionRepository
						.save(xmProductionSectionModel);
				responce.put("success", 1);
				responce.put("data", cxmProductionSectionModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" Production Section Updated Successfully!..");

			} else {

				CXmProductionSectionModel resultsProdSectionName = null;
				if (xmProductionSectionModel.getProduction_section_name() == null || xmProductionSectionModel.getProduction_section_name().isEmpty()) {
					resultsProdSectionName = iXmProductionSectionRepository
							.getCheck(xmProductionSectionModel.getProduction_section_name(), null, xmProductionSectionModel.getCompany_id());
				} else {
					resultsProdSectionName = iXmProductionSectionRepository.getCheck(
							xmProductionSectionModel.getProduction_section_name(),
							xmProductionSectionModel.getProduction_section_short_name(), xmProductionSectionModel.getCompany_id());
				}

				if (resultsProdSectionName != null) {
					responce.put("success", 0);
					responce.put("error", "Product Section Name or Short Name is already exist!");
					return responce;
				} else {

					CXmProductionSectionModel respContent = iXmProductionSectionRepository
							.save(xmProductionSectionModel);

					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XmProductionSection/FnAddUpdateProdSectionRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XmProductionSection/FnAddUpdateProdSectionRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}
	
	
	@Override
	public Map<String, Object> FnDeleteProdSectionRecord(int production_section_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iXmProductionSectionRepository.FnDeleteProductionSectionRecord(production_section_id, deleted_by);
			responce.put("success", 1);

		} catch (Exception e) {

			responce.put("success", 0);
			responce.put("error", e.getMessage());
		}
		return responce;
	}
	
	
	@Override
	public Map<String, Object> FnShowParticularProdSectionRecordForUpdate(int production_section_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			CXmProductionSectionModel productionSectionModel = iXmProductionSectionRepository
					.FnShowProductionSectionRecordForUpdate(company_id, production_section_id);

			responce.put("ProductionSectionData", productionSectionModel);
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

	/* ****************************************************Production Sub Section API Start************************************************ */
	
	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateProdSubSectionAndGodownMappingRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int  production_sub_section_id = commonIdsObj.getInt("production_sub_section_id");

		AtomicInteger atomicProdSubSecId = new AtomicInteger(production_sub_section_id); 
		
		JSONObject masterjson = jsonObject.getJSONObject("ProductionSubSectionJson");
		JSONArray godownMappingArray = (JSONArray) jsonObject.get("GodownMappingData");
		
		try {
			
			//  JSON data represented by the `masterjson` object into an instance of CXmProductionSubSectionModel class
			CXmProductionSubSectionModel jsonModel =  objectMapper.readValue(masterjson.toString(), CXmProductionSubSectionModel.class);
			
			if(jsonModel.getProduction_sub_section_id() == 0) {
			
				CXmProductionSectionModel resultsProdSubSectionName = null;
				if (jsonModel.getProduction_sub_section_name() == null || jsonModel.getProduction_sub_section_name().isEmpty()) {
					resultsProdSubSectionName = iXmProductionSubSectionRepository
							.getCheck(jsonModel.getProduction_sub_section_name(), null, jsonModel.getCompany_id());
				} else {
					resultsProdSubSectionName = iXmProductionSubSectionRepository.getCheck(jsonModel.getProduction_sub_section_name(),
							jsonModel.getProduction_sub_section_short_name(), jsonModel.getCompany_id());
				}

				if (resultsProdSubSectionName != null) {
					responce.put("success", 0);
					responce.put("error", "Product Sub Section Name or Short Name is already exist!");
					return responce;
				}
				
			}
			
			//Save And Update Production Sub Section Records 
			CXmProductionSubSectionModel responceProductionSubSectionModel = iXmProductionSubSectionRepository.save(jsonModel);
			atomicProdSubSecId.set(responceProductionSubSectionModel.getProduction_sub_section_id());
			
			//update Production Sub-Section Godown 
			iXmProductionSubSectionGodownMappingRepository.updateProductionSubSectionGodownMapping(responceProductionSubSectionModel.getProduction_sub_section_id(),company_id);
			
			// JSON array into a list of CXmProductionSubSectionGodownMappingModel objects using an ObjectMapper
			List<CXmProductionSubSectionGodownMappingModel> productionSubSectionGodownMappingModel  = objectMapper.readValue(godownMappingArray.toString(),
					new TypeReference<List<CXmProductionSubSectionGodownMappingModel>>() {
					});
			
			// Iterate over each CXmProductionSubSectionGodownMappingModel object in the list
			productionSubSectionGodownMappingModel.forEach(items -> {
			    // Set production_sub_section_id and production_section_id for each item from responceProductionSubSectionModel
				items.setProduction_sub_section_id(responceProductionSubSectionModel.getProduction_sub_section_id());
				items.setProduction_section_id(responceProductionSubSectionModel.getProduction_section_id());
				items.setParent_department_id(responceProductionSubSectionModel.getParent_department_id());
			});	
			
			// Save all updated CXmProductionSubSectionGodownMappingModel objects to the repository
			iXmProductionSubSectionGodownMappingRepository.saveAll(productionSubSectionGodownMappingModel);

			responce.put("data", responceProductionSubSectionModel);
			responce.put("success", 1);
			responce.put("message", production_sub_section_id == 0 ? "Record added successfully!..." : "Record update successfully!...");

			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XmProductionSection/FnAddUpdateProdSubSectionRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XmProductionSection/FnAddUpdateProdSubSectionRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		
		return responce;
	}
	
	
	
	@Override
	public Map<String, Object> FnDeleteProdSubSectionRecord(int production_sub_section_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			//Delete Production Sub Section Record 
			iXmProductionSubSectionRepository.FnDeleteProductionSubSectionRecord(production_sub_section_id, deleted_by);
			
			//Delete Production Sub Section Godown Mapping 
			iXmProductionSubSectionGodownMappingRepository.FnDeleteProductionSubSectionGodownMappingRecord(production_sub_section_id,deleted_by);

			responce.put("success", 1);

		} catch (Exception e) {

			responce.put("success", 0);
			responce.put("error", e.getMessage());
		}
		return responce;
	}
	
	
	@Override
	public Map<String, Object> FnShowParticularProdSubSectionRecordForUpdate(int production_sub_section_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//fetch Production Sub Section Details
			CXmProductionSubSectionModel productionSubSectionModel = iXmProductionSubSectionRepository
					.FnShowProductionSubSectionRecordForUpdate(company_id, production_sub_section_id);

			//fetch Production Sub SectionGodown Mapping Records
			List<CXmProductionSubSectionGodownMappingViewModel>  productionSubSectionGodownMapppingData = 
					iXmProductionSubSectionGodownMappingViewRepository.FnShowProdGodownMappingRecordForUpdate(production_sub_section_id, company_id);
			
			responce.put("productionSubSectionData", productionSubSectionModel);
			responce.put("ProductionSubSectionGodownMapppingData", productionSubSectionGodownMapppingData);
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
	

}
