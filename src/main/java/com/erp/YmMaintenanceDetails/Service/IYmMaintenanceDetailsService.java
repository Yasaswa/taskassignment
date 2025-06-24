package com.erp.YmMaintenanceDetails.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IYmMaintenanceDetailsService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int maintenance_task_master_id, String deleted_by, int company_id);

	Map<String, Object> FnShowAllMaintenanceMasterAndDetailsRecords(int maintenance_task_master_id, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

}
