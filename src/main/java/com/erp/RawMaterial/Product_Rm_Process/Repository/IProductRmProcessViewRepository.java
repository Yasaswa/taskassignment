package com.erp.RawMaterial.Product_Rm_Process.Repository;

import com.erp.RawMaterial.Product_Rm_Process.Model.CProductRmProcessViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductRmProcessViewRepository extends JpaRepository<CProductRmProcessViewModel, Integer> {

	@Query(value = "Select * FROM smv_product_rm_process where company_id = ?1", nativeQuery = true)
	List<CProductRmProcessViewModel> FnShowAllActiveRecords(int company_id);

}
