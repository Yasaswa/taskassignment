package com.erp.AmCompanySettings.Service;

import com.erp.AmCompanySettings.Model.CAmCompanySettingsModel;
import com.erp.AmCompanySettings.Model.CAmCompanySettingsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IAmCompanySettingsService {

	Map<String, Object> FnAddUpdateRecord(CAmCompanySettingsModel cAmCompanySettingsModel);

	Object FnDeleteRecord(int am_company_settings_id, int company_id);

//	Object FnDeleteRecord(int am_company_settings_id, int company_id , String deleted_by);

	Page<CAmCompanySettingsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	//Page<CAmCompanySettingsRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int am_company_settings_id, int company_id);

	Page<CAmCompanySettingsViewModel> FnShowParticularRecord(int am_company_settings_id, Pageable pageable, int company_id);


}
