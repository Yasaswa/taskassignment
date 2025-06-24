package com.erp.Product_Category5.Repository;

import com.erp.Product_Category5.Model.CProductCategory5ViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductCategory5ViewRepository extends JpaRepository<CProductCategory5ViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_category5 where is_delete =0 and product_category5_id = ?1", nativeQuery = true)
	CProductCategory5ViewModel FnShowParticularRecordForUpdate(int product_category5_id);

	@Query(value = "Select * FROM  smv_product_category5 where is_delete =0  order by product_category5_id Desc", nativeQuery = true)
	Page<CProductCategory5ViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_category5 order by product_category5_id Desc", nativeQuery = true)
	Page<CProductCategory5ViewModel> FnShowAllRecords(Pageable pageable);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_category5 where is_delete =0 and company_id = ?1 and product_category5_id = ?2")
	CProductCategory5ViewModel FnShowParticularRecord(int company_id, int product_category5_id);

	@Query(value = "SELECT * FROM smv_product_category5_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
