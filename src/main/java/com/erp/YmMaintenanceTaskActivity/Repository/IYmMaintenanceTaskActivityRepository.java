package com.erp.YmMaintenanceTaskActivity.Repository;

import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface IYmMaintenanceTaskActivityRepository extends JpaRepository<CYmMaintenanceTaskActivityModel, Integer> {

	@Query(value = "FROM CYmMaintenanceTaskActivityModel model where model.is_delete =0 and model.maintenance_task_activity_id = ?1 and model.company_id = ?2")
	CYmMaintenanceTaskActivityModel FnShowParticularRecordForUpdate(int maintenance_task_activity_id, int company_id);

	@Modifying
	@Transactional
	@Query(value = "update ym_maintenance_task_activity set is_delete = 1 , deleted_on = CURRENT_TIMESTAMP() where production_department_id = ?1 and  production_sub_department_id = ?2 and company_id = ?3", nativeQuery = true)
	Object updateMaintenanceTaskActivity(int production_department_id, int production_sub_department_id, int company_id);

	@Query(value = "SELECT * FROM ymv_maintenance_task_activity  where  production_department_id=?1 and production_sub_department_id=?2 and company_id =?3 order by maintenance_task_activity_id Desc", nativeQuery = true)
	Page<CYmMaintenanceTaskActivityModel> FnShowParticularRecord(int production_department_id,
	                                                             int production_sub_department_id, int company_id, Pageable pageable);


}
