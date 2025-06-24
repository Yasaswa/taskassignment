package com.erp.Customer_Contacts.Controller;

import com.erp.Customer_Contacts.Service.ICustomerContactsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/customercontacts", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CCustomerContactsController {

	@Autowired
	ICustomerContactsService iCustomerContactsService;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestParam("CContactGridData") JSONObject jsonObject) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iCustomerContactsService.FnAddUpdateRecord(jsonObject);

		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{customer_contact_id}")
	public Object FnDeleteRecord(@PathVariable int customer_contact_id) {
		return iCustomerContactsService.FnDeleteRecord(customer_contact_id);
	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cCustomerContactsViewModel = null;

		try {
			cCustomerContactsViewModel = iCustomerContactsService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCustomerContactsViewModel.toString();
	}

	@GetMapping("/FnShowAllActiveRecords")
	public Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cCustomerContactsViewModel = null;
		try {
			cCustomerContactsViewModel = iCustomerContactsService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCustomerContactsViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{customer_contact_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int customer_contact_id) throws SQLException {
		Object cCustomerContactsViewModel = null;
		try {
			cCustomerContactsViewModel = iCustomerContactsService.FnShowParticularRecordForUpdate(customer_contact_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cCustomerContactsViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecords/{customer_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecords(@PathVariable int customer_id, @PathVariable int company_id) throws SQLException {
		Map<String, Object> cCustomerContactsViewModel = iCustomerContactsService.FnShowParticularRecord(customer_id, company_id);
		return cCustomerContactsViewModel;
	}

}
