package com.erp.HmEarningHeads.Repository;

import com.erp.HmEarningHeads.Model.CHmEarningHeadsViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IHmEarningHeadsViewRepository extends JpaRepository<CHmEarningHeadsViewModel, Integer> {

	@Query(value = "FROM CHmEarningHeadsViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.earning_heads_id Desc")
	Page<CHmEarningHeadsViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CHmEarningHeadsViewModel model where model.is_delete =0 and model.earning_heads_id = ?1 and model.company_id = ?2 order by model.earning_heads_id Desc")
	Page<CHmEarningHeadsViewModel> FnShowParticularRecord(int earning_heads_id, Pageable pageable, int company_id);

	@Query(value = "select * FROM hmv_earning_heads_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CHmEarningHeadsViewModel model where model.is_delete = 0 " +
//			"and model.company_id = ?1" +
			"")
	List<CHmEarningHeadsViewModel> FnShowEarningHeadMapingRecords(int company_id);

}
