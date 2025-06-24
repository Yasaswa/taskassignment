package com.erp.SmProductSr.Repository;

import com.erp.SmProductSr.Model.CSmProductSrViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ISmProductSrViewRepository extends JpaRepository<CSmProductSrViewModel, Integer> {

	@Query(value = "FROM CSmProductSrViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.product_sr_id Desc")
	Page<CSmProductSrViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrViewModel model where model.is_delete =0 and model.product_sr_id = ?1 and model.company_id = ?2 order by model.product_sr_id Desc")
	Page<CSmProductSrViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrViewModel model where model.is_delete = 0 and model.product_sr_id = ?1 and model.company_id = ?2")
	CSmProductSrViewModel FnShowProductServiceMasterRecords(int product_sr_id, int company_id);

	@Query(value = "Select * From smv_product_sr model where is_delete = 0 and company_id = ?1 order by product_sr_id Desc", nativeQuery = true)
	CSmProductSrViewModel FnShowProductServiceActiveRecords(int company_id);


}
