package com.erp.Employee.Employees.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

 import com.erp.Employee.Employees.Model.CEmployeeQualificationModel;
 
 
public interface IEmployeesQualificationRepository extends JpaRepository<CEmployeeQualificationModel, Integer> {
	
	
	@Query(value = "FROM CEmployeeQualificationModel model where model.is_delete = 0 AND model.employee_id = ?1")
	List<CEmployeeQualificationModel> FnShowParticularRecord( int employee_id);

	@Modifying
	@Transactional
	@Query(value = "update CEmployeeQualificationModel model set model.is_delete = 1, model.deleted_by =?3, deleted_on = CURRENT_TIMESTAMP() WHERE model.employee_id = ?1  AND employee_academic_id NOT IN ?2 AND is_delete = 0")
	void updateEmplOldEduDtls(int employee_id, List<Integer> emplEduTrnIds, String deleted_by);
	
	@Modifying
	@Transactional
	@Query(value = "update cm_employees_academic set is_active = false ,deleted_by=?2, deleted_on = CURRENT_TIMESTAMP() where employee_id=?1", nativeQuery = true)
	Object fndeleteEmployeeQualificationDetail(int employee_id, String deleted_by);
 	

}
