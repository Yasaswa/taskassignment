package com.erp.Transporter.Repository;

import com.erp.Transporter.Model.CTransporterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ITransporterRepository extends JpaRepository<CTransporterModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_transporter set is_delete = 1, deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where transporter_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int transporter_id, String deleted_by);

//	@Query(nativeQuery = true, value = "SELECT * FROM cm_transporter WHERE (transporter_name = ?1 or transporter_short_name = ?2) AND company_id = ?3")
//	CTransporterModel getCheck(String transporter_name, String transporter_short_name, int company_id);

	@Query(value = "FROM  CTransporterModel ctm where ctm.is_delete =0 and ctm.transporter_id = ?1")
	CTransporterModel FnShowParticularRecordForUpdate(int transporter_id);

	@Query(value = "FROM  CTransporterModel model where model.is_delete =0 AND model.transporter_id = ?1 AND model.company_id = ?2")
	CTransporterModel FnShowParticularTransporterRecord(int transporter_id, int company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_transporter WHERE (transporter_name = ?1 OR (?2 IS NOT NULL AND transporter_short_name = ?2)) and company_id = ?3 order by transporter_id Desc limit 1")
	CTransporterModel getCheck(String transporter_name, String transporter_short_name, int company_id);

}
