package com.erp.Employee.Employee_Grade.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessModel;
import com.erp.Employee.Employee_Grade.Model.CAmModulesFormsGradeAccessViewModel;

public interface IAmModulesFormsGradeAccessViewRepository extends JpaRepository<CAmModulesFormsGradeAccessViewModel, Integer>{


	@Query(value = "FROM CAmModulesFormsGradeAccessViewModel model where model.is_delete = 0 and model.modules_forms_grade_access_id = ?1 and model.company_id = ?2")
	CAmModulesFormsGradeAccessViewModel FnShowParticularRecordForUpdate(int modules_forms_grade_access_id,
			int company_id);

	
	@Query(value = "FROM CAmModulesFormsGradeAccessViewModel model where model.is_delete = false and model.grade_id = ?1 and model.company_id = ?2")
	List<CAmModulesFormsGradeAccessViewModel> FnShowAllActiveRecords(int grade_id, int company_id);


}
