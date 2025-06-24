package com.erp.Employee.Employee_Grade.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.erp.Agents.Agent.Model.CAgentModel;
import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsDesignationAccess.Model.CAmModulesFormsDesignationAccessModel;
import com.erp.AmModulesFormsDesignationAccess.Model.CAmModulesFormsDesignationAccessViewModel;
import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessModel;
import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessViewModel;
import com.erp.Employee.Employee_Grade.Model.CEmployeeRpt_GradeModel;
import com.erp.Employee.Employee_Grade.Model.CEmployeeView_GradeModel;
import com.erp.Employee.Employee_Grade.Model.CEmployee_GradeModel;
import com.erp.Employee.Employee_Grade.Repository.IAmModulesFormsGradeAccessRepository;
import com.erp.Employee.Employee_Grade.Repository.IAmModulesFormsGradeAccessViewRepository;
import com.erp.Employee.Employee_Grade.Repository.IEmployeeRpt_GradeRepository;
import com.erp.Employee.Employee_Grade.Repository.IEmployeeView_GradeRepository;
import com.erp.Employee.Employee_Grade.Repository.IEmployee_GradeRepository;
import com.erp.XmYarnPackingTypes.Model.CXmYarnPackingTypesViewModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CEmployee_GradeServiceImpl implements IEmployee_GradeService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IEmployee_GradeRepository iEmployee_GradeRepository;

	@Autowired
	IEmployeeView_GradeRepository iEmployeeView_GradeRepository;

	@Autowired
	IEmployeeRpt_GradeRepository iEmployeeRpt_GradeRepository;

	@Autowired
	IAmModulesFormsGradeAccessRepository iAmModulesFormsGradeAccessRepository;
	
	@Autowired
	IAmModulesFormsGradeAccessViewRepository iAmModulesFormsGradeAccessViewRepository;
	
	@Override
	public Map<String, Object> FnAddUpdateRecord(CEmployee_GradeModel cEmployee_GradeModel) {
		
		Map<String, Object> responce = new HashMap<>();
		
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = cEmployee_GradeModel.getCompany_id();
		
		try {
			Optional<CEmployee_GradeModel> option = iEmployee_GradeRepository.findById(cEmployee_GradeModel.getEmployee_grade_id());

			if (option.isPresent()) {
				CEmployee_GradeModel mymodel = iEmployee_GradeRepository.save(cEmployee_GradeModel);
				String json = objectMapper.writeValueAsString(mymodel);
				
				responce.put("success", 1);
				responce.put("data", json);
				responce.put("error", "");
				responce.put("message", "Record updated succesfully!...");				
				return responce;
			}else {
				CEmployee_GradeModel resultsEmployeeGradeName = null;
				
				if (cEmployee_GradeModel.getShort_name() == null || cEmployee_GradeModel.getShort_name().isEmpty()) {
					
					resultsEmployeeGradeName = iEmployee_GradeRepository.getCheck(cEmployee_GradeModel.getEmployee_grade_name(),
							null, cEmployee_GradeModel.getCompany_id());
				} else {
					
					resultsEmployeeGradeName = iEmployee_GradeRepository.getCheck(cEmployee_GradeModel.getEmployee_grade_name(),
							cEmployee_GradeModel.getShort_name(), cEmployee_GradeModel.getCompany_id());
				}
				if (resultsEmployeeGradeName != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", "Employee Grade Name and Short Name is already exist!");
					return responce;
				}
				
				else {

					CEmployee_GradeModel employeeGradeModelRecord = iEmployee_GradeRepository.save(cEmployee_GradeModel);
					responce.put("success", 1);
					responce.put("data", employeeGradeModelRecord);
					responce.put("error", "");
					responce.put("message", "Record added succesfully!...");
					return responce;
				}
			}
			

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/employeegrade/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/employeegrade/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

			return responce;
		}

		return responce;

	}
	
	@Override
	public Map<String, Object> FnDeleteRecord(int employee_grade_id, int company_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Delete Employee Grade Record
			 iEmployee_GradeRepository.FnDeleteRecord(employee_grade_id,company_id,deleted_by);

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


	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int employee_grade_id, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {

			CEmployeeView_GradeModel employeeGradeModelRecord = iEmployeeView_GradeRepository
					.FnShowParticularRecordForUpdate(employee_grade_id,company_id);

			resp.put("success", 1);
			resp.put("EmployeeGradeModelRecord", employeeGradeModelRecord);

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
	
//*****************************************************Api for Modules Form Grade Access*****************************************************************

	@Override
	public Map<String, Object> FnAddUpdateGradeAccessRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		int grade_id = commonIdsObj.getInt("grade_id");
		
		JSONArray array = (JSONArray) jsonObject.get("employeeGradeAcessRecords");
		
		try {
			
			iAmModulesFormsGradeAccessRepository.updateGradeAccess(grade_id, company_branch_id,
					company_id);
			
			List<CAmModulesFormsGradeAccessModel> gradeAccessModels = objectMapper.readValue(array.toString(),
					new TypeReference<List<CAmModulesFormsGradeAccessModel>>() {
					});

			iAmModulesFormsGradeAccessRepository.saveAll(gradeAccessModels);
			
			responce.put("success", 1);
			responce.put("data",gradeAccessModels);
			responce.put("message", "Records added successfully!...");
			responce.put("error", "");
			
		}  catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/employeegrade/FnAddUpdateGradeAccessRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/employeegrade/FnAddUpdateGradeAccessRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

			return responce;
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDeleteGradeAccessRecord(int modules_forms_grade_access_id, int company_id,String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		
		try {
			
			//Delete Modules Forms Grade Access Record
			iAmModulesFormsGradeAccessRepository.FnDeleteRecords(modules_forms_grade_access_id,company_id,deleted_by);

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

	@Override
	public Map<String, Object> FnShowParticularGradeAccessRecordForUpdate(int modules_forms_grade_access_id,
			int company_id) {
		
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//Fetch Modules Forms Grade Access Record
			CAmModulesFormsGradeAccessViewModel modulesFormsGradeAccessViewModelRecord =
					iAmModulesFormsGradeAccessViewRepository.FnShowParticularRecordForUpdate(modules_forms_grade_access_id, company_id);
			
			responce.put("success",1);
			responce.put("ModulesFormsGradeAccessViewModelRecord", modulesFormsGradeAccessViewModelRecord);
			
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success",0);
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

	@Override
	public List<CAmModulesFormsGradeAccessViewModel> FnShowAllAciveRecords(int grade_id, int company_id) {
		return iAmModulesFormsGradeAccessViewRepository.FnShowAllActiveRecords(grade_id, company_id);

	}
	
	


}
