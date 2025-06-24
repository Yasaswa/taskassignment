package com.erp.XmProductionElectricalMeter.Controller;

import com.erp.XmProductionElectricalMeter.Service.IXmProductionElectricalMeterService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/api/XmProductionElectricalMeter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CXmProductionElectricalMeterController {

	@Autowired
	IXmProductionElectricalMeterService iXmProductionElectricalMeterService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestParam("XmElectricalMeterData") JSONObject jsonObject) {
		Map<String, Object> responce = iXmProductionElectricalMeterService.FnAddUpdateRecord(jsonObject);
		return responce;

	}

	@DeleteMapping("/FnDeleteRecord/{prod_electrical_meter_id}/{deleted_by}/{company_id}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int prod_electrical_meter_id, @PathVariable String deleted_by, @PathVariable int company_id) {
		return iXmProductionElectricalMeterService.FnDeleteRecord(prod_electrical_meter_id, deleted_by, company_id);
	}

	// use for edit
	@GetMapping("/FnShowAllElectricalMeterAndMappingRecords/{prod_electrical_meter_id}/{company_id}")
	public Map<String, Object> FnShowAllElectricalMeterAndMappingRecords(@PathVariable int prod_electrical_meter_id, @PathVariable int company_id)
			throws SQLException {
		return iXmProductionElectricalMeterService.FnShowAllElectricalMeterAndMappingRecords(prod_electrical_meter_id, company_id);
	}

	@GetMapping("/FnShowAllReportRecords")
	public Map<String, Object> FnShowAllReportRecords(Pageable pageable, @RequestParam("reportType") String reportType) throws SQLException {
		return iXmProductionElectricalMeterService.FnShowAllReportRecords(pageable, reportType);

	}

}
