package com.erp.FmProfitCenter.Repository;

import com.erp.FmProfitCenter.Model.CFmProfitCenterViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface IFmProfitCenterViewRepository extends JpaRepository<CFmProfitCenterViewModel, Integer> {

	@Query(value = "FROM CFmProfitCenterViewModel cdvm where cdvm.is_delete =0  order by cdvm.profit_center_id Desc")
	Page<CFmProfitCenterViewModel> FnShowAllActiveRecords(Pageable pageable);

	@Query(value = "FROM CFmProfitCenterViewModel cdvm where cdvm.is_delete =0 and cdvm.profit_center_id = ?1 order by cdvm.profit_center_id Desc")
	Page<CFmProfitCenterViewModel> FnShowParticularRecord(int profit_center_id, Pageable pageable);

	@Query(value = "SELECT * FROM fmv_profit_center_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);


}
