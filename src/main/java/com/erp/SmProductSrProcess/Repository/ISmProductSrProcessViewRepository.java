package com.erp.SmProductSrProcess.Repository;

import com.erp.SmProductSrProcess.Model.CSmProductSrProcessViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISmProductSrProcessViewRepository extends JpaRepository<CSmProductSrProcessViewModel, Integer> {

	@Query(value = "FROM CSmProductSrProcessViewModel model where  model.company_id = ?1")
	Page<CSmProductSrProcessViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrProcessViewModel model where  model.product_sr_id = ?1 and model.company_id = ?2")
	Page<CSmProductSrProcessViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrProcessViewModel model where model.product_sr_id = ?1 and model.company_id = ?2")
	List<CSmProductSrProcessViewModel> FnShowProductServiceProcessRecords(int product_sr_id, int company_id);

	@Query(value = "FROM CSmProductSrProcessViewModel model where  model.company_id = ?1")
	List<CSmProductSrProcessViewModel> FnShowProductSrProcessActiveRecords(int company_id);


}
