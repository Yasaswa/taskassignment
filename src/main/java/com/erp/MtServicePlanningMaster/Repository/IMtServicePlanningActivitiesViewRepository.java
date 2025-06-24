package com.erp.MtServicePlanningMaster.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.MtServicePlanningMaster.Model.CMtServicePlanningActivitiesViewModel;

public interface IMtServicePlanningActivitiesViewRepository extends JpaRepository<CMtServicePlanningActivitiesViewModel, Integer>{

	
	@Query(value = "FROM CMtServicePlanningActivitiesViewModel model where model.is_delete = 0 and model.service_planning_master_transaction_id = ?1 and model.company_id = ?2")
	List<CMtServicePlanningActivitiesViewModel> FnShowSrPlanningActivitiesRecordForUpdate(
			int service_planning_master_transaction_id, int company_id);

}
