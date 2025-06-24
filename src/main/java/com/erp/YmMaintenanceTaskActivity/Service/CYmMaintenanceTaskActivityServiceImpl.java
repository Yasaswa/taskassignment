package com.erp.YmMaintenanceTaskActivity.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityModel;
import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityViewModel;
import com.erp.YmMaintenanceTaskActivity.Repository.IYmMaintenanceTaskActivityRepository;
import com.erp.YmMaintenanceTaskActivity.Repository.IYmMaintenanceTaskActivityRptRepository;
import com.erp.YmMaintenanceTaskActivity.Repository.IYmMaintenanceTaskActivityViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CYmMaintenanceTaskActivityServiceImpl implements IYmMaintenanceTaskActivityService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IYmMaintenanceTaskActivityRepository iYmMaintenanceTaskActivityRepository;

	@Autowired
	IYmMaintenanceTaskActivityViewRepository iYmMaintenanceTaskActivityViewRepository;

	@Autowired
	IYmMaintenanceTaskActivityRptRepository iYmMaintenanceTaskActivityRptRepository;


	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");

		int company_id = commonIdsObj.getInt("company_id");
		int production_department_id = commonIdsObj.getInt("production_department_id");
		int production_sub_department_id = commonIdsObj.getInt("production_sub_department_id");

		try {
			JSONArray array = (JSONArray) jsonObject.get("MaintenanceTaskJsons");

			Object obj = iYmMaintenanceTaskActivityRepository.updateMaintenanceTaskActivity(production_department_id, production_sub_department_id, company_id);

			List<CYmMaintenanceTaskActivityModel> maintenanceTaskActivityModel = objectMapper.readValue(array.toString(), new TypeReference<List<CYmMaintenanceTaskActivityModel>>() {
			});

			iYmMaintenanceTaskActivityRepository.saveAll(maintenanceTaskActivityModel);

			resp.put("success", "1");
			resp.put("message", "Records added succesfully!...");
			resp.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/YmMaintenanceTaskActivity/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/YmMaintenanceTaskActivity/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}
		return resp;
	}


	@Override
	public Object FnDeleteRecord(int maintenance_task_activity_id, int company_id, String deleted_by) {
		Optional<CYmMaintenanceTaskActivityModel> option = iYmMaintenanceTaskActivityRepository.findById(maintenance_task_activity_id);
		CYmMaintenanceTaskActivityModel cYmMaintenanceTaskActivityModel = new CYmMaintenanceTaskActivityModel();
		if (option.isPresent()) {
			cYmMaintenanceTaskActivityModel = option.get();
			cYmMaintenanceTaskActivityModel.setIs_delete(true);
			cYmMaintenanceTaskActivityModel.setDeleted_on(new Date());
			cYmMaintenanceTaskActivityModel.setDeleted_by(deleted_by);
			iYmMaintenanceTaskActivityRepository.save(cYmMaintenanceTaskActivityModel);
		}
		return cYmMaintenanceTaskActivityModel;
	}

	@Override
	public Page<CYmMaintenanceTaskActivityViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iYmMaintenanceTaskActivityViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int maintenance_task_activity_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CYmMaintenanceTaskActivityModel cYmMaintenanceTaskActivityModel = null;
		try {
			cYmMaintenanceTaskActivityModel = iYmMaintenanceTaskActivityRepository
					.FnShowParticularRecordForUpdate(maintenance_task_activity_id, company_id);
			responce.put("success", "1");
			responce.put("data", cYmMaintenanceTaskActivityModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

	@Override
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iYmMaintenanceTaskActivityViewRepository.FnShowAllReportRecords(pageable);
	}


	@Override
	public Page<CYmMaintenanceTaskActivityModel> FnShowParticularRecord(int production_department_id, int production_sub_department_id, int company_id, Pageable pageable) {
		return iYmMaintenanceTaskActivityRepository.FnShowParticularRecord(production_department_id, production_sub_department_id, company_id, pageable);
	}

//	@Override
//	public Page<CYmMaintenanceTaskActivityViewModel> FnShowParticularRecord(int maintenance_task_activity_id,
//			int company_id, Pageable pageable) {
//		return iYmMaintenanceTaskActivityViewRepository.FnShowParticularRecord(maintenance_task_activity_id, company_id, pageable);
//	}


}
