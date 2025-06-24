package com.erp.Bank_Contacts.Repository;

import com.erp.Bank_Contacts.Model.CBankContactsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface IBankContactsRepository extends JpaRepository<CBankContactsModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update cm_banks_contacts set is_delete = 1 , deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where bank_contact_id = ?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int bank_contact_id, int company_id, String deleted_by);


	@Modifying
	@Transactional
	@Query(value = "update cm_banks_contacts set is_delete = 1 where bank_contact_id=?1 and company_id = ?2", nativeQuery = true)
	void FnDeleteRecord(int bank_contact_id, Integer company_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_banks_contacts WHERE bank_contact_person = ?1")
	CBankContactsModel getCheck(String bank_contact_person);

	@Query("FROM CBankContactsModel  cbcm where  cbcm.is_delete =  0 and cbcm.bank_id = ?1 ")
	List<CBankContactsModel> findByBankId(int bank_id);


	@Modifying
	@Transactional
	@Query(value = "update cm_banks_contacts set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where bank_id=?1 and company_id = ?2", nativeQuery = true)
	Object updateBankContactActiveStatus(int bank_id, int company_id);

}
