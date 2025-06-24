package com.erp.RawMaterial.Product_Rm.Repository;

import com.erp.RawMaterial.Product_Rm.Model.CProductRmSummaryViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IProductRmSummaryViewRepository extends JpaRepository<CProductRmSummaryViewModel, String> {

	@Query(value = "SELECT * FROM smv_product_rm_summary", nativeQuery = true)
	Page<CProductRmSummaryViewModel> FnShowAllProductRmmaterialSummaryViewRecords(Pageable pageable);


}
