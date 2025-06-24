package com.erp.YmMaintenanceDetails.Repository;

import com.erp.YmMaintenanceDetails.Model.CYmMaintenanceTaskDetailsViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IYmMaintenanceTaskDetailsViewRepository extends JpaRepository<CYmMaintenanceTaskDetailsViewModel, Integer> {

	@Query(value = "from CYmMaintenanceTaskDetailsViewModel model where model.is_delete = 0 and model.maintenance_task_master_id = ?1 and model.company_id = ?2")
	List<CYmMaintenanceTaskDetailsViewModel> FnShowMaintenanceDetailsRecords(int maintenance_task_master_id,
	                                                                         int company_id);

}
