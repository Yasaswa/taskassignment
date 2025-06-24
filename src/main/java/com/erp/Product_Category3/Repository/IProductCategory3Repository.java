package com.erp.Product_Category3.Repository;

import com.erp.Product_Category3.Model.CProductCategory3Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductCategory3Repository extends JpaRepository<CProductCategory3Model, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_category3 set is_delete = 1 , deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_category3_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int product_category3_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_category3 WHERE (product_category3_name = ?1 OR (?2 IS NOT NULL AND product_category3_short_name = ?2)) and company_id = ?3 order by product_category3_id Desc limit 1")
	CProductCategory3Model getCheck(String product_category3_name, String product_category3_short_name,
			Integer company_id);

//	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_category3 WHERE product_category3_name = ?1")
//	CProductCategory3Model getCheck(String product_category3_name);
}
