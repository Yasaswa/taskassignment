package com.erp.SmProductSrCustomer.Repository;

import com.erp.SmProductSrCustomer.Model.CSmProductSrCustomerViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISmProductSrCustomerViewRepository extends JpaRepository<CSmProductSrCustomerViewModel, Integer> {

	@Query(value = "FROM CSmProductSrCustomerViewModel model where model.company_id = ?1")
	Page<CSmProductSrCustomerViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrCustomerViewModel model where model.product_sr_id = ?1 and model.company_id = ?2")
	Page<CSmProductSrCustomerViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrCustomerViewModel model where model.product_sr_id = ?1 and model.company_id = ?2")
	List<CSmProductSrCustomerViewModel> FnShowProductServiceCustomerRecords(int product_sr_id, int company_id);

	@Query(value = "FROM CSmProductSrCustomerViewModel model where model.company_id = ?1")
	List<CSmProductSrCustomerViewModel> FnShowProductSrCustomerActiveRecords(int company_id);


}
