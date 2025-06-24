package com.erp.HmManualAttendance.Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.erp.HmManualAttendance.Service.IHmManualAttendanceService;

@RestController
@RequestMapping("/api/HmManualAttendance")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmManualAttendanceController {

	@Autowired
	IHmManualAttendanceService iHmManualAttendanceService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("MannualAttendanceData") JSONObject jsonObject) {
		Map<String, Object> responce = iHmManualAttendanceService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{attendance_transaction_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int attendance_transaction_id, @PathVariable String deleted_by) {
		return iHmManualAttendanceService.FnDeleteRecord(attendance_transaction_id, deleted_by);

	}

//	@GetMapping("/FnShowParticularRecordForUpdate/{attendance_transaction_id}/{company_id}")
//	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int attendance_transaction_id,@PathVariable int company_id) {
//		Map<String, Object> responce = iHmManualAttendanceService.FnShowParticularRecordForUpdate(attendance_transaction_id,company_id);
//		return responce;
//	}
// 

	@PostMapping("/FnReadExcel")
	public HashMap<Object, Object> FnReadExcel(@RequestParam("file") MultipartFile reapExcelDataFile) {
		HashMap<Object, Object> responce = new HashMap<Object, Object>();
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
			List<String> columns = new ArrayList<String>();
			List<String> formFieldData = new ArrayList<String>();
			ArrayList<Object> data = new ArrayList<Object>();
			XSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println("=> " + sheet.getSheetName());
			DataFormatter dataFormatter = new DataFormatter();
			System.out.println("Iterating over Rows and Columns using Iterator");
			Iterator<Row> rowIterator = sheet.rowIterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				System.out.println("Row Number: " + row.getRowNum());
				Iterator<Cell> cellIterator = row.cellIterator();
				List<String> getData = new ArrayList<>();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int currentColumnIndex = cell.getColumnIndex();
					// Your logic for all columns...
					String cellValue = dataFormatter.formatCellValue(cell);

					if (row.getRowNum() == 9) {
						columns.add(cellValue);
					} else if (row.getRowNum() > 9) {
						getData.add(cellValue);

						// Quantity data entry check
						String decimalPattern = "([0-9]*)\\.([0-9]*)";
						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);

						// Apply conditions for specific columns
//				            if ((currentColumnIndex == 7 || currentColumnIndex == 19 || currentColumnIndex == 20 ||
//				                 currentColumnIndex == 20 || currentColumnIndex == 28) &&
//				                !StringUtils.isEmpty(cellValue) &&
//				                !(NumberUtils.isDigits(cellValue) || matchCellValue)) {
//				            	System.out.println("Wrong Numeric data at col-index: " + currentColumnIndex);
//				                responce.put("success", "0");
//				                responce.put("error", "Please enter valid data in file!...");
//				                return responce;
//				            }
					}

					// Adjusted else position
					if (row.getRowNum() >= 5 && row.getRowNum() <= 8 && cellValue.trim().length() > 0) {
						formFieldData.add(cellValue);
					}
					

					System.out.print(cellValue + "-" + currentColumnIndex + "\t");
				}

				if (!getData.isEmpty()) {
					data.add(getData);
				}

				System.out.println();
			}
			
			List<Map<String, Object>> excelData = transformData(data, columns);

			
			responce.put("success", 1);
			responce.put("data", excelData);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}
	
    public List<Map<String, Object>> transformData(ArrayList<Object> data, List<String> columns) {
        return data.stream()
                .filter(row -> row instanceof List) // Ensure that the element is a List
                .map(row -> (List<Object>) row) // Cast to List<Object>
                .map(row -> {
                    Map<String, Object> transformedRow = new HashMap<>();
                    for (int index = 0; index < columns.size(); index++) {
                        String columnName = columns.get(index);
                        Object columnValue = row.get(index);
                        String key;
                        if (columnName.equals("Employee Name")) {
                            key = "field_name";
                        } else {
                            key = columnName; // Use column name as key by default
                        }
                        transformedRow.put(key, columnValue);
                    }
                    return transformedRow;
                })
                .collect(Collectors.toList());
    }
	
	@PostMapping("/FnShowMannualAttendanceForUpdate")
	public Map<String, Object> FnShowMannualAttendanceForUpdate(@RequestParam("GetMannualAttendanceData") JSONObject jsonObject) {
		Map<String, Object> responce = iHmManualAttendanceService.FnShowMannualAttendanceRecordForUpdate(jsonObject);
		return responce;
	}
}
