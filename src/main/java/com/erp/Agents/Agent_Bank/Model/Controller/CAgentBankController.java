package com.erp.Agents.Agent_Bank.Model.Controller;

import com.erp.Agents.Agent_Bank.Service.IAgentBankService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/agentbank")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CAgentBankController {

	@Autowired
	IAgentBankService iAgentBankService;

	@PostMapping("/FnAddUpdateRecord")
	public Object FnAddUpdateRecord(@RequestParam("agentBankJson") JSONObject jsonObject) throws SQLException {
		JSONObject responce = new JSONObject();
		responce = iAgentBankService.FnAddUpdateRecord(jsonObject);

		return responce.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{agent_bank_id}")
	public Object FnDeleteRecord(@PathVariable int agent_bank_id) {
		return iAgentBankService.FnDeleteRecord(agent_bank_id);

	}

	// Added by Mohit :- this api use when we need deleted by functionality

//	@DeleteMapping("/FnDeleteRecord/{agent_bank_id}/{company_id}/{deleted_by}")
//	public Object FnDeleteRecord(@PathVariable int agent_bank_id, @PathVariable int company_id, @PathVariable String deleted_by) {
//		return iAgentBankService.FnDeleteRecord(agent_bank_id, company_id, deleted_by);
//
//	}

	@GetMapping("/FnShowAllActiveRecords")
	Object FnShowAllActiveRecords(Pageable pageable) throws SQLException {
		Object cAgentBankViewModel = null;
		cAgentBankViewModel = iAgentBankService.FnShowAllActiveRecords(pageable);

		return cAgentBankViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecord/{company_id}/{company_branch_id}/{agent_bank_id}")
	public Object FnShowParticularRecord(@PathVariable int company_id, @PathVariable int company_branch_id,
	                                     @PathVariable int agent_bank_id) throws SQLException {
		JSONObject resp = new JSONObject();

		resp = iAgentBankService.FnShowParticularRecord(company_id, company_branch_id, agent_bank_id);

		return resp.toString();

	}

	@GetMapping("/FnShowAllRecords")
	Object FnShowAllRecords(Pageable pageable) throws SQLException {
		Object cAgentBankViewModel = null;
		cAgentBankViewModel = iAgentBankService.FnShowAllRecords(pageable);

		return cAgentBankViewModel.toString();
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{agent_id}/{company_id}")
	public HashMap<String, Object> FnShowParticularRecordForUpdate(@PathVariable int agent_id, @PathVariable int company_id) throws SQLException {
		HashMap<String, Object> resp = new HashMap<>();
		resp = iAgentBankService.FnShowParticularRecordForUpdate(agent_id, company_id);

		return resp;

	}
}
