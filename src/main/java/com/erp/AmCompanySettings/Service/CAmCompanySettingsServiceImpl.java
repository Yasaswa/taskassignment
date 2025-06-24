package com.erp.AmCompanySettings.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmCompanySettings.Model.CAmCompanySettingsModel;
import com.erp.AmCompanySettings.Model.CAmCompanySettingsViewModel;
import com.erp.AmCompanySettings.Repository.IAmCompanySettingsRepository;
import com.erp.AmCompanySettings.Repository.IAmCompanySettingsViewRepository;
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
public class CAmCompanySettingsServiceImpl implements IAmCompanySettingsService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAmCompanySettingsRepository iAmCompanySettingsRepository;

	@Autowired
	IAmCompanySettingsViewRepository iAmCompanySettingsViewRepository;

//	@Autowired
//	IAmCompanySettingsRptRepository iAmCompanySettingsRptRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CAmCompanySettingsModel cAmCompanySettingsModel) {
		Map<String, Object> responce = new HashMap<>();

		CAmCompanySettingsModel MyModel = null;
		int company_id = cAmCompanySettingsModel.getCompany_id();
		try {
			int companySettings = iAmCompanySettingsRepository.updateStatus(company_id);

			CAmCompanySettingsModel respContent = iAmCompanySettingsRepository.save(cAmCompanySettingsModel);

			responce.put("success", "1");
			responce.put("data", respContent);
			responce.put("error", "");
			responce.put("message", "Record added successfully!...");


		} catch (DataAccessException e) {
			e.printStackTrace();
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/AmCompanySettings/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmCompanySettings/FnAddUpdateRecord",
					0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Object FnDeleteRecord(int am_company_settings_id, int company_id) {
		Optional<CAmCompanySettingsModel> option = iAmCompanySettingsRepository.findById(am_company_settings_id);
		CAmCompanySettingsModel cAmCompanySettingsModel = new CAmCompanySettingsModel();
		if (option.isPresent()) {
			cAmCompanySettingsModel = option.get();
			cAmCompanySettingsModel.setIs_delete(true);
			cAmCompanySettingsModel.setDeleted_on(new Date());
			iAmCompanySettingsRepository.save(cAmCompanySettingsModel);

		}
		return cAmCompanySettingsModel;
	}

//	@Override
//	public Object FnDeleteRecord(int am_company_settings_id, int company_id, String deleted_by) {
//		Optional<CAmCompanySettingsModel> option = iAmCompanySettingsRepository.findById(am_company_settings_id);
//		CAmCompanySettingsModel cAmCompanySettingsModel = new CAmCompanySettingsModel();
//		if (option.isPresent()) {
//			cAmCompanySettingsModel = option.get();
//			cAmCompanySettingsModel.setIs_delete(true);
//			cAmCompanySettingsModel.setDeleted_on(new Date());
//			cAmCompanySettingsModel.setDeleted_by(deleted_by);
//			iAmCompanySettingsRepository.save(cAmCompanySettingsModel);
//
//		}
//		return cAmCompanySettingsModel;
//	}

	@Override
	public Page<CAmCompanySettingsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iAmCompanySettingsViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int am_company_settings_id, int company_id) {
		Map<String, Object> responce = new HashMap();
		CAmCompanySettingsViewModel cAmCompanySettingsViewModel = null;
		try {
			cAmCompanySettingsViewModel = iAmCompanySettingsViewRepository
					.FnShowParticularRecordForUpdate(am_company_settings_id, company_id);
			responce.put("success", "1");
			responce.put("data", cAmCompanySettingsViewModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}

//	@Override
//	public Page<CAmCompanySettingsRptModel> FnShowAllReportRecords(Pageable pageable, int company_id) {
//		return iAmCompanySettingsRptRepository.FnShowAllReportRecords(pageable, company_id);
//	}

	@Override
	public Page<CAmCompanySettingsViewModel> FnShowParticularRecord(int am_company_settings_id, Pageable pageable,
	                                                                int company_id) {
		return iAmCompanySettingsViewRepository.FnShowParticularRecord(am_company_settings_id, pageable, company_id);
	}

}
