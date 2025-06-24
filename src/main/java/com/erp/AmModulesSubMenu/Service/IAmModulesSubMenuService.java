package com.erp.AmModulesSubMenu.Service;

import com.erp.AmModulesSubMenu.Model.CAmModulesSubMenuModel;
import com.erp.AmModulesSubMenu.Model.CAmModulesSubMenuViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IAmModulesSubMenuService {

	Map<String, Object> FnAddUpdateRecord(CAmModulesSubMenuModel cAmModulesSubMenuModel);

	Object FnDeleteRecord(int modules_sub_menu_id, int company_id);

//	Object FnDeleteRecord(int modules_sub_menu_id, int company_id, String deleted_by);

	Page<CAmModulesSubMenuViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

//	Page<CAmModulesSubMenuRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int modules_sub_menu_id, int company_id);

	Page<CAmModulesSubMenuViewModel> FnShowParticularRecord(int modules_sub_menu_id, Pageable pageable, int company_id);

}
