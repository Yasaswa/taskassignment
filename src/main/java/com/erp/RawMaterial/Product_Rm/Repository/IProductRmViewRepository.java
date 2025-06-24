package com.erp.RawMaterial.Product_Rm.Repository;

import com.erp.RawMaterial.Product_Rm.Model.CProductRmViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductRmViewRepository extends JpaRepository<CProductRmViewModel, Long> {


	@Query(value = "Select * FROM  smv_product_rm order by product_rm_id Desc", nativeQuery = true)
	Page<CProductRmViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_rm where is_delete =0  order by product_rm_id Desc", nativeQuery = true)
	Page<CProductRmViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_rm where is_delete =0 and product_rm_id = ?1", nativeQuery = true)
	CProductRmViewModel FnShowParticularRecordForUpdate(int product_rm_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_rm where is_delete =0 and company_id = ?1 and product_rm_id = ?2")
	CProductRmViewModel FnShowParticularRecord(int company_id, int product_rm_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_rm  where is_delete =0 and  company_id = ?1 and company_branch_id= ?2 and product_rm_id = ?3")
	CProductRmViewModel FnShowParticularRecordForUpdate(int company_id, int company_branch_id, int product_rm_id);


	@Query(value = "SELECT * FROM smv_product_rm_rpt_details", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllProductRmDetailsRptRecords(Pageable pageable);

	@Query(value = "Select * From smv_product_rm_rpt_summary", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportSummaryRecords(Pageable pageable);


}
