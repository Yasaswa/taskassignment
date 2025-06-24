package com.erp.FinishGoods.SmProductFgQaMapping.Repository;

import com.erp.FinishGoods.SmProductFgQaMapping.Model.CSmProductFgQaMappingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISmProductFgQaMappingViewRepository extends JpaRepository<CSmProductFgQaMappingViewModel, Integer> {

	@Query(value = "from CSmProductFgQaMappingViewModel")
	Page<CSmProductFgQaMappingViewModel> FnShowAllActiveRecords(Pageable pageable);

}
