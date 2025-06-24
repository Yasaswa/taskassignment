package com.erp.Transporter.Repository;

import com.erp.Transporter.Model.CTransporterBanksViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITransporterBanksViewRepository extends JpaRepository<CTransporterBanksViewModel, Long> {

	@Query(value = "Select * FROM  cmv_transporter_banks order by transporter_bank_id Desc", nativeQuery = true)
	Page<CTransporterBanksViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_transporter_banks where is_delete =0  order by transporter_bank_id Desc", nativeQuery = true)
	Page<CTransporterBanksViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_transporter_banks where is_delete =0 and transporter_bank_id = ?1", nativeQuery = true)
	CTransporterBanksViewModel FnShowParticularRecordForUpdate(int transporter_bank_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_transporter_banks where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and transporter_bank_id = ?3")
	CTransporterBanksViewModel FnShowParticularRecord(int company_id, int company_branch_id, int transporter_bank_id);

	@Query(value = "FROM CTransporterBanksViewModel ctbvm where ctbvm.company_id =?1 and ctbvm.company_branch_id =?2")
	List<CTransporterBanksViewModel> checkRecordIfExist(int company_id, int company_branch_id);

	@Query(value = "FROM CTransporterBanksViewModel ctbvm where ctbvm.is_delete =0  and ctbvm.company_id = ?1 and ctbvm.company_branch_id = ?2 and ctbvm.transporter_id =?3")
	List<CTransporterBanksViewModel> FnShowParticularRecordForUpdate(int company_id, int company_branch_id,
	                                                                 int transporter_id);
}
