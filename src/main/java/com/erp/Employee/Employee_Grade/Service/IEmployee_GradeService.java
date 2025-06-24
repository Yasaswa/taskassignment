package com.erp.Employee.Employee_Grade.Service;

import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessModel;
import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessViewModel;
import com.erp.Employee.Employee_Grade.Model.CEmployee_GradeModel;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface IEmployee_GradeService {

	Map<String, Object> FnAddUpdateRecord(CEmployee_GradeModel cEmployee_GradeModel);

	Map<String, Object> FnDeleteRecord(int employee_grade_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int employee_grade_id, int company_id);

	Map<String, Object> FnAddUpdateGradeAccessRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteGradeAccessRecord(int modules_forms_grade_access_id, int company_id, String deleted_by);

	Map<String, Object> FnShowParticularGradeAccessRecordForUpdate(int modules_forms_grade_access_id,
			int company_id);

	List<CAmModulesFormsGradeAccessViewModel> FnShowAllAciveRecords(int grade_id, int company_id);


}
