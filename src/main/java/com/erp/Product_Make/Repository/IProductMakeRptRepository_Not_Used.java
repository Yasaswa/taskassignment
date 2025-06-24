package com.erp.Product_Make.Repository;

import com.erp.Product_Make.Model.CProductMakeRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductMakeRptRepository_Not_Used extends JpaRepository<CProductMakeRptModel_Not_Used, String> {

//	@Query(value ="SELECT * FROM smv_product_make_rpt", nativeQuery = true)
//	Page<CProductMakeRptModel> FnShowAllReportRecords(Pageable pageable);
}
