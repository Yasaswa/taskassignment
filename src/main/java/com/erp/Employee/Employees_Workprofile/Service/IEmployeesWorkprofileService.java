package com.erp.Employee.Employees_Workprofile.Service;

import com.erp.Employee.Employees_Workprofile.Model.CEmployeesWorkprofileModel;

import java.util.Map;

public interface IEmployeesWorkprofileService {

	Map<String, Object> FnAddUpdateRecord(CEmployeesWorkprofileModel cEmployeesWorkprofileModel);

	Object FnDeleteRecord(int employee_workprofile_id);


}
