package com.erp.SmProductPrParameters.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductPrParameters.Model.CSmProductPrParametersViewModel;

public interface ISmProductPrParametersViewRepository extends JpaRepository<CSmProductPrParametersViewModel, Integer> {

	@Query(value = "FROM CSmProductPrParametersViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.company_id Desc")
	Page<CSmProductPrParametersViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductPrParametersViewModel model where model.is_delete = 0 and model.product_type_id = ?1 and model.company_id = ?2")
	CSmProductPrParametersViewModel FnShowParticularRecordForUpdate(int product_type_id, int company_id);

}
