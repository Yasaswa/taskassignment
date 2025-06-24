package com.erp.AmCompanySettings.Repository;

import com.erp.AmCompanySettings.Model.CAmCompanySettingsModel;
import com.erp.AmCompanySettings.Model.CAmCompanySettingsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IAmCompanySettingsRepository extends JpaRepository<CAmCompanySettingsModel, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM am_company_settings WHERE auto_rm_materail_name_flag = ?1")
	CAmCompanySettingsModel checkIfNameExist(String auto_rm_materail_name_flag);

	@Modifying
	@Transactional
	@Query(value = "update am_company_settings set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where company_id = ?1", nativeQuery = true)
	int updateStatus(int company_id);

	@Query(value = "FROM CAmCompanySettingsModel model where model.is_delete =0 and model.company_id = ?1")
	CAmCompanySettingsModel FnGetCompanySettings(int company_id);
}
