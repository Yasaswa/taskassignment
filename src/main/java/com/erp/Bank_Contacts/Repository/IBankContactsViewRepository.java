package com.erp.Bank_Contacts.Repository;

import com.erp.Bank_Contacts.Model.CBankContactsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBankContactsViewRepository extends JpaRepository<CBankContactsViewModel, Long> {


	@Query(value = "Select * FROM cmv_banks_contacts order by bank_contact_id Desc", nativeQuery = true)
	Page<CBankContactsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_banks_contacts where is_delete =0  order by bank_contact_id Desc", nativeQuery = true)
	Page<CBankContactsViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_banks_contacts where is_delete =0 and bank_id = ?1 and company_id = ?2", nativeQuery = true)
	List<CBankContactsViewModel> FnShowParticularRecordForUpdate(int bank_id, int company_id);

	@Query(nativeQuery = true, value = "Select * FROM cmv_banks_contacts where is_delete =0 and company_id = ?1 and bank_contact_id = ?2")
	CBankContactsViewModel FnShowParticularRecord(int company_id, int bank_contact_id);

	@Query(value = "Select * FROM cmv_banks_contacts where is_delete =0 and bank_id = ?1 and company_id = ?2", nativeQuery = true)
	List<CBankContactsViewModel> FnShowBankContactsRecords(int bank_id, int company_id);

}
