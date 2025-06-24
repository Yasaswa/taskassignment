package com.erp.AmModulesForms.Repository;

import com.erp.AmModulesForms.Model.CAmModulesFormsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;


public interface IAmModulesFormsRepository extends JpaRepository<CAmModulesFormsModel, Integer> {

	@Query(value = "FROM CAmModulesFormsModel model where model.is_delete =0 and model.modules_forms_id = ?1 and model.company_id = ?2")
	CAmModulesFormsModel FnShowParticularRecordForUpdate(int modules_forms_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update am_modules_forms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where designation_id=?1 and company_branch_id = ?2 and company_id = ?3", nativeQuery = true)
	int updateStatus(int designation_id, int company_branch_id, int company_id);


	@Query(nativeQuery = true, value = "SELECT * FROM am_modules_forms WHERE modules_forms_name = ?1")
	CAmModulesFormsModel getCheck(String modules_forms_name);

	@Modifying
	@Transactional
	@Query(value = "update am_modules_forms set is_delete = 1, deleted_on = CURRENT_TIMESTAMP()  where modules_forms_id=?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int modules_forms_id, int company_id);

//	@Modifying
//	@Transactional
//	@Query(value = "update am_modules_forms set is_delete = 1, deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP()  where modules_forms_id=?1 and company_id = ?2", nativeQuery = true)	
//	Object FnDeleteRecord(int modules_forms_id, int company_id, String deleted_by);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO am_modules_forms_user_access " +
			"(company_id, company_branch_id, user_type, user_id, modules_forms_id, module_id, sub_module_id, " +
			"all_access, read_access, add_access, modify_access, delete_access, approve_access, special_access, " +
			"is_active, is_delete, created_by, created_on, modified_by, modified_on, deleted_by, deleted_on, " +
			"access_control, user_code, company_access) " +
			"VALUES (:companyId, :companyBranchId, :userType, :userId, :modulesFormsId, :moduleId, :subModuleId, " +
			":allAccess, :readAccess, :addAccess, :modifyAccess, :deleteAccess, :approveAccess, :specialAccess, " +
			"1, 0, :createdBy, CURRENT_TIMESTAMP(), NULL, CURRENT_TIMESTAMP(), NULL, NULL, " +
			":accessControl, :userCode, :companyAccess)",
			nativeQuery = true)
	void insertUserAccess(@Param("companyId") int companyId,
						  @Param("companyBranchId") int companyBranchId,
						  @Param("userType") String userType,
						  @Param("userId") String userId,
						  @Param("modulesFormsId") int modulesFormsId,
						  @Param("moduleId") int moduleId,
						  @Param("subModuleId") int subModuleId,
						  @Param("allAccess") int allAccess,
						  @Param("readAccess") int readAccess,
						  @Param("addAccess") int addAccess,
						  @Param("modifyAccess") int modifyAccess,
						  @Param("deleteAccess") int deleteAccess,
						  @Param("approveAccess") int approveAccess,
						  @Param("specialAccess") int specialAccess,
						  @Param("createdBy") String createdBy,
						  @Param("accessControl") String accessControl,
						  @Param("userCode") String userCode,
						  @Param("companyAccess") String companyAccess);

}
