package com.erp.Transporter_Banks.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Transporter.Model.CTransporterBanksModel;
import com.erp.Transporter.Model.CTransporterBanksViewModel;
import com.erp.Transporter.Repository.ITransporterBanksRepository;
import com.erp.Transporter.Repository.ITransporterBanksViewRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CTransporterBanksServiceImpl implements ITransporterBanksService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ITransporterBanksRepository iTransporterBanksRepository;

	@Autowired
	ITransporterBanksViewRepository iTransporterBanksViewRepository;

	@Override
	public JSONObject FnAddUpdateRecord(JSONObject jsonObject) {
		CTransporterBanksModel cTransporterBankModel = new CTransporterBanksModel();
		JSONObject resp = new JSONObject();
		int company_branch_id = 0;
		int company_id = 0;
		int transporter_id = 0;
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
					} else if (currentKey.equals("transporter_id")) {
						transporter_id = Integer.parseInt(value);
					}
				}
			}

			Object obj = iTransporterBanksRepository.updateTransporterBankActiveStatus(company_id,
					transporter_id);

			List<CTransporterBanksModel> banksModels = new ArrayList<>();

			if (!bankIds.keySet().isEmpty()) {
				for (String currentKey : bankIds.keySet()) {
					CTransporterBanksModel bankModel = new CTransporterBanksModel();
					Object key = bankIds.get(currentKey);
					String bank_id = key.toString();

					bankModel.setCompany_id(company_id);
					bankModel.setCompany_branch_id(company_branch_id);
					bankModel.setTransporter_id(transporter_id);
					bankModel.setBank_id(Integer.parseInt(bank_id));
					banksModels.add(bankModel);
				}
				iTransporterBanksRepository.saveAll(banksModels);

			}
			resp.put("success", "1");
			resp.put("message", "Records added successfully !...");
			resp.put("error", "");


		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/transporterbanks/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/transporterbanks/FnAddUpdateRecord",
					0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());

		}

		return resp;

	}

	@Override
	public Object FnDeleteRecord(int transporter_bank_id) {
		return iTransporterBanksRepository.FnDeleteRecord(transporter_bank_id);
	}

	@Override
	public Object FnShowAllRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CTransporterBanksViewModel> data = iTransporterBanksViewRepository.FnShowAllRecords(pageable);

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
			Page<CTransporterBanksViewModel> data = iTransporterBanksViewRepository.FnShowAllActiveRecords(pageable);

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
	public Object FnShowParticularRecordForUpdate(int transporter_bank_id) {
		JSONObject resp = new JSONObject();
		try {

			CTransporterBanksViewModel json = iTransporterBanksViewRepository
					.FnShowParticularRecordForUpdate(transporter_bank_id);

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
	public Object FnShowParticularRecord(int company_id, int company_branch_id, int transporter_bank_id) {
		JSONObject resp = new JSONObject();
		try {

			CTransporterBanksViewModel json = iTransporterBanksViewRepository.FnShowParticularRecord(company_id,
					company_branch_id, transporter_bank_id);

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
	public List<CTransporterBanksViewModel> FnShowParticularRecordForUpdate(int company_id, int company_branch_id,
	                                                                        int transporter_id) {
		return iTransporterBanksViewRepository.FnShowParticularRecordForUpdate(company_id, company_branch_id,
				transporter_id);
	}

}
