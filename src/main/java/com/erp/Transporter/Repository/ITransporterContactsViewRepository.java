package com.erp.Transporter.Repository;

import com.erp.Transporter.Model.CTransporterContactsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITransporterContactsViewRepository extends JpaRepository<CTransporterContactsViewModel, Long> {


	@Query(value = "Select * FROM  cmv_transporter_contacts order by transporter_contact_id Desc", nativeQuery = true)
	Page<CTransporterContactsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_transporter_contacts where is_delete =0  order by transporter_contact_id Desc", nativeQuery = true)
	Page<CTransporterContactsViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_transporter_contacts where is_delete =0 and transporter_id = ?1", nativeQuery = true)
	List<CTransporterContactsViewModel> FnShowParticularRecordForUpdate(int transporter_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_transporter_contacts where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and transporter_contact_id = ?3")
	CTransporterContactsViewModel FnShowParticularRecord(int company_id, int company_branch_id, int transporter_contact_id);

}
