package com.erp.AmModulesFormsUserAccess.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.erp.AmModulesFormsUserAccess.Model.CUserViewModel;

public interface IUserViewModelRepository extends JpaRepository<CUserViewModel, Integer> {

//	@Query(value = "FROM CUserViewModel model where model.username = ?1 and model.company_id = ?2 and model.user_code != ?3")
//	// Commented Because UserName is unique for all companies.(as per prashant sir told on 08-07-2024)
//	@Query(value = "FROM CUserViewModel model WHERE ?1 IS NOT NULL AND model.username = ?1 " +
//			"and model.company_id = ?2 " +
//			"and model.user_code != ?3 ")
//	CUserViewModel getUserName(String username, int company_id, String user_code);

	@Query(value = "FROM CUserViewModel model WHERE ?1 IS NOT NULL AND model.username = ?1 " +
			"and model.user_code != ?2 ")
	CUserViewModel getUserName(String username, String user_code);

}
