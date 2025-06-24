package com.erp.Agents.Agent_Contacts.Service;

import com.erp.Agents.Agent_Contacts.Model.CAgentContactsModel;
import com.erp.Agents.Agent_Contacts.Model.CAgentContactsViewModel;
import com.erp.Agents.Agent_Contacts.Repository.IAgentContactsRepository;
import com.erp.Agents.Agent_Contacts.Repository.IAgentContactsViewRepository;
import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CAgentContactsServiceImpl implements IAgentContactsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAgentContactsRepository iAgentContactsRepository;

	@Autowired
	IAgentContactsViewRepository iAgentContactsViewRepository;

	@Override
	public Object FnDeleteRecord(int agent_contact_id) {
		// TODO Auto-generated method stub
		return iAgentContactsRepository.FnDeleteRecord(agent_contact_id);
	}

//	@Override
//	public Object FnDeleteRecord(int agent_contact_id, int company_id, String deleted_by) {
//		// TODO Auto-generated method stub
//		return iAgentContactsRepository.FnDeleteRecord(agent_contact_id, company_id, deleted_by);
//	}

	@Override
	public JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int agent_contact_id) {

		JSONObject resp = new JSONObject();
		try {

			CAgentContactsViewModel json = iAgentContactsViewRepository.FnShowParticularRecord(company_id,
					company_branch_id, agent_contact_id);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", e.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public JSONObject FnShowParticularRecordForUpdate(int agent_contact_id) {

		JSONObject resp = new JSONObject();
		try {

			CAgentContactsViewModel json = iAgentContactsViewRepository
					.FnShowParticularRecordForUpdate(agent_contact_id);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

			return resp;

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}

	@Override
	public Map<String, Object> FnShowAllActiveRecords(int agent_id, int company_id, Pageable pageable) {
		Map<String, Object> resp = new HashMap<>();
		try {
			Page<CAgentContactsViewModel> data = iAgentContactsViewRepository.FnShowAllActiveRecords(agent_id,
					company_id, pageable);
			resp.put("data", data);
			resp.put("success", "1");
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return resp;

	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CAgentContactsViewModel> data = iAgentContactsViewRepository.FnShowAllRecords(pageable);
			String json = objectMapper.writeValueAsString(data);
			resp.put("data", json);
			resp.put("success", "1");
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			resp.put("success", "0");
			resp.put("error", "");
			e.printStackTrace();
		}

		return resp;

	}

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		int company_id = jsonObject.getInt("Company_id");
		try {
			JSONObject aContactJsons = (JSONObject) jsonObject.get("AContactJsons");
			JSONObject aContactIds = (JSONObject) jsonObject.get("AContactIds");
			int agent_id = aContactIds.getInt("agent_id");

			Object obj = iAgentContactsRepository.updateAgentContactActiveStatus(agent_id, company_id);

			for (int jsonData = 1; jsonData <= aContactJsons.length(); jsonData++) {
				JSONObject data = (JSONObject) aContactJsons.get(Integer.toString(jsonData));
				CAgentContactsModel cAgentContactsModel = new CAgentContactsModel();
				cAgentContactsModel.setCompany_id(Integer.parseInt(data.getString("company_id")));
				cAgentContactsModel.setCompany_branch_id(Integer.parseInt(data.getString("company_branch_id")));
				cAgentContactsModel.setAgent_id(data.getInt("agent_id"));
				cAgentContactsModel.setAgent_contact_person(data.getString("agent_contact_person"));
				cAgentContactsModel.setAgent_designation(data.getString("agent_designation"));
				cAgentContactsModel.setAgent_contact_no(data.getString("agent_contact_no"));
				cAgentContactsModel.setAgent_email_id(data.getString("agent_email_id"));
				cAgentContactsModel.setAgent_alternate_contact(data.getString("agent_alternate_contact"));
				cAgentContactsModel.setAgent_alternate_EmailId(data.getString("agent_alternate_EmailId"));
				cAgentContactsModel = iAgentContactsRepository.save(cAgentContactsModel);
			}

			resp.put("success", "1");
			resp.put("message", "Records added succesfully!...");
			resp.put("error", "");
			return resp;
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/agentcontacts/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/agentcontacts/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("error", e.getMessage());
		}

		return resp;
	}

}
