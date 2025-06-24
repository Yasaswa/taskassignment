package com.erp.XmProductionStoppageReasons.Service;

import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsModel;
import com.erp.XmProductionStoppageReasons.Model.CXmProductionStoppageReasonsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IXmProductionStoppageReasonsService {

	Map<String, Object> FnAddUpdateRecord(CXmProductionStoppageReasonsModel cXmProductionStoppageReasonsModel);

	Object FnDeleteRecord(int production_stoppage_reasons_id, int company_id, String deleted_by);

	Page<CXmProductionStoppageReasonsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int production_stoppage_reasons_id, int company_id);

	Page<CXmProductionStoppageReasonsViewModel> FnShowParticularRecord(int production_stoppage_reasons_id, Pageable pageable, int company_id);

	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);

//	Page<CXmProductionStoppageReasonsRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

}
