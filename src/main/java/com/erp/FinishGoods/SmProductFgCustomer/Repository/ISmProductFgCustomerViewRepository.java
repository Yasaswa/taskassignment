package com.erp.FinishGoods.SmProductFgCustomer.Repository;

import com.erp.FinishGoods.SmProductFgCustomer.Model.CSmProductFgCustomerViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISmProductFgCustomerViewRepository extends JpaRepository<CSmProductFgCustomerViewModel, Integer> {

	@Query(value = "FROM CSmProductFgCustomerViewModel model where model.company_id = ?1")
	Page<CSmProductFgCustomerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductFgCustomerViewModel model where model.company_id = ?1 and model.customer_id = ?2")
	Page<CSmProductFgCustomerViewModel> FnShowAllActiveRecordsCustomersFG(Pageable pageable, int company_id,
	                                                                      int customer_id);

}
