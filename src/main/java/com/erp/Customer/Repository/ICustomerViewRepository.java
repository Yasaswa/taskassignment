package com.erp.Customer.Repository;

import com.erp.Customer.Model.CCustomerViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICustomerViewRepository extends JpaRepository<CCustomerViewModel, Long> {


	@Query(value = "Select * FROM cmv_customer order by customer_id Desc", nativeQuery = true)
	Page<CCustomerViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_customer where is_delete =0  order by customer_id Desc", nativeQuery = true)
	Page<CCustomerViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM cmv_customer where is_delete =0 and customer_id = ?1 and company_id = ?2", nativeQuery = true)
	CCustomerViewModel FnShowParticularRecordForUpdate(int customer_id, int company_id);

	@Query(nativeQuery = true, value = "Select * FROM cmv_customer where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and customer_id = ?3")
	CCustomerViewModel FnShowParticularRecord(int company_id, int company_branch_id, int customer_id);

	@Query(value = "Select * FROM cmv_customer where is_delete =0 and company_id=?1 order by customer_id Desc", nativeQuery = true)
	List<CCustomerViewModel> FnShowProductSrCustomerActiveRecords(int company_id);

	@Query(value = "FROM CCustomerViewModel model where model.is_delete =0 and model.company_id = ?1")
	List<CCustomerViewModel> FnShowAllCustomerRecords(int company_id);
}
