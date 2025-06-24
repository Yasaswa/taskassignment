package com.erp.Product_Category4.Repository;

import com.erp.Product_Category4.Model.CProductCategory4ViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductCategory4ViewRepository extends JpaRepository<CProductCategory4ViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_category4 where is_delete =0 and product_category4_id = ?1", nativeQuery = true)
	CProductCategory4ViewModel FnShowParticularRecordForUpdate(int product_category4_id);

	@Query(value = "Select * FROM  smv_product_category4 where is_delete =0  order by product_category4_id Desc", nativeQuery = true)
	Page<CProductCategory4ViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_category4 order by product_category4_id Desc", nativeQuery = true)
	Page<CProductCategory4ViewModel> FnShowAllRecords(Pageable pageable);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_category4 where is_delete =0 and company_id = ?1 and product_category4_id = ?2")
	CProductCategory4ViewModel FnShowParticularRecord(int company_id, int product_category4_id);

	@Query(value = "SELECT * FROM smv_product_category4_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
