package com.erp.PtIssueReport;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.erp.StoreStockReport.Controller.CStoreStockReport.exportToExcel;

@RestController
@RequestMapping("/api/IsuueReport")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CissueReport {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostMapping("/getStoresIssueReport/{reportType}/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> MaterialTransactions(@RequestParam("commonIds") JSONObject commonIdsObj , @PathVariable("reportType") String reportType) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> materialTransactionByProcedure = new ArrayList<>();
        try {
            Integer company_id = commonIdsObj.getInt("company_id");
            String from_date = commonIdsObj.getString("from_date");
            String to_date = commonIdsObj.getString("to_date");
            String costCenterId = commonIdsObj.getString("cost_center_id");
            String productTypeId = commonIdsObj.getString("productTypeId");
            Integer PageCurrent = commonIdsObj.getInt("PageCurrent");
            Integer entriesPerPage = commonIdsObj.getInt("entriesPerPage");

            if(reportType.equalsIgnoreCase("summary")) {
                materialTransactionByProcedure = CallGenerateIssueSummaryReport(from_date, to_date, company_id, costCenterId, productTypeId, PageCurrent, entriesPerPage);
            }else {
                // Define the stored procedure cal
                materialTransactionByProcedure = CallGenerateIssueReport(from_date, to_date, company_id, costCenterId, productTypeId, PageCurrent, entriesPerPage);
            }
            response.put("success", 1);
            response.put("issueReportData", materialTransactionByProcedure);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }

    private List<Map<String, Object>> CallGenerateIssueSummaryReport(String fromDate, String toDate, Integer company_id, String costCenterId, String productTypeId, Integer PageCurrent, Integer entriesPerPage) {
        List<Map<String, Object>> getData = new ArrayList<>();
        String storedProcedure = "{call GenerateIssueReportSummary(?,?,?,?,?,?,?)}";

        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall(storedProcedure);
                    cs.setString(1, fromDate);
                    cs.setString(2, toDate);
                    cs.setInt(3, company_id);
                    cs.setString(4, costCenterId);
                    cs.setString(5, productTypeId);
                    cs.setInt(6, PageCurrent);
                    cs.setInt(7, entriesPerPage);
                    return cs;
                },
                (CallableStatementCallback<List<Map<String, Object>>>) cs -> {
                    cs.execute();

                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs != null && rs.next()) {
                            Map<String, Object> resultMap = new HashMap<>();
//                            resultMap.put("department_name", rs.getObject("department_name"));
                            resultMap.put("sub_department_name", rs.getObject("sub_department_name"));
                            resultMap.put("profit_center_short_name", rs.getObject("profit_center_short_name"));
                            resultMap.put("cost_center_name", rs.getObject("cost_center_name"));
                            resultMap.put("product_category1_name", rs.getObject("product_category1_name"));
                            resultMap.put("product_category2_name", rs.getObject("product_category2_name"));
                            resultMap.put("issue_quantity", rs.getObject("total_issue_quantity"));
                            resultMap.put("batch_rate", rs.getObject("average_batch_rate"));
                            resultMap.put("material_value", rs.getObject("total_material_value"));
                            resultMap.put("company_id", rs.getObject("company_id"));
                            resultMap.put("total_count", rs.getObject("total_count"));
                            getData.add(resultMap);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("MaterialTransactionData: " + getData);
                    return getData; // Returning the entire list instead of a single map
                }
        );
    }

    private List<Map<String, Object>> CallGenerateIssueReport(String fromDate, String toDate, Integer company_id, String costCenterId, String productTypeId, Integer PageCurrent, Integer entriesPerPage) {
        List<Map<String, Object>> getData = new ArrayList<>();
        String storedProcedure = "{call GenerateIssueReport(?,?,?,?,?,?,?)}";

        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall(storedProcedure);
                    cs.setString(1, fromDate);
                    cs.setString(2, toDate);
                    cs.setInt(3, company_id);
                    cs.setString(4, costCenterId);
                    cs.setString(5, productTypeId);
                    cs.setInt(6, PageCurrent);
                    cs.setInt(7, entriesPerPage);
                    return cs;
                },
                (CallableStatementCallback<List<Map<String, Object>>>) cs -> {
                    cs.execute();

                    try (ResultSet rs = cs.getResultSet()) {
                        int serialNo = 1; // Initialize a counter for serial number

                        while (rs != null && rs.next()) {
                            Map<String, Object> resultMap = new HashMap<>();
                            resultMap.put("serial_no", serialNo); // Add the serial number
                            resultMap.put("profit_center_short_name", rs.getObject("profit_center_short_name"));
                            resultMap.put("profit_center_name", rs.getObject("profit_center_name"));
                            resultMap.put("cost_center_name", rs.getObject("cost_center_name"));
                            resultMap.put("department_name", rs.getObject("department_name"));
                            resultMap.put("sub_department_name", rs.getObject("sub_department_name"));
                            resultMap.put("product_category1_name", rs.getObject("product_category1_name"));
                            resultMap.put("product_category2_name", rs.getObject("product_category2_name"));
                            resultMap.put("material_name", rs.getObject("material_name"));
                            resultMap.put("material_code", rs.getObject("material_code"));
                            resultMap.put("material_unit_name", rs.getObject("material_unit_name"));
                            resultMap.put("issue_quantity", rs.getObject("issue_quantity"));
                            resultMap.put("batch_rate", rs.getObject("batch_rate"));
                            resultMap.put("material_value", rs.getObject("material_value"));
                            resultMap.put("issue_no", rs.getObject("issue_no"));
                            resultMap.put("issue_date", rs.getObject("issue_date"));
                            resultMap.put("created_on", rs.getObject("created_on"));
                            resultMap.put("company_id", rs.getObject("company_id"));
                            resultMap.put("total_count", rs.getObject("total_count"));
                            getData.add(resultMap);
                            serialNo++; // Increment the serial number for the next iteration
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//                    System.out.println("MaterialTransactionData: " + getData);
                    return getData; // Returning the entire list instead of a single map
                }
        );
    }

    @PostMapping("/getExportIssueReport")
    public void ExcelIssueReport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {

        List<Map<String, Object>> materialTransactionByProcedure = new ArrayList<>();
        try {
            Integer company_id = commonIdsObj.getInt("company_id");
            String from_date = commonIdsObj.getString("from_date");
            String to_date = commonIdsObj.getString("to_date");
            String productTypeId = commonIdsObj.getString("productTypeId");
            String costCenterId = commonIdsObj.getString("cost_center_id");
            String reportType = commonIdsObj.getString("reportType");

            if(reportType.equalsIgnoreCase("summary")) {
                materialTransactionByProcedure = CallGenerateIssueSummaryReport(from_date, to_date, company_id, costCenterId, productTypeId,0,0);
            }else {
                // Define the stored procedure cal
                materialTransactionByProcedure = CallGenerateIssueReport(from_date, to_date, company_id, costCenterId, productTypeId,0 ,0);
            }

            Map<String, String> summaryColumns = new LinkedHashMap<>();
            summaryColumns.put("serial_no","Sr. No.");
            summaryColumns.put("sub_department_name","Sub-Department Name");
            summaryColumns.put("cost_center_name","Cost Center");
            summaryColumns.put("product_category1_name","Category1 Name");
            summaryColumns.put("product_category2_name","Category2 Name");
            summaryColumns.put("issue_quantity","Issued Qty");
            summaryColumns.put("batch_rate","Rate");
            summaryColumns.put("material_value","Value");

            // Details Table Columns and Accessors.
            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("department_name", "Department Name");
            detailsColumns.put("sub_department_name", "Sub-Department Name");
            detailsColumns.put("profit_center_short_name", "Profit Center");
            detailsColumns.put("cost_center_name", "Cost Center");
            detailsColumns.put("product_category1_name", "Category1 Name");
            detailsColumns.put("product_category2_name", "Category2 Name");
            detailsColumns.put("material_code", "Material Code");
            detailsColumns.put("material_name", "Material Name");
            detailsColumns.put("material_unit_name", "Unit");
            detailsColumns.put("issue_quantity", "Issued Qty");
            detailsColumns.put("batch_rate", "Rate");
            detailsColumns.put("material_value", "Value");
            detailsColumns.put("issue_no", "Issue No");
            detailsColumns.put("issue_date", "Issue Date");

            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList("issue_quantity","material_value"));

            exportToExcel(materialTransactionByProcedure,  reportType.equalsIgnoreCase("summary") ? summaryColumns : detailsColumns, totalsColumnHeader, response, commonIdsObj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
