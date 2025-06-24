package com.erp.Employee.Employees.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IEmployeesService {

//	Map<String, Object> FnAddUpdateRecord(CEmployeesModel cEmployeesModel);

	Object FnDeleteRecord(int employee_id, String deleted_by);

	Object FnShowAllRecords(Pageable pageable);

	Map<String, Object> FnShowParticularRecordForUpdate(int employee_id);

	Object FnShowAllActiveRecords(Pageable pageable);

	Object FnShowAllReportRecords(Pageable pageable);

	Object FnShowAllSummaryReportRecords(Pageable pageable);

	Object FnShowAllSummaryRecords(Pageable pageable);

	Object FnShowParticularRecord(int company_id, int company_branch_id, int employee_id);

	Map<String, Object> EarningAndDeductionFnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> EarningAndDeductionFnShowAllRecords(int employee_id);

	Map<String, Object> FnShowEmployeeSalaryHeadsMappingRecord(int employee_salary_heads_mapping_id, int company_id);

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject, MultipartFile empImageFile,boolean isApprove);

	Map<String, Object> FnShowSalaryAndWorkProfileRecords(int employee_id);

	Map<String, Object> GetDataFromDesignationOrEmplyeeType(int company_id, int common_id, String earning_deduction_mapping_baseKey);


}
