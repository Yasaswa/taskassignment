package com.erp.Product_Category3.Repository;

import com.erp.Product_Category3.Model.CProductCategory3ViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductCategory3ViewRepository extends JpaRepository<CProductCategory3ViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_category3 where is_delete =0 and product_category3_id = ?1", nativeQuery = true)
	CProductCategory3ViewModel FnShowParticularRecordForUpdate(int product_category3_id);

	@Query(value = "Select * FROM  smv_product_category3 where is_delete =0  order by product_category3_id Desc", nativeQuery = true)
	Page<CProductCategory3ViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_category3 order by product_category3_id Desc", nativeQuery = true)
	Page<CProductCategory3ViewModel> FnShowAllRecords(Pageable pageable);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_category3 where is_delete =0 and company_id = ?1 and product_category3_id = ?2")
	CProductCategory3ViewModel FnShowParticularRecord(int company_id, int product_category3_id);

	@Query(value = "SELECT * FROM smv_product_category3_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
