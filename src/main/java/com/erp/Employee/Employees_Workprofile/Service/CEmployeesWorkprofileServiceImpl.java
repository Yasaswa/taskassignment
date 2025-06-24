package com.erp.Employee.Employees_Workprofile.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Employee.Employees_Workprofile.Model.CEmployeesWorkprofileModel;
import com.erp.Employee.Employees_Workprofile.Repository.IEmployeesWorkprofileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CEmployeesWorkprofileServiceImpl implements IEmployeesWorkprofileService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IEmployeesWorkprofileRepository iEmployeesWorkprofileRepository;

	@Override
	public Object FnDeleteRecord(int employee_workprofile_id) {
		return iEmployeesWorkprofileRepository.FnDeleteRecord(employee_workprofile_id);
	}

	@Override
	public Map<String, Object> FnAddUpdateRecord(CEmployeesWorkprofileModel cEmployeesWorkprofileModel) {
		Map<String, Object> resp = new HashMap<>();
		CEmployeesWorkprofileModel MyModel = null;
		int company_id = cEmployeesWorkprofileModel.getCompany_id();
		try {
			Optional<CEmployeesWorkprofileModel> option = iEmployeesWorkprofileRepository
					.findById(cEmployeesWorkprofileModel.getEmployee_workprofile_id());

			if (option.isPresent()) {
				cEmployeesWorkprofileModel.setModified_on(new Date());
				CEmployeesWorkprofileModel mymodel = iEmployeesWorkprofileRepository.save(cEmployeesWorkprofileModel);
				resp.put("success", "1");
				resp.put("data", mymodel);
				resp.put("error", "");
				resp.put("message", "Record updated succesfully!...");
				System.out.println(" Employees Work profile updated successfully!..");
			} else {

				CEmployeesWorkprofileModel EmployeesWorkprofileModel = iEmployeesWorkprofileRepository
						.save(cEmployeesWorkprofileModel);
				resp.put("success", "1");
				resp.put("data", EmployeesWorkprofileModel);
				resp.put("error", "");
				resp.put("message", "Record added succesfully!...");

				System.out.println(" Employees Work profile  saved succesfully!..");
			}

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/employeesworkprofile/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				resp.put("success", "0");
				resp.put("data", "");
				resp.put("error", sqlEx.getMessage());

			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/employeesworkprofile/FnAddUpdateRecord", 0, e.getMessage());
			resp.put("success", "0");
			resp.put("data", "");
			resp.put("error", e.getMessage());
		}

		return resp;

	}

}
