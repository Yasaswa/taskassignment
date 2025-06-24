package com.erp.FinishGoods.SmProductFgCommercial.Repository;

import com.erp.FinishGoods.SmProductFgCommercial.Model.CSmProductFgCommercialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Map;

public interface ISmProductFgCommercialRepository extends JpaRepository<CSmProductFgCommercialModel, Integer> {

	@Query(value = "FROM  CSmProductFgCommercialModel cdvm where cdvm.is_delete =0 and cdvm.product_fg_id = ?1")
	CSmProductFgCommercialModel FnShowParticularRecordForUpdate(int product_fg_commercial_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_commercial set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteCommercialRecords(String product_fg_id, String deleted_by);

	@Query(value = "SELECT * FROM sm_product_fg_commercial WHERE product_fg_id = ?1 and is_delete = 0", nativeQuery = true)
	Map<String, Object> FnShowParticularProductFgCommercialRecords(String product_fg_id);

//	@Query(value = "SELECT * FROM sm_product_fg_commercial WHERE product_fg_id = ?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
//	Map<String, Object> FnShowParticularProductFgCommercialRecords(String product_fg_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_commercial set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_fg_id=?1 and is_delete = 0", nativeQuery = true)
	void updateActiveStatusProductCommercial(String product_fg_id);


}
