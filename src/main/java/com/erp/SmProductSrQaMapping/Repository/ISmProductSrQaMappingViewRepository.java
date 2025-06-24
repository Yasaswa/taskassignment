package com.erp.SmProductSrQaMapping.Repository;

import com.erp.SmProductSrQaMapping.Model.CSmProductSrQaMappingModel;
import com.erp.SmProductSrQaMapping.Model.CSmProductSrQaMappingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISmProductSrQaMappingViewRepository extends JpaRepository<CSmProductSrQaMappingViewModel, Integer> {

	@Query(value = "FROM CSmProductSrQaMappingViewModel model where  model.company_id = ?1 order by model.product_qa_parameters_id Desc")
	Page<CSmProductSrQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrQaMappingViewModel model where model.product_sr_id = ?1 and model.company_id = ?2 order by model.product_qa_parameters_id Desc")
	Page<CSmProductSrQaMappingViewModel> FnShowParticularRecord(int product_sr_id, Pageable pageable, int company_id);

	@Query(value = "FROM CSmProductSrQaMappingModel model where model.is_delete = 0  and  model.product_sr_id = ?1 and model.company_id = ?2")
	List<CSmProductSrQaMappingModel> FnShowProductServiceQaMappingRecords(int product_sr_id, int company_id);

	@Query(value = "FROM CSmProductSrQaMappingViewModel model where  model.company_id = ?1 order by model.product_qa_parameters_id Desc")
	List<CSmProductSrQaMappingModel> FnShowProductSrQaMappingActiveRecords(int company_id);


}
