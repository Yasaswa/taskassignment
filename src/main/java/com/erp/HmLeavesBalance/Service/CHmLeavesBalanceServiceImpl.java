package com.erp.HmLeavesBalance.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmLeavesBalance.Model.CHmLeaveBalanceModel;
import com.erp.HmLeavesBalance.Repository.IHmLeavesBalanceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CHmLeavesBalanceServiceImpl implements IHmLeavesBalanceService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHmLeavesBalanceRepository iHmLeavesBalanceRepository;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		String financial_year = commonIdsObj.getString("financial_year");
		String employee_type = commonIdsObj.getString("employee_type");
		int company_id = commonIdsObj.getInt("company_id");
//		int leaves_balance_id = commonIdsObj.getInt("leaves_balance_id");

		JSONArray leaveBalenceDetails = (JSONArray) jsonObject.get("LeavesBalanceData");

		try {

			List<CHmLeaveBalanceModel> leaveBalenceDetailsObj = objectMapper.readValue(
					leaveBalenceDetails.toString(), new TypeReference<List<CHmLeaveBalanceModel>>() {
					});
			iHmLeavesBalanceRepository.updateStatus(financial_year, employee_type, company_id);

			iHmLeavesBalanceRepository.saveAll(leaveBalenceDetailsObj);

			responce.put("success", "1");
//				responce.put("data", responseLeavesBalanceModel);
			responce.put("error", "");
			responce.put("message", "Record added successfully!...");


		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/HmLeavesBalance/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HmLeavesBalance/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;
	}


	@Override
	public Map<String, Object> FnDeleteRecord(int leaves_balance_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iHmLeavesBalanceRepository.FnDeleteRecord(leaves_balance_id, deleted_by);
			responce.put("success", 1);
		} catch (Exception e) {
			responce.put("success", 0);
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}


	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int leaves_balance_id) {
		Map<String, Object> responce = new HashMap<>();

		CHmLeaveBalanceModel cHmLeavesBalanceModel = null;
		try {
			cHmLeavesBalanceModel = iHmLeavesBalanceRepository.FnShowParticularRecordForUpdate(company_id, leaves_balance_id);
			responce.put("success", "1");
			responce.put("data", cHmLeavesBalanceModel);
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

}
