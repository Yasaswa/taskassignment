package com.erp.HtShiftRoster.Controller;

import com.erp.HtShiftRoster.Model.CHtShiftRosterModel;
import com.erp.HtShiftRoster.Repository.IHtShiftRosterRepository;
import com.erp.HtShiftRoster.Service.IHtShiftRosterService;
import com.erp.HtShiftRosterNew.Model.CHtShiftRosterNewModel;
import com.erp.HtShiftRosterNew.Repository.IHtShiftRosterNewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/shiftRoster")
public class CHtShiftRosterController {

	private final IHtShiftRosterRepository iHtShiftRosterRepository;

	private final IHtShiftRosterService iHtShiftRosterService;

	private final IHtShiftRosterNewRepository iHtShiftRosterNewRepository;


	@PostMapping("/FnShowShiftRosterEmployeeData")
	public List<Map<String, Object>> FnShowShiftRosterEmployeeData(@RequestParam("getShiftRosterDetails") String getShiftRosterDetailsStr) {
		JSONObject jsonObject = new JSONObject(getShiftRosterDetailsStr); // Convert the string to a JSONObject
		return iHtShiftRosterService.FnShowShiftRosterEmployeeData(jsonObject);
	}

	@PostMapping("/FnAddUpdateShiftRosterDetails")
	public Map<String, Object> FnAddUpdateAttendanceDetails(@RequestParam("getShiftRosterDetails") String getShiftRosterDetailsStr) {
		Map<String, Object> response = new LinkedHashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Parse the incoming JSON string
			JSONObject getShiftRosterDetails = new JSONObject(getShiftRosterDetailsStr);
			JSONObject shiftRosterMasterData = getShiftRosterDetails.getJSONObject("ShiftRoseterMasterData");
			JSONArray shiftDetailsData = getShiftRosterDetails.getJSONArray("shiftDetailsData");
			JSONArray currentShiftDetailsData = getShiftRosterDetails.getJSONArray("currentShiftDetailsData");
			String employee_type = shiftRosterMasterData.getString("employee_type");

			// Convert JSON to model for shiftRosterMasterData
			CHtShiftRosterModel attendanceRequests = objectMapper.readValue(shiftRosterMasterData.toString(), CHtShiftRosterModel.class);

			List<CHtShiftRosterModel> addShiftRosterDeatils = new ArrayList<>();

			for (int i = 0; i < shiftDetailsData.length(); i++) {
				JSONObject shiftDetail = shiftDetailsData.getJSONObject(i);

				// Extract necessary fields
				int fieldId = shiftDetail.getInt("field_id");
				String employee_code = shiftDetail.getString("employee_code");
				int departmentId = shiftDetail.getInt("department_id");
				int subDepartmentId = shiftDetail.getInt("sub_department_id");


				// Retrieve existing records for the specified month and employee
				List<CHtShiftRosterModel> existingRecords = iHtShiftRosterRepository.findByEmployee_code(
						employee_code,
						attendanceRequests.getAttendance_month(),
						attendanceRequests.getAttendance_year()
				);
				Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
				CHtShiftRosterModel mergedRecord = new CHtShiftRosterModel();
				CHtShiftRosterModel addMergeRecord = new CHtShiftRosterModel();

				// Merge existing records with the new data
				if (!existingRecords.isEmpty()) {
					// Assuming there's only one record per employee per month
					mergedRecord = existingRecords.get(0);
					BeanUtils.copyProperties(mergedRecord, addMergeRecord);

					// Mark all existing records as deleted
					for (CHtShiftRosterModel record : existingRecords) {
						record.setIs_delete(true);
						iHtShiftRosterRepository.save(record);
					}
				} else {
					addMergeRecord.setCompany_id(attendanceRequests.getCompany_id());
					addMergeRecord.setCompany_branch_id(attendanceRequests.getCompany_branch_id());
					addMergeRecord.setEmployee_type(attendanceRequests.getEmployee_type());
					addMergeRecord.setAttendance_month(attendanceRequests.getAttendance_month());
					addMergeRecord.setAttendance_year(attendanceRequests.getAttendance_year());
					addMergeRecord.setProcess_date(currentDate);
					addMergeRecord.setRoster_id(attendanceRequests.getRoster_id());
					addMergeRecord.setFinancial_year(attendanceRequests.getFinancial_year());
					addMergeRecord.setCreated_by(attendanceRequests.getCreated_by());
				}

				// Update employee-specific fields
				addMergeRecord.setRoster_process_id(0);
				addMergeRecord.setEmployee_id(fieldId);
				addMergeRecord.setEmployee_type(employee_type);
				addMergeRecord.setProcess_date(currentDate);
				addMergeRecord.setEmployee_code(employee_code);
				addMergeRecord.setDepartment_id(departmentId);
				addMergeRecord.setSub_department_id(subDepartmentId);

				// Process shift details
				Iterator<String> keys = shiftDetail.keys();
				while (keys.hasNext()) {
					String key = keys.next();

					// Check if the key matches the pattern \d+/\\d+
					if (key.matches("\\d+/\\d+")) {
						String[] parts = key.split("/");
						int day = Integer.parseInt(parts[0]);
						String shiftIdField = "shift_id" + day;
						String shiftNameField = "shift_name" + day;

						try {
							// Get the fields using reflection
							Field shiftIdFieldObj = mergedRecord.getClass().getDeclaredField(shiftIdField);
							Field shiftNameFieldObj = mergedRecord.getClass().getDeclaredField(shiftNameField);

							// Ensure accessibility of private fields
							shiftIdFieldObj.setAccessible(true);
							shiftNameFieldObj.setAccessible(true);

							// Extract id and Name from the nested JSON object
							JSONObject nestedObject = shiftDetail.getJSONObject(key);
							String id = nestedObject.getString("id");
							String name = nestedObject.getString("Name");

							// Set the values in the model object
							shiftIdFieldObj.set(addMergeRecord, id);
							shiftNameFieldObj.set(addMergeRecord, name);
						} catch (NoSuchFieldException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}

				addShiftRosterDeatils.add(addMergeRecord);
			}

//			This is for current shift
			List<CHtShiftRosterNewModel> addShiftRosterNewDeatils = new ArrayList<>();

			for (int i = 0; i < currentShiftDetailsData.length(); i++) {
				JSONObject shiftDetail = currentShiftDetailsData.getJSONObject(i);

				// Extract necessary fields
				int fieldId = shiftDetail.getInt("field_id");
				String employee_code = shiftDetail.getString("employee_code");
				int departmentId = shiftDetail.getInt("department_id");
				int subDepartmentId = shiftDetail.getInt("sub_department_id");


				// Retrieve existing records for the specified month and employee
				List<CHtShiftRosterNewModel> existingRecords = iHtShiftRosterNewRepository.findByEmployee_code(
						employee_code,
						attendanceRequests.getAttendance_month(),
						attendanceRequests.getAttendance_year()
				);
				Date currentDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

				CHtShiftRosterNewModel mergedRecord = new CHtShiftRosterNewModel();
				CHtShiftRosterNewModel addMergeRecord = new CHtShiftRosterNewModel();

				// Merge existing records with the new data
				if (!existingRecords.isEmpty()) {
					// Assuming there's only one record per employee per month
					mergedRecord = existingRecords.get(0);
					BeanUtils.copyProperties(mergedRecord, addMergeRecord);

					// Mark all existing records as deleted
					for (CHtShiftRosterNewModel record : existingRecords) {
						record.setIs_delete(true);
						iHtShiftRosterNewRepository.save(record);
					}
				} else {
					addMergeRecord.setCompany_id(attendanceRequests.getCompany_id());
					addMergeRecord.setCompany_branch_id(attendanceRequests.getCompany_branch_id());
					addMergeRecord.setEmployee_type(attendanceRequests.getEmployee_type());
					addMergeRecord.setAttendance_month(attendanceRequests.getAttendance_month());
					addMergeRecord.setAttendance_year(attendanceRequests.getAttendance_year());
					addMergeRecord.setProcess_date(currentDate);
					addMergeRecord.setRoster_id(attendanceRequests.getRoster_id());
					addMergeRecord.setFinancial_year(attendanceRequests.getFinancial_year());
					addMergeRecord.setCreated_by(attendanceRequests.getCreated_by());
				}

				// Update employee-specific fields
				addMergeRecord.setRoster_process_id(0);
				addMergeRecord.setEmployee_id(fieldId);
				addMergeRecord.setEmployee_type(employee_type);
				addMergeRecord.setProcess_date(currentDate);
				addMergeRecord.setEmployee_code(employee_code);
				addMergeRecord.setDepartment_id(departmentId);
				addMergeRecord.setSub_department_id(subDepartmentId);

				// Process shift details
				Iterator<String> keys = shiftDetail.keys();
				while (keys.hasNext()) {
					String key = keys.next();

					// Check if the key matches the pattern \d+/\\d+
					if (key.matches("\\d+/\\d+")) {
						String[] parts = key.split("/");
						int day = Integer.parseInt(parts[0]);
						String shiftIdField = "shift_id" + day;
						String shiftNameField = "shift_name" + day;

						try {
							// Get the fields using reflection
							Field shiftIdFieldObj = mergedRecord.getClass().getDeclaredField(shiftIdField);
							Field shiftNameFieldObj = mergedRecord.getClass().getDeclaredField(shiftNameField);

							// Ensure accessibility of private fields
							shiftIdFieldObj.setAccessible(true);
							shiftNameFieldObj.setAccessible(true);

							// Extract id and Name from the nested JSON object
							JSONObject nestedObject = shiftDetail.getJSONObject(key);
							Integer id = Integer.parseInt(nestedObject.getString("id"));
							String name = nestedObject.getString("Name");

							// Set the values in the model object
							shiftIdFieldObj.set(addMergeRecord, id);
							shiftNameFieldObj.set(addMergeRecord, name);
						} catch (NoSuchFieldException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}

				addShiftRosterNewDeatils.add(addMergeRecord);
			}

			// Save the merged record as a new entry
			iHtShiftRosterRepository.saveAll(addShiftRosterDeatils);

            iHtShiftRosterNewRepository.saveAll(addShiftRosterNewDeatils);

			// Prepare your response
			response.put("success", 1);
			response.put("error", "");
			response.put("message", "Records added successfully");

		} catch (Exception e) {
			response.put("status", "error");
			response.put("message", e.getMessage());
			e.printStackTrace();
		}

		return response;
	}

}









