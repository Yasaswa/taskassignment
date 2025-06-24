package com.erp.Agents.Agent_Bank.Service;

import com.erp.Agents.Agent_Bank.Model.CAgentBankModel;
import com.erp.Agents.Agent_Bank.Model.CAgentBankViewModel;
import com.erp.Agents.Agent_Bank.Repository.IAgentBankRepository;
import com.erp.Agents.Agent_Bank.Repository.IAgentBankViewRepository;
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
import java.util.List;

@Service
public class CAgentBankServiceImpl implements IAgentBankService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAgentBankRepository iAgentBankRepository;

	@Autowired
	IAgentBankViewRepository iAgentBankViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		CAgentBankModel cAgentBankModel = new CAgentBankModel();
		JSONObject resp = new JSONObject();
		int company_branch_id = 0;
		int company_id = 0;
		int agent_id = 0;
		try {
			JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
			JSONObject bankIds = (JSONObject) jsonObject.get("bankIds");

			if (!compAndBrnchId.keySet().isEmpty()) {
				for (String currentKey : compAndBrnchId.keySet()) {
					Object key = compAndBrnchId.get(currentKey);
					String value = key.toString();
					if (currentKey.equals("company_branch_id")) {
						company_branch_id = Integer.parseInt(value);
					} else if (currentKey.equals("company_id")) {
						company_id = Integer.parseInt(value);
					} else if (currentKey.equals("agent_id")) {
						agent_id = Integer.parseInt(value);

					}
				}
			}

//			Delete previous records
			Object obj = iAgentBankRepository.updateAgentBankActiveStatus(company_id,
					agent_id);


			if (!bankIds.keySet().isEmpty()) {
				for (String currentKey : bankIds.keySet()) {
					CAgentBankModel bankModel = new CAgentBankModel();
					Object key = bankIds.get(currentKey);
					String bank_id = key.toString();

					bankModel.setCompany_id(company_id);
					bankModel.setCompany_branch_id(company_branch_id);
					bankModel.setAgent_id(agent_id);
					bankModel.setBank_id(Integer.parseInt(bank_id));

					cAgentBankModel = iAgentBankRepository.save(bankModel);
				}

			}

			resp.put("success", "1");
			resp.put("message", "Records added successfully !...");
			resp.put("error", "");

			return resp;


		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/agentbank/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/agentbank/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;

		}

		return resp;

	}


	@Override
	public Object FnDeleteRecord(int agent_bank_id) {
		return iAgentBankRepository.FnDeleteRecord(agent_bank_id);
	}

//	@Override
//	public Object FnDeleteRecord(int agent_bank_id, int company_id, String deleted_by) {
//		return iAgentBankRepository.FnDeleteRecord(agent_bank_id, company_id, deleted_by);
//	}

	@Override
	public HashMap<String, Object> FnShowParticularRecordForUpdate(int agent_id, int company_id) {
		HashMap<String, Object> resp = new HashMap<>();
		try {

			List<CAgentBankViewModel> json = iAgentBankViewRepository.FnShowParticularRecordForUpdate(agent_id, company_id);
			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

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
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CAgentBankViewModel> data = iAgentBankViewRepository.FnShowAllRecords(pageable);
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
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CAgentBankViewModel> data = iAgentBankViewRepository.FnShowAllActiveRecords(pageable);
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
	public JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int agent_bank_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CAgentBankViewModel json = iAgentBankViewRepository.FnShowParticularRecord(company_id, company_branch_id, agent_bank_id);
			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
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


}
