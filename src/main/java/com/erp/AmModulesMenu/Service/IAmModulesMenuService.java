package com.erp.AmModulesMenu.Service;

import com.erp.AmModulesMenu.Model.CAmModulesMenuModel;
import com.erp.AmModulesMenu.Model.CAmModulesMenuViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IAmModulesMenuService {

	Map<String, Object> FnAddUpdateRecord(CAmModulesMenuModel cAmModulesMenuModel);

	Object FnDeleteRecord(int modules_menu_id, int company_id);

//	Object FnDeleteRecord(int modules_menu_id, int company_id, String deleted_by);


	Page<CAmModulesMenuViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

//	Page<CAmModulesMenuRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int modules_menu_id, int company_id);

	Page<CAmModulesMenuViewModel> FnShowParticularRecord(int modules_menu_id, Pageable pageable, int company_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);


}
