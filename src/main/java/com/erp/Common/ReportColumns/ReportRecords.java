package com.erp.Common.ReportColumns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/api/report")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportRecords {

	@Autowired
	private JdbcTemplate executeQuery;

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(@RequestParam("reportType") String reportType,
	                                                  @RequestParam("viewName") String viewName) {

		Map<String, Object> response = new HashMap<>();
		StringBuilder query = new StringBuilder();
		Set<String> headerKeys = null;
		Map<String, Object> result = null;
		try {
			query.append("SELECT * FROM ").append(viewName);
			result = executeQuery.queryForObject(query.toString(), new RowMapperForData());

			if (!result.isEmpty()) {
				headerKeys = result.keySet();
			}

			response.put("content", result);
			response.put("headerKeys", headerKeys);
		} catch (EmptyResultDataAccessException e) {
			// Handle the case where no results are returned
			response.put("content", 0);
			response.put("headerKeys", Collections.emptySet());
		}
		return response;
	}

	// RowMapper implementation to map the result set to a Map<String, Object>
	private static class RowMapperForData implements RowMapper<Map<String, Object>> {
		@Override
		public Map<String, Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			// Implement the mapping logic based on your database structure
			// For example, you can iterate over the ResultSet and populate a map
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			Map<String, Object> result = new LinkedHashMap<>();

			for (int i = 1; i <= columnCount; i++) {
				String columnName = metaData.getColumnName(i);
				Object columnValue = resultSet.getObject(i);
				result.put(columnName, columnValue);
			}

			return result;
		}
	}
}
