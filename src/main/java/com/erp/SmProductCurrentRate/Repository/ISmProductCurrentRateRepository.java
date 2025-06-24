package com.erp.SmProductCurrentRate.Repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.SmProductCurrentRate.Model.CSmProductCurrentRateModel;


public interface ISmProductCurrentRateRepository extends JpaRepository<CSmProductCurrentRateModel, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "update CSmProductCurrentRateModel model set model.is_delete = 1, model.deleted_on = CURRENT_TIMESTAMP() WHERE model.product_material_id = ?1 and company_id = ?2")
	void updateProductCurrentRateDetails(String product_material_id, int company_id);



}
