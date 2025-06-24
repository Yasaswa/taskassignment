package com.erp.AmModulesSubMenu.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.AmModulesSubMenu.Model.CAmModulesSubMenuModel;
import com.erp.AmModulesSubMenu.Model.CAmModulesSubMenuViewModel;
import com.erp.AmModulesSubMenu.Repository.IAmModulesSubMenuRepository;
import com.erp.AmModulesSubMenu.Repository.IAmModulesSubMenuViewRepository;
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
public class CAmModulesSubMenuServiceImpl implements IAmModulesSubMenuService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IAmModulesSubMenuRepository iAmModulesSubMenuRepository;

	@Autowired
	IAmModulesSubMenuViewRepository iAmModulesSubMenuViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CAmModulesSubMenuModel cAmModulesSubMenuModel) {
		Map<String, Object> responce = new HashMap();
		Optional<CAmModulesSubMenuModel> option = iAmModulesSubMenuRepository
				.findById(cAmModulesSubMenuModel.getModules_sub_menu_id());
		CAmModulesSubMenuModel MyModel = null;
		int company_id = cAmModulesSubMenuModel.getCompany_id();
		try {
			if (option.isPresent()) {
				cAmModulesSubMenuModel.setModified_on(new Date());
				CAmModulesSubMenuModel AmModulesSubMenuModel = iAmModulesSubMenuRepository.save(cAmModulesSubMenuModel);
				responce.put("success", "1");
				responce.put("data", AmModulesSubMenuModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" AmModulesSubMenu Updated Successfully!..");
			} else {
				CAmModulesSubMenuModel model = iAmModulesSubMenuRepository
						.checkIfNameExist(cAmModulesSubMenuModel.getModules_sub_menu_name());

				if (model != null) {
					responce.put("success", "0");
					responce.put("data", "");
					responce.put("error", " AmModulesSubMenu  is already exist!");

					return responce;

				} else {

					CAmModulesSubMenuModel respContent = iAmModulesSubMenuRepository.save(cAmModulesSubMenuModel);

					responce.put("success", "1");
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModulesSubMenu/FnAddUpdateRecord",
						sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/AmModulesSubMenu/FnAddUpdateRecord", 0,
					e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}


	@Override
	public Object FnDeleteRecord(int modules_sub_menu_id, int company_id) {
		Optional<CAmModulesSubMenuModel> option = iAmModulesSubMenuRepository.findById(modules_sub_menu_id);
		CAmModulesSubMenuModel cAmModulesSubMenuModel = new CAmModulesSubMenuModel();
		if (option.isPresent()) {
			cAmModulesSubMenuModel = option.get();
			cAmModulesSubMenuModel.setIs_delete(true);
			cAmModulesSubMenuModel.setDeleted_on(new Date());
			iAmModulesSubMenuRepository.save(cAmModulesSubMenuModel);

		}
		return cAmModulesSubMenuModel;
	}

//	@Override
//	public Object FnDeleteRecord(int modules_sub_menu_id, int company_id, String deleted_by) {
//	Optional<CAmModulesSubMenuModel> option = iAmModulesSubMenuRepository.findById(modules_sub_menu_id);
//		CAmModulesSubMenuModel cAmModulesSubMenuModel = new CAmModulesSubMenuModel();
//		if(option.isPresent()) {
//			cAmModulesSubMenuModel = option.get();
//			cAmModulesSubMenuModel.setIs_delete(true);
//			cAmModulesSubMenuModel.setDeleted_on(new Date());
//			cAmModulesSubMenuModel.setDeleted_by(deleted_by);
//			iAmModulesSubMenuRepository.save(cAmModulesSubMenuModel);
//			
//		}
//		return cAmModulesSubMenuModel;
//	}


	@Override
	public Page<CAmModulesSubMenuViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id) {
		return iAmModulesSubMenuViewRepository.FnShowAllActiveRecords(pageable, company_id);
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int modules_sub_menu_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CAmModulesSubMenuViewModel cAmModulesSubMenuViewModel = null;
		try {
			cAmModulesSubMenuViewModel = iAmModulesSubMenuViewRepository.FnShowParticularRecordForUpdate(modules_sub_menu_id, company_id);
			responce.put("success", "1");
			responce.put("data", cAmModulesSubMenuViewModel);
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
	public Page<CAmModulesSubMenuViewModel> FnShowParticularRecord(int modules_sub_menu_id, Pageable pageable, int company_id) {
		return iAmModulesSubMenuViewRepository.FnShowParticularRecord(modules_sub_menu_id, pageable, company_id);
	}

	@Override
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id) {
		return iAmModulesSubMenuViewRepository.FnShowAllReportRecords(pageable, company_id);
	}

//	@Override
//	public Page<CAmModulesSubMenuRptModel> FnShowAllReportRecords(Pageable pageable, int company_id) {
//		return iAmModulesSubMenuRptRepository.FnShowAllReportRecords(pageable, company_id);
//	}


}
