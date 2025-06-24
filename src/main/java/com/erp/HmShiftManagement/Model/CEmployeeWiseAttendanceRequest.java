package com.erp.HmShiftManagement.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Transient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CEmployeeWiseAttendanceRequest {
	private int company_id;
	private String employee_type_group;
	private String employee_code;
	private String old_employee_code;
	private String employee_id;
	private String shift_name;
	private boolean two_day_shift;
	private String attendance_date;
	private Integer department_id;
	private Integer sub_department_id;

	@Transient
	private String dt_att_To_date;
}
