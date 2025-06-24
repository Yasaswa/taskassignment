package com.erp.HmAttendanceDevice.Service;

import com.erp.HmAttendanceDevice.Model.CHmAttendanceDeviceModel;
import com.erp.HmAttendanceDevice.Model.CHmDailyAttendanceProcess;

import java.util.Map;

public interface IHmAttendanceDeviceService {

	Map<String, Object> FnAddUpdateRecord(CHmAttendanceDeviceModel cHmAttendanceDeviceModel);

	Map<String, Object> FnDeleteRecord(int company_id, String deleted_by);

	Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int attendance_device_id);


	boolean isPortOpen(String remoteIp, int port);

	Map<String, Object> FnShowParticularEmployeeCode(String attDeviceEmpCode ,String attDateTime);

	Map<String, Object> FnAddDailyAttendance(CHmDailyAttendanceProcess hmDailyAttendanceProcess);

}
