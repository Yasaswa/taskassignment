package com.erp.Customer_Contacts.Repository;

import com.erp.Customer_Contacts.Model.CCustomerContactsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ICustomerContactsRepository extends JpaRepository<CCustomerContactsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update cm_customer_contacts set is_delete = 1  , deleted_on = CURRENT_TIMESTAMP() where customer_contact_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int customer_contact_id);

	@Query(nativeQuery = true, value = "SELECT * FROM cm_customer_contacts WHERE customer_contact_id = ?1")
	CCustomerContactsModel getCheck(int customer_contact_id);

	@Query("FROM CCustomerContactsModel  ctcm where  ctcm.is_delete =  0 and ctcm.customer_id = ?1")
	List<CCustomerContactsModel> findBycustomerid(int customer_id);

	@Modifying
	@Transactional
	@Query(value = "update cm_customer_contacts  set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where customer_id=?1 and company_id = ?2", nativeQuery = true)
	void updateCustomerContactActiveStatus(int customer_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update cm_customer_contacts set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where cust_branch_id=?1 and is_delete = 0", nativeQuery = true)
	void FnDeleteCustomerBranchContactsRecords(int cust_branch_id, String deleted_by);

	@Query(nativeQuery = true,value = "Select * from cm_customer_contacts where is_delete = 0 and customer_id = ?1 and company_id = ?2")
	List<CCustomerContactsModel> FnShowCustomerBranchRecords(int customer_id, int company_id);


}
