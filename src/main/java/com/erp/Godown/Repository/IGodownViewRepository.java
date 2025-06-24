package com.erp.Godown.Repository;

import com.erp.Godown.Model.CGodownViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IGodownViewRepository extends JpaRepository<CGodownViewModel, Long> {

	@Query(value = "Select * FROM  cmv_godown order by godown_id Desc", nativeQuery = true)
	Page<CGodownViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_godown where is_delete =0  order by godown_id Desc", nativeQuery = true)
	Page<CGodownViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "Select * FROM  cmv_godown where is_delete =0 and godown_id = ?1", nativeQuery = true)
	CGodownViewModel FnShowParticularRecordForUpdate(int godown_id);

	@Query(nativeQuery = true, value = "Select * FROM  cmv_godown where is_delete =0 and company_id = ?1 and godown_id = ?2")
	CGodownViewModel FnShowParticularRecord(int company_id, int godown_id);

	@Query(value = "SELECT * FROM cmv_godown_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable);


}
