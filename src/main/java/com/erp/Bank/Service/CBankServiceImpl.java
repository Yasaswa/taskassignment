package com.erp.Bank.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Bank.Model.CBankModel;
import com.erp.Bank.Model.CBankViewModel;
import com.erp.Bank.Repository.IBankRepository;
import com.erp.Bank.Repository.IBankviewRepository;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CBankServiceImpl implements IBankService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IBankRepository iBankRepository;

	@Autowired
	IBankviewRepository iBankViewRepository;

	@Autowired
	IBankContactsRepository iBankContactsRepository;

	@Autowired
	IBankContactsViewRepository iBankContactsViewRepository;

	@Autowired
	private JdbcTemplate executeQuery;

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject) {

		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		JSONObject commonIdsObj = (JSONObject) jsonObject.get("commonIds");
		int company_id = commonIdsObj.getInt("company_id");
		int company_branch_id = commonIdsObj.getInt("company_branch_id");
		int bank_id = commonIdsObj.getInt("bank_id");

		AtomicInteger atomicBankId = new AtomicInteger(bank_id);

		JSONObject json = jsonObject.getJSONObject("BankData");
		JSONArray bankContactarray = (JSONArray) jsonObject.get("BContactJsons");

		try {
			CBankModel responseBankMaster = null;
			CBankModel jsonModel = objectMapper.readValue(json.toString(), CBankModel.class);

			if (jsonModel.getBank_id() == 0) {
				CBankModel model = iBankRepository.getCheck(jsonModel.getBank_name(), jsonModel.getBank_account_no(),
						jsonModel.getAccount_type(), jsonModel.getBank_branch_name(), company_id);
				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " Bank Branch Name is already exist!");
					return responce;

				}
			}

			responseBankMaster = iBankRepository.save(jsonModel);
			atomicBankId.set(responseBankMaster.getBank_id());

			// Bank Contact
			if (!bankContactarray.isEmpty()) {
				iBankContactsRepository.updateBankContactActiveStatus(bank_id, company_id);

				List<CBankContactsModel> bankContactModels = objectMapper.readValue(bankContactarray.toString(),
						new TypeReference<List<CBankContactsModel>>() {
						});
				bankContactModels.forEach(item -> {
					item.setBank_contact_id(0);
					item.setBank_id(atomicBankId.get());
				});

				iBankContactsRepository.saveAll(bankContactModels);
			}

			responce.put("success", 1);
			responce.put("data", responseBankMaster);
			responce.put("error", "");
			responce.put("message", bank_id == 0 ? "Record added successfully!" : "Record updated successfully!");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/bank/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/bank/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int bank_id, int company_id) {
		Map<String, Object> responce = new HashMap();
		try {

			CBankViewModel json = iBankViewRepository.FnShowParticularRecordForUpdate(bank_id, company_id);
			List<CBankContactsViewModel> BankContactsRecords = iBankContactsViewRepository
					.FnShowBankContactsRecords(bank_id, company_id);

			responce.put("data", json);
			responce.put("BankContactData", BankContactsRecords);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}

	@Override
	public Object FnShowAllRecords() {
		JSONObject resp = new JSONObject();
		try {
			List<CBankViewModel> data = iBankViewRepository.FnShowAllRecords();

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
	public Object FnShowAllActiveRecords(int company_id, Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CBankViewModel> data = iBankViewRepository.FnShowAllActiveRecords(company_id, pageable);

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
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iBankViewRepository.FnShowAllReportRecords(pageable);

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
	public Object FnDeleteRecord(int bank_id, int company_id, String deleted_by) {
		return iBankRepository.FnDeleteRecord(bank_id, company_id, deleted_by);
	}

	@Override
	public Object FnShowParticularRecord(int company_id, Pageable pageable) {

		JSONObject resp = new JSONObject();
		try {
			Page<CBankViewModel> data = iBankViewRepository.FnShowParticularRecord(company_id, pageable);

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
