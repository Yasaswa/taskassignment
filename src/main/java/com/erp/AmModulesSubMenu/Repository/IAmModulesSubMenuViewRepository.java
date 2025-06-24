package com.erp.AmModulesSubMenu.Repository;

import com.erp.AmModulesSubMenu.Model.CAmModulesSubMenuViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IAmModulesSubMenuViewRepository extends JpaRepository<CAmModulesSubMenuViewModel, Long> {

	@Query(value = "FROM CAmModulesSubMenuViewModel model where model.modules_sub_menu_id = ?1 and model.company_id = ?2")
	Page<CAmModulesSubMenuViewModel> FnShowParticularRecord(int modules_sub_menu_id, Pageable pageable, int company_id);

	@Query(value = "FROM CAmModulesSubMenuViewModel model where model.company_id = ?1")
	Page<CAmModulesSubMenuViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CAmModulesSubMenuViewModel model where model.is_delete =0 and model.modules_sub_menu_id = ?1 and model.company_id = ?2")
	CAmModulesSubMenuViewModel FnShowParticularRecordForUpdate(int modules_sub_menu_id, int company_id);

	@Query(value = "SELECT * FROM amv_modules_sub_menu_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);


}
