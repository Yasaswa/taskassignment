package com.erp.Department.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Company.Companies.Model.CCompanyModel;
import com.erp.Department.Model.CDepartmentModel;
import com.erp.Department.Model.CDepartmentViewModel;
import com.erp.Department.Repository.IDepartmentRepository;
import com.erp.Department.Repository.IDepartmentViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CDepartmentServiceImpl implements IDepartmentService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IDepartmentRepository iDepartmentRepository;

	@Autowired
	IDepartmentViewRepository iDepartmentViewRepository;

	@Autowired
	private JdbcTemplate executeQuery;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CDepartmentModel cDepartmentModel) {
		Map<String, Object> resp = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = cDepartmentModel.getCompany_id();
		try {
			Optional<CDepartmentModel> option = iDepartmentRepository.findById(cDepartmentModel.getDepartment_id());

			if (option.isPresent()) {
				cDepartmentModel.setModified_on(new Date());
				CDepartmentModel mymodel = iDepartmentRepository.save(cDepartmentModel);
				String json = objectMapper.writeValueAsString(mymodel);
				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
			} else {
				
//				CDepartmentModel model = iDepartmentRepository.CheckIfExist(cDepartmentModel.getDepartment_name());
//				if (model != null) {
//					resp.put("success", "0");
//					resp.put("data", "");
//					resp.put("error", " Department Name is already exist!");
//				} 
//				
				//Check similar Department Name,Department short name is exist or not
				CDepartmentModel resultsDepartmentName = null;
				if (cDepartmentModel.getDepartment_short_name() == null ||cDepartmentModel.getDepartment_short_name().isEmpty()) {
					resultsDepartmentName = iDepartmentRepository.getCheck(cDepartmentModel.getDepartment_name(),
							null, cDepartmentModel.getDepartment_type(), cDepartmentModel.getCompany_id());
				} else {
					resultsDepartmentName = iDepartmentRepository.getCheck(cDepartmentModel.getDepartment_name(),
							cDepartmentModel.getDepartment_short_name(),  cDepartmentModel.getDepartment_type(),cDepartmentModel.getCompany_id());
				}

				if (resultsDepartmentName != null) {
					resp.put("success", 0);
					resp.put("data", "");
					resp.put("error", "Department Name or Short-Name is already exist!");
					return resp;
				}else {
					CDepartmentModel DepartmentModel = iDepartmentRepository.save(cDepartmentModel);
					String json = objectMapper.writeValueAsString(DepartmentModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");
				}
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/department/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/department/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}
		return resp;

	}


	@Override
	public Object FnDeleteRecord(int department_id, String deleted_by) {
		return iDepartmentRepository.FnDeleteRecord(department_id, deleted_by);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CDepartmentViewModel> data = iDepartmentViewRepository.FnShowAllRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
			resp.put("success", "1");
			resp.put("error", "");

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

		return resp;

	}

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CDepartmentViewModel> data = iDepartmentViewRepository.FnShowAllActiveRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
			resp.put("success", "1");
			resp.put("error", "");

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

		return resp;

	}


	@Override
	public JSONObject FnShowParticularRecordForUpdate(int department_id, int company_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CDepartmentModel json = iDepartmentRepository.FnShowParticularRecordForUpdate(department_id, company_id);
			String json1 = objectMapper.writeValueAsString(json);
			resp.put("success", "1");
			resp.put("data", json1);
			resp.put("error", "");

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
	public JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int department_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CDepartmentViewModel json = iDepartmentViewRepository.FnShowParticularRecord(company_id, company_branch_id, department_id);
			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
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
			Page<Map<String, Object>> data = iDepartmentViewRepository.FnShowAllReportRecords(pageable);
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


}
