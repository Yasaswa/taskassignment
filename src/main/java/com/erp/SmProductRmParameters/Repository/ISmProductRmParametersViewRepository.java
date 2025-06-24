package com.erp.SmProductRmParameters.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductRmParameters.Model.CSmProductRmParametersViewModel;

public interface ISmProductRmParametersViewRepository extends JpaRepository<CSmProductRmParametersViewModel, Integer> {

	@Query("FROM CSmProductRmParametersViewModel model WHERE model.is_delete = 0 AND model.product_type_id = ?1 AND model.company_id = ?2")
	CSmProductRmParametersViewModel FnShowParticularRecordForUpdate(int product_type_id, int company_id);

}
