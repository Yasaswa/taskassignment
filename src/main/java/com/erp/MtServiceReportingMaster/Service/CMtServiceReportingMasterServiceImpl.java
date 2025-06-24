package com.erp.MtServiceReportingMaster.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.MtServiceReportingMaster.Model.CMtServiceReportingActivitiesDetailsModel;
import com.erp.MtServiceReportingMaster.Model.CMtServiceReportingActivitiesDetailsViewModel;
import com.erp.MtServiceReportingMaster.Model.CMtServiceReportingMasterModel;
import com.erp.MtServiceReportingMaster.Model.CMtServiceReportingMasterViewModel;
import com.erp.MtServiceReportingMaster.Repository.IMtServiceReportingActivitiesDetailsRepository;
import com.erp.MtServiceReportingMaster.Repository.IMtServiceReportingActivitiesDetailsViewRepository;
import com.erp.MtServiceReportingMaster.Repository.IMtServiceReportingMasterRepository;
import com.erp.MtServiceReportingMaster.Repository.IMtServiceReportingMasterViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CMtServiceReportingMasterServiceImpl implements IMtServiceReportingMasterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IMtServiceReportingMasterRepository iMtServiceReportingMasterRepository;
	
	@Autowired
	IMtServiceReportingActivitiesDetailsRepository iMtServiceReportingActivitiesDetailsRepository;
	
	@Autowired
	IMtServiceReportingMasterViewRepository iMtServiceReportingMasterViewRepository;
	
	@Autowired
	IMtServiceReportingActivitiesDetailsViewRepository iMtServiceReportingActivitiesDetailsViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {

		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		// get CommonId's
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int  service_reporting_master_transaction_id = commonIdsObj.getInt("service_reporting_master_transaction_id");

		// get master data
		JSONObject masterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDetailData");
		
		String reporting_status = "";
		try {

			CMtServiceReportingMasterModel cMtServiceReportingMasterModel = 
					objectMapper.readValue(masterjson.toString(), CMtServiceReportingMasterModel.class);
			
//			save cMtServiceReportingMasterModel
			CMtServiceReportingMasterModel respServiceReportingMasterModel = iMtServiceReportingMasterRepository.save(cMtServiceReportingMasterModel);
			
			reporting_status =  respServiceReportingMasterModel.getReporting_status();
			
			if (!detailsArray.isEmpty()) {
				
		        // JSON array into a list of CMtServiceReportingActivitiesDetailsModel objects using an ObjectMapper
				List<CMtServiceReportingActivitiesDetailsModel> cMtServiceReportingActivitiesDetailsModels = 
						objectMapper.readValue(detailsArray.toString(),new TypeReference<List<CMtServiceReportingActivitiesDetailsModel>>() {});	
				
				
				cMtServiceReportingActivitiesDetailsModels.forEach(detailsRec ->{		
					detailsRec.setService_reporting_master_transaction_id(respServiceReportingMasterModel.getService_reporting_master_transaction_id());
					detailsRec.setCustomer_approved_date(new Date());
					detailsRec.setService_reporting_date(respServiceReportingMasterModel.getService_reporting_date());
					detailsRec.setService_reporting_code(respServiceReportingMasterModel.getService_reporting_code());

				});
				
//				save cMtServiceReportingActivitiesDetailsModels 
				iMtServiceReportingActivitiesDetailsRepository.saveAll(cMtServiceReportingActivitiesDetailsModels);
			}
			
			responce.put("success", 1);
			responce.put("data", respServiceReportingMasterModel);
			responce.put("error", "");
			responce.put("message",service_reporting_master_transaction_id == 0 ? "Record added successfully!...": "Record updated successfully!...");
//			responce.put("message",
//					reporting_status.equals("A") ? "Record Approved successfully!..."
//							: (reporting_status.equals("R") ? ""
//									: (service_reporting_master_transaction_id == 0 ? "Record added successfully!..."
//											: "Record updated successfully!...")));
			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtServiceReportingMaster/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/MtServiceReportingMaster/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int service_reporting_master_transaction_id, int company_id,
			String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Delete Service Reporting Master Record
			iMtServiceReportingMasterRepository.FnDeleteServiceReportingMasterRecord(service_reporting_master_transaction_id,company_id,deleted_by);
			
			//Delete Service Reporting Activities Details Record
			iMtServiceReportingActivitiesDetailsRepository.FnDeleteSrReportingActivitiesDetailsRecord(service_reporting_master_transaction_id,company_id,deleted_by);
			
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int service_reporting_master_transaction_id,	int company_id) {
		Map<String, Object> responce = new HashMap<>();
		
		try {
			
//			CMtServiceReportingMasterViewModel serviceReportingMasterData = iMtServiceReportingMasterViewRepository
//					.FnShowServiceReportingMasterRecordForUpdate(service_reporting_master_transaction_id, company_id);
			
//			List<CMtServiceReportingActivitiesDetailsViewModel> serviceReportingActivitiesDetailsData = 
//					iMtServiceReportingActivitiesDetailsViewRepository.FnShowServiceReportingActivitiesDetailsRecordForUpdate(service_reporting_master_transaction_id,company_id);
			
			
			//Fetch Service Reporting Master data for update 
			Map<String, Object> serviceReportingMasterData = iMtServiceReportingMasterViewRepository
					.FnShowServiceReportingMasterRecord(service_reporting_master_transaction_id, company_id);
			
			//Fetch Service Reporting Activities Details data for update 
			List<Map<String, Object>> serviceReportingActivitiesDetailsData = 
					iMtServiceReportingActivitiesDetailsViewRepository.FnShowServiceReportingActivitiesDetailRecordForUpdate(service_reporting_master_transaction_id,company_id);
			
			responce.put("ServiceReportingMasterData", serviceReportingMasterData);
			responce.put("ServiceReportingActivitiesDetailsData", serviceReportingActivitiesDetailsData);
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
