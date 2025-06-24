package com.erp.Common.Filter;

import com.erp.Common.MasterData.Model.CConditionModel;
import com.erp.Common.MasterData.Model.CGlobalQueryObjectModel;
import com.erp.Common.MasterData.Model.CJoinConditionModel;
import com.erp.Common.MasterData.Model.CJoinModel;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class DynamicQueryController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostMapping("/FnShowRecords")
	public Map<String, Object> FnShowRecords(@RequestParam("data") JSONObject json, HttpServletRequest req) throws IOException {
//		String jsonData = json.toString();
//
//		Gson gson = new Gson();
//		CGlobalQueryObjectModel globalQuery = gson.fromJson(jsonData, CGlobalQueryObjectModel.class);
//
//		List<Map<String, Object>> dataList = new ArrayList<>();
//		try {
//			StringBuilder queryBuilder = new StringBuilder();
//
//			// Append the operation
//			if ("select".equalsIgnoreCase(globalQuery.getOperation())) {
//				queryBuilder.append("SELECT ");
//				queryBuilder.append(String.join(", ", globalQuery.getColumns()));
//				queryBuilder.append(" FROM ");
//				queryBuilder.append(globalQuery.getTable());
//
//				// Append joins
//				for (CJoinModel join : globalQuery.getJoins()) {
//					queryBuilder.append(" ").append(join.getType().toUpperCase()).append(" JOIN ").append(join.getTable());
//					List<CJoinConditionModel> cJoinConditionModels = join.getOn();
//					if (!cJoinConditionModels.isEmpty()) {
//						queryBuilder.append(" ON ");
//						queryBuilder.append(cJoinConditionModels.stream()
//								.map(joinItem -> joinItem.getLeft() +
//										(joinItem.getOperator() == null ? " = " : " " + joinItem.getOperator() + " ") +
//										joinItem.getRight())
//								.collect(Collectors.joining(" AND ")));
//					}
//				}
//
//				// Append conditions
//				if (!globalQuery.getConditions().isEmpty()) {
//					queryBuilder.append(" WHERE ");
//					for (int i = 0; i < globalQuery.getConditions().size(); i++) {
//						CConditionModel condition = globalQuery.getConditions().get(i);
//						queryBuilder.append(buildConditionString(condition));
//
//						// Append logical operator (AND/OR) if present
//						if (i < globalQuery.getConditions().size() - 1) {
//							String logicalOperator = condition.getLogicalOperator();
//							if (logicalOperator != null && !logicalOperator.isEmpty()) {
//								queryBuilder.append(" ").append(logicalOperator).append(" ");
//							} else {
//								queryBuilder.append(" AND ");
//							}
//						}
//					}
//				}
//
//				// Add GROUP BY clause
//				if (globalQuery.getGroupBy() != null && !globalQuery.getGroupBy().isEmpty()) {
//					queryBuilder.append(" GROUP BY ");
//					queryBuilder.append(String.join(", ", globalQuery.getGroupBy()));
//				}
//
//				// Add ORDER BY clause
//				if (globalQuery.getOrderBy() != null && !globalQuery.getOrderBy().isEmpty()) {
//					queryBuilder.append(" ORDER BY ");
//					queryBuilder.append(String.join(", ", globalQuery.getOrderBy()));
//				}
//
//				// Add LIMIT and OFFSET for pagination
//				if (json.has("limit")) {
//					queryBuilder.append(" LIMIT ").append(json.getInt("limit"));
//				}
//				if (json.has("offset")) {
//					queryBuilder.append(" OFFSET ").append(json.getInt("offset"));
//				}
//			}
//
//			System.out.println("globalJsonQueryExecution query: " + queryBuilder);
//			dataList = jdbcTemplate.queryForList(queryBuilder.toString());
//			System.out.println("dataList: - " + dataList.toString());
//	} catch (Exception e) {
//			e.printStackTrace();
//		}
//

		return new HashMap<>();
//		return iMasterDataService.FnShowRecords(json, globalQuery);
	}

	private String buildConditionString(CConditionModel condition) {
		StringBuilder conditionBuilder = new StringBuilder();
		conditionBuilder.append(condition.getField()).append(" ").append(condition.getOperator());

		if ("IN".equalsIgnoreCase(condition.getOperator()) || "NOT IN".equalsIgnoreCase(condition.getOperator())) {
			conditionBuilder.append(" (");
			List<String> formattedValues = condition.getValues().stream()
					.map(value -> isNumeric(value) ? value : "'" + value + "'")
					.collect(Collectors.toList());
			conditionBuilder.append(String.join(", ", formattedValues));
			conditionBuilder.append(")");
		} else if ("LIKE".equalsIgnoreCase(condition.getOperator())) {
			conditionBuilder.append(" '").append(condition.getValue()).append("'");
		} else if ("IS NOT NULL".equalsIgnoreCase(condition.getOperator()) || "IS NULL".equalsIgnoreCase(condition.getOperator())) {
			// Just append the operator, no value needed
		} else {
			conditionBuilder.append(" '").append(condition.getValue()).append("'");
		}

		return conditionBuilder.toString();
	}
}
