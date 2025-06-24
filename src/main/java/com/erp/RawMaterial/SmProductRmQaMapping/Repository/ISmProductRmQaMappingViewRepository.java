package com.erp.RawMaterial.SmProductRmQaMapping.Repository;

import com.erp.RawMaterial.SmProductRmQaMapping.Model.CSmProductRmQaMappingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISmProductRmQaMappingViewRepository extends JpaRepository<CSmProductRmQaMappingViewModel, Integer> {

	@Query(value = "from CSmProductRmQaMappingViewModel model where model.company_id = ?1")
	Page<CSmProductRmQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

}
