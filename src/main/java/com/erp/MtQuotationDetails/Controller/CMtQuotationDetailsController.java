package com.erp.MtQuotationDetails.Controller;

import com.erp.MtQuotationDetails.Repository.IMtSalesQuotationExistingExpectedFunctionalityViewRepository;
import com.erp.MtQuotationDetails.Repository.IMtSalesQuotationMasterTradingRepository;
import com.erp.MtQuotationDetails.Repository.IMtSalesQuotationTermsTradingViewRepository;
import com.erp.MtQuotationDetails.Service.IMtQuotationDetailsService;
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
import org.springframework.jdbc.core.JdbcTemplate;
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
@RequestMapping("/api/MtQuotationDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtQuotationDetailsController {

	@Value("${printOuts.file.path}")
	private String printOutsDirectory;

	@Autowired
	IMtQuotationDetailsService iMtQuotationDetailsService;

	@Autowired
	IMtSalesQuotationMasterTradingRepository iMtSalesQuotationMasterTradingRepository;

	@Autowired
	IMtSalesQuotationTermsTradingViewRepository iMtSalesQuotationTermsTradingViewRepository;

	@Autowired
	IMtSalesQuotationExistingExpectedFunctionalityViewRepository iMtSalesQuotationExistingExpectedFunctionalityViewRepository;

	@Autowired
	JdbcTemplate executequery;

	@PostMapping("/FnAddUpdateRecord/{isApprove}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("MtQuotationData") JSONObject jsonObject,
	                                             @PathVariable boolean isApprove) {
		Map<String, Object> responce = iMtQuotationDetailsService.FnAddUpdateRecord(jsonObject, isApprove);
		return responce;
	}

	@PostMapping("/FnAddUpdateQuotationFollowupRecord")
	public Map<String, Object> FnAddUpdateQuotationFollowupRecord(
			@RequestParam("MtQuotationFollowupData") JSONObject jsonObject) {
		return iMtQuotationDetailsService.FnAddUpdateQuotationFollowupRecord(jsonObject);
	}

	@DeleteMapping("/FnDeleteRecord/{quotation_version}/{company_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("quotation_no") String quotation_no,
	                                          @PathVariable int quotation_version, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iMtQuotationDetailsService.FnDeleteRecord(quotation_no, quotation_version, company_id, deleted_by);

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

			String filename = reapExcelDataFile.getOriginalFilename().substring(0,
					reapExcelDataFile.getOriginalFilename().lastIndexOf("."));

			String[] company_id = filename.split("@");
//			String quotation_no = company_id[0].replace("_", "/");

//			CMtSalesQuotationMasterTradingModel cmtQuotationMasterTradingModel = iMtSalesQuotationMasterTradingRepository
//					.getLastQuotationVersion(quotation_no, company_id[1]);

			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);
//			responce.put("quotationVersion", cmtQuotationMasterTradingModel.getQuotation_version());

		} catch (Exception e) {

			e.printStackTrace();
		}
		return responce;
	}

	// use API for edit
	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{quotation_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("quotation_no") String quotation_no,
	                                                                 @PathVariable int quotation_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iMtQuotationDetailsService.FnShowAllDetailsandMastermodelRecords(quotation_no, quotation_version,
				financial_year, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType)
			throws SQLException {
		return iMtQuotationDetailsService.FnShowAllReportRecords(pageable, reportType);

	}

	@PostMapping("/FnGetQuotationDetailsByItemStatus/{company_id}")
	public Map<String, Object> FnGetQuotationDetailsByItemStatus(@PathVariable int company_id,
	                                                             @RequestParam("QuotationInformation") JSONObject jsonObject) {
		return iMtQuotationDetailsService.FnGetQuotationDetailsByItemStatus(jsonObject, company_id);
	}

	@PostMapping("/FnShowEnquiryDetailsRecords/{company_id}")
	public Map<String, Object> FnShowEnquiryDetailsRecords(@RequestParam("enquiryNos") JSONObject enquiryNo,
	                                                       @PathVariable int company_id) throws SQLException {
		return iMtQuotationDetailsService.FnShowEnquiryDetailsRecords(enquiryNo, company_id);
	}

	@PostMapping("/FnSendEmail/{quotation_master_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int quotation_master_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("quotationPDF") MultipartFile purchaseOrderPDF) {

		// Ensure the upload directory exists; create it if not
		File uploadDir = new File(printOutsDirectory.toString());
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save the file to the specified directory
		String fileName = purchaseOrderPDF.getOriginalFilename();
		Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

		try {
			purchaseOrderPDF.transferTo(filePath.toFile());

			// Update the mail-attachments means add the newly created file-path.
			JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
			mailAttachments.put(filePath.toString());
			emailData.put("mailAttachmentFilePaths", mailAttachments);
			System.out.println("Quotation PDF File Saved: " + filePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iMtQuotationDetailsService.FnSendEmail(company_id,
				quotation_master_transaction_id, emailData);
		return emailSendingResponse;
	}

}
