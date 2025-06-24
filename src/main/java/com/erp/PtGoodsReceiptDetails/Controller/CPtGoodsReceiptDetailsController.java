package com.erp.PtGoodsReceiptDetails.Controller;

import com.erp.PtGoodsReceiptDetails.Model.CPtGoodsReceiptDetailsViewModel;
import com.erp.PtGoodsReceiptDetails.Repository.IPtGoodsReceiptMasterRepository;
import com.erp.PtGoodsReceiptDetails.Repository.IPtGoodsReceiptsNotesTaxSummaryViewRepository;
import com.erp.PtGoodsReceiptDetails.Service.IPtGoodsReceiptDetailsService;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/PtGoodsReceiptDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtGoodsReceiptDetailsController {

	@Autowired
	IPtGoodsReceiptDetailsService iPtGoodsReceiptDetailsService;

	@Autowired
	IPtGoodsReceiptMasterRepository iPtGoodsReceiptMasterRepository;

	@Autowired
	IPtGoodsReceiptsNotesTaxSummaryViewRepository iPtGoodsReceiptsNotesTaxSummaryViewRepository;

	@Autowired
	JdbcTemplate executequery;

	@PostMapping("/FnAddUpdateRecord/{isApprove}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("GoodReceiptNoteData") JSONObject jsonObject, @PathVariable boolean isApprove) {
		Map<String, Object> responce = iPtGoodsReceiptDetailsService.FnAddUpdateRecord(jsonObject, isApprove);
		return responce;
	}

	@DeleteMapping("/FnDeleteRecord/{goods_receipt_master_transaction_id}/{UserName}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int goods_receipt_master_transaction_id, @PathVariable String UserName) {
		return iPtGoodsReceiptDetailsService.FnDeleteRecord(goods_receipt_master_transaction_id, UserName);

	}


	@GetMapping("/FnShowParticularRecords/{goods_receipt_details_transaction_id}/{company_id}")
	public Page<CPtGoodsReceiptDetailsViewModel> FnShowParticularRecord(
			@PathVariable int goods_receipt_details_transaction_id, Pageable pageable, @PathVariable int company_id) {
		Page<CPtGoodsReceiptDetailsViewModel> cptGoodsReceiptDetailsViewModel = iPtGoodsReceiptDetailsService
				.FnShowParticularRecord(goods_receipt_details_transaction_id, pageable, company_id);
		return cptGoodsReceiptDetailsViewModel;

	}

	@PostMapping("/FnShowPurchaseOrderDetailsTradingPurchaseRecord/{company_id}")
	public Map<String, Object> FnShowPurchaseOrderDetailsTradingPurchaseRecord(
			@RequestParam("purchaseOrderNos") JSONObject purchaseOrderNo, Pageable pageable,
			@PathVariable int company_id) {
		return iPtGoodsReceiptDetailsService.FnShowPurchaseOrderDetailsTradingPurchaseRecord(purchaseOrderNo, pageable,
				company_id);
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

//						Quantity data entry check
						String decimalPattern = "([0-9]*)\\.([0-9]*)";
						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);
						if ((cell.getColumnIndex() == 8 || cell.getColumnIndex() == 9 || cell.getColumnIndex() == 10 || cell.getColumnIndex() == 11 || cell.getColumnIndex() == 14 || cell.getColumnIndex() == 29)
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

	public List<Map<String, Object>> transformData(ArrayList<Object> data, List<String> columns) {
		return data.stream()
				.filter(row -> row instanceof List) // Ensure that the element is a List
				.map(row -> (List<Object>) row) // Cast to List<Object>
				.map(row -> IntStream.range(0, columns.size())
						.boxed()
						.collect(Collectors.toMap(columns::get, index -> row.get(index))))
				.collect(Collectors.toList());
	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{goods_receipt_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(
			@RequestParam("goods_receipt_no") String goods_receipt_no, @PathVariable int goods_receipt_version,
			@PathVariable String financial_year, @PathVariable int company_id) throws SQLException {
		return iPtGoodsReceiptDetailsService.FnShowAllDetailsandMastermodelRecords(goods_receipt_no,
				goods_receipt_version, financial_year, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType)
			throws SQLException {
		return iPtGoodsReceiptDetailsService.FnShowAllReportRecords(pageable, reportType);

	}

	@PostMapping("/FnShowPurchaseOrderDetailsRecords")
	public Map<String, Object> FnShowPurchaseOrderDetailsRecords(@RequestParam("purchaseOrderNos") JSONObject purchaseOrderNo) {
		return iPtGoodsReceiptDetailsService.FnShowPurchaseOrderDetailsRecords(purchaseOrderNo);
	}

}
