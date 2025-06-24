package com.erp.Product_Type.Repository;

import com.erp.Product_Type.Model.CProductTypeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IProductTypeViewRepository extends JpaRepository<CProductTypeViewModel, Integer> {

	@Query(value = "Select * FROM  smv_product_type order by product_type_id Desc", nativeQuery = true)
	Page<CProductTypeViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_type where is_delete =0  order by product_type_id Desc", nativeQuery = true)
	Page<CProductTypeViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_type where is_delete =0 and product_type_id = ?1", nativeQuery = true)
	CProductTypeViewModel FnShowParticularRecordForUpdate(int product_type_id);


	@Query(value = "FROM  CProductTypeViewModel model where model.is_delete = 0 and model.product_type_id = ?1 "
//			"and  model.company_id = ?2"
	)
	CProductTypeViewModel FnShowParticularRecord(int product_type_id,int company_id);
	
	@Query(value = "SELECT * FROM smv_product_type_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);
}
