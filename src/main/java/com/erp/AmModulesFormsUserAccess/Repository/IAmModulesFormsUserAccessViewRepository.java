package com.erp.AmModulesFormsUserAccess.Repository;

import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IAmModulesFormsUserAccessViewRepository extends JpaRepository<CAmModulesFormsUserAccessViewModel, Integer> {

	@Query(value = "FROM CAmModulesFormsUserAccessViewModel model where model.is_delete = false and model.user_code = ?1 order by model.modules_forms_user_access_id Desc")
	List<CAmModulesFormsUserAccessViewModel> FnShowAllActiveRecords(String user_code, int company_id);

	@Query(value = "FROM CAmModulesFormsUserAccessViewModel model where model.is_delete =0 and model.modules_forms_user_access_id = ?1 and model.company_id = ?2 order by model.modules_forms_user_access_id Desc")
	Page<CAmModulesFormsUserAccessViewModel> FnShowParticularRecord(int modules_forms_user_access_id, Pageable pageable, int company_id);

//	@Query(value = "FROM CAmModulesFormsUserAccessViewModel model where model.is_delete =0 and model.company_id = ?1 and model.company_branch_id = ?2 and  model.login_name = ?3 and model.login_password = ?4 LIMIT 1")	
//	@Query(nativeQuery = true, value = "SELECT * from amv_modules_forms_user_access model WHERE model.is_delete = 0 and company_id =?1 and company_branch_id=?2 and login_name = ?3 and login_password = ?4 limiT 1")
//    CAmModulesFormsUserAccessViewModel modulesuseraccessIsExist(int company_id, int company_branch_id, String username,String password);


	@Query(nativeQuery = true, value = "SELECT * from amv_modules_forms_user_access model WHERE model.is_delete = 0 and company_id =?1 and company_branch_id=?2 and login_name = ?3 and login_password = ?4 limit 1")
	CAmModulesFormsUserAccessViewModel login(int company_id, int company_branch_id, String username, String password);

	@Query(value = "FROM  CAmModulesFormsUserAccessViewModel model where model.is_delete =0 AND model.user_id = ?1 AND model.company_id = ?2")
	List<CAmModulesFormsUserAccessViewModel> FnShowModulesFormsUserAccessRecords(int transporter_id, int company_id);


	@Query(value = "FROM CAmModulesFormsUserAccessViewModel model where model.is_delete = 0 and model.user_name = ?1 and model.company_id = ?2")
	CAmModulesFormsUserAccessViewModel getUserName(String username, int company_id);

	@Query(value = "FROM CAmModulesFormsUserAccessViewModel model where model.is_delete = false and model.user_code = ?1")
	List<CAmModulesFormsUserAccessViewModel> getAllModuleForms(
//			int company_id,
			String user_code);


}
