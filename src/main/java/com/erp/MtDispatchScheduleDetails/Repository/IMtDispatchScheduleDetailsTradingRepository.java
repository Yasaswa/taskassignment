package com.erp.MtDispatchScheduleDetails.Repository;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleDetailsTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


public interface IMtDispatchScheduleDetailsTradingRepository extends JpaRepository<CMtDispatchScheduleDetailsTradingModel, Integer> {

	@Query(value = "FROM CMtDispatchScheduleDetailsTradingModel model where model.is_delete =0 and model.dispatch_schedule_details_transaction_id = ?1 and model.company_id = ?2")
	CMtDispatchScheduleDetailsTradingModel FnShowParticularRecordForUpdate(int dispatch_schedule_details_transaction_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_dispatch_schedule_details_trading set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where dispatch_schedule_no = ?1 and dispatch_schedule_version = ?2 and financial_year = ?3 and company_id = ?4", nativeQuery = true)
	void updateStatus(String dispatch_schedule_no, Integer dispatch_schedule_version, String financial_year,
	                  int company_id);


	@Modifying
	@Transactional
	@Query(value = "update mt_dispatch_schedule_details_trading set is_delete = 1,deleted_by=?4, deleted_on = CURRENT_TIMESTAMP() where dispatch_schedule_no = ?1 and dispatch_schedule_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteDispatchDetails(String dispatch_schedule_no,
	                           int dispatch_schedule_version, int company_id, String deleted_by);


	@Query(value = "SELECT * FROM mtv_dispatch_schedule_details_trading WHERE dispatch_schedule_no = ?1 and dispatch_schedule_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	List<Map<String, Object>> FnShowDispatchScheduleDetailsRecords(String dispatch_schedule_no,
	                                                               int dispatch_schedule_version, String financial_year, int company_id);


	@Query(value = "FROM CMtDispatchScheduleDetailsTradingModel model where model.is_delete=0 and model.company_id = ?1  AND model.dispatch_schedule_no IN ?2")
	List<CMtDispatchScheduleDetailsTradingModel> FnShowDispatchScheduleDetailsByDispScheduleNos(int company_id, List<String> dispatchScheduleNosList);


	@Query(value = "SELECT DISTINCT model.dispatch_schedule_no, model.dispatch_schedule_master_transaction_id FROM CMtDispatchScheduleDetailsTradingModel model WHERE model.is_delete = 0 AND model.company_id = ?1 AND model.customer_order_no IN ?2 AND model.dispatch_schedule_item_status IN ('A', 'I','C')")
	List<Object[]> FnShowDispatchScheduleMasterNos(int company_id,
												   List<String> customerOrderNosList);
}
