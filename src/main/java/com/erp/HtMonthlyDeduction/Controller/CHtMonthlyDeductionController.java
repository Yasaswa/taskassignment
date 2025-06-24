package com.erp.HtMonthlyDeduction.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.erp.HtMonthlyDeduction.Service.IHtMonthlyDeductionService;

@RestController
@RequestMapping("/api/HtMonthlyDeduction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHtMonthlyDeductionController {

	@Autowired
	IHtMonthlyDeductionService iHtMonthlyDeductionService;

	@PostMapping("/FnAddUpdateRecord")

	public Map<String, Object> FnAddUpdateRecord(@RequestParam("HmMonthlyDeductionData") JSONObject jsonObject) {
		Map<String, Object> resp = new HashMap<>();
		resp = iHtMonthlyDeductionService.FnAddUpdateRecord(jsonObject);
		return resp;
	}

	@DeleteMapping("/FnDeleteRecord/{monthly_deduction_master_transaction_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int monthly_deduction_master_transaction_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iHtMonthlyDeductionService.FnDeleteRecord(monthly_deduction_master_transaction_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{monthly_deduction_master_transaction_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(
			@PathVariable int monthly_deduction_master_transaction_id, @PathVariable int company_id)  {
		return  iHtMonthlyDeductionService.FnShowParticularRecordForUpdate(monthly_deduction_master_transaction_id, company_id);
	}


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
					String cellValue = dataFormatter.formatCellValue(cell);
					if (row.getRowNum() == 9) {
						columns.add(cellValue);
					} else if (row.getRowNum() > 9) {
						getData.add(cellValue);
					} else if (row.getRowNum() >= 4 && row.getRowNum() <= 7 && cellValue.trim().length() > 0) {
						formFieldData.add(cellValue);
					}
					System.out.print(cellValue + "\t");
				}
				if (getData.size() != 0) {
					data.add(getData);
				}
				System.out.println();
			}

			String filename = reapExcelDataFile.getOriginalFilename().substring(0,
					reapExcelDataFile.getOriginalFilename().lastIndexOf("."));

			String[] company_id = filename.split("@");
//			String enquiry_no = company_id[0].replace("_", "/");

			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return responce;
	}

 
}
