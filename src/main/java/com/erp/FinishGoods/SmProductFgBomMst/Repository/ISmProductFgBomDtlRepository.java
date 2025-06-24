package com.erp.FinishGoods.SmProductFgBomMst.Repository;

import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmProductFgBomDtlModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductFgBomDtlRepository extends JpaRepository<CSmProductFgBomDtlModel, Integer> {

	@Query("from CSmProductFgBomDtlModel fgdm where fgdm.product_fg_bom_no =?1 and fgdm.product_fg_bom_version = ?2")
	Page<CSmProductFgBomDtlModel> FnShowParticularRecords(String product_fg_bom_no, int product_fg_bom_version,
	                                                      Pageable pageable);

	@Query("from CSmProductFgBomDtlModel pdm where pdm.product_fg_bom_no =?1")
	List<CSmProductFgBomDtlModel> findListByBOMNo(String product_fg_bom_no);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_bom_dtl set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_fg_bom_no=?1 and company_id = ?2 and is_delete = 0", nativeQuery = true)
	Object updateBomDetailsActiveStatusBomDetail(String product_fg_bom_no, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_bom_dtl set is_delete = 1, deleted_by= ?2, deleted_on = CURRENT_TIMESTAMP() where product_fg_bom_no=?1 and is_delete = 0", nativeQuery = true)
	void updateBomDetailsActiveStatus(String productFgBomNo, String deleted_by, int company_id);
}
