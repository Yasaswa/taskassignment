package com.erp.SmProductPr.Repository;

import com.erp.SmProductPr.Model.CSmProductPrTechnicalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ISmProductPrTechnicalRepository extends JpaRepository<CSmProductPrTechnicalModel, Integer> {

	@Query(value = "FROM  CSmProductPrTechnicalModel cdvm where cdvm.is_delete =0 and cdvm.product_pr_technical_name = ?1 and cdvm.company_id = ?2")
	CSmProductPrTechnicalModel checkIfExist(String product_pr_technical_name, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr_technical set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_pr_technical_id=?1 ,company_id = ?2", nativeQuery = true)
	void updateActiveStatusProductTechnical(int product_pr_technical_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr_technical set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_pr_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteTechnicalRecords(String product_fg_id, String deleted_by);
}
