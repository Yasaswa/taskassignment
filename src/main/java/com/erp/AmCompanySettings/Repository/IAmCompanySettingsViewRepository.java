package com.erp.AmCompanySettings.Repository;

import com.erp.AmCompanySettings.Model.CAmCompanySettingsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAmCompanySettingsViewRepository extends JpaRepository<CAmCompanySettingsViewModel, Long> {

	@Query(value = "FROM CAmCompanySettingsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.am_company_settings_id Desc")
	Page<CAmCompanySettingsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CAmCompanySettingsViewModel model where model.is_delete =0 and model.am_company_settings_id = ?1 and model.company_id = ?2 order by model.am_company_settings_id Desc")
	Page<CAmCompanySettingsViewModel> FnShowParticularRecord(int am_company_settings_id, Pageable pageable, int company_id);

	@Query(value = "FROM CAmCompanySettingsViewModel model where model.is_delete =0 and model.am_company_settings_id = ?1 and model.company_id = ?2 order by model.am_company_settings_id Desc")
	CAmCompanySettingsViewModel FnShowParticularRecordForUpdate(int am_company_settings_id, int company_id);

}
