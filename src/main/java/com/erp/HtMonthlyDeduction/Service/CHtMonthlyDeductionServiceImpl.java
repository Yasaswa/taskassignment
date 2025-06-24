package com.erp.HtMonthlyDeduction.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HtMonthlyDeduction.Model.CHtMonthlyDeductionDeatilsModel;
import com.erp.HtMonthlyDeduction.Model.CHtMonthlyDeductionMasterModel;
import com.erp.HtMonthlyDeduction.Repository.IHtMonthlyDeductionMasterRepository;
import com.erp.HtMonthlyDeduction.Repository.IHtMonthlyDeductionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CHtMonthlyDeductionServiceImpl implements IHtMonthlyDeductionService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHtMonthlyDeductionMasterRepository iHtMonthlyDeductionMasterRepository;

	@Autowired
	IHtMonthlyDeductionRepository iHtMonthlyDeductionRepository;

	@Override
	@Transactional
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int salary_year = commonIdsObj.getInt("salary_year");
		String salary_month = commonIdsObj.getString("salary_month");
		int deduction_type_id = commonIdsObj.getInt("deduction_type_id");
		int company_id = commonIdsObj.getInt("company_id");
		JSONObject mstObj = (JSONObject) jsonObject.getJSONObject("MonthlyDeductionMst");
		JSONArray monthlyDeductionDetails = (JSONArray) jsonObject.get("MonthlyDeductionData");

		try {
			CHtMonthlyDeductionMasterModel monthlyDeductionMaster = objectMapper.readValue(mstObj.toString(),
					CHtMonthlyDeductionMasterModel.class);

			// Update the previous master and details set as deleted.
			iHtMonthlyDeductionMasterRepository.deletePreviousRecords(deduction_type_id, salary_year, salary_month, monthlyDeductionMaster.getEmployee_type(),
					company_id, monthlyDeductionMaster.getCreated_by());
			iHtMonthlyDeductionRepository.deletePreviousRecords(monthlyDeductionMaster.getMonthly_deduction_master_transaction_id(),
					company_id, monthlyDeductionMaster.getCreated_by());

			// Save the master tbl.
			CHtMonthlyDeductionMasterModel savedMstData = iHtMonthlyDeductionMasterRepository
					.save(monthlyDeductionMaster);
			// Save the Deduction Details.
			List<CHtMonthlyDeductionDeatilsModel> deductionDtls = objectMapper.readValue(
					monthlyDeductionDetails.toString(), new TypeReference<List<CHtMonthlyDeductionDeatilsModel>>() {
					});
			deductionDtls.forEach(deductionDtl -> {
				deductionDtl.setMonthly_deduction_master_transaction_id(
						savedMstData.getMonthly_deduction_master_transaction_id());
			});
			iHtMonthlyDeductionRepository.saveAll(deductionDtls);

			responce.put("success", "1");
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
	public Map<String, Object> FnDeleteRecord(int monthly_deduction_transaction_id, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		// Update the previous master and details set as deleted.
		iHtMonthlyDeductionMasterRepository.deleteRecords(monthly_deduction_transaction_id, company_id, deleted_by);
		iHtMonthlyDeductionRepository.deleteRecords(monthly_deduction_transaction_id, company_id, deleted_by);
		
		responce.put("success", "1");
		responce.put("error", "");
		responce.put("message", "Record deleted successfully!...");
		return responce;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int monthly_deduction_master_transaction_id,
			int company_id) {
		Map<String, Object> responce = new HashMap<>();
		// CHtMonthlyDeductionDeatilsModel cHtMonthlyDeductionModel = null;
		try {

			CHtMonthlyDeductionMasterModel masterData = iHtMonthlyDeductionMasterRepository
					.FnShowParticularRecordForUpdate(monthly_deduction_master_transaction_id, company_id);
			List<Map<String, Object>> deductionDetails = iHtMonthlyDeductionRepository.FnShowParticularRecordForUpdate(monthly_deduction_master_transaction_id, company_id);

			responce.put("success", "1");
			responce.put("MasterData", masterData);
			responce.put("DetailsData", deductionDetails);
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
