package com.erp.Product_Unit_Conversion.Repository;

import com.erp.Product_Unit_Conversion.Model.CProductUnitConversionViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductUnitConversionViewRepository extends JpaRepository<CProductUnitConversionViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_unit_conversion order by conversion_id Desc", nativeQuery = true)
	Page<CProductUnitConversionViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_unit_conversion where is_delete =0  order by conversion_id Desc", nativeQuery = true)
	Page<CProductUnitConversionViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_unit_conversion where is_delete =0 and conversion_id = ?1", nativeQuery = true)
	CProductUnitConversionViewModel FnShowParticularRecordForUpdate(int conversion_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_unit_conversion where is_delete =0 and conversion_id = ?1 and company_id = ?2")
	CProductUnitConversionViewModel FnShowParticularRecord(int conversion_id, int company_id);

	@Query(value = "SELECT * FROM smv_product_unit_conversion_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
