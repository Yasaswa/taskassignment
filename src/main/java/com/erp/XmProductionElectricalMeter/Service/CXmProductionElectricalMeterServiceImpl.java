package com.erp.XmProductionElectricalMeter.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.XmProductionElectricalMeter.Model.CXmProductionElectricalMeterMachineMappingModel;
import com.erp.XmProductionElectricalMeter.Model.CXmProductionElectricalMeterMachineMappingViewModel;
import com.erp.XmProductionElectricalMeter.Model.CXmProductionElectricalMeterModel;
import com.erp.XmProductionElectricalMeter.Model.CXmProductionElectricalMeterViewModel;
import com.erp.XmProductionElectricalMeter.Repository.IXmProductionElectricalMeterMachineMappingRepository;
import com.erp.XmProductionElectricalMeter.Repository.IXmProductionElectricalMeterMachineMappingViewRepository;
import com.erp.XmProductionElectricalMeter.Repository.IXmProductionElectricalMeterRepository;
import com.erp.XmProductionElectricalMeter.Repository.IXmProductionElectricalMeterViewRepository;
import com.erp.XmProductionSection.Model.CXmProductionSectionModel;
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
public class CXmProductionElectricalMeterServiceImpl implements IXmProductionElectricalMeterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IXmProductionElectricalMeterRepository iXmProductionElectricalMeterRepository;

	@Autowired
	IXmProductionElectricalMeterMachineMappingRepository iXmProductionElectricalMeterMachineMappingRepository;

	@Autowired
	IXmProductionElectricalMeterViewRepository iXmProductionElectricalMeterViewRepository;

	@Autowired
	IXmProductionElectricalMeterMachineMappingViewRepository iXmProductionElectricalMeterMachineMappingViewRepository;

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
		int prod_electrical_meter_id = commonIdsObj.getInt("prod_electrical_meter_id");

		JSONObject electricalMeterjson = jsonObject.getJSONObject("TransHeaderData");
		JSONArray meterMappingArray = (JSONArray) jsonObject.get("TransDetailData");

		try {

			CXmProductionElectricalMeterModel jsonModel = objectMapper.readValue(electricalMeterjson.toString(),
					CXmProductionElectricalMeterModel.class);

//		    production electrical meter

			CXmProductionElectricalMeterModel electricalMeterModel = new CXmProductionElectricalMeterModel();

			String query = "Select * FROM xm_production_electrical_meter WHERE is_delete = 0 and prod_electrical_meter_id = '"
					+ prod_electrical_meter_id + "' and company_id = " + company_id + "";

			List<CXmProductionElectricalMeterModel> results = executeQuery.query(query,
					new BeanPropertyRowMapper<>(CXmProductionElectricalMeterModel.class));

			if (!results.isEmpty()) {
				update = true;
				electricalMeterModel = results.get(0);
				electricalMeterModel.setDeleted_on(new Date());
				electricalMeterModel.setIs_delete(true);
				iXmProductionElectricalMeterRepository.save(electricalMeterModel);
			}
			if (jsonModel.getProd_electrical_meter_id() == 0) {
		
				//Check similar Production Electrical Meter Name and short name is exist or not
					CXmProductionElectricalMeterModel resultsProdElectricMeterName = null;
					if (jsonModel.getProd_electrical_meter_short_name() == null || jsonModel.getProd_electrical_meter_short_name().isEmpty()) {
						resultsProdElectricMeterName = iXmProductionElectricalMeterRepository
								.getCheck(jsonModel.getProd_electrical_meter_name(), null, jsonModel.getCompany_id());
					} else {
						resultsProdElectricMeterName = iXmProductionElectricalMeterRepository.getCheck(
								jsonModel.getProd_electrical_meter_name(),
								jsonModel.getProd_electrical_meter_short_name(), jsonModel.getCompany_id());
					}
					if (resultsProdElectricMeterName != null) {
						responce.put("success", 0);
						responce.put("error", "Product Section Name or Short Name is already exist!");
						return responce;
					}
			
			}
			
			CXmProductionElectricalMeterModel responceProductionElectricalMeter = iXmProductionElectricalMeterRepository
					.save(jsonModel);

//		   production electrical meter machine mapping

			iXmProductionElectricalMeterMachineMappingRepository.updateStatus(prod_electrical_meter_id, company_id);

			List<CXmProductionElectricalMeterMachineMappingModel> cxmProductionElectricalMeterMachineMappingModel = objectMapper
					.readValue(meterMappingArray.toString(),
							new TypeReference<List<CXmProductionElectricalMeterMachineMappingModel>>() {

							});

			cxmProductionElectricalMeterMachineMappingModel.forEach(items -> {
				items.setProd_electrical_meter_id(responceProductionElectricalMeter.getProd_electrical_meter_id());
			});

			iXmProductionElectricalMeterMachineMappingRepository
					.saveAll(cxmProductionElectricalMeterMachineMappingModel);

			responce.put("success", "1");
			responce.put("data", responceProductionElectricalMeter);
			responce.put("error", "");
			responce.put("message", update ? "Record updated successfully!" : "Record added successfully!");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/XmProductionElectricalMeter/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/XmProductionElectricalMeter/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int prod_electrical_meter_id, String deleted_by, int company_id) {
		Map<String, Object> responce = new HashMap<>();

		try {
			iXmProductionElectricalMeterRepository.deleteElectricalMeter(prod_electrical_meter_id, deleted_by, company_id);

			iXmProductionElectricalMeterMachineMappingRepository.deleteElectricalMeterMapping(prod_electrical_meter_id, deleted_by,
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
	public Map<String, Object> FnShowAllElectricalMeterAndMappingRecords(int prod_electrical_meter_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();

		try {

			CXmProductionElectricalMeterViewModel electricalMeterRecords = iXmProductionElectricalMeterViewRepository
					.FnShowElectricalMeterRecords(prod_electrical_meter_id, company_id);

			List<CXmProductionElectricalMeterMachineMappingViewModel> electricalMeterMappingRecords = iXmProductionElectricalMeterMachineMappingViewRepository
					.FnShowElectricalMeterMappingRecords(prod_electrical_meter_id, company_id);

			responce.put("ElectricalMasterRecord", electricalMeterRecords);
			responce.put("ElectricalMasterMappingRecord", electricalMeterMappingRecords);

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
			query = "SELECT * FROM xmv_production_electrical_meter_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		} else {
			query = "SELECT * FROM xmv_production_electrical_meter_machine_mapping_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		}
		return response;

	}

}
