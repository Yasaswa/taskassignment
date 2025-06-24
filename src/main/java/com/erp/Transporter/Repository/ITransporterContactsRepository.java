package com.erp.Transporter.Repository;

import com.erp.Transporter.Model.CTransporterContactsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ITransporterContactsRepository extends JpaRepository<CTransporterContactsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_transporter_contacts set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where transporter_contact_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int transporter_contact_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_transporter_contacts WHERE transporter_contact_person = ?1")
	CTransporterContactsModel getCheck(String transporter_contact_person);

	@Query("FROM CTransporterContactsModel  ctcm where  ctcm.is_delete =  0 and ctcm.transporter_id = ?1 ")
	List<CTransporterContactsModel> findByTransporterId(int transporter_id);


	@Modifying
	@Transactional
	@Query(value = "update cm_transporter_contacts set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where transporter_id=?1 and company_id =?2", nativeQuery = true)
	Object updateTransporterContactActiveStatus(int transporter_id, int company_id);

	@Query(value = "FROM  CTransporterContactsModel model where model.is_delete =0 AND model.transporter_id = ?1 AND model.company_id = ?2")
	List<CTransporterContactsModel> FnShowParticularTransporterContactRecord(int transporter_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_transporter_contacts set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where transporter_id=?1", nativeQuery = true)
	Object deletTransporterContactRecords(int transporter_id, String deleted_by);
}
