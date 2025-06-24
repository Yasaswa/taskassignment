package com.erp.MtDispatchScheduleDetails.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleInspectionDetailsTradingViewModel;


public interface IMtDispatchScheduleInspectionDetailsTradingViewRepository extends JpaRepository<CMtDispatchScheduleInspectionDetailsTradingViewModel, Integer> {

	
	@Query(value = "FROM CMtDispatchScheduleInspectionDetailsTradingViewModel model where model.is_delete = 0 and model.dispatch_schedule_no = ?1 AND model.dispatch_schedule_version = ?2  AND model.company_id = ?3")
	List<CMtDispatchScheduleInspectionDetailsTradingViewModel> FnShowDispatchScheduleInspectionDetailsRecords(String dispatch_schedule_no, int dispatch_schedule_version,
			int company_id);

}
