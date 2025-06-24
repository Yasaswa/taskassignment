package com.erp.MtDispatchScheduleDetails.Repository;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleDetailsTradingViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IMtDispatchScheduleDetailsTradingViewRepository extends JpaRepository<CMtDispatchScheduleDetailsTradingViewModel, Integer> {

	@Query(value = "FROM CMtDispatchScheduleDetailsTradingViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.dispatch_schedule_details_transaction_id Desc")
	Page<CMtDispatchScheduleDetailsTradingViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);


	@Query(value = "FROM CMtDispatchScheduleDetailsTradingViewModel model where model.is_delete =0 and model.dispatch_schedule_details_transaction_id = ?1 and model.company_id = ?2 order by model.dispatch_schedule_details_transaction_id Desc")
	Page<CMtDispatchScheduleDetailsTradingViewModel> FnShowParticularRecord(
			int dispatch_schedule_details_transaction_id, Pageable pageable, int company_id);

	@Query(value = "Select * FROM mtv_dispatch_schedule_details_trading_sales_order where is_delete = 0 and dispatch_schedule_no = ?1 and dispatch_schedule_version = ?2 and company_id = ?3", nativeQuery = true)
	List<Map<String, Object>> FnShowDispatchScheduleDetailsTradingRecords(String dispatch_schedule_no, int dispatch_schedule_version, int company_id);

	// Added the item_status filter due to it is required into the disp_challan.
	@Query(value = "FROM CMtDispatchScheduleDetailsTradingViewModel model where model.is_delete=0 and model.company_id = ?1  AND model.dispatch_schedule_no IN ?2 and model.dispatch_schedule_item_status NOT IN ('Z', 'C', 'R')")
	List<CMtDispatchScheduleDetailsTradingViewModel> FnShowDispatchScheduleDetails(int company_id, List<String> dispatchScheduleNosList);


	@Query(value = "SELECT DISTINCT model.dispatch_schedule_no, model.dispatch_schedule_master_transaction_id FROM CMtDispatchScheduleDetailsTradingViewModel model WHERE model.is_delete = 0 AND model.company_id = ?1 AND model.customer_order_no IN ?2 AND model.dispatch_note_status IN ('A', 'I','C')")
	List<Object[]> FnShowDispatchScheduleMasterNos(int company_id,
	                                               List<String> customerOrderNosList);

	@Query(value = "select * FROM mtv_dispatch_schedule_details_trading_rpt", nativeQuery = true)
	Map<String, Object> FnShowAlldetailsReportRecords();

}
