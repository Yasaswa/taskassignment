package com.erp.RawMaterial.SmProductRmBomDtl.Repository;

import com.erp.RawMaterial.SmProductRmBomDtl.Model.CSmProductRmBomDtlModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface ISmProductRmBomDtlRepository extends JpaRepository<CSmProductRmBomDtlModel, Integer> {

	@Query("from CSmProductRmBomDtlModel pdm where pdm.product_rm_bom_no =?1 and pdm.product_rm_bom_version = ?2")
	Page<CSmProductRmBomDtlModel> FnShowParticularRecords(String product_rm_bom_no, int product_rm_bom_version,
	                                                      Pageable pageable);

	@Query("from CSmProductRmBomDtlModel pdm where pdm.product_rm_bom_no =?1")
	List<CSmProductRmBomDtlModel> findListByBOMNo(String product_rm_bom_no);

	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_bom_dtl set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where product_rm_bom_no=?1 and is_delete = 0 and company_id = ?3", nativeQuery = true)
	Object updateBomDetailsActiveStatus(String product_rm_bom_no, String deleted_by, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update sm_product_rm_bom_dtl set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where product_rm_bom_no = ?1 and company_id = ?2", nativeQuery = true)
	Object updateBomDetailsActiveStatusBomDetail(String product_rm_bom_no, int company_id);


}
