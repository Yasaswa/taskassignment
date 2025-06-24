package com.erp.MtDispatchScheduleDetails.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtDispatchScheduleDetails.Model.CMtDispatchSchedulePackingDetailsModel;


public interface IMtDispatchSchedulePackingDetailsRepository extends JpaRepository<CMtDispatchSchedulePackingDetailsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update CMtDispatchSchedulePackingDetailsModel model set model.is_delete = 1, model.deleted_by = ?4, model.deleted_on = CURRENT_TIMESTAMP() where model.dispatch_schedule_no = ?1 and model.dispatch_schedule_version = ?2 and model.company_id = ?3")
	void deleteDispatchSchedulePackingDetails(String dispatch_schedule_no, int dispatch_schedule_version,
			int company_id, String deleted_by);




}
