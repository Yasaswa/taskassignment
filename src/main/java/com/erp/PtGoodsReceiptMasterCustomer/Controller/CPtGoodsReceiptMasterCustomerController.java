package com.erp.PtGoodsReceiptMasterCustomer.Controller;

import com.erp.PtGoodsReceiptMasterCustomer.Service.IPtGoodsReceiptMasterCustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/PtGoodsReceiptMasterCustomer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtGoodsReceiptMasterCustomerController {

	@Autowired
	IPtGoodsReceiptMasterCustomerService iPtGoodsReceiptMasterCustomerService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("GoodReceiptMasterCustomerData") JSONObject jsonObject) throws JsonProcessingException {
		Map<String, Object> responce = iPtGoodsReceiptMasterCustomerService.FnAddUpdateRecord(jsonObject);
		return responce;
	}

	@DeleteMapping("/FnDeleteRecord/{customer_id}/{company_id}/{UserName}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("customer_goods_receipt_no") String customer_goods_receipt_no, @PathVariable int customer_id, @PathVariable int company_id, @PathVariable String UserName) {
		return iPtGoodsReceiptMasterCustomerService.FnDeleteRecord(customer_goods_receipt_no, customer_id, company_id, UserName);

	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iPtGoodsReceiptMasterCustomerService.FnShowAllReportRecords(pageable, reportType);

	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{customer_goods_receipt_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("customer_goods_receipt_no") String customer_goods_receipt_no,
	                                                                 @PathVariable int customer_goods_receipt_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iPtGoodsReceiptMasterCustomerService.FnShowAllDetailsandMastermodelRecords(customer_goods_receipt_no, customer_goods_receipt_version, financial_year,
				company_id);
	}
	
	@PostMapping("/FnUpdateCustomerStockDetailsRecord")
	public Map<String, Object> FnUpdateCustomerStockDetailsRecord(@RequestParam("CustomerMaterialReceiptData") JSONObject jsonObject) {
		Map<String, Object> responce = iPtGoodsReceiptMasterCustomerService.FnUpdateCustomerStockDetailsRecord(jsonObject);
		return responce;
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
					if (row.getRowNum() == 10) {
						columns.add(cellValue);
					} else if (row.getRowNum() > 10) {
						getData.add(cellValue);

//	 						Quantity data entry check
						String decimalPattern = "([0-9]*)\\.([0-9]*)";
						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);
						int columnIndex = cell.getColumnIndex();
						if ((columnIndex == 3 || columnIndex == 4 || columnIndex == 5)
								&& !(NumberUtils.isDigits(cellValue) || matchCellValue == true)) {
							responce.put("success", 0);
							responce.put("error", "Please enter valid data in file!...");
							return responce;
						}
					} else if (row.getRowNum() >= 5 && row.getRowNum() <= 8 && cellValue.trim().length() > 0) {
						formFieldData.add(cellValue);
					}
					System.out.print(cellValue + "\t");
				}
				if (getData.size() != 0) {
					data.add(getData);
				}

				System.out.println();
			}

			responce.put("success", 1);
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);


		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("error", e.getMessage());
		}
		return responce;
	}


}
