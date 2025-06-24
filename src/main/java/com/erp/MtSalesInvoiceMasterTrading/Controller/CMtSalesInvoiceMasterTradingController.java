package com.erp.MtSalesInvoiceMasterTrading.Controller;

import com.erp.MtSalesInvoiceMasterTrading.Service.IMtSalesInvoiceMasterTradingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/MtSalesInvoiceMasterTrading")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CMtSalesInvoiceMasterTradingController {

	@Autowired
	IMtSalesInvoiceMasterTradingService iMtSalesInvoiceMasterTradingService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("salesInvoiceMasterData") JSONObject jsonObject) {
		Map<String, Object> responce = iMtSalesInvoiceMasterTradingService.FnAddUpdateRecord(jsonObject);
		return responce;
	}

	@PostMapping("/FnCancelSalesInvoice")
	public Map<String, Object> FnCancelSalesInvoice(@RequestParam("salesInvoiceData") JSONObject jsonObject) {
		return iMtSalesInvoiceMasterTradingService.FnCancelSalesInvoice(jsonObject);
	}

	@DeleteMapping("/FnDeleteRecord/{sales_invoice_version}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@RequestParam("sales_invoice_no") String sales_invoice_no, @PathVariable int sales_invoice_version, @PathVariable int company_id) {
		return iMtSalesInvoiceMasterTradingService.FnDeleteRecord(sales_invoice_no, sales_invoice_version, company_id);

	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iMtSalesInvoiceMasterTradingService.FnShowAllReportRecords(pageable, reportType);

	}

	@PostMapping("/FnShowAllDetailsAndMastermodelRecords")
	public Map<String, Object> FnShowAllDetailsandMastermodelRecords(@RequestParam("invoiceIdentity") JSONObject jsonObject)
			throws SQLException {
		return iMtSalesInvoiceMasterTradingService.FnShowAllDetailsandMastermodelRecords(jsonObject);
	}

	@PostMapping("/FnShowDispatchChallanDetails")
	public Map<String, Object> FnShowDispatchChallanDetails(@RequestParam("dispatchChallanData") JSONObject dispatchChallanData) {
		return iMtSalesInvoiceMasterTradingService.FnShowDispatchChallanDetails(dispatchChallanData);
	}


	@GetMapping("/FnGetSaleInvoiceDataByStatus/{sales_invoice_status}/{company_id}")
	public Map<String, Object> FnGetSaleInvoiceDataByStatus(@PathVariable String sales_invoice_status, @PathVariable int company_id) {
		return iMtSalesInvoiceMasterTradingService.FnGetSaleInvoiceDataByStatus(sales_invoice_status, company_id);
	}

	@GetMapping("/FnGenerateTokenForEInvoice/{company_id}")
	public Map<String, Object> FnGenerateTokenForEInvoice(@PathVariable int company_id) {
		return iMtSalesInvoiceMasterTradingService.FnGenerateTokenForEInvoice(company_id);
	}

	@GetMapping("/FnGetSalesInvoiceDetails/{company_id}")
	public Map<String, Object> FnGetSalesInvoiceDetails(@RequestParam String sales_invoice_no, @PathVariable int company_id) {
		return iMtSalesInvoiceMasterTradingService.FnGetSalesInvoiceDetails(sales_invoice_no, company_id);
	}

	@PostMapping("/FnGenerateEInvoice")
	public Map<String, Object> FnGenerateEInvoice(@RequestParam("E-InvoiceJson") JSONObject jsonObject) {
		return iMtSalesInvoiceMasterTradingService.FnGenerateEInvoice(jsonObject);
	}

}
