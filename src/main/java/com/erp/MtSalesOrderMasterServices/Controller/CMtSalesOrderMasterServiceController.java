package com.erp.MtSalesOrderMasterServices.Controller;

import com.erp.MtSalesOrderMasterServices.Model.CMtSalesOrderMasterServicesSummaryViewModel;
import com.erp.MtSalesOrderMasterServices.Service.IMtSalesOrderMasterService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;


@RestController
@RequestMapping("/api/MtSalesOrderMasterService")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtSalesOrderMasterServiceController {

	@Autowired
	IMtSalesOrderMasterService iMtSalesOrderMasterService;

	@Value("${printOuts.file.path}")
	private String printOutsDirectory;

	@PostMapping("/FnAddUpdateRecord/{isApprove}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("MtSalesOrderServiceData") JSONObject jsonObject, @PathVariable boolean isApprove) {
		Map<String, Object> response = iMtSalesOrderMasterService.FnAddUpdateRecord(jsonObject, isApprove);
		return response;

	}

	@DeleteMapping("/FnDeleteRecord/{sales_order_version}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("sales_order_no") String sales_order_no,
	                                          @PathVariable int sales_order_version, @PathVariable int company_id ,String deleted_by) {
		return iMtSalesOrderMasterService.FnDeleteRecord(sales_order_no, sales_order_version, company_id,deleted_by);

	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{sales_order_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("sales_order_no") String sales_order_no,  @PathVariable int sales_order_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iMtSalesOrderMasterService.FnShowAllDetailsandMastermodelRecords(sales_order_no, sales_order_version, financial_year,
				company_id);
	}

	@GetMapping("/FnShowSalesOrderDetailsTradingCustomerRecord/{company_id}")
	public Page<CMtSalesOrderMasterServicesSummaryViewModel> FnShowSalesOrderDetailsTradingCustomerRecord(@RequestParam("customer_order_no") String customer_order_no, Pageable pageable, @PathVariable int company_id) {
		return iMtSalesOrderMasterService.FnShowSalesOrderDetailsTradingCustomerRecord(customer_order_no, pageable, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iMtSalesOrderMasterService.FnShowAllReportRecords(pageable, reportType);

	}

	@GetMapping("/FnGetQuotationNoList/{company_id}")
	public Map<String, Object> FnGetEnquiryNoList(@PathVariable int company_id) {
		return iMtSalesOrderMasterService.FnGetQuotationNoList(company_id);
	}

	@PostMapping("/FnShowQuotationDetailsRecords/{company_id}")
	public Map<String, Object> FnShowEnquiryDetailsRecords(@RequestParam("quotationNos") JSONObject quotationNo, @PathVariable int company_id) {
		return iMtSalesOrderMasterService.FnShowQuotationDetailsRecords(quotationNo, company_id);
	}


	@PostMapping("/FnGetQuotationDetailsByItemStatus/{company_id}")
	public Map<String, Object> FnGetQuotationDetailsByItemStatus(@PathVariable int company_id,
	                                                             @RequestParam("QuotationInformation") JSONObject jsonObject) {
		return iMtSalesOrderMasterService.FnGetQuotationDetailsByItemStatus(jsonObject, company_id);
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

//						// Quantity data entry check
//						String decimalPattern = "([0-9]*)\\.([0-9]*)";
//						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);
//						if (cell.getColumnIndex() == 4
//								&& !(NumberUtils.isDigits(cellValue) || matchCellValue == true)) {
//							responce.put("success", "0");
//							responce.put("error", "Please enter valid data in file!...");
//							return responce;
//						}
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

//			String filename = reapExcelDataFile.getOriginalFilename().substring(0,
//					reapExcelDataFile.getOriginalFilename().lastIndexOf("."));
//
//			String[] company_id = filename.split("@");

			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return responce;
	}

	@PostMapping("/FnAcceptCustomerOrder/{company_id}")
	public Map<String, Object> FnAcceptCustomerOrder(
			@RequestParam("customerSOAcceptanceJson") JSONObject customerSOAcceptanceJson,
			@PathVariable int company_id) {
		Map<String, Object> response = iMtSalesOrderMasterService.FnAcceptCustomerOrder(customerSOAcceptanceJson,
				company_id);
		return response;
	}

	@PostMapping("/FnSendEmail/{sales_order_master_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int sales_order_master_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("salesOrderPDF") MultipartFile salesOrderPDF) {

		// Ensure the upload directory exists; create it if not
		File uploadDir = new File(printOutsDirectory.toString());
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save the file to the specified directory
		String fileName = salesOrderPDF.getOriginalFilename();
		Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

		try {
			salesOrderPDF.transferTo(filePath.toFile());

			// Update the mail-attachments means add the newly created file-path.
			JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
			mailAttachments.put(filePath.toString());
			emailData.put("mailAttachmentFilePaths", mailAttachments);
			System.out.println("Sales Order PDF File Saved: " + filePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iMtSalesOrderMasterService.FnSendEmail(company_id,
				sales_order_master_transaction_id, emailData);
		return emailSendingResponse;
	}


}
