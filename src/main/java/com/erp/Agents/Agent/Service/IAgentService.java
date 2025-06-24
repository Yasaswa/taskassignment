package com.erp.Agents.Agent.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IAgentService {


	Object FnDeleteRecord(int agent_id, int company_id, String deleted_by);

	JSONObject FnShowAllRecords(Pageable pageable);

	JSONObject FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int agent_id, int company_id);

	Map<String, Object> FnShowParticularRecord(int company_id, int agent_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject cAgentModel);

	Object FnShowAllReportRecords(Pageable pageable);

}
