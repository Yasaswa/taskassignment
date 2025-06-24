package com.erp.Customer_Bank.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Customer_Bank.Model.CCustomerBankModel;
import com.erp.Customer_Bank.Model.CCustomerBankViewModel;
import com.erp.Customer_Bank.Repository.ICustomerBankRepository;
import com.erp.Customer_Bank.Repository.ICustomerBankViewRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CCustomerBankServiceImpl implements ICustomerBankService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICustomerBankRepository iCustomerBankRepository;

	@Autowired
	ICustomerBankViewRepository iCustomerBankViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject resp = new JSONObject();
		int company_id = 0;
		int customer_id = 0;
		int cust_branch_id = 0;
		AtomicInteger atomic_cust_branch_id = new AtomicInteger();
		AtomicInteger atomic_cust_id = new AtomicInteger();
		try {
			JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");
			JSONArray bankArray = (JSONArray) jsonObject.get("bankIds");
			if (!compAndBrnchId.keySet().isEmpty()) {
				for (String currentKey : compAndBrnchId.keySet()) {
					Object key = compAndBrnchId.get(currentKey);
					String value = key.toString();
					if (currentKey.equals("company_id")) {
						company_id = Integer.parseInt(value);
					} else if (currentKey.equals("customer_id")) {
						customer_id = Integer.parseInt(value);
						atomic_cust_id.set(customer_id);
					} else if (currentKey.equals("cust_branch_id")) {
						cust_branch_id = Integer.parseInt(value);
						atomic_cust_branch_id.set(cust_branch_id);
					}
				}
			}

//			iCustomerBankRepository.updateCustomerBankActiveStatus(cust_branch_id);
			iCustomerBankRepository.updateCustomerBankActiveStatus(customer_id, company_id);

			List<CCustomerBankModel> customerbankList = objectMapper.readValue(bankArray.toString(),
					new TypeReference<List<CCustomerBankModel>>() {
					});
			customerbankList.forEach(item -> {
				item.setCust_bank_id(0);
				item.setCust_branch_id(atomic_cust_branch_id.get());
				item.setCustomer_id(atomic_cust_id.get());
			});

			iCustomerBankRepository.saveAll(customerbankList);

			resp.put("success", "1");
			resp.put("message", "Records added successfully !...");
			resp.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/customerbank/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/customerbank/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int cust_bank_id) {
		return iCustomerBankRepository.FnDeleteRecord(cust_bank_id);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CCustomerBankViewModel> data = iCustomerBankViewRepository.FnShowAllRecords(pageable);
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
			Page<CCustomerBankViewModel> data = iCustomerBankViewRepository.FnShowAllActiveRecords(pageable);
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
	public HashMap<String, Object> FnShowParticularRecordForUpdate(int cust_branch_id, int company_id) {
		HashMap<String, Object> resp = new HashMap<>();
		try {

			List<CCustomerBankViewModel> json = iCustomerBankViewRepository
					.FnShowParticularRecordForUpdate(cust_branch_id, company_id);
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
	public JSONObject FnShowParticularRecord(int company_id, int company_branch_id, int cust_bank_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			CCustomerBankViewModel json = iCustomerBankViewRepository.FnShowParticularRecord(company_id,
					company_branch_id, cust_bank_id);
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
