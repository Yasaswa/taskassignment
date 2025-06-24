package com.erp.XmProductionElectricalMeter.Service;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface IXmProductionElectricalMeterService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int prod_electrical_meter_id, String deleted_by, int company_id);

	Map<String, Object> FnShowAllElectricalMeterAndMappingRecords(int prod_electrical_meter_id, int company_id);

	Map<String, Object> FnShowAllReportRecords(Pageable pageable, String reportType);

}
