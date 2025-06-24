package com.erp.SmProductSrParameters.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductSrParameters.Model.CSmProductSrParametersViewModel;


public interface ISmProductSrParametersViewRepository extends JpaRepository<CSmProductSrParametersViewModel, Integer> {

	
	
	
	@Query(value = "FROM CSmProductSrParametersViewModel model where model.is_delete = 0 and model.product_type_id = ?1 and model.company_id = ?2")
	CSmProductSrParametersViewModel FnShowParticularRecordForUpdate(int product_type_id, int company_id);

//	@Query(value = "FROM CSmProductSrParametersViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.company_id Desc")
//	Page<CSmProductSrParametersViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

//	@Query(value = "FROM CSmProductSrParametersViewModel model where model.is_delete =0 and model.company_id = ?1 and model.company_id = ?2 order by model.company_id Desc")
//	Page<CSmProductSrParametersViewModel> FnShowParticularRecord(int company_id, Pageable pageable, int company_id);
//	

}
