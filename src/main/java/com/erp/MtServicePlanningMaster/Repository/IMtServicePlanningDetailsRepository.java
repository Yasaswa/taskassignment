package com.erp.MtServicePlanningMaster.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningDetailsModel;

public interface IMtServicePlanningDetailsRepository extends JpaRepository<CMtServicePlanningDetailsModel, Integer>{

	@Modifying
	@Transactional
	@Query(value = "update CMtServicePlanningDetailsModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.service_planning_master_transaction_id = ?1")
	void FnDeleteSrPlanningDetailsRecord(int service_planning_master_transaction_id, String deleted_by);

	
	
}
