package com.erp.HmShiftManagement.Controller;

import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.Employee.Employees.Repository.IEmployeesViewRepository;
import com.erp.HmShiftManagement.Model.CHmAttLog2Model;
import com.erp.HmShiftManagement.Repository.IHmAttLog2ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/csvimport")
public class CCSVImportController {

	@Autowired
	private IHmAttLog2ModelRepository csvImportRepository;

	@Autowired
	private IEmployeesViewRepository iEmployeesViewRepository;


	@PostMapping("/CSVImport/{company_id}")
	public ResponseEntity<Map<String, String>> importCsv(@RequestParam("csvFile") MultipartFile csvFile, @PathVariable int company_id) {
		Date currentDate = new Date();
		Map<String, String> response = new HashMap<>();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
			String[] headerColumn = null;
			String line;

			if ((line = reader.readLine()) != null) {
				headerColumn = line.split(",");
			}

//			GET ALL EMPLOYEE  LIST
			List<CEmployeesViewModel> viewModels = iEmployeesViewRepository.FnShowEmployeeRecords(company_id);

			List<CHmAttLog2Model> addPunchingDetails = new ArrayList<>();

			while ((line = reader.readLine()) != null) {
				String[] values = line.split(",");
				if (values.length < headerColumn.length) {
					continue;
				}

				String empID = values[0].trim();
				String date = values[2].trim();
				String punch1 = values[3].trim();
				String punch2 = values[4].trim();
				String punch3 = values[5].trim();

				savePunchEntry(empID, date, punch1, currentDate, addPunchingDetails, viewModels, "punch_1");
				savePunchEntry(empID, date, punch2, currentDate, addPunchingDetails, viewModels, "punch_2");
//				savePunchEntry(empID, date, punch3, currentDate, addPunchingDetails);
			}

			csvImportRepository.saveAll(addPunchingDetails);

			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	private void savePunchEntry(String empID, String date, String punchTime, Date currentDate, List<CHmAttLog2Model> addPunchingDetails,
	                            List<CEmployeesViewModel> viewModels, String punch_type) {
		if (punchTime.isEmpty()) {
			return;
		}

		CHmAttLog2Model cCSVImport = new CHmAttLog2Model();
		cCSVImport.setAtt_device_ip(null);
		// Remove leading zeros
		empID = empID.replaceFirst("^0+", "");

//       Now pass the modified empID to the method
		cCSVImport.setAtt_device_emp_code(empID);
		cCSVImport.setAtt_device_transaction_id(null);

//		GET PARTICULAR EMPLOYEE RECORED
		String finalEmpID = empID;

		boolean isTwoDayShift = viewModels.stream()
				.filter(employee -> finalEmpID.equals(employee.getOld_employee_code()))
				.map(CEmployeesViewModel::isTwo_day_shift)  // Get the `two_day_shift` flag
				.findFirst()                                // Find the first matching employee
				.orElse(false);

		// Parse the date and punchTime into LocalDateTime
		LocalDateTime punchDateTime = LocalDateTime.of(
				LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
				LocalTime.parse(punchTime)
		);

//		String dateTimeString = LocalDateTime.of(
//						LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MMM-yyyy")),
//						LocalTime.parse(punchTime))
//				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		// If it's a two-day shift, add one day to the punch date
		if (isTwoDayShift && punch_type.equals("punch_2")) {
			punchDateTime = punchDateTime.plusDays(1);
		}

		// Format the updated punch date and time to "yyyy-MM-dd HH:mm:ss"
		String dateTimeString = punchDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		// Set the punch date and time in the entity
		cCSVImport.setAtt_date_time(dateTimeString);
		cCSVImport.setAtt_date_added(currentDate);
		cCSVImport.setAtt_punch_status(null);
		cCSVImport.setAtt_punch_type(null);

		addPunchingDetails.add(cCSVImport);

	}
}
