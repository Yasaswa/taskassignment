package com.erp.HmShiftManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CEmployeeMissPunchDetailsRequest {
	private int company_id;
	private String employee_type_group;
	private String employee_code;
	private String old_employee_code;
	private int employee_id;
	private String shift_name;
	private boolean two_day_shift;
	private String attendance_from_date;
	private String attendance_to_date;
	private Integer department_id;
	private Integer sub_department_id;
}