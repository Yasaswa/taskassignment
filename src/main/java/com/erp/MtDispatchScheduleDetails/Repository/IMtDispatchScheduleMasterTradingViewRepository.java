package com.erp.MtDispatchScheduleDetails.Repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleMasterTradingViewModel;

public interface IMtDispatchScheduleMasterTradingViewRepository extends JpaRepository<CMtDispatchScheduleMasterTradingViewModel, Integer> {


	@Query(value = "FROM CMtDispatchScheduleMasterTradingViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.dispatch_schedule_master_transaction_id Desc")
	Page<CMtDispatchScheduleMasterTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CMtDispatchScheduleMasterTradingViewModel model where model.is_delete = 0 and model.dispatch_schedule_master_transaction_id = ?1 and model.company_id =?2 order by model.dispatch_schedule_master_transaction_id Desc")
	Page<CMtDispatchScheduleMasterTradingViewModel> FnShowParticularRecord(int dispatch_schedule_master_transaction_id,
	                                                                       Pageable pageable, int company_id);


	@Query(value = "select * FROM mtv_dispatch_schedule_master_trading_rpt", nativeQuery = true)
	Map<String, Object> FnShowAllSummaryReportRecords();

//	@Query(value = "SELECT * FROM mtv_dispatch_schedule_master_trading  WHERE dispatch_schedule_no = ?1 and dispatch_schedule_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	@Query(value = "FROM CMtDispatchScheduleMasterTradingViewModel model WHERE dispatch_schedule_no = ?1 and dispatch_schedule_version = ?2 and financial_year = ?3 and company_id= ?4")
	CMtDispatchScheduleMasterTradingViewModel FnShowDispatchScheduleMasterRecord(String dispatch_schedule_no, int dispatch_schedule_version,
	                                                       String financial_year, int company_id);

}
