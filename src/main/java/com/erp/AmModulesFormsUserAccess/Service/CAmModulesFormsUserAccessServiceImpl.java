package com.erp.AmModulesFormsUserAccess.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessModel;
import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;
import com.erp.AmModulesFormsUserAccess.Repository.IAmModulesFormsUserAccessRepository;
import com.erp.AmModulesFormsUserAccess.Repository.IAmModulesFormsUserAccessViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CAmModulesFormsUserAccessServiceImpl implements IAmModulesFormsUserAccessService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAmModulesFormsUserAccessRepository iAmModulesFormsUserAccessRepository;

	@Autowired
	IAmModulesFormsUserAccessViewRepository iAmModulesFormsUserAccessViewRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		String user_code = commonIdsObj.getString("user_code");
//		String user_type = commonIdsObj.getString("user_type");
		JSONArray array = (JSONArray) jsonObject.get("userAcessRecords");

		try {
			ObjectMapper objectMapper = new ObjectMapper();

			List<CAmModulesFormsUserAccessModel> accessModels = objectMapper.readValue(array.toString(),
					new TypeReference<List<CAmModulesFormsUserAccessModel>>() {
					});

			List<Integer> getDistinctModuleForms = accessModels.stream().map(CAmModulesFormsUserAccessModel::getModules_forms_id).collect(Collectors.toList());

//			if (getDistinctModuleForms != null && getDistinctModuleForms.size() != 0) {
//				iAmModulesFormsUserAccessRepository.updateStatusByModuleForms(user_code, getDistinctModuleForms,
//						company_id);
//			}else{
//				iAmModulesFormsUserAccessRepository.updateStatus(user_code,
//						company_id);
//			}

			iAmModulesFormsUserAccessRepository.updateStatus(user_code,
					company_id);

			if (accessModels != null && accessModels.size() != 0) {
				iAmModulesFormsUserAccessRepository.saveAll(accessModels);
			}

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("message", "Records added successfully!...");
			responce.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				e.printStackTrace();
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModulesFormsUserAccess/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/producttype/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}

		return responce;

	}

	@Override
	public List<CAmModulesFormsUserAccessViewModel> FnShowAllActiveRecords(String user_code, int company_id) {
		return iAmModulesFormsUserAccessViewRepository.FnShowAllActiveRecords(user_code, company_id);
	}

}
