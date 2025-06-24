package com.erp.Transporter.Repository;

import com.erp.Transporter.Model.CTransporterBanksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ITransporterBanksRepository extends JpaRepository<CTransporterBanksModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_transporter_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where transporter_bank_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int transporter_bank_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_transporter_banks WHERE transporter_bank_account_type = ?1")
	CTransporterBanksModel getCheck(String transporter_bank_account_type);

	@Modifying
	@Transactional
	@Query(value = "update cm_transporter_banks set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where transporter_id=?1 and company_id = ?2", nativeQuery = true)
	Object updateTransporterBankActiveStatus(int transporter_id, int company_id);

	@Query(value = "FROM  CTransporterBanksModel model where model.is_delete =0 AND model.transporter_id = ?1 AND model.company_id = ?2")
	List<CTransporterBanksModel> FnShowParticularTransporterBankRecord(int transporter_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_transporter_banks set is_delete = 1, deleted_by = ?2 ,deleted_on = CURRENT_TIMESTAMP() where transporter_id=?1", nativeQuery = true)
	Object deletTransporterBankRecords(int transporter_id, String deleted_by);

}
