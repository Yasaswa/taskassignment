package com.erp.Employee.Employees.Repository;

import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IEmployeesViewRepository extends JpaRepository<CEmployeesViewModel, Integer> {

	@Query(value = "Select * FROM  cmv_employee order by employee_id Desc", nativeQuery = true)
	Page<CEmployeesViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_employee where is_delete =0  order by employee_id Desc", nativeQuery = true)
	Page<CEmployeesViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_employee where is_delete =0 and employee_id = ?1", nativeQuery = true)
	CEmployeesViewModel FnShowParticularRecordForUpdate(int employee_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_employee where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and employee_id = ?1")
	CEmployeesViewModel FnShowParticularRecord(int company_id, int company_branch_id, int employee_id);

	@Query(value = "SELECT * FROM cmv_employee_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

	@Query(value = "SELECT * FROM cmv_employee_summary_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllSummaryReportRecords(Pageable pageable);

	@Query(value = "FROM CEmployeesViewModel model where model.is_delete = 0 and model.employee_id = ?1")
	CEmployeesViewModel FnShowEmployeeMasterRecords(int employee_id);

	@Query(value = "FROM CEmployeesViewModel model where model.is_delete = 0 and model.employee_id IN ?1")
	List<CEmployeesViewModel> FnGetEmployeesByEmplIds(List<Integer> employee_id);

//	@Query(value = "FROM CEmployeesViewModel model where model.is_delete = FALSE and model.is_active = TRUE and model.employee_type = ?1 and model.company_id = ?2 ORDER BY department_id ASC, sub_department_id ASC, employee_code ASC")
//	List<CEmployeesViewModel> FnGetEmployeesByEmplType(String employee_type, int company_id);

@Query(value = "FROM CEmployeesViewModel model " +
		"WHERE model.is_delete = FALSE " +
		"AND model.is_active = TRUE " +
		"AND model.employee_type = ?1 " +
		"AND (?2 IS NULL OR model.department_id = ?2) " +
		"AND (?3 IS NULL OR model.sub_department_id = ?3) " +
		"AND model.company_id = ?4 " +
		"ORDER BY model.department_id ASC, model.sub_department_id ASC, model.employee_code ASC")
List<CEmployeesViewModel> FnGetEmployeesByEmplType(String employeeType, Integer departmentId, Integer subDepartmentId, int companyId);

	@Query(value = "FROM CEmployeesViewModel model where model.is_active = 1 and model.company_id = ?1")
	List<CEmployeesViewModel> FnShowEmployeeRecords(int company_id);

	@Query(value = "FROM CEmployeesViewModel model " +
			"WHERE model.is_delete = FALSE " +
			"AND model.is_active = TRUE " +
			"AND (?1 IS NULL OR model.employee_type_group = ?1) " +
			"AND (?2 IS NULL OR model.department_id = ?2) " +
			"AND (?3 IS NULL OR model.sub_department_id = ?3) " +
			"AND model.company_id = ?4 " +
			"AND (?5 IS NULL OR model.old_employee_code = ?5) " +
			"ORDER BY model.department_id ASC, model.sub_department_id ASC, model.employee_code ASC")
	List<CEmployeesViewModel> FnGetEmployeesForReport(String employeeType, Integer department, Integer subDepartment, int companyId, String punchingCode);
}
