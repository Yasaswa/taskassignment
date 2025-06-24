package com.erp.schedule_Ledger.Service;

import com.erp.schedule_Ledger.Model.CScheduleLedgerModel;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IScheduleLedgerService {

	Map<String, Object> FnAddUpdateRecord(CScheduleLedgerModel cScheduleLedgerModel);

	Object FnDeleteRecord(int schedule_ledger_id, int company_id, String deleted_by);

	Map<String, Object> FnShowAllRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int schedule_ledger_id, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecord(int schedule_ledger_id, int company_id);

	Map<String, Object> FnShowScheduleLedgerSrNo(String report_side, String report_type, int company_id);

}
