package com.erp.RawMaterial.Product_Rm.Repository;

import com.erp.RawMaterial.Product_Rm.Model.CProductRmDetailsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface IProductRmDetailsViewRepository extends JpaRepository<CProductRmDetailsViewModel, String> {


	@Query(value = "SELECT * FROM smv_product_rm_details", nativeQuery = true)
	Page<CProductRmDetailsViewModel> FnShowAllProductRmDetailsViewRecords(Pageable pageable);


}
