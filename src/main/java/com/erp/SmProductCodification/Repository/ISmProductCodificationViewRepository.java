package com.erp.SmProductCodification.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductCodification.Model.CSmProductCodificationViewModel;


public interface ISmProductCodificationViewRepository extends JpaRepository<CSmProductCodificationViewModel, Integer> {

	@Query(value = "FROM CSmProductCodificationViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.product_codification_id Desc")
	Page<CSmProductCodificationViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductCodificationViewModel model where model.is_delete =0 and model.product_codification_id = ?1 and model.company_id = ?2 order by model.product_codification_id Desc")
	Page<CSmProductCodificationViewModel> FnShowParticularRecord(int product_codification_id, Pageable pageable, int company_id);
	

}
