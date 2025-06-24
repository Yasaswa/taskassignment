package com.erp.FinishGoods.SmProductFgBomMst.Repository;

import com.erp.FinishGoods.SmProductFgBomMst.Model.CSmProductFgBomMstModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ISmProductFgBomMstRepository extends JpaRepository<CSmProductFgBomMstModel, Integer> {

	@Query(value = "FROM CSmProductFgBomMstModel fgMst where fgMst.is_delete =0 and fgMst.product_fg_bom_id = ?1")
	CSmProductFgBomMstModel FnShowParticularRecordForUpdate(int product_fg_bom_id);

	@Query(value = "FROM  CSmProductFgBomMstModel cbm where cbm.is_delete =0 and cbm.product_fg_bom_no = ?1")
	CSmProductFgBomMstModel checkIfExist(String product_fg_bom_no);

	@Query("from CSmProductFgBomDtlModel pdm where pdm.product_fg_bom_id = ?1")
	Page<CSmProductFgBomMstModel> FnShowParticularRecord(int product_fg_bom_id, Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_fg_bom_mst set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_fg_id = ?1 and company_id = ?2", nativeQuery = true)
	void deleteProductFgBomMstRecords(int product_fg_id, int company_id);

}
