package com.erp.YmMaintenanceDetails.Controller;

import com.erp.YmMaintenanceDetails.Service.IYmMaintenanceDetailsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/YmMaintenanceDetails")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CYmMaintenanceDetailsController {

	@Autowired
	IYmMaintenanceDetailsService iYmMaintenanceDetailsService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("YmMaintenanceData") JSONObject jsonObject) {
		Map<String, Object> responce = iYmMaintenanceDetailsService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{maintenance_task_master_id}/{deleted_by}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int maintenance_task_master_id, @PathVariable String deleted_by, @PathVariable int company_id) {
		return iYmMaintenanceDetailsService.FnDeleteRecord(maintenance_task_master_id, deleted_by, company_id);
	}

	// use for edit
	@GetMapping("/FnShowAllMaintenanceMasterAndDetailsRecords/{maintenance_task_master_id}/{company_id}")
	public Map<String, Object> FnShowAllMaintenanceMasterAndDetailsRecords(@PathVariable int maintenance_task_master_id, @PathVariable int company_id)
			throws SQLException {
		return iYmMaintenanceDetailsService.FnShowAllMaintenanceMasterAndDetailsRecords(maintenance_task_master_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iYmMaintenanceDetailsService.FnShowAllReportRecords(pageable, reportType);

	}

}
