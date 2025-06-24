package com.erp.Transporter_Contacts.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Transporter.Model.CTransporterContactsModel;
import com.erp.Transporter.Model.CTransporterContactsViewModel;
import com.erp.Transporter.Repository.ITransporterContactsRepository;
import com.erp.Transporter.Repository.ITransporterContactsViewRepository;
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
public class CTransporterContactsServiceImpl implements ITransporterContactsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ITransporterContactsRepository iTransporterContactsRepository;

	@Autowired
	ITransporterContactsViewRepository iTransporterContactsViewRepository;


	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		JSONObject resp = new JSONObject();
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");

		int company_id = commonIdsObj.getInt("company_id");
		int transporter_id = commonIdsObj.getInt("transporter_id");
		try {

			JSONArray array = (JSONArray) jsonObject.get("TContactJsons");

			Object obj = iTransporterContactsRepository.updateTransporterContactActiveStatus(transporter_id,
					company_id);

			List<CTransporterContactsModel> accessModels = objectMapper.readValue(array.toString(),
					new TypeReference<List<CTransporterContactsModel>>() {
					});

			iTransporterContactsRepository.saveAll(accessModels);

			resp.put("success", "1");
			resp.put("message", "Records added succesfully!...");
			resp.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/transportercontacts/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/transportercontacts/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("error", e.getMessage());
		}

		return resp;
	}

	@Override
	public Object FnDeleteRecord(int transporter_contact_id) {
		return iTransporterContactsRepository.FnDeleteRecord(transporter_contact_id);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CTransporterContactsViewModel> data = iTransporterContactsViewRepository.FnShowAllRecords(pageable);

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
			Page<CTransporterContactsViewModel> data = iTransporterContactsViewRepository
					.FnShowAllActiveRecords(pageable);

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
	public List<CTransporterContactsViewModel> FnShowParticularRecordForUpdate(int transporter_id) {
		List<CTransporterContactsViewModel> contactsViewModels = null;
		try {
			contactsViewModels = iTransporterContactsViewRepository.FnShowParticularRecordForUpdate(transporter_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contactsViewModels;
	}

	@Override
	public Object FnShowParticularRecord(int company_id, int company_branch_id, int transporter_contact_id) {
		JSONObject resp = new JSONObject();
		try {

			CTransporterContactsViewModel json = iTransporterContactsViewRepository.FnShowParticularRecord(company_id,
					company_branch_id, transporter_contact_id);

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
