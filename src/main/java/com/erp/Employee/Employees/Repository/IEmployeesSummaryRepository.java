package com.erp.Employee.Employees.Repository;

import com.erp.Employee.Employees.Model.CEmployeesSummaryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IEmployeesSummaryRepository extends JpaRepository<CEmployeesSummaryModel, Integer> {

	@Query(value = "SELECT * FROM cmv_employee_summary", nativeQuery = true)
	Page<CEmployeesSummaryModel> FnShowAllSummaryRecords(Pageable pageable);


}
