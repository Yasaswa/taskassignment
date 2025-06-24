package com.erp.SmProductSrParameters.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductSrParameters.Model.CSmProductSrParametersModel;

public interface ISmProductSrParametersRepository extends JpaRepository<CSmProductSrParametersModel, Integer> {

	@Query(value = "FROM CSmProductSrParametersModel model where model.is_delete = 0 and model.product_sr_parameter_name = ?1")
	CSmProductSrParametersModel checkIfNameExist(String product_sr_parameter_name);
	
	@Query(value = "update CSmProductSrParametersModel model set model.is_delete = 1 , deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_parameter_id = ?1")
	void FnDeleteProductSrParametersRecord(int product_parameter_id, String deleted_by);


}
