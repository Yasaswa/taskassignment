package com.erp.AmModulesSubMenu.Repository;

import com.erp.AmModulesSubMenu.Model.CAmModulesSubMenuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IAmModulesSubMenuRepository extends JpaRepository<CAmModulesSubMenuModel, Integer> {

	@Query(value = "FROM CAmModulesSubMenuModel model where model.is_delete =0 and model.modules_sub_menu_id = ?1 and model.company_id = ?2")
	CAmModulesSubMenuModel FnShowParticularRecordForUpdate(int modules_sub_menu_id, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM am_modules_sub_menu WHERE modules_sub_menu_name = ?1")
	CAmModulesSubMenuModel checkIfNameExist(String modules_sub_menu_name);


}
