package com.erp.AmModulesForms.Service;

import com.erp.AmModulesForms.Model.CAmModulesFormsModel;
import com.erp.AmModulesForms.Model.CAmModulesFormsViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IAmModulesFormsService {

	List<CAmModulesFormsViewModel> FnShowAllActiveRecords(int company_id);

	CAmModulesFormsViewModel FnShowParticularRecord(int modules_forms_id, int company_id);

	JSONObject FnAddUpdateRecord(CAmModulesFormsModel cAmModulesFormsModel);

	Object FnDeleteRecord(int modules_forms_id, int company_id);

//	Object FnDeleteRecord(int modules_forms_id, int company_id, String deleted_by);

//	Page<CAmModulesFormsRptModel> FnShowAllReportRecords(Pageable pageable);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}