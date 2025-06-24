package com.erp.Company.Company_Branch.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Company.Company_Branch.Model.CCompanyBranchModel;
import com.erp.Company.Company_Branch.Model.CCompanyBranchViewModel;
import com.erp.Company.Company_Branch.Repository.ICompanyBranchRepository;
import com.erp.Company.Company_Branch.Repository.ICompanyBranchViewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CCompanyBranchServiceImpl implements ICompanyBranchService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICompanyBranchRepository iCompanyBranchRepository;

	@Autowired
	ICompanyBranchViewRepository iCompanyBranchViewRepository;

	@Autowired
	private JdbcTemplate executeQuery;

	@Override
	public JSONObject FnAddUpdateRecord(CCompanyBranchModel companyBranchModel) {
		JSONObject resp = new JSONObject();
		int company_id = companyBranchModel.getCompany_id();
		try {
			Optional<CCompanyBranchModel> option = iCompanyBranchRepository
					.findById(companyBranchModel.getCompany_branch_id());

			if (option.isPresent()) {
				ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
				CCompanyBranchModel MyModel = iCompanyBranchRepository.save(companyBranchModel);
				System.out.println("Company branch updated successfully!..");
				String json = mapper.writeValueAsString(MyModel);

				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated successfully!...");
				return resp;

			} else {

				String company_branch_name = companyBranchModel.getCompany_branch_name();
				CCompanyBranchModel branchModel = iCompanyBranchRepository.checkIfExist(company_branch_name);

				if (branchModel != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Branch Name is already exist!");
					return resp;

				} else {
					CCompanyBranchModel cCompanyBranchModel = new CCompanyBranchModel();
					ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
					cCompanyBranchModel = iCompanyBranchRepository.save(companyBranchModel);
					String json = mapper.writeValueAsString(cCompanyBranchModel);

					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added successfully!...");
					return resp;

				}

			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/companybranch/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/companybranch/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;

		}
		return resp;

	}

	@Override
	public CCompanyBranchModel FnDeleteRecord(int company_branch_id, String deleted_by) {
		Optional<CCompanyBranchModel> option = iCompanyBranchRepository.findById(company_branch_id);
		CCompanyBranchModel branchModel = new CCompanyBranchModel();
		if (option.isPresent()) {
			branchModel = option.get();
			branchModel.setIs_delete(true);
			branchModel.setDeleted_on(new Date());
			branchModel.setDeleted_by(deleted_by);
			branchModel = iCompanyBranchRepository.save(branchModel);
		}
		return branchModel;
	}

	@Override
	public Page<CCompanyBranchViewModel> FnShowAllRecords(Pageable pageable) {
		return iCompanyBranchViewRepository.FnShowAllRecords(pageable);
	}

	@Override
	public Page<CCompanyBranchViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iCompanyBranchViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public CCompanyBranchModel FnShowParticularRecordForUpdate(int company_branch_id) {
		return iCompanyBranchRepository.FnShowParticularRecordForUpdate(company_branch_id);
	}

	@Override
	public Page<CCompanyBranchViewModel> FnShowParticularRecord(int company_id, Pageable pageable) {
		return iCompanyBranchViewRepository.FnShowParticularRecord(company_id, pageable);
	}

	@Override
	public Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable) {
		return iCompanyBranchViewRepository.FnShowAllReportRecords(pageable);
	}

	@Override
	public List<CCompanyBranchViewModel> FnShowFilterRecords(JSONObject jsonQuery) {
		boolean is_deleteStatus = false;
		String query = "Select ";
		JSONObject select = (JSONObject) jsonQuery.get("selectQuery");
		JSONObject where = (JSONObject) jsonQuery.get("whereQuery");
		JSONObject operator = (JSONObject) jsonQuery.get("operators");
		JSONObject groupBy = (JSONObject) jsonQuery.get("group_by");
		JSONObject additionalParam = (JSONObject) jsonQuery.get("additionalParam");

		if (!select.keySet().isEmpty()) {
			for (String currentKey : select.keySet()) {
				Object key = select.get(currentKey);
				String value = key.toString();
				query += value + ", ";
			}
			query = query.substring(0, query.length() - 2);
		} else {
			query += "*";

		}

		query += " FROM cmv_company_branch";
		query += " WHERE ";
		if (!where.keySet().isEmpty()) {
			for (String currentKey : where.keySet()) {
				Object key = where.get(currentKey);
				String value = key.toString();
				String operatorValue = (String) operator.get(currentKey);
				String val = String.format("'%s'", value.toString());
				query += currentKey + " " + operatorValue + " " + val + " AND ";
			}
			for (String currentKey : where.keySet()) {
				if (!currentKey.equals("is_delete")) {
					is_deleteStatus = true;
				}
			}
			if (is_deleteStatus == true) {
				query += "is_delete = 0";
			} else {
				query = query.substring(0, query.length() - 5);
			}
		} else {
			for (String currentKey : additionalParam.keySet()) {
				Object key = additionalParam.get(currentKey);
				String value = key.toString();
				String val = String.format("'%s'", value.toString());
				query += currentKey + " " + " = " + val + " AND ";
			}
			query = query.substring(0, query.length() - 5);
		}

		if (groupBy.get("GROUP BY") != "") {
			String groupByVal = (String) groupBy.get("GROUP BY");
			query += " GROUP BY " + groupByVal + " ORDER BY " + groupByVal;
		}
		System.out.println("query: " + query);

		return executeQuery.query(query, new BeanPropertyRowMapper(CCompanyBranchViewModel.class));
	}

	@Override
	public JSONObject FnMShowFilterRecords(JSONObject jsonQuery, int page, int size) {
		JSONObject resp = new JSONObject();
		List<CCompanyBranchViewModel> branchViewModels = null;
		int viewModel = 0;
		try {
			boolean is_deleteStatus = false;
			String query = "Select ";
			String countCheck = "Select COUNT(*) ";
			JSONObject select = (JSONObject) jsonQuery.get("selectQuery");
			JSONObject where = (JSONObject) jsonQuery.get("whereQuery");
			JSONObject operator = (JSONObject) jsonQuery.get("operators");
			JSONObject groupBy = (JSONObject) jsonQuery.get("group_by");
			JSONObject additionalParam = (JSONObject) jsonQuery.get("additionalParam");

			if (!select.keySet().isEmpty()) {
				for (String currentKey : select.keySet()) {
					Object key = select.get(currentKey);
					String value = key.toString();
					query += value + ", ";
				}
				query = query.substring(0, query.length() - 2);
			} else {
				query += "*";

			}
			query += " FROM cmv_company_branch ";
			countCheck += " FROM cmv_company_branch ";

			query += " WHERE ";
			countCheck += " WHERE ";

			if (!where.keySet().isEmpty()) {
				for (String currentKey : where.keySet()) {
					Object key = where.get(currentKey);
					String value = key.toString();
					String operatorValue = (String) operator.get(currentKey);
					String val = String.format("'%s'", value.toString());
					query += currentKey + " " + operatorValue + " " + val + " AND ";
					countCheck += currentKey + " " + operatorValue + " " + val + " AND ";
				}
				for (String currentKey : where.keySet()) {
					if (!currentKey.equals("is_delete")) {
						is_deleteStatus = true;
					}
				}
				if (is_deleteStatus == true) {
					query += "is_delete = 0";
					countCheck += "is_delete = 0";
				} else {
					query = query.substring(0, query.length() - 5);
					countCheck = countCheck.substring(0, countCheck.length() - 5);
				}
			} else {
				for (String currentKey : additionalParam.keySet()) {
					Object key = additionalParam.get(currentKey);
					String value = key.toString();
					String val = String.format("'%s'", value.toString());
					query += currentKey + " " + " = " + val + " AND ";
					countCheck += currentKey + " " + " = " + val + " AND ";
				}
				query = query.substring(0, query.length() - 5);
				countCheck = countCheck.substring(0, countCheck.length() - 5);
			}
			if (page != 0) {
				page = size * page;
			}
			if (groupBy.get("GROUP BY") != "") {
				String groupByVal = (String) groupBy.get("GROUP BY");
				query += " GROUP BY " + groupByVal + " ORDER BY " + groupByVal;
				countCheck += " GROUP BY " + groupByVal + " ORDER BY " + groupByVal;
				query += " DESC LIMIT " + page + ", " + size;
				countCheck += " LIMIT " + size + " OFFSET " + page;
			} else {
				query += " ORDER BY company_branch_id DESC LIMIT " + page + ", " + size;
				countCheck += " ORDER BY company_branch_id DESC LIMIT " + size;
			}

			System.out.println("query: " + query);
			System.out.println("countCheck: " + countCheck);

			branchViewModels = executeQuery.query(query, new BeanPropertyRowMapper(CCompanyBranchViewModel.class));
			viewModel = executeQuery.queryForObject(countCheck, Integer.class);

			resp.put("content", branchViewModels);
			resp.put("totalElements", viewModel);

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
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;
		}
		return resp;
	}

}
