package com.erp.AmModulesFormsUserAccess.Repository;

import com.erp.AmModulesFormsUserAccess.Model.CAmModulesFormsUserAccessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface IAmModulesFormsUserAccessRepository extends JpaRepository<CAmModulesFormsUserAccessModel, Integer> {

	@Query(value = "FROM CAmModulesFormsUserAccessModel model where model.is_delete =0 and model.modules_forms_user_access_id = ?1 and model.company_id = ?2")
	CAmModulesFormsUserAccessModel FnShowParticularRecordForUpdate(int modules_forms_user_access_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update am_modules_forms_user_access set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where user_code = ?1 " +
			"and company_id = ?2 and is_delete = 0", nativeQuery = true)
	int updateStatus(String user_code, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update am_modules_forms_user_access set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where user_code = ?1 " +
			"and modules_forms_id NOT IN (?2) and company_id = ?3 and is_delete = 0", nativeQuery = true)
	int updateStatusByModuleForms(String user_code, List<Integer> getDistinctModuleForms, int company_id);



}
