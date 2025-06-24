package com.erp.Product_Type.Repository;

import com.erp.Product_Type.Model.CProductTypeRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRptRepository_Not_Used extends JpaRepository<CProductTypeRptModel_Not_Used, String> {

//	@Query(value ="SELECT * FROM smv_product_type_rpt", nativeQuery = true)
//	Page<CProductTypeRptModel> FnShowAllReportRecords(Pageable pageable);

}
