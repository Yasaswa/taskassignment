package com.erp.AmModulesForms.Repository;

import com.erp.AmModulesForms.Model.CAmModulesFormsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IAmModulesFormsViewRepository extends JpaRepository<CAmModulesFormsViewModel, Integer> {

	@Query(value = "FROM CAmModulesFormsViewModel model where model.is_delete = 0")
	List<CAmModulesFormsViewModel> FnShowAllActiveRecords(int company_id);
//	ORDER BY model.menu_type, model.modules_sub_menu_name

	@Query(value = "FROM CAmModulesFormsViewModel model where model.is_delete =0 and model.modules_forms_id = ?1 and model.company_id = ?2")
	CAmModulesFormsViewModel FnShowParticularRecord(int modules_forms_id, int company_id);


	@Query(value = "SELECT * FROM amv_modules_forms_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);


}
