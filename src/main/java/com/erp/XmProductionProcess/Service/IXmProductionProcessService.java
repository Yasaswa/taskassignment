package com.erp.XmProductionProcess.Service;

import com.erp.XmProductionProcess.Model.CXmProductionProcessModel;
import com.erp.XmProductionProcess.Model.CXmProductionProcessViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IXmProductionProcessService {

	Map<String, Object> FnAddUpdateRecord(CXmProductionProcessModel cXmProductionProcessModel);

	Object FnDeleteRecord(int production_process_id, int company_id, String deleted_by);

	Page<CXmProductionProcessViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int production_process_id, int company_id);

	Page<CXmProductionProcessViewModel> FnShowParticularRecord(int production_process_id, Pageable pageable, int company_id);

}
