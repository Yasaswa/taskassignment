package com.erp.Agents.Agent_Bank.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;

public interface IAgentBankService {

	Object FnDeleteRecord(int agent_bank_id);

//	Object FnDeleteRecord(int agent_bank_id, int company_id, String deleted_by);

	Object FnShowAllActiveRecords(Pageable pageable);

	HashMap<String, Object> FnShowParticularRecordForUpdate(int agent_id, int company_id);

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int agent_bank_id);

	Object FnShowAllRecords(Pageable pageable);

}
