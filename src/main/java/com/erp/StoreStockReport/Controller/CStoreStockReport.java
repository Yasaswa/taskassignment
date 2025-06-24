package com.erp.StoreStockReport.Controller;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.StoreStockReport.Service.IStoreStockReportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/StockReport")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CStoreStockReport {

    @Autowired
    CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
    @Autowired
    IStoreStockReportService iStoreStockReportService;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void exportToExcel(
            List<Map<String, Object>> data,
            Map<String, String> exportColumns,
            List<String> totalsColumnHeader,
            HttpServletResponse response,
            JSONObject commonIdsObj
    ) throws IOException {
        exportToExcel(data, exportColumns, totalsColumnHeader, response, commonIdsObj, new HashMap<>());
    }

    public static void exportToExcel(
            List<Map<String, Object>> data,
            Map<String, String> exportColumns,
            List<String> totalsColumnHeader,
            HttpServletResponse response,
            JSONObject commonIdsObj,
            Map<String, String> subColumnsToMerge
    ) throws IOException {
        // Set response headers
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=stock_report.xlsx");
        String sheetName = commonIdsObj.getString("SheetName");

        // Create workbook and sheet
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        // headers and subHeaders styles
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setWrapText(true);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);
        headerStyle.setFont(boldFont);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // Light grey
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //Total Row Styles
        CellStyle totalsStyle = workbook.createCellStyle();
        totalsStyle.setFont(boldFont);
        totalsStyle.setBorderTop(BorderStyle.THIN);
        totalsStyle.setBorderBottom(BorderStyle.THIN);
        totalsStyle.setBorderLeft(BorderStyle.THIN);
        totalsStyle.setBorderRight(BorderStyle.THIN);

        // Company Header styles
        CellStyle centeredStyle = workbook.createCellStyle();
        centeredStyle.setAlignment(HorizontalAlignment.CENTER);
        centeredStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        Font companyFont = workbook.createFont();
        companyFont.setBold(true);
        companyFont.setFontHeightInPoints((short) 12);
        centeredStyle.setFont(companyFont);

        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);

        // Add rows for the header and company details
        Row row1 = sheet.createRow(0);
        Row row2 = sheet.createRow(1);
        Row row3 = sheet.createRow(2);
        Row row4 = sheet.createRow(3);

        // Merge cells for company details
        for (int i = 0; i < 4; i++) {
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, exportColumns.size() - 1));
        }

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");


        // Set company details
        row1.createCell(0).setCellValue(commonIdsObj.getString("COMPANY_NAME"));
        row1.getCell(0).setCellStyle(centeredStyle);
        row2.createCell(0).setCellValue(commonIdsObj.getString("COMPANY_ADDRESS"));
        row2.getCell(0).setCellStyle(centeredStyle);
        row3.createCell(0).setCellValue(commonIdsObj.getString("ReportName"));
        row3.getCell(0).setCellStyle(centeredStyle);
        if (!Objects.equals(from_date, "") && !Objects.equals(to_date, "")) {
            String[] frmdateParts = from_date.split("-");
            from_date = frmdateParts[2] + "-" + frmdateParts[1] + "-" + frmdateParts[0];
            String[] toDateParts = to_date.split("-");
            to_date = toDateParts[2] + "-" + toDateParts[1] + "-" + toDateParts[0];

            row4.createCell(0).setCellValue("From: " + from_date + " To: " + to_date);
            row4.getCell(0).setCellStyle(centeredStyle);
        }

        // Create merged header row and subheader row
        Row mergedHeaderRow = sheet.createRow(4);
        Row headerRow = sheet.createRow(5);
        mergedHeaderRow.setHeightInPoints(30);
        headerRow.setHeightInPoints(30);

        Map<Integer, Integer> maxColumnWidths = new HashMap<>();

        // Track merged regions and column indices
        Map<String, Integer> mergedColumnStartIndices = new HashMap<>();
        int headerIndex = 0;

        for (String key : exportColumns.keySet()) {
            if (subColumnsToMerge != null && subColumnsToMerge.containsKey(key)) {
                // Subheader exists for this header
                String mergedHeader = subColumnsToMerge.get(key);
                if (!mergedColumnStartIndices.containsKey(mergedHeader)) {
                    mergedColumnStartIndices.put(mergedHeader, headerIndex);
                }

                // Set subheader cell
                Cell headerCell = headerRow.createCell(headerIndex);
                headerCell.setCellValue(exportColumns.get(key));
                headerCell.setCellStyle(headerStyle);

            } else {
                // No subheader, merge cells for the header
                sheet.addMergedRegion(new CellRangeAddress(4, 5, headerIndex, headerIndex));
                for (int row = 4; row <= 5; row++) {
                    Cell cell = sheet.getRow(row) == null ? sheet.createRow(row).createCell(headerIndex)
                            : sheet.getRow(row).createCell(headerIndex);
                    cell.setCellValue(row == 4 ? exportColumns.get(key) : ""); // Only set header value on top row
                    cell.setCellStyle(headerStyle); // Apply style to every cell in the merged region
                }
            }
            maxColumnWidths.put(headerIndex, exportColumns.get(key).length());
            headerIndex++;
        }

        if (subColumnsToMerge != null) {
            for (Map.Entry<String, Integer> entry : mergedColumnStartIndices.entrySet()) {
                String mergedHeader = entry.getKey();
                int startIndex = entry.getValue();
                int endIndex = startIndex;

                // Calculate the correct endIndex by iterating over subsequent columns
                for (int i = startIndex + 1; i < headerIndex; i++) {
                    String currentHeader = exportColumns.keySet().toArray(new String[0])[i];
                    if (subColumnsToMerge.get(currentHeader) != null &&
                            subColumnsToMerge.get(currentHeader).equals(mergedHeader)) {
                        endIndex = i;
                    } else {
                        break;
                    }
                }

                // Merge cells for the parent header row
                sheet.addMergedRegion(new CellRangeAddress(4, 4, startIndex, endIndex));
                for (int i = startIndex; i <= endIndex; i++) {
                    Cell mergedHeaderCell = mergedHeaderRow.getCell(i);
                    if (mergedHeaderCell == null) {
                        mergedHeaderCell = mergedHeaderRow.createCell(i);
                    }
                    mergedHeaderCell.setCellValue(mergedHeader);
                    mergedHeaderCell.setCellStyle(headerStyle);
                }
            }
        }

        // Set column widths dynamically using a separate variable for row population
        int dataColumnIndex = 0;
        for (String key : exportColumns.keySet()) {
            sheet.setColumnWidth(dataColumnIndex, exportColumns.get(key).length() * 256); // Dynamically set column width
            dataColumnIndex++;
        }

        // Populate data rows
        int rowIndex = 6; // Start after headers
        int serialNumber = 1; //  start for sr no
        Map<String, Double> totalsMap = new HashMap<>();
        for (Map<String, Object> rowData : data) {
            Row row = sheet.createRow(rowIndex++);
            int cellIndex = 0;

            for (String key : exportColumns.keySet()) {
                Object value = rowData.get(key);
                Cell cell = row.createCell(cellIndex);
                if ("serial_no".equalsIgnoreCase(key)) {
                    // Assign sequential serial numbers
                    cell.setCellValue(serialNumber++);
                } else {
                    if (value instanceof Number) {
                        double numericValue = ((Number) value).doubleValue();
                        cell.setCellValue(numericValue);

                        String numericString = Double.toString(numericValue);
                        maxColumnWidths.put(cellIndex, Math.max(maxColumnWidths.getOrDefault(cellIndex, 0), numericString.length()));

                        if (totalsColumnHeader.contains(key)) {
                            totalsMap.put(key, totalsMap.getOrDefault(key, 0.0) + numericValue);
                        }
                    } else if (value != null) {
                        String stringValue = value.toString();
                        cell.setCellValue(stringValue);
                        // Update max column width based on the data length
                        maxColumnWidths.put(cellIndex, Math.max(maxColumnWidths.get(cellIndex), stringValue.length()));
                    } else {
                        cell.setCellValue("");
                    }
                }
                cell.setCellStyle(dataCellStyle);
                cellIndex++;
            }
        }

        // Adjust column widths based on the maximum width for each column
        for (Map.Entry<Integer, Integer> entry : maxColumnWidths.entrySet()) {
            int colIndex = entry.getKey();
            int width = entry.getValue();
            sheet.setColumnWidth(colIndex, Math.min((width + 2) * 256, 50 * 256)); // Cap at Excel's max column width
        }

        // Create totals row
        Row totalsRow = sheet.createRow(rowIndex++);
        int totalsCellIndex = 0;
        for (String key : exportColumns.keySet()) {
            Cell cell = totalsRow.createCell(totalsCellIndex++);
            if (totalsColumnHeader.contains(key)) {
                double totalValue = totalsMap.getOrDefault(key, 0.0);
                cell.setCellValue(totalValue);
            } else if (totalsCellIndex == 1) {
                cell.setCellValue("Total");
            }
            cell.setCellStyle(totalsStyle);
        }

        // Write workbook to response
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    private static BigDecimal convertToBigDecimal(Object value) {
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        } else if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        return BigDecimal.ZERO;
    }

    // get report data for General stores & spares sparts ptoduct Type
    @PostMapping("/getStoresStockReport/{reportType}/{PageCurrent}/{entriesPerPage}/{company_id}")
    public Map<String, Object> MaterialTransactions(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("reportType") String reportType
            , @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage, @PathVariable String company_id) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> stockReportData = new ArrayList<>();
        Long total_count = 0L;
        try {
            Integer companyId = company_id.equalsIgnoreCase("All") ? 0 : Integer.parseInt(company_id);

            if (reportType.equalsIgnoreCase("summary")) {
                stockReportData = iStoreStockReportService.GetStockReportByDateSummary(commonIdsObj, PageCurrent, entriesPerPage, companyId);
            } else {
                stockReportData = iStoreStockReportService.GetStockReportByDateDetails(commonIdsObj, PageCurrent, entriesPerPage, companyId);
            }
            if (!stockReportData.isEmpty()) {
                total_count = (Long) stockReportData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("storeStockReportData", stockReportData);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/getPurchaseOutstandingMaterials/{company_id}/{page}/{pageSize}")
    public Map<String, Object> getOutstandingMaterials(@RequestParam("commonIds") JSONObject commonIdsObj,
                                                       @PathVariable("company_id") Integer company_id,
                                                       @PathVariable("page") Integer page,
                                                       @PathVariable("pageSize") Integer pageSize) {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> outstandingMaterialsReportData = new ArrayList<>();

        try {
            // Extract parameters from JSON input with default values
            String from_date = commonIdsObj.optString("from_date", "");
            String to_date = commonIdsObj.optString("to_date", "");
            String productTypeId = commonIdsObj.optString("product_type_id", "");
            String category1 = commonIdsObj.optString("category1", "All");
            String category2 = commonIdsObj.optString("category2", "All");
            String product_id = commonIdsObj.optString("product_id", "");
            String supplierBranchId = commonIdsObj.optString("supplier_branch_id", "");

            // Calculate offset based on page and pageSize
//            int offset = (page - 1) * pageSize;
            int offset = page * pageSize;
            // Fetch data from the stored procedure
            Map<String, Object> result = CallPurchaseOutstandingMaterials(
                    from_date, to_date, productTypeId, category1, category2,
                    company_id, product_id, supplierBranchId, offset, pageSize
            );

            outstandingMaterialsReportData = (List<Map<String, Object>>) result.get("data");
            Integer totalCount = (Integer) result.get("totalCount");
            int count = (totalCount != null) ? totalCount : 0;


            // Prepare the response
            response.put("success", 1);
            response.put("outstandingMaterialsReportData", outstandingMaterialsReportData);
            response.put("total", count); // Include the total count for pagination

        } catch (DataAccessException e) {
            handleDataAccessException(e, response, company_id, "/api/getPurchaseOutstandingMaterials");
        } catch (Exception e) {
            handleGeneralException(e, response, company_id, "/api/getPurchaseOutstandingMaterials");
        }

        return response;
    }


    private Map<String, Object> CallPurchaseOutstandingMaterials(String fromDate, String toDate, String productTypeId, String category1, String category2, Integer companyId, String productId, String supplierBranchId, Integer offset, Integer limit) {
        List<Map<String, Object>> getData = new ArrayList<>();


        String storedProcedure = "{call GetPurchaseOutstandingMaterials(?,?,?,?,?,?,?,?,?,?)}";

        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall(storedProcedure);
                    cs.setString(1, fromDate);
                    cs.setString(2, toDate);
                    cs.setString(3, productTypeId);
                    cs.setString(4, category1);
                    cs.setString(5, category2);
                    cs.setString(6, productId);
                    cs.setString(7, supplierBranchId);
                    cs.setInt(8, companyId);
                    cs.setInt(9, offset);
                    cs.setInt(10, limit);
                    return cs;
                },
                (CallableStatementCallback<Map<String, Object>>) cs -> {
                    cs.execute();

                    // Get the total count from the first result set (if applicable)


                    // Get the main result set
                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs != null && rs.next()) {
                            Map<String, Object> resultMap = new HashMap<>();
                            resultMap.put("totalCount", rs.getObject("total_count"));
                            resultMap.put("po_approved_date", rs.getObject("po_approved_date"));
                            resultMap.put("purchase_order_no", rs.getObject("purchase_order_no"));
                            resultMap.put("supp_branch_name", rs.getObject("supp_branch_name"));
                            resultMap.put("purchase_order_type", rs.getObject("purchase_order_type"));
                            resultMap.put("product_category1_name", rs.getObject("product_category1_name"));
                            resultMap.put("product_category2_name", rs.getObject("product_category2_name"));
                            resultMap.put("material_code", rs.getObject("material_code"));
                            resultMap.put("material_name", rs.getObject("material_name"));
                            resultMap.put("product_material_po_approved_quantity", rs.getObject("product_material_po_approved_quantity"));
                            resultMap.put("pending_quantity", rs.getObject("pending_quantity"));
                            resultMap.put("material_rate", rs.getObject("material_rate"));
                            resultMap.put("material_id", rs.getObject("material_id"));
                            getData.add(resultMap);
                        }
                    }

                    // Prepare the response map
                    Map<String, Object> result = new HashMap<>();
                    result.put("data", getData);
                    // Send the total count separately
                    return result;
                }
        );
    }

    private void handleDataAccessException(DataAccessException e, Map<String, Object> response, Integer company_id, String endpoint) {
        e.printStackTrace();
        if (e.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) e.getRootCause();
            amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", endpoint, sqlEx.getErrorCode(), sqlEx.getMessage());
        }
        response.put("success", 0);
        response.put("data", "");
        response.put("error", e.getMessage());
    }

    private void handleGeneralException(Exception e, Map<String, Object> response, Integer company_id, String endpoint) {
        e.printStackTrace();
        amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", endpoint, 0, e.getMessage());
        response.put("success", 0);
        response.put("data", "");
        response.put("error", e.getMessage());
    }

    @PostMapping("/getStockReportToExport/{reportType}")
    public void GetStockReportToExport(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("reportType") String reportType, HttpServletResponse response) {
        List<Map<String, Object>> StockReportDataToExport = new ArrayList<>();
        try {
            StockReportDataToExport = iStoreStockReportService.GetStockReportToExport(commonIdsObj, reportType);

            Map<String, String> summaryColumns = new LinkedHashMap<>();
            summaryColumns.put("serial_no", "Sr. No.");
            summaryColumns.put("product_category1_name", "Category1 Name");
            summaryColumns.put("product_category2_name", "Category2 Name");

            summaryColumns.put("opening_quantity", "Opening Balance Qty");
            summaryColumns.put("opening_average_batch_rate", "Opening Balance Rate");
            summaryColumns.put("opening_total_material_value", "Opening Balance Value");

            summaryColumns.put("purchase_quantity", "Purchase Balance Qty");
            summaryColumns.put("purchase_average_batch_rate", "Purchase Balance Rate");
            summaryColumns.put("purchase_total_material_value", "Purchase Balance Value");

            summaryColumns.put("adjust_by_add", "Add By Adjust");
            summaryColumns.put("adjust_by_add_value", "Add Value By Adjust");
            summaryColumns.put("adjust_by_reduce", "Reduce By Adjust");
            summaryColumns.put("adjust_by_reduce_value", "Reduce Value By Adjust");

            summaryColumns.put("transfer_issue_quantity", "Transfer Outward quantity");
            summaryColumns.put("transfer_issue_quantity_value", "Transfer Outward value");
            summaryColumns.put("transfer_receipt_quantity", "Transfer Inward quantity");
            summaryColumns.put("transfer_receipt_quantity_value", "Transfer Inward value");

            summaryColumns.put("issue_quantity", "Issue Balance Qty");
            summaryColumns.put("issue_average_batch_rate", "Issue Balance Rate");
            summaryColumns.put("issue_total_material_value", "Issue Balance Value");

            summaryColumns.put("return_quantity", "Return Qty");
            summaryColumns.put("return_average_batch_rate", "Return Rate");
            summaryColumns.put("return_total_material_value", "Return Value");

            summaryColumns.put("supplier_return_quantity", "Return Qty");
            summaryColumns.put("supplier_return_batch_rate", "Return Rate");
            summaryColumns.put("supplier_return_material_value", "Return Value");

            summaryColumns.put("closing_balance_quantity", "Closing Balance Qty");
            summaryColumns.put("closing_average_batch_rate", "Closing Balance Rate");
            summaryColumns.put("closing_total_material_value", "Closing Balance Value");

            // Define your custom headers mapping
            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("product_category1_name", "Category1 Name");
            detailsColumns.put("product_category2_name", "Category2 Name");
            detailsColumns.put("godown_section_beans_name", "Godown section beans Name");
            detailsColumns.put("material_code", "Material Code");
            detailsColumns.put("material_name", "Material Name");
            detailsColumns.put("opening_quantity", "Opening Balance Qty");
            detailsColumns.put("opening_average_batch_rate", "Opening Balance Rate");
            detailsColumns.put("opening_total_material_value", "Opening Balance Value");
            detailsColumns.put("purchase_quantity", "Purchase Balance Qty");
            detailsColumns.put("purchase_average_batch_rate", "Purchase Balance Rate");
            detailsColumns.put("purchase_total_material_value", "Purchase Balance Value");
            detailsColumns.put("adjust_by_add", "Add By Adjust");
            detailsColumns.put("adjust_by_add_value", "Add Value By Adjust");
            detailsColumns.put("adjust_by_reduce", "Reduce By Adjust");
            detailsColumns.put("adjust_by_reduce_value", "Reduce Value By Adjust");
            detailsColumns.put("transfer_issue_quantity", "transfer Outward quantity");
            detailsColumns.put("transfer_issue_quantity_value", "transfer Outward value");
            detailsColumns.put("transfer_receipt_quantity", "transfer Inward quantity");
            detailsColumns.put("transfer_receipt_quantity_value", "transfer Inward value");
            detailsColumns.put("issue_quantity", "Issue Balance Qty");
            detailsColumns.put("issue_average_batch_rate", "Issue Balance Rate");
            detailsColumns.put("issue_total_material_value", "Issue Balance Value");
            detailsColumns.put("return_quantity", "Return Qty");
            detailsColumns.put("return_average_batch_rate", "Return Rate");
            detailsColumns.put("return_total_material_value", "Return Value");
            detailsColumns.put("supplier_return_quantity", "Return Qty");
            detailsColumns.put("supplier_return_batch_rate", "Return Rate");
            detailsColumns.put("supplier_return_material_value", "Return Value");
            detailsColumns.put("closing_balance_quantity", "Closing Balance Qty");
            detailsColumns.put("closing_average_batch_rate", "Closing Balance Rate");
            detailsColumns.put("closing_total_material_value", "Closing Balance Value");

            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(
                    "opening_total_material_value",
                    "opening_quantity",
                    "purchase_quantity",
                    "purchase_total_material_value",
                    "adjust_by_add",
                    "adjust_by_add_value",
                    "adjust_by_reduce",
                    "adjust_by_reduce_value",
                    "transfer_issue_quantity",
                    "transfer_issue_quantity_value",
                    "transfer_receipt_quantity",
                    "transfer_receipt_quantity_value",
                    "issue_quantity",
                    "issue_total_material_value",
                    "return_quantity",
                    "return_total_material_value",
                    "supplier_return_quantity",
                    "supplier_return_material_value",
                    "closing_balance_quantity",
                    "closing_total_material_value"
            ));

            Map<String, String> subColumnsToMergeForSummary = new LinkedHashMap<>();
            //Opening28
            subColumnsToMergeForSummary.put("opening_quantity", "Opening Balance");
            subColumnsToMergeForSummary.put("opening_average_batch_rate", "Opening Balance");
            subColumnsToMergeForSummary.put("opening_total_material_value", "Opening Balance");
            //Purchase
            subColumnsToMergeForSummary.put("purchase_quantity", "Purchase");
            subColumnsToMergeForSummary.put("purchase_average_batch_rate", "Purchase");
            subColumnsToMergeForSummary.put("purchase_total_material_value", "Purchase");
            //Adjust
            subColumnsToMergeForSummary.put("adjust_by_add", "Adjust");
            subColumnsToMergeForSummary.put("adjust_by_reduce", "Adjust");
            subColumnsToMergeForSummary.put("adjust_by_add_value", "Adjust");
            subColumnsToMergeForSummary.put("adjust_by_reduce_value", "Adjust");
            //Transfer
            subColumnsToMergeForSummary.put("transfer_issue_quantity", "Unit Transfer");
            subColumnsToMergeForSummary.put("transfer_issue_quantity_value", "Unit Transfer");
            subColumnsToMergeForSummary.put("transfer_receipt_quantity", "Unit Transfer");
            subColumnsToMergeForSummary.put("transfer_receipt_quantity_value", "Unit Transfer");
            //Stores Issue
            subColumnsToMergeForSummary.put("issue_quantity", "Issue Balance");
            subColumnsToMergeForSummary.put("issue_average_batch_rate", "Issue Balance");
            subColumnsToMergeForSummary.put("issue_total_material_value", "Issue Balance");

            //Return Department
            subColumnsToMergeForSummary.put("return_quantity", "Department Return");
            subColumnsToMergeForSummary.put("return_average_batch_rate", "Department Return");
            subColumnsToMergeForSummary.put("return_total_material_value", "Department Return");

            //Return Supplier
            subColumnsToMergeForSummary.put("supplier_return_quantity", "Supplier Return");
            subColumnsToMergeForSummary.put("supplier_return_batch_rate", "Supplier Return");
            subColumnsToMergeForSummary.put("supplier_return_material_value", "Supplier Return");

            //closing balance
            subColumnsToMergeForSummary.put("closing_balance_quantity", "Closing Balance");
            subColumnsToMergeForSummary.put("closing_average_batch_rate", "Closing Balance");
            subColumnsToMergeForSummary.put("closing_total_material_value", "Closing Balance");

            Map<String, String> subColumnsToMergeForDetails = new LinkedHashMap<>();

            subColumnsToMergeForDetails.put("opening_quantity", "Opening Balance");
            subColumnsToMergeForDetails.put("opening_average_batch_rate", "Opening Balance");
            subColumnsToMergeForDetails.put("opening_total_material_value", "Opening Balance");

            subColumnsToMergeForDetails.put("purchase_quantity", "Purchase");
            subColumnsToMergeForDetails.put("purchase_average_batch_rate", "Purchase");
            subColumnsToMergeForDetails.put("purchase_total_material_value", "Purchase");

            subColumnsToMergeForDetails.put("adjust_by_add", "Adjust");
            subColumnsToMergeForDetails.put("adjust_by_add_value", "Adjust");
            subColumnsToMergeForDetails.put("adjust_by_reduce", "Adjust");
            subColumnsToMergeForDetails.put("adjust_by_reduce_value", "Adjust");

            subColumnsToMergeForDetails.put("transfer_issue_quantity", "Unit Transfer");
            subColumnsToMergeForDetails.put("transfer_issue_quantity_value", "Unit Transfer");
            subColumnsToMergeForDetails.put("transfer_receipt_quantity", "Unit Transfer");
            subColumnsToMergeForDetails.put("transfer_receipt_quantity_value", "Unit Transfer");

            subColumnsToMergeForDetails.put("issue_quantity", "Issue Balance");
            subColumnsToMergeForDetails.put("issue_average_batch_rate", "Issue Balance");
            subColumnsToMergeForDetails.put("issue_total_material_value", "Issue Balance");

            //Department Supplier
            subColumnsToMergeForDetails.put("return_quantity", "Department Return");
            subColumnsToMergeForDetails.put("return_average_batch_rate", "Department Return");
            subColumnsToMergeForDetails.put("return_total_material_value", "Department Return");

            //Return Supplier
            subColumnsToMergeForDetails.put("supplier_return_quantity", "Supplier Return");
            subColumnsToMergeForDetails.put("supplier_return_batch_rate", "Supplier Return");
            subColumnsToMergeForDetails.put("supplier_return_material_value", "Supplier Return");

            subColumnsToMergeForDetails.put("closing_balance_quantity", "Closing Balance");
            subColumnsToMergeForDetails.put("closing_average_batch_rate", "Closing Balance");
            subColumnsToMergeForDetails.put("closing_total_material_value", "Closing Balance");

            // Export the data to Excel
            exportToExcel(StockReportDataToExport, reportType.equalsIgnoreCase("summary") ? summaryColumns : detailsColumns, totalsColumnHeader, response, commonIdsObj, reportType.equalsIgnoreCase("summary") ? subColumnsToMergeForSummary : subColumnsToMergeForDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/getAllStoresStockReportToPrint/{reportType}")
    public Map<String, Object> getAllStoresStockReportToPrint(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("reportType") String reportType
    ) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> stockReportData = new ArrayList<>();
        try {
            stockReportData = iStoreStockReportService.GetStockReportToExport(commonIdsObj, reportType);

            response.put("storeStockReportData", stockReportData);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }

    // get report data for Product Raw material ptoduct Type
    @PostMapping("/getRawMaterialStockReport/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> getRawMaterialStockReport(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> stockReportData = new ArrayList<>();
        List<Map<String, Object>> totalRawMaterialStockDetails = new ArrayList<>();

        Long total_count = 0L;
        try {
            stockReportData = iStoreStockReportService.GetRawMaterialStockReportByDateDetails(commonIdsObj, PageCurrent, entriesPerPage);
            totalRawMaterialStockDetails = iStoreStockReportService.GetRawMaterialStockReportByDateDetailsToExport(commonIdsObj);
            Map<String, BigDecimal> grandTotalForRawMaterial = totalRawMaterialStockDetails.stream()
                    .flatMap(map -> map.entrySet().stream())
                    .filter(entry -> entry.getValue() instanceof BigDecimal || entry.getValue() instanceof Number)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> convertToBigDecimal(entry.getValue()), // Convert values to BigDecimal
                            BigDecimal::add // Sum values with the same key
                    ));
            System.out.println("Grand Total For RawMaterial: " + grandTotalForRawMaterial);

            if (!stockReportData.isEmpty()) {
                total_count = (Long) stockReportData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("storeStockReportData", stockReportData);
            response.put("totalRawMaterialStockDetails", grandTotalForRawMaterial);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/getYarnStockReport/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> getYarnStockReport(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> stockReportData = new ArrayList<>();
        Map<String, Object> totalYarnStockDetails = new HashMap<>();
        Long total_count = 0L;
        try {
            stockReportData = iStoreStockReportService.GetYarnStockReportByDateDetails(commonIdsObj, PageCurrent, entriesPerPage);
            totalYarnStockDetails = iStoreStockReportService.GetYarnStockTotalDetails(commonIdsObj);
            if (!stockReportData.isEmpty()) {
                total_count = (Long) stockReportData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("storeStockReportData", stockReportData);
            response.put("totalYarnStockData", totalYarnStockDetails);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/getRawMaterialStockReport")
    public Map<String, Object> GetRawMaterialStockReportByDateDetailsToPrint(@RequestParam("commonIds") JSONObject commonIdsObj) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> stockReportData = new ArrayList<>();
        Long total_count = 0L;
        try {
            stockReportData = iStoreStockReportService.GetRawMaterialStockReportByDateDetailsToExport(commonIdsObj);

            if (!stockReportData.isEmpty()) {
                total_count = (Long) stockReportData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("storeStockReportData", stockReportData);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/getRawMaterialStockReportToExport")
    public void GetRawMaterialStockReportToExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> StockReportDataToExport = new ArrayList<>();
        try {
            StockReportDataToExport = iStoreStockReportService.GetRawMaterialStockReportByDateDetailsToExport(commonIdsObj);

            // Define your custom headers mapping
            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("material_code", "Material Code");
            detailsColumns.put("material_name", "Count");
            detailsColumns.put("lot_no", "Lot No");
            detailsColumns.put("supp_name", "Supplier");
            detailsColumns.put("cone_per_wt", "Cone Per wt");
            detailsColumns.put("godown_name", "Godown Name");

            detailsColumns.put("opening_no_of_boxes", "Boxes");
            detailsColumns.put("opening_quantity", "Cones");
            detailsColumns.put("opening_total_box_weight", "Weight(Kgs)");

            detailsColumns.put("purchase_no_of_boxes", "Boxes");
            detailsColumns.put("purchase_quantity", "Cones");
            detailsColumns.put("purchase_total_box_weight", "Weight(Kgs)");
            detailsColumns.put("purchase_average_batch_rate", "Rate");
            detailsColumns.put("purchase_total_material_value", "Amount");

//            detailsColumns.put("adjust_by_add", "Add Adjust");
//            detailsColumns.put("adjust_by_reduce", "Reduce Adjust");

            // issue Warping
            detailsColumns.put("issue_no_of_boxes_warping", "Boxes");
            detailsColumns.put("issue_material_quantity_warping", "Cones");
            detailsColumns.put("issue_total_material_weight_warping", "Weight(Kgs)");

            // issue weaving
            detailsColumns.put("issue_no_of_boxes_weaving", "Boxes");
            detailsColumns.put("issue_material_quantity_weaving", "Cones");
            detailsColumns.put("issue_total_material_weight_weaving", "Weight(Kgs)");

            // return Warping
            detailsColumns.put("product_material_return_boxes_warping", "Boxes");
            detailsColumns.put("product_material_return_quantity_warping", "Cones");
            detailsColumns.put("product_material_return_weight_warping", "Weight(Kgs)");

            // return weaving
            detailsColumns.put("product_material_return_boxes_weaving", "Boxes");
            detailsColumns.put("product_material_return_quantity_weaving", "Cones");
            detailsColumns.put("product_material_return_weight_weaving", "Weight(Kgs)");

            // return to Supplier
            detailsColumns.put("supplier_return_boxes", "Boxes");
            detailsColumns.put("supplier_return_quantity", "Cones");
            detailsColumns.put("supplier_return_weight", "Weight(Kgs)");

            // closing balance
            detailsColumns.put("closing_no_of_boxes", "Boxes");
            detailsColumns.put("closing_balance_quantity", "Cones");
            detailsColumns.put("closing_total_box_weight", "Weight(Kgs)");

            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(
                    "total_quantity", "opening_no_of_boxes", "opening_quantity", "opening_total_box_weight",
                    "purchase_no_of_boxes", "purchase_quantity", "purchase_total_box_weight",
                    "purchase_average_batch_rate", "purchase_total_material_value",
//                    "adjust_by_add", "adjust_by_reduce",
                    "issue_no_of_boxes_warping", "issue_material_quantity_warping",
                    "issue_total_material_weight_warping", "product_material_return_boxes_warping",
                    "product_material_return_quantity_warping", "product_material_return_weight_warping",
                    "issue_no_of_boxes_weaving", "issue_material_quantity_weaving",
                    "issue_total_material_weight_weaving", "product_material_return_boxes_weaving",
                    "product_material_return_quantity_weaving", "product_material_return_weight_weaving",
                    "supplier_return_boxes", "supplier_return_quantity", "supplier_return_weight",
                    "closing_no_of_boxes", "closing_balance_quantity", "closing_total_box_weight"
            ));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();
            //Opening
            subColumnsToMerge.put("opening_no_of_boxes", "Opening Balance");
            subColumnsToMerge.put("opening_quantity", "Opening Balance");
            subColumnsToMerge.put("opening_total_box_weight", "Opening Balance");
            //Purchase
            subColumnsToMerge.put("purchase_no_of_boxes", "Purchase");
            subColumnsToMerge.put("purchase_quantity", "Purchase");
            subColumnsToMerge.put("purchase_total_box_weight", "Purchase");
            subColumnsToMerge.put("purchase_average_batch_rate", "Purchase");
            subColumnsToMerge.put("purchase_total_material_value", "Purchase");
            //Adjust
//            subColumnsToMerge.put("adjust_by_add","Adjust");
//            subColumnsToMerge.put("adjust_by_reduce","Adjust");
            //Warping Issue
            subColumnsToMerge.put("issue_no_of_boxes_warping", "Issued to Warping");
            subColumnsToMerge.put("issue_material_quantity_warping", "Issued to Warping");
            subColumnsToMerge.put("issue_total_material_weight_warping", "Issued to Warping");
            //Weaving Issue
            subColumnsToMerge.put("issue_no_of_boxes_weaving", "Issued to Weaving");
            subColumnsToMerge.put("issue_material_quantity_weaving", "Issued to Weaving");
            subColumnsToMerge.put("issue_total_material_weight_weaving", "Issued to Weaving");
            //Warping Return
            subColumnsToMerge.put("product_material_return_boxes_warping", "Return From Warping");
            subColumnsToMerge.put("product_material_return_quantity_warping", "Return From Warping");
            subColumnsToMerge.put("product_material_return_weight_warping", "Return From Warping");
            //Weaving Return
            subColumnsToMerge.put("product_material_return_boxes_weaving", "Return From Weaving");
            subColumnsToMerge.put("product_material_return_quantity_weaving", "Return From Weaving");
            subColumnsToMerge.put("product_material_return_weight_weaving", "Return From Weaving");
            //Supplier Return
            subColumnsToMerge.put("supplier_return_boxes", "Return From Supplier");
            subColumnsToMerge.put("supplier_return_quantity", "Return From Supplier");
            subColumnsToMerge.put("supplier_return_weight", "Return From Supplier");
            // Closing Balance
            subColumnsToMerge.put("closing_no_of_boxes", "Closing Balance");
            subColumnsToMerge.put("closing_balance_quantity", "Closing Balance");
            subColumnsToMerge.put("closing_total_box_weight", "Closing Balance");

            // Export the data to Excel
            exportToExcel(StockReportDataToExport, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/getYarnStockReportToExport")
    public void GetYarnStockReportToExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> YarnStockReportDataToExport = new ArrayList<>();
        try {
            YarnStockReportDataToExport = iStoreStockReportService.GetYarnStockReportByDateDetailsToExport(commonIdsObj);

            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("material_code", "Material Code");
            detailsColumns.put("material_name", "Material Name");
            detailsColumns.put("lot_no", "Lot No");
            detailsColumns.put("supp_name", "Supplier Name");
            detailsColumns.put("godown_name", "Godown Name");
            detailsColumns.put("closing_no_of_boxes", "Closing Boxes");
            detailsColumns.put("closing_balance_quantity", "Closing Cones");
            detailsColumns.put("closing_total_box_weight", "Closing KGs.");

            // Specify columns to calculate totals
            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList("closing_no_of_boxes", "closing_balance_quantity", "closing_total_box_weight"));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();
            subColumnsToMerge.put("closing_no_of_boxes", "Closing Balance");
            subColumnsToMerge.put("closing_balance_quantity", "Closing Balance");
            subColumnsToMerge.put("closing_total_box_weight", "Closing Balance");

            // Export the data to Excel
            exportToExcel(YarnStockReportDataToExport, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Api call for material aging report
    @PostMapping("/getAgingStockDetails/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> getStoresAgingReport(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer page, @PathVariable("entriesPerPage") Integer pageSize) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> materialAgingDataByProcedure = new ArrayList<>();
        List<Map<String, Object>> totalAgingStockDetails = new ArrayList<>();

        Long total_count = 0L;
        try {
            String reportType = commonIdsObj.getString("reportType");

            if (reportType.equalsIgnoreCase("summary")) {
                materialAgingDataByProcedure = iStoreStockReportService.CallGenerateAgingProcedureSummary(commonIdsObj, page, pageSize);
                commonIdsObj.put("actionType", "getData"); // Set actionType
                totalAgingStockDetails = iStoreStockReportService.CallGenerateAgingProcedureSummary(commonIdsObj, 0, 0);

            } else {
                // Define the stored procedure cal
                materialAgingDataByProcedure = iStoreStockReportService.CallGenerateAgingProcedure(commonIdsObj, page, pageSize);
                commonIdsObj.put("actionType", "getData"); // Set actionType
                totalAgingStockDetails = iStoreStockReportService.CallGenerateAgingProcedure(commonIdsObj, 0, 0);

            }

            if (!materialAgingDataByProcedure.isEmpty()) {
                total_count = (Long) materialAgingDataByProcedure.get(0).get("totalRowCount");
            }

// Compute grand total for all numeric fields
            Map<String, Object> grandTotalRow = new HashMap<>();

            totalAgingStockDetails.forEach(row -> {
                row.forEach((key, value) -> {
                    if (value instanceof Number) { // Sum numeric values
                        BigDecimal currentValue = convertToBigDecimal(value);
                        BigDecimal total = convertToBigDecimal(grandTotalRow.getOrDefault(key, BigDecimal.ZERO));
                        grandTotalRow.put(key, total.add(currentValue));
                    } else {
                        grandTotalRow.putIfAbsent(key, "Total"); // Label for non-numeric fields
                    }
                });
            });
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("agingStockDetailsData", materialAgingDataByProcedure);
            response.put("grandTotalagingStock", List.of(grandTotalRow)); // Wrap in List for a single-row response

        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }


    // New Aging Report Export (21-01-2025)
    @PostMapping("/getAgingStockDetailsExportExcel")
    public void getAgingStockDetailsExportExcel(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> getAgingStockDetailsExportExcel = new ArrayList<>();
        String productTypeId = commonIdsObj.getString("productTypeId");
        String unitForQuantity = productTypeId.equals("12") ? "No. of Cones" : "Quantity";
        String reportType = commonIdsObj.getString("reportType");
        try {
            if (reportType.equalsIgnoreCase("summary")) {
                getAgingStockDetailsExportExcel = iStoreStockReportService.CallGenerateAgingProcedureSummary(commonIdsObj, 0, 0);

            } else {
                // Define the stored procedure cal
                getAgingStockDetailsExportExcel = iStoreStockReportService.CallGenerateAgingProcedure(commonIdsObj, 0, 0);
            }
            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("product_category1_name", "Category 1 Name");
            detailsColumns.put("product_category2_name", "Category 2 Name");
            if (!reportType.equalsIgnoreCase("summary")) {
                detailsColumns.put("product_material_code", "Material Code");
                detailsColumns.put("product_material_name", "Material Name");
                detailsColumns.put("godown_section_beans_name", "Godown");
            }
            detailsColumns.put("below_30_days", unitForQuantity);
            detailsColumns.put("below_1_30_amount", "Amount");
            detailsColumns.put("between_31_and_60_days", unitForQuantity);
            detailsColumns.put("below_31_60_amount", "Amount");
            detailsColumns.put("between_61_and_180_days", unitForQuantity);
            detailsColumns.put("below_61_180_amount", "Amount");
            detailsColumns.put("between_181_and_365_days", unitForQuantity);
            detailsColumns.put("below_181_365_amount", "Amount");
            detailsColumns.put("above_one_year", unitForQuantity);
            detailsColumns.put("above_one_year_amount", "Amount");
            detailsColumns.put("total_closing_quantity", unitForQuantity);
            detailsColumns.put("total_closing_amount", "Amount");

            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(
                    "below_30_days",
                    "below_1_30_amount",
                    "between_31_and_60_days",
                    "below_31_60_amount",
                    "between_61_and_180_days",
                    "below_61_180_amount",
                    "between_181_and_365_days",
                    "below_181_365_amount",
                    "above_one_year",
                    "above_one_year_amount",
                    "total_closing_quantity",
                    "total_closing_amount"
            ));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();
            subColumnsToMerge.put("below_30_days", "Below 30 Days");
            subColumnsToMerge.put("below_1_30_amount", "Below 30 Days");
            subColumnsToMerge.put("between_31_and_60_days", "31-60 Days");
            subColumnsToMerge.put("below_31_60_amount", "31-60 Days");
            subColumnsToMerge.put("between_61_and_180_days", "61-180 Days");
            subColumnsToMerge.put("below_61_180_amount", "61-180 Days");
            subColumnsToMerge.put("between_181_and_365_days", "181-365 Days");
            subColumnsToMerge.put("below_181_365_amount", "181-365 Days");
            subColumnsToMerge.put("above_one_year", "Above One Year");
            subColumnsToMerge.put("above_one_year_amount", "Above One Year");
            subColumnsToMerge.put("total_closing_quantity", "Total");
            subColumnsToMerge.put("total_closing_amount", "Total");

            // Export the data to Excel
            exportToExcel(getAgingStockDetailsExportExcel, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @PostMapping("/getFabricStockReport/{PageCurrent}/{entriesPerPage}")
//    /getYarnStockReport/{PageCurrent}/{entriesPerPage}
    public Map<String, Object> FabricTransaction(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> fabricStockData = new ArrayList<>();
        Long total_count = 0L;
        try {
            fabricStockData = iStoreStockReportService.GetFabricStockReport(commonIdsObj, PageCurrent, entriesPerPage);
            if (!fabricStockData.isEmpty()) {
                total_count = (Long) fabricStockData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("fabricStockData", fabricStockData);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/getSizedBeamStockReportExportData")
    public void GetSizedBeamStockReportExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> SizedBeamStockReportData = new ArrayList<>();
        try {
            SizedBeamStockReportData = iStoreStockReportService.GetSizedBeamStockReportExport(commonIdsObj);
            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("sizing_production_date", "Production Date");
            detailsColumns.put("product_material_name", "Count");
            detailsColumns.put("total_ends", "Total Ends");
            detailsColumns.put("beam_inward_type", "Beam No.");
            detailsColumns.put("sizing_length", "Length");
            detailsColumns.put("set_no", "Set No.");
            detailsColumns.put("job_type", "Job Type");
            detailsColumns.put("sized_beam_status_desc", "Beam Status");
            detailsColumns.put("customer_name", "Customer Name");
            detailsColumns.put("remaining_length", "Remaining Length");
            detailsColumns.put("cut_beam_date", "Beam Cut Date");
            detailsColumns.put("sizing_production_code", "Sizing Production Code");

            // Specify columns to calculate totals
            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(""));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();

            // Export the data to Excel
            exportToExcel(SizedBeamStockReportData, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cotton Bales Report


    @PostMapping("/getCottonBaleStockReport/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> getCottonBaleStockReport(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> stockReportData = new ArrayList<>();
        List<Map<String, Object>> totalRawMaterialStockDetails = new ArrayList<>();

        Long total_count = 0L;
        try {
            stockReportData = iStoreStockReportService.GetCottonBaleStockReportByDateDetails(commonIdsObj, PageCurrent, entriesPerPage);
            totalRawMaterialStockDetails = iStoreStockReportService.GetCottonBaleStockReportTotals(commonIdsObj);

            Map<String, BigDecimal> grandTotalForCottonBale = totalRawMaterialStockDetails.stream()
                    .flatMap(map -> map.entrySet().stream())
                    .filter(entry -> entry.getValue() instanceof BigDecimal || entry.getValue() instanceof Number)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> convertToBigDecimal(entry.getValue()), // Convert values to BigDecimal
                            BigDecimal::add // Sum values with the same key
                    ));

            if (!stockReportData.isEmpty()) {
                total_count = (Long) stockReportData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("CottonBaleStockReportData", stockReportData);
            response.put("totalCottonBaleData", grandTotalForCottonBale);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }


    @PostMapping("/getCottonBaleStockReportToPrint")
    public Map<String, Object> GetCottonBaleStockReportByDateDetailsToPrint(@RequestParam("commonIds") JSONObject commonIdsObj) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> stockReportData = new ArrayList<>();
        Long total_count = 0L;
        try {
            stockReportData = iStoreStockReportService.GetCottonBaleStockReportByDateDetailsToExport(commonIdsObj);

            if (!stockReportData.isEmpty()) {
                total_count = (Long) stockReportData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("CottonBaleStockReport", stockReportData);
        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }


    @PostMapping("/getCottonBaleStockReportToExport")
    public void GetCottonBaleStockReportToExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> StockReportDataToExport = new ArrayList<>();
        try {
            StockReportDataToExport = iStoreStockReportService.GetCottonBaleStockReportByDateDetailsToExport(commonIdsObj);

            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("material_code", "Material Code");
            detailsColumns.put("material_name", "Material Name");
            detailsColumns.put("batch_no", "Mill Lot No.");
            detailsColumns.put("supplier_batch_no", "Supplier Lot No.");
            detailsColumns.put("godown_name", "Godown Name");
            detailsColumns.put("press_running_no_from", "From");
            detailsColumns.put("press_running_no_to", "To");
            detailsColumns.put("batch_rate", "Rate");
            //Opening
            detailsColumns.put("opening_quantity", "No. Bales");
            detailsColumns.put("opening_weight", "Weight(Kgs)");
            detailsColumns.put("opening_amount", "Amount");
            //Purchase
            detailsColumns.put("purchase_quantity", "No. Bales");
            detailsColumns.put("purchase_weight", "Weight(Kgs)");
            detailsColumns.put("purchase_amount", "Amount");
            //Issue Warping
            detailsColumns.put("issue_quantity", "No. Bales");
            detailsColumns.put("issue_weight", "Weight(Kgs)");
            detailsColumns.put("issue_amount", "Amount");
            //Closing balance
            detailsColumns.put("closing_quantity", "No. Bales");
            detailsColumns.put("closing_weight", "Weight(Kgs)");
            detailsColumns.put("closing_amount", "Amount");

            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(
                    "opening_quantity", "opening_weight",  "opening_amount",
                    "purchase_quantity", "purchase_weight", "purchase_amount",
                    "issue_quantity", "issue_weight", "issue_amount",
                    "closing_quantity", "closing_weight", "closing_amount"
            ));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();
            //Press NOs
            subColumnsToMerge.put("press_running_no_from", "Press Running No");
            subColumnsToMerge.put("press_running_no_to", "Press Running No");
            //Opening
            subColumnsToMerge.put("opening_quantity", "Opening Balance");
            subColumnsToMerge.put("opening_weight", "Opening Balance");
            subColumnsToMerge.put("opening_amount", "Opening Balance");
            //Purchase
            subColumnsToMerge.put("purchase_quantity", "Purchase");
            subColumnsToMerge.put("purchase_weight", "Purchase");
            subColumnsToMerge.put("purchase_amount", "Purchase");
            //Warping Issue
            subColumnsToMerge.put("issue_quantity", "Issued");
            subColumnsToMerge.put("issue_weight", "Issued");
            subColumnsToMerge.put("issue_amount", "Issued");
            // Closing Balance
            subColumnsToMerge.put("closing_quantity", "Closing Balance");
            subColumnsToMerge.put("closing_weight", "Closing Balance");
            subColumnsToMerge.put("closing_amount", "Closing Balance");
            // Export the data to Excel
            exportToExcel(StockReportDataToExport, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Cotton Bales Weightage Report
//    @PostMapping("/FnGetBalesWeightageReport")
//    public Map<String, Object> FnGetBalesWeightageReport(@RequestParam("commonIds") JSONObject commonIdsObj){
//        return iStoreStockReportService.FnGetBalesWeightageReport(commonIdsObj);
//    }


    @PostMapping("/FnGetBalesWeightageReport/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> FnGetBalesWeightageReport(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> CottonBalesWeightage = new ArrayList<>();
        Long total_count = 0L;
        try {
            CottonBalesWeightage = iStoreStockReportService.FnGetBalesWeightageReport(commonIdsObj, PageCurrent, entriesPerPage);

            if (!CottonBalesWeightage.isEmpty()) {
                total_count = (Long) CottonBalesWeightage.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("CottonBalesWeightage", CottonBalesWeightage);

        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/getBottomDetailsStockReport/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> getBottomDetailsStockReport(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage) {
        Map<String, Object> response = new HashMap<>(); // Initialize response map
        List<Map<String, Object>> stockReportData = new ArrayList<>();
        Long total_count = 0L;
        try {
            stockReportData = iStoreStockReportService.BottomDetailsStockReportByDateDetails(commonIdsObj, PageCurrent, entriesPerPage);

            if (!stockReportData.isEmpty()) {
                total_count = (Long) stockReportData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("BottomDetailsStockReportData", stockReportData);

        } catch (Exception e) {
            response.put("success", 0);
            e.printStackTrace();
        }
        return response;
    }


    @PostMapping("/GetBottomDetailsStockReportToExport")
    public void GetBottomDetailsStockReportToExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> BottomDetailsStockReportDataToExport = new ArrayList<>();
        try {
            BottomDetailsStockReportDataToExport = iStoreStockReportService.GetBottomDetailsStockReportByDateDetailsToExport(commonIdsObj);
            if (BottomDetailsStockReportDataToExport != null && !BottomDetailsStockReportDataToExport.isEmpty()) {
                BottomDetailsStockReportDataToExport.forEach(entry ->
                        entry.put("stock_status", "A".equals(entry.get("stock_status")) ? "In Stock" :
                                "D".equals(entry.get("stock_status")) ? "Dispatched" :
                                        "Partial Dispatch"));
            }
            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("set_no", "Set No.");
            detailsColumns.put("material_code", "Material Code");
            detailsColumns.put("material_name", "Material Name");
            detailsColumns.put("customer_name", "Party Name");
            detailsColumns.put("supplier_name", "Supplier Name");
            detailsColumns.put("sr_no", "Serial No");
            detailsColumns.put("no_of_package", "No Of Cones");
            detailsColumns.put("gross_weight", "Gross Weight");
            detailsColumns.put("tare_weight", "Tare Weight");
            detailsColumns.put("net_weight", "Net Weight");
            detailsColumns.put("cone_type", "Cone Type");
            detailsColumns.put("bora_box", "Bora/Box");
            detailsColumns.put("stock_status", "Stock Status");


            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(
                    "no_of_package", "gross_weight",
                    "tare_weight", "net_weight"
            ));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();
            //Opening
//            subColumnsToMerge.put("gross_weight","Weight");
//            subColumnsToMerge.put("tare_weight","Weight");


            // Export the data to Excel
            exportToExcel(BottomDetailsStockReportDataToExport, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cotton Bales Weightage Report Export Excel Functionality

    @PostMapping("/GetCottonBalesWeightageStockReportToExport")
    public void GetCottonBalesWeightageStockReportToExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> CottonBalesWeightageReportDataToExport = new ArrayList<>();
        try {
            CottonBalesWeightageReportDataToExport = iStoreStockReportService.GetCottonBalesStockReportByDateDetailsToExport(commonIdsObj);

            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("mixing_chart_no", "Mixing Chart No.");
            detailsColumns.put("mixing_chart_date", "Mixing Chart Date");
            detailsColumns.put("uhml", "UHML");
            detailsColumns.put("mi", "ML");
            detailsColumns.put("ul", "UL");
            detailsColumns.put("mic", "MIC");
            detailsColumns.put("str", "STR");
            detailsColumns.put("rd", "RD");
            detailsColumns.put("b_plus", "B +");
            detailsColumns.put("total_neps", "Total Neps");
            detailsColumns.put("sc_n", "SC");
            detailsColumns.put("sfc_n", "SFC");
            detailsColumns.put("trash", "Trash");
            detailsColumns.put("moisture", "Moisture");

            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(
                    "uhml", "mi",
                    "ul", "mic", "str", "rd", "b_plus", "total_neps", "sc_n", "sfc_n", "trash", "moisture"
            ));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();

            // Export the data to Excel
            exportToExcel(CottonBalesWeightageReportDataToExport, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/FnGetBalesDailyIssueReport")
    public Map<String, Object> FnGetBalesDailyIssueReport(@RequestParam("commonIdsObj") JSONObject commonIdsObj) {
        Map<String, Object> response = new HashMap<>();

        String from_date = commonIdsObj.getString("from_date");
        String to_date = commonIdsObj.getString("to_date");
        String press_running_no_from = commonIdsObj.getString("press_running_no_from");
        String press_running_no_to = commonIdsObj.getString("press_running_no_to");
        String batch_no = commonIdsObj.getString("batch_no");
        String supplier_batch_no = commonIdsObj.getString("supplier_batch_no");
        List<String> distinctLotNos = new ArrayList<>();

        MapSqlParameterSource paramsForDailyIssue = new MapSqlParameterSource();
        StringBuilder queryForDailyIssue = new StringBuilder();

        queryForDailyIssue.append("WITH RECURSIVE date_series AS ( ");
        queryForDailyIssue.append("  SELECT DATE(:fromDate) AS transaction_date ");
        queryForDailyIssue.append("  UNION ALL ");
        queryForDailyIssue.append("  SELECT transaction_date + INTERVAL 1 DAY ");
        queryForDailyIssue.append("  FROM date_series ");
        queryForDailyIssue.append("  WHERE transaction_date < DATE(:toDate) ");
        queryForDailyIssue.append("), ");

        queryForDailyIssue.append("batch_series AS ( ");
        queryForDailyIssue.append("  SELECT DISTINCT batch_no FROM sm_material_stock_log WHERE is_delete = 0 ");
        if (!batch_no.equals("0")) {
            queryForDailyIssue.append(" AND batch_no IN (:distinctLotNos) ");
            distinctLotNos = Arrays.asList(batch_no.split(","));
            paramsForDailyIssue.addValue("distinctLotNos", distinctLotNos);
        }
        queryForDailyIssue.append(") ");

        queryForDailyIssue.append("SELECT ");
        queryForDailyIssue.append("  bs.batch_no, ");
        queryForDailyIssue.append("  ds.transaction_date, ");
        queryForDailyIssue.append("  COALESCE(SUM(smsl.quantity), 0) AS total_issue_quantity ");
        queryForDailyIssue.append("FROM batch_series bs ");
        queryForDailyIssue.append("CROSS JOIN date_series ds ");
        queryForDailyIssue.append("LEFT JOIN sm_material_stock_log smsl ");
        queryForDailyIssue.append("  ON bs.batch_no = smsl.batch_no ");
        queryForDailyIssue.append("  AND ds.transaction_date = smsl.transaction_date ");
        queryForDailyIssue.append("  AND smsl.is_delete = 0 ");
        queryForDailyIssue.append("  AND smsl.transaction_type = 'Issue' ");
        queryForDailyIssue.append("GROUP BY bs.batch_no, ds.transaction_date ");
        queryForDailyIssue.append("ORDER BY bs.batch_no, ds.transaction_date;");

// Add date parameters
        paramsForDailyIssue.addValue("fromDate", from_date);
        paramsForDailyIssue.addValue("toDate", to_date);

// Run the query
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> dailyIssueData = namedParameterJdbcTemplate.queryForList(
                queryForDailyIssue.toString(),
                paramsForDailyIssue
        );

        distinctLotNos = dailyIssueData.stream()
                .map(data -> data.get("batch_no").toString())
                .distinct()
                .collect(Collectors.toList());

// Query for Opening Balance

        StringBuilder queryForOpeningBalance = new StringBuilder();
        MapSqlParameterSource paramsForOpeningClosing = new MapSqlParameterSource();

        queryForOpeningBalance.append("WITH opening_stock AS ( ");
        queryForOpeningBalance.append("  SELECT ");
        queryForOpeningBalance.append("    product_material_id, ");
        queryForOpeningBalance.append("    batch_no, ");
        queryForOpeningBalance.append("    COALESCE(SUM(CASE WHEN transaction_date < :from_date THEN ");
        queryForOpeningBalance.append("      CASE ");
        queryForOpeningBalance.append("        WHEN transaction_type = 'Purchase' THEN quantity ");
        queryForOpeningBalance.append("        WHEN transaction_type = 'Issue' THEN -quantity ");
        queryForOpeningBalance.append("      END ");
        queryForOpeningBalance.append("    END), 0) AS opening_quantity ");
        queryForOpeningBalance.append("  FROM sm_material_stock_log ");
        queryForOpeningBalance.append("  WHERE is_delete = 0 ");
        queryForOpeningBalance.append("  GROUP BY product_material_id, batch_no ");
        queryForOpeningBalance.append(") ");

        queryForOpeningBalance.append("SELECT ");
        queryForOpeningBalance.append("  st.batch_no, ");
        queryForOpeningBalance.append("  grncb.supplier_batch_no AS supplier_batch_no, ");
        queryForOpeningBalance.append("  grncb.press_running_no_from AS press_running_no_from, ");
        queryForOpeningBalance.append("  grncb.press_running_no_to AS press_running_no_to, ");
        queryForOpeningBalance.append("  os.opening_quantity, ");
        queryForOpeningBalance.append(" SUM(CASE WHEN tr.transaction_type = 'Issue' AND tr.transaction_date BETWEEN :from_date AND :to_date THEN tr.quantity ELSE 0 END) AS issue_quantity, ");
        queryForOpeningBalance.append("  (os.opening_quantity + ");
        queryForOpeningBalance.append("    SUM(CASE WHEN tr.transaction_type = 'Purchase' ");
        queryForOpeningBalance.append("             AND tr.transaction_date BETWEEN :from_date AND :to_date ");
        queryForOpeningBalance.append("             THEN tr.quantity ELSE 0 END) - ");
        queryForOpeningBalance.append("    SUM(CASE WHEN tr.transaction_type = 'Issue' ");
        queryForOpeningBalance.append("             AND tr.transaction_date BETWEEN :from_date AND :to_date ");
        queryForOpeningBalance.append("             THEN tr.quantity ELSE 0 END) ");
        queryForOpeningBalance.append("  ) AS closing_quantity ");

        queryForOpeningBalance.append("FROM sm_product_material_stock_new st ");
        queryForOpeningBalance.append("LEFT JOIN sm_material_stock_log tr ");
        queryForOpeningBalance.append("  ON st.product_material_id = tr.product_material_id ");
        queryForOpeningBalance.append("  AND st.batch_no = tr.batch_no ");
        queryForOpeningBalance.append("LEFT JOIN opening_stock os ");
        queryForOpeningBalance.append("  ON st.product_material_id = os.product_material_id ");
        queryForOpeningBalance.append("  AND st.batch_no = os.batch_no ");
        queryForOpeningBalance.append("LEFT JOIN pt_grn_cotton_bales_details AS grncb ");
        queryForOpeningBalance.append("  ON grncb.goods_receipt_no = st.goods_receipt_no ");
        queryForOpeningBalance.append("  AND grncb.batch_no = st.batch_no ");
        queryForOpeningBalance.append("  AND grncb.is_delete = 0 ");
        queryForOpeningBalance.append("WHERE tr.is_delete = 0 AND st.batch_no IN (:distinctLotNos) ");

        if (!press_running_no_from.equals("0")) {
            queryForOpeningBalance.append(" AND grncb.press_running_no_from = :press_running_no_from ");
            paramsForOpeningClosing.addValue("press_running_no_from", press_running_no_from);
        }
        if (!press_running_no_to.equals("0")) {
            queryForOpeningBalance.append(" AND grncb.press_running_no_to = :press_running_no_to ");
            paramsForOpeningClosing.addValue("press_running_no_to", press_running_no_to);
        }
        if (!supplier_batch_no.equals("0")) {
            queryForOpeningBalance.append(" AND grncb.supplier_batch_no = :supplier_batch_no ");
            paramsForOpeningClosing.addValue("supplier_batch_no", supplier_batch_no);
        }

        queryForOpeningBalance.append("GROUP BY st.product_material_id, st.batch_no;");

        paramsForOpeningClosing.addValue("distinctLotNos", distinctLotNos);  // Ensure it's a List<String>
        paramsForOpeningClosing.addValue("from_date", from_date);
        paramsForOpeningClosing.addValue("to_date", to_date);

        System.out.println("Query For Opening Balance: " + queryForOpeningBalance);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate2 = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
        List<Map<String, Object>> openingBalanceAgainstLotNo = namedParameterJdbcTemplate2.queryForList(queryForOpeningBalance.toString(), paramsForOpeningClosing);

        response.put("DailyBalesIssueData", dailyIssueData);
        response.put("OpeningBalanceAgainstLotNos", openingBalanceAgainstLotNo);

        return response;
    }

    @PostMapping("/GetSizedBeamStockReportData/{PageCurrent}/{entriesPerPage}")
    public Map<String, Object> GetSizedBeamStockReportData(@RequestParam("commonIds") JSONObject commonIdsObj, @PathVariable("PageCurrent") Integer PageCurrent, @PathVariable("entriesPerPage") Integer entriesPerPage) {
        List<Map<String, Object>> SizedBeamStockReportData = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        Long total_count = 0L;
        try {
            SizedBeamStockReportData = iStoreStockReportService.GetSizedBeamStockReportData(commonIdsObj, PageCurrent, entriesPerPage);
            if (!SizedBeamStockReportData.isEmpty()) {
                total_count = (Long) SizedBeamStockReportData.get(0).get("total_count");
            }
            response.put("success", 1);
            response.put("total_count", total_count);
            response.put("SizedBeamStockReportData", SizedBeamStockReportData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/GetLoomProductionWastageReportData")
    public Map<String, Object> GetLoomProductionWastageReportData(@RequestParam("commonIds") JSONObject commonIdsObj) {
        List<Map<String, Object>> LoomProductionWastageReportData = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        try {
            LoomProductionWastageReportData = iStoreStockReportService.GetLoomProductionWastageReportData(commonIdsObj);

            response.put("success", 1);
            response.put("LoomProductionWastageReportData", LoomProductionWastageReportData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/GetLoomProductionWastageReportExport")
    public void GetLoomProductionWastageReportExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> LoomProductionWastageReportExportData = new ArrayList<>();
        try {
            LoomProductionWastageReportExportData = iStoreStockReportService.GetLoomProductionWastageReportData(commonIdsObj);

            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
            detailsColumns.put("production_wastage_types_name", "Production Wastage Type");
            detailsColumns.put("total_wastage", "Total Wastage");

            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList(
                    "total_wastage"
            ));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();
            // Export the data to Excel
            exportToExcel(LoomProductionWastageReportExportData, detailsColumns, totalsColumnHeader, response, commonIdsObj, subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/getFabricStockReportExport")
    public void GetFabricStockReportExport(@RequestParam("commonIds") JSONObject commonIdsObj, HttpServletResponse response) {
        List<Map<String, Object>> FabricStockReportDataToExport = new ArrayList<>();
        try {
            FabricStockReportDataToExport = iStoreStockReportService.GetFabricStockReportExport(commonIdsObj);
            Map<String, String> detailsColumns = new LinkedHashMap<>();
            detailsColumns.put("serial_no", "Sr. No.");
//            detailsColumns.put("product_category1_name", "Category Name");
            detailsColumns.put("batch_no", "Product Code");
            detailsColumns.put("material_name", "Product Name");
            detailsColumns.put("godown_name", "Godown Name");
            detailsColumns.put("opening_no_of_boxes", "No of Rolls");
            detailsColumns.put("opening_quantity", "Meter");
            detailsColumns.put("opening_weight", "Weight");
            detailsColumns.put("production_no_of_boxes", "No of Rolls");
            detailsColumns.put("production_quantity", "Meter");
            detailsColumns.put("production_weight", "Weight");
            detailsColumns.put("sales_no_of_boxes", "No of Rolls");
            detailsColumns.put("sales_quantity", "Meter");
            detailsColumns.put("sales_weight", "Weight");
            detailsColumns.put("sales_rejection_no_of_boxes", "No of Rolls");
            detailsColumns.put("sales_rejection_quantity", "Meter");
            detailsColumns.put("sales_rejection_weight", "Weight");
            detailsColumns.put("closing_no_of_boxes", "No of Rolls");
            detailsColumns.put("closing_balance_quantity", "Meter");
            detailsColumns.put("closing_balance_weight", "Weight");
            // Specify columns to calculate totals

            List<String> totalsColumnHeader = new ArrayList<>(Arrays.asList("opening_no_of_boxes","opening_quantity","opening_weight","production_no_of_boxes","production_quantity","production_weight","sales_no_of_boxes","sales_quantity","sales_weight","sales_rejection_no_of_boxes","sales_rejection_quantity","sales_rejection_weight","closing_no_of_boxes","closing_balance_quantity","closing_balance_weight"));

            Map<String, String> subColumnsToMerge = new LinkedHashMap<>();
            subColumnsToMerge.put("opening_no_of_boxes","Opening Balance");
            subColumnsToMerge.put("opening_quantity","Opening Balance");
            subColumnsToMerge.put("opening_weight","Opening Balance");
            subColumnsToMerge.put("production_no_of_boxes","Packing ");
            subColumnsToMerge.put("production_quantity","Packing ");
            subColumnsToMerge.put("production_weight","Packing ");
            subColumnsToMerge.put("sales_no_of_boxes","Sales");
            subColumnsToMerge.put("sales_quantity","Sales");
            subColumnsToMerge.put("sales_weight","Sales");
            subColumnsToMerge.put("sales_rejection_no_of_boxes","Sales Rejection");
            subColumnsToMerge.put("sales_rejection_quantity","Sales Rejection");
            subColumnsToMerge.put("sales_rejection_weight","Sales Rejection");
            subColumnsToMerge.put("closing_no_of_boxes","Closing Balance");
            subColumnsToMerge.put("closing_balance_quantity","Closing Balance");
            subColumnsToMerge.put("closing_balance_weight","Closing Balance");
            // Export the data to Excel
            exportToExcel(FabricStockReportDataToExport, detailsColumns, totalsColumnHeader, response, commonIdsObj,subColumnsToMerge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
