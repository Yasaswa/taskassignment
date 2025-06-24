package com.erp.HmManualAttendance.Service;

import java.util.Map;

import org.json.JSONObject;


public interface IHmManualAttendanceService {

	Map<String, Object> FnAddUpdateRecord(JSONObject jsonObject);

	Map<String, Object> FnDeleteRecord(int attendance_transaction_id, String deleted_by);

//	Map<String, Object> FnShowParticularRecordForUpdate(int attendance_transaction_id, int company_id);

	Map<String, Object> FnShowMannualAttendanceRecordForUpdate(JSONObject jsonObject);


}
