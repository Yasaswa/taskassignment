package com.erp.AmModulesFormsDesignationAccess.Repository;

import com.erp.AmModulesFormsDesignationAccess.Model.CAmModulesFormsDesignationAccessModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IAmModulesFormsDesignationAccessRepository extends JpaRepository<CAmModulesFormsDesignationAccessModel, Integer> {

	@Query(value = "FROM CAmModulesFormsDesignationAccessModel model where model.is_delete =0 and model.modules_forms_designation_access_id = ?1 and model.company_id = ?2")
	CAmModulesFormsDesignationAccessModel FnShowParticularRecordForUpdate(int modules_forms_designation_access_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update am_modules_forms_designation_access set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where designation_id=?1 and company_branch_id = ?2 and company_id = ?3", nativeQuery = true)
	int updateStatus(int designation_id, int company_branch_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update am_modules_forms_designation_access set is_delete = 1 ,deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where designation_id = ?1", nativeQuery = true)
	void FnDeleteAmModulesFormsDesignationAccessDetails(int designation_id, String deleted_by);

}
