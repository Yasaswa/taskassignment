package com.erp.Employee.Employees.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.Employee.Employees.Model.CEmployeeEarningMappingViewModel;

public interface IEmployeeEarningMappingViewModelRepository extends JpaRepository<CEmployeeEarningMappingViewModel, Integer> {

	@Query(value = "FROM CEmployeeEarningMappingViewModel model where model.employee_code IN ?1 and model.company_id = ?2 and model.is_delete = false")
	List<CEmployeeEarningMappingViewModel> FnGetEarningMappingsByEmplCodes(List<String> employeeCodes, int company_id);
}
