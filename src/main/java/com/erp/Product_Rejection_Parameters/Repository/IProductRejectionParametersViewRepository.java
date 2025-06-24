package com.erp.Product_Rejection_Parameters.Repository;

import com.erp.Product_Rejection_Parameters.Model.CProductRejectionParametersViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IProductRejectionParametersViewRepository extends JpaRepository<CProductRejectionParametersViewModel, Long> {


	@Query(value = "Select * FROM  smv_product_rejection_parameters order by product_rejection_parameters_id Desc", nativeQuery = true)
	Page<CProductRejectionParametersViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_rejection_parameters where is_delete =0  order by product_rejection_parameters_id Desc", nativeQuery = true)
	Page<CProductRejectionParametersViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_rejection_parameters where is_delete =0 and product_rejection_parameters_id = ?1", nativeQuery = true)
	CProductRejectionParametersViewModel FnShowParticularRecordForUpdate(int product_rejection_parameters_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_rejection_parameters where is_delete =0 and company_id = ?1 and product_rejection_parameters_id = ?2")
	CProductRejectionParametersViewModel FnShowParticularRecord(int company_id, int product_rejection_parameters_id);

	@Query(value = "SELECT * FROM smv_product_rejection_parameters_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
