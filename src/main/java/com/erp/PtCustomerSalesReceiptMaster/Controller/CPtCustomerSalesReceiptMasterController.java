package com.erp.PtCustomerSalesReceiptMaster.Controller;

import com.erp.PtCustomerSalesReceiptMaster.Service.IPtCustomerSalesReceiptMasterService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/PtCustomerSalesReceiptMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtCustomerSalesReceiptMasterController {

	@Value("${printOuts.file.path}")
	private String printOutsDirectory;

	@Autowired
	IPtCustomerSalesReceiptMasterService iPtCustomerSalesReceiptMasterService;

	@PostMapping("/FnAddUpdateRecord/{isApproveOrCancel}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("ptCustomerSalesReceiptData") JSONObject jsonObject, @PathVariable String isApproveOrCancel) {
		Map<String, Object> responce = iPtCustomerSalesReceiptMasterService.FnAddUpdateRecord(jsonObject, isApproveOrCancel);
		return responce;
	}

	@DeleteMapping("/FnDeleteRecord/{customer_sales_receipt_version}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("customer_sales_receipt_no") String customer_sales_receipt_no,
	                                          @PathVariable int customer_sales_receipt_version, @PathVariable int company_id) {
		return iPtCustomerSalesReceiptMasterService.FnDeleteRecord(customer_sales_receipt_no,
				customer_sales_receipt_version, company_id);

	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{customer_sales_receipt_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("customer_sales_receipt_no") String customer_sales_receipt_no,
	                                                                 @PathVariable int customer_sales_receipt_version, @PathVariable String financial_year,
	                                                                 @PathVariable int company_id) throws SQLException {
		return iPtCustomerSalesReceiptMasterService.FnShowAllDetailsandMastermodelRecords(customer_sales_receipt_no,
				customer_sales_receipt_version, financial_year, company_id);
	}


	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType)
			throws SQLException {
		return iPtCustomerSalesReceiptMasterService.FnShowAllReportRecords(pageable, reportType);

	}

	@PostMapping("/FnSendEmail/{customer_sales_receipt_master_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int customer_sales_receipt_master_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("customerSalesReceiptPDF") MultipartFile customerSalesReceiptPDF) {

		// Ensure the upload directory exists; create it if not
		File uploadDir = new File(printOutsDirectory.toString());
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save the file to the specified directory
		String fileName = customerSalesReceiptPDF.getOriginalFilename();
		Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

		try {
			customerSalesReceiptPDF.transferTo(filePath.toFile());

			// Update the mail-attachments means add the newly created file-path.
			JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
			mailAttachments.put(filePath.toString());
			emailData.put("mailAttachmentFilePaths", mailAttachments);
			System.out.println("Customer Sales Receipt PDF File Saved: " + filePath.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iPtCustomerSalesReceiptMasterService.FnSendEmail(company_id,
				customer_sales_receipt_master_transaction_id, emailData);
		return emailSendingResponse;
	}


}
