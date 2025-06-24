package com.erp.Product_Type.Repository;

import com.erp.Product_Type.Model.CProductTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductTypeRepository extends JpaRepository<CProductTypeModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_type set is_delete = 1 ,deleted_by=?2 , deleted_on = CURRENT_TIMESTAMP() where product_type_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_type_id, String deleted_by);

//	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_type WHERE product_type_name = ?1")
//	CProductTypeModel getCheck(String product_type_name);

	
	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_type WHERE (product_type_name = ?1 OR (?2 IS NOT NULL AND product_type_short_name = ?2)) and company_id = ?3 order by product_type_id Desc limit 1")
	CProductTypeModel getCheck(String product_type_name, String product_type_short_name, Integer company_id);

}
