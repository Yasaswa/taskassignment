package com.erp.Product_Unit_Conversion.Repository;

import com.erp.Product_Unit_Conversion.Model.CProductUnitConversionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductUnitConversionRepository extends JpaRepository<CProductUnitConversionModel, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_unit_conversion WHERE product_from_unit_id = ?1")
	CProductUnitConversionModel getCheck(Integer product_from_unit_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_unit_conversion set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where conversion_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int conversion_id, String deleted_by);

}
