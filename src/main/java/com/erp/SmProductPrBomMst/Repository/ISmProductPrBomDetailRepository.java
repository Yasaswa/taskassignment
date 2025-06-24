package com.erp.SmProductPrBomMst.Repository;

import com.erp.SmProductPrBomMst.Model.CSmProductPrBomDtlModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ISmProductPrBomDetailRepository extends JpaRepository<CSmProductPrBomDtlModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr_bom_dtl set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_pr_bom_no=?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
	void updateBomDetailsActiveStatusBomDetail(String product_pr_bom_no, int company_id);

}
