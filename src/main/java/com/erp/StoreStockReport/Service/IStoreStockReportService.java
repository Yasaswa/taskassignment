package com.erp.StoreStockReport.Service;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface IStoreStockReportService {


    List<Map<String, Object>> GetStockReportByDateSummary(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage, Integer company_id);


    List<Map<String, Object>> GetStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage, Integer company_id);

    List<Map<String, Object>> GetStockReportToExport(JSONObject commonIdsObj, String reportType);

    List<Map<String, Object>> GetRawMaterialStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage);

    List<Map<String, Object>> GetRawMaterialStockReportByDateDetailsToExport(JSONObject commonIdsObj);

    List<Map<String, Object>> GetYarnStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage);

    Map<String, Object>  GetYarnStockTotalDetails(JSONObject commonIdsObj);

    List<Map<String, Object>> GetYarnStockReportByDateDetailsToExport(JSONObject commonIdsObj);

    List<Map<String, Object>> getAgingStockDetailsExportExcel(JSONObject commonIdsObj);

    List<Map<String, Object>> GetFabricStockReport(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage);

    List<Map<String, Object>> GetFabricStockReportExport(JSONObject commonIdsObj);

    List<Map<String, Object>> CallGenerateAgingProcedure(JSONObject jsonObject, Integer page, Integer pageSize);

    List<Map<String, Object>> GetSizedBeamStockReportExport(JSONObject commonIdsObj);

    List<Map<String, Object>> CallGenerateAgingProcedureSummary(JSONObject commonIdsObj, Integer page, Integer pageSize);

    List<Map<String, Object>> GetCottonBaleStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage);

    List  <Map<String, Object>> GetCottonBaleStockReportTotals(JSONObject commonIdsObj);

    List<Map<String, Object>> GetCottonBaleStockReportByDateDetailsToExport(JSONObject commonIdsObj);

//    Map<String, Object> FnGetBalesWeightageReport(JSONObject jsonObject);

    List<Map<String, Object>> FnGetBalesWeightageReport(JSONObject jsonObject,  Integer pageCurrent, Integer entriesPerPage );

    List<Map<String, Object>> BottomDetailsStockReportByDateDetails(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage);

    List<Map<String, Object>> GetBottomDetailsStockReportByDateDetailsToExport(JSONObject commonIdsObj);

    List<Map<String, Object>> GetCottonBalesStockReportByDateDetailsToExport(JSONObject commonIdsObj);

    List<Map<String, Object>> GetSizedBeamStockReportData(JSONObject commonIdsObj, Integer pageCurrent, Integer entriesPerPage);

    List<Map<String, Object>> GetLoomProductionWastageReportData(JSONObject commonIdsObj);

}
    