package com.erp.RawMaterial.Product_Rm_Technical.Repository;

import com.erp.RawMaterial.Product_Rm_Technical.Model.CProductRmTechnicalViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRmTechnicalViewRepository extends JpaRepository<CProductRmTechnicalViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_rm_technical order by product_rm_id Desc", nativeQuery = true)
	Page<CProductRmTechnicalViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_rm_technical where is_delete =0  order by product_rm_id Desc", nativeQuery = true)
	Page<CProductRmTechnicalViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_rm_technical where is_delete =0 and company_id = ?1 and company_branch_id = ?2 and product_rm_id = ?3")
	CProductRmTechnicalViewModel FnShowParticularRecord(int company_id, int company_branch_id, int product_rm_id);
}
