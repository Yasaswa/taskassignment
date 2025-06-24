package com.erp.Product_Packing.Repository;

import com.erp.Product_Packing.Model.CProductPackingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface IProductPackingViewRepository extends JpaRepository<CProductPackingViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_packing order by product_packing_id Desc", nativeQuery = true)
	Page<CProductPackingViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_packing where is_delete =0  order by product_packing_id Desc", nativeQuery = true)
	Page<CProductPackingViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_packing where is_delete =0 and product_packing_id = ?1", nativeQuery = true)
	CProductPackingViewModel FnShowParticularRecordForUpdate(int product_packing_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_packing where is_delete =0  and product_packing_id = ?1")
	CProductPackingViewModel FnShowParticularRecord(int product_packing_id);

	@Query(value = "SELECT * FROM smv_product_packing_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllReportRecords();
}
