package com.erp.MtDispatchScheduleDetails.Repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchSchedulePackingDetailsViewModel;


public interface IMtDispatchSchedulePackingDetailsViewRepository extends JpaRepository<CMtDispatchSchedulePackingDetailsViewModel, Integer> {

	
	@Query(value = "FROM CMtDispatchSchedulePackingDetailsViewModel model where model.is_delete = 0 and model.dispatch_schedule_no = ?1 AND model.dispatch_schedule_version = ?2  AND model.financial_year = ?3 AND model.company_id = ?4")
	List<CMtDispatchSchedulePackingDetailsViewModel> FnShowDispatchSchedulePackingDetailsRecords(
			String dispatch_schedule_no, int dispatch_schedule_version, String financial_year, int company_id);	
	
	@Query(value = "FROM CMtDispatchSchedulePackingDetailsViewModel model where model.is_delete = 0 and model.dispatch_schedule_no IN ?1 and model.company_id = ?2 order by dispatch_schedule_packing_id asc")
	List<CMtDispatchSchedulePackingDetailsViewModel> FnShowdispatchSchedulePackingDetails(
			List<String> dispatchScheduleNoList, int company_id);

}
