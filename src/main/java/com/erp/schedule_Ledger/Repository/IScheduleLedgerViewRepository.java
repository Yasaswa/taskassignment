package com.erp.schedule_Ledger.Repository;

import com.erp.schedule_Ledger.Model.CScheduleLedgerViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IScheduleLedgerViewRepository extends JpaRepository<CScheduleLedgerViewModel, Long> {

	@Query(value = "Select * FROM  fmv_schedule_ledger where company_id =?1 order by schedule_ledger_id Desc", nativeQuery = true)
	Page<CScheduleLedgerViewModel> FnShowAllRecords(Pageable pageable, int company_id);

	@Query(value = "Select * FROM  fmv_schedule_ledger where is_delete =0 and company_id = ?1 order by schedule_ledger_id Desc", nativeQuery = true)
	Page<CScheduleLedgerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "Select * FROM  fmv_schedule_ledger where is_delete =0 and schedule_ledger_id = ?1 and company_id = ?2", nativeQuery = true)
	CScheduleLedgerViewModel FnShowParticularRecordForUpdate(int schedule_ledger_id, int company_id);

	@Query(nativeQuery = true, value = "Select * FROM  fmv_schedule_ledger where is_delete =0 and schedule_ledger_id = ?1 and company_id = ?2")
	CScheduleLedgerViewModel FnShowParticularRecord(int schedule_ledger_id, int company_id);

	@Query(value = "SELECT * FROM fmv_schedule_ledger_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);
}
