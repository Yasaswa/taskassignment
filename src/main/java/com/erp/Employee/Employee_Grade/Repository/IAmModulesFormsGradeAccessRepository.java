package com.erp.Employee.Employee_Grade.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessModel;

public interface IAmModulesFormsGradeAccessRepository extends JpaRepository<CAmModulesFormsGradeAccessModel, Integer>{
	
	@Modifying
	@Transactional
	@Query(value = "Update CAmModulesFormsGradeAccessModel model SET model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.grade_id = ?1 and model.company_branch_id = ?2 and model.company_id = ?3")
	int updateGradeAccess(int grade_id, int company_branch_id, int company_id);

	
	@Transactional
	@Modifying
	@Query(value = "UPDATE CAmModulesFormsGradeAccessModel model SET model.is_delete = true, model.deleted_by = ?3, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.modules_forms_grade_access_id  = ?1 and model.company_id = ?2")
	void FnDeleteRecords(int modules_forms_grade_access_id, int company_id, String deleted_by);

}
