package com.erp.RawMaterial.SmProductRmBomMst.Repository;

import com.erp.RawMaterial.SmProductRmBomMst.Model.CSmProductRmBomMstModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISmProductRmBomMstRepository extends JpaRepository<CSmProductRmBomMstModel, Integer> {

	@Query(value = "FROM  CSmProductRmBomMstModel cdvm where cdvm.is_delete =0 and cdvm.product_rm_bom_id = ?1")
	CSmProductRmBomMstModel FnShowParticularRecordForUpdate(int product_rm_bom_id);

	@Query(value = "FROM  CSmProductRmBomMstModel cbm where cbm.is_delete =0 and cbm.product_rm_bom_no = ?1")
	CSmProductRmBomMstModel checkIfExist(String product_rm_bom_no);

	@Query("from CSmProductRmBomDtlModel pdm where pdm.product_rm_bom_id = ?1")
	Page<CSmProductRmBomMstModel> FnShowParticularRecord(int product_rm_bom_id, Pageable pageable);


}
