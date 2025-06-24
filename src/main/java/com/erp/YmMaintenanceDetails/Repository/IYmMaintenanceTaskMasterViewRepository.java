package com.erp.YmMaintenanceDetails.Repository;

import com.erp.YmMaintenanceDetails.Model.CYmMaintenanceTaskMasterViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IYmMaintenanceTaskMasterViewRepository extends JpaRepository<CYmMaintenanceTaskMasterViewModel, Integer> {

	@Query(value = "from CYmMaintenanceTaskMasterViewModel model where model.is_delete = 0 and model.maintenance_task_master_id = ?1 and model.company_id = ?2")
	CYmMaintenanceTaskMasterViewModel FnShowMaintenanceMasterRecords(int maintenance_task_master_id, int company_id);

}
