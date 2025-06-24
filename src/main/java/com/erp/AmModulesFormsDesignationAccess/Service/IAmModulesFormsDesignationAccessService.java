package com.erp.AmModulesFormsDesignationAccess.Service;

import com.erp.AmModulesFormsDesignationAccess.Model.CAmModulesFormsDesignationAccessViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IAmModulesFormsDesignationAccessService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int modules_forms_designation_access_id, int company_id);

//	Object FnDeleteRecord(int modules_forms_designation_access_id, int company_id, String deleted_by);

	List<CAmModulesFormsDesignationAccessViewModel> FnShowAllActiveRecords(int designation_id, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int modules_forms_designation_access_id, int company_id);

	Page<CAmModulesFormsDesignationAccessViewModel> FnShowParticularRecord(int modules_forms_designation_access_id, Pageable pageable, int company_id);

}
