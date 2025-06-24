package com.erp.AmModulesMenu.Repository;

import com.erp.AmModulesMenu.Model.CAmModulesMenuModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IAmModulesMenuRepository extends JpaRepository<CAmModulesMenuModel, Integer> {


	@Query(nativeQuery = true, value = "SELECT * FROM am_modules_menu WHERE modules_menu_name = ?1")
	CAmModulesMenuModel getCheck(String getmodules_menu_name);

	@Query(nativeQuery = true, value = "SELECT * FROM am_modules_menu WHERE modules_menu_name = ?1")
	CAmModulesMenuModel checkIfNameExist(String getmodules_menu_name);


}
