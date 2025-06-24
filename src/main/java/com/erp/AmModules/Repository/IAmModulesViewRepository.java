package com.erp.AmModules.Repository;

import com.erp.AmModules.Model.CAmModulesViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface IAmModulesViewRepository extends JpaRepository<CAmModulesViewModel, Long> {

	@Query(value = "FROM CAmModulesViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.modules_id Desc")
	Page<CAmModulesViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


	@Query(value = "FROM CAmModulesViewModel model where model.is_delete =0 and model.modules_id = ?1 and model.company_id = ?2 order by model.modules_id Desc")
	Page<CAmModulesViewModel> FnShowParticularRecord(int modules_id, Pageable pageable, int company_id);

	@Query(value = "SELECT * FROM amv_modules_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
