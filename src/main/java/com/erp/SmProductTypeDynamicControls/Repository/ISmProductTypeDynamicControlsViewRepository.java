package com.erp.SmProductTypeDynamicControls.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.SmProductTypeDynamicControls.Model.CSmProductTypeDynamicControlsViewModel;

public interface ISmProductTypeDynamicControlsViewRepository extends JpaRepository<CSmProductTypeDynamicControlsViewModel, Integer> {

	@Query(value = "FROM CSmProductTypeDynamicControlsViewModel model where model.is_delete = 0 and model.product_type_dynamic_controls_id = ?1 " +
//			"and model.company_id = ?2" +
			"")
	CSmProductTypeDynamicControlsViewModel FnShowParticularRecordForUpdate(int product_type_dynamic_controls_id,
			int company_id);

}
