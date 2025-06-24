package com.erp.Product_Packing.Repository;

import com.erp.Product_Packing.Model.CProductPackingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductPackingRepository extends JpaRepository<CProductPackingModel, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_packing WHERE product_packing_name = ?1 and company_id = ?2")
	CProductPackingModel getCheck(String product_packing_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_packing set is_delete = 1 , deleted_by = ?2 , deleted_on = CURRENT_TIMESTAMP() where product_packing_id = ?1", nativeQuery = true)
	Object FnDeleteRecord(int product_packing_id, String deleted_by);
}
