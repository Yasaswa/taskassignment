package com.erp.MtServicePlanningMaster.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningActivitiesModel;
import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningActivitiesViewModel;
import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningDetailsModel;
import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningDetailsViewModel;
import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningMasterModel;
import com.erp.MtServicePlanningMaster.Repository.IMtServicePlanningActivitiesRepository;
import com.erp.MtServicePlanningMaster.Repository.IMtServicePlanningActivitiesViewRepository;
import com.erp.MtServicePlanningMaster.Repository.IMtServicePlanningDetailsRepository;
import com.erp.MtServicePlanningMaster.Repository.IMtServicePlanningDetailsViewRepository;
import com.erp.MtServicePlanningMaster.Repository.IMtServicePlanningMasterRepository;
import com.erp.MtServicePlanningMaster.Repository.IMtServicePlanningMasterViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CMtServicePlanningMasterServiceImpl implements IMtServicePlanningMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtServicePlanningMasterRepository iMtServicePlanningMasterRepository;

	@Autowired
	IMtServicePlanningMasterViewRepository iMtServicePlanningMasterViewRepository;
	
	@Autowired
	IMtServicePlanningActivitiesRepository iMtServicePlanningActivitiesRepository;
	
	@Autowired
	IMtServicePlanningDetailsRepository iMtServicePlanningDetailsRepository;
	
	@Autowired
	IMtServicePlanningActivitiesViewRepository iMtServicePlanningActivitiesViewRepository;
	
	@Autowired
	IMtServicePlanningDetailsViewRepository iMtServicePlanningDetailsViewRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int service_planning_master_transaction_id = commonIdsObj.getInt("service_planning_master_transaction_id");

		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
		JSONArray activitiesArray = (JSONArray) jsonObject.get("TransActivitiesData");

		try {

			CMtServicePlanningMasterModel cMtServicePlanningMasterModel = objectMapper.readValue(masterjson.toString(),
					CMtServicePlanningMasterModel.class);

			//save cMtServicePlanningMasterModel
			CMtServicePlanningMasterModel respServicePlanningMasterModelRecord = iMtServicePlanningMasterRepository.save(cMtServicePlanningMasterModel);
			
			// Service Planning Details Array
			if(!detailsArray.isEmpty()) {
				List<CMtServicePlanningDetailsModel> cMtServicePlanningDetailsModel = objectMapper
						.readValue(detailsArray.toString(),new TypeReference<List<CMtServicePlanningDetailsModel>>() {});

				cMtServicePlanningDetailsModel.forEach(detailsRec -> {
					detailsRec.setService_planning_master_transaction_id(respServicePlanningMasterModelRecord.getService_planning_master_transaction_id());

				});
				
				//saved cXtWeavingProductionWarpingDetailsModel
				iMtServicePlanningDetailsRepository.saveAll(cMtServicePlanningDetailsModel);
			}
			
			// Service Planning  Activities Array
			if(!activitiesArray.isEmpty()) {
				List<CMtServicePlanningActivitiesModel> cMtServicePlanningActivitiesModel = objectMapper
						.readValue(activitiesArray.toString(),new TypeReference<List<CMtServicePlanningActivitiesModel>>() {});

				cMtServicePlanningActivitiesModel.forEach(activitiesDetail -> {
					activitiesDetail.setService_planning_master_transaction_id(respServicePlanningMasterModelRecord.getService_planning_master_transaction_id());

				});
				
				//saved cMtServicePlanningActivitiesModel
				iMtServicePlanningActivitiesRepository.saveAll(cMtServicePlanningActivitiesModel);
			}
			
			responce.put("success", 1);
			responce.put("data", respServicePlanningMasterModelRecord);
			responce.put("error", "");
			responce.put("message", service_planning_master_transaction_id == 0 ? "Record added successfully!...":"Record update successfully!");


		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtServicePlanningMaster/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success",0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtServicePlanningMaster/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int service_planning_master_transaction_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Delete Service Planning Master Record
			iMtServicePlanningMasterRepository.FnDeleteSrPlanningMasterRecord(service_planning_master_transaction_id,deleted_by);
			
			//Delete Service Planning Details Record
			iMtServicePlanningDetailsRepository.FnDeleteSrPlanningDetailsRecord(service_planning_master_transaction_id,deleted_by);
			
			//Delete Service Planning Activities Record
			iMtServicePlanningActivitiesRepository.FnDeleteSrPlanningActivitiesRecord(service_planning_master_transaction_id,deleted_by);
			
			responce.put("success", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			
		}
		return responce;	
	}
	
	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int service_planning_master_transaction_id,
			int company_id) {
		Map<String, Object> responce = new HashMap<>();
		
		try {
			
			//Fetch Service Planning Master Records
			CMtServicePlanningMasterModel servicePlanningMasterData = 
					iMtServicePlanningMasterRepository.FnShowSrPlanningMasterRecordForUpdate(service_planning_master_transaction_id, company_id);
			
			//Fetch Service Planning Details Records
			List<CMtServicePlanningDetailsViewModel> servicePlanningDetailsData = iMtServicePlanningDetailsViewRepository.FnShowSrPlanningDetailsRecordForUpdate(service_planning_master_transaction_id,company_id);
			
			//Fetch Service Planning Activities Records
			List<CMtServicePlanningActivitiesViewModel> servicePlanningActivitiesData = iMtServicePlanningActivitiesViewRepository.FnShowSrPlanningActivitiesRecordForUpdate(service_planning_master_transaction_id,company_id);

			responce.put("ServicePlanningMasterData", servicePlanningMasterData);
			responce.put("ServicePlanningDetailsData", servicePlanningDetailsData);
			responce.put("ServicePlanningActivitiesData", servicePlanningActivitiesData);
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
