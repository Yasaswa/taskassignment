package com.erp.MtSalesQuotationMasterServices.Controller;

import com.erp.MtSalesQuotationMasterServices.Service.IMtSalesQuotationMasterServicesService;
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
@RequestMapping("/api/MtSalesQuotationMasterServices")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtSalesQuotationMasterServicesController {

	@Value("${printOuts.file.path}")
	private String printOutsDirectory;

	@Autowired
	IMtSalesQuotationMasterServicesService iMtSalesQuotationMasterServicesService;

	@PostMapping("/FnAddUpdateRecord/{isApprove}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("MtSalesQuotationServiceData") JSONObject jsonObject, @PathVariable boolean isApprove) {
		Map<String, Object> responce = iMtSalesQuotationMasterServicesService.FnAddUpdateRecord(jsonObject, isApprove);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{quotation_version}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("quotation_no") String quotation_no, @PathVariable int quotation_version, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iMtSalesQuotationMasterServicesService.FnDeleteRecord(quotation_no, quotation_version, company_id, deleted_by);

	}

	@PostMapping("/FnAddUpdateQuotationFollowupRecord")
	public Map<String, Object> FnAddUpdateQuotationFollowupRecord(
			@RequestParam("MtQuotationFollowupData") JSONObject jsonObject) {
		return iMtSalesQuotationMasterServicesService.FnAddUpdateQuotationFollowupRecord(jsonObject);
	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{quotation_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("quotation_no") String quotation_no, @PathVariable int quotation_version, @PathVariable String financial_year,
	                                                                 @PathVariable int company_id) throws SQLException {
		return iMtSalesQuotationMasterServicesService.FnShowAllDetailsandMastermodelRecords(quotation_no,
				quotation_version, financial_year, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType)
			throws SQLException {
		return iMtSalesQuotationMasterServicesService.FnShowAllReportRecords(pageable, reportType);

	}

	@GetMapping("/FnGetEnquiryNoList/{company_id}")
	public Map<String, Object> FnGetEnquiryNoList(@PathVariable int company_id) {
		return iMtSalesQuotationMasterServicesService.FnGetEnquiryNoList(company_id);
	}

	@PostMapping("/FnShowEnquiryDetailsRecords/{company_id}")
	public Map<String, Object> FnShowEnquiryDetailsRecords(@RequestParam("enquiryNos") JSONObject enquiryNo, @PathVariable int company_id) {
		return iMtSalesQuotationMasterServicesService.FnShowEnquiryDetailsRecords(enquiryNo, company_id);
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
					int currentColumnIndex = cell.getColumnIndex();
					// Your logic for all columns...
					String cellValue = dataFormatter.formatCellValue(cell);

					if (row.getRowNum() == 10) {
						columns.add(cellValue);
					} else if (row.getRowNum() > 10) {
						getData.add(cellValue);

						// Quantity data entry check
						String decimalPattern = "([0-9]*)\\.([0-9]*)";
						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);

						// Apply conditions for specific columns
//				            if ((currentColumnIndex == 7 || currentColumnIndex == 10 || currentColumnIndex == 19 ||
//				                 currentColumnIndex == 20 || currentColumnIndex == 27 || currentColumnIndex == 28) &&
//				                !StringUtils.isEmpty(cellValue) &&
//				                !(NumberUtils.isDigits(cellValue) || matchCellValue)) {
//				                responce.put("success", "0");
//				                responce.put("error", "Please enter valid data in file!...");
//				                return responce;
//				            }
					}

					// Adjusted else position
					if (row.getRowNum() >= 5 && row.getRowNum() <= 8) {
						formFieldData.add(cellValue);
					}

					System.out.print(cellValue + "\t");
				}

				if (!getData.isEmpty()) {
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

	@PostMapping("/FnSendEmail/{quotation_master_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int quotation_master_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("quotationPDF") MultipartFile quotationPDF) {

		// Ensure the upload directory exists; create it if not
		File uploadDir = new File(printOutsDirectory.toString());
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save the file to the specified directory
		String fileName = quotationPDF.getOriginalFilename();
		Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

		try {
			quotationPDF.transferTo(filePath.toFile());

			// Update the mail-attachments means add the newly created file-path.
			JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
			mailAttachments.put(filePath.toString());
			emailData.put("mailAttachmentFilePaths", mailAttachments);
			System.out.println("Sales Order PDF File Saved: " + filePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iMtSalesQuotationMasterServicesService.FnSendEmail(company_id,
				quotation_master_transaction_id, emailData);
		return emailSendingResponse;
	}

}
