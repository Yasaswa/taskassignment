package com.erp.MtServicePlanningMaster.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningMasterModel;


public interface IMtServicePlanningMasterRepository extends JpaRepository<CMtServicePlanningMasterModel, Integer> {
	
	@Modifying
	@Transactional
	@Query(value = "update CMtServicePlanningMasterModel model set model.is_delete = 1 , model.deleted_by = ?2 , model.deleted_on = CURRENT_TIMESTAMP() where model.service_planning_master_transaction_id = ?1")
	void FnDeleteSrPlanningMasterRecord(int service_planning_master_transaction_id, String deleted_by);

	@Query(value = "FROM CMtServicePlanningMasterModel model where model.is_delete = 0 and model.service_planning_master_transaction_id = ?1 and model.company_id = ?2")
	CMtServicePlanningMasterModel FnShowSrPlanningMasterRecordForUpdate(int service_planning_master_transaction_id,
			int company_id);


}
