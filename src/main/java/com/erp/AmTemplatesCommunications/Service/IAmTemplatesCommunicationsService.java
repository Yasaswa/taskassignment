package com.erp.AmTemplatesCommunications.Service;

import com.erp.AmTemplatesCommunications.Model.CAmTemplatesCommunicationsModel;
import com.erp.AmTemplatesCommunications.Model.CAmTemplatesCommunicationsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface IAmTemplatesCommunicationsService {

	Map<String, Object> FnAddUpdateRecord(CAmTemplatesCommunicationsModel cAmTemplatesCommunicationsModel);

	Object FnDeleteRecord(int communications_templates_id, int company_id, String deleted_by);

	Page<CAmTemplatesCommunicationsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

//	Page<CAmTemplatesCommunicationsRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int communications_templates_id, int company_id);

	Page<CAmTemplatesCommunicationsViewModel> FnShowParticularRecord(int communications_templates_id, Pageable pageable, int company_id);


}
