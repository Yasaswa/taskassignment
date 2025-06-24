package com.erp.RawMaterial.Product_Rm.Repository;

import com.erp.RawMaterial.Product_Rm.Model.CProductRmListViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductRmListViewRepository extends JpaRepository<CProductRmListViewModel, Long> {


	@Query(value = "SELECT * FROM smv_product_rm_list", nativeQuery = true)
	Page<CProductRmListViewModel> FnShowAllProductRmListViewRecords(Pageable pageable);


}
