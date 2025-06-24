package com.erp.Product_Make.Repository;

import com.erp.Product_Make.Model.CProductMakeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductMakeRepository extends JpaRepository<CProductMakeModel, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update sm_product_make set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where product_make_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_make_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_make WHERE (product_make_name = ?1 or (?2 IS NOT NULL AND product_make_short_name = ?2)) and company_id = ?3 order by product_make_id Desc limit 1")
	CProductMakeModel getCheck(String product_make_name, String product_make_short_name, Integer company_id);


} 
