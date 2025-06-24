package com.erp.Product_Qa_Parameters.Repository;

import com.erp.Product_Qa_Parameters.Model.CProductQaParametersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductQaParametersRepository extends JpaRepository<CProductQaParametersModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_qa_parameters set is_delete = 1 ,deleted_by=?2, deleted_on = CURRENT_TIMESTAMP() where product_qa_parameters_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_qa_parameters_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_qa_parameters WHERE (product_qa_parameters_name = ?1 or (?2 IS NOT NULL AND product_qa_parameters_short_name = ?2)) and company_id = ?3 order by product_qa_parameters_id Desc limit 1")
	CProductQaParametersModel getCheck(String product_qa_parameters_name, String product_qa_parameters_short_name,
	                                   Integer company_id);

}
