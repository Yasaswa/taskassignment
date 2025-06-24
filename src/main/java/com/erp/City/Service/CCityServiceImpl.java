package com.erp.City.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.City.Model.CCityModel;
import com.erp.City.Model.CCityViewModel;
import com.erp.City.Repository.ICityRepository;
import com.erp.City.Repository.ICityViewRepository;
import com.erp.Contractors.Model.CContractorsModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;

@Service
public class CCityServiceImpl implements ICityService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICityRepository iCityRepository;

	@Autowired
	ICityViewRepository iCityViewRepository;


	@Autowired
	JdbcTemplate executeQuery;

	@Override
	public JSONObject FnAddUpdateRecord(CCityModel cityModel) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		Optional<CCityModel> option = iCityRepository.findById(cityModel.getCity_id());
		int company_id = cityModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cityModel.setModified_on(new Date());
				CCityModel json = iCityRepository.save(cityModel);

				resp.put("success", "1");
				resp.put("data", json);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" city updated successfully!..");
				return resp;

			} else {
				//Check similar City Name and  short name is exist or not
				CCityModel resultsCityName = null;
				if (cityModel.getCity_short_name() == null || cityModel.getCity_short_name().isEmpty()) {
					resultsCityName = iCityRepository.getCheck(cityModel.getCity_name(),
							null,cityModel.getState_id(),
							cityModel.getDistrict_id());
				} else {
					resultsCityName = iCityRepository.getCheck(cityModel.getCity_name(),
							cityModel.getCity_short_name(), cityModel.getState_id(),
							cityModel.getDistrict_id());
				}

				if (resultsCityName != null) {
					resp.put("success", 0);
					resp.put("data", "");
					resp.put("error", "City Name or  Is Already Exist!");
					return resp;
				}
				else {
					CCityModel cModel = iCityRepository.save(cityModel);
					String json = objectMapper.writeValueAsString(cModel);
					resp.put("success", "1");
					resp.put("data", json);
					resp.put("error", "");
					resp.put("message", "Record added succesfully!...");

					System.out.println(" city saved succesfully!..");
					System.out.println("res: " + resp);
					return resp;

				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();

				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/city/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/city/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

			return resp;
		}
		return resp;
	}

	@Override
	public Object FnDeleteRecord(int city_id, String deleted_by) {
		return iCityRepository.FnDeleteRecord(city_id, deleted_by);
	}

	@Override
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CCityViewModel> data = iCityViewRepository.FnShowAllActiveRecords(pageable);

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
	public Object FnShowAllRecords(Pageable pageable) {

		JSONObject resp = new JSONObject();
		try {
			Page<CCityViewModel> data = iCityViewRepository.FnShowAllRecords(pageable);

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
	public Object FnShowParticularRecord(int company_id, int city_id) {
		JSONObject resp = new JSONObject();
		try {

//			CCityViewModel json = iCityViewRepository.FnShowParticularRecord(company_id, city_id);

			CCityViewModel json = iCityViewRepository.FnShowParticularRecord(city_id);


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
			Page<Map<String, Object>> data = iCityViewRepository.FnShowAllReportRecords(pageable);
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
	public Map<String, Object> FnShowParticularRecordForUpdate(int city_id) {
		Map<String, Object> resp = new HashMap();
		try {

			CCityViewModel json = iCityViewRepository.FnShowParticularRecordForUpdate(city_id);

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
				resp.put("error", e.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public List<CCityViewModel> FnShowFilterRecords(JSONObject jsonQuery) {
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

		query += " FROM cmv_city";
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
		} else {
			query += " ORDER BY city_id";
		}
		System.out.println("query: " + query);

		return executeQuery.query(query, new BeanPropertyRowMapper(CCityViewModel.class));
	}

	@Override
	public JSONObject FnMShowFilterRecords(JSONObject jsonQuery, int page, int size) {
		JSONObject resp = new JSONObject();
		List<CCityViewModel> cCityViewModels = null;
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
			query += " FROM cmv_city ";
			countCheck += " FROM cmv_city ";

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
				query += " ORDER BY city_id DESC LIMIT " + page + ", " + size;
				countCheck += " ORDER BY city_id DESC LIMIT " + size;
			}

			System.out.println("query: " + query);
			System.out.println("countCheck: " + countCheck);

			cCityViewModels = executeQuery.query(query, new BeanPropertyRowMapper(CCityViewModel.class));
			viewModel = executeQuery.queryForObject(countCheck, Integer.class);

			resp.put("content", cCityViewModels);
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
