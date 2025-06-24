package com.erp.Agents.Agent_Contacts.Controller;

import com.erp.Agents.Agent_Contacts.Service.IAgentContactsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/agentcontacts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAgentContactsController {

	@Autowired
	IAgentContactsService iAgentContactsService;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestParam("AContactGridData") JSONObject jsonObject) throws SQLException {
		JSONObject resp = new JSONObject();
		resp = iAgentContactsService.FnAddUpdateRecord(jsonObject);

		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{agent_contact_id}")
	public Object FnDeleteRecord(@PathVariable int agent_contact_id) {
		return iAgentContactsService.FnDeleteRecord(agent_contact_id);
	}

// Added by Mohit :- this api use when we need deleted by functionality	

//	@DeleteMapping("/FnDeleteRecord/{agent_contact_id}/{company_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int agent_contact_id, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iAgentContactsService.FnDeleteRecord(agent_contact_id, company_id, deleted_by);
//	}

	@GetMapping("/FnShowAllActiveRecords/{agent_id}/{company_id}")
	public Object FnShowAllActiveRecords(@PathVariable int agent_id, @PathVariable int company_id, Pageable pageable) throws SQLException {
		Map<String, Object> cAgentContactsViewModel = new HashMap<>();
		cAgentContactsViewModel = iAgentContactsService.FnShowAllActiveRecords(agent_id, company_id, pageable);

		return cAgentContactsViewModel;
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{agent_contact_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int agent_contact_id) throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iAgentContactsService.FnShowParticularRecord(company_id, company_branch_id, agent_contact_id);

		return resp.toString();

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cAgentContactsViewModel = null;
		cAgentContactsViewModel = iAgentContactsService.FnShowAllRecords(pageable);

		return cAgentContactsViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{agent_contact_id}")
	public Object FnShowParticularRecordForUpdate(@PathVariable int agent_contact_id) throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iAgentContactsService.FnShowParticularRecordForUpdate(agent_contact_id);

		return resp.toString();

	}

}
