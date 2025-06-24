package com.erp.HmHrpayrollSettings.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.HmHrpayrollSettings.Model.CHmHrpayrollSettingsModel;

import javax.transaction.Transactional;

public interface IHmHrpayrollSettingsRepository extends JpaRepository<CHmHrpayrollSettingsModel, Integer> {

	@Query(value = "FROM CHmHrpayrollSettingsModel model where model.is_delete = false and model.hrpayroll_settings_id = ?1 and model.company_id = ?2")
	CHmHrpayrollSettingsModel FnShowParticularRecordForUpdate(int hrpayroll_settings_id, int company_id);

	@Query(value = "FROM CHmHrpayrollSettingsModel model where model.is_delete = false and model.company_id = ?1")
	CHmHrpayrollSettingsModel FnGetPayrollSettingByCompanyId(int company_id);

	@Modifying
	@Transactional
	@Query(value = "update CHmHrpayrollSettingsModel model SET model.lock_date = ?1 ,model.lock_status = ?2  WHERE model.company_id = ?3 AND model.hrpayroll_settings_id = ?4")
	void UpdateLockStatus(String lockDate, boolean lockStatus, int companyId, int hrpayrollSettingsId);
}
	