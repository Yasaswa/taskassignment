package com.erp.Employee.CmEmployeesSalary.Repository;

import com.erp.Employee.CmEmployeesSalary.Model.CCmEmployeesSalaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICmEmployeesSalaryRepository extends JpaRepository<CCmEmployeesSalaryModel, Integer> {

	@Query(value = "FROM CCmEmployeesSalaryModel model where model.is_delete =0 and model.employee_salary_id = ?1 and model.company_id = ?2")
	CCmEmployeesSalaryModel FnShowParticularRecordForUpdate(int employee_salary_id, int company_id);

	@Query(value = "FROM CCmEmployeesSalaryModel model where model.is_delete =0 and model.employee_id = ?1")
	CCmEmployeesSalaryModel FnShowEmployeeSalaryRecords(int employee_id);

}
