package com.erp.FmGeneralLedger.Service;

import com.erp.FmGeneralLedger.Model.CFmGeneralLedgerViewModel;
import com.erp.Sl_Gl_Mapping.Model.CSl_Gl_MappingModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IFmGeneralLedgerService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int general_ledger_id, int company_id, String deleted_by);

	Page<CFmGeneralLedgerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int general_ledger_id, int company_id);

	Page<CSl_Gl_MappingModel> FnShowParticularRecord(int general_ledger_id, Pageable pageable, int company_id);

}
