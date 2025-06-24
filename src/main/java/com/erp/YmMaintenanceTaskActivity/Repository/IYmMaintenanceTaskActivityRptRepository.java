package com.erp.YmMaintenanceTaskActivity.Repository;

import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityRptModel_Not_Used;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface IYmMaintenanceTaskActivityRptRepository extends JpaRepository<CYmMaintenanceTaskActivityRptModel_Not_Used, String> {

	@Query(value = "SELECT * FROM ymv_maintenance_task_activity_rpt", nativeQuery = true)
	Page<List<Map<String, Object>>> FnShowAllReportRecords(Pageable pageable, int company_id);

}
