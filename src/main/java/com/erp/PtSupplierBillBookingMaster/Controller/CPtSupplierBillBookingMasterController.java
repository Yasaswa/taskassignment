package com.erp.PtSupplierBillBookingMaster.Controller;

import com.erp.PtSupplierBillBookingMaster.Repository.IPtSupplierBillBookingMasterRepository;
import com.erp.PtSupplierBillBookingMaster.Service.IPtSupplierBillBookingMasterService;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/PtSupplierBillBookingMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtSupplierBillBookingMasterController {

	@Value("${printOuts.file.path}")
	private String printOutsDirectory;

	@Autowired
	IPtSupplierBillBookingMasterService iPtSupplierBillBookingMasterService;

	@Autowired
	IPtSupplierBillBookingMasterRepository iPtSupplierBillBookingMasterRepository;

	@PostMapping("/FnAddUpdateRecord/{isApproveOrCancel}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("PtsupplierBillBookingData") JSONObject jsonObject,
	                                             @PathVariable String isApproveOrCancel) {
		Map<String, Object> responce = iPtSupplierBillBookingMasterService.FnAddUpdateRecord(jsonObject,
				isApproveOrCancel);
		return responce;
	}

	@DeleteMapping("/FnDeleteRecord/{supplier_bill_booking_version}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("supplier_bill_booking_no") String supplier_bill_booking_no, @RequestParam("user_name") String user_name,
	                                          @PathVariable int supplier_bill_booking_version, @PathVariable int company_id) {
		return iPtSupplierBillBookingMasterService.FnDeleteRecord(supplier_bill_booking_no,
				supplier_bill_booking_version, company_id, user_name);

	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType)
			throws SQLException {
		return iPtSupplierBillBookingMasterService.FnShowAllReportRecords(pageable, reportType);

	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{supplier_bill_booking_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(
			@RequestParam("supplier_bill_booking_no") String supplier_bill_booking_no,
			@PathVariable int supplier_bill_booking_version, @PathVariable String financial_year,
			@PathVariable int company_id) throws SQLException {
		return iPtSupplierBillBookingMasterService.FnShowAllDetailsandMastermodelRecords(supplier_bill_booking_no,
				supplier_bill_booking_version, financial_year, company_id);
	}

	@PostMapping("/FnShowGoodReceiptSummaryRecords")
	public Map<String, Object> FnShowGoodReceiptSummaryRecords(@RequestParam("goodReceiptNos") JSONObject goodReceiptNo,
	                                                           Pageable pageable) {
		return iPtSupplierBillBookingMasterService.FnShowGoodReceiptSummaryRecords(goodReceiptNo, pageable);
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
						if (cell.getColumnIndex() == 4
								&& !(NumberUtils.isDigits(cellValue) || matchCellValue == true)) {
							responce.put("success", "0");
							responce.put("error", "Please enter valid data in file!...");
							return responce;
						}
					} else if (row.getRowNum() >= 5 && row.getRowNum() <= 8) {
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

			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return responce;
	}

	@PostMapping("/FnSendEmail/{supplier_bill_booking_master_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int supplier_bill_booking_master_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("billBookingPDF") MultipartFile billBookingPDF) {

		// Ensure the upload directory exists; create it if not
		File uploadDir = new File(printOutsDirectory.toString());
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save the file to the specified directory
		String fileName = billBookingPDF.getOriginalFilename();
		Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

		try {
			billBookingPDF.transferTo(filePath.toFile());

			// Update the mail-attachments means add the newly created file-path.
			JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
			mailAttachments.put(filePath.toString());
			emailData.put("mailAttachmentFilePaths", mailAttachments);
			System.out.println("Bill Booking PDF File Saved: " + filePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iPtSupplierBillBookingMasterService.FnSendEmail(company_id,
				supplier_bill_booking_master_transaction_id, emailData);
		return emailSendingResponse;
	}

	@GetMapping("/FnShowBillBookingDetailsRecords/{supplier_id}/{company_id}/{bill_book_type_id}")
	public Map<String, Object> FnShowBillBookingDetailsRecords(@PathVariable int supplier_id, @PathVariable int company_id, @PathVariable int bill_book_type_id) {
		return iPtSupplierBillBookingMasterService.FnShowBillBookingDetailsRecords(supplier_id, company_id , bill_book_type_id);
	}

	@GetMapping("/FnShowSalesInvoiceDetailsRecords/{customer_id}/{company_id}")
	public Map<String, Object> FnShowSalesInvoiceDetailsRecords(@PathVariable int customer_id, @PathVariable int company_id) {
		return iPtSupplierBillBookingMasterService.FnShowSalesInvoiceDetailsRecords(customer_id, company_id);
	}

}
