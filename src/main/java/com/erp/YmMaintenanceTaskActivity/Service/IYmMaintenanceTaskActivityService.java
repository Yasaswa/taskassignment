package com.erp.YmMaintenanceTaskActivity.Service;

import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityModel;
import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityViewModel;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IYmMaintenanceTaskActivityService {

	JSONObject FnAddUpdateRecord(JSONObject jsonObject);

	Object FnDeleteRecord(int maintenance_task_activity_id, int company_id, String deleted_by);

	Page<CYmMaintenanceTaskActivityViewModel> FnShowAllActiveRecords(Pageable pageable, int company_id);

	Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, int company_id);

	Map<String, Object> FnShowParticularRecordForUpdate(int maintenance_task_activity_id, int company_id);

	Page<CYmMaintenanceTaskActivityModel> FnShowParticularRecord(int production_department_id,
	                                                             int production_sub_department_id, int company_id, Pageable pageable);


}
