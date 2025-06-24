package com.erp.Agents.Agent_Contacts.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IAgentContactsService {


	Object FnDeleteRecord(int agent_contact_id);

//	Object FnDeleteRecord(int agent_contact_id, int company_id, String deleted_by);

	Map<String, Object> FnShowAllActiveRecords(int agent_id, int company_id, Pageable pageable);

	Object FnShowAllRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int agent_contact_id);

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int agent_contact_id);


}
