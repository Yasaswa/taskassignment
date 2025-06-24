package com.erp.HmEmployeeTypeEarningAndTypeDeduction.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Model.CHmEmployeeTypeDeductionMappingModel;
import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Model.CHmEmployeeTypeEarningMappingModel;
import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Repository.IHmEmployeeTypeDeductionMappingRepository;
import com.erp.HmEmployeeTypeEarningAndTypeDeduction.Repository.IHmEmployeeTypeEarningMappingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CHmEmployeeTypeEarningAndDeductionMappingServiceImpl implements IHmEmployeeTypeEarningAndDeductionMappingService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHmEmployeeTypeEarningMappingRepository iHmEmployeeTypeEarningMappingRepository;

	@Autowired
	IHmEmployeeTypeDeductionMappingRepository iHmEmployeeTypeDeductionMappingRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		int employee_type_id = commonIdsObj.getInt("employee_type_id");
		String employee_type_name = commonIdsObj.getString("employee_type_name");
		String employee_type_group_name = commonIdsObj.getString("employee_type_group_name");

		JSONArray masterjson = (JSONArray) jsonObject.get("TransEarningHeadData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDeductionHeadData");

//		Employee Type Earning
		try {
			List<CHmEmployeeTypeEarningMappingModel> cEmployeeTypeEarningMapping = objectMapper
					.readValue(masterjson.toString(), new TypeReference<List<CHmEmployeeTypeEarningMappingModel>>() {
					});

			iHmEmployeeTypeEarningMappingRepository.updateEmployeeEarningRecord(company_id, employee_type_name, employee_type_group_name);

			iHmEmployeeTypeEarningMappingRepository.saveAll(cEmployeeTypeEarningMapping);

//		Employee Type Deduction 

			iHmEmployeeTypeDeductionMappingRepository.updateDeductionRecord(company_id, employee_type_name, employee_type_group_name);

			List<CHmEmployeeTypeDeductionMappingModel> cEmployeeDeductionMappingModel = objectMapper
					.readValue(detailsArray.toString(), new TypeReference<List<CHmEmployeeTypeDeductionMappingModel>>() {
					});

			iHmEmployeeTypeDeductionMappingRepository.saveAll(cEmployeeDeductionMappingModel);

			responce.put("success", 1);
			responce.put("data", "");
			responce.put("error", "");
			responce.put("message", "Record added successfully!...");


		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/HmEmployeeTypeEarningAndTypeDeduction/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HmEmployeeTypeEarningAndTypeDeduction/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;


	}

	@Override
	public Object FnDeleteRecord(int employee_type_id, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();

		try {
			iHmEmployeeTypeEarningMappingRepository.deleteEmployeeTypeEarningMapping(employee_type_id, company_id, deleted_by);

			iHmEmployeeTypeDeductionMappingRepository.deleteEmployeeTypeDeductionMapping(employee_type_id, company_id, deleted_by);

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowAllRecords(String employee_type_name, String employee_type_group_name,
	                                            int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			List<CHmEmployeeTypeEarningMappingModel> earningMappingRecords =
					iHmEmployeeTypeEarningMappingRepository.FnShowErningMapingRecords(employee_type_name, employee_type_group_name, company_id);

			List<CHmEmployeeTypeDeductionMappingModel> deductionMappingRecords =
					iHmEmployeeTypeDeductionMappingRepository.FnShowdeductionMappingRecords(employee_type_name, employee_type_group_name, company_id);

			responce.put("EarningMappingRecords", earningMappingRecords);
			responce.put("DeductionMappingRecords", deductionMappingRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

}
