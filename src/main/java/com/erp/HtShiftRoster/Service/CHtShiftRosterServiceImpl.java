package com.erp.HtShiftRoster.Service;

import com.erp.HtShiftRoster.Repository.IHtShiftRosterRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CHtShiftRosterServiceImpl implements IHtShiftRosterService {
	@Autowired
	private IHtShiftRosterRepository iHtShiftRosterRepository;

	@Override
	public List<Map<String, Object>> FnShowShiftRosterEmployeeData(JSONObject jsonObject) {

		JSONObject attendanceJson = jsonObject.getJSONObject("EmployeeShiftRosterData");
		String employeeType = attendanceJson.getString("employee_type");

		// Initialize departmentId and subDepartmentId as null
		Integer departmentId = null;
		Integer subDepartmentId = null;

//      Check if department_id is present and not empty
		if (attendanceJson.has("department_id") && !attendanceJson.isNull("department_id")) {
			departmentId = attendanceJson.getInt("department_id");
		}

//      Check if sub_department_id is present and not empty
		if (attendanceJson.has("sub_department_id") && !attendanceJson.isNull("sub_department_id")) {
			subDepartmentId = attendanceJson.getInt("sub_department_id");
		}

		int attendanceMonth = attendanceJson.getInt("attendance_month");
		int attendanceYear = attendanceJson.getInt("attendance_year");
		int shiftId = attendanceJson.getInt("shift_id");
		int company_id = attendanceJson.getInt("company_id");
		List<Map<String, Object>> response = new ArrayList<>();

		try {
			// Fetch employee details
			List<Object[]> employeeDetails = iHtShiftRosterRepository.findShiftRosterEmployeeDetails(
					employeeType, attendanceMonth, attendanceYear, shiftId, company_id);

			// Fetch employee master data if employeeDetails is empty
			List<Object[]> employeeMasterDetails = iHtShiftRosterRepository.findShiftRosterEmployeeData(
					employeeType, departmentId, subDepartmentId, shiftId, company_id);

			// Use a set to store unique employees based on a unique key, like employee_code
			Set<String> uniqueEmployeeCodes = new HashSet<>();

			// Process employeeDetails
			for (Object[] detail : employeeDetails) {
				String employeeCode = (String) detail[1]; // Assuming detail[1] is employee_code
				if (!uniqueEmployeeCodes.contains(employeeCode)) {
					Map<String, Object> employee = new HashMap<>();
					employee.put("field_id", detail[0]);
					employee.put("employee_code", detail[1]);
					employee.put("field_name", detail[2]);
					employee.put("department_name", detail[3]);
					employee.put("department_id", detail[4]);
					employee.put("sub_department_id", detail[5]);
					employee.put("old_employee_code", detail[6]);
					response.add(employee);
					uniqueEmployeeCodes.add(employeeCode);
				}
			}

			// Process employeeMasterDetails to add unique entries
			for (Object[] detail : employeeMasterDetails) {
				String employeeCode = (String) detail[1]; // Assuming detail[1] is employee_code
				if (!uniqueEmployeeCodes.contains(employeeCode)) {
					Map<String, Object> employee = new HashMap<>();
					employee.put("field_id", detail[0]);
					employee.put("employee_code", detail[1]);
					employee.put("field_name", detail[2]);
					employee.put("department_name", detail[3]);
					employee.put("department_id", detail[4]);
					employee.put("sub_department_id", detail[5]);
					employee.put("old_employee_code", detail[6]);
					response.add(employee);
					uniqueEmployeeCodes.add(employeeCode);
				}
			}

			// Now 'response' contains unique employees from both employeeDetails and employeeMasterDetails

		} catch (Exception e) {
			// Handle exceptions
			e.printStackTrace();
		}

		return response;
	}
}
