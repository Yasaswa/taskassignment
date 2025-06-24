package com.erp.Product_Unit.Repository;

import com.erp.Product_Unit.Model.CProductUnitViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductUnitViewRepository extends JpaRepository<CProductUnitViewModel, Long> {


	@Query(value = "Select * FROM  smv_product_unit order by product_unit_id Desc", nativeQuery = true)
	Page<CProductUnitViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_unit where is_delete =0  order by product_unit_id Desc", nativeQuery = true)
	Page<CProductUnitViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_unit where is_delete =0 and product_unit_id = ?1 and company_id = ?2", nativeQuery = true)
	CProductUnitViewModel FnShowParticularRecordForUpdate(int product_unit_id, int company_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_unit where is_delete =0 and company_id = ?1 and product_unit_id = ?2")
	CProductUnitViewModel FnShowParticularRecord(int company_id, int product_unit_id);

	@Query(value = "SELECT * FROM smv_product_unit_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
