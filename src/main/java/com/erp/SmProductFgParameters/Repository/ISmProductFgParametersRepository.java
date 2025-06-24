package com.erp.SmProductFgParameters.Repository;


import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductFgParameters.Model.CSmProductFgParametersModel;


public interface ISmProductFgParametersRepository extends JpaRepository<CSmProductFgParametersModel, Integer> {

	
	@Query(value = "FROM CSmProductFgParametersModel model where model.is_delete = 0 AND model.product_fg_parameter_name = ?1")
	CSmProductFgParametersModel checkIfNameExist(String product_fg_parameter_name);
	
	@Modifying
	@Transactional
	@Query(value = "update CSmProductFgParametersModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_parameter_id = ?1")
	void FnDeleteProductFgParametersRecord(int product_parameter_id, String deleted_by);



}
