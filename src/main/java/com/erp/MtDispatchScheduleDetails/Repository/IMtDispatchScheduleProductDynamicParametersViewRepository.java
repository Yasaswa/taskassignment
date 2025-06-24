package com.erp.MtDispatchScheduleDetails.Repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchScheduleProductDynamicParametersViewModel;


public interface IMtDispatchScheduleProductDynamicParametersViewRepository extends JpaRepository<CMtDispatchScheduleProductDynamicParametersViewModel, Integer> {

	@Query(value = "FROM CMtDispatchScheduleProductDynamicParametersViewModel model where model.is_delete = 0 and model.dispatch_schedule_no = ?1 AND model.dispatch_schedule_version = ?2  AND model.company_id = ?3")
	List<CMtDispatchScheduleProductDynamicParametersViewModel> FnShowDispatchScheduleProductDynamicParametersRecords(
			String dispatch_schedule_no, int dispatch_schedule_version, int company_id);


	@Query(value = "FROM CMtDispatchScheduleProductDynamicParametersViewModel model where model.is_delete = 0 and model.dispatch_schedule_no IN ?1 AND model.company_id = ?2")
	List<CMtDispatchScheduleProductDynamicParametersViewModel> FnShowDispatchSchedulePropertiesParametersRecords(List<String> dispatchScheduleNosList, int companyId);
}
