package com.erp.Employee.Employees_Workprofile.Repository;

import com.erp.Employee.Employees_Workprofile.Model.CEmployeesWorkprofileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IEmployeesWorkprofileRepository extends JpaRepository<CEmployeesWorkprofileModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_employees_workprofile set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where employee_workprofile_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int employee_workprofile_id);

	@Query(value = "FROM CEmployeesWorkprofileModel model where model.is_delete = 0 and model.employee_id = ?1")
	CEmployeesWorkprofileModel FnShowEmployeeWorkProfileRecords(int employee_id);


}
