package com.erp.AmModules.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModules.Model.CAmModulesModel;
import com.erp.AmModules.Model.CAmModulesViewModel;
import com.erp.AmModules.Repository.IAmModulesRepository;
import com.erp.AmModules.Repository.IAmModulesViewRepository;
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
public class CAmModulesServiceImpl implements IAmModulesService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAmModulesRepository iAmModulesRepository;

	@Autowired
	IAmModulesViewRepository iAmModulesViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CAmModulesModel cAmModulesModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CAmModulesModel> option = iAmModulesRepository.findById(cAmModulesModel.getModules_id());
		CAmModulesModel MyModel = null;

		int company_id = cAmModulesModel.getCompany_id();

		try {
			if (option.isPresent()) {
				cAmModulesModel.setModified_on(new Date());
				CAmModulesModel amModulesModel = iAmModulesRepository.save(cAmModulesModel);
				responce.put("success", "1");
				responce.put("data", amModulesModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" AmModules Updated Successfully!..");
			} else {
				CAmModulesModel model = iAmModulesRepository.checkIfNameExist(cAmModulesModel.getModule_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " AmModules  is already exist!");

					return responce;

				} else {

					CAmModulesModel respContent = iAmModulesRepository.save(cAmModulesModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModules/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModules/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

	@Override
	public Object FnDeleteRecord(int modules_id) {
		Optional<CAmModulesModel> option = iAmModulesRepository.findById(modules_id);
		CAmModulesModel cAmModulesModel = new CAmModulesModel();
		if (option.isPresent()) {
			cAmModulesModel = option.get();
			cAmModulesModel.setIs_delete(true);
			cAmModulesModel.setDeleted_on(new Date());
			iAmModulesRepository.save(cAmModulesModel);
		}
		return cAmModulesModel;
	}

//	@Override
//	public Object FnDeleteRecord(int modules_id, int company_id, String deleted_by) {
//		Optional<CAmModulesModel> option = iAmModulesRepository.findById(modules_id);
//		CAmModulesModel cAmModulesModel = new CAmModulesModel();
//		if(option.isPresent()) {
//			cAmModulesModel = option.get();
//			cAmModulesModel.setIs_delete(true);
//			cAmModulesModel.setDeleted_on(new Date());
//			cAmModulesModel.setDeleted_by(deleted_by);
//			iAmModulesRepository.save(cAmModulesModel);
//		}
//		return cAmModulesModel;
//	}

	@Override
	public Page<CAmModulesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iAmModulesViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int modules_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CAmModulesModel cAmModulesModel = null;

		try {
			cAmModulesModel = iAmModulesRepository.FnShowParticularRecordForUpdate(modules_id, company_id);
			responce.put("success", "1");
			responce.put("data", cAmModulesModel);
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

//	@Override
//	public Page<CAmModulesRptModel> FnShowAllReportRecords(Pageable pageable, int company_id) {
//		return iAmModulesRptRepository.FnShowAllReportRecords(pageable, company_id);
//	}

	@Override
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iAmModulesViewRepository.FnShowAllReportRecords(pageable, company_id);
	}

	@Override
	public Page<CAmModulesViewModel> FnShowParticularRecord(int modules_id, Pageable pageable, int company_id) {
		return iAmModulesViewRepository.FnShowParticularRecord(modules_id, pageable, company_id);
	}

}
