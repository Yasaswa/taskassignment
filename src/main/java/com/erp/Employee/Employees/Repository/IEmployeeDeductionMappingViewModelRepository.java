package com.erp.Employee.Employees.Repository;

import com.erp.Employee.Employees.Model.CEmployeeDeductionMappingViewModel;
import com.erp.Employee.Employees.Model.CEmployeeEarningMappingViewModel;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmployeeDeductionMappingViewModelRepository extends JpaRepository<CEmployeeDeductionMappingViewModel, Integer> {

	@Query(value = "FROM CEmployeeDeductionMappingViewModel model where model.employee_code IN ?1 and model.company_id = ?2 and is_delete = false")
	List<CEmployeeDeductionMappingViewModel> FnGetDeductionsMappingsByEmplCodes(List<String> employeeCodes, int company_id);

}
