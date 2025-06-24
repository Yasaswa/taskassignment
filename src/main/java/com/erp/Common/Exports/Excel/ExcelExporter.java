package com.erp.Common.Exports.Excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ExcelExporter {
    private final XSSFWorkbook workbook;
    private final JSONObject listUsers;
    int rowCount = 4;
    private XSSFSheet sheet;

    public ExcelExporter(JSONObject exportJson) {
        this.listUsers = exportJson;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        int columnCount = 0;
        sheet = workbook.createSheet("Users");
        Row row = null;

        CellStyle styleForFilterElements = workbook.createCellStyle();
        XSSFFont fontForFilterElements = workbook.createFont();
        fontForFilterElements.setBold(true);
        fontForFilterElements.setFontHeight(12);
        styleForFilterElements.setFont(fontForFilterElements);

        JSONObject filtrKeyVals = (JSONObject) listUsers.get("filtrKeyValue");
        rowCount++;

//		Excel Filter values
        if (!filtrKeyVals.keySet().isEmpty()) {
            int count = 0;
            for (int filterKeyIndex = 0; filterKeyIndex < filtrKeyVals.length(); filterKeyIndex++) {
                if (count == 0) {
                    row = sheet.createRow(rowCount);
                    sheet.addMergedRegion(new CellRangeAddress(rowCount, // first row (0-based)
                            rowCount, // last row (0-based)
                            count, // first column (0-based)
                            1 // last column (0-based)
                    ));
                } else {
                    row = sheet.getRow(rowCount);
                    sheet.addMergedRegion(new CellRangeAddress(rowCount, // first row (0-based)
                            rowCount, // last row (0-based)
                            count, // first column (0-based)
                            count + 1 // last column (0-based)
                    ));
                }
                Object excelDataValue = filtrKeyVals.getString(Integer.toString(filterKeyIndex));
                createCell(row, count, excelDataValue, styleForFilterElements);
                if (rowCount == 8) {
                    rowCount = 5;
                    count += 2;
                } else {
                    rowCount++;
                }

            }
            rowCount = 8;
            rowCount++;
        }

//		Excel Column Heads

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);

        style.setFont(font);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        JSONArray columnNames = (JSONArray) listUsers.get("columns");

        row = sheet.createRow(rowCount++);
        for (int colKeyIndex = 0; colKeyIndex < columnNames.length(); colKeyIndex++) {
            JSONObject columnObject = columnNames.getJSONObject(colKeyIndex);
            String header = columnObject.getString("Headers");

            // Use the createCell method to create cells in your row
            createCell(row, columnCount, header, style);

            columnCount++;
        }


    }

//	private void writeDataLines() {
//		int columnCount = 0;
//		Row row = null;
//		CellStyle style = workbook.createCellStyle();
//		XSSFFont font = workbook.createFont();
//
//		font.setFontHeight(11);
//		style.setFont(font);
//
//		style.setBorderTop(BorderStyle.THIN);
//		style.setBorderBottom(BorderStyle.THIN);
//		style.setBorderLeft(BorderStyle.THIN);
//		style.setBorderRight(BorderStyle.THIN);
//
////		JSONObject columnNames = (JSONObject) listUsers.get("columns");
//		JSONArray columnNames = (JSONArray) listUsers.get("columns");
//		JSONObject columnValues = (JSONObject) listUsers.get("allData");
//
//		for (int jsonAllDataKeys = 0; jsonAllDataKeys < columnValues.length(); jsonAllDataKeys++) {
//			row = sheet.createRow(rowCount++);
//			columnCount = 0;
//			JSONObject data = (JSONObject) columnValues.get(Integer.toString(jsonAllDataKeys));
//			for (int colKeyIndex = 0; colKeyIndex < columnNames.length(); colKeyIndex++) {
//				JSONObject columnObject = columnNames.getJSONObject(colKeyIndex);
//				String accessor = columnObject.getString("accessor");
//				// Use opt to avoid JSONException if the key is not present
//				Object excelDataValue = data.opt(accessor);
//
//				// If the key is not found, excelDataValue will be null, set it to an empty string
//				if (excelDataValue == null || JSONObject.NULL.equals(excelDataValue)) {
//					excelDataValue = "";
//				} else if (accessor.contains("date")) {
//					// Format date from yyyy-mm-dd to dd-mm-yyyy
//					String dateStr = excelDataValue.toString();
//					try {
//						SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
//						SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
//						Date date = fromFormat.parse(dateStr);
//						excelDataValue = toFormat.format(date);
//					} catch (ParseException e) {
//						// Handle parse exception if the date format is incorrect
//						e.printStackTrace();
////						excelDataValue = dateStr; // Use original value if parsing fails
//					}
//				}
//				createCell(row, columnCount++, excelDataValue, style);
//			}
//		}
//
//		// Use IntStream to generate column indices
//		int[] columnsToAutoSize = IntStream.range(0, columnCount).toArray();
//		autoSizeColumns(sheet, columnsToAutoSize); // Columns you want to autofit
//
////		Create Excel Header
//		CellStyle styleHeading = workbook.createCellStyle();
//		XSSFFont fontHeading = workbook.createFont();
//
//		fontHeading.setFontHeight(16);
//		styleHeading.setFont(fontHeading);
//		fontHeading.setBold(true);
//		styleHeading.setAlignment(HorizontalAlignment.CENTER);
//		row = sheet.createRow(0);
//
//		JSONObject headingJson = (JSONObject) listUsers.get("headings");
//
//		Object company = headingJson.get("CompanyName");
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnCount));
//		createCell(row, 0, company, styleHeading);
//
//		// Style for address
//
//		CellStyle styleAddr = workbook.createCellStyle();
//		XSSFFont fontAddr = workbook.createFont();
//
//		fontAddr.setFontHeight(13);
//		styleAddr.setFont(fontAddr);
//		styleAddr.setAlignment(HorizontalAlignment.CENTER);
//
//		Object address = headingJson.get("CompanyAddress");
//		boolean isAddressRowCreated = false;
//
//		if (address != null && !address.toString().isEmpty() && !"null".equals(address.toString())) {
//			row = sheet.createRow(1);
//			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, columnCount));
//			createCell(row, 0, address, styleAddr);
//			isAddressRowCreated = true;  // Set the flag to true as the address row was created
//		}
//
//		if (isAddressRowCreated) {
//			row = sheet.createRow(2);  // Create the second row if the first row was created
//		} else {
//			row = sheet.createRow(1);  // Otherwise, create this as the first row
//		}
//
//		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, columnCount));
//
////      Style for Report Name
//		CellStyle styleReport = workbook.createCellStyle();
//		XSSFFont fontReport = workbook.createFont();
//
//		fontReport.setFontHeight(13);
//		styleReport.setFont(fontReport);
//		fontReport.setBold(true);
//		styleReport.setAlignment(HorizontalAlignment.CENTER);
//
//		if (isAddressRowCreated) {
//			row = sheet.createRow(3);  // Create the third row if the first row was created
//		} else {
//			row = sheet.createRow(2);  // Otherwise, create this as the second row
//		}
//
//		Object reportName = headingJson.get("ReportName");
//		sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, columnCount));
//		createCell(row, 0, reportName, styleReport);
//
//	}

    private void writeDataLines() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();

        font.setFontHeight(11);
        style.setFont(font);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        JSONArray columnNames = (JSONArray) listUsers.get("columns");
        JSONObject columnValues = (JSONObject) listUsers.get("allData");

        // Use IntStream to generate row indices
        IntStream.range(0, columnValues.length())
                .forEach(jsonAllDataKeys -> {
                    Row row = sheet.createRow(rowCount++);
                    final int[] columnCount = {0};
                    JSONObject data = (JSONObject) columnValues.get(Integer.toString(jsonAllDataKeys));

                    // Use IntStream to generate column indices
                    IntStream.range(0, columnNames.length())
                            .forEach(colKeyIndex -> {
                                JSONObject columnObject = columnNames.getJSONObject(colKeyIndex);
                                String accessor = columnObject.getString("accessor");
                                Object excelDataValue = data.opt(accessor);

                                if (excelDataValue == null || JSONObject.NULL.equals(excelDataValue)) {
                                    excelDataValue = "";
                                } else if (accessor.contains("date")) {
                                    String dateStr = excelDataValue.toString();
                                    try {
                                        SimpleDateFormat fromFormat = new SimpleDateFormat("yyyy-MM-dd");
                                        SimpleDateFormat toFormat = new SimpleDateFormat("dd-MM-yyyy");
                                        Date date = fromFormat.parse(dateStr);
                                        excelDataValue = toFormat.format(date);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
//                                      excelDataValue = dateStr; // Use original value if parsing fails
                                    }
                                }
                                createCell(row, columnCount[0]++, excelDataValue, style);
                            });
                });

        // Use IntStream to generate column indices
        int[] columnsToAutoSize = IntStream.range(0, columnNames.length()).toArray();
        autoSizeColumns(sheet, columnsToAutoSize); // Columns you want to autofit

//      Create Excel Header
        CellStyle styleHeading = workbook.createCellStyle();
        XSSFFont fontHeading = workbook.createFont();

        fontHeading.setFontHeight(16);
        styleHeading.setFont(fontHeading);
        fontHeading.setBold(true);
        styleHeading.setAlignment(HorizontalAlignment.CENTER);
        Row row = sheet.createRow(0);

        JSONObject headingJson = (JSONObject) listUsers.get("headings");

        Object company = headingJson.get("CompanyName");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNames.length()));
        createCell(row, 0, company, styleHeading);

        // Style for address

        CellStyle styleAddr = workbook.createCellStyle();
        XSSFFont fontAddr = workbook.createFont();

        fontAddr.setFontHeight(13);
        styleAddr.setFont(fontAddr);
        styleAddr.setAlignment(HorizontalAlignment.CENTER);

        Object address = headingJson.get("CompanyAddress");
        boolean isAddressRowCreated = false;

        if (address != null && !address.toString().isEmpty() && !"null".equals(address.toString())) {
            row = sheet.createRow(1);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, columnNames.length()));
            createCell(row, 0, address, styleAddr);
            isAddressRowCreated = true;  // Set the flag to true as the address row was created
        }

        if (isAddressRowCreated) {
            row = sheet.createRow(2);  // Create the second row if the first row was created
        } else {
            row = sheet.createRow(1);  // Otherwise, create this as the first row
        }

        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, columnNames.length()));

//      Style for Report Name
        CellStyle styleReport = workbook.createCellStyle();
        XSSFFont fontReport = workbook.createFont();

        fontReport.setFontHeight(13);
        styleReport.setFont(fontReport);
        fontReport.setBold(true);
        styleReport.setAlignment(HorizontalAlignment.CENTER);

        if (isAddressRowCreated) {
            row = sheet.createRow(3);  // Create the third row if the first row was created
        } else {
            row = sheet.createRow(2);  // Otherwise, create this as the second row
        }

        Object reportName = headingJson.get("ReportName");
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 0, columnNames.length()));
        createCell(row, 0, reportName, styleReport);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
        } else {
            cell.setCellValue(value.toString()); // Use toString() for other types
        }
        cell.setCellStyle(style);
    }

    private void autoSizeColumns(Sheet sheet, int[] columns) {
        for (int column : columns) {
            sheet.autoSizeColumn(column);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        String checkExportKey = listUsers.getString("key");


        if (checkExportKey.equals("bomExport")) {
            JSONArray columnsToUnlockLock = listUsers.getJSONArray("editable_cols");

            // Stream for rows starting from row index 10 to rowCount
            IntStream.range(10, rowCount)
                    .mapToObj(sheet::getRow)
                    .filter(row -> row != null)  // Ensure row is not null
                    .forEach(row -> {
                        // Stream for columns in columnsToUnlockLock
                        IntStream.range(0, columnsToUnlockLock.length())
                                .map(columnsToUnlockLock::getInt)
                                .mapToObj(row::getCell)
                                .filter(cell -> cell != null)  // Ensure cell is not null
                                .forEach(cell -> {
                                    CellStyle cellStyle = workbook.createCellStyle();
                                    cellStyle.cloneStyleFrom(cell.getCellStyle());

                                    cellStyle.setLocked(false);
                                    cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
                                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                                    cell.setCellStyle(cellStyle);
                                });
                    });

        }


        String checkRegisterKey = null;
        if (listUsers.has("keys")) {
            checkRegisterKey = listUsers.getString("keys");
        }
        if ("register".equals(checkRegisterKey)) {
            // check for only employee Attendance register
            JSONObject allData = listUsers.getJSONObject("allData");

            // Create a red CellStyle
            CellStyle redStyle = workbook.createCellStyle();
            redStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            redStyle.setFillPattern(FillPatternType.FINE_DOTS);

            // Iterate through rows starting from row index 10 to rowCount
            IntStream.range(10, rowCount)
                    .mapToObj(sheet::getRow)
                    .filter(row -> row != null)  // Ensure row is not null
                    .forEach(row -> {
                        int rowIndex = row.getRowNum(); // Get the actual row index

                        // Convert rowIndex to a suitable key if needed
                        String rowKey = String.valueOf(rowIndex - 10); // Adjust index based on starting point

                        if (allData.has(rowKey)) {
                            JSONObject entry = allData.getJSONObject(rowKey);

                            if ("M".equals(entry.optString("attendance_flag"))) {
                                // Apply red style to every cell in the row
//								for (Cell cell : row) {
//									if (cell != null) {
//										cell.setCellStyle(redStyle);
//									}
//								}
                                Cell inTimeCell = row.getCell(4);
                                if (inTimeCell == null) {
                                    inTimeCell = row.createCell(4);
                                }
                                Cell outTimeCell = row.getCell(5);
                                if (outTimeCell == null) {
                                    outTimeCell = row.createCell(5);
                                }

                                // Apply red style to cells
                                inTimeCell.setCellStyle(redStyle);
                                outTimeCell.setCellStyle(redStyle);
                            }

                        }
                    });
        }

//		Sheet Locked Start
//		sheet.protectSheet("");
//		Sheet Locked Closed
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}
