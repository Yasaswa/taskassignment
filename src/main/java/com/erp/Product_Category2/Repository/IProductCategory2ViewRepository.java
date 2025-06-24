package com.erp.Product_Category2.Repository;

import com.erp.Product_Category2.Model.CProductCategory2ViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductCategory2ViewRepository extends JpaRepository<CProductCategory2ViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_category2 order by product_category2_id Desc", nativeQuery = true)
	Page<CProductCategory2ViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_category2 where is_delete =0  order by product_category2_id Desc", nativeQuery = true)
	Page<CProductCategory2ViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_category2 where is_delete =0 and product_category2_id = ?1", nativeQuery = true)
	CProductCategory2ViewModel FnShowParticularRecordForUpdate(int product_category2_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_category2 where is_delete =0 and company_id = ?1 and product_category2_id = ?2")
	CProductCategory2ViewModel FnShowParticularRecord(int company_id, int product_category2_id);

	@Query(value = "SELECT * FROM smv_product_category2_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);
}
