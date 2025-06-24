package com.erp.SmProductFgRmStockDetailsSummary.Controller;

import com.erp.SmProductFgRmStockDetailsSummary.Service.ISmProductFgStockDetailsSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/SmProductFgRmStockDetailsSummary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CSmProductFgStockDetailsSummaryController {

	@Autowired
	ISmProductFgStockDetailsSummaryService iSmProductFgStockDetailsSummaryService;

	@Autowired
	JdbcTemplate executequery;


	@GetMapping("/FnShowAllFinishGoodsReportRecords")
	public Map<String, Object> FnShowAllFinishGoodsReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iSmProductFgStockDetailsSummaryService.FnShowAllFinishGoodsReportRecords(pageable, reportType);

	}

	@GetMapping("/FnShowAllRawMaterialsReportRecords")
	public Map<String, Object> FnShowAllRawMaterialsReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iSmProductFgStockDetailsSummaryService.FnShowAllRawMaterialsReportRecords(pageable, reportType);

	}

}
