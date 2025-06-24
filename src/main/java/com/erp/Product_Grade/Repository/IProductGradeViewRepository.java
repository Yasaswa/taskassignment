package com.erp.Product_Grade.Repository;

import com.erp.Product_Grade.Model.CProductGradeViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductGradeViewRepository extends JpaRepository<CProductGradeViewModel, Long> {

	@Query(value = "Select * FROM  smv_product_grade order by product_grade_id Desc", nativeQuery = true)
	Page<CProductGradeViewModel> FnShowAllRecords(Pageable pageable);


	@Query(value = "Select * FROM  smv_product_grade where is_delete =0  order by product_grade_id Desc", nativeQuery = true)
	Page<CProductGradeViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_grade where is_delete =0 and product_grade_id = ?1", nativeQuery = true)
	CProductGradeViewModel FnShowParticularRecordForUpdate(int product_grade_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_grade where is_delete =0 and company_id = ?1 and product_grade_id = ?2")
	CProductGradeViewModel FnShowParticularRecord(int company_id, int product_grade_id);

	/* Added By Dipti (ERP DB Testing 1.1) */

//	@Query(value = "Select * FROM  smv_product_grade order by product_material_grade_id Desc", nativeQuery = true)
//	Page<CProductGradeViewModel> FnShowAllRecords(Pageable pageable);
//	
//	
//	@Query(value = "Select * FROM  smv_product_grade where is_delete =0  order by product_material_grade_id Desc", nativeQuery = true)
//	Page<CProductGradeViewModel> FnShowAllActiveRecords(Pageable pageable);
//	
//	@Query(value = "Select * FROM  smv_product_grade where is_delete =0 and product_material_grade_id = ?1", nativeQuery = true)
//	CProductGradeViewModel FnShowParticularRecordForUpdate(int product_material_grade_id);
//	
//	@Query(nativeQuery = true, value ="Select * FROM  smv_product_grade where is_delete =0 and company_id = ?1 and product_material_grade_id = ?2")
//	CProductGradeViewModel FnShowParticularRecord(int company_id, int product_material_grade_id);

	/* Added By Dipti (ERP DB Testing 1.1) */


}
