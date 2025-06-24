package com.erp.Employee.Employee_Grade.Repository;

import com.erp.Employee.Employee_Grade.Model.CEmployee_GradeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IEmployee_GradeRepository extends JpaRepository<CEmployee_GradeModel, Integer> {

	@Transactional
	@Modifying
	@Query(value = "UPDATE CEmployee_GradeModel model SET model.is_delete = true, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.employee_grade_id = ?1 and model.company_id = ?2")
	void FnDeleteRecord(int employee_grade_id, int company_id, String deleted_by);

	
	@Query(nativeQuery = true, value = "SELECT * FROM cm_employee_grade WHERE (employee_grade_name = ?1 OR (?2 IS NOT NULL AND short_name = ?2)) and company_id = ?3 order by employee_grade_id Desc limit 1")
	CEmployee_GradeModel getCheck(String employee_grade_name, String short_name, Integer company_id);


}
