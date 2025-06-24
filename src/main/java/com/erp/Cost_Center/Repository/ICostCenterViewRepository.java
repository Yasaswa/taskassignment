package com.erp.Cost_Center.Repository;

import com.erp.Cost_Center.Model.CCostCenterViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface ICostCenterViewRepository extends JpaRepository<CCostCenterViewModel, Long> {

	@Query(value = "Select * FROM  fmv_cost_center order by cost_center_id Desc", nativeQuery = true)
	Page<CCostCenterViewModel> FnShowAllRecords(Pageable pageable);

	@Query(value = "Select * FROM  fmv_cost_center where is_delete =0 and company_id =?1  order by cost_center_id Desc", nativeQuery = true)
	Page<CCostCenterViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "Select * FROM  fmv_cost_center where is_delete =0 and cost_center_id = ?1 and company_id = ?2", nativeQuery = true)
	CCostCenterViewModel FnShowParticularRecordForUpdate(int cost_center_id, int company_id);

	@Query(nativeQuery = true, value = "Select * FROM  fmv_cost_center where is_delete =0 and company_id = ?1  and cost_center_id = ?2")
	CCostCenterViewModel FnShowParticularRecord(int company_id, int cost_center_id);

	@Query(value = "SELECT * FROM fmv_cost_center_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowReportRecords(Pageable pageable, int company_id);
}
