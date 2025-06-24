package com.erp.AmModules.Service;

import com.erp.AmModules.Model.CAmModulesModel;
import com.erp.AmModules.Model.CAmModulesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IAmModulesService {

	Map<String, Object> FnAddUpdateRecord(CAmModulesModel cAmModulesModel);

	Object FnDeleteRecord(int modules_id);

//	Object FnDeleteRecord(int modules_id, int company_id, String deleted_by);

	Page<CAmModulesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int modules_id, int company_id);

	Page<CAmModulesViewModel> FnShowParticularRecord(int modules_id, Pageable pageable, int company_id);

//	Page<CAmModulesRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);


}
