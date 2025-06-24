package com.erp.YmMaintenanceTaskActivity.Controller;

import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityModel;
import com.erp.YmMaintenanceTaskActivity.Model.CYmMaintenanceTaskActivityViewModel;
import com.erp.YmMaintenanceTaskActivity.Service.IYmMaintenanceTaskActivityService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;


@RestController
@RequestMapping("/api/YmMaintenanceTaskActivity")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CYmMaintenanceTaskActivityController {

	@Autowired
	IYmMaintenanceTaskActivityService iYmMaintenanceTaskActivityService;

	@PostMapping("/FnAddUpdateRecord")
	public String FnAddUpdateRecord(@RequestParam("CYmMaintenanceData") JSONObject jsonObject) {

		JSONObject resp = new JSONObject();
		resp = iYmMaintenanceTaskActivityService.FnAddUpdateRecord(jsonObject);
		return resp.toString();

	}

	@DeleteMapping("/FnDeleteRecord/{maintenance_task_activity_id}/{company_id}/{deleted_by}")
	public Object FnDeleteRecord(@PathVariable int maintenance_task_activity_id, @PathVariable int company_id, @PathVariable String deleted_by) {
		return iYmMaintenanceTaskActivityService.FnDeleteRecord(maintenance_task_activity_id, company_id, deleted_by);

	}

	@GetMapping("/FnShowAllActiveRecords/{company_id}")
	public Page<CYmMaintenanceTaskActivityViewModel> FnShowAllActiveRecords(Pageable pageable, @PathVariable int company_id) throws SQLException {
		Page<CYmMaintenanceTaskActivityViewModel> cYmMaintenanceTaskActivityViewModel = iYmMaintenanceTaskActivityService.FnShowAllActiveRecords(pageable, company_id);
		return cYmMaintenanceTaskActivityViewModel;
	}

	@GetMapping("/FnShowParticularRecordForUpdate/{maintenance_task_activity_id}/{company_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int maintenance_task_activity_id, @PathVariable int company_id) {
		return iYmMaintenanceTaskActivityService.FnShowParticularRecordForUpdate(maintenance_task_activity_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords/{company_id}")
	public Page<Map<String, Object>> FnShowAllReportRecords(Pageable pageable, @PathVariable int company_id) {
		return iYmMaintenanceTaskActivityService.FnShowAllReportRecords(pageable, company_id);

	}

	@GetMapping("/FnShowParticularRecords/{production_department_id}/{production_sub_department_id}/{company_id}")
	public Page<CYmMaintenanceTaskActivityModel> FnShowParticularRecord(@PathVariable int production_department_id, @PathVariable int production_sub_department_id, @PathVariable int company_id, Pageable pageable) {
		return iYmMaintenanceTaskActivityService.FnShowParticularRecord(production_department_id, production_sub_department_id, company_id, pageable);

	}


}
