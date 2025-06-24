package com.erp.HmDeductionHeads.Repository;

import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IHmDeductionHeadsViewRepository extends JpaRepository<CHmDeductionHeadsViewModel, Integer> {

	@Query(value = "FROM CHmDeductionHeadsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.deduction_heads_id Desc")
	Page<CHmDeductionHeadsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


	@Query(value = "FROM CHmDeductionHeadsViewModel model where model.is_delete =0 and model.deduction_heads_id = ?1 and model.company_id = ?2 order by model.deduction_heads_id Desc")
	Page<CHmDeductionHeadsViewModel> FnShowParticularRecord(int deduction_heads_id, Pageable pageable, int company_id);

	@Query(value = "select * FROM hmv_deduction_heads_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CHmDeductionHeadsViewModel model "
//			+
//			"where model.company_id = ?1"
	)
	List<CHmDeductionHeadsViewModel> FnShowDeductionMapingRecords(int company_id);

}
