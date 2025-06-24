package com.erp.Sl_Gl_Mapping.Service;

import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingModel;
import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISl_Gl_MappingService {

	JSONObject FnAddUpdateRecord(CSl_Gl_MappingModel cSl_Gl_MappingModel);

	Object FnDeleteRecord(int sl_gl_mapping_id);

	Object FnShowAllRecords(Pageable pageable);

	Object FnShowAllActiveRecords(Pageable pageable);

	JSONObject FnShowParticularRecordForUpdate(int sl_gl_mapping_id);

	Object FnShowAllReportRecords(Pageable pageable);

	Page<CSl_Gl_MappingViewModel> FnShowParticularRecord(int company_id, int schedule_ledger_id, Pageable pageable);

}
