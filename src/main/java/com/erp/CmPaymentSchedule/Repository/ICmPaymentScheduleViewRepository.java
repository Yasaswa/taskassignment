package com.erp.CmPaymentSchedule.Repository;

import com.erp.CmPaymentSchedule.Model.CCmPaymentScheduleViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ICmPaymentScheduleViewRepository extends JpaRepository<CCmPaymentScheduleViewModel, Integer> {

	@Query(value = "FROM CCmPaymentScheduleViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.payment_schedule_id Desc")
	Page<CCmPaymentScheduleViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CCmPaymentScheduleViewModel model where model.is_delete =0 and model.payment_schedule_id = ?1 and model.company_id = ?2 order by model.payment_schedule_id Desc")
	Page<CCmPaymentScheduleViewModel> FnShowParticularRecord(int payment_schedule_id, Pageable pageable,
	                                                         int company_id);

	@Query(value = "Select * From cmv_payment_schedule_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
