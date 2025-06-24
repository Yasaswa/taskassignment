package com.erp.AmModulesMenu.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesMenu.Model.CAmModulesMenuModel;
import com.erp.AmModulesMenu.Model.CAmModulesMenuViewModel;
import com.erp.AmModulesMenu.Repository.IAmModulesMenuRepository;
import com.erp.AmModulesMenu.Repository.IAmModulesMenuViewRepository;
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
public class CAmModulesMenuServiceImpl implements IAmModulesMenuService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAmModulesMenuRepository iAmModulesMenuRepository;

	@Autowired
	IAmModulesMenuViewRepository iAmModulesMenuViewRepository;


	@Override
	public Map<String, Object> FnAddUpdateRecord(CAmModulesMenuModel cAmModulesMenuModel) {
		Map<String, Object> responce = new HashMap();
		Optional<CAmModulesMenuModel> option = iAmModulesMenuRepository
				.findById(cAmModulesMenuModel.getModules_menu_id());
		CAmModulesMenuModel MyModel = null;
		int company_id = cAmModulesMenuModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cAmModulesMenuModel.setModified_on(new Date());
				CAmModulesMenuModel AmModulesMenuModel = iAmModulesMenuRepository.save(cAmModulesMenuModel);
				responce.put("success", "1");
				responce.put("data", AmModulesMenuModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" AmModulesMenu Updated Successfully!..");
			} else {
				CAmModulesMenuModel model = iAmModulesMenuRepository
						.checkIfNameExist(cAmModulesMenuModel.getModules_menu_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " AmModulesMenu  is already exist!");

					return responce;

				} else {

					CAmModulesMenuModel respContent = iAmModulesMenuRepository.save(cAmModulesMenuModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModulesMenu/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModulesMenu/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}


	@Override
	public Object FnDeleteRecord(int modules_menu_id, int company_id) {
		Optional<CAmModulesMenuModel> option = iAmModulesMenuRepository.findById(modules_menu_id);
		CAmModulesMenuModel cAmModulesMenuModel = new CAmModulesMenuModel();
		if (option.isPresent()) {
			cAmModulesMenuModel = option.get();
			cAmModulesMenuModel.setIs_delete(true);
			cAmModulesMenuModel.setDeleted_on(new Date());
			iAmModulesMenuRepository.save(cAmModulesMenuModel);

		}
		return cAmModulesMenuModel;
	}

//	@Override
//	public Object FnDeleteRecord(int modules_menu_id, int company_id, String deleted_by) {
//	Optional<CAmModulesMenuModel> option = iAmModulesMenuRepository.findById(modules_menu_id);
//		CAmModulesMenuModel cAmModulesMenuModel = new CAmModulesMenuModel();
//		if(option.isPresent()) {
//			cAmModulesMenuModel = option.get();
//			cAmModulesMenuModel.setIs_delete(true);
//			cAmModulesMenuModel.setDeleted_on(new Date());
//			cAmModulesMenuModel.setDeleted_by(deleted_by);
//			iAmModulesMenuRepository.save(cAmModulesMenuModel);
//			
//		}
//		return cAmModulesMenuModel;
//	}


	@Override
	public Page<CAmModulesMenuViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iAmModulesMenuViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int modules_menu_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CAmModulesMenuViewModel cAmModulesMenuViewModel = null;
		try {
			cAmModulesMenuViewModel = iAmModulesMenuViewRepository.FnShowParticularRecordForUpdate(modules_menu_id, company_id);
			responce.put("success", "1");
			responce.put("data", cAmModulesMenuViewModel);
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


	@Override
	public Page<CAmModulesMenuViewModel> FnShowParticularRecord(int modules_menu_id, Pageable pageable, int company_id) {
		return iAmModulesMenuViewRepository.FnShowParticularRecord(modules_menu_id, pageable, company_id);
	}


	@Override
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iAmModulesMenuViewRepository.FnShowAllReportRecords(pageable, company_id);
	}


//	@Override
//	public Page<CAmModulesMenuRptModel> FnShowAllReportRecords(Pageable pageable, int company_id) {
//		return iAmModulesMenuRptRepository.FnShowAllReportRecords(pageable, company_id);
//	}


}
