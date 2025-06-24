package com.erp.Product_Category1.Repository;

import com.erp.Product_Category1.Model.CProductCategory1Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

import javax.transaction.Transactional;

public interface IProductCategory1Repository extends JpaRepository<CProductCategory1Model, Integer> {


	@Modifying
	@Transactional
	@Query(value = "update sm_product_category1 set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_category1_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int product_category1_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_category1 WHERE (product_category1_name = ?1 OR (?2 IS NOT NULL AND product_category1_short_name = ?2)) and company_id = ?3 order by product_category1_id Desc limit 1")
	CProductCategory1Model getCheck(String product_category1_name, String product_category1_short_name, int company_id);

	@Query(value = "Select * FROM  smv_product_category1 WHERE is_delete = 0 AND product_category1_id = ?1 AND company_id = ?2",nativeQuery = true)
	Map<String, Object> FnShowProductCategory1RecordForUpdate(int product_category1_id, int company_id);
}
