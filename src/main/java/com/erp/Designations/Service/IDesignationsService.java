package com.erp.Designations.Service;


import com.erp.Designations.Model.CDesignationsModel;
import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public interface IDesignationsService {

	HashMap<String, Object> FnAddUpdateRecord(CDesignationsModel cDesignationsModel);

	Map<String, Object> FnDeleteRecord(int designation_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int designation_id);

	Object FnShowAllReportRecords(Pageable pageable);

	JSONObject FnShowParticularRecord(int company_id, int designation_id);

	Map<String, Object> EarningAndDeductionFnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> EarningAndDeductionFnShowAllRecords(int designation_id);

	Map<String, Object> FnShowEarningAndDeductionRecords(int company_id);

	Map<String, Object> FnShowDesignationSalaryHeadsMappingRecord(int designation_salary_heads_mapping_id,
	                                                              int company_id);

	Map<String, Object> FnEmployeeEarningTypeAndDeductionTypeShowAllRecords(String employee_type_name, int company_id);

}
