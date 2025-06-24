package com.erp.Product_Make.Repository;

import com.erp.Product_Make.Model.CProductMakeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductMakeViewRepository extends JpaRepository<CProductMakeViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_make order by product_make_id Desc", nativeQuery = true)
	Page<CProductMakeViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_make where is_delete =0  order by product_make_id Desc", nativeQuery = true)
	Page<CProductMakeViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_make where is_delete =0 and product_make_id = ?1", nativeQuery = true)
	CProductMakeViewModel FnShowParticularRecordForUpdate(int product_make_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_make where is_delete =0 and company_id = ?1 and product_make_id = ?2")
	CProductMakeViewModel FnShowParticularRecord(int company_id, int product_make_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_make_rpt")
	Map<String, Object> FnShowAllReportRecords();

}
