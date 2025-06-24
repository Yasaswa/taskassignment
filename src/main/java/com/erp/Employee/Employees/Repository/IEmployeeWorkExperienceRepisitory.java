package com.erp.Employee.Employees.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.erp.Employee.Employees.Model.CEmployeeExperienceModel;
import com.erp.Employee.Employees.Model.CEmployeeQualificationModel;

public interface IEmployeeWorkExperienceRepisitory extends JpaRepository<CEmployeeExperienceModel, Integer> {
	
	@Query(value = "FROM CEmployeeExperienceModel model where model.is_delete = 0 AND model.employee_id = ?1")
	List<CEmployeeExperienceModel> FnShowEmployeeExperienceRecord( int employee_id);
	
	
	@Modifying
	@Transactional
	@Query(value = "update cm_employee_work_experience set is_active = false, deleted_by=?2, deleted_on = CURRENT_TIMESTAMP() where employee_id=?1", nativeQuery = true)
	Object fndeleteEmployeeRecord(int employee_id, String deleted_by);
	
}
