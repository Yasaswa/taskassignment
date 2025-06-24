package com.erp.YmMaintenanceDetails.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.YmMaintenanceDetails.Model.CYmMaintenanceTaskDetailsModel;
import com.erp.YmMaintenanceDetails.Model.CYmMaintenanceTaskDetailsViewModel;
import com.erp.YmMaintenanceDetails.Model.CYmMaintenanceTaskMasterModel;
import com.erp.YmMaintenanceDetails.Model.CYmMaintenanceTaskMasterViewModel;
import com.erp.YmMaintenanceDetails.Repository.IYmMaintenanceTaskDetailsRepository;
import com.erp.YmMaintenanceDetails.Repository.IYmMaintenanceTaskDetailsViewRepository;
import com.erp.YmMaintenanceDetails.Repository.IYmMaintenanceTaskMasterRepository;
import com.erp.YmMaintenanceDetails.Repository.IYmMaintenanceTaskMasterViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CYmMaintenanceDetailsServiceImpl implements IYmMaintenanceDetailsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IYmMaintenanceTaskMasterRepository iYmMaintenanceTaskMasterRepository;

	@Autowired
	IYmMaintenanceTaskDetailsRepository iYmMaintenanceTaskDetailsRepository;

	@Autowired
	IYmMaintenanceTaskMasterViewRepository iYmMaintenanceTaskMasterViewRepository;

	@Autowired
	IYmMaintenanceTaskDetailsViewRepository iYmMaintenanceTaskDetailsViewRepository;

	@Autowired
	private JdbcTemplate executeQuery;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		boolean update = false;

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		int maintenance_task_master_id = commonIdsObj.getInt("maintenance_task_master_id");

		JSONObject maintenanceMeterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray maintenanceDetailsArray = (JSONArray) jsonObject.get("TransDetailData");

		try {

			CYmMaintenanceTaskMasterModel jsonModel = objectMapper.readValue(maintenanceMeterjson.toString(),
					CYmMaintenanceTaskMasterModel.class);

//		   Maintenance Master

			CYmMaintenanceTaskMasterModel maintenanceTaskMasterModel = new CYmMaintenanceTaskMasterModel();

			String query = "Select * FROM ym_maintenance_task_master WHERE is_delete = 0 and maintenance_task_master_id = '"
					+ maintenance_task_master_id + "' and company_id = " + company_id + "";

			List<CYmMaintenanceTaskMasterModel> results = executeQuery.query(query,
					new BeanPropertyRowMapper<>(CYmMaintenanceTaskMasterModel.class));

			if (!results.isEmpty()) {
				update = true;
				maintenanceTaskMasterModel = results.get(0);
				maintenanceTaskMasterModel.setDeleted_on(new Date());
				maintenanceTaskMasterModel.setIs_delete(true);
				iYmMaintenanceTaskMasterRepository.save(maintenanceTaskMasterModel);
			}

			CYmMaintenanceTaskMasterModel responceMaintenanceTaskMasterModel = iYmMaintenanceTaskMasterRepository
					.save(jsonModel);

//		  Maintenance Details

			iYmMaintenanceTaskDetailsRepository.updateStatus(maintenance_task_master_id, company_id);

			List<CYmMaintenanceTaskDetailsModel> cymMaintenanceTaskDetailsModel = objectMapper.readValue(
					maintenanceDetailsArray.toString(), new TypeReference<List<CYmMaintenanceTaskDetailsModel>>() {

					});

			cymMaintenanceTaskDetailsModel.forEach(items -> {
				items.setMaintenance_task_master_id(responceMaintenanceTaskMasterModel.getMaintenance_task_master_id());
			});

			iYmMaintenanceTaskDetailsRepository.saveAll(cymMaintenanceTaskDetailsModel);

			responce.put("success", "1");
			responce.put("data", responceMaintenanceTaskMasterModel);
			responce.put("error", "");
			responce.put("message", update ? "Record updated successfully!" : "Record added successfully!");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/YmMaintenanceDetails/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/YmMaintenanceDetails/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int maintenance_task_master_id, String deleted_by, int company_id) {

		Map<String, Object> responce = new HashMap<>();

		try {
			iYmMaintenanceTaskMasterRepository.deleteMaintenanceMaster(maintenance_task_master_id, deleted_by,
					company_id);

			iYmMaintenanceTaskDetailsRepository.deleteMaintenanceDetails(maintenance_task_master_id, deleted_by,
					company_id);

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", "");
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllMaintenanceMasterAndDetailsRecords(int maintenance_task_master_id,
	                                                                       int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			CYmMaintenanceTaskMasterViewModel maintenanceMasterRecords = iYmMaintenanceTaskMasterViewRepository
					.FnShowMaintenanceMasterRecords(maintenance_task_master_id, company_id);

			List<CYmMaintenanceTaskDetailsViewModel> maintenanceDetailsRecords = iYmMaintenanceTaskDetailsViewRepository
					.FnShowMaintenanceDetailsRecords(maintenance_task_master_id, company_id);

			responce.put("MaintenanceMasterRecord", maintenanceMasterRecords);
			responce.put("MaintenanceDetailsRecord", maintenanceDetailsRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		String query;
		if ("summary".equals(reportType)) {
			query = "SELECT * FROM ymv_maintenance_task_master_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		} else {
			query = "SELECT * FROM ymv_maintenance_task_details_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		}
		return response;
	}
}
