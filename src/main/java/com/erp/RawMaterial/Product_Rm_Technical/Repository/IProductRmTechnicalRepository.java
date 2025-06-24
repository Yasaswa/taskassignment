package com.erp.RawMaterial.Product_Rm_Technical.Repository;

import com.erp.RawMaterial.Product_Rm_Technical.Model.CProductRmTechnicalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IProductRmTechnicalRepository extends JpaRepository<CProductRmTechnicalModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_technical set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_rm_id=?1", nativeQuery = true)
	Object FnDeleteRecord(int product_rm_id);

	@Query(nativeQuery = true, value = "SELECT * FROM sm_product_rm_technical WHERE product_rm_technical_name = ?1 and is_delete = 0")
	CProductRmTechnicalModel getCheck(String product_rm_technical_name);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_technical set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_rm_technical_id=?1", nativeQuery = true)
	Object updateActiveStatusProductTechnical(int product_rm_technical_id);


	@Query(value = "Select * FROM  sm_product_rm_technical where  is_delete =0 and company_id = ?1 and company_branch_id = ?2 and product_rm_id = ?3", nativeQuery = true)
	CProductRmTechnicalModel FnShowParticularRecordForUpdate(int company_id, int company_branch_id, int product_rm_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_technical set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_rm_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteProductRmTechnicalRecords(String product_rm_id, String deleted_by);

}
