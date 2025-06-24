package com.erp.HmAttendanceDevice.Controller;

import com.erp.HmAttendanceDevice.Model.CHmAttendanceDeviceModel;
import com.erp.HmAttendanceDevice.Model.CHmDailyAttendanceProcess;
import com.erp.HmAttendanceDevice.Service.IHmAttendanceDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/HmAttendanceDevice")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CHmAttendanceDeviceController {

	@Autowired
	IHmAttendanceDeviceService iHmAttendanceDeviceService;

	@PostMapping("/FnAddUpdateRecord")
	public Map<String, Object> FnAddUpdateRecord(@RequestBody CHmAttendanceDeviceModel HmAttendanceDeviceModel) {
		Map<String, Object> resp = new HashMap<>();
		resp = iHmAttendanceDeviceService.FnAddUpdateRecord(HmAttendanceDeviceModel);
		return resp;

	}

	@DeleteMapping("/FnDeleteRecord/{attendance_device_id}/{deleted_by}")
	public Map<String, Object> FnDeleteRecord(@PathVariable int attendance_device_id, @PathVariable String deleted_by) {
		Map<String, Object> resp = iHmAttendanceDeviceService.FnDeleteRecord(attendance_device_id, deleted_by);
		return resp;

	}

	@GetMapping("/FnShowParticularRecordForUpdate/{company_id}/{attendance_device_id}")
	public Map<String, Object> FnShowParticularRecordForUpdate(@PathVariable int company_id,
	                                                           @PathVariable int attendance_device_id) {
		return iHmAttendanceDeviceService.FnShowParticularRecordForUpdate(company_id, attendance_device_id);
	}
	@GetMapping("/check-port")
	public String checkPort(@RequestParam String remoteIp, @RequestParam int port) {
		boolean isPortOpen = iHmAttendanceDeviceService.isPortOpen(remoteIp, port);
		if (isPortOpen) {
			return "Port " + port + " is open on " + remoteIp;
		} else {
			return "Port " + port + " is closed or unreachable on " + remoteIp;
		}
	}

	@GetMapping("/FnShowParticularEmployeeCode/{att_device_emp_code}/{att_date_time}")
	public Map<String, Object> FnShowParticularEmployeeCode(@PathVariable String att_device_emp_code,@PathVariable String att_date_time) {
		return iHmAttendanceDeviceService.FnShowParticularEmployeeCode(att_device_emp_code,att_date_time);
	}
	@PostMapping("/FnAddDailyAttendance")
	public Map<String, Object> FnAddDailyAttendance(@RequestBody CHmDailyAttendanceProcess HmDailyAttendanceProcess) {
		Map<String, Object> resp = new HashMap<>();
		resp = iHmAttendanceDeviceService.FnAddDailyAttendance(HmDailyAttendanceProcess);
		return resp;

	}

}
