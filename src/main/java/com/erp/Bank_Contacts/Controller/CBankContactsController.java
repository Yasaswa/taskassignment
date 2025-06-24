package com.erp.Bank_Contacts.Controller;

import com.erp.Bank_Contacts.Model.CBankContactsViewModel;
import com.erp.Bank_Contacts.Service.IBankContactsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/bankcontacts", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CBankContactsController {

	@Autowired
	IBankContactsService iBankContactsService;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestParam("BContactGridData") JSONObject jsonObject) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iBankContactsService.FnAddUpdateRecord(jsonObject);

		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{bank_contact_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int bank_contact_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iBankContactsService.FnDeleteRecord(bank_contact_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cBankContactsViewModel = null;

		try {
			cBankContactsViewModel = iBankContactsService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cBankContactsViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cBankContactsViewModel = null;

		try {
			cBankContactsViewModel = iBankContactsService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cBankContactsViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{bank_contact_id}")
	Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int bank_contact_id) throws SQLException {
		Object cBankContactsViewModel = null;
		try {
			cBankContactsViewModel = iBankContactsService.FnShowParticularRecord(company_id, bank_contact_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cBankContactsViewModel;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{bank_id}/{company_id}")
	public List<CBankContactsViewModel> FnShowParticularRecordForUpdate(@PathVariable int bank_id,
	                                                                    @PathVariable int company_id) throws SQLException {
		List<CBankContactsViewModel> cBankContactsViewModel = null;
		cBankContactsViewModel = iBankContactsService.FnShowParticularRecordForUpdate(bank_id, company_id);

		return cBankContactsViewModel;

	}

}
