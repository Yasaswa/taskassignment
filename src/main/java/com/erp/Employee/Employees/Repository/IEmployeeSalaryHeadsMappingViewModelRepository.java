package com.erp.Employee.Employees.Repository;

import com.erp.Employee.Employees.Model.CEmployeeSalaryHeadsMappingViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IEmployeeSalaryHeadsMappingViewModelRepository extends JpaRepository<CEmployeeSalaryHeadsMappingViewModel, Integer> {


	@Query(value = "FROM  CEmployeeSalaryHeadsMappingViewModel model where model.is_delete = 0 and model.employee_salary_heads_mapping_id = ?1 and model.company_id = ?2")
	List<CEmployeeSalaryHeadsMappingViewModel> FnShowEmployeeSalaryHeadMappingRecord(int employee_salary_heads_mapping_id,
	                                                                                 int company_id);

}
