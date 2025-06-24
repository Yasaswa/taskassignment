package com.erp.HmEarningHeads.Repository;

import com.erp.HmEarningHeads.Model.CHmEarningHeadsRptModel_Not_Used;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHmEarningHeadsRptRepository_Not_Used extends JpaRepository<CHmEarningHeadsRptModel_Not_Used, Integer> {

//	@Query(value ="select * FROM hmv_earning_heads_rpt", nativeQuery = true)
//	Page<CHmEarningHeadsRptModel> FnShowAllReportRecords(Pageable pageable, int company_id);

}
