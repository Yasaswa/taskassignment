package com.erp.Product_Grade.Repository;

import com.erp.Product_Grade.Model.CProductGradeRptModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IProductGradeRptRepository extends JpaRepository<CProductGradeRptModel, String> {


	@Query(value = "SELECT * FROM smv_product_grade_rpt", nativeQuery = true)
	Page<CProductGradeRptModel> FnShowAllReportRecords(Pageable pageable);

}
