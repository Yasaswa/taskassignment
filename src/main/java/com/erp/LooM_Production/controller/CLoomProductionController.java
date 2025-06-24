package com.erp.LooM_Production.controller;

import com.erp.XtWeavingProductionLoomMaster.Repository.IXtWeavingProductionLoomMasterRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/LoomProduction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CLoomProductionController {

	@Autowired
	IXtWeavingProductionLoomMasterRepository iXtWeavingProductionLoomMasterRepository;

	ArrayList<Map<String, Object>> productBasedproperties = new ArrayList<>();
	@PostMapping("/FnReadExcel")
	public Map<String, Object> uploadExcel(@RequestParam("file") MultipartFile file) {
		Map<String, Object> response = new HashMap<>();

		try {
			String filename = file.getOriginalFilename();

			// Normalize filenames for comparison
			List<String> existingFiles = iXtWeavingProductionLoomMasterRepository.getExistingImportFileName(filename);

			System.out.println("existingFiles" + existingFiles);

			// Check if the uploaded file name already exists in the list of existing file names
			if (existingFiles.contains(filename)) {
				response.put("success", false);
				response.put("message", "File with the same name already exists.");
				return response;
			}

			response.put("filename", filename);

			List<Map<String, Object>> sheetData = new ArrayList<>();
			List<List<String>> sheetColumns = new ArrayList<>();

			// Detect file type and create appropriate Workbook
			try (InputStream inputStream = new ByteArrayInputStream(file.getBytes());
				 Workbook workbook = createWorkbook(inputStream, filename)) {

				// Debug: Print the number of sheets in the workbook
				System.out.println("workbook sheets : " + workbook.getNumberOfSheets());

				// Process the third sheet only
				int sheetIndex = 2; // Index of the third sheet
				Sheet sheet = workbook.getSheetAt(sheetIndex);
				String sheetName = sheet.getSheetName();
				System.out.println("sheetName: " + sheetName);

				Map<String, Object> sheetInfo = new HashMap<>();
				List<String> columns = new ArrayList<>();
				List<List<String>> data = new ArrayList<>();

				FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

				Iterator<Row> rowIterator = sheet.rowIterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					Cell dateCell = row.getCell(0);
					if (dateCell != null) {
						String dateValue = getCellValueAsString(dateCell, evaluator);
						if (!"Total".equalsIgnoreCase(dateValue.trim())) {
							Iterator<Cell> cellIterator = row.cellIterator();
							List<String> rowData = new ArrayList<>();
							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next();
								String cellValue = getCellValueAsString(cell, evaluator).replaceAll("\\n|↵", "");
								if (row.getRowNum() == 3) {
									if (!cellValue.isEmpty()) {
										columns.add(cellValue);
									}
								} else {
									rowData.add(cellValue);
								}
							}
							if (!rowData.isEmpty()) {
								data.add(rowData);
							}
						}
					}
				}

				sheetInfo.put("columns", columns);
				sheetInfo.put("data", data);
				sheetColumns.add(columns);
				sheetData.add(sheetInfo);
			}

			Map<String, Object> transformedData = transformData(sheetData, sheetColumns);

			response.put("formFieldAllSheetData", transformedData);
			response.put("productBasedproperties", productBasedproperties);
			response.put("success", true);

		} catch (Exception e) {
			response.put("success", false);
			response.put("message", "Error processing the Excel file: " + e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * Create a Workbook instance based on the file type.
	 */
	private Workbook createWorkbook(InputStream inputStream, String filename) throws IOException, IOException {
		if (filename.endsWith(".xlsx")) {
			return new SXSSFWorkbook(new XSSFWorkbook(inputStream)); // For .xlsx files
		} else if (filename.endsWith(".xls")) {
			return new HSSFWorkbook(inputStream); // For .xls files
		} else {
			throw new IllegalArgumentException("Invalid file format. Only .xls and .xlsx are supported.");
		}
	}


	// Round off numeric values to 2 decimal places and Format the date as
	// "yyyy/MM/dd"
	private String getCellValueAsString(Cell cell, FormulaEvaluator evaluator) {
		// Check if cell is null
		if (cell == null) {
			return ""; // Return empty string
		}
		// Determine cell type and return corresponding string value
		switch (cell.getCellType()) {
			case STRING:
				// If cell type is string, return string value
				return cell.getStringCellValue().trim();
			case NUMERIC:
				// If cell type is numeric, format value accordingly
				if (DateUtil.isCellDateFormatted(cell)) {
					// If cell contains date, format as "yyyy/MM/dd"
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					return sdf.format(date);
				} else {
					// If cell contains numeric value, format to include only four decimal places
					double numericValue = cell.getNumericCellValue();
					DecimalFormat df = new DecimalFormat("#.####"); // Format to include four decimal places
					return df.format(numericValue);
				}
			case BOOLEAN:
				// If cell type is boolean, return boolean value as string
				return String.valueOf(cell.getBooleanCellValue());
			case FORMULA:
				// If cell type is formula, evaluate and return value accordingly
				CellValue cellValue = evaluator.evaluate(cell);
				switch (cellValue.getCellType()) {
					case STRING:
						return cellValue.getStringValue();
					case NUMERIC:
						double numericValue = cellValue.getNumberValue();
						DecimalFormat df = new DecimalFormat("#.####"); // Format to include four decimal places
						return df.format(numericValue);
					case BOOLEAN:
						return String.valueOf(cellValue.getBooleanValue());
					default:
						return ""; // or null, depending on your use case
				}
			default:
				return ""; // or null, depending on your use case
		}
	}

	private Map<String, Object> transformSheet(List<String> columns, List<List<String>> data) {
		List<Map<String, Object>> transformedData = new ArrayList<>(); // List to store transformed data

		List<String> setNos = new ArrayList<>();

		// Create new columns list with "Date" and "Shift" added
		List<String> newColumns = new ArrayList<>(columns); // Create a new list to avoid modifying the original
		newColumns.remove("Shiftname"); // Remove the "Shiftname" column
		newColumns.add(0, "Shift"); // Add "Shift" column at the beginning
		newColumns.add(0, "Date"); // Add "Date" column at the beginning

		// Iterate over data rows skipping the first two elements
		for (int rowIndex = 2; rowIndex < data.size(); rowIndex++) {
			List<String> row = data.get(rowIndex);
			Map<String, Object> transformedRow = new LinkedHashMap<>(); // Use LinkedHashMap to maintain order

			// Extract Date and Shift from Shiftname
			Map<String, String> dateAndShift = extractDateAndShift(row.get(0));
			transformedRow.put("Date", dateAndShift.get("Date"));
			transformedRow.put("Shift", dateAndShift.get("Shift"));

			// Iterate over columns and transform data accordingly
			for (int i = 1; i < columns.size(); i++) {
				String column = columns.get(i).trim(); // Trim whitespace from column name
				String cellValue = i < row.size() ? row.get(i) : null;

				// Transform data for specific column if necessary
				if (column.equalsIgnoreCase("Beam")) {
					if(!cellValue.isEmpty()){
						Map<String, String> beamData = transformBeamData(cellValue);
						transformedRow.put("Beam", beamData.get("Beam"));
						transformedRow.put("set_no", beamData.get("set_no"));
						String setNo = beamData.get("set_no") != null ? beamData.get("set_no").toString() : null;
						if (setNo != null && !setNos.contains(setNo)) {
							setNos.add(setNo);
						}
					}
				} else {
					transformedRow.put(column, cellValue); // Add transformed cell value to row map
				}
			}
			transformedData.add(transformedRow); // Add transformed row to transformed data list
		}

		List<String> sortNos = transformedData.parallelStream()
				.map(dat -> (String) dat.get("Style")) // Extract the "Style" values
				.collect(Collectors.toList()); // Collect into a list
		productBasedproperties = iXtWeavingProductionLoomMasterRepository.FnGetProductBasedProperties(sortNos);


		ArrayList<Map<String, Object>> materialStyles = iXtWeavingProductionLoomMasterRepository.FnGetMaterialStyles(setNos);

		// Create map to store transformed sheet data
		Map<String, Object> transformedSheet = new HashMap<>();
		transformedSheet.put("columns", newColumns); // Add new columns to transformed sheet map
		transformedSheet.put("data", transformedData); // Add transformed data to transformed sheet map
		transformedSheet.put("materialStyles", materialStyles);
		return transformedSheet; // Return transformed sheet map
	}

	// Method to extract Date and Shift from Shiftname
	private Map<String, String> extractDateAndShift(String shiftName) {
		Map<String, String> dateAndShift = new HashMap<>();
		if (shiftName != null && shiftName.matches("\\d{4}/\\d{2}/\\d{2}\\.[AB]")) {
			String[] parts = shiftName.split("\\.");
//	        dateAndShift.put("Date", parts[0]); // Extract Date
			dateAndShift.put("Date", parts[0].replace("/", "-")); // Extract Date and replace slashes with hyphens
			String shiftKey = parts[1]; // Extract Shift key (A or B)
			// Map shift key to shift name
			Map<String, String> shiftMap = new HashMap<>();
			shiftMap.put("A", "I");
			shiftMap.put("B", "II");
			dateAndShift.put("Shift", shiftMap.getOrDefault(shiftKey, shiftKey)); // Extract Shift
		} else {
			dateAndShift.put("Date", shiftName); // If Shiftname doesn't match the pattern, use it as Date
			dateAndShift.put("Shift", ""); // Set Shift to empty string
		}
		return dateAndShift;
	}

	// Method to transform data for all sheets
	private Map<String, Object> transformData(List<Map<String, Object>> sheetData, List<List<String>> sheetColumns) {
		Map<String, Object> transformedDataMap = new LinkedHashMap<>(); // Map to store transformed data

		// Iterate over sheet data and transform each sheet
		for (int i = 0; i < sheetData.size(); i++) {
			Map<String, Object> originalSheet = sheetData.get(i); // Get original sheet data
			List<String> columns = sheetColumns.get(i); // Retrieve columns for this sheet
			List<List<String>> data = (List<List<String>>) originalSheet.get("data"); // Retrieve data for this sheet

			// Transform data for the current sheet
			Map<String, Object> transformedSheet = transformSheet(columns, data);

			// Use the sheet name from the third sheet instead of the default name
			String sheetName = "Sheet " + (i + 1); // Default name if the name is not present
			if (i == 2) { // If it's the third sheet
				sheetName = sheetData.get(i).get("name").toString(); // Get the name of the third sheet
			}
			transformedDataMap.put(sheetName, transformedSheet); // Add transformed sheet data to transformed data map
		}
		return transformedDataMap; // Return transformed data map
	}

	
	private static Map<String, String> transformBeamData(String cellValue) {
		Map<String, String> beamData = new HashMap<>();
		if (cellValue != null) {
			
			String[] parts = cellValue.split("[=-]");

			String firstPart = parts[0] + "-" + parts[1]; // This will be "40"

			String secondPart = parts.length > 2 ? parts[2] : "0"; // This will be "6069" if available, else an empty string

			// Putting values into the beamData map
			beamData.put("Beam", firstPart);
			beamData.put("set_no", secondPart);
		}
		return beamData;
	}
}