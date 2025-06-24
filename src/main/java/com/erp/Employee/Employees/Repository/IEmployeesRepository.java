package com.erp.Employee.Employees.Repository;

import com.erp.Employee.Employees.Model.CEmployeesModel;
import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IEmployeesRepository extends JpaRepository<CEmployeesModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_employee set is_active = false, deleted_by=?2, deleted_on = CURRENT_TIMESTAMP() where employee_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int employee_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_employee WHERE employee_name = ?1")
	CEmployeesModel getCheck(String employee_name);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_employee WHERE company_id =?1 AND company_branch_id =?2 AND username = ?3 AND password = ?4")
	CEmployeesModel checkUserIsExist(int company_id, int company_branch_id, String username, String password);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_employee WHERE employee_name = ?1 and company_id = ?2  and is_active = 1 and is_delete = 0")
	CEmployeesModel getCheck(String employee_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CEmployeesModel model set model.password = ?1 where model.employee_code=?2 and model.company_id = ?3 and model.is_delete = false")
	void updateUserCode(String confirmEncryptedPass, String user_code, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_employee WHERE (machine_employee_code = ?1 OR ?1 IS NULL) AND (old_employee_code = ?2 OR ?2 IS NULL) AND company_id = ?3 AND is_active = 1 AND is_delete = 0")
	CEmployeesModel getCheck(String machineEmployeeCode, String oldEmployeeCode, int companyId);
	@Modifying
	@Transactional
	@Query(value = "update cm_employee set is_active = false,is_delete = true, deleted_by=?2, deleted_on = CURRENT_TIMESTAMP() where employee_id=?1", nativeQuery = true)
	void FnDeleteEmployeeRecord(int employeeId, String modifiedBy);
}
