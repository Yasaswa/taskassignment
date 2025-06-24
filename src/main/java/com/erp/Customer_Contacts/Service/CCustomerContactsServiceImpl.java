package com.erp.Customer_Contacts.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Customer_Contacts.Model.CCustomerContactsModel;
import com.erp.Customer_Contacts.Model.CCustomerContactsViewModel;
import com.erp.Customer_Contacts.Repository.ICustomerContactsRepository;
import com.erp.Customer_Contacts.Repository.ICustomerContactsViewRepository;
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
import java.util.Map;

@Service
public class CCustomerContactsServiceImpl implements ICustomerContactsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICustomerContactsRepository iCustomerContactsRepository;

	@Autowired
	ICustomerContactsViewRepository iCustomerContactsViewRepository;


	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");

		int company_id = commonIdsObj.getInt("company_id");
		int customer_id = commonIdsObj.getInt("customer_id");
		try {
			JSONArray array = (JSONArray) jsonObject.get("CContactJsons");

			iCustomerContactsRepository.updateCustomerContactActiveStatus(customer_id, company_id);

			List<CCustomerContactsModel> contactsModels = objectMapper.readValue(array.toString(),
					new TypeReference<List<CCustomerContactsModel>>() {
					});

			contactsModels.forEach(item -> {
				item.setCustomer_contact_id(0);
			});

			iCustomerContactsRepository.saveAll(contactsModels);

			resp.put("success", "1");
			resp.put("message", "Records added succesfully!...");
			resp.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/customercontacts/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/customercontacts/FnAddUpdateRecord",
					0, e.getMessage());
			resp.put("success", "0");
			resp.put("error", e.getMessage());
		}

		return resp;
	}

	@Override
	public Object FnDeleteRecord(int customer_contact_id) {
		return iCustomerContactsRepository.FnDeleteRecord(customer_contact_id);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Page<CCustomerContactsViewModel> data = iCustomerContactsViewRepository.FnShowAllRecords(pageable);
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
			Page<CCustomerContactsViewModel> data = iCustomerContactsViewRepository.FnShowAllActiveRecords(pageable);
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
	public Object FnShowParticularRecordForUpdate(int customer_contact_id) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		try {

//			CCustomerContactsViewModel json = iCustomerContactsViewRepository
//					.FnShowParticularRecordForUpdate(customer_contact_id);
//			String json1 = objectMapper.writeValueAsString(json);
			resp.put("success", "1");
//			resp.put("data", json1);
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
	public Map<String, Object> FnShowParticularRecord(int customer_id, int company_id) {
		Map<String, Object> resp = new HashMap();
		try {
			List<CCustomerContactsViewModel> json = iCustomerContactsViewRepository
					.FnShowParticularRecords(customer_id, company_id);

			resp.put("success", "1");
			resp.put("data", json);
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}

		return resp;
	}


}
