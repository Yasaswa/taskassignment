package com.erp.Transporter_Contacts.Controller;

import com.erp.Transporter.Model.CTransporterContactsViewModel;
import com.erp.Transporter_Contacts.Service.ITransporterContactsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/transportercontacts", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CTransporterContactsController {

	@Autowired
	ITransporterContactsService iTransporterContactsService;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestParam("TContactGridData") JSONObject jsonObject) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iTransporterContactsService.FnAddUpdateRecord(jsonObject);
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{transporter_contact_id}")
	public Object FnDeleteRecord(@PathVariable int transporter_contact_id) {
		return iTransporterContactsService.FnDeleteRecord(transporter_contact_id);

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cTransporterContactsViewModel = null;

		try {
			cTransporterContactsViewModel = iTransporterContactsService.FnShowAllRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterContactsViewModel;
	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cTransporterContactsViewModel = null;

		try {
			cTransporterContactsViewModel = iTransporterContactsService.FnShowAllActiveRecords(pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterContactsViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{transporter_contact_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int transporter_contact_id) throws SQLException {
		Object cTransporterContactsViewModel = null;
		try {
			cTransporterContactsViewModel = iTransporterContactsService.FnShowParticularRecord(company_id,
					company_branch_id, transporter_contact_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterContactsViewModel;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{transporter_id}")
	public List<CTransporterContactsViewModel> FnShowParticularRecordForUpdate(@PathVariable int transporter_id)
			throws SQLException {
		List<CTransporterContactsViewModel> cTransporterContactsViewModel = null;
		try {
			cTransporterContactsViewModel = iTransporterContactsService.FnShowParticularRecordForUpdate(transporter_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cTransporterContactsViewModel;

	}

}
