package com.erp.CmBanksList.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.CmBanksList.Model.CCmBanksListModel;
import com.erp.CmBanksList.Model.CCmBanksListViewModel;
import com.erp.CmBanksList.Repository.ICmBanksListRepository;
import com.erp.CmBanksList.Repository.ICmBanksListViewRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CCmBanksListServiceImpl implements ICmBanksListService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICmBanksListRepository iCmBanksListRepository;

	@Autowired
	ICmBanksListViewRepository iCmBanksListViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CCmBanksListModel cCmBanksListModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CCmBanksListModel> option = iCmBanksListRepository.findById(cCmBanksListModel.getBank_id());
		CCmBanksListModel MyModel = null;
		int company_id = cCmBanksListModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cCmBanksListModel.setModified_on(new Date());
				CCmBanksListModel cCmBankListModel = iCmBanksListRepository.save(cCmBanksListModel);
				responce.put("success", "1");
				responce.put("data", cCmBankListModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" CmBanksList Updated Successfully!..");
			}

//			Check similar Bank List Name or short name is exist or not

			CCmBanksListModel resultsBankListName = null;

			if (cCmBanksListModel.getBank_short_name() == null
					|| cCmBanksListModel.getBank_short_name().isEmpty()) {

				resultsBankListName = iCmBanksListRepository.getCheck(cCmBanksListModel.getBank_name(), null,
						cCmBanksListModel.getCompany_id());
			} else {
				resultsBankListName = iCmBanksListRepository.getCheck(cCmBanksListModel.getBank_name(),
						cCmBanksListModel.getBank_short_name(), cCmBanksListModel.getCompany_id());
			}

			if (resultsBankListName != null) {
				responce.put("success", 0);
				responce.put("error", "Bank List Name or Short Name is already exist!");
				return responce;
			} else {
				CCmBanksListModel respContent = iCmBanksListRepository.save(cCmBanksListModel);

				responce.put("success", "1");
				responce.put("data", respContent);
				responce.put("error", "");
				responce.put("message", "Record added successfully!...");
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmBanksList/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmBanksList/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int bank_id, int company_id, String deleted_by) {
		Optional<CCmBanksListModel> option = iCmBanksListRepository.findById(bank_id);
		CCmBanksListModel cCmBanksListModel = new CCmBanksListModel();
		if (option.isPresent()) {
			cCmBanksListModel = option.get();
			cCmBanksListModel.setIs_delete(true);
			cCmBanksListModel.setDeleted_on(new Date());
			cCmBanksListModel.setDeleted_by(deleted_by);
			iCmBanksListRepository.save(cCmBanksListModel);

		}
		return cCmBanksListModel;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int bank_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CCmBanksListModel cCmBanksListModel = null;
		try {
			cCmBanksListModel = iCmBanksListRepository.FnShowParticularRecordForUpdate(bank_id, company_id);
			responce.put("success", "1");
			responce.put("data", cCmBanksListModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

	@Override
	public Object FnShowAllReportRecords(Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<Map<String, Object>> data = iCmBanksListViewRepository.FnShowAllReportRecords(pageable);
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

	@Override
	public Page<CCmBanksListViewModel> FnShowParticularRecord(int bank_id, Pageable pageable, int company_id) {
		return iCmBanksListViewRepository.FnShowParticularRecord(bank_id, company_id, pageable);

	}

	@Override
	public Object FnShowAllActiveRecords(int company_id, Pageable pageable) {
		JSONObject resp = new JSONObject();
		try {
			Page<CCmBanksListViewModel> data = iCmBanksListViewRepository.FnShowAllActiveRecords(company_id, pageable);

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
