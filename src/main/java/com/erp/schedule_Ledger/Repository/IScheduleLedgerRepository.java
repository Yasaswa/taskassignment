package com.erp.schedule_Ledger.Repository;

import com.erp.schedule_Ledger.Model.CScheduleLedgerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IScheduleLedgerRepository extends JpaRepository<CScheduleLedgerModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update fm_schedule_ledger set is_delete = 1 , deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where schedule_ledger_id = ?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int schedule_ledger_id, int company_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM fm_schedule_ledger WHERE schedule_ledger_name = ?1 and company_id =?2")
	CScheduleLedgerModel getCheck(String schedule_ledger_name, int company_id);

	@Query(nativeQuery = true, value = "SELECT MAX(report_sr_no) FROM fm_schedule_ledger WHERE report_side = ?1 and report_type = ?2 and company_id =?3")
	int FnShowScheduleLedgerSrNo(String report_side, String report_type, int company_id);

}
