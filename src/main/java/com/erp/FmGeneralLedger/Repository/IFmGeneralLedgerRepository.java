package com.erp.FmGeneralLedger.Repository;

import com.erp.FmGeneralLedger.Model.CFmGeneralLedgerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface IFmGeneralLedgerRepository extends JpaRepository<CFmGeneralLedgerModel, Integer> {

	@Query(value = "FROM CFmGeneralLedgerModel model where model.is_delete =0 and model.general_ledger_id = ?1 and model.company_id = ?2")
	CFmGeneralLedgerModel FnShowParticularRecordForUpdate(int general_ledger_id, int company_id);

	@Query(value = "FROM CFmGeneralLedgerModel model where model.is_delete =0 and model.general_ledger_name = ?1 and model.company_id = ?2")
	CFmGeneralLedgerModel checkIfNameExist(String general_ledger_name, int company_id);

	@Query(value = "FROM CFmGeneralLedgerModel model where model.is_delete =0 and model.general_ledger_name = ?1 and model.company_id = ?2")
	CFmGeneralLedgerModel checkIfGLedgerExist(String general_ledger_name, int company_id);


	@Query(value = "Select * FROM fmv_general_ledger_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);
}
