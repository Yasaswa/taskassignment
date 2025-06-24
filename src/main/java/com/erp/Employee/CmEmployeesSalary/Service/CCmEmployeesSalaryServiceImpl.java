package com.erp.Employee.CmEmployeesSalary.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Employee.CmEmployeesSalary.Model.CCmEmployeesSalaryModel;
import com.erp.Employee.CmEmployeesSalary.Repository.ICmEmployeesSalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CCmEmployeesSalaryServiceImpl implements ICmEmployeesSalaryService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	ICmEmployeesSalaryRepository iCmEmployeesSalaryRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CCmEmployeesSalaryModel cCmEmployeesSalaryModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CCmEmployeesSalaryModel> option = iCmEmployeesSalaryRepository
				.findById(cCmEmployeesSalaryModel.getEmployee_salary_id());
		CCmEmployeesSalaryModel MyModel = null;
		int company_id = cCmEmployeesSalaryModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CCmEmployeesSalaryModel model = iCmEmployeesSalaryRepository.save(cCmEmployeesSalaryModel);
				responce.put("success", "1");
				responce.put("data", model);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" CmEmployeesSalary Updated Successfully!..");
			} else {

				CCmEmployeesSalaryModel respContent = iCmEmployeesSalaryRepository.save(cCmEmployeesSalaryModel);

				responce.put("success", "1");
				responce.put("data", respContent);
				responce.put("error", "");
				responce.put("message", "Record added successfully!...");

				System.out.println("CmEmployeesSalary  saved succesfully!..");
				return responce;
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmEmployeesSalary/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/CmEmployeesSalary/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

			return responce;
		}

		return responce;

	}


	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int employee_salary_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		CCmEmployeesSalaryModel cCmEmployeesSalaryModel = null;
		try {
			cCmEmployeesSalaryModel = iCmEmployeesSalaryRepository.FnShowParticularRecordForUpdate(employee_salary_id,
					company_id);
			responce.put("success", "1");
			responce.put("data", cCmEmployeesSalaryModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}
		return responce;
	}


}
