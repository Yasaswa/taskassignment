package com.erp.Transporter.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;
import com.erp.AmModulesFormsUserAccess.Model.CUserViewModel;
import com.erp.AmModulesFormsUserAccess.Repository.IAmModulesFormsUserAccessViewRepository;
import com.erp.AmModulesFormsUserAccess.Repository.IUserViewModelRepository;
import com.erp.Common.PasswordsSecurity.PasswordManager;
import com.erp.Taxtype.Model.CTaxtypeModel;
import com.erp.Transporter.Model.CTransporterBanksModel;
import com.erp.Transporter.Model.CTransporterContactsModel;
import com.erp.Transporter.Model.CTransporterModel;
import com.erp.Transporter.Model.CTransporterViewModel;
import com.erp.Transporter.Repository.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CTransporterServiceImpl implements ITransporterService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ITransporterRepository iTransporterRepository;

	@Autowired
	ITransporterViewRepository iTransporterViewRepository;

	@Autowired
	ITransporterRptRepository iTransporterRptRepository;

	@Autowired
	ITransporterBanksRepository iTransporterBanksRepository;

	@Autowired
	ITransporterContactsRepository iTransporterContactsRepository;

	@Autowired
	IUserViewModelRepository iUserViewModelRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	JdbcTemplate executeQuery;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int transporter_id = commonIdsObj.getInt("transporter_id");

		JSONObject Transporterjson = jsonObject.getJSONObject("TransporterData");
		JSONArray bankArray = (JSONArray) jsonObject.get("BankData");
		JSONArray contactArray = (JSONArray) jsonObject.get("ContactData");

		try {
			//Transporter  
			CTransporterModel transporterModel = objectMapper.readValue(Transporterjson.toString(),
					CTransporterModel.class);

			// Check User Name Exist or Not
//			// Commented Because UserName is unique for all companies.(as per prashant sir told on 08-07-2024)
//			CUserViewModel checkUserName = iUserViewModelRepository.getUserName(transporterModel.getUsername(), company_id, transporterModel.getTransporter_vendor_code());
			CUserViewModel checkUserName = iUserViewModelRepository.getUserName(transporterModel.getUsername(), transporterModel.getTransporter_vendor_code());

			if (checkUserName != null) {
				responce.put("success", 0);
				responce.put("error", "User Name is already exist!");
				return responce;
			}


			if (transporterModel.getTransporter_id() == 0) {
//				If New supplier then generate the random password and encrypt it and save it in db.
				String randomPassword = RandomStringUtils.random(15, true, true);
//				System.out.println("Transporter: \t" + "Random Password: " + randomPassword + "\tEncrypted Password: " + PasswordManager.encrypt(randomPassword));
				transporterModel.setPassword(passwordEncoder.encode(randomPassword));

				//Check similar Transporter Name,Transporter short name is exist or not
				CTransporterModel resultsTransporterName = null;
				if (transporterModel.getTransporter_short_name() == null || transporterModel.getTransporter_short_name().isEmpty()) {
					resultsTransporterName = iTransporterRepository.getCheck(transporterModel.getTransporter_name(),
							null,company_id);
				} else {
					resultsTransporterName = iTransporterRepository.getCheck(transporterModel.getTransporter_name(),
							transporterModel.getTransporter_short_name(), company_id);
				}

				if (resultsTransporterName != null) {
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("message", " Transporter Name Or Short Name is already exist!");
					return responce;
				}
				
			} else {
				// Means supplier is already exist then get the password and encrypt it and store it in db.
				String encryptedPassword = passwordEncoder.encode(transporterModel.getPassword());
//				System.out.println("Transporter: \t" + "Original Password: " + transporterModel.getPassword() + "\tEncrypted Password: " + encryptedPassword);
				transporterModel.setPassword(encryptedPassword);

			}
			CTransporterModel responseTransporterModel = iTransporterRepository.save(transporterModel);

			//Transporter Bank
			iTransporterBanksRepository.updateTransporterBankActiveStatus(transporter_id, company_id);

			if (!bankArray.isEmpty()) {
				List<CTransporterBanksModel> cTransporterBanksModel = objectMapper
						.readValue(bankArray.toString(), new TypeReference<List<CTransporterBanksModel>>() {
						});

				cTransporterBanksModel.forEach(items -> {
					items.setTransporter_id(responseTransporterModel.getTransporter_id());
					items.setTransporter_bank_id(0);
				});

				iTransporterBanksRepository.saveAll(cTransporterBanksModel);

			}

			//Transporter Contact
			iTransporterContactsRepository.updateTransporterContactActiveStatus(transporter_id, company_id);

			if (!contactArray.isEmpty()) {
				List<CTransporterContactsModel> cTransporterContactsModel = objectMapper
						.readValue(contactArray.toString(), new TypeReference<List<CTransporterContactsModel>>() {
						});

				cTransporterContactsModel.forEach(items -> {
					items.setTransporter_id(responseTransporterModel.getTransporter_id());
					items.setTransporter_contact_id(0);
				});

				iTransporterContactsRepository.saveAll(cTransporterContactsModel);

			}
			responce.put("success", 1);
			responce.put("data", responseTransporterModel);
			responce.put("error", "");
//			transporter_id
			responce.put("message", transporter_id == 0 ? "Record added Successfully..!" : "Record updated Successfully..!");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				e.printStackTrace();
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/transporter/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/transporter/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}


	@Override
	public Map<String, Object> FnShowAllRecords(int transporter_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			
			//fetch Transporter Details
			CTransporterModel transporterRecords = iTransporterRepository
					.FnShowParticularTransporterRecord(transporter_id, company_id);

			//fetch Transporter Banks Details
			List<CTransporterBanksModel> transporterBankRecords = iTransporterBanksRepository
					.FnShowParticularTransporterBankRecord(transporter_id, company_id);

			//fetch Transporter Contacts Details
			List<CTransporterContactsModel> transporterContactRecords = iTransporterContactsRepository
					.FnShowParticularTransporterContactRecord(transporter_id, company_id);


			responce.put("transporterRecords", transporterRecords);
			responce.put("transporterBankRecords", transporterBankRecords);
			responce.put("transporterContactRecords", transporterContactRecords);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Object FnDeleteRecord(int transporter_id, String deleted_by) {
		Map<String, Object> resp = new HashMap<>();
		try {
			
			//Delete Transporter Details
			iTransporterRepository.FnDeleteRecord(transporter_id, deleted_by);
			
			//Delete Transporter Banks Details
			iTransporterBanksRepository.deletTransporterBankRecords(transporter_id, deleted_by);
			
			//Delete Transporter Contacts Details
			iTransporterContactsRepository.deletTransporterContactRecords(transporter_id, deleted_by);
			
			resp.put("success",1);
			resp.put("data", "");
		} catch (Exception e) {
			resp.put("success", 0);
			resp.put("error", "");
			e.printStackTrace();
		}
		
		return resp;
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CTransporterViewModel> data = iTransporterViewRepository.FnShowAllRecords(pageable);

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
	public Object FnShowAllActiveRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CTransporterViewModel> data = iTransporterViewRepository.FnShowAllActiveRecords(pageable);

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
	public Object FnShowParticularRecordForUpdate(int transporter_id) {
		JSONObject resp = new JSONObject();
		try {

			CTransporterModel json = iTransporterRepository.FnShowParticularRecordForUpdate(transporter_id);

//			String decryptedPassword = PasswordManager.decrypt(json.getPassword());
//			json.setPassword(decryptedPassword);

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
	public JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int transporter_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CTransporterViewModel cTransporterViewModel = iTransporterViewRepository.FnShowParticularRecord(company_id,
					company_branch_id, transporter_id);
			String json = objectMapper.writeValueAsString(cTransporterViewModel);
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
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iTransporterRptRepository.FnShowAllReportRecords(pageable);
			System.out.println(data);
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



}
