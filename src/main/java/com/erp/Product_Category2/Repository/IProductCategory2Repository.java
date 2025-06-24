package com.erp.Product_Category2.Repository;

import com.erp.Product_Category2.Model.CProductCategory2Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductCategory2Repository extends JpaRepository<CProductCategory2Model, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_category2 set is_delete = 1 , deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_category2_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_category2_id, String deleted_by);

	
	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_category2 WHERE (product_category2_name = ?1 ANd (?2 IS NOT NULL AND product_category2_short_name = ?2)) and company_id = ?4 and company_id = ?3 order by product_category2_id Desc limit 1")
	CProductCategory2Model getCheck(String product_category2_name, String product_category2_short_name,
			Integer company_id, Integer product_category1_id);

}
