package com.erp.SmProductPrBomMst.Repository;

import com.erp.SmProductPrBomMst.Model.CSmProductPrBomMstModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface ISmProductPrBomMstRepository extends JpaRepository<CSmProductPrBomMstModel, Integer> {


	@Query(value = "FROM  CSmProductPrBomMstModel cbm where cbm.is_delete =0 and cbm.product_pr_bom_no = ?1")
	CSmProductPrBomMstModel checkIfExist(String product_pr_bom_no);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_pr_bom_dtl set is_delete = 1, deleted_by= ?2, deleted_on = CURRENT_TIMESTAMP() where product_pr_bom_no=?1 and is_delete = 0", nativeQuery = true)
	void updateBomDetailsActiveStatus(String product_pr_bom_no, String deleted_by);


}
