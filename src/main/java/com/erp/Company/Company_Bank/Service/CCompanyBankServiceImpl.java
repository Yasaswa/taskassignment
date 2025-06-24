package com.erp.Company.Company_Bank.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Company.Company_Bank.Model.CCompanyBankModel;
import com.erp.Company.Company_Bank.Model.CCompanyBankViewModel;
import com.erp.Company.Company_Bank.Repository.ICompanyBankRepository;
import com.erp.Company.Company_Bank.Repository.ICompanyBankViewRepository;
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
public class CCompanyBankServiceImpl implements ICompanyBankService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICompanyBankRepository iCompanyBankRepository;

	@Autowired
	ICompanyBankViewRepository iCompanyBankViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		CCompanyBankModel cCompanyBankModel = new CCompanyBankModel();
		ObjectMapper objectMapper = new ObjectMapper();
		JSONObject resp = new JSONObject();
		JSONArray bankArray = (JSONArray) jsonObject.get("bankIds");
		int company_branch_id = 0;
		int company_id = 0;
		try {
			JSONObject compAndBrnchId = (JSONObject) jsonObject.get("compAndBrnchId");

			if (!compAndBrnchId.keySet().isEmpty()) {
				for (String currentKey : compAndBrnchId.keySet()) {
					Object key = compAndBrnchId.get(currentKey);
					String value = key.toString();
					if (currentKey.equals("company_branch_id")) {
						company_branch_id = Integer.parseInt(value);
					} else if (currentKey.equals("company_id")) {
						company_id = Integer.parseInt(value);
					}
				}
			}
			iCompanyBankRepository.updateCompanyBankActiveStatus(company_id, company_branch_id);

			List<CCompanyBankModel> bankList = objectMapper.readValue(bankArray.toString(),
					new TypeReference<List<CCompanyBankModel>>() {
					});

			iCompanyBankRepository.saveAll(bankList);

			resp.put("success", "1");
			resp.put("data", "");
			resp.put("error", "");

		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/companybank/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());
				return resp;

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/companybank/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
			return resp;

		}

		return resp;

	}

	@Override
	public Page<CCompanyBankViewModel> FnShowAllRecords(Pageable pageable) {
		return iCompanyBankViewRepository.FnShowAllRecords(pageable);
	}

	@Override
	public Object FnDeleteRecord(int company_bank_id) {
		return iCompanyBankRepository.FnDeleteRecord(company_bank_id);
	}

	@Override
	public Page<CCompanyBankViewModel> FnShowAllActiveRecords(Pageable pageable) {
		return iCompanyBankViewRepository.FnShowAllActiveRecords(pageable);
	}

	@Override
	public CCompanyBankViewModel FnShowParticularRecord(int company_bank_id) {
		return iCompanyBankViewRepository.FnShowParticularRecord(company_bank_id);
	}

	@Override
	public List<CCompanyBankViewModel> FnShowParticularRecordForUpdate(int company_branch_id, int company_id,
	                                                                   Pageable pageable) {
		return iCompanyBankViewRepository.FnShowParticularRecordForUpdate(company_branch_id, company_id, pageable);
	}

}
