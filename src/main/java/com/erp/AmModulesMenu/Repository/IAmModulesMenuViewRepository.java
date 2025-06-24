package com.erp.AmModulesMenu.Repository;

import com.erp.AmModulesMenu.Model.CAmModulesMenuViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IAmModulesMenuViewRepository extends JpaRepository<CAmModulesMenuViewModel, Long> {

	@Query(value = "FROM CAmModulesMenuViewModel model where model.modules_menu_id = ?1 and model.company_id = ?2")
	Page<CAmModulesMenuViewModel> FnShowParticularRecord(int modules_menu_id, Pageable pageable, int company_id);

	@Query(value = "FROM CAmModulesMenuViewModel model where model.company_id = ?1")
	Page<CAmModulesMenuViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CAmModulesMenuViewModel model where model.is_delete =0 and model.modules_menu_id = ?1 and model.company_id = ?2")
	CAmModulesMenuViewModel FnShowParticularRecordForUpdate(int modules_menu_id, int company_id);

	@Query(value = "SELECT * FROM amv_modules_menu_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
