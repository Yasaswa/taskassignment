package com.erp.YmMaintenanceTaskActivity.Repository;

import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;


public interface IYmMaintenanceTaskActivityViewRepository extends JpaRepository<CYmMaintenanceTaskActivityViewModel, Integer> {

	@Query(value = "FROM CYmMaintenanceTaskActivityViewModel model where model.is_delete = 0 and model.company_id = ?1 order by model.maintenance_task_activity_id Desc")
	Page<CYmMaintenanceTaskActivityViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	@Query(value = "FROM CYmMaintenanceTaskActivityViewModel model  where model.is_delete =0 and model.maintenance_task_activity_id = ?1 and model.company_id = ?2 order by model.maintenance_task_activity_id Desc")
	Page<CYmMaintenanceTaskActivityViewModel> FnShowParticularRecord(int maintenance_task_activity_id, int company_id, Pageable pageable);

	@Query(value = "SELECT * FROM ymv_maintenance_task_activity_rpt", nativeQuery = true)
	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable);


}
