package com.erp.Bank_Contacts.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Bank_Contacts.Model.CBankContactsModel;
import com.erp.Bank_Contacts.Model.CBankContactsViewModel;
import com.erp.Bank_Contacts.Repository.IBankContactsRepository;
import com.erp.Bank_Contacts.Repository.IBankContactsViewRepository;
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
import java.util.List;

@Service
public class CBankContactsServiceImpl implements IBankContactsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IBankContactsRepository iBankContactsRepository;

	@Autowired
	IBankContactsViewRepository iBankContactsViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");

		int company_id = commonIdsObj.getInt("company_id");
		int bank_id = commonIdsObj.getInt("bank_id");
		try {
			JSONArray array = (JSONArray) jsonObject.get("BContactJsons");

			Object obj = iBankContactsRepository.updateBankContactActiveStatus(bank_id, company_id);

			List<CBankContactsModel> bankContactModels = objectMapper.readValue(array.toString(), new TypeReference<List<CBankContactsModel>>() {
			});

			iBankContactsRepository.saveAll(bankContactModels);

			resp.put("success", "1");
			resp.put("message", "Records added succesfully!...");
			resp.put("error", "");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/bankcontacts/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/bankcontacts/FnAddUpdateRecord", 0,
					e.getMessage());
			resp.put("success", "0");
			resp.put("error", e.getMessage());
		}

		return resp;
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {

		JSONObject resp = new JSONObject();
		try {
			Page<CBankContactsViewModel> data = iBankContactsViewRepository.FnShowAllRecords(pageable);

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
			Page<CBankContactsViewModel> data = iBankContactsViewRepository.FnShowAllActiveRecords(pageable);

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
	public Object FnDeleteRecord(int bank_contact_id, int company_id, String deleted_by) {
		return iBankContactsRepository.FnDeleteRecord(bank_contact_id, company_id, deleted_by);
	}

	@Override
	public List<CBankContactsViewModel> FnShowParticularRecordForUpdate(int bank_id, int company_id) {
		List<CBankContactsViewModel> contactsViewModels = null;
		try {
			contactsViewModels = iBankContactsViewRepository.FnShowParticularRecordForUpdate(bank_id, company_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contactsViewModels;
	}

	@Override
	public Object FnShowParticularRecord(int company_id, int bank_contact_id) {
		JSONObject resp = new JSONObject();
		try {

			CBankContactsViewModel json = iBankContactsViewRepository.FnShowParticularRecord(company_id,
					bank_contact_id);

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

}
