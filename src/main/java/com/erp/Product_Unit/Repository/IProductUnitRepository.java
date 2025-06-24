package com.erp.Product_Unit.Repository;

import com.erp.Product_Unit.Model.CProductUnitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductUnitRepository extends JpaRepository<CProductUnitModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_unit set is_delete = 1 ,deleted_by = ?3 , deleted_on = CURRENT_TIMESTAMP() where product_unit_id=?1 and company_id = ?2", nativeQuery = true)
	Object FnDeleteRecord(int product_unit_id, int company_id, String deleted_by);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_unit WHERE product_unit_name = ?1")
	CProductUnitModel getCheck(String product_unit_name);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_unit WHERE (product_unit_name = ?1 or product_unit_short_name = ?2)and company_id = ?3 and is_delete = 0")
	CProductUnitModel getCheck(String product_unit_name, String product_unit_short_name, Integer company_id);

}
