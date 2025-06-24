package com.erp.SmProductPr.Repository;

import com.erp.SmProductPr.Model.CSmProductPrCommercialModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ISmProductPrCommercialRepository extends JpaRepository<CSmProductPrCommercialModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr_commercial set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where product_pr_id=?1 and is_delete = 0", nativeQuery = true)
	void updateActiveStatusProductCommercial(String product_pr_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr_commercial set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_pr_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteCommercialRecords(String product_pr_id, String deleted_by);
}
