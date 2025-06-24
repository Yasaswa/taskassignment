package com.erp.AmModules.Repository;

import com.erp.AmModules.Model.CAmModulesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IAmModulesRepository extends JpaRepository<CAmModulesModel, Integer> {

	@Query(value = "FROM CAmModulesModel model where model.is_delete =0 and model.modules_id = ?1 and model.company_id = ?2")
	CAmModulesModel FnShowParticularRecordForUpdate(int modules_id, int company_id);


	//@Query(value = "FROM CAmModulesModel cppm where cppm.is_delete =0 and cppm.module_name = ?1")

	@Query(nativeQuery = true, value = "SELECT * FROM am_modules WHERE module_name = ?1")
	CAmModulesModel checkIfNameExist(String module_name);


}
