package com.erp.Product_Grade.Repository;

import com.erp.Product_Grade.Model.CProductGradeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductGradeRepository extends JpaRepository<CProductGradeModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_grade set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_grade_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_grade_id);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_grade WHERE (product_grade_name = ?1 OR (?2 IS NOT NULL AND product_grade_short_name = ?2)) and company_id = ?3 order by product_grade_id Desc limit 1")
	CProductGradeModel getCheck(String product_grade_name, String product_grade_short_name, int company_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_grade WHERE product_grade_name = ?1")
//	CProductGradeModel getCheck(String product_grade_name);

}
