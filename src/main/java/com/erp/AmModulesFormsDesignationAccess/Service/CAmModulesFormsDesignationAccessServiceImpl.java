package com.erp.AmModulesFormsDesignationAccess.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsDesignationAccess.Model.CAmModulesFormsDesignationAccessModel;
import com.erp.AmModulesFormsDesignationAccess.Model.CAmModulesFormsDesignationAccessViewModel;
import com.erp.AmModulesFormsDesignationAccess.Repository.IAmModulesFormsDesignationAccessRepository;
import com.erp.AmModulesFormsDesignationAccess.Repository.IAmModulesFormsDesignationAccessViewRepository;
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
public class CAmModulesFormsDesignationAccessServiceImpl implements IAmModulesFormsDesignationAccessService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAmModulesFormsDesignationAccessRepository iAmModulesFormsDesignationAccessRepository;

	@Autowired
	IAmModulesFormsDesignationAccessViewRepository iAmModulesFormsDesignationAccessViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		int designation_id = commonIdsObj.getInt("designation_id");
		JSONArray array = (JSONArray) jsonObject.get("designationAcessRecords");

		try {
			int accessModel = iAmModulesFormsDesignationAccessRepository.updateStatus(designation_id, company_branch_id,
					company_id);
			ObjectMapper objectMapper = new ObjectMapper();
			List<CAmModulesFormsDesignationAccessModel> accessModels = objectMapper.readValue(array.toString(),
					new TypeReference<List<CAmModulesFormsDesignationAccessModel>>() {
					});

			iAmModulesFormsDesignationAccessRepository.saveAll(accessModels);

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("message", "Records added successfully!...");
			responce.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/AmModulesFormsDesignationAccess/FnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/AmModulesFormsDesignationAccess/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int modules_forms_designation_access_id, int company_id) {
		Optional<CAmModulesFormsDesignationAccessModel> option = iAmModulesFormsDesignationAccessRepository
				.findById(modules_forms_designation_access_id);
		CAmModulesFormsDesignationAccessModel cAmModulesFormsDesignationAccessModel = new CAmModulesFormsDesignationAccessModel();
		if (option.isPresent()) {
			cAmModulesFormsDesignationAccessModel = option.get();
			cAmModulesFormsDesignationAccessModel.setIs_delete(true);
			cAmModulesFormsDesignationAccessModel.setDeleted_on(new Date());
			iAmModulesFormsDesignationAccessRepository.save(cAmModulesFormsDesignationAccessModel);

		}
		return cAmModulesFormsDesignationAccessModel;
	}

//	@Override
//	public Object FnDeleteRecord(int modules_forms_designation_access_id, int company_id, String deleted_by) {
//		Optional<CAmModulesFormsDesignationAccessModel> option = iAmModulesFormsDesignationAccessRepository
//				.findById(modules_forms_designation_access_id);
//		CAmModulesFormsDesignationAccessModel cAmModulesFormsDesignationAccessModel = new CAmModulesFormsDesignationAccessModel();
//		if (option.isPresent()) {
//			cAmModulesFormsDesignationAccessModel = option.get();
//			cAmModulesFormsDesignationAccessModel.setIs_delete(true);
//			cAmModulesFormsDesignationAccessModel.setDeleted_on(new Date());
//			cAmModulesFormsDesignationAccessModel.setDeleted_by(deleted_by);
//			iAmModulesFormsDesignationAccessRepository.save(cAmModulesFormsDesignationAccessModel);
//
//		}
//		return cAmModulesFormsDesignationAccessModel;
//	}

	@Override
	public List<CAmModulesFormsDesignationAccessViewModel> FnShowAllActiveRecords(int designation_id, int company_id) {
		return iAmModulesFormsDesignationAccessViewRepository.FnShowAllActiveRecords(designation_id, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int modules_forms_designation_access_id,
	                                                           int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CAmModulesFormsDesignationAccessModel cAmModulesFormsDesignationAccessModel = null;
		try {
			cAmModulesFormsDesignationAccessModel = iAmModulesFormsDesignationAccessRepository
					.FnShowParticularRecordForUpdate(modules_forms_designation_access_id, company_id);
			responce.put("success", "1");
			responce.put("data", cAmModulesFormsDesignationAccessModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

	@Override
	public Page<CAmModulesFormsDesignationAccessViewModel> FnShowParticularRecord(
			int modules_forms_designation_access_id, Pageable pageable, int company_id) {
		return iAmModulesFormsDesignationAccessViewRepository
				.FnShowParticularRecord(modules_forms_designation_access_id, pageable, company_id);
	}

}
