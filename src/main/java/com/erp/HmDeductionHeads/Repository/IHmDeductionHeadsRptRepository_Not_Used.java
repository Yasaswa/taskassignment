package com.erp.HmDeductionHeads.Repository;

import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHmDeductionHeadsRptRepository_Not_Used extends JpaRepository<CHmDeductionHeadsRptModel_Not_Used, Integer> {

//	@Query(value ="select * FROM hmv_deduction_heads_rpt", nativeQuery = true)
//	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
