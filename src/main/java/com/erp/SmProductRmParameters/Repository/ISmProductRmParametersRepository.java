package com.erp.SmProductRmParameters.Repository;



import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.SmProductRmParameters.Model.CSmProductRmParametersModel;


public interface ISmProductRmParametersRepository extends JpaRepository<CSmProductRmParametersModel, Integer> {

	@Query(value = "FROM CSmProductRmParametersModel model where model.is_delete =0 and model.product_rm_parameter_name = ?1 and model.company_id = ?2")
	CSmProductRmParametersModel checkIfNameExist(String product_rm_parameter_name,int company_id);


	@Modifying
	@Transactional
	@Query(value = "update CSmProductRmParametersModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.product_parameter_id = ?1")
	void FnDeleteProductRmParametersRecord(int product_parameter_id, String deleted_by);


}
