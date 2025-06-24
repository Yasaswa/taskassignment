package com.erp.Payment_Terms.Controller;

import com.erp.Payment_Terms.Model.CPaymentTermsModel;
import com.erp.Payment_Terms.Service.IPaymentTermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/paymentterms", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CPaymentTermsController {

	@Autowired
	IPaymentTermsService iPaymentTermsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CPaymentTermsModel cPaymentTermsModel) {
		Map<String, Object> resp = iPaymentTermsService.FnAddUpdateRecord(cPaymentTermsModel);
		return resp;
	}

	@DeleteMapping("/FnDeleteRecord/{payment_terms_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int payment_terms_id, @PathVariable String deleted_by) {
		return iPaymentTermsService.FnDeleteRecord(payment_terms_id, deleted_by);
	}

	@GetMapping("/FnShowAllRecords")
	public Map<String, Object> FnShowAllRecords(Pageable pageable) throws SQLException {
		Map<String, Object> cPaymentTermsViewModel = iPaymentTermsService.FnShowAllRecords(pageable);
		return cPaymentTermsViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	public Map<String, Object> FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Map<String, Object> cPaymentTermsViewModel = iPaymentTermsService.FnShowAllActiveRecords(pageable);
		return cPaymentTermsViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{payment_terms_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int payment_terms_id) throws SQLException {
		Map<String, Object> resp = iPaymentTermsService.FnShowParticularRecordForUpdate(payment_terms_id);
		return resp;

	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{payment_terms_id}")
	public Map<String, Object> FnShowParticularRecord(@PathVariable int company_id,
	                                                  @PathVariable int payment_terms_id) {
		Map<String, Object> resp = iPaymentTermsService.FnShowParticularRecord(company_id, payment_terms_id);
		return resp;

	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable) throws SQLException {
		Map<String, Object> cPaymentTermsRptModel = iPaymentTermsService.FnShowAllReportRecords(pageable);
		return cPaymentTermsRptModel;

	}

}
