package com.erp.YmMaintenanceDetails.Repository;

import com.erp.YmMaintenanceDetails.Model.CYmMaintenanceTaskDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IYmMaintenanceTaskDetailsRepository extends JpaRepository<CYmMaintenanceTaskDetailsModel, Integer> {

	@Modifying
	@Transactional
	@Query(value = "update ym_maintenance_task_details set is_delete = 1, deleted_on = CURRENT_TIMESTAMP() where maintenance_task_master_id = ?1 and company_id = ?2", nativeQuery = true)
	void updateStatus(int maintenance_task_master_id, int company_id);


	@Modifying
	@Transactional
	@Query(value = "update ym_maintenance_task_details set is_delete = 1, deleted_by = ?2, deleted_on = CURRENT_TIMESTAMP() where maintenance_task_master_id = ?1 and company_id = ?3", nativeQuery = true)
	void deleteMaintenanceDetails(int maintenance_task_master_id, String deleted_by, int company_id);

}
