package com.erp.SmProductFgRmStockDetailsSummary.Service;

import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ISmProductFgStockDetailsSummaryService {

	Map<String, Object> FnShowAllFinishGoodsReportRecords(Pageable pageable, String reportType);

	Map<String, Object> FnShowAllRawMaterialsReportRecords(Pageable pageable, String reportType);

}
