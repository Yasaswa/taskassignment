package com.erp.HtSalarySummaryNew.Service;

import com.erp.AmApplicationErrorLogs.Service.CAmApplicationErrorLogsServiceImpl;
import com.erp.Department.Model.CDepartmentViewModel;
import com.erp.Department.Repository.IDepartmentViewRepository;
import com.erp.Employee.Employees.Model.CEmployeeDeductionMappingViewModel;
import com.erp.Employee.Employees.Model.CEmployeeEarningMappingViewModel;
import com.erp.Employee.Employees.Model.CEmployeesViewModel;
import com.erp.Employee.Employees.Repository.IEmployeeDeductionMappingViewModelRepository;
import com.erp.Employee.Employees.Repository.IEmployeeEarningMappingViewModelRepository;
import com.erp.Employee.Employees.Repository.IEmployeesViewRepository;
import com.erp.HmDeductionHeads.Model.CHmDeductionHeadsModel;
import com.erp.HmEarningHeads.Model.CHmEarningHeadsModel;
import com.erp.HmHrpayrollSettings.Model.CHmHrpayrollSettingsModel;
import com.erp.HmHrpayrollSettings.Repository.IHmHrpayrollSettingsRepository;
import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyJobTypeModel;
import com.erp.HmShiftManagement.Model.CHtAttendanceMonthlyJobTypeNewModel;
import com.erp.HmShiftManagement.Repository.IHtAttendanceMonthlyJobTypeNewRepository;
import com.erp.HmShiftManagement.Repository.IHtAttendanceMonthlyJobTypeRepository;
import com.erp.HtAttendaceNew.Model.CHtAttendanceMonthlyNewModel;
import com.erp.HtAttendaceNew.Repository.IHtAttendanceMonthlyNewRepository;
import com.erp.HtMonthlyDeduction.Model.CHtMonthlyDeductionDeatilsModel;
import com.erp.HtMonthlyDeduction.Repository.IHtMonthlyDeductionRepository;
import com.erp.HtSalarySummary.Model.CHtSalarySummaryViewModel;
import com.erp.HtSalarySummary.Repository.IHtSalarySummaryViewRepository;
import com.erp.HtSalarySummaryNew.Model.CHtSalaryDeductionNewModel;
import com.erp.HtSalarySummaryNew.Model.CHtSalaryEarningsNewModel;
import com.erp.HtSalarySummaryNew.Model.CHtSalarySummaryNewModel;
import com.erp.HtSalarySummaryNew.Repository.IHtSalaryDeductionNewRepository;
import com.erp.HtSalarySummaryNew.Repository.IHtSalaryEarningsNewRepository;
import com.erp.HtSalarySummaryNew.Repository.IHtSalarySummaryNewRepository;
import com.erp.JobType.Model.CJobTypeModel;
import com.erp.JobType.Repository.IJobTypeRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CHtSalarySummaryNewServiceImpl implements IHtSalarySummaryNewService {

	static DecimalFormat formatDecimalVal = new DecimalFormat("0.00");

	@Autowired
	CAmApplicationErrorLogsServiceImpl amApplicationErrorLogsServiceImpl;
	@Autowired
	IEmployeesViewRepository iEmployeesViewRepository;
	// Monthly JobTypeWise Attendace Records;
	@Autowired
	IHtAttendanceMonthlyJobTypeNewRepository iHtAttendanceMonthlyJobTypeNewRepository;
	@Autowired
	IHtAttendanceMonthlyJobTypeRepository iHtAttendanceMonthlyJobTypeRepository;
	@Autowired
	IJobTypeRepository iJobTypeRepository;
	@Autowired
	private IHtSalarySummaryNewRepository htSalarySummaryNewRepository;
	@Autowired
	private IHtSalarySummaryViewRepository iHtSalarySummaryViewRepository;
	@Autowired
	private IHtSalaryEarningsNewRepository iHtSalaryEarningsNewRepository;
	@Autowired
	private IHtSalaryDeductionNewRepository iHtSalaryDeductionNewRepository;
	@Autowired
	private IHtAttendanceMonthlyNewRepository iHtAttendanceMonthlyNewRepository;
	@Autowired
	private IHtMonthlyDeductionRepository iHtMonthlyDeductionRepository;
	@Autowired
	private IHmHrpayrollSettingsRepository iHmHrpayrollSettingsRepository;
	@Autowired
	private JdbcTemplate executeQuery;
	@Autowired
	private IEmployeeEarningMappingViewModelRepository iEmployeeEarningMappingViewModelRepository;
	@Autowired
	private IEmployeeDeductionMappingViewModelRepository iEmployeeDeductionMappingViewModelRepository;
	@Autowired
	private IDepartmentViewRepository iDepartmentViewRepository;


	//	// To calculate the deductions By Deduction Name.
	private static void processDeductionHead(CHmDeductionHeadsModel deductionHead, String deductionName, Map<String, Object> deductionHeadsNameMap, Map<String, Object> combinedHeadsNameMap) {
		if (deductionHead != null) {
			double outputVal = 0.0;
			if ("Formula".equals(deductionHead.getCalculation_type())) {
				String paramReplacedFormula = FnReplaceFormulaParams(deductionHead.getFormula(), combinedHeadsNameMap, 0);
				outputVal = FnEvaluateExpression(paramReplacedFormula);
			} else if ("Amount".equals(deductionHead.getCalculation_type())) {
				outputVal = deductionHead.getCalculation_value();
			}
			deductionHeadsNameMap.put(deductionName, formatDecimalVal.format(outputVal));
			combinedHeadsNameMap.put(deductionName, formatDecimalVal.format(outputVal));
		}
	}

	//	// Supportable format 08:00:00-20:00:00 && 08:00:00/20:00:00 && 08:00-20:00 && 08:00/20:00
	private static Double FnCalculateWorkingHours(String punchInAndOut) {
		String[] times;
		if (punchInAndOut.contains("-")) {
			times = punchInAndOut.split("-");
		} else {
			times = punchInAndOut.split("/");
		}
		String punchInTime = times[0], punchOutTime = times[1];

		LocalDateTime punchIn = LocalDateTime.of(LocalDate.now(), LocalTime.parse(punchInTime, determineFormatter(punchInTime)));
		LocalDateTime punchOut = LocalDateTime.of(LocalDate.now(), LocalTime.parse(punchOutTime, determineFormatter(punchOutTime)));
		// Check if punch-out time is before punch-in time, indicating it's the next day
		if (punchOut.isBefore(punchIn)) {
			punchOut = punchOut.plusDays(1); // Add a day to punch-out time
		}
		Duration duration = Duration.between(punchIn, punchOut);
		Double calculatedHrs = duration.toMinutes() / 60.0;
//        System.out.println("In: " + punchInTime + " \tOut: " + punchOutTime +
//                " \tWorked hours: " + duration.toHours() + " hrs " + duration.toMinutesPart() + " min" +
//                " \tIn decimal Hrs: " + calculatedHrs);
		return calculatedHrs;
	}

	// Get the JobTypeWise Woker Monthly Attendace Data From List;
	public static List<CHtAttendanceMonthlyJobTypeModel> FnFilterJobTypeWiseMontlyAttByConditions(
			List<CHtAttendanceMonthlyJobTypeModel> allEmplsJobTypeCodeWiseMonthlyAttendance, Predicate<CHtAttendanceMonthlyJobTypeModel> conditionsForFilter
	) {
		return allEmplsJobTypeCodeWiseMonthlyAttendance.stream().filter(conditionsForFilter).collect(Collectors.toList());
//        // ******** Method To Call That Function and pass conditions
//        FnFilterJobTypeWiseMontlyAttByConditions(allEmplJobTypeCodeWiseMonthlyAttList, item ->
//                item.getEmployee_code().equals(employeeCode) &&
//                        (item.getJob_type_id().equals(jobCodeId)));
	}

	// Get the Data By Conditions from the Passed JobTypeList;
	public static List<CJobTypeModel> FnFilterJobTypesByConditions(List<CJobTypeModel> jobTypeDetails, Predicate<CJobTypeModel> conditionsForFilter) {
		return jobTypeDetails.stream().filter(conditionsForFilter).collect(Collectors.toList());
	}

	private static DateTimeFormatter determineFormatter(String time) {
		if (time.contains(":") && time.split(":").length == 3) {
			return DateTimeFormatter.ofPattern("HH:mm:ss");
		} else {
			return DateTimeFormatter.ofPattern("HH:mm");
		}
	}

	public static long FnGetCeilingValue(Object value) {
		if (value instanceof Integer) {
			return (Integer) value;
		} else if (value instanceof Double) {
			return (int) Math.ceil((Double) value);
		} else if (value instanceof String) {
			try {
				double doubleValue = Double.parseDouble((String) value);
				return (int) Math.ceil(doubleValue);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return 0;
			}
		} else if (value instanceof Number) {
			return (int) Math.ceil(((Number) value).doubleValue()); // This will handle int, float, etc., treating them as double.
		} else {
			System.err.println("Unsupported type: " + value.getClass().getName());
			return 0;
		}
	}

	//  // Function for calculate the Number of days occurance in Month.
	public static Map<String, Integer> getDayOccuranceCountsInMonth(String monthName, int year) {
		Map<String, Integer> dayCounts = new HashMap<>();
		// Initialize map with days of the week and counts set to 0
		for (DayOfWeek day : DayOfWeek.values()) {
			dayCounts.put(day.name(), 0);
		}
		if (monthName == null || monthName.trim().isEmpty() || year < 1) {
			return dayCounts;
		}
		Month month;
		try {
			month = Month.valueOf(monthName.toUpperCase(Locale.ENGLISH));
		} catch (IllegalArgumentException e) {
			return dayCounts;
		}

		LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
		int daysInMonthCount = firstDayOfMonth.lengthOfMonth();

		// Populate map with actual day counts
		for (int day = 1; day <= daysInMonthCount; day++) {
			DayOfWeek dayOfWeek = firstDayOfMonth.plusDays(day - 1).getDayOfWeek();
			String dayName = dayOfWeek.name();
			dayCounts.put(dayName, dayCounts.get(dayName) + 1);
		}
		return dayCounts;
	}

	private static String FnReplaceFormulaParams(String formula, Map<String, Object> formulaKeysNdValues, Object defaultValue) {
		// Create a map to map keys in the formula to keys in the formulaKeysNdValuesmap
		Map<String, String> keyMapping = new HashMap<>();
		keyMapping.put("P3", "Basic Salary");
		keyMapping.put("P2", "PF");
		keyMapping.put("TE", "Total Earning");

		// Replace keys in the formula with their corresponding values from the map
		for (Map.Entry<String, String> entry : keyMapping.entrySet()) {
			Object value = formulaKeysNdValues.getOrDefault(entry.getValue(), defaultValue);
			// Replace all occurrences of the key in the formula
			formula = formula.replaceAll("\\b" + entry.getKey() + "\\b", value.toString());
		}
		return formula;
	}

	//	// To Handle the ternary operator based expressions also.
	public static double FnEvaluateExpression(String expression) {
		try {
			char[] tokens = expression.toCharArray();
			Stack<Double> values = new Stack<>();
			Stack<Character> ops = new Stack<>();

			for (int i = 0; i < tokens.length; i++) {
				if (tokens[i] == ' ') continue;

				if (Character.isDigit(tokens[i]) || tokens[i] == '.') {
					StringBuilder sb = new StringBuilder();
					while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
						sb.append(tokens[i++]);
					}
					values.push(Double.parseDouble(sb.toString()));
					i--;
				} else if (tokens[i] == '(') {
					ops.push(tokens[i]);
				} else if (tokens[i] == ')') {
					while (ops.peek() != '(') values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
					ops.pop();
				} else if (tokens[i] == '?') {
					// Evaluate the condition
					while (!ops.isEmpty() && ops.peek() != '(') {
						values.push(applyOperation(ops.pop(), values.pop(), values.pop()));
					}
					double condition = values.pop();

					// Parse true and false expressions
					int j = i + 1;
					int colonIndex = findColonIndex(tokens, j);
					if (colonIndex == -1) return 0; // Invalid expression

					String truePart = expression.substring(j, colonIndex).trim();
					String falsePart = expression.substring(colonIndex + 1).trim();

					double trueResult = FnEvaluateExpression(truePart);
					double falseResult = FnEvaluateExpression(falsePart);

					return condition != 0 ? trueResult : falseResult;
				} else if (isOperator(tokens[i])) {
					while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
						values.push(applyOperation(ops.pop(), values.pop(), values.pop()));

					ops.push(tokens[i]);
				} else {
					return 0; // Return 0 if an invalid character is encountered
				}
			}

			while (!ops.empty()) values.push(applyOperation(ops.pop(), values.pop(), values.pop()));

			return values.pop();
		} catch (Exception e) {
			return 0; // Return 0 for any other exception
		}
	}

	private static int findColonIndex(char[] tokens, int start) {
		int nested = 0;
		for (int i = start; i < tokens.length; i++) {
			if (tokens[i] == '?') nested++;
			if (tokens[i] == ':') {
				if (nested == 0) return i;
				nested--;
			}
		}
		return -1;
	}

	public static boolean isOperator(char op) {
		return op == '+' || op == '-' || op == '*' || op == '/' || op == '<' || op == '>' || op == '!';
	}

	public static boolean hasPrecedence(char op1, char op2) {
		if (op2 == '(' || op2 == ')') return false;
		if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
		return (op1 != '<' && op1 != '>') || (op2 != '+' && op2 != '-' && op2 != '*' && op2 != '/');
	}

	public static double applyOperation(char op, double b, double a) {
		switch (op) {
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				if (b == 0) throw new ArithmeticException("Division by zero");
				return a / b;
			case '<':
				return a < b ? 1 : 0;
			case '>':
				return a > b ? 1 : 0;
			case '!':
				return a != b ? 1 : 0;
			default:
				throw new IllegalArgumentException("Invalid operator: " + op);
		}
	}

	/**
	 * Sums the values of a specified field from objects in the list that satisfy a given condition.
	 * If no condition is provided, it sums the field values for all objects.
	 *
	 * @param list      The list of objects.
	 * @param fieldName The name of the field to sum.
	 * @param condition The optional condition to filter objects. Pass null to sum all.
	 * @param <T>       The type of objects in the list.
	 * @return The sum of the field values that meet the condition, or all if condition is null.
	 */
	public static <T> Double FnSumOfField(List<T> list, String fieldName, Predicate<T> condition) {
		try {
			// Extract the field using reflection
			Field field = list.get(0).getClass().getDeclaredField(fieldName);
			field.setAccessible(true);

			return list.stream()
					.filter(condition != null ? condition : item -> true)  // Use the condition if provided
					.mapToDouble(item -> {
						try {
							// Get the field value from the object and convert it to double
							Object value = field.get(item);
							return value != null ? ((Double) value) : 0.0;
						} catch (IllegalAccessException e) {
							// Print error log and return default value
							System.err.println("Error accessing field value: " + e.getMessage());
							return 0.0;
						}
					})
					.sum();
		} catch (NoSuchFieldException e) {
			// Print error log and return default value
			System.err.println("Field not found: " + e.getMessage());
			return 0.0;
		}
	}

	/**
	 * Filters a list of objects based on a given condition.
	 *
	 * @param list      The list of objects to filter.
	 * @param condition The condition to apply for filtering.
	 * @param <T>       The type of objects in the list.
	 * @return A new list containing only the objects that satisfy the condition.
	 */
	public static <T> List<T> FnFilterListByConditions(List<T> list, Predicate<T> condition) {
		return list.stream()
				.filter(condition)  // Apply the predicate to filter items
				.collect(Collectors.toList());  // Collect the filtered items into a new list
	}

	@Transactional
	@Override
	public Map<String, Object> FnAddUpdateRecord(JSONObject json, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Save Functionality.
			JSONObject commonFields = (JSONObject) json.get("commonFields");
			String employeeType = commonFields.getString("employee_type");
			int salaryForMonth = commonFields.getInt("salary_month");
			int salaryForYear = commonFields.getInt("salary_year");

			JSONArray allEmplSalarySummaryData = (JSONArray) json.get("AllEmployeeSalarySummaryData");
			JSONArray allEmplEarningsSalaryData = (JSONArray) json.get("AllEmployeeSalaryEarningsData");
			JSONArray allEmplDeductionsSalaryData = (JSONArray) json.get("AllEmployeeSalaryDeductionsData");

			// Saving the employee Salary Summary Data
			List<CHtSalarySummaryNewModel> allEmplSalarySummaryModels = objectMapper.readValue(allEmplSalarySummaryData.toString(), new TypeReference<List<CHtSalarySummaryNewModel>>() {
			});
			// Collecting employee IDs into a list
			List<Integer> employeeIds = allEmplSalarySummaryModels.parallelStream().map(CHtSalarySummaryNewModel::getEmployee_id) // Map each object to its employee ID
					.collect(Collectors.toList());

			// Update the old records.
			int numberOfUpdatedSummaryRows = htSalarySummaryNewRepository.deletePreviousRecords(employeeIds, salaryForYear, salaryForMonth, company_id, allEmplSalarySummaryModels.get(0).getCreated_by());
			iHtSalaryEarningsNewRepository.deletePreviousRecords(employeeIds, salaryForYear, salaryForMonth, company_id, allEmplSalarySummaryModels.get(0).getCreated_by());
			iHtSalaryDeductionNewRepository.deletePreviousRecords(employeeIds, salaryForYear, salaryForMonth, company_id, allEmplSalarySummaryModels.get(0).getCreated_by());

			allEmplSalarySummaryModels.forEach(employeeSalarySummary -> {
				employeeSalarySummary.setSalary_transaction_id(0);
			});
			List<CHtSalarySummaryNewModel> savedAllEmplSalarySummaryModels = htSalarySummaryNewRepository.saveAll(allEmplSalarySummaryModels);

			List<CHtSalaryEarningsNewModel> allEmplSalaryEarningsModels = objectMapper.readValue(allEmplEarningsSalaryData.toString(), new TypeReference<List<CHtSalaryEarningsNewModel>>() {
			});
			List<CHtSalaryEarningsNewModel> allEmplSalaryEarningsModelsForSave = new ArrayList<>();

			List<CHtSalaryDeductionNewModel> allEmplSalaryDeductionsModels = objectMapper.readValue(allEmplDeductionsSalaryData.toString(), new TypeReference<List<CHtSalaryDeductionNewModel>>() {
			});
			List<CHtSalaryDeductionNewModel> allEmplSalaryDeductionsModelsForSave = new ArrayList<>();

			savedAllEmplSalarySummaryModels.forEach(employeeSavedSalarySummary -> {
				int employeeId = employeeSavedSalarySummary.getEmployee_id();
				// Find matching earings using employee id.
				List<CHtSalaryEarningsNewModel> employeeSalaryEarnings = allEmplSalaryEarningsModels.parallelStream().filter(e -> e.getEmployee_id() == employeeId).map(earning -> {
					earning.setSalary_transaction_id(employeeSavedSalarySummary.getSalary_transaction_id()); // Set defaultValue to whatever your default field value is
					return earning;
				}).collect(Collectors.toList());
				allEmplSalaryEarningsModelsForSave.addAll(employeeSalaryEarnings);

				// Find matching deductions using employee id.
				List<CHtSalaryDeductionNewModel> employeeSalaryDeductions = allEmplSalaryDeductionsModels.parallelStream().filter(d -> d.getEmployee_id() == employeeId).map(deduction -> {
					deduction.setSalary_transaction_id(employeeSavedSalarySummary.getSalary_transaction_id()); // Set defaultValue to whatever your default field value is
					return deduction;
				}).collect(Collectors.toList());
				allEmplSalaryDeductionsModelsForSave.addAll(employeeSalaryDeductions);

			});
			// Save the Salary Earnings & Deductions.
			iHtSalaryEarningsNewRepository.saveAll(allEmplSalaryEarningsModelsForSave);
			iHtSalaryDeductionNewRepository.saveAll(allEmplSalaryDeductionsModelsForSave);

			responce.put("success", "1");
			responce.put("data", "");
			responce.put("error", "");
			responce.put("message", (numberOfUpdatedSummaryRows > 0) ? "Records updated successfully...!" : "Records added successfully...!");

		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HtSalarySummaryNew/FnAddUpdateRecord", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HtSalarySummaryNew/FnAddUpdateRecord", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
		}
		return responce;
	}

	@Override
	public Object FnDeleteRecord(int salary_transaction_id, int company_id) {
		Optional<CHtSalarySummaryNewModel> option = htSalarySummaryNewRepository.findById(salary_transaction_id);
		CHtSalarySummaryNewModel cHtSalarySummaryModel = new CHtSalarySummaryNewModel();
		if (option.isPresent()) {
			cHtSalarySummaryModel = option.get();
			cHtSalarySummaryModel.setIs_delete(true);
			cHtSalarySummaryModel.setDeleted_on(new Date());
			htSalarySummaryNewRepository.save(cHtSalarySummaryModel);
		}
		return cHtSalarySummaryModel;
	}

	@Override
	public Map<String, Object> FnShowParticularRecordForUpdate(int salary_transaction_id, int company_id) {
		Map<String, Object> responce = new HashMap<>();
		try {
			CHtSalarySummaryViewModel cHtSalarySummaryViewModel = iHtSalarySummaryViewRepository.FnShowParticularRecordForUpdate(salary_transaction_id, company_id);
			List<Map<String, Object>> salaryEarningsModels = iHtSalaryEarningsNewRepository.FnGetMonthlyEarningBySalarySummaryTransId(salary_transaction_id, company_id);
			List<Map<String, Object>> salaryDeductionModels = iHtSalaryDeductionNewRepository.FnGetMonthlyDeductionBySalarySummaryTransId(salary_transaction_id, company_id);

			responce.put("success", "1");
			responce.put("SummaryData", cHtSalarySummaryViewModel);
			responce.put("EarningData", salaryEarningsModels);
			responce.put("DeductionData", salaryDeductionModels);
			responce.put("error", "");
		} catch (DataAccessException e) {
			if (e.getRootCause() instanceof SQLException) {
				SQLException sqlEx = (SQLException) e.getRootCause();
				amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HtSalarySummary/FnShowParticularRecordForUpdate", sqlEx.getErrorCode(), sqlEx.getMessage());
				System.out.println(sqlEx.getMessage());
				responce.put("success", "0");
				responce.put("data", "");
				responce.put("error", e.getMessage());
				return responce;
			}
		} catch (Exception e) {
			e.printStackTrace();
			amApplicationErrorLogsServiceImpl.addErrorLog(company_id, "api", "/api/HtSalarySummary/FnShowParticularRecordForUpdate", 0, e.getMessage());
			responce.put("success", "0");
			responce.put("data", "");
			responce.put("error", e.getMessage());
			return responce;
		}
		return responce;
	}

	@Override
	public Map<String, Object> FnDisplaySalariesCalculations(@RequestParam("MtSalaryProcessingFilters") JSONObject MtSalaryProcessingFilters, int company_id) {
		Map<String, Object> responce = new HashMap<>();

		JSONObject filters = (JSONObject) MtSalaryProcessingFilters.get("filters");
		String employeeType = filters.getString("employee_type");
		String employeeGroup = filters.getString("employee_group");
		String salaryForMonth = filters.getString("salary_month");
		int salaryForYear = filters.getInt("salary_year");

		int departmentId = filters.optInt("department_id", 0);
		int subDepartmentId = filters.optInt("sub_department_id", 0);
		Integer depId = departmentId == 0 ? null : departmentId;
		Integer subDepId = subDepartmentId == 0 ? null : subDepartmentId;


		int salaryForMonthNumber = Month.valueOf(salaryForMonth.toUpperCase()).getValue();

		List<String> employeeGroups = Stream.of("All", employeeGroup).map(value -> "'" + value + "'").collect(Collectors.toList());

		List<CHmEarningHeadsModel> earningHeads = new ArrayList<CHmEarningHeadsModel>();
		AtomicReference<List<CHmDeductionHeadsModel>> deductionHeadsRef = new AtomicReference<>(new ArrayList<>());

		StringBuilder query = new StringBuilder();
		List<Object> queryParams = new ArrayList<Object>();

//		//** Get Employee Data as per employee type from employee tbl Using Repo Quries.
		List<CEmployeesViewModel> employeeList = iEmployeesViewRepository.FnGetEmployeesByEmplType(employeeType, depId, subDepId, company_id);

//        List<CEmployeesViewModel> employeeList = iEmployeesViewRepository.FnGetEmployeesByEmplType(employeeType, company_id);
		List<String> employeeCodes = employeeList.parallelStream().map(employee -> employee.getEmployee_code()).collect(Collectors.toList());

//      // ** Get All Department_Details.
		List<CDepartmentViewModel> allDepartments = iDepartmentViewRepository.FnGetAllDepartments();

//		//** Get Earning Heads
		query = new StringBuilder();
		queryParams.clear();
		query.append("Select ").append("earning_heads_id, earning_head_name, calculation_type, formula, salary_parameter1, salary_parameter2, salary_parameter3, ").append("salary_parameter4, salary_parameter5, salary_parameter6, ").append("salary_parameter7 ").append("From hm_earning_heads ").append("Where ").append("earning_group IN (").append(String.join(", ", employeeGroups)).append(")");
		query.append(" AND is_delete = ? ").append("ORDER by head_position ASC");
		queryParams.add(false);
		System.out.println("Query For Get Earning Heads: " + query);
		earningHeads = executeQuery.query(query.toString(), queryParams.toArray(), new BeanPropertyRowMapper<>(CHmEarningHeadsModel.class));


//		//** Get Deduction Heads
		query = new StringBuilder();
		queryParams.clear();
		query.append("Select ").append("deduction_heads_id, deduction_head_name, calculation_type, formula, salary_parameter1, salary_parameter2, salary_parameter3, ").append("salary_parameter4, salary_parameter5, salary_parameter6, ").append("salary_parameter7 ").append("From hm_deduction_heads ").append("Where ").append("deduction_group IN (").append(String.join(", ", employeeGroups)).append(")");
		query.append(" AND is_delete = ? ").append("ORDER by head_position ASC");
		queryParams.add(false);
		System.out.println("Query For Get Deduction Heads: " + query);
		deductionHeadsRef.set(executeQuery.query(query.toString(), queryParams.toArray(), new BeanPropertyRowMapper<>(CHmDeductionHeadsModel.class)));

//		// ** Get the HsRPayroll Setting;
		CHmHrpayrollSettingsModel hrPayrollSettings = iHmHrpayrollSettingsRepository.FnGetPayrollSettingByCompanyId(company_id);
		AtomicReference<Double> pfAmountLimit = new AtomicReference<Double>(0.0);
		AtomicReference<Double> attendanceAllowanceDays = new AtomicReference<Double>(0.0);
		if (hrPayrollSettings != null) {
			pfAmountLimit.set(hrPayrollSettings.getPf_limit());
			attendanceAllowanceDays.set(hrPayrollSettings.getAttendance_allowance_days());
		}

//		// ** Now Collect the Salary Details;
		List<HashMap<String, Object>> salaryProcessDtls = new ArrayList<HashMap<String, Object>>();

		LinkedHashMap<String, Object> defaultEarningHeadsNameMap = earningHeads.stream().collect(Collectors.toMap(CHmEarningHeadsModel::getEarning_head_name, head -> formatDecimalVal.format(0.0), (existingValue, newValue) -> existingValue, LinkedHashMap::new));
		defaultEarningHeadsNameMap.put("Total Earning", formatDecimalVal.format(0.0));

		LinkedHashMap<String, Object> defaultDeductionHeadsNameMap = deductionHeadsRef.get().stream().collect(Collectors.toMap(CHmDeductionHeadsModel::getDeduction_head_name, head -> formatDecimalVal.format(0.0), (existingValue, newValue) -> existingValue, LinkedHashMap::new));
		defaultDeductionHeadsNameMap.put("Total Deduction", formatDecimalVal.format(0.0));

		int daysInMonth = Month.valueOf(salaryForMonth.toUpperCase()).length(Year.isLeap(salaryForYear));

//		// ** Get all employees earning mapping and deduction mapping queries as per employee codes.
		List<CEmployeeEarningMappingViewModel> allEmplEarningMappingsList = iEmployeeEarningMappingViewModelRepository.FnGetEarningMappingsByEmplCodes(employeeCodes, company_id);
		List<CEmployeeDeductionMappingViewModel> allEmplDeductionMappingsList = iEmployeeDeductionMappingViewModelRepository.FnGetDeductionsMappingsByEmplCodes(employeeCodes, company_id);

//		// ** Get Monthly attendance as per the employee code month, year, companyId
		List<CHtAttendanceMonthlyNewModel> allEmplMonthlyAttendanceList = iHtAttendanceMonthlyNewRepository.FnGetMonthlyAttendanceByEmplCodes(employeeCodes, salaryForMonthNumber, salaryForYear, company_id);

//		// ** Get Monthly JobTypeWise attendance as per the employee code, month, year, companyId; Only Applicable For Workers;
		List<CHtAttendanceMonthlyJobTypeNewModel> allEmplJobTypeCodeWiseMonthlyAttList = iHtAttendanceMonthlyJobTypeNewRepository.getJobTypeWiseMonthlyRecordsByMonth(employeeCodes, salaryForMonthNumber, salaryForYear, company_id);
		List<CHtAttendanceMonthlyJobTypeModel> allEmplJobTypeCodeWiseMonthlyAttListActual = iHtAttendanceMonthlyJobTypeRepository.getJobTypeWiseMonthlyRecordsByMonth(employeeCodes, salaryForMonthNumber, salaryForYear, company_id);

//      // ** All JobTypes;
		List<CJobTypeModel> allJobTypeDetails = iJobTypeRepository.getJobTypeDetails(company_id);

//		// ** Get Monthly Deductions as per the employee code month, year, companyId
		List<CHtMonthlyDeductionDeatilsModel> allEmplMonthlyDeductionsList = iHtMonthlyDeductionRepository.FnGetMonthlyDeductionsByEmplCodes(employeeCodes, salaryForMonth, salaryForYear, company_id);

//      // Calculate the Weekly-Offs occurances in month.
		Map<String, Integer> allWeeklyOffsCountsInMonth = getDayOccuranceCountsInMonth(salaryForMonth, salaryForYear);

//		// *************** Employee Salary Processing Started ********************************

		employeeList.stream()
				.forEach(employee -> {
					HashMap<String, Object> emplSalaryDtl = new LinkedHashMap<String, Object>();
					HashMap<String, Object> earningHeadsNameMap = new LinkedHashMap<>(defaultEarningHeadsNameMap);
					HashMap<String, Object> deductionHeadsNameMap = new LinkedHashMap<>(defaultDeductionHeadsNameMap);
//			        //******** It will storing all Heads means Earning Nd Deductions
					HashMap<String, Object> combinedHeadsNameMap = new LinkedHashMap<>();

					int currentEmplId = employee.getEmployee_id();
					String currentEmplCode = employee.getEmployee_code();
					String currentEmplType = employee.getEmployee_type();
					String currentEmplTypeGroup = employee.getEmployee_type_group();
					String currentEmplShift = employee.getCurrent_shift_start_end_time();
					Boolean pfIsApplicable = employee.getPf_flag();
					String currentEmplWeeklyOffName = employee.getWeeklyoff_name() != null ? employee.getWeeklyoff_name() : "";

					CHtAttendanceMonthlyNewModel currentEmplMonthlyAttendance = allEmplMonthlyAttendanceList.stream().filter(attendance -> attendance.getEmployee_id() == currentEmplId && currentEmplCode.equals(attendance.getEmployee_code())).findFirst().orElse(null);
					Double f_totalSalaryDays = (currentEmplMonthlyAttendance != null) ? currentEmplMonthlyAttendance.getTotal_salary_days() : 0.0;
					Double f_CurrMonthDays = (currentEmplMonthlyAttendance != null) ? currentEmplMonthlyAttendance.getMonth_days() : 0.0;
					Double f_CurrMonthWOffsDays = (currentEmplMonthlyAttendance != null) ? currentEmplMonthlyAttendance.getWeek_off_days() : 0.0;

//			        // Create a map with earning head names as keys and corresponding mapping objects as values
					Map<String, CEmployeeEarningMappingViewModel> curEmployeeEarningMappingsMap = allEmplEarningMappingsList.parallelStream().filter(mapping -> mapping.getEmployee_id() == currentEmplId && currentEmplCode.equals(mapping.getEmployee_code())).collect(Collectors.toMap(CEmployeeEarningMappingViewModel::getEarning_head_name, Function.identity()));

//			        // Create a map with deduction head names as keys and corresponding mapping objects as values
					Map<String, CEmployeeDeductionMappingViewModel> curEmployeeDeductionMappingsMap = allEmplDeductionMappingsList.parallelStream().filter(mapping -> mapping.getEmployee_id() == currentEmplId && currentEmplCode.equals(mapping.getEmployee_code())).collect(Collectors.toMap(CEmployeeDeductionMappingViewModel::getDeduction_head_name, Function.identity()));
//			        // Update earningHeadsNameMap with default values from current employee's earning mappings
					if (currentEmplMonthlyAttendance != null) {
						curEmployeeDeductionMappingsMap.forEach((deductionName, mapping) -> deductionHeadsNameMap.merge(deductionName, formatDecimalVal.format(mapping.getCalculation_value()), (oldValue, newValue) -> newValue));
					}

//			        // *** Also update the values into the All Heads mapping Map.
					combinedHeadsNameMap.putAll(earningHeadsNameMap);
					combinedHeadsNameMap.putAll(deductionHeadsNameMap);

//			        // ** Current employee Monthly Deductions
					List<CHtMonthlyDeductionDeatilsModel> currentEmplMonthlyDeductions = allEmplMonthlyDeductionsList.stream().filter(deduction -> deduction.getEmployee_id().equals(currentEmplId) && currentEmplCode.equals(deduction.getEmployee_code())).collect(Collectors.toList());

					long c_netPayableSalary = 0;
					Double f_salaryPerDay = 0.0, f_SalaryPerHrs = 0.0, c_MonthlyActualWorkedHrs = 0.0, c_MonthlyWorkedHrs = 0.0;
					if (currentEmplMonthlyAttendance != null) {
//                   // ** Getting From hm_earning_employee_mapping
//                   Double f_employeeTotalSalary = curEmployeeEarningMappingsMap.containsKey("Salary")
//                           ? curEmployeeEarningMappingsMap.get("Salary").getCalculation_value() : 0.0;
//                   earningHeadsNameMap.put("Salary", formatDecimalVal.format(f_employeeTotalSalary));
//                   combinedHeadsNameMap.put("Salary", formatDecimalVal.format(f_employeeTotalSalary));

//				// ** Getting From cmv_employee
						Double f_employeeTotalSalary = employee.getSalary();

						Double f_BasicSal = curEmployeeEarningMappingsMap.containsKey("Basic Salary") ? curEmployeeEarningMappingsMap.get("Basic Salary").getCalculation_value() : 0.0;
						Double f_BasicSalPercent = f_BasicSal / f_employeeTotalSalary * 100;

						Double f_OtherAllowance = curEmployeeEarningMappingsMap.containsKey("Other Allowance") ? curEmployeeEarningMappingsMap.get("Other Allowance").getCalculation_value() : 0.0;
						Double f_OtherAllowancePercent = f_OtherAllowance / f_employeeTotalSalary * 100;

//				        // ** Salary calculation starts For Earnings;
//                      f_salaryPerDay = f_employeeTotalSalary / daysInMonth;   // first Salary/(31 Or 30)
//                      f_salaryPerDay = f_employeeTotalSalary / (f_CurrMonthDays - f_CurrMonthWOffsDays);    // Then Salary/ (30 or 31 - 4 Or 5)

//                      It will getting from attendance but now not getting properly so we calculate it here.
						int currrentMonthWOs = allWeeklyOffsCountsInMonth.getOrDefault(currentEmplWeeklyOffName.toUpperCase(Locale.ENGLISH), 0);
						f_salaryPerDay = f_employeeTotalSalary / (f_CurrMonthDays - currrentMonthWOs);


						Double f_shiftFixedHrs = FnCalculateWorkingHours(currentEmplShift);
						Double c_calculatedSalary = 0.0;
						if (currentEmplTypeGroup.equals("Staffs")) {
							earningHeadsNameMap.put("Salary", formatDecimalVal.format(f_employeeTotalSalary));
							combinedHeadsNameMap.put("Salary", formatDecimalVal.format(f_employeeTotalSalary));
							// Means calculate as per the day basis

							if (currentEmplType.equals("Semi-Staff")) {
								///**Calculation for semi-staffs using total monthly hours
								c_MonthlyWorkedHrs = currentEmplMonthlyAttendance.getMonthly_hours();
								f_totalSalaryDays = c_MonthlyWorkedHrs / f_shiftFixedHrs;
								f_totalSalaryDays = (double) Math.round(f_totalSalaryDays);
								// If total salary days exceed or equal 26, cap the salary calculation at 26 days
								double totalDays = f_CurrMonthDays - currrentMonthWOs;

								if (f_totalSalaryDays >= totalDays) {
									c_calculatedSalary = totalDays * f_salaryPerDay;
									f_totalSalaryDays = totalDays;
								} else {
									// Otherwise, calculate salary for the actual total salary days
									c_calculatedSalary = f_totalSalaryDays * f_salaryPerDay;
								}

							} else {
								c_calculatedSalary = f_totalSalaryDays * f_salaryPerDay;
							}

							Double c_basicSalary = (c_calculatedSalary * f_BasicSalPercent) / 100;
							earningHeadsNameMap.put("Basic Salary", formatDecimalVal.format(c_basicSalary));
							combinedHeadsNameMap.put("Basic Salary", formatDecimalVal.format(c_basicSalary));

							Double c_OtherAllowncesSalary = (c_calculatedSalary * f_OtherAllowancePercent) / 100;
							earningHeadsNameMap.put("Other Allowance", formatDecimalVal.format(c_OtherAllowncesSalary));
							combinedHeadsNameMap.put("Other Allowance", formatDecimalVal.format(c_OtherAllowncesSalary));

							System.out.println("\nEmpl_Code: " + currentEmplCode + "\tMonthDays: " + f_CurrMonthDays + "\tWeeklyOffDays: " + f_CurrMonthWOffsDays + "\tSalaryDays: " + f_totalSalaryDays);
							System.out.println("F_StdSalary: " + f_employeeTotalSalary + "\tF_SalaryPerDay: " + f_salaryPerDay + "\tf_BasicSal: " + f_BasicSal + "\tf_BasicSalPercent: " + f_BasicSalPercent + "\tf_OtherAllowance: " + f_OtherAllowance + "\tf_OtherAllowancePercent: " + f_OtherAllowancePercent);
							System.out.println("C_StdSalary: " + c_calculatedSalary + "\tC_Basic: " + c_basicSalary + "\tC_OA: " + c_OtherAllowncesSalary);
//                  // *********** Staff Basic & Other Earning calculations ends.

						} else if (currentEmplTypeGroup.equals("Workers")) {
//               // ************** Old Code Before 02/08/2024 _ Before worker JobTypeWise Salary;
//                   // *******************************************************************************************************************************************
////				    // Means calculate as per the hours basis
////                  // *** Worker's Salary Values. (As per Department) Before Grade based Salary.
////                    Double f_workerPerDaySalary = (employee.get("worker_perday_salary") instanceof Double) ? (Double) employee.get("worker_perday_salary") : (employee.get("worker_perday_salary") instanceof BigDecimal) ? ((BigDecimal) employee.get("worker_perday_salary")).doubleValue() : 0.0;
//
//                    Double f_workerPerDaySalary = 0.0;
//                    CDepartmentViewModel currentEmplSubDept = allDepartments.parallelStream()
//                            .filter(dept -> dept.getDepartment_id() == employee.getSub_department_id())
//                            .findFirst()
//                            .orElse(null);
//                    f_workerPerDaySalary  = FnGetWorkerPerDaySalary(currentEmplSubDept, employee);
//
////                    f_workerPerDaySalary = currentEmplSubDept.getWorker_perday_salary();
//                    Double f_workerPerDayAttAllow = currentEmplSubDept.getWorker_perday_attendance_allowance();
//                    Double f_workerPerDayNightAllow = currentEmplSubDept.getWorker_perday_night_allowance();
//
//                    f_salaryPerDay = f_workerPerDaySalary;
//                    f_SalaryPerHrs = f_salaryPerDay / f_shiftFixedHrs;
//
////                  // *** Getting worker's working hrs for current month getting from monthly attendance;
//                    c_MonthlyWorkedHrs = currentEmplMonthlyAttendance.getMonthly_hours();
//                    c_calculatedSalary = c_MonthlyWorkedHrs * f_SalaryPerHrs;
//                    earningHeadsNameMap.put("Basic Salary", formatDecimalVal.format(c_calculatedSalary));
//                    combinedHeadsNameMap.put("Basic Salary", formatDecimalVal.format(c_calculatedSalary));
//
////                  // *** Worker Attendance & Night Allowances Calculations.
//                    Double c_nightAllowance = 0.0, c_attendance_allowance = 0.0;
////                  Double c_emplAttAllowDays = currentEmplMonthlyAttendance.getPresent_days() + currentEmplMonthlyAttendance.getHalf_days() + currentEmplMonthlyAttendance.getOd_days();
//                    Double c_emplAttAllowDays = (currentEmplMonthlyAttendance.getPresent_days() + currentEmplMonthlyAttendance.getHalf_days() + currentEmplMonthlyAttendance.getOd_days() + currentEmplMonthlyAttendance.getCoff_days());
//
//                    if (attendanceAllowanceDays.get() != 0 && attendanceAllowanceDays.get() <= c_emplAttAllowDays) {
//                        c_attendance_allowance = c_emplAttAllowDays * f_workerPerDayAttAllow;
//                        earningHeadsNameMap.put("Attend Allow", formatDecimalVal.format(c_attendance_allowance));
//                        combinedHeadsNameMap.put("Attend Allow", formatDecimalVal.format(c_attendance_allowance));
//                    }
//
//                    c_nightAllowance = currentEmplMonthlyAttendance.getNight_days() * f_workerPerDayNightAllow;
//                    earningHeadsNameMap.put("Night Allow", formatDecimalVal.format(c_nightAllowance));
//                    combinedHeadsNameMap.put("Night Allow", formatDecimalVal.format(c_nightAllowance));
//
//                    // Calculate Worker Present Day as per the totalWorkedHrs / shift_hrs. (So Updating the Salary Days.)
//                    f_totalSalaryDays = c_MonthlyWorkedHrs / f_shiftFixedHrs;
//                    // Round to the nearest whole number and cast back to double
//                    f_totalSalaryDays = (double) Math.round(f_totalSalaryDays);
//
//                    System.out.println("\nEmpl_Code: " + currentEmplCode + "\tMonthDays: " + f_CurrMonthDays + "\tWeeklyOffDays: " + f_CurrMonthWOffsDays + "\tSalaryDays: " + f_totalSalaryDays);
//                    System.out.println("F_PerDaySalary: " + f_salaryPerDay + "\tF_HrHalary: " + f_SalaryPerHrs + "\tc_BasicSal: " + c_calculatedSalary + "\tc_WorkedHrs: " + c_MonthlyWorkedHrs);
//                  // *********** Worker Basic & Other Allowance & Attend. Allow. & Night Allowance Earning calculations ends.
//                   // *******************************************************************************************************************************************


//                  // ************************ Worker JobTypeWise Salary Changes After 02/08/2024 Start's **********************************************************
							Map<String, Double> stringDoubleMap = FnCalculateWrkSalaryJobTypeCodeWise(employee, currentEmplMonthlyAttendance,
									allEmplJobTypeCodeWiseMonthlyAttList, earningHeadsNameMap, combinedHeadsNameMap, deductionHeadsNameMap, allJobTypeDetails, hrPayrollSettings, allEmplJobTypeCodeWiseMonthlyAttListActual);

							f_SalaryPerHrs = stringDoubleMap.getOrDefault("f_SalaryPerHrs", 0.0);
							c_MonthlyWorkedHrs = stringDoubleMap.getOrDefault("c_MonthlyWorkedHrs", 0.0);
							c_calculatedSalary = stringDoubleMap.getOrDefault("c_calculatedSalary", 0.0);

//                    c_totalEarning
//                    f_SalaryPerHrs
//                    c_MonthlyWorkedHrs
//                    c_calculatedSalary

//                  // ************************ Worker JobTypeWise Salary Changes After 02/08/2024 Ends's **********************************************************

							///**Calculation for semi-staffs using total monthly hours
						}

						earningHeadsNameMap.put("Arrear Paid", curEmployeeEarningMappingsMap.containsKey("Arrear Paid") ? curEmployeeEarningMappingsMap.get("Arrear Paid").getCalculation_value() : 0.0);
						combinedHeadsNameMap.put("Arrear Paid", curEmployeeEarningMappingsMap.containsKey("Arrear Paid") ? curEmployeeEarningMappingsMap.get("Arrear Paid").getCalculation_value() : 0.0);

//                // Commented because In Pashupati have another salary structure. (They have structure as above depends on workertype)
//                Double c_basicSalary = c_calculatedSalary * 0.50;
//                earningHeadsNameMap.put("Basic Salary", formatDecimalVal.format(c_basicSalary));
//                combinedHeadsNameMap.put("Basic Salary", formatDecimalVal.format(c_basicSalary));
//                Double c_OtherAllowncesSalary = c_calculatedSalary - c_basicSalary;
//                earningHeadsNameMap.put("Other Allowance", formatDecimalVal.format(c_OtherAllowncesSalary));
//                combinedHeadsNameMap.put("Other Allowance", formatDecimalVal.format(c_OtherAllowncesSalary));

//				// Sum the values in the earningHeadsNameMap, excluding the "Salary" key
						Double c_totalEarning = earningHeadsNameMap.entrySet().stream().filter(entry -> !entry.getKey().equalsIgnoreCase("Salary")).mapToDouble(entry -> Double.parseDouble(entry.getValue().toString())).sum();
						earningHeadsNameMap.put("Total Earning", FnGetCeilingValue(c_totalEarning));
						combinedHeadsNameMap.put("Total Earning", FnGetCeilingValue(c_totalEarning));
//				// ** Salary calculation Ends For Earnings;

//				// ********* Salary calculation starts For Deductions;
						Set<String> excludedKeysInDeductionSum = Stream.of("E-PF1", "E-PF2").map(String::toLowerCase).collect(Collectors.toSet());

						if (pfIsApplicable && !currentEmplTypeGroup.equals("Workers")) {
							Double c_pf = 0.0;
							Double c_basic = earningHeadsNameMap.containsKey("Basic Salary") ? Double.parseDouble((String) earningHeadsNameMap.get("Basic Salary")) : 0.0;

							if (pfAmountLimit.get() != 0 && c_basic < pfAmountLimit.get()) {
//                        c_pf = c_basicSalary * 0.12;
								CHmDeductionHeadsModel PFDeductionHead = deductionHeadsRef.get().parallelStream().filter(obj -> "PF".equals(obj.getDeduction_head_name())).findFirst().orElse(null);
								processDeductionHead(PFDeductionHead, "PF", deductionHeadsNameMap, combinedHeadsNameMap);
							} else {
								c_pf = (pfAmountLimit.get() != null) ? pfAmountLimit.get() * 0.12 : 0.0;
								deductionHeadsNameMap.put("PF", formatDecimalVal.format(c_pf));
								combinedHeadsNameMap.put("PF", formatDecimalVal.format(c_pf));
							}

//                   // ** EPF-1 Calculation
							CHmDeductionHeadsModel EPF1DeductionHead = deductionHeadsRef.get().parallelStream().filter(obj -> "E-PF1".equals(obj.getDeduction_head_name())).findFirst().orElse(null);
							processDeductionHead(EPF1DeductionHead, EPF1DeductionHead.getDeduction_head_name(), deductionHeadsNameMap, combinedHeadsNameMap);

//                   // ** EPF-2 Calculation
							CHmDeductionHeadsModel EPF2DeductionHead = deductionHeadsRef.get().parallelStream().filter(obj -> "E-PF2".equals(obj.getDeduction_head_name())).findFirst().orElse(null);
							processDeductionHead(EPF2DeductionHead, EPF2DeductionHead.getDeduction_head_name(), deductionHeadsNameMap, combinedHeadsNameMap);
						}

						CHmDeductionHeadsModel PTDeductionHead = deductionHeadsRef.get().parallelStream().filter(obj -> "Prof Tax".equals(obj.getDeduction_head_name())).findFirst().orElse(null);
						processDeductionHead(PTDeductionHead, PTDeductionHead.getDeduction_head_name(), deductionHeadsNameMap, combinedHeadsNameMap);

//              // ** Add the canteen_deduction; (Total_Present_Days * Perday_Canteen_Amt)
						allEmplDeductionMappingsList.parallelStream()
								.filter(deductionMaping ->
										deductionMaping.getEmployee_id() == currentEmplId && currentEmplCode.equals(deductionMaping.getEmployee_code())
												&& (deductionMaping.getDeduction_heads_id() == 15 || "Canteen".equals(deductionMaping.getDeduction_head_name()))
								)
								.findFirst()
								.ifPresent(canteenDeduction -> {
											double totalCanteenDed = canteenDeduction.getCalculation_value() * currentEmplMonthlyAttendance.getPresent_days();
											deductionHeadsNameMap.put(canteenDeduction.getDeduction_head_name(), formatDecimalVal.format(totalCanteenDed));
											combinedHeadsNameMap.put(canteenDeduction.getDeduction_head_name(), formatDecimalVal.format(totalCanteenDed));
										}
								);

//				// ** Also add the monthly deductions(as per monthly deduction upload).
						deductionHeadsRef.get().forEach(deduction -> {
							currentEmplMonthlyDeductions.parallelStream().filter(monthlyDeduction -> monthlyDeduction.getDeduction_type_id().equals(deduction.getDeduction_heads_id())).findFirst().ifPresent(monthlyDeduction -> {
								deductionHeadsNameMap.put(deduction.getDeduction_head_name(), formatDecimalVal.format(monthlyDeduction.getDeduction_amount()));
								combinedHeadsNameMap.put(deduction.getDeduction_head_name(), formatDecimalVal.format(monthlyDeduction.getDeduction_amount()));
							});
						});

						Double c_totalDeductions = deductionHeadsNameMap.entrySet().stream().filter(entry -> !excludedKeysInDeductionSum.contains(entry.getKey().toLowerCase())).mapToDouble(entry -> Double.parseDouble(entry.getValue().toString())).sum();

						deductionHeadsNameMap.put("Total Deduction", c_totalDeductions);
						combinedHeadsNameMap.put("Total Deduction", c_totalDeductions);
//				// ********* Salary calculation Ends For Deductions;
						c_netPayableSalary = FnGetCeilingValue(c_totalEarning) - FnGetCeilingValue(c_totalDeductions); // Net Salary
					}

					emplSalaryDtl.put("employee_id", currentEmplId);
					emplSalaryDtl.put("employee_name", employee.getEmployee_name());
					emplSalaryDtl.put("employee_code", currentEmplCode);
					emplSalaryDtl.put("employee_type", currentEmplType);
					emplSalaryDtl.put("department_name", employee.getDepartment_name());
					emplSalaryDtl.put("department_id", employee.getDepartment_id());
					emplSalaryDtl.put("sub_department_id", employee.getSub_department_id());
					emplSalaryDtl.put("sub_department_name", employee.getSub_department_name());
					emplSalaryDtl.put("designation_id", employee.getDesignation_id());
					emplSalaryDtl.put("Month Days", daysInMonth);
					emplSalaryDtl.put("Present Days", formatDecimalVal.format(f_totalSalaryDays));
					emplSalaryDtl.put("old_employee_code", employee.getOld_employee_code());
					if (currentEmplTypeGroup.equals("Workers")) {
//                emplSalaryDtl.put("Worked Hrs", formatDecimalVal.format(c_MonthlyWorkedHrs));
//                emplSalaryDtl.put("PerDay Salary", formatDecimalVal.format(f_salaryPerDay));
//                emplSalaryDtl.put("PerHr Salary", formatDecimalVal.format(f_SalaryPerHrs));

						emplSalaryDtl.put("Worked Hrs", formatDecimalVal.format(c_MonthlyWorkedHrs));
//                emplSalaryDtl.put("PerDay Salary", formatDecimalVal.format(0.0));
//                emplSalaryDtl.put("PerHr Salary", formatDecimalVal.format(0.0));
					}
					emplSalaryDtl.putAll(earningHeadsNameMap); // put all default earning heads with default value 0 into dtls;
					emplSalaryDtl.putAll(deductionHeadsNameMap); // all default deduction heads with default value 0 into dtls;
					emplSalaryDtl.put("Net Salary", FnGetCeilingValue(c_netPayableSalary));

					salaryProcessDtls.add(emplSalaryDtl); // Add it in list;
				});
		responce.put("success", 1);
		responce.put("EarningHeadsCols", defaultEarningHeadsNameMap);
		responce.put("DeductionHeadsCols", defaultDeductionHeadsNameMap);
		responce.put("data", salaryProcessDtls);
		responce.put("error", "");
		return responce;
	}

	//    // ******* Function For calculate the Worker Salary From Attendace;
	private Map<String, Double> FnCalculateWrkSalaryJobTypeCodeWise(CEmployeesViewModel employee, CHtAttendanceMonthlyNewModel currentEmplMonthlyAttendance,
	                                                                List<CHtAttendanceMonthlyJobTypeNewModel> allEmplJobTypeCodeWiseMonthlyAttList,
	                                                                HashMap<String, Object> earningHeadsNameMap, HashMap<String, Object> combinedHeadsNameMap,
	                                                                HashMap<String, Object> deductionHeadsNameMap, List<CJobTypeModel> allJobTypeDetails, CHmHrpayrollSettingsModel hrPayrollSettings, List<CHtAttendanceMonthlyJobTypeModel> allEmplJobTypeCodeWiseMonthlyAttListActual) {
		Map<String, Double> salaryDetails = new HashMap<>();
		// ** Some Settings From HR_SettingsTbl
		Double attendanceAllowanceDays = hrPayrollSettings.getAttendance_allowance_days();
		Double nightAllowanceDays = hrPayrollSettings.getNight_allowance_days();

		// Calculate totalShiftHours from the shift time string
		String shiftStartAndEndTime = employee.getCurrent_shift_start_end_time(); // Assuming you have the employee object
		Double totalShiftHours = FnCalculateWorkingHours(shiftStartAndEndTime); // You need to have this method defined as shown in the previous answer


		// Initiallized Calculation's Fields;
		Double c_totalEarning = 0.0, c_calculatedWrkSalary = 0.0, c_calculatedWrkSalaryActual = 0.0,
				c_attendanceAllow = 0.0, c_nightAllow = 0.0, c_specialAllow = 0.0,
				c_MonthlyWorkedDays = 0.0, c_MonthlyWorkedHrs = 0.0;

		// *** Calculation's started;
		c_calculatedWrkSalary = FnSumOfField(
				allEmplJobTypeCodeWiseMonthlyAttList,
				"job_type_total_salary",
				item -> item.getEmployee_code().equals(employee.getEmployee_code())
		);

		c_calculatedWrkSalaryActual = FnSumOfField(
				allEmplJobTypeCodeWiseMonthlyAttListActual,
				"job_type_total_salary",
				item -> item.getEmployee_code().equals(employee.getEmployee_code())
		);

		// Calculate difference between calculated salary and actual salary
		double c_salaryDifference = c_calculatedWrkSalary - c_calculatedWrkSalaryActual;

//      Cast to an integer to remove the decimal part
		int c_salaryDifferenceWhole = (int) c_salaryDifference;

		deductionHeadsNameMap.put("Canteen", formatDecimalVal.format(c_salaryDifferenceWhole));
		combinedHeadsNameMap.put("Canteen", formatDecimalVal.format(c_salaryDifferenceWhole));

		CJobTypeModel currentEmplJobType = allJobTypeDetails.parallelStream()
				.filter(item -> item.getJob_type_id() == employee.getJob_type_id())
				.findFirst().orElseGet(null);


		earningHeadsNameMap.put("Basic Salary", formatDecimalVal.format(c_calculatedWrkSalary));
		combinedHeadsNameMap.put("Basic Salary", formatDecimalVal.format(c_calculatedWrkSalary));

		// ** Add the calculated HourlyBasis Salary into total_earning;
		c_totalEarning = +c_calculatedWrkSalary;

		// Total PresentDays;
//        Double totalPresentDays = currentEmplMonthlyAttendance.getPresent_days() + currentEmplMonthlyAttendance.getHalf_days();
		Double totalPresentDays = currentEmplMonthlyAttendance.getTotal_salary_days();
		c_MonthlyWorkedHrs = currentEmplMonthlyAttendance.getMonthly_hours();

		// Check if the monthly worked hours exceed the total shift hours
		if (c_MonthlyWorkedHrs > totalShiftHours) {
			c_MonthlyWorkedHrs = totalShiftHours * currentEmplMonthlyAttendance.getTotal_salary_days();
		}

		if (attendanceAllowanceDays != 0 && attendanceAllowanceDays <= totalPresentDays) {
			c_attendanceAllow = currentEmplJobType.getJob_type_attendance_allowance() * totalPresentDays;
			earningHeadsNameMap.put("Attend Allow", formatDecimalVal.format(c_attendanceAllow));
			combinedHeadsNameMap.put("Attend Allow", formatDecimalVal.format(c_attendanceAllow));
		}

		c_nightAllow = currentEmplJobType.getJob_type_night_allowance() * currentEmplMonthlyAttendance.getNight_days();
		earningHeadsNameMap.put("Night Allow", formatDecimalVal.format(c_nightAllow));
		combinedHeadsNameMap.put("Night Allow", formatDecimalVal.format(c_nightAllow));

		if (nightAllowanceDays != 0 && nightAllowanceDays <= currentEmplMonthlyAttendance.getNight_days()) {
			c_specialAllow = currentEmplJobType.getJob_type_special_allowance() * currentEmplMonthlyAttendance.getNight_days();
			earningHeadsNameMap.put("Special Allow", formatDecimalVal.format(c_specialAllow));
			combinedHeadsNameMap.put("Special Allow", formatDecimalVal.format(c_specialAllow));
		}


//        // Put Data In the Map It Requires There;
		salaryDetails.put("c_totalEarning", c_totalEarning);
		salaryDetails.put("c_MonthlyWorkedHrs", c_MonthlyWorkedHrs);
		salaryDetails.put("c_calculatedSalary", c_calculatedWrkSalary);
		return salaryDetails;
	}

	public Double FnGetWorkerPerDaySalary(CDepartmentViewModel currentEmplDept, CEmployeesViewModel currentEmplDtl) {
		final int TRAINEE_WORKER_GRADE_ID = 84;
		final int SEMI_SKILLED_WORKER_GRADE_ID = 85;
		final int SKILLED_WORKER_GRADE_ID = 86;
		Double workerPerDaySalary = 0.0;
		switch (currentEmplDtl.getGrade_id()) {
			case TRAINEE_WORKER_GRADE_ID:
				workerPerDaySalary = currentEmplDept.getTrainee_worker_perday_salary();
				return workerPerDaySalary;

			case SEMI_SKILLED_WORKER_GRADE_ID:
				workerPerDaySalary = currentEmplDept.getSemiskillled_worker_perday_salary();
				return workerPerDaySalary;

			case SKILLED_WORKER_GRADE_ID:
				workerPerDaySalary = currentEmplDept.getWorker_perday_salary();
				return workerPerDaySalary;

			default:
				workerPerDaySalary = currentEmplDept.getWorker_perday_salary();
				return workerPerDaySalary;
		}
	}
}