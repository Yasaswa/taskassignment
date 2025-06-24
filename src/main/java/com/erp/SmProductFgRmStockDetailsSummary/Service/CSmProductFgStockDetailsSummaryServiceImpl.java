package com.erp.SmProductFgRmStockDetailsSummary.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CSmProductFgStockDetailsSummaryServiceImpl implements ISmProductFgStockDetailsSummaryService {


	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	private JdbcTemplate executeQuery;

	@Override
	public Map<String, Object> FnShowAllFinishGoodsReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		String query;
		if ("summary".equals(reportType)) {
			query = "SELECT * FROM smv_product_fg_stock_summary_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		} else {
			query = "SELECT * FROM smv_product_fg_stock_details_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		}
		return response;
	}

	@Override
	public Map<String, Object> FnShowAllRawMaterialsReportRecords(Pageable pageable, String reportType) {
		Map<String, Object> response = new HashMap<>();
		String query;
		if ("summary".equals(reportType)) {
			query = "SELECT * FROM smv_product_rm_stock_summary_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		} else {
			query = "SELECT * FROM smv_product_rm_stock_details_rpt";
			List<Map<String, Object>> results = executeQuery.queryForList(query.toString());
			response.put("content", results);
		}
		return response;
	}
}
