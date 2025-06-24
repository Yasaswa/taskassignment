package com.erp.Agents.Agent.Service;

import com.erp.Agents.Agent.Model.CAgentModel;
import com.erp.Agents.Agent.Model.CAgentViewModel;
import com.erp.Agents.Agent.Repository.IAgentRepository;
import com.erp.Agents.Agent.Repository.IAgentViewRepository;
import com.erp.Agents.Agent_Bank.Model.CAgentBankModel;
import com.erp.Agents.Agent_Bank.Repository.IAgentBankRepository;
import com.erp.Agents.Agent_Contacts.Model.CAgentContactsModel;
import com.erp.Agents.Agent_Contacts.Repository.IAgentContactsRepository;
import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsUserAccess.Model.CUserViewModel;
import com.erp.AmModulesFormsUserAccess.Repository.IUserViewModelRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CAgentServiceImpl implements IAgentService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAgentRepository iAgentRepository;

	@Autowired
	IAgentViewRepository iAgentViewRepository;

	@Autowired
	IAgentBankRepository iAgentBankRepository;

	@Autowired
	IAgentContactsRepository iAgentContactsRepository;

	@Autowired
	IUserViewModelRepository iUserViewModelRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> resp = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int temp_agent_id = commonIdsObj.getInt("agent_id");
		AtomicInteger agent_id = new AtomicInteger(temp_agent_id);

		JSONObject agentJson = jsonObject.getJSONObject("TransAgent");
		JSONArray bankArray = (JSONArray) jsonObject.get("TransAgentBankData");
		JSONArray contactArray = (JSONArray) jsonObject.get("TransAgentContactData");

		try {
			CAgentModel agentModel = objectMapper.readValue(agentJson.toString(),
					CAgentModel.class);

			// Check User Name Exist or Not	

//			// Commented Because UserName is unique for all companies.(as per prashant sir told on 08-07-2024)
//			CUserViewModel checkUserName = iUserViewModelRepository.getUserName(agentModel.getUsername(), company_id, agentModel.getAgent_vendor_code());
			CUserViewModel checkUserName = iUserViewModelRepository.getUserName(agentModel.getUsername(), agentModel.getAgent_vendor_code());

			if (checkUserName != null) {
				resp.put("success", 0);
				resp.put("error", "User Name is already exist!");
				return resp;
			}


			if (agentModel.getAgent_id() == 0) {
//				If New agent then generate the random password and encrypt it and save it in db
				CAgentModel resultsAgentName = null;
				if (agentModel.getAgent_short_name() == null || agentModel.getAgent_short_name().isEmpty()) {
					resultsAgentName = iAgentRepository.getCheck(agentModel.getAgent_short_name(),
							null, agentModel.getCompany_id());
				} else {
					resultsAgentName = iAgentRepository.getCheck(agentModel.getAgent_name(),
							agentModel.getAgent_short_name(), agentModel.getCompany_id());
				}

				if (resultsAgentName != null) {
					resp.put("success", "0");
					resp.put("data", "");
					resp.put("error", "Agent Name or Short-Name is already exist!");
					return resp;
				}
				String randomPassword = RandomStringUtils.random(15, true, true);
//				System.out.println("Agent: \t" + "Random Password: " + randomPassword + "\tEncrypted Password: " + PasswordManager.encrypt(randomPassword));
				agentModel.setPassword(passwordEncoder.encode(randomPassword));


			} else {
				// Means supplier is already exist then get the password and encrypt it and store it in db.
				String encryptedPassword = passwordEncoder.encode(agentModel.getPassword());
//				System.out.println("Agent: \t" + "Original Password: " + agentModel.getPassword() + "\tEncrypted Password: " + encryptedPassword);
				agentModel.setPassword(encryptedPassword);

			}
			CAgentModel agentMasterResponse = iAgentRepository.save(agentModel);
			agent_id.set(agentMasterResponse.getAgent_id());

			iAgentBankRepository.updateAgentBankActiveStatus(company_id, agent_id.get());

			if (!bankArray.isEmpty()) {
				List<CAgentBankModel> cAgentBanksModel = objectMapper
						.readValue(bankArray.toString(), new TypeReference<List<CAgentBankModel>>() {
						});

				cAgentBanksModel.forEach(items -> {
					items.setAgent_id(agent_id.get());
				});

				iAgentBankRepository.saveAll(cAgentBanksModel);
			}

			//Transporter Contact
			iAgentContactsRepository.updateAgentContactActiveStatus(agent_id.get(), company_id);

			if (!contactArray.isEmpty()) {
				List<CAgentContactsModel> cAgentContactsModel = objectMapper
						.readValue(contactArray.toString(), new TypeReference<List<CAgentContactsModel>>() {
						});

				cAgentContactsModel.forEach(items -> {
					items.setAgent_id(agent_id.get());
				});

				iAgentContactsRepository.saveAll(cAgentContactsModel);
			}

			resp.put("success", "1");
			resp.put("data", agentMasterResponse);
			resp.put("error", "");
			resp.put("message", temp_agent_id == 0 ? "Record added Successfully..!" : "Record updated Successfully..!");


		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/agent/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/agent/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int agent_id, int company_id, String deleted_by) {
		iAgentBankRepository.FnDeleteAgentBanksRecord(deleted_by, agent_id, company_id);
		iAgentContactsRepository.FnDeleteAgentContactsRecord(deleted_by, agent_id, company_id);
		return iAgentRepository.FnDeleteRecord(agent_id, company_id, deleted_by);
	}

	@Override
	public JSONObject FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CAgentViewModel> data = iAgentViewRepository.FnShowAllRecords(pageable);
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
	public JSONObject FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CAgentViewModel> data = iAgentViewRepository.FnShowAllActiveRecords(pageable);
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
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iAgentViewRepository.FnShowAllReportRecords(pageable);
			resp.put("success", "1");
			resp.put("error", "");
			return new Object[]{data, resp};

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

		return new Object[]{"", resp};
	}

	@Override
	public JSONObject FnShowParticularRecordForUpdate(int agent_id, int company_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CAgentViewModel json = iAgentViewRepository.FnShowParticularRecordForUpdate(agent_id, company_id);

//			String decryptedPassword = PasswordManager.decrypt(json.getAgent_password());
//			json.setAgent_password(decryptedPassword);

			String json1 = objectMapper.writeValueAsString(json);

			resp.put("success", "1");
			resp.put("data", json1);
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
	public Map<String, Object> FnShowParticularRecord(int company_id, int agent_id) {
		Map<String, Object> resp = new HashMap<>();
		try {
			CAgentModel agentDetails = iAgentRepository.FnShowParticularRecord(company_id, agent_id);
//			String decryptedPassword = PasswordManager.decrypt(agentDetails.getPassword());
//			agentDetails.setPassword(decryptedPassword);
			List<CAgentBankModel> agentBankDetails = iAgentBankRepository.FnShowParticularRecord(company_id, agent_id);
			List<CAgentContactsModel> agentContactDetails = iAgentContactsRepository.FnShowParticularRecord(company_id, agent_id);

			resp.put("success", 1);
			resp.put("agentDetails", agentDetails);
			resp.put("agentBankDetails", agentBankDetails);
			resp.put("agentContactDetails", agentContactDetails);
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				resp.put("success", 0);
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resp;
	}


}
