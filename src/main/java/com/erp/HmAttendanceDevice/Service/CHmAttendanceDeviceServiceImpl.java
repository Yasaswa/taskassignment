package com.erp.HmAttendanceDevice.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.HmAttendanceDevice.Model.CHmAttendanceDeviceModel;
import com.erp.HmAttendanceDevice.Model.CHmDailyAttendanceProcess;
import com.erp.HmAttendanceDevice.Model.CHmattendanceLog;
import com.erp.HmAttendanceDevice.Repository.IHmAttendanceDeviceRepository;
import com.erp.HmAttendanceDevice.Repository.IHmAttendanceDeviceViewRepository;
import com.erp.HmAttendanceDevice.Repository.IHmDailyAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CHmAttendanceDeviceServiceImpl implements IHmAttendanceDeviceService {

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;

	@Autowired
	IHmDailyAttendanceRepository iHmDailyAttendanceRepository;
	@Autowired
	IHmAttendanceDeviceRepository iHmAttendanceDeviceRepository;

	@Autowired
	IHmAttendanceDeviceViewRepository iHmAttendanceDeviceViewRepository;

	@Override
	public Map<String, Object> FnAddUpdateRecord(CHmAttendanceDeviceModel cHmAttendanceDeviceModel) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CHmAttendanceDeviceModel> option = iHmAttendanceDeviceRepository
				.findById(cHmAttendanceDeviceModel.getAttendance_device_id());
		int company_id = cHmAttendanceDeviceModel.getCompany_id();
		try {
			if (option.isPresent()) {
				CHmAttendanceDeviceModel cHmAttendancedeviceModel = iHmAttendanceDeviceRepository
						.save(cHmAttendanceDeviceModel);
				responce.put("success", 1);
				responce.put("data", cHmAttendancedeviceModel);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");
				System.out.println(" Attendance Device Updated Successfully!..");
			} else {
				CHmAttendanceDeviceModel model = iHmAttendanceDeviceRepository
						.checkIfNameExist(cHmAttendanceDeviceModel.getAttendance_device_name());

				if (model != null) {
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("error", " Attendance Device  is already exist!");

					return responce;

				} else {

					CHmAttendanceDeviceModel respContent = iHmAttendanceDeviceRepository.save(cHmAttendanceDeviceModel);

					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/HmAttendanceDevice/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/HmAttendanceDevice/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}

	@Override
	public Map<String, Object> FnDeleteRecord(int attendance_device_id, String deleted_by) {
		Map<String, Object> responce = new HashMap<>();
		try {
			iHmAttendanceDeviceRepository.FnDeleteRecord(attendance_device_id, deleted_by);
			responce.put("success", 1);
		} catch (Exception e) {
			responce.put("success", 0);
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;

	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int company_id, int attendance_device_id) {
		Map<String, Object> responce = new HashMap<>();
		CHmAttendanceDeviceModel cHmAttendanceDeviceModel = null;
		try {
			cHmAttendanceDeviceModel = iHmAttendanceDeviceRepository.FnShowParticularRecordForUpdate(company_id,
					attendance_device_id);
			responce.put("success", 1);
			responce.put("data", cHmAttendanceDeviceModel);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}

		} catch (Exception e) {
			e.printStackTrace();
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}
	@Override
	public boolean isPortOpen(String remoteIp, int port) {
		try (Socket socket = new Socket(remoteIp, port)) {
			return true; // Port is open
		} catch (IOException e) {
			return false; // Port is closed or unreachable
		}
	}

	public Map<String, Object> FnShowParticularEmployeeCode(String attDeviceEmpCode, String attDateTime) {
		Map<String, Object> response = new HashMap<>();

			try {
				SimpleDateFormat formatters = new SimpleDateFormat("yyyy-MM-dd");
				Date attDate = formatters.parse(attDateTime);
				// Retrieve the attendance logs for the given employee code and date
				List<CHmattendanceLog> logs = iHmAttendanceDeviceRepository.findFirstAndLastLogsByEmployeeCodeAndToday(attDeviceEmpCode, attDate);
				CHmattendanceLog firstLog;
				CHmattendanceLog lastLog;

				if (logs.isEmpty()) {

					DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate localDate = LocalDate.parse(attDateTime, dateFormatter);
					LocalDateTime dateTimeAtStartOfDay = localDate.atStartOfDay();
					String formattedDateTime = dateTimeAtStartOfDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

					firstLog = new CHmattendanceLog();
					firstLog.setAtt_date_time(formattedDateTime);


				lastLog = firstLog;
			} else {
				firstLog = logs.get(0);
				lastLog = logs.size() == 1 ? firstLog : logs.get(1);
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime firstEntryTime = LocalDateTime.parse(firstLog.getAtt_date_time(), formatter);
			LocalDateTime lastEntryTime = LocalDateTime.parse(lastLog.getAtt_date_time(), formatter);

			Duration duration = Duration.between(firstEntryTime, lastEntryTime);
			long workingMinutes = duration.toMinutes();
			String dateOnly = firstEntryTime.toLocalDate().toString();

			Map<String, Object> data = new HashMap<>();
			data.put("attendance_date", firstEntryTime.getDayOfMonth());
			data.put("att_date_time", dateOnly);
			data.put("attendance_month", firstEntryTime.getMonthValue());
			data.put("attendance_year", firstEntryTime.getYear());
			data.put("in_time", firstEntryTime.toLocalTime().toString());
			data.put("out_time", lastEntryTime.toLocalTime().toString());
			data.put("working_minutes", workingMinutes);
			data.put("present_flag", logs.isEmpty() ? 'A' : (logs.size() == 1 ? 'M' : 'P')); // Set present_flag based on logs availability

			response.put("success", 1);
			response.put("data", data);
			response.put("employeeAllDetails",logs );
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				System.out.println(sqlEx.getMessage());
				response.put("success", 0);
				response.put("data", "");
				response.put("employeeAllDetails","");
				response.put("error", sqlEx.getMessage());
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("success", 0);
			response.put("data", "");
			response.put("employeeAllDetails","");
			response.put("error", e.getMessage());
			return response;
		}
		return response;
	}


	@Override
	public Map<String, Object> FnAddDailyAttendance(CHmDailyAttendanceProcess cHmDailyAttendanceProcess) {
		Map<String, Object> responce = new HashMap<>();
		Optional<CHmDailyAttendanceProcess> option = iHmDailyAttendanceRepository
				.findById(cHmDailyAttendanceProcess.getDaily_attendance_id());
		int company_id = cHmDailyAttendanceProcess.getCompany_id();
		try {
			if (option.isPresent()) {
				CHmDailyAttendanceProcess cHmdailyAttendanceProcess = iHmDailyAttendanceRepository
						.save(cHmDailyAttendanceProcess);
				responce.put("success", 1);
				responce.put("data", cHmdailyAttendanceProcess);
				responce.put("error", "");
				responce.put("message", "Record updated successfully!...");

			} else {

				Date att_date_time = cHmDailyAttendanceProcess.getAtt_date_time();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				String formattedDateString = dateFormat.format(att_date_time);
				Date formattedDate = dateFormat.parse(formattedDateString);
				CHmDailyAttendanceProcess model = iHmDailyAttendanceRepository.checkIfNameExist(cHmDailyAttendanceProcess.getEmployee_code(), formattedDate);

				if (model != null) {
					responce.put("success", 0);
					responce.put("data", "");
					responce.put("error", "Employee Daily Attendance is already exist!");
					return responce;

				} else {

					CHmDailyAttendanceProcess respContent = iHmDailyAttendanceRepository.save(cHmDailyAttendanceProcess);
					responce.put("success", 1);
					responce.put("data", respContent);
					responce.put("error", "");
					responce.put("message", "Record added successfully!...");
				}
			}
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
						"/api/HmAttendanceDevice/FnAddDailyAttendance", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", 0);
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api",
					"/api/HmAttendanceDevice/FnAddDailyAttendance", 0, e.getMessage());
			responce.put("success", 0);
			responce.put("data", "");
			responce.put("error", e.getMessage());

		}

		return responce;

	}
}
