package com.erp.Product_Qa_Parameters.Repository;

import com.erp.Product_Qa_Parameters.Model.CProductQaParametersViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IProductQaParametersViewRepository extends JpaRepository<CProductQaParametersViewModel, Long> {


	@Query(value = "Select * FROM  smv_product_qa_parameters order by product_qa_parameters_id Desc", nativeQuery = true)
	Page<CProductQaParametersViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_qa_parameters where is_delete =0  order by product_qa_parameters_id Desc", nativeQuery = true)
	Page<CProductQaParametersViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  smv_product_qa_parameters where is_delete =0 and product_qa_parameters_id = ?1", nativeQuery = true)
	CProductQaParametersViewModel FnShowParticularRecordForUpdate(int product_qa_parameters_id);

	@Query(nativeQuery = true, value = "Select * FROM  smv_product_qa_parameters where is_delete =0 and company_id = ?1 and product_qa_parameters_id = ?2")
	CProductQaParametersViewModel FnShowParticularRecord(int company_id, int product_qa_parameters_id);

	@Query(value = "Select * FROM  smv_product_qa_parameters where is_delete =0 and company_id=?1 order by product_qa_parameters_id Desc", nativeQuery = true)
	List<CProductQaParametersViewModel> FnShowProductSrQaMappingActiveRecords(int company_id);


	@Query(value = "FROM CProductQaParametersViewModel model where model.is_delete=0 and model.company_id = ?1 and product_qa_type IN ('All' , 'Purchase')")
	List<CProductQaParametersViewModel> FnShowAllQaParametersRecords(int company_id);

	@Query(value = "SELECT * FROM smv_product_qa_parameters_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);

}
