package com.erp.Product_Category1.Repository;

import com.erp.Product_Category1.Model.CProductCategory1ViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductCategory1ViewRepository extends JpaRepository<CProductCategory1ViewModel, Long> {

//	@Query(value = "Select * FROM  smv_product_category1 order by product_category1_id Desc", nativeQuery = true)
//	Page<CProductCategory1ViewModel> FnShowAllRecords(Pageable pageable);
//
//	@Query(value = "Select * FROM  smv_product_category1 where is_delete =0  order by product_category1_id Desc", nativeQuery = true)
//	Page<CProductCategory1ViewModel> FnShowAllActiveRecords(Pageable pageable);
//
//	@Query(value = "Select * FROM  smv_product_category1 where is_delete =0 and product_category1_id = ?1", nativeQuery = true)
//	CProductCategory1ViewModel FnShowParticularRecordForUpdate(int product_category1_id);
//
//	@Query(nativeQuery = true, value = "Select * FROM  smv_product_category1 where is_delete =0 and company_id = ?1 and product_category1_id = ?2")
//	CProductCategory1ViewModel FnShowParticularRecord(int company_id, int product_category1_id);
//
//	@Query(value = "SELECT * FROM smv_product_category1_rpt", nativeQuery = true)
//	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_category1 WHERE is_delete = 0 AND product_category1_id = ?1 AND company_id = ?2",nativeQuery = true)
	Map<String, Object> FnShowProductCategory1RecordForUpdate(int product_category1_id, int company_id);

//	@Query(value = "FROM CProductCategory1ViewModel model where model.is_delete = 0 and model.product_category1_id = ?1 and model.company_id = ?2")
//	Map<String, Object> FnShowProductCategory1RecordForUpdate(int product_category1_id, int company_id);

}
