package com.erp.XtWeavingProductionLoomMaster.Controller;

import com.erp.StoreStockReport.Controller.CStoreStockReport;
import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsModel;
import com.erp.XtWeavingProductionLoomMaster.Service.IXtWeavingProductionLoomMasterService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.math.RoundingMode;


@RestController
@RequestMapping("/api/XtWeavingProductionLoomMaster")
public class CXtWeavingProductionLoomMasterController {


    @Autowired
    CStoreStockReport cStoreStockReport;

    @Autowired
    IXtWeavingProductionLoomMasterService iXtWeavingProductionLoomMasterService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostMapping("/FnAddUpdateRecord")
    public Map<String, Object> FnAddUpdateRecord(@RequestParam("WeavingProductionLoomData") JSONObject jsonObject) {
        Map<String, Object> responce = iXtWeavingProductionLoomMasterService.FnAddUpdateRecord(jsonObject);
        return responce;

    }

    @DeleteMapping("/FnDeleteRecord/{weaving_production_loom_master_id}/{company_id}/{deleted_by}")
    public Map<String, Object> FnDeleteRecord(@PathVariable int weaving_production_loom_master_id, @PathVariable int company_id, String deleted_by) {
        return iXtWeavingProductionLoomMasterService.FnDeleteRecord(weaving_production_loom_master_id, company_id, deleted_by);

    }


    @GetMapping("/FnShowParticularRecordForUpdate/{weaving_production_loom_master_id}/{company_id}")
    public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int weaving_production_loom_master_id, @PathVariable int company_id) {
        Map<String, Object> responce = iXtWeavingProductionLoomMasterService.FnShowParticularRecordForUpdate(weaving_production_loom_master_id, company_id);
        return responce;
    }


    @GetMapping("/FnGetLoomProductionData/{from_date}/{to_date}/{company_id}/{loom_no}")
    public ArrayList<Map<String, Object>> FnGetLoomProductionData(
            @PathVariable String from_date,
            @PathVariable String to_date,
            @PathVariable Integer company_id,
            @PathVariable String loom_no,
            @RequestParam String sort_no) {

        ArrayList<Map<String, Object>> masterData = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder("SELECT * FROM xt_weaving_production_loom_details AS xwpid WHERE xwpid.is_delete = 0 AND xwpid.company_id = ? ");
            List<Object> params = new ArrayList<>();
            params.add(company_id);

            if (from_date.equals(to_date)) {
                query.append("AND xwpid.loom_production_date = ? ");
                params.add(from_date);
            } else {
                query.append("AND xwpid.loom_production_date BETWEEN ? AND ? ");
                params.add(from_date);
                params.add(to_date);
            }

            if (!"loom_no".equals(loom_no) && !loom_no.isEmpty()) {
                query.append("AND xwpid.machine_id = ? ");
                params.add(loom_no);
            }

            if (!"sort_no".equals(sort_no) && !sort_no.isEmpty()) {
                query.append("AND xwpid.sort_no = ? ");
                params.add(sort_no);
            }

            masterData = new ArrayList<>(jdbcTemplate.queryForList(query.toString(), params.toArray()));

        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());

            masterData.clear();
            masterData.add(errorMap);
        }
        return masterData;
    }


    @GetMapping("/FnGetLoomProductionStoppageData/{from_date}/{to_date}/{company_id}/{production_stoppage_reasons_name}/{production_stoppage_reasons_type}/{sub_section_id}")
    public ArrayList<Map<String, Object>> FnGetLoomProductionStoppageData(
            @PathVariable String from_date,
            @PathVariable String to_date,
            @PathVariable Integer company_id,
            @PathVariable String production_stoppage_reasons_name,
            @PathVariable String production_stoppage_reasons_type,
            @PathVariable Integer sub_section_id
    ) {
        ArrayList<Map<String, Object>> masterData = new ArrayList<>();
        try {
            List<Object> params = new ArrayList<>();
            StringBuilder query = new StringBuilder("SELECT xpsr.production_stoppage_reasons_name, xpsr.sub_section_id, xpsr.production_stoppage_reasons_type, COALESCE(SUM(xwpws.total_time), 0) AS total_time ");
            query.append("FROM xm_production_stoppage_reasons xpsr ");
            query.append("LEFT JOIN xtv_weaving_production_warping_stoppage xwpws ");
            query.append("  ON xpsr.production_stoppage_reasons_name = xwpws.production_stoppage_reasons_name ");
            query.append("  AND xwpws.is_delete = 0 ");

            // Handle date parameters
            if (from_date.equals(to_date)) {
                query.append("  AND xwpws.production_date = ? ");
                params.add(from_date);
            } else {
                query.append("  AND xwpws.production_date BETWEEN ? AND ? ");
                params.add(from_date);
                params.add(to_date);
            }

            // WHERE clause with dynamic company_id
            query.append("WHERE xpsr.section_id = 18 ");
            query.append("  AND xpsr.is_delete = 0 ");
            query.append("  AND xpsr.company_id = ? ");
            params.add(company_id); // Add company_id to params

            // Other filters
            if (!production_stoppage_reasons_name.equals("All")) {
                query.append(" AND xpsr.production_stoppage_reasons_name = ? ");
                params.add(production_stoppage_reasons_name);
            }
            if (!production_stoppage_reasons_type.equals("All")) {
                query.append(" AND xpsr.production_stoppage_reasons_type = ? ");
                params.add(production_stoppage_reasons_type);
            }
            if (sub_section_id != 0) {
                query.append(" AND xpsr.sub_section_id = ? ");
                params.add(sub_section_id);
            }

            query.append(" GROUP BY xpsr.production_stoppage_reasons_name; ");

            masterData = new ArrayList<>(jdbcTemplate.queryForList(query.toString(), params.toArray()));
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorMap = new HashMap<>();
            errorMap.put("error", e.getMessage());
            masterData.clear();
            masterData.add(errorMap);
        }
        return masterData;
    }


    @PostMapping("/GetLoomProductionStoppageReportExport/{from_date}/{to_date}/{company_id}/{production_stoppage_reasons_name}/{production_stoppage_reasons_type}/{sub_section_id}")
    public void GetLoomProductionWastageReportExport(@PathVariable String from_date,
                                                     @PathVariable String to_date,
                                                     @PathVariable Integer company_id,
                                                     @PathVariable String production_stoppage_reasons_name,
                                                     @PathVariable String production_stoppage_reasons_type,
                                                     @PathVariable Integer sub_section_id,
                                                     @RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {


        List<Map<String, Object>> LoomProductionWastageReportExportData = new ArrayList<>();
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            JSONArray stoppageDetails = commonIdsObj.getJSONArray("StoppageDetails");

            List<CXmProductionStoppageReasonsModel> stoppageModels = objectMapper.readValue(stoppageDetails.toString(), new TypeReference<List<CXmProductionStoppageReasonsModel>>() {});

            Integer loomnolength = (Integer) commonIdsObj.getInt("loomnolength");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate fromDate = LocalDate.parse(from_date, formatter);
            LocalDate toDate = LocalDate.parse(to_date, formatter);

            long noOfDaysDifference = ChronoUnit.DAYS.between(fromDate, toDate);
            int effectiveDays = (int) noOfDaysDifference + 1;


            List<Object> params = new ArrayList<>();
            StringBuilder query = new StringBuilder("SELECT xpsr.production_stoppage_reasons_name, xpsr.sub_section_id, xpsr.production_stoppage_reasons_type, COALESCE(SUM(xwpws.total_time), 0) AS total_time ");
            query.append("FROM xm_production_stoppage_reasons xpsr ");
            query.append("LEFT JOIN xtv_weaving_production_warping_stoppage xwpws ");
            query.append("  ON xpsr.production_stoppage_reasons_name = xwpws.production_stoppage_reasons_name ");
            query.append("  AND xwpws.is_delete = 0 ");

            // Handle date parameters
            if (from_date.equals(to_date)) {
                query.append("  AND xwpws.production_date = ? ");
                params.add(from_date);
            } else {
                query.append("  AND xwpws.production_date BETWEEN ? AND ? ");
                params.add(from_date);
                params.add(to_date);
            }

            // WHERE clause with dynamic company_id
            query.append("WHERE xpsr.section_id = 18 ");
            query.append("  AND xpsr.is_delete = 0 ");
            query.append("  AND xpsr.company_id = ? ");
            params.add(company_id); // Add company_id to params

            // Other filters
            if (!production_stoppage_reasons_name.equals("All")) {
                query.append(" AND xpsr.production_stoppage_reasons_name = ? ");
                params.add(production_stoppage_reasons_name);
            }
            if (!production_stoppage_reasons_type.equals("All")) {
                query.append(" AND xpsr.production_stoppage_reasons_type = ? ");
                params.add(production_stoppage_reasons_type);
            }
            if (sub_section_id != 0) {
                query.append(" AND xpsr.sub_section_id = ? ");
                params.add(sub_section_id);
            }

            query.append(" GROUP BY xpsr.production_stoppage_reasons_name; ");

            LoomProductionWastageReportExportData = new ArrayList<>(jdbcTemplate.queryForList(query.toString(), params.toArray()));

            LoomProductionWastageReportExportData = LoomProductionWastageReportExportData.stream()
                    .map(data -> {

                        CXmProductionStoppageReasonsModel cXmProductionStoppageReasonsModel = stoppageModels.stream()
                                .filter(stoppage -> stoppage.getProduction_stoppage_reasons_name().equals(data.get("production_stoppage_reasons_name")))
                                .findFirst()
                                .orElse(null); // handle null if needed

                        List<String> stoppageNames = Arrays.asList("WARP BREAK", "WEFT CUT");

                        int machines = stoppageNames.contains(data.get("production_stoppage_reasons_name")) ? loomnolength : 1;

                        double totalTime = Double.parseDouble(data.get("total_time").toString());

                        double denominator = loomnolength * 1440 * effectiveDays;
                        double lossPercent = (totalTime / denominator) * 100;

                        double stdCalculation = 1440 * 48; // assumed fixed hours
                        double stdPercent = 0.0;

                        if (cXmProductionStoppageReasonsModel != null) {
                            double stdLossPerHour = cXmProductionStoppageReasonsModel.getStd_stoppage_loss_per_hour();
                            double stdMinutes = cXmProductionStoppageReasonsModel.getStd_stoppage_minutes();

                            stdPercent = ((stdLossPerHour * stdMinutes * machines) / stdCalculation) * 100;
                        }

                        data.put("loss_percent", BigDecimal.valueOf(lossPercent).setScale(4, RoundingMode.HALF_UP));
                        data.put("std_percent", BigDecimal.valueOf(stdPercent).setScale(2, RoundingMode.HALF_UP));

                        return data;
                    })
                    .collect(Collectors.toList());

            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("production_stoppage_reasons_type", "Stoppage Loss Type");
            detailsColumns.put("production_stoppage_reasons_name", "Stoppage Reason Name");
            detailsColumns.put("std_percent", "Std %");
            detailsColumns.put("total_time", "Total Time");
            detailsColumns.put("loss_percent", "Lost %");
            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(
                  "std_percent" , "total_time" , "loss_percent"
            ));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();
            // Export the data to Excel
            cStoreStockReport.exportToExcel(LoomProductionWastageReportExportData, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/GetStdNormsForLoomUtilizationReportData/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> GetStdNormsForLoomUtilizationReportData(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage) {
        List<Map<String, Object>> StdNormsForLoomData = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        Long total_count = 0L;
        long totalsOfStdPercentage = 0L;
        try {
            StdNormsForLoomData = iXtWeavingProductionLoomMasterService.GetStdNormsForLoomUtilizationReportData(commonIdsObj, PageCurrent, entriesPerPage);
            List<Map<String, Object>> stdNormSdata = iXtWeavingProductionLoomMasterService.GetStdNormsForLoomUtilizationReportData(commonIdsObj, 0, 100);
            if (!StdNormsForLoomData.isEmpty()) {
                total_count = (Long) StdNormsForLoomData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("StdNormsForLoomData", StdNormsForLoomData);
            response.put("stdNormSdata", stdNormSdata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
