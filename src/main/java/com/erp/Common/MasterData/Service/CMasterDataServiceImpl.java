package com.erp.Common.MasterData.Service;

import com.erp.Common.MasterData.Model.*;
import com.erp.Common.MasterData.Repository.IMasterDataViewRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CMasterDataServiceImpl implements IMasterDataService {

	private static final List<String> notIncludedCompanyIdList = Arrays.asList("cmv_country", "cmv_district", "smv_product_packing", "amv_financial_year", "hmv_weeklyoff",
			"cmv_state", "cmv_city", "amv_modules_menu", "amv_modules_menu", "amv_modules_sub_menu", "cmv_godown", "cmv_godown_section", "cmv_godown_section_beans", "hmv_job_type","cmv_supplier",
			"smv_product_unit", "smv_product_category1", "smv_product_category2", "smv_product_type", "cmv_hsn_sac", "amv_properties", "smv_product_rm", "xmv_spinning_prod_count", "smv_product_fg_technical", "smv_product_fg");
	@Autowired
	IMasterDataViewRepository iMasterDataViewRepository;
	@Autowired
	CacheManager cacheManager;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static boolean isNumeric(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public Map<String, Object> FnShowParticularRecord(JSONObject json, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			List<Object> queryParams = new ArrayList<>();
			String view = json.getString("viewName");
			String colName = json.optString("colName"); // Use optString to handle null or missing values
			String identityValue = json.optString("identityValue"); // Use optString to handle null or missing values

			StringBuilder query = new StringBuilder("SELECT field_id, field_name FROM ").append(view).append(" WHERE ");
			if (!notIncludedCompanyIdList.contains(view.trim())) {
				query.append("company_id = ? ");
				queryParams.add(company_id);
			}

			if (colName != null && identityValue != null && !colName.isEmpty() && !identityValue.isEmpty()) {
				if (query.toString().trim().endsWith("?")) {
					query.append(" AND ");
				}

				query.append(colName).append(" = ?");
				queryParams.add(identityValue);

			}

			if (!colName.equals("is_delete") || colName == null || colName.isEmpty()) {
				if (query.toString().trim().endsWith("?")) {
					query.append(" AND ");
				}
				query.append(" is_delete = 0");
			}

			if ((!colName.equals("is_active") || colName == null || colName.isEmpty()) && (view.equals("cmv_employee") || view.equals("cm_employee"))) {
				if (query.toString().trim().endsWith("?")) {
					query.append(" AND ");
				}
				query.append(" is_active = 1");
			}

			System.out.println("FnShowParticularRecord: " + query);
			List<CMasterDataViewModel> fetchdata = jdbcTemplate.query(query.toString(), queryParams.toArray(),
					new BeanPropertyRowMapper<>(CMasterDataViewModel.class));

			resp.put("success", "1");
			resp.put("data", fetchdata);
			resp.put("error", "");
			resp.put("message", "Record added succesfully!...");

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
		}

		return resp;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordByOperator(JSONObject json, int company_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			String view = json.getString("viewName");
			String colName = json.getString("colName");
			String identityValue = json.getString("identityValue");
			String operator = json.getString("operator");
			List<Object> queryParams = new ArrayList<>();

			StringBuilder query = new StringBuilder("SELECT field_id, field_name FROM ").append(view).append(" WHERE ");
			if (!notIncludedCompanyIdList.contains(view.trim())) {
				query.append("company_id = ? ");
				queryParams.add(company_id);
			}

			if (colName != null && identityValue != null && !colName.isEmpty() && !identityValue.isEmpty()) {
				if (query.toString().trim().endsWith("?")) {
					query.append(" AND ");
				}
				query.append(colName).append(" ").append(operator).append(" ?");
				queryParams.add(identityValue);
			}

			if (!colName.equals("is_delete") || colName == null || colName.isEmpty()) {
				if (query.toString().trim().endsWith("?")) {
					query.append(" AND ");
				}
				query.append(" is_delete = 0");
			}

			if ((!colName.equals("is_active") || colName == null || colName.isEmpty()) && (view.equals("cmv_employee") || view.equals("cm_employee"))) {
				if (query.toString().trim().endsWith("?")) {
					query.append(" AND ");
				}
				query.append(" is_active = 1");
			}

			System.out.println("FnShowParticularRecordByOperator: " + query);

			List<CMasterDataViewModel> fetchdata = jdbcTemplate.query(query.toString(), queryParams.toArray(),
					new BeanPropertyRowMapper<>(CMasterDataViewModel.class));

			resp.put("success", "1");
			resp.put("data", fetchdata);
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
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}

		return resp;
	}

	@Override
//	@Cacheable(value = "catcher", key = "#json.toString()")
	public List<Map<String, Object>> FnShowRecords(JSONObject json, CGlobalQueryObjectModel globalQuery) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		try {
			StringBuilder queryBuilder = new StringBuilder();

			// Append the operation
			if ("select".equalsIgnoreCase(globalQuery.operation)) {
				queryBuilder.append("SELECT ");
				queryBuilder.append(String.join(", ", globalQuery.columns));
				queryBuilder.append(" FROM ");
				queryBuilder.append(globalQuery.table);

				// Append joins
				for (CJoinModel join : globalQuery.joins) {
					queryBuilder.append(" ").append(join.type.toUpperCase()).append(" JOIN ").append(join.table);
					List<CJoinConditionModel> cJoinConditionModels = join.getOn();
					if (cJoinConditionModels.size() != 0) {
						IntStream.range(0, cJoinConditionModels.size()).forEach(index -> {
							CJoinConditionModel joinItem = cJoinConditionModels.get(index);
							if (!joinItem.left.equals("") && !joinItem.right.equals("")) {
								if (index == 0)
									queryBuilder.append(" ON ");
//								queryBuilder.append(joinItem.left).append(" = ").append(joinItem.right);
								queryBuilder.append(joinItem.left).append(joinItem.operator == null ? " = " : " " + joinItem.operator + " ").append(joinItem.right);

								// You can use 'index' here for whatever purposes you need
							}
							if (index != cJoinConditionModels.size() - 1 && cJoinConditionModels.size() > 1) {
								queryBuilder.append(" AND ");
							}
						});

					}

				}

				//          Append conditions
				if (!globalQuery.conditions.isEmpty()) {
					queryBuilder.append(" WHERE ");
					for (int i = 0; i < globalQuery.conditions.size(); i++) {
						CConditionModel condition = globalQuery.conditions.get(i);
						queryBuilder.append(condition.field).append(" ").append(condition.operator);

						if ("IN".equalsIgnoreCase(condition.operator)
								|| "NOT IN".equalsIgnoreCase(condition.operator)) {
							queryBuilder.append(" (");
							// Enclose string values with quotes
							List<String> formattedValues = condition.values.stream()
									.map(value -> isNumeric(value) ? value : "'" + value + "'")
									.collect(Collectors.toList());
							queryBuilder.append(String.join(", ", formattedValues));
							queryBuilder.append(")");
						} else if ("LIKE".equalsIgnoreCase(condition.operator)) {
							queryBuilder.append(" '").append(condition.value).append("'");
						} else if ("IS NOT NULL".equalsIgnoreCase(condition.operator)) {
							queryBuilder.append(" ");
//							.append(condition.value).append("'");
						} else {
							if (condition.field.contains("is_delete")) {
								queryBuilder.append(" ").append(condition.value).append(" ");
							} else {
								queryBuilder.append(" '").append(condition.value).append("'");

							}
						}
						if (i < globalQuery.conditions.size() - 1) {
							queryBuilder.append(" AND ");
						}
					}
				}

				if (globalQuery.conditions.isEmpty() && (globalQuery.table.equals("cmv_employee") || globalQuery.table.equals("cm_employee"))){
					queryBuilder.append(" WHERE ");
					queryBuilder.append("is_active").append(" ").append("=");
					queryBuilder.append(" ").append(1).append(" ");
				}else if((globalQuery.table.equals("cmv_employee") || globalQuery.table.equals("cm_employee"))){
					queryBuilder.append(" AND is_active").append(" ").append("=");
					queryBuilder.append(" ").append(1).append(" ");
				}

				// Add GROUP BY clause
				if (globalQuery.groupBy != null && !globalQuery.groupBy.isEmpty()) {
					queryBuilder.append(" GROUP BY ");
					queryBuilder.append(String.join(", ", globalQuery.groupBy));
				}

				// Add ORDER BY clause
				if (globalQuery.orderBy != null && !globalQuery.orderBy.isEmpty()) {
					queryBuilder.append(" ORDER BY ");
					queryBuilder.append(String.join(", ", globalQuery.orderBy));
				}

			}
			System.out.println("globalJsonQueryExecution query: " + queryBuilder);
			dataList = jdbcTemplate.queryForList(queryBuilder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;

	}

	@Override
//	@CacheEvict(value = "catcher", allEntries = true)
	public List<Map<String, Object>> FnEvictRecords(JSONObject json, CGlobalQueryObjectModel globalQuery) {
		System.out.println("Catche evicted!...");
		return FnShowRecords(json, globalQuery);
	}

	@Override
	public Map<String, Object> FnShowMaterialItems(JSONObject materialSearchObject) { //search item filter
		Map<String, Object> resp = new HashMap<>();
		try {
			boolean isMainGodown = materialSearchObject.getBoolean("isMainGodown");
			Integer companyId = materialSearchObject.getInt("company_id");
			Integer product_type_id = materialSearchObject.getInt("product_type_id");
			String godownId = materialSearchObject.getString("godown_id");
			String searchInput = materialSearchObject.getString("searchInput");
			Integer productCategory2Id = materialSearchObject.getInt("product_category2_id");
			Integer productCategory1Id = materialSearchObject.getInt("product_category1_id");


			List<Object> queryParams = new ArrayList<>();
			StringBuilder query = new StringBuilder();
			query.append("SELECT rm_fg_sr.*, ");
			query.append("IFNULL((SELECT SUM(st.closing_balance_quantity) ");
			query.append("FROM sm_product_rm_stock_details st ");
			query.append("WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			if (isMainGodown) {
				query.append(" AND st.godown_id = ").append(godownId);
			}
			query.append(" AND st.company_id = ").append(companyId);
			query.append(" AND st.day_closed = ").append(0);

			query.append(" ), 0) AS closing_balance_quantity, ");

			query.append("IFNULL((SELECT SUM(st.closing_balance_weight) ");
			query.append("FROM sm_product_rm_stock_details st ");
			query.append("WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			if (isMainGodown) {
				query.append(" AND st.godown_id = ").append(godownId);
			}
			query.append(" AND st.company_id = ").append(companyId);
			query.append(" AND st.day_closed = ").append(0);
			query.append(" ), 0) AS closing_balance_weight, ");

//			query.append("IFNULL((SELECT st.material_rate ");
//			query.append("FROM sm_product_rm_stock_summary st ");
//			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
//			if (isMainGodown) {
//				query.append(" AND st.godown_id = ").append(godownId);
//			}
//			query.append(" AND st.company_id = ").append(companyId);
//			query.append("), 0) AS material_rate, ");

			query.append("IFNULL((SELECT SUM(st.reserve_quantity) ");
			query.append("FROM sm_product_rm_stock_details st ");
			query.append("WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			if (isMainGodown) {
				query.append(" AND st.godown_id = ").append(godownId);
			}
			query.append(" AND st.company_id = ").append(companyId);
			query.append(" AND st.day_closed = ").append(0);
			query.append(" ), 0) AS reserve_quantity, ");

			query.append("IFNULL((SELECT SUM(st.reserve_weight) ");
			query.append("FROM sm_product_rm_stock_details st ");
			query.append("WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			if (isMainGodown) {
				query.append(" AND st.godown_id = ").append(godownId);
			}
			query.append(" AND st.company_id = ").append(companyId);
			query.append(" AND st.day_closed = ").append(0);
			query.append(" ), 0) AS reserve_weight ");

			query.append("FROM smv_product_rm_fg_sr AS rm_fg_sr ");
			query.append(" WHERE rm_fg_sr.is_delete = '0' AND ");
			if (product_type_id != 0) {
				query.append("rm_fg_sr.product_type_id = ");
				query.append(product_type_id);
				query.append(" AND ");
			}
			if (productCategory1Id != 0) {
				query.append("rm_fg_sr.product_material_category1_id = ");
				query.append(productCategory1Id);
				query.append(" AND ");
			}
			if (productCategory2Id != 0) {
				query.append("rm_fg_sr.product_material_category2_id = ");
				query.append(productCategory2Id);
				query.append(" AND ");
			}


			query.append("(rm_fg_sr.product_material_code").append(" LIKE ?");
			queryParams.add("%" + searchInput);

			if (query.length() > "SELECT * FROM ".length()) {
				query.append(" OR ");
				query.append("rm_fg_sr.product_material_name")
						.append(" LIKE ?");
				queryParams.add("%" + searchInput + "%");
				query.append(")");
			}

			System.out.println("getSearch data: " + query);
			List<Map<String, Object>> fetchdata = jdbcTemplate.query(query.toString(), queryParams.toArray(), new ColumnMapRowMapper());

			resp.put("success", "1");
			resp.put("data", fetchdata);
			resp.put("error", ""); // No error
			resp.put("message", "Records fetched successfully");

		} catch (DataAccessException e) {
			resp.put("success", "0");
			resp.put("data", ""); // No data in case of failure
			resp.put("error", "Database access error: " + e.getMessage());

		} catch (JSONException e) {
			resp.put("success", "0");
			resp.put("data", ""); // No data in case of failure
			resp.put("error", "JSON parsing error: " + e.getMessage());

		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("data", ""); // No data in case of failure
			resp.put("error", "Unexpected error: " + e.getMessage());
		}
		return resp;
	}

	@Override
	public Map<String, Object> FnShowSearchMaterialItems(JSONObject materialSearchObject) {
		Map<String, Object> resp = new HashMap<>();
		try {
			String searchInput = (String) materialSearchObject.get("searchInput");
			Integer companyId = materialSearchObject.getInt("company_id");
			int godownId = materialSearchObject.getInt("godown_id");
			Integer productTypeId = materialSearchObject.getInt("product_type_id");
			Integer productCategory1Id = materialSearchObject.getInt("product_category1_id");
			Integer productCategory2Id = materialSearchObject.getInt("product_category2_id");
			MapSqlParameterSource params = new MapSqlParameterSource();
			StringBuilder query = new StringBuilder();

			query.append(" SELECT rm_fg_sr.*, ");
			query.append(" IFNULL((SELECT SUM(st.closing_balance_quantity) ");
			query.append(" FROM sm_product_rm_stock_details st ");
			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			query.append(" AND st.company_id = :companyId ");
			params.addValue("companyId", companyId);
			query.append(" AND st.day_closed = :dayClosed ");
			params.addValue("dayClosed", 0);

			if (godownId != 0) {
				query.append(" AND st.godown_id = :godownId ");
				params.addValue("godownId", godownId);
			}
			query.append( "), 0) AS closing_balance_quantity, ");
			query.append(" IFNULL((SELECT SUM(st.closing_balance_weight) ");

			query.append(" FROM sm_product_rm_stock_details st ");
			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			query.append(" AND st.company_id = :companyId ");
			params.addValue("companyId", companyId);
			query.append(" AND st.day_closed = :dayClosed ");
			params.addValue("dayClosed", 0);

			if (godownId != 0) {
				query.append(" AND st.godown_id = :godownId ");
				params.addValue("godownId", godownId);
			}
			query.append( "), 0) AS closing_balance_weight, ");
			//-------------- total_box_weight -----//
			query.append(" IFNULL((SELECT SUM(st.total_box_weight) ");
			query.append(" FROM sm_product_rm_stock_details st ");
			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			query.append(" AND st.company_id = :companyId ");
			params.addValue("companyId", companyId);
			query.append(" AND st.day_closed = :dayClosed ");
			params.addValue("dayClosed", 0);

			if (godownId != 0) {
				query.append(" AND st.godown_id = :godownId ");
				params.addValue("godownId", godownId);
			}
			query.append( "), 0) AS total_box_weight, ");
			//-------------- total_quantity_in_box -----//
			query.append(" IFNULL((SELECT SUM(st.total_quantity_in_box) ");
			query.append(" FROM sm_product_rm_stock_details st ");
			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			query.append(" AND st.company_id = :companyId ");
			params.addValue("companyId", companyId);
			query.append(" AND st.day_closed = :dayClosed ");
			params.addValue("dayClosed", 0);

			if (godownId != 0) {
				query.append(" AND st.godown_id = :godownId ");
				params.addValue("godownId", godownId);
			}
			query.append( "), 0) AS total_quantity_in_box, ");
			//-------------- weight_per_box_item -----//
			query.append(" IFNULL((SELECT SUM(st.weight_per_box_item) ");
			query.append(" FROM sm_product_rm_stock_details st ");
			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			query.append(" AND st.company_id = :companyId ");
			params.addValue("companyId", companyId);
			query.append(" AND st.day_closed = :dayClosed ");
			params.addValue("dayClosed", 0);

			if (godownId != 0) {
				query.append(" AND st.godown_id = :godownId ");
				params.addValue("godownId", godownId);
			}
			query.append( "), 0) AS weight_per_box_item, ");

//			query.append("IFNULL((SELECT st.material_rate ");
//			query.append("FROM sm_product_rm_stock_summary st ");
//			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
//			if (godownId != 0) {
//				query.append(" AND st.godown_id = :godownId ");
//				params.addValue("godownId", godownId);
//			}
//			query.append(" AND st.company_id = ").append(companyId);
//			query.append("), 0) AS material_rate, ");

			query.append(" IFNULL((SELECT SUM(st.reserve_quantity) ");
			query.append(" FROM sm_product_rm_stock_details st ");
			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			query.append(" AND st.company_id = :companyId ");
			params.addValue("companyId", companyId);
			query.append(" AND st.day_closed = :dayClosed ");
			params.addValue("dayClosed", 0);

			if (godownId != 0) {
				query.append(" AND st.godown_id = :godownId ");
				params.addValue("godownId", godownId);
			}
			query.append( "), 0) AS reserve_quantity, ");

			query.append(" IFNULL((SELECT SUM(st.reserve_quantity) ");
			query.append(" FROM sm_product_rm_stock_details st ");
			query.append(" WHERE st.product_rm_id = rm_fg_sr.product_material_id ");
			query.append(" AND st.company_id = :companyId ");
			params.addValue("companyId", companyId);
			query.append(" AND st.day_closed = :dayClosed ");
			params.addValue("dayClosed", 0);

			if (godownId != 0) {
				query.append(" AND st.godown_id = :godownId ");
				params.addValue("godownId", godownId);
			}
			query.append( "), 0) AS reserve_weight ");

			query.append(" FROM smv_product_rm_fg_sr AS rm_fg_sr ");
			query.append(" WHERE rm_fg_sr.is_delete = '0' ");
			params.addValue("searchNameInput", "%" + searchInput + "%");
			params.addValue("searchCodeInput", "%" + searchInput + "%");

			if (productTypeId != 0) {
				query.append(" AND rm_fg_sr.product_type_id = :productTypeId ");
				params.addValue("productTypeId", productTypeId);
			}
			if (productCategory1Id != 0) {
				query.append(" AND rm_fg_sr.product_material_category1_id = :productCategory1Id ");
				params.addValue("productCategory1Id", productCategory1Id);
			}
			if (productCategory2Id != 0) {
				query.append(" AND rm_fg_sr.product_material_category2_id = :productCategory2Id ");
				params.addValue("productCategory2Id", productCategory2Id);
			}

			query.append(" AND (rm_fg_sr.product_material_code LIKE :searchCodeInput ");
			query.append(" OR rm_fg_sr.product_material_name LIKE :searchNameInput)");

			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
			List<Map<String, Object>> fetchData = namedParameterJdbcTemplate.queryForList(query.toString(), params);
			System.out.println("search Query" + query);
			resp.put("success", "1");
			resp.put("data", fetchData);
			resp.put("error", ""); // No error
			resp.put("message", "Records fetched successfully");

		} catch (DataAccessException e) {
			resp.put("success", "0");
			resp.put("data", ""); // No data in case of failure
			resp.put("error", "Database access error: " + e.getMessage());

		} catch (JSONException e) {
			resp.put("success", "0");
			resp.put("data", ""); // No data in case of failure
			resp.put("error", "JSON parsing error: " + e.getMessage());

		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("data", ""); // No data in case of failure
			resp.put("error", "Unexpected error: " + e.getMessage());
		}
		return resp;
	}
}
