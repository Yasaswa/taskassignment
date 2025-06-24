package com.erp.Employee.CmEmployeesSalary.Service;

import com.erp.Employee.CmEmployeesSalary.Model.CCmEmployeesSalaryModel;

import java.util.Map;


public interface ICmEmployeesSalaryService {

	Map<String, Object> FnAddUpdateRecord(CCmEmployeesSalaryModel cCmEmployeesSalaryModel);

	Map<String, Object> FnShowParticularRecordForUpdate(int employee_salary_id, int company_id);


}
