package com.erp.XtSpinningProductionRfMaster.Controller;

import com.erp.XtSpinningProductionRfMaster.Service.IXtSpinningProductionRfMasterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/XtSpinningProductionRfMaster")
public class CXtSpinningProductionRfMasterController {

	private static Row row = null;
	@Autowired
	IXtSpinningProductionRfMasterService iXtSpinningProductionRfMasterService;
	int rowCount = 11;
	private XSSFSheet sheet;

	private static CellStyle getHeaderCellStyle(Workbook workbook) {
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		style.setFont(font);
		return style;
	}

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("SpinningProductionRfData") JSONObject jsonObject) {
		Map<String, Object> responce = iXtSpinningProductionRfMasterService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{spinning_production_rf_master_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int spinning_production_rf_master_id, @PathVariable String deleted_by) {
		Map<String, Object> resp = iXtSpinningProductionRfMasterService.FnDeleteRecord(spinning_production_rf_master_id, deleted_by);
		return resp;
	}

	@GetMapping("/FnShowParticularRecordUpdate/{spinning_production_rf_master_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordUpdate(@PathVariable int spinning_production_rf_master_id, @PathVariable int company_id) {
		return iXtSpinningProductionRfMasterService.FnShowParticularRecordUpdate(spinning_production_rf_master_id, company_id);
	}

	@GetMapping("/FnShowParticularShiftSummary/{shift}/{spinning_production_date}/{company_id}")
	public Map<String, Object> FnShowParticularShiftSummary(@PathVariable String shift, @PathVariable String spinning_production_date, @PathVariable int company_id) {
		return iXtSpinningProductionRfMasterService.FnShowParticularShiftSummary(shift, spinning_production_date, company_id);
	}

	@PostMapping("/FnShowProductionReport")
	public Map<String, Object> FnShowProductionReport(@RequestParam("reportRequest") JSONObject reportRequestJson, HttpServletResponse httpServletResponse) throws JsonProcessingException {
		// Convert JSONObject to a Map using Jackson ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> reportRequest = objectMapper.readValue(reportRequestJson.toString(), Map.class);
		return iXtSpinningProductionRfMasterService.FnShowProductionReport(reportRequest, httpServletResponse);
	}

	@PostMapping("/FnProductionReportExport")
	public void FnProductionReportExport(@RequestParam("reportExportRequest") JSONObject reportExportRequestJson, HttpServletResponse httpServletResponse) throws IOException {
		// Convert JSONObject to a Map using Jackson ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> reportRequest = objectMapper.readValue(reportExportRequestJson.toString(), Map.class);
		List<Map<String, Object>> productionReportColumns = (List<Map<String, Object>>) reportRequest.get("productionReportColumns");
		List<Map<String, Object>> productionReportTotalColumns = (List<Map<String, Object>>) reportRequest.get("productionReportTotalColumns");
		List<Map<String, Object>> productionShift = (List<Map<String, Object>>) reportRequest.get("productionShift");
		List<String> machines = (List<String>) reportRequest.get("machines");
		Map<String, Object> productionReportDetails = (Map<String, Object>) reportRequest.get("productionReportDetails");
		Map<String, Object> headerPartDetails = (Map<String, Object>) reportRequest.get("headerPartDetails");

		httpServletResponse.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		httpServletResponse.setHeader(headerKey, headerValue);

		XSSFWorkbook workbook = new XSSFWorkbook();

		writeHeaderLine(productionReportColumns, productionReportTotalColumns, productionShift, headerPartDetails, productionReportDetails, workbook);
		writeDataLines(productionReportColumns, productionReportTotalColumns, productionShift, machines, productionReportDetails, workbook);

//		Sheet Locked Closed
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();

		workbook.write(outputStream);
		workbook.close();

//      Set up HTTP headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setContentDispositionFormData("attachment", "example.xlsx");
		outputStream.close();
		System.out.println("Excel file has been generated successfully.");
	}

	private void writeHeaderLine(List<Map<String, Object>> productionReportColumns, List<Map<String, Object>> productionReportTotalColumns, List<Map<String, Object>> productionShift, Map<String, Object> headerPartDetails, Map<String, Object> productionReportDetails, XSSFWorkbook workbook) {
		AtomicInteger columnCount = new AtomicInteger(0);

		sheet = workbook.createSheet("Ring Frame");

//		Excel Column Heads
		CellStyle style = createCellStyle(workbook, true, (short) 12, HorizontalAlignment.CENTER);

		Row ringFrameShiftRow = sheet.createRow(rowCount);

		createCell(ringFrameShiftRow, columnCount.get(), "Ring Frame", style);
		columnCount.getAndIncrement();

		AtomicInteger startColumnIndex = new AtomicInteger(1); // Initial start column index
		AtomicInteger endColumnIndex = new AtomicInteger(productionReportColumns.size() - 1);

		IntStream.range(0, productionShift.size())
				.mapToObj(index -> new AbstractMap.SimpleEntry<>(index, productionShift.get(index)))
				.forEach(entry -> {
					int index = entry.getKey();
					Map<String, Object> productionShiftObj = entry.getValue();

					createCell(ringFrameShiftRow, columnCount.get(), productionShiftObj.get("property_name"), style);
					CellRangeAddress mergedRegion = new CellRangeAddress(rowCount, rowCount, startColumnIndex.get(), endColumnIndex.get());
					sheet.addMergedRegion(mergedRegion);

					mergeRegion(mergedRegion, style);

					startColumnIndex.set(endColumnIndex.get() + 1); // Update start column index for the next shift
					columnCount.set(endColumnIndex.get() + 1); // Update columnCount for the next shift

					if (productionShift.size() - 1 == index) {
						createCell(ringFrameShiftRow, columnCount.get(), "Totals", style);
						mergedRegion = new CellRangeAddress(rowCount, rowCount, startColumnIndex.get(), (startColumnIndex.get() + productionReportTotalColumns.size()) - 1);
						sheet.addMergedRegion(mergedRegion);

						mergeRegion(mergedRegion, style);
						endColumnIndex.set((startColumnIndex.get() + productionReportTotalColumns.size()) - 1);
						return;
					} else {
						endColumnIndex.getAndAdd(startColumnIndex.get() - 1);
					}
				});

		rowCount++;

		columnCount.set(0);
		Row productionDetailsRow = sheet.createRow(rowCount++);
		createCell(productionDetailsRow, columnCount.get(), "Production Details :", style);

		columnCount.set(0);
		Row row = sheet.createRow(rowCount++);

//		Create one cell at same row for machine no
		createCell(row, columnCount.get(), "M/C No", style);
		columnCount.getAndIncrement();

//		Iterate on production shift
		for (int shiftIndex = 0; shiftIndex < productionShift.size(); shiftIndex++) {
			String shift = (String) productionShift.get(shiftIndex).get("property_name");

//			Iterate on production report columns
			productionReportColumns.forEach(columnObject -> {
				String header = (String) columnObject.get("Header");
				String accessor = (String) columnObject.get("accessor");

				// Use the createCell method to create cells in your row
				if (!accessor.equals("machine_short_name")) {
					createCell(row, columnCount.get(), header, style);
					columnCount.getAndIncrement();
				}

			});

		}

//		Iterate on total of single machine on total shifts production
		productionReportTotalColumns.forEach(totalColumnsObj -> {
			String header = (String) totalColumnsObj.get("Header");

			// Use the createCell method to create cells in your row
			createCell(row, columnCount.get(), header, style);
			columnCount.getAndIncrement();

		});

//		Header details of company & their addresses
		columnCount.set(0);
		CellStyle headerCompanyStyle = createCellStyle(workbook, true, (short) 16, HorizontalAlignment.CENTER);
		Row headerRow = sheet.createRow(0);
		createCell(headerRow, columnCount.get(), headerPartDetails.get("company_name"), headerCompanyStyle);

		CellRangeAddress mergedCellRegion = new CellRangeAddress(0, 0, 0, endColumnIndex.get());
		sheet.addMergedRegion(mergedCellRegion);

		mergeRegion(mergedCellRegion, headerCompanyStyle);

		columnCount.set(0);
		CellStyle headerAddressStyle = createCellStyle(workbook, false, (short) 16, HorizontalAlignment.CENTER);
		headerRow = sheet.createRow(1);
		createCell(headerRow, columnCount.get(), headerPartDetails.get("company_address1"), headerAddressStyle);

		mergedCellRegion = new CellRangeAddress(1, 1, 0, endColumnIndex.get());
		sheet.addMergedRegion(mergedCellRegion);

		mergeRegion(mergedCellRegion, headerAddressStyle);


//		Header details of plant & production date
		columnCount.set(0);
		CellStyle headerStyle = createCellStyle(workbook, true, (short) 12, HorizontalAlignment.LEFT);
		headerRow = sheet.createRow(5);
		createCell(headerRow, columnCount.get(), "Plant : " + headerPartDetails.get("plant_name"), headerStyle);

		mergedCellRegion = new CellRangeAddress(5, 5, 0, endColumnIndex.get());
		sheet.addMergedRegion(mergedCellRegion);

		mergeRegion(mergedCellRegion, headerStyle);

		columnCount.set(0);
		headerRow = sheet.createRow(6);
		createCell(headerRow, columnCount.get(), "Production Date : " + headerPartDetails.get("spinning_production_date"), headerStyle);

		mergedCellRegion = new CellRangeAddress(6, 6, 0, endColumnIndex.get());
		sheet.addMergedRegion(mergedCellRegion);

		mergeRegion(mergedCellRegion, headerStyle);

	}

	private void writeDataLines(List<Map<String, Object>> productionReportColumns, List<Map<String, Object>> productionReportTotalColumns, List<Map<String, Object>> productionShift, List<String> machines, Map<String, Object> productionReportDetails, XSSFWorkbook workbook) {
		AtomicInteger columnCount = new AtomicInteger();
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();

		font.setFontHeight(11);
		style.setFont(font);

		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

//		Get day totals of all machines
		List<Map<String, Object>> productionDayTotalsArr = (List<Map<String, Object>>) productionReportDetails.get("dayTotals");
		Map<String, Object> productionDayTotalObj = productionDayTotalsArr.get(0);

		for (int machineNoIndex = 0; machineNoIndex < machines.size(); machineNoIndex++) {
			row = sheet.createRow(rowCount++); // create a row

			String machine_no = machines.get(machineNoIndex);
			List<Map<String, Object>> productionMachineDetail = (List<Map<String, Object>>) productionReportDetails.get(machine_no);
			List<Map<String, Object>> productionDetails = (List<Map<String, Object>>) productionMachineDetail.get(0).get("production_details");
			Map<String, Object> productionDetailsTotals = (Map<String, Object>) productionMachineDetail.get(0).get("totals");

//          Write machine no on first index
			createCell(row, columnCount.getAndIncrement(), machine_no, style);

			// Now you have the production details for the current machine_no, you can iterate over it
			int finalMachineNoIndex = machineNoIndex;

			IntStream.range(0, productionShift.size())
					.mapToObj(index -> new AbstractMap.SimpleEntry<>(index, productionShift.get(index)))
					.forEach(entry -> {
						int index = entry.getKey();
						Map<String, Object> productionShiftObj = entry.getValue();

						String shift = (String) productionShiftObj.get("property_name");

//						get shift wise all machine totals
						List<Map<String, Object>> productionAllMachineTotalsShiftWise = (List<Map<String, Object>>) productionReportDetails.get(shift);

						Map<String, Object> getShiftWiseProductionDetail = productionDetails.parallelStream()
								.filter(item -> item.get("shift").equals(shift))
								.findFirst().get();

						// Iterate over each column definition
						for (Map<String, Object> column : productionReportColumns) {
							String accessor = (String) column.get("accessor");

							// Retrieve data from productionDetail using the accessor
							if (!accessor.equals("machine_short_name")) {
								Object value = getShiftWiseProductionDetail.get(accessor);

								if (value.equals(null)) {
									value = "";
								}
								createCell(row, columnCount.getAndIncrement(), value, style);
							}

//			               Enter totals of particular shift of all machines
							if (machines.size() - 1 == finalMachineNoIndex) {
								if (accessor.equals("machine_short_name") && index == 0) {
									Row createTotalRow = sheet.createRow(rowCount + 1);
									createCell(createTotalRow, columnCount.get() - 1, "Total: ", style);
								}

								if (productionAllMachineTotalsShiftWise.get(0).containsKey("total_" + accessor)) {
//								    Check is row already exist
									Row existingRow = getExistingRow(sheet, rowCount + 1);

									createCell(existingRow, columnCount.get() - 1, productionAllMachineTotalsShiftWise.get(0).get("total_" + accessor), style);

								}

							}

//			               Enter day totals of particular shift of all machines
							if (machines.size() - 1 == finalMachineNoIndex && index == 0) {
								if (accessor.equals("machine_short_name") && index == 0) {
									Row createDayTotalRow = sheet.createRow(rowCount + 2);
									createCell(createDayTotalRow, columnCount.get() - 1, "Day Total: ", style);
								}

								if (productionDayTotalObj.containsKey(accessor)) {
//								    Check is row already exist
									Row existingRow = getExistingRow(sheet, rowCount + 2);

									createCell(existingRow, columnCount.get() - 1, productionDayTotalObj.get(accessor), style);

								}

							}
						}

						if (productionShift.size() - 1 == index) {
							for (Map<String, Object> column : productionReportTotalColumns) {
								String accessor = (String) column.get("accessor");

								// Retrieve data from productionDetail using the accessor
								Object value = productionDetailsTotals.get(accessor);

								if (value.equals(null)) {
									value = "";
								}
								createCell(row, columnCount.getAndIncrement(), value, style);
							}
						}


					});

		}

//      Add border to table rows in excel
//		addBorderToRows(sheet, workbook, 0, rowCount, 0, columnCount.get());
	}

	private void mergeRegion(CellRangeAddress mergedRegion, CellStyle style) {
		// Apply style to the merged region
		for (int rowIndex = mergedRegion.getFirstRow(); rowIndex <= mergedRegion.getLastRow(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}
			for (int columnIndex = mergedRegion.getFirstColumn(); columnIndex <= mergedRegion.getLastColumn(); columnIndex++) {
				Cell cell = row.getCell(columnIndex);
				if (cell == null) {
					cell = row.createCell(columnIndex);
				}
				cell.setCellStyle(style);
			}
		}
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

	private Row getExistingRow(Sheet sheet, int rowNum) {
		return StreamSupport.stream(sheet.spliterator(), false)
				.filter(row -> row.getRowNum() == rowNum)
				.findFirst()
				.orElse(null); // Return null if row doesn't exist
	}

	private CellStyle createCellStyle(XSSFWorkbook workbook, boolean fontBoldOrNot, short fontSize, HorizontalAlignment alignment) {
		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();

		// Set font boldness
		font.setBold(fontBoldOrNot);

		// Set font size
		font.setFontHeightInPoints(fontSize);

		style.setFont(font);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		// Set alignment
		style.setAlignment(alignment);

		return style;
	}

	private void addBorderToRows(Sheet sheet, XSSFWorkbook workbook, int startRowNum, int lastRowNum, int startColNum, int lastColNum) {
		// Add borders to cells up to column 3 (0-based index)
		for (int i = startRowNum; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				for (int j = startColNum; j < lastColNum; j++) {
					Cell cell = row.getCell(j);
					if (cell == null) {
						cell = row.createCell(j);
					}
					CellStyle style = workbook.createCellStyle();
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderTop(BorderStyle.THIN);
					cell.setCellStyle(style);
				}
			}
		}

	}
}
