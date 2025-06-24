package com.erp.MtDispatchScheduleDetails.Repository;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleMasterTradingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public interface IMtDispatchScheduleMasterTradingRepository
		extends JpaRepository<CMtDispatchScheduleMasterTradingModel, Integer> {

	@Query(value = "FROM CMtDispatchScheduleMasterTradingModel model where model.is_delete =0 and model.dispatch_schedule_master_transaction_id = ?1 and model.company_id = ?2")
	CMtDispatchScheduleMasterTradingModel FnShowParticularRecordForUpdate(int dispatch_schedule_master_transaction_id,
	                                                                      int company_id);

	@Modifying
	@Transactional
	@Query(value = "update mt_dispatch_schedule_master_trading set is_delete = 1,deleted_by=?4, deleted_on = CURRENT_TIMESTAMP() where dispatch_schedule_no = ?1 and dispatch_schedule_version = ?2 and company_id = ?3", nativeQuery = true)
	void deleteDispatchMaster(String dispatch_schedule_no, int dispatch_schedule_version, int company_id,
	                          String deleted_by);

	@Query(value = "Select * from mt_dispatch_schedule_master_trading where is_delete = 0 and dispatch_schedule_no = ?1 and company_id = ?2", nativeQuery = true)
	CMtDispatchScheduleMasterTradingModel getLastDispatchScheduleVersion(String dispatch_schedule_no,
	                                                                     String company_id);

	@Query(value = "SELECT * FROM mtv_dispatch_schedule_master_trading  WHERE dispatch_schedule_no = ?1 and dispatch_schedule_version = ?2 and financial_year = ?3 and company_id= ?4", nativeQuery = true)
	Map<String, Object> FnShowDispatchScheduleMasterRecord(String dispatch_schedule_no, int dispatch_schedule_version,
	                                                       String financial_year, int company_id);

	@Query(value = "FROM CMtDispatchScheduleMasterTradingModel model where model.is_delete=0 and model.company_id = ?1 AND model.dispatch_schedule_no IN ?2")
	List<CMtDispatchScheduleMasterTradingModel> FnShowDispatchSchedulesByDispSchNos(int company_id,
	                                                                                List<String> dispScheduleNosList);

}
