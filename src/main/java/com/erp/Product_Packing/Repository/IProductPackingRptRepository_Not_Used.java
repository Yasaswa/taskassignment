package com.erp.Product_Packing.Repository;

import com.erp.Product_Packing.Model.CProductPackingRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductPackingRptRepository_Not_Used extends JpaRepository<CProductPackingRptModel_Not_Used, String> {

//	@Query(value ="SELECT * FROM smv_product_packing_rpt", nativeQuery = true)
//	List<CProductPackingRptModel> FnShowAllReportRecords();


}
