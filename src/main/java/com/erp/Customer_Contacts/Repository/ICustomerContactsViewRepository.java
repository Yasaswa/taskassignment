package com.erp.Customer_Contacts.Repository;

import com.erp.Customer_Contacts.Model.CCustomerContactsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerContactsViewRepository extends JpaRepository<CCustomerContactsViewModel, Long> {

	@Query(value = "Select * FROM cmv_customer_contacts order by customer_contact_id Desc", nativeQuery = true)
	Page<CCustomerContactsViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_customer_contacts where is_delete =0  order by customer_contact_id Desc", nativeQuery = true)
	Page<CCustomerContactsViewModel> FnShowAllActiveRecords(Pageable pageable);

//	@Query(value = "Select * FROM cmv_customer_contacts where is_delete =0 and customer_contact_id = ?1", nativeQuery = true)
//	CCustomerContactsViewModel FnShowParticularRecordForUpdate(int customer_contact_id);


	@Query(value = "FROM CCustomerContactsViewModel custc where custc.is_delete =0 and custc.customer_id = ?1 and custc.company_id = ?2")
	List<CCustomerContactsViewModel> FnShowParticularRecords(int customer_id, int company_id);


	@Query("from CCustomerContactsViewModel ccvm where ccvm.is_delete =0 and ccvm.customer_id = ?1")
	List<CCustomerContactsViewModel> FnShowParticularRecordForUpdate(int customer_id);

}
