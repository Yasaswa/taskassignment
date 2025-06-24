package com.erp.PtSupplierPurchasePaymentMaster.Controller;

import com.erp.PtSupplierPurchasePaymentMaster.Service.IPtSupplierPurchasePaymentMasterService;
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
@RequestMapping("/api/PtSupplierPurchasePaymentMaster")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPtSupplierPurchasePaymentMasterController {

	@Value("${printOuts.file.path}")
	private String printOutsDirectory;
	@Autowired
	IPtSupplierPurchasePaymentMasterService iPtSupplierPurchasePaymentMasterService;

	@PostMapping("/FnAddUpdateRecord/{isApproveOrCancel}")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("ptSupplierPurchasePaymentData") JSONObject jsonObject, @PathVariable String isApproveOrCancel) {
		Map<String, Object> responce = iPtSupplierPurchasePaymentMasterService.FnAddUpdateRecord(jsonObject, isApproveOrCancel);
		return responce;
	}


	@DeleteMapping("/FnDeleteRecord/{supplier_purchase_payment_version}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("supplier_purchase_payment_no") String supplier_purchase_payment_no,
	                                          @PathVariable int supplier_purchase_payment_version, @PathVariable int company_id) {
		return iPtSupplierPurchasePaymentMasterService.FnDeleteRecord(supplier_purchase_payment_no,
				supplier_purchase_payment_version, company_id);

	}

	@GetMapping("/FnShowAllDetailsAndMastermodelRecords/{supplier_purchase_payment_version}/{financial_year}/{company_id}")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("supplier_purchase_payment_no") String supplier_purchase_payment_no,
	                                                                 @PathVariable int supplier_purchase_payment_version, @PathVariable String financial_year,
	                                                                 @PathVariable int company_id) throws SQLException {
		return iPtSupplierPurchasePaymentMasterService.FnShowAllDetailsandMastermodelRecords(supplier_purchase_payment_no,
				supplier_purchase_payment_version, financial_year, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType)
			throws SQLException {
		return iPtSupplierPurchasePaymentMasterService.FnShowAllReportRecords(pageable, reportType);

	}

	@PostMapping("/FnSendEmail/{supplier_purchase_payment_master_transaction_id}/{company_id}")
	public Map<String, Object> FnSendEmail(@PathVariable int supplier_purchase_payment_master_transaction_id,
	                                       @PathVariable int company_id, @RequestParam("EmailData") JSONObject emailData,
	                                       @RequestParam("purchasePaymentPDF") MultipartFile purchasePaymentPDF) {

		// Ensure the upload directory exists; create it if not
		File uploadDir = new File(printOutsDirectory.toString());
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// Save the file to the specified directory
		String fileName = purchasePaymentPDF.getOriginalFilename();
		Path filePath = Paths.get(printOutsDirectory.toString(), fileName);

		try {
			purchasePaymentPDF.transferTo(filePath.toFile());

			// Update the mail-attachments means add the newly created file-path.
			JSONArray mailAttachments = (JSONArray) emailData.get("mailAttachmentFilePaths");
			mailAttachments.put(filePath.toString());
			emailData.put("mailAttachmentFilePaths", mailAttachments);
			System.out.println("Purchase Payment PDF File Saved: " + filePath.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

//		return iPtPurchaseDetailsService.FnSendEmail(company_id, supplier_purchase_payment_master_transaction_id, emailData);
		// Now send for email sending.
		Map<String, Object> emailSendingResponse = iPtSupplierPurchasePaymentMasterService.FnSendEmail(company_id,
				supplier_purchase_payment_master_transaction_id, emailData);
		return emailSendingResponse;
	}


}
