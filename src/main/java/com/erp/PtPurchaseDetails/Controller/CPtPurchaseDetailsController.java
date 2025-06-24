package com.erp.PtPurchaseDetails.Controller;

import com.erp.PtPurchaseDetails.Model.CPtPurchaseOrderDetailsViewModel;
import com.erp.PtPurchaseDetails.Repository.*;
import com.erp.PtPurchaseDetails.Service.IPtPurchaseDetailsService;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/PtPurchaseDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtPurchaseDetailsController {

	@Autowired
	IPtPurchaseDetailsService iPtPurchaseDetailsService;
	@Autowired
	IPtPurchaseOrderMasterRepository iPtPurchaseOrderMasterRepository;
	@Autowired
	IPtPurchaseOrderSchedulesViewRepository iPtPurchaseOrderSchedulesViewRepository;
	@Autowired
	IPtPurchaseOrderPaymentTermsRepository iPtPurchaseOrderPaymentTermsRepository;
	@Autowired
	IPtPurchaseOrderTermsTradingRepository iPtPurchaseOrderTermsTradingRepository;
	@Autowired
	IPtPurchaseOrderTaxSummaryViewRepository iPtPurchaseOrderTaxSummaryViewRepository;
	@Autowired
	JdbcTemplate executequery;
	@Value("${printOuts.file.path}")
	private String printOutsDirectory;

	@PostMapping("/FnAddUpdateRecord/{isApproveOrPreClosed}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("StPurchaseData") JSONObject jsonObject, @PathVariable String isApproveOrPreClosed) {
		Map<String, Object> responce = iPtPurchaseDetailsService.FnAddUpdateRecord(jsonObject, isApproveOrPreClosed);
		return responce;
	}


	@DeleteMapping("/FnDeleteRecord/{purchase_order_version}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("purchase_order_no") String purchase_order_no, @RequestParam("user_name") String user_name,
	                                          @PathVariable int purchase_order_version, @PathVariable int company_id) {
		return iPtPurchaseDetailsService.FnDeleteRecord(purchase_order_no, purchase_order_version, company_id, user_name);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CPtPurchaseOrderDetailsViewModel> FnShowAllActiveRecords(Pageable pageable,
	                                                                     @PathVariable int company_id) throws SQLException {
		Page<CPtPurchaseOrderDetailsViewModel> cptPurchaseOrderDetailsViewModel = iPtPurchaseDetailsService
				.FnShowAllActiveRecords(pageable, company_id);
		return cptPurchaseOrderDetailsViewModel;
	}

	@GetMapping("/FnShowParticularRecords/{purchase_order_details_transaction_id}/{company_id}")
	public Page<CPtPurchaseOrderDetailsViewModel> FnShowParticularRecord(
			@PathVariable int purchase_order_details_transaction_id, Pageable pageable, @PathVariable int company_id) {
		Page<CPtPurchaseOrderDetailsViewModel> cptPurchaseOrderDetailsViewModel = iPtPurchaseDetailsService
				.FnShowParticularRecord(purchase_order_details_transaction_id, pageable, company_id);
		return cptPurchaseOrderDetailsViewModel;

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

//						Quantity data entry check
						String decimalPattern = "([0-9]*)\\.([0-9]*)";
						boolean matchCellValue = Pattern.matches(decimalPattern, cellValue);
						int columnIndex = cell.getColumnIndex();
						if ((columnIndex == 7 || columnIndex == 8 || columnIndex == 10)
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

		/*
		 * try { XSSFWorkbook workbook = new
		 * XSSFWorkbook(reapExcelDataFile.getInputStream()); List<String> columns = new
		 * ArrayList<String>(); List<String> formFieldData = new ArrayList<String>();
		 * ArrayList<Object> data = new ArrayList<Object>(); XSSFSheet sheet =
		 * workbook.getSheetAt(0); System.out.println("=> " + sheet.getSheetName());
		 * DataFormatter dataFormatter = new DataFormatter();
		 * System.out.println("Iterating over Rows and Columns using Iterator");
		 * Iterator<Row> rowIterator = sheet.rowIterator(); while
		 * (rowIterator.hasNext()) { Row row = rowIterator.next();
		 * System.out.println("Row Number: " + row.getRowNum()); Iterator<Cell>
		 * cellIterator = row.cellIterator(); List<String> getData = new ArrayList<>();
		 *
		 * while (cellIterator.hasNext()) { Cell cell = cellIterator.next(); String
		 * cellValue = dataFormatter.formatCellValue(cell); if (row.getRowNum() == 10) {
		 * columns.add(cellValue); } else if (row.getRowNum() > 10) {
		 * getData.add(cellValue);
		 *
		 * // Quantity data entry check String decimalPattern = "([0-9]*)\\.([0-9]*)";
		 * boolean matchCellValue = Pattern.matches(decimalPattern, cellValue); if
		 * (cell.getColumnIndex() == 4 && !(NumberUtils.isDigits(cellValue) ||
		 * matchCellValue == true)) { responce.put("success", "0");
		 * responce.put("error", "Please enter valid data in file!..."); return
		 * responce; } } else if (row.getRowNum() >= 5 && row.getRowNum() <= 8) {
		 * formFieldData.add(cellValue); } System.out.print(cellValue + "\t"); } if
		 * (getData.size() != 0) { data.add(getData); }
		 *
		 * System.out.println(); }
		 *
		 * String filename = reapExcelDataFile.getOriginalFilename().substring(0,
		 * reapExcelDataFile.getOriginalFilename().lastIndexOf("."));
		 *
		 * String[] company_id = filename.split("@"); String purchase_order_no =
		 * company_id[0].replace("_", "/");
		 *
		 * CPtPurchaseOrderMasterModel cPtPurchaseOrderMasterModel =
		 * iPtPurchaseOrderMasterRepository
		 * .getLastPurchaseOrderVersion(purchase_order_no, company_id[1]);
		 *
		 * List<CPtPurchaseOrderSchedulesViewModel> cptPurchaseOrderSchedulesViewModel =
		 * iPtPurchaseOrderSchedulesViewRepository
		 * .FnShowPurchaseOrderSchedules(cPtPurchaseOrderMasterModel.
		 * getPurchase_order_no(),
		 * cPtPurchaseOrderMasterModel.getPurchase_order_version(),
		 * Integer.parseInt(company_id[1])); List<CPtPurchaseOrderPaymentTermsModel>
		 * cptPurchaseOrderPaymentTermsModel = iPtPurchaseOrderPaymentTermsRepository
		 * .FnShowPurchaseOrderPaymentTerms(cPtPurchaseOrderMasterModel.
		 * getPurchase_order_no(),
		 * cPtPurchaseOrderMasterModel.getPurchase_order_version(),
		 * Integer.parseInt(company_id[1])); List<CPtPurchaseOrderTermsModel>
		 * cptPurchaseOrderTermsTradingModel = iPtPurchaseOrderTermsTradingRepository
		 * .FnShowPurchaseOrderTermsTrading(cPtPurchaseOrderMasterModel.
		 * getPurchase_order_no(),
		 * cPtPurchaseOrderMasterModel.getPurchase_order_version(),
		 * Integer.parseInt(company_id[1])); List<CPtPurchaseOrderTaxSummaryViewModel>
		 * cptPurchaseOrderTaxSummaryViewModel =
		 * iPtPurchaseOrderTaxSummaryViewRepository
		 * .FnShowPurchaseOrderTaxSummaryView(cPtPurchaseOrderMasterModel.
		 * getPurchase_order_no(),
		 * cPtPurchaseOrderMasterModel.getPurchase_order_version(),
		 * Integer.parseInt(company_id[1]));
		 *
		 * responce.put("success", "1"); responce.put("data", data);
		 * responce.put("columns", columns); responce.put("formFieldData",
		 * formFieldData); responce.put("purchaseOrderVersion",
		 * cPtPurchaseOrderMasterModel.getPurchase_order_version());
		 * responce.put("PurchaseOrderSchedules", cptPurchaseOrderSchedulesViewModel);
		 * responce.put("PurchaseOrderPaymentTerms", cptPurchaseOrderPaymentTermsModel);
		 * responce.put("PurchaseOrderTermsTrading", cptPurchaseOrderTermsTradingModel);
		 * responce.put("PurchaseOrderTaxSummaryView",
		 * cptPurchaseOrderTaxSummaryViewModel); } catch (Exception e) {
		 *
		 * e.printStackTrace(); } return responce;
		 */
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

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{purchase_order_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("purchase_order_no") String purchase_order_no,
	                                                                 @PathVariable int purchase_order_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iPtPurchaseDetailsService.FnShowAllDetailsandMastermodelRecords(purchase_order_no,
				purchase_order_version, financial_year, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iPtPurchaseDetailsService.FnShowAllReportRecords(pageable, reportType);

	}

	@PostMapping("/FnShowIndentDetailsRecords/{company_id}")
	public Map<String, Object> FnShowIndentDetailsRecords(@RequestParam("indentNos") JSONObject indentNo,
	                                                      @PathVariable int company_id)
			throws SQLException {
		return iPtPurchaseDetailsService.FnShowIndentDetailsRecords(indentNo, company_id);
	}

	@PostMapping("/FnAcceptPurchaseOrder/{company_id}")
	public Map<String, Object> FnAcceptCustomerOrder(
			@RequestParam("POAcceptanceJson") JSONObject POAcceptanceJson,
			@PathVariable int company_id) {
		Map<String, Object> response = iPtPurchaseDetailsService.FnAcceptPurchaseOrder(POAcceptanceJson,
				company_id);
		return response;
	}

	@PostMapping("/FnSendEmail/{po_master_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int po_master_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("purchaseOrderPDF") MultipartFile purchaseOrderPDF) {

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
			System.out.println("Purchase Order PDF File Saved: " + filePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

//		return iPtPurchaseDetailsService.FnSendEmail(company_id, po_master_transaction_id, emailData);
		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iPtPurchaseDetailsService.FnSendEmail(company_id,
				po_master_transaction_id, emailData);
		return emailSendingResponse;
	}

	@GetMapping("/FnGetPartialgrnMaterials/{purchase_order_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnGetPartialgrnMaterials(@RequestParam("purchase_order_no") String purchase_order_no,
																	 @PathVariable int purchase_order_version, @PathVariable String financial_year, @PathVariable int company_id)
			throws SQLException {
		return iPtPurchaseDetailsService.FnGetPartialgrnMaterials(purchase_order_no,
				purchase_order_version, financial_year, company_id);
	}

	@PostMapping("/FnUpdatePreClosed/{company_id}/{po_master_transaction_id}")
	public Map<String, Object> FnUpdatePreClosed(@RequestParam("poPreClosedData") JSONObject jsonObject, @PathVariable Integer company_id, @PathVariable Integer po_master_transaction_id) {
		Map<String, Object> responce = iPtPurchaseDetailsService.FnUpdatePreClosedRecord(jsonObject, company_id, po_master_transaction_id);
		return responce;
	}

}
