package com.erp.SmProductPrParameters.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductPrParameters.Model.CSmProductPrParametersModel;


public interface ISmProductPrParametersRepository extends JpaRepository<CSmProductPrParametersModel, Integer> {

	@Query(value = "FROM CSmProductPrParametersModel model where model.is_delete = 0 and model.product_pr_parameter_name = ?1")
	CSmProductPrParametersModel checkIfNameExist(String product_pr_parameter_name);

	
	@Modifying
	@Transactional
	@Query(value = "update CSmProductPrParametersModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_parameter_id = ?1")
	void FnDeleteProductPrParametersRecord(int product_parameter_id, String deleted_by);

	
}
