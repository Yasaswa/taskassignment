package com.erp.FmGeneralLedger.Repository;

import com.erp.FmGeneralLedger.Model.CFmGeneralLedgerViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IFmGeneralLedgerViewRepository extends JpaRepository<CFmGeneralLedgerViewModel, Integer> {

	@Query(value = "FROM CFmGeneralLedgerViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.general_ledger_id Desc")
	Page<CFmGeneralLedgerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CFmGeneralLedgerViewModel model where model.is_delete =0 and model.general_ledger_id = ?1 and model.company_id = ?2 order by model.general_ledger_id Desc")
	Page<CFmGeneralLedgerViewModel> FnShowParticularRecord(int general_ledger_id, Pageable pageable, int company_id);


}
