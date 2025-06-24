package com.erp.Designations.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsDesignationAccess.Repository.IAmModulesFormsDesignationAccessRepository;
import com.erp.Designations.Model.*;
import com.erp.Designations.Repository.*;
import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsViewModel;
import com.erp.HmDeductionHeads.Repository.IHmDeductionHeadsViewRepository;
import com.erp.HmEarningHeads.Model.CHmEarningHeadsViewModel;
import com.erp.HmEarningHeads.Repository.IHmEarningHeadsViewRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CDesignationsServiceImpl implements IDesignationsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IDesignationsRepository iDesignationsRepository;

	@Autowired
	IDesignationEarningMappingModelRepository iDesignationEarningMappingModelRepository;

	@Autowired
	IDesignationDeductionMappingModelRepository iDesignationDeductionMappingModelRepository;

	@Autowired
	IDesignationEarningMappingViewModelRepository iDesignationEarningMappingViewModelRepository;

	@Autowired
	IDesignationDeductionMappingViewModelRepository iDesignationDeductionMappingViewModelRepository;

	@Autowired
	IDesignationsViewRepository iDesignationsViewRepository;

	@Autowired
	IHmEarningHeadsViewRepository iHmEarningHeadsViewRepository;

	@Autowired
	IHmDeductionHeadsViewRepository iHmDeductionHeadsViewRepository;

	@Autowired
	IDesignationSalaryHeadsMappingRecordRepository iDesignationSalaryHeadsMappingRecordRepository;

	@Autowired
	IHmEmployeeTypeEarningMappingRepository iHmEmployeeTypeEarningMappingRepository;

	@Autowired
	IHmEmployeeTypeDeductionMappingRepository iHmEmployeeTypeDeductionMappingRepository;

	@Autowired
	IAmModulesFormsDesignationAccessRepository iAmModulesFormsDesignationAccessRepository;

	@Autowired
	JdbcTemplate executeQuery;

	@Override
	public HashMap<String, Object> FnAddUpdateRecord(CDesignationsModel cDesignationsModel) {
		HashMap<String, Object> resp = new HashMap<>();
		int company_id = cDesignationsModel.getCompany_id();
		try {
			Optional<CDesignationsModel> option = iDesignationsRepository
					.findById(cDesignationsModel.getDesignation_id());
			CDesignationsModel MyModel = null;
			if (option.isPresent()) {
				cDesignationsModel.setModified_on(new Date());
				CDesignationsModel json = iDesignationsRepository.save(cDesignationsModel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!..");
				System.out.println(" Designation updated successfully!..");
			} else {
				CDesignationsModel model = iDesignationsRepository.getCheck(cDesignationsModel.getDesignation_name(), company_id);

				if (model != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", " Designation is already exist!");
					return resp;
				} else {

					CDesignationsModel json = iDesignationsRepository.save(cDesignationsModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added successfully!..");
					System.out.println(" Designation Seved  successfully!..");
					return resp;
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/designation/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/designation/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;
		}
		return resp;
	}

	@Override
	public Map<String, Object> FnDeleteRecord(int designation_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {

			iDesignationsRepository.FnDeleteRecord(designation_id, deleted_by);
			iAmModulesFormsDesignationAccessRepository.FnDeleteAmModulesFormsDesignationAccessDetails(designation_id, deleted_by);
			iDesignationDeductionMappingModelRepository.FnDeleteDesignationDeductionMappingRecord(designation_id, deleted_by);
			iDesignationEarningMappingModelRepository.FnDeleteDesignationEarningMappingRecord(designation_id, deleted_by);

			responce.put("success", 1);

		} catch (Exception e) {
			responce.put("success", 0);
			responce.put("error", "");
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CDesignationsViewModel> data = iDesignationsViewRepository.FnShowAllRecords(pageable);
			resp.put("success", "1");
			resp.put("error", "");

			return new Object[]{data, resp};

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return new Object[]{"", resp};
	}

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CDesignationsViewModel> data = iDesignationsViewRepository.FnShowAllActiveRecords(pageable);

			resp.put("success", "1");
			resp.put("error", "");

			return new Object[]{data, resp};

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return new Object[]{"", resp};
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int designation_id) {
		Map<String, Object> resp = new HashMap();
		try {

			CDesignationsViewModel json = iDesignationsViewRepository.FnShowParticularRecordForUpdate(designation_id);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public JSONObject FnShowParticularRecord(int company_id, int designation_id) {
		JSONObject resp = new JSONObject();
		try {

			CDesignationsViewModel json = iDesignationsViewRepository.FnShowParticularRecordForUpdate(company_id,
					designation_id);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iDesignationsViewRepository.FnShowAllReportRecords(pageable);
			System.out.println(data);
			resp.put("success", "1");
			resp.put("error", "");

			return new Object[]{data, resp};

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return new Object[]{"", resp};
	}

	@Override
	public Map<String, Object> EarningAndDeductionFnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		int designation_id = commonIdsObj.getInt("designation_id");

		JSONArray masterjson = (JSONArray) jsonObject.get("TransEarningHeadData");
		JSONArray detailsArray = (JSONArray) jsonObject.get("TransDeductionHeadData");

//		Earning Head 
		try {
			List<CDesignationEarningMappingModel> cDesignationEarningMapping = objectMapper
					.readValue(masterjson.toString(), new TypeReference<List<CDesignationEarningMappingModel>>() {
					});

			iDesignationEarningMappingModelRepository.updateEarningRecord(company_id, designation_id);

			iDesignationEarningMappingModelRepository.saveAll(cDesignationEarningMapping);

//			Deduction Head

			iDesignationDeductionMappingModelRepository.updateDeductionRecord(company_id, designation_id);

			List<CDesignationDeductionMappingModel> cDesignationDeductionMappingModel = objectMapper
					.readValue(detailsArray.toString(), new TypeReference<List<CDesignationDeductionMappingModel>>() {
					});

			iDesignationDeductionMappingModelRepository.saveAll(cDesignationDeductionMappingModel);

			responce.put("success", 1);
			responce.put("data", "");
			responce.put("error", "");
			responce.put("message",
					designation_id == 0 ? "Record added successfully!..." : "Record updated successfully..!");
		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/designation/EarningAndDeductionFnAddUpdateRecord", sqlEx.getErrorCode(),
						sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/designation/EarningAndDeductionFnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Map<String, Object> EarningAndDeductionFnShowAllRecords(int designation_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			List<CDesignationEarningMappingModel> earningMappingRecords = iDesignationEarningMappingModelRepository
					.FnShowErningMapingRecords(designation_id);

			List<CDesignationDeductionMappingModel> deductionMappingRecords = iDesignationDeductionMappingModelRepository
					.FnShowDeductionMapingRecords(designation_id);

			responce.put("EarningMappingRecords", earningMappingRecords);
			responce.put("DeductionMappingRecords", deductionMappingRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowEarningAndDeductionRecords(int company_id) {
		Map<String, Object> responce = new HashMap<>();

		try {
			List<CHmEarningHeadsViewModel> earningMappingRecords = iHmEarningHeadsViewRepository
					.FnShowEarningHeadMapingRecords(company_id);

			List<CHmDeductionHeadsViewModel> deductionMappingRecords = iHmDeductionHeadsViewRepository
					.FnShowDeductionMapingRecords(company_id);

			responce.put("EarningMappingRecords", earningMappingRecords);
			responce.put("DeductionMappingRecords", deductionMappingRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnShowDesignationSalaryHeadsMappingRecord(int designation_salary_heads_mapping_id,
	                                                                     int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {

			List<CDesignationSalaryHeadsMappingViewModel> data = iDesignationSalaryHeadsMappingRecordRepository
					.FnShowDesignationSalaryHeadsMappingRecord(designation_salary_heads_mapping_id, company_id);

			responce.put("data", data);
			responce.put("success", 1);
			responce.put("error", "");

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
	public Map<String, Object> FnEmployeeEarningTypeAndDeductionTypeShowAllRecords(String employee_type_name,
	                                                                               int company_id) {
		Map<String, Object> responce = new HashMap<>();

		try {
			List<CHmEmployeeTypeEarningMappingModel> earningMappingRecords = iHmEmployeeTypeEarningMappingRepository
					.FnShowEmployeeEarningMappingRecords(employee_type_name, company_id);

			List<CHmEmployeeTypeDeductionMappingModel> deductionMappingRecords = iHmEmployeeTypeDeductionMappingRepository
					.FnShowEmployeeDeductionMappingRecords(employee_type_name, company_id);

			responce.put("EmployeeEarningMappingRecords", earningMappingRecords);
			responce.put("EmployeeDeductionMappingRecords", deductionMappingRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}
}
