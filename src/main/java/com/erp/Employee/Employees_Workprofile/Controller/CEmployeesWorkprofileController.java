package com.erp.Employee.Employees_Workprofile.Controller;

import com.erp.Employee.Employees_Workprofile.Model.CEmployeesWorkprofileModel;
import com.erp.Employee.Employees_Workprofile.Service.IEmployeesWorkprofileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/employeesworkprofile")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CEmployeesWorkprofileController {

	@Autowired
	IEmployeesWorkprofileService iEmployeesWorkprofileService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CEmployeesWorkprofileModel cEmployeesWorkprofileModel)
			throws SQLException {
		Map<String, Object> resp = iEmployeesWorkprofileService.FnAddUpdateRecord(cEmployeesWorkprofileModel);
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{employee_workprofile_id}")
	public Object FnDeleteRecord(@PathVariable int employee_workprofile_id) {
		return iEmployeesWorkprofileService.FnDeleteRecord(employee_workprofile_id);
	}

}
