package com.erp.Product_Category5.Repository;

import com.erp.Product_Category5.Model.CProductCategory5Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductCategory5Repository extends JpaRepository<CProductCategory5Model, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_category5 set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where product_category5_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int product_category4_id, String deleted_by);

	
	
	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_category5 WHERE (product_category5_name = ?1 OR (?2 IS NOT NULL AND product_category5_short_name = ?2)) and company_id = ?3 order by product_category5_id Desc limit 1")
	CProductCategory5Model getCheck(String product_category5_name, String product_category5_short_name,
			Integer company_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_category5 WHERE product_category5_name = ?1")
//	CProductCategory5Model getCheck(String product_category5_name);
}
