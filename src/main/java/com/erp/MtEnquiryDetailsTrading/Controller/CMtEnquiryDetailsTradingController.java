package com.erp.MtEnquiryDetailsTrading.Controller;

import com.erp.MtEnquiryDetailsTrading.Model.CMtEnquiryDetailsTradingViewModel;
import com.erp.MtEnquiryDetailsTrading.Repository.IMtEnquiryExistingExpectedFunctionalityViewRepository;
import com.erp.MtEnquiryDetailsTrading.Repository.IMtEnquiryMasterTradingSummaryRepository;
import com.erp.MtEnquiryDetailsTrading.Repository.IMtEnquiryTermsTradingRepository;
import com.erp.MtEnquiryDetailsTrading.Service.IMtEnquiryDetailsTradingService;
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
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/MtEnquiryDetailsTrading")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtEnquiryDetailsTradingController {

	@Value("${printOuts.file.path}")
	private String printOutsDirectory;

	@Autowired
	IMtEnquiryDetailsTradingService iMtEnquiryDetailsTradingService;

	@Autowired
	IMtEnquiryMasterTradingSummaryRepository iMtEnquiryMasterTradingSummaryRepository;

	@Autowired
	IMtEnquiryTermsTradingRepository iMtEnquiryTermsTradingRepository;


	@Autowired
	IMtEnquiryExistingExpectedFunctionalityViewRepository iMtEnquiryExistingExpectedFunctionalityViewRepository;


	@PostMapping("/FnAddUpdateRecord/{isApprove}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("StEnquiryData") JSONObject jsonObject, @PathVariable boolean isApprove) {
		Map<String, Object> responce = iMtEnquiryDetailsTradingService.FnAddUpdateRecord(jsonObject, isApprove);
		return responce;

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CMtEnquiryDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CMtEnquiryDetailsTradingViewModel> cMtEnquiryDetailsTradingViewModel = iMtEnquiryDetailsTradingService.FnShowAllActiveRecords(pageable, company_id);
		return cMtEnquiryDetailsTradingViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{enquiry_details_transaction_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int enquiry_details_transaction_id, @PathVariable int company_id) {
		return iMtEnquiryDetailsTradingService.FnShowParticularRecordForUpdate(enquiry_details_transaction_id, company_id);
	}

	@GetMapping("/FnShowParticularRecords/{enquiry_details_transaction_id}/{company_id}")
	public Page<CMtEnquiryDetailsTradingViewModel> FnShowParticularRecord(@PathVariable int enquiry_details_transaction_id, Pageable pageable, @PathVariable int company_id) {
		return iMtEnquiryDetailsTradingService.FnShowParticularRecord(enquiry_details_transaction_id, pageable, company_id);

	}


	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{enquiry_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("enquiry_no") String enquiry_no,
	                                                                 @PathVariable int enquiry_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iMtEnquiryDetailsTradingService.FnShowAllDetailsandMastermodelRecords(enquiry_no, enquiry_version, financial_year,
				company_id);
	}

	@DeleteMapping("/FnDeleteRecord/{enquiry_version}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("enquiry_no") String enquiry_no, @PathVariable int enquiry_version, @PathVariable int company_id) {
		return iMtEnquiryDetailsTradingService.FnDeleteRecord(enquiry_no, enquiry_version, company_id);

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
						if (cell.getColumnIndex() == 8
								&& !(NumberUtils.isDigits(cellValue) || matchCellValue == true)) {
							responce.put("success", "0");
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

			String filename = reapExcelDataFile.getOriginalFilename().substring(0,
					reapExcelDataFile.getOriginalFilename().lastIndexOf("."));

			String[] company_id = filename.split("@");
			String enquiry_no = company_id[0].replace("_", "/");

//			CMtEnquiryMasterTradingModel cMtEnquiryMasterTradingModel = iMtEnquiryMasterTradingSummaryRepository.getLastPOEnquiryVersion(enquiry_no, company_id[1]);
//			List<CMtEnquiryExistingExpectedFunctionalityViewModel> cMtEnquiryExistingExpectedFunctionalityViewModel = iMtEnquiryExistingExpectedFunctionalityViewRepository
//					.FnShowEnquiryExistingTrading(cMtEnquiryMasterTradingModel.getEnquiry_no(), cMtEnquiryMasterTradingModel.getEnquiry_version(), Integer.parseInt(company_id[1]));
//			List<CMtEnquiryTermsTradingModel> MtEnquiryTermsTradingModel = iMtEnquiryTermsTradingRepository.FnShowEnquiryPoTerms(cMtEnquiryMasterTradingModel.getEnquiry_no(), cMtEnquiryMasterTradingModel.getEnquiry_version(), Integer.parseInt(company_id[1]));

			responce.put("success", "1");
			responce.put("data", data);
			responce.put("columns", columns);
			responce.put("formFieldData", formFieldData);
//			responce.put("enquiryversion", cMtEnquiryMasterTradingModel.getEnquiry_version());
//			responce.put("EnquiryExistingExpected", cMtEnquiryExistingExpectedFunctionalityViewModel);
//			responce.put("EnquiryPOTerms", MtEnquiryTermsTradingModel);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return responce;
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iMtEnquiryDetailsTradingService.FnShowAllReportRecords(pageable, reportType);

	}

	@PostMapping("/FnSendEmail/{enquiry_master_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int enquiry_master_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("EnquiryPDF") MultipartFile EnquiryPDF) {

		// Ensure the upload directory exists; create it if not
		File uploadDir = new File(printOutsDirectory.toString());
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save the file to the specified directory
		String fileName = EnquiryPDF.getOriginalFilename();
		Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

		try {
			EnquiryPDF.transferTo(filePath.toFile());

			// Update the mail-attachments means add the newly created file-path.
			JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
			mailAttachments.put(filePath.toString());
			emailData.put("mailAttachmentFilePaths", mailAttachments);
			System.out.println("Trading Enquiry PDF File Saved: " + filePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iMtEnquiryDetailsTradingService.FnSendEmail(company_id,
				enquiry_master_transaction_id, emailData);
		return emailSendingResponse;
	}

}
