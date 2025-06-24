package com.erp.Product_Rejection_Parameters.Repository;

import com.erp.Product_Rejection_Parameters.Model.CProductRejectionParametersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductRejectionParametersRepository extends JpaRepository<CProductRejectionParametersModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update sm_product_rejection_parameters set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where product_rejection_parameters_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_rejection_parameters_id, String deleted_by);


	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_rejection_parameters WHERE (product_rejection_parameters_name = ?1 or (?2 IS NOT NULL AND product_rejection_parameters_short_name = ?2)) and company_id = ?3 order by product_rejection_parameters_id Desc limit 1")
	CProductRejectionParametersModel getCheck(String product_rejection_parameters_name,
	                                          String product_rejection_parameters_short_name, Integer company_id);

}
