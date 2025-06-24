package com.erp.SmProductPr.Repository;

import com.erp.SmProductPr.Model.CSmProductPrQaMappingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ISmProductPrQaMappingRepository extends JpaRepository<CSmProductPrQaMappingModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr_qa_mapping set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_pr_id=?1 and company_id=?2", nativeQuery = true)
	void updateProductPrQaMappingActiveStatus(String product_pr_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr_qa_mapping set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_pr_id = ?1 and is_delete = 0", nativeQuery = true)
	void deleteQaMappingRecords(String product_pr_id, String deleted_by);
}
