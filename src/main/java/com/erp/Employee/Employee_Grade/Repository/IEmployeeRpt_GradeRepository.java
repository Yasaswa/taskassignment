package com.erp.Employee.Employee_Grade.Repository;

import com.erp.Employee.Employee_Grade.Model.CEmployeeRpt_GradeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmployeeRpt_GradeRepository extends JpaRepository<CEmployeeRpt_GradeModel, String> {

	@Query(value = "SELECT * FROM cmv_employee_grade_rpt", nativeQuery = true)
	Page<CEmployeeRpt_GradeModel> FnShowAllReportRecords(Pageable pageable);
}
