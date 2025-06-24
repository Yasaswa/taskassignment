package com.erp.RawMaterial.Product_Rm_Commercial.Repository;

import com.erp.RawMaterial.Product_Rm_Commercial.Model.CProductRmCommercialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductRmCommercialRepository extends JpaRepository<CProductRmCommercialModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_commercial set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_rm_id=?1 and is_delete = 0", nativeQuery = true)
	Object updateActiveStatusProductCommercial(int product_rm_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_commercial set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_rm_id=?1 and is_delete = 0", nativeQuery = true)
	Object updateActiveStatusProductCommercialByRMId(String product_rm_id);

	@Query(value = "FROM  CProductRmCommercialModel cpcm where  cpcm.is_delete =0 and cpcm.company_id = ?1 and cpcm.company_branch_id = ?2 and cpcm.product_rm_id = ?3")
	CProductRmCommercialModel FnShowParticularRecordForUpdate(int company_id, int company_branch_id,
	                                                          String product_rm_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_commercial set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteProductRmCommercialRecords(String product_rm_id, String deleted_by);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_commercial set product_rm_mrp = ?2 where product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
	void updateMaasterRateByAdjustment(String product_rm_id, double material_rate);

}
